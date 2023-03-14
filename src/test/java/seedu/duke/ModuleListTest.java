package seedu.duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class ModuleListTest {
    @Test
    public void deleteModuleTest() {
        ModuleList moduleList = new ModuleList();
        moduleList.addModule("CS2113T", "4", "CORE");
        assertEquals(1, moduleList.getModuleList().size());
        moduleList.deleteModule("CS2113T");
        assertEquals(0, moduleList.getModuleList().size());
    }

    @Test
    public void addDifferentModuleTest() {
        ModuleList moduleList = new ModuleList();
        moduleList.addModule("CS2113T", "4", "CORE");
        assertEquals(1, moduleList.getModuleList().size());
        moduleList.addModule("GEA1000", "4", "GE");
        assertEquals(2, moduleList.getModuleList().size());
        moduleList.addModule("PC1201", "4", "UE");
        assertEquals(3, moduleList.getModuleList().size());
    }

}
