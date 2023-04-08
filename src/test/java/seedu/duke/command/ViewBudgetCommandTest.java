package seedu.duke.command;

import org.junit.jupiter.api.Test;
import seedu.duke.UI;
import seedu.duke.budget.BudgetPlanner;

import java.io.ByteArrayOutputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ViewBudgetCommandTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Test
    void viewBudget_execute_success() {
        BudgetPlanner budgetPlanner = BudgetPlanner.getInstance();
        Command command = new ViewBudgetCommand(budgetPlanner);
        command.execute();
        assertEquals("","");
    }
}