package seedu.apollo;

import org.junit.jupiter.api.Test;
import seedu.apollo.task.TaskList;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class UiTest {

    @Test
    void printList_normalInput_noExceptionThrown() throws IOException {
        Storage storage = new Storage("test.txt");
        Ui ui = new Ui();
        TaskList tasks = storage.load(ui);
        assertDoesNotThrow(() -> ui.printList(tasks));
    }
}
