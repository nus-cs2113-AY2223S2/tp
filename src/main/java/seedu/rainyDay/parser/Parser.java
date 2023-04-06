package seedu.rainyDay.parser;

import seedu.rainyDay.RainyDay;
import seedu.rainyDay.command.Command;
import seedu.rainyDay.command.ExitCommand;
import seedu.rainyDay.command.ExportCommand;
import seedu.rainyDay.command.ShortcutDeleteCommand;
import seedu.rainyDay.command.ShortcutViewCommand;
import seedu.rainyDay.exceptions.ErrorMessage;
import seedu.rainyDay.exceptions.RainyDayException;
import seedu.rainyDay.modules.Ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//@@author azriellee
public class Parser {
    private static final Logger logger = Logger.getLogger(Parser.class.getName());

    public Command parseUserInput(String userInput) throws RainyDayException {
        try {
            assert userInput != null : "Failed to read user input!";
            String[] action = userInput.split("\\s+", 2);
            if (action[0].equalsIgnoreCase(Command.COMMAND_ADD)) {
                logger.info("add command executing");
                return ParseAdd.addStatement(userInput);
            } else if (action[0].equalsIgnoreCase(Command.COMMAND_DELETE)) {
                logger.info("delete command executing");
                return ParseDelete.parseDeleteStatement(userInput);
            } else if (action[0].equalsIgnoreCase(Command.COMMAND_VIEW)) {
                logger.info("view command executing");
                return ParseView.generateReport(userInput);
            } else if (action[0].equalsIgnoreCase(Command.COMMAND_HELP)) {
                logger.info("help command executing");
                return ParseHelp.displayHelp(userInput.trim());
            } else if (action[0].equalsIgnoreCase(Command.COMMAND_FILTER)) {
                logger.info("filter command executing");
                return ParseFilter.filterStatement(userInput);
            } else if (action[0].equalsIgnoreCase(Command.COMMAND_EDIT)) {
                logger.info("edit command executing");
                return ParseEdit.editStatement(userInput);
            } else if (action[0].equalsIgnoreCase(Command.COMMAND_EXPORT)) {
                logger.info("export command executing");
                return new ExportCommand();
            } else if (action[0].equalsIgnoreCase(Command.COMMAND_SHORTCUT)) {
                logger.info("shortcut command executing");
                return ParseShortcut.generateShortcut(action[1].trim());
            } else if (action[0].equalsIgnoreCase(Command.COMMAND_SET_BUDGET)) {
                logger.info("set budget command executing");
                return ParseSetBudget.setUserBudgetGoal(action[1].trim());
            } else if (action[0].equalsIgnoreCase(Command.COMMAND_DELETE_SHORTCUT)) {
                logger.info("delete_shortcut command executing");
                return new ShortcutDeleteCommand(action[1].trim());
            } else if (action[0].equalsIgnoreCase(Command.COMMAND_VIEW_SHORTCUT)) {
                logger.info("view_shortcut command executing");
                return new ShortcutViewCommand();
            } else if (action[0].equalsIgnoreCase(Command.COMMAND_IGNORE)) {
                logger.info("ignore command executing");
                return ParseIgnore.ignoreStatement(userInput);
            } else if (action[0].equalsIgnoreCase(Command.COMMAND_UNIGNORE)) {
                logger.info("ignore command executing");
                return ParseIgnore.ignoreStatement(userInput);
            } else if (action[0].equalsIgnoreCase(Command.COMMAND_EXIT)) {
                logger.info("exit command executing");
                return new ExitCommand();
            } else {
                // check if the user has a shortcut command
                HashMap<String, String> shortcutCommands = RainyDay.savedData.getShortcutCommands();
                if (shortcutCommands.containsKey(userInput)) {
                    return parseUserInput(processShortcutUsage(shortcutCommands, userInput));
                }
                logger.warning("unrecognised input from user!");
                throw new RainyDayException(ErrorMessage.UNRECOGNIZED_INPUT.toString());
            }
        } catch (RainyDayException e) {
            throw new RainyDayException(e.getMessage());
        }
    }

    public static LocalDate setDate(String input) throws RainyDayException {
        Pattern pattern = Pattern.compile("-date\\s+(.*)");
        Matcher matcher = pattern.matcher(input);
        if (matcher.matches()) {
            String inputDate = matcher.group(1).trim();
            String[] dateMonthYear = inputDate.split("/");
            return checkValidDateAndSet(dateMonthYear);
        } else {
            logger.warning("add command given by user in the wrong format");
            throw new RainyDayException(ErrorMessage.INVALID_DATE_FORMAT.toString());
        }
    }

    private static LocalDate checkValidDateAndSet(String[] dateMonthYear) throws RainyDayException {
        if (dateMonthYear.length != 3) {
            logger.warning("add command given by user in the wrong format");
            throw new RainyDayException(ErrorMessage.INVALID_DATE_FORMAT.toString());
        }
        String day = getDay(dateMonthYear[0]);
        String month = getMonth(dateMonthYear[1]);
        String year = getYear(dateMonthYear[2]);
        String inputDate = day + "/" + month + "/" + year;
        LocalDate date = LocalDate.parse(inputDate, DateTimeFormatter.ofPattern("dd/MM/uuuu"));
        checkMatchDate(day, month, year, date);
        return date;
    }

    private static String getDay(String day) throws RainyDayException {
        if (!isStringOfInteger(day)) {
            logger.warning("add command given by user in the wrong format");
            throw new RainyDayException(ErrorMessage.INVALID_DAY.toString());
        }
        if (day.length() == 1) {
            return "0" + day;
        }
        int dayInt = Integer.parseInt(day);
        if (dayInt > 31) {
            logger.warning("add command given by user in the wrong format");
            throw new RainyDayException(ErrorMessage.INVALID_DAY.toString());
        }
        return day;
    }

    private static String getMonth(String month) throws RainyDayException {
        if (!isStringOfInteger(month)) {
            logger.warning("add command given by user in the wrong format");
            throw new RainyDayException(ErrorMessage.INVALID_MONTH.toString());
        }
        if (month.length() == 1) {
            return "0" + month;
        }
        int monthInt = Integer.parseInt(month);
        if (monthInt > 12) {
            logger.warning("add command given by user in the wrong format");
            throw new RainyDayException(ErrorMessage.INVALID_MONTH.toString());
        }
        return month;
    }

    private static String getYear(String year) throws RainyDayException {
        if (!isStringOfInteger(year)) {
            logger.warning("add command given by user in the wrong format");
            throw new RainyDayException(ErrorMessage.INVALID_YEAR.toString());
        }
        if (year.length() != 4) {
            logger.warning("add command given by user in the wrong format");
            throw new RainyDayException(ErrorMessage.INVALID_YEAR.toString());
        }
        return String.format("%04d", Integer.parseInt(year));
    }

    /**
     * Checks whether a given string only contains digit characters
     *
     * @param input input given by the user
     * @return true if input only contains digit characters, false otherwise
     */
    private static boolean isStringOfInteger(String input) {
        char[] inputInArray = input.toCharArray();
        for (char c : inputInArray) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }

    private static void checkMatchDate(String day, String month, String year, LocalDate date) throws RainyDayException {
        boolean isMatchDay = Integer.parseInt(day) == date.getDayOfMonth();
        boolean isMatchMonth = Integer.parseInt(month) == date.getMonthValue();
        boolean isMatchYear = Integer.parseInt(year) == date.getYear();
        if (!isMatchDay || !isMatchMonth || !isMatchYear) {
            throw new RainyDayException(ErrorMessage.INVALID_DATE.toString());
        }
    }

    private String processShortcutUsage(HashMap<String, String> shortcutCommands, String shortcut) {
        String actualCommand = shortcutCommands.get(shortcut);
        Ui.printShortCutConfirmation(shortcut, actualCommand);
        return shortcutCommands.get(shortcut);
    }
}
