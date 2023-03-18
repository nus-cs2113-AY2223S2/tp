package seedu.pocketpal.frontend.commands;

import seedu.pocketpal.backend.Backend;
import seedu.pocketpal.communication.Request;
import seedu.pocketpal.communication.RequestMethod;
import seedu.pocketpal.communication.Response;
import seedu.pocketpal.communication.ResponseStatus;
import seedu.pocketpal.data.entry.Entry;
import seedu.pocketpal.data.parsing.EntryParser;
import seedu.pocketpal.frontend.constants.MessageConstants;
import seedu.pocketpal.frontend.exceptions.InvalidEntryIdException;
import seedu.pocketpal.frontend.ui.UI;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Represents the delete feature in PocketPal. Users may specify
 * the index of the entry to be deleted
 * e.g., <code>/delete 10</code>
 */
public class DeleteCommand extends Command {
    private static final Logger logger = Logger.getLogger(DeleteCommand.class.getName());

    private final Integer entryId;

    public DeleteCommand(Integer inputId) {
        this.entryId = inputId;
    }

    /**
     * Deletes Entry object from entry log
     *
     * @param ui UI to output action result
     * @param backend  Backend to process requests
     */
    @Override
    public void execute(UI ui, Backend backend) throws InvalidEntryIdException {
        // delete request
        Request requestDelete = new Request(RequestMethod.DELETE, String.valueOf(entryId));
        Response responseDelete = backend.requestEndpointEntry(requestDelete);
        if (responseDelete.getResponseStatus() == ResponseStatus.NOT_FOUND) {
            logger.log(Level.WARNING, "Input entry ID is invalid");
            throw new InvalidEntryIdException(MessageConstants.MESSAGE_INVALID_ID);
        }

        Entry deletedEntry = EntryParser.deserialise(responseDelete.getData());
        ui.printExpenditureDeleted(deletedEntry);
    }

    public Integer getEntryId() {
        return this.entryId;
    }
}
