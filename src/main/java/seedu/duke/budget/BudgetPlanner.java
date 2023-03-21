package seedu.duke.budget;

public class BudgetPlanner {

    public static final int MAX_BUDGET = 20000000;
    private int budget;
    private int surplus;
    private int accommodationTotalCost;
    private int airplaneTicketTotalCost;
    private int foodTotalCost;
    private int entertainmentTotalCost;
    private BudgetStorage budgetStorage;

    public BudgetPlanner() {
        budget = 0;
        initialiseCost();
        updateSurplus();
    }

    public void setBudget(int budget) {
        budget = handleInvalidBudget(budget);
        this.budget = budget;
        budgetStorage.setBudget(budget);
    }

    public void setAccommodationTotalCost(int accommodationTotalCost) {
        accommodationTotalCost = handleInvalidBudget(accommodationTotalCost);
        this.accommodationTotalCost = accommodationTotalCost;
        budgetStorage.setAccommodationCost(accommodationTotalCost);
    }

    public void setAirplaneTicketTotalCost(int airplaneTicketTotalCost) {
        airplaneTicketTotalCost = handleInvalidBudget(airplaneTicketTotalCost);
        this.airplaneTicketTotalCost = airplaneTicketTotalCost;
        budgetStorage.setAirplaneTicketCost(airplaneTicketTotalCost);
    }

    public void setFoodTotalCost(int foodTotalCost) {
        foodTotalCost = handleInvalidBudget(foodTotalCost);
        this.foodTotalCost = foodTotalCost;
        budgetStorage.setFoodCost(foodTotalCost);
    }

    public void setEntertainmentTotalCost(int entertainmentTotalCost) {
        entertainmentTotalCost = handleInvalidBudget(entertainmentTotalCost);
        this.entertainmentTotalCost = entertainmentTotalCost;
        budgetStorage.setEntertainmentCost(entertainmentTotalCost);
    }

    private void initialiseCost() {
        budgetStorage = new BudgetStorage();
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

    private int handleInvalidBudget(int amount) {
        if (amount < 0) {
            amount = 0;
        }
        if (amount > MAX_BUDGET) {
            amount = MAX_BUDGET;
        }
        return amount;
    }

    public int getBudget() {
        return budget;
    }

    public int getSurplus() {
        updateSurplus();
        return surplus;
    }

    public int getAccommodationTotalCost() {
        return accommodationTotalCost;
    }

    public int getAirplaneTicketTotalCost() {
        return airplaneTicketTotalCost;
    }

    public int getFoodTotalCost() {
        return foodTotalCost;
    }

    public int getEntertainmentTotalCost() {
        return entertainmentTotalCost;
    }
}
