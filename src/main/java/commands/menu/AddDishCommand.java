package commands.menu;

import commands.Command;
import manager.DishManager;
import ui.TextUi;

import java.util.ArrayList;


public class AddDishCommand extends Command {

    public static final String COMMAND_WORD = "add_dish";
    private String dishName;
    private Integer dishPrice;
    private ArrayList<String> ingredientsList;

    public AddDishCommand(String dishName, Integer dishPrice, ArrayList<String> ingredientsList) {
        this.dishName = dishName;
        this.dishPrice = dishPrice;
        this.ingredientsList = ingredientsList;
    }

    @Override
    public void execute(TextUi ui) {
        DishManager.addDish(this.dishName, this.dishPrice, this.ingredientsList, ui);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
