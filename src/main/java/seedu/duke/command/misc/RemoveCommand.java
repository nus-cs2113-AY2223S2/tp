package seedu.duke.command.misc;

import seedu.duke.DukeException;
import seedu.duke.DukeSession;
import seedu.duke.command.ExecutableCommand;

/**
 * Represents the "remove" command.
 */

public class RemoveCommand extends ExecutableCommand {

    public static int indexOfExistingIngredient;
    String arguments;
    String flag;

    public RemoveCommand(String arguments, String flag) {
        this.arguments = arguments;
        this.flag = flag;
    }

    public static boolean isInList(String name) {
        for (int i = 0; i < DukeSession.ingredients.size(); i += 1) {
            if (DukeSession.ingredients.get(i).getName().equals(name)) {
                indexOfExistingIngredient = i;
                return true;
            }
        }
        return false;
    }

    private static void inputValidation(int quantity, String name) throws DukeException {
        if (quantity <= 0) {
            throw new DukeException("OOPS, quantity must be greater than 0");
        }
        if (name.isBlank()) {
            throw new DukeException("OOPS, name cannot be blank");
        }
        if (!isInList(name)) {
            throw new DukeException("OOPS, ingredient is not in fridge");
        }
    }

    private static void removeIngredient(DukeSession dukeSession, int quantity, int fridgeQuantity, String name)
            throws DukeException {
        if (quantity > fridgeQuantity) {
            throw new DukeException("OOPS, quantity to remove is more than quantity in the fridge");
        } else if (quantity == fridgeQuantity) {
            DukeSession.ingredients.remove(indexOfExistingIngredient);
            dukeSession.getUi().printMessage(String.format("All %s has been removed", name));
        } else {
            int newQuantity = fridgeQuantity - quantity;
            DukeSession.ingredients.get(indexOfExistingIngredient).setQuantity(newQuantity);
            dukeSession.getUi().printMessage(String.format("Success! new quantity of %s is %d", name, newQuantity));
        }
    }

    public void execute(DukeSession dukeSession) {
        try {
            int quantity = Integer.parseInt(flag);
            String name = arguments;
            inputValidation(quantity, name);
            int fridgeQuantity = DukeSession.ingredients.get(indexOfExistingIngredient).getQuantity();
            removeIngredient(dukeSession, quantity, fridgeQuantity, name);
        } catch (Exception e) {
            dukeSession.getUi().printMessage(String.valueOf(e));
        }
    }
}
