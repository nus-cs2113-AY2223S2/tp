// @@author adenteo
package pocketpal.frontend.parser;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import pocketpal.data.entry.Category;
import pocketpal.frontend.constants.MessageConstants;
import pocketpal.frontend.commands.Command;
import pocketpal.frontend.commands.AddCommand;
import pocketpal.frontend.commands.DeleteCommand;
import pocketpal.frontend.commands.EditCommand;
import pocketpal.frontend.commands.ExitCommand;
import pocketpal.frontend.commands.HelpCommand;
import pocketpal.frontend.commands.ViewCommand;
import pocketpal.frontend.exceptions.InvalidCommandException;
import pocketpal.frontend.exceptions.InvalidArgumentsException;
import pocketpal.frontend.exceptions.MissingArgumentsException;
import pocketpal.frontend.exceptions.InvalidCategoryException;
import pocketpal.frontend.exceptions.MissingDateException;
import pocketpal.frontend.exceptions.InvalidDateException;
import pocketpal.frontend.util.CategoryUtil;
import pocketpal.frontend.util.StringUtil;

public class Parser {

    private static final String COMMAND_ADD = "/add";
    private static final String COMMAND_VIEW = "/view";
    private static final String COMMAND_EDIT = "/edit";
    private static final String COMMAND_DELETE = "/delete";
    private static final String COMMAND_HELP = "/help";
    private static final String COMMAND_BYE = "/bye";
    private static final Logger logger = Logger.getLogger(Parser.class.getName());
    private static final String validDescriptionRegex = "[a-zA-Z0-9\\s]*";
    private static final String validPriceRegex = "[0-9.]*";
    private static final Pattern descriptionPattern = Pattern.compile("(-d)\\s+(.*?)(\\s+-c|$)");
    private static final Pattern categoryPattern = Pattern.compile("(-c|-category)\\s+(\\S+)");
    private static final Pattern pricePattern = Pattern.compile("(-p|-price)\\s+(\\S+)");
    private static final Pattern idPattern = Pattern.compile("(\\s+)?(\\S+)");
    private static final Pattern startDatePattern = Pattern.compile("(-sd|-startdate)\\s+(0*\\d+/0*\\d+/\\d{2,})");
    private static final Pattern endDatePattern = Pattern.compile("(-ed|-enddate)\\s+(0*\\d+/0*\\d+/\\d{2,})");

    /**
     * @param userInput The entire string of user input.
     * @throws InvalidCommandException   If command entered by user is not
     *                                   recognisable.
     * @throws InvalidArgumentsException If arguments following command is in
     *                                   incorrect format.
     * @throws MissingArgumentsException If required arguments are missing.
     */
    public Command parseUserInput(String userInput) throws
            InvalidCommandException, InvalidArgumentsException,
            MissingArgumentsException, InvalidCategoryException, MissingDateException, InvalidDateException {
        logger.entering(Parser.class.getName(), "parseUserInput()");
        userInput = userInput.trim();
        if (userInput.isEmpty()) {
            logger.log(Level.WARNING, "User entered an empty string",
                    new MissingArgumentsException(MessageConstants.MESSAGE_EMPTY_INPUT));
            throw new MissingArgumentsException(MessageConstants.MESSAGE_EMPTY_INPUT);
        }
        String[] userInputArray = userInput.split(" ", 2);
        assert userInputArray.length >= 1 : "Input must contain at least one command";
        String command = userInputArray[0].toLowerCase();
        String arguments = userInput.replaceFirst(command, "").trim();
        logger.log(Level.INFO, "User input command: " + command);
        logger.log(Level.INFO, "User input arguments: " + arguments);
        switch (command) {
        case COMMAND_ADD:
            logger.exiting(Parser.class.getName(), "parseUserInput()");
            return parseAddCommand(arguments);
        case COMMAND_VIEW:
            logger.exiting(Parser.class.getName(), "parseUserInput()");
            return parseViewCommand(arguments);
        case COMMAND_EDIT:
            logger.exiting(Parser.class.getName(), "parseUserInput()");
            return parseEditCommand(arguments);
        case COMMAND_DELETE:
            logger.exiting(Parser.class.getName(), "parseUserInput()");
            return parseDeleteCommand(arguments);
        case COMMAND_HELP:
            logger.exiting(Parser.class.getName(), "parseUserInput()");
            return parseHelpCommand();
        case COMMAND_BYE:
            logger.exiting(Parser.class.getName(), "parseUserInput()");
            return parseByeCommand();
        default:
            logger.log(Level.WARNING, "User command is invalid");
            logger.exiting(Parser.class.getName(), "parseUserInput()");
            throw new InvalidCommandException(MessageConstants.MESSAGE_INVALID_COMMAND);
        }
    }

    /**
     * Returns a string array of length 3, containing the description, category and
     * price respectively.
     *
     * @param arguments User arguments entered after the add command.
     * @return String[] Array containing description, category and price respectively.
     */
    private String[] parseAddArguments(String arguments) throws MissingArgumentsException, InvalidArgumentsException {
        logger.entering(Parser.class.getName(), "parseAddArguments()");
        String description;
        String category;
        String price;
        String[] argumentsArray = new String[3];
        description = extractDetail(arguments, descriptionPattern);
        category = extractDetail(arguments, categoryPattern);
        price = extractDetail(arguments, pricePattern);
        if (description.isEmpty()) {
            logger.warning("Missing description: " + MessageConstants.MESSAGE_MISSING_DESCRIPTION_ADD);
            throw new MissingArgumentsException(MessageConstants.MESSAGE_MISSING_DESCRIPTION_ADD);
        }
        if (category.isEmpty()) {
            logger.warning("Missing category: " + MessageConstants.MESSAGE_MISSING_CATEGORY_ADD);
            throw new MissingArgumentsException(MessageConstants.MESSAGE_MISSING_CATEGORY_ADD);
        }
        if (price.isEmpty()) {
            logger.warning("Missing price: " + MessageConstants.MESSAGE_MISSING_PRICE_ADD);
            throw new MissingArgumentsException(MessageConstants.MESSAGE_MISSING_PRICE_ADD);
        }
        argumentsArray[0] = description;
        argumentsArray[1] = category;
        argumentsArray[2] = price;
        logger.exiting(Parser.class.getName(), "parseAddArguments()");
        return argumentsArray;
    }

    /**
     * @param arguments User arguments entered after the command.
     * @throws MissingArgumentsException If user did not specify description,
     *                                   category and price.
     * @throws InvalidArgumentsException
     */
    private Command parseAddCommand(String arguments)
            throws MissingArgumentsException, InvalidArgumentsException, InvalidCategoryException {
        logger.entering(Parser.class.getName(), "parseAddCommand()");
        logger.info("Parsing add command with arguments: " + arguments);
        if (arguments.isEmpty()) {
            throw new MissingArgumentsException(MessageConstants.MESSAGE_MISSING_ARGS_ADD);
        }
        String[] argumentsArray = parseAddArguments(arguments);
        assert argumentsArray.length == 3 : "User input must contain description, category, and price";
        String description = argumentsArray[0];
        checkIfDescriptionValid(description);
        String category = argumentsArray[1];
        String price = argumentsArray[2];
        logger.info("User input description: " + description);
        logger.info("User input category: " + category);
        logger.info("User input price: " + price);
        double priceDouble;
        checkIfPriceValid(price);
        priceDouble = Double.parseDouble(price);
        logger.exiting(Parser.class.getName(), "parseAddCommand()");
        return new AddCommand(description, priceDouble, category);
    }

    private Command parseByeCommand() {
        // Print bye message
        logger.entering(Parser.class.getName(), "parseByeCommand()");
        logger.info("Program exiting.");
        logger.exiting(Parser.class.getName(), "parseByeCommand()");
        return new ExitCommand();
    }

    /**
     * @param arguments User arguments entered after the command.
     * @throws InvalidArgumentsException If ID entered is out of range, or not in
     *                                   integer format.
     * @throws MissingArgumentsException If required ID is not entered.
     */
    private Command parseDeleteCommand(String arguments)
            throws InvalidArgumentsException, MissingArgumentsException {
        logger.entering(Parser.class.getName(), "parseDeleteCommand()");
        logger.info("Parsing delete command with arguments: " + arguments);
        if (arguments.isEmpty()) {
            logger.log(Level.WARNING, "Index of the expense not specified");
            throw new MissingArgumentsException(MessageConstants.MESSAGE_INVALID_ID);
        }
        String[] argumentsArray = arguments.split(" ");
        assert argumentsArray.length >= 1 : "User input must contain at least one argument";
        // String expenseId = argumentsArray[0];
        int expenseIdInt;
        Integer[] expenseIds = new Integer[argumentsArray.length];
        try {
            for (int i = 0; i < argumentsArray.length; i++) {
                expenseIdInt = Integer.parseInt(argumentsArray[i]);
                if (expenseIdInt < 1) {
                    throw new InvalidArgumentsException(MessageConstants.MESSAGE_INVALID_ID);
                }
                assert argumentsArray[i].matches("\\d+") : "Expense ID must be an integer";
                logger.log(Level.INFO, "Removing specified expense id {0} from list", argumentsArray[i]);
                expenseIds[i] = expenseIdInt;
            }
        } catch (NumberFormatException e) {
            logger.log(Level.WARNING, "Index of the expense specified is not an integer");
            throw new InvalidArgumentsException(MessageConstants.MESSAGE_INVALID_ID);
        }
        logger.exiting(Parser.class.getName(), "parseDeleteCommand(arguments)");
        return new DeleteCommand(expenseIds);
    }

    private Command parseHelpCommand() {
        // Print help message
        logger.entering(Parser.class.getName(), "parseHelpCommand()");
        logger.info("Displaying help message.");
        logger.exiting(Parser.class.getName(), "parseHelpCommand()");
        return new HelpCommand();
    }

    /**
     * @param arguments User arguments entered after the edit command
     * @return String[] Array containing expense ID, description, category and price respectively.
     */
    private String[] parseEditArguments(String arguments) {
        logger.entering(Parser.class.getName(), "parseEditArguments()");
        String expenseId;
        String description;
        String category;
        String price;
        String[] argumentsArray = new String[4];
        expenseId = extractDetail(arguments, idPattern);
        description = extractDetail(arguments, descriptionPattern);
        category = extractDetail(arguments, categoryPattern);
        price = extractDetail(arguments, pricePattern);
        argumentsArray[0] = expenseId;
        argumentsArray[1] = description;
        argumentsArray[2] = category;
        argumentsArray[3] = price;
        logger.exiting(Parser.class.getName(), "parseEditArguments()");
        return argumentsArray;
    }

    /**
     * @param arguments User arguments entered after the command.
     * @throws MissingArgumentsException If required ID is not entered.
     * @throws InvalidArgumentsException If ID entered is out of range, or not in
     *                                   integer format.
     */
    private Command parseEditCommand(String arguments) throws MissingArgumentsException, InvalidArgumentsException {
        logger.entering(Parser.class.getName(), "parseEditCommand()");
        logger.info("Parsing arguments for edit command: " + arguments);
        if (arguments.isEmpty()) {
            logger.warning("Missing arguments for edit command: " + MessageConstants.MESSAGE_MISSING_ARGS_EDIT);
            throw new MissingArgumentsException(MessageConstants.MESSAGE_MISSING_ARGS_EDIT);
        }
        String[] argumentsArray = parseEditArguments(arguments);
        String expenseId = argumentsArray[0];
        String description = argumentsArray[1];
        String category = argumentsArray[2];
        String price = argumentsArray[3];
        logger.info("User input expense ID: " + expenseId);
        logger.info("User input description: " + description);
        logger.info("User input category: " + category);
        logger.info("User input price: " + price);
        try {
            Integer.parseInt(expenseId);
        } catch (NumberFormatException e) {
            logger.warning("Expense ID is not an integer: " + MessageConstants.MESSAGE_INVALID_ID);
            throw new InvalidArgumentsException(MessageConstants.MESSAGE_INVALID_ID);
        }
        if (!price.isEmpty()) {
            checkIfPriceValid(price);
            Double.parseDouble(price);
        }
        if (!category.isEmpty()) {
            category = category.toUpperCase();
            try {
                Category.valueOf(category);
            } catch (IllegalArgumentException e) {
                logger.warning("Category does not exist: " + MessageConstants.MESSAGE_INVALID_CATEGORY);
                throw new InvalidArgumentsException(MessageConstants.MESSAGE_INVALID_CATEGORY);
            }
        }
        logger.exiting(Parser.class.getName(), "parseEditCommand()");
        return new EditCommand(expenseId, description, category, price);
    }

    /**
     * @param arguments User arguments entered after the command.
     * @throws InvalidArgumentsException If user specified a non-integer for expense
     *                                   ID.
     */
    private Command parseViewCommand(String arguments) throws InvalidArgumentsException, InvalidCategoryException, InvalidDateException, MissingDateException {
        logger.entering(Parser.class.getName(), "parseViewCommand()");
        logger.info("Parsing view command with arguments: " + arguments);
        if (arguments.isEmpty()) {
            logger.info("No count specified. Listing all expenses");
            return new ViewCommand(Integer.MAX_VALUE);
        }
        String[] argumentsArray = arguments.split(" ");
        assert argumentsArray.length >= 1 : "User input must contain at least 1 argument";
        Double priceMinDouble;
        Double priceMaxDouble;
        Category category = null;
        String categoryStr = extractDetail(arguments, categoryPattern);
        if (!categoryStr.isEmpty()) {
            CategoryUtil.convertStringToCategory(StringUtil.toTitleCase(categoryStr));
        }
        String priceMinStr = extractDetail(arguments, pricePattern);
        String priceMaxStr = extractDetail(arguments, pricePattern);
        if (!priceMinStr.isEmpty()) {
            checkIfPriceValid(priceMinStr);
        } else {
            priceMinStr = "0";
        }
        if (!priceMaxStr.isEmpty()) {
            checkIfPriceValid(priceMaxStr);
        } else {
            priceMaxStr = Integer.toString(Integer.MAX_VALUE);
        }
        String startDateString = extractDetail(arguments, startDatePattern);
        String endDateString = extractDetail(arguments, endDatePattern);
        if (startDateString.isEmpty() ^ endDateString.isEmpty()) {
            logger.info("Missing at least one date as view command request parameter");
            throw new MissingDateException(MessageConstants.MESSAGE_MISSING_DATE);
        }
        Integer viewCountInt;
        String viewCount = extractDetail(arguments, idPattern); //detail extracted is either view count or an
        // optional flag indicated by user
        if (viewCount.matches("-sd|-p|-c|-startdate|-enddate|-category|-price")) {
            viewCount = Integer.toString(Integer.MAX_VALUE);
        }
        try {
            viewCountInt = Integer.parseInt(viewCount);
        } catch (NumberFormatException e) {
            logger.warning("Expense ID is not an integer: " + MessageConstants.MESSAGE_INVALID_ID);
            throw new InvalidArgumentsException(MessageConstants.MESSAGE_INVALID_ID);
        }
        if (viewCountInt < 0) {
            logger.warning("Negative expense ID provided: " + MessageConstants.MESSAGE_INVALID_ID);
            throw new InvalidArgumentsException(MessageConstants.MESSAGE_INVALID_ID);
        }
        logger.info("User entered count:" + viewCount);
        logger.info("User entered category:" + categoryStr);
        logger.info("User entered start date: " + startDateString);
        logger.info("User entered end date: " + endDateString);
        logger.info("start date identified as: " + startDateString);
        isValidDate(startDateString);
        logger.info("start date verified");
        startDateString += " 00:00";
        logger.info("end date identified as: " + endDateString);
        isValidDate(endDateString);
        logger.info("end date verified");
        endDateString += " 23:59";
        priceMinDouble = Double.parseDouble(priceMinStr);
        priceMaxDouble = Double.parseDouble(priceMaxStr);
        if (priceMaxDouble < priceMinDouble) {
            logger.warning("Maximum price range higher than minimum: " + MessageConstants.MESSAGE_INVALID_PRICE_RANGE);
            throw new InvalidArgumentsException(MessageConstants.MESSAGE_INVALID_PRICE_RANGE);
        }
        logger.exiting(Parser.class.getName(), "parseViewCommand()");
        return new ViewCommand(viewCountInt, category, priceMinDouble, priceMaxDouble, startDateString, endDateString);
    }

    private String extractDetail(String string, Pattern detail) {
        String detailToExtract;
        Matcher matcher = detail.matcher(string);
        if (matcher.find()) {
            System.out.println(matcher.group(0));
            detailToExtract = matcher.group(2).trim();
        } else {
            detailToExtract = "";
        }
        return detailToExtract;
    }

    private void checkIfDescriptionValid(String description) throws InvalidArgumentsException {
        boolean isValid = description.matches(validDescriptionRegex);
        if (!isValid) {
            throw new InvalidArgumentsException(MessageConstants.MESSAGE_INVALID_DESCRIPTION);
        }
    }

    private void checkIfPriceValid(String price) throws InvalidArgumentsException {
        boolean isValid = price.matches(validPriceRegex);
        if (!isValid) {
            throw new InvalidArgumentsException(MessageConstants.MESSAGE_INVALID_PRICE);
        }
    }

    private void isValidDate(String dateString) throws InvalidDateException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yy");
        try {
            simpleDateFormat.setLenient(false);
            Date testDate = simpleDateFormat.parse(dateString);
        } catch (java.text.ParseException e) {
            logger.warning("Invalid date entered: " + MessageConstants.MESSAGE_INVALID_DATE);
            throw new InvalidDateException(MessageConstants.MESSAGE_INVALID_DATE);
        }
    }

}
// @@author