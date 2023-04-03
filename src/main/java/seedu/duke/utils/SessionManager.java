package seedu.duke.utils;

import seedu.duke.objects.AlertList;
import seedu.duke.objects.Inventory;
import seedu.duke.types.Types;

/**
 * Class to manage the session of the program and its storage features.
 */
public class SessionManager {
    private static boolean isAutoSaveActive = true;

    public static void writeSession(final Inventory inventory) {
        Storage.writeCSV(inventory);
    }

    public static void writeSession(final AlertList alertList) {
        Storage.writeCSV(alertList);
    }

    public static Inventory getSession() {
        return Storage.readCSV(Types.SESSIONFILEPATH);
    }

    public static AlertList getSessionAlerts() {
        return Storage.readAlertCSV();
    }

    public static boolean getAutoSave() {
        return isAutoSaveActive;
    }

    public static void setAutoSave(final boolean isAutoSaveActive) {
        SessionManager.isAutoSaveActive = isAutoSaveActive;
    }

    public static String inventoryDataFileExist() {
        return Storage.checkDataFileExist(true);
    }

    public static String alertDataFileExist() {
        return Storage.checkDataFileExist(false);
    }


}
