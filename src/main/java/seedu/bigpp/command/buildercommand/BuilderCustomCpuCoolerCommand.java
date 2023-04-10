package seedu.bigpp.command.buildercommand;

import seedu.bigpp.component.cpucooler.CPUCooler;
import seedu.bigpp.datastorage.DataStorage;
import seedu.bigpp.exceptions.PPException;
import seedu.bigpp.ui.UI;

public class BuilderCustomCpuCoolerCommand extends BuilderCustomComponentCommand {

    public BuilderCustomCpuCoolerCommand(String arguments) {
        super(arguments);
    }

    public int getExpectedArgumentLength() {
        return 6;
    }

    public String addNewComponent(String[] argumentList, DataStorage dataStorage, String name, String brand,
            float price)
            throws PPException {
        float power = 0;
        float noise = 0;
        int rpm = 0;

        try {
            rpm = Integer.parseInt(argumentList[3]);
        } catch (NumberFormatException e) {
            throw new PPException(
                    "Please enter a valid rpm");
        }

        try {
            noise = Float.parseFloat(argumentList[4]);

            if (noise > 500) {
                throw new PPException(
                        "Too large of a noise level");
            }
        } catch (NumberFormatException e) {
            throw new PPException(
                    "Please enter a valid noise level");
        }

        try {
            power = Float.parseFloat(argumentList[5]);
            if (power > 5000) {
                throw new PPException(
                        "Too large of a power");
            }

        } catch (NumberFormatException e) {
            throw new PPException(
                    "Please enter a valid power");
        }

        // Check if all the values are positive
        if (power < 0 || rpm < 0 || noise < 0) {
            throw new PPException("power, rpm and noise should be positive");
        }

        CPUCooler cpuCooler = new CPUCooler(name, brand, price, rpm, noise, power);

        return dataStorage.pcList.get(UI.pcBuilderMenu.getPCIndex()).setCpuCooler(cpuCooler);
    }
}
