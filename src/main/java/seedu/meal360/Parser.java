package seedu.meal360;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import seedu.meal360.exceptions.InvalidNegativeValueException;
import seedu.meal360.exceptions.InvalidRecipeNameException;

public class Parser {

    Ui ui = new Ui();

    public String combineWords(String[] input, int startIndex) {
        StringBuilder word = new StringBuilder(input[startIndex]);
        for (int i = startIndex + 1; i < input.length - 1; i++) {
            word.append(" ").append(input[i]);
        }

        return word.toString();
    }

    public Recipe parseAddRecipe(String[] input, RecipeList recipeList) {
        String recipeName = combineWords(input, 2);
        HashMap<String, Integer> ingredients = new HashMap<>();
        Scanner userInput = new Scanner(System.in);
        System.out.println("Please Enter The Ingredients & Quantity: ");
        while (true) {
            String line = userInput.nextLine();
            if (line.equals("done")) {
                ui.printSeparator();
                break;
            } else {
                String[] command = line.trim().split(" ");
                String ingredientName = combineWords(command, 0);
                ingredients.put(ingredientName, Integer.parseInt(command[command.length - 1]));
            }
        }
        Recipe newRecipe = new Recipe(recipeName, ingredients);
        recipeList.addRecipe(newRecipe);
        return newRecipe;
    }

    public Recipe parseEditRecipe(String[] input, RecipeList recipeList) {
        String recipeName = combineWords(input, 2);
        Recipe recipeToEdit;
        HashMap<String, Integer> ingredients = new HashMap<>();
        Scanner userInput = new Scanner(System.in);
        if (recipeList.findByName(recipeName) == null) {
            return null;
        } else {
            recipeToEdit = recipeList.findByName(recipeName);
        }
        System.out.println("Do you want to edit recipe fully or partially?");
        System.out.println("Press 1 for full edit | Press 2 for partial edit");

        Scanner getInput = new Scanner(System.in);
        int index = getInput.nextInt();
        if (index == 1) {
            System.out.println("Please Enter New Ingredients & Quantity: ");
            while (true) {
                String line = userInput.nextLine();
                if (line.equals("done")) {
                    break;
                }
                String[] command = line.trim().split(" ");
                ingredients.put(command[0], Integer.parseInt(command[1]));
                recipeList.editRecipe(recipeToEdit, ingredients);
            }
        } else if (index == 2) {
            System.out.println("These are the ingredients for the recipe:");
            ui.printSeparator();
            Recipe recipe = parseViewRecipe(recipeName, recipeList);
            ui.printRecipe(recipe);
            ui.printSeparator();
            System.out.println("Which ingredient do you want to change?");
            int ingredientIndex = getInput.nextInt();
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
            String line = userInput.nextLine();
            String[] command = line.trim().split(" ");
            String newIngredientName = combineWords(command, 0);
            recipeToEdit.getIngredients().remove(ingredientToRemove);
            recipeToEdit.getIngredients()
                    .put(newIngredientName, Integer.parseInt(command[command.length - 1]));
            recipeList.editRecipe(recipeToEdit, recipeToEdit.getIngredients());
        }

        return recipeToEdit;
    }

    public String parseDeleteRecipe(String[] input, RecipeList recipeList) {
        // user inputted recipe name
        if (input[1].contains("r/")) {
            // skip over /r in recipe name
            String recipeToDelete = input[1].substring(2);
            if (recipeToDelete.equals("all")) {
                StringBuilder allRecipes = new StringBuilder();
                System.out.println("recipeList size: " + recipeList.size());
                int index = 0;
                while (recipeList.size() != 0) {
                    allRecipes.append(recipeList.deleteRecipe(index).getName()).append(", ");
                }
                allRecipes = new StringBuilder(allRecipes.substring(0, allRecipes.length() - 2));
                return allRecipes.toString();
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
                StringBuilder rangeRecipes = new StringBuilder();
                int startIndex = Integer.parseInt(input[1].charAt(0) + "");
                int endIndex = Integer.parseInt(input[1].charAt(2) + "");
                startIndex -= 1;
                endIndex -= 1;
                int newSize = recipeList.size() - ((endIndex - startIndex) + 1);
                while (recipeList.size() != newSize) {
                    rangeRecipes.append(recipeList.deleteRecipe(startIndex).getName()).append(", ");
                }
                rangeRecipes = new StringBuilder(rangeRecipes.substring(0, rangeRecipes.length() - 2));
                return rangeRecipes.toString();
            } else {
                int recipeIndex = Integer.parseInt(input[1]);
                // need to subtract 1 since list is 1-based
                return recipeList.deleteRecipe(recipeIndex - 1).getName();
            }
        }
    }

    public RecipeList parseListRecipe(String[] inputs, RecipeList recipeList) {
        String[] filters;
        if (inputs.length == 1) {
            filters = null;
        } else {
            StringBuilder filterString = new StringBuilder(inputs[1]);
            for (int i = 2; i < inputs.length; i++) {
                filterString.append(" ").append(inputs[i]);
            }
            filters = filterString.toString().split("&");
        }
        return recipeList.listRecipes(filters);
    }

    public Recipe parseViewRecipe(String[] command, RecipeList recipes) {
        assert command[0].equals("view");
        Recipe requestedRecipe;
        int recipeIndex = 0;
        try {
            recipeIndex = Integer.parseInt(command[1]);
            requestedRecipe = recipes.get(recipeIndex - 1);
        } catch (NumberFormatException error) {
            String errorMessage = String.format(
                    "Please enter a valid recipe number. You entered %s, " + "which is not a number.",
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

    public WeeklyPlan parseWeeklyPlan(String[] command, RecipeList recipes)
            throws InvalidRecipeNameException, InvalidNegativeValueException {
        WeeklyPlan updatedWeeklyPlan;
        try {
            switch (command[1]) {
            case "/add":
                updatedWeeklyPlan = parseAddSingleWeeklyPlan(command, recipes);
                break;
            case "/delete":
                updatedWeeklyPlan = parseDeleteSingleWeeklyPlan(command, recipes);
                break;
            case "/multiadd":
                updatedWeeklyPlan = parseAddMultiWeeklyPlan(command, recipes);
                break;
            case "/multidelete":
                updatedWeeklyPlan = parseDeleteMultiWeeklyPlan(command, recipes);
                break;
            default:
                throw new IllegalArgumentException(
                        "Please indicate if you would want to add or delete the recipe from your weekly "
                                + "plan.");
            }
        } catch (ArrayIndexOutOfBoundsException error) {
            throw new ArrayIndexOutOfBoundsException("Insufficient number of arguments provided.");
        } catch (NumberFormatException error) {
            throw new NumberFormatException("Please enter a valid number as the last argument.");
        }
        return updatedWeeklyPlan;
    }

    private WeeklyPlan parseAddSingleWeeklyPlan(String[] command, RecipeList recipes)
            throws InvalidNegativeValueException, InvalidRecipeNameException {
        int numDays = Integer.parseInt(command[command.length - 1]);
        if (numDays < 1) {
            throw new InvalidNegativeValueException("Number of days needs to be at least 1.");
        }

        int nameLastIndex = command.length - 1;
        WeeklyPlan thisWeekPlan = new WeeklyPlan();
        StringBuilder recipeName = new StringBuilder(command[2]);
        for (int i = 3; i < nameLastIndex; i++) {
            recipeName.append(" ").append(command[i].toLowerCase().trim());
        }

        if (recipes.findByName(recipeName.toString().trim()) != null) {
            thisWeekPlan.put(recipeName.toString(), numDays);
            return thisWeekPlan;
        } else {
            throw new InvalidRecipeNameException("Please indicate a valid recipe name.");
        }
    }

    private WeeklyPlan parseDeleteSingleWeeklyPlan(String[] command, RecipeList recipes)
            throws InvalidRecipeNameException {
        int nameLastIndex = command.length;
        WeeklyPlan thisWeekPlan = new WeeklyPlan();
        StringBuilder recipeName = new StringBuilder(command[2]);
        for (int i = 3; i < nameLastIndex; i++) {
            recipeName.append(" ").append(command[i].toLowerCase().trim());
        }

        if (recipes.findByName(recipeName.toString().trim()) != null) {
            thisWeekPlan.put(recipeName.toString(), 0);
            return thisWeekPlan;
        } else {
            throw new InvalidRecipeNameException("Please indicate a valid recipe name.");
        }
    }

    private WeeklyPlan parseAddMultiWeeklyPlan(String[] command, RecipeList recipes) {
        WeeklyPlan recipesToAdd = new WeeklyPlan();
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
                quantities.add(Integer.parseInt(command[i + 1]));
            }
        }

        // Checks that command is entered in the correct
        if (startIndices.size() != endIndices.size()) {
            throw new IllegalArgumentException("Please ensure the number of recipes and quantities match.");
        }

        // Building the recipe names
        for (int i = 0; i < startIndices.size(); i++) {
            int nameStartIndex = startIndices.get(i) + 1;
            int nameEndIndex = endIndices.get(i) - 1;
            recipeName = getRecipeNames(command, recipeNames, recipeName, nameStartIndex, nameEndIndex);
        }

        // Add each recipe to the weekly plan
        for (int i = 0; i < recipeNames.size(); i++) {
            if (recipes.findByName(recipeNames.get(i)) != null) {
                recipesToAdd.put(recipeNames.get(i), quantities.get(i));
            } else {
                throw new IllegalArgumentException("Please indicate a valid recipe name.");
            }
        }

        return recipesToAdd;
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

    private WeeklyPlan parseDeleteMultiWeeklyPlan(String[] command, RecipeList recipes)
            throws InvalidRecipeNameException {
        WeeklyPlan recipesToDelete = new WeeklyPlan();
        ArrayList<String> recipeNames = new ArrayList<>();
        ArrayList<Integer> startIndices = new ArrayList<>();
        StringBuilder recipeName = new StringBuilder();

        for (int i = 0; i < command.length; i++) {
            if (command[i].equals("/r")) {
                startIndices.add(i);
            }
        }

        // Building the recipe names
        for (int i = 0; i < startIndices.size(); i++) {
            int nameStartIndex = startIndices.get(i) + 1;
            int nameEndIndex =
                    (i == startIndices.size() - 1) ? command.length - 1 : startIndices.get(i + 1) - 1;

            recipeName = getRecipeNames(command, recipeNames, recipeName, nameStartIndex, nameEndIndex);
        }

        // Add each recipe to the weekly plan
        for (String name : recipeNames) {
            if (recipes.findByName(name) != null) {
                recipesToDelete.put(name, 0);
            } else {
                throw new InvalidRecipeNameException("Please indicate a valid recipe name.");
            }
        }

        return recipesToDelete;
    }
}
