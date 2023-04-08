package seedu.duke.command;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.recipe.IngredientList;
import seedu.duke.recipe.Recipe;
import seedu.duke.recipe.RecipeList;
import seedu.duke.recipe.StepList;
import seedu.duke.ui.UI;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class AddToRecipeTest {
    ByteArrayOutputStream output;
    PrintStream console;
    UI ui;
    private static final String INVALID_ADD_TO_RECIPE_DESCRIPTION = "OOPS!!!\n" +
            "Looks like the description of the command is invalid!\n" +
            "To add elements to the recipe, please follow the following layout:\n" +
            "\naddtorecipe --[s/i] id/[index] desc/[description of step/ingredient]";
    private static final String INGREDIENT_ADD_SUCCESS =
            "The ingredient has been successfully added to the ingredient list!";
    private static final String DUPLICATE_INGREDIENT_ERROR =
            "The ingredient you wish to add is already on the ingredient list and hence cannot be added.";
    private static final String DUPLICATE_STEP_ERROR =
            "The step you wish to add is already on the step list and hence cannot be added.";
    private static final String STEP_ADD_SUCCESS =
            "The step has been successfully added to the step list!";
    private static final String EMPTY_STEP_DESCRIPTION_MESSAGE = "Description of step cannot be empty!";
    private static final String EMPTY_INGREDIENT_DESCRIPTION_MESSAGE = "Description of ingredient cannot be empty!";
    private static final String STEP_QUIT_MESSAGE = "The step was not added to the step list!";
    @BeforeEach
    public void setup() throws Exception {
        RecipeList.createRecipeList();
        RecipeList.addNewRecipe(new
                Recipe("Oyakodon", "Japanese", new IngredientList(), new StepList()));

        output = new ByteArrayOutputStream();
        console = System.out;
        ui = new UI();
        System.setOut(new PrintStream(output));
    }

    @AfterEach
    public void end() throws Exception {
        System.setOut(console);
        RecipeList.clearRecipeList();
    }
    @Test
    public void testAddToRecipeIngredientNoDesc() throws Exception {
        new Command(CommandType.ADDTORECIPE, "--i id/1").execute(ui);
        assertEquals(INVALID_ADD_TO_RECIPE_DESCRIPTION, output.toString().trim());
    }
    @Test
    public void testAddToRecipeIngredientNoId() throws Exception {
        new Command(CommandType.ADDTORECIPE, "--i desc/Egg").execute(ui);
        assertEquals(INVALID_ADD_TO_RECIPE_DESCRIPTION,output.toString().trim());
    }
    @Test
    public void testAddToRecipeIngredientNoIdDesc() throws Exception {
        new Command(CommandType.ADDTORECIPE, "--i").execute(ui);
        assertEquals(INVALID_ADD_TO_RECIPE_DESCRIPTION, output.toString().trim());
    }
    @Test
    public void testAddToRecipeNoType() throws Exception {
        new Command(CommandType.ADDTORECIPE, "id/1 desc/Flammenwerfer").execute(ui);
        assertEquals(INVALID_ADD_TO_RECIPE_DESCRIPTION, output.toString().trim());
    }
    @Test
    public void testAddToRecipeIngredientWithDupe() throws Exception {
        new Command(CommandType.ADDTORECIPE, "--i id/1 desc/Eggs").execute(ui);
        assertEquals(INGREDIENT_ADD_SUCCESS,output.toString().trim());
        new Command(CommandType.ADDTORECIPE, "--i id/1 desc/Eggs").execute(ui);
        assertEquals(DUPLICATE_INGREDIENT_ERROR, output.toString().trim());
    }
    @Test
    public void testAddToRecipeIngredientEmptyDesc() throws Exception {
        new Command(CommandType.ADDTORECIPE, "--i id/1 desc/").execute(ui);
        assertEquals(EMPTY_INGREDIENT_DESCRIPTION_MESSAGE, output.toString().trim());
    }
    @Test
    public void testAddToRecipeStepEmptyDesc() throws Exception {
        new Command(CommandType.ADDTORECIPE, "--s id/1 desc/").execute(ui);
        assertEquals(EMPTY_STEP_DESCRIPTION_MESSAGE, output.toString().trim());
    }
    @Test
    public void testAddToRecipeStepNoDesc() throws Exception {
        new Command(CommandType.ADDTORECIPE, "--s id/1").execute(ui);
        assertEquals(INVALID_ADD_TO_RECIPE_DESCRIPTION, output.toString().trim());
    }
    @Test
    public void testAddToRecipeStepNoId() throws Exception {
        new Command(CommandType.ADDTORECIPE, "--s desc/Beat egg").execute(ui);
        assertEquals(INVALID_ADD_TO_RECIPE_DESCRIPTION,output.toString().trim());
    }
    @Test
    public void testAddToRecipeStepNoIdDesc() throws Exception {
        new Command(CommandType.ADDTORECIPE, "--s").execute(ui);
        assertEquals(INVALID_ADD_TO_RECIPE_DESCRIPTION, output.toString().trim());
    }
}
