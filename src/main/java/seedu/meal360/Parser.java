package seedu.meal360;

public class Parser {

    public String[] parseCommand(String command) {
        return command.split(" ");
    }

    public Recipe parseAddRecipe(String[] input) {
        return new Recipe("test", null);
    }

    public Recipe parseEditRecipe(String[] input) {
        return new Recipe("test", null);
    }

    public Recipe parseDeleteRecipe(String[] input, RecipeList recipeList) {
        // user inputted recipe name
        if (input[1].contains("/r")) {
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

    public String parsePrepareRecipe(String[] input) {
        return "test";
    }

    public void parseListRecipe(String[] input) {
        // list recipe
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
