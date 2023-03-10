package seedu.apollo.module;

import org.junit.jupiter.api.Test;
import seedu.apollo.exception.ModuleNotFoundException;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ModuleListTest {

    @Test
    void findModule_invalidModuleCode_expectException() {
        ModuleList allModules = new ModuleList(new ArrayList<Module>());
        String invalidModuleCode = "cs2113";
        assertThrows(ModuleNotFoundException.class, () -> allModules.findModule(invalidModuleCode));
    }
}
