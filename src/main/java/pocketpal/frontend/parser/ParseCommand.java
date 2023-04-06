package pocketpal.frontend.parser;

import pocketpal.frontend.commands.Command;
import pocketpal.frontend.constants.MessageConstants;
import pocketpal.frontend.constants.ParserConstants;
import pocketpal.frontend.exceptions.*;
import pocketpal.frontend.util.CategoryUtil;
import pocketpal.frontend.util.StringUtil;

import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class ParseCommand {
    private static final Logger logger = Logger.getLogger(ParseCommand.class.getName());

    public abstract Command parseArguments(String input) throws InvalidArgumentsException,
            InvalidCategoryException, MissingArgumentsException, MissingDateException, InvalidDateException,
            UnknownOptionException, UnknownArgumentException;

    public String extractDetail(String input, Pattern pattern) throws MissingArgumentsException {
        String detail = null;
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {
            String option = matcher.group(ParserConstants.OPTION_GROUP);
            detail = matcher.group(ParserConstants.DETAIL_GROUP);
            if (detail != null) {
                detail = detail.trim();
            }
            boolean isMissingArgument = (detail == null || detail.isEmpty()) && (option != null);
            if (isMissingArgument) { //option specified with 0 arguments
                throw new MissingArgumentsException(MessageConstants.MESSAGE_MISSING_OPTION_ARG + option);
            }
        }
        return detail;
    }

    public String extractArgumentsBeforeOption(String input, Pattern pattern) {
        String arguments = null;
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {
            arguments = matcher.group(ParserConstants.ARGUMENTS_GROUP).trim();
        }
        return arguments;
    }

    public void checkIdValidity(String id) throws InvalidArgumentsException {
        if (id == null || id.isEmpty()) {
            return;
        }
        try {
            Integer.parseInt(id);
        } catch (NumberFormatException e) {
            throw new InvalidArgumentsException(MessageConstants.MESSAGE_INVALID_ID);
        }
    }

    public void checkDescriptionValidity(String description) throws InvalidArgumentsException {
        if (description == null) { //description not declared
            return;
        }
        if (description.contains(",")) {
            throw new InvalidArgumentsException(MessageConstants.MESSAGE_INVALID_DESCRIPTION);
        }
    }

    public void checkPriceValidity(String price) throws InvalidArgumentsException {
        if (price == null) { //price not declared
            return;
        }
        boolean isValid = price.matches(ParserConstants.VALID_PRICE_REGEX);
        if (!isValid) {
            throw new InvalidArgumentsException(MessageConstants.MESSAGE_INVALID_AMOUNT);
        }
        double priceDouble;
        try {
            priceDouble = Double.parseDouble(price);
        } catch (NumberFormatException e) {
            throw new InvalidArgumentsException(MessageConstants.MESSAGE_INVALID_AMOUNT);
        }
        if (priceDouble > ParserConstants.MAX_VALUE || priceDouble < ParserConstants.MIN_VALUE) {
            throw new InvalidArgumentsException(MessageConstants.MESSAGE_INVALID_AMOUNT);
        }
    }

    /**
     * Checks if date is valid.
     *
     * @param dateString User specified date.
     * @throws InvalidDateException If date is invalid.
     */
    public void checkDateValidity(String dateString) throws InvalidDateException {
        if (dateString == null) { //date not declared;
            return;
        }
        Matcher matcher = ParserConstants.DATE_FORMATTER.matcher(dateString);
        if (!matcher.find()) { //Date incorrect format
            throw new InvalidDateException(MessageConstants.MESSAGE_INVALID_DATE);
        }
    }

    public void checkCategoryValidity(String category) throws InvalidCategoryException {
        if (category == null) { //option not declared
            return;
        }
        category = StringUtil.toTitleCase(category);
        CategoryUtil.convertStringToCategory(category);
    }

    public void checkUnknownOptionExistence(String input, String availableOptions) throws UnknownOptionException {
        if (input.isEmpty()) {
            return;
        }
        String[] arguments = input.split("\\s+");
        for (String argument : arguments) {
            char firstCharacter = argument.charAt(0);
            if (firstCharacter == ParserConstants.OPTION_INDICATOR
                    && !argument.matches(availableOptions)) { //option not recognised
                throw new UnknownOptionException(MessageConstants.MESSAGE_UNKNOWN_OPTION + argument);
            }
        }
    }

}
