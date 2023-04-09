package commands.menu;

import commands.Command;
import manager.DishManager;
import ui.TextUi;

public class FindDishCommand extends Command {

    public static final String COMMAND_WORD = "find_dish";
    private String stringToFind;

    /**
     * Constructor of FindDishCommand that is going to be executed.
     *
     * @param stringToFind The string that will be used to search dishes.
     */
    public FindDishCommand(String stringToFind) {
        this.stringToFind = stringToFind;
    }

    /**
     * Execute the command of finding dish.
     *
     * @param ui The Ui instance. Use to display messages to users.
     */
    @Override
    public void execute(TextUi ui) {
        ui.printMessage(DishManager.findDish(this.stringToFind));
    }

    /**
     * Returns the exit value.
     * If false, program continues to run.
     *
     * @return the exit value.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
