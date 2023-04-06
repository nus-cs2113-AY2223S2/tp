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
        DishManager.deleteDishCommand(this.index, ui);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
