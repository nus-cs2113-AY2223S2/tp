package seedu.mealcompanion.storage;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import seedu.mealcompanion.MealCompanionSession;
import seedu.mealcompanion.exception.MealCompanionException;
import seedu.mealcompanion.ingredient.Ingredient;
import seedu.mealcompanion.ingredient.IngredientList;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;

//@@author jingyaaa
class IngredientStorageTest {

    @Test
    public void writeIngredientToFile() throws IOException, MealCompanionException {
        MealCompanionSession mealCompanionSession = new MealCompanionSession();
        mealCompanionSession.getIngredientStorage().filename = "Output.txt";
        mealCompanionSession.getIngredientStorage().getFile(mealCompanionSession.getIngredients());
        Ingredient ingredientToAdd1 = new Ingredient("apple", 2);
        Ingredient ingredientToAdd2 = new Ingredient("egg", 3);
        mealCompanionSession.getIngredientStorage().writeIngredientToFile(ingredientToAdd1);
        mealCompanionSession.getIngredientStorage().writeIngredientToFile(ingredientToAdd2);
        String fileOutput = new String(Files.readAllBytes(Path.of("Output.txt")));
        String fileExpected = new String(Files.readAllBytes(Path.of("Expected.txt")));
        assertEquals(fileExpected, fileOutput);
    }


    @Test
    public void writeIngredientsToFile() throws IOException, MealCompanionException {
        MealCompanionSession mealCompanionSession = new MealCompanionSession();
        mealCompanionSession.getIngredientStorage().filename = "Output.txt";
        IngredientList ingredientList = new IngredientList();
        Ingredient ingredientToAdd1 = new Ingredient("apple", 2);
        Ingredient ingredientToAdd2 = new Ingredient("egg", 3);
        ingredientList.add(ingredientToAdd1);
        ingredientList.add(ingredientToAdd2);
        mealCompanionSession.getIngredientStorage().writeIngredientsToFile(ingredientList);
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
