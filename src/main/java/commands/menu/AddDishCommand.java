package commands.menu;

import commands.Command;
import ui.TextUi;

import java.util.ArrayList;


public class AddDishCommand extends Command {
    public AddDishCommand(String name, Integer price, ArrayList<String> ingredients, ArrayList<Dish> dishes) {
        Dish dish = new Dish(name, price, ingredients);
        dishes.add(dish);
    }
    @Override
    public void execute(TextUi ui) {

    }

    @Override
    public boolean isExit() {
        return false;
    }
}
