package seedu.calorietracker;

/**
 * Represents food consumed.
 */
public class Food {
    private String foodName;
    private int calories;

    public Food(String foodName, int calories) {
        this.foodName = foodName;
        this.calories = calories;
    }

    public String getFoodName() {
        return foodName;
    }

    public int getCalories() {
        return calories;
    }
}
