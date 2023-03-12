package utils;

import commands.Command;
import commands.ExitCommand;
import commands.IncorrectCommand;
import commands.meeting.AddMeetingCommand;
import commands.meeting.DeleteMeetingCommand;
import commands.meeting.ViewMeetingCommand;
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
        case AddMeetingCommand.COMMAND_WORD:
            return new AddMeetingCommand(userInputSplit[2],userInputSplit[1]);
        case DeleteMeetingCommand.COMMAND_WORD:
            return new DeleteMeetingCommand(userInputSplit[1]);
        case ViewMeetingCommand.COMMAND_WORD:
            return new ViewMeetingCommand();
        default:
            return new IncorrectCommand();
        }
        //@@damithc
    }


    private Command prepareExitCommand() {
        return new ExitCommand();
    }
}
