package seedu.mealcompanion.command.misc;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.mealcompanion.MealCompanionSession;
import seedu.mealcompanion.command.recipe.RecipeAllCommand;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

//@@author ngyida
class RecipeAllCommandTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void testRecipeAll_hasRecipes() {
        MealCompanionSession mealCompanionSession = new MealCompanionSession();
        RecipeAllCommand command = new RecipeAllCommand();
        command.execute(mealCompanionSession);
        String predictedOutput = "There is no recipe available." + System.lineSeparator();
        if (mealCompanionSession.getRecipes().isEmpty()) {
            assertEquals(predictedOutput, outContent.toString());
        } else {
            assertNotEquals(predictedOutput, outContent.toString());
        }
    }
}



