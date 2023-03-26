package seedu.rainyDay.data;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import seedu.rainyDay.modules.Parser;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

class ParserTest {
    ArrayList<FinancialStatement> statements = new ArrayList<>();
    HashMap<Integer, Double> monthlyExpenditures = new HashMap<>();
    FinancialReport financialReport = new FinancialReport(statements, monthlyExpenditures);

    // todo add more test cases
    @Test
    public void parseAddInCommand() throws Exception {
        FinancialReport testReport = new FinancialReport(statements, monthlyExpenditures);
        testReport.addStatement(new FinancialStatement("noodles", "in", 5, "Default",
                LocalDate.now()));
        new Parser().parseUserInput("add -in noodles $5");
        assertEquals(financialReport.getFullStatement(0),
                testReport.getFullStatement(0)); // todo, update after modified
    }

    @Test
    public void parseAddOutCommand() {
        try {
            FinancialReport testReport = new FinancialReport(statements, monthlyExpenditures);
            testReport.addStatement(new FinancialStatement("noodles", "out", 5,
                    "Default", LocalDate.now()));
            new Parser().parseUserInput("add -out noodles $5");
            assertEquals(financialReport.getFullStatement(0),
                    testReport.getFullStatement(0)); // todo, update after modified
        } catch (Exception e) {
            System.out.println("Wrong input format! Please refer to help for correct user input!");
        }
    }

    /*
    @Test
    public void parseDeleteCommand() {
        financialReport.clearReport();
        new AddFinancialStatement("Ipad", "out", 120).execute();
        new AddFinancialStatement("angpao", "in", 3000).execute();
        try {
            Parser.parseUserInput("delete 2");
            assertEquals(1, RainyDay.financialReport.getStatementCount());
        } catch (Exception e) {
            System.out.println("Wrong input format! Please refer to help for correct user input!");
        }
    }
    */
}
