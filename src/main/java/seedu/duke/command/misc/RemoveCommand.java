package seedu.duke.command.misc;

import seedu.duke.DukeException;
import seedu.duke.DukeSession;
import seedu.duke.command.ExecutableCommand;

/**
 * Represents the "remove" command.
 */

public class RemoveCommand extends ExecutableCommand {

    private static int indexOfExistingIngredient;
    String arguments;
    String flag;

    public RemoveCommand(String arguments, String flag) {
        this.arguments = arguments;
        this.flag = flag;
    }

    public static int findIndex(String name) {
        for (int i = 0; i < DukeSession.ingredients.size(); i += 1) {
            if (DukeSession.ingredients.get(i).getName().equals(name)) {
                return i;
            }
        }
        return -1;
    }

    private static void validateInput(int quantity, String name) throws DukeException {
        if (quantity <= 0) {
            throw new DukeException("OOPS, quantity must be greater than 0");
        }
        if (name.isBlank()) {
            throw new DukeException("OOPS, name cannot be blank");
        }
        if (indexOfExistingIngredient == -1) {
            throw new DukeException("OOPS, ingredient is not in fridge");
        }
        if (quantity > DukeSession.ingredients.get(indexOfExistingIngredient).getQuantity()) {
            throw new DukeException("OOPS, quantity to remove is more than quantity in the fridge");
        }
    }

    private static void removeIngredient(DukeSession dukeSession, int quantity, String name) {
        int fridgeQuantity = DukeSession.ingredients.get(indexOfExistingIngredient).getQuantity();
        int newQuantity = fridgeQuantity - quantity;
        DukeSession.ingredients.get(indexOfExistingIngredient).setQuantity(newQuantity);
        dukeSession.getUi().printMessage(String.format("Success! new quantity of %s is %d", name, newQuantity));
        if (newQuantity == 0) {
            DukeSession.ingredients.remove(indexOfExistingIngredient);
            dukeSession.getUi().printMessage(String.format("All %s has been removed", name));
        }
    }

    public void execute(DukeSession dukeSession) {
        try {
            int quantity = Integer.parseInt(flag);
            String name = arguments;
            indexOfExistingIngredient = findIndex(name);
            validateInput(quantity, name);
            removeIngredient(dukeSession, quantity, name);
        } catch (Exception e) {
            dukeSession.getUi().printMessage(String.valueOf(e));
        }
    }
}
