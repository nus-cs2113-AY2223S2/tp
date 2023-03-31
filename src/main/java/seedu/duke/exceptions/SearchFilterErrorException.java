package seedu.duke.exceptions;


public class SearchFilterErrorException extends Exception {
    public void missingPriceComparator() {
        System.out.println("Please enter the price comparator!");
    }

    public void incorrectSearchParameters() {
        System.out.println("Wrong/Incomplete Format! Please use the format: search [KEYWORD]");
    }

    public void incorrectSearchUPCParameters() {
        System.out.println("Wrong/Incomplete Format! Please use the format: searchupc [UPC]");
    }

    public void incorrectFilterParameters() {
        System.out.println("Wrong/Incomplete Format! Please use the format: filter f/{price/category} "
                + "{p/[gt/get/lt/let] [Price] or [Category keywords]");
    }
}
