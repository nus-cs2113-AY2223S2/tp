package seedu.duke.command;

import org.junit.jupiter.api.Test;
import seedu.duke.Deadline;
import seedu.duke.DeadlineStorage;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddDeadlineCommandTest {

    DeadlineStorage deadlineStorage = DeadlineStorage.getInstance();
    Deadline deadline = new Deadline("test", "01-01-2023");

    @Test
    public void addDeadlineCommandConstructor_correctVariableType_success() {
        AddDeadlineCommand command = new AddDeadlineCommand(deadline, deadlineStorage);
        assertEquals("test//01-01-2023", command.getDeadlineToAdd().toString());
    }

    @Test
    public void addDeadlineCommandConstructor_nullModule_success() {
        AddDeadlineCommand command = new AddDeadlineCommand(null, deadlineStorage);
        assertEquals(null, command.getDeadlineToAdd());
    }

    @Test
    public void addDeadlineCommandExecuteFunction_correctVariableType_success() {
        AddDeadlineCommand command = new AddDeadlineCommand(deadline, deadlineStorage);
        command.execute();
        int lastIndex = deadlineStorage.getDeadlines().size() - 1;
        assert lastIndex >= 0 : "ArraySize should be more than 0";
        assertEquals("test//01-01-2023",
                deadlineStorage.getDeadlines().get(lastIndex).toString());
    }
}
