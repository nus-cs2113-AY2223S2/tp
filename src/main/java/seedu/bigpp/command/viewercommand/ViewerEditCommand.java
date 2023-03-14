package seedu.bigpp.command.viewercommand;

import seedu.bigpp.command.Command;
import seedu.bigpp.exceptions.PPException;
import seedu.bigpp.exceptions.PPIndexOutOfBoundsException;
import seedu.bigpp.exceptions.viewerexceptions.ViewerMissingIndexException;
import seedu.bigpp.pc.PCList;
import seedu.bigpp.ui.UI;

public class ViewerEditCommand extends Command {
    public ViewerEditCommand(String arguments) {
        super.setArguments(arguments);
    }

    /**
     * Edit a PC of the given index from the PC list
     * @return the name of the PC created
     */
    @Override
    public String executeCommand() throws PPException {
        // throw exception if no index is selected
        if (super.getArguments().equals("")) {
            throw new ViewerMissingIndexException();
        }
        int pcIndex = Integer.parseInt(super.getArguments()) - 1;
        // throw exception if index selected is out of the PCList range
        if (pcIndex < 0 || Integer.parseInt(super.getArguments()) > PCList.getList().size()) {
            throw new PPIndexOutOfBoundsException();
        }
        UI.setPCBuilderMode(pcIndex);
        return "Currently editing PC: " + PCList.getPC(pcIndex).getName();
    }
}
