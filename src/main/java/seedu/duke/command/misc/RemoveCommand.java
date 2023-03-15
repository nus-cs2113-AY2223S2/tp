package seedu.duke.command.misc;

import seedu.duke.DukeException;
import seedu.duke.DukeSession;
import seedu.duke.command.ExecutableCommand;

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
     * Finds the index of a specified ingredient name in the ingredients list
     *
     * @param dukeSession the DukeSession containing the list of ingredients
     * @param name the name of the ingredient
     * @return the index of the ingredient in the ingredients list and -1 if ingredient does not exist
     */
    public static int findIndex(DukeSession dukeSession, String name) {
        for (int i = 0; i < dukeSession.getIngredients().size(); i += 1) {
            if (dukeSession.getIngredients().get(i).getMetadata().getName().equals(name)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Validates if the remove command can be executed with the arguments provided and throws
     * exception when it cannot be done
     *
     * @param dukeSession the DukeSession containing the list of ingredients
     * @param quantity the quantity of ingredient to be removed
     * @param name the name of the ingredient to be removed
     * @throws DukeException when user input results in unexpected behaviour
     */
    private static void validateInput(DukeSession dukeSession, Double quantity, String name) throws DukeException {
        if (quantity <= 0) {
            throw new DukeException("OOPS, quantity must be greater than 0");
        }
        if (name.isBlank()) {
            throw new DukeException("OOPS, name cannot be blank");
        }
        if (indexOfExistingIngredient == -1) {
            throw new DukeException("OOPS, ingredient is not in fridge");
        }
        if (quantity > dukeSession.getIngredients().get(indexOfExistingIngredient).getQuantity()) {
            throw new DukeException("OOPS, quantity to remove is more than quantity in the fridge");
        }
    }

    /**
     * Removes a specified quantity of an ingredient from the ingredient list
     *
     * @param dukeSession the DukeSession containing the list of ingredients
     * @param quantity the quantity of ingredient to be removed
     * @param name the name of the ingredient to be removed
     */

    private static void removeIngredient(DukeSession dukeSession, Double quantity, String name) {
        double fridgeQuantity = dukeSession.getIngredients().get(indexOfExistingIngredient).getQuantity();
        double newQuantity = fridgeQuantity - quantity;
        dukeSession.getIngredients().get(indexOfExistingIngredient).setQuantity(newQuantity);
        dukeSession.getUi().printMessage(String.format("Success! new quantity of %s is %f", name, newQuantity));
        if (newQuantity == 0) {
            dukeSession.getIngredients().remove(indexOfExistingIngredient);
            dukeSession.getUi().printMessage(String.format("All %s has been removed", name));
        }
    }

    /**
     * Executes the remove command
     *
     * @param dukeSession the DukeSession containing the list of ingredients
     */

    public void execute(DukeSession dukeSession) {
        try {
            Double quantity = Double.parseDouble(amount);
            indexOfExistingIngredient = findIndex(dukeSession, name);
            validateInput(dukeSession, quantity, name);
            removeIngredient(dukeSession, quantity, name);
        } catch (Exception e) {
            dukeSession.getUi().printMessage(String.valueOf(e));
        }
    }
}
