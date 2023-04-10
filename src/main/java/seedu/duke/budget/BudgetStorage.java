package seedu.duke.budget;

import seedu.duke.DatabaseInterface;
import seedu.duke.UI;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class BudgetStorage implements DatabaseInterface {

    private static final String SAVED_BUDGET_FILE_PATH = "data/budget.txt";
    private static BudgetStorage instance = null;
    private Accommodation accommodation;
    private AirplaneTicket airplaneTicket;
    private Food food;
    private Entertainment entertainment;

    private int budget;

    /**
     * Constructor of BudgetStorage that creates a new BudgetStorage class
     */
    private BudgetStorage() {
        try {
            initialiseDatabase();
        } catch (IOException e) {
            System.out.println("Initialise Budget Failure");
        }
    }

    /**
     * Returns the instance of the BudgetStorage object.
     * Creates a new instance of BudgetStorage if it has never been created before, else it will return the current
     * instance of the BudgetStorage.
     * Follows Singleton pattern.
     *
     * @return a BudgetStorage object.
     */
    public static BudgetStorage getInstance() {
        if (instance == null) {
            instance = new BudgetStorage();
        }
        return instance;
    }

    /**
     * Initialises the BudgetStorage database by retrieving file information from the txt file
     *
     * @throws IOException when there is an error accessing the savedModulesFile
     */
    @Override
    public void initialiseDatabase() throws IOException {
        File savedModulesFile = new File(SAVED_BUDGET_FILE_PATH);
        if (!savedModulesFile.exists()) {
            File directory = new File("data");
            directory.mkdirs();
            savedModulesFile.createNewFile();
            setBaseBudget();
            updateBudgetStorage();
            return;
        }
        readBudgetData();
    }

    private void setBaseBudget() {
        budget = 0;
        accommodation = new Accommodation(0);
        airplaneTicket = new AirplaneTicket(0);
        food = new Food(0);
        entertainment = new Entertainment(0);
    }

    /**
     * Returns true if budget storage is corrupted and when there is tampering of the txt file.
     * Returns false if the budget storage is not corrupted and the values are within the set amount.
     *
     * @return a boolean value, either true or false if the values in the txt file are within acceptable range.
     */
    @Override
    public boolean checkDatabaseCorrupted() {
        if (budget < 0 || budget > BudgetPlanner.MAX_BUDGET) {
            return true;
        }
        if (accommodation.getPrice() < 0 || accommodation.getPrice() > BudgetPlanner.MAX_BUDGET) {
            return true;
        }
        if (airplaneTicket.getPrice() < 0 || airplaneTicket.getPrice() > BudgetPlanner.MAX_BUDGET) {
            return true;
        }
        if (entertainment.getPrice() < 0 || entertainment.getPrice() > BudgetPlanner.MAX_BUDGET) {
            return true;
        }
        if (food.getPrice() < 0 || food.getPrice() > BudgetPlanner.MAX_BUDGET) {
            return true;
        }
        return false;
    }

    /**
     * Reads the Budget Data from the txt file and initialises the value for budget, accommodation, airplaneTicket,
     * food and entertainment. Checks for txt file tampering too, where reset will happen if that are values not within
     * the range of acceptable budget.
     */
    private void readBudgetData() {
        try (BufferedReader br = new BufferedReader(new FileReader(SAVED_BUDGET_FILE_PATH))) {
            String line;
            ArrayList<String> costs = new ArrayList<>();
            while ((line = br.readLine()) != null) {
                costs.add(line);
            }
            if (costs.size() != 5) {
                corruptBudgetFixProcedure();
                return;
            }
            try {
                budget = Integer.parseInt(costs.get(0));
                accommodation = new Accommodation(Integer.parseInt(costs.get(1)));
                airplaneTicket = new AirplaneTicket(Integer.parseInt(costs.get(2)));
                food = new Food(Integer.parseInt(costs.get(3)));
                entertainment = new Entertainment(Integer.parseInt(costs.get(4)));
            } catch (NumberFormatException e) {
                corruptBudgetFixProcedure();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        boolean isBudgetDataCorrupted = checkDatabaseCorrupted();
        if (isBudgetDataCorrupted) {
            corruptBudgetFixProcedure();
        }
    }

    /**
     * Resets the budgetStorage to 0 for all budgets.
     */
    private void corruptBudgetFixProcedure() {
        UI.printBudgetStorageCorruptedMessage();
        setBaseBudget();
        updateBudgetStorage();
    }

    /**
     * Updates the txt file by rewriting the txt file.
     */
    private void updateBudgetStorage() {
        try {
            writeListToFile();
        } catch (IOException e) {
            System.out.println("Unable to save to database");
        }
    }

    /**
     * Writes the list of format to the txt file.
     *
     * @throws IOException when the file path does not exist even though there is writing to the txt file
     */
    private void writeListToFile() throws IOException {
        FileWriter fw = new FileWriter(SAVED_BUDGET_FILE_PATH);
        String stringToAdd = "";
        stringToAdd += writeTaskPreparation(integerToString(budget));
        stringToAdd += writeTaskPreparation(integerToString(getAccommodationCost()));
        stringToAdd += writeTaskPreparation(integerToString(getAirplaneTicketCost()));
        stringToAdd += writeTaskPreparation(integerToString(getFoodCost()));
        stringToAdd += writeTaskPreparation(integerToString(getEntertainmentCost()));
        fw.write(stringToAdd);
        fw.close();
    }

    /**
     * Returns the accommodation cost
     *
     * @return an integer value for the accommodation cost
     */
    public int getAccommodationCost() {
        return accommodation.getPrice();
    }

    /**
     * Returns the airplane ticket cost
     *
     * @return an integer value for the airplane ticket cost
     */
    public int getAirplaneTicketCost() {
        return airplaneTicket.getPrice();
    }

    /**
     * Returns the food cost
     *
     * @return an integer value for the food cost
     */
    public int getFoodCost() {
        return food.getPrice();
    }

    /**
     * Returns the entertainment cost
     *
     * @return an integer value for the entertainment cost
     */
    public int getEntertainmentCost() {
        return entertainment.getPrice();
    }

    /**
     * Returns the budget
     *
     * @return an integer value for the budget
     */
    public int getBudget() {
        return budget;
    }

    /**
     * Sets the new Budget
     *
     * @param budget the value of the budget to set
     */
    public void setBudget(int budget) {
        this.budget = budget;
        updateBudgetStorage();
    }

    /**
     * Sets the Accommodation Cost
     *
     * @param cost the value of the accommodation to set
     */
    public void setAccommodationCost(int cost) {
        accommodation.setPrice(cost);
        updateBudgetStorage();
    }

    /**
     * Sets the Airplane Ticket Cost
     *
     * @param cost the value of the airplane ticket to set
     */
    public void setAirplaneTicketCost(int cost) {
        airplaneTicket.setPrice(cost);
        updateBudgetStorage();
    }

    /**
     * Sets the Food Cost
     *
     * @param cost the value of the food to set
     */
    public void setFoodCost(int cost) {
        food.setPrice(cost);
        updateBudgetStorage();
    }

    /**
     * Sets the Entertainment Cost
     *
     * @param cost the value of the entertainment to set
     */
    public void setEntertainmentCost(int cost) {
        entertainment.setPrice(cost);
        updateBudgetStorage();
    }

    private String integerToString(int integer) {
        try {
            return Integer.toString(integer);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "0";
    }
}
