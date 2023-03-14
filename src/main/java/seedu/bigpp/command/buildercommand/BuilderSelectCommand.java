package seedu.bigpp.command.buildercommand;

import seedu.bigpp.command.Command;
import seedu.bigpp.component.Component;
import seedu.bigpp.exceptions.PPException;
import seedu.bigpp.exceptions.PPIndexOutOfBoundsException;
import seedu.bigpp.exceptions.builderexceptions.BuilderIncorrectComponentException;
import seedu.bigpp.exceptions.builderexceptions.BuilderMissingIndexException;
import seedu.bigpp.exceptions.builderexceptions.BuilderMissingSelectException;
import seedu.bigpp.pc.PCList;
import seedu.bigpp.ui.UI;

import static seedu.bigpp.datastorage.DataStorage.stringToComponentListMap;

public class BuilderSelectCommand extends Command {

    public BuilderSelectCommand(String arguments) {
        setArguments(arguments);
    }

    /**
     * Change the Component of the PC that the builder is working on
     *
     * @return Added Component message
     */
    @Override
    public String executeCommand() throws PPException {
        String inputString = getArguments();
        //throw exception if no component is selected eg. "select"
        if (inputString.equals("")) {
            throw new BuilderMissingSelectException();
        }
        String[] inputArray = inputString.split(" ", 2);
        String componentTypeString = inputArray[0];
        componentTypeString = componentTypeString.toLowerCase();

        //throw exception if component type is not valid eg. "select jfk"
        if (!stringToComponentListMap.containsKey(componentTypeString)) {
            throw new BuilderIncorrectComponentException();
        }

        int componentIndex;
        if (inputArray.length == 2) {
            componentIndex = Integer.parseInt(inputArray[1]) - 1;
        } else {
            //throw exception if no index is selected eg. "select cpu"
            throw new BuilderMissingIndexException();
        }
        if (componentIndex < 0 || componentIndex >= stringToComponentListMap.get(inputArray[0]).size()) {
            //throw exception if index is out of bounds eg. "select cpu 100"
            throw new PPIndexOutOfBoundsException();
        }

        int pcIndex = UI.builderMenu.getPCIndex();
        PCList.getPC(pcIndex)
                .setComponent((Component) stringToComponentListMap.get(componentTypeString).get(componentIndex));
        return "Component added!";
    }
}
