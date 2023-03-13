package utils;

import commands.*;

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
                return new AddStaffCommand(userInputSplit);
            case "view_staff":
                return new ViewStaffCommand();
            case "delete_staff":
                return new DeleteStaffCommand(userInputSplit);
        default:
            return new IncorrectCommand();
        }
        //@@damithc
    }

    private Command prepareExitCommand() {
        return new ExitCommand();
    }
}
