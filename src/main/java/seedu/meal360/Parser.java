package seedu.meal360;

import java.util.HashMap;
import java.util.Scanner;

public class Parser {

    private static final Ui ui = new Ui();

    public String[] parseCommand(String command) {
        return command.split(" ");
    }

    public Recipe parseAddRecipe(String[] input, RecipeList recipeList) {

        StringBuilder recipeName = new StringBuilder(input[2]);
        for(int i = 3; i < input.length; i++){
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
                for(int i=1; i < command.length-1; i++){
                    iName.append(" ").append(command[i]);
                }
                ingredients.put(iName.toString(), Integer.parseInt(command[command.length-1]));
            }
        }
        Recipe newRecipe = new Recipe(recipeName.toString(), ingredients);
        recipeList.addRecipe(newRecipe);
        return newRecipe;
    }

    public Recipe parseAddRecipe(String[] input) {
        return new Recipe("test", null);
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

    public void parseHelpUser(){
        ui.printSeparator();
        System.out.println("These are the operations you can do. Please follow the proper input" +
                            " formats while typing.");
        ui.printSeparator();
        System.out.println("1. Add Recipe: add /r {recipe name}");
        System.out.println("2. View Recipe: view {index number} or view /r {recipe name}");
        System.out.println("3. Edit Recipe: edit {index number} or view /r {recipe name}");
        System.out.println("4. Delete Recipe: delete {index number} or view /r {recipe name}");
        System.out.println("5. List All Recipes: list");
        System.out.println("6. Exit: bye");
        ui.printSeparator();
    }

    public String parsePrepareRecipe(String[] input) {
        return "test";
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
        int recipeIndex = Integer.parseInt(command[1]) - 1;
        return recipes.get(recipeIndex);
    }

    public RecipeList parseLoadDatabase(String input) {
        return new RecipeList();
    }

    public String parseSaveDatabase(String input) {
        return "test";
    }
}
