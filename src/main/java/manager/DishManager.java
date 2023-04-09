package manager;

import common.Messages;
import entity.Dish;
import ui.TextUi;
import utils.DishStorage;

import java.io.IOException;
import java.util.ArrayList;

public class DishManager {
    private static ArrayList<Dish> dishes = new ArrayList<Dish>();

    /**
     * Constructor of DishManager that is going to be executed.
     *
     * @param dishes The arraylist of dishes to be set as the list of dishes.
     */
    public DishManager(ArrayList<Dish> dishes) {
        DishManager.dishes = dishes;
    }

    /**
     * Returns the size of the list of dishes
     *
     * @return The size of the list of dishes
     */
    public static int getDishesSize() {
        return dishes.size();
    }

    /**
     * Add a Dish to the dishes arraylist inside DishManager.
     *
     * @param name The name of the dish.
     * @param price The price of the dish in cents
     * @param ingredients The arraylist of ingredients associated with the dish
     * @param ui The Ui instance. Use to display messages to users.
     */
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

    /**
     * Delete a dish of a certain index in dishes
     *
     * @param index Index of the dish to be deleted.
     * @param ui The Ui instance. Use to display messages to users.
     */
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

    /**
     * View all the dishes in dishes.
     *
     * @return The formatted string containing all the dishes in dishes.
     */
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

    /**
     * Find a dish using a keyword.
     * Name of dish will be split into words and if the keyword is a substring of any of them, the dish is returned.
     *
     * @param stringToFind The keyword used to search for dishes that match it.
     * @return A formatted string with all the dishes that contains the keyword.
     */
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

    /**
     * Format the index and the respective dish in a printable format
     *
     * @param index The index of where the dish is located.
     * @param dish The dish itself.
     * @return The formatted string of the dish and its respective index to be printed.
     */
    public static String stringOfDishWithIndex(int index, Dish dish) {
        return index + ". " + stringOfDish(dish);
    }

    /**
     * Formats the dish in a printable format
     *
     * @param dish
     * @return The formatted string of the dish to be printed.
     */
    private static String stringOfDish(Dish dish) {
        return dish.getDishName() + "; $"
                + dish.getPriceOfDishInDollars() + "; "
                + dish.getIngredientsList();
    }

    /**
     * Check if the dish name already exists in the list of dishes.
     * Returns true if there is already a dish with the same name in dishes.
     * Returns false otherwise
     *
     * @param name Name of the dish to be checked again
     * @return Boolean whether the dish name already exists inside dishes
     */
    public static boolean isInsideDishes(String name) {
        for (Dish dish : dishes) {
            if (dish.getDishName().equals(name)) {
                return true;
            }
        }
        return false;
    }

}
