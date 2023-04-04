package seedu.duke.exceptions;

import seedu.duke.utils.Ui;

public class CategoryFormatException extends Exception {
    public void incorrectParameters(){
        System.out.println("Wrong/Incorrect Format! Please use either of the formats:\nList all categories: cat list" +
                "\nList all items and all categories:\ncat table" );
        Ui.printLine();
    }
}
