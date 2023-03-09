package seedu.rainyDay;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.rainyDay.rainyDay.addFinancialStatement;
import static seedu.rainyDay.rainyDay.deleteFinancialStatement;
import static seedu.rainyDay.rainyDay.generateReport;

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

    @Test
    public void verifyReport() {
        String actualReport = generateReport();
        String expectedReport = "Your financial report is empty";
        assertEquals(expectedReport, actualReport);

        addFinancialStatement("Ipad", "out", 120);
        addFinancialStatement("pork", "out", 5);
        addFinancialStatement("angpao", "in", 3000);
        actualReport = generateReport();
        expectedReport = String.join(System.lineSeparator(), "1. Ipad -$120 (out)", "2. pork -$5 (out)",
                "3. angpao +$3000 (in)" + System.lineSeparator(), "Inflow: $3000", "Outflow: $125",
                "Remaining value: $2875");
        assertEquals(expectedReport, actualReport);
    }
}
