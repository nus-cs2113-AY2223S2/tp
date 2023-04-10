package seedu.bigpp.command.buildercommand.comparecommand;

import seedu.bigpp.command.Command;
import seedu.bigpp.component.psu.PSU;
import seedu.bigpp.datastorage.DataStorage;
import seedu.bigpp.exceptions.PPException;

import static seedu.bigpp.component.ComponentType.PSU_TYPE;

public class BuilderComparePsuCommand extends Command {
    public BuilderComparePsuCommand(String arguments) {
        setArguments(arguments);
    }

    /**
     * Compare all the specifications between 2 selected psu components
     * @return the comparison table of the 2 components
     */
    @Override
    public String executeCommand(DataStorage dataStorage)
            throws PPException {
        String inputString = getArguments();

        // throw exception if command does not contain "&"
        if (!inputString.contains("&")) {
            throw new PPException("Please input an index after selecting a component");
        }

        String[] componentIndexes = inputString.split("&", 2);
        String firstComponent = componentIndexes[0].trim();
        String secondComponent = componentIndexes[1].trim();

        // check if firstComponent and secondComponent is a number
        int firstComponentIndex;
        int secondComponentIndex;
        try {
            firstComponentIndex = Integer.parseInt(firstComponent) - 1;
            secondComponentIndex = Integer.parseInt(secondComponent) - 1;
        } catch (NumberFormatException e) {
            throw new PPException("Please enter a positive integer within 16 bits");
        }

        // check if index is out of bounds
        if (firstComponentIndex < 0 || firstComponentIndex >= dataStorage.stringToComponentListMap.get(PSU_TYPE)
                .size()) {
            throw new PPException("Please enter a valid index");
        }
        if (secondComponentIndex < 0 || secondComponentIndex >= dataStorage.stringToComponentListMap.get(PSU_TYPE)
                .size()) {
            throw new PPException("Please enter a valid index");
        }

        // get the 2 components
        PSU firstComponentObject = (PSU) dataStorage.stringToComponentListMap.get(PSU_TYPE).get(firstComponentIndex);
        PSU secondComponentObject = (PSU) dataStorage.stringToComponentListMap.get(PSU_TYPE).get(secondComponentIndex);

        // format the comparison table in outputString
        String outputString = String.format("%96s", "_".repeat(96));
        outputString += String.format("%n|%-12s|%-40s|%-40s|", "NAME", firstComponentObject.getName(),
                secondComponentObject.getName());
        outputString += String.format("%n|%-12s|%-40s|%-40s|", "-".repeat(12), "-".repeat(40), "-".repeat(40));
        outputString += String.format("%n|%-12s|%-40s|%-40s|", "PRICE", "$" + firstComponentObject.getPrice(),
                "$" + secondComponentObject.getPrice());
        outputString += String.format("%n|%-12s|%-40s|%-40s|", "EFFICIENCY", firstComponentObject.getEfficiency(),
                secondComponentObject.getEfficiency());
        outputString += String.format("%n|%-12s|%-40s|%-40s|", "FORM FACTOR", firstComponentObject.getFormFactor(),
                secondComponentObject.getFormFactor());
        outputString += String.format("%n|%-12s|%-40s|%-40s|", "POWER", firstComponentObject.getPower() + "W",
                secondComponentObject.getPower() + "W");
        outputString += String.format("%n%96s", "_".repeat(96));
        return outputString;
    }
}
