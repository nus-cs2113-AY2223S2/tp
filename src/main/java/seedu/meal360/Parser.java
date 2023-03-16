package seedu.meal360;

import java.util.HashMap;
import java.util.Scanner;

public class Parser {

    Ui ui = new Ui();

    public String combineWords(String[] input, int startIndex){
        StringBuilder word = new StringBuilder(input[startIndex]);
        for(int i = startIndex+1; i< input.length-1; i++) {
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
                String iName = combineWords(command, 0);
                ingredients.put(iName, Integer.parseInt(command[command.length - 1]));
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
        if(index == 1){
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
        } else if(index == 2){
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
                if(ingredientIndex == count){
                    ingredientToRemove = ingredient;
                    System.out.println("Ingredient to be changed:");
                    ui.printSeparator();
                    String toPrint = String.format("%s(%d)", ingredient, recipeToEdit.getIngredients().get(ingredient));
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
            recipeToEdit.getIngredients().put(newIngredientName, Integer.parseInt(command[command.length-1]));
            recipeList.editRecipe(recipeToEdit, recipeToEdit.getIngredients());
        }

        return recipeToEdit;
    }

    public Recipe parseDeleteRecipe(String[] input, RecipeList recipeList) {
        // user inputted recipe name
        if (input[1].contains("r/")) {
            // skip over /r in recipe name
            String recipeName = input[1].substring(2);
            int recipeIndex = 1;
            for (Recipe recipe : recipeList) {
                // find index of recipe we want to delete
                if (recipe.getName().equals(recipeName)) {
                    break;
                }
                recipeIndex++;
            }
            return recipeList.deleteRecipe(recipeIndex);
            // user inputted index of recipe in list
        } else {
            int recipeIndex = Integer.parseInt(input[1]);
            return recipeList.deleteRecipe(recipeIndex);
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
    public Recipe parseViewRecipe(String recipeName, RecipeList recipes){
        int recipeIndex = 1;
        for (Recipe recipe : recipes) {
            if (recipe.getName().equals(recipeName)) {
                break;
            }
            recipeIndex++;
        }
        return recipes.get(recipeIndex-1);
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
