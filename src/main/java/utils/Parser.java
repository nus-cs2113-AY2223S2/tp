package utils;


import commands.AddStaffCommand;
import commands.ViewStaffCommand;
import commands.DeleteStaffCommand;
import commands.ExitCommand;
import commands.IncorrectCommand;
import commands.Command;
import common.Messages;
import exceptions.DinerDirectorException;

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
        case "add_staff":
            return prepareAddStaffCommand(userInput);
        case "view_staff":
            return prepareViewStaffCommand();
        case "delete_staff":
            return prepareDeleteStaffCommand(userInput);
        default:
            return new IncorrectCommand();
        }
        //@@damithc
    }

    private Command prepareAddStaffCommand(String userInput) {
        String[] userInputSplit = userInput.split(" ");
        try {
            if (userInputSplit.length < 5) {
                throw new DinerDirectorException(Messages.ERROR_ADD_STAFF_COMMAND);
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
                throw new DinerDirectorException(Messages.ERROR_ADD_STAFF_COMMAND);
            }
            String staffName = userInputSplit[1];

            return new DeleteStaffCommand(staffName);
        } catch (DinerDirectorException e) {
            System.out.println(e.getMessage());
            return new IncorrectCommand();
        }
    }

    private Command prepareExitCommand() {
        return new ExitCommand();
    }
}
