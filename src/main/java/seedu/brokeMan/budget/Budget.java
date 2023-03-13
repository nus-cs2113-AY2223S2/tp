package seedu.brokeMan.budget;

public class Budget {
    private double budget;
    public Budget(double budget) {
        this.budget = budget;
    }

    public void viewBudget() {
        System.out.println("You have " + this.budget + " left.");
    }
    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
        System.out.println("You have successfully set " + budget + " as your budget.");
    }
}
