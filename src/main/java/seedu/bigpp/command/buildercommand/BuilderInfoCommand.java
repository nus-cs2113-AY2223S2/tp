package seedu.bigpp.command.buildercommand;

import seedu.bigpp.command.Command;
import seedu.bigpp.datastorage.DataStorage;
import seedu.bigpp.exceptions.PPException;
import seedu.bigpp.ui.UI;

public class BuilderInfoCommand extends Command {

    public BuilderInfoCommand(String arguments) {
        setArguments(arguments);
    }

    /**
     * View all the specifications of a component in the pc build
     * @return the specifications of the component
     */
    @Override
    public String executeCommand(DataStorage dataStorage)
            throws PPException {

        String componentTypeString = getArguments();
        componentTypeString = componentTypeString.toLowerCase();
        componentTypeString = componentTypeString.trim();

        if (componentTypeString.equals("")) {
            throw new PPException("Please enter a component");
        }

        // throw exception if component type is not valid eg. "list jfk"
        if (!dataStorage.stringToComponentListMap.containsKey(componentTypeString)) {
            throw new PPException(
                    "Please select a valid component (cpu,gpu,ram,storage,psu,motherboard,cpu-cooler,chassis)");
        }

        int pcIndex = UI.pcBuilderMenu.getPCIndex();
        if (dataStorage.pcList.get(pcIndex).getComponent(componentTypeString) == null) {
            throw new PPException("You have not have selected anything for this component");
        }

        String outputString = "Here is the specifications of your selected " + componentTypeString + ": \n";
        return outputString + dataStorage.pcList.get(pcIndex).getComponent(componentTypeString).toString(true);

    }
}
