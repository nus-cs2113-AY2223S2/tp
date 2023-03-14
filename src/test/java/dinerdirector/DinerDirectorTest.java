package dinerdirector;

import static org.junit.jupiter.api.Assertions.assertTrue;

import commands.Command;
import commands.IncorrectCommand;
import commands.meeting.AddMeetingCommand;
import commands.meeting.DeleteMeetingCommand;
import commands.meeting.ViewMeetingCommand;
import org.junit.jupiter.api.Test;
import utils.Parser;

import java.util.ArrayList;

public class DinerDirectorTest {
    @Test
    void runCommandLoopUntilExit_userInput_meetingCommand() {
        ArrayList<String> listOfCommands = new ArrayList<>();
        listOfCommands.add("add_meeting n/a t/3pm");
        listOfCommands.add("view_meetings");
        listOfCommands.add("delete_meeting n/a");
        listOfCommands.add("view_meeting");

        for (String listOfCommand : listOfCommands) {
            Command command = new Parser().parseCommand(listOfCommand);
            if (listOfCommand.equals("view_meeting")){
                assertTrue(command instanceof IncorrectCommand);
            } else if (listOfCommand.equals("view_meetings")) {
                assertTrue(command instanceof ViewMeetingCommand);
            } else if (listOfCommand.equals("add_meeting n/a t/3pm")) {
                assertTrue(command instanceof AddMeetingCommand);
            } else if (listOfCommand.equals("delete_meeting n/a")) {
                assertTrue(command instanceof DeleteMeetingCommand);
            }
        }
    }
}
