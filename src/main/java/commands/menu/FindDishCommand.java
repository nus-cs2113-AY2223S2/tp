package commands.menu;

import commands.Command;
import manager.DishManager;
import ui.TextUi;

public class FindDishCommand extends Command {

    public static final String COMMAND_WORD = "find_dish";
    private String stringToFind;
    public FindDishCommand(String stringToFind) {
        this.stringToFind = stringToFind;
    }

    @Override
    public void execute(TextUi ui) {
        ui.printMessage(DishManager.findDish(this.stringToFind));
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
