package seedu.duke.command;

import org.junit.jupiter.api.Test;
import seedu.duke.DataReader;
import seedu.duke.Deadline;
import seedu.duke.DeadlineStorage;
import seedu.duke.Module;
import seedu.duke.Parser;
import seedu.duke.ModuleStorage;
import seedu.duke.University;
import seedu.duke.budget.BudgetPlanner;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DeleteModuleCommandTest {
    DeadlineStorage deadlineStorage = DeadlineStorage.getInstance();
    ModuleStorage storage = ModuleStorage.getInstance();
    DataReader dataReader = DataReader.getDataReaderOneInstance();

    @Test
    void userInput_remove_correctVariableTypeSuccess() {
        String userInput = "/remove KU/1";
        ArrayList<University> universities = dataReader.getUniversities();
        ArrayList<Module> modules = new ArrayList<>();
        ArrayList<Module> puModules = new ArrayList<>();
        ArrayList<Deadline> deadlines = new ArrayList<>();
        Module module = new Module(1, "AE320", "Aerodynamics II", 3,
                "ME4231", "Aerodynamics", 4);
        modules.add(module);
        Parser parser = Parser.getInstance();
        BudgetPlanner budgetPlanner = BudgetPlanner.getInstance();
        assertTrue(parser.parseUserCommand(userInput, universities, modules, puModules, storage, deadlineStorage,
                budgetPlanner, deadlines) instanceof DeleteModuleCommand);
    }

    @Test
    public void deleteModule_correctFormatSuccess() {
        ArrayList<Module> uniList1 = new ArrayList<>();
        Module module = new Module(1, "AE320", "Aerodynamics II", 3,
                "ME4231", "Aerodynamics", 4);
        uniList1.add(module);
        ModuleStorage.deleteModule(1, uniList1, storage, 1);
        assertEquals(0, uniList1.size());
    }
}
