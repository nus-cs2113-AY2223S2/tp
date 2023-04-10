package seedu.apollo.command.utils;

import org.junit.jupiter.api.Test;
import seedu.apollo.stub.UiStub;
import seedu.apollo.calendar.Calendar;
import seedu.apollo.command.Command;
import seedu.apollo.module.ModuleList;
import seedu.apollo.storage.Storage;
import seedu.apollo.task.TaskList;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class HelpCommandTest {

    TaskList taskList;
    UiStub uiStub = new UiStub();
    Storage storage;
    ModuleList moduleList;
    ModuleList allModules;
    Calendar calendar;

    @Test
    void execute_normalHelpCommand_expectNoException() {
        Command helpCommand = new HelpCommand();
        assertDoesNotThrow(() -> helpCommand.execute(taskList, uiStub, storage, moduleList, allModules, calendar));
    }
}
