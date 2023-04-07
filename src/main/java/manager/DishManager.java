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

    public static void addDish(String name, int price, ArrayList<String> ingredients, TextUi ui) {
        Dish dish = new Dish(name, price, ingredients);
        dishes.add(dish);
        ui.printMessage("Added dish: " + DishManager.stringOfDishWithIndex(DishManager.getDishesSize(), dish));
        try {
            DishStorage dishStorage = new DishStorage();
            dishStorage.writeToDishFile(dishes);
        } catch (IOException e) {
            ui.printMessage(Messages.ERROR_STORAGE_INVALID_WRITE_LINE);
        }
    }

    public static void deleteDish(int index, TextUi ui) {
        Dish selectedDish = dishes.get(index);
        dishes.remove(index);
        ui.printMessage("Deleted dish: " + DishManager.stringOfDishWithIndex(index + 1, selectedDish));
        try {
            DishStorage dishStorage = new DishStorage();
            dishStorage.writeToDishFile(dishes);
        } catch (IOException e) {
            ui.printMessage(Messages.ERROR_STORAGE_DELETE_FAILED);
        }
    }

    public static String viewDish() {
        int index = 1;
        String everyDishInList = "";
        for (Dish dish : dishes) {
            everyDishInList += stringOfDishWithIndex(index, dish) + System.lineSeparator();
            index++;
        }
        if (everyDishInList.isEmpty()) {
            everyDishInList = Messages.MESSAGES_THE_LIST_OF_DISHES_IS_EMPTY;
        }
        return everyDishInList;
    }

    public static String findDish(String stringToFind) {
        ArrayList<Dish> dishesMatchingKeyword = new ArrayList<>();
        ArrayList<Integer> indexes = new ArrayList<Integer>();
        for (int i = 0; i < getDishesSize(); i++) {
            String[] words = dishes.get(i).getDishName().split(" ");
            for (String word : words) {
                if (word.toLowerCase().contains(stringToFind.toLowerCase())) {
                    dishesMatchingKeyword.add(dishes.get(i));
                    indexes.add(i + 1);
                }
            }
        }

        String dishesWithStringToFindInList = "";

        if (dishesMatchingKeyword.size() == 0) {
            dishesWithStringToFindInList = "There are no dishes matching the keyword you have entered."
                    + System.lineSeparator();
        } else {
            for (int i = 0; i < dishesMatchingKeyword.size(); i++) {
                dishesWithStringToFindInList += stringOfDishWithIndex(indexes.get(i), dishesMatchingKeyword.get(i))
                        + System.lineSeparator();
            }
        }

        return dishesWithStringToFindInList;
    }

    public static String stringOfDishWithIndex(int index, Dish dish) {
        return index + ". " + stringOfDish(dish);
    }

    private static String stringOfDish(Dish dish) {
        return dish.getDishName() + "; $"
                + dish.getPriceOfDishInDollars() + "; "
                + dish.getIngredientsList();
    }

    public static boolean isInsideDishes(String name) {
        for (Dish dish : dishes) {
            if (dish.getDishName().equals(name)) {
                return true;
            }
        }
        return false;
    }

}
