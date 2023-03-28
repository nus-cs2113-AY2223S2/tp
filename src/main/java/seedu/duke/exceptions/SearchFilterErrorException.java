package seedu.duke.exceptions;

import static seedu.duke.utils.ColorCode.ANSI_RED;
import static seedu.duke.utils.ColorCode.ANSI_RESET;

public class SearchFilterErrorException extends Exception {
    public void missingPriceComparator() {
        System.out.println(ANSI_RED + "Please enter the price comparator!" + ANSI_RESET);
    }
    public void incorrectSearchParameters(){
        System.out.println(ANSI_RED + "Wrong/Incomplete Format! Please use the format: search [KEYWORD]"  + ANSI_RESET);
    }
    public void incorrectSearchUPCParameters(){
        System.out.println(ANSI_RED + "Wrong/Incomplete Format! Please use the format: searchupc [UPC]"  + ANSI_RESET);
    }
    public void incorrectFilterParameters(){
        System.out.println(ANSI_RED + "Wrong/Incomplete Format! Please use the format: filter f/{price/category} "
                + "{p/[gt/get/lt/let] [Price] or [Category keywords]"  + ANSI_RESET);
    }
}
