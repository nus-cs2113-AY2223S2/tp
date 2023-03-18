package seedu.definitions;

public enum MealTypes {
    BREAKFAST("Breakfast"),
    LUNCH("Lunch"),
    DINNER("Dinner");

    private String mealType;

    private MealTypes(String mealType) {
        this.mealType = mealType;
    } 

    @Override
    public String toString() {
        return mealType;
    }
}
