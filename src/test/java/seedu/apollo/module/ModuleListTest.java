package seedu.apollo.module;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class ModuleListTest {

    @Test
    void findModule_invalidModuleCode_expectNull() {
        ModuleList allModules = new ModuleList();
        String invalidModuleCode = "cs2113";
        assertEquals(null, allModules.findModule(invalidModuleCode));
    }
    
}
