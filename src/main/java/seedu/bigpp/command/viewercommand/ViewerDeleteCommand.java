package seedu.bigpp.command.viewercommand;

import seedu.bigpp.command.Command;
import seedu.bigpp.exceptions.PPException;
import seedu.bigpp.datastorage.DataStorage;
import seedu.bigpp.pc.PC;

public class ViewerDeleteCommand extends Command {

    public ViewerDeleteCommand(String arguments) {
        super.setArguments(arguments);
    }

    /**
     * Delete a PC of a given index from the PC list
     * @return if the index is a Prebuilt PC, inform the user that the PC of that
     *         index cannot be deleted. Or else,
     *         print the name of the PC deleted
     */
    @Override
    public String executeCommand(DataStorage dataStorage) throws PPException {
        String argument = super.getArguments();

        // ensure only one argument is given
        argument = argument.split(" ")[0];

        if (argument.equals("")) {
            throw new PPException("Please input an index");
        }
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
        if (pc.getIsPreBuilt()) {
            throw new PPException("Unable to delete Prebuilt PC");
        }
        dataStorage.pcList.remove(pcIndex);
        return "Custom PC: [ " + pc.getName() + " ] has been deleted";
    }
}
