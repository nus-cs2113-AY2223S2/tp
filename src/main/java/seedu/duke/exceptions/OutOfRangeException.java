package seedu.duke.exceptions;

import seedu.duke.utils.Ui;


public class OutOfRangeException extends Exception{
    public void printOutOfRange() {
        Ui.printLine();
        System.out.println("The number you have entered is out of range! (Accepted range is 0 to 99999999)");
        Ui.printLine();
    }
}
