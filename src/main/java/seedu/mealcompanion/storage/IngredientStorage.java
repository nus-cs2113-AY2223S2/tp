package seedu.mealcompanion.storage;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import seedu.mealcompanion.ingredient.Ingredient;
import seedu.mealcompanion.ingredient.IngredientList;
import seedu.mealcompanion.ui.MealCompanionUI;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

//@@ author jingyaaa

/**
 * Reads ingredients initially stored in a file to ingredient list and updates stored ingredients in a file
 * after every change made to the ingredient list.
 */

public class IngredientStorage {

    public String filename = "ingredients.txt";
    private Gson gson = new Gson();
    private BufferedWriter bufferedWriter = null;
    private boolean fileHasBeenEdited = false;

    private MealCompanionUI ui;

    public IngredientStorage(MealCompanionUI ui) {
        this.ui = ui;
    }

    /**
     * Checks for existing file containing ingredients and initialises buffered writer accordingly
     *
     * @param ingredients current ingredient list
     */

    public void getFile(IngredientList ingredients) {
        boolean ingredientFileExists = Files.exists(Path.of(filename));
        if (!ingredientFileExists) {
            try {
                this.bufferedWriter = new BufferedWriter(new FileWriter(filename));
            } catch (IOException e) {
                ui.printMessage("Oops, unable to create or write to file");
            }
            return;
        }
        assert ingredientFileExists;
        processStoredIngredients(ingredients);
        try {
            this.bufferedWriter = new BufferedWriter(new FileWriter(filename, true));
        } catch (IOException e) {
            ui.printMessage("Oops, unable to create or write to file");
        }
    }

    /**
     * Reads from existing file and adds all ingredient objects found to ingredient list
     *
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
            if (fileHasBeenEdited) {
                ui.printMessage("Please refrain from editing the 'ingredients.txt' file, " +
                        "some ingredients in your list has been affected and is now invalid.");
            }
        } catch (FileNotFoundException e) {
            ui.printMessage("Oops, an error occurred while loading file");
        } catch (IOException e) {
            ui.printMessage("Oops, an error occurred while reading file");
        } catch (JsonSyntaxException e) {
            ui.printMessage("Please refrain from editing the 'ingredients.txt' file, stored data cannot be loaded");
        }
    }

    /**
     * Processes an ingredient string in JSON format to an object and adds it to the ingredient list
     *
     * @param inputFromFile string containing an ingredient object in JSON format
     * @param ingredients   current ingredient list
     */
    private void addStoredIngredients(String inputFromFile, IngredientList ingredients) {
        Ingredient ingredient = gson.fromJson(inputFromFile, Ingredient.class);
        if (ingredient.getMetadata() == null ||
                ingredient.getMetadata().getName() == null ||
                (ingredient.getMetadata().getUnits() == null && ingredient.getMetadata().getUnitLabel() == null) ||
                ingredient.getQuantity() <= 0 || ingredient.getQuantity() > 10000) {
            fileHasBeenEdited = true;
            return;
        }
        ingredients.add(ingredient);
    }

    /**
     * Write all ingredients in the ingredient list into a new file
     *
     * @param ingredients current ingredient list
     */

    public void writeIngredientsToFile(IngredientList ingredients) {
        try {
            this.bufferedWriter = new BufferedWriter(new FileWriter(filename));
        } catch (IOException e) {
            ui.printMessage("Oops, unable to create or write to file");
        }
        for (Ingredient ingredient : ingredients.getIngredients()) {
            writeIngredientToFile(ingredient);
        }
    }

    /**
     * Writes a given ingredient into file
     *
     * @param ingredient ingredient object
     */

    public void writeIngredientToFile(Ingredient ingredient) {
        String ingredientString = gson.toJson(ingredient);
        try {
            this.bufferedWriter.write(ingredientString + System.lineSeparator());
            this.bufferedWriter.flush();
        } catch (Exception e) {
            ui.printMessage("Oops, an error occurred while saving file");
        }
    }

}
