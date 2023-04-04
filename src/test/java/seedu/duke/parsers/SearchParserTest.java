package seedu.duke.parsers;

import org.junit.jupiter.api.Test;
import seedu.duke.objects.Inventory;
import seedu.duke.stubsmocks.SearchParserMock;
import seedu.duke.types.Types;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SearchParserTest {
    /**
     * Runs SearchParserMock for a given input and returns whether the input was parsed successfully
     * Trims input to simulate trimming in ParserHandler
     *
     * @param input Simulated user input test string
     * @param searchType Type of search to be conducted
     * @return Returns whether the parse was successful
     */
    private boolean getMockSearchResult(String input, Types.SearchType searchType){
        Inventory inventory = new Inventory();
        input = input.trim();
        SearchParserMock searchParserMock = new SearchParserMock(input, inventory, searchType);
        searchParserMock.run();
        return searchParserMock.isValidParse();
    }
    @Test
    public void searchKeywordPositiveTests(){
        assertTrue(getMockSearchResult("test", Types.SearchType.KEYWORD));
        assertTrue(getMockSearchResult("test 2", Types.SearchType.KEYWORD));
        assertTrue(getMockSearchResult("test multiple words in a line", Types.SearchType.KEYWORD));
    }
    @Test
    public void searchKeywordNegativeTests(){
        assertFalse(getMockSearchResult("", Types.SearchType.KEYWORD));
        assertFalse(getMockSearchResult(" ", Types.SearchType.KEYWORD));
        assertFalse(getMockSearchResult("         ", Types.SearchType.KEYWORD));
    }
    @Test
    public void searchUPCPositiveTests(){
        assertTrue(getMockSearchResult("123", Types.SearchType.UPC));
        assertTrue(getMockSearchResult("241704928709481720487083728730", Types.SearchType.UPC));
    }
    @Test
    public void searchUPCNegativeTests(){
        assertFalse(getMockSearchResult("", Types.SearchType.UPC));
        assertFalse(getMockSearchResult(" ", Types.SearchType.UPC));
        assertFalse(getMockSearchResult("         ", Types.SearchType.UPC));
        assertFalse(getMockSearchResult("123 a", Types.SearchType.UPC));
        assertFalse(getMockSearchResult("123 2231131", Types.SearchType.UPC));
        assertFalse(getMockSearchResult("124 24142 412 4124 124 ", Types.SearchType.UPC));
    }
}
