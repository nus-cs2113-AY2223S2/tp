package seedu.Parser;

import seedu.commands.*;

import java.time.LocalDate;

public class ParseLendBorrow {
    public static Command lendBorrowItem(String line, String command) {
        // Format: lend s/description a/amount n/borrower_name d/lent_date b/deadline
        // Format: borrow s/description a/amount n/borrower_name d/lent_date b/deadline
        String description;
        double amount;
        String name;
        LocalDate deadline;
        LocalDate lentDate;

        try {
            int space = line.indexOf(" ");
            String details = line.substring(space);

            int posSSlash = details.indexOf('/');
            String allDetails = details.substring(posSSlash+1);
            int posASlash = allDetails.indexOf('/');
            description = allDetails.substring(0, posASlash-2);
            String moreDetails = allDetails.substring(posASlash+1);

            int posNSlash = moreDetails.indexOf('/');
            String amountString = moreDetails.substring(0, posNSlash-2);
            amount = Double.parseDouble(amountString);
            String borrowLendDetails = moreDetails.substring(posNSlash+1);

            int posDSlash = borrowLendDetails.indexOf('/');
            name = borrowLendDetails.substring(0, posDSlash-2);
            String dates = borrowLendDetails.substring(posDSlash+1);

            int posBSlash = dates.indexOf('/');
            String lentDateString = dates.substring(0, posBSlash-2);
            String deadlineString = dates.substring(posBSlash+1);
            lentDate = ParseDate.parseDate(lentDateString);
            deadline = ParseDate.parseDate(deadlineString);
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            return new InvalidCommand("Invalid");
        }
        switch (command) {
        case LendExpenditureCommand.COMMAND_WORD:
            return new LendExpenditureCommand(description, name, amount, lentDate, deadline);
        case BorrowExpenditureCommand.COMMAND_WORD:
            return new BorrowExpenditureCommand(description, name, amount, lentDate, deadline);
        default:
            return new InvalidCommand("Invalid");
        }
    }
}
