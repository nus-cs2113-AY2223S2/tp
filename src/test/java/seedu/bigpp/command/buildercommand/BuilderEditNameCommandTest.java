package seedu.bigpp.command.buildercommand;

import org.junit.jupiter.api.Test;

import seedu.bigpp.exceptions.PPException;
import seedu.bigpp.datastorage.DataStorage;
import seedu.bigpp.pc.PC;
import seedu.bigpp.ui.UI;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BuilderEditNameCommandTest {

    private DataStorage dataStorage = new DataStorage();

    @Test
    public void executeCommand_editNameCommand_success() throws PPException {
        PC pc = new PC("PC1", true);
        dataStorage.pcList.add(pc);
        UI.setPCBuilderMode(0);
        String editNameCommandResult = new BuilderEditNameCommand("PC2").executeCommand(dataStorage);
        assertEquals("Current build name is now: PC2", editNameCommandResult);
    }

    @Test
    public void executeCommand_editNameCommandMissingArgument_exceptionThrown() {
        PC pc = new PC("PC1", true);
        dataStorage.pcList.add(pc);
        UI.setPCBuilderMode(0);
        Exception exception = assertThrows(PPException.class, () -> new BuilderEditNameCommand("")
                .executeCommand(dataStorage));
        assertEquals("Please enter a name", exception.getMessage());
    }

}
