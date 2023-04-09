package chching;

import chching.record.ExpenseList;
import chching.record.Expense;
import chching.record.IncomeList;
import chching.record.Income;
import chching.record.Record;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.FileHandler;
import java.util.logging.LogManager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonElement;

/**
 * Models a class to handle storage for the program.
 */
public class Storage {
    public static final String TYPE_PROPERTY = "type";
    public static final String CATEGORY_PROPERTY = "category";
    public static final String DESCRIPTION_PROPERTY = "description";
    public static final String DATE_PROPERTY = "date";
    public static final String AMOUNT_PROPERTY = "amount";
    public static final String EXPENSE_SYMBOL = "E";
    public static final String INCOME_SYMBOL = "I";
    private static final Logger logger = Logger.getLogger(ChChing.class.getName());
    
    static {
        LogManager.getLogManager().reset();
        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setLevel(Level.SEVERE);
        logger.addHandler(consoleHandler);
        logger.setLevel(Level.ALL);
        try {
            new File("data/StorageLog.log").createNewFile();
            FileHandler fileHandler = new FileHandler("data/StorageLog.log");
            fileHandler.setLevel(Level.FINE);
            logger.addHandler(fileHandler);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "File logger not working.", e);
        }
    }

    private final File file;

    /**
     * Build constructor for the Storage class.
     *
     * @param filepath the filepath of the storage.
     */
    Storage(String filepath) {
        String dirname = filepath.substring(0, filepath.lastIndexOf("/"));
        File dir = new File(dirname);
        dir.mkdirs();
        this.file = new File(filepath);
    }

    public ArrayList<Income> loadIncomes() {
        ArrayList<Income> incomes = new ArrayList<>();

        try {
            Gson gson = new Gson();

            String json = Files.readString(file.toPath());

            JsonArray entries = gson.fromJson(json, JsonArray.class);

            for (JsonElement entry : entries) {
                JsonObject jsonObj = entry.getAsJsonObject();
                String type = jsonObj.get(TYPE_PROPERTY).getAsString();
                if (type.equals(INCOME_SYMBOL)) {
                    String description = jsonObj.get(DESCRIPTION_PROPERTY).getAsString();
                    LocalDate date = LocalDate.parse(jsonObj.get(DATE_PROPERTY).getAsString());
                    double amount = jsonObj.get(AMOUNT_PROPERTY).getAsDouble();
                    Income income = new Income(description, date, amount);
                    incomes.add(income);
                }
            }
        } catch (IOException e) {
            System.out.println("Unfortunately, income list file can't be found. I'll make a new one!");
            logger.info("Loading incomes failed");
        }
        logger.info("Successfully loaded incomes");
        return incomes;

    }

    public ArrayList<Expense> loadExpenses() {
        ArrayList<Expense> expenses = new ArrayList<>();

        try {
            Gson gson = new Gson();

            String json = Files.readString(file.toPath());

            JsonArray entries = gson.fromJson(json, JsonArray.class);

            for (JsonElement entry : entries) {
                JsonObject jsonObj = entry.getAsJsonObject();
                String type = jsonObj.get(TYPE_PROPERTY).getAsString();
                if (type.equals(EXPENSE_SYMBOL)) {
                    String description = jsonObj.get(DESCRIPTION_PROPERTY).getAsString();
                    String category = jsonObj.get(CATEGORY_PROPERTY).getAsString();
                    LocalDate date = LocalDate.parse(jsonObj.get(DATE_PROPERTY).getAsString());
                    double amount = jsonObj.get(AMOUNT_PROPERTY).getAsDouble();
                    Expense expense = new Expense(category, description, date, amount);
                    expenses.add(expense);
                }
            }
        } catch (IOException e) {
            System.out.println("Unfortunately, expense list file can't be found. I'll make a new one!");
            logger.info("Loading expenses failed");
        }
        logger.info("Successfully loaded expenses");
        return expenses;
    }

    public void save(IncomeList incomes, ExpenseList expenses) {
        JsonArray jsonArray = new JsonArray();

        for (int i = 0; i < incomes.size(); i++) {
            Record income = incomes.get(i);

            JsonObject incomeObj = new JsonObject();
            incomeObj.addProperty(TYPE_PROPERTY, INCOME_SYMBOL);
            incomeObj.addProperty(DESCRIPTION_PROPERTY, income.getDescription());
            incomeObj.addProperty(DATE_PROPERTY, income.getDate().toString());
            incomeObj.addProperty(AMOUNT_PROPERTY, income.getValue());

            jsonArray.add(incomeObj);
        }

        for (int i = 0; i < expenses.size(); i++) {
            Record expense = expenses.get(i);

            JsonObject expenseObj = new JsonObject();
            expenseObj.addProperty(TYPE_PROPERTY, EXPENSE_SYMBOL);
            expenseObj.addProperty(CATEGORY_PROPERTY, expense.getCategory());
            expenseObj.addProperty(DESCRIPTION_PROPERTY, expense.getDescription());
            expenseObj.addProperty(DATE_PROPERTY, expense.getDate().toString());
            expenseObj.addProperty(AMOUNT_PROPERTY, expense.getValue());

            jsonArray.add(expenseObj);
        }

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonString = gson.toJson(jsonArray);

        // write the JSON string to a file
        try (FileWriter file = new FileWriter(this.file)) {
            file.write(jsonString);

        } catch (IOException e) {
            System.out.println("An error occurred while writing JSON data to file.");
            logger.info("Error saving data");
            e.printStackTrace();
        }
    }
}

