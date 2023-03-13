package seedu.rainyDay;

import static org.junit.jupiter.api.Assertions.assertEquals;

import seedu.rainyDay.command.Command;
import seedu.rainyDay.data.FinancialReport;
import seedu.rainyDay.data.FinancialStatement;

import org.junit.jupiter.api.Test;


import java.util.ArrayList;

class RainyDayTest {

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
