package seedu.duke;


import seedu.duke.objects.Inventory;
import seedu.duke.utils.SessionManager;
import seedu.duke.utils.Ui;
import seedu.duke.utils.ParserHandler;

public class MagusStock {
    /**
     * Main entry-point for the java.duke.Duke application.
     */
    private ParserHandler parserHandler;
    private Inventory inventory;

    public MagusStock() {
        Ui.greetUser();
        inventory = new Inventory();
        inventory = SessionManager.getSession();
        inventory.setAlertList(SessionManager.getSessionAlerts());
        parserHandler = new ParserHandler(inventory);
    }

    public void run() {
        while (true) {
            parserHandler.run();
        }
    }

    public static void main(String[] args) {
        System.setProperty("file.encoding", "UTF-8");
        new MagusStock().run();
    }
}
