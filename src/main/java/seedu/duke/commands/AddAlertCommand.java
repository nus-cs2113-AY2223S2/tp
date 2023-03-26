package seedu.duke.commands;

import seedu.duke.objects.Alert;
import seedu.duke.objects.AlertList;
import seedu.duke.objects.Inventory;
import java.util.HashMap;

import seedu.duke.utils.Ui;


public class AddAlertCommand extends Command  {
    private final Alert alert;
    private AlertList alertList;



    public AddAlertCommand(Inventory inventory, Alert alert) {
        super(inventory);
        this.alert = alert;
        this.alertList = inventory.getAlertList();
    }

    private void checkAddAlertUpc() {

        if (upcCodes.containsKey(alert.getUpc())) {
            addAlertCommand();
        } else {
            Ui.printItemNotFound();
        }
    }

    private void addAlertCommand() {
        if (alert.getMinmax().equals(MIN_KEYWORD)) {
            addMinAlert();
        }

        if (alert.getMinmax().equals(MAX_KEYWORD)) {
            addMaxAlert();
        }
    }

    private void addMinAlert() {
        if (!alertList.getMinAlertUpcs().containsKey(alert.getUpc())) {
            if (isMinValueValid(alert.getStock(), alert.getUpc(), alertList.getMaxAlertUpcs())) {
                alertList.setMinAlertUpcs(alert.getUpc(), alert.getStock());
                Ui.printSuccessAddAlert();

            } else {
                Ui.printInvalidMinAlert();
            }
        } else {
            Ui.printExistingMinAlert();
        }

    }

    private void addMaxAlert() {
        if (!alertList.getMaxAlertUpcs().containsKey(alert.getUpc())) {
            if (isMaxValueValid(alert.getStock(), alert.getUpc(), alertList.getMinAlertUpcs())) {
                alertList.setMaxAlertUpcs(alert.getUpc(), alert.getStock());
                Ui.printSuccessAddAlert();
            } else {
                Ui.printInvalidMaxAlert();
            }
        } else {
            Ui.printExistingMaxAlert();
        }

    }

    private boolean isMinValueValid(int minStock, String upc, HashMap<String, Integer> maxUpcMap) {
        if (maxUpcMap.containsKey(upc) && maxUpcMap.get(upc) <= minStock) {
            return false;
        }
        return true;
    }

    private boolean isMaxValueValid(int maxStock, String upc, HashMap<String, Integer> minUpcMap) {
        if (minUpcMap.containsKey(upc) && minUpcMap.get(upc) >= maxStock) {
            return false;
        }
        return true;
    }


    @Override
    public void run() {
        checkAddAlertUpc();
    }
}
