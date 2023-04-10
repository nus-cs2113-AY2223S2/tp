package seedu.duke.command;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import seedu.duke.budget.BudgetPlanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class EditEntertainmentCommandTest {
    @Test
    @Order(1)
    void editEntertainment_negativeAmount_success() {
        BudgetPlanner budgetPlanner = BudgetPlanner.getInstance();
        Command command = new EditEntertainmentCommand(-2, budgetPlanner);
        command.execute();
        assertEquals(0, budgetPlanner.getEntertainmentTotalCost());
    }

    @Test
    @Order(2)
    void editEntertainment_correctAmount_success() {
        BudgetPlanner budgetPlanner = BudgetPlanner.getInstance();
        Command command = new EditEntertainmentCommand(2000, budgetPlanner);
        command.execute();
        assertEquals(2000, budgetPlanner.getEntertainmentTotalCost());
    }

    @Test
    @Order(3)
    void editEntertainmentAmountNoChange_negativeAmount_success() {
        BudgetPlanner budgetPlanner = BudgetPlanner.getInstance();
        Command command = new EditEntertainmentCommand(-2, budgetPlanner);
        command.execute();
        assertEquals(2000, budgetPlanner.getEntertainmentTotalCost());
    }

    @Test
    @Order(4)
    void editEntertainmentAmountNoChange_exceedAmount_success() {
        BudgetPlanner budgetPlanner = BudgetPlanner.getInstance();
        Command command = new EditEntertainmentCommand(20000001, budgetPlanner);
        command.execute();
        assertEquals(2000, budgetPlanner.getEntertainmentTotalCost());
    }

    @Test
    @Order(5)
    void editEntertainmentAmountNoChange_exceedIntegerAmount_success() {
        BudgetPlanner budgetPlanner = BudgetPlanner.getInstance();
        Command command = new EditEntertainmentCommand(Integer.MAX_VALUE + 1, budgetPlanner);
        command.execute();
        assertEquals(2000, budgetPlanner.getEntertainmentTotalCost());
    }

    @AfterAll
    private static void cleanup() {
        BudgetPlanner budgetPlanner = BudgetPlanner.getInstance();
        budgetPlanner.setEntertainmentTotalCost(0);
    }
}
