package seedu.duke.utils.parsers;

import seedu.duke.objects.Inventory;
import seedu.duke.utils.SessionManager;
import seedu.duke.utils.Ui;

public class AutoSaveParser extends Parser {

    public AutoSaveParser(String rawInput, Inventory inventory) {
        super(rawInput, inventory);
    }

    public void run() {
        if (rawInput.equals("on")) {
            SessionManager.setAutoSave(true);
            SessionManager.writeSession(inventory);
            Ui.printAutoSaveEnabled();
        } else if (rawInput.equals("off")) {
            SessionManager.setAutoSave(false);
            Ui.printAutoSaveDisabled();
        } else {
            Ui.printInvalidAutoSaveInput();
        }
    }

}
