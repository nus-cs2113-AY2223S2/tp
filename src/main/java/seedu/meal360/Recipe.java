package seedu.meal360;

import java.util.HashMap;

public class Recipe {

    private String name;
    private HashMap<String, Integer> ingredients;

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
}
