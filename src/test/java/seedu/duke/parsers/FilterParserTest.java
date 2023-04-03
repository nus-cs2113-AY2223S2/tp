package seedu.duke.parsers;

import org.junit.jupiter.api.Test;
import seedu.duke.objects.Inventory;
import seedu.duke.stubsmocks.FilterParserMock;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FilterParserTest {
    /**
     * Runs FilterParserMock for a given input and returns whether the input was parsed successfully.
     * Trims input to simulate trimming in ParserHandler
     *
     * @param input Simulated user input test string
     * @return Returns whether the parse was successful
     */
    private boolean getMockFilterResult(String input){
        Inventory inventory = new Inventory();
        input = input.trim();
        FilterParserMock filterParserMock = new FilterParserMock(input, inventory);
        filterParserMock.run();
        return filterParserMock.isValidParse();
    }
    @Test
    public void filterPricePositiveTests(){
        assertTrue(getMockFilterResult("f/price p/gt 10"));
        assertTrue(getMockFilterResult("f/price p/gt 10.23"));
        assertTrue(getMockFilterResult("f/price p/get 10"));
        assertTrue(getMockFilterResult("f/price p/get 10.23"));
        assertTrue(getMockFilterResult("f/price p/lt 10"));
        assertTrue(getMockFilterResult("f/price p/lt 10.23"));
        assertTrue(getMockFilterResult("f/price p/let 10"));
        assertTrue(getMockFilterResult("f/price p/let 10.23"));
    }

    @Test
    public void filterPriceNegativeTests(){
        assertFalse(getMockFilterResult("f/price p/gt 10e"));
        assertFalse(getMockFilterResult("f/price p/g2t 10"));
        assertFalse(getMockFilterResult("f/price p/gt         "));
        assertFalse(getMockFilterResult("f/price"));
        assertFalse(getMockFilterResult("f/price p/get 10e"));
        assertFalse(getMockFilterResult("f/price p/get         "));
        assertFalse(getMockFilterResult("f/price p/lt 10e"));
        assertFalse(getMockFilterResult("f/price p/l2t 10"));
        assertFalse(getMockFilterResult("f/price p/lt         "));
        assertFalse(getMockFilterResult("f/price p/let 10e"));
        assertFalse(getMockFilterResult("f/price p/let         "));
        assertFalse(getMockFilterResult("f/price p//lt 10"));
        assertFalse(getMockFilterResult("f/price weird 10"));
    }

    @Test
    public void filterCategoryPositiveTests(){
        assertTrue(getMockFilterResult("f/category p/gt 10"));
        assertTrue(getMockFilterResult("f/category testing"));
        assertTrue(getMockFilterResult("f/category aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"));
    }

    @Test
    public void filterCategoryNegativeTests(){
        assertFalse(getMockFilterResult("f/category"));
        assertFalse(getMockFilterResult("f/category "));
        assertFalse(getMockFilterResult("f/category     "));
        assertFalse(getMockFilterResult("f/category            "));
    }
    @Test
    public void miscellaneousFilterNegativeTests(){
        assertFalse(getMockFilterResult("f/categori ee"));
        assertFalse(getMockFilterResult("f/cat ee"));
        assertFalse(getMockFilterResult("random words"));
        assertFalse(getMockFilterResult("price p/gt 10"));
    }
}
