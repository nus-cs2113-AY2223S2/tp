package seedu.mealcompanion.command.misc;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.mealcompanion.MealCompanionSession;
import seedu.mealcompanion.command.ingredients.AddCommand;
import seedu.mealcompanion.command.recipe.RecipeNeedCommand;
import seedu.mealcompanion.exception.CommandRunException;
import seedu.mealcompanion.exception.InvalidIngredientNameException;
import seedu.mealcompanion.exception.MealCompanionException;
import seedu.mealcompanion.recipe.IngredientDatabase;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

//@@author jingyaaa
class RecipeNeedCommandTest {

    private final ByteArrayOutputStream newOutStream = new ByteArrayOutputStream();

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(newOutStream));
    }

    @Test
    void execute() throws MealCompanionException, CommandRunException, InvalidIngredientNameException {
        MealCompanionSession mealCompanionSession = new MealCompanionSession();

        RecipeNeedCommand command = new RecipeNeedCommand(mealCompanionSession.getRecipes().getRecipe(1));
        command.execute(mealCompanionSession);
        String expectedOutput = "These are the ingredient(s) you are missing:"
                + System.lineSeparator()
                + "1. water (quantity: 300)"
                + System.lineSeparator();
        assertEquals(expectedOutput, newOutStream.toString());

        AddCommand addCommand = new AddCommand(IngredientDatabase.getDbInstance().getKnownIngredient("water"), 150);
        addCommand.execute(mealCompanionSession);
        newOutStream.reset();
        command.execute(mealCompanionSession);
        expectedOutput = "These are the ingredient(s) you are missing:"
                + System.lineSeparator()
                + "1. water (quantity: 150)"
                + System.lineSeparator();
        assertEquals(expectedOutput, newOutStream.toString());

        addCommand.execute(mealCompanionSession);
        newOutStream.reset();
        command.execute(mealCompanionSession);
        expectedOutput = "You have all the ingredients to make this recipe!" + System.lineSeparator();
        assertEquals(expectedOutput, newOutStream.toString());

    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(System.out);
    }
}
