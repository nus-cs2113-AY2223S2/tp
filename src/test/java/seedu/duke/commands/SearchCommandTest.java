package seedu.duke.commands;
import org.junit.jupiter.api.Test;
import seedu.duke.objects.Item;
import seedu.duke.objects.Inventory;
import seedu.duke.utils.parsers.AddParser;
import seedu.duke.utils.parsers.EditParser;
import seedu.duke.types.Types;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;


import java.util.ArrayList;

public class SearchCommandTest {
    Inventory inventory = new Inventory();
    @Test
    public void searchByKeywordTest(){
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
        Command removeCommand = new RemoveCommand(inventory, "2", "Y");
        removeCommand.run();
        removeCommand = new RemoveCommand(inventory, "1", "Y");
        removeCommand.run();
        searchCommand = new SearchCommand(inventory, "orange", Types.SearchType.KEYWORD);
        searchResults = searchCommand.searchKeyword();
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
        removeCommand = new RemoveCommand(inventory, "1", "Y");
        removeCommand.run();
        addParser = new AddParser("n/orange upc/1 qty/5 p/5",inventory);
        addParser.run();
        addParser = new AddParser("n/orange upc/2 qty/5 p/5",inventory);
        addParser.run();
        searchCommand = new SearchCommand(inventory, "orange", Types.SearchType.KEYWORD);
        searchResults = searchCommand.searchKeyword();
        assertEquals(searchResults.size(),2);
        editParser = new EditParser("upc/2 n/laptops", inventory);
        editParser.run();
        searchCommand = new SearchCommand(inventory, "orange", Types.SearchType.KEYWORD);
        searchResults = searchCommand.searchKeyword();
        assertEquals(searchResults.size(),1);
        removeCommand = new RemoveCommand(inventory, "2", "Y");
        removeCommand.run();
        addParser = new AddParser("n/orange upc/2 qty/5 p/5",inventory);
        addParser.run();
        removeCommand = new RemoveCommand(inventory, "2", "Y");
        removeCommand.run();
        searchCommand = new SearchCommand(inventory, "orange", Types.SearchType.KEYWORD);
        searchResults = searchCommand.searchKeyword();
        assertEquals(searchResults.size(),1);
    }

    @Test
    public void searchByUPCTest(){
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
