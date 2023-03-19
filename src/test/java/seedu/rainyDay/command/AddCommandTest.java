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

public class AddCommandTest {
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
    public void execute_singleStatement_statementInformation() {
        setUpStreams();
        AddCommand addCommand = new AddCommand("Ipad", "out", 120.50, "Default");
        addCommand.setData(financialReport);
        addCommand.execute();
        String expectedAddStatement = "Done! Added: OUTFLOW for Ipad, -$120.50" + System.lineSeparator();
        assertEquals(expectedAddStatement, outContent.toString());
        restoreStreams();
    }

    @Test
    public void execute_multipleStatements_statementsInformation() {
        setUpStreams();
        AddCommand addCommand = new AddCommand("angpao", "in", 3000.00, "Default");
        addCommand.setData(financialReport);
        addCommand.execute();
        String expectedAddStatement = "Done! Added: INFLOW for angpao, +$3000.00" + System.lineSeparator();
        assertEquals(expectedAddStatement, outContent.toString());
        restoreStreams();
        addCommand = new AddCommand("textbook", "out", 50.00, "Default");
        addCommand.setData(financialReport);
        addCommand.execute();
        assertEquals(2, financialReport.getStatementCount());
    }
}
