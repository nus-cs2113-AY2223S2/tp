package storage;

import data.Account;
import data.ExpenseList;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

// https://kodejava.org/how-do-i-store-objects-in-file/

public class Storage {

    private static final String READ_STORAGE_SUCCESSFUL = "All past expenses retrieved successfully!";
    private static final String READ_EXPENSELIST_ERROR = "Error reading expense list.";
    private static final String WRITE_TO_EXPENSELIST_ERROR = "Error writing to expense list.";

    private ExpenseList expenseList;
    private Account account;


    public Storage(ExpenseList expenseList) {
        this.expenseList = expenseList;
    }

    public Storage(Account account) {
        this.account = account;
    }

    public Account getAccount() {
        return account;
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

    /**
     * Reads ExpenseList object from dat file and store as expenseList.
     */

    public ExpenseList initialiseExpenseList() {
        try {
            FileInputStream fis = new FileInputStream("expenselist.dat");
            ObjectInputStream ois = new ObjectInputStream(fis);
            expenseList = (ExpenseList) ois.readObject();
            System.out.println(READ_STORAGE_SUCCESSFUL);

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
