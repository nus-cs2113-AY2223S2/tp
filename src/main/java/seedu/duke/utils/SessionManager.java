package seedu.duke.utils;

import seedu.duke.objects.Inventory;

public class SessionManager {
    private static boolean isAutoSaveActive = true;

    public SessionManager() {
    }

    public static void writeSession(Inventory inventory) {
        Storage.writeCSV(inventory);
    }

    public static Inventory getSession() {
        return Storage.readCSV();
    }

    public static boolean getAutoSave() {
        return isAutoSaveActive;
    }

    public void setAutoSave(boolean isAutoSaveActive) {
        this.isAutoSaveActive = isAutoSaveActive;
    }

    public static String inventoryDataFileExist() {
        return Storage.inventoryDataFileExist();
    }
}
