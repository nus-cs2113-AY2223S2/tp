package seedu.duke.storage;

import com.google.gson.Gson;
import seedu.duke.ingredient.Ingredient;
import seedu.duke.ingredient.IngredientList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;


public class IngredientStorage {

    public String filename = "ingredients.txt";
    private Gson gson = new Gson();
    private BufferedWriter bufferedWriter = null;

    /**
     * Checks for existing file containing ingredients and initialises buffered writer accordingly
     * @param ingredients current ingredient list
     */

    public void getFile(IngredientList ingredients) {
        boolean ingredientFileExists = Files.exists(Path.of(filename));
        if (!ingredientFileExists) {
            try {
                this.bufferedWriter = new BufferedWriter(new FileWriter(filename));
            } catch (IOException e) {
                System.out.println("Oops, unable to create or write to file");
            }
            return;
        }
        assert ingredientFileExists;
        processStoredIngredients(ingredients);
        try {
            this.bufferedWriter = new BufferedWriter(new FileWriter(filename, true));
        } catch (IOException e) {
            System.out.println("Oops, unable to create or write to file");
        }
    }

    /**
     * Reads from existing file and adds all ingredient objects found to ingredient list
     * @param ingredients current ingredient list
     */

    private void processStoredIngredients(IngredientList ingredients) {
        try {
            FileReader fileReader = new FileReader(filename);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String ingredientString = bufferedReader.readLine();
            while (ingredientString != null) {
                addStoredIngredients(ingredientString, ingredients);
                ingredientString = bufferedReader.readLine();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Oops, an error occurred while loading file");
        } catch (IOException e) {
            System.out.println("Oops, an error occurred while reading file");
        }
    }

    /**
     * Processes an ingredient string in JSON format to an object and adds it to the ingredient list
     * @param inputFromFile string containing an ingredient object in JSON format
     * @param ingredients current ingredient list
     */
    private void addStoredIngredients(String inputFromFile, IngredientList ingredients) {
        Ingredient ingredient = gson.fromJson(inputFromFile, Ingredient.class);
        ingredients.add(ingredient);
    }

    /**
     * Write all ingredients in the ingredient list into a new file
     * @param ingredients current ingredient list
     */

    public void writeIngredientsToFile(IngredientList ingredients) {
        try {
            this.bufferedWriter = new BufferedWriter(new FileWriter(filename));
        } catch (IOException e) {
            System.out.println("Oops, unable to create or write to file");
        }
        for (Ingredient ingredient: ingredients.getIngredients()) {
            writeIngredientToFile(ingredient);
        }
    }

    /**
     * Writes a given ingredient into file
     * @param ingredient ingredient object
     */

    public void writeIngredientToFile(Ingredient ingredient) {
        String ingredientString = gson.toJson(ingredient);
        try {
            this.bufferedWriter.write(ingredientString + "\n");
            this.bufferedWriter.flush();
        } catch (Exception e) {
            System.out.println("Oops, an error occurred while saving file");
        }
    }

}
