package seedu.duke.command;

import org.junit.jupiter.api.Test;
import seedu.duke.DataReader;
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

public class DeleteDeadlineCommandTest {
    DeadlineStorage deadlineStorage = DeadlineStorage.getInstance();
    Storage storage = Storage.getInstance();
    DataReader dataReader = DataReader.getDataReaderOneInstance();

    @Test
    void userInput_removeDeadline_correctVariableTypeSuccess() {
        String userInput = "/deadline/remove 1";
        ArrayList<University> universities = dataReader.getUniversities();
        ArrayList<Module> modules = new ArrayList<>();
        ArrayList<Module> puModules = new ArrayList<>();
        ArrayList<Deadline> deadlines = new ArrayList<>();
        Deadline deadline = new Deadline("test", "01-01-2023");
        deadlines.add(deadline);
        Parser parser = Parser.getInstance();
        BudgetPlanner budgetPlanner = BudgetPlanner.getInstance();
        assertTrue(parser.parseUserCommand(userInput, universities, modules, puModules, storage, deadlineStorage,
                budgetPlanner, deadlines) instanceof DeleteDeadlineCommand);
    }

    @Test
    public void deleteDeadline_correctFormatSuccess() {
        ArrayList<Deadline> deadlines = new ArrayList<>();
        Deadline deadline = new Deadline("test", "01-01-2023");
        deadlines.add(deadline);
        DeadlineStorage.deleteDeadline(1, deadlines, deadlineStorage);
        assertEquals(0, deadlines.size());
    }
}
