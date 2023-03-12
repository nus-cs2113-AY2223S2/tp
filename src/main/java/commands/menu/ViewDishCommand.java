package commands.menu;

import commands.Command;
import ui.TextUi;

import java.util.ArrayList;

public class ViewDishCommand extends Command {
    public ViewDishCommand(ArrayList<Dish> dishes) {
        int index = 1;
        for (Dish dish : dishes) {
            printDishWithIndex(index, dish);
            index++;
        }
    }

    private void printDishWithIndex(int index, Dish dish) {
        System.out.print(index + ". ");
        printDish(dish);
    }

    private void printDish(Dish dish) {
        System.out.println(dish.getDishName() + "; $"
                + dish.getPriceOfDishInDollars() + "; "
                + dish.ingredientsList);
    }

    @Override
    public void execute(TextUi ui) {

    }

    @Override
    public boolean isExit() {
        return false;
    }
}
