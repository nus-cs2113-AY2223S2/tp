package seedu.duke.commands;

import org.junit.jupiter.api.Test;
import seedu.duke.objects.Inventory;
import seedu.duke.utils.parsers.AddParser;
import seedu.duke.utils.parsers.RestockParser;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RestockCommandTest {
    Inventory inventory;

    /**
     * Setup of a simple inventory list of size 1.
     */
    public void setupInventory() {
        inventory = new Inventory();
        AddParser addParser = new AddParser("n/orange upc/123 qty/5 p/5", inventory);
        addParser.run();
    }

    @Test
    public void restockItemLevelTest() {
        System.out.println("restockItemLevelTest");
        setupInventory();
        RestockParser restockParser = new RestockParser("upc/123 qty/5", inventory);
        restockParser.run();
        assertEquals(10, inventory.getItemInventory().get(0).getQuantity());
    }

    @Test
    public void restockNegativeQuantityTest() {
        System.out.println("restockNegativeQuantityTest");
        setupInventory();
        RestockParser restockParser = new RestockParser("upc/123 qty/-1", inventory);
        restockParser.run();
        assertEquals(5, inventory.getItemInventory().get(0).getQuantity());
    }

    @Test
    public void emptyRestockCommandTest() {
        System.out.println("emptyRestockCommandTest");
        setupInventory();
        RestockParser restockParser = new RestockParser("upc/123 qty/", inventory);
        restockParser.run();
        assertEquals(5, inventory.getItemInventory().get(0).getQuantity());
    }

    @Test
    public void stringAsRestockInputTest() {
        System.out.println("stringAsRestockInputTest");
        setupInventory();
        RestockParser restockParser = new RestockParser("upc/123 qty/Test", inventory);
        restockParser.run();
        assertEquals(5, inventory.getItemInventory().get(0).getQuantity());
    }

    @Test
    public void noQuantityInputTest() {
        System.out.println("noQuantityInputTest");
        setupInventory();
        RestockParser restockParser = new RestockParser("upc/123", inventory);
        restockParser.run();
        assertEquals(5, inventory.getItemInventory().get(0).getQuantity());
    }

    @Test
    public void quantityWithSpaceTest() {
        System.out.println("quantityWithSpaceTest");
        setupInventory();
        RestockParser restockParser = new RestockParser("upc/123 qty/ ", inventory);
        restockParser.run();
        assertEquals(5, inventory.getItemInventory().get(0).getQuantity());
    }

    @Test
    public void noSpacingBetweenParametersTest() {
        System.out.println("noSpacingBetweenParametersTest");
        setupInventory();
        RestockParser restockParser = new RestockParser("upc/123qty/", inventory);
        restockParser.run();
        assertEquals(5, inventory.getItemInventory().get(0).getQuantity());
    }

    @Test
    public void wrongQuantityParameterTest() {
        System.out.println("wrongQuantityParameterTest");
        setupInventory();
        RestockParser restockParser = new RestockParser("upc/123 q/-1", inventory);
        restockParser.run();
        assertEquals(5, inventory.getItemInventory().get(0).getQuantity());
    }

    @Test
    public void multipleDeclarationTest() {
        System.out.println("multipleDeclarationTest");
        setupInventory();
        RestockParser restockParser = new RestockParser("upc/123 qty/-1 qty/5 upc/123", inventory);
        restockParser.run();
        assertEquals(5, inventory.getItemInventory().get(0).getQuantity());
    }

    @Test
    public void wrongUpcCodeTest() {
        System.out.println("wrongUpcCodeTest");
        setupInventory();
        RestockParser restockParser = new RestockParser("upc/24234 qty/-1", inventory);
        restockParser.run();
        assertEquals(5, inventory.getItemInventory().get(0).getQuantity());
    }

    @Test
    public void noUpcCodeTest() {
        System.out.println("noUpcCodeTest");
        setupInventory();
        RestockParser restockParser = new RestockParser("qty/5", inventory);
        restockParser.run();
        assertEquals(5, inventory.getItemInventory().get(0).getQuantity());
    }

    @Test
    public void multipleQuantityInputTest() {
        System.out.println("multipleQuantityInputTest");
        setupInventory();
        RestockParser restockParser = new RestockParser("upc/123 qty/100 qty/6", inventory);
        restockParser.run();
        assertEquals(5, inventory.getItemInventory().get(0).getQuantity());
    }

    @Test
    public void outOfRangeQuantityTest() {
        System.out.println("outOfRangeQuantityTest");
        setupInventory();
        RestockParser restockParser = new RestockParser("upc/123 qty/99999999999999", inventory);
        restockParser.run();
        assertEquals(5, inventory.getItemInventory().get(0).getQuantity());
    }
}
