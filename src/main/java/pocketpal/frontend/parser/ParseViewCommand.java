package pocketpal.frontend.parser;

import pocketpal.data.entry.Category;
import pocketpal.frontend.commands.Command;
import pocketpal.frontend.commands.ViewCommand;
import pocketpal.frontend.constants.EntryConstants;
import pocketpal.frontend.constants.MessageConstants;
import pocketpal.frontend.constants.ParserConstants;
import pocketpal.frontend.exceptions.InvalidArgumentsException;
import pocketpal.frontend.exceptions.InvalidCategoryException;
import pocketpal.frontend.exceptions.InvalidDateException;
import pocketpal.frontend.exceptions.MissingArgumentsException;
import pocketpal.frontend.exceptions.MissingDateException;
import pocketpal.frontend.exceptions.UnknownOptionException;
import pocketpal.frontend.util.CategoryUtil;
import pocketpal.frontend.util.StringUtil;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Logger;

public class ParseViewCommand extends ParseCommand {
    private static final Logger logger = Logger.getLogger(ParseViewCommand.class.getName());
    String viewCount;
    int viewCountInt = Integer.MAX_VALUE;
    String category;
    Category categoryObject = null;
    String startDate;
    String endDate;
    Double startPrice;
    Double endPrice;

    /**
     * Returns a ViewCommand object to be executed by the backend. The object may
     * contain the user specified view count, as well as other optional flags such
     * as date or price ranges. Any missing required inputs or incorrect formats
     * will be raised to the user via the UI.
     *
     * @param input User input string after the view command.
     * @return Command ViewCommand object to be executed by backend.
     * @throws InvalidArgumentsException If required arguments are in incorrect format.
     * @throws InvalidCategoryException  If specified category does not exist.
     * @throws InvalidDateException      If specified date does not exist.
     * @throws MissingDateException      If required end/start date is not specified.
     * @throws MissingArgumentsException If arguments are not specified for options.
     * @throws UnknownOptionException    If an unknown option is used.
     */
    @Override
    public Command parseArguments(String input) throws InvalidArgumentsException, MissingDateException,
            InvalidDateException, UnknownOptionException, MissingArgumentsException, InvalidCategoryException {
        if (input.isEmpty()) {
            return new ViewCommand(viewCountInt);
        }
        checkUnknownOptionExistence(input.trim(), ParserConstants.VIEW_OPTIONS);
        String[] dates = extractDates(input);
        Double[] prices = extractPrices(input);
        viewCount = extractId(input, ParserConstants.ID_PATTERN);
        category = extractDetail(input, ParserConstants.CATEGORY_PATTERN);
        startDate = dates[0];
        endDate = dates[1];
        startPrice = prices[0];
        endPrice = prices[1];
        checkIdValidity(viewCount);
        checkCategoryValidity(category);
        if (viewCount != null && !viewCount.isEmpty()) {
            viewCountInt = Integer.parseInt(viewCount);
        }
        if (category != null) {
            category = StringUtil.toTitleCase(category);
            categoryObject = CategoryUtil.convertStringToCategory(category);
        }
        if (startDate != null && endDate != null && !isDateRangeValid(startDate, endDate)) {
            throw new InvalidDateException(MessageConstants.MESSAGE_MIXED_DATE);
        }
        return new ViewCommand(viewCountInt, categoryObject, startPrice, endPrice, startDate, endDate);
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
    private String[] extractDates(String arguments) throws InvalidDateException,
            MissingDateException, MissingArgumentsException {
        String[] dates = new String[ParserConstants.START_END_ARRAY_SIZE];
        String startDateString = extractDetail(arguments, ParserConstants.START_DATE_PATTERN);
        String endDateString = extractDetail(arguments, ParserConstants.END_DATE_PATTERN);
        if (startDateString != null) {
            logger.info("start date identified as: " + startDateString);
            checkDateValidity(startDateString);
            logger.info("start date verified");
            startDateString = startDateString + EntryConstants.EARLIEST_TIME;
        }
        if (endDateString != null) {
            logger.info("end date identified as: " + endDateString);
            checkDateValidity(endDateString);
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
     * Returns the start and end prices specified by the user when using the filter
     * by price feature. If both not specified, the entire price range is displayed.
     * If only the starting price is specified, all expenses above that price is
     * displayed.
     *
     * @param arguments User input string after view command.
     * @return Double[] Array containing start and end price respectively.
     * @throws InvalidArgumentsException If price specified is not in numerical form
     *                                   or if range specified is invalid.
     */
    private Double[] extractPrices(String arguments) throws InvalidArgumentsException, MissingArgumentsException {
        Double[] prices = new Double[ParserConstants.START_END_ARRAY_SIZE];
        String priceMinStr = extractDetail(arguments, ParserConstants.START_PRICE_PATTERN);
        String priceMaxStr = extractDetail(arguments, ParserConstants.END_PRICE_PATTERN);
        if (priceMinStr != null) {
            checkPriceValidity(priceMinStr);
        } else {
            priceMinStr = ParserConstants.MIN_VALUE_STR;
        }
        if (priceMaxStr != null) {
            checkPriceValidity(priceMaxStr);
        } else {
            priceMaxStr = ParserConstants.MAX_VALUE_STR;
        }
        double priceMinDouble = Double.parseDouble(priceMinStr);
        double priceMaxDouble = Double.parseDouble(priceMaxStr);
        if (priceMaxDouble < priceMinDouble) {
            logger.warning("Maximum price higher than minimum: "
                    + MessageConstants.MESSAGE_INVALID_AMOUNT_RANGE);
            throw new InvalidArgumentsException(MessageConstants.MESSAGE_INVALID_AMOUNT_RANGE);
        }
        prices[0] = priceMinDouble;
        prices[1] = priceMaxDouble;
        return prices;
    }

    private Boolean isDateRangeValid(String startDate, String endDate) throws InvalidDateException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(ParserConstants.DATE_FORMAT);
        LocalDateTime startDateTime = LocalDateTime.parse(startDate, formatter);
        LocalDateTime endDateTime = LocalDateTime.parse(endDate, formatter);
        if (startDateTime.isAfter(endDateTime)) {
            return false;
        }
        return true;
    }
}

