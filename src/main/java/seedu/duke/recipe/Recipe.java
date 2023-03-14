package seedu.duke.recipe;

import seedu.duke.ingredient.IngredientList;

public class Recipe {
    private String name;
    private IngredientList ingredients;
    private InstructionList instructions;

    public Recipe(String name, IngredientList ingredients, InstructionList instructions) {
        this.name = name;
        this.ingredients = ingredients;
        this.instructions = instructions;
    }

    public String getName() {
        return name;
    }

    public IngredientList getIngredients() {
        return ingredients;
    }

    public InstructionList getInstructions() {
        return instructions;
    }
}
