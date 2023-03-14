package seedu.duke.recipe;

import com.google.gson.annotations.SerializedName;

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
    private String unitLabel;

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
        return name.toString();
    }
}
