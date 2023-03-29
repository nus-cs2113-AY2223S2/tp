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
            expenditureType = AcademicExpenditure.EXPENDITURE_TYPE;
            break;
        case "accommodation":
            expenditureType = AccommodationExpenditure.EXPENDITURE_TYPE;
            break;
        case "borrow":
            expenditureType = BorrowExpenditure.EXPENDITURE_TYPE;
            break;
        case "entertainment":
            expenditureType = EntertainmentExpenditure.EXPENDITURE_TYPE;
            break;
        case "food":
            expenditureType = FoodExpenditure.EXPENDITURE_TYPE;
            break;
        case "lend":
            expenditureType = LendExpenditure.EXPENDITURE_TYPE;
            break;
        case "other":
            expenditureType = OtherExpenditure.EXPENDITURE_TYPE;
            break;
        case "transport":
            expenditureType = TransportExpenditure.EXPENDITURE_TYPE;
            break;
        case "tuition":
            expenditureType = TuitionExpenditure.EXPENDITURE_TYPE;
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
