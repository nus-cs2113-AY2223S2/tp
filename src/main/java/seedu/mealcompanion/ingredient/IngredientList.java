package seedu.mealcompanion.ingredient;

import seedu.mealcompanion.MealCompanionException;

import java.util.ArrayList;

//@@author TJW0911
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

    //@@author jingyaaa
    public Ingredient get(String ingredientName) throws MealCompanionException {
        for (Ingredient ingredient : ingredients) {
            if (ingredientName.equals(ingredient.getMetadata().getName())) {
                return ingredient;
            }
        }
        throw new MealCompanionException("Oops, ingredient not found");
    }

    //@@author TJW0911
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
