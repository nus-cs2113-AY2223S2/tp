package storage;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import data.Expense;
import data.ExpenseList;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Objects;


// https://kodejava.org/how-do-i-store-objects-in-file/

public class Storage {

    private static final String READ_STORAGE_SUCCESSFUL = "All past expenses retrieved successfully!";
    private static final String READ_EXPENSELIST_ERROR = "Error reading expense list.";
    private static final String WRITE_TO_EXPENSELIST_ERROR = "Error writing to expense list.";
    private static final String NEW_EXPENSE = "New expense list created.";
    private static final String WRITING_TO_FILE_ERROR = "Error writing to account file";
    private static final String INITIAL_WELCOME_MESSAGE = "Welcome to Duke!";
    private static final String SUBSEQUENT_WELCOME_MESSAGE = "Welcome back!";
    private static final String DATA_CORRUPTED_ERROR = "Data file corrupted. " +
            "Save the remaining data to another location before deleting the current data file. " +
            "Restart the programme after deleting the corrupted data file to proceed.";
    private static final String CREATE_FILE_ERROR = "Error creating file.";
    private static final Gson GSON = new Gson();

    private ExpenseList expenseList;


    public Storage(ExpenseList expenseList) {
        this.expenseList = expenseList;
    }

    /**
     * Saves expenses to json file as json object.
     *
     * @param filePath Path at which the json file is stored.
     */
    public void saveExpenses(String filePath) {
        try {
            File file = new File(filePath);
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(GSON.toJson(expenseList.getExpenseList()));
            fileWriter.close();
        } catch (IOException e) {
            System.out.println(WRITING_TO_FILE_ERROR);
        }
    }


    /**
     * Creates JSON file if it does not exist with user specified file path.
     *
     * @param filePath Path at which the json file is stored.
     */
    private void createFile(String filePath) {
        try {
            File f = new File(filePath);
            if (f.createNewFile()) {
                // first time that the programme is being run, update welcome message later on
                System.out.println(INITIAL_WELCOME_MESSAGE);
            } else {
                System.out.println(SUBSEQUENT_WELCOME_MESSAGE);
            }
        } catch (IOException e) {
            System.out.println(CREATE_FILE_ERROR);
        }
    }

    //TODO: JSON
    /**
     * Loads expenses from json file and save as an ArrayList of Expenses
     * @param filePath Path at which the json file is stored.
     *
     * @return An arraylist of expenses.
     * @throws NullPointerException if json file is empty.
     */

    public ArrayList<Expense> loadExpenses(String filePath) throws NullPointerException {
        ArrayList<Expense> expenses = new ArrayList<>();
        createFile(filePath);
        try {
            Reader reader = Files.newBufferedReader(Paths.get(filePath));
            Type typeOfObject = new TypeToken<ArrayList<Expense>>() {}.getType();
            expenses = GSON.fromJson(reader, typeOfObject);
        } catch (JsonSyntaxException e) {
            System.out.println(DATA_CORRUPTED_ERROR);
            System.exit(0);
        } catch (IOException e) {
            System.out.println(CREATE_FILE_ERROR);
        }

        return Objects.requireNonNullElseGet(expenses, ArrayList::new);
    }

    public ExpenseList getExpenseList() {
        return expenseList;
    }

}
