package seedu.duke.command.misc;

import seedu.duke.DukeException;
import seedu.duke.DukeSession;
import seedu.duke.command.ExecutableCommand;
import seedu.duke.ingredient.Ingredient;

/**
 * Represents the "add" command.
 */
public class AddCommand extends ExecutableCommand {

    private static int indexOfExistingIngredient;
    String name;
    String amount;

    public AddCommand(String arguments, String flag) {
        this.name = arguments;
        this.amount = flag;
    }

    private static void addToExistingIngredients(DukeSession dukeSession, int quantity, int index) {
        int newQuantity = DukeSession.ingredients.get(index).getQuantity() + quantity;
        DukeSession.ingredients.get(index).setQuantity(newQuantity);
        dukeSession.getUi().printMessage("Here is the new quantity of the ingredient:");
        dukeSession.getUi().printMessage(String.valueOf(DukeSession.ingredients.get(index)));
    }

    private static void addNewIngredient(DukeSession dukeSession, int quantity, String name) {
        Ingredient ingredient = new Ingredient(name, quantity);
        DukeSession.ingredients.add(ingredient);
        dukeSession.getUi().printMessage("the following ingredient has been added");
        dukeSession.getUi().printMessage(String.valueOf(ingredient));
    }

    public static int findIndex(String name) {
        for (int i = 0; i < DukeSession.ingredients.size(); i += 1) {
            if (DukeSession.ingredients.get(i).getName().equals(name)) {
                return i;
            }
        }
        return -1;
    }

    public void execute(DukeSession dukeSession) {
        try {
            int quantity = Integer.parseInt(amount);
            if (quantity <= 0) {
                throw new DukeException("OOPS, quantity must be greater than 0");
            }
            if (name.isBlank()) {
                throw new DukeException("OOPS, name cannot be blank");
            }
            indexOfExistingIngredient = findIndex(name);
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
