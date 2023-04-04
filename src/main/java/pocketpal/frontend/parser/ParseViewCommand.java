package pocketpal.frontend.parser;

import pocketpal.data.entry.Category;
import pocketpal.frontend.commands.Command;
import pocketpal.frontend.commands.ViewCommand;
import pocketpal.frontend.constants.EntryConstants;
import pocketpal.frontend.constants.MessageConstants;
import pocketpal.frontend.exceptions.*;
import pocketpal.frontend.util.CategoryUtil;
import pocketpal.frontend.util.StringUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

public class ParseViewCommand extends ParseCommand {
    private static final Logger logger = Logger.getLogger(ParseViewCommand.class.getName());
    String viewCount;
    int viewCountInt = Integer.MAX_VALUE;
    String category;
    Category categoryObject = null;
    String startDate;
    String endDate;
    String startPrice;
    String endPrice;

    @Override
    public Command parseArguments(String input) throws MissingArgumentsException, InvalidArgumentsException, InvalidCategoryException {
        if (input.isEmpty()) { //
            return new ViewCommand(viewCountInt);
        }
        String[] dates = new String[ParserConstants.DATES_ARRAY_SIZE];
        viewCount = extractId(input, ParserConstants.ID_PATTERN);
        category = extractDetail(input, ParserConstants.CATEGORY_PATTERN);
        startPrice = extractDetail(input, ParserConstants.START_PRICE_PATTERN);
        endPrice = extractDetail(input, ParserConstants.END_PRICE_PATTERN);
        startDate = dates[0];
        endDate = dates[1];
        checkIdValidity(viewCount);
        checkCategoryValidity(category);
        checkDateValidity(startDate);
        checkDateValidity(endDate);
        checkPriceValidity(startPrice);
        checkPriceValidity(endPrice);
        if (startPrice == null) {
            startPrice = ParserConstants.ZERO_VALUE;
        }
        if (endPrice == null) {
            endPrice = ParserConstants.MAX_VALUE;
        }
        if (viewCount != null) {
            viewCountInt = Integer.parseInt(viewCount);
        }
        if (category != null) {
            category = StringUtil.toTitleCase(category);
            categoryObject = CategoryUtil.convertStringToCategory(category);
        }
        return new ViewCommand(viewCountInt, categoryObject, Double.parseDouble(startPrice), Double.parseDouble(endPrice), startDate, endDate);
    }

    //@@author leonghuenweng

    /**
     * Returns the start and end dates specified by the user when using the filter
     * by date feature. Both dates have to specified if user uses this feature. If
     * both not specified, all expenses are displayed.
     *
     * @param arguments User input string after view command.
     * @return String[] Array containing start and end date respectively.
     * @throws InvalidDateException If date specified does not exist.
     * @throws MissingDateException If either start or end date is not specified.
     */
    private String[] extractDates(String arguments) throws InvalidDateException, MissingDateException, MissingArgumentsException {
        String[] dates = new String[ParserConstants.DATES_ARRAY_SIZE];
        String startDateString = extractDetail(arguments, ParserConstants.START_DATE_PATTERN);
        String endDateString = extractDetail(arguments, ParserConstants.END_DATE_PATTERN);
        if (startDateString != null) {
            logger.info("start date identified as: " + startDateString);
            isValidDate(startDateString);
            logger.info("start date verified");
            startDateString = startDateString + EntryConstants.EARLIEST_TIME;
        }
        if (endDateString != null) {
            logger.info("end date identified as: " + endDateString);
            isValidDate(endDateString);
            logger.info("end date verified");
            endDateString = endDateString + EntryConstants.LATEST_TIME;
        }
        if (startDateString == null ^ endDateString == null) {
            logger.info("Missing at least one date as view command request parameter");
            throw new MissingDateException(MessageConstants.MESSAGE_MISSING_DATE);
        }
        dates[0] = startDateString;
        dates[1] = endDateString;
        return dates;
    }


    /**
     * Checks if date is valid.
     *
     * @param dateString User specified date.
     * @throws InvalidDateException If date is invalid.
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

