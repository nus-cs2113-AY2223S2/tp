package seedu.mealcompanion.command.ingredients;

import seedu.mealcompanion.MealCompanionException;
import seedu.mealcompanion.MealCompanionSession;
import seedu.mealcompanion.command.ExecutableCommand;
import seedu.mealcompanion.ingredient.IngredientList;

//@@author TJW0911
/**
 * Represents the "remove" command.
 */

public class RemoveCommand extends ExecutableCommand {

    private static int indexOfExistingIngredient;
    String name;
    String amount;

    public RemoveCommand(String arguments, String flag) {
        this.name = arguments;
        this.amount = flag;
    }


    /**
     * Validates if the remove command can be executed with the arguments provided and throws
     * exception when it cannot be done
     *
     * @param quantity the quantity of ingredient to be removed
     * @param ingredients the list of ingredients the user has
     * @param name the name of the ingredient the user inputted
     * @throws MealCompanionException when user input results in unexpected behaviour
     */
    private static void validateInput(int quantity, IngredientList ingredients, String name)
            throws MealCompanionException {
        if (quantity <= 0) {
            throw new MealCompanionException("OOPS, quantity must be greater than 0");
        }
        if (name == null || name.trim().isEmpty()) {
            throw new MealCompanionException("OOPS, name cannot be empty");
        }
        if (indexOfExistingIngredient == -1) {
            throw new MealCompanionException("OOPS, ingredient is not in fridge");
        }
        if (quantity > ingredients.get(indexOfExistingIngredient).getQuantity()) {
            throw new MealCompanionException("OOPS, quantity to remove is more than quantity in the fridge");
        }
    }

    /**
     * Removes a specified quantity of an ingredient from the ingredient list
     *
     * @param mealCompanionSession the MealCompanionSession containing the list of ingredients
     * @param quantity the quantity of ingredient to be removed
     * @param name the name of the ingredient to be removed
     */

    private static void removeIngredient(MealCompanionSession mealCompanionSession, int quantity,
                                         String name, IngredientList ingredients) {
        int fridgeQuantity = ingredients.get(indexOfExistingIngredient).getQuantity();
        assert fridgeQuantity >= quantity: "fridgeQuantity should be more than quantity to be removed";
        int newQuantity = fridgeQuantity - quantity;
        ingredients.get(indexOfExistingIngredient).setQuantity(newQuantity);
        mealCompanionSession.getUi().printMessage(
                String.format("Success! new quantity of %s is %d", name.toLowerCase(), newQuantity));
        if (newQuantity == 0) {
            ingredients.remove(indexOfExistingIngredient);
            mealCompanionSession.getUi().printMessage(String.format("All %s has been removed", name.toLowerCase()));
        }
        mealCompanionSession.getIngredientStorage().writeIngredientsToFile(ingredients);
    }

    /**
     * Executes the remove command
     *
     * @param mealCompanionSession the MealCompanionSession containing the list of ingredients
     */

    public void execute(MealCompanionSession mealCompanionSession) {
        try {
            int quantity = Integer.parseInt(amount);
            IngredientList ingredients = mealCompanionSession.getIngredients();
            indexOfExistingIngredient = ingredients.findIndex(name);
            validateInput(quantity, ingredients, name);
            removeIngredient(mealCompanionSession, quantity, name, ingredients);
        } catch (NumberFormatException e) {
            mealCompanionSession.getUi().printMessage("OOPS, please follow the format:" +
                    " add <ingredient> /qty <quantity>");
            mealCompanionSession.getUi()
                    .printMessage("Note: quantity provided must be greater than 0 and not exceed 10000");
        } catch (MealCompanionException e) {
            mealCompanionSession.getUi().printMessage(e.getMessage());
        } catch (Exception e) {
            mealCompanionSession.getUi().printMessage("OOPS, please follow the command format in the UG");
        }
    }
}
