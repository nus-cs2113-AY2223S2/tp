package seedu.Parser;

import seedu.commands.Command;
import seedu.commands.EditCommand;
import seedu.commands.InvalidCommand;

public class ParseEdit {
    public static Command editItem(String line) {
        // Format: edit, index, d/date, a/amount, s/description
        int space = line.indexOf(" ");
        String[] allDetails = SplitCommand.splitCommand(space, line);
        String details = allDetails[1];

        int posDSlash = details.indexOf('/');
        String[] furtherDetails = SplitCommand.splitCommand(posDSlash, details);
        String indexVal = furtherDetails[0];
        String editDetails = furtherDetails[1];

        int posASlash = editDetails.indexOf('/');
        String[] dateAmountDescription = SplitCommand.splitCommand(posASlash,editDetails);
        String dateVal = dateAmountDescription[0];
        String editPriceAndDescription = dateAmountDescription[1];

        int posSSlash = editPriceAndDescription.indexOf('/');
        String[] amountDescription = SplitCommand.splitCommand(posSSlash,editPriceAndDescription);
        String amountVal = amountDescription[0];
        String descriptionVal = dateAmountDescription[1];

        try {
            int indexIntVal = Integer.parseInt(indexVal);
            System.out.println(indexIntVal);
            return new EditCommand(indexIntVal, dateVal, amountVal, descriptionVal);
        } catch (NumberFormatException numberFormatException) {
            return new InvalidCommand("Invalid");
        }
    }
}
