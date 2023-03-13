package utils;

import commands.Command;
import commands.ExitCommand;
import commands.IncorrectCommand;
import commands.menu.AddDishCommand;
import commands.menu.DeleteDishCommand;
import commands.menu.ViewDishCommand;
import common.Messages;
import exceptions.DinerDirectorException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import static manager.DishManager.getDishesSize;

public class Parser {

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
        case AddDishCommand.ADD_DISH_COMMAND:
            return prepareAddDishCommand();
        case ViewDishCommand.VIEW_DISH_COMMAND:
            return prepareViewDishCommand();
        case DeleteDishCommand.DELETE_DISH_COMMAND:
            return prepareDeleteDishCommand(userInputSplit);
        default:
            return new IncorrectCommand();
        }
        //@@damithc
    }

    private Command prepareDeleteDishCommand(String[] userInputSplit) {
        int indexToRemove = 0;
        try {
            indexToRemove = Integer.parseInt(userInputSplit[1]) - 1;
            if (indexToRemove < 0 || indexToRemove >= getDishesSize()) {
                throw new DinerDirectorException(Messages.MESSAGE_INVALID_INDEX_FOR_DISH_COMMAND);
            }
        } catch (NumberFormatException e) {
            System.out.println(Messages.MESSAGE_NOT_A_VALID_INTEGER_COMMAND);
            return new IncorrectCommand();
        } catch (DinerDirectorException e) {
            System.out.println(e.getMessage());
            return new IncorrectCommand();
        }
        return new DeleteDishCommand(indexToRemove);
    }

    private Command prepareViewDishCommand() {
        return new ViewDishCommand();
    }

    private Command prepareAddDishCommand() {
        //call for subsequent inputs:
        // name of dish?
        // price of dish?
        // list of ingredients, maybe indicate number of ingredients followed by listing them

        String name;
        int price;
        ArrayList<String> ingredients;

        try {
            System.out.println("Name of Dish?");
            Scanner userInput = new Scanner(System.in);
            name = userInput.nextLine();
            if (name.isBlank()) {
                throw new DinerDirectorException(Messages.MESSAGE_BLANK_DISH_NAME_COMMAND);
            }

            System.out.println("Price of Dish? (In cents)");
            userInput = new Scanner(System.in);
            price = Integer.parseInt(userInput.nextLine());
            if (price < 0) {
                throw new DinerDirectorException(Messages.MESSAGE_NEGATIVE_PRICE_COMMAND);
            }

            System.out.println("List of ingredients? (Separate it by spaces)");
            userInput = new Scanner(System.in);
            String[] userInputSplit = userInput.nextLine().split(" ");
            ingredients = new ArrayList<>(Arrays.asList(userInputSplit));

        } catch (DinerDirectorException e) {
            System.out.println(e.getMessage());
            return new IncorrectCommand();
        } catch (NumberFormatException e) {
            System.out.println("Price of dish must be an integer!");
            return new IncorrectCommand();
        }

        return new AddDishCommand(name, price, ingredients);
    }

    private Command prepareExitCommand() {
        return new ExitCommand();
    }
}
