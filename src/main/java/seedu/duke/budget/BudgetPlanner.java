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
        boolean isInvalidAmount = checkInvalidAmount(budget);
        if (isInvalidAmount) {
            return;
        }
        budget = checkExceedMaxAmount(budget);
        this.budget = budget;
        budgetStorage.setBudget(budget);
    }

    public void setAccommodationTotalCost(int accommodationTotalCost) {
        boolean isInvalidAmount = checkInvalidAmount(accommodationTotalCost);
        if (isInvalidAmount) {
            return;
        }
        accommodationTotalCost = checkExceedMaxAmount(accommodationTotalCost);
        this.accommodationTotalCost = accommodationTotalCost;
        budgetStorage.setAccommodationCost(accommodationTotalCost);
    }

    public void setAirplaneTicketTotalCost(int airplaneTicketTotalCost) {
        boolean isInvalidAmount = checkInvalidAmount(airplaneTicketTotalCost);
        if (isInvalidAmount) {
            return;
        }
        airplaneTicketTotalCost = checkExceedMaxAmount(airplaneTicketTotalCost);
        this.airplaneTicketTotalCost = airplaneTicketTotalCost;
        budgetStorage.setAirplaneTicketCost(airplaneTicketTotalCost);
    }

    public void setFoodTotalCost(int foodTotalCost) {
        boolean isInvalidAmount = checkInvalidAmount(foodTotalCost);
        if (isInvalidAmount) {
            return;
        }
        foodTotalCost = checkExceedMaxAmount(foodTotalCost);
        this.foodTotalCost = foodTotalCost;
        budgetStorage.setFoodCost(foodTotalCost);
    }

    public void setEntertainmentTotalCost(int entertainmentTotalCost) {
        boolean isInvalidAmount = checkInvalidAmount(entertainmentTotalCost);
        if (isInvalidAmount) {
            return;
        }
        entertainmentTotalCost = checkExceedMaxAmount(entertainmentTotalCost);
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
        if (amount < 0) {
            return true;
        }
        return false;
    }

    private int checkExceedMaxAmount(int amount) {
        int newBudget = Math.min(amount, MAX_BUDGET);
        if (newBudget < amount) {
            System.out.println("Maximum budget of " + MAX_BUDGET + " allowed");
        }
        return newBudget;
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
