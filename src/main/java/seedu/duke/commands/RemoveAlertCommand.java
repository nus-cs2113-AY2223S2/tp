package seedu.duke.commands;

import seedu.duke.objects.AlertList;
import seedu.duke.objects.Inventory;
import seedu.duke.utils.Ui;

import java.util.HashMap;

public class RemoveAlertCommand extends Command {

    private AlertList alertList;
    private String upc;
    private String minmax;

    public RemoveAlertCommand(Inventory inventory, String upc, String minmax) {
        super(inventory);
        this.alertList = inventory.getAlertList();
        this.upc = upc;
        this.minmax = minmax;
    }

    public RemoveAlertCommand(Inventory inventory, String upc) {
        super(inventory);
        this.alertList = inventory.getAlertList();
        this.upc = upc;
    }

    private void checkRemoveAlertUpc() {
        if (upcCodes.containsKey(upc)) {
            removeAlert();
        } else {
            Ui.printItemNotFound();
        }
    }

    private void removeAlert() {

        if (minmax.equals(MIN_KEYWORD) && hasUpcInAlerts(alertList.getMinAlertUpcs())) {
            alertList.getMinAlertUpcs().remove(upc);
            Ui.printSuccessRemoveAlertCommand();
        } else if (minmax.equals(MAX_KEYWORD) && hasUpcInAlerts(alertList.getMaxAlertUpcs())) {
            alertList.getMaxAlertUpcs().remove(upc);
            Ui.printSuccessRemoveAlertCommand();
        } else {
            Ui.printNonExistentRemoveAlert();
        }
    }

    private boolean hasUpcInAlerts(HashMap<String, Integer> alertUpcMap) {
        if (alertUpcMap.containsKey(upc)) {
            return true;
        }
        return false;
    }

    @Override
    public void run() {
        checkRemoveAlertUpc();
    }
}
