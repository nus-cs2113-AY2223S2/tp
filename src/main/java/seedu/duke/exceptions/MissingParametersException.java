package seedu.duke.exceptions;

import seedu.duke.utils.Ui;

public class MissingParametersException extends Exception {
    public static final String LINE = "____________________________________________________________";

    public void missingAddItemParameters() {
        Ui.printLine();
        System.out.println("Add parameters incomplete! Please follow the format provided in the user " +
                "guide!");
        Ui.printLine();
    }

    public void missingRemoveItemParameters() {
        Ui.printLine();
        System.out.println("Wrong/Incomplete Entry For Remove! Please refer to UG for more information" +
                "\nSample Format:\nRemove by UPC: \"remove f/upc [UPC]\"\nOR\n" + "Remove by item index: \"remove " +
                "f/index [INDEX]\"");
        Ui.printLine();
    }

    public void missingAlertParameters() {
        Ui.printLine();
        System.out.println("Wrong/Incomplete Entry For Alert! Please refer to UG for more information");
        Ui.printLine();
    }
}
