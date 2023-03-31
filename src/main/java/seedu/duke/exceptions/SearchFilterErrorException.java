package seedu.duke.exceptions;


public class SearchFilterErrorException extends Exception {
    public void missingPriceComparator() {
         System.out.println("Price comparator missing or malformed! Please use the format: filter " +
                "f/{price/category} {p/[gt/get/lt/let] [Price] or [Category keywords]}");
    }

    public void incorrectSearchParameters() {
        System.out.println("Wrong/Incomplete Format! Please use the format:" +
                " search [KEYWORDS]");
    }

    public void incorrectSearchUPCParameters() {
        System.out.println("Wrong/Incomplete Format! Please use the format: searchupc [UPC]");
    }

    public void incorrectFilterParameters() {
        System.out.println("Wrong/Incomplete Format! Please use the format: filter f/{price/category} "
                + "{p/[gt/get/lt/let] [Price] or [Category keywords]}");
    }
}
