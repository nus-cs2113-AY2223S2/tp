package seedu.bigpp.command.viewercommand;

import seedu.bigpp.command.Command;
import seedu.bigpp.exceptions.PPException;
import seedu.bigpp.exceptions.PPIndexOutOfBoundsException;
import seedu.bigpp.exceptions.viewerexceptions.ViewerMissingIndexException;
import seedu.bigpp.pc.PC;
import seedu.bigpp.pc.PCList;

public class ViewerViewCommand extends Command {

    public ViewerViewCommand(String arguments) {
        super.setArguments(arguments);
    }

    /**
     * Display all the components of the PC of a given index
     * @return the components of the PC of that index
     */
    @Override
    public String executeCommand() throws PPException {
        String index = super.getArguments();
        // throw exception if no index is selected
        if (index.equals("")) {
            throw new ViewerMissingIndexException();
        }
        // throw exception if index selected is out of the PCList range
        if (Integer.parseInt(index) < 0 || Integer.parseInt(super.getArguments()) > PCList.getList().size()) {
            throw new PPIndexOutOfBoundsException();
        }
        PC pc = (PCList.getList()).get(Integer.parseInt(index) - 1);
        return pc.viewComponents();
    }
}
