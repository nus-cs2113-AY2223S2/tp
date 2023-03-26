package seedu.duke.commands;

import org.junit.jupiter.api.Test;
import seedu.duke.objects.Inventory;
import seedu.duke.utils.parsers.AddParser;
import seedu.duke.utils.parsers.RestockParser;

public class RestockCommandTest {
    Inventory inventory;

    /**
     * Test for all scenarios of user inputs for the "restock" command.
     */
    @Test
    public void restockItemLevel() {
        inventory = new Inventory();
        AddParser addParser = new AddParser("n/orange upc/123 qty/5 p/5",inventory);
        addParser.run();
        RestockParser restockParser = new RestockParser("upc/123 qty/5", inventory);
        restockParser.run();
        restockParser = new RestockParser("upc/123 qty/-1", inventory);
        restockParser.run();
        restockParser = new RestockParser("upc/123 qty/", inventory);
        restockParser.run();
        restockParser = new RestockParser("upc/123 qty/Test", inventory);
        restockParser.run();
        restockParser = new RestockParser("upc/123", inventory);
        restockParser.run();
        restockParser = new RestockParser("upc/123 qty/ ", inventory);
        restockParser.run();
        restockParser = new RestockParser("upc/123qty/", inventory);
        restockParser.run();
        restockParser = new RestockParser("upc/123 q/-1", inventory);
        restockParser.run();
        restockParser = new RestockParser("upc/123 qty/-1 qty/5 upc/123", inventory);
        restockParser.run();
        restockParser = new RestockParser("upc/24234 qty/-1", inventory);
        restockParser.run();
        restockParser = new RestockParser("qty/5", inventory);
        restockParser.run();
    }
}
