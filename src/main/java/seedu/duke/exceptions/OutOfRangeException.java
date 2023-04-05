package seedu.duke.exceptions;

import static seedu.duke.utils.Ui.LINE;

public class OutOfRangeException extends Exception{
    public void printOutOfRange() {
        System.out.println(LINE);
        System.out.println("The number you have entered exceeds the maximum limit of 99,999,999.");
        System.out.println(LINE);
    }
}
