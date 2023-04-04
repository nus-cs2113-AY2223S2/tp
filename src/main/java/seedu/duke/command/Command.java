package seedu.duke.command;


import seedu.duke.exceptions.IncompleteInputException;
import seedu.duke.exceptions.InvalidIndexRangeException;
import seedu.duke.exceptions.OutOfIndexException;
import seedu.duke.exceptions.RecipeListEmptyException;
import seedu.duke.parser.Parser;
import seedu.duke.recipe.IngredientList;
import seedu.duke.recipe.Recipe;
import seedu.duke.recipe.RecipeList;
import seedu.duke.recipe.StepList;
import seedu.duke.storage.Storage;
import seedu.duke.ui.IntLib;
import seedu.duke.ui.StringLib;
import seedu.duke.ui.UI;

import java.io.IOException;
import java.util.ArrayList;

import static seedu.duke.ui.IntLib.RECIPE_NAME_INDEX;
import static seedu.duke.ui.IntLib.RECIPE_INGREDIENTS_INDEX;
import static seedu.duke.ui.IntLib.RECIPE_TAG_INDEX;
import static seedu.duke.ui.IntLib.RECIPE_SUM_OF_STEPS_INDEX;

/**
 * Represents a particular command to be carried out consisting of the
 * command type and command description.
 * <p></p>
 * A <code>Command</code> object corresponds to a particular command represented
 * by <code>type</code> and <code>fullDescription</code> (e.g. <code>DELETE,6</code>)
 */
public class Command {
    private final CommandType type;
    private final String fullDescription;

    /**
     * Class constructor specifying the type of command and its follow-up description.
     *
     * @param type an Enum that represents a particular command.
     * @param fullDescription a String that contains the follow-up description for the command.
     */
    public Command(CommandType type, String fullDescription) {
        this.type = type;
        this.fullDescription = fullDescription;
    }

    /**
     * Returns if the command type is <code>CommandType.EXIT</code>
     * in order to terminate the programme.
     *
     * @return      if the command is the exit command type.
     */
    public boolean isExit() {
        return this.type == CommandType.EXIT;
    }

    /**
     * Based on the <code>type</code>, carries out different tasks assigned
     * while fully checking for any exceptions that may occur along the way.
     *
     * @param recipeList the current list of recipes to be modified or used.
     */
    public void execute(RecipeList recipeList, UI ui) throws IOException {

        int recipeListIndex;
        int recipeCount = recipeList.getCurrRecipeNumber();
        switch (type) {
        case LIST:
            ui.showRecipeList(recipeList.getRecipeList());
            break;
        case ADD:
            try {
                if (fullDescription.isEmpty()) {
                    throw new IncompleteInputException("The description of " + type + " cannot be empty.\n");
                }
                ArrayList<String> parsed = Parser.parseRecipe(fullDescription);
                String recipeName = parsed.get(RECIPE_NAME_INDEX);
                IngredientList ingredientLists =
                        Parser.parseIngredients(parsed.get(RECIPE_INGREDIENTS_INDEX));
                String recipeTag = parsed.get(RECIPE_TAG_INDEX).toString();
                int sumOfSteps = Integer.parseInt(parsed.get(RECIPE_SUM_OF_STEPS_INDEX));
                StepList recipeSteps = Parser.parseSteps(ui,sumOfSteps);
                recipeList.addNewRecipe(new Recipe(recipeName, recipeTag, ingredientLists, recipeSteps));
                recipeCount = recipeList.getCurrRecipeNumber();
                ui.showRecipeAdded(recipeList.getNewestRecipe(), recipeCount);
                Storage.writeSavedFile();
            } catch (Exception e) {
                ui.showAddingRecipeErrorMessage(e);
            }
            break;
        case DELETE:
            try {
                if (fullDescription.isEmpty()) {
                    throw new IncompleteInputException("The index of " + type + " cannot be empty.\n");
                }
                recipeListIndex = Integer.parseInt(fullDescription);
                if (recipeCount == 0) {
                    System.out.println(StringLib.EMPTY_LIST_MESSAGE);
                    break;
                }
                if (recipeListIndex <= 0 || recipeListIndex > recipeCount) {
                    System.out.println(StringLib.POS_INT);
                    System.out.println("Valid range: " + 1 + " to " + recipeCount);
                    break;
                }
                Recipe recipeToBeDeleted = recipeList.getRecipeFromList(recipeListIndex);
                recipeList.removeRecipe(recipeListIndex);
                recipeCount = recipeList.getCurrRecipeNumber();
                ui.showRecipeDeleted(recipeToBeDeleted, recipeCount);
                Storage.writeSavedFile();
            } catch (Exception e) {
                ui.showDeletingTaskErrorMessage(e, type);
            }
            break;
        case CLEAR:
            recipeList.clearRecipeList();
            ui.showRecipeListCleared();
            Storage.writeSavedFile();
            break;
        case VIEW:
            try {
                if (fullDescription.isEmpty()) {
                    throw new IncompleteInputException("The KEYWORDS of " + type + " cannot be empty.\n");
                }
                if (recipeList.isEmpty()) {
                    throw new RecipeListEmptyException();
                }
                Recipe recipeToBeViewed = recipeList.viewRecipe(fullDescription);
                ui.showRecipeViewed(recipeToBeViewed, ui);
            } catch (Exception e) {
                ui.showViewingRecipeErrorMessage(e);
            }
            break;
        case FINDNAME:
            RecipeList.searchRecipeList(fullDescription);
            break;
        case FINDTAG:
            RecipeList.searchByTag(fullDescription);
            break;
        case EDITSTEP:
            try {
                if (fullDescription.isEmpty()) {
                    throw new IncompleteInputException("The index of " + type + " cannot be empty.\n");
                }
                int recipeListNum = Integer.parseInt(fullDescription);
                if (recipeCount == 0) {
                    System.out.println(StringLib.EMPTY_LIST_MESSAGE);
                    break;
                }
                if (recipeListNum <= 0 || recipeListNum > recipeCount) {
                    System.out.println(StringLib.POS_INT);
                    System.out.println("Valid range: " + 1 + " to " + recipeCount);
                    break;
                }
                Recipe recipeToEdit = recipeList.getRecipeFromList(recipeListNum);
                StepList recipeToEditStepList = recipeToEdit.getStepList();
                int maxSteps = recipeToEditStepList.getCurrStepNumber();
                if (maxSteps == 0) {
                    assert (maxSteps - 1 == -1);
                    throw new OutOfIndexException(StringLib.NO_STEPS_ERROR);

                }

                recipeToEditStepList.showFullStepList();
                ui.showEditRecipeStepPrompt();
                String input = ui.readCommand();
                if (input.trim().equals(StringLib.STEP_VIEW_QUIT_KEYWORD) ||
                        input.contains(StringLib.STEP_VIEW_QUIT_KEYWORD)) {
                    break;
                }
                int stepIndex = Integer.parseInt(input) - 1;
                if (stepIndex < maxSteps && stepIndex >= 0) {
                    assert (maxSteps - stepIndex > 0);
                    recipeToEditStepList.editStep(stepIndex, ui);
                } else if (stepIndex <= 0) {
                    System.out.println(StringLib.POS_INT);
                    System.out.println("Valid range: " + 1 + " to " + maxSteps);
                } else {
                    throw new OutOfIndexException(StringLib.INPUT_STEPS_INDEX_EXCEEDED +
                            "\nValid Range: 1 to " + maxSteps);
                }
                Storage.writeSavedFile();
            } catch (Exception e) {
                ui.showEditErrorMessage(e);
            }
            break;
            
        case EDITINGREDIENT:
            try {
                if (fullDescription.isEmpty()) {
                    throw new IncompleteInputException("The index of " + type + " cannot be empty.\n");
                }
                int recipeListNum = Integer.parseInt(fullDescription);
                if (recipeCount == 0) {
                    System.out.println(StringLib.EMPTY_LIST_MESSAGE);
                    break;
                }
                if (recipeListNum <= 0 || recipeListNum > recipeCount) {
                    System.out.println(StringLib.POS_INT);
                    System.out.println("Valid range: " + 1 + " to " + recipeCount);
                    break;
                }
                Recipe recipeToEdit = recipeList.getRecipeFromList(recipeListNum);
                IngredientList recipeToEditIngredientList = recipeToEdit.getIngredientList();
                int maxSteps = recipeToEditIngredientList.getCurrIngredientNumber();
                if (maxSteps == 0) {
                    throw new OutOfIndexException(StringLib.NO_INGREDIENTS_ERROR);
                }
                recipeToEditIngredientList.showList();
                ui.showEditRecipeIngredientPrompt();
                String input = ui.readCommand();
                if (input.trim().equals(StringLib.STEP_VIEW_QUIT_KEYWORD) ||
                        input.contains(StringLib.STEP_VIEW_QUIT_KEYWORD)) {
                    break;
                }
                int ingredientIndex = Integer.parseInt(input) - 1;

                if (ingredientIndex < 0 || ingredientIndex >= maxSteps) {
                    throw new InvalidIndexRangeException(IntLib.NONEMPTY_START_NUMBER,maxSteps);
                }
                recipeToEditIngredientList.editIngredient(ui, ingredientIndex);
                Storage.writeSavedFile();
            } catch (Exception e) {
                ui.showEditErrorMessage(e);
            }
            break;
        case EDIT:
            try {
                EditType editType = Parser.parseEditType(fullDescription);
                boolean isEditIngredient = editType == EditType.INGREDIENT;
                boolean isEditStep = editType == EditType.STEP;
                Object[] parsed = Parser.parseEditRecipeIndex(fullDescription.substring(4),editType);
                int recipeIndex = (int) parsed[0];
                String editDescription = (String) parsed[1];
                if(isEditIngredient) {
                    Parser.parseEditIngredient(recipeList, recipeIndex, editDescription);
                } else if (isEditStep) {
                    Parser.parseEditStep(recipeList, recipeIndex, editDescription);
                }
            } catch (Exception e) {
                ui.showErrorMessage(e);
            }
            Storage.writeSavedFile();
            break;
        case HELP:
            ui.showHelp();
            break;
        case EXIT:
            ui.showExit();
            break;
        case UNKNOWN:
            ui.showUnrecognizableErrorMessage();
            break;
        default:
            ui.showUnrecognizableCommandErrorMessage();
        }
    }
}
