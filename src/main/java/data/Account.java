package data;

import java.util.ArrayList;

public class Account {
    protected String accountName;
    protected ArrayList<Expense> account;
    protected int accountSize;
    private String password;
    public static int accountNumber = 1;

    public Account(String accountName, String password) {
        this.account = new ArrayList<>();
        accountSize = 0;
        this.accountName = accountName;
        this.password = password;
        accountNumber++;
        System.out.printf("User %s has been created\n", accountName);
    }

    public int getAccountSize() {
        return this.accountSize;
    }

    public String getAccountName() {
        return this.accountName;
    }

    public Expense getExpense(int index) {
        return account.get(index);
    }

    public String getPassword() { return this.password; }

    public static void login(String accountName, String password) {
        for (int i = 1; i <= accountNumber; i++)
            System.out.println("Enter 'login' if you have an existing account or 'sign up' if you have not done so.");
    }

}


