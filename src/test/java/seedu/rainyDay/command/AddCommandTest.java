package seedu.rainyDay.command;

import org.junit.jupiter.api.Test;
import seedu.rainyDay.data.FinancialReport;
import seedu.rainyDay.data.FinancialStatement;
import seedu.rainyDay.data.MonthlyExpenditures;
import seedu.rainyDay.data.SavedData;
import seedu.rainyDay.data.UserData;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddCommandTest {
    ArrayList<FinancialStatement> statements = new ArrayList<>();
    FinancialReport financialReport = new FinancialReport(statements);
    HashMap<Integer, Double> expenditures = new HashMap<>();
    MonthlyExpenditures monthlyExpenditures = new MonthlyExpenditures(expenditures);
    SavedData savedData = new SavedData(financialReport);
    UserData userData = new UserData(savedData, monthlyExpenditures);

    @Test
    public void execute_singleStatement_statementInformation() {
        DateTimeFormatter formatters = DateTimeFormatter.ofPattern("dd/MM/uuuu");
        String dateString = LocalDate.now().format(formatters);
        AddCommand addCommand = new AddCommand("Ipad", "out", 120.50, "Default",
                LocalDate.now());
        addCommand.setData(userData);
        CommandResult outcome = addCommand.execute();
        FinancialStatement expectedStatement = new FinancialStatement("Ipad", "out", 120.5,
                "Default", LocalDate.now());
        assertEquals(1, savedData.getStatementCount());
        assertEquals(expectedStatement.getFullStatement(),
                savedData.getStatement(0).getFullStatement());

        String expectedOutput = "Done! Added: out for Ipad, -$120.50, in category Default on " + dateString;
        assertEquals(expectedOutput, outcome.output);
    }

    @Test
    public void execute_multipleStatements_statementsInformation() {
        DateTimeFormatter formatters = DateTimeFormatter.ofPattern("dd/MM/uuuu");
        String dateString = LocalDate.now().format(formatters);
        AddCommand addCommand = new AddCommand("angpao", "in", 3000.00, "Default",
                LocalDate.now());
        addCommand.setData(userData);
        CommandResult outcome = addCommand.execute();
        FinancialStatement expectedStatement = new FinancialStatement("angpao", "in", 3000.00,
                "Default", LocalDate.now());
        assertEquals(expectedStatement.getFullStatement(),
                financialReport.getFinancialStatement(0).getFullStatement());

        String expectedOutput = "Done! Added: in for angpao, +$3000.00, in category Default on " + dateString;
        assertEquals(expectedOutput, outcome.output);

        addCommand = new AddCommand("textbook", "out", 50.00, "Default",
                LocalDate.now());
        addCommand.setData(userData);
        outcome = addCommand.execute();
        assertEquals(2, savedData.getStatementCount());

        expectedStatement = new FinancialStatement("textbook", "out", 50.00,
                "Default", LocalDate.now());
        assertEquals(expectedStatement.getFullStatement(),
                financialReport.getFinancialStatement(1).getFullStatement());

        expectedOutput = "Done! Added: out for textbook, -$50.00, in category Default on " + dateString;
        assertEquals(expectedOutput, outcome.output);
    }
}
