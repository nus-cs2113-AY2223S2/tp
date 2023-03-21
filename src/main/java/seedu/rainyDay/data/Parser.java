package seedu.rainyDay.data;

import seedu.rainyDay.RainyDay;
import seedu.rainyDay.command.Command;
import seedu.rainyDay.command.AddCommand;
import seedu.rainyDay.command.DeleteCommand;
import seedu.rainyDay.command.EditCommand;
import seedu.rainyDay.command.ExportCommand;
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

    private String direction;

    private String description;

    private String category;

    private String filterFlag;

    private double amount = -1.0;

    private String field;

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
            logger.info("filter command executing");
            return filterStatement(action[1]);
        } else if (action[0].equalsIgnoreCase(Command.COMMAND_EDIT)) {
            logger.info("edit command executing");
            return editStatement(userInput);
        } else if (action[0].equalsIgnoreCase(Command.COMMAND_EXPORT)) {
            logger.info("export command executing");
            return generateExport();
        } else {
            logger.warning("unrecognised input from user!");
            return new InvalidCommand(ErrorMessage.UNRECOGNIZED_INPUT.toString());
        }
    }

    private Command addStatement(String addInput) { // example: add -<in/out> <description> $value -c -d
        try {
            this.category = "miscellaneous";
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
            return new FilterCommand(this.field, this.filterFlag);

        } catch (Exception e) {
            logger.warning("filter command given by user in the wrong format");
            return new InvalidCommand(ErrorMessage.WRONG_FILTER_FORMAT.toString());
        }
    }

    private void parseDefaultFilterByDescription(String input) {
        this.field = input.trim();
        this.filterFlag = "-d";
    }

    private void parseFilterByDescription(String input) {
        Pattern pattern = Pattern.compile("^(-d)\\s+?([^\\s-]+(?:\\s+[^\\s-]+)*)\\s*$");
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {
            this.filterFlag = matcher.group(1);
            this.field = matcher.group(2);
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
            this.field = matcher.group(2);
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
            this.field = "none";
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
            this.field = matcher.group(1);
        } else {
            logger.warning("filter command given by user in the wrong format");
            throw new IllegalArgumentException(ErrorMessage.WRONG_FILTER_FORMAT.toString());
        }
    }

    private void parseEditByValue(String input) {
        Pattern pattern = Pattern.compile("^(-v)\\s+\\$([0-9]+(?:\\.[0-9]{1,2})?)\\s*$");
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {
            this.filterFlag = matcher.group(1);
            this.field = matcher.group(2);
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
                tokens[2] = tokens[2].replaceFirst("add ", "");
                String remainingInformation = returnRemainingInformation(tokens[2]);
                if (remainingInformation.trim().isEmpty()) {
                    return new EditCommand(index, description, direction, amount, category);
                }
                if (!remainingInformation.contains("-c ") && !remainingInformation.contains("-date ")) {
                    logger.info("returning new InvalidCommand object");
                    return new InvalidCommand(ErrorMessage.WRONG_ADD_FORMAT.toString());
                }
                if (remainingInformation.contains("-c ")) {
                    remainingInformation = setCategory(remainingInformation);
                }
                if (remainingInformation.contains("-date ")) {
                    setDate(remainingInformation);
                }
                return new EditCommand(index, description, direction, amount, category);
            } else if (tokens[2].contains("-d")) { // todo add -date
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
                return new InvalidCommand(ErrorMessage.WRONG_INPUT_FORMAT.toString());
            }
        } catch (Exception e) {
            logger.warning("edit index provided incorrectly");
            return new InvalidCommand(ErrorMessage.WRONG_INPUT_FORMAT.toString()); // todo update
        }
    }

    public ExportCommand generateExport() {
        return new ExportCommand();
    }


}
