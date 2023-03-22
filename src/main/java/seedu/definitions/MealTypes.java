package seedu.definitions;

public enum MealTypes {
    BREAKFAST("Breakfast", 0),
    LUNCH("Lunch", 1),
    DINNER("Dinner", 2);

    private String mealType;
    private int order;

    private MealTypes(String mealType, int order) {
        this.mealType = mealType;
        this.order = order;
    } 

    @Override
    public String toString() {
        return mealType;
    }

    public int getOrder() {
        return this.order;
    }

    public int isBefore(MealTypes other) {
        return this.getOrder() < other.getOrder() ? -1 : 1;
    }

    public static MealTypes fromString(String mealType) {
        for (MealTypes mt : MealTypes.values()) {
            if (mt.mealType.equalsIgnoreCase(mealType)) {
                return mt;
            }
        }
        return null;
    }

    public static String getSupportedMealTypes() {
        String output = "";
        for (MealTypes mt : MealTypes.values()) {
            output += mt.toString() + ", ";
        }
        return output.substring(0, output.length() - 2);
    }
}
