package seedu.duke.budget;

import seedu.duke.DatabaseInterface;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class BudgetStorage implements DatabaseInterface {

    private static final String SAVED_BUDGET_FILE_PATH = "data/budget.txt";

    private Accommodation accommodation;
    private AirplaneTicket airplaneTicket;
    private Food food;
    private Entertainment entertainment;

    private int budget;

    public BudgetStorage() {
        try {
            initialiseDatabase();
        } catch (IOException e) {
            System.out.println("Initialise Budget Failure");
        }
    }

    @Override
    public void initialiseDatabase() throws IOException {
        File savedModulesFile = new File(SAVED_BUDGET_FILE_PATH);
        if (!savedModulesFile.exists()) {
            File directory = new File("data");
            directory.mkdirs();
            savedModulesFile.createNewFile();
            budget = 0;
            accommodation = new Accommodation(0);
            airplaneTicket = new AirplaneTicket(0);
            food = new Food(0);
            entertainment = new Entertainment(0);
            updateBudgetStorage();
            return;
        }
        readBudgetData();
    }

    private void readBudgetData() {
        try (BufferedReader br = new BufferedReader(new FileReader(SAVED_BUDGET_FILE_PATH))) {
            String line;
            ArrayList<String> costs = new ArrayList<>();
            while ((line = br.readLine()) != null) {
                costs.add(line);
            }
            budget = Integer.parseInt(costs.get(0));
            accommodation = new Accommodation(Integer.parseInt(costs.get(1)));
            airplaneTicket = new AirplaneTicket(Integer.parseInt(costs.get(2)));
            food = new Food(Integer.parseInt(costs.get(3)));
            entertainment = new Entertainment(Integer.parseInt(costs.get(4)));
        } catch (Exception e) {
            e.printStackTrace();
        }
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
