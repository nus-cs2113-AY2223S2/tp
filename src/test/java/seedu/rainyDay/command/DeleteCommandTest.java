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

public class DeleteCommandTest {
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
    public void execute() {
        //RainyDay.clearFinancialReport();
        AddCommand addFirstCommand = new AddCommand("Ipad", "out", 120, "Default");
        addFirstCommand.setData(financialReport);
        addFirstCommand.execute();
        AddCommand addSecondCommand = new AddCommand("angpao", "in", 3000, "Default");
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
}
