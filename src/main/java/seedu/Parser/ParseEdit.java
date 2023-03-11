package seedu.Parser;

import seedu.commands.Command;
import seedu.commands.EditCommand;
import seedu.commands.InvalidCommand;

public class ParseEdit {
    public static Command editItem(String line) {
        // Format: edit, index, d/date, a/amount, s/description
        int space = line.indexOf(" ");
        String details = line.substring(space);
        System.out.println("Details : " + details);
        int posDSlash = details.indexOf('/');
        String indexVal = details.substring(1, posDSlash-2);
        String editDetails = details.substring(posDSlash+1);
        System.out.println("indexval = " + indexVal);
        System.out.println("editdetails = " + editDetails);

        int posASlash = editDetails.indexOf('/');
        String dateVal = editDetails.substring(0, posASlash-2);
        System.out.println("dateval = " + dateVal);
        String editPriceAndDescription = editDetails.substring(posASlash+1);
        System.out.println("editpriceand = " + editPriceAndDescription);
        int posSSlash = editPriceAndDescription.indexOf('/');
        String amountVal = editPriceAndDescription.substring(0, posSSlash-2);
        System.out.println("amountval = " + amountVal);
        String descriptionVal = editPriceAndDescription.substring(posSSlash+1);
        System.out.println("descriptionval = " + descriptionVal);

        try {
            int indexIntVal = Integer.parseInt(indexVal);
            System.out.println(indexIntVal);
            return new EditCommand(indexIntVal, dateVal, amountVal, descriptionVal);
        } catch (NumberFormatException numberFormatException) {
            return new InvalidCommand("Invalid");
        }
    }
}
