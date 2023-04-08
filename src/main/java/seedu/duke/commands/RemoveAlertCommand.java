package seedu.duke.commands;

import seedu.duke.objects.AlertList;
import seedu.duke.objects.Inventory;
import seedu.duke.utils.SessionManager;
import seedu.duke.utils.Ui;

import java.util.HashMap;

/**
 * Represents the command for removing an alert.
 */
public class RemoveAlertCommand extends Command {

    private AlertList alertList;
    private String upc;
    private String minmax;

    /**
     * Constructor for the RemoveAlertCommand class.
     * @param inventory The inventory to be initialised in the Command class.
     * @param upc The UPC of the alert to be removed.
     * @param minmax The type of alert to be removed - either minimum or maximum.
     */
    public RemoveAlertCommand(Inventory inventory, String upc, String minmax) {
        super(inventory);
        this.alertList = inventory.getAlertList();
        this.upc = upc;
        this.minmax = minmax;
    }

    /**
     * Checks if the UPC of the alert to be removed belongs to an existing item in the inventory.
     */
    private void checkRemoveAlertUpc() {
        if (upcCodes.containsKey(upc)) {
            removeAlert();
        } else {
            Ui.printItemNotFound();
        }
    }


    /**
     * Removes an alert from its corresponding hash map.
     */
    private void removeAlert() {

        if (minmax.equals(MIN_KEYWORD) && hasUpcInAlerts(alertList.getMinAlertUpcs())) {
            alertList.getMinAlertUpcs().remove(upc);
            Ui.printSuccessRemoveAlertCommand();
            SessionManager.writeSession(alertList);
        } else if (minmax.equals(MAX_KEYWORD) && hasUpcInAlerts(alertList.getMaxAlertUpcs())) {
            alertList.getMaxAlertUpcs().remove(upc);
            Ui.printSuccessRemoveAlertCommand();
            SessionManager.writeSession(alertList);
        } else {
            Ui.printNonExistentRemoveAlert();
        }
    }

    /**
     * Checks if the UPC of the alert to be removed belongs to an existing alert.
     * @param alertUpcMap The hash map to be checked for the UPC.
     * @return True if the hash map contains the UPC of the alert to be removed, false otherwise.
     */
    private boolean hasUpcInAlerts(HashMap<String, Integer> alertUpcMap) {
        return alertUpcMap.containsKey(upc);
    }


    /**
     * Executes the command for the removing of an alert.
     */
    @Override
    public void run() {
        checkRemoveAlertUpc();
    }
}
