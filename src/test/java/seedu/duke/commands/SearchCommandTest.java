package seedu.duke.commands;
import org.junit.jupiter.api.Test;
import seedu.duke.objects.Item;
import seedu.duke.objects.Inventory;
import seedu.duke.utils.parser.Parser;
import seedu.duke.types.Types;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;


import java.util.ArrayList;

public class SearchCommandTest {
    Inventory inventory = new Inventory();
    Parser testParser = new Parser(inventory);
    @Test
    public void searchByKeywordTest(){
        testParser.parseAdd("n/orange upc/1 qty/5 p/5",inventory);
        testParser.parseAdd("n/orange upc/2 qty/5 p/5",inventory);
        SearchCommand searchCommand = new SearchCommand(inventory, "orange", Types.SearchType.KEYWORD);
        ArrayList<Item> searchResults = searchCommand.searchKeyword();
        assertEquals(searchResults.size(),2);
        testParser.parseEdit("upc/2 n/laptops", inventory);
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
        testParser.parseAdd("n/orange upc/1 qty/5 p/5",inventory);
        testParser.parseAdd("n/orange upc/2 qty/5 p/5",inventory);
        removeCommand = new RemoveCommand(inventory, "2", "Y");
        removeCommand.run();
        searchCommand = new SearchCommand(inventory, "orange", Types.SearchType.KEYWORD);
        searchResults = searchCommand.searchKeyword();
        assertEquals(searchResults.size(),1);
        removeCommand = new RemoveCommand(inventory, "1", "Y");
        removeCommand.run();
        testParser.parseAdd("n/small orange upc/1 qty/5 p/5",inventory);
        testParser.parseAdd("n/large orange upc/2 qty/5 p/5",inventory);
        searchCommand = new SearchCommand(inventory, "orange", Types.SearchType.KEYWORD);
        searchResults = searchCommand.searchKeyword();
        assertEquals(searchResults.size(),2);
        testParser.parseEdit("upc/2 n/laptop", inventory);
        searchCommand = new SearchCommand(inventory, "orange", Types.SearchType.KEYWORD);
        searchResults = searchCommand.searchKeyword();
        assertEquals(searchResults.size(),1);
        removeCommand = new RemoveCommand(inventory, "2", "Y");
        removeCommand.run();
        testParser.parseAdd("n/large orange upc/2 qty/5 p/5",inventory);
        removeCommand = new RemoveCommand(inventory, "2", "Y");
        removeCommand.run();
        searchCommand = new SearchCommand(inventory, "orange", Types.SearchType.KEYWORD);
        searchResults = searchCommand.searchKeyword();
        assertEquals(searchResults.size(),1);
    }

    @Test
    public void searchByUPCTest(){
        testParser.parseAdd("n/orange upc/1 qty/5 p/5",inventory);
        testParser.parseAdd("n/orange upc/2 qty/5 p/5",inventory);
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
        testParser.parseEdit("upc/1 n/laptops", inventory);
        searchCommand = new SearchCommand(inventory, "1", Types.SearchType.UPC);
        searchResult = searchCommand.searchUPC();
        assertFalse(searchResult==null);
    }
}
