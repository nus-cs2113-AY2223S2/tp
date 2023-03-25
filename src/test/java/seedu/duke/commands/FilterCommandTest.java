package seedu.duke.commands;

import org.junit.jupiter.api.Test;
import seedu.duke.objects.Inventory;
import seedu.duke.objects.Item;
import seedu.duke.utils.parsers.AddParser;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class FilterCommandTest {
    Inventory inventory;
    @Test
    public void filterPriceTest(){
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
}
