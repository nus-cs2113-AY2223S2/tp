package seedu.mealcompanion.command.misc;

import org.junit.jupiter.api.Test;
import seedu.mealcompanion.MealCompanionException;
import seedu.mealcompanion.MealCompanionSession;
import seedu.mealcompanion.command.recipe.RecipePossibleCommand;
import seedu.mealcompanion.ingredient.Ingredient;
import seedu.mealcompanion.ingredient.IngredientList;
import seedu.mealcompanion.recipe.IngredientDatabase;
import seedu.mealcompanion.recipe.InstructionList;
import seedu.mealcompanion.recipe.Recipe;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

//@@author ngyida
class RecipePossibleCommandTest {

    @Test
    public void testHasEnoughIngredient_hasEnough() throws MealCompanionException {
        RecipePossibleCommand command = new RecipePossibleCommand();
        Ingredient water = new Ingredient("water", 100);
        IngredientList ingredients = new IngredientList();
        ingredients.add(new Ingredient("water", 100));
        ingredients.add(new Ingredient("chicken", 5));
        assertEquals(true, command.hasEnoughIngredient(water, ingredients));
    }


    @Test
    public void recipePossibleAndRecipeNotPossible() throws MealCompanionException {
        MealCompanionSession mealCompanionSession = new MealCompanionSession();
        IngredientDatabase db = IngredientDatabase.getDbInstance();
        // add allergens to mealCompanionSession
        mealCompanionSession.setAllergens(Arrays.asList(
                db.getKnownIngredients().get("milk"), db.getKnownIngredients().get("eggs")));

        // Add ingredients to mealCompanionSession's ingredients
        mealCompanionSession.getIngredients().add(new Ingredient("bread", 2));
        mealCompanionSession.getIngredients().add(new Ingredient("ham", 1));

        // Add hamSandwich recipe to mealCompanionSession
        // hamSandwich is possible
        IngredientList hamSandwichIngredients = new IngredientList();
        hamSandwichIngredients.add(new Ingredient("bread", 2));
        hamSandwichIngredients.add(new Ingredient("ham", 1));
        Recipe hamSandwich = new Recipe("ham sandwich", false,200, 30, 10,
                hamSandwichIngredients, new InstructionList());
        mealCompanionSession.getRecipes().add(hamSandwich);

        // Add eggSandwich recipe to mealCompanionSession.
        // eggSandwich is not possible.
        IngredientList eggSandwichIngredients = new IngredientList();
        eggSandwichIngredients.add(new Ingredient("bread", 2));
        eggSandwichIngredients.add(new Ingredient("egg", 1));
        Recipe eggSandwich = new Recipe("egg sandwich", false,200, 30, 10,
                eggSandwichIngredients, new InstructionList());
        mealCompanionSession.getRecipes().add(eggSandwich);
    }

    public void testHasEnoughIngredient_notEnough() throws MealCompanionException {
        RecipePossibleCommand command = new RecipePossibleCommand();
        Ingredient chicken = new Ingredient("chicken", 100);
        IngredientList ingredients = new IngredientList();
        ingredients.add(new Ingredient("water", 100));
        ingredients.add(new Ingredient("chicken", 5));
        assertEquals(false, command.hasEnoughIngredient(chicken, ingredients));
    }

    @Test
    public void testHasEnoughIngredient_notFound() throws MealCompanionException {
        RecipePossibleCommand command = new RecipePossibleCommand();
        Ingredient salt = new Ingredient("salt", 1);
        IngredientList ingredients = new IngredientList();
        ingredients.add(new Ingredient("water", 100));
        ingredients.add(new Ingredient("chicken", 5));
        assertEquals(false, command.hasEnoughIngredient(salt, ingredients));
    }

    @Test
    public void testCanMakeRecipe_canMake() throws MealCompanionException {
        RecipePossibleCommand command = new RecipePossibleCommand();
        IngredientList waterIngredients = new IngredientList();
        waterIngredients.add(new Ingredient("water", 100));
        Recipe cupOfWater = new Recipe("Cup of Water", false, 0, 0, 0,
                waterIngredients, new InstructionList());
        IngredientList fridgeIngredients = new IngredientList();
        fridgeIngredients.add(new Ingredient("water", 100));
        fridgeIngredients.add(new Ingredient("chicken", 5));
        assertEquals(true, command.canMakeRecipe(cupOfWater, fridgeIngredients));
    }

    @Test
    public void testCanMakeRecipe_notEnoughIngredient_cannotMake() throws MealCompanionException {
        RecipePossibleCommand command = new RecipePossibleCommand();
        IngredientList waterIngredients = new IngredientList();
        waterIngredients.add(new Ingredient("water", 100));
        Recipe cupOfWater = new Recipe("Cup of Water", false,0, 0, 0,
                waterIngredients, new InstructionList());
        IngredientList fridgeIngredients = new IngredientList();
        fridgeIngredients.add(new Ingredient("water", 90));
        fridgeIngredients.add(new Ingredient("chicken", 5));
        assertEquals(false, command.canMakeRecipe(cupOfWater, fridgeIngredients));
    }

    @Test
    public void testCanMakeRecipe_missingIngredient_cannotMake() throws MealCompanionException {
        RecipePossibleCommand command = new RecipePossibleCommand();
        IngredientList waterIngredients = new IngredientList();
        waterIngredients.add(new Ingredient("water", 100));
        Recipe cupOfWater = new Recipe("Cup of Water", false,0, 0, 0,
                waterIngredients, new InstructionList());
        IngredientList fridgeIngredients = new IngredientList();
        fridgeIngredients.add(new Ingredient("apple", 9));
        fridgeIngredients.add(new Ingredient("chicken", 5));
        assertEquals(false, command.canMakeRecipe(cupOfWater, fridgeIngredients));
    }
}

