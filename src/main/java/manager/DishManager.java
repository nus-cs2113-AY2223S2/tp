package manager;

import common.Messages;
import entity.Dish;
import ui.TextUi;
import utils.DishStorage;

import java.io.IOException;
import java.util.ArrayList;

public class DishManager {
    private static ArrayList<Dish> dishes = new ArrayList<Dish>();

    public DishManager(ArrayList<Dish> dishes) {
        DishManager.dishes = dishes;
    }

    public static int getDishesSize() {
        return dishes.size();
    }

    public static void addDishCommand(String name, int price, ArrayList<String> ingredients, TextUi ui) {
        Dish dish = new Dish(name, price, ingredients);
        dishes.add(dish);
        try {
            DishStorage dishStorage = new DishStorage();
            dishStorage.writeToDishFile(dishes);
        } catch (IOException e) {
            ui.printMessage(String.format(Messages.ERROR_STORAGE_INVALID_WRITE_LINE, dish));
        }
    }

    public static void deleteDishCommand(int index, TextUi ui) {
        Dish selectedDish = dishes.get(index);
        dishes.remove(index);
        try {
            DishStorage dishStorage = new DishStorage();
            dishStorage.writeToDishFile(dishes);
        } catch (IOException e) {
            ui.printMessage(Messages.ERROR_STORAGE_DELETE_FAILED);
        }
    }

    public static String viewDishCommand() {
        int index = 1;
        String everyDishInList = "";
        for (Dish dish : dishes) {
            everyDishInList += stringOfDishWithIndex(index, dish) + System.lineSeparator();
            index++;
        }
        return everyDishInList;
    }

    private static String stringOfDishWithIndex(int index, Dish dish) {
        return index + ". " + stringOfDish(dish);
    }

    private static String stringOfDish(Dish dish) {
        return dish.getDishName() + "; $"
                + dish.getPriceOfDishInDollars() + "; "
                + dish.getIngredientsList();
    }

}
