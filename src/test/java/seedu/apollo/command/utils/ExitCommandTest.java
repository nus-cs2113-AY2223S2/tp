package seedu.apollo.command.utils;

import org.junit.jupiter.api.Test;
import seedu.apollo.stub.UiStub;
import seedu.apollo.calendar.Calendar;
import seedu.apollo.command.Command;
import seedu.apollo.module.ModuleList;
import seedu.apollo.storage.Storage;
import seedu.apollo.task.TaskList;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class ExitCommandTest {

    TaskList taskList;
    UiStub uiStub = new UiStub();
    Storage storage;
    ModuleList moduleList;
    ModuleList allModules;
    Calendar calendar;

    @Test
    void execute_normalExitCommand_expectIsExit() {
        Command exitCommand = new ExitCommand();
        assertDoesNotThrow(() -> exitCommand.execute(taskList, uiStub, storage, moduleList, allModules, calendar));
        assertTrue(exitCommand.isExit);
    }

}
