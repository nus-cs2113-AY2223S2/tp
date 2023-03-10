package seedu.duke.ui;

import seedu.duke.command.CommandType;
import seedu.duke.exceptions.FileParseReadingException;
import seedu.duke.exceptions.IncompleteInputException;
import seedu.duke.exceptions.RecipeListEmptyError;
import seedu.duke.recipe.Recipe;

import static seedu.duke.ui.StringLib.CREATING_NEW_FILE_AND_DIRECTORY;
import static seedu.duke.ui.StringLib.DUDE_MAIN_ERROR;
import static seedu.duke.ui.StringLib.EMPTY_LIST_MESSAGE;
import static seedu.duke.ui.StringLib.EXIT_MESSAGE;
import static seedu.duke.ui.StringLib.FILE_IO_ERROR;
import static seedu.duke.ui.StringLib.FILE_LOADING_DEFAULT_ERROR;
import static seedu.duke.ui.StringLib.FILE_NOT_FOUND_ERROR;
import static seedu.duke.ui.StringLib.FILE_PARSE_READING_ERROR;
import static seedu.duke.ui.StringLib.FIND_LIST_MESSAGE;
import static seedu.duke.ui.StringLib.HELP;
import static seedu.duke.ui.StringLib.LINE;
import static seedu.duke.ui.StringLib.MISSING_DESCRIPTION_ERROR;
import static seedu.duke.ui.StringLib.MISSING_INPUTS_ERROR;
import static seedu.duke.ui.StringLib.NO_MATCHING_FIND_RESULTS_MESSAGE;
import static seedu.duke.ui.StringLib.PARSING_STRING_ERROR;
import static seedu.duke.ui.StringLib.PREFIX_EMPTY_LIMIT_LIST_ERROR;
import static seedu.duke.ui.StringLib.RECIPE_ADDING_DEFAULT_ERROR;
import static seedu.duke.ui.StringLib.RECIPE_DELETING_DEFAULT_ERROR;
import static seedu.duke.ui.StringLib.RECIPE_FINDING_DEFAULT_ERROR;
import static seedu.duke.ui.StringLib.SUFFIX_EMPTY_LIMIT_LIST_ERROR;
import static seedu.duke.ui.StringLib.UNRECOGNIZABLE_COMMAND_ERROR;
import static seedu.duke.ui.StringLib.UNRECOGNIZABLE_ERROR;
import static seedu.duke.ui.StringLib.WELCOME_MESSAGE;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class Ui {

    public static String readCommand() {
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }
    public static void showFindResults(ArrayList<Recipe> list, String keywords) {
        if (list.size() == 0) {
            System.out.println(NO_MATCHING_FIND_RESULTS_MESSAGE + keywords + '\n');
            return;
        }
        System.out.println(FIND_LIST_MESSAGE);
        int i = 1;
        for (Recipe t : list) {
            System.out.println(i + ". " + t.toString());
            i += 1;
        }
        System.out.println();
    }
    public static void showRecipeList(ArrayList<Recipe> list) {
        if (list.size() == 0) {
            System.out.println(EMPTY_LIST_MESSAGE);
            return;
        }
        System.out.println("\nRECIPE LIST\n");
        int i = 1;
        for (Recipe t : list) {
            System.out.println(i + ". " + t.toString());
            i += 1;
        }
        System.out.println();
    }
    public static void showRecipeAdded(Recipe recipe, int recipeListSize) {
        System.out.println('\n' + "Got it. I've added this recipe:");
        System.out.println("  " + recipe.toString());
        System.out.println("Now you have " + recipeListSize + " recipes in the list." + '\n');
    }
    public static void showRecipeDeleted(Recipe recipe, int recipeListSize) {
        System.out.println('\n' + "Noted. I've removed this recipe:");
        System.out.println("  " + recipe.toString());
        System.out.println("Now you have " + recipeListSize + " recipes in the list." + '\n');
    }
    public static void showWelcome() {
        System.out.println(WELCOME_MESSAGE);
    }
    public static void showExit() {
        System.out.println(EXIT_MESSAGE);
    }
    public static void showHelp() {
        System.out.println(HELP);
    }
    public static void showLine() {
        System.out.println(LINE);
    }
    public static void showDudeMainError(Exception e) {
        if (e instanceof IOException) {
            System.out.println(FILE_IO_ERROR + e);
        } else {
            System.out.println(DUDE_MAIN_ERROR + e);
        }
    }
    public static void showUnrecognizableErrorMessage() {
        System.out.println(UNRECOGNIZABLE_ERROR);
    }
    public static void showUnrecognizableCommandErrorMessage() {
        System.out.println(UNRECOGNIZABLE_COMMAND_ERROR);
    }

    public static void showLoadingErrorMessage(Exception e) {
        if (e instanceof FileNotFoundException) {
            System.out.println(FILE_NOT_FOUND_ERROR + e + CREATING_NEW_FILE_AND_DIRECTORY);
        } else if (e instanceof FileParseReadingException) {
            System.out.println(FILE_PARSE_READING_ERROR + e);
        } else {
            System.out.println(FILE_LOADING_DEFAULT_ERROR + e);
        }
    }
    public static void showAddingRecipeErrorMessage(Exception e) {
        if (e instanceof IncompleteInputException) {
            System.out.println(MISSING_DESCRIPTION_ERROR + e);
        } else if (e instanceof StringIndexOutOfBoundsException) {
            System.out.println(PARSING_STRING_ERROR + e);
        } else {
            System.out.println(RECIPE_ADDING_DEFAULT_ERROR + e);
        }
    }
    public static void showDeletingTaskErrorMessage(Exception e, CommandType type) {
        if (e instanceof IncompleteInputException) {
            System.out.println(MISSING_DESCRIPTION_ERROR + e);
        } else if (e instanceof IndexOutOfBoundsException || e instanceof NullPointerException ||
                   e instanceof RecipeListEmptyError) {
            System.out.println(PREFIX_EMPTY_LIMIT_LIST_ERROR + type + SUFFIX_EMPTY_LIMIT_LIST_ERROR);
        } else {
            System.out.println(RECIPE_DELETING_DEFAULT_ERROR + e);
        }
    }
    public static void showFindingTaskErrorMessage(Exception e) {
        if (e instanceof IncompleteInputException) {
            System.out.println(MISSING_INPUTS_ERROR + e);
        } else {
            System.out.println(RECIPE_FINDING_DEFAULT_ERROR + e);
        }
    }
}
