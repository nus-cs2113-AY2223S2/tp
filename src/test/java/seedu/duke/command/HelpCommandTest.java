package seedu.duke.command;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import seedu.duke.Deadline;
import seedu.duke.DeadlineStorage;
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
        DeadlineStorage deadlineStorage = new DeadlineStorage();
        Parser parser = new Parser();
        BudgetPlanner budgetPlanner = new BudgetPlanner();
        assertTrue(parser.parseUserCommand(userInput, universities, modules, puModules, storage, deadlineStorage,
                budgetPlanner, deadlines) instanceof HelpCommand);
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
                + "LIST PU                          : Provides the list of Partner Universities available\n"
                + "LIST [PU ABBRV]                  : Provides the list of all modules available "
                + "in the specified Partner University\n"
                + "LIST [PU INDEX]                  : Provides the list of all modules available "
                + "in the specified Partner University\n"
                + "                                   by index of LIST PU\n"
                + "LIST [PU ABBRV] /filter [FILTER] : Provides the list of modules in the specified filters\n"
                + "                                  [FILTER] Format 1: mc == [num of MCs]\n"
                + "                                  [FILTER] Format 2: [description] in name\n"
                + "LIST CURRENT                     : Provides the list of modules that the user has added to his/her "
                + "list of interest\n"
                + "LIST CURRENT [PU ABBRV]          : Provides the list of modules that user has added to his list of\n"
                + "                                   list of interest for the specified PU\n"
                + "ADD [PU ABBRV]/[MODULE CODE]     : Adds the specified module into user's current list of modules\n"
                + "REMOVE [PU ABBRV]/ [INDEX]       : Removes the specified module by index from user's current list\n"
                + "SEARCH [NUS MOD CODE]            : Search for PU modules that can map the user's targeted module\n"
                + "/budget budget [AMOUNT]          : Allows the user to input/edit the total amount of budget for "
                + "his/her SEP trip\n"
                + "/budget accommodation [AMOUNT]   : Allows the user to input/edit the total amount of accommodation "
                + "cost\n                                   for his/her SEP trip\n"
                + "/budget airplane [AMOUNT]        : Allows the user to input/edit the total amount of airplane\n"
                + "                                   ticket cost for his/her SEP trip\n"
                + "/budget food [AMOUNT]            : Allows the user to input/edit the total amount of food "
                + "cost for his/her SEP trip\n"
                + "/budget entertainment [AMOUNT]   : Allows the user to input/edit the total amount of entertainment\n"
                + "                                   cost for his/her SEP trip\n"
                + "/budget view                     : Provides an overview of the user's planned budget\n"
                + "/deadline/list                   : Provides the list of deadlines the user has added\n"
                + "/deadline/add [DEADLINE DESCRIPTION] /by [DD-MM-YYYY] : Allows the user to add in his/her own "
                + "personalized deadlines\n"
                + "                                    of the key dates for certain SEP requirements\n"
                + "/deadline/remove [DEADLINE INDEX] : Allows the user to remove the specific deadline from the list\n"
                + "EXIT                              : Exits the program\n\n"
                + System.lineSeparator()
                + READ_COMMAND_INPUT + System.lineSeparator()
                + LINE.stripTrailing()), outContent.toString().stripTrailing());
        outContent.reset();
    }
    //@@author dfa
}
