package seedu.rainyDay.command;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import seedu.rainyDay.data.FinancialReport;
import seedu.rainyDay.data.FinancialStatement;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ViewCommandTest {
    ArrayList<FinancialStatement> statements = new ArrayList<>();
    FinancialReport financialReport = new FinancialReport(statements);

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    public void execute_emptyReport_emptyReportStatement() {
        setUpStreams();
        //RainyDay.clearFinancialReport();
        ViewCommand viewList = new ViewCommand();
        viewList.setData(financialReport);
        viewList.execute();
        String expectedReport = "Your financial report is empty" + System.lineSeparator();
        assertEquals(expectedReport, outContent.toString());
        restoreStreams();
    }

    @Test
    public void execute_nonEmptyReport_reportWithStatements() {
        setUpStreams();
        //RainyDay.clearFinancialReport();
        financialReport.addStatement(new FinancialStatement("Ipad", "out", 120));
        financialReport.addStatement(new FinancialStatement("pork", "out", 5));
        financialReport.addStatement(new FinancialStatement("angpao", "in", 3000));
        ViewCommand viewList = new ViewCommand();
        viewList.setData(financialReport);
        viewList.execute();
        String expectedReport = String.join(System.lineSeparator(), "Here is your full financial report!",
                "1. Ipad -$120 (out)", "2. pork -$5 (out)", "3. angpao +$3000 (in)" + System.lineSeparator(),
                "Inflow: $3000", "Outflow: $125", "Remaining value: $2875" + System.lineSeparator());
        assertEquals(expectedReport, outContent.toString());
        restoreStreams();
    }
}
