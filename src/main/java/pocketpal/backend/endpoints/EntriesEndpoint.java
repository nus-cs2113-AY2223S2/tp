package pocketpal.backend.endpoints;

import pocketpal.backend.constants.MiscellaneousConstants;
import pocketpal.communication.Request;
import pocketpal.communication.RequestParams;
import pocketpal.communication.Response;
import pocketpal.communication.ResponseStatus;
import pocketpal.data.entrylog.EntryLog;
import pocketpal.frontend.constants.MessageConstants;
import pocketpal.frontend.exceptions.InvalidArgumentsException;
import pocketpal.frontend.exceptions.InvalidCategoryException;
import pocketpal.frontend.exceptions.InvalidDateException;
import pocketpal.frontend.util.CategoryUtil;
import pocketpal.frontend.util.StringUtil;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Logger;

public class EntriesEndpoint extends Endpoint {
    private static final Logger logger = Logger.getLogger(EntriesEndpoint.class.getName());
    private final EntryLog entries;

    public EntriesEndpoint(EntryLog entries) {
        this.entries = entries;
    }

    /**
     * Main entry point for handling GET request
     *
     * @param request Request to be processed
     * @return Response with requested data
     */
    @Override
    public Response handleGet(Request request) {
        logger.info("/entries [GET]: request received");
        assert request != null;
        return request.hasParam(RequestParams.GET_SIZE)
                ? handleGetSize()
                : handleGetEntries(request);
    }

    /**
     * Get the number of entries
     *
     * @return Response with number of entries
     */
    private Response handleGetSize() {
        logger.info("/entries [GET]: request size");
        return new Response(ResponseStatus.OK, String.valueOf(entries.getSize()));
    }

    /**
     * Get entries by request parameters
     *
     * @param request The request should have the following data
     *                - data?: Number of recent entries to view, otherwise all entries will be returned.
     *                - params?: Entry filters
     * @return Response with requested entries
     */
    private Response handleGetEntries(Request request) {
        logger.info("/entries [GET]: request entries");

        try {
            // filter entries by parameters
            final EntryLog filteredEntries = handleGetEntriesFilter(request, entries);
            logger.info("/entries [GET]: OK" + request.getBody());
            if (!request.hasParam(RequestParams.NUM_ENTRIES)) {
                return new Response(ResponseStatus.OK, filteredEntries.serialise());
            }
            // return recent N entries
            final int numberOfEntriesRequested = getPositiveIntegerFromString(request
                    .getParam(RequestParams.NUM_ENTRIES));
            final EntryLog recentEntries = filteredEntries.getLatestEntries(numberOfEntriesRequested);
            return new Response(ResponseStatus.OK, recentEntries.serialise());
        } catch (InvalidCategoryException e) {
            logger.warning("/entries [GET]: unknown filter category" + request.getBody());
            return new Response(ResponseStatus.UNPROCESSABLE_CONTENT, MessageConstants.MESSAGE_INVALID_CATEGORY);
        } catch (InvalidDateException e) {
            logger.warning("/entries [GET]: invalid date");
            return new Response(ResponseStatus.UNPROCESSABLE_CONTENT, MessageConstants.MESSAGE_MIXED_DATE);
        } catch (NumberFormatException e) {
            logger.warning("/entries [GET]: invalid number of entries requested");
            return new Response(
                    ResponseStatus.UNPROCESSABLE_CONTENT,
                    MessageConstants.MESSAGE_INVALID_ID
            );
        } catch (InvalidArgumentsException e) {
            logger.warning("/entries [GET]: invalid arguments");
            return new Response(ResponseStatus.UNPROCESSABLE_CONTENT, e.getMessage());
        }
    }

    /**
     * Helper method for filtering entries based on request parameters.
     *
     * @param request The request should have the following data
     *                - param?: FILTER_BY_AMOUNT_START
     *                - param?: FILTER_BY_AMOUNT_END
     *                - param?: FILTER_BY_CATEGORY
     *                - param?: FILTER_BY_QUERY
     *                - param?: FILTER_BY_TIME_START
     *                - param?: FILTER_BY_TIME_END
     * @return Filtered entries if parameters exist, otherwise all entries
     * @throws InvalidCategoryException If category parameter is invalid
     */
    private EntryLog handleGetEntriesFilter(Request request, EntryLog entries) throws InvalidCategoryException,
            InvalidDateException, InvalidArgumentsException {
        logger.info("/entries [GET]: filtering entries");
        EntryLog filteredEntries = entries;
        final String category = request.getParam(RequestParams.FILTER_BY_CATEGORY);
        if (request.hasParam(RequestParams.FILTER_BY_CATEGORY)) {
            logger.info("/entries [GET]: filter by category - " + category);
            filteredEntries = filteredEntries.filterByCategory(CategoryUtil.convertStringToCategory(category));
        }

        if (request.hasParam(RequestParams.FILTER_BY_QUERY)) {
            String query = request.getParam(RequestParams.FILTER_BY_QUERY);
            logger.info("/entries: filter by query - " + query);
            filteredEntries = filteredEntries.filterByQuery(query);
        }

        // filter entries by amounts
        final boolean hasAmountStart = request.hasParam(RequestParams.FILTER_BY_AMOUNT_START);
        final boolean hasAmountEnd = request.hasParam(RequestParams.FILTER_BY_AMOUNT_END);
        final double filterAmountStart = hasAmountStart
                ? getAmountFromString(request.getParam(RequestParams.FILTER_BY_AMOUNT_START))
                : MiscellaneousConstants.AMOUNT_MIN_DOUBLE;
        final double filterAmountEnd = hasAmountEnd
                ? getAmountFromString(request.getParam(RequestParams.FILTER_BY_AMOUNT_END))
                : MiscellaneousConstants.AMOUNT_MAX_DOUBLE;
        logger.info("/entries [GET]: filter amount "
                + "(start: " + filterAmountStart
                + ", end: " + StringUtil.doubleToString(filterAmountEnd) + ")");
        filteredEntries = filteredEntries.filterByAmount(filterAmountStart, filterAmountEnd);

        // @@author leonghuenweng
        // filter entries by date
        boolean hasStartDate = request.hasParam(RequestParams.FILTER_BY_TIME_START);
        boolean hasEndDate = request.hasParam(RequestParams.FILTER_BY_TIME_END);
        boolean isFilterBetweenDates = hasStartDate && hasEndDate;
        if (isFilterBetweenDates) {
            String startDateString = request.getParam(RequestParams.FILTER_BY_TIME_START);
            String endDateString = request.getParam(RequestParams.FILTER_BY_TIME_END);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            LocalDateTime startDateTime = LocalDateTime.parse(startDateString, formatter);
            LocalDateTime endDateTime = LocalDateTime.parse(endDateString, formatter);

            logger.info(
                    "/entries [GET]: filter by date (start: " + startDateString + ", end: " + endDateString + ")");
            filteredEntries = filteredEntries.filterBetweenDates(startDateTime, endDateTime);
        }
        // @@author

        return filteredEntries;
    }
}
