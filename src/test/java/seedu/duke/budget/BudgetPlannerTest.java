package seedu.duke.budget;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BudgetPlannerTest {

    private static final String BUDGET_PATH = "data/budget.txt";
    File f = new File(BUDGET_PATH);

    @Test
    @Order(1)
    void firstInitialiseBudgetPlanner_oneInstanceAllBudgetsSetToZero_success() {
        BudgetPlanner budgetPlanner = new BudgetPlanner();
        assertEquals(0, budgetPlanner.getBudget());
        assertEquals(0, budgetPlanner.getAccommodationTotalCost());
        assertEquals(0, budgetPlanner.getAirplaneTicketTotalCost());
        assertEquals(0, budgetPlanner.getEntertainmentTotalCost());
        assertEquals(0, budgetPlanner.getFoodTotalCost());
        assertEquals(0, budgetPlanner.getSurplus());
        f.delete();
    }


    @Test
    @Order(2)
    void setBudget_invalidAmountNegative_successNoChangeToPreviousBudget() {
        BudgetPlanner budgetPlanner = new BudgetPlanner();
        budgetPlanner.setBudget(-1);
        assertEquals(0, budgetPlanner.getBudget());
        f.delete();
    }

    @Test
    @Order(3)
    void setBudget_validAmount_success() {
        BudgetPlanner budgetPlanner = new BudgetPlanner();
        budgetPlanner.setBudget(100);
        assertEquals(100, budgetPlanner.getBudget());
        f.delete();
    }

    @Test
    @Order(4)
    void setBudget_exceedingAmount_successBudgetNotChanged() {
        BudgetPlanner budgetPlanner = new BudgetPlanner();
        budgetPlanner.setBudget(100000000);
        assertEquals(0, budgetPlanner.getBudget());
        f.delete();
    }


    @Test
    @Order(5)
    void setAccommodation_invalidAmountNegative_successNoChangeToPreviousBudget() {
        BudgetPlanner budgetPlanner = new BudgetPlanner();
        budgetPlanner.setAccommodationTotalCost(-1);
        assertEquals(0, budgetPlanner.getAccommodationTotalCost());
        f.delete();
    }

    @Test
    @Order(6)
    void setAccommodation_validAmount_success() {
        BudgetPlanner budgetPlanner = new BudgetPlanner();
        budgetPlanner.setAccommodationTotalCost(100);
        assertEquals(100, budgetPlanner.getAccommodationTotalCost());
        f.delete();
    }

    @Test
    @Order(7)
    void setAccommodation_exceedingAmount_successBudgetNotChanged() {
        BudgetPlanner budgetPlanner = new BudgetPlanner();
        budgetPlanner.setAccommodationTotalCost(100000000);
        assertEquals(0, budgetPlanner.getAccommodationTotalCost());
        f.delete();
    }

    @Test
    @Order(8)
    void setAirplaneTicket_invalidAmountNegative_successNoChangeToPreviousBudget() {
        BudgetPlanner budgetPlanner = new BudgetPlanner();
        budgetPlanner.setAirplaneTicketTotalCost(-1);
        assertEquals(0, budgetPlanner.getAirplaneTicketTotalCost());
        f.delete();
    }

    @Test
    @Order(9)
    void setAirplaneTicket_validAmount_success() {
        BudgetPlanner budgetPlanner = new BudgetPlanner();
        budgetPlanner.setAirplaneTicketTotalCost(100);
        assertEquals(100, budgetPlanner.getAirplaneTicketTotalCost());
        f.delete();
    }

    @Test
    @Order(10)
    void setAirplaneTicket_exceedingAmount_successBudgetNotChanged() {
        BudgetPlanner budgetPlanner = new BudgetPlanner();
        budgetPlanner.setAirplaneTicketTotalCost(100000000);
        assertEquals(0, budgetPlanner.getAirplaneTicketTotalCost());
        f.delete();
    }

    @Test
    @Order(11)
    void setFood_invalidAmountNegative_successNoChangeToPreviousBudget() {
        BudgetPlanner budgetPlanner = new BudgetPlanner();
        budgetPlanner.setFoodTotalCost(-1);
        assertEquals(0, budgetPlanner.getFoodTotalCost());
        f.delete();
    }

    @Test
    @Order(12)
    void setFood_validAmount_success() {
        BudgetPlanner budgetPlanner = new BudgetPlanner();
        budgetPlanner.setFoodTotalCost(100);
        assertEquals(100, budgetPlanner.getFoodTotalCost());
        f.delete();
    }

    @Test
    @Order(13)
    void setFood_exceedingAmount_successBudgetNotChanged() {
        BudgetPlanner budgetPlanner = new BudgetPlanner();
        budgetPlanner.setFoodTotalCost(100000000);
        assertEquals(0, budgetPlanner.getFoodTotalCost());
        f.delete();
    }

    @Test
    @Order(14)
    void setEntertainment_invalidAmountNegative_successNoChangeToPreviousBudget() {
        BudgetPlanner budgetPlanner = new BudgetPlanner();
        budgetPlanner.setEntertainmentTotalCost(-1);
        assertEquals(0, budgetPlanner.getEntertainmentTotalCost());
        f.delete();
    }

    @Test
    @Order(15)
    void setEntertainment_validAmount_success() {
        BudgetPlanner budgetPlanner = new BudgetPlanner();
        budgetPlanner.setEntertainmentTotalCost(100);
        assertEquals(100, budgetPlanner.getEntertainmentTotalCost());
        f.delete();
    }

    @Test
    @Order(16)
    void setEntertainment_exceedingAmount_successBudgetNotChanged() {
        BudgetPlanner budgetPlanner = new BudgetPlanner();
        budgetPlanner.setEntertainmentTotalCost(100000000);
        assertEquals(0, budgetPlanner.getEntertainmentTotalCost());
        f.delete();
    }

    @Test
    @Order(17)
    void getBudget_correctInteger_success() {
        int testPrice = 12;
        BudgetPlanner budgetPlanner = new BudgetPlanner();
        assertEquals(0, budgetPlanner.getBudget());
        budgetPlanner.setBudget(testPrice);
        assertEquals(testPrice, budgetPlanner.getBudget());
        f.delete();
    }

    @Test
    @Order(22)
    void getSurplus_correctInteger_success() {
        int testPrice = 12;
        BudgetPlanner budgetPlanner = new BudgetPlanner();
        assertEquals(0, budgetPlanner.getSurplus());
        budgetPlanner.setBudget(testPrice);
        assertEquals(testPrice, budgetPlanner.getSurplus());
        f.delete();
    }

    @Test
    @Order(18)
    void getAccommodationTotalCost_correctInteger_success() {
        int testPrice = 12;
        BudgetPlanner budgetPlanner = new BudgetPlanner();
        assertEquals(0, budgetPlanner.getAccommodationTotalCost());
        budgetPlanner.setAccommodationTotalCost(testPrice);
        assertEquals(testPrice, budgetPlanner.getAccommodationTotalCost());
        f.delete();
    }

    @Test
    @Order(19)
    void getAirplaneTicketTotalCost_correctInteger_success() {
        int testPrice = 12;
        BudgetPlanner budgetPlanner = new BudgetPlanner();
        assertEquals(0, budgetPlanner.getAirplaneTicketTotalCost());
        budgetPlanner.setAirplaneTicketTotalCost(testPrice);
        assertEquals(testPrice, budgetPlanner.getAirplaneTicketTotalCost());
        f.delete();
    }

    @Test
    @Order(20)
    void getFoodTotalCost_correctInteger_success() {
        int testPrice = 12;
        BudgetPlanner budgetPlanner = new BudgetPlanner();
        assertEquals(0, budgetPlanner.getFoodTotalCost());
        budgetPlanner.setFoodTotalCost(testPrice);
        assertEquals(testPrice, budgetPlanner.getFoodTotalCost());
        f.delete();
    }

    @Test
    @Order(21)
    void getEntertainmentTotalCost_correctInteger_success() {
        int testPrice = 12;
        BudgetPlanner budgetPlanner = new BudgetPlanner();
        assertEquals(0, budgetPlanner.getEntertainmentTotalCost());
        budgetPlanner.setEntertainmentTotalCost(testPrice);
        assertEquals(testPrice, budgetPlanner.getEntertainmentTotalCost());
        f.delete();
    }
}
