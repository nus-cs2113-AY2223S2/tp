package commands.menu;

import commands.Command;
import manager.DishManager;
import ui.TextUi;

public class ViewDishCommand extends Command {

    public static final String COMMAND_WORD = "view_dish";

    public ViewDishCommand() {
    }

    @Override
    public void execute(TextUi ui) {
        ui.printMessage(DishManager.viewDish());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
