package seedu.apollo.module;

import org.junit.jupiter.api.Test;
import seedu.apollo.storage.Storage;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ModuleTest {

    @Test
    void getCode_validModuleCode_returnModuleCode() throws FileNotFoundException {
        Storage storage = new Storage("test.txt", "testModuleData.txt" );
        ModuleList allModules = storage.loadModuleData();
        ModuleList modules = new ModuleList();
        modules.add(allModules.findModule("CS2113"));
        assertEquals("CS2113", modules.findModule("CS2113").getCode());
    }

    @Test
    void getCode_invalidModuleCode_expectNull() throws FileNotFoundException {
        Storage storage = new Storage("test.txt", "testModuleData.txt" );
        ModuleList allModules = storage.loadModuleData();
        assertNull(allModules.findModule("hello"));
    }

    @Test
    void getTitle_validModuleCode_returnTitle() throws FileNotFoundException {
        Storage storage = new Storage("test.txt", "testModuleData.txt" );
        ModuleList allModules = storage.loadModuleData();
        ModuleList modules = new ModuleList();
        modules.add(allModules.findModule("CS2113"));
        assertEquals("Software Engineering & Object-Oriented Programming",
                modules.findModule("CS2113").getTitle());
    }

    @Test
    void getTitle_invalidModuleCode_expectNull() throws FileNotFoundException {
        Storage storage = new Storage("test.txt", "testModuleData.txt" );
        ModuleList allModules = storage.loadModuleData();
        assertNull(allModules.findModule("bye"));
    }

    @Test
    void getModuleCredits_validModuleCode_returnModuleCredits() throws FileNotFoundException {
        Storage storage = new Storage("test.txt", "testModuleData.txt" );
        ModuleList allModules = storage.loadModuleData();
        ModuleList modules = new ModuleList();
        modules.add(allModules.findModule("CS2113"));
        assertEquals("4", modules.findModule("CS2113").getModuleCredits());
    }

    @Test
    void getModuleCredits_invalidModuleCode_expectNull() throws FileNotFoundException {
        Storage storage = new Storage("test.txt", "testModuleData.txt" );
        ModuleList allModules = storage.loadModuleData();
        assertNull(allModules.findModule("help"));
    }

}
