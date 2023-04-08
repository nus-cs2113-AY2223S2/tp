package seedu.duke;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class StorageTest {

    private static final String SAVED_MODULES_FILE_PATH = "data/saved_modules.txt";
    private static File f = new File(SAVED_MODULES_FILE_PATH);

    @BeforeAll
    private static void reset() {
        f.delete();
    }

    @Test
    @Order(2)
    public void addModule_correctFormat_success() {
        Storage storage = Storage.getInstance();
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
        Storage storage = Storage.getInstance();
        assertEquals(0, storage.getModules().size());
    }

    @Test
    @Order(3)
    public void initialiseStorage_oneModuleSaved_success() {
        Storage storage = Storage.getInstance();
        assertEquals(1, storage.getModules().size());
    }

    @Test
    @Order(4)
    public void initialiseStorage_nonCorruptFiles_success() {
        Storage storage = Storage.getInstance();
        try {
            storage.initialiseDatabase();
        } catch (IOException e) {
            System.out.println("Storage failed corrupt file");
        }
        System.out.println(storage.getModules().get(0).toString());
        assertEquals(1, storage.getModules().size());
        f.delete();
    }

    @Test
    @Order(5)
    public void initialiseStorage_corruptFiles_success() {
        Storage storage = Storage.getInstance();
        int initialSize = storage.getModules().size();
        try {
            FileWriter fw = new FileWriter(SAVED_MODULES_FILE_PATH, true);
            fw.write("hello");
            fw.close();
        } catch (IOException e) {
            System.out.println("Storage failed corrupt file");
        }
        try {
            storage.initialiseDatabase();
        } catch (IOException e) {
            System.out.println("Storage failed corrupt file");
        }
        assertEquals(initialSize, storage.getModules().size());
        f.delete();
    }
}
