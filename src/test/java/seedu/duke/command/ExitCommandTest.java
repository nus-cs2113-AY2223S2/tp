package seedu.duke.command;

import org.junit.jupiter.api.Test;
import seedu.duke.Deadline;
import seedu.duke.DeadlineStorage;
import seedu.duke.Module;
import seedu.duke.Parser;
import seedu.duke.Storage;
import seedu.duke.University;
import seedu.duke.budget.BudgetPlanner;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ExitCommandTest {

    boolean setExit = false;

    @Test
    void userInput_exit_correctVariableTypeSuccess() {
        String userInput = "/exit";
        ArrayList<University> universities = new ArrayList<>();
        ArrayList<Module> modules = new ArrayList<>();
        ArrayList<Module> puModules = new ArrayList<>();
        ArrayList<Deadline> deadlines = new ArrayList<>();
        DeadlineStorage deadlineStorage = DeadlineStorage.getInstance();
        Storage storage = Storage.getInstance();
        Parser parser = new Parser();
        BudgetPlanner budgetPlanner = BudgetPlanner.getInstance();
        assertTrue(parser.parseUserCommand(userInput, universities, modules, puModules, storage, deadlineStorage,
                budgetPlanner, deadlines) instanceof ExitCommand);
    }

    @Test
    void execute_exit_success() {
        ExitCommand exitCommand = new ExitCommand();
        exitCommand.execute();
        setExit = exitCommand.getIsExit();
        assertEquals(true, setExit);
    }

}
