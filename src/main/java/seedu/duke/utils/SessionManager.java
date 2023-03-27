package seedu.duke.utils;

import seedu.duke.objects.Inventory;

/**
 * Class to manage the session of the program and its storage features.
 */
public class SessionManager {
    private static boolean isAutoSaveActive = true;

    public SessionManager() {
    }

    public static void writeSession(final Inventory inventory) {
        Storage.writeCSV(inventory);
    }

    public static Inventory getSession() {
        return Storage.readCSV();
    }

    public static boolean getAutoSave() {
        return isAutoSaveActive;
    }

    public static void setAutoSave(final boolean isAutoSaveActive) {
        SessionManager.isAutoSaveActive = isAutoSaveActive;
    }

    public static String inventoryDataFileExist() {
        return Storage.inventoryDataFileExist();
    }
}
