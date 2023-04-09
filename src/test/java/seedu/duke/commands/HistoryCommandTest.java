package seedu.duke.commands;

import org.junit.jupiter.api.Test;
import seedu.duke.objects.Inventory;
import seedu.duke.objects.Item;
import seedu.duke.utils.parsers.AddParser;
import seedu.duke.utils.parsers.EditParser;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HistoryCommandTest {
    Inventory inventory;

    /**
     * Integration tests with add and edit functions.
     */
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
        assertEquals("orange", results.get(0).getName());
        assertEquals("Laptops", results.get(1).getName());
        editParser = new EditParser("upc/1 n/TV qty/1 p/10999.0", inventory);
        editParser.run();
        historyCommand = new HistoryCommand(inventory, "1");
        results = historyCommand.getHistoryResults();
        assertEquals(3, results.size());
        assertEquals("orange", results.get(0).getName());
        assertEquals("Laptops", results.get(1).getName());
        assertEquals("TV", results.get(2).getName());
    }

    /**
     * Integration tests with remove function.
     */
    @Test
    public void historyRemoveTest(){
        inventory = new Inventory();
        AddParser addParser = new AddParser("n/orange upc/1 qty/5 p/5", inventory);
        addParser.run();
        HistoryCommand historyCommand = new HistoryCommand(inventory, "1");
        ArrayList<Item> results = historyCommand.getHistoryResults();
        assertEquals(1, results.size());
        RemoveCommand removeCommand = new RemoveCommand(inventory, "1");
        removeCommand.run();
        historyCommand = new HistoryCommand(inventory, "1");
        results = historyCommand.getHistoryResults();
        assertEquals(null, results);
    }

    /**
     * Integration tests with edit category
     */
    @Test
    public void historyCategoryTest(){
        inventory = new Inventory();
        AddParser addParser = new AddParser("n/orange upc/1 qty/5 p/5", inventory);
        addParser.run();
        HistoryCommand historyCommand = new HistoryCommand(inventory, "1");
        ArrayList<Item> results = historyCommand.getHistoryResults();
        assertEquals("Uncategorized", results.get(0).getCategory());
        EditParser editParser = new EditParser("upc/1 c/fruits",inventory);
        editParser.run();
        historyCommand = new HistoryCommand(inventory, "1");
        results = historyCommand.getHistoryResults();
        assertEquals("Uncategorized", results.get(0).getCategory());
        assertEquals("fruits", results.get(1).getCategory());
        addParser = new AddParser("n/orange upc/2 qty/5 p/5 c/fruits", inventory);
        addParser.run();
        historyCommand = new HistoryCommand(inventory, "2");
        results = historyCommand.getHistoryResults();
        assertEquals("fruits", results.get(0).getCategory());
    }
}
