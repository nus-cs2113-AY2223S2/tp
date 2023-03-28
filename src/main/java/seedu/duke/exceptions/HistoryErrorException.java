package seedu.duke.exceptions;

import static seedu.duke.utils.ColorCode.ANSI_RED;
import static seedu.duke.utils.ColorCode.ANSI_RESET;

public class HistoryErrorException extends Exception{
    public void incorrectParameters(){
        System.out.println(ANSI_RED + "Wrong/Incomplete Format! Please use the format: history [UPC]"  + ANSI_RESET);
    }
}
