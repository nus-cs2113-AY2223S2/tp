package seedu.rainyDay.data;

import seedu.rainyDay.RainyDay;
import seedu.rainyDay.command.AddCommand;
import seedu.rainyDay.command.Command;
import seedu.rainyDay.command.DeleteCommand;
import seedu.rainyDay.command.FilterCommand;
import seedu.rainyDay.command.ViewCommand;
import seedu.rainyDay.command.HelpCommand;
import seedu.rainyDay.exceptions.ErrorMessage;
import seedu.rainyDay.exceptions.RainyDayException;

import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {

    private static String direction = null;
    private static String description = null;
    private static String category = null;
    private static String filterFlag = null;
    private static double amount = -1.0;
    private static final Logger logger = Logger.getLogger(Parser.class.getName());

    public static Command parseUserInput(String userInput) throws RainyDayException {
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
            return filterStatement(action[1]);
        } else {
            logger.warning("unrecognised input from user!");
            throw new RainyDayException(ErrorMessage.UNRECOGNIZED_INPUT.toString());
        }
    }

    private static AddCommand addStatement(String userInput) throws IllegalArgumentException {
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
            logger.warning("add command given by user in the wrong format");
            throw new IllegalArgumentException(ErrorMessage.WRONG_ADD_FORMAT.toString());
        }
    }

    private static void parseDescriptionAndCategory(String userInput) throws IllegalArgumentException {
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

    private static void parseDescriptionOnly(String userInput) throws IllegalArgumentException {
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

    private static void parseCategoryOnly(String userInput) throws IllegalArgumentException {
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

    private static void parseOnly(String userInput) throws IllegalArgumentException {
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

    public static DeleteCommand deleteStatement(String userInput) throws IllegalArgumentException {
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
            logger.warning("delete index provided incorrectly");
            throw new IllegalArgumentException(ErrorMessage.WRONG_DELETE_INDEX.toString());
        }
    }

    public static ViewCommand generateReport() {
        return new ViewCommand();
    }

    public static HelpCommand displayHelp() {
        return new HelpCommand();
    }

    private static FilterCommand filterStatement(String input) {
        try {
            if (input.contains("-d")) {
                parseFilterByDescription(input);
            } else if (input.contains("-c")) {
                parseFilterByCategory(input);
            } else {
                parseDefaultFilterByDescription(input);
            }
            return new FilterCommand(description, filterFlag);
        } catch (Exception e) {
            logger.warning("filter command given by user in the wrong format");
            throw new IllegalArgumentException(ErrorMessage.WRONG_FILTER_FORMAT.toString());
        }
    }

    private static void parseDefaultFilterByDescription(String input) {
        description = input.trim();
        filterFlag = "-d";
    }

    private static void parseFilterByDescription(String input) {
        Pattern pattern = Pattern.compile("(-d)\\s+?([^\\s-]+(?:\\s+[^\\s-]+)*)\\s*");
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {
            filterFlag = matcher.group(1);
            description = matcher.group(2);
        } else {
            logger.warning("filter command given by user in the wrong format");
            throw new IllegalArgumentException(ErrorMessage.WRONG_FILTER_FORMAT.toString());
        }
    }

    private static void parseFilterByCategory(String input) {
        Pattern pattern = Pattern.compile("(-c)\\s+?([^\\s-]+(?:\\s+[^\\s-]+)*)\\s*");
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {
            filterFlag = matcher.group(1);
            description = matcher.group(2);
        } else {
            logger.warning("filter command given by user in the wrong format");
            throw new IllegalArgumentException(ErrorMessage.WRONG_FILTER_FORMAT.toString());
        }
    }
}
