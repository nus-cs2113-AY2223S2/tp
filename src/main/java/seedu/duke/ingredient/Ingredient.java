package seedu.duke.ingredient;

import seedu.duke.DukeException;
import seedu.duke.recipe.IngredientDatabase;
import seedu.duke.recipe.IngredientMetadata;

public class Ingredient {
    protected IngredientMetadata metadata;
    protected double quantity;

    public Ingredient(String name, double quantity) throws DukeException {
        IngredientDatabase db = IngredientDatabase.getDbInstance();

        if (!db.getKnownIngredients().containsKey(name)) {
            throw new DukeException("Ingredient with name: " + name + " does not exist");
        }
        this.metadata = db.getKnownIngredients().get(name);
        this.quantity = quantity;
    }
    @Override
    public String toString() {
        if (this.metadata.getUnitLabel() == null ){
            return this.quantity + " " + this.metadata.getName();
        }
        return this.quantity + " " + this.metadata.getUnitLabel() + " " + this.metadata.getName();
    }

    public IngredientMetadata getMetadata() {
        return metadata;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public double getQuantity() {
        return quantity;
    }
}
