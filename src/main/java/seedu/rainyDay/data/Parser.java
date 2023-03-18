package seedu.rainyDay.data;

import seedu.rainyDay.RainyDay;
import seedu.rainyDay.command.AddCommand;
import seedu.rainyDay.command.Command;
import seedu.rainyDay.command.DeleteCommand;
import seedu.rainyDay.command.ViewCommand;
import seedu.rainyDay.command.HelpCommand;
import seedu.rainyDay.exceptions.ErrorMessage;
import seedu.rainyDay.exceptions.RainyDayException;

import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {

    private static Logger logger = Logger.getLogger("Parser.log");
    private static String direction = null;
    private static String description = null;
    private static String category = null;
    private static double amount = -1.0;

    public static Command parseUserInput(String userInput) throws RainyDayException {
        assert userInput != null : "Failed to read user input!";
        String[] action = userInput.split("\\s+", 2);
        if (action[0].equalsIgnoreCase(Command.COMMAND_ADD)) {
            logger.info("add command executing");
            return addStatement(userInput);
        }
        if (action[0].equalsIgnoreCase(Command.COMMAND_DELETE)) {
            logger.info("delete command executing");
            return deleteStatement(userInput); //todo: fix this to reduce calls of split.();
        }
        if (action[0].equalsIgnoreCase(Command.COMMAND_VIEW)) {
            logger.info("view command executing");
            return generateReport();
        }
        if (action[0].equalsIgnoreCase(Command.COMMAND_HELP)) {
            return displayHelp();
        } else {
            logger.warning("unrecognised input from user!");
            throw new RainyDayException(ErrorMessage.UNRECOGNIZED_INPUT.toString());
        }
    }
/**
    private static AddCommand addStatement(String userInput) {
        try {
            userInput = userInput.trim();
            String direction;
            if (userInput.substring(0, 3).equalsIgnoreCase("-in")) {
                direction = userInput.substring(1, 3);
                userInput = userInput.substring(3);
            } else if (userInput.substring(0, 4).equalsIgnoreCase("-out")) {
                direction = userInput.substring(1, 4);
                userInput = userInput.substring(4);
            } else {
                throw new IllegalArgumentException();
            }
            String[] data = userInput.split("\\$");
            String description = data[0].trim();
            data = data[1].split("-c");
            int amount = Integer.parseInt(data[0].trim());
            String category = data[1].trim();
            return new AddCommand(description, direction, amount, category);
        } catch (Exception e) {
            logger.warning("add command given by user in the wrong format");
            throw new IllegalArgumentException(ErrorMessage.WRONG_ADD_FORMAT.toString());
        }
    }
**/
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
        if(userInput.contains("-d") && userInput.contains("-c")) {
            Pattern pattern = Pattern.compile("-(in|out)\\s+(?:-d\\s+)?([^\\s-]+(?:\\s+[^\\s-]+)*)\\s+" +
                    "-c\\s+(\\S+(?:\\s+\\S+)*)\\s+\\$([\\d.]+)");
            Matcher matcher = pattern.matcher(userInput);
            Pattern pattern2 = Pattern.compile("-(in|out)\\s+(?:-c\\s+)?([^\\s-]+(?:\\s+[^\\s-]+)*)\\s+" +
                    "-d\\s+(\\S+(?:\\s+\\S+)*)\\s+\\$([\\d.]+)");
            Matcher matcher2 = pattern2.matcher(userInput);
            if(matcher.find()) {
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
        if(matcher.find()) {
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
        if(matcher.find()) {
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
        if(matcher.find()) {
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
}
