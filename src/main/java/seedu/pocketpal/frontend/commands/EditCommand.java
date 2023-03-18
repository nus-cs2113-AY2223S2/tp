package seedu.pocketpal.frontend.commands;

import seedu.pocketpal.backend.Backend;
import seedu.pocketpal.communication.Request;
import seedu.pocketpal.communication.RequestMethod;
import seedu.pocketpal.communication.RequestParams;
import seedu.pocketpal.communication.Response;
import seedu.pocketpal.communication.ResponseStatus;
import seedu.pocketpal.frontend.constants.MessageConstants;
import seedu.pocketpal.data.entry.Entry;
import seedu.pocketpal.data.parsing.EntryParser;
import seedu.pocketpal.frontend.exceptions.InvalidArgumentsException;
import seedu.pocketpal.frontend.exceptions.InvalidCategoryException;
import seedu.pocketpal.frontend.util.StringUtil;
import seedu.pocketpal.frontend.ui.UI;

public class EditCommand extends Command {
    private final int expenseId;
    private final String newDescription;
    private final String newPrice;
    private final String newCategory;

    public EditCommand(String expenseId, String description, String category, String price) {
        this.expenseId = Integer.parseInt(expenseId);
        this.newCategory = category;
        this.newDescription = description;
        this.newPrice = price;
    }

    /**
     * Only edits attributes which have new values provided by the user, the other
     * attributes are unchanged
     *
     * @param ui      UI to output action result
     * @param backend Backend to process requests
     * @throws InvalidArgumentsException If expenseId is invalid
     * @throws InvalidCategoryException  If category is invalid
     */
    @Override
    public void execute(UI ui, Backend backend) throws InvalidArgumentsException, InvalidCategoryException {
        final Request request = new Request(RequestMethod.PATCH, String.valueOf(expenseId));
        if (!newPrice.isEmpty()) {
            request.addParam(RequestParams.EDIT_AMOUNT, newPrice);
        }
        if (!newDescription.isEmpty()) {
            request.addParam(RequestParams.EDIT_DESCRIPTION, newDescription);
        }
        if (!newCategory.isEmpty()) {
            request.addParam(RequestParams.EDIT_CATEGORY, StringUtil.toTitleCase(newCategory));
        }

        Response response = backend.requestEndpointEntry(request);
        // throw errors if input values are erroneous
        if (response.getResponseStatus() == ResponseStatus.NOT_FOUND) {
            throw new InvalidArgumentsException(MessageConstants.MESSAGE_INVALID_ID);
        }
        if (response.getResponseStatus() == ResponseStatus.UNPROCESSABLE_CONTENT) {
            throw new InvalidCategoryException(MessageConstants.MESSAGE_INVALID_CATEGORY);
        }

        Entry newEntry = EntryParser.deserialise(response.getData());
        ui.printExpenditureEdited(newEntry);
    }

    /**
     * Used for testing other methods in editCommands Class
     *
     * @return String Array of all attributes in editCommand
     */
    public String[] getAttributes() {
        return new String[]{
            Integer.toString(this.expenseId),
            this.newCategory,
            this.newDescription,
            this.newPrice
        };
    }
}
