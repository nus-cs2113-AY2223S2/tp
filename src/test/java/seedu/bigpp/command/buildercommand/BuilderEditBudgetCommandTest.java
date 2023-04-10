package seedu.bigpp.command.buildercommand;

import org.junit.jupiter.api.Test;

import seedu.bigpp.exceptions.PPException;
import seedu.bigpp.datastorage.DataStorage;
import seedu.bigpp.pc.PC;
import seedu.bigpp.ui.UI;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
// import static org.junit.jupiter.api.Assertions.assertThrows;


public class BuilderEditBudgetCommandTest {

    private DataStorage dataStorage = new DataStorage();

    @Test
    public void executeCommand_editBudgetCommand_success() throws PPException {
        PC pc = new PC("PC1", true);
        dataStorage.pcList.add(pc);
        UI.setPCBuilderMode(0);
        String editBudgetCommandResult = new BuilderEditBudgetCommand("1000").executeCommand(dataStorage);
        assertEquals("Current build budget is now: $1000.00", editBudgetCommandResult);
    }

    @Test
    public void executeCommand_editBudgetCommandMissingArgument_exceptionThrown() {
        PC pc = new PC("PC1", true);
        dataStorage.pcList.add(pc);
        UI.setPCBuilderMode(0);

        Exception exception = assertThrows(PPException.class, () -> new BuilderEditBudgetCommand("")
                .executeCommand(dataStorage));
        assertEquals("Please enter a budget after the command", exception.getMessage());
    }

    @Test
    public void executeCommand_editBudgetCommandInvalidType_exceptionThrown() {
        PC pc = new PC("PC1", true);
        dataStorage.pcList.add(pc);
        UI.setPCBuilderMode(0);

        Exception exception = assertThrows(PPException.class, () -> new BuilderEditBudgetCommand("abc")
                .executeCommand(dataStorage));
        assertEquals("Please enter a budget that is an integer", exception.getMessage());
    }

    //budget that is 0 throws exception
    @Test
    public void executeCommand_editBudgetCommandInvalidNumber_exceptionThrown() {
        PC pc = new PC("PC1", true);
        dataStorage.pcList.add(pc);
        UI.setPCBuilderMode(0);

        Exception exception = assertThrows(PPException.class, () -> new BuilderEditBudgetCommand("0")
                .executeCommand(dataStorage));
        assertEquals("Please enter a budget that is greater than 0", exception.getMessage());
    }
}
