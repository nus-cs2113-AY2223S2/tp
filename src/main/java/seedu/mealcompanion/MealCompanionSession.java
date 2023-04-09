package seedu.mealcompanion;

import seedu.mealcompanion.command.ExecutableCommand;
import seedu.mealcompanion.command.factory.ExecutableCommandFactory;
import seedu.mealcompanion.command.factory.allergen.AllergenAddCommandFactory;
import seedu.mealcompanion.command.factory.allergen.AllergenListCommandFactory;
import seedu.mealcompanion.command.factory.allergen.AllergenRemoveCommandFactory;
import seedu.mealcompanion.command.factory.ingredients.IngredientsListCommandFactory;
import seedu.mealcompanion.command.factory.ingredients.IngredientsSearchCommandFactory;
import seedu.mealcompanion.command.factory.ingredients.AddCommandFactory;
import seedu.mealcompanion.command.factory.misc.ByeCommandFactory;
import seedu.mealcompanion.command.factory.misc.ClearCommandFactory;
import seedu.mealcompanion.command.factory.misc.HelloAnswerCommandFactory;
import seedu.mealcompanion.command.factory.misc.HelloPSLECommandFactory;
import seedu.mealcompanion.command.factory.misc.HelloWorldCommandFactory;
import seedu.mealcompanion.command.factory.misc.HelpCommandFactory;
import seedu.mealcompanion.command.factory.recipe.MakeCommandFactory;
import seedu.mealcompanion.command.factory.recipe.RecipeAllCommandFactory;
import seedu.mealcompanion.command.factory.recipe.RecipeAlmostCommandFactory;
import seedu.mealcompanion.command.factory.recipe.RecipeDetailCommandFactory;
import seedu.mealcompanion.command.factory.recipe.RecipeFavouriteCommandFactory;
import seedu.mealcompanion.command.factory.recipe.RecipeFindCommandFactory;
import seedu.mealcompanion.command.factory.recipe.RecipeNeedCommandFactory;
import seedu.mealcompanion.command.factory.recipe.RecipePossibleCommandFactory;
import seedu.mealcompanion.command.factory.recipe.RecipeRandomCommandFactory;
import seedu.mealcompanion.command.factory.recipe.RecipeUnfavouriteCommandFactory;
import seedu.mealcompanion.command.factory.ingredients.RemoveCommandFactory;
import seedu.mealcompanion.exception.CommandRunException;
import seedu.mealcompanion.exception.InvalidCommandException;
import seedu.mealcompanion.ingredient.IngredientList;
import seedu.mealcompanion.parser.CommandArguments;
import seedu.mealcompanion.parser.CommandTokens;
import seedu.mealcompanion.recipe.IngredientMetadata;
import seedu.mealcompanion.recipe.RecipeList;
import seedu.mealcompanion.router.CommandRouterNode;
import seedu.mealcompanion.storage.IngredientStorage;
import seedu.mealcompanion.ui.MealCompanionUI;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MealCompanionSession {

    private static final CommandRouterNode COMMAND_TREE =
            new CommandRouterNode()

                    .route("hello", new CommandRouterNode()
                            .route("world", new HelloWorldCommandFactory())
                            .route("psle", new HelloPSLECommandFactory())
                            .route("40$1.80", new HelloAnswerCommandFactory())
                    )
                    .route("bye", new ByeCommandFactory())
                    .route("add", new AddCommandFactory())
                    .route("clear", new ClearCommandFactory())
                    .route("help", new HelpCommandFactory())
                    .route("remove", new RemoveCommandFactory())
                    .route("make", new MakeCommandFactory())
                    .route("recipe", new CommandRouterNode()
                            .route("possible", new RecipePossibleCommandFactory())
                            .route("all", new RecipeAllCommandFactory())
                            .route("almost", new RecipeAlmostCommandFactory())
                            .route("random", new RecipeRandomCommandFactory())
                            .route("need", new RecipeNeedCommandFactory())
                            .route("favourite", new RecipeFavouriteCommandFactory())
                            .route("unfavourite", new RecipeUnfavouriteCommandFactory())
                            .route("find", new RecipeFindCommandFactory())
                    )
                    .route("recipe", new RecipeDetailCommandFactory())
                    .route("ingredients", new CommandRouterNode()
                            .route("list", new IngredientsListCommandFactory())
                            .route("search", new IngredientsSearchCommandFactory())
                    )
                    .route("allergen", new CommandRouterNode()
                            .route("add", new AllergenAddCommandFactory())
                            .route("remove", new AllergenRemoveCommandFactory())
                            .route("list", new AllergenListCommandFactory())
                    );

    private final IngredientList ingredients;
    private final RecipeList recipes;
    private final MealCompanionUI ui;
    private final MealCompanionControlFlow controlFlow;
    private final IngredientStorage ingredientStorage;
    private final List<IngredientMetadata> allergens;


    public MealCompanionSession() {
        this.ui = new MealCompanionUI(new Scanner(System.in));
        this.controlFlow = new MealCompanionControlFlow();
        this.ingredients = new IngredientList();
        this.recipes = new RecipeList("/recipes.json");
        this.ingredientStorage = new IngredientStorage(this.ui);
        this.allergens = new ArrayList<>();
    }

    public void setAllergens(List<IngredientMetadata> allergens) {
        this.allergens.clear();
        this.allergens.addAll(allergens);
    }

    public List<IngredientMetadata> getAllergens() {
        return this.allergens;
    }


    /**
     * Returns the <code>MealCompanionUI</code> for the current session.
     *
     * @return Handle to control UI.
     */
    public MealCompanionUI getUi() {
        return ui;
    }

    /**
     * Returns the <code>MealCompanionControlFlow</code> for the current session.
     *
     * @return Handle to set the control flow.
     */
    public MealCompanionControlFlow getControlFlow() {
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
     * Runs the read, evaluate, print loop for MealCompanion.
     */
    public void runRepl() {
        ingredientStorage.getFile(this.ingredients);
        this.ui.printLogo();
        this.ui.printIntroduction();
        while (this.controlFlow.shouldRun()) {
            String nextCommand = ui.getNextCommandString();
            CommandTokens tokens = new CommandTokens(nextCommand);
            ExecutableCommandFactory commandFactory = MealCompanionSession.COMMAND_TREE.resolve(tokens);

            if (commandFactory == null) {
                ui.printMessage("Not a command!");
                continue;
            }

            try {
                ExecutableCommand cmd = commandFactory
                        .buildCommand(this, new CommandArguments(tokens));
                cmd.execute(this);
            } catch (InvalidCommandException e) {
                ui.printMessage(e.getMessage());
                ui.printMessage("Command usage: " + commandFactory.getCommandFormat());
            } catch (CommandRunException e) {
                ui.printMessage(e.getMessage());
            }
            if (!this.controlFlow.shouldRun()) {
                this.ui.printFarewell();
            }
        }
    }
}
