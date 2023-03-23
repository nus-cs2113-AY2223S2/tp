package seedu.duke.budget;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BudgetStorageTest {

    private static final int TEST_INT = 12;
    private static final String BUDGET_PATH = "data/budget.txt";
    File f = new File(BUDGET_PATH);

    @Test
    @Order(1)
    void initialiseDatabase_txtFileDoesNotExist_catchIOExceptionSuccess() {
        BudgetStorage budgetStorage = new BudgetStorage();
        try {
            budgetStorage.initialiseDatabase();
        } catch (Exception e) {
            assertEquals(new IOException(), e.getClass());
        }
        f.delete();
    }

    @Test
    @Order(2)
    void initialiseDatabase_txtFileDoesNotExist_creationSuccess() {
        BudgetStorage budgetStorage = new BudgetStorage();
        try {
            budgetStorage.initialiseDatabase();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        assertEquals(0, budgetStorage.getBudget());
        assertEquals(0, budgetStorage.getAccommodationCost());
        assertEquals(0, budgetStorage.getAirplaneTicketCost());
        assertEquals(0, budgetStorage.getEntertainmentCost());
        assertEquals(0, budgetStorage.getFoodCost());
        f.delete();
    }

    void initialiseTestDatabase() {
        BudgetStorage budgetStorage = new BudgetStorage();
        budgetStorage.setBudget(TEST_INT);
        budgetStorage.setEntertainmentCost(TEST_INT);
        budgetStorage.setFoodCost(TEST_INT);
        budgetStorage.setAirplaneTicketCost(TEST_INT);
        budgetStorage.setAccommodationCost(TEST_INT);
    }

    @Test
    @Order(13)
    void initialiseDatabase_existingTxtFile_readSuccess() {
        initialiseTestDatabase();
        BudgetStorage budgetStorage = new BudgetStorage();
        assertEquals(TEST_INT, budgetStorage.getBudget());
        assertEquals(TEST_INT, budgetStorage.getEntertainmentCost());
        assertEquals(TEST_INT, budgetStorage.getFoodCost());
        assertEquals(TEST_INT, budgetStorage.getAccommodationCost());
        assertEquals(TEST_INT, budgetStorage.getAirplaneTicketCost());
        f.delete();
    }

    @Test
    @Order(3)
    void getAccommodationCost() {
        BudgetStorage budgetStorage = new BudgetStorage();
        assertEquals(0, budgetStorage.getAccommodationCost());
        f.delete();
    }

    @Test
    @Order(4)
    void getAirplaneTicketCost() {
        BudgetStorage budgetStorage = new BudgetStorage();
        assertEquals(0, budgetStorage.getAirplaneTicketCost());
        f.delete();
    }

    @Test
    @Order(5)
    void getFoodCost() {
        BudgetStorage budgetStorage = new BudgetStorage();
        assertEquals(0, budgetStorage.getFoodCost());
        f.delete();
    }

    @Test
    @Order(6)
    void getEntertainmentCost() {
        BudgetStorage budgetStorage = new BudgetStorage();
        assertEquals(0, budgetStorage.getEntertainmentCost());
        f.delete();
    }

    @Test
    @Order(7)
    void getBudget() {
        BudgetStorage budgetStorage = new BudgetStorage();
        assertEquals(0, budgetStorage.getBudget());
        f.delete();
    }

    @Test
    @Order(8)
    void setBudget() {
        BudgetStorage budgetStorage = new BudgetStorage();
        budgetStorage.setBudget(TEST_INT);
        assertEquals(TEST_INT, budgetStorage.getBudget());
        f.delete();
    }

    @Test
    @Order(9)
    void setAccommodationCost() {
        BudgetStorage budgetStorage = new BudgetStorage();
        budgetStorage.setAccommodationCost(TEST_INT);
        assertEquals(TEST_INT, budgetStorage.getAccommodationCost());
        f.delete();
    }

    @Test
    @Order(10)
    void setAirplaneTicketCost() {
        BudgetStorage budgetStorage = new BudgetStorage();
        budgetStorage.setAirplaneTicketCost(TEST_INT);
        assertEquals(TEST_INT, budgetStorage.getAirplaneTicketCost());
        f.delete();
    }

    @Test
    @Order(11)
    void setFoodCost() {
        BudgetStorage budgetStorage = new BudgetStorage();
        budgetStorage.setFoodCost(TEST_INT);
        assertEquals(TEST_INT, budgetStorage.getFoodCost());
        f.delete();
    }

    @Test
    @Order(12)
    void setEntertainmentCost() {
        BudgetStorage budgetStorage = new BudgetStorage();
        budgetStorage.setEntertainmentCost(TEST_INT);
        assertEquals(TEST_INT, budgetStorage.getEntertainmentCost());
        f.delete();
    }
}
