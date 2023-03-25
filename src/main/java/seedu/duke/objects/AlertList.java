package seedu.duke.objects;
import java.util.ArrayList;
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
}
