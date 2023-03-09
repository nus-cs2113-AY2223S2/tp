package seedu.rainyDay;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.rainyDay.rainyDay.addFinancialStatement;

import org.junit.jupiter.api.Test;
import seedu.rainyDay.data.FinancialStatement;

import java.util.ArrayList;

class rainyDayTest {
    @Test
    public void sampleTest() {
        assertTrue(true);
    }

    @Test
    public void verifyAddStatement() {
        ArrayList<FinancialStatement> financialReport = new ArrayList<FinancialStatement>();
        String actualAddStatement = addFinancialStatement("Ipad", "out", 120);
        String expectedAddStatement = "Done, added: out for Ipad, $120";
        assertEquals(expectedAddStatement, actualAddStatement);
    }


}
