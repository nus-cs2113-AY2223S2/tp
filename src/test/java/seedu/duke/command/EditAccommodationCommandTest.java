package seedu.duke.command;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import seedu.duke.budget.BudgetPlanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class EditAccommodationCommandTest {

    @Test
    @Order(1)
    void editAccommodation_negativeAmount_success() {
        BudgetPlanner budgetPlanner = BudgetPlanner.getInstance();
        Command command = new EditAccommodationCommand(-2, budgetPlanner);
        command.execute();
        assertEquals(0, budgetPlanner.getAccommodationTotalCost());
    }

    @Test
    @Order(2)
    void editAccommodation_correctAmount_success() {
        BudgetPlanner budgetPlanner = BudgetPlanner.getInstance();
        Command command = new EditAccommodationCommand(2000, budgetPlanner);
        command.execute();
        assertEquals(2000, budgetPlanner.getAccommodationTotalCost());
    }

    @Test
    @Order(3)
    void editAccommodationAmountNoChange_negativeAmount_success() {
        BudgetPlanner budgetPlanner = BudgetPlanner.getInstance();
        Command command = new EditAccommodationCommand(-2, budgetPlanner);
        command.execute();
        assertEquals(2000, budgetPlanner.getAccommodationTotalCost());
    }

    @Test
    @Order(4)
    void editAccommodationAmountNoChange_exceedAmount_success() {
        BudgetPlanner budgetPlanner = BudgetPlanner.getInstance();
        Command command = new EditAccommodationCommand(20000001, budgetPlanner);
        command.execute();
        assertEquals(2000, budgetPlanner.getAccommodationTotalCost());
    }

    @Test
    @Order(5)
    void editAccommodationAmountNoChange_exceedIntegerAmount_success() {
        BudgetPlanner budgetPlanner = BudgetPlanner.getInstance();
        Command command = new EditAccommodationCommand(Integer.MAX_VALUE + 1, budgetPlanner);
        command.execute();
        assertEquals(2000, budgetPlanner.getAccommodationTotalCost());
    }

    @AfterAll
    private static void cleanup() {
        BudgetPlanner budgetPlanner = BudgetPlanner.getInstance();
        budgetPlanner.setAccommodationTotalCost(0);
    }
}