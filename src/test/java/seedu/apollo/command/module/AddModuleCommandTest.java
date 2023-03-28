package seedu.apollo.command.module;

import org.junit.jupiter.api.Test;
import seedu.apollo.exception.utils.IllegalCommandException;
import seedu.apollo.module.ModuleList;
import seedu.apollo.storage.Storage;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AddModuleCommandTest {

    Storage storage = new Storage("test.txt", "testModuleData.txt");
    ModuleList allModules = storage.loadModuleData();

    AddModuleCommandTest() throws FileNotFoundException {
    }

    @Test
    void testAddModuleCommand_invalidParams_expectsIllegalCommandException() {
        assertThrows(IllegalCommandException.class, () -> new AddModuleCommand("addmod modules", allModules));
    }

    @Test
    void testAddModuleCommand_validParams_expectsNoException() {
        assertDoesNotThrow(() -> new AddModuleCommand("CS2113", allModules));
    }

}
