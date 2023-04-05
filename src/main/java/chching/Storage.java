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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonElement;

/**
 * Models a class to handle storage for the program.
 */
public class Storage {
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
            // Create a GSON object
            Gson gson = new Gson();

            // Read the JSON file into a string
            String json = Files.readString(file.toPath());

            // Parse the JSON string into a JsonArray
            JsonArray entries = gson.fromJson(json, JsonArray.class);

            // Loop through the entries and add each income to the list
            for (JsonElement entry : entries) {
                JsonObject jsonObj = entry.getAsJsonObject();
                String type = jsonObj.get("type").getAsString();
                if (type.equals("I")) {
                    String description = jsonObj.get("description").getAsString();
                    LocalDate date = LocalDate.parse(jsonObj.get("date").getAsString());
                    double amount = jsonObj.get("amount").getAsDouble();
                    Income income = new Income(description, date, amount);
                    incomes.add(income);
                }
            }
        } catch (IOException e) {
            System.out.println("Unfortunately, income list file can't be found. I'll make a new one!");
        }

        return incomes;
    }

    public ArrayList<Expense> loadExpenses() {
        ArrayList<Expense> expenses = new ArrayList<>();

        try {
            // Create a GSON object
            Gson gson = new Gson();

            // Read the JSON file into a string
            String json = Files.readString(file.toPath());

            // Parse the JSON string into a JsonArray
            JsonArray entries = gson.fromJson(json, JsonArray.class);

            // Loop through the entries and add each income to the list
            for (JsonElement entry : entries) {
                JsonObject jsonObj = entry.getAsJsonObject();
                String type = jsonObj.get("type").getAsString();
                if (type.equals("E")) {
                    String description = jsonObj.get("description").getAsString();
                    String category = jsonObj.get("category").getAsString();
                    LocalDate date = LocalDate.parse(jsonObj.get("date").getAsString());
                    double amount = jsonObj.get("amount").getAsDouble();
                    Expense expense = new Expense(category, description, date, amount);
                    expenses.add(expense);
                }
            }
        } catch (IOException e) {
            System.out.println("Unfortunately, expense list file can't be found. I'll make a new one!");
        }

        return expenses;
    }

    public void save(IncomeList incomes, ExpenseList expenses) {
        JsonArray jsonArray = new JsonArray();

        for (int i = 0; i < incomes.size(); i++) {
            Record income = incomes.get(i);

            JsonObject incomeObj = new JsonObject();
            incomeObj.addProperty("type", "I");
            incomeObj.addProperty("description", income.getDescription());
            incomeObj.addProperty("date", income.getDate().toString());
            incomeObj.addProperty("amount", income.getValue());

            jsonArray.add(incomeObj);
        }

        for (int i = 0; i < expenses.size(); i++) {
            Record expense = expenses.get(i);

            JsonObject expenseObj = new JsonObject();
            expenseObj.addProperty("type", "E");
            expenseObj.addProperty("category", expense.getCategory());
            expenseObj.addProperty("description", expense.getDescription());
            expenseObj.addProperty("date", expense.getDate().toString());
            expenseObj.addProperty("amount", expense.getValue());

            jsonArray.add(expenseObj);
        }

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonString = gson.toJson(jsonArray);

        // write the JSON string to a file
        try (FileWriter file = new FileWriter(this.file)) {
            file.write(jsonString);

        } catch (IOException e) {
            System.out.println("An error occurred while writing JSON data to file.");
            e.printStackTrace();
        }
    }
}

