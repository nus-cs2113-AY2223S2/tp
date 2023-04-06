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

    private BudgetStorage() {
        try {
            initialiseDatabase();
        } catch (IOException e) {
            System.out.println("Initialise Budget Failure");
        }
    }

    public static BudgetStorage getInstance() {
        if (instance == null) {
            instance = new BudgetStorage();
        }
        return instance;
    }

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

    private void corruptBudgetFixProcedure() {
        UI.printBudgetStorageCorruptedMessage();
        setBaseBudget();
        updateBudgetStorage();
    }

    private void updateBudgetStorage() {
        try {
            writeListToFile();
        } catch (IOException e) {
            System.out.println("Unable to save to database");
        }
    }

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

    public int getAccommodationCost() {
        return accommodation.getPrice();
    }

    public int getAirplaneTicketCost() {
        return airplaneTicket.getPrice();
    }

    public int getFoodCost() {
        return food.getPrice();
    }

    public int getEntertainmentCost() {
        return entertainment.getPrice();
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
        updateBudgetStorage();
    }

    public void setAccommodationCost(int cost) {
        accommodation.setPrice(cost);
        updateBudgetStorage();
    }

    public void setAirplaneTicketCost(int cost) {
        airplaneTicket.setPrice(cost);
        updateBudgetStorage();
    }

    public void setFoodCost(int cost) {
        food.setPrice(cost);
        updateBudgetStorage();
    }

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
