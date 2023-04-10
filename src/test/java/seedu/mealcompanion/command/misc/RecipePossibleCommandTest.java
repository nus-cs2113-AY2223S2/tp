package seedu.mealcompanion.command.misc;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import seedu.mealcompanion.MealCompanionSession;
import seedu.mealcompanion.command.recipe.RecipePossibleCommand;
import seedu.mealcompanion.exception.MealCompanionException;
import seedu.mealcompanion.ingredient.Ingredient;
import seedu.mealcompanion.ingredient.IngredientList;
import seedu.mealcompanion.recipe.IngredientDatabase;
import seedu.mealcompanion.recipe.InstructionList;
import seedu.mealcompanion.recipe.Recipe;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

//@@author ngyida
class RecipePossibleCommandTest {
    private static IngredientList ingredients = new IngredientList();

    @BeforeAll
    static void createIngredients() {
        try {
            ingredients.add(new Ingredient("water", 100));
            ingredients.add(new Ingredient("chicken", 5));
        } catch (MealCompanionException e) {
            System.out.println("Failed to initialise ingredient list");
        }
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
        Recipe hamSandwich = new Recipe("ham sandwich", false, 200, 30, 10,
                hamSandwichIngredients, new InstructionList());
        mealCompanionSession.getRecipes().add(hamSandwich);

        // Add eggSandwich recipe to mealCompanionSession.
        // eggSandwich is not possible.
        IngredientList eggSandwichIngredients = new IngredientList();
        eggSandwichIngredients.add(new Ingredient("bread", 2));
        eggSandwichIngredients.add(new Ingredient("egg", 1));
        Recipe eggSandwich = new Recipe("egg sandwich", false, 200, 30, 10,
                eggSandwichIngredients, new InstructionList());
        mealCompanionSession.getRecipes().add(eggSandwich);
    }

    @Test
    public void testHasEnoughIngredient_hasEnough_returnTrue() throws MealCompanionException {
        RecipePossibleCommand command = new RecipePossibleCommand();
        Ingredient water = new Ingredient("water", 100);
        assertTrue(command.hasEnoughIngredient(water, ingredients));
    }

    @Test
    public void testHasEnoughIngredient_notEnough_returnFalse() throws MealCompanionException {
        RecipePossibleCommand command = new RecipePossibleCommand();
        Ingredient chicken = new Ingredient("chicken", 100);
        assertFalse(command.hasEnoughIngredient(chicken, ingredients));
    }

    @Test
    public void testHasEnoughIngredient_notFound_returnFalse() throws MealCompanionException {
        RecipePossibleCommand command = new RecipePossibleCommand();
        Ingredient salt = new Ingredient("salt", 1);
        assertFalse(command.hasEnoughIngredient(salt, ingredients));
    }

    @Test
    public void testCanMakeRecipe_canMake_returnTrue() throws MealCompanionException {
        RecipePossibleCommand command = new RecipePossibleCommand();
        IngredientList waterIngredients = new IngredientList();
        waterIngredients.add(new Ingredient("water", 100));
        Recipe cupOfWater = new Recipe("Cup of Water", false, 0, 0, 0,
                waterIngredients, new InstructionList());
        assertTrue(command.canMakeRecipe(cupOfWater, ingredients));
    }

    @Test
    public void testCanMakeRecipe_notEnoughIngredient_returnFalse() throws MealCompanionException {
        RecipePossibleCommand command = new RecipePossibleCommand();
        IngredientList waterIngredients = new IngredientList();
        waterIngredients.add(new Ingredient("water", 200));
        Recipe cupOfWater = new Recipe("Cup of Water", false, 0, 0, 0,
                waterIngredients, new InstructionList());
        assertFalse(command.canMakeRecipe(cupOfWater, ingredients));
    }

    @Test
    public void testCanMakeRecipe_missingIngredient_returnFalse() throws MealCompanionException {
        RecipePossibleCommand command = new RecipePossibleCommand();
        IngredientList beefIngredients = new IngredientList();
        beefIngredients.add(new Ingredient("Ground Beef", 2));
        Recipe beef = new Recipe("Beef", false, 0, 0, 0,
                beefIngredients, new InstructionList());
        assertFalse(command.canMakeRecipe(beef, ingredients));
    }
}

