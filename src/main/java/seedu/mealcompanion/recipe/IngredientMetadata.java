package seedu.mealcompanion.recipe;

import com.google.gson.annotations.SerializedName;

/**
 * Represents information about a type of ingredient
 */
public class IngredientMetadata {
    enum UnitType {
        @SerializedName("weight")
        WEIGHT,
        @SerializedName("count")
        COUNT,
        @SerializedName("volume")
        VOLUME
    }

    private String name;
    private UnitType units;
    private String unitLabel = null;

    public String getName() {
        return name;
    }

    public UnitType getUnits() {
        return units;
    }

    public String getUnitLabel() {
        return unitLabel;
    }

    @Override
    public String toString() {
        if (this.unitLabel == null) {
            return name;
        }
        return name + ": " + this.unitLabel;
    }
}
