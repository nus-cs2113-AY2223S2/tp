package seedu.duke.command.misc;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.DukeSession;
import seedu.duke.ingredient.Ingredient;
import seedu.duke.ingredient.IngredientList;
import seedu.duke.recipe.InstructionList;
import seedu.duke.recipe.Recipe;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RecipePossibleCommandTest {
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
    public void RecipePossibleAndRecipeNotPossible() {
        DukeSession dukeSession = new DukeSession();
        // Add ingredients to dukeSession's ingredients
        dukeSession.getIngredients().add(new Ingredient("bread", 2));
        dukeSession.getIngredients().add(new Ingredient("ham", 1));

        // Add hamSandwich recipe to dukeSession
        // hamSandwich is possible
        IngredientList hamSandwichIngredients = new IngredientList();
        hamSandwichIngredients.add(new Ingredient("bread", 2));
        hamSandwichIngredients.add(new Ingredient("ham", 1));
        Recipe hamSandwich = new Recipe("ham sandwich", hamSandwichIngredients, new InstructionList());
        dukeSession.getRecipes().add(hamSandwich);

        // Add eggSandwich recipe to dukeSession.
        // eggSandwich is not possible.
        IngredientList eggSandwichIngredients = new IngredientList();
        eggSandwichIngredients.add(new Ingredient("bread", 2));
        eggSandwichIngredients.add(new Ingredient("egg", 1));
        Recipe eggSandwich = new Recipe("egg sandwich", eggSandwichIngredients, new InstructionList());
        dukeSession.getRecipes().add(eggSandwich);

        // Test
        RecipePossibleCommand command = new RecipePossibleCommand();
        command.execute(dukeSession);
        String predictedOutput = "Here are the recipe(s) that you can make:\r\n1. ham sandwich\r\n";
        assertEquals(predictedOutput, outContent.toString());
    }
}

