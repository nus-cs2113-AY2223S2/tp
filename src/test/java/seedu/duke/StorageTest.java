package seedu.duke;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class StorageTest {

    private static final String SAVED_MODULES_FILE_PATH = "data/saved_modules.txt";

    File f = new File(SAVED_MODULES_FILE_PATH);

    @Test
    @Order(2)
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
    @Order(1)
    public void initialiseStorage_noModuleSaved_success() {
        Storage storage = new Storage();
        assertEquals(0, storage.getModules().size());
    }

    @Test
    @Order(3)
    public void initialiseStorage_oneModuleSaved_success() {
        Storage storage = new Storage();
        assertEquals(1, storage.getModules().size());
        f.delete();
    }
}
