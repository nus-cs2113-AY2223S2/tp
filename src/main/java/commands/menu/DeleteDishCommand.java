package commands.menu;

import commands.Command;
import manager.DishManager;
import ui.TextUi;

public class DeleteDishCommand extends Command {

    public static final String COMMAND_WORD = "delete_dish";
    private int index;

    /**
     * Constructor of DeleteDishCommand that is going to be executed.
     *
     * @param index The index that will be used to locate the dish and delete it.
     */
    public DeleteDishCommand(int index) {
        this.index = index;
    }

    /**
     * Execute the command of deleting dish.
     *
     * @param ui The Ui instance. Use to display messages to users.
     */
    @Override
    public void execute(TextUi ui) {
        DishManager.deleteDish(this.index, ui);
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
