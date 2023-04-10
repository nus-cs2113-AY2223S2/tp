package seedu.bigpp.command.viewercommand;

import seedu.bigpp.command.Command;
import seedu.bigpp.exceptions.PPException;
import seedu.bigpp.datastorage.DataStorage;
import seedu.bigpp.pc.PC;
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
    public String executeCommand(DataStorage dataStorage) throws PPException {
        String argument = super.getArguments();

        // ensure only one argument is given
        argument = argument.split(" ")[0];

        // throw exception if no index is selected
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

        // if the PC is pre-built, create a copy of the PC to edit
        if (dataStorage.pcList.get(pcIndex).getIsPreBuilt()) {
            PC pcCopy = new PC(dataStorage.pcList.get(pcIndex));
            dataStorage.pcList.add(pcCopy);
            pcIndex = dataStorage.pcList.size() - 1;
        }

        UI.setPCBuilderMode(pcIndex);
        return "Currently editing PC: " + dataStorage.pcList.get(pcIndex).getName();
    }
}
