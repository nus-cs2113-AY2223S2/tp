package utils;

import commands.Command;
import commands.ExitCommand;
import commands.IncorrectCommand;
import commands.menu.AddDishCommand;
import commands.menu.DeleteDishCommand;
import commands.menu.StoreDish;
import commands.menu.ViewDishCommand;
import ui.TextUi;

import java.util.ArrayList;
import java.util.Scanner;

public class Parser {

    private TextUi ui;

    public Command parseCommand(String userInput) {
        String[] userInputSplit = userInput.split(" ");
        String commandWord = userInputSplit[0];

        //@@damithc darrenangwx-reused
        //Source:
        //https://github.com/nus-cs2113-AY2223S2/personbook/blob/main/src/main/java/seedu/personbook/parser/Parser.java
        //Reused the switch skeleton
        switch (commandWord) {
        case ExitCommand.COMMAND_WORD:
            return prepareExitCommand();
        case "add_dish":
            return prepareAddDishCommand();
        case "view_dish":
            return prepareViewDishCommand();
        case "delete_dish":
            return prepareDeleteDishCommand(userInputSplit[1]);
        default:
            return new IncorrectCommand();
        }
        //@@damithc
    }

    private Command prepareDeleteDishCommand(String index) {
        Integer indexToRemove = Integer.parseInt(index) - 1;
        return new DeleteDishCommand(indexToRemove, StoreDish.dishes);
    }

    private Command prepareViewDishCommand() {
        return new ViewDishCommand(StoreDish.dishes);
    }

    private Command prepareAddDishCommand() {
        //call for subsequent inputs:
        // name of dish?
        // price of dish?
        // list of ingredients, maybe indicate number of ingredients followed by listing them
        //return new AddDishCommand();

        String name = "";
        Integer price = 0;
        ArrayList<String> ingredients = new ArrayList<>();


        //Scanner in = new Scanner(System.in);
        //String line = in.nextLine();
        //String[] commands = line.split(" ");


        System.out.println("Name of Dish?");
        Scanner userInput = new Scanner(System.in);
        name = userInput.nextLine();
        System.out.println("Price of Dish? (In cents)");
        userInput = new Scanner(System.in);
        price = Integer.parseInt(userInput.nextLine());
        System.out.println("List of ingredients? (Separate it by spaces)");
        userInput = new Scanner(System.in);
        String[] userInputSplit = userInput.nextLine().split(" ");
        for (String ingredient : userInputSplit) {
            ingredients.add(ingredient);
        }
        //return new AddDishCommand(name, price, ingredients, StoreDish.dishes);
        return new AddDishCommand(name, price, ingredients, StoreDish.dishes);
    }


    private Command prepareExitCommand() {
        return new ExitCommand();
    }
}
