package seedu.rainyDay;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.rainyDay.rainyDay.addFinancialStatement;
import static seedu.rainyDay.rainyDay.deleteFinancialStatement;

import org.junit.jupiter.api.Test;

class rainyDayTest {
    @Test
    public void sampleTest() {
        assertTrue(true);
    }

    @Test
    public void verifyAddStatement() {
        String actualAddStatement = addFinancialStatement("Ipad", "out", 120);
        String expectedAddStatement = "Done, added: out for Ipad, $120";
        assertEquals(expectedAddStatement, actualAddStatement);
    }

    @Test
    public void verifyDeleteStatement() {
        addFinancialStatement("Ipad", "out", 120);
        String actualDeleteStatement = deleteFinancialStatement(1);
        String expectedDeleteStatement = "Done, deleted \"Ipad\" from the financial report";
        assertEquals(expectedDeleteStatement, actualDeleteStatement);
    }

}
