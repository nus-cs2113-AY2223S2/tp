package seedu.duke;

import seedu.duke.command.Command;
import seedu.duke.parser.Parser;
import seedu.duke.recipe.RecipeList;
import seedu.duke.storage.Storage;
import seedu.duke.ui.UI;

/**
 * Represents the Recipe Manager programme. A <code>Duke</code> object corresponds to
 * a Recipe Manager.
 */
public class Duke {

    private final RecipeList recipes;
    private final UI ui = new UI();
    /**
     * Class constructor specifying filePath for saving data.
     *
     * @param filePath a relative file path giving the location of the saved file.
     */
    public Duke(String filePath) {
        Storage.setFilePath(filePath);
        try {
            Storage.createDirectory();
        } catch (Exception e) {
            ui.showLoadingErrorMessage(e);
        } finally {
            recipes = new RecipeList();
            ui.showLine();
        }
    }

    /**
     * Starts the Duke programme and continuously take in user inputs until
     * it is terminated by the user.
     */
    public void run() {
        ui.showWelcome();
        ui.showLine();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parseCommands(fullCommand);
                c.execute(recipes,ui);
                isExit = c.isExit();
            } catch (Exception e) {
                ui.showDudeMainError(e);
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * The main method that runs the entire programme.
     */
    public static void main(String[] args) {
        new Duke("data/recipeList.txt").run();
        System.exit(0);
    }
}
