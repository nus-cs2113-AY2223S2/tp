package seedu.rainyDay.data;

import seedu.rainyDay.RainyDay;
import seedu.rainyDay.command.Command;
import seedu.rainyDay.command.AddCommand;
import seedu.rainyDay.command.DeleteCommand;
import seedu.rainyDay.command.EditCommand;
import seedu.rainyDay.command.ViewCommand;
import seedu.rainyDay.command.HelpCommand;
import seedu.rainyDay.command.FilterCommand;
import seedu.rainyDay.command.InvalidCommand;
import seedu.rainyDay.exceptions.ErrorMessage;
import seedu.rainyDay.exceptions.RainyDayException;

import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {

    private static String direction;
    private static String description;
    private static String category;
    private static String filterFlag;
    private static double amount = -1.0;
    private static String field;
    private static final Logger logger = Logger.getLogger(Parser.class.getName());

    public Command parseUserInput(String userInput) throws RainyDayException {
        assert userInput != null : "Failed to read user input!";
        String[] action = userInput.split("\\s+", 2);
        if (action[0].equalsIgnoreCase(Command.COMMAND_ADD)) {
            logger.info("add command executing");
            return addStatement(userInput);
        } else if (action[0].equalsIgnoreCase(Command.COMMAND_DELETE)) {
            logger.info("delete command executing");
            return deleteStatement(userInput); //todo: fix this to reduce calls of split.();
        } else if (action[0].equalsIgnoreCase(Command.COMMAND_VIEW)) {
            logger.info("view command executing");
            return generateReport();
        } else if (action[0].equalsIgnoreCase(Command.COMMAND_HELP)) {
            return displayHelp();
        } else if (action[0].equalsIgnoreCase(Command.COMMAND_FILTER)) {
            logger.info("filter command executing");
            return filterStatement(action[1]);
        } else if (action[0].equalsIgnoreCase(Command.COMMAND_EDIT)) {
            logger.info("edit command executing");
            return editStatement(userInput);
        } else { // todo add filter
            logger.warning("unrecognised input from user!");
            return new InvalidCommand();
        }
    }

    private Command addStatement(String userInput) throws IllegalArgumentException {
        try {
            if (userInput.contains("-d") && userInput.contains("-c")) {
                parseDescriptionAndCategory(userInput);
            } else if (userInput.contains("-d")) {
                parseDescriptionOnly(userInput);
            } else if (userInput.contains("-c")) {
                parseCategoryOnly(userInput);
            } else {
                parseOnly(userInput);
            }
            return new AddCommand(description, direction, amount, category);
        } catch (Exception e) {
            return new InvalidCommand();
            //logger.warning("add command given by user in the wrong format");
            //throw new IllegalArgumentException(ErrorMessage.WRONG_ADD_FORMAT.toString());
        }
    }

    private void parseDescriptionAndCategory(String userInput) throws IllegalArgumentException {
        if (userInput.contains("-d") && userInput.contains("-c")) {
            Pattern pattern = Pattern.compile("-(in|out)\\s+(?:-d\\s+)?([^\\s-]+(?:\\s+[^\\s-]+)*)\\s+" +
                    "-c\\s+(\\S+(?:\\s+\\S+)*)\\s+\\$([\\d.]+)");
            Matcher matcher = pattern.matcher(userInput);
            Pattern pattern2 = Pattern.compile("-(in|out)\\s+(?:-c\\s+)?([^\\s-]+(?:\\s+[^\\s-]+)*)\\s+" +
                    "-d\\s+(\\S+(?:\\s+\\S+)*)\\s+\\$([\\d.]+)");
            Matcher matcher2 = pattern2.matcher(userInput);
            if (matcher.find()) {
                direction = matcher.group(1);
                description = matcher.group(2);
                category = matcher.group(3);
                amount = Double.parseDouble(matcher.group(4));
            } else if (matcher2.find()) {
                direction = matcher2.group(1);
                category = matcher2.group(2);
                description = matcher2.group(3);
                amount = Double.parseDouble(matcher2.group(4));
            } else {
                logger.warning("add command given by user in the wrong format");
                throw new IllegalArgumentException(ErrorMessage.WRONG_ADD_FORMAT.toString());
            }
        }
    }

    private void parseDescriptionOnly(String userInput) throws IllegalArgumentException {
        Pattern pattern = Pattern.compile("-(in|out)\\s+(?:-d\\s+)?([^\\s-]+(?:\\s+[^\\s-]+)*)\\s+\\$([\\d.]+)");
        Matcher matcher = pattern.matcher(userInput);
        if (matcher.find()) {
            direction = matcher.group(1);
            description = matcher.group(2);
            amount = Double.parseDouble(matcher.group(3));
            category = "miscellaneous";
        } else {
            logger.warning("add command given by user in the wrong format");
            throw new IllegalArgumentException(ErrorMessage.WRONG_ADD_FORMAT.toString());
        }
    }

    private void parseCategoryOnly(String userInput) throws IllegalArgumentException {
        Pattern pattern = Pattern.compile("-(in|out)\\s+(?:-c\\s+)?([^\\s-]+(?:\\s+[^\\s-]+)*)\\s+\\$([\\d.]+)");
        Matcher matcher = pattern.matcher(userInput);
        if (matcher.find()) {
            direction = matcher.group(1);
            description = "miscellaneous";
            amount = Double.parseDouble(matcher.group(3));
            category = matcher.group(2);
        } else {
            logger.warning("add command given by user in the wrong format");
            throw new IllegalArgumentException(ErrorMessage.WRONG_ADD_FORMAT.toString());
        }
    }

    private void parseOnly(String userInput) throws IllegalArgumentException {
        Pattern pattern = Pattern.compile("-(in|out)\\s+\\$([\\d.]+)");
        Matcher matcher = pattern.matcher(userInput);
        if (matcher.find()) {
            direction = matcher.group(1);
            description = "miscellaneous";
            amount = Double.parseDouble(matcher.group(2));
            category = "miscellaneous";
        } else {
            throw new IllegalArgumentException(ErrorMessage.WRONG_ADD_FORMAT.toString());
        }
    }

    public Command deleteStatement(String userInput) throws IllegalArgumentException {
        String[] tokens = userInput.split("\\s+");
        if (tokens.length != 2) {
            logger.warning("invalid delete index from user");
            throw new IllegalArgumentException(ErrorMessage.NO_DELETE_INDEX.toString());
        }
        try {
            int index = Integer.parseInt(tokens[1]);
            if (index > RainyDay.financialReport.getStatementCount()) {
                throw new IllegalArgumentException(ErrorMessage.WRONG_DELETE_INDEX.toString());
            }
            return new DeleteCommand(index);
        } catch (Exception e) {
            return new InvalidCommand();
            //logger.warning("delete index provided incorrectly");
            //throw new IllegalArgumentException(ErrorMessage.WRONG_DELETE_INDEX.toString());
        }
    }

    public ViewCommand generateReport() {
        return new ViewCommand();
    }

    public HelpCommand displayHelp() {
        return new HelpCommand();
    }

    private static Command filterStatement(String input) {
        try {
            if (input.contains("-d")) {
                parseFilterByDescription(input);
            } else if (input.contains("-c")) {
                parseFilterByCategory(input);
            } else if (input.contains("-in")) {
                parseFilterByFlowDirection(input);
            } else if (input.contains("-out")) {
                parseFilterByFlowDirection(input);
            } else {
                parseDefaultFilterByDescription(input);
            }
            return new FilterCommand(field, filterFlag);
        } catch (Exception e) {
            return new InvalidCommand();
            //logger.warning("filter command given by user in the wrong format");
            //throw new IllegalArgumentException(ErrorMessage.WRONG_FILTER_FORMAT.toString());
        }
    }

    private static void parseDefaultFilterByDescription(String input) {
        field = input.trim();
        filterFlag = "-d";
    }

    private static void parseFilterByDescription(String input) {
        Pattern pattern = Pattern.compile("^(-d)\\s+?([^\\s-]+(?:\\s+[^\\s-]+)*)\\s*$");
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {
            filterFlag = matcher.group(1);
            field = matcher.group(2);
        } else {
            logger.warning("filter command given by user in the wrong format");
            throw new IllegalArgumentException(ErrorMessage.WRONG_FILTER_FORMAT.toString());
        }
    }

    private static void parseFilterByCategory(String input) {
        Pattern pattern = Pattern.compile("^(-c)\\s+?([^\\s-]+(?:\\s+[^\\s-]+)*)\\s*$");
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {
            filterFlag = matcher.group(1);
            field = matcher.group(2);
        } else {
            logger.warning("filter command given by user in the wrong format");
            throw new IllegalArgumentException(ErrorMessage.WRONG_FILTER_FORMAT.toString());
        }
    }

    private static void parseFilterByFlowDirection(String input) {
        Pattern pattern = Pattern.compile("^(-in|-out)\\s*$");
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {
            filterFlag = matcher.group(1);
            field = "none";
        } else {
            logger.warning("filter command given by user in the wrong format");
            throw new IllegalArgumentException(ErrorMessage.WRONG_FILTER_FORMAT.toString());
        }
    }

    private static void parseEditByValue(String input) {
        Pattern pattern = Pattern.compile("^(-v)\\s+\\$([0-9]+(?:\\.[0-9]{1,2})?)\\s*$");
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {
            filterFlag = matcher.group(1);
            field = matcher.group(2);
        } else {
            logger.warning("filter command given by user in the wrong format");
            throw new IllegalArgumentException(ErrorMessage.WRONG_FILTER_FORMAT.toString());
        }
    }

    public Command editStatement(String userInput) throws IllegalArgumentException {
        try {
            String[] tokens = userInput.split("\\s+", 3);
            if (tokens.length == 1) {
                logger.warning("invalid edit index from user");
                throw new IllegalArgumentException(ErrorMessage.NO_EDIT_INDEX.toString());
            }

            int index = Integer.parseInt(tokens[1]);
            if (index > RainyDay.financialReport.getStatementCount()) {
                throw new IllegalArgumentException(ErrorMessage.WRONG_EDIT_INDEX.toString());
            }

            if (tokens[2].contains("add")) {
                if (tokens[2].contains("-d") && tokens[2].contains("-c")) {
                    parseDescriptionAndCategory(tokens[2]);
                } else if (tokens[2].contains("-d")) {
                    parseDescriptionOnly(tokens[2]);
                } else if (tokens[2].contains("-c")) {
                    parseCategoryOnly(tokens[2]);
                } else {
                    parseOnly(tokens[2]);
                }
                return new EditCommand(index, description, direction, amount, category);
            } else if (tokens[2].contains("-d")) {
                parseFilterByDescription(tokens[2]);
                return new EditCommand(index, "-d", field);
            } else if (tokens[2].contains("-c")) {
                parseFilterByCategory(tokens[2]);
                return new EditCommand(index, "-c", field);
            } else if (tokens[2].contains("-v")) {
                parseEditByValue(tokens[2]);
                return new EditCommand(index, "-v", Double.parseDouble(field));
            } else if (tokens[2].equals("-out")) {
                return new EditCommand(index, "-out");
            } else if (tokens[2].equals("-in")) {
                return new EditCommand(index, "-in");
            } else {
                return new InvalidCommand();
            }
        } catch (Exception e) {
            return new InvalidCommand();
            //logger.warning("edit index provided incorrectly");
            //throw new IllegalArgumentException(ErrorMessage.WRONG_DELETE_INDEX.toString());
        }
    }
}
