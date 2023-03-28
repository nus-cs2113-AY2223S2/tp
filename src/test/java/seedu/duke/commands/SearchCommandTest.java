package seedu.duke.commands;
import org.junit.jupiter.api.Test;
import seedu.duke.objects.Item;
import seedu.duke.objects.Inventory;
import seedu.duke.utils.parsers.AddParser;
import seedu.duke.utils.parsers.EditParser;
import seedu.duke.types.Types;
import seedu.duke.utils.parsers.RestockParser;
import seedu.duke.utils.parsers.SellParser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;


import java.util.ArrayList;

public class SearchCommandTest {
    Inventory inventory;

    /**
     * Search integration tests with add and edit functions.
     */
    @Test
    public void searchByKeywordAddEditTest(){
        inventory = new Inventory();
        AddParser addParser = new AddParser("n/orange upc/1 qty/5 p/5",inventory);
        addParser.run();
        addParser = new AddParser("n/orange upc/2 qty/5 p/5",inventory);
        addParser.run();
        SearchCommand searchCommand = new SearchCommand(inventory, "orange", Types.SearchType.KEYWORD);
        ArrayList<Item> searchResults = searchCommand.searchKeyword();
        assertEquals(searchResults.size(),2);
        EditParser editParser = new EditParser("upc/2 n/laptops", inventory);
        editParser.run();
        searchCommand = new SearchCommand(inventory, "orange", Types.SearchType.KEYWORD);
        searchResults = searchCommand.searchKeyword();
        assertEquals(searchResults.size(),1);
    }

    /**
     * Integration tests with remove function.
     */
    @Test
    public void searchByKeywordRemoveTest(){
        inventory = new Inventory();
        AddParser addParser = new AddParser("n/orange upc/1 qty/5 p/5",inventory);
        addParser.run();
        addParser = new AddParser("n/laptops upc/2 qty/5 p/5",inventory);
        addParser.run();
        Command removeCommand = new RemoveCommand(inventory, "2", "Y");
        removeCommand.run();
        removeCommand = new RemoveCommand(inventory, "1", "Y");
        removeCommand.run();
        SearchCommand searchCommand = new SearchCommand(inventory, "orange", Types.SearchType.KEYWORD);
        ArrayList<Item> searchResults = searchCommand.searchKeyword();
        assertEquals(searchResults,null);
        addParser = new AddParser("n/orange upc/1 qty/5 p/5",inventory);
        addParser.run();
        addParser = new AddParser("n/orange upc/2 qty/5 p/5",inventory);
        addParser.run();
        removeCommand = new RemoveCommand(inventory, "2", "Y");
        removeCommand.run();
        searchCommand = new SearchCommand(inventory, "orange", Types.SearchType.KEYWORD);
        searchResults = searchCommand.searchKeyword();
        assertEquals(searchResults.size(),1);
    }

    /**
     * Edge case test that happens when add, edit and remove are called.
     */
    @Test
    public void searchByKeywordsEdgeCasesTest(){
        inventory = new Inventory();
        AddParser addParser = new AddParser("n/orange upc/1 qty/5 p/5",inventory);
        addParser.run();
        addParser = new AddParser("n/orange upc/2 qty/5 p/5",inventory);
        addParser.run();
        EditParser editParser = new EditParser("upc/2 n/laptops", inventory);
        editParser.run();
        RemoveCommand removeCommand = new RemoveCommand(inventory, "2", "Y");
        removeCommand.run();
        addParser = new AddParser("n/orange upc/2 qty/5 p/5",inventory);
        addParser.run();
        removeCommand = new RemoveCommand(inventory, "2", "Y");
        removeCommand.run();
        SearchCommand searchCommand = new SearchCommand(inventory, "orange", Types.SearchType.KEYWORD);
        ArrayList<Item> searchResults = searchCommand.searchKeyword();
        assertEquals(searchResults.size(),1);
        editParser = new EditParser("upc/1 n/laptops", inventory);
        editParser.run();
        searchCommand = new SearchCommand(inventory, "orange", Types.SearchType.KEYWORD);
        searchResults = searchCommand.searchKeyword();
        assertEquals(null, searchResults);
    }

    /**
     * Integration tests with sell and restock functions.
     */

    @Test
    public void searchByKeywordSellRestockTest(){
        inventory = new Inventory();
        AddParser addParser = new AddParser("n/orange upc/123 qty/5 p/5",inventory);
        addParser.run();
        SearchCommand searchCommand = new SearchCommand(inventory, "orange", Types.SearchType.KEYWORD);
        ArrayList<Item> searchResults = searchCommand.searchKeyword();
        assertEquals(searchResults.size(),1);
        SellParser sellParser = new SellParser("upc/123 qty/1", inventory);
        sellParser.run();
        searchCommand = new SearchCommand(inventory, "orange", Types.SearchType.KEYWORD);
        searchResults = searchCommand.searchKeyword();
        assertEquals(searchResults.size(),1);
        RestockParser restockParser = new RestockParser("upc/123 qty/5", inventory);
        restockParser.run();
        searchCommand = new SearchCommand(inventory, "orange", Types.SearchType.KEYWORD);
        searchResults = searchCommand.searchKeyword();
        assertEquals(searchResults.size(),1);
        addParser = new AddParser("n/orange upc/2 qty/5 p/5",inventory);
        addParser.run();
        sellParser = new SellParser("upc/2 qty/1", inventory);
        sellParser.run();
        searchCommand = new SearchCommand(inventory, "orange", Types.SearchType.KEYWORD);
        searchResults = searchCommand.searchKeyword();
        assertEquals(searchResults.size(),2);
        restockParser = new RestockParser("upc/2 qty/5", inventory);
        restockParser.run();
        searchCommand = new SearchCommand(inventory, "orange", Types.SearchType.KEYWORD);
        searchResults = searchCommand.searchKeyword();
        assertEquals(searchResults.size(),2);
    }

    /**
     * Test for searchUPC function.
     */

    @Test
    public void searchByUPCTest(){
        inventory = new Inventory();
        AddParser addParser = new AddParser("n/orange upc/1 qty/5 p/5",inventory);
        addParser.run();
        addParser = new AddParser("n/orange upc/2 qty/5 p/5",inventory);
        addParser.run();
        SearchCommand searchCommand = new SearchCommand(inventory, "2", Types.SearchType.UPC);
        Item searchResult = searchCommand.searchUPC();
        assertFalse(searchResult==null);
        searchCommand = new SearchCommand(inventory, "3", Types.SearchType.UPC);
        searchResult = searchCommand.searchUPC();
        assertTrue(searchResult==null);
        Command removeCommand = new RemoveCommand(inventory, "2", "Y");
        removeCommand.run();
        searchCommand = new SearchCommand(inventory, "2", Types.SearchType.UPC);
        searchResult = searchCommand.searchUPC();
        assertTrue(searchResult==null);
        EditParser editParser = new EditParser("upc/1 n/laptops", inventory);
        editParser.run();
        searchCommand = new SearchCommand(inventory, "1", Types.SearchType.UPC);
        searchResult = searchCommand.searchUPC();
        assertFalse(searchResult==null);
    }
}
