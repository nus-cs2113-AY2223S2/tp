package seedu.duke.recipe;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import seedu.duke.command.Command;
import seedu.duke.ui.StringLib;
import seedu.duke.ui.UI;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.duke.command.CommandType.EDITINGREDIENT;


public class EditIngredientTest {
    ByteArrayOutputStream output;
    PrintStream console;
    RecipeList recipeList;
    UI ui;


    @BeforeEach
    public void setup() throws Exception {
        recipeList = new RecipeList();
        ArrayList<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(new Ingredient("maggi"));
        ingredients.add(new Ingredient("water"));
        IngredientList ingredientList = new IngredientList(ingredients);
        recipeList.addNewRecipe(new
                Recipe("Maggi", "Fast", ingredientList, new StepList()));
        output = new ByteArrayOutputStream();
        console = System.out;
        System.setOut(new PrintStream(output));
    }

    @AfterEach
    public void end() throws Exception {
        System.setOut(console);
    }

    /**
     * Test the result of changing step 2 "water" to "boiling water"
     *
     * @throws Exception if test returns unexpected result
     */
    @Test
    @Disabled
    public void editPositiveTestCase() throws Exception {
        // method to simulate scanner in adapted from
        // https://stackoverflow.com/questions/6415728/junit-testing-with-simulated-user-input
        InputStream sysInBackup = System.in;
        ByteArrayInputStream in = new ByteArrayInputStream("boiling water".getBytes());
        System.setIn(in);
        new Command(EDITINGREDIENT,"2").execute(recipeList,ui);

        System.setIn(sysInBackup);

    }
    /**
     * Test the result of NULL recipe index.
     *
     * @throws Exception if there is an error in the test.
     */
    @Test
    @Disabled
    public void editMissingRecipeIndexTestCase() throws Exception {
        new Command(EDITINGREDIENT,"").execute(recipeList,ui);
        assertEquals(StringLib.RECIPE_EDITING_DEFAULT_ERROR +
                        "The index of EDITINGREDIENT cannot be empty.\n",
                output.toString().trim());
    }

    /**
     * Test the result of 0 recipe index
     *
     * @throws Exception if test has an error
     */
    @Test
    public void editRecipeIndexZeroTestCase() throws Exception {
        new Command(EDITINGREDIENT,"0").execute(recipeList,ui);
        assertEquals(StringLib.POS_INT + System.lineSeparator() + "Valid range: " + 1 + " to " + 1,
                '\n' + output.toString().trim());
    }

    /**
     * Test the result of recipe index exceeding recipe list
     *
     * @throws Exception if test has an error
     */
    @Test
    public void editRecipeIndexExceededTestCase() throws Exception {
        new Command(EDITINGREDIENT,"6").execute(recipeList,ui);
        assertEquals(StringLib.POS_INT + System.lineSeparator() + "Valid range: " + 1 + " to " + 1,
                '\n' + output.toString().trim());
    }


}
