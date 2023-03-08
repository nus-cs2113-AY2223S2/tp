package MajorClasses;

public class Expense {
    protected double expenseAmount;
    protected Time expenseTime;
    protected String description;

    public Expense() {
        this.expenseAmount = 0;
    }

    public Expense(int expenseAmount, Time expenseTime, String description) {
        this.expenseAmount = expenseAmount;
        this.expenseTime = expenseTime;
        this.description = description;
    }


    public double getExpenseAmount() {
        return expenseAmount;
    }

    public Time getExpenseTime() {
        return expenseTime;
    }

    public String getDescription() {
        return description;
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

    public void printTask() {
        System.out.println(this.expenseAmount + " ");
        System.out.println(this.expenseTime + " " + this.description);
    }
}

