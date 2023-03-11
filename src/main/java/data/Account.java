package data;

import java.util.ArrayList;

public class Account {
    protected String accountName;
    protected ArrayList<Expense> account;
    protected int accountSize;

    public Account(String accountName) {
        this.account = new ArrayList<>();
        accountSize = 0;
        this.accountName = accountName;
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

}


