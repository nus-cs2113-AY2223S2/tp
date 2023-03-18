package utils;


import commands.staff.AddStaffCommand;
import commands.staff.ViewStaffCommand;
import commands.staff.DeleteStaffCommand;
import commands.HelpCommand;
import commands.ExitCommand;
import commands.deadline.AddDeadlineCommand;
import commands.deadline.ViewDeadlineCommand;
import commands.deadline.DeleteDeadlineCommand;
import commands.menu.AddDishCommand;
import commands.menu.DeleteDishCommand;
import commands.menu.ViewDishCommand;
import commands.IncorrectCommand;
import commands.meeting.AddMeetingCommand;
import commands.meeting.DeleteMeetingCommand;
import commands.meeting.ViewMeetingCommand;
import commands.Command;
import java.util.logging.Logger;
import java.util.logging.Level;

import common.Messages;
import exceptions.DinerDirectorException;
import entity.Deadline;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import static manager.DishManager.getDishesSize;

/**
 * Parser to tokenize the input
 */
public class Parser {

    private static final Logger logger = Logger.getLogger("Foo");

    public Command parseCommand(String userInput) {
        assert userInput != null : "userInput should not be null";
        String[] userInputSplit = userInput.split(" ");
        String commandWord = userInputSplit[0];
        String userInputNoCommand = userInput.replace(userInputSplit[0], "");
        //@@damithc darrenangwx-reused
        //Source:
        //https://github.com/nus-cs2113-AY2223S2/personbook/blob/main/src/main/java/seedu/personbook/parser/Parser.java
        //Reused the switch skeleton
        switch (commandWord) {
        case HelpCommand.COMMAND_WORD:
            return prepareHelpCommand();
        case ExitCommand.COMMAND_WORD:
            return prepareExitCommand();
        case AddMeetingCommand.COMMAND_WORD:
            return prepareAddMeetingCommand(userInputNoCommand);
        case DeleteMeetingCommand.COMMAND_WORD:
            return prepareDeleteMeetingCommand(userInputNoCommand);
        case ViewMeetingCommand.COMMAND_WORD:
            return prepareViewMeetingCommand(commandWord);
        case AddStaffCommand.COMMAND_WORD:
            return prepareAddStaffCommand(userInput);
        case DeleteStaffCommand.COMMAND_WORD:
            return prepareDeleteStaffCommand(userInput);
        case ViewStaffCommand.COMMAND_WORD:
            return prepareViewStaffCommand();
        case AddDeadlineCommand.COMMAND_WORD:
            return prepareAddDeadlineCommand(userInputNoCommand);
        case DeleteDeadlineCommand.COMMAND_WORD:
            return prepareDeleteDeadlineCommand(userInputNoCommand);
        case ViewDeadlineCommand.COMMAND_WORD:
            return prepareViewDeadlineCommand(userInputNoCommand);
        case AddDishCommand.COMMAND_WORD:
            return prepareAddDishCommand(userInputNoCommand);
        case DeleteDishCommand.COMMAND_WORD:
            return prepareDeleteDishCommand(userInputSplit);
        case ViewDishCommand.COMMAND_WORD:
            return prepareViewDishCommand(userInputNoCommand);
        default:
            return new IncorrectCommand();
        }
        //@@damithc
    }

    //Solution below adapted from https://github.com/Stella1585/ip/blob/master/src/main/java/duke/Parser.java
    private Command prepareAddMeetingCommand(String description) {
        String[] words = (description.trim()).split("t/");
        String[] testName = (description.trim()).split("n/");
        try {
            if (((description.trim()).isEmpty()) || (!description.contains("n/")) || (words.length < 2)) {
                throw new DinerDirectorException(Messages.ERROR_MEETING_MISSING_PARAM);
            } else if ((testName.length > 2) || (words.length > 2)) {
                throw new DinerDirectorException(Messages.ERROR_MEETING_EXCESS_ADD_PARAM);
            }
        } catch (DinerDirectorException e) {
            System.out.println(e);
            return new IncorrectCommand();
        }
        String issue = (words[0].substring(2)).trim();
        String time = words[1].trim();
        return new AddMeetingCommand(time, issue);
    }

    private Command prepareViewMeetingCommand(String userInput) {
        try {
            if (!userInput.trim().equals("view_meetings")) {
                throw new DinerDirectorException(Messages.ERROR_MEETING_EXCESS_VIEW_PARAM);
            }
        } catch (DinerDirectorException e) {
            System.out.println(e);
            return new IncorrectCommand();
        }
        return new ViewMeetingCommand();
    }

    private Command prepareDeleteMeetingCommand(String description) {
        String issue;
        try {
            issue = description.trim().substring(2);
            if (((description.trim()).isEmpty()) || (!description.contains("n/")) || (description.length() < 3)) {
                throw new DinerDirectorException(Messages.ERROR_MEETING_MISSING_PARAM);
            }
        } catch (DinerDirectorException e) {
            System.out.println(e);
            return new IncorrectCommand();
        }
        return new DeleteMeetingCommand(issue);
    }

    private Command prepareAddStaffCommand(String userInput) {
        String[] userInputSplit = userInput.split(" ");
        try {
            if (userInputSplit.length < 5) {
                throw new DinerDirectorException(Messages.ERROR_STAFF_ADD_MISSING_PARAM);
            }
            String staffName = userInputSplit[1];
            String staffWorkingDay = userInputSplit[2];
            String staffPhoneNumber = userInputSplit[3];
            String staffDateOfBirth = userInputSplit[4];
            return new AddStaffCommand(staffName, staffWorkingDay, staffPhoneNumber, staffDateOfBirth);
        } catch (DinerDirectorException e) {
            System.out.println(e.getMessage());
            return new IncorrectCommand();
        }
    }

    private Command prepareViewStaffCommand() {
        return new ViewStaffCommand();
    }

    private Command prepareDeleteStaffCommand(String userInput) {
        String[] userInputSplit = userInput.split(" ");
        try {
            if (userInputSplit.length < 2) {
                throw new DinerDirectorException(Messages.ERROR_STAFF_ADD_MISSING_PARAM);
            }
            String staffName = userInputSplit[1];

            return new DeleteStaffCommand(staffName);
        } catch (DinerDirectorException e) {
            System.out.println(e.getMessage());
            return new IncorrectCommand();
        }
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
     *
     * @param description contains the deadline description and due date.
     * @return the add deadline command.
     */
    private Command prepareAddDeadlineCommand(String description) { //  n/ t/
        String[] words = (description.trim()).split("t/"); // n/
        try {
            String[] testName = (words[0].trim()).split("n/");  // n/
            if (((description.trim()).isEmpty()) || (!description.contains("n/"))
                    || (words.length < 2) || (testName.length < 1)) {
                logger.log(Level.WARNING, "Error parsing add deadline command.");
                throw new DinerDirectorException(Messages.ERROR_DEADLINE_MISSING_PARAM);
            } else if ((testName.length > 2) || (words.length > 2)) {
                logger.log(Level.WARNING, "Error parsing add deadline command.");
                throw new DinerDirectorException(Messages.ERROR_DEADLINE_EXCESS_PARAM);
            }
        } catch (DinerDirectorException e) {
            System.out.println(e);
            return new IncorrectCommand();
        }
        String name = (words[0].substring(2)).trim();
        String dueDate = words[1].trim();
        assert !name.isEmpty() : "name should be present";
        assert !dueDate.isEmpty() : "dueDate should be present";
        Deadline deadline = new Deadline(name, dueDate);
        return new AddDeadlineCommand(deadline);
    }

    /**
     * Checks for error in the view deadline command, then returns a view deadline command.
     *
     * @param userInput view deadline command
     * @return the view deadline command.
     */
    private Command prepareViewDeadlineCommand(String userInput) {
        try {
            if (!(userInput.trim()).isEmpty()) {
                logger.log(Level.WARNING, "Error parsing view deadline command.");
                throw new DinerDirectorException(Messages.ERROR_DEADLINE_EXCESS_LIST_PARAM);
            }
        } catch (DinerDirectorException e) {
            System.out.println(e);
            return new IncorrectCommand();
        }
        assert (userInput.trim()).isEmpty() : Messages.ERROR_DEADLINE_EXCESS_LIST_PARAM;
        return new ViewDeadlineCommand();
    }


    /**
     * Checks for error in the delete deadline command, then returns
     * a delete deadline command.
     *
     * @param description contains the index number.
     * @return the delete deadline command.
     */
    private Command prepareDeleteDeadlineCommand(String description) {
        int index;
        try {
            index = Integer.parseInt((description.trim())) - 1;
            if (description.isEmpty()) {
                logger.log(Level.WARNING, "Error parsing add deadline command.");
                throw new DinerDirectorException(Messages.ERROR_DEADLINE_MISSING_INDEX);
            }
        } catch (NumberFormatException e) {
            System.out.println(Messages.ERROR_DEADLINE_MISSING_INDEX);
            logger.log(Level.WARNING, "Input in delete deadline command was not an integer.", e);
            return new IncorrectCommand();
        } catch (DinerDirectorException e) {
            System.out.println(e);
            return new IncorrectCommand();
        }
        assert index >= 0 : "indexToRemove should be 0 or greater.";
        return new DeleteDeadlineCommand(index);
    }

    private Command prepareDeleteDishCommand(String[] userInputSplit) {
        int indexToRemove = 0;
        try {
            if (userInputSplit.length <= 1) {
                throw new DinerDirectorException(Messages.ERROR_DISH_EMPTY_INDEX);
            }
            indexToRemove = Integer.parseInt(userInputSplit[1]) - 1;
            if (indexToRemove < 0 || indexToRemove >= getDishesSize()) {
                throw new DinerDirectorException(Messages.ERROR_DISH_INVALID_INDEX);
            }
        } catch (NumberFormatException e) {
            System.out.println(Messages.ERROR_DISH_NOT_A_VALID_INTEGER);
            return new IncorrectCommand();
        } catch (DinerDirectorException e) {
            System.out.println(e.getMessage());
            return new IncorrectCommand();
        }
        assert indexToRemove >= 0 : "indexToRemove should be 0 or greater";
        return new DeleteDishCommand(indexToRemove);
    }

    private Command prepareViewDishCommand(String userInputNoCommand) {
        try {
            if (!userInputNoCommand.isBlank()) {
                throw new DinerDirectorException(Messages.ERROR_COMMAND_INVALID);
            }
        } catch (DinerDirectorException e) {
            return new IncorrectCommand();
        }
        return new ViewDishCommand();
    }

    private Command prepareAddDishCommand(String userInputNoCommand) {
        //call for subsequent inputs:
        // name of dish?
        // price of dish?
        // list of ingredients, maybe indicate number of ingredients followed by listing them
        try {
            if (!userInputNoCommand.isBlank()) {
                throw new DinerDirectorException(Messages.ERROR_COMMAND_INVALID);
            }
        } catch (DinerDirectorException e) {
            return new IncorrectCommand();
        }

        String name;
        int price;
        ArrayList<String> ingredients;

        try {
            Scanner userInput = new Scanner(System.in);
            System.out.println("Name of Dish?");
            name = userInput.nextLine();
            if (name.isBlank()) {
                throw new DinerDirectorException(Messages.ERROR_DISH_BLANK_DISH_NAME_COMMAND);
            }

            System.out.println("Price of Dish? (In cents)");
            String priceInString = userInput.nextLine();
            price = Integer.parseInt(priceInString);
            if (price < 0) {
                throw new DinerDirectorException(Messages.ERROR_DISH_NEGATIVE_PRICE_COMMAND);
            }

            System.out.println("List of ingredients? (Separate it by spaces)");
            if (!userInput.hasNext()) {
                throw new DinerDirectorException(Messages.ERROR_DISH_MISSING_INGREDIENT);
            }
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
