package seedu.duke.command.misc;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.DukeException;
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
    public void recipePossibleAndRecipeNotPossible() throws DukeException {
        DukeSession dukeSession = new DukeSession();
        // Add ingredients to dukeSession's ingredients
        dukeSession.getIngredients().add(new Ingredient("bread", 2.0));
        dukeSession.getIngredients().add(new Ingredient("ham", 1.0));

        // Add hamSandwich recipe to dukeSession
        // hamSandwich is possible
        IngredientList hamSandwichIngredients = new IngredientList();
        hamSandwichIngredients.add(new Ingredient("bread", 2.0));
        hamSandwichIngredients.add(new Ingredient("ham", 1.0));
        Recipe hamSandwich = new Recipe("ham sandwich", hamSandwichIngredients, new InstructionList());
        dukeSession.getRecipes().add(hamSandwich);

        // Add eggSandwich recipe to dukeSession.
        // eggSandwich is not possible.
        IngredientList eggSandwichIngredients = new IngredientList();
        eggSandwichIngredients.add(new Ingredient("bread", 2.0));
        eggSandwichIngredients.add(new Ingredient("egg", 1.0));
        Recipe eggSandwich = new Recipe("egg sandwich", eggSandwichIngredients, new InstructionList());
        dukeSession.getRecipes().add(eggSandwich);

        // Test
        RecipePossibleCommand command = new RecipePossibleCommand();
        command.execute(dukeSession);
        String osName = System.getProperty("os.name");
        String newline = "\n";
        if (osName.contains("Windows")) {
            newline = "\r\n";
        }
        String predictedOutput = "Here are the recipe(s) that you can make:" + newline + "1. ham sandwich" + newline;
        assertEquals(predictedOutput, outContent.toString());
    }
}

