package seedu.meal360;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.Scanner;

import seedu.meal360.exceptions.IngredientNotFoundException;
import seedu.meal360.exceptions.InvalidRecipeNameException;
import seedu.meal360.exceptions.InvalidValueException;
import seedu.meal360.exceptions.NoRecipeException;
import seedu.meal360.exceptions.RecipeNotFoundInTagException;
import seedu.meal360.exceptions.TagNotFoundException;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.LocalDate;

public class Parser {

    Ui ui = new Ui();

    String recipeErrorMessage =
            "Wrong Format or Invalid Quantity. Please enter ingredients properly!" + "[eg:chicken=100]";

    public String combineWords(String[] input, int startIndex, int length) {
        StringBuilder word = new StringBuilder(input[startIndex].trim());
        for (int i = startIndex + 1; i < length; i++) {
            word.append(" ").append(input[i].trim());
        }

        return word.toString();
    }

    /**
     * Parses the user input into an array of strings. Trims away leading and trailing whitespaces, and
     * converts all words to lowercase.
     *
     * @author jaredoong
     * @param input the user input
     * @return an array of strings representing the user input
     */
    public String[] cleanUserInput(String input) {
        input = input.replaceAll("\\s+", " ");
        input = input.toLowerCase();
        return input.trim().split(" ");
    }

    /**
     * Parses an array of strings representing ingredients and their quantities.
     *
     * @param commands an array of strings in the format "name=quantity"
     * @return a map of ingredient names to their quantities
     * @throws IllegalArgumentException if an ingredient quantity is negative or an ingredient name is empty
     */
    public HashMap<String, Integer> parseIngredientName(String[] commands) {
        HashMap<String, Integer> ingredients = new HashMap<>();
        int flag = 0;
        String currentIngredient = null;

        for (String command : commands) {
            int indexOfEqual = command.indexOf("=");
            if (indexOfEqual == -1) {
                if (command.isEmpty()) {
                    continue;
                }
                if (currentIngredient != null) {
                    currentIngredient += " " + command;
                } else {
                    currentIngredient = command;
                }
                flag++;
            } else {
                if (flag > 0) {
                    currentIngredient += " " + command.substring(0, indexOfEqual);
                    flag = 0;
                } else {
                    currentIngredient = command.substring(0, indexOfEqual);
                }
                String quantityString = command.substring(indexOfEqual + 1);
                if (quantityString.isEmpty()) {
                    throw new IllegalArgumentException("Ingredient quantity cannot be empty");
                }
                int quantity = Integer.parseInt(quantityString);
                if (quantity < 0) {
                    throw new IllegalArgumentException("Ingredient quantity cannot be negative");
                }
                if (currentIngredient.isEmpty()) {
                    throw new IllegalArgumentException("Ingredient name cannot be empty");
                }
                ingredients.put(currentIngredient, quantity);
            }
        }
        return ingredients;
    }

    public Recipe parseAddRecipe(String[] input, RecipeList recipeList) {
        int addedIngredient = 0;
        String recipeName = combineWords(input, 2, input.length);
        if (recipeList.findByName(recipeName) != null) {
            return null;
        } else if (!Objects.equals(input[1], "/r")) {
            throw new ArrayIndexOutOfBoundsException();
        }
        HashMap<String, Integer> ingredients = new HashMap<>();
        Scanner userInput = new Scanner(System.in);
        System.out.println("Please Enter The Ingredients & Quantity (enter \"done\" when complete): ");
        while (true) {
            try {
                String line = userInput.nextLine();
                if (line.equals("done")) {
                    ui.printSeparator();
                    if (addedIngredient == 0 || ingredients.size() == 0) {
                        ui.printMessage(
                                "Add at least 1 ingredient before entering 'done'! [eg: chicken=100]");
                        ui.printSeparator();
                    } else {
                        break;
                    }
                } else {
                    addedIngredient = 1;
                    String[] command = line.trim().split(" ");
                    ingredients = parseIngredientName(command);
                    if (ingredients.size() == 0) {
                        ui.printSeparator();
                        ui.printMessage(recipeErrorMessage);
                        ui.printSeparator();
                    }
                }
            } catch (IllegalArgumentException e) {
                ui.printSeparator();
                ui.printMessage(e.getMessage());
                ui.printSeparator();
            }
        }
        Recipe newRecipe = new Recipe(recipeName, ingredients);
        recipeList.addRecipe(newRecipe);
        return newRecipe;
    }

    public Recipe parseEditRecipe(String[] input, RecipeList recipeList) {
        int addedIngredient = 0;
        String recipeName = combineWords(input, 2, input.length);
        Recipe recipeToEdit;
        HashMap<String, Integer> ingredients = new HashMap<>();
        Scanner userInput = new Scanner(System.in);
        if (recipeList.findByName(recipeName) == null) {
            return null;
        }
        recipeToEdit = recipeList.findByName(recipeName);
        System.out.println("Do you want to edit recipe fully or partially?");
        System.out.println("Press 1 for full edit | Press 2 for partial edit | Press 3 to add ingredients");
        int index;
        Scanner getNum = new Scanner(System.in);
        while (true) {
            try {
                index = Integer.parseInt(getNum.nextLine());
                if (index < 1 || index > 3) {
                    ui.printSeparator();
                    ui.printMessage("Index cannot be negative or out of bounds!");
                    ui.printSeparator();
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                ui.printSeparator();
                ui.printMessage("Enter valid index for respective editing options!");
                ui.printSeparator();
            }
        }
        if (index == 1) {
            System.out.println("Please Enter New Ingredients & Quantity: ");
            while (true) {
                try {
                    String line = userInput.nextLine();
                    if (line.equals("done")) {
                        ui.printSeparator();
                        if (addedIngredient == 0 || ingredients.size() == 0) {
                            ui.printMessage(
                                    "Add at least 1 ingredient before entering 'done'! [eg: chicken=100]");
                            ui.printSeparator();
                        } else {
                            break;
                        }
                    } else {
                        addedIngredient = 1;
                        String[] command = line.trim().split(" ");
                        ingredients = parseIngredientName(command);
                        if (ingredients.size() != 0) {
                            recipeList.editRecipe(recipeToEdit, ingredients);
                        } else {
                            ui.printSeparator();
                            ui.printMessage(recipeErrorMessage);
                            ui.printSeparator();
                        }
                    }
                } catch (IllegalArgumentException | StringIndexOutOfBoundsException e) {
                    ui.printSeparator();
                    ui.printMessage(recipeErrorMessage);
                    ui.printSeparator();
                }
            }
        } else if (index == 2) {
            System.out.println("These are the ingredients for the recipe:");
            ui.printSeparator();
            Recipe recipe = parseViewRecipe(recipeName, recipeList);
            ui.printRecipe(recipe);
            ui.printSeparator();
            System.out.println("Which ingredient do you want to change?");
            int ingredientIndex = 0;
            Scanner getIndex = new Scanner(System.in);
            while (true) {
                try {
                    ingredientIndex = Integer.parseInt(getIndex.nextLine());
                    if (ingredientIndex <= 0 || ingredientIndex > recipe.getNumOfIngredients()) {
                        ui.printSeparator();
                        ui.printMessage("Index cannot be negative or out of bounds!");
                        ui.printSeparator();
                    } else {
                        break;
                    }
                } catch (NumberFormatException e) {
                    ui.printSeparator();
                    ui.printMessage("Enter valid ingredient index!");
                    ui.printSeparator();
                }
            }
            ingredientIndex -= 1;
            int count = 0;
            String ingredientToRemove = null;
            for (String ingredient : recipeToEdit.getIngredients().keySet()) {
                if (ingredientIndex == count) {
                    ingredientToRemove = ingredient;
                    System.out.println("Ingredient to be changed:");
                    ui.printSeparator();
                    String toPrint = String.format("%s(%d)", ingredient,
                            recipeToEdit.getIngredients().get(ingredient));
                    System.out.println(ui.formatMessage(toPrint));
                    ui.printSeparator();
                    break;
                }
                count++;
            }
            System.out.println("Please enter the new ingredient:");

            while (true) {
                try {
                    String line = userInput.nextLine();
                    String command = line.replaceAll("\\s+", " ");
                    int indexOfEqual = command.indexOf("=");
                    if (command.charAt(indexOfEqual + 1) == '-') {
                        ui.printSeparator();
                        ui.printMessage("Invalid ingredient quantity!");
                        ui.printSeparator();
                    } else {
                        String newIngredientName = command.substring(0, indexOfEqual);
                        int newIngredientQuantity = Integer.parseInt(command.substring(indexOfEqual + 1));
                        recipeToEdit.getIngredients().remove(ingredientToRemove);
                        recipeToEdit.getIngredients().put(newIngredientName, newIngredientQuantity);
                        recipeList.editRecipe(recipeToEdit, recipeToEdit.getIngredients());
                        break;
                    }
                } catch (IllegalArgumentException | StringIndexOutOfBoundsException e) {
                    ui.printSeparator();
                    ui.printMessage(recipeErrorMessage);
                    ui.printSeparator();
                }
            }
        } else {
            HashMap<String, Integer> newIngredientList = recipeToEdit.getIngredients();
            System.out.println("These are the current ingredients:");
            ui.printSeparator();
            Recipe recipe = parseViewRecipe(recipeName, recipeList);
            ui.printRecipe(recipe);
            ui.printSeparator();
            System.out.println("Please Enter Additional Ingredients & Quantity: ");
            while (true) {
                try {
                    String line = userInput.nextLine();
                    if (line.equals("done")) {
                        ui.printSeparator();
                        if (addedIngredient == 0 || ingredients.size() == 0) {
                            ui.printMessage(
                                    "Add at least 1 ingredient before entering 'done'! [eg: chicken=100]");
                            ui.printSeparator();
                        } else {
                            break;
                        }
                    } else {
                        addedIngredient = 1;
                        String[] command = line.trim().split(" ");
                        ingredients = parseIngredientName(command);
                        newIngredientList.putAll(ingredients);
                        if (ingredients.size() != 0) {
                            recipeList.editRecipe(recipeToEdit, newIngredientList);
                        } else {
                            ui.printSeparator();
                            ui.printMessage(recipeErrorMessage);
                            ui.printSeparator();
                        }
                    }
                } catch (IllegalArgumentException | StringIndexOutOfBoundsException e) {
                    ui.printSeparator();
                    ui.printMessage(recipeErrorMessage);
                    ui.printSeparator();
                }
            }
        }
        return recipeToEdit;
    }

    public String parseDeleteRecipe(String[] input, RecipeList recipeList) {
        // user inputted recipe name
        try {
            if (!input[1].equals("/r") && !input[1].contains("-")) {
                Integer.parseInt(input[1]);
            }
        } catch (NumberFormatException e) {
            throw new IndexOutOfBoundsException();
        }
        if (input[1].contains("/r")) {
            // skip over /r in recipe name
            String recipeToDelete = "";
            for (int i = 2; i < input.length; i++) {
                recipeToDelete += input[i] + " ";
            }
            recipeToDelete = recipeToDelete.trim();
            if (recipeToDelete.equals("all")) {
                String allRecipes = "";
                int index = 0;
                while (recipeList.size() != 0) {
                    allRecipes += recipeList.deleteRecipe(index).getName() + ", ";
                }
                allRecipes = allRecipes.substring(0, allRecipes.length() - 2);
                return allRecipes;
            } else {
                int recipeIndex = 0;
                for (Recipe recipe : recipeList) {
                    // find index of recipe we want to delete
                    if (recipe.getName().equals(recipeToDelete)) {
                        break;
                    }
                    recipeIndex++;
                }
                return recipeList.deleteRecipe(recipeIndex).getName();
            }
            // user inputted index of recipe in list
        } else {
            // deleting a range of recipes
            if (input[1].length() >= 3) {
                String[] range = input[1].trim().split("-");
                int startIndex = Integer.parseInt(range[0]);
                int endIndex = Integer.parseInt(range[1]);
                startIndex -= 1;
                endIndex -= 1;
                if (startIndex < 0 || endIndex >= recipeList.size() || endIndex < startIndex) {
                    throw new IndexOutOfBoundsException();
                }
                int newSize = recipeList.size() - ((endIndex - startIndex) + 1);
                String rangeRecipes = "";
                while (recipeList.size() != newSize) {
                    rangeRecipes += recipeList.deleteRecipe(startIndex).getName() + ", ";
                }
                rangeRecipes = String.valueOf(
                        new StringBuilder(rangeRecipes.substring(0, rangeRecipes.length() - 2)));
                return rangeRecipes;
            } else {
                int recipeIndex = Integer.parseInt(input[1]);
                ;
                recipeIndex = Integer.parseInt(input[1]);
                // need to subtract 1 since list is 1-based
                return recipeList.deleteRecipe(recipeIndex - 1).getName();
            }
        }
    }

    /**
     * Extract inputs from users whether it is adding recipes to a tag or removing recipe from a tag.
     * Then, proceed to add or remove the recipes from the tag, and returns a string whether
     * the recipes are successfully add to or remove from the tag.
     *
     * @author junenita
     * @param inputs array containing string of inputs
     * @param recipeList list containing all recipes data
     * @return a string whether successfully added or remove recipes from the tag
     * @throws NoRecipeException If users entered invalid recipe.
     * @throws RecipeNotFoundInTagException If users try to remove a recipe that is not in the tag.
     * @throws TagNotFoundException If users try to remove recipes from a tag that has not been created.
     */
    public String parseTagRecipe(String[] inputs, RecipeList recipeList)
            throws RecipeNotFoundInTagException, TagNotFoundException, NoRecipeException {
        String returnMessage;
        String tag;
        boolean isOnlyTagWordInCommand = inputs.length == 1;
        boolean isAddTag;
        boolean isRemoveTag;

        if (isOnlyTagWordInCommand) {
            throw new IllegalArgumentException("Please indicate at least a tag and a recipe.");
        }

        StringBuilder commandString = new StringBuilder(inputs[1]);
        for (int i = 2; i < inputs.length; i++) {
            commandString.append(" ").append(inputs[i]);
        }

        isAddTag = commandString.indexOf(">>") == -1 && commandString.indexOf("<<") != -1;
        isRemoveTag = commandString.indexOf("<<") == -1 && commandString.indexOf(">>") != -1;
        if (!(isAddTag || isRemoveTag)) {
            throw new IllegalArgumentException("Please enter the command in the correct format.");
        } else if (isAddTag) {
            tag = parseAddRecipeTag(commandString.toString(), recipeList);
            returnMessage = "add " + tag;
        } else if (isRemoveTag) {
            tag = parseRemoveRecipeTag(commandString.toString(), recipeList);
            returnMessage = "remove " + tag;
        } else {
            throw new IllegalArgumentException("Invalid command.");
        }
        return returnMessage;
    }

    /**
     * Extract the tag label and recipes. Then, proceed to add the recipes to the tag,
     * and returns the tag label that users modified
     *
     * @author junenita
     * @param command string contain tag label and recipes that users want to add
     * @param recipeList list containing all recipes data
     * @return tag label that is modified
     * @throws NoRecipeException If users entered invalid recipe.
     */
    public String parseAddRecipeTag(String command, RecipeList recipeList) throws NoRecipeException {
        String tag;
        Recipe recipe;
        String[] recipesToTag;
        boolean isUnableToFindTheRecipe;
        String[] args = command.trim().split("<<");
        boolean isNotEnoughArgs = args.length < 2 || args[0].equals("") || args[1].equals("");

        if (isNotEnoughArgs) {
            throw new IllegalArgumentException("Please enter the command in the correct format.");
        }

        tag = args[0].trim();
        recipesToTag = args[1].split("&&");
        for (String recipeName : recipesToTag) {
            recipeName = recipeName.trim();
            recipe = recipeList.findByName(recipeName);
            isUnableToFindTheRecipe = recipe == null;
            if (isUnableToFindTheRecipe) {
                String errorMessage1 = "Unable to find the recipe: \"" + recipeName + "\" in the" + " tag.";
                String errorMessage2 = "All the recipe before \"" + recipeName + "\" (if any) are "
                        + "successfully added from the tag.";
                throw new NoRecipeException(String.format("%-97s|\n| %-97s", errorMessage1, errorMessage2));
            }
            recipeList.addRecipeToTag(tag, recipe);
        }
        return tag;
    }

    /**
     * Extract the tag label and recipes. Then, proceed to remove the recipes from the tag,
     * and returns the tag label that users modified
     *
     * @author junenita
     * @param command string contain tag label and recipes that users want to remove
     * @param recipeList list containing all recipes data
     * @return tag label that is modified
     * @throws NoRecipeException If users entered invalid recipe.
     * @throws RecipeNotFoundInTagException If users try to remove a recipe that is not in the tag.
     * @throws TagNotFoundException If users try to remove recipes from a tag that has not been created.
     */
    public String parseRemoveRecipeTag(String command, RecipeList recipeList)
            throws RecipeNotFoundInTagException, TagNotFoundException, NoRecipeException {
        String tag;
        Recipe recipe;
        String[] recipesToRemove;
        boolean isUnableToFindTag;
        boolean isNoRecipeInTheList;
        String[] args = command.trim().split(">>");
        boolean isNotEnoughArgs = args.length < 2 || args[0].equals("") || args[1].equals("");

        if (isNotEnoughArgs) {
            throw new IllegalArgumentException("Please enter the command in the correct format.");
        }

        tag = args[0].trim();
        isUnableToFindTag = !recipeList.tags.containsKey(tag);
        if (isUnableToFindTag) {
            throw new TagNotFoundException("There is no \"" + tag + "\" tag found. Please make sure you have "
                    + "entered the correct tag.");
        }

        recipesToRemove = args[1].split("&&");
        for (String recipeName : recipesToRemove) {
            recipeName = recipeName.trim();
            recipe = recipeList.findByName(recipeName);
            isNoRecipeInTheList = recipe == null;
            if (isNoRecipeInTheList) {
                String errorMessage1 = "Unable to find the recipe: \"" + recipeName + "\" in the" + " tag.";
                String errorMessage2 = "All the recipe before \"" + recipeName + "\" (if any) are "
                        + "successfully removed from the tag.";
                throw new NoRecipeException(String.format("%-97s|\n| %-97s", errorMessage1, errorMessage2));
            }

            try {
                recipeList.removeRecipeFromTag(tag, recipe);
            } catch (IndexOutOfBoundsException | RecipeNotFoundInTagException e) {
                String errorMessage1 = "Unable to find the recipe: \"" + recipeName + "\" in the" + " tag.";
                String errorMessage2 = "All the recipe before \"" + recipeName + "\" (if any) are "
                        + "successfully removed from the tag.";
                throw new RecipeNotFoundInTagException(
                        String.format("%-97s|\n| %-97s", errorMessage1, errorMessage2));
            }
        }
        return tag;
    }

    /**
     * Extract the filters from users' input. Then, proceed to extract the recipes by the filters.
     *
     * @author junenita
     * @param inputs array containing string of inputs, including the filter
     * @param recipeList list containing all recipes data
     * @return list of recipes that are filtered by the input
     * @throws NoRecipeException If users entered invalid recipe.
     * @throws TagNotFoundException If users try to remove recipes from a tag that has not been created.
     */
    public RecipeList parseListRecipe(String[] inputs, RecipeList recipeList)
            throws TagNotFoundException, NoRecipeException {
        String[] filters;
        RecipeList recipeListToPrint;
        boolean hasTagArgs = inputs.length > 2;
        boolean isOnlyListWordInCommand = inputs.length == 1;
        boolean isTag = false;
        int firstArgsIndex = 1;

        if (isOnlyListWordInCommand) {
            filters = null;
        } else {
            isTag = inputs[1].equals("/t");
            if (isTag && !hasTagArgs) {
                throw new TagNotFoundException("Please include at least a tag.");
            } else if (isTag) {
                firstArgsIndex = 2;
            }

            StringBuilder filterString = new StringBuilder(inputs[firstArgsIndex]);
            for (int i = firstArgsIndex + 1; i < inputs.length; i++) {
                filterString.append(" ").append(inputs[i]);
            }

            filters = filterString.toString().split("&&");
        }
        recipeListToPrint = recipeList.listRecipes(filters, isTag);
        return recipeListToPrint;
    }

    /**
     * Extract the recipe index that the users wishes to view. Then, proceed to extract the recipe and returns
     * the recipe.
     *
     * @author jaredoong
     * @param command string contain `view` and recipe index
     * @param recipes list containing all recipes data
     * @return recipe that user wishes to view
     * @throws NumberFormatException          If users entered invalid recipe index.
     * @throws ArrayIndexOutOfBoundsException If users did not enter a recipe index.
     * @throws IndexOutOfBoundsException      If users entered a recipe index that is out of bounds.
     */
    public Recipe parseViewRecipe(String[] command, RecipeList recipes) {
        assert command[0].equals("view");
        Recipe requestedRecipe;
        int recipeIndex = 0;
        try {
            recipeIndex = Integer.parseInt(command[1]);
            requestedRecipe = recipes.get(recipeIndex - 1);
        } catch (NumberFormatException error) {
            String errorMessage = String.format(
                    "Please enter a valid recipe number. You entered %s, " + "which is not a valid number.",
                    command[1]);
            throw new NumberFormatException(errorMessage);
        } catch (ArrayIndexOutOfBoundsException error) {
            String errorMessage = "Please enter a valid recipe number. You did not enter a recipe number.";
            throw new ArrayIndexOutOfBoundsException(errorMessage);
        } catch (IndexOutOfBoundsException error) {
            String errorMessage = String.format(
                    "Please enter a valid recipe number. You entered %d, " + "which is out of bounds.",
                    recipeIndex);
            throw new IndexOutOfBoundsException(errorMessage);
        }
        return requestedRecipe;
    }

    public Recipe parseViewRecipe(String recipeName, RecipeList recipes) {
        int recipeIndex = 1;
        for (Recipe recipe : recipes) {
            if (recipe.getName().equals(recipeName)) {
                break;
            }
            recipeIndex++;
        }
        return recipes.get(recipeIndex - 1);
    }

    /**
     * Returns a Recipe object that contain a recipe's name and ingredients.
     *
     * @author junenita
     * @param recipes an object containing all recipes data
     * @return a random recipe from the input recipes.
     * @throws NoRecipeException If recipes.size() == 0.
     */
    public Recipe parseRandomRecipe(RecipeList recipes) throws NoRecipeException {
        if (recipes.size() == 0) {
            throw new NoRecipeException("There is no recipe in the list for random.");
        }
        return recipes.randomRecipe();
    }

    /**
     * Checks whether the user wants to edit single, multiple, or clear all the recipes in the weekly plan.
     * Then, return a WeeklyPlan object that contains the recipes that the user wants to add or delete.
     *
     * @author jaredoong
     * @param command an array containing the user input
     * @param recipes an object containing all recipes data
     * @return a WeeklyPlan object that contains the recipes that the user wants to add or delete
     * @throws NumberFormatException      If users entered invalid character for the quantity.
     * @throws InvalidRecipeNameException If users entered invalid recipe name.
     * @throws InvalidValueException      If users entered invalid quantity.
     * @throws IllegalArgumentException   If users did not enter a valid command.
     **/
    public WeeklyPlan parseWeeklyPlan(String[] command, RecipeList recipes)
            throws IllegalArgumentException, NumberFormatException, InvalidRecipeNameException,
            InvalidValueException {
        WeeklyPlan updatedWeeklyPlan;
        switch (command[1]) {
        case "/add":
        case "/delete":
            updatedWeeklyPlan = parseEditSingleWeeklyPlan(command, recipes);
            break;
        case "/multiadd":
        case "/multidelete":
            updatedWeeklyPlan = parseEditMultiWeeklyPlan(command, recipes);
            break;
        case "/clear":
            updatedWeeklyPlan = new WeeklyPlan();
            break;
        default:
            throw new IllegalArgumentException(
                    "Please indicate if you would want to add to, delete from, or clear the weekly "
                            + "plan.");
        }

        return updatedWeeklyPlan;
    }

    /**
     * Parses the user input to extract the single recipe that the user wants to add or delete from the weekly
     * plan.
     *
     * @author jaredoong
     * @param command an array containing the user input
     * @param recipes an object containing all recipes data
     * @return a WeeklyPlan object that contains the single recipe and the quantity that the user wants to add
     *     or delete
     * @throws NumberFormatException      If users entered invalid character for the quantity.
     * @throws InvalidRecipeNameException If users entered invalid recipe name.
     * @throws InvalidValueException      If users entered invalid quantity.
     * @throws IllegalArgumentException   If users did not enter a valid command.
     */
    private WeeklyPlan parseEditSingleWeeklyPlan(String[] command, RecipeList recipes)
            throws IllegalArgumentException, NumberFormatException, InvalidValueException,
            InvalidRecipeNameException {
        int numDays;

        if (command.length < 4) {
            throw new IllegalArgumentException("Please enter the command in the correct format.");
        }

        try {
            numDays = Integer.parseInt(command[command.length - 1]);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Please enter a number between 1 to 1000 for the quantity.");
        }

        if (numDays > 1000 || numDays < 1) {
            throw new InvalidValueException("Please enter a number between 1 to 1000 for the quantity.");
        }

        int nameLastIndex = command.length - 1;
        WeeklyPlan edits = new WeeklyPlan();
        StringBuilder recipeName = new StringBuilder(command[2]);
        for (int i = 3; i < nameLastIndex; i++) {
            recipeName.append(" ").append(command[i]);
        }

        if (recipes.findByName(recipeName.toString().trim()) != null) {
            edits.put(recipeName.toString(), numDays);
            return edits;
        } else {
            throw new InvalidRecipeNameException("Please indicate a valid recipe name.");
        }
    }

    /**
     * Parses the user input to extract the multiple recipes that the user wants to add or delete from the
     * weekly plan.
     *
     * @author jaredoong
     * @param command an array containing the user input
     * @param recipes an object containing all recipes data
     * @return a WeeklyPlan object that contains the multiple recipes and the quantities that the user wants
     *     to add or delete
     * @throws NumberFormatException      If users entered invalid character for the quantity.
     * @throws InvalidRecipeNameException If users entered invalid recipe name.
     * @throws InvalidValueException      If users entered invalid quantity.
     * @throws IllegalArgumentException   If users did not enter a valid command.
     */
    private WeeklyPlan parseEditMultiWeeklyPlan(String[] command, RecipeList recipes)
            throws IllegalArgumentException, NumberFormatException, InvalidValueException,
            InvalidRecipeNameException {
        int quantity;
        if (command.length < 6) {
            throw new IllegalArgumentException("Please enter the command in the correct format.");
        }

        WeeklyPlan recipesToEdit = new WeeklyPlan();
        ArrayList<Integer> quantities = new ArrayList<>();
        ArrayList<String> recipeNames = new ArrayList<>();
        ArrayList<Integer> startIndices = new ArrayList<>();
        ArrayList<Integer> endIndices = new ArrayList<>();
        StringBuilder recipeName = new StringBuilder();

        for (int i = 0; i < command.length; i++) {
            if (command[i].equals("/r")) {
                startIndices.add(i);
            } else if (command[i].equals("/q")) {
                endIndices.add(i);
                try {
                    quantity = Integer.parseInt(command[i + 1]);
                } catch (NumberFormatException e) {
                    throw new NumberFormatException(
                            "Please enter a positive number between 1 to 1000 for the quantity.");
                }
                if (quantity < 1 || quantity > 1000) {
                    throw new InvalidValueException(
                            "Please enter a positive number between 1 to 1000 for the quantity.");
                }
                quantities.add(quantity);
            }
        }

        // Checks that command is entered in the correct
        if (startIndices.size() != endIndices.size() || startIndices.size() == 0) {
            throw new IllegalArgumentException("Please enter the command in the correct format.");
        }

        // Building the recipe names
        for (int i = 0; i < startIndices.size(); i++) {
            int nameStartIndex = startIndices.get(i) + 1;
            int nameEndIndex = endIndices.get(i) - 1;
            recipeName = getRecipeNames(command, recipeNames, recipeName, nameStartIndex, nameEndIndex);
        }

        // Add each recipe to the weekly plan
        try {
            for (int i = 0; i < recipeNames.size(); i++) {
                if (recipes.findByName(recipeNames.get(i)) != null) {
                    recipesToEdit.put(recipeNames.get(i), quantities.get(i));
                } else {
                    throw new InvalidRecipeNameException("Please indicate a valid recipe name.");
                }
            }
        } catch (NumberFormatException error) {
            throw new NumberFormatException(
                    "Please enter a positive number between 1 to 1000 for the quantity.");
        }

        return recipesToEdit;
    }

    private StringBuilder getRecipeNames(String[] command, ArrayList<String> recipeNames,
            StringBuilder recipeName, int nameStartIndex, int nameEndIndex) {
        recipeName.append(command[nameStartIndex].toLowerCase().trim());
        for (int j = nameStartIndex + 1; j <= nameEndIndex; j++) {
            recipeName.append(" ").append(command[j].toLowerCase().trim());
        }
        recipeNames.add(recipeName.toString());
        recipeName = new StringBuilder();
        return recipeName;
    }

    // @@author
    // parser to read dd/mm/yyyy format as local date catching invalid date format
    public LocalDate parseDate(String input) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date = null;
        try {
            date = LocalDate.parse(input, formatter);
            return date;
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Please enter a valid date in the format dd/mm/yyyy.");
        }
    }

    /**
     * Parses the user input to extract the ingredient name, quantity, and expiry date that the user wants to
     * add to the ingredient list. Returns the Ingredient object that contains parsed data.
     *
     * @author jaredoong
     * @param command an array containing the user input
     * @return an Ingredient object that contains the ingredient name, quantity, and expiry date
     * @throws NumberFormatException    If users entered invalid character for the quantity.
     * @throws InvalidValueException    If users entered invalid quantity.
     * @throws IllegalArgumentException If users did not enter a valid command.
     */
    public Ingredient parseAddUserIngredients(String[] command)
            throws NumberFormatException, IllegalArgumentException, InvalidValueException {
        String ingredientName = null;
        Integer ingredientCount = null;
        String expiryDate = null;

        for (int i = 1; i < command.length; i++) {
            switch (command[i]) {
            case "/n":
                StringBuilder nameBuilder = new StringBuilder();
                while (++i < command.length && !command[i].startsWith("/")) {
                    if (nameBuilder.length() > 0) {
                        nameBuilder.append(" ");
                    }
                    nameBuilder.append(command[i]);
                }
                ingredientName = nameBuilder.toString();
                i--; // Move back to the previous flag
                break;
            case "/c":
                try {
                    ingredientCount = Integer.parseInt(command[++i]);
                } catch (NumberFormatException e) {
                    throw new NumberFormatException(
                            "Please enter a positive number between 1 to 1000 for the quantity.");
                }

                if (ingredientCount < 1 || ingredientCount > 1000) {
                    throw new InvalidValueException(
                            "Please enter a positive number between 1 to 1000 for the quantity.");
                }
                break;
            case "/d":
                expiryDate = command[++i];
                break;
            default:
                throw new IllegalArgumentException("Missing required information. Please provide "
                        + "ingredient name, count, and expiry date.");
            }
        }

        if (ingredientName == null || ingredientCount == null || expiryDate == null) {
            throw new IllegalArgumentException("Missing required information. Please use /n, /c, and /d.");
        }

        return new Ingredient(ingredientName, ingredientCount, expiryDate);
    }

    /**
     * Parses the user input to extract the ingredient name and quantity that the user wants to delete from
     * the ingredient list. Returns the Ingredient object that contains parsed data.
     *
     * @author jaredoong
     * @param command an array containing the user input
     * @return an Ingredient object that contains the ingredient name, quantity, and dummy expiry date
     * @throws NumberFormatException       If users entered invalid character for the quantity.
     * @throws IngredientNotFoundException If users entered invalid ingredient name.
     * @throws InvalidValueException       If users entered invalid quantity.
     * @throws IllegalArgumentException    If users did not enter a valid command.
     */
    public Ingredient parseDeleteUserIngredients(String[] command)
            throws NumberFormatException, IllegalArgumentException, IngredientNotFoundException,
            InvalidValueException {
        String ingredientName = null;
        Integer ingredientCount = null;

        for (int i = 1; i < command.length; i++) {
            switch (command[i]) {
            case "/n":
                StringBuilder nameBuilder = new StringBuilder();
                while (++i < command.length && !command[i].startsWith("/")) {
                    if (nameBuilder.length() > 0) {
                        nameBuilder.append(" ");
                    }
                    nameBuilder.append(command[i]);
                }
                ingredientName = nameBuilder.toString();
                i--; // Move back to the previous flag
                break;
            case "/c":
                try {
                    ingredientCount = Integer.parseInt(command[++i]);
                } catch (NumberFormatException e) {
                    throw new NumberFormatException(
                            "Please enter a positive number between 1 to 1000 for the quantity.");
                }

                if (ingredientCount < 1 || ingredientCount > 1000) {
                    throw new InvalidValueException(
                            "Please enter a positive number between 1 to 1000 for the quantity.");
                }
                break;
            default:
                throw new IllegalArgumentException(
                        "Missing required information. Please provide " + "ingredient name and count.");
            }
        }

        if (ingredientName == null || ingredientCount == null) {
            throw new IllegalArgumentException("Missing required information. Please use /n and /c.");
        }

        return new Ingredient(ingredientName, ingredientCount, "01/01/2020");
    }

    /**
     * Parses the user input to extract the recipe which the user wants to mark as done. Returns the recipe
     * name to be deleted from the weekly plan.
     *
     * @author jaredoong
     * @param command         an array containing the user input
     * @param userIngredients an object containing all ingredients data
     * @param weeklyPlan      an object containing all weekly plan data
     * @param recipes         an object containing all recipes data
     * @return a string containing the recipe name to be deleted from the weekly plan.
     * @throws NumberFormatException       If users entered invalid character for the quantity.
     * @throws IngredientNotFoundException If users entered invalid ingredient name.
     * @throws IllegalArgumentException    If users enter a recipe name that does not exist in the weekly
     *                                     plan, or if the user does not have enough ingredients to make the
     *                                     recipe.
     */
    public String parseMarkDone(String[] command, IngredientList userIngredients, WeeklyPlan weeklyPlan,
            RecipeList recipes)
            throws IllegalArgumentException, IngredientNotFoundException, InvalidRecipeNameException {
        if (command.length == 2) {
            throw new IllegalArgumentException("Please enter a recipe name.");
        }
        String recipeName = combineWords(command, 2, command.length);
        if (recipes.findByName(recipeName) == null) {
            throw new InvalidRecipeNameException("Please enter a valid recipe name.");
        }

        if (weeklyPlan.get(recipeName) == null) {
            throw new IllegalArgumentException("Recipe does not exist in weekly plan.");
        }

        HashMap<String, Integer> ingredients = recipes.findByName(recipeName).getIngredients();
        for (String ingredient : ingredients.keySet()) {
            int currentCount = userIngredients.findIngredientCount(ingredient);
            if (currentCount < ingredients.get(ingredient)) {
                throw new IllegalArgumentException(
                        "You do not have enough ingredients to mark this recipe as done.");
            }
        }

        return recipeName;
    }
}
