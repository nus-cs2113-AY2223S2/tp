package seedu.duke.command.misc;

import seedu.duke.DukeException;
import seedu.duke.DukeSession;
import seedu.duke.command.ExecutableCommand;
import seedu.duke.ingredient.Ingredient;

/**
 * Represents the "add" command.
 */
public class AddCommand extends ExecutableCommand {

    public static int indexOfExistingIngredient;
    String arguments;
    String flag;

    public AddCommand(String arguments, String flag) {
        this.arguments = arguments;
        this.flag = flag;
    }

    private static void addToExistingIngredients(DukeSession dukeSession, int quantity, int index) {
        double newQuantity = DukeSession.ingredients.get(index).getQuantity() + quantity;
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

    public static boolean isInList(String name) {
        for (int i = 0; i < DukeSession.ingredients.size(); i += 1) {
            if (DukeSession.ingredients.get(i).getName().equals(name)) {
                indexOfExistingIngredient = i;
                return true;
            }
        }
        return false;
    }

    public void execute(DukeSession dukeSession) {
        try {
            int quantity = Integer.parseInt(flag);
            String name = arguments;
            if (quantity <= 0) {
                throw new DukeException("OOPS, quantity must be greater than 0");
            }
            if (name.isBlank()) {
                throw new DukeException("OOPS, name cannot be blank");
            }
            if (isInList(name)) {
                addToExistingIngredients(dukeSession, quantity, indexOfExistingIngredient);
            } else {
                addNewIngredient(dukeSession, quantity, name);
            }
        } catch (Exception e) {
            dukeSession.getUi().printMessage(String.valueOf(e));
        }
    }
}
