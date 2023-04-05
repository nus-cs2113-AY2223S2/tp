package seedu.duke.exceptions;

import seedu.duke.utils.Ui;


public class OutOfRangeException extends Exception{
    public void printOutOfRange() {
        System.out.println(Ui.LINE);
        System.out.println("The number you have entered exceeds the maximum limit of 99,999,999.");
        System.out.println(Ui.LINE);
    }
}
