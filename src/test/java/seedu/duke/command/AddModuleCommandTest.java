package seedu.duke.command;

import org.junit.jupiter.api.Test;
import seedu.duke.Module;
import seedu.duke.Storage;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AddModuleCommandTest {

    Storage storage = Storage.getInstance();
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
        assertEquals(null, command.getModuleToAdd());
    }

    @Test
    public void addModuleCommandExecuteFunction_correctVariableType_success() {
        AddModuleCommand command = new AddModuleCommand(module, storage);
        command.execute();
        int lastIndex = storage.getModules().size() - 1;
        assert lastIndex >= 0 : "ArraySize should be more than 0";
        assertEquals("1,AE320,Aerodynamics II,3,ME4231,Aerodynamics,4",
                storage.getModules().get(lastIndex).toString());
    }

    @Test
    public void addModuleCommandExecuteFunction_nullModule_successfullyCatchNullPointerException() {
        int databaseSize = storage.getModules().size();
        AddModuleCommand command = new AddModuleCommand(null, storage);
        try {
            command.execute();
        } catch (AssertionError e) {
            System.out.println("Null Pointer caught");
        }
        assertEquals(databaseSize, storage.getModules().size());
    }

}
