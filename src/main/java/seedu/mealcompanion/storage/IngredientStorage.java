package seedu.mealcompanion.storage;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import seedu.mealcompanion.exception.MealCompanionException;
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

import static seedu.mealcompanion.command.ingredients.AddCommand.MAX_INGREDIENTS;
import static seedu.mealcompanion.command.ingredients.AddCommand.MIN_INGREDIENTS;

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
                        "some ingredients in your list may have been affected and is now invalid.");
                ui.printMessage("File will now be overwritten to contain only valid ingredients.");
                writeIngredientsToFile(ingredients);
            }
        } catch (FileNotFoundException e) {
            ui.printMessage("Oops, an error occurred while loading file");
        } catch (IOException e) {
            ui.printMessage("Oops, an error occurred while reading file");
        } catch (JsonSyntaxException e) {
            ui.printMessage("Please refrain from editing the 'ingredients.txt' file, stored data cannot be loaded.");
            ui.printMessage("The corrupted file will now be overwritten.");
            ingredients.clear();
            try {
                this.bufferedWriter = new BufferedWriter(new FileWriter(filename));
            } catch (IOException ex) {
                ui.printMessage("Oops, unable to create or write to file");
            }
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
        try {
            // check if is valid ingredient
            Ingredient checkIngredient = new Ingredient(ingredient.getMetadata().getName(), ingredient.getQuantity());
            // check if there is duplicate
            if (duplicatedIngredients(ingredients, checkIngredient)) {
                ui.printMessage("File has been edited to contain duplicated ingredients, duplicates will be ignored");
                return;
            }
            // check for valid quantity
            if (checkIngredient.getQuantity() <= MIN_INGREDIENTS || checkIngredient.getQuantity() > MAX_INGREDIENTS) {
                fileHasBeenEdited = true;
                return;
            }
            // check for valid units
            if (ingredient.getMetadata().getUnits() == null && ingredient.getMetadata().getUnitLabel() == null) {
                fileHasBeenEdited = true;
            }
            ingredients.add(checkIngredient);
        } catch (MealCompanionException e) {
            fileHasBeenEdited = true;
        } catch (NullPointerException e) {
            fileHasBeenEdited = true;
        }
    }

    /**
     * Checks for duplicated ingredients after user edits the ingredients.txt file.
     *
     * @param ingredients list of ingredients
     * @param ingredient ingredient to be checked
     * @return true if the ingredient is duplicated
     */
    private boolean duplicatedIngredients(IngredientList ingredients, Ingredient ingredient) {
        for (Ingredient ingredientInList : ingredients.getIngredients()) {
            if (ingredientInList.getMetadata().getName().contentEquals(ingredient.getMetadata().getName())) {
                fileHasBeenEdited = true;
                return true;
            }
        }
        return false;
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
