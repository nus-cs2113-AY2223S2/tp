package seedu.mealcompanion.command.misc;

import seedu.mealcompanion.MealCompanionException;
import seedu.mealcompanion.MealCompanionSession;
import seedu.mealcompanion.command.ExecutableCommand;
import seedu.mealcompanion.ingredient.Ingredient;
import seedu.mealcompanion.recipe.IngredientDatabase;

//@@author TJW0911
/**
 * Represents the "add" command.
 */
public class AddCommand extends ExecutableCommand {

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

    private void addToExistingIngredients(MealCompanionSession mealCompanionSession, Double quantity, int index) {
        double newQuantity = mealCompanionSession.getIngredients().get(index).getQuantity() + quantity;
        mealCompanionSession.getIngredients().get(index).setQuantity(newQuantity);
        mealCompanionSession.getUi().printMessage("Here is the new quantity of the ingredient:");
        mealCompanionSession.getUi().printMessage(String.valueOf(mealCompanionSession.getIngredients().get(index)));
        mealCompanionSession.getIngredientStorage().writeIngredientsToFile(mealCompanionSession.getIngredients());
    }

    /**
     * Adds a specified quantity of a new ingredient to the ingredients list
     *
     * @param mealCompanionSession the MealCompanionSession containing the list of ingredients
     * @param quantity the quantity of ingredient to be added
     * @param name the name of the ingredient
     */

    private void addNewIngredient(MealCompanionSession mealCompanionSession, Double quantity, String name)
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
     * Finds the index of a specified ingredient name in the ingredients list
     *
     * @param mealCompanionSession the MealCompanionSession containing the list of ingredients
     * @param name the name of the ingredient
     * @return the index of the ingredient in the ingredients list and -1 if ingredient does not exist
     */

    public int findIndex(MealCompanionSession mealCompanionSession, String name) {
        for (int i = 0; i < mealCompanionSession.getIngredients().size(); i += 1) {
            if (mealCompanionSession.getIngredients().get(i).getMetadata().getName().equalsIgnoreCase(name)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Add an ingredient of specified quantity to ingredients list.
     *
     * @param mealCompanionSession the MealCompanionSession containing the list of ingredients
     */

    public void execute(MealCompanionSession mealCompanionSession) {
        try {
            Double quantity = Double.parseDouble(amount);
            if (quantity <= 0) {
                throw new MealCompanionException("OOPS, quantity must be greater than 0");
            }
            int indexOfExistingIngredient = findIndex(mealCompanionSession, name);
            if (indexOfExistingIngredient == -1) {
                addNewIngredient(mealCompanionSession, quantity, name);
            } else {
                addToExistingIngredients(mealCompanionSession, quantity, indexOfExistingIngredient);
            }
        } catch (NumberFormatException e) {
            mealCompanionSession.getUi().printMessage("OOPS, please input a number for quantity");
        } catch (NullPointerException e) {
            mealCompanionSession.getUi().printMessage("OOPS, Certain fields are empty");
            mealCompanionSession.getUi().printMessage("please follow the format: add <ingredient> /qty <quantity>");
        } catch (Exception e) {
            mealCompanionSession.getUi().printMessage(String.valueOf(e));
        }
    }
}
