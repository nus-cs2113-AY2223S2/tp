package seedu.mealcompanion.command.misc;

import org.junit.jupiter.api.Test;
import seedu.mealcompanion.MealCompanionSession;
import seedu.mealcompanion.command.ingredients.ClearCommand;
import seedu.mealcompanion.exception.MealCompanionException;
import seedu.mealcompanion.ingredient.Ingredient;

import static org.junit.jupiter.api.Assertions.assertEquals;

//@@author TJW0911
public class ClearCommandTest {

    @Test
    public void removeAllIngredients() throws MealCompanionException {
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

        assertEquals(6, mealCompanionSession.getIngredients().size());
        new ClearCommand().execute(mealCompanionSession);
        assertEquals(0, mealCompanionSession.getIngredients().size());
    }
}
