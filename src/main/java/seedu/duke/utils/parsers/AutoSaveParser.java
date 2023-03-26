package seedu.duke.utils.parsers;

import seedu.duke.objects.Inventory;
import seedu.duke.utils.Ui;

public class AutoSaveParser extends Parser {

    public AutoSaveParser(String rawInput, Inventory inventory) {
        super(rawInput, inventory);
    }

    public void run() {
        if (rawInput.equals("on")) {
            session.setAutoSave(true);
            session.writeSession(inventory);
            Ui.printAutoSaveEnabled();
        } else if (rawInput.equals("off")) {
            session.setAutoSave(false);
            Ui.printAutoSaveDisabled();
        } else {
            Ui.printInvalidAutoSaveInput();
        }
    }

}
