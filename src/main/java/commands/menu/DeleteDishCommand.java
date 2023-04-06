package commands.menu;

import commands.Command;
import manager.DishManager;
import ui.TextUi;

public class DeleteDishCommand extends Command {

    public static final String COMMAND_WORD = "delete_dish";
    private int index;

    public DeleteDishCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TextUi ui) {
        DishManager.deleteDish(this.index, ui);
        ui.printMessage("deleted dish");
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
