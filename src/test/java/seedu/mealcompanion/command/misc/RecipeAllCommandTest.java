package seedu.mealcompanion.command.misc;

import org.junit.jupiter.api.Test;
import seedu.mealcompanion.command.recipe.RecipeAllCommand;
import seedu.mealcompanion.ingredient.IngredientList;
import seedu.mealcompanion.recipe.InstructionList;
import seedu.mealcompanion.recipe.Recipe;
import seedu.mealcompanion.recipe.RecipeList;

import static org.junit.jupiter.api.Assertions.assertEquals;

//@@author ngyida
class RecipeAllCommandTest {
    @Test
    public void testGetRecipeNameList_noRecipe() {
        RecipeList emptyRecipes = new RecipeList();
        String predictedOutput = "There is no recipe available.";
        RecipeAllCommand command = new RecipeAllCommand();
        assertEquals(command.getAllRecipeNameList(emptyRecipes), predictedOutput);
    }

    @Test
    public void testGetRecipeNameList_twoRecipes() {
        RecipeList twoRecipes = new RecipeList();
        Recipe firstRecipe = new Recipe("Dummy Recipe 1", false, 0, 0, 0,
                new IngredientList(), new InstructionList());
        Recipe secondRecipe = new Recipe("Dummy Recipe 2", false, 0, 0, 0,
                new IngredientList(), new InstructionList());
        twoRecipes.add(firstRecipe);
        twoRecipes.add(secondRecipe);
        String predictedOutput = "Here is the full list of recipes:" + System.lineSeparator() + "1. Dummy Recipe 1"
                + System.lineSeparator() + "2. Dummy Recipe 2" + System.lineSeparator();
        RecipeAllCommand command = new RecipeAllCommand();
        assertEquals(command.getAllRecipeNameList(twoRecipes), predictedOutput);
    }
}



