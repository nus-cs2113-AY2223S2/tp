package seedu.duke.recipe;

import java.util.ArrayList;

public class Recipe {
    protected IngredientList ingredientList;
    protected ArrayList<String> steps;
    protected String name;
    protected String tag;

    public Recipe(String inputName, String inputTag, IngredientList inputList, ArrayList<String> inputSteps) {
        name = inputName;
        tag = inputTag;
        ingredientList = inputList;
        steps = inputSteps;
    }

    public IngredientList getIngredientList() {
        return ingredientList;
    }

    public ArrayList<String> getSteps() {
        return steps;
    }

    public String getName() {
        return name;
    }

    public String getTag() {
        return tag;
    }
    public String toString() {
        return '[' + tag + "] " + name;
    }
}
