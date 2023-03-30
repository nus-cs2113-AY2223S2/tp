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
    private static final Pattern descriptionPattern = Pattern.compile("(-d|-description)\\s+(.*?)" +
            "(\\s+-c|-p|-category|-price|$)");
    private static final Pattern categoryPattern = Pattern.compile("(-c|-category)\\s+(\\S+)");
    private static final Pattern pricePattern = Pattern.compile("(-p|-price)\\s+(\\S+)");
    private static final Pattern idPattern = Pattern.compile("(\\s+)?(\\S+)");
    private static final Pattern startDatePattern = Pattern.compile("(-sd|-startdate)\\s+(0*\\d+/0*\\d+/\\d{2,})");
    private static final Pattern endDatePattern = Pattern.compile("(-ed|-enddate)\\s+(0*\\d+/0*\\d+/\\d{2,})");


    
    /**
     * Returns a Command object that is to be executed by the backend. If any input
     * requirements are not met, the respective exceptions will be thrown and error
     * messages will be displayed to the user
     * via the UI.
     * 
     * @param userInput Entire user input string
     * @return Command Command object to be executed
     * @throws InvalidCommandException   If command entered is invalid
     * @throws InvalidArgumentsException If arguments entered are in wrong format
     * @throws MissingArgumentsException If required arguments are missing
     * @throws InvalidCategoryException  If category entered is invalid
     * @throws MissingDateException      If required date is missing
     * @throws InvalidDateException      If date entered is invalid
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
     * price respectively. If any of the fields are missing, an empty string is
     * returned for that field and an error will be raised 
     *
     * @param arguments User arguments entered after the add command.
     * @return String[] Array containing description, category and price
     *         respectively.
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
     * Returns an AddCommand object to be executed by the backend. The AddCommand
     * contains the parameters of the expenses to be added to the expense list.
     * 
     * @param arguments User input entered after add command.
     * @return Command AddCommand object to be executed.
     * @throws MissingArgumentsException If required arguments are missing.
     * @throws InvalidArgumentsException If required arguments are in wrong format.
     * @throws InvalidCategoryException  If category entered is invalid.
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

    
    /**
     * Returns an ExitCommand object to be executed by the backend. The ExitCommand
     * will terminate the program.
     * 
     * @return Command ExitCommand object to be executed.
     */
    private Command parseByeCommand() {
        // Print bye message
        logger.entering(Parser.class.getName(), "parseByeCommand()");
        logger.info("Program exiting.");
        logger.exiting(Parser.class.getName(), "parseByeCommand()");
        return new ExitCommand();
    }

    
    
    /**
     * Returns an DeleteCommand object to be executed by the backend. The
     * DeleteCommand takes in an integer index of the expense to be deleted.
     * 
     * @param arguments User input after the delete command.
     * @return Command DeleteCommand object to be executed.
     * @throws InvalidArgumentsException If entered expense ID does not exist.
     * @throws MissingArgumentsException If required expense ID is not entered.
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

    
    /**
     * Returns a HelpCommand object to be executed by the backend. The help menu is
     * displayed to the user when this command is executed
     * 
     * @return Command HelpCommand to be executed.
     */
    private Command parseHelpCommand() {
        // Print help message
        logger.entering(Parser.class.getName(), "parseHelpCommand()");
        logger.info("Displaying help message.");
        logger.exiting(Parser.class.getName(), "parseHelpCommand()");
        return new HelpCommand();
    }

    
    
    /** 
     * @param arguments
     * @return String[]
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
     * @param arguments
     * @return Command
     * @throws MissingArgumentsException
     * @throws InvalidArgumentsException
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
     * @param arguments
     * @return Command
     * @throws InvalidArgumentsException
     * @throws InvalidCategoryException
     * @throws InvalidDateException
     * @throws MissingDateException
     */
    private Command parseViewCommand(String arguments) throws InvalidArgumentsException, InvalidCategoryException,
            InvalidDateException, MissingDateException {
        logger.entering(Parser.class.getName(), "parseViewCommand()");
        logger.info("Parsing view command with arguments: " + arguments);
        if (arguments.isEmpty()) {
            logger.info("No count specified. Listing all expenses");
            return new ViewCommand(Integer.MAX_VALUE);
        }
        String[] argumentsArray = arguments.split(" ");
        assert argumentsArray.length >= 1 : "User input must contain at least 1 argument";
        Category category = null;
        String categoryStr = extractDetail(arguments, categoryPattern);
        if (!categoryStr.isEmpty()) {
            category = CategoryUtil.convertStringToCategory(StringUtil.toTitleCase(categoryStr));
        }
        Double[] startEndPrices = extractPrices(arguments);
        Double priceMinDouble = startEndPrices[0];
        Double priceMaxDouble = startEndPrices[1];
        Integer viewCountInt = extractViewCount(arguments);
        String[] startEndDates = extractDates(arguments);
        String startDateString = startEndDates[0];
        String endDateString = startEndDates[1];
        logger.info("User entered count:" + viewCountInt);
        logger.info("User entered category:" + categoryStr);
        logger.info("User entered start date: " + startDateString);
        logger.info("User entered end date: " + endDateString);
        logger.exiting(Parser.class.getName(), "parseViewCommand()");
        return new ViewCommand(viewCountInt, category, priceMinDouble, priceMaxDouble, startDateString, endDateString);
    }

    
    /** 
     * @param arguments
     * @return Integer
     * @throws InvalidArgumentsException
     */
    private Integer extractViewCount(String arguments) throws InvalidArgumentsException {
        String viewCount = extractDetail(arguments, idPattern); //detail extracted is either view count or an
        // optional flag indicated by user
        if (viewCount.matches("-sd|-p|-c|-startdate|-enddate|-category|-price")) {
            viewCount = Integer.toString(Integer.MAX_VALUE);
        }
        Integer viewCountInt;
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
        return viewCountInt;
    }

    
    /** 
     * @param arguments
     * @return Double[]
     * @throws InvalidArgumentsException
     */
    private Double[] extractPrices(String arguments) throws InvalidArgumentsException {
        Double[] prices = new Double[2];
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
        Double priceMinDouble = Double.parseDouble(priceMinStr);
        Double priceMaxDouble = Double.parseDouble(priceMaxStr);
        if (priceMaxDouble < priceMinDouble) {
            logger.warning("Maximum price range higher than minimum: " + MessageConstants.MESSAGE_INVALID_PRICE_RANGE);
            throw new InvalidArgumentsException(MessageConstants.MESSAGE_INVALID_PRICE_RANGE);
        }
        prices[0] = priceMinDouble;
        prices[1] = priceMaxDouble;
        return prices;
    }

    
    /** 
     * @param arguments
     * @return String[]
     * @throws InvalidDateException
     * @throws MissingDateException
     */
    private String[] extractDates(String arguments) throws InvalidDateException, MissingDateException {
        String[] dates = new String[2];
        String startDateString = extractDetail(arguments, startDatePattern);
        String endDateString = extractDetail(arguments, endDatePattern);
        if (!startDateString.isEmpty()) {
            logger.info("start date identified as: " + startDateString);
            isValidDate(startDateString);
            logger.info("start date verified");
            startDateString += " 00:00";
        }
        if (!endDateString.isEmpty()) {
            logger.info("end date identified as: " + endDateString);
            isValidDate(endDateString);
            logger.info("end date verified");
            endDateString += " 23:59";
        }
        if (startDateString.isEmpty() ^ endDateString.isEmpty()) {
            logger.info("Missing at least one date as view command request parameter");
            throw new MissingDateException(MessageConstants.MESSAGE_MISSING_DATE);
        }
        dates[0] = startDateString;
        dates[1] = endDateString;
        return dates;
    }

    
    /** 
     * @param string
     * @param detail
     * @return String
     */
    private String extractDetail(String string, Pattern detail) {
        String detailToExtract;
        Matcher matcher = detail.matcher(string);
        if (matcher.find()) {
            detailToExtract = matcher.group(2).trim();
        } else {
            detailToExtract = "";
        }
        return detailToExtract;
    }

    
    /** 
     * @param description
     * @throws InvalidArgumentsException
     */
    private void checkIfDescriptionValid(String description) throws InvalidArgumentsException {
        boolean isValid = description.matches(validDescriptionRegex);
        if (!isValid) {
            throw new InvalidArgumentsException(MessageConstants.MESSAGE_INVALID_DESCRIPTION);
        }
    }

    
    /** 
     * @param price
     * @throws InvalidArgumentsException
     */
    private void checkIfPriceValid(String price) throws InvalidArgumentsException {
        boolean isValid = price.matches(validPriceRegex);
        if (!isValid) {
            throw new InvalidArgumentsException(MessageConstants.MESSAGE_INVALID_PRICE);
        }
    }

    
    /** 
     * @param dateString
     * @throws InvalidDateException
     */
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
