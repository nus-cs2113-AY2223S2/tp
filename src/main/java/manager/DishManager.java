package manager;

import entity.Dish;

import java.util.ArrayList;

public class DishManager {
    private static ArrayList<Dish> dishes = new ArrayList<Dish>();

    public static int getDishesSize() {
        return dishes.size();
    }

    public static void AddDishCommand(String name, int price, ArrayList<String> ingredients) {
        Dish dish = new Dish(name, price, ingredients);
        dishes.add(dish);
    }

    public static void DeleteDishCommand(int index) {
        Dish selectedDish = dishes.get(index);
        dishes.remove(index);
    }

    public static String ViewDishCommand() {
        int index = 1;
        String everyDishInList = "";
        for (Dish dish : dishes) {
            everyDishInList += StringOfDishWithIndex(index, dish) + System.lineSeparator();
            index++;
        }
        return everyDishInList;
    }

    private static String StringOfDishWithIndex(int index, Dish dish) {
        return index + ". " + StringOfDish(dish);
    }

    private static String StringOfDish(Dish dish) {
        return dish.getDishName() + "; $"
                + dish.getPriceOfDishInDollars() + "; "
                + dish.getIngredientsList();
    }

}
