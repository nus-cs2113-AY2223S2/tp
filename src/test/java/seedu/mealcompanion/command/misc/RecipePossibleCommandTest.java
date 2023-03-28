package seedu.mealcompanion.command.misc;

import org.junit.jupiter.api.Test;
import seedu.mealcompanion.MealCompanionException;
import seedu.mealcompanion.command.recipe.RecipePossibleCommand;
import seedu.mealcompanion.ingredient.Ingredient;
import seedu.mealcompanion.ingredient.IngredientList;
import seedu.mealcompanion.recipe.InstructionList;
import seedu.mealcompanion.recipe.Recipe;

import static org.junit.jupiter.api.Assertions.assertEquals;

//@@author ngyida
class RecipePossibleCommandTest {

    @Test
    public void testHasEnoughIngredient_hasEnough() throws MealCompanionException {
        RecipePossibleCommand command = new RecipePossibleCommand();
        Ingredient water = new Ingredient("water", 100.00);
        IngredientList ingredients = new IngredientList();
        ingredients.add(new Ingredient("water", 100.00));
        ingredients.add(new Ingredient("chicken", 5.00));
        assertEquals(true, command.hasEnoughIngredient(water, ingredients));
    }

    @Test
    public void testHasEnoughIngredient_notEnough() throws MealCompanionException {
        RecipePossibleCommand command = new RecipePossibleCommand();
        Ingredient chicken = new Ingredient("chicken", 100.00);
        IngredientList ingredients = new IngredientList();
        ingredients.add(new Ingredient("water", 100.00));
        ingredients.add(new Ingredient("chicken", 5.00));
        assertEquals(false, command.hasEnoughIngredient(chicken, ingredients));
    }

    @Test
    public void testHasEnoughIngredient_notFound() throws MealCompanionException {
        RecipePossibleCommand command = new RecipePossibleCommand();
        Ingredient salt = new Ingredient("salt", 1.00);
        IngredientList ingredients = new IngredientList();
        ingredients.add(new Ingredient("water", 100.00));
        ingredients.add(new Ingredient("chicken", 5.00));
        assertEquals(false, command.hasEnoughIngredient(salt, ingredients));
    }

    @Test
    public void testCanMakeRecipe_canMake() throws MealCompanionException {
        RecipePossibleCommand command = new RecipePossibleCommand();
        IngredientList waterIngredients = new IngredientList();
        waterIngredients.add(new Ingredient("water", 100.00));
        Recipe cupOfWater = new Recipe("Cup of Water", 0, 0, 0, waterIngredients, new InstructionList());
        IngredientList fridgeIngredients = new IngredientList();
        fridgeIngredients.add(new Ingredient("water", 100.00));
        fridgeIngredients.add(new Ingredient("chicken", 5.00));
        assertEquals(true, command.canMakeRecipe(cupOfWater, fridgeIngredients));
    }

    @Test
    public void testCanMakeRecipe_notEnoughIngredient_cannotMake() throws MealCompanionException {
        RecipePossibleCommand command = new RecipePossibleCommand();
        IngredientList waterIngredients = new IngredientList();
        waterIngredients.add(new Ingredient("water", 100.00));
        Recipe cupOfWater = new Recipe("Cup of Water", 0, 0, 0, waterIngredients, new InstructionList());
        IngredientList fridgeIngredients = new IngredientList();
        fridgeIngredients.add(new Ingredient("water", 90.00));
        fridgeIngredients.add(new Ingredient("chicken", 5.00));
        assertEquals(false, command.canMakeRecipe(cupOfWater, fridgeIngredients));
    }

    @Test
    public void testCanMakeRecipe_missingIngredient_cannotMake() throws MealCompanionException {
        RecipePossibleCommand command = new RecipePossibleCommand();
        IngredientList waterIngredients = new IngredientList();
        waterIngredients.add(new Ingredient("water", 100.00));
        Recipe cupOfWater = new Recipe("Cup of Water", 0, 0, 0, waterIngredients, new InstructionList());
        IngredientList fridgeIngredients = new IngredientList();
        fridgeIngredients.add(new Ingredient("apple", 9.00));
        fridgeIngredients.add(new Ingredient("chicken", 5.00));
        assertEquals(false, command.canMakeRecipe(cupOfWater, fridgeIngredients));
    }
}

