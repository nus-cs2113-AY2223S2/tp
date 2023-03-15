package seedu.mealcompanion.ingredient;

import java.util.ArrayList;
public class IngredientList {
    ArrayList<Ingredient> ingredients;

    public IngredientList() {
        ingredients = new ArrayList<>();
    }

    public void add(Ingredient ingredient) {
        ingredients.add(ingredient);
    }

    public Ingredient get(int i) {
        return ingredients.get(i);
    }
    public void remove(int i) {
        ingredients.remove(i);
    }

    public int size() {
        return ingredients.size();
    }

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }
}
