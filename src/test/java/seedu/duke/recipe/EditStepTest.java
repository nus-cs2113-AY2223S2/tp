package seedu.duke.recipe;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import seedu.duke.ui.UI;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

public class EditStepTest {
    ByteArrayOutputStream output;
    PrintStream console;
    RecipeList recipeList;
    UI ui;

    @BeforeEach
    public void setup() throws Exception {
        recipeList = new RecipeList();
        ArrayList<Step> steps = new ArrayList<>();
        steps.add(new Step("boil water"));
        steps.add(new Step("drink boiling water"));
        StepList stepList = new StepList(steps);
        recipeList.addNewRecipe(new
                Recipe("TypicalRecipe", "Fast", new IngredientList(), stepList));
        output = new ByteArrayOutputStream();
        console = System.out;
        ui = new UI();
        System.setOut(new PrintStream(output));
    }

    @AfterEach
    public void end() throws Exception {
        System.setOut(console);
    }

    
}
