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


    private int Budget;

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
            Budget = Integer.parseInt(costs.get(0));
            accommodation = new Accommodation(Integer.parseInt(costs.get(1)));
            airplaneTicket = new AirplaneTicket(Integer.parseInt(costs.get(2)));
            food = new Food(Integer.parseInt(costs.get(3)));
            entertainment = new Entertainment(Integer.parseInt(costs.get(4)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void writeListToFile() throws IOException {
        FileWriter fw = new FileWriter(SAVED_BUDGET_FILE_PATH);
        String stringToAdd = "";
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
        return Budget;
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
