package seedu.duke;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.Test;
import seedu.duke.commands.AddCommand;
import seedu.duke.commands.EditCommand;
import seedu.duke.commands.FilterCommand;
import seedu.duke.commands.RemoveCommand;
import seedu.duke.commands.RestockCommand;
import seedu.duke.commands.SearchCommand;
import seedu.duke.commands.SellCommand;
import seedu.duke.objects.Inventory;
import seedu.duke.objects.Item;
import seedu.duke.types.Types;
import seedu.duke.utils.SessionManager;
import seedu.duke.utils.Storage;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EfficiencyBenchmark {
    private static final long timeToBeat = 1000;
    private static final int DATASET_SIZE = 4000;
    private static long totalTime = 0;
    Inventory inventory = Storage.readCSV("./src/test/data/BenchmarkData.txt");
    @Test
    @Order(1)
    public void loadInventoryTest(){
        Inventory loadInventory;
        long start = System.currentTimeMillis();
        loadInventory = Storage.readCSV("./src/test/data/BenchmarkData.txt");
        long end = System.currentTimeMillis();
        long timeTaken = end - start;
        totalTime += timeTaken;
        System.out.println("Time taken to load: " + timeTaken + "ms");
        assertTrue(timeTaken<=timeToBeat);
        assertEquals(DATASET_SIZE,loadInventory.getItemInventory().size());
    }
    @Test
    @Order(2)
    public void searchInventoryTest(){
        long start = System.currentTimeMillis();
        SearchCommand searchCommand = new SearchCommand(inventory, "samsung", Types.SearchType.KEYWORD);
        ArrayList<Item> results = searchCommand.searchKeyword();
        long end = System.currentTimeMillis();
        long timeTaken = end - start;
        totalTime += timeTaken;
        System.out.println("Time taken to find " + results.size() + " items: " + timeTaken + "ms");
        assertTrue(timeTaken<=timeToBeat);
        assertTrue(results.size()>0);
        start = System.currentTimeMillis();
        searchCommand = new SearchCommand(inventory, "123", Types.SearchType.UPC);
        Item result = searchCommand.searchUPC();
        end = System.currentTimeMillis();
        timeTaken = end - start;
        System.out.println("Time taken to find item: " + timeTaken + "ms");
        assertTrue(result != null);
        assertTrue(timeTaken <= timeToBeat);
    }

    @Test
    @Order(3)
    public void filterInventoryTest(){
        long start = System.currentTimeMillis();
        FilterCommand filterCommand = new FilterCommand(inventory, "uncategorized", "f/category");
        ArrayList<Item> results = filterCommand.getFilteredItems();
        long end = System.currentTimeMillis();
        long timeTaken = end - start;
        totalTime += timeTaken;
        System.out.println("Time taken to filter " + results.size() + " items: " + timeTaken + "ms");
        assertTrue(timeTaken<=timeToBeat);
        assertTrue(results.size()>0);
        start = System.currentTimeMillis();
        filterCommand = new FilterCommand(inventory, 10.25, "p/gt");
        results = filterCommand.getFilteredItems();
        end = System.currentTimeMillis();
        timeTaken = end - start;
        System.out.println("Time taken to filter " + results.size() + " items: " + timeTaken + "ms");
        assertTrue(timeTaken<=timeToBeat);
        assertTrue(results.size()>0);
    }

    @Test
    @Order(4)
    public void crudTest(){
        SessionManager.setAutoSave(false);
        long start = System.currentTimeMillis();
        Item item = new Item("test","10000",24,10.1);
        AddCommand addCommand = new AddCommand(inventory,item);
        addCommand.run();
        RemoveCommand removeCommand = new RemoveCommand(inventory,"10000");
        removeCommand.run();
        EditCommand editCommand = new EditCommand(inventory, "upc/9999 n/newtest".split(" "));
        editCommand.run();
        SellCommand sellCommand = new SellCommand(inventory, "upc/9999 qty/100".split(" "));
        sellCommand.run();
        RestockCommand restockCommand = new RestockCommand(inventory, "upc/9999 qty/100".split(" "));
        restockCommand.run();
        long end = System.currentTimeMillis();
        long timeTaken = end - start;
        totalTime += timeTaken;
        System.out.println("Time taken for all CRUD commands: " + timeTaken + "ms");
        System.out.println("Time taken for tests: " + totalTime + "ms");
        assertTrue(timeTaken<=timeToBeat);
    }
}
