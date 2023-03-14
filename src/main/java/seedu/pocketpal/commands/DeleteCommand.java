package seedu.pocketpal.commands;

import seedu.pocketpal.constants.MessageConstants;
import seedu.pocketpal.entries.Entry;
import seedu.pocketpal.entrylog.EntryLog;
import seedu.pocketpal.exceptions.InvalidArgumentsException;
import seedu.pocketpal.exceptions.InvalidEntryIdException;
import seedu.pocketpal.ui.UI;

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
        this.entryId = inputId - 1;
    }

    /**
     * Deletes Entry object from entry log
     *
     * @param entries List of entries to delete from
     */
    @Override
    public void executor(EntryLog entries, UI ui)
            throws InvalidArgumentsException, InvalidEntryIdException {
        if (entryId < 0 || entryId >= entries.getSize()) {
            logger.log(Level.WARNING, "Input entry ID is invalid");
            throw new InvalidEntryIdException(MessageConstants.MESSAGE_INVALID_ID);
        }
        Entry deletedEntry = entries.deleteEntry(entryId);
        new UI().printExpenditureDeleted(deletedEntry);
    }

    public Integer getEntryId() {
        return this.entryId;
    }
}
