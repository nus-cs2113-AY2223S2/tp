package seedu.rainyDay.data;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import seedu.rainyDay.exceptions.ErrorMessage;
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
    public void parseAddInCommand() {
        FinancialReport testReport = new FinancialReport(statements, monthlyExpenditures);
        testReport.addStatement(new FinancialStatement("noodles", "in", 5, "miscellaneous",
                LocalDate.now()));
        try {
            new Parser().parseUserInput("add -in noodles $5");
            assertEquals(financialReport.getFullStatement(0),
                    testReport.getFullStatement(0)); // todo, update after modified
            testReport.addStatement(new FinancialStatement("rice", "in", 10, "food",
                    LocalDate.now()));
            new Parser().parseUserInput("add -in rice $10 -c food");
            assertEquals(financialReport.getFullStatement(1),
                    testReport.getFullStatement(1));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void parseAddOutCommand() {
        try {
            FinancialReport testReport = new FinancialReport(statements, monthlyExpenditures);
            testReport.addStatement(new FinancialStatement("noodles", "out", 5,
                    "miscellaneous", LocalDate.now()));
            new Parser().parseUserInput("add -out noodles $5");
            assertEquals(financialReport.getFullStatement(0),
                    testReport.getFullStatement(0)); // todo, update after modified
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void parseWrongAddFormat() {
        try {
            new Parser().parseUserInput("add");
        } catch (Exception e) {
            assertEquals(ErrorMessage.MISSING_DETAILS.toString(),
                    e.getMessage().toString());
        }
        try {
            new Parser().parseUserInput("add -out rice $5");
        } catch (Exception e) {
            assertEquals(ErrorMessage.WRONG_ADD_FORMAT.toString(),
                    e.getMessage().toString());
        }
        try {
            new Parser().parseUserInput("add -out rice $5 -date aa/bb/cccc");
        } catch (Exception e) {
            assertEquals(ErrorMessage.WRONG_ADD_FORMAT.toString(),
                    e.getMessage().toString());
        }
    }

    @Test
    public void parseDeleteCommand() {
        financialReport.clearReport();
        financialReport.addStatement(new FinancialStatement("noodles", "in", 5, "miscellaneous",
                LocalDate.now()));
        financialReport.addStatement(new FinancialStatement("noodles2", "in", 5, "miscellaneous",
                LocalDate.now()));
        try {
            new Parser().parseUserInput("delete 2");
            assertEquals(1, financialReport.getStatementCount());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void parseWrongDeleteFormat() {
        try {
            new Parser().parseUserInput("delete");

        } catch (Exception e) {
            assertEquals(e.getMessage().toString(), ErrorMessage.NO_DELETE_INDEX.toString());
        }
    }
}
