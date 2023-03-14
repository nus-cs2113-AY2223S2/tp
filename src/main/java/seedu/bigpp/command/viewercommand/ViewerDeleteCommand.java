package seedu.bigpp.command.viewercommand;

import seedu.bigpp.command.Command;
import seedu.bigpp.exceptions.PPException;
import seedu.bigpp.exceptions.PPIndexOutOfBoundsException;
import seedu.bigpp.exceptions.viewerexceptions.ViewerMissingIndexException;
import seedu.bigpp.pc.PC;
import seedu.bigpp.pc.PCList;

public class ViewerDeleteCommand extends Command {

    public ViewerDeleteCommand(String arguments) {
        super.setArguments(arguments);
    }

    /**
     * Delete a PC of a given index from the PC list
     * @return if the index is a Prebuilt PC, inform the user that the PC of that index cannot be deleted. Or else,
     *         print the name of the PC deleted
     */
    @Override
    public String executeCommand() throws PPException {
        // throw exception if no index is selected
        if (super.getArguments().equals("")) {
            throw new ViewerMissingIndexException();
        }
        int index = Integer.parseInt(super.getArguments()) - 1;
        // throw exception if index selected is out of the PCList range
        if (index < 0 || Integer.parseInt(super.getArguments()) > PCList.getList().size()) {
            throw new PPIndexOutOfBoundsException();
        }
        PC pc = (PCList.getList()).get(index);
        if (pc.getIsPreBuilt()) {
            return "Unable to delete Prebuilt PC";
        }
        PCList.deletePC(index);
        return "Custom PC: [ " + pc.getName() + " ] has been deleted";
    }
}
