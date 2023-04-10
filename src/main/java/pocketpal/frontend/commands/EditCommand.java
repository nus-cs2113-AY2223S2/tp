// @@author leonghuenweng
package pocketpal.frontend.commands;

import pocketpal.backend.Backend;
import pocketpal.data.entry.Entry;
import pocketpal.data.parsing.EntryParser;
import pocketpal.frontend.exceptions.InvalidArgumentsException;
import pocketpal.frontend.exceptions.InvalidCategoryException;
import pocketpal.communication.Request;
import pocketpal.communication.RequestMethod;
import pocketpal.communication.RequestParams;
import pocketpal.communication.Response;
import pocketpal.communication.ResponseStatus;
import pocketpal.frontend.util.StringUtil;
import pocketpal.frontend.ui.UI;

public class EditCommand extends Command {
    private final int entryId;
    private final String newDescription;
    private final String newPrice;
    private final String newCategory;

    public EditCommand(String entryId, String description, String category, String price) {
        this.entryId = Integer.parseInt(entryId);
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
     * @throws InvalidArgumentsException If entryId is invalid
     * @throws InvalidCategoryException  If category is invalid
     */
    @Override
    public void execute(UI ui, Backend backend) throws InvalidArgumentsException, InvalidCategoryException {
        final Request request = new Request(RequestMethod.PATCH, String.valueOf(entryId));
        if (newPrice != null) {
            request.addParam(RequestParams.EDIT_AMOUNT, newPrice);
        }
        if (newDescription != null) {
            request.addParam(RequestParams.EDIT_DESCRIPTION, newDescription);
        }
        if (newCategory != null) {
            request.addParam(RequestParams.EDIT_CATEGORY, StringUtil.toTitleCase(newCategory));
        }

        Response response = backend.requestEndpointEntry(request);
        // throw errors if input values are erroneous
        if (response.getResponseStatus() != ResponseStatus.OK) {
            throw new InvalidArgumentsException(response.getData());
        }

        Entry newEntry = EntryParser.deserialise(response.getData());
        ui.printEntryEdited(newEntry);
    }

    /**
     * Used for testing other methods in editCommands Class
     *
     * @return String Array of all attributes in editCommand
     */
    public String[] getAttributes() {
        return new String[]{
            Integer.toString(this.entryId),
            this.newCategory,
            this.newDescription,
            this.newPrice
        };
    }
}
// @@author
