package seedu.duke.exceptions;

import seedu.duke.utils.Ui;


public class OutOfRangeException extends Exception{
    public void printOutOfRange() {
        Ui.printLine();
        System.out.println("The number you have entered exceeds the maximum limit of 99,999,999.");
        Ui.printLine();
    }

    public void printEditOutOfRange() {
        Ui.printLine();
        System.out.println("The number you have entered should be between 0 and 99,999,999.");
        Ui.printLine();
    }
}
