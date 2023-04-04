package seedu.duke.commands;

import org.junit.jupiter.api.Test;
import seedu.duke.objects.Inventory;
import seedu.duke.objects.Item;
import seedu.duke.utils.parsers.AddParser;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class FilterCommandTest {
    Inventory inventory;

    /**
     * Tests the filter price function.
     */
    @Test
    public void filterPriceTest() {
        inventory = new Inventory();
        AddParser addParser = new AddParser("n/orange upc/1 qty/5 p/5",inventory);
        addParser.run();
        FilterCommand filterCommand = new FilterCommand(inventory, 5.0, "p/get");
        ArrayList<Item> filteredItems = filterCommand.getFilteredItems();
        assertEquals(1, filteredItems.size());
        filterCommand = new FilterCommand(inventory, 5.0, "p/let");
        filteredItems = filterCommand.getFilteredItems();
        assertEquals(1, filteredItems.size());
        filterCommand = new FilterCommand(inventory, 4.5, "p/gt");
        filteredItems = filterCommand.getFilteredItems();
        assertEquals(1, filteredItems.size());
        filterCommand = new FilterCommand(inventory, 5.5, "p/lt");
        filteredItems = filterCommand.getFilteredItems();
        assertEquals(1, filteredItems.size());
        filterCommand = new FilterCommand(inventory, 3.5, "p/lt");
        filteredItems = filterCommand.getFilteredItems();
        assertEquals(null, filteredItems);
    }

    @Test
    public void filterCategoryTest() {
        inventory = new Inventory();
        AddParser addParser = new AddParser("n/orange upc/1 qty/5 p/5",inventory);
        addParser.run();
        AddParser addParser1 = new AddParser("n/apple upc/2 qty/5 p/5 c/fruit",inventory);
        addParser1.run();
        FilterCommand filterCommand = new FilterCommand(inventory, "fruit", "f/category");
        ArrayList<Item> filteredItems = filterCommand.getFilteredItems();
        assertEquals(1, filteredItems.size());
        filterCommand = new FilterCommand(inventory, "uncategorized", "f/category");
        filteredItems = filterCommand.getFilteredItems();
        assertEquals(1, filteredItems.size());
        AddParser addParser2 = new AddParser("n/watermelon upc/3 qty/5 p/5 c/fruit",inventory);
        addParser2.run();
        filterCommand = new FilterCommand(inventory, "fruit", "f/category");
        filteredItems = filterCommand.getFilteredItems();
        assertEquals(2, filteredItems.size());
        RemoveCommand removeCommand = new RemoveCommand(inventory, 0);
        removeCommand.run();
        filterCommand = new FilterCommand(inventory, "uncategorized", "f/category");
        filteredItems = filterCommand.getFilteredItems();
        assertNull(filteredItems);
    }
}
