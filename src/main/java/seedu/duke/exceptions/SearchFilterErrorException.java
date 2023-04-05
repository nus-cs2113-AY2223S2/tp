package seedu.duke.exceptions;


import seedu.duke.utils.Ui;

public class SearchFilterErrorException extends Exception {
    public void missingPriceComparator() {
        System.out.println("Price comparator missing or malformed! Please use the format: filter " +
                "f/{price/category} {p/[gt/get/lt/let] [Price] or [Category keywords]}");
        Ui.printLine();
    }

    public void incorrectSearchParameters() {
        System.out.println("Wrong/Incomplete Entry For Search by Keywords! Please refer to UG for more information" +
                "\nSample Format: \"search [KEYWORDS]\"");
        Ui.printLine();
    }

    public void incorrectSearchUPCParameters() {
        System.out.println("Wrong/Incomplete Entry For Search by UPC! Please refer to UG for more information\nSample "
                + "Format: \"searchupc [UPC]\"");
        Ui.printLine();
    }

    public void incorrectFilterParameters() {
        System.out.println("Wrong/Incomplete Entry For Filter! Please refer to UG for more information\n" +
                "Sample Format:\n For price filter: \"filter f/price p/{gt/get/lt/let} [Price]\"\n " +
                "For category filter: \"filter f/category [Keywords]\"");
        Ui.printLine();
    }

    public void missingCategoryParameters() {
        System.out.println("Missing keywords to search for appropriate category! Please use the format: \"filter " +
                "f/category [Keywords]\"");
        Ui.printLine();
    }
}
