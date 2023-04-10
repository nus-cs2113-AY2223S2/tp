package seedu.duke.commands;

import org.junit.jupiter.api.Test;
import seedu.duke.objects.Inventory;
import seedu.duke.utils.parsers.AddParser;
import seedu.duke.utils.parsers.SellParser;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SellCommandTest {
    Inventory inventory;

    /**
     * Setup of a simple inventory list of size 1.
     */
    public void setupInventory() {
        inventory = new Inventory();
        AddParser addParser = new AddParser("n/orange upc/123 qty/5 p/5", inventory);
        addParser.run();
    }

    /**
     * Test for all scenarios of user inputs for the "sell" command.
     */
    @Test
    public void sellItemTest() {
        setupInventory();
        SellParser sellParser = new SellParser("upc/123 qty/1", inventory);
        sellParser.run();
        assertEquals(4, inventory.getItemInventory().get(0).getQuantity());
    }

    @Test
    public void negativeQuantityTest() {
        setupInventory();
        SellParser sellParser = new SellParser("upc/123 qty/-1", inventory);
        sellParser.run();
        assertEquals(5, inventory.getItemInventory().get(0).getQuantity());
    }

    @Test
    public void noQuantityInputTest() {
        setupInventory();
        SellParser sellParser = new SellParser("upc/123 qty/", inventory);
        sellParser.run();
        assertEquals(5, inventory.getItemInventory().get(0).getQuantity());
    }

    @Test
    public void spaceInputsTest() {
        setupInventory();
        SellParser sellParser = new SellParser("upc/123 qty/   ", inventory);
        sellParser.run();
        assertEquals(5, inventory.getItemInventory().get(0).getQuantity());
    }

    @Test
    public void unknownParameterTest() {
        setupInventory();
        SellParser sellParser = new SellParser("upc/123 qty/1 n/orange", inventory);
        sellParser.run();
        assertEquals(5, inventory.getItemInventory().get(0).getQuantity());
    }

    @Test
    public void noQuantityParameterTest() {
        setupInventory();
        SellParser sellParser = new SellParser("upc/123 test", inventory);
        sellParser.run();
        assertEquals(5, inventory.getItemInventory().get(0).getQuantity());
    }

    @Test
    public void noParameterTest() {
        setupInventory();
        SellParser sellParser = new SellParser("upc/123", inventory);
        sellParser.run();
        assertEquals(5, inventory.getItemInventory().get(0).getQuantity());
    }

    @Test
    public void nonExistentUpcTest() {
        setupInventory();
        SellParser sellParser = new SellParser("upc/12 qty/1", inventory);
        sellParser.run();
        assertEquals(5, inventory.getItemInventory().get(0).getQuantity());
    }

    @Test
    public void sellMoreThanTotalQuantityTest() {
        setupInventory();
        SellParser sellParser = new SellParser("upc/123 qty/1000", inventory);
        sellParser.run();
        assertEquals(5, inventory.getItemInventory().get(0).getQuantity());
    }

    @Test
    public void noSpacingTest() {
        setupInventory();
        SellParser sellParser = new SellParser("upc/123qty/1", inventory);
        sellParser.run();
        assertEquals(5, inventory.getItemInventory().get(0).getQuantity());
    }

    @Test
    public void stringInputTest() {
        setupInventory();
        SellParser sellParser = new SellParser("upc/123 qty/dog", inventory);
        sellParser.run();
        assertEquals(5, inventory.getItemInventory().get(0).getQuantity());
    }

    @Test
    public void noUpcCodeTest() {
        setupInventory();
        SellParser sellParser = new SellParser("upc/", inventory);
        sellParser.run();
        assertEquals(5, inventory.getItemInventory().get(0).getQuantity());
    }

    @Test
    public void rangeLimitTest() {
        setupInventory();
        SellParser sellParser = new SellParser("upc/123 qty/999999999999", inventory);
        sellParser.run();
        assertEquals(5, inventory.getItemInventory().get(0).getQuantity());
    }

}
