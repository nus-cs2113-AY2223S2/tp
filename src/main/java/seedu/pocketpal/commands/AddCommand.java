package seedu.pocketpal.commands;

import seedu.pocketpal.constants.EntryConstants;
import seedu.pocketpal.constants.MessageConstants;
import seedu.pocketpal.entries.Category;
import seedu.pocketpal.entries.Entry;
import seedu.pocketpal.entrylog.EntryLog;
import seedu.pocketpal.exceptions.InvalidCategoryException;
import seedu.pocketpal.ui.UI;
import seedu.pocketpal.util.StringUtil;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Represents the add feature in PocketPal. Users may provide a description
 * and specify the corresponding price and category of their entry
 * e.g., <code>/add lunch -p 15 -c food</code>
 */
public class AddCommand extends Command {
    private static final Logger logger = Logger.getLogger(AddCommand.class.getName());
    private final Entry entryObj;

    /**
     * AddCommand constructor which initialises entryObj to be added
     *
     * @param description Description of the entry
     * @param category    Category which entry belongs to
     * @param amount      Price of entry
     */
    public AddCommand(String description, double amount, String category) throws InvalidCategoryException {
        switch (StringUtil.toTitleCase(category)) {
        case EntryConstants.CLOTHING:
            this.entryObj = new Entry(description, amount, Category.CLOTHING);
            break;

        case EntryConstants.ENTERTAINMENT:
            this.entryObj = new Entry(description, amount, Category.ENTERTAINMENT);
            break;

        case EntryConstants.FOOD:
            this.entryObj = new Entry(description, amount, Category.FOOD);
            break;

        case EntryConstants.INCOME:
            this.entryObj = new Entry(description, amount, Category.INCOME);
            break;

        case EntryConstants.MEDICAL:
            this.entryObj = new Entry(description, amount, Category.MEDICAL);
            break;

        case EntryConstants.OTHERS:
            this.entryObj = new Entry(description, amount, Category.OTHERS);
            break;

        case EntryConstants.PERSONAL:
            this.entryObj = new Entry(description, amount, Category.PERSONAL);
            break;

        case EntryConstants.TRANSPORTATION:
            this.entryObj = new Entry(description, amount, Category.TRANSPORTATION);
            break;

        case EntryConstants.UTILITIES:
            this.entryObj = new Entry(description, amount, Category.UTILITIES);
            break;

        default:
            logger.log(Level.WARNING, "Input category is invalid");
            throw new InvalidCategoryException(MessageConstants.MESSAGE_INVALID_CATEGORY);
        }
    }

    /**
     * Adds Entry object to entry log
     *
     * @param entries List of entries to add to
     */
    @Override
    public void executor(EntryLog entries, UI ui) {
        entries.addEntry(entryObj);
        ui.printExpenditureAdded(entryObj);
    }

    public Entry getEntryObj() {
        return this.entryObj;
    }
}
