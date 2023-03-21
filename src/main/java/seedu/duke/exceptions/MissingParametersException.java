package seedu.duke.exceptions;

import static seedu.duke.utils.ColorCode.ANSI_RED;
import static seedu.duke.utils.ColorCode.ANSI_RESET;

public class MissingParametersException extends Exception {
    public void missingAddItemParameters() {
        System.out.println(ANSI_RED + "Add parameters incomplete! Please follow the format provided in the user " +
                "guide!" + ANSI_RESET);
    }

    public void missingSearchItemParameters() {
        System.out.println(ANSI_RED + "Search parameters incomplete! Please follow the format provided in the user " +
                "guide!" + ANSI_RESET);
    }

    public void missingRemoveItemParameters() {
        System.out.println(ANSI_RED + "Remove parameters incomplete! Please follow the format(s) below\n" +
                "Remove by UPC: remove f/item upc/[UPC]\n" + "Remove by item index: remove f/index [INDEX]" +
                ANSI_RESET);
    }
}
