package seedu.bigpp.command.buildercommand;

import seedu.bigpp.command.Command;
import seedu.bigpp.component.Component;
import seedu.bigpp.datastorage.DataStorage;
import seedu.bigpp.exceptions.PPException;
import seedu.bigpp.ui.UI;
import seedu.bigpp.ui.UIState;

public class BuilderSelectCommand extends Command {

    public BuilderSelectCommand(String arguments) {
        setArguments(arguments);
    }

    /**
     * Selects a component to be added to the PC that the builder is working on
     * @return Added Component message
     */
    @Override
    public String executeCommand(DataStorage dataStorage) throws PPException {
        assert UI.getUiState() == UIState.PCBUILDER : "UI state should be PCBUILDER";

        String inputString = getArguments();
        // throw exception if no component is selected eg. "select"
        if (inputString.equals("")) {
            throw new PPException("Please select a component");
        }

        String[] inputArray = inputString.split(" ", 2);
        String componentTypeString = inputArray[0];
        componentTypeString = componentTypeString.trim();
        componentTypeString = componentTypeString.toLowerCase();

        // throw exception if component type is not valid eg. "select jfk"
        if (!dataStorage.stringToComponentListMap.containsKey(componentTypeString)) {
            throw new PPException(
                    "Please select a valid component (cpu,gpu,ram,storage,psu,motherboard,cpu-cooler,chassis)");
        }

        // throw exception if no index is selected eg. "select cpu"
        if (inputArray.length == 1) {
            throw new PPException("Please input an index after selecting a component");
        }

        String indexString = inputArray[1].trim();

        // throw exception if index is not a number eg. "select cpu a"
        if (indexString.matches(".*\\D.*")) {
            throw new PPException("Please enter a positive integer");
        }

        int componentIndex = 0;
        try {
            componentIndex = Integer.parseInt(indexString) - 1;
        } catch (NumberFormatException e) {
            throw new PPException("Please enter a postive integer within 16 bits");
        }
        // throw exception if index is out of bounds eg. "select cpu 100"
        if (componentIndex < 0
                || componentIndex >= dataStorage.stringToComponentListMap.get(componentTypeString).size()) {
            throw new PPException("Please enter a valid index");
        }

        int pcIndex = UI.pcBuilderMenu.getPCIndex();

        float currentCost = dataStorage.pcList.get(pcIndex).getCost();
        int pcBudget = dataStorage.pcList.get(pcIndex).getBudget();
        float componentPrice = dataStorage.stringToComponentListMap.get(componentTypeString).get(componentIndex)
                .getPrice();

        if (pcBudget != -1 && componentPrice + currentCost > pcBudget) {
            throw new PPException("You have exceeded your budget, component not added.");
        }

        dataStorage.pcList.get(pcIndex)
                .setComponent((Component) dataStorage.stringToComponentListMap.get(componentTypeString)
                        .get(componentIndex));

        return componentTypeString + " added! : " + dataStorage.stringToComponentListMap.get(componentTypeString).get(
                componentIndex).getName();
    }
}
