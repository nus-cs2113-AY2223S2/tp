package commands.menu;

import commands.Command;
import manager.DishManager;
import ui.TextUi;

public class DeleteDishCommand extends Command {

    public static final String DELETE_DISH_COMMAND = "delete_dish";
    private int index;

    public DeleteDishCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TextUi ui) {
        DishManager.DeleteDishCommand(this.index);
        ui.printMessage("deleted dish");
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
