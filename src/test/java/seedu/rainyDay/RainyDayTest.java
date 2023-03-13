package seedu.rainyDay;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.After;
import org.junit.Before;
import seedu.rainyDay.command.Command;
import seedu.rainyDay.data.FinancialReport;
import seedu.rainyDay.data.FinancialStatement;

import org.junit.jupiter.api.Test;


import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

class RainyDayTest {

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
    public void parseAddInCommand() {
        try {
            ArrayList<FinancialStatement> statements = new ArrayList<>();
            FinancialReport testReport = new FinancialReport(statements);
            testReport.addStatement(new FinancialStatement("noodles", "in", 5));

            RainyDay.clearFinancialReport();
            RainyDay.parseUserInput("add -in noodles $5");

            assertEquals(RainyDay.financialReport.getFullStatement(0),
                    testReport.getFullStatement(0));
        } catch (Exception e) {
            System.out.println("Wrong input format! Please refer to help for correct user input!");
        }
    }

    @Test
    public void parseAddOutCommand() {
        try {
            ArrayList<FinancialStatement> statements = new ArrayList<>();
            FinancialReport testReport = new FinancialReport(statements);
            testReport.addStatement(new FinancialStatement("noodles", "out", 5));
            RainyDay.clearFinancialReport();
            RainyDay.parseUserInput("add -out noodles $5");
            assertEquals(RainyDay.financialReport.getFullStatement(0),
                    testReport.getFullStatement(0));
        } catch (Exception e) {
            System.out.println("Wrong input format! Please refer to help for correct user input!");
        }
    }

    @Test
    public void parseDeleteCommand() {
        RainyDay.clearFinancialReport();
        Command.addFinancialStatement("Ipad", "out", 120);
        Command.addFinancialStatement("angpao", "in", 3000);
        ArrayList<FinancialStatement> statements = new ArrayList<>();
        FinancialReport testReport = new FinancialReport(statements);
        testReport.addStatement(new FinancialStatement("Ipad", "out", 120));
        try {
            RainyDay.parseUserInput("delete 2");
            assertEquals(RainyDay.financialReport.getFullStatement(0),
                    testReport.getFullStatement(0));
        } catch (Exception e) {
            System.out.println("Wrong input format! Please refer to help for correct user input!");
        }
    }



}
