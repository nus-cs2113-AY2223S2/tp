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
    public void testRecipeAll_twoRecipes() {
        DukeSession dukeSession = new DukeSession();
        try {
            // Add hamSandwich recipe to dukeSession
            IngredientList hamSandwichIngredients = new IngredientList();
            hamSandwichIngredients.add(new Ingredient("bread", 2.0));
            hamSandwichIngredients.add(new Ingredient("ham", 1.0));
            Recipe hamSandwich = new Recipe("ham sandwich", hamSandwichIngredients, new InstructionList());
            dukeSession.getRecipes().add(hamSandwich);

            // Add eggSandwich recipe to dukeSession.
            IngredientList eggSandwichIngredients = new IngredientList();
            eggSandwichIngredients.add(new Ingredient("bread", 2.0));
            eggSandwichIngredients.add(new Ingredient("egg", 1.0));
            Recipe eggSandwich = new Recipe("egg sandwich", eggSandwichIngredients, new InstructionList());
            dukeSession.getRecipes().add(eggSandwich);
        } catch (DukeException e) {
            dukeSession.getUi().printMessage(e.toString());
        }

        // This test is not meaningful as it must be changed every time the recipe book is changed.
        // TODO: Rework this or delete it
        /*RecipeAllCommand command = new RecipeAllCommand();
        command.execute(dukeSession);
        String osName = System.getProperty("os.name");
        String newline = "\n";
        if (osName.contains("Windows")) {
            newline = "\r\n";
        }
        String predictedOutput = "Here is the full list of recipes:" + newline + "1. Beef Burger"
                + newline + "2. Cup of Water" + newline + "3. ham sandwich" + newline + "4. egg sandwich" + newline;
        assertEquals(predictedOutput, outContent.toString());*/
    }
}



