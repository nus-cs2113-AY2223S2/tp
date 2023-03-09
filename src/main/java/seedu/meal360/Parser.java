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

    public void parseListRecipe(String[] input) {
        // list recipe
    }

    public RecipeList parseLoadDatabase(String input) {
        return new RecipeList();
    }

    public String parseSaveDatabase(String input) {
        return "test";
    }
}
