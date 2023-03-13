package dinerdirector;

import static org.junit.jupiter.api.Assertions.assertTrue;

import commands.Command;
import commands.IncorrectCommand;
import commands.deadlinecommand.AddDeadlineCommand;
import commands.deadlinecommand.DeleteDeadlineCommand;
import commands.deadlinecommand.ViewDeadlineCommand;
import org.junit.jupiter.api.Test;
import utils.Parser;

import java.util.ArrayList;

public class DinerDirectorTest {
    @Test
    void runCommandLoopUntilExit_userInput_command() {
        ArrayList<String> listOfCommands = new ArrayList<>();
        listOfCommands.add("add_deadline");
        listOfCommands.add("add_deadline n/add command");
        listOfCommands.add("add_deadline t/when to command");
        listOfCommands.add("add_deadline n/add command t/when to command");
        listOfCommands.add("view_deadline");
        listOfCommands.add("view_deadline dsjfnskldf");
        listOfCommands.add("delete_deadline");
        listOfCommands.add("delete_deadline 1");

        for (String listOfCommand : listOfCommands) {
            Command command = new Parser().parseCommand(listOfCommand);
            if (listOfCommand.equals("add_deadline") ||
                    listOfCommand.equals("add_deadline n/add command") ||
                    listOfCommand.equals("add_deadline t/when to command") ||
                    listOfCommand.equals("view_deadline dsjfnskldf") ||
                    listOfCommand.equals("delete_deadline")) {
                assertTrue(command instanceof IncorrectCommand);
            } else if (listOfCommand.equals("view_deadline")) {
                assertTrue(command instanceof ViewDeadlineCommand);
            } else if (listOfCommand.equals("add_deadline n/add command t/when to command")) {
                assertTrue(command instanceof AddDeadlineCommand);
            } else if (listOfCommand.equals("delete_deadline 1")) {
                assertTrue(command instanceof DeleteDeadlineCommand);
            }
        }

    }
}
