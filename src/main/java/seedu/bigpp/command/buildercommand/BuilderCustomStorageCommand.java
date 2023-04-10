package seedu.bigpp.command.buildercommand;

import seedu.bigpp.component.storage.Storage;
import seedu.bigpp.datastorage.DataStorage;
import seedu.bigpp.exceptions.PPException;
import seedu.bigpp.ui.UI;

public class BuilderCustomStorageCommand extends BuilderCustomComponentCommand {

    public BuilderCustomStorageCommand(String arguments) {
        super(arguments);
    }

    public int getExpectedArgumentLength() {
        return 6;
    }

    public String addNewComponent(String[] argumentList, DataStorage dataStorage, String Exception, String brand,
            float price)
            throws PPException {
        float power = 0;
        int size = 0;

        try {
            size = Integer.parseInt(argumentList[4]);
        } catch (NumberFormatException e) {
            throw new PPException(
                    "Size should be 512, 1024, 2048 or 4096");
        }

        if (size != 512 && size != 1024 && size != 2048 && size != 4096) {
            throw new PPException(
                    "Size should be 512, 1024, 2048 or 4096");
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
        if (power < 0 || size < 0) {
            throw new PPException("power and size should be positive");
        }

        String type = argumentList[3].trim();
        if (!type.equals("ssd") && !type.equals("hdd")) {
            throw new PPException("Please enter a valid type for the custom component (ssd, hdd)");
        }
        Storage storage = new Storage(Exception, brand, price, type, size, power);

        return dataStorage.pcList.get(UI.pcBuilderMenu.getPCIndex()).setStorage(storage);
    }
}
