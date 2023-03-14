package data;

import java.util.Objects;

public class Expense {
    protected double expenseAmount;
    protected Time expenseTime;
    protected String description;
    protected Currency currencyType;

    public Expense() {
        this.expenseAmount = 0;
    }

    public Expense(double expenseAmount, Time expenseTime, String description, Currency currencyType) {
        this.expenseAmount = expenseAmount;
        this.expenseTime = expenseTime;
        this.description = description;
        this.currencyType = currencyType;
    }

    public double getExpenseAmount() {
        return expenseAmount;
    }

    public String getExpenseTime() {
        return expenseTime.toString();
    }

    public String getDescription() {
        return description;
    }

    public Currency getCurrencyType() {
        return currencyType;
    }

    // The setter method will be used if the User want to change some information in their previous expense
    public void setExpenseAmount(double expenseAmount) {
        this.expenseAmount = expenseAmount;
    }

    public void setExpenseTime(Time expenseTime) {
        this.expenseTime = expenseTime;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        return Objects.equals(this.getDescription(), ((Expense) obj).getDescription())
                && this.getExpenseAmount() == ((Expense) obj).getExpenseAmount()
                && Objects.equals(this.getExpenseTime(), ((Expense) obj).getExpenseTime())
                && Objects.equals(this.getCurrencyType(), ((Expense) obj).getCurrencyType());
    }

    /**
     * Override print method to print the information of an expense in a standard format
     */
    @Override
    public String toString() {
        // Currently not useful
        String currencyString = Currency.returnCurrency(this.currencyType);
        String amountString = Double.toString(this.expenseAmount);
        String descriptionString = this.description;
        String timeString = this.expenseTime.toString();
        return (currencyString + amountString + " cat:" + descriptionString + " date:" + timeString);
    }

    public void printExpense() {
        String currencyString = Currency.returnCurrency(this.currencyType);
        String descriptionString = this.description;
        String timeString = this.expenseTime.toString();

        System.out.print(currencyString);
        printExpenseAmount();
        System.out.println(" cat:" + descriptionString + " date:" + timeString);
    }

    /**
     * To standardize the printed expense amount to be 2 decimal places
     * If the number is integer or only 1 digit, then no change
     */
    public void printExpenseAmount() {
        String expenseAmountString = Double.toString(expenseAmount);
        int digitNumbers = expenseAmountString.length() - expenseAmountString.indexOf(".") - 1;
        if (digitNumbers == 1) {
            // No digit
            if (expenseAmountString.endsWith("0")) {
                System.out.print(expenseAmountString.substring(0,expenseAmountString.length() - 2));
            } else {
                System.out.print(expenseAmountString);
            }
        } else {
            double expenseAmountTwoDecimal = Math.round(this.expenseAmount * 100.0) / 100.0;
            System.out.printf("%.2f", expenseAmountTwoDecimal);
        }
    }
}

