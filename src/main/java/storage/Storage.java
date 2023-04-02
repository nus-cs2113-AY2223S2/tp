package storage;

import com.google.gson.Gson;
import data.Expense;
import data.ExpenseList;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.EOFException;
import java.util.ArrayList;


// https://kodejava.org/how-do-i-store-objects-in-file/

public class Storage {

    private static final String READ_STORAGE_SUCCESSFUL = "All past expenses retrieved successfully!";
    private static final String READ_EXPENSELIST_ERROR = "Error reading expense list.";
    private static final String WRITE_TO_EXPENSELIST_ERROR = "Error writing to expense list.";
    private static final String CREATE_FILE_ERROR = "Error creating file.";
    private static final String INITIAL_WELCOME_MESSAGE = "";
    private static final String NEW_EXPENSE = "New expense list created.";
    private static final Gson GSON = new Gson();

    private ExpenseList expenseList;
    private ArrayList<Expense> expenses;

    public Storage(ArrayList<Expense> expenses) {
        this.expenses = expenses;
    }

    public Storage(ExpenseList expenseList) {
        this.expenseList = expenseList;
    }

    //json
    public void saveExpenses(ArrayList<Expense> expenses, String filePath) {
        File file = new File(filePath);
        try {
            file.createNewFile();
        } catch (IOException e) {
            System.out.println("Error creating account file.");
        }

        try {
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(GSON.toJson(expenses));
        } catch (IOException e) {
            System.out.println("Error writing to account file");
        }
    }

    public ArrayList<Expense> loadExpenses(String filePath) {
        ArrayList<Expense> expenses = new ArrayList<>();
        return expenses;
    }


    public ExpenseList getExpenseList() {
        return expenseList;
    }

    /**
     * Saves ExpenseList objects to dat file.
     */
    public void saveExpenseList() {
        try {
            FileOutputStream fos = new FileOutputStream("expenselist.dat");
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(expenseList);
        } catch (IOException e) {
            System.out.println(WRITE_TO_EXPENSELIST_ERROR);
        }
    }

    public boolean createFile() {
        try {
            File f = new File("expenselist.dat");
            if (f.createNewFile()) {
                // first time that the programme is being run, update welcome message later on
                System.out.println(INITIAL_WELCOME_MESSAGE);
                return true;
            }
        } catch (IOException e) {
            System.out.println(CREATE_FILE_ERROR);
        }
        return false;
    }

    /**
     * Reads ExpenseList object from dat file and store as expenseList.
     */

    public ExpenseList initialiseExpenseList() {
        try {
            boolean isFileCreated = createFile();
            if (isFileCreated) {
                expenseList = new ExpenseList();
                System.out.println("File created");
            } else {
                FileInputStream fis = new FileInputStream("expenselist.dat");
                ObjectInputStream ois = new ObjectInputStream(fis);
                expenseList = (ExpenseList) ois.readObject();
                System.out.println(READ_STORAGE_SUCCESSFUL);
            }
        } catch (EOFException e) {
            System.out.print(NEW_EXPENSE);
            expenseList = new ExpenseList();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(READ_EXPENSELIST_ERROR);
        }
        return expenseList;
    }


    public void clearContent() {
        try {
            FileOutputStream fos = new FileOutputStream("expenselist.dat");
            fos.close();
        } catch (IOException e) {
            System.out.println(WRITE_TO_EXPENSELIST_ERROR);
        }
    }

}
