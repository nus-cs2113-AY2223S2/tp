package utils;

import common.Messages;
import entity.Dish;
import exceptions.DinerDirectorException;
import manager.DishManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DishStorage {
    public static final String FILENAME_DISH = "dish_list.txt";
    private static final String FILEPATH_DISH_LIST = Storage.FILE_DIRECTORY + "/" + FILENAME_DISH;

    /**
     * Reads and Loads data from a file if it exists.
     *
     * @throws FileNotFoundException If file is not found, throw an exception. But file will be created if not found.
     */
    public void readAndLoadFromDishFile() throws FileNotFoundException {
        File file = new File(FILEPATH_DISH_LIST);
        Scanner in = new Scanner(file);
        ArrayList<Dish> listOfDishes = new ArrayList<>();

        while (in.hasNext()) {
            String text = in.nextLine();

            String regex = "^(\\S.*?)~\\|~(\\d+)~\\|~(.*?)$";

            Pattern dishPattern = Pattern.compile(regex);
            Matcher parsedDishInput = dishPattern.matcher(text);

            String name = null;
            Integer price;
            ArrayList<String> ingredients = new ArrayList<>();
            Dish dish = null;

            try {
                if (parsedDishInput.matches()) {
                    name = parsedDishInput.group(1);
                    try {
                        price = Integer.parseInt(parsedDishInput.group(2));
                    } catch (NumberFormatException e) {
                        throw new DinerDirectorException(Messages.ERROR_PRICE_EXCEED_INTEGER_BOUNDS);
                    }

                    String[] ingredientList = parsedDishInput.group(3).split(";");
                    for (String ingredient : ingredientList) {
                        String regexNumbers = "^[+-]?\\d+(?:\\.\\d+)?$";
                        Pattern pattern = Pattern.compile(regexNumbers);
                        Matcher parsedIngredient = pattern.matcher(ingredient);
                        if (!ingredient.isBlank() && !parsedIngredient.matches()) {
                            ingredients.add(ingredient);
                        }
                    }

                    for (Dish currentDish : listOfDishes) {
                        if (currentDish.getDishName().equals(name)) {
                            throw new DinerDirectorException(Messages.ERROR_DISH_STORAGE_DUPLICATE_DISH_NAME);
                        }
                    }
                    dish = new Dish(name, price, ingredients);
                } else {
                    throw new DinerDirectorException(Messages.ERROR_STORAGE_INVALID_READ_LINE);
                }
            } catch (DinerDirectorException e) {
                System.out.println(String.format(e.getMessage(), text));
            }

            if (dish != null) {
                listOfDishes.add(dish);
            }
        }
        new DishManager(listOfDishes);
    }

    /**
     * Writes the user tasks into a file.
     *
     * @param listOfDishes An arraylist storing the list of things the user created.
     * @throws IOException Some IO Exception has occured.
     */
    public void writeToDishFile(ArrayList<Dish> listOfDishes) throws IOException {
        FileWriter filewriter = new FileWriter(FILEPATH_DISH_LIST);
        for (Dish dish : listOfDishes) {
            filewriter.write(dish.savableString() + System.lineSeparator());
        }
        filewriter.close();
    }
}
