package seedu.mealcompanion.recipe;

import seedu.mealcompanion.ingredient.IngredientList;

public class Recipe {
    private String name;
    private int calorieCount;
    private int prepTime;
    private int cookTime;
    private IngredientList ingredients;
    private InstructionList instructions;

    public Recipe(String name, int calorieCount, int prepTime, int cookTime, IngredientList ingredients,
                  InstructionList instructions) {
        this.name = name;
        this.calorieCount = calorieCount;
        this.ingredients = ingredients;
        this.instructions = instructions;
        this.prepTime = prepTime;
        this.cookTime = cookTime;
    }

    public String getName() {
        return name;
    }

    public int getCalorieCount() {
        return calorieCount;
    }

    public int getPrepTime() {
        return prepTime;
    }

    public int getCookTime() {
        return cookTime;
    }

    public IngredientList getIngredients() {
        return ingredients;
    }

    public InstructionList getInstructions() {
        return instructions;
    }
}
