package pocketpal.backend.endpoints;

import pocketpal.communication.Request;
import pocketpal.communication.RequestParams;
import pocketpal.communication.Response;
import pocketpal.communication.ResponseStatus;
import pocketpal.data.entrylog.EntryLog;
import pocketpal.frontend.constants.MessageConstants;
import pocketpal.frontend.exceptions.InvalidCategoryException;
import pocketpal.frontend.exceptions.InvalidDateException;
import pocketpal.frontend.util.CategoryUtil;

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
        if (request.hasParam(RequestParams.GET_SIZE)) {
            return handleGetSize();
        }
        return handleGetEntries(request);
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
        final EntryLog entriesToFilter = request.hasParam(RequestParams.NUM_ENTRIES)
                ? entries.getLatestEntries(Integer.parseInt(request.getParam(RequestParams.NUM_ENTRIES)))
                : entries;

        try {
            final EntryLog filteredEntries = handleGetEntriesFilter(request, entriesToFilter);
            logger.info("/entries [GET]: OK" + request.getBody());
            return new Response(ResponseStatus.OK, filteredEntries.serialise());
        } catch (InvalidCategoryException e) {
            logger.warning("/entries [GET]: unknown filter category" + request.getBody());
            return new Response(ResponseStatus.UNPROCESSABLE_CONTENT, MessageConstants.MESSAGE_INVALID_CATEGORY);
        } catch (InvalidDateException e) {
            logger.warning("/entries [GET]: invalid date");
            return new Response(ResponseStatus.UNPROCESSABLE_CONTENT, MessageConstants.MESSAGE_MIXED_DATE);
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
            InvalidDateException {
        logger.info("/entries [GET]: filtering entries");
        EntryLog filteredEntries = entries;
        String category = request.getParam(RequestParams.FILTER_BY_CATEGORY);
        try {
            if (request.hasParam(RequestParams.FILTER_BY_CATEGORY)) {
                logger.info("/entries [GET]: filter by category - " + category);
                filteredEntries = filteredEntries.filterByCategory(CategoryUtil.convertStringToCategory(category));
            }
        } catch (InvalidCategoryException e) {
            logger.warning("/entries [GET]: received invalid category - " + category);
            throw e;
        }

        if (request.hasParam(RequestParams.FILTER_BY_QUERY)) {
            String query = request.getParam(RequestParams.FILTER_BY_QUERY);
            logger.info("/entries: filter by query - " + query);
            filteredEntries = filteredEntries.filterByQuery(query);
        }

        boolean hasAmountStart = request.hasParam(RequestParams.FILTER_BY_AMOUNT_START);
        boolean hasAmountEnd = request.hasParam(RequestParams.FILTER_BY_AMOUNT_END);
        boolean hasStartDate = request.hasParam(RequestParams.FILTER_BY_TIME_START);
        boolean hasEndDate = request.hasParam(RequestParams.FILTER_BY_TIME_END);
        boolean isFilterBetweenDates = hasStartDate && hasEndDate;
        boolean isFilterAmountRange = hasAmountStart && hasAmountEnd;
        boolean isFilterMinAmount = hasAmountStart && !hasAmountEnd;
        boolean isFilterMaxAmount = !hasAmountStart && hasAmountEnd;

        if (isFilterAmountRange) {
            double amountStart = Double.parseDouble(request.getParam(RequestParams.FILTER_BY_AMOUNT_START));
            double amountEnd = Double.parseDouble(request.getParam(RequestParams.FILTER_BY_AMOUNT_END));
            logger.info("/entries [GET]: filter by range (start: " + amountStart + ", end: " + amountEnd + ")");
            filteredEntries = filteredEntries.filterByAmount(amountStart, amountEnd);
        }
        if (isFilterMinAmount) {
            double amountStart = Double.parseDouble(request.getParam(RequestParams.FILTER_BY_AMOUNT_START));
            logger.info("/entries [GET]: filter by amount >= " + amountStart);
            filteredEntries = filteredEntries.filterByAmount(amountStart, Double.MAX_VALUE);
        }
        if (isFilterMaxAmount) {
            double amountEnd = Double.parseDouble(request.getParam(RequestParams.FILTER_BY_AMOUNT_END));
            logger.info("/entries [GET]: filter by amount <= " + amountEnd);
            filteredEntries = filteredEntries.filterByAmount(0, amountEnd);
        }
        if (isFilterBetweenDates) {
            String startDateString = request.getParam(RequestParams.FILTER_BY_TIME_START);
            String endDateString = request.getParam(RequestParams.FILTER_BY_TIME_END);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy HH:mm");
            LocalDateTime startDateTime = LocalDateTime.parse(startDateString, formatter);
            LocalDateTime endDateTime = LocalDateTime.parse(endDateString, formatter);
            logger.info(
                    "/entries [GET]: filter by date (start: " + startDateString + ", end: " + endDateString + ")");
            filteredEntries = filteredEntries.filterBetweenDates(startDateTime, endDateTime);
        }

        return filteredEntries;
    }
}
