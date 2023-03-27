package seedu.duke.commands;

import org.junit.jupiter.api.Test;
import seedu.duke.objects.Inventory;
import seedu.duke.objects.Item;
import seedu.duke.utils.parsers.AddParser;
import seedu.duke.utils.parsers.EditParser;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HistoryCommandTest {
    Inventory inventory;
    @Test
    public void historyAddEditTest() {
        inventory = new Inventory();
        AddParser addParser = new AddParser("n/orange upc/1 qty/5 p/5", inventory);
        addParser.run();
        HistoryCommand historyCommand = new HistoryCommand(inventory, "1");
        ArrayList<Item> results = historyCommand.getHistoryResults();
        assertEquals(1, results.size());
        EditParser editParser = new EditParser("upc/1 n/Laptops qty/5 p/10.0", inventory);
        editParser.run();
        historyCommand = new HistoryCommand(inventory, "1");
        results = historyCommand.getHistoryResults();
        assertEquals(2, results.size());
        assertTrue(results.get(0).getName().equals("orange"));
        assertTrue(results.get(1).getName().equals("Laptops"));
        editParser = new EditParser("upc/1 n/TV qty/1 p/10999.0", inventory);
        editParser.run();
        historyCommand = new HistoryCommand(inventory, "1");
        results = historyCommand.getHistoryResults();
        assertEquals(3, results.size());
        assertTrue(results.get(0).getName().equals("orange"));
        assertTrue(results.get(1).getName().equals("Laptops"));
        assertTrue(results.get(2).getName().equals("TV"));
    }
    @Test
    public void historyRemoveTest(){
        inventory = new Inventory();
        AddParser addParser = new AddParser("n/orange upc/1 qty/5 p/5", inventory);
        addParser.run();
        HistoryCommand historyCommand = new HistoryCommand(inventory, "1");
        ArrayList<Item> results = historyCommand.getHistoryResults();
        assertEquals(1, results.size());
        RemoveCommand removeCommand = new RemoveCommand(inventory, "1", "Y");
        removeCommand.run();
        historyCommand = new HistoryCommand(inventory, "1");
        results = historyCommand.getHistoryResults();
        assertEquals(null, results);
    }
}
