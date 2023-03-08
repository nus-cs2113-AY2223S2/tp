package seedu.duke;

import java.util.ArrayList;
public class IngredientList {
    ArrayList<Ingredient> Ingredients;

    public IngredientList() {
        Ingredients = new ArrayList<>();
    }

    public void add(Ingredient ingredient) {
        Ingredients.add(ingredient);
    }

    public Ingredient get(int i) {
        return Ingredients.get(i);
    }
    public void remove(int i) {
        Ingredients.remove(i);
    }

    public int size() {
        return Ingredients.size();
    }
}
