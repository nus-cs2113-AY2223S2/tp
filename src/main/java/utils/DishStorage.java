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

public class DishStorage {
    public static final String FILENAME_DISH = "dish_list.txt";
    private static final String FILEPATH_DISH_LIST = Storage.FILE_DIRECTORY + "/" + FILENAME_DISH;

    /**
     * Read and Load data from a file if it exists.
     *
     * @throws FileNotFoundException If file is not found, throw an exception. But file will be created if not found.
     */
    public void readAndLoadFromDishFile() throws FileNotFoundException {
        File file = new File(FILEPATH_DISH_LIST);
        Scanner in = new Scanner(file);
        ArrayList<Dish> listOfDishes = new ArrayList<>();
        Dish dish;

        while (in.hasNext()) {
            String text = in.nextLine();
            String[] components = text.split("~\\|~");
            try {
                if (components.length == 3 && !components[0].equals("") && !components[1].equals("")) {
                    ArrayList<String> ingredients = new ArrayList<>();
                    System.out.println(components[0] + ' ' + components[1] + ' ' + components[2]);
                    String[] ingredientList = components[2].split(";");
                    for (String ingredient : ingredientList) {
                        if (!ingredient.isBlank()) {
                            ingredients.add(ingredient);
                        }
                    }
                    dish = new Dish(components[0], Integer.parseInt(components[1]), ingredients);
                    listOfDishes.add(dish);
                } else if (components.length == 2 && !components[0].equals("")) {
                    dish = new Dish(components[0], Integer.parseInt(components[1]), new ArrayList<String>());
                    listOfDishes.add(dish);
                } else {
                    throw new DinerDirectorException(Messages.ERROR_STORAGE_INVALID_READ_LINE);
                }
            } catch (DinerDirectorException e) {
                System.out.println(String.format(Messages.ERROR_STORAGE_INVALID_READ_LINE, text));
            }
        }

        new DishManager(listOfDishes);
    }

    /**
     * Write the user tasks into a file.
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
