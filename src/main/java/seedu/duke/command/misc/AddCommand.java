package seedu.duke.command.misc;

import seedu.duke.DukeException;
import seedu.duke.DukeSession;
import seedu.duke.command.ExecutableCommand;
import seedu.duke.ingredient.Ingredient;
import seedu.duke.recipe.IngredientDatabase;

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
     * @param dukeSession the DukeSession containing the list of ingredients
     * @param quantity the quantity of ingredient to be added
     * @param index the index of the ingredient in the ingredients list
     */

    private void addToExistingIngredients(DukeSession dukeSession, Double quantity, int index) {
        double newQuantity = dukeSession.getIngredients().get(index).getQuantity() + quantity;
        dukeSession.getIngredients().get(index).setQuantity(newQuantity);
        dukeSession.getUi().printMessage("Here is the new quantity of the ingredient:");
        dukeSession.getUi().printMessage(String.valueOf(dukeSession.getIngredients().get(index)));
        dukeSession.getIngredientStorage().writeIngredientsToFile(dukeSession.getIngredients());
    }
    
    /**
     * Adds a specified quantity of a new ingredient to the ingredients list
     *
     * @param dukeSession the DukeSession containing the list of ingredients
     * @param quantity the quantity of ingredient to be added
     * @param name the name of the ingredient
     */

    private void addNewIngredient(DukeSession dukeSession, Double quantity, String name) throws DukeException {
        IngredientDatabase db = IngredientDatabase.getDbInstance();
        if (!db.getKnownIngredients().containsKey(name)) {
            throw new DukeException("Unknown ingredient named: " + name);
        }
        Ingredient ingredient = new Ingredient(name, quantity);
        dukeSession.getIngredients().add(ingredient);
        dukeSession.getUi().printMessage("the following ingredient has been added");
        dukeSession.getUi().printMessage(String.valueOf(ingredient));
        dukeSession.getIngredientStorage().writeIngredientToFile(ingredient);
    }

    /**
     * Finds the index of a specified ingredient name in the ingredients list
     *
     * @param dukeSession the DukeSession containing the list of ingredients
     * @param name the name of the ingredient
     * @return the index of the ingredient in the ingredients list and -1 if ingredient does not exist
     */

    public int findIndex(DukeSession dukeSession, String name) {
        for (int i = 0; i < dukeSession.getIngredients().size(); i += 1) {
            if (dukeSession.getIngredients().get(i).getMetadata().getName().equals(name)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Add an ingredient of specified quantity to ingredients list.
     *
     * @param dukeSession the DukeSession containing the list of ingredients
     */

    public void execute(DukeSession dukeSession) {
        try {
            Double quantity = Double.parseDouble(amount);
            if (quantity <= 0) {
                throw new DukeException("OOPS, quantity must be greater than 0");
            }
            if (name.isBlank()) {
                throw new DukeException("OOPS, name cannot be blank");
            }
            int indexOfExistingIngredient = findIndex(dukeSession, name);
            if (indexOfExistingIngredient == -1) {
                addNewIngredient(dukeSession, quantity, name);
            } else {
                addToExistingIngredients(dukeSession, quantity, indexOfExistingIngredient);
            }
        } catch (Exception e) {
            dukeSession.getUi().printMessage(String.valueOf(e));
        }
    }
}
