package seedu.rainyDay;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import seedu.rainyDay.command.DeleteCommand;
import seedu.rainyDay.command.ViewCommand;
import seedu.rainyDay.data.FinancialReport;
import seedu.rainyDay.data.FinancialStatement;
import seedu.rainyDay.command.AddCommand;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommandTest {
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
    public void addFinancialStatement() {
        setUpStreams();
        AddCommand addCommand = new AddCommand("Ipad", "out", 120);
        addCommand.setData(financialReport);
        addCommand.execute();
        String expectedAddStatement = "Done! Added: out for Ipad, -$120" + System.lineSeparator();
        assertEquals(expectedAddStatement, outContent.toString());
        restoreStreams();
    }

    @Test
    public void addMultipleFinancialStatement() {
        setUpStreams();
        AddCommand addCommand = new AddCommand("angpao", "in", 3000);
        addCommand.setData(financialReport);
        addCommand.execute();
        String expectedAddStatement = "Done! Added: in for angpao, +$3000" + System.lineSeparator();
        assertEquals(expectedAddStatement, outContent.toString());
        restoreStreams();
        //Command.addFinancialStatement("Ipad", "out", 120);
        //TODO: assertequal() to check if size is 2, and if the 2 tasks are correctly stored
    }

    @Test
    public void deleteFinancialStatement() {
        //RainyDay.clearFinancialReport();
        AddCommand addFirstCommand = new AddCommand("Ipad", "out", 120);
        addFirstCommand.setData(financialReport);
        addFirstCommand.execute();
        AddCommand addSecondCommand = new AddCommand("angpao", "in", 3000);
        addSecondCommand.setData(financialReport);
        addSecondCommand.execute();

        setUpStreams();
        DeleteCommand deleteCommand = new DeleteCommand(1);
        deleteCommand.setData(financialReport);
        deleteCommand.execute();
        String expectedDeleteStatement = "Done, deleted \"Ipad\" from the financial report"
                + System.lineSeparator();
        assertEquals(expectedDeleteStatement, outContent.toString());


        //TODO figure out how to flush the streams after first assert.
        //Command.deleteFinancialStatement(1);
        //expectedDeleteStatement = "Done, deleted \"angpao\" from the financial report"
        // + System.lineSeparator();;
        //assertEquals(expectedDeleteStatement, actualDeleteStatement);
        restoreStreams();
    }

    @Test
    public void generateEmptyReport() {
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
    public void generateNonEmptyReport() {
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
