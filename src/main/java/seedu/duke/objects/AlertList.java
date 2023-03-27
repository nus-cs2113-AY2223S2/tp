package seedu.duke.objects;
import seedu.duke.utils.Ui;

import java.util.HashMap;

public class AlertList {
    private final HashMap<String, Integer> minAlertUpcs = new HashMap<>();
    private final HashMap<String, Integer> maxAlertUpcs = new HashMap<>();

    public HashMap<String, Integer> getMinAlertUpcs() {
        return minAlertUpcs;
    }

    public HashMap<String, Integer> getMaxAlertUpcs() {
        return maxAlertUpcs;
    }

    public int getTotalAlertNumber() {
        return  minAlertUpcs.size() + maxAlertUpcs.size();
    }
    public void setMinAlertUpcs(String upc, int min) {
        this.minAlertUpcs.put(upc, min);
    }

    public void setMaxAlertUpcs(String upc, int max) {
        this.maxAlertUpcs.put(upc, max);
    }

    public void checkAlerts(String upc, String name, int currentQuantity) {
        //AlertList alertList = getAlertList();
        //removing alert causes it to drop below minimum
        //but also needs to check if above maximum

        if (getMinAlertUpcs().containsKey(upc)) {
            //check if has fallen below qty or not
            if (getMinAlertUpcs().get(upc) > currentQuantity) {
                //System.out.println("ALERT: The quantity of " + name + " is below the minimum level.");
                Ui.printMinAlertWarning(name, getMinAlertUpcs().get(upc));
            }
        }

        if (getMaxAlertUpcs().containsKey(upc)) {
            //check if has fallen below qty or not
            if (getMaxAlertUpcs().get(upc) < currentQuantity) {
                //System.out.println("ALERT: The quantity exceeds the maximum level.");
                Ui.printMaxAlertWarning(name, getMaxAlertUpcs().get(upc));
            }
        }
    }


}
