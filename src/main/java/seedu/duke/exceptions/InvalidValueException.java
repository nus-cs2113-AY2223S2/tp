package seedu.duke.exceptions;

import static seedu.duke.utils.ColorCode.ANSI_RED;
import static seedu.duke.utils.ColorCode.ANSI_RESET;

public class InvalidValueException extends Exception {
    public static final String LINE = "____________________________________________________________";

    public void quantityOutOfRangeException() {
        System.out.println(LINE);
        System.out.println(ANSI_RED + "Quantity you have provided is out of range! Please provided a number between" +
                "0 - 2,147,483,647" + ANSI_RESET);
        System.out.println(LINE);
    }

}
