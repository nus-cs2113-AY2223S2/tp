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

    public static String findDishCommand(String stringToFind) {
        ArrayList<Dish> dishesMatchingKeyword = new ArrayList<>();
        ArrayList<Integer> indexes = new ArrayList<Integer>();
        for (int i = 0; i < getDishesSize(); i++) {
            String[] words = dishes.get(i).getDishName().split(" ");
            for (String word : words) {
                if (word.equals(stringToFind)) {
                    dishesMatchingKeyword.add(dishes.get(i));
                    indexes.add(i + 1);
                }
            }
        }

        String dishesWithStringToFindInList = "";
        for (int i = 0; i < dishesMatchingKeyword.size(); i++) {
            dishesWithStringToFindInList += stringOfDishWithIndex(indexes.get(i), dishesMatchingKeyword.get(i))
                    + System.lineSeparator();
        }
        return dishesWithStringToFindInList;
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
