package seedu.meal360;

import java.util.HashMap;

public class Recipe {

    // changed ingredients to public to edit via editRecipe
    private static IngredientList ingredientList = new IngredientList();
    public HashMap<String, Integer> ingredients;
    private String name;
    private Boolean available;


    public Recipe(String name, HashMap<String, Integer> ingredients) {
        this.name = name;
        this.ingredients = ingredients;
    }

    public String getName() {
        return name;
    }

    public HashMap<String, Integer> getIngredients() {
        return ingredients;
    }

    public int getNumOfIngredients() {
        return ingredients.size();
    }

    public boolean isAvailable() {
        for (String ingredientName : ingredients.keySet()) {
            if (ingredientList.containsKey(ingredientName)) {
                Ingredient ingredient = ingredientList.get(ingredientName);
                if (ingredient.ingredientCount < ingredients.get(ingredientName)) {
                    return false;
                }
            } else {
                return false;
            }
        }
        return true;
    }
}
