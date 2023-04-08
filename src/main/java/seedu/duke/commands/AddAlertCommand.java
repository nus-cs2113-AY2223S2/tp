package seedu.duke.commands;

import seedu.duke.objects.Alert;
import seedu.duke.objects.AlertList;
import seedu.duke.objects.Inventory;
import java.util.HashMap;

import seedu.duke.utils.SessionManager;
import seedu.duke.utils.Ui;


/**
 * Represents the command for adding an alert.
 */
public class AddAlertCommand extends Command  {
    private final Alert alert;
    private AlertList alertList;


    /**
     * Constructor for the AddAlertCommand class.
     * @param inventory The inventory to be initialised in the Command class.
     * @param alert The alert to be added to the alert list in the inventory.
     */
    public AddAlertCommand(final Inventory inventory, final Alert alert) {
        super(inventory);
        this.alert = alert;
        this.alertList = inventory.getAlertList();
    }

    /**
     * Checks if the UPC of the alert to be added is one of an existing item in the inventory.
     */
    private void checkAddAlertUpc() {

        if (upcCodes.containsKey(alert.getUpc())) {
            addAlert();
        } else {
            Ui.printItemNotFound();
        }
    }

    /**
     * Adds either a minimum or maximum alert to the alert list.
     */
    private void addAlert() {
        if (alert.getMinmax().equals(MIN_KEYWORD)) {
            addMinAlert();
        } else if (alert.getMinmax().equals(MAX_KEYWORD)) {
            addMaxAlert();
        } else {
            assert false: Ui.printInvalidAlertType();
        }
    }

    /**
     * Adds a minimum alert to the alert list.
     */
    private void addMinAlert() {
        if (!alertList.getMinAlertUpcs().containsKey(alert.getUpc())) {
            if (isMinValueValid(alert.getStock(), alert.getUpc(), alertList.getMaxAlertUpcs())) {
                alertList.setMinAlertUpcs(alert.getUpc(), alert.getStock());
                Ui.printSuccessAddAlert();
                SessionManager.writeSession(alertList);

                alertList.checkAlerts(alert.getUpc(), inventory.getUpcCodes().get(alert.getUpc()).getName(),
                        inventory.getUpcCodes().get(alert.getUpc()).getQuantity());

            } else {
                Ui.printInvalidMinAlert();
            }
        } else {
            Ui.printExistingMinAlert();
        }

    }

    /**
     * Adds a maximum alert to the alert list.
     */
    private void addMaxAlert() {
        if (!alertList.getMaxAlertUpcs().containsKey(alert.getUpc())) {
            if (isMaxValueValid(alert.getStock(), alert.getUpc(), alertList.getMinAlertUpcs())) {
                alertList.setMaxAlertUpcs(alert.getUpc(), alert.getStock());
                Ui.printSuccessAddAlert();
                SessionManager.writeSession(alertList);

                alertList.checkAlerts(alert.getUpc(), inventory.getUpcCodes().get(alert.getUpc()).getName(),
                        inventory.getUpcCodes().get(alert.getUpc()).getQuantity());
            } else {
                Ui.printInvalidMaxAlert();
            }
        } else {
            Ui.printExistingMaxAlert();
        }

    }

    /**
     * Checks if the value of a minimum alert is valid.
     * @param minStock The value specified in the minimum alert.
     * @param upc The UPC of the minimum alert.
     * @param maxUpcMap The hash map containing maximum alerts.
     * @return True if there are no maximum alerts whose value is less than or equal to the minimum
     *         alert's value, false otherwise.
     */
    private boolean isMinValueValid(final int minStock, final String upc, final HashMap<String, Integer> maxUpcMap) {
        return !maxUpcMap.containsKey(upc) || maxUpcMap.get(upc) > minStock;
    }

    /**
     * Checks if the value of a maximum alert is valid.
     * @param maxStock The value specified in the maximum alert.
     * @param upc The UPC of the maximum alert.
     * @param minUpcMap The hash map containing minimum alerts.
     * @return True if there are no minimum alerts whose value is less than or equal to the maximum
     *         alert's value, false otherwise.
     */
    private boolean isMaxValueValid(final int maxStock, final String upc, final HashMap<String, Integer> minUpcMap) {
        return !minUpcMap.containsKey(upc) || minUpcMap.get(upc) < maxStock;
    }

    /**
     * Executes the command for the adding of an alert.
     */
    @Override
    public void run() {
        checkAddAlertUpc();
    }
}
