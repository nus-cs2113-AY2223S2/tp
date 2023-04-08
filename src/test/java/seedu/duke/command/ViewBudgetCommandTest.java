package seedu.duke.command;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import seedu.duke.UI;
import seedu.duke.budget.BudgetPlanner;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ViewBudgetCommandTest {

    //@@author dfa-reused
    //Reused from https://stackoverflow.com/questions/1119385/junit-test-for-system-out-println
    private static final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeAll
    public void setUpStream() {
        System.setOut(new PrintStream(outContent));
    }

    //@@author
    @Test
    void viewBudget_execute_success() {

        outContent.reset();
        BudgetPlanner budgetPlanner = BudgetPlanner.getInstance();
        Command command = new ViewBudgetCommand(budgetPlanner);
        command.execute();
        UI ui = UI.getUiOneInstance();
        String ls = System.lineSeparator();
        assertEquals(ls + ui.getLine() + ls
                        + "Total budget: " + budgetPlanner.getBudget() + ls
                        + "Accommodation cost: " + budgetPlanner.getAccommodationTotalCost() + ls
                        + "Airplane Ticket cost: " + budgetPlanner.getAirplaneTicketTotalCost() + ls
                        + "Food cost: " + budgetPlanner.getFoodTotalCost() + ls
                        + "Entertainment cost: " + budgetPlanner.getEntertainmentTotalCost() + ls
                        + "Surplus/Deficit: " + budgetPlanner.getSurplus() + ls
                        + ui.getLine()
                , ls + outContent.toString().stripTrailing());
    }
}