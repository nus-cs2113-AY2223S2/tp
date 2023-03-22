package seedu.duke.commands;

import seedu.duke.objects.Alert;
import seedu.duke.objects.AlertList;
import seedu.duke.objects.Inventory;

import seedu.duke.utils.Ui;


public class AddAlertCommand extends Command  {

    private final Alert alert;
    private AlertList alertList;

    private static final String MIN_KEYWORD = "min";
    private static final String MAX_KEYWORD = "max";

    public AddAlertCommand(Inventory inventory, Alert alert, AlertList alertList) {
        super(inventory);
        this.alert = alert;
        this.alertList = alertList;
    }

    private void checkAlertUpc() {

        if (upcCodes.containsKey(alert.getUpc())) {
            addAlertCommand();
        } else {
            Ui.printItemNotFound();
        }
    }

    private void addAlertCommand() {
        if (alert.getMinmax().equals(MIN_KEYWORD)) {

            if (!alertList.getMinAlertUpcs().containsKey(alert.getUpc())) {
                alertList.setMinAlertUpcs(alert.getUpc(), alert.getStock());
            } else {
                Ui.printExistingMinAlert();
            }
        }

        if (alert.getMinmax().equals(MAX_KEYWORD)) {

            if (!alertList.getMaxAlertUpcs().containsKey(alert.getUpc())) {
                alertList.setMaxAlertUpcs(alert.getUpc(), alert.getStock());
            } else {
                Ui.printExistingMaxAlert();
            }
        }
    }

    /*
     TODO:
     Check if minimum alert is set to be more than an existing max alert/
     check if maximum alert is set to be less than an existing min alert
    */


    @Override
    public void run() {
        checkAlertUpc();
    }
}
