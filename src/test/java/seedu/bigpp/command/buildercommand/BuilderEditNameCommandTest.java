package seedu.bigpp.command.buildercommand;

import org.junit.jupiter.api.Test;

import seedu.bigpp.exceptions.PPException;
import seedu.bigpp.pc.PC;
import seedu.bigpp.pc.PCList;
import seedu.bigpp.ui.UI;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BuilderEditNameCommandTest {

    @Test
    public void executeCommand_editNameCommand_success() throws PPException{
        PC pc = new PC("PC1", true);
        PCList.addPC(pc);
        UI.setPCBuilderMode(0);
        String editNameCommandResult = new BuilderEditNameCommand("PC2").executeCommand();
        assertEquals("Current build name is now: PC2", editNameCommandResult);
    }
}
