package seedu.duke;


import seedu.duke.objects.Inventory;
import seedu.duke.types.Types;
import seedu.duke.utils.SessionManager;
import seedu.duke.utils.Storage;
import seedu.duke.utils.Ui;
import seedu.duke.objects.AlertList;
import seedu.duke.utils.ParserHandler;

public class Duke {
    /**
     * Main entry-point for the java.duke.Duke application.
     */
    private Storage storage;
    private Ui ui;
    private ParserHandler parserHandler;
    private Inventory inventory;
    private SessionManager currentSession;
    private AlertList alertList;


    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        inventory = new Inventory();
        inventory = currentSession.getSession();
        alertList = new AlertList();
        parserHandler = new ParserHandler(inventory, alertList);
    }

    public void run() {
        while (true) {
            parserHandler.run();
        }
    }

    public static void main(String[] args) {
        new Duke(Types.SESSIONFILEPATH).run();
    }
}
