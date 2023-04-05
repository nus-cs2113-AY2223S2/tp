// @@author kaceycsn
package pocketpal.frontend.commands;

import pocketpal.backend.Backend;
import pocketpal.data.entry.Category;
import pocketpal.data.entry.Entry;
import pocketpal.data.parsing.EntryParser;
import pocketpal.frontend.constants.EntryConstants;
import pocketpal.frontend.constants.MessageConstants;
import pocketpal.frontend.exceptions.InvalidCategoryException;
import pocketpal.communication.Request;
import pocketpal.communication.RequestMethod;
import pocketpal.communication.Response;
import pocketpal.communication.ResponseStatus;
import pocketpal.frontend.ui.UI;
import pocketpal.frontend.util.StringUtil;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Represents the add feature in PocketPal. Users may provide
 * a description and specify the corresponding price and
 * category of their entry
 * e.g., <code>/add lunch -d chicken rice -p 15 -c food</code>
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
     * @throws InvalidCategoryException If input category is invalid
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
     * Constructs AddCommand object using entry to be added
     *
     * @param entry Entry to be added into the Entry Log
     */
    public AddCommand(Entry entry) {
        this.entryObj = new Entry(entry.getDescription(), entry.getAmount(), entry.getCategory());
    }

    /**
     * Adds Entry object to entry log
     *
     * @param ui UI to output action result
     * @param backend  Backend to process requests
     */
    @Override
    public void execute(UI ui, Backend backend) {
        final Request request = new Request(RequestMethod.POST, EntryParser.serialise(entryObj));
        final Response response = backend.requestEndpointEntry(request);
        if (response.getResponseStatus() == ResponseStatus.CREATED) {
            ui.printExpenditureAdded(entryObj);
            return;
        }
        logger.severe("Add entry operation failed.");
        throw new AssertionError();
    }

    /**
     * Returns entry object initialised by the AddCommand constructor
     *
     * @return Entry object
     */

    public Entry getEntryObj() {
        return this.entryObj;
    }
}
// @@author
