package seedu.duke.budget;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.Order;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BudgetStorageTest {

    private static final int TEST_INT = 12;
    private static final String BUDGET_PATH = "data/budget.txt";
    File f = new File(BUDGET_PATH);

    @BeforeEach
    public void setBudgetPlanner() {
        BudgetPlanner budgetPlanner = BudgetPlanner.getInstance();
        budgetPlanner.setBudget(0);
        budgetPlanner.setFoodTotalCost(0);
        budgetPlanner.setAccommodationTotalCost(0);
        budgetPlanner.setEntertainmentTotalCost(0);
        budgetPlanner.setAirplaneTicketTotalCost(0);
    }

    @Test
    @Order(1)
    void initialiseDatabase_txtFileDoesNotExist_catchIOExceptionSuccess() {
        BudgetStorage budgetStorage = BudgetStorage.getInstance();
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
        f.delete();
        BudgetStorage budgetStorage = BudgetStorage.getInstance();
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
        BudgetStorage budgetStorage = BudgetStorage.getInstance();
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
        BudgetStorage budgetStorage = BudgetStorage.getInstance();
        assertEquals(TEST_INT, budgetStorage.getBudget());
        assertEquals(TEST_INT, budgetStorage.getEntertainmentCost());
        assertEquals(TEST_INT, budgetStorage.getFoodCost());
        assertEquals(TEST_INT, budgetStorage.getAccommodationCost());
        assertEquals(TEST_INT, budgetStorage.getAirplaneTicketCost());
        f.delete();
    }

    @Test
    @Order(14)
    void initialiseDatabase_corruptFile_readSuccess() {
        f.delete();
        try {
            FileWriter fw = new FileWriter(BUDGET_PATH);
            fw.write("-1\n200000000000\nasdf\n120");
        } catch (IOException e) {
            fail("Filewriter creation failed");
        }
        BudgetStorage budgetStorage = BudgetStorage.getInstance();
        assertEquals(0, budgetStorage.getBudget());
        assertEquals(0, budgetStorage.getEntertainmentCost());
        assertEquals(0, budgetStorage.getFoodCost());
        assertEquals(0, budgetStorage.getAccommodationCost());
        assertEquals(0, budgetStorage.getAirplaneTicketCost());
        f.delete();
    }

    @Test
    @Order(15)
    void initialiseDatabase_corruptFileReset_readSuccess() {
        f.delete();
        try {
            FileWriter fw = new FileWriter(BUDGET_PATH);
            fw.write("");
            fw.close();
        } catch (IOException e) {
            fail("Filewriter creation failed");
        }
        BudgetStorage budgetStorage = BudgetStorage.getInstance();
        assertEquals(0, budgetStorage.getBudget());
        assertEquals(0, budgetStorage.getEntertainmentCost());
        assertEquals(0, budgetStorage.getFoodCost());
        assertEquals(0, budgetStorage.getAccommodationCost());
        assertEquals(0, budgetStorage.getAirplaneTicketCost());
        f.delete();
    }
    @Test
    @Order(3)
    void getAccommodationCost_initialiseZero_success() {
        BudgetStorage budgetStorage = BudgetStorage.getInstance();
        assertEquals(0, budgetStorage.getAccommodationCost());
        f.delete();
    }

    @Test
    @Order(4)
    void getAirplaneTicketCost_initialiseZero_success() {
        BudgetStorage budgetStorage = BudgetStorage.getInstance();
        assertEquals(0, budgetStorage.getAirplaneTicketCost());
        f.delete();
    }

    @Test
    @Order(5)
    void getFoodCost_initialiseZero_success() {
        BudgetStorage budgetStorage = BudgetStorage.getInstance();
        assertEquals(0, budgetStorage.getFoodCost());
        f.delete();
    }

    @Test
    @Order(6)
    void getEntertainmentCost_initialiseZero_success() {
        BudgetStorage budgetStorage = BudgetStorage.getInstance();
        assertEquals(0, budgetStorage.getEntertainmentCost());
        f.delete();
    }

    @Test
    @Order(7)
    void getBudget_initialiseZero_success() {
        BudgetStorage budgetStorage = BudgetStorage.getInstance();
        assertEquals(0, budgetStorage.getBudget());
        f.delete();
    }

    @Test
    @Order(8)
    void setBudget_correctInteger_success() {
        BudgetStorage budgetStorage = BudgetStorage.getInstance();
        budgetStorage.setBudget(TEST_INT);
        assertEquals(TEST_INT, budgetStorage.getBudget());
        f.delete();
    }

    @Test
    @Order(9)
    void setAccommodationCost_correctInteger_success() {
        BudgetStorage budgetStorage = BudgetStorage.getInstance();
        budgetStorage.setAccommodationCost(TEST_INT);
        assertEquals(TEST_INT, budgetStorage.getAccommodationCost());
        f.delete();
    }

    @Test
    @Order(10)
    void setAirplaneTicketCost_correctInteger_success() {
        BudgetStorage budgetStorage = BudgetStorage.getInstance();
        budgetStorage.setAirplaneTicketCost(TEST_INT);
        assertEquals(TEST_INT, budgetStorage.getAirplaneTicketCost());
        f.delete();
    }

    @Test
    @Order(11)
    void setFoodCost_correctInteger_success() {
        BudgetStorage budgetStorage = BudgetStorage.getInstance();
        budgetStorage.setFoodCost(TEST_INT);
        assertEquals(TEST_INT, budgetStorage.getFoodCost());
        f.delete();
    }

    @Test
    @Order(12)
    void setEntertainmentCost_correctInteger_success() {
        BudgetStorage budgetStorage = BudgetStorage.getInstance();
        budgetStorage.setEntertainmentCost(TEST_INT);
        assertEquals(TEST_INT, budgetStorage.getEntertainmentCost());
        f.delete();
    }
}
