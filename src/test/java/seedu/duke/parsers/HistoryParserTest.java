package seedu.duke.parsers;

import org.junit.jupiter.api.Test;
import seedu.duke.objects.Inventory;
import seedu.duke.stubsmocks.HistoryParserMock;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HistoryParserTest {
    /**
     * Runs HistoryParserMock for a given input and returns whether the input was parsed successfully.
     * Trims input to simulate trimming in ParserHandler
     *
     * @param input Simulated user input test string
     * @return Returns whether the parse was successful
     */
    private boolean getMockHistoryResult(String input){
        Inventory inventory = new Inventory();
        input = input.trim();
        HistoryParserMock historyParserMock = new HistoryParserMock(input, inventory);
        historyParserMock.run();
        return historyParserMock.isValidParse();
    }
    @Test
    public void historyParserPositiveTests(){
        assertTrue(getMockHistoryResult("123131"));
        assertTrue(getMockHistoryResult("123-131"));
        assertTrue(getMockHistoryResult("upcButWordsInAWeirdSystem"));
    }
    @Test
    public void historyParserNegativeTests(){
        assertFalse(getMockHistoryResult(""));
        assertFalse(getMockHistoryResult(" "));
        assertFalse(getMockHistoryResult("     "));
        assertFalse(getMockHistoryResult("           "));
        assertFalse(getMockHistoryResult("123 131"));
        assertFalse(getMockHistoryResult("123 131 23 132 13 12 31"));
    }
}
