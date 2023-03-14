package seedu.duke.storage;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import seedu.duke.DukeException;
import seedu.duke.DukeSession;
import seedu.duke.ingredient.Ingredient;
import seedu.duke.ingredient.IngredientList;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;

class IngredientStorageTest {

    @Test
    public void writeIngredientToFile() throws IOException, DukeException {
        DukeSession dukeSession = new DukeSession();
        dukeSession.getIngredientStorage().filename = "Output.txt";
        dukeSession.getIngredientStorage().getFile(dukeSession.getIngredients());
        Ingredient ingredientToAdd1 = new Ingredient("apple", 2);
        Ingredient ingredientToAdd2 = new Ingredient("egg", 3);
        dukeSession.getIngredientStorage().writeIngredientToFile(ingredientToAdd1);
        dukeSession.getIngredientStorage().writeIngredientToFile(ingredientToAdd2);
        String fileOutput = new String(Files.readAllBytes(Path.of("Output.txt")));
        String fileExpected = new String(Files.readAllBytes(Path.of("Expected.txt")));
        assertEquals(fileExpected, fileOutput);
    }


    @Test
    public void writeIngredientsToFile() throws IOException, DukeException {
        DukeSession dukeSession = new DukeSession();
        dukeSession.getIngredientStorage().filename = "Output.txt";
        IngredientList ingredientList = new IngredientList();
        Ingredient ingredientToAdd1 = new Ingredient("apple", 2);
        Ingredient ingredientToAdd2 = new Ingredient("egg", 3);
        ingredientList.add(ingredientToAdd1);
        ingredientList.add(ingredientToAdd2);
        dukeSession.getIngredientStorage().writeIngredientsToFile(ingredientList);
        String fileOutput = new String(Files.readAllBytes(Path.of("Output.txt")));
        String fileExpected = new String(Files.readAllBytes(Path.of("Expected.txt")));
        assertEquals(fileExpected, fileOutput);
    }


    @AfterEach
    public void tearDown() throws IOException {
        PrintWriter pw = new PrintWriter("Output.txt");
        pw.close();
    }

}
