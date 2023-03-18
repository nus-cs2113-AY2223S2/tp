package seedu.duke;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StorageTest {
    

    @Test
    @Order(1)
    public void addModule_correctFormat_success() {
        Storage storage = new Storage();
        Module module = new Module(1, "AE320", "Aerodynamics II", 3,
                "ME4231", "Aerodynamics", 4);
        storage.addModuleToModuleList(module);
        int lastIndex = storage.getModules().size() - 1;
        assertEquals("1,AE320,Aerodynamics II,3,ME4231,Aerodynamics,4",
                storage.getModules().get(lastIndex).toString());
    }

    @Test
    @Order(2)
    public void initialiseStorage_noModuleSaved_success() {
        Storage storage = new Storage();
        assertEquals(0, storage.getModules().size());
    }


}
