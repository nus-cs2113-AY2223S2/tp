package seedu.Parser;

import seedu.commands.*;

import java.time.LocalDate;

public class ParseAdd {
    public static Command addItem(String line, String command) {
        // Format: category d/date, a/amount, s/description

        double amount;
        LocalDate date;
        String descriptionVal;

        try {
            int space = line.indexOf(" ");
            String details = line.substring(space);
            int posDSlash = details.indexOf('/');
            String editDetails = details.substring(posDSlash+1);
            int posASlash = editDetails.indexOf('/');
            String dateVal = editDetails.substring(0, posASlash-2);
            String editPriceAndDescription = editDetails.substring(posASlash+1);

            int posSSlash = editPriceAndDescription.indexOf('/');
            String amountVal = editPriceAndDescription.substring(0, posSSlash-2);
            descriptionVal = editPriceAndDescription.substring(posSSlash+1);
            amount = Double.parseDouble(amountVal);
            date = ParseDate.parseDate(dateVal);
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            return new InvalidCommand("Invalid");
        }
        switch (command) {
        case AcademicExpenditureCommand.COMMAND_WORD:
            return new AcademicExpenditureCommand(descriptionVal, amount, date);
        case AccommodationExpenditureCommand.COMMAND_WORD:
            return new AccommodationExpenditureCommand(descriptionVal, amount, date);
        case EntertainmentExpenditureCommand.COMMAND_WORD:
            return new EntertainmentExpenditureCommand(descriptionVal, amount, date);
        case FoodExpenditureCommand.COMMAND_WORD:
            return new FoodExpenditureCommand(descriptionVal, amount, date);
        case OtherExpenditureCommand.COMMAND_WORD:
            return new OtherExpenditureCommand(descriptionVal, amount, date);
        case TransportExpenditureCommand.COMMAND_WORD:
            return new TransportExpenditureCommand(descriptionVal, amount, date);
        case TuitionExpenditureCommand.COMMAND_WORD:
            return new TuitionExpenditureCommand(descriptionVal, amount, date);
        default:
            return new InvalidCommand("Invalid");
        }
    }
}
