package seedu.bigpp.command.buildercommand;

import seedu.bigpp.command.Command;
import seedu.bigpp.datastorage.DataStorage;
import seedu.bigpp.exceptions.PPException;
import seedu.bigpp.ui.UI;

public class BuilderUnselectCommand extends Command {

    public BuilderUnselectCommand(String arguments) {
        setArguments(arguments);
    }

    //@@author Ryujikjs
    /**
     * Change the Component of the PC to null
     * @return component removed message
     */
    @Override
    public String executeCommand(DataStorage dataStorage)
            throws PPException {

        String componentTypeString = getArguments();
        // throw exception if no component is selected .eg. "Unselect"
        if (componentTypeString.equals("")) {
            throw new PPException("Please enter a component");
        }

        componentTypeString = componentTypeString.toLowerCase();
        componentTypeString = componentTypeString.trim();

        // throw exception if component type is not valid .eg. "unselect jfk"
        if (!dataStorage.stringToComponentListMap.containsKey(componentTypeString)) {
            throw new PPException(
                    "Please select a valid component (cpu,gpu,ram,storage,psu,motherboard,cpu-cooler,chassis)");
        }

        int pcIndex = UI.pcBuilderMenu.getPCIndex();

        if (dataStorage.pcList.get(pcIndex).getComponent(componentTypeString) == null) {
            throw new PPException("Component is already unselected!");
        }

        dataStorage.pcList.get(pcIndex).setNullComponent(componentTypeString);

        assert dataStorage.pcList.get(pcIndex).getComponent(componentTypeString) == null : "Component should be null";

        return componentTypeString + " removed!";
    }
}
