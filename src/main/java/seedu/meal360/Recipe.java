package seedu.meal360;

import java.util.HashMap;

public class Recipe {

    // changed ingredients to public to edit via editRecipe
    public HashMap<String, Integer> ingredients;
    private String name;

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

}
