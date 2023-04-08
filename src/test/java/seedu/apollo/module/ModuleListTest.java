package seedu.apollo.module;

import org.junit.jupiter.api.Test;
import seedu.apollo.storage.Storage;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;


class ModuleListTest {

    @Test
    void addModule_invalidModuleCode_expectNull() {
        ModuleList allModules = new ModuleList();
        String invalidModuleCode = "cg2113";
        ModuleList modules = new ModuleList();
        modules.add(allModules.findModule(invalidModuleCode));
        assertNull(allModules.findModule(invalidModuleCode));
        assertEquals(1, modules.size());
    }

    @Test
    void findModule_invalidModuleCode_expectNull() throws FileNotFoundException {
        Storage storage = new Storage("test.txt", "testModuleData.txt" );
        ModuleList allModules = storage.loadModuleData();
        ModuleList modules = new ModuleList();
        modules.add(allModules.findModule("CS2113"));
        assertNull(modules.findModule("CS2040C"));
    }

    @Test
    void findModule_validModuleCode_returnsModule() throws FileNotFoundException {
        Storage storage = new Storage("test.txt", "testModuleData.txt" );
        ModuleList allModules = storage.loadModuleData();
        ModuleList modules = new ModuleList();
        modules.add(allModules.findModule("CS2113"));
        Module validModule = allModules.findModule("CS2113");
        assertEquals(validModule, modules.findModule("CS2113"));
    }
}
