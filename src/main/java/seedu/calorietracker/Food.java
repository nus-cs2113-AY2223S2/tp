package seedu.calorietracker;

//@@author calebcjl
/**
 * Represents food consumed.
 */
public class Food {
    private final String foodName;
    private final int calories;

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
