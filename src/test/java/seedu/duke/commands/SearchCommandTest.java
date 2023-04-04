package seedu.duke.commands;
import org.junit.jupiter.api.Test;
import seedu.duke.objects.Item;
import seedu.duke.objects.Inventory;
import seedu.duke.utils.parsers.AddParser;
import seedu.duke.utils.parsers.EditParser;
import seedu.duke.types.Types;
import seedu.duke.utils.parsers.RestockParser;
import seedu.duke.utils.parsers.SellParser;


import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

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
        assertEquals(2, searchResults.size());
        EditParser editParser = new EditParser("upc/2 n/laptops", inventory);
        editParser.run();
        searchCommand = new SearchCommand(inventory, "orange", Types.SearchType.KEYWORD);
        searchResults = searchCommand.searchKeyword();
        assertEquals(1, searchResults.size());
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
        Command removeCommand = new RemoveCommand(inventory, "2");
        removeCommand.run();
        removeCommand = new RemoveCommand(inventory, "1");
        removeCommand.run();
        SearchCommand searchCommand = new SearchCommand(inventory, "orange", Types.SearchType.KEYWORD);
        ArrayList<Item> searchResults = searchCommand.searchKeyword();
        assertEquals(null,searchResults);
        addParser = new AddParser("n/orange upc/1 qty/5 p/5",inventory);
        addParser.run();
        addParser = new AddParser("n/orange upc/2 qty/5 p/5",inventory);
        addParser.run();
        removeCommand = new RemoveCommand(inventory, "2");
        removeCommand.run();
        searchCommand = new SearchCommand(inventory, "orange", Types.SearchType.KEYWORD);
        searchResults = searchCommand.searchKeyword();
        assertEquals(1,searchResults.size());
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
        RemoveCommand removeCommand = new RemoveCommand(inventory, "2");
        removeCommand.run();
        addParser = new AddParser("n/orange upc/2 qty/5 p/5",inventory);
        addParser.run();
        removeCommand = new RemoveCommand(inventory, "2");
        removeCommand.run();
        SearchCommand searchCommand = new SearchCommand(inventory, "orange", Types.SearchType.KEYWORD);
        ArrayList<Item> searchResults = searchCommand.searchKeyword();
        assertEquals(1,searchResults.size());
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
        assertEquals(1,searchResults.size());
        SellParser sellParser = new SellParser("upc/123 qty/1", inventory);
        sellParser.run();
        searchCommand = new SearchCommand(inventory, "orange", Types.SearchType.KEYWORD);
        searchResults = searchCommand.searchKeyword();
        assertEquals(1,searchResults.size());
        RestockParser restockParser = new RestockParser("upc/123 qty/5", inventory);
        restockParser.run();
        searchCommand = new SearchCommand(inventory, "orange", Types.SearchType.KEYWORD);
        searchResults = searchCommand.searchKeyword();
        assertEquals(1,searchResults.size());
        addParser = new AddParser("n/orange upc/2 qty/5 p/5",inventory);
        addParser.run();
        sellParser = new SellParser("upc/2 qty/1", inventory);
        sellParser.run();
        searchCommand = new SearchCommand(inventory, "orange", Types.SearchType.KEYWORD);
        searchResults = searchCommand.searchKeyword();
        assertEquals(2,searchResults.size());
        restockParser = new RestockParser("upc/2 qty/5", inventory);
        restockParser.run();
        searchCommand = new SearchCommand(inventory, "orange", Types.SearchType.KEYWORD);
        searchResults = searchCommand.searchKeyword();
        assertEquals(2,searchResults.size());
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
        assertNotNull(searchResult);
        searchCommand = new SearchCommand(inventory, "3", Types.SearchType.UPC);
        searchResult = searchCommand.searchUPC();
        assertNull(searchResult);
        Command removeCommand = new RemoveCommand(inventory, "2");
        removeCommand.run();
        searchCommand = new SearchCommand(inventory, "2", Types.SearchType.UPC);
        searchResult = searchCommand.searchUPC();
        assertNull(searchResult);
        EditParser editParser = new EditParser("upc/1 n/laptops", inventory);
        editParser.run();
        searchCommand = new SearchCommand(inventory, "1", Types.SearchType.UPC);
        searchResult = searchCommand.searchUPC();
        assertNotNull(searchResult);
    }

    /**
     *
     */
    @Test
    public void searchDuplicationTest(){
        inventory = new Inventory();
        AddParser addParser = new AddParser("n/iphone 20 pro max max max upc/0123241 qty/10 p/2000",inventory);
        addParser.run();
        SearchCommand searchCommand = new SearchCommand(inventory, "max", Types.SearchType.KEYWORD);
        ArrayList<Item> searchResults = searchCommand.searchKeyword();
        assertEquals(1,searchResults.size());
    }

    /**
     *
     */
    @Test
    public void searchMultipleKeywordTest(){
        inventory = new Inventory();
        AddParser addParser = new AddParser("n/laptop sleeves upc/111 qty/10 p/15",inventory);
        addParser.run();
        addParser = new AddParser("n/laptop upc/222 qty/10 p/1500",inventory);
        addParser.run();
        addParser = new AddParser("n/shirt sleeves upc/333 qty/10 p/5",inventory);
        addParser.run();
        SearchCommand searchCommand = new SearchCommand(inventory, "shirt", Types.SearchType.KEYWORD);
        ArrayList<Item> searchResults = searchCommand.searchKeyword();
        assertEquals(1,searchResults.size());
        searchCommand = new SearchCommand(inventory, "sleeves", Types.SearchType.KEYWORD);
        searchResults = searchCommand.searchKeyword();
        assertEquals(2,searchResults.size());
        searchCommand = new SearchCommand(inventory, "laptop sleeves", Types.SearchType.KEYWORD);
        searchResults = searchCommand.searchKeyword();
        assertEquals(1,searchResults.size());
        searchCommand = new SearchCommand(inventory, "shirt sleeves", Types.SearchType.KEYWORD);
        searchResults = searchCommand.searchKeyword();
        assertEquals(1,searchResults.size());
    }
}
