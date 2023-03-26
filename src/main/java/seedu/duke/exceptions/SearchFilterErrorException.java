package seedu.duke.exceptions;

import static seedu.duke.utils.ColorCode.ANSI_RED;
import static seedu.duke.utils.ColorCode.ANSI_RESET;

public class SearchFilterErrorException extends Exception {
    public void missingPriceComparator() {
        System.out.println(ANSI_RED + "Please enter the price comparator!" + ANSI_RESET);
    }
}
