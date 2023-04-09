package commands.menu;

import commands.Command;
import manager.DishManager;
import ui.TextUi;

public class ViewDishCommand extends Command {

    public static final String COMMAND_WORD = "view_dish";

    /**
     * Constructor of DeleteDishCommand that is going to be executed.
     *
     */
    public ViewDishCommand() {
    }

    /**
     * Execute the command of viewing the list of dishes.
     *
     * @param ui The Ui instance. Use to display messages to users.
     */
    @Override
    public void execute(TextUi ui) {
        ui.printMessage(DishManager.viewDish());
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
