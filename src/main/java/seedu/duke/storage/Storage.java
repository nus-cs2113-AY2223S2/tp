package seedu.duke.storage;

import seedu.duke.recipe.Ingredient;
import seedu.duke.recipe.IngredientList;
import seedu.duke.recipe.Recipe;
import seedu.duke.recipe.RecipeList;

import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The <code>Storage</code> class contains various methods which involves
 * handling of the saved file. For instance, file reading, file writing and saving.
 */
public class Storage {

    private static final String SEPARATOR = " I:I ";
    private static final String DIRECTORY_CREATED = "\nDirectory for file saving created.";
    private static final String DIRECTORY_EXISTS = "\nDirectory for file saving already exists.";
    private static final String FILE_CREATED = "Save file created.";
    private static final String FILE_EXISTS = "Save file already exists.";
    private static final String FILE_PARSING_ERROR = "The task does not meet the parsing requirements for unpacking.";
    public static final String DISH = "Dish";
    public static final String INGREDIENT_LIST = "Ingredient List";
    public static final String STEPS = "Steps";
    private static String filePath;

    /**
     * Sets the <code>filePath</code> to a specific value.
     *
     * @param filePath the String containing the location of the file path to be created.
     */
    public static void setFilePath(String filePath) {
        Storage.filePath = filePath;
    }

    /**
     * Creates a new file and directory to store
     * save file, if it does not already exist.
     *
     * @throws IOException if an I/O error has occurred.
     */
    public static void createDirectory() throws IOException {
        File directory = new File("data");
        if (directory.mkdir()) {
            System.out.println(DIRECTORY_CREATED + "\n");
        } else {
            System.out.println(DIRECTORY_EXISTS + "\n");
        }
    }
    public static void writeSavedFile() throws IOException {
        File saveFile;
        FileWriter saveWriter;
        for (Recipe dish : RecipeList.getRecipeList()) {
            saveFile = new File("data/" + dish.getName());
            System.out.println(dish.getName());
            saveWriter = new FileWriter("data/" + dish.getName() + ".txt");
            saveWriter.write(dish.getName() + "\n");
            saveWriter.write(dish.getTag() + "\n");
            saveWriter.write(INGREDIENT_LIST + "\n");
            for (Ingredient ingredient : dish.getIngredientList().getList()) {
                saveWriter.write( ingredient.getName() + "\n");
            }
            saveWriter.write(SEPARATOR + "\n");
            saveWriter.write(STEPS + "\n");
            for (String step : dish.getSteps()) {
                saveWriter.write(step + "\n");
            }
            saveWriter.close();
        }
    }

}
