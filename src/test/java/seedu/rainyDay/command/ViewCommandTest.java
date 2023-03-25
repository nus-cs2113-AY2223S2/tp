package seedu.rainyDay.command;

import org.junit.jupiter.api.Test;
import seedu.rainyDay.data.FinancialReport;
import seedu.rainyDay.data.FinancialStatement;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ViewCommandTest {

    ArrayList<FinancialStatement> statements = new ArrayList<>();
    FinancialReport financialReport = new FinancialReport(statements);

    @Test
    public void execute_emptyReport_emptyReportStatement() {
        LocalDate startDate = LocalDate.now().minusYears(10);
        ViewCommand viewList = new ViewCommand(startDate, false); //todo fix this with -all flag. Temp set to maximum
        viewList.setData(financialReport);
        String expectedReport = "Your financial report is empty";
        assertEquals(expectedReport, viewList.execute().output);
    }

}
