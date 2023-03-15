package seedu.duke;

import seedu.duke.command.ExecutableCommand;
import seedu.duke.command.factory.ExecutableCommandFactory;
import seedu.duke.command.factory.ingredients.IngredientsListCommandFactory;
import seedu.duke.command.factory.misc.AddCommandFactory;
import seedu.duke.command.factory.misc.ByeCommandFactory;
import seedu.duke.command.factory.misc.HelloWorldCommandFactory;
import seedu.duke.command.factory.misc.RecipeAllCommandFactory;
import seedu.duke.command.factory.misc.RecipeDetailCommandFactory;
import seedu.duke.command.factory.misc.RecipePossibleCommandFactory;
import seedu.duke.command.factory.misc.RemoveCommandFactory;
import seedu.duke.command.factory.misc.HelpCommandFactory;
import seedu.duke.ingredient.IngredientList;
import seedu.duke.parser.CommandArguments;
import seedu.duke.parser.CommandTokens;
import seedu.duke.recipe.RecipeList;
import seedu.duke.router.CommandRouterNode;
import seedu.duke.storage.IngredientStorage;
import seedu.duke.ui.DukeUI;

import java.util.Scanner;

public class DukeSession {

    private static final CommandRouterNode COMMAND_TREE =
            new CommandRouterNode()

                    .route("hello", new CommandRouterNode()
                            .route("world", new HelloWorldCommandFactory())
                    )
                    .route("bye", new ByeCommandFactory())
                    .route("add", new AddCommandFactory())
                    .route("help", new HelpCommandFactory())
                    .route("remove", new RemoveCommandFactory())
                    .route("recipe", new CommandRouterNode()
                            .route("possible", new RecipePossibleCommandFactory())
                            .route("all", new RecipeAllCommandFactory()))
                    .route("recipe", new RecipeDetailCommandFactory())
                    .route("ingredients", new CommandRouterNode()
                            .route("list", new IngredientsListCommandFactory())
                    );
    private final IngredientList ingredients;
    private final RecipeList recipes;
    private final DukeUI ui;
    private final DukeControlFlow controlFlow;
    private final IngredientStorage ingredientStorage;




    public DukeSession() {
        this.ui = new DukeUI(new Scanner(System.in));
        this.controlFlow = new DukeControlFlow();
        this.ingredients = new IngredientList();
        this.recipes = new RecipeList();
        this.ingredientStorage = new IngredientStorage();
    }

    /**
     * Returns the <code>DukeUI</code> for the current session.
     *
     * @return Handle to control UI.
     */
    public DukeUI getUi() {
        return ui;
    }

    /**
     * Returns the <code>DukeControlFlow</code> for the current session.
     *
     * @return Handle to set the control flow.
     */
    public DukeControlFlow getControlFlow() {
        return controlFlow;
    }

    /**
     * Returns the <code>IngredientList</code> for the current session.
     *
     * @return Handle to access the Ingredient List.
     */
    public IngredientList getIngredients() {
        return ingredients;
    }

    /**
     * Returns the <code>RecipeList</code> for the current session.
     *
     * @return Handle to access the Recipe List.
     */
    public RecipeList getRecipes() {
        return recipes;
    }

    public IngredientStorage getIngredientStorage() {
        return ingredientStorage;
    }

    /**
     * Runs the read, evaluate, print loop for Duke.
     */
    public void runDuke() {
        ingredientStorage.getFile(this.ingredients);
        this.ui.printIntroduction();
        while (this.controlFlow.shouldRun()) {
            String nextCommand = ui.getNextCommandString();
            CommandTokens tokens = new CommandTokens(nextCommand);
            ExecutableCommandFactory commandFactory = this.COMMAND_TREE.resolve(tokens);

            if (commandFactory == null) {
                System.out.println("Not a command!");
                continue;
            }

            ExecutableCommand cmd = commandFactory.buildCommand(this, new CommandArguments(tokens));
            cmd.execute(this);
        }
    }
}
