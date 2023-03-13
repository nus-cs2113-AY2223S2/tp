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

    public String parseDeleteRecipe(String[] input) {
        return "test";
    }

    public String parsePrepareRecipe(String[] input) {
        return "test";
    }

    public RecipeList parseListRecipe(String[] input, RecipeList recipeList) {
        RecipeList recipeListToPrint = new RecipeList();
        String filter;
        if (input.length == 1) {
            filter = null;
        } else {
            filter = input[1];
        }
        return recipeList.listRecipes(filter);
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
