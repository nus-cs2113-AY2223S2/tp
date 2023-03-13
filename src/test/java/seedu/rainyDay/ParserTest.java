package seedu.rainyDay;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.rainyDay.RainyDay.financialReport;

import seedu.rainyDay.command.Command;
import seedu.rainyDay.data.FinancialReport;
import seedu.rainyDay.data.FinancialStatement;

import org.junit.jupiter.api.Test;
import seedu.rainyDay.data.Parser;


import java.util.ArrayList;

class ParserTest {
    ArrayList<FinancialStatement> statements = new ArrayList<>();
    FinancialReport financialReport = new FinancialReport(statements);
    @Test
    public void parseAddInCommand() {
        try {
            FinancialReport testReport = new FinancialReport(statements);
            testReport.addStatement(new FinancialStatement("noodles", "in", 5));
            Parser.parseUserInput("add -in noodles $5");
            assertEquals(financialReport.getFullStatement(0),
                    testReport.getFullStatement(0));
        } catch (Exception e) {
            System.out.println("Wrong input format! Please refer to help for correct user input!");
        }
    }

    @Test
    public void parseAddOutCommand() {
        try {
            FinancialReport testReport = new FinancialReport(statements);
            testReport.addStatement(new FinancialStatement("noodles", "out", 5));
            Parser.parseUserInput("add -out noodles $5");
            assertEquals(financialReport.getFullStatement(0),
                    testReport.getFullStatement(0));
        } catch (Exception e) {
            System.out.println("Wrong input format! Please refer to help for correct user input!");
        }
    }

    @Test
    public void parseDeleteCommand() {
        Command.addFinancialStatement("Ipad", "out", 120);
        Command.addFinancialStatement("angpao", "in", 3000);
        FinancialReport testReport = new FinancialReport(statements);
        testReport.addStatement(new FinancialStatement("Ipad", "out", 120));
        testReport.addStatement(new FinancialStatement("angpao", "in", 3000));
        try {
            Parser.parseUserInput("delete 2");
            assertEquals(RainyDay.financialReport.getFullStatement(0),
                    testReport.getFullStatement(0));
        } catch (Exception e) {
            System.out.println("Wrong input format! Please refer to help for correct user input!");
        }
    }
}
