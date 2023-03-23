package seedu.duke.commands;

import seedu.duke.objects.Alert;
import seedu.duke.objects.AlertList;
import seedu.duke.objects.Inventory;
import seedu.duke.utils.Ui;

public class RemoveAlertCommand extends Command {

    private AlertList alertList;
    private String upc;
    private String minmax;

    public RemoveAlertCommand(Inventory inventory, AlertList alertList, String upc, String minmax) {
        super(inventory);
        this.alertList = alertList;
        this.upc = upc;
        this.minmax = minmax;

    }

    private void checkRemoveAlertUpc() {
        if (upcCodes.containsKey(upc)) {
            //ddAlertCommand();
        } else {
            Ui.printItemNotFound();
        }

        System.out.println("entered checkremovealertupc");
    }

    @Override
    public void run() {
        checkRemoveAlertUpc();
    }

}
