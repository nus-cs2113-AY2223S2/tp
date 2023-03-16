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

    private static final String VIEW_TEST_RESULT =
            "+-----+------------------------------+------------+----------------+\n" +
                    "|Here is your full financial report!                               |\n" +
                    "+-----+------------------------------+------------+----------------+\n" +
                    "|Index|Name                          |Amount      |Category        |\n" +
                    "|00001|Ipad                          | -$120      |Default         |\n" +
                    "|00002|pork                          | -$5        |Default         |\n" +
                    "|00003|angpao                        | +$3000     |Default         |\n" +
                    "+-----+------------------------------+------------+----------------+\n" +
                    "|Inflow: $3000\n" +
                    "|Outflow: $125\n" +
                    "|Remaining value: $2875\n";

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
        assertEquals(VIEW_TEST_RESULT, outContent.toString());
        restoreStreams();
    }
}
