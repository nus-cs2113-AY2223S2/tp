package commands.menu;

import commands.Command;
import ui.TextUi;

import java.util.ArrayList;

public class DeleteDishCommand extends Command {
    public DeleteDishCommand(int index, ArrayList<Dish> dishes) {
        Dish selectedDish = dishes.get(index);
        dishes.remove(index);
    }

    @Override
    public void execute(TextUi ui) {

    }

    @Override
    public boolean isExit() {
        return false;
    }
}
