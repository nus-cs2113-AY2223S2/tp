package commands.menu;

import commands.Command;
import manager.DishManager;
import ui.TextUi;

public class FindDishCommand extends Command {

    private String stringToFind;
    public FindDishCommand(String stringToFind) {
       this.stringToFind = stringToFind;
    }

    @Override
    public void execute(TextUi ui) {
        ui.printMessage(DishManager.findDishCommand(this.stringToFind));
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
