package seedu.duke.command;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import seedu.duke.Deadline;
import seedu.duke.Module;
import seedu.duke.Parser;
import seedu.duke.Storage;
import seedu.duke.University;
import seedu.duke.budget.BudgetPlanner;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class HelpCommandTest {
    private static final String READ_COMMAND_INPUT = "What can I do for you?";
    private static final String LINE = "____________________________________________________________";

    //@@author dfa-reused
    //Reused from https://stackoverflow.com/questions/1119385/junit-test-for-system-out-println
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    //@@author dfa

    @Test
    void userInput_help_correctVariableTypeSuccess() {
        String userInput = "/help";
        ArrayList<University> universities = new ArrayList<>();
        ArrayList<Module> modules = new ArrayList<>();
        ArrayList<Module> puModules = new ArrayList<>();
        ArrayList<Deadline> deadlines = new ArrayList<>();
        Storage storage = new Storage();
        Parser parser = new Parser();
        BudgetPlanner budgetPlanner = new BudgetPlanner();
        assertTrue(parser.parseUserCommand(userInput, universities, modules, puModules, storage, budgetPlanner,
                deadlines) instanceof HelpCommand);
    }

    //@@author dfa-reused
    //Reused from https://stackoverflow.com/questions/1119385/junit-test-for-system-out-println
    @BeforeAll
    public void setUpStream() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterAll
    public void restoreStream() {
        System.setOut(originalOut);
    }

    @Test
    void execute_help_success() {
        HelpCommand helpCommand = new HelpCommand();
        helpCommand.execute();
        assertEquals(("Here are the list of commands:\n"
                + "LIST PU                     : Provides the list of Partner Universities available\n"
                + "LIST [PU ABBRV]             : Provides the list of all modules available " +
                "in the specified Partner University\n"
                + "LIST [PU INDEX]             : Provides the list of all modules available " +
                "in the specified Partner University\n"
                + "                              by index of LIST PU\n"
                + "LIST CURRENT                : Provides the list of modules that the user has added to his/her " +
                "list of interest\n"
                + "ADD [PU ABBRV]/[MODULE CODE]: Adds the specified module into user's current list of modules\n"
                + "REMOVE [INDEX]              : Removes the specified module by index from user's current list\n"
                + "EXIT                        : Exits the program\n\n"
                + System.lineSeparator()
                + READ_COMMAND_INPUT + System.lineSeparator()
                + LINE.stripTrailing()), outContent.toString().stripTrailing());
        outContent.reset();
    }
    //@@author dfa
}
