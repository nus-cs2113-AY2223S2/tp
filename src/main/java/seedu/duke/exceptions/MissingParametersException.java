package seedu.duke.exceptions;


public class MissingParametersException extends Exception {
    public static final String LINE = "____________________________________________________________";

    public void missingAddItemParameters() {
        System.out.println(LINE);
        System.out.println("Add parameters incomplete! Please follow the format provided in the user " +
                "guide!");
        System.out.println(LINE);
    }

    public void missingSearchItemParameters() {
        System.out.println(LINE);
        System.out.println("Search parameters incomplete! Please follow the format provided in the user " +
                "guide!");
        System.out.println(LINE);
    }

    public void missingHistoryItemParameters() {
        System.out.println(LINE);
        System.out.println("History parameters incorrect! Please follow the format provided in the user " +
                "guide!");
        System.out.println(LINE);
    }

    public void missingRemoveItemParameters() {
        System.out.println(LINE);
        System.out.println("Remove parameters incomplete! Please follow the format(s) below\n" +
                "Remove by UPC: remove f/upc [UPC]\n" + "Remove by item index: remove f/index [INDEX]");
        System.out.println(LINE);
    }

    public void missingAlertParameters() {
        System.out.println(LINE);
        System.out.println("Alert parameters incomplete! Please follow the format provided " +
                "in the user guide!");
        System.out.println(LINE);
    }

    public void missingAddAlertParameters() {
        System.out.println(LINE);
        System.out.println("Add alert parameters incomplete! Please follow the format provided in the user "
                + "guide!");
        System.out.println(LINE);
    }

    public void missingRemoveAlertParameters() {
        System.out.println(LINE);
        System.out.println("Remove alert parameters incomplete! Please follow the format provided in the " +
                "user guide!");
        System.out.println(LINE);
    }
}
