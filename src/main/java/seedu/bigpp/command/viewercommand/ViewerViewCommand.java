package seedu.bigpp.command.viewercommand;

import seedu.bigpp.command.Command;
import seedu.bigpp.exceptions.PPException;
import seedu.bigpp.datastorage.DataStorage;
import seedu.bigpp.pc.PC;

public class ViewerViewCommand extends Command {

    public ViewerViewCommand(String arguments) {
        super.setArguments(arguments);
    }

    /**
     * Display all the components of the PC of a given index
     * @return the components of the PC of that index
     */
    @Override
    public String executeCommand(DataStorage dataStorage) throws PPException {
        String argument = super.getArguments();

        // ensure only one argument is given
        argument = argument.split(" ")[0];

        // throw exception if no index is selected
        if (argument.equals("")) {
            throw new PPException("Please input an index");
        }

        // throw exception if index selected is not an integer
        if (argument.matches(".*\\D.*")) {
            throw new PPException("Please enter a positive integer");
        }

        int pcIndex = 0;
        try {
            pcIndex = Integer.parseInt(argument) - 1;
        } catch (NumberFormatException e) {
            throw new PPException("Please enter a postive integer within 16 bits");
        }
        // throw exception if index selected is out of the PCList range
        if (pcIndex < 0 || pcIndex >= dataStorage.pcList.size()) {
            throw new PPException("Please enter a valid index");
        }

        PC pc = (dataStorage.pcList).get(pcIndex);
        return pc.viewComponents();
    }
}
