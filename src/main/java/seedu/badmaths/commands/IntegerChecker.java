package seedu.badmaths.commands;

import seedu.badmaths.ui.Ui;

public class IntegerChecker {
    public static boolean isAnInt(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            Ui.printInvalidNumberEntered();
            return false;
        }
    }
}
