package seedu.duke.commands;

import seedu.duke.entries.Category;
import seedu.duke.entries.Entry;
import seedu.duke.entrylog.EntryLog;
import seedu.duke.exceptions.InvalidArgumentsException;
import seedu.duke.ui.UI;

public class EditCommand extends Command {
    private final int expenseId;
    private final String newDescription;
    private final String newPrice;
    private String newCategory;

    public EditCommand(String expenseId, String description, String category, String price) {
        this.expenseId = Integer.parseInt(expenseId) - 1;
        this.newCategory = category;
        this.newDescription = description;
        this.newPrice = price;
    }

    /**
     * Only edits attributes which have new values provided by the user, the other
     * attributes are unchanged
     *
     * @param entryLog Entry Log containing all entries
     * @throws InvalidArgumentsException If expenseId is invalid
     */
    @Override
    public void executor(EntryLog entryLog) throws InvalidArgumentsException {
        UI ui = new UI();
        Entry oldEntry = entryLog.getEntry(this.expenseId);

        if (!newPrice.isEmpty()) {
            oldEntry.setAmount(Double.parseDouble(this.newPrice));
        }
        if (!newDescription.isEmpty()) {
            oldEntry.setDescription(this.newDescription);
        }
        if (!newCategory.isEmpty()) {
            this.newCategory = this.newCategory.toUpperCase();
            oldEntry.setCategory(Category.valueOf(this.newCategory));
        }
        ui.printExpenditureEdited(oldEntry);
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
