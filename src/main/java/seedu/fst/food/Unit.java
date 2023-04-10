package seedu.fst.food;

//@@author wanjuin

/**
 * This enum represents the units of food.
 */
public enum Unit {
    MILLIGRAM("mg"),
    GRAM("g"),
    KILOGRAM("kg"),
    MILLILITER("ml"),
    LITER("l"),
    SERVING("serving"),
    SERVINGS("servings"),
    UNIT("unit"),
    UNITS("units"),
    BOX("box"),
    BOXES("boxes"),
    PACKET("packet"),
    PACKETS("packets");

    public String abbreviation;

    Unit (String abbreviation) {
        this.abbreviation = abbreviation;
    }
}
