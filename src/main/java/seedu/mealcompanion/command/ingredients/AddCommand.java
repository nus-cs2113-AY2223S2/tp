package seedu.mealcompanion.command.ingredients;

import seedu.mealcompanion.MealCompanionException;
import seedu.mealcompanion.MealCompanionSession;
import seedu.mealcompanion.command.ExecutableCommand;
import seedu.mealcompanion.ingredient.Ingredient;
import seedu.mealcompanion.ingredient.IngredientList;
import seedu.mealcompanion.recipe.IngredientDatabase;

//@@author TJW0911
/**
 * Represents the "add" command.
 */
public class AddCommand extends ExecutableCommand {

    private static final int MAX_INGREDIENTS = 10000;
    String name;
    String amount;

    public AddCommand(String arguments, String flag) {
        this.name = arguments;
        this.amount = flag;
    }

    /**
     * Adds a specified quantity of ingredient already in the ingredients list
     *
     * @param mealCompanionSession the MealCompanionSession containing the list of ingredients
     * @param quantity the quantity of ingredient to be added
     * @param index the index of the ingredient in the ingredients list
     */

    private void addToExistingIngredients(MealCompanionSession mealCompanionSession, int quantity, int index)
            throws MealCompanionException {
        IngredientList ingredients = mealCompanionSession.getIngredients();
        int newQuantity = ingredients.get(index).getQuantity() + quantity;
        if (newQuantity > MAX_INGREDIENTS) {
            throw new MealCompanionException("OOPS, new total ingredient amount cannot exceed 10000");
        }
        ingredients.get(index).setQuantity(newQuantity);
        mealCompanionSession.getUi().printMessage("Here is the new quantity of the ingredient:");
        mealCompanionSession.getUi().printMessage(String.valueOf(ingredients.get(index)));
        mealCompanionSession.getIngredientStorage().writeIngredientsToFile(ingredients);
    }

    /**
     * Adds a specified quantity of a new ingredient to the ingredients list
     *
     * @param mealCompanionSession the MealCompanionSession containing the list of ingredients
     * @param quantity the quantity of ingredient to be added
     * @param name the name of the ingredient
     */

    private void addNewIngredient(MealCompanionSession mealCompanionSession, int quantity, String name)
            throws MealCompanionException {
        IngredientDatabase db = IngredientDatabase.getDbInstance();
        if (!db.getKnownIngredients().containsKey(name)) {
            throw new MealCompanionException("Unknown ingredient named: " + name);
        }
        Ingredient ingredient = new Ingredient(name, quantity);
        mealCompanionSession.getIngredients().add(ingredient);
        mealCompanionSession.getUi().printMessage("the following ingredient has been added");
        mealCompanionSession.getUi().printMessage(String.valueOf(ingredient));
        mealCompanionSession.getIngredientStorage().writeIngredientToFile(ingredient);
    }


    /**
     * Add an ingredient of specified quantity to ingredients list.
     *
     * @param mealCompanionSession the MealCompanionSession containing the list of ingredients
     */

    public void execute(MealCompanionSession mealCompanionSession) {
        try {
            int quantity = Integer.parseInt(amount);
            if (quantity <= 0 || quantity > MAX_INGREDIENTS) {
                throw new MealCompanionException("OOPS, quantity provided must be greater than 0 and not exceed 10000");
            }
            int indexOfExistingIngredient = mealCompanionSession.getIngredients().findIndex(name);
            if (indexOfExistingIngredient == -1) {
                addNewIngredient(mealCompanionSession, quantity, name);
            } else {
                addToExistingIngredients(mealCompanionSession, quantity, indexOfExistingIngredient);
            }
        } catch (NumberFormatException e) {
            mealCompanionSession.getUi()
                    .printMessage("OOPS, please input an integer no greater than 10000 for quantity");
        } catch (NullPointerException e) {
            mealCompanionSession.getUi().printMessage("OOPS, Certain fields are empty");
            mealCompanionSession.getUi().printMessage("please follow the format: add <ingredient> /qty <quantity>");
        } catch (Exception e) {
            mealCompanionSession.getUi().printMessage(String.valueOf(e));
        }
    }
}
