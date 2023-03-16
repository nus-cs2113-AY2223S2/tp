package seedu.duke.command;

import org.junit.jupiter.api.Test;
import seedu.duke.Module;
import seedu.duke.Storage;

import static org.junit.jupiter.api.Assertions.*;

class AddModuleCommandTest {

    Storage storage = new Storage();
    Module module = new Module(1, "AE320", "Aerodynamics II", 3,
            "ME4231", "Aerodynamics", 4);

    @Test
    public void addModuleCommandConstructor_correctVariableType_success() {
        AddModuleCommand command = new AddModuleCommand(module, storage);
        assertEquals("1,AE320,Aerodynamics II,3,ME4231,Aerodynamics,4", command.getModuleToAdd().toString());
    }

    @Test
    public void addModuleCommandConstructor_nullModule_success() {
        AddModuleCommand command = new AddModuleCommand(null, storage);
        assertEquals(null,command.getModuleToAdd());
    }

    @Test
    public void addModuleCommandExecuteFunction_correctVariableType_success() {
        AddModuleCommand command = new AddModuleCommand(module, storage);
        command.execute();
        int lastIndex = storage.getModule().size() - 1;
        assert lastIndex >= 0 : "ArraySize should be more than 0";
        assertEquals("1,AE320,Aerodynamics II,3,ME4231,Aerodynamics,4",
                storage.getModule().get(lastIndex).toString());
    }

    @Test
    public void addModuleCommandExecuteFunction_nullModule_successfullyCatchNullPointerException() {
        int databaseSize = storage.getModule().size();
        AddModuleCommand command = new AddModuleCommand(null, storage);
        try {
            command.execute();
        } catch (NullPointerException e) {
            assertEquals(databaseSize, storage.getModule().size());
        }
    }


}