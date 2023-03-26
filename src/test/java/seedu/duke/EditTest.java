package seedu.duke;

import org.junit.jupiter.api.Test;
import seedu.duke.commands.SearchCommand;
import seedu.duke.objects.Inventory;
import seedu.duke.objects.Item;
import seedu.duke.utils.parsers.AddParser;
import seedu.duke.utils.parsers.EditParser;
import seedu.duke.types.Types;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EditTest {

    Inventory inventory = new Inventory();

    @Test
    public void sampleTest() {
        AddParser addParser = new AddParser("n/orange upc/123 qty/5 p/5",inventory);
        addParser.run();
        EditParser editParser = new EditParser("upc/123 n/apple", inventory);
        editParser.run();
        String[] data = {"upc/123"};
        Item updatedItem = null;
        SearchCommand searchCommand = new SearchCommand(inventory, "123", Types.SearchType.UPC);
        updatedItem = searchCommand.searchUPC();
        assertEquals("apple", updatedItem.getName(), "Name not changed to apple.");
    }
}
