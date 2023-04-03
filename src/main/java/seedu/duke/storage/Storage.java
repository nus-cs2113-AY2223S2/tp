package seedu.duke.storage;

import seedu.duke.exceptions.FileStorageException;
import seedu.duke.recipe.Ingredient;
import seedu.duke.recipe.IngredientList;
import seedu.duke.recipe.Recipe;
import seedu.duke.recipe.RecipeList;
import seedu.duke.recipe.Step;
import seedu.duke.recipe.StepList;
import seedu.duke.ui.StringLib;

import java.io.FileNotFoundException;
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

    private static final String DIRECTORY_CREATED = "\nDirectory for file saving created.";
    private static final String DIRECTORY_EXISTS = "\nDirectory for file saving already exists.";
    private static String filePath = "data";

    /**
     * Sets the <code>filePath</code> to a specific value.
     *
     * @param filePath the String containing the location of the file path to be created.
     */
    public static void setFilePath(String filePath) {
        Storage.filePath = filePath;
    }

    /**
     * Creates a new directory to store
     * save file, if it does not already exist.
     *
     * @throws FileStorageException if an I/O error has occurred.
     */
    public static void createDirectory() throws FileStorageException {
        try {
            File directory = new File(filePath);
            if (directory.mkdir()) {
                System.out.println(DIRECTORY_CREATED + "\n");
            } else {
                System.out.println(DIRECTORY_EXISTS + "\n");
            }
        } catch (Exception e) {
            throw new FileStorageException(e.getMessage());
        }
    }

    public static void deleteFile(File file) throws FileStorageException {
        try {
            if (file.isFile()) {
                file.delete();
            } else if (file.isDirectory()) {
                File[] files = file.listFiles();
                for (File item : files) {
                    deleteFile(item);
                }
            }
        } catch (Exception e) {
            throw new FileStorageException("Error in file clear:" + e.getMessage());
        }
    }

    /**
     * Writes save file in data folder.
     * Format:
     * DishName
     * Tag
     * "Ingredient list"
     * list ingredients
     * "Step list"
     * list steps
     * @throws IOException
     */
    public static void writeSavedFile() {
        File folder = new File(filePath);
        try {
            deleteFile(folder);
        } catch (FileStorageException e) {
            System.out.println(e.getMessage());
            return;
        }
        try {
            FileWriter saveWriter;
            int dishIndex = 1;
            for (Recipe dish : RecipeList.getRecipeList()) {
                saveWriter = new FileWriter("data/" + dishIndex + ".txt");
                saveWriter.write(dish.getName() + "\n");
                saveWriter.write(dish.getTag() + "\n");
                saveWriter.write(StringLib.INGREDIENT_LIST + "\n");
                for (Ingredient ingredient : dish.getIngredientList().getList()) {
                    saveWriter.write( ingredient.getName() + "\n");
                }
                saveWriter.write(StringLib.SAVE_SEPARATOR + "\n");
                saveWriter.write(StringLib.STEP_LIST + "\n");
                for (Step step : dish.getStepList().getList()) {
                    saveWriter.write(step.getStep() + "\n");
                }
                saveWriter.close();
                dishIndex++;
            }
        } catch (IOException e) {
            System.out.println("Error in file writing:" + e.getMessage());
        }
    }
    public static ArrayList<File> findValidSaveFiles() {
        File searchArea = new File("data");
        File[] saveList = searchArea.listFiles();
        ArrayList<File> validSaveFiles = new ArrayList<>();
        for (File item : saveList) {
            if (item.getName().contains(".txt")) {
                validSaveFiles.add(item);
            }
        }
        return validSaveFiles;
    }

    /**
     * Loads all saved recipes into recipe list.
     *
     * @throws FileNotFoundException
     */
    public static void loadSaveFiles() throws FileNotFoundException {
        ArrayList<File> validSaves = findValidSaveFiles();
        ArrayList<Recipe> recipeList = new ArrayList<>();
        for (File saveFile : validSaves) {
            Scanner reader = new Scanner(saveFile);
            String name = reader.nextLine();
            String tag = reader.nextLine();
            ArrayList<Ingredient> ingredientList = new ArrayList<>();
            ArrayList<Step> stepList = new ArrayList<>();
            while (reader.hasNextLine()) {
                String ingredient = reader.nextLine();
                if (ingredient.equals(StringLib.INGREDIENT_LIST)) {
                } else if (ingredient.equals(StringLib.SAVE_SEPARATOR)) {
                    break;
                } else {
                    ingredientList.add(new Ingredient(ingredient));
                }
            }
            while (reader.hasNextLine()) {
                String step = reader.nextLine();
                if (step.equals(StringLib.IMPORT_END_RECIPE)) {
                    break;
                } else if (step.equals(StringLib.STEP_LIST)) {
                } else {
                    stepList.add(new Step(step));
                }
            }
            recipeList.add(new Recipe(
                    name,
                    tag,
                    new IngredientList(ingredientList),
                    new StepList(stepList)));
            reader.close();
        }
        for (Recipe item : recipeList) {
            RecipeList.addNewRecipe(item);
        }
    }
}
