package utils;

import commands.Command;
import commands.HelpCommand;
import commands.ExitCommand;

import commands.deadlinecommand.AddDeadlineCommand;
import commands.deadlinecommand.ViewDeadlineCommand;
import commands.deadlinecommand.DeleteDeadlineCommand;
import commands.menu.AddDishCommand;
import commands.menu.DeleteDishCommand;
import commands.menu.ViewDishCommand;
import commands.IncorrectCommand;
import commands.meeting.AddMeetingCommand;
import commands.meeting.DeleteMeetingCommand;
import commands.meeting.ViewMeetingCommand;

import common.Messages;
import exceptions.DinerDirectorException;
import entity.Deadline;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import static manager.DishManager.getDishesSize;

public class Parser {

    public Command parseCommand(String userInput) {
        //@@damithc darrenangwx-reused
        //Source:
        //https://github.com/nus-cs2113-AY2223S2/personbook/blob/main/src/main/java/seedu/personbook/parser/Parser.java
        //Reused the switch skeleton

        String[] userInputSplit = userInput.split(" ", 2);
        String userInputNoCommand = userInput.replace(userInputSplit[0], "");
        String commandWord = userInputSplit[0];

        switch (commandWord) {
        case HelpCommand.COMMAND_WORD:
            return prepareHelpCommand();
        case ExitCommand.COMMAND_WORD:
            return prepareExitCommand();
        case AddMeetingCommand.COMMAND_WORD:
            return new AddMeetingCommand(userInputSplit[2],userInputSplit[1]);
        case DeleteMeetingCommand.COMMAND_WORD:
            return new DeleteMeetingCommand(userInputSplit[1]);
        case ViewMeetingCommand.COMMAND_WORD:
            return new ViewMeetingCommand();
        case AddDeadlineCommand.COMMAND_WORD:
            return prepareAddDeadlineCommand(userInputNoCommand);
        case ViewDeadlineCommand.COMMAND_WORD:
            return prepareViewDeadlineCommand(userInputNoCommand);
        case DeleteDeadlineCommand.COMMAND_WORD:
            return prepareDeleteDeadlineCommand(userInputNoCommand);
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

    private Command prepareHelpCommand() {
        return new HelpCommand();
    }
    
    private Command prepareExitCommand() {
        return new ExitCommand();
    }

    //Solution below adapted from https://github.com/Stella1585/ip/blob/master/src/main/java/duke/Parser.java
    /**
     * Creates a deadline item based on descriptions given by the user, then returns
     * an add deadline command.
     * @param description contains the deadline description and due date.
     * @return the add deadline command.
     */
    private Command prepareAddDeadlineCommand(String description) {
        String[] words = (description.trim()).split("t/");
        String[] testName = (description.trim()).split("n/");
        try {
            if (((description.trim()).isEmpty()) || (!description.contains("n/")) || (words.length < 2)) {
                throw new DinerDirectorException(Messages.MESSAGE_MISSING_PARAM);
            } else if ((testName.length > 2) || (words.length > 2)) {
                throw new DinerDirectorException(Messages.MESSAGE_EXCESS_PARAM);
            }
        } catch (DinerDirectorException e) {
            System.out.println(e);
            return new IncorrectCommand();
        }
        String name = (words[0].substring(2)).trim();
        String dueDate = words[1].trim();
        Deadline deadline = new Deadline(name, dueDate);
        return new AddDeadlineCommand(deadline);
    }

    /**
     * Checks for error in the view deadline command, then returns a view deadline command.
     * @param userInput view deadline command
     * @return the view deadline command.
     */
    private Command prepareViewDeadlineCommand(String userInput){
        try {
            if(!(userInput.trim()).isEmpty()){
                throw new DinerDirectorException(Messages.MESSAGE_EXCESS_LIST_PARAM);
            }
        } catch (DinerDirectorException e) {
            System.out.println(e);
            return new IncorrectCommand();
        }
        return new ViewDeadlineCommand();
    }


    /**
     * Checks for error in the delete deadline command, then returns
     * a delete deadline command.
     * @param description contains the index number.
     * @return the delete deadline command.
     */
    private Command prepareDeleteDeadlineCommand(String description) {
        int index;
        try {
            index = Integer.parseInt((description.trim())) - 1;
            if (description.isEmpty()) {
                throw new DinerDirectorException(Messages.MESSAGE_MISSING_INDEX);
            }
        } catch (NumberFormatException e) {
            System.out.println(Messages.MESSAGE_MISSING_INDEX);
            return new IncorrectCommand();
        } catch (DinerDirectorException e) {
            System.out.println(e);
            return new IncorrectCommand();
        }
        return new DeleteDeadlineCommand(index);
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

}
