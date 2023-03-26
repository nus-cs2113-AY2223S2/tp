package seedu.duke.exceptions;

import static seedu.duke.utils.ColorCode.ANSI_RED;
import static seedu.duke.utils.ColorCode.ANSI_RESET;
public class StorageErrorException extends Exception{
    public void loadingError(){
        System.out.println(ANSI_RED + "There was an error in loading." + ANSI_RESET);
    }
}
