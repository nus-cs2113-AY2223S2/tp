package seedu.duke.commands;

import org.junit.jupiter.api.Test;
import seedu.duke.objects.Inventory;
import seedu.duke.utils.parsers.AddParser;
import seedu.duke.utils.parsers.SellParser;

public class SellCommandTest {
    Inventory inventory;

    /**
     * Test for all scenarios of user inputs for the "sell" command.
     */
    @Test
    public void sellItemTest() {
        inventory = new Inventory();
        AddParser addParser = new AddParser("n/orange upc/123 qty/5 p/5",inventory);
        addParser.run();
        SellParser sellParser = new SellParser("upc/123 qty/1", inventory);
        sellParser.run();
        sellParser = new SellParser("upc/123 qty/-1", inventory);
        sellParser.run();
        sellParser = new SellParser("upc/123 qty/", inventory);
        sellParser.run();
        sellParser = new SellParser("upc/123 qty/   ", inventory);
        sellParser.run();
        sellParser = new SellParser("upc/123 qty/1 n/orange", inventory);
        sellParser.run();
        sellParser = new SellParser("upc/123 test", inventory);
        sellParser.run();
        sellParser = new SellParser("upc/123", inventory);
        sellParser.run();
        sellParser = new SellParser("upc/12 qty/1", inventory);
        sellParser.run();
        sellParser = new SellParser("upc/123 qty/1000", inventory);
        sellParser.run();
        sellParser = new SellParser("upc/123qty/1", inventory);
        sellParser.run();
        sellParser = new SellParser("upc/123 qty/dog", inventory);
        sellParser.run();
        sellParser = new SellParser("upc/", inventory);
        sellParser.run();
        sellParser = new SellParser("upc", inventory);
        sellParser.run();
    }

}
