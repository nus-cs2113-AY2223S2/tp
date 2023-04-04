package seedu.duke.exceptions;

import static seedu.duke.utils.Ui.LINE;

public class OutOfRangeException extends Exception{
    public void printOutOfRange() {
        System.out.println(LINE);
        System.out.println("The number you have entered is out of range! (Accepted range is 0 to 99999999)");
        System.out.println(LINE);
    }
}
