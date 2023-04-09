package seedu.mealcompanion.command.misc;

import org.junit.jupiter.api.Test;
import seedu.mealcompanion.MealCompanionSession;
import seedu.mealcompanion.command.recipe.MakeCommand;
import seedu.mealcompanion.exception.CommandRunException;
import seedu.mealcompanion.exception.MealCompanionException;
import seedu.mealcompanion.ingredient.Ingredient;

import static org.junit.jupiter.api.Assertions.assertEquals;

//@@author TJW0911
public class MakeCommandTest {

    @Test
    public void makeCupOfWater() throws MealCompanionException, CommandRunException {
        MealCompanionSession mealCompanionSession = new MealCompanionSession();
        Ingredient ingredient = new Ingredient("water", 2000);
        mealCompanionSession.getIngredients().add(ingredient);
        assertEquals(2000, mealCompanionSession.getIngredients().get(0).getQuantity());
        new MakeCommand(mealCompanionSession.getRecipes().getRecipe(1)).execute(mealCompanionSession);
        assertEquals(1700, mealCompanionSession.getIngredients().get(0).getQuantity());
    }

    @Test
    public void makeBeefBurger() throws MealCompanionException, CommandRunException {
        MealCompanionSession mealCompanionSession = new MealCompanionSession();
        Ingredient ingredient1 = new Ingredient("ground beef", 500);
        Ingredient ingredient2 = new Ingredient("salt", 2000);
        Ingredient ingredient3 = new Ingredient("black pepper", 10);
        Ingredient ingredient4 = new Ingredient("Worcestershire sauce", 200);
        Ingredient ingredient5 = new Ingredient("garlic powder", 2000);
        Ingredient ingredient6 = new Ingredient("hamburger buns", 20);

        mealCompanionSession.getIngredients().add(ingredient1);
        mealCompanionSession.getIngredients().add(ingredient2);
        mealCompanionSession.getIngredients().add(ingredient3);
        mealCompanionSession.getIngredients().add(ingredient4);
        mealCompanionSession.getIngredients().add(ingredient5);
        mealCompanionSession.getIngredients().add(ingredient6);

        assertEquals(500, mealCompanionSession.getIngredients().get(0).getQuantity());
        assertEquals(2000, mealCompanionSession.getIngredients().get(1).getQuantity());
        assertEquals(10, mealCompanionSession.getIngredients().get(2).getQuantity());
        assertEquals(200, mealCompanionSession.getIngredients().get(3).getQuantity());
        assertEquals(2000, mealCompanionSession.getIngredients().get(4).getQuantity());
        assertEquals(20, mealCompanionSession.getIngredients().get(5).getQuantity());

        new MakeCommand(mealCompanionSession.getRecipes().getRecipe(0)).execute(mealCompanionSession);

        assertEquals(50, mealCompanionSession.getIngredients().get(0).getQuantity());
        assertEquals(1998, mealCompanionSession.getIngredients().get(1).getQuantity());
        assertEquals(9, mealCompanionSession.getIngredients().get(2).getQuantity());
        assertEquals(170, mealCompanionSession.getIngredients().get(3).getQuantity());
        assertEquals(1998, mealCompanionSession.getIngredients().get(4).getQuantity());
        assertEquals(18, mealCompanionSession.getIngredients().get(5).getQuantity());
    }
}
