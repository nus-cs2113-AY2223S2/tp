package seedu.pocketpal.frontend.commands;

import seedu.pocketpal.backend.Backend;
import seedu.pocketpal.communication.Request;
import seedu.pocketpal.communication.RequestParams;
import seedu.pocketpal.communication.RequestMethod;
import seedu.pocketpal.communication.Response;
import seedu.pocketpal.communication.ResponseStatus;
import seedu.pocketpal.data.entry.Category;
import seedu.pocketpal.data.entrylog.EntryLog;
import seedu.pocketpal.data.parsing.EntryLogParser;
import seedu.pocketpal.frontend.exceptions.InvalidCategoryException;
import seedu.pocketpal.frontend.constants.MessageConstants;
import seedu.pocketpal.frontend.ui.UI;
import seedu.pocketpal.frontend.util.StringUtil;

public class ViewCommand extends Command {
    private final int numberOfEntriesToView;
    private final Category categoryToView;

    private final Double priceToViewMin;
    private final Double priceToViewMax;

    public ViewCommand(int numberOfEntriesToView) {
        this(numberOfEntriesToView, null, 0.0 , Double.MAX_VALUE);
    }

    public ViewCommand(int numberOfEntriesToView, Category categoryToView, Double priceToViewMin, Double priceToViewMax) {
        this.categoryToView = categoryToView;
        this.numberOfEntriesToView = numberOfEntriesToView;
        this.priceToViewMin = priceToViewMin;
        this.priceToViewMax = priceToViewMax;
        System.out.println(priceToViewMin);
        System.out.println(priceToViewMax);
        System.out.println("VIEWCOUNT: " + numberOfEntriesToView);
    }

//    public ViewCommand(int numberOfEntriesToView, Double priceToViewMin, Double priceToViewMax){
//        this.numberOfEntriesToView = numberOfEntriesToView;
//        this.priceToViewMin = priceToViewMin;
//        this.priceToViewMax = priceToViewMax;
//    }

//    public ViewCommand(int priceToViewMin, int priceToViewMax){
//        this.numberOfEntriesToView = 2;
//        this.categoryToView = null;
//        this.priceToViewMin = priceToViewMin;
//        this.priceToViewMax = priceToViewMax;
//    }

    /**
     * Executes the view command.
     *
     * @param ui      UI to output action result
     * @param backend Backend to process requests
     */
    @Override
    public void execute(UI ui, Backend backend) throws InvalidCategoryException {
        final Request request = new Request(RequestMethod.GET);
        request.addParam(RequestParams.NUM_ENTRIES, String.valueOf(numberOfEntriesToView));

        if (categoryToView != null) {
            String category = StringUtil.toTitleCase(String.valueOf(categoryToView));
            request.addParam(RequestParams.FILTER_BY_CATEGORY, category);
        }

        request.addParam(RequestParams.FILTER_BY_AMOUNT_START, String.valueOf(priceToViewMin));
        request.addParam(RequestParams.FILTER_BY_AMOUNT_END, String.valueOf(priceToViewMax));

        Response response = backend.requestEndpointEntries(request);
        EntryLog relevantEntries = EntryLogParser.deserialise(response.getData());
        if (response.getResponseStatus() == ResponseStatus.UNPROCESSABLE_CONTENT) {
            throw new InvalidCategoryException(MessageConstants.MESSAGE_INVALID_CATEGORY);
        }

        if (categoryToView != null) {
            ui.printEntriesToBeViewed(relevantEntries, categoryToView);
        } else {
            ui.printEntriesToBeViewed(relevantEntries);
        }
    }
}
