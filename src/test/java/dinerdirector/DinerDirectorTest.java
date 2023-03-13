package dinerdirector;

import static org.junit.jupiter.api.Assertions.assertTrue;

import commands.Command;
import commands.ExitCommand;
import commands.HelpCommand;
import commands.IncorrectCommand;
import org.junit.jupiter.api.Test;
import utils.Parser;

import java.util.ArrayList;

public class DinerDirectorTest {

    @Test
    void runCommandLoopUntilExit_userInput_command() {
        ArrayList<String> listOfCommands = new ArrayList<>();
        listOfCommands.add("help");
        listOfCommands.add("exit");
        listOfCommands.add("asdf");

        for (String listOfCommand : listOfCommands) {
            Command command = new Parser().parseCommand(listOfCommand);
            if (listOfCommand.equals("help")) {
                assertTrue(command instanceof HelpCommand);
            } else if (listOfCommand.equals("exit")) {
                assertTrue(command instanceof ExitCommand);
            } else {
                assertTrue(command instanceof IncorrectCommand);
            }
        }
    }
}
