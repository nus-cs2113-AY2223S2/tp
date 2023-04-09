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

    /**
     * Constructor of AddDishCommand that is going to be executed.
     *
     * @param dishName The name of the dish
     * @param dishPrice The price of the dish in cents (Integer)
     * @param ingredientsList An list of ingredients used to make the dish
     */
    public AddDishCommand(String dishName, Integer dishPrice, ArrayList<String> ingredientsList) {
        this.dishName = dishName;
        this.dishPrice = dishPrice;
        this.ingredientsList = ingredientsList;
    }

    /**
     * Execute the command of adding dish.
     *
     * @param ui The Ui instance. Use to display messages to users.
     */
    @Override
    public void execute(TextUi ui) {
        DishManager.addDish(this.dishName, this.dishPrice, this.ingredientsList, ui);
    }

    /**
     * Returns the exit value.
     * If false, program continues to run.
     *
     * @return the exit value.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
