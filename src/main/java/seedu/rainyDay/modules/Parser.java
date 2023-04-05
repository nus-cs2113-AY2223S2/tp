package seedu.rainyDay.modules;

import seedu.rainyDay.RainyDay;
import seedu.rainyDay.command.AddCommand;
import seedu.rainyDay.command.Command;
import seedu.rainyDay.command.DeleteCommand;
import seedu.rainyDay.command.EditCommand;
import seedu.rainyDay.command.ExitCommand;
import seedu.rainyDay.command.ExportCommand;
import seedu.rainyDay.command.FilterCommand;
import seedu.rainyDay.command.HelpCommand;
import seedu.rainyDay.command.IgnoreCommand;
import seedu.rainyDay.command.SetBudgetCommand;
import seedu.rainyDay.command.ShortcutAddCommand;
import seedu.rainyDay.command.ShortcutDeleteCommand;
import seedu.rainyDay.command.ShortcutViewCommand;
import seedu.rainyDay.command.ViewCommand;
import seedu.rainyDay.exceptions.ErrorMessage;
import seedu.rainyDay.exceptions.RainyDayException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//@@author azriellee
public class Parser {
    private static final Logger logger = Logger.getLogger(Parser.class.getName());
    private String direction;
    private String description;
    private String category;
    private double amount = -1.0;
    private LocalDate date;

    public Command parseUserInput(String userInput) throws RainyDayException {
        try {
            assert userInput != null : "Failed to read user input!";
            String[] action = userInput.split("\\s+", 2);
            if (action[0].equalsIgnoreCase(Command.COMMAND_ADD)) {
                logger.info("add command executing");
                return addStatement(userInput);
            } else if (action[0].equalsIgnoreCase(Command.COMMAND_DELETE)) {
                logger.info("delete command executing");
                return parseDeleteStatement(userInput);
            } else if (action[0].equalsIgnoreCase(Command.COMMAND_VIEW)) {
                logger.info("view command executing");
                return generateReport(userInput);
            } else if (action[0].equalsIgnoreCase(Command.COMMAND_HELP)) {
                logger.info("help command executing");
                return displayHelp(userInput.trim());
            } else if (action[0].equalsIgnoreCase(Command.COMMAND_FILTER)) {
                logger.info("filter command executing");
                return filterStatement(userInput);
            } else if (action[0].equalsIgnoreCase(Command.COMMAND_EDIT)) {
                logger.info("edit command executing");
                return editStatement(userInput);
            } else if (action[0].equalsIgnoreCase(Command.COMMAND_EXPORT)) {
                logger.info("export command executing");
                return new ExportCommand();
            } else if (action[0].equalsIgnoreCase(Command.COMMAND_SHORTCUT)) {
                logger.info("shortcut command executing");
                return generateShortcut(action[1].trim());
            } else if (action[0].equalsIgnoreCase(Command.COMMAND_SET_BUDGET)) {
                logger.info("set budget command executing");
                return setUserBudgetGoal(action[1].trim());
            } else if (action[0].equalsIgnoreCase(Command.COMMAND_DELETE_SHORTCUT)) {
                logger.info("delete_shortcut command executing");
                return new ShortcutDeleteCommand(action[1].trim());
            } else if (action[0].equalsIgnoreCase(Command.COMMAND_VIEW_SHORTCUT)) {
                logger.info("view_shortcut command executing");
                return new ShortcutViewCommand();
            } else if (action[0].equalsIgnoreCase(Command.COMMAND_IGNORE)) {
                logger.info("ignore command executing");
                return ignoreStatement(userInput);
            } else if (action[0].equalsIgnoreCase(Command.COMMAND_UNIGNORE)) {
                logger.info("ignore command executing");
                return ignoreStatement(userInput);
            } else if (action[0].equalsIgnoreCase(Command.COMMAND_EXIT)) {
                logger.info("exit command executing");
                return exitCommand();
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

    private Command addStatement(String userInput) throws RainyDayException { // example: add -<in/out> <description>
        // $value -c -date
        try {
            String addInput = userInput.split("\\s+", 2)[1];
            this.category = "miscellaneous";
            this.date = LocalDate.now();
            String remainingInformation = returnRemainingInformation(addInput);
            logger.info("obtained mandatory information");
            if (remainingInformation.trim().isEmpty()) {
                logger.info("returning new AddCommand object");
                return new AddCommand(description.trim(), direction, amount, category, date);
            }
            if (!addInput.contains("-c") && !addInput.contains("-date")) {
                logger.info("returning new InvalidCommand object");
                throw new RainyDayException(ErrorMessage.WRONG_ADD_FORMAT.toString());
            }
            logger.info("checking for presence of -c");
            if (addInput.contains("-c")) {
                remainingInformation = setCategory(remainingInformation);
            }
            logger.info("checking for presence of -date");
            if (addInput.contains("-date")) {
                setDate(remainingInformation);
            }
            logger.info("returning new AddCommand object");
            return new AddCommand(description.trim(), direction, amount, category, date);
        } catch (IndexOutOfBoundsException e) {
            logger.warning("add command given by user in the wrong format");
            throw new RainyDayException(ErrorMessage.WRONG_ADD_FORMAT.toString());
        } catch (RainyDayException e) {
            throw new RainyDayException(e.getMessage() + ErrorMessage.ADD_FORMAT);
        }
    }

    private String returnRemainingInformation(String input) throws RainyDayException {
        try {
            int flag = 0;
            Pattern pattern = Pattern.compile("-(in|out)\\s+(.+)\\$([\\d.]+)");
            Matcher matcher = pattern.matcher(input);
            if (!matcher.matches()) {
                flag = 1;
                pattern = Pattern.compile("-(in|out)\\s+(.+)\\$([\\d.]+)\\s+(.*)");
                matcher = pattern.matcher(input);
                if (!matcher.matches()) {
                    logger.warning("add command given by user in the wrong format");
                    throw new RainyDayException(ErrorMessage.WRONG_ADD_FORMAT.toString());
                }
            }
            this.direction = matcher.group(1);
            this.description = matcher.group(2);
            if (this.description.contains("-")) {
                logger.warning("unsupported description name");
                throw new RainyDayException(ErrorMessage.UNSUPPORTED_DESCRIPTION_NAME.toString());
            }
            if (this.description.trim().isEmpty()) {
                logger.warning("unsupported description name");
                throw new RainyDayException(ErrorMessage.EMPTY_DESCRIPTION_NAME.toString());
            }
            double exactAmount = Double.parseDouble(matcher.group(3)); // check
            exactAmount = (int) (exactAmount * 100);
            if (exactAmount == 0) {
                throw new RainyDayException(ErrorMessage.WRONG_ADD_FORMAT.toString());
            }
            this.amount = exactAmount / 100;
            if (flag == 0) {
                logger.info("obtaining mandatory information");
                return "";
            }
            logger.info("obtaining mandatory information");
            return matcher.group(4);
        } catch (Exception e) {
            logger.warning("add command given by user in the wrong format");
            throw new RainyDayException(e.getMessage());
        }
    }

    //@@author lil1n
    private String setCategory(String input) throws RainyDayException {
        int flag = 0;
        Pattern pattern = Pattern.compile("-c\\s+(.+)\\s+-date\\s+(.*)");
        Matcher matcher = pattern.matcher(input);
        if (!matcher.matches()) {
            flag = 1;
            pattern = Pattern.compile("-c\\s+(.+)");
            matcher = pattern.matcher(input);
            if (!matcher.matches()) {
                logger.warning("add command given by user in the wrong format");
                throw new RainyDayException(ErrorMessage.EMPTY_CATEGORY_NAME.toString());
            }
        }
        this.category = matcher.group(1);
        checkCategoryName(this.category);
        if (flag == 0) {
            logger.info("obtaining category");
            return "-date " + matcher.group(2);
        }
        logger.info("obtaining category");
        return "";
    }

    private void checkCategoryName(String category) throws RainyDayException {
        if (category.trim().indexOf("-date") == 0) {
            logger.warning("empty category name");
            throw new RainyDayException(ErrorMessage.EMPTY_CATEGORY_NAME.toString());
        }
        if (category.contains("-date") && category.length() - "-date".length() == category.indexOf("-date")) {
            logger.warning("-date flag provided but no date provided");
            throw new RainyDayException(ErrorMessage.NO_DATE_PROVIDED.toString());
        }
        if (category.contains("-")) {
            logger.warning("unsupported category name");
            throw new RainyDayException(ErrorMessage.UNSUPPORTED_CATEGORY_NAME.toString());
        }
    }

    private void setDate(String input) throws RainyDayException {
        Pattern pattern = Pattern.compile("-date\\s+(.*)");
        Matcher matcher = pattern.matcher(input);
        if (matcher.matches()) {
            String inputDate = matcher.group(1).trim();
            String[] dateMonthYear = inputDate.split("/");
            checkValidDateAndSet(dateMonthYear);
        } else {
            logger.warning("add command given by user in the wrong format");
            throw new RainyDayException(ErrorMessage.INVALID_DATE_FORMAT.toString());
        }
    }

    public void checkValidDateAndSet(String[] dateMonthYear) throws RainyDayException {
        if (dateMonthYear.length != 3) {
            logger.warning("add command given by user in the wrong format");
            throw new RainyDayException(ErrorMessage.INVALID_DATE_FORMAT.toString());
        }
        String day = getDay(dateMonthYear[0]);
        String month = getMonth(dateMonthYear[1]);
        String year = getYear(dateMonthYear[2]);
        String inputDate = day + "/" + month + "/" + year;
        this.date = LocalDate.parse(inputDate, DateTimeFormatter.ofPattern("dd/MM/uuuu"));
        checkMatchDate(day, month, year, this.date);
    }

    private String getDay(String day) throws RainyDayException {
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

    private String getMonth(String month) throws RainyDayException {
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

    private String getYear(String year) throws RainyDayException {
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
    private boolean isStringOfInteger(String input) {
        char[] inputInArray = input.toCharArray();
        for (char c : inputInArray) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }

    private void checkMatchDate(String day, String month, String year, LocalDate date) throws RainyDayException {
        boolean isMatchDay = Integer.parseInt(day) == date.getDayOfMonth();
        boolean isMatchMonth = Integer.parseInt(month) == date.getMonthValue();
        boolean isMatchYear = Integer.parseInt(year) == date.getYear();
        if (!isMatchDay || !isMatchMonth || !isMatchYear) {
            throw new RainyDayException(ErrorMessage.INVALID_DATE.toString());
        }
    }

    //@@author azriellee
    public Command parseDeleteStatement(String userInput) throws RainyDayException {
        String[] tokens = userInput.split("\\s+");
        if (tokens.length != 2) {
            logger.warning("invalid delete index from user");
            throw new RainyDayException(ErrorMessage.NO_DELETE_INDEX.toString());
        }
        if (RainyDay.savedData.getFinancialReport().getStatementCount() == 0) {
            throw new RainyDayException(ErrorMessage.EMPTY_FINANCIAL_REPORT.toString());
        }
        try {
            int index = Integer.parseInt(tokens[1]);
            if (index > RainyDay.savedData.getFinancialReport().getStatementCount()) {
                throw new IllegalArgumentException();
            }
            if (index <= 0) {
                throw new IllegalArgumentException();
            }
            return new DeleteCommand(index);
        } catch (Exception e) {
            logger.warning("delete index not a valid number");
            throw new RainyDayException(String.format(ErrorMessage.WRONG_DELETE_INDEX.toString(),
                    RainyDay.savedData.getFinancialReport().getStatementCount() + "!"));
        }
    }

    //@@author BenjaminPoh
    public Command generateReport(String input) throws RainyDayException {
        input = input.substring(4).trim();
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = LocalDate.now();
        if (input.equals("")) {
            startDate = LocalDate.of(startDate.getYear(), startDate.getMonth(), 1);
            endDate = startDate.plusMonths(1);
            endDate = endDate.minusDays(1);
            return new ViewCommand(startDate, endDate, false, false);
        }
        if (input.equals("-sort")) {
            startDate = LocalDate.of(startDate.getYear(), startDate.getMonth(), 1);
            endDate = startDate.plusMonths(1);
            endDate = endDate.minusDays(1);
            return new ViewCommand(startDate, endDate, true, false);
        }
        Pattern pattern = Pattern.compile("^(-all)?\\s*((-sort)?)\\s*$");
        Matcher matcher = pattern.matcher(input);
        if (matcher.matches()) {
            try {
                boolean sortRequired = matcher.group(2).equals("-sort");
                startDate = LocalDate.of(1, 1, 1);
                endDate = LocalDate.of(9999, 12, 31);
                return new ViewCommand(startDate, endDate, sortRequired, true);
            } catch (Exception e) {
                logger.warning("view command given by user in the wrong format");
                throw new RainyDayException(ErrorMessage.WRONG_VIEW_FORMAT.toString());
            }
        }

        pattern = Pattern.compile("^(\\d{1,2})([dwmy])\\s*((-sort)?)\\s*$");
        matcher = pattern.matcher(input);
        if (matcher.matches()) {
            try {
                logger.info("obtaining relevant data");
                int minusAmount = Integer.parseInt(matcher.group(1));
                String dateType = matcher.group(2);
                boolean sortRequired = matcher.group(3).equals("-sort");
                if (dateType.equals("d") && minusAmount < 32) {
                    startDate = startDate.minusDays(minusAmount);
                    return new ViewCommand(startDate, endDate, sortRequired, false);
                }
                if (dateType.equals("w") && minusAmount < 5) {
                    startDate = startDate.minusWeeks(minusAmount);
                    return new ViewCommand(startDate, endDate, sortRequired, false);
                }
                if (dateType.equals("m") && minusAmount < 13) {
                    startDate = startDate.minusMonths(minusAmount);
                    return new ViewCommand(startDate, endDate, sortRequired, false);
                }
                if (dateType.equals("y") && minusAmount < 11) {
                    startDate = startDate.minusYears(minusAmount);
                    return new ViewCommand(startDate, endDate, sortRequired, false);
                }
                logger.warning("view command given by user in the wrong format");
                throw new RainyDayException(ErrorMessage.WRONG_VIEW_FORMAT.toString());
            } catch (Exception e) {
                logger.warning("view command given by user in the wrong format");
                throw new RainyDayException(ErrorMessage.WRONG_VIEW_FORMAT.toString());
            }
        } else {
            logger.warning("view command given by user in the wrong format");
            throw new RainyDayException(ErrorMessage.WRONG_VIEW_FORMAT.toString());
        }
    }

    //@@author BenjaminPoh
    public HelpCommand displayHelp(String input) {
        input = input.substring(4);
        return new HelpCommand(input.trim());
    }

    //@@author ChongQiRong
    private Command filterStatement(String userInput) throws RainyDayException {
        String[] action = userInput.split("\\s+", 2);
        if (action.length == 1) {
            logger.warning("No flags in filter");
            throw new RainyDayException(ErrorMessage.WRONG_FILTER_FORMAT.toString());
        }

        int sizeOfFilterFlagAndField = 0;
        String[] flagAndField = action[1].split("\\s");
        for (String s : flagAndField) {
            if (s.equals("-in")) {
                sizeOfFilterFlagAndField += 1;
            } else if (s.equals("-out")) {
                sizeOfFilterFlagAndField += 1;
            } else if (s.equals("-d")) {
                sizeOfFilterFlagAndField += 2;
            } else if (s.equals("-c")) {
                sizeOfFilterFlagAndField += 2;
            } else if (s.equals("-date")) {
                sizeOfFilterFlagAndField += 2;
            }
        }

        if (action[1].startsWith("-in") | action[1].startsWith("-out") | action[1].startsWith("-d")
                | action[1].startsWith("-c") | action[1].startsWith("-date")) {
            ArrayList<String> filterFlagAndField = parseFilterMultipleFlags(action[1], sizeOfFilterFlagAndField);
            return new FilterCommand(filterFlagAndField);
        } else {
            logger.warning("unrecognised input from user!");
            throw new RainyDayException(ErrorMessage.WRONG_FILTER_FORMAT.toString());
        }
    }

    private ArrayList<String> parseFilterMultipleFlags(String input, int sizeOfFilterFlagAndField) throws
            RainyDayException {
        Pattern pattern = Pattern.compile("(?:(-in|-out)\\s*)?\\s*" +
                "(?:(-d)\\s+([^\\s-]+(?:\\s+[^\\s-]+)*)\\s*)?" +
                "(?:(-c)\\s+([^\\s-]+(?:\\s+[^\\s-]+)*)\\s*)?" +
                "(.*)$");
        Matcher matcher = pattern.matcher(input);
        ArrayList<String> filterFlagAndField = new ArrayList<>();

        if (matcher.find()) {
            if (matcher.group(6).trim().indexOf("-date") != 0 && !matcher.group(6).equals("")) {
                throw new RainyDayException(ErrorMessage.WRONG_EDIT_FORMAT.toString());
            }
            for (int i = 1; i <= 5; i += 1) {
                if (matcher.group(i) == null) {
                    continue;
                }
                filterFlagAndField.add(matcher.group(i));
            }
            if (matcher.group(6).contains("-date")) {
                try {
                    setDate(matcher.group(6));
                    DateTimeFormatter formatters = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    String dateString = this.date.format(formatters);
                    filterFlagAndField.add("-date");
                    filterFlagAndField.add(dateString);
                } catch (RainyDayException e) {
                    throw new RainyDayException(e.getMessage() + ErrorMessage.FILTER_FORMAT);
                }
            }
            if (filterFlagAndField.size() != sizeOfFilterFlagAndField) {
                logger.warning("filter command given by user in the wrong format");
                throw new RainyDayException(ErrorMessage.WRONG_FILTER_FORMAT.toString());
            }
            return filterFlagAndField;
        } else {
            logger.warning("filter command given by user in the wrong format");
            throw new RainyDayException(ErrorMessage.WRONG_FILTER_FORMAT.toString());
        }
    }

    private Command editStatement(String userInput) throws RainyDayException {
        String[] tokens = userInput.split("\\s+", 3);
        if (tokens.length < 3) {
            logger.warning("invalid edit index from user");
            throw new RainyDayException(ErrorMessage.WRONG_EDIT_FORMAT.toString());
        }

        int lengthOfReport = RainyDay.savedData.getFinancialReport().getStatementCount();
        if (lengthOfReport == 0) {
            throw new RainyDayException(ErrorMessage.EMPTY_FINANCIAL_REPORT.toString());
        }

        int index = Integer.parseInt(tokens[1]);
        if (index > lengthOfReport || index <= 0) {
            logger.warning("invalid edit index from user");
            throw new RainyDayException(String.format(ErrorMessage.WRONG_EDIT_INDEX.toString(),
                    lengthOfReport + "!"));
        }

        int sizeOfEditFlagAndField = 0;
        String[] flagAndField = tokens[2].split("\\s");
        for (String s : flagAndField) {
            if (s.equals("-in")) {
                sizeOfEditFlagAndField += 1;
            } else if (s.equals("-out")) {
                sizeOfEditFlagAndField += 1;
            } else if (s.equals("-d")) {
                sizeOfEditFlagAndField += 2;
            } else if (s.equals("-v")) {
                sizeOfEditFlagAndField += 2;
            } else if (s.equals("-c")) {
                sizeOfEditFlagAndField += 2;
            } else if (s.equals("-date")) {
                sizeOfEditFlagAndField += 2;
            }
        }

        if (tokens[2].startsWith("-in") | tokens[2].startsWith("-out") | tokens[2].startsWith("-d")
                | tokens[2].startsWith("-c") | tokens[2].startsWith("-v") | tokens[2].startsWith("-date")) {
            ArrayList<String> editFlagAndField = parseEditMultipleFlags(tokens[2], sizeOfEditFlagAndField);
            return new EditCommand(index, editFlagAndField);
        } else {
            logger.warning("unrecognised edit input from user!");
            throw new RainyDayException(ErrorMessage.WRONG_EDIT_FORMAT.toString());
        }
    }

    private ArrayList<String> parseEditMultipleFlags(String input, int sizeOfEditFlagAndField) throws
            RainyDayException {
        Pattern pattern = Pattern.compile("(?:(-in|-out)\\s*)?\\s*" +
                "(?:(-d)\\s+([^\\s-]+(?:\\s+[^\\s-]+)*)\\s*)?" +
                "(?:(-v)\\s+\\$([\\d]+(?:\\.?[\\d]){0,2})\\s*)?" +
                "(?:(-c)\\s+([^\\s-]+(?:\\s+[^\\s-]+)*)\\s*)?" +
                "(.*)$");
        Matcher matcher = pattern.matcher(input);
        ArrayList<String> editFlagAndField = new ArrayList<>();

        if (matcher.find()) {
            if (matcher.group(8).trim().indexOf("-date") != 0 && !matcher.group(8).equals("")) {
                throw new RainyDayException(ErrorMessage.WRONG_EDIT_FORMAT.toString());
            }
            for (int i = 1; i <= 7; i += 1) {
                if (matcher.group(i) == null) {
                    continue;
                }
                editFlagAndField.add(matcher.group(i));
            }

            if (matcher.group(8).contains("-date")) {
                try {
                    setDate(matcher.group(8));
                    DateTimeFormatter formatters = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    String dateString = this.date.format(formatters);
                    editFlagAndField.add("-date");
                    editFlagAndField.add(dateString);
                } catch (RainyDayException e) {
                    throw new RainyDayException(e.getMessage() + ErrorMessage.EDIT_FORMAT);
                }
            }

            if (editFlagAndField.size() == 0 || editFlagAndField.size() != sizeOfEditFlagAndField) {
                logger.warning("edit command given by user in the wrong format");
                throw new RainyDayException(ErrorMessage.WRONG_EDIT_FORMAT.toString());
            }
            return editFlagAndField;
        } else {
            logger.warning("edit command given by user in the wrong format");
            throw new RainyDayException(ErrorMessage.WRONG_EDIT_FORMAT.toString());
        }
    }

    //@@author KN-CY
    private Command generateShortcut(String userInput) throws RainyDayException {
        if (!userInput.contains(" -maps ")) {
            throw new RainyDayException(ErrorMessage.WRONG_SHORTCUT_FORMAT.toString());
        }
        String[] tokens = userInput.split(" -maps ");

        // check for > 1 instance of " -maps "
        if (tokens.length > 2) { //
            throw new RainyDayException(ErrorMessage.WRONG_SHORTCUT_FORMAT.toString());
        }
        String key = tokens[0];
        String value = tokens[1];

        // ensure that shortcut is a single word
        if (key.contains(" ")) {
            throw new RainyDayException(ErrorMessage.WRONG_SHORTCUT_FORMAT.toString());
        }
        return new ShortcutAddCommand(key, value);
    }

    //@@author KN-CY
    private String processShortcutUsage(HashMap<String, String> shortcutCommands, String shortcut) {
        String actualCommand = shortcutCommands.get(shortcut);
        Ui.printShortCutConfirmation(shortcut, actualCommand);
        return shortcutCommands.get(shortcut);
    }

    //@@author BenjaminPoh
    private Command setUserBudgetGoal(String userInput) throws RainyDayException {
        try {
            double amount = Double.parseDouble(userInput);
            amount = (int) (amount * 100);
            amount /= 100;
            if (amount < 0) {
                throw new IllegalArgumentException();
            }
            return new SetBudgetCommand(amount);
        } catch (Exception e) {
            logger.warning("set budget details provided incorrectly");
            throw new RainyDayException(ErrorMessage.WRONG_SET_BUDGET_FORMAT.toString());
        }
    }

    //@@author azriellee
    public Command ignoreStatement(String userInput) throws RainyDayException {
        String[] tokens = userInput.split("\\s+", 2);
        if (tokens.length < 2) {
            logger.warning("no ignore index from user");
            throw new RainyDayException(ErrorMessage.WRONG_IGNORE_FORMAT.toString());
        }
        if (RainyDay.savedData.getFinancialReport().getStatementCount() == 0) {
            throw new RainyDayException(ErrorMessage.EMPTY_FINANCIAL_REPORT.toString());
        }
        try {
            int index = Integer.parseInt(tokens[1]);
            if (index > RainyDay.savedData.getFinancialReport().getStatementCount()) {
                throw new IllegalArgumentException();
            }
            if (index <= 0) {
                throw new IllegalArgumentException();
            }
            return new IgnoreCommand(index, tokens[0]);
        } catch (Exception e) {
            logger.warning("ignore index provided incorrectly");
            throw new RainyDayException(String.format(ErrorMessage.WRONG_IGNORE_INDEX.toString(),
                    RainyDay.savedData.getFinancialReport().getStatementCount() + "!"));
        }
    }

    public Command exitCommand() {
        return new ExitCommand();
    }
}
