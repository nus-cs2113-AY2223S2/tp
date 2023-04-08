package seedu.duke.command;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import seedu.duke.budget.BudgetPlanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class EditFoodCommandTest {
    @Test
    @Order(1)
    void editFood_negativeAmount_success() {
        BudgetPlanner budgetPlanner = BudgetPlanner.getInstance();
        Command command = new EditFoodCommand(-2, budgetPlanner);
        command.execute();
        assertEquals(0, budgetPlanner.getFoodTotalCost());
    }

    @Test
    @Order(2)
    void editFood_correctAmount_success() {
        BudgetPlanner budgetPlanner = BudgetPlanner.getInstance();
        Command command = new EditFoodCommand(2000, budgetPlanner);
        command.execute();
        assertEquals(2000, budgetPlanner.getFoodTotalCost());
    }

    @Test
    @Order(3)
    void editFoodAmountNoChange_negativeAmount_success() {
        BudgetPlanner budgetPlanner = BudgetPlanner.getInstance();
        Command command = new EditFoodCommand(-2, budgetPlanner);
        command.execute();
        assertEquals(2000, budgetPlanner.getFoodTotalCost());
    }

    @Test
    @Order(4)
    void editFoodAmountNoChange_exceedAmount_success() {
        BudgetPlanner budgetPlanner = BudgetPlanner.getInstance();
        Command command = new EditFoodCommand(20000001, budgetPlanner);
        command.execute();
        assertEquals(2000, budgetPlanner.getFoodTotalCost());
    }

    @Test
    @Order(5)
    void editFoodAmountNoChange_exceedIntegerAmount_success() {
        BudgetPlanner budgetPlanner = BudgetPlanner.getInstance();
        Command command = new EditFoodCommand(Integer.MAX_VALUE + 1, budgetPlanner);
        command.execute();
        assertEquals(2000, budgetPlanner.getFoodTotalCost());
    }

    @AfterAll
    private static void cleanup() {
        BudgetPlanner budgetPlanner = BudgetPlanner.getInstance();
        budgetPlanner.setFoodTotalCost(0);
    }
}
