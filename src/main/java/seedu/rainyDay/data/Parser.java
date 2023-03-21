package seedu.rainyDay.data;

import seedu.rainyDay.RainyDay;
import seedu.rainyDay.command.Command;
import seedu.rainyDay.command.AddCommand;
import seedu.rainyDay.command.DeleteCommand;
import seedu.rainyDay.command.ViewCommand;
import seedu.rainyDay.command.HelpCommand;
import seedu.rainyDay.command.FilterCommand;
import seedu.rainyDay.command.InvalidCommand;
import seedu.rainyDay.exceptions.ErrorMessage;
import seedu.rainyDay.exceptions.RainyDayException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    private static final Logger logger = Logger.getLogger(Parser.class.getName());

    private String direction = null;

    private String description = null;

    private String category = "miscellaneous";

    private String filterFlag = null;

    private double amount = -1.0;

    private LocalDate date = null;


    public Command parseUserInput(String userInput) {
        assert userInput != null : "Failed to read user input!";
        String[] action = userInput.split("\\s+", 2);
        if (action[0].equalsIgnoreCase(Command.COMMAND_ADD)) {
            logger.info("add command executing");
            return addStatement(action[1].trim());
        } else if (action[0].equalsIgnoreCase(Command.COMMAND_DELETE)) {
            logger.info("delete command executing");
            return deleteStatement(userInput); //todo: fix this to reduce calls of split.();
        } else if (action[0].equalsIgnoreCase(Command.COMMAND_VIEW)) {
            logger.info("view command executing");
            return generateReport();
        } else if (action[0].equalsIgnoreCase(Command.COMMAND_HELP)) {
            return displayHelp();
        } else if (action[0].equalsIgnoreCase(Command.COMMAND_FILTER)) {
            System.out.println(action[0]);
            System.out.println(action[1]);
            return filterStatement(action[1]);
        } else {
            logger.warning("unrecognised input from user!");
            return new InvalidCommand(ErrorMessage.UNRECOGNIZED_INPUT.toString());
        }
    }

    private Command addStatement(String addInput) { // example: add -<in/out> <description> $value -c -d
        try {
            String remainingInformation = returnRemainingInformation(addInput);
            logger.info("obtained mandatory information");
            if (remainingInformation.trim().isEmpty()) {
                logger.info("returning new AddCommand object");
                return new AddCommand(description.trim(), direction, amount, category, date);
            }
            if (!addInput.contains("-c ") && !addInput.contains("-date ")) {
                logger.info("returning new InvalidCommand object");
                return new InvalidCommand(ErrorMessage.WRONG_ADD_FORMAT.toString());
            }
            logger.info("checking for presence of -c");
            if (addInput.contains("-c ")) {
                remainingInformation = setCategory(remainingInformation);
            }
            logger.info("checking for presence of -date");
            if (addInput.contains("-date ")) {
                setDate(remainingInformation);
            }
            logger.info("returning new AddCommand object");
            return new AddCommand(description.trim(), direction, amount, category, date);
        } catch (Exception e) {
            logger.warning("add command given by user in the wrong format");
            return new InvalidCommand(ErrorMessage.WRONG_ADD_FORMAT.toString());
        }
    }

    private String returnRemainingInformation(String input) throws RainyDayException {
        try {
            Pattern pattern = Pattern.compile("-(in|out)\\s+(.+)\\$([\\d.]+)");
            Matcher matcher = pattern.matcher(input);
            if (matcher.matches()) {
                this.direction = matcher.group(1);
                this.description = matcher.group(2);
                this.amount = Double.parseDouble(matcher.group(3));
                logger.info("obtaining mandatory information");
                return "";
            }
            Pattern newPattern = Pattern.compile("-(in|out)\\s+(.+)\\$([\\d.]+)\\s+(.*)");
            Matcher newMatcher = newPattern.matcher(input);
            if (newMatcher.matches()) {
                this.direction = newMatcher.group(1);
                this.description = newMatcher.group(2);
                this.amount = Double.parseDouble(newMatcher.group(3));
                logger.info("obtaining mandatory information");
                return newMatcher.group(4);
            }
        } catch (Exception e) {
            logger.warning("add command given by user in the wrong format");
            throw new RainyDayException(ErrorMessage.WRONG_ADD_FORMAT.toString());
        }
        logger.warning("add command given by user in the wrong format");
        throw new RainyDayException(ErrorMessage.WRONG_ADD_FORMAT.toString());
    }

    private String setCategory(String input) throws RainyDayException {
        Pattern pattern = Pattern.compile("-c\\s+(\\S+)");
        Matcher matcher = pattern.matcher(input);
        if (matcher.matches()) {
            this.category = matcher.group(1);
            logger.info("obtaining category");
            return "";
        }
        Pattern newPattern = Pattern.compile("-c\\s+(\\S+)\\s+(.*)");
        Matcher newMatcher = newPattern.matcher(input);
        if (newMatcher.matches()) {
            this.category = newMatcher.group(1);
            logger.info("obtaining category");
            return newMatcher.group(2);
        }
        logger.warning("add command given by user in the wrong format");
        throw new RainyDayException(ErrorMessage.WRONG_ADD_FORMAT.toString());
    }

    private void setDate(String input) throws RainyDayException {
        Pattern pattern = Pattern.compile("-date\\s+(\\d{2}/\\d{2}/\\d{4})");
        Matcher matcher = pattern.matcher(input);
        if (matcher.matches()) {
            try {
                this.date = LocalDate.parse(matcher.group(1).trim(), DateTimeFormatter.ofPattern("dd/MM/uuuu"));
                logger.info("obtaining date");
            } catch (DateTimeParseException e) {
                logger.warning("add command given by user in the wrong format");
                throw new RainyDayException(ErrorMessage.WRONG_ADD_FORMAT.toString());
            }
        } else {
            logger.warning("add command given by user in the wrong format");
            throw new RainyDayException(ErrorMessage.WRONG_ADD_FORMAT.toString());
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
            logger.warning("delete index provided incorrectly");
            return new InvalidCommand(ErrorMessage.WRONG_DELETE_INDEX.toString());
        }
    }

    public ViewCommand generateReport() {
        return new ViewCommand();
    }

    public HelpCommand displayHelp() {
        return new HelpCommand();
    }

    private Command filterStatement(String input) {
        try {
            if (input.contains("-date")) {
                parseFilterByDate(input);
            } else if (input.contains("-d")) {
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
            return new FilterCommand(this.description, this.filterFlag);
        } catch (Exception e) {
            logger.warning("filter command given by user in the wrong format");
            return new InvalidCommand(ErrorMessage.WRONG_FILTER_FORMAT.toString());
        }
    }

    private void parseDefaultFilterByDescription(String input) {
        this.description = input.trim();
        this.filterFlag = "-d";
    }

    private void parseFilterByDescription(String input) {
        Pattern pattern = Pattern.compile("^(-d)\\s+?([^\\s-]+(?:\\s+[^\\s-]+)*)\\s*$");
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {
            this.filterFlag = matcher.group(1);
            this.description = matcher.group(2);
        } else {
            logger.warning("filter command given by user in the wrong format");
            throw new IllegalArgumentException(ErrorMessage.WRONG_FILTER_FORMAT.toString());
        }
    }

    private void parseFilterByCategory(String input) {
        Pattern pattern = Pattern.compile("^(-c)\\s+?([^\\s-]+(?:\\s+[^\\s-]+)*)\\s*$");
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {
            this.filterFlag = matcher.group(1);
            this.description = matcher.group(2);
        } else {
            logger.warning("filter command given by user in the wrong format");
            throw new IllegalArgumentException(ErrorMessage.WRONG_FILTER_FORMAT.toString());
        }
    }

    private void parseFilterByFlowDirection(String input) {
        Pattern pattern = Pattern.compile("^(-in|-out)\\s*$");
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {
            this.filterFlag = matcher.group(1);
            this.description = "none";
        } else {
            logger.warning("filter command given by user in the wrong format");
            throw new IllegalArgumentException(ErrorMessage.WRONG_FILTER_FORMAT.toString());
        }
    }

    private void parseFilterByDate(String input) {
        Pattern pattern = Pattern.compile("-date\\s+(\\d{2}/\\d{2}/\\d{4})");
        Matcher matcher = pattern.matcher(input);
        if (matcher.matches()) {
            this.filterFlag = "-date";
            this.description = matcher.group(1);
        } else {
            logger.warning("filter command given by user in the wrong format");
            throw new IllegalArgumentException(ErrorMessage.WRONG_FILTER_FORMAT.toString());
        }
    }
}
