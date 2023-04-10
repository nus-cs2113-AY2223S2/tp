package seedu.duke.command;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import seedu.duke.budget.BudgetPlanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class EditAirplaneTicketCommandTest {
    @Test
    @Order(1)
    void editAirplaneTicket_negativeAmount_success() {
        BudgetPlanner budgetPlanner = BudgetPlanner.getInstance();
        Command command = new EditAirplaneTicketCommand(-2, budgetPlanner);
        command.execute();
        assertEquals(0, budgetPlanner.getAirplaneTicketTotalCost());
    }

    @Test
    @Order(2)
    void editAirplaneTicket_correctAmount_success() {
        BudgetPlanner budgetPlanner = BudgetPlanner.getInstance();
        Command command = new EditAirplaneTicketCommand(2000, budgetPlanner);
        command.execute();
        assertEquals(2000, budgetPlanner.getAirplaneTicketTotalCost());
    }

    @Test
    @Order(3)
    void editAirplaneTicketAmountNoChange_negativeAmount_success() {
        BudgetPlanner budgetPlanner = BudgetPlanner.getInstance();
        Command command = new EditAirplaneTicketCommand(-2, budgetPlanner);
        command.execute();
        assertEquals(2000, budgetPlanner.getAirplaneTicketTotalCost());
    }

    @Test
    @Order(4)
    void editAirplaneTicketAmountNoChange_exceedAmount_success() {
        BudgetPlanner budgetPlanner = BudgetPlanner.getInstance();
        Command command = new EditAirplaneTicketCommand(20000001, budgetPlanner);
        command.execute();
        assertEquals(2000, budgetPlanner.getAirplaneTicketTotalCost());
    }

    @Test
    @Order(5)
    void editAirplaneTicketAmountNoChange_exceedIntegerAmount_success() {
        BudgetPlanner budgetPlanner = BudgetPlanner.getInstance();
        Command command = new EditAirplaneTicketCommand(Integer.MAX_VALUE + 1, budgetPlanner);
        command.execute();
        assertEquals(2000, budgetPlanner.getAirplaneTicketTotalCost());
    }

    @AfterAll
    private static void cleanup() {
        BudgetPlanner budgetPlanner = BudgetPlanner.getInstance();
        budgetPlanner.setAirplaneTicketTotalCost(0);
    }
}
