package seedu.rainyDay.command;

import org.junit.jupiter.api.Test;
import seedu.rainyDay.RainyDay;
import seedu.rainyDay.data.FinancialReport;
import seedu.rainyDay.data.FinancialStatement;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommandTest {
    @Test
    public void addFinancialStatement() {
        String actualAddStatement = Command.addFinancialStatement("Ipad", "out", 120);
        String expectedAddStatement = "Done, added: out for Ipad, $120";
        assertEquals(expectedAddStatement, actualAddStatement);

        actualAddStatement = Command.addFinancialStatement("angpao", "in", 3000);
        expectedAddStatement = "Done, added: in for angpao, $3000";
        assertEquals(expectedAddStatement, actualAddStatement);
    }

    @Test
    public void deleteFinancialStatement() {
        RainyDay.clearFinancialReport();
        Command.addFinancialStatement("Ipad", "out", 120);
        Command.addFinancialStatement("angpao", "in", 3000);
        String actualDeleteStatement = Command.deleteFinancialStatement(1);
        String expectedDeleteStatement = "Done, deleted \"Ipad\" from the financial report";
        assertEquals(expectedDeleteStatement, actualDeleteStatement);

        actualDeleteStatement = Command.deleteFinancialStatement(1);
        expectedDeleteStatement = "Done, deleted \"angpao\" from the financial report";
        assertEquals(expectedDeleteStatement, actualDeleteStatement);
    }

    @Test
    public void generateReport() {
        ArrayList<FinancialStatement> statements = new ArrayList<>();
        FinancialReport financialReport = new FinancialReport(statements);
        String actualReport = Command.generateReport(financialReport);
        String expectedReport = "Your financial report is empty";
        assertEquals(expectedReport, actualReport);

        financialReport.addStatement(new FinancialStatement("Ipad", "out", 120));
        financialReport.addStatement(new FinancialStatement("pork", "out", 5));
        financialReport.addStatement(new FinancialStatement("angpao", "in", 3000));
        actualReport = Command.generateReport(financialReport);
        expectedReport = String.join(System.lineSeparator(), "1. Ipad -$120 (out)", "2. pork -$5 (out)",
                "3. angpao +$3000 (in)" + System.lineSeparator(), "Inflow: $3000", "Outflow: $125",
                "Remaining value: $2875");
        assertEquals(expectedReport, actualReport);
    }
}
