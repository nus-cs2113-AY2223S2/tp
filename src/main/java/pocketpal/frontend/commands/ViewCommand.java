// @@author leonghuenweng
package pocketpal.frontend.commands;

import pocketpal.backend.Backend;
import pocketpal.backend.constants.MiscellaneousConstants;
import pocketpal.data.entry.Category;
import pocketpal.data.entrylog.EntryLog;
import pocketpal.data.parsing.EntryLogParser;
import pocketpal.frontend.constants.MessageConstants;
import pocketpal.frontend.exceptions.InvalidArgumentsException;
import pocketpal.frontend.exceptions.InvalidCategoryException;
import pocketpal.communication.Request;
import pocketpal.communication.RequestParams;
import pocketpal.communication.RequestMethod;
import pocketpal.communication.Response;
import pocketpal.communication.ResponseStatus;
import pocketpal.frontend.ui.UI;
import pocketpal.frontend.util.StringUtil;

public class ViewCommand extends Command {
    private final int numberOfEntriesToView;
    private final Category categoryToView;
    private final String startDateString;
    private final String endDateString;

    private final Double priceToViewMin;
    private final Double priceToViewMax;

    public ViewCommand(int numberOfEntriesToView) {
        this(numberOfEntriesToView, null, MiscellaneousConstants.AMOUNT_MIN_DOUBLE,
                MiscellaneousConstants.AMOUNT_MAX_DOUBLE, "", "");
    }

    public ViewCommand(int numberOfEntriesToView,
                       Category categoryToView, Double priceToViewMin,
                       Double priceToViewMax, String startDate, String endDate) {
        this.categoryToView = categoryToView;
        this.numberOfEntriesToView = numberOfEntriesToView;
        this.priceToViewMin = priceToViewMin;
        this.priceToViewMax = priceToViewMax;
        this.startDateString = startDate;
        this.endDateString = endDate;
    }

    /**
     * Executes the view command.
     *
     * @param ui      UI to output action result
     * @param backend Backend to process requests
     */
    @Override
    public void execute(UI ui, Backend backend) throws InvalidCategoryException, InvalidArgumentsException {
        final Request request = new Request(RequestMethod.GET);
        request.addParam(RequestParams.NUM_ENTRIES, String.valueOf(numberOfEntriesToView));

        if (categoryToView != null) {
            String category = StringUtil.toTitleCase(String.valueOf(categoryToView));
            request.addParam(RequestParams.FILTER_BY_CATEGORY, category);
        }
        if (numberOfEntriesToView <= 0) {
            throw new InvalidArgumentsException(MessageConstants.MESSAGE_INVALID_ID);
        }
        if (!startDateString.isEmpty() && !endDateString.isEmpty()){
            request.addParam(RequestParams.FILTER_BY_TIME_START, startDateString);
            request.addParam(RequestParams.FILTER_BY_TIME_END, endDateString);
        }

        request.addParam(RequestParams.FILTER_BY_AMOUNT_START,String.format("%f", priceToViewMin));
        request.addParam(RequestParams.FILTER_BY_AMOUNT_END, String.format("%f", priceToViewMax));

        Response response = backend.requestEndpointEntries(request);
        if (response.getResponseStatus() == ResponseStatus.UNPROCESSABLE_CONTENT) {
            throw new InvalidCategoryException(response.getData());
        }

        EntryLog relevantEntries = EntryLogParser.deserialise(response.getData());
        if (categoryToView != null) {
            ui.printEntriesToBeViewed(relevantEntries, categoryToView);
        } else {
            ui.printEntriesToBeViewed(relevantEntries);
        }
    }
}
// @@author
