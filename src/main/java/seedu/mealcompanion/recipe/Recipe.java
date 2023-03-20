package seedu.mealcompanion.recipe;

import seedu.mealcompanion.ingredient.IngredientList;

public class Recipe {
    private String name;
    private int calorieCount;
    private IngredientList ingredients;
    private InstructionList instructions;

    public Recipe(String name, int calorieCount, IngredientList ingredients, InstructionList instructions) {
        this.name = name;
        this.calorieCount = calorieCount;
        this.ingredients = ingredients;
        this.instructions = instructions;
    }

    public String getName() {
        return name;
    }

    public int getCalorieCount() {
        return calorieCount;
    }

    public IngredientList getIngredients() {
        return ingredients;
    }

    public InstructionList getInstructions() {
        return instructions;
    }
}
