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
            String allDetailsList[] = SplitCommand.splitCommand(posSSlash, details);
            String allDetails = allDetailsList[1];

            int posASlash = allDetails.indexOf('/');
            String moreDetailsList[] = SplitCommand.splitCommand(posASlash, allDetails);
            description = moreDetailsList[0];
            String moreDetails = moreDetailsList[1];

            int posNSlash = moreDetails.indexOf('/');
            String amountStringList[] = SplitCommand.splitCommand(posNSlash, moreDetails);
            String amountString = amountStringList[0];
            amount = Double.parseDouble(amountString);
            String borrowLendDetails = amountStringList[1];

            int posDSlash = borrowLendDetails.indexOf('/');
            String nameList[] = SplitCommand.splitCommand(posDSlash, borrowLendDetails);
            name = nameList[0];
            String dates = nameList[1];

            int posBSlash = dates.indexOf('/');
            String datesList[] = SplitCommand.splitCommand(posBSlash,dates);
            String lentDateString = datesList[0];
            String deadlineString = datesList[1];
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
