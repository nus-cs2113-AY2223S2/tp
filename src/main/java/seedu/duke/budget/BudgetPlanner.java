package seedu.duke.budget;

public class BudgetPlanner {
    
    public static final int MAX_BUDGET = 20000000;
    private int budget;
    private int surplus;
    private int accommodationTotalCost;
    private int airplaneTicketTotalCost;
    private int foodTotalCost;
    private int entertainmentTotalCost;

    public BudgetPlanner() {
        budget = 0;
        initialiseCost();
        updateSurplus();
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    private void initialiseCost() {
        BudgetStorage budgetStorage = new BudgetStorage();
        budget = budgetStorage.getBudget();
        accommodationTotalCost = budgetStorage.getAccommodationCost();
        airplaneTicketTotalCost = budgetStorage.getAirplaneTicketCost();
        foodTotalCost = budgetStorage.getFoodCost();
        entertainmentTotalCost = budgetStorage.getEntertainmentCost();
    }

    private int getTotalCost() {
        return accommodationTotalCost + airplaneTicketTotalCost + foodTotalCost + entertainmentTotalCost;
    }

    private void updateSurplus() {
        surplus = budget - getTotalCost();
    }
}
