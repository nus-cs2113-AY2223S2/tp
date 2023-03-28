package seedu.duke.commands;

import org.junit.jupiter.api.Test;
import seedu.duke.objects.Inventory;
import seedu.duke.utils.parsers.AddParser;
import seedu.duke.utils.parsers.EditParser;
import seedu.duke.utils.parsers.SellParser;

public class EditCommandTest {
    Inventory inventory;

    /**
     * Test for all scenarios of user inputs for the "sell" command.
     */
    @Test
    public void sellItemTest() {
        inventory = new Inventory();

        AddParser addParser = new AddParser("n/orange upc/123 qty/5 p/5",inventory);
        addParser.run();
        EditParser editParser = new EditParser("upc/123 n/", inventory);
        editParser.run();
        editParser = new EditParser("upc/123 n/oranges qty/5", inventory);
        editParser.run();
        editParser = new EditParser("upc/123 n/orange and apples qty/5", inventory);
        editParser.run();
        editParser = new EditParser("upc/123 qty/-5", inventory);
        editParser.run();
        editParser = new EditParser("upc/123 p/4", inventory);
        editParser.run();
        editParser = new EditParser("upc/123 n/p/", inventory);
        editParser.run();
        editParser = new EditParser("upc/12", inventory);
        editParser.run();
        editParser = new EditParser("upc/12 n/pear", inventory);
        editParser.run();
        editParser = new EditParser("upc/123 c/fruit", inventory);
        editParser.run();
        editParser = new EditParser("upc/123 c/fruit and vegetables", inventory);
        editParser.run();
        editParser = new EditParser("upc/123 p/double", inventory);
        editParser.run();
    }

}
