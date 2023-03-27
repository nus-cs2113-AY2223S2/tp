package seedu.commands;

import seedu.exceptions.WrongInputException;
import seedu.expenditure.ExpenditureList;
import seedu.expenditure.AcademicExpenditure;
import seedu.expenditure.AccommodationExpenditure;
import seedu.expenditure.BorrowExpenditure;
import seedu.expenditure.EntertainmentExpenditure;
import seedu.expenditure.FoodExpenditure;
import seedu.expenditure.LendExpenditure;
import seedu.expenditure.OtherExpenditure;
import seedu.expenditure.TransportExpenditure;
import seedu.expenditure.TuitionExpenditure;


public class ViewTypeExpenditureCommand extends Command {
    public static final String COMMAND_WORD = "viewtype";
    public final String expenditureType;

    public ViewTypeExpenditureCommand(String userInput) throws WrongInputException {
        switch (userInput) {
        case "academic":
            expenditureType = AcademicExpenditure.expenditureType;
            break;
        case "accommodation":
            expenditureType = AccommodationExpenditure.expenditureType;
            break;
        case "borrow":
            expenditureType = BorrowExpenditure.expenditureType;
            break;
        case "entertainment":
            expenditureType = EntertainmentExpenditure.expenditureType;
            break;
        case "food":
            expenditureType = FoodExpenditure.expenditureType;
            break;
        case "lend":
            expenditureType = LendExpenditure.expenditureType;
            break;
        case "other":
            expenditureType = OtherExpenditure.expenditureType;
            break;
        case "transport":
            expenditureType = TransportExpenditure.expenditureType;
            break;
        case "tuition":
            expenditureType = TuitionExpenditure.expenditureType;
            break;
        default:
            throw new WrongInputException();
        }
    }

    @Override
    public CommandResult execute(ExpenditureList expenditures) {
        return new CommandResult("Here are the specified expenditures: \n"
                + ExpenditureList.specificTypeString(expenditureType));
    }
}
