package seedu.Parser;

import seedu.commands.Command;
import seedu.commands.AcademicExpenditureCommand;
import seedu.commands.AccommodationExpenditureCommand;
import seedu.commands.EntertainmentExpenditureCommand;
import seedu.commands.FoodExpenditureCommand;
import seedu.commands.OtherExpenditureCommand;
import seedu.commands.TransportExpenditureCommand;
import seedu.commands.TuitionExpenditureCommand;
import seedu.commands.InvalidCommand;


import java.time.LocalDate;

public class ParseAdd {
    public static Command addItem(String line, String command) {
        // Format: category d/date, a/amount, s/description

        double amount;
        LocalDate date;
        String descriptionVal;

        try {
            int posDSlash = line.indexOf('/');
            String[] editDetailsList = SplitCommand.splitCommand(posDSlash, line);
            String editDetails = editDetailsList[1];
            System.out.println(editDetails);

            int posASlash = editDetails.indexOf('/');
            String[] dateList = SplitCommand.splitCommand(posASlash, editDetails);
            String dateVal = dateList[0];
            String editPriceAndDescription = dateList[1];
            System.out.println(dateVal);
            System.out.println(editPriceAndDescription);

            int posSSlash = editPriceAndDescription.indexOf('/');
            String[] amountDescriptionList = SplitCommand.splitCommand(posSSlash, editPriceAndDescription);
            String amountVal = amountDescriptionList[0];
            descriptionVal = amountDescriptionList[1];
            amount = Double.parseDouble(amountVal);
            date = ParseDate.parseDate(dateVal);
            System.out.println(amountVal);
            System.out.println(descriptionVal);
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
