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

    /**
     * Constructor of BudgetPlanner class
     */
    private BudgetPlanner() {
        budget = 0;
        initialiseCost();
        updateSurplus();
    }

    /**
     * Returns the instance of the BudgetPlanner object.
     * Creates a new instance of BudgetPlanner if it has never been created before, else it will return the current
     * instance of the BudgetPlanner.
     * Follows Singleton pattern.
     *
     * @return a BudgetPlanner object.
     */
    public static BudgetPlanner getInstance() {
        if (instance == null) {
            instance = new BudgetPlanner();
        }
        return instance;
    }

    /**
     * Sets the budget value and checks if the amount is valid.
     * Sets the budgetStorage budget if value is valid.
     *
     * @param budget the value of the budget.
     */
    public void setBudget(int budget) {
        boolean isInvalidAmount = checkInvalidAmount(budget);
        if (isInvalidAmount) {
            return;
        }
        this.budget = budget;
        budgetStorage.setBudget(budget);
    }

    /**
     * Sets the Accommodation cost and checks if the amount is valid
     * Sets the budgetStorage accommodation cost too if value is valid.
     *
     * @param accommodationTotalCost the cost of the accommodation.
     */
    public void setAccommodationTotalCost(int accommodationTotalCost) {
        boolean isInvalidAmount = checkInvalidAmount(accommodationTotalCost);
        if (isInvalidAmount) {
            return;
        }
        this.accommodationTotalCost = accommodationTotalCost;
        budgetStorage.setAccommodationCost(accommodationTotalCost);
    }

    /**
     * Sets the airplane ticket cost and checks if the amount is valid
     * Sets the budgetStorage airplane ticket cost too if value is valid.
     *
     * @param airplaneTicketTotalCost the cost of the airplane ticket.
     */
    public void setAirplaneTicketTotalCost(int airplaneTicketTotalCost) {
        boolean isInvalidAmount = checkInvalidAmount(airplaneTicketTotalCost);
        if (isInvalidAmount) {
            return;
        }
        this.airplaneTicketTotalCost = airplaneTicketTotalCost;
        budgetStorage.setAirplaneTicketCost(airplaneTicketTotalCost);
    }

    /**
     * Sets the Food cost and checks if the amount is valid
     * Sets the budgetStorage food cost too if value is valid.
     *
     * @param foodTotalCost the cost of the food.
     */
    public void setFoodTotalCost(int foodTotalCost) {
        boolean isInvalidAmount = checkInvalidAmount(foodTotalCost);
        if (isInvalidAmount) {
            return;
        }
        this.foodTotalCost = foodTotalCost;
        budgetStorage.setFoodCost(foodTotalCost);
    }

    /**
     * Sets the Entertainment cost and checks if the amount is valid
     * Sets the budgetStorage entertainment cost too if value is valid.
     *
     * @param entertainmentTotalCost the cost of the entertainment.
     */
    public void setEntertainmentTotalCost(int entertainmentTotalCost) {
        boolean isInvalidAmount = checkInvalidAmount(entertainmentTotalCost);
        if (isInvalidAmount) {
            return;
        }
        this.entertainmentTotalCost = entertainmentTotalCost;
        budgetStorage.setEntertainmentCost(entertainmentTotalCost);
    }

    /**
     * Initialises the budgetStorage and retrieve the values of budget, accommodation, airplane, food and entertainment
     * from budgetStorage
     */
    private void initialiseCost() {
        budgetStorage = budgetStorage.getInstance();
        budget = budgetStorage.getBudget();
        accommodationTotalCost = budgetStorage.getAccommodationCost();
        airplaneTicketTotalCost = budgetStorage.getAirplaneTicketCost();
        foodTotalCost = budgetStorage.getFoodCost();
        entertainmentTotalCost = budgetStorage.getEntertainmentCost();
    }

    private int getTotalCost() {
        return accommodationTotalCost + airplaneTicketTotalCost + foodTotalCost + entertainmentTotalCost;
    }

    /**
     * Calculates the surplus
     */
    private void updateSurplus() {
        surplus = budget - getTotalCost();
    }

    private boolean checkInvalidAmount(int amount) {
        if (amount < 0 || amount > MAX_BUDGET) {
            return true;
        }
        return false;
    }

    /**
     * Returns the budget value
     *
     * @return the budget value
     */
    public int getBudget() {
        return budget;
    }

    /**
     * Returns the surplus
     *
     * @return the surplus value
     */
    public int getSurplus() {
        updateSurplus();
        return surplus;
    }

    /**
     * Returns the accommodation cost
     *
     * @return the accommodation cost
     */
    public int getAccommodationTotalCost() {
        return accommodationTotalCost;
    }

    /**
     * Returns the airplane ticket cost
     *
     * @return the airplane ticket cost
     */
    public int getAirplaneTicketTotalCost() {
        return airplaneTicketTotalCost;
    }

    /**
     * Returns the food cost
     *
     * @return the food cost
     */
    public int getFoodTotalCost() {
        return foodTotalCost;
    }

    /**
     * Returns the entertainment cost
     *
     * @return the entertainment cost
     */
    public int getEntertainmentTotalCost() {
        return entertainmentTotalCost;
    }
}
