package seedu.mealcompanion.recipe;

import seedu.mealcompanion.ingredient.IngredientList;

//@@author ngyida
public class Recipe {
    private String name;
    private int calorieCount;
    private int prepTime;
    private int cookTime;
    private IngredientList ingredients;
    private InstructionList instructions;
    private boolean isFavourite;

    //@@author EthanYidong
    public Recipe(String name, boolean isFavourite, int calorieCount, int prepTime, int cookTime,
                  IngredientList ingredients, InstructionList instructions) {
        this.name = name;
        this.isFavourite = isFavourite;
        this.calorieCount = calorieCount;
        this.ingredients = ingredients;
        this.instructions = instructions;
        this.prepTime = prepTime;
        this.cookTime = cookTime;
    }

    //@@author ngyida
    public String getName() {
        return name;
    }

    //@@author Jjzeng123
    public String getFavStatus() {
        return (isFavourite ? "*" : "");
    }

    //@@author Jjzeng123
    public void setFavourite() {
        isFavourite = true;
    }

    //@@author Jjzeng123
    public void unSetFavourite() {
        isFavourite = false;
    }

    //@@author Jjzeng123
    public int getCalorieCount() {
        return calorieCount;
    }

    //@@author EthanYidong
    public int getPrepTime() {
        return prepTime;
    }

    public int getCookTime() {
        return cookTime;
    }

    //@@author ngyida

    /**
     * Get the list of ingredients of the recipe
     *
     * @return IngredientList of recipe
     */
    public IngredientList getIngredients() {
        return ingredients;
    }

    //@@author ngyida

    /**
     * Get the list of instructions of the recipe
     *
     * @return InstructionList of recipe
     */
    public InstructionList getInstructions() {
        return instructions;
    }


    //@@author ngyida

    /**
     * Get a formatted string of recipe details.
     *
     * @return string of recipe details
     */
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
