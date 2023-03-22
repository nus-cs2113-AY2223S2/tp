package seedu.duke.commands;
import seedu.duke.objects.Alert;
import seedu.duke.objects.AlertList;
import seedu.duke.objects.Inventory;

import java.util.ArrayList;
import java.util.HashMap;

public class AddAlertCommand extends Command  {

    private final Alert alert;
    private AlertList alertList;

    public AddAlertCommand(Inventory inventory, Alert alert, AlertList alertList) {
        super(inventory);
        this.alert = alert;
        this.alertList = alertList;
    }

    private void addAlertCommand() {
        //check if upc even exists in the first place
        //check if upc exists in corresponding hashmap
        //determine if min or max
        //add to min-hashmap or max-hashmap
        System.out.println("alert.getUpc() is " + alert.getUpc());

        if (!upcCodes.containsKey(alert.getUpc())) {
            System.out.println("alert.getMinmax is " + alert.getMinmax());
            if (alert.getMinmax().equals("min") && !alertList.getMinAlertUpcs().containsKey(alert.getUpc())) {
                alertList.setMinAlertUpcs(alert.getUpc(), alert.getStock());

            } else {
                System.out.println("This item already has a minimum alert. Delete the old one first.");
                return;
            }

            if (alert.getMinmax().equals("max") && !alertList.getMaxAlertUpcs().containsKey(alert.getUpc())) {
                alertList.setMaxAlertUpcs(alert.getUpc(), alert.getStock());
            } else {
                System.out.println("This item already has a maximum alert. Delete the old one first.");
                return;
            }

        } else {
            System.out.println("This item does not exist in the inventory");
        }


    }

    @Override
    public void run() {
        addAlertCommand();
    }


}
