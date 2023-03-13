package manager;

import entity.Dish;

import java.util.ArrayList;

public class DishManager {
    private static ArrayList<Dish> dishes = new ArrayList<Dish>();

    public static int getDishesSize() {
        return dishes.size();
    }

    public static void addDishCommand(String name, int price, ArrayList<String> ingredients) {
        Dish dish = new Dish(name, price, ingredients);
        dishes.add(dish);
    }

    public static void deleteDishCommand(int index) {
        Dish selectedDish = dishes.get(index);
        dishes.remove(index);
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
