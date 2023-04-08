package seedu.duke.command;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import seedu.duke.budget.BudgetPlanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class EditBudgetCommandTest {
    @Test
    @Order(1)
    void editBudget_negativeAmount_success() {
        BudgetPlanner budgetPlanner = BudgetPlanner.getInstance();
        Command command = new EditBudgetCommand(-2, budgetPlanner);
        command.execute();
        assertEquals(0, budgetPlanner.getBudget());
    }

    @Test
    @Order(2)
    void editBudget_correctAmount_success() {
        BudgetPlanner budgetPlanner = BudgetPlanner.getInstance();
        Command command = new EditBudgetCommand(2000, budgetPlanner);
        command.execute();
        assertEquals(2000, budgetPlanner.getBudget());
    }

    @Test
    @Order(3)
    void editBudgetAmountNoChange_negativeAmount_success() {
        BudgetPlanner budgetPlanner = BudgetPlanner.getInstance();
        Command command = new EditBudgetCommand(-2, budgetPlanner);
        command.execute();
        assertEquals(2000, budgetPlanner.getBudget());
    }

    @Test
    @Order(4)
    void editBudgetAmountNoChange_exceedAmount_success() {
        BudgetPlanner budgetPlanner = BudgetPlanner.getInstance();
        Command command = new EditBudgetCommand(20000001, budgetPlanner);
        command.execute();
        assertEquals(2000, budgetPlanner.getBudget());
    }

    @Test
    @Order(5)
    void editBudgetAmountNoChange_exceedIntegerAmount_success() {
        BudgetPlanner budgetPlanner = BudgetPlanner.getInstance();
        Command command = new EditBudgetCommand(Integer.MAX_VALUE + 1, budgetPlanner);
        command.execute();
        assertEquals(2000, budgetPlanner.getBudget());
    }

    @AfterAll
    private static void cleanup() {
        BudgetPlanner budgetPlanner = BudgetPlanner.getInstance();
        budgetPlanner.setBudget(0);
    }

}