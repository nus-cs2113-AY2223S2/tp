package seedu.rainyDay.parser;

import org.junit.jupiter.api.Test;
import seedu.rainyDay.RainyDay;
import seedu.rainyDay.command.EditCommand;
import seedu.rainyDay.command.ExitCommand;
import seedu.rainyDay.command.ExportCommand;
import seedu.rainyDay.command.FilterCommand;
import seedu.rainyDay.command.HelpCommand;
import seedu.rainyDay.command.SetBudgetCommand;
import seedu.rainyDay.command.ShortcutAddCommand;
import seedu.rainyDay.command.ShortcutDeleteCommand;
import seedu.rainyDay.command.ShortcutViewCommand;
import seedu.rainyDay.command.ViewCommand;
import seedu.rainyDay.data.FinancialReport;
import seedu.rainyDay.data.FinancialStatement;
import seedu.rainyDay.data.MonthlyExpenditures;
import seedu.rainyDay.data.SavedData;
import seedu.rainyDay.exceptions.ErrorMessage;
import seedu.rainyDay.exceptions.RainyDayException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ParserTest {
    ArrayList<FinancialStatement> statements = new ArrayList<>();
    FinancialReport financialReport = new FinancialReport(statements);
    SavedData savedData = new SavedData(financialReport);
    HashMap<Integer, Double> expenditures = new HashMap<>();
    MonthlyExpenditures monthlyExpenditures = new MonthlyExpenditures(expenditures);

    // todo add more test cases
    @Test
    public void parseAddInCommand() {
        FinancialReport testReport = new FinancialReport(statements);
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
            FinancialReport testReport = new FinancialReport(statements);
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
            assertEquals(ErrorMessage.WRONG_ADD_FORMAT.toString(),
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
            assertEquals(ErrorMessage.INVALID_DAY + ErrorMessage.ADD_FORMAT.toString(),
                    e.getMessage().toString());
        }
    }

    @Test
    public void parseUserInput_validAddCommand_addCommand() throws RainyDayException {
        assertEquals(AddCommand.class, new Parser().parseUserInput("add -in abc $40.2").getClass());
        assertEquals(AddCommand.class, new Parser().parseUserInput("add -out abcedfg $4").getClass());
        assertEquals(AddCommand.class, new Parser().parseUserInput("add -out new phone $1800.99 -c electronics")
                .getClass());
        assertEquals(AddCommand.class, new Parser().parseUserInput("add -in income $3000.5 -c income from side job")
                .getClass());
        assertEquals(AddCommand.class, new Parser().parseUserInput
                ("add -out computer $4000 -c new gadget -date 1/4/2023").getClass());
        assertEquals(AddCommand.class, new Parser().parseUserInput
                ("add -in youtube $400 -c full time job -date 20/4/2023").getClass());
        assertEquals(AddCommand.class, new Parser().parseUserInput
                ("add -out food $3.20 -date 22/05/2023").getClass());
        assertEquals(AddCommand.class, new Parser().parseUserInput
                ("add -in allowance $20 -date 22/10/2023").getClass());
    }

    @Test
    public void parseUserInput_invalidAddCommand_exception() {
        assertThrows(RainyDayException.class, () -> new Parser().parseUserInput("add"));
        assertThrows(RainyDayException.class, () -> new Parser().parseUserInput("add -a"));
        assertThrows(RainyDayException.class, () -> new Parser().parseUserInput("add -"));
        assertThrows(RainyDayException.class, () -> new Parser().parseUserInput("add -out"));
        assertThrows(RainyDayException.class, () -> new Parser().parseUserInput("add -in $12"));
        assertThrows(RainyDayException.class, () -> new Parser().parseUserInput("add -in description"));
        assertThrows(RainyDayException.class, () -> new Parser().parseUserInput("add -in des-cription $12"));
        assertThrows(RainyDayException.class, () -> new Parser().parseUserInput("add -in description $12 abc"));
        assertThrows(RainyDayException.class, () -> new Parser().parseUserInput
                ("add -out description $12 -c cat-egory"));
        assertThrows(RainyDayException.class, () -> new Parser().parseUserInput
                ("add -out description $12 -c"));
        assertThrows(RainyDayException.class, () -> new Parser().parseUserInput
                ("add -out description $12 -date"));
        assertThrows(RainyDayException.class, () -> new Parser().parseUserInput
                ("add -out description $12 -date 1"));
        assertThrows(RainyDayException.class, () -> new Parser().parseUserInput
                ("add -out description $12 -date 34/2/2024"));
        assertThrows(RainyDayException.class, () -> new Parser().parseUserInput
                ("add -out description $12 -date 31/2/2024"));
        assertThrows(RainyDayException.class, () -> new Parser().parseUserInput
                ("add -out description $12 -c category -date 12/12a/2024"));
        assertThrows(RainyDayException.class, () -> new Parser().parseUserInput
                ("add -out description $12 -c category -date 12/12/2024a"));
        assertThrows(RainyDayException.class, () -> new Parser().parseUserInput
                ("add -out description $12 -c category -date a/12/2024"));
        assertThrows(RainyDayException.class, () -> new Parser().parseUserInput
                ("add -out description $12 -c category -date 1/ 12/2024 "));
        assertThrows(RainyDayException.class, () -> new Parser().parseUserInput
                ("add -out description $12 -c category -date 1/12/ 2024"));
        assertThrows(RainyDayException.class, () -> new Parser().parseUserInput
                ("add -out description $12 -c category -date 1/12/2024 abc"));
        assertThrows(RainyDayException.class, () -> new Parser().parseUserInput
                ("add -out description $12 abc -c category -date 1/12/2024"));
        assertThrows(RainyDayException.class, () -> new Parser().parseUserInput
                ("add -out description $12.a -c category -date 1/12/2024"));
        assertThrows(RainyDayException.class, () -> new Parser().parseUserInput
                ("add -out description $12 -c category -daate 1/12/2024"));
        assertThrows(RainyDayException.class, () -> new Parser().parseUserInput
                ("add -out description $12 -date 12/2024"));
        assertThrows(RainyDayException.class, () -> new Parser().parseUserInput
                ("add -out description $12 -date 11/13/2024"));
        assertThrows(RainyDayException.class, () -> new Parser().parseUserInput
                ("add -out description $12 -date 11/12/024"));
        assertThrows(RainyDayException.class, () -> new Parser().parseUserInput
                ("add -out description $0"));
        assertThrows(RainyDayException.class, () -> new Parser().parseUserInput
                ("add -out description $12 -date 11/12/024"));
        assertThrows(RainyDayException.class, () -> new Parser().parseUserInput
                ("add -out        $12"));
        assertThrows(RainyDayException.class, () -> new Parser().parseUserInput
                ("add -out abc $21474836.48"));
        assertThrows(RainyDayException.class, () -> new Parser().parseUserInput
                ("add -out abc $21474836.47 -c -date"));
        assertThrows(RainyDayException.class, () -> new Parser().parseUserInput
                ("add -out abc $21474836.47 -c category -date"));
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

    @Test
    public void parseGenerateReport() throws RainyDayException {
        assertEquals(ViewCommand.class, new Parser().parseUserInput("view").getClass());
        assertEquals(ViewCommand.class, new Parser().parseUserInput("view -sort").getClass());
        assertEquals(ViewCommand.class, new Parser().parseUserInput("view -all -sort").getClass());
        assertEquals(ViewCommand.class, new Parser().parseUserInput("view 21d -sort").getClass());
        assertEquals(ViewCommand.class, new Parser().parseUserInput("view 2w -sort").getClass());
        assertEquals(ViewCommand.class, new Parser().parseUserInput("view 1m -sort").getClass());
        assertEquals(ViewCommand.class, new Parser().parseUserInput("view 5y -sort").getClass());

        assertThrows(RainyDayException.class, () -> new Parser().parseUserInput("view -test"));
        assertThrows(RainyDayException.class, () -> new Parser().parseUserInput("view 32d -sort"));
        assertThrows(RainyDayException.class, () -> new Parser().parseUserInput("view 5w -sort"));
        assertThrows(RainyDayException.class, () -> new Parser().parseUserInput("view 13m -sort"));
        assertThrows(RainyDayException.class, () -> new Parser().parseUserInput("view 11y -sort"));
    }

    //@@author ChongQiRong
    @Test
    public void parseWrongFilterCommand() {
        assertThrows(RainyDayException.class, () -> new Parser().parseUserInput("filter"));
        assertThrows(RainyDayException.class, () -> new Parser().parseUserInput("filter 1 -in"));
        assertThrows(RainyDayException.class, () -> new Parser().parseUserInput("filter -in -out"));
        assertThrows(RainyDayException.class, () -> new Parser().parseUserInput("filter -in -c -d -date 23/01/2023"));
        assertThrows(RainyDayException.class, () -> new Parser().parseUserInput("filter -date 32/01/2023"));
        assertThrows(RainyDayException.class, () -> new Parser().parseUserInput("filter -d -c -date 32/01/2023"));
        assertThrows(RainyDayException.class, () -> new Parser().parseUserInput("filter -date 24/01/2023 test"));
        assertThrows(RainyDayException.class, () -> new Parser().parseUserInput("filter -d"));
    }

    @Test
    public void parseValidFilterCommand() throws RainyDayException {
        assertEquals(FilterCommand.class, new Parser().parseUserInput("filter -in").getClass());
        assertEquals(FilterCommand.class, new Parser().parseUserInput("filter -out").getClass());
        assertEquals(FilterCommand.class, new Parser().parseUserInput("filter -d chicken").getClass());
        assertEquals(FilterCommand.class, new Parser().parseUserInput("filter -c Food and Drinks").getClass());
        assertEquals(FilterCommand.class, new Parser().parseUserInput("filter -date 23/01/2023").getClass());
        assertEquals(FilterCommand.class, new Parser().parseUserInput("filter -d rice -c Food -date 04/04/2023")
                .getClass());
        assertEquals(FilterCommand.class, new Parser().parseUserInput("filter -date 23/01/2023 25/03/2023").getClass());
        assertEquals(FilterCommand.class, new Parser().parseUserInput("filter -in -date 23/01/2023 25/03/2023")
                .getClass());
    }

    @Test
    public void parseWrongEditCommand() {
        assertThrows(RainyDayException.class, () -> new Parser().parseUserInput("edit 1"));
    }

    @Test
    public void parseEditCommand() throws RainyDayException {
        RainyDay.savedData = savedData;
        savedData.getFinancialReport().clearReport();
        assertThrows(RainyDayException.class, () -> new Parser().parseUserInput("edit 1 -out"));

        savedData.addStatement(new FinancialStatement("noodles", "in", 5, "miscellaneous",
                LocalDate.now()));
        assertEquals(EditCommand.class, new Parser().parseUserInput("edit 1 -out").getClass());
        assertEquals(EditCommand.class, new Parser().parseUserInput("edit 1 -out -d Beef noodles -v $15 -c Food and " +
                "Drinks -date 01/04/2023").getClass());

        assertThrows(RainyDayException.class, () -> new Parser().parseUserInput("edit -1 -out"));
        assertThrows(RainyDayException.class, () -> new Parser().parseUserInput("edit"));
        assertThrows(RainyDayException.class, () -> new Parser().parseUserInput("edit 1 -test"));
        assertThrows(RainyDayException.class, () -> new Parser().parseUserInput("edit 1 -date 32/01/2034"));
        assertThrows(RainyDayException.class, () -> new Parser().parseUserInput("edit 1 -in test"));
    }

    @Test
    public void parseSetBudgetCommand() throws RainyDayException {
        assertEquals(SetBudgetCommand.class, new Parser().parseUserInput("setbudget 1000").getClass());
        assertThrows(Exception.class, () -> new Parser().parseUserInput("setbudget -1000"));
    }

    @Test
    public void parseHelpCommand() throws RainyDayException {
        assertEquals(HelpCommand.class, new Parser().parseUserInput("help").getClass());
    }

    @Test
    public void parseByeCommand() throws RainyDayException {
        assertEquals(ExitCommand.class, new Parser().parseUserInput("bye").getClass());
    }

    @Test
    public void parseExportCommand() throws RainyDayException {
        assertEquals(ExportCommand.class, new Parser().parseUserInput("export").getClass());
    }
    @Test
    public void parseShortcutCommand() throws RainyDayException {
        assertEquals(ShortcutAddCommand.class, new Parser().parseUserInput("shortcut a -maps b").getClass());
    }

    @Test
    public void parseShortcutCommand_invalidCommand_exceptionThrown() {
        assertThrows(RainyDayException.class,
                () -> new Parser().parseUserInput("shortcut a -maps b -maps c").getClass());
        assertThrows(RainyDayException.class,
                () -> new Parser().parseUserInput("shortcut a -map c").getClass());
        assertThrows(RainyDayException.class,
                () -> new Parser().parseUserInput("shortcut a b -maps c").getClass());
    }
    
    @Test
    public void parseShortcutViewCommand() throws RainyDayException {
        assertEquals(ShortcutViewCommand.class, new Parser().parseUserInput("shortcut_view").getClass());
    }

    @Test
    public void parseShortcutDeleteCommand() throws RainyDayException {
        assertEquals(ShortcutDeleteCommand.class, new Parser().parseUserInput("shortcut_delete 4").getClass());
    }

    @Test
    public void parseInvalidCommand_exceptionThrown() throws RainyDayException {
        assertThrows(RainyDayException.class, () -> new Parser().parseUserInput("hello_world").getClass());
    }
}
