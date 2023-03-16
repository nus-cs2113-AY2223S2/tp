package seedu.meal360;

import java.util.HashMap;
import java.util.Scanner;

public class Parser {

    public Recipe parseAddRecipe(String[] input, RecipeList recipeList) {
        StringBuilder recipeName = new StringBuilder(input[2]);
        for (int i = 3; i < input.length; i++) {
            recipeName.append(" ").append(input[i]);
        }
        HashMap<String, Integer> ingredients = new HashMap<>();
        Scanner userInput = new Scanner(System.in);
        System.out.println("Please Enter The Ingredients & Quantity: ");
        while (true) {
            String line = userInput.nextLine();
            if (line.equals("done")) {
                break;
            } else {
                String[] command = line.trim().split(" ");
                StringBuilder iName = new StringBuilder(command[0]);
                for (int i = 1; i < command.length - 1; i++) {
                    iName.append(" ").append(command[i]);
                }
                ingredients.put(iName.toString(), Integer.parseInt(command[command.length - 1]));
            }
        }
        Recipe newRecipe = new Recipe(recipeName.toString(), ingredients);
        recipeList.addRecipe(newRecipe);
        return newRecipe;
    }

    public Recipe parseEditRecipe(String[] input, RecipeList recipeList) {
        String recipeName = input[1].substring(2);
        Recipe recipeToEdit;
        HashMap<String, Integer> ingredients = new HashMap<>();
        Scanner userInput = new Scanner(System.in);
        if (recipeList.findByName(recipeName) == null) {
            // ui recipe not found
            return null;
        } else {
            recipeToEdit = recipeList.findByName(recipeName);
        }
        System.out.println("Please Enter New Ingredients & Quantity: ");
        while (true) {
            String line = userInput.nextLine();
            if (line.equals("done")) {
                break;
            }
            String[] command = line.trim().split(" ");
            ingredients.put(command[0], Integer.parseInt(command[1]));
        }
        recipeList.editRecipe(recipeToEdit, ingredients);
        return recipeToEdit;
    }

    public String parseDeleteRecipe(String[] input, RecipeList recipeList) {
        // user inputted recipe name
        if (input[1].contains("r/")) {
            // skip over /r in recipe name
            String recipeToDelete = input[1].substring(2);
            if (recipeToDelete.equals("all")) {
                String allRecipes = "";
                System.out.println("recipeList size: " + recipeList.size());
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
                String rangeRecipes = "";
                int startIndex = Integer.parseInt(input[1].charAt(0) + "");
                int endIndex = Integer.parseInt(input[1].charAt(2) + "");
                startIndex -= 1;
                endIndex -= 1;
                int newSize = recipeList.size() - ((endIndex - startIndex) + 1);
                while (recipeList.size() != newSize) {
                    rangeRecipes += recipeList.deleteRecipe(startIndex).getName() + ", ";
                }
                rangeRecipes = rangeRecipes.substring(0, rangeRecipes.length() - 2);
                return rangeRecipes;
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
            filters = inputs[1].split("&");
        }
        return recipeList.listRecipes(filters);
    }

    public Recipe parseViewRecipe(String[] command, RecipeList recipes) {
        assert command[0].equals("view");
        int recipeIndex = Integer.parseInt(command[1]) - 1;
        return recipes.get(recipeIndex);
    }

    public WeeklyPlan parseWeeklyPlan(String[] command, RecipeList recipes) {
        if (!command[1].equals("/add") && !command[1].equals("/delete")) {
            throw new IllegalArgumentException(
                    "Please indicate if you would want to add or delete the recipe from your weekly "
                            + "plan.");
        }

        int numDays = 0;
        if (command[1].equals("/add")) {
            numDays = Integer.parseInt(command[command.length - 1]);
            if (numDays < 1) {
                throw new NumberFormatException();
            }
        }

        int nameLastIndex = (command[1].equals("/add")) ? command.length - 1 : command.length;
        WeeklyPlan thisWeekPlan = new WeeklyPlan();
        StringBuilder recipeName = new StringBuilder(command[2]);
        for (int i = 3; i < nameLastIndex; i++) {
            recipeName.append(" ").append(command[i]);
        }

        if (recipes.findByName(recipeName.toString().trim()) != null) {
            thisWeekPlan.put(recipeName.toString(), numDays);
            return thisWeekPlan;
        } else {
            throw new IllegalArgumentException("Please indicate a valid recipe name.");
        }
    }

    public RecipeList parseLoadDatabase(String input) {
        return new RecipeList();
    }

    public String parseSaveDatabase(String input) {
        return "test";
    }
}
