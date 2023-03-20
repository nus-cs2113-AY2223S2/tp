package seedu.duke.budget;

public class BudgetPlanner {
    public static final int MAX_BUDGET = 20000000;
    int budget;
    int surplus;
    Accommodation accommodation;
    AirplaneTicket airplaneTicket;
    Food food;
    Entertainment entertainment;


    public BudgetPlanner(int budget) {
        if (budget > MAX_BUDGET || budget < 0) {
            budget = MAX_BUDGET;
        }
        this.budget = budget;
        this.surplus = budget;
        accommodation = new Accommodation(0);
        airplaneTicket = new AirplaneTicket(0);
        food = new Food(0);
        entertainment = new Entertainment(0);
    }

    private void updateSurplus() {
        surplus = budget - accommodation.getPrice() - airplaneTicket.getPrice() - food.getPrice()
                - entertainment.getPrice();
    }

}
