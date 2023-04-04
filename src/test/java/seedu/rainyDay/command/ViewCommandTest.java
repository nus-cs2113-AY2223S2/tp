package seedu.rainyDay.command;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import seedu.rainyDay.data.AllData;
import seedu.rainyDay.data.FinancialReport;
import seedu.rainyDay.data.FinancialStatement;
import seedu.rainyDay.data.UserData;
import seedu.rainyDay.data.MonthlyExpenditures;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ViewCommandTest {

    ArrayList<FinancialStatement> statements = new ArrayList<>();
    FinancialReport financialReport = new FinancialReport(statements);
    HashMap<Integer, Double> expenditures = new HashMap<>();
    MonthlyExpenditures monthlyExpenditures = new MonthlyExpenditures(expenditures);
    UserData userData = new UserData(financialReport);
    AllData allData = new AllData(userData,monthlyExpenditures);
    
    @Test
    public void execute_emptyReport_emptyReportStatement() {
        ViewCommand viewList = new ViewCommand(LocalDate.now(), LocalDate.now(), false, true);
        viewList.setData(allData);
        String expectedReport = String.format("Your financial report is empty for %s till %s",
                LocalDate.now(), LocalDate.now());
        assertEquals(expectedReport, viewList.execute().output);
    }

    class sortByValue implements Comparator<Integer> {
        public int compare(Integer firstIndex, Integer secondIndex) {
            FinancialStatement firstStatement = userData.getStatement(firstIndex);
            FinancialStatement secondStatement = userData.getStatement(secondIndex);
            if (firstStatement.getFlowSymbol().equals("+") && secondStatement.getFlowSymbol().equals("-")) {
                return -1;
            }
            if (firstStatement.getFlowSymbol().equals("-") && secondStatement.getFlowSymbol().equals("+")) {
                return 1;
            }
            return (int) ((firstStatement.getValue() * 100) - (secondStatement.getValue() * 100));
        }
    }


    @Test
    public void sortingComparatorTest() {
        financialReport.clearReport();
        financialReport.addStatement(new FinancialStatement(
                "Big Outflow (3)", "out", 100, "test", LocalDate.now()));
        financialReport.addStatement(new FinancialStatement(
                "Big Inflow (1)", "in", 100, "test", LocalDate.now()));
        financialReport.addStatement(new FinancialStatement(
                "Small Outflow (4)", "out", 1, "test", LocalDate.now()));
        financialReport.addStatement(new FinancialStatement(
                "Small Inflow (2)", "in", 1, "test", LocalDate.now()));

        ArrayList<Integer> indexes = new ArrayList<>();
        for(int i = 0; i < financialReport.getStatementCount(); i++) {
            indexes.add(i);
            System.out.println(financialReport.getFinancialStatement(i).getFullStatement());
        }

        indexes.sort(new sortByValue()); //to fix this garbage

        for(int i = 0; i < financialReport.getStatementCount(); i++) {
            for(int j = 0; j < i; j++) {
                FinancialStatement smaller = financialReport.getFinancialStatement(i);
                FinancialStatement bigger = financialReport.getFinancialStatement(j);
                Assertions.assertTrue(smaller.getValue() < bigger.getValue() ||
                        (smaller.getFlowSymbol().equals("+")) && (bigger.getFlowSymbol().equals("-")));
            }
        }
    }

}
