package seedu.duke;

import org.junit.jupiter.api.Test;
import seedu.duke.commands.SearchCommand;
import seedu.duke.exceptions.EditErrorException;
import seedu.duke.objects.Inventory;
import seedu.duke.objects.Item;
import seedu.duke.utils.parser.Parser;
import seedu.duke.types.Types;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class EditTest {

    Inventory inventory = new Inventory();
    Parser testParser = new Parser(inventory);

    @Test
    public void sampleTest() {
        testParser.parseAdd("n/orange upc/123 qty/5 p/5",inventory);
        testParser.parseEdit("upc/123 n/apple",inventory);
        String[] data = {"upc/123"};
        Item updatedItem = null;
        SearchCommand searchCommand = new SearchCommand(inventory, "123", Types.SearchType.UPC);
        updatedItem = searchCommand.searchUPC();
        assertEquals("apple", updatedItem.getName(), "Name not changed to apple.");
    }
}
