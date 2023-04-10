package seedu.bigpp.command.buildercommand;

import seedu.bigpp.component.chassis.Chassis;
import seedu.bigpp.datastorage.DataStorage;
import seedu.bigpp.exceptions.PPException;
import seedu.bigpp.pc.FormFactorEnum;
import seedu.bigpp.ui.UI;

public class BuilderCustomChassisCommand extends BuilderCustomComponentCommand {

    public BuilderCustomChassisCommand(String arguments) {
        super(arguments);
    }

    public int getExpectedArgumentLength() {
        return 4;
    }

    public String addNewComponent(String[] argumentList, DataStorage dataStorage, String name, String brand,
            float price)
            throws PPException {

        String formFactor = argumentList[3].trim().toLowerCase();
        if (FormFactorEnum.isFormFactor(formFactor) == false) {
            throw new PPException("Please enter a valid formfactor for the custom component (mini, micro, atx)");
        }
        Chassis chassis = new Chassis(name, brand, price, formFactor);

        return dataStorage.pcList.get(UI.pcBuilderMenu.getPCIndex()).setChassis(chassis);
    }
}
