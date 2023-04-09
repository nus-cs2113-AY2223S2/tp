package seedu.duke.exceptions;

import seedu.duke.utils.Ui;

public class CategoryFormatException extends Exception {
    public void incorrectParameters() {
        System.out.println("Wrong/Incomplete Entry For Category! Please refer to UG for more information\n" +
                "Sample Format:" +
                "\nList all categories: \"cat list\"" +
                "\nList all items and all categories: \"cat table\"");
        Ui.printLine();
    }

    public void missingCategory() {
        System.out.println("Something went wrong, a Missing Category exception is caught, please try again!\n");
        Ui.printLine();
    }
}
