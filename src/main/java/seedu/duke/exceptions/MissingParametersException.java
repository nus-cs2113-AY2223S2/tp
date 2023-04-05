package seedu.duke.exceptions;


public class MissingParametersException extends Exception {
    public static final String LINE = "____________________________________________________________";

    public void missingAddItemParameters() {
        System.out.println(LINE);
        System.out.println("Add parameters incomplete! Please follow the format provided in the user " +
                "guide!");
        System.out.println(LINE);
    }

    public void missingRemoveItemParameters() {
        System.out.println(LINE);
        System.out.println("Wrong/Incomplete Entry For Remove! Please refer to UG for more information" +
                "\nSample Format:\nRemove by UPC: \"remove f/upc [UPC]\"\nOR\n" + "Remove by item index: \"remove " +
                "f/index [INDEX]\"");
        System.out.println(LINE);
    }

    public void missingAlertParameters() {
        System.out.println(LINE);
        System.out.println("Wrong/Incomplete Entry For Alert! Please refer to UG for more information");
        System.out.println(LINE);
    }
}
