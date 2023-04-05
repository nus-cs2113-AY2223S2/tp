package seedu.duke.budget;

public class BudgetPlanner {

    public static final int MAX_BUDGET = 20000000;
    private static BudgetPlanner instance = null;
    private int budget;
    private int surplus;
    private int accommodationTotalCost;
    private int airplaneTicketTotalCost;
    private int foodTotalCost;
    private int entertainmentTotalCost;
    private BudgetStorage budgetStorage;

    private BudgetPlanner() {
        budget = 0;
        initialiseCost();
        updateSurplus();
    }

    public static BudgetPlanner getInstance() {
        if (instance == null) {
            instance = new BudgetPlanner();
        }
        return instance;
    }

    public void setBudget(int budget) {
        boolean isInvalidAmount = checkInvalidAmount(budget);
        if (isInvalidAmount) {
            return;
        }
        this.budget = budget;
        budgetStorage.setBudget(budget);
    }

    public void setAccommodationTotalCost(int accommodationTotalCost) {
        boolean isInvalidAmount = checkInvalidAmount(accommodationTotalCost);
        if (isInvalidAmount) {
            return;
        }
        this.accommodationTotalCost = accommodationTotalCost;
        budgetStorage.setAccommodationCost(accommodationTotalCost);
    }

    public void setAirplaneTicketTotalCost(int airplaneTicketTotalCost) {
        boolean isInvalidAmount = checkInvalidAmount(airplaneTicketTotalCost);
        if (isInvalidAmount) {
            return;
        }
        this.airplaneTicketTotalCost = airplaneTicketTotalCost;
        budgetStorage.setAirplaneTicketCost(airplaneTicketTotalCost);
    }

    public void setFoodTotalCost(int foodTotalCost) {
        boolean isInvalidAmount = checkInvalidAmount(foodTotalCost);
        if (isInvalidAmount) {
            return;
        }
        this.foodTotalCost = foodTotalCost;
        budgetStorage.setFoodCost(foodTotalCost);
    }

    public void setEntertainmentTotalCost(int entertainmentTotalCost) {
        boolean isInvalidAmount = checkInvalidAmount(entertainmentTotalCost);
        if (isInvalidAmount) {
            return;
        }
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

    private boolean checkInvalidAmount(int amount) {
        if (amount < 0 || amount > MAX_BUDGET) {
            return true;
        }
        return false;
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
