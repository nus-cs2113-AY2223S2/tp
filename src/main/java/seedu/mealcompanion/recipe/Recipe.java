package seedu.mealcompanion.recipe;

import seedu.mealcompanion.ingredient.Ingredient;
import seedu.mealcompanion.ingredient.IngredientList;
import seedu.mealcompanion.ui.MealCompanionUI;

//@@author ngyida
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

    @Override
    public String toString() {
        StringBuilder recipeDetails = new StringBuilder();
        recipeDetails.append("Recipe for " + this.name + System.lineSeparator());
        recipeDetails.append("Calories: " + this.calorieCount + System.lineSeparator());
        recipeDetails.append("Ingredients:" + System.lineSeparator());
        recipeDetails.append(ingredients.toString());
        recipeDetails.append("Instructions:" + System.lineSeparator());
        recipeDetails.append(instructions.toString());
        return String.valueOf(recipeDetails);
    }
}
