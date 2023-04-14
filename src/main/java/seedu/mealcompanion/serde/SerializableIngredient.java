package seedu.mealcompanion.serde;

/**
 * Helper class for JSON deserialization from desired ingredient format
 */
public class SerializableIngredient {
    private String name;
    private int amount;

    public String getName() {
        return name;
    }

    public int getAmount() {
        return amount;
    }
}
