package seedu.duke.utils;

import seedu.duke.objects.Inventory;

public class SessionManager {
    private static SessionManager sessionManager = null;

    private SessionManager() {
    }
    public static SessionManager getInstance() {
        if (sessionManager == null) {
            sessionManager = new SessionManager();
        }
        return sessionManager;
    }

    public static void writeSession(Inventory inventory) {
        Storage.writeCSV(inventory);
    }

    public static Inventory getSession() {
        return Storage.readCSV();
    }
}
