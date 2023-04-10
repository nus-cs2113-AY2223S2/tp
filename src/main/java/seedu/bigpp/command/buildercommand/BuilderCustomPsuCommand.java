package seedu.bigpp.command.buildercommand;

import seedu.bigpp.component.psu.PSU;
import seedu.bigpp.datastorage.DataStorage;
import seedu.bigpp.exceptions.PPException;
import seedu.bigpp.pc.FormFactorEnum;
import seedu.bigpp.ui.UI;

public class BuilderCustomPsuCommand extends BuilderCustomComponentCommand {

    public BuilderCustomPsuCommand(String arguments) {
        super(arguments);
    }

    public int getExpectedArgumentLength() {
        return 6;
    }

    public String addNewComponent(String[] argumentList, DataStorage dataStorage, String name, String brand,
            float price)
            throws PPException {
        float power = 0;

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
        if (power < 0) {
            throw new PPException("power should be positive");
        }

        String efficiency = argumentList[3].trim().toLowerCase();
        String formFactor = argumentList[4].trim().toLowerCase();
        if (!efficiency.equals("gold") && !efficiency.equals("silver") && !efficiency.equals("bronze")) {
            throw new PPException("Please enter a valid efficiency for the custom component (gold, silver, bronze)");
        }
        if (FormFactorEnum.isFormFactor(formFactor) == false) {
            throw new PPException("Please enter a valid formfactor for the custom component (mini, micro, atx)");
        }
        PSU psu = new PSU(name, price, brand, efficiency, formFactor, power);

        return dataStorage.pcList.get(UI.pcBuilderMenu.getPCIndex()).setPsu(psu);
    }
}
