package seedu.apollo.module;

import org.junit.jupiter.api.Test;
import seedu.apollo.exception.ModuleNotFoundException;

import static org.junit.jupiter.api.Assertions.assertThrows;

class ModuleListTest {

    @Test
    void findModule_invalidModuleCode_expectException() {
        ModuleList allModules = new ModuleList();
        String invalidModuleCode = "cs2113";
        assertThrows(ModuleNotFoundException.class, () -> allModules.findModule(invalidModuleCode));
    }
    
}
