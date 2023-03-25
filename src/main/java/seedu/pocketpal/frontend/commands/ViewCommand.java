// @@author leonghuenweng
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

    public ViewCommand(int numberOfEntriesToView) {
        this(numberOfEntriesToView, null);
    }

    public ViewCommand(int numberOfEntriesToView, Category categoryToView) {
        this.categoryToView = categoryToView;
        this.numberOfEntriesToView = numberOfEntriesToView;
    }

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
// @@author