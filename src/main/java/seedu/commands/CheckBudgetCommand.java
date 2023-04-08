package seedu.commands;

import java.time.LocalDate;
import java.time.Year;
// import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import seedu.exceptions.EmptyStringException;
import seedu.expenditure.BorrowExpenditure;
import seedu.expenditure.Expenditure;
import seedu.expenditure.ExpenditureList;
import seedu.expenditure.LendExpenditure;
import seedu.parser.ParseIndividualValue;

public class CheckBudgetCommand extends Command {
    public static final String COMMAND_WORD = "check";
    public static final String BLANK = "";
    public static final String DSLASH = "d/";
    public static final String YSLASH = "y/";
    public static final String SLASH = "/";
    public static final String TSLASH = "t/";
    private final String userInput;
    private double totalAmount = 0;
    private double borrowedAmount = 0;
    private double lentAmount = 0;

    public CheckBudgetCommand(String userInput) {
        this.userInput = userInput;
    }

    @Override
    public CommandResult execute(ExpenditureList expenditures) {
        try {
            String timePeriod = ParseIndividualValue.parseIndividualValue(userInput, BLANK, SLASH);
            double budget = expenditures.getBudgetSet();
            switch (timePeriod) {
            case "d":
                checkDay(expenditures);
                return getCheckCommandResult(budget, totalAmount, borrowedAmount, lentAmount);
            case "y":
                checkYear(expenditures);
                return getCheckCommandResult(budget, totalAmount, borrowedAmount, lentAmount);
            case "c":
                checkAll(expenditures);
                return getCheckCommandResult(budget, totalAmount, borrowedAmount, lentAmount);
            case "t":
                String category = ParseIndividualValue.parseIndividualValue(userInput, TSLASH, BLANK);
                return checkCategory(expenditures, category, budget);
            default:
                return new CommandResult("Failed to check! Please check the format and try again!");
            }
        } catch (DateTimeParseException s) {
            return new CommandResult("Failed to check! Please check the format and try again!");
        } catch (StringIndexOutOfBoundsException e) {
            return new CommandResult("Failed to check! Please check the format and try again!");
        } catch (EmptyStringException e) {
            return new CommandResult("Failed to check! Please check the format and try again!");
        }
    }

    private void checkAll(ExpenditureList expenditures) {
        for (Expenditure individualExpenditure : expenditures.getExpenditures()) {
            updateAmount(individualExpenditure);
        }
    }

    private CommandResult checkCategory(ExpenditureList expenditures, String category, double budget) {
        switch (category) {
        case "academic":
            checkRespectiveExpenditureType(expenditures, "Acad");
            return getCheckCommandResult(budget, totalAmount, borrowedAmount, lentAmount);
        case "accommodation":
            checkRespectiveExpenditureType(expenditures, "Accom");
            return getCheckCommandResult(budget, totalAmount, borrowedAmount, lentAmount);
        case "entertainment":
            checkRespectiveExpenditureType(expenditures, "En");
            return getCheckCommandResult(budget, totalAmount, borrowedAmount, lentAmount);
        case "food":
            checkRespectiveExpenditureType(expenditures, "F");
            return getCheckCommandResult(budget, totalAmount, borrowedAmount, lentAmount);
        case "other":
            checkRespectiveExpenditureType(expenditures, "O");
            return getCheckCommandResult(budget, totalAmount, borrowedAmount, lentAmount);
        case "transport":
            checkRespectiveExpenditureType(expenditures, "Tr");
            return getCheckCommandResult(budget, totalAmount, borrowedAmount, lentAmount);
        case "tuition":
            checkRespectiveExpenditureType(expenditures, "Tu");
            return getCheckCommandResult(budget, totalAmount, borrowedAmount, lentAmount);
        default:
            return new CommandResult("Category stated does not exist! Please check the format and try again!");
        }
    }

    private void checkRespectiveExpenditureType(ExpenditureList expenditures, String category) {
        for (Expenditure individualExpenditure : expenditures.getExpenditures()) {
            if (individualExpenditure.getExpenditureType().equals(category)) {
                updateAmount(individualExpenditure);
            }
        }
    }

    private void updateAmount(Expenditure individualExpenditure) {
        if (individualExpenditure.getExpenditureType().equals("B")) {

            borrowedAmount += individualExpenditure.getValue();
        } else if (individualExpenditure.getExpenditureType().equals("L")) {
            lentAmount += individualExpenditure.getValue();
        } else if (!individualExpenditure.getPaidIcon().equals("[X]")) {
            totalAmount += individualExpenditure.getValue();
        }
    }

    private static CommandResult getCheckCommandResult(double budget, double totalAmount,
            double borrowedAmount, double lentAmount) {
        if (budget == 0) {
            // Prevents the user from checking when budget is 0, as it does not provide any
            // insight
            return new CommandResult("Your current budget is set at 0, please use the 'set' command to set a budget.");
        } else if (budget >= totalAmount) {
            // Remaining budget available
            double difference = budget - totalAmount;
            return new CommandResult(String.format(
                    "You are $%.2f away from exceeding your budget of $%.2f." + "\n" +
                            "Your current spending stands at: $%.2f" + "\n" + "Sum of money borrowed: $%.2f"
                            + "\n" + "Sum of money lent: $%.2f",
                    difference, budget, totalAmount, borrowedAmount, lentAmount));
        } else {
            // Amount of budget exceeded by
            double difference = totalAmount - budget;
            return new CommandResult(String.format(
                    "You have exceeded your budget of $%.2f by $%.2f! " + "\n" +
                            "Your current spending stands at: $%.2f " + "\n" + "Sum of money borrowed: $%.2f" + "\n"
                            + "Sum of money lent: $%.2f",
                    budget, difference, totalAmount, borrowedAmount, lentAmount));
        }
    }

    /**
     * @author TzeLoong
     */
    private void checkYear(ExpenditureList expenditures) throws EmptyStringException {
        DateTimeFormatter formatYear = DateTimeFormatter.ofPattern("uuuu");
        String yearVal = ParseIndividualValue.parseIndividualValue(userInput, YSLASH,
                BLANK);
        Year year = Year.parse(yearVal, formatYear);
        for (Expenditure individualExpenditure : expenditures.getExpenditures()) {
            if (individualExpenditure instanceof BorrowExpenditure) {
                int startYear = ((BorrowExpenditure) individualExpenditure).getDate().getYear();
                int endYear = ((BorrowExpenditure) individualExpenditure).getDeadline().getYear();
                if (year.getValue() >= startYear && year.getValue() <= endYear) {
                    borrowedAmount += individualExpenditure.getValue();
                }
            } else if (individualExpenditure instanceof LendExpenditure) {
                int startTheYear = ((LendExpenditure) individualExpenditure).getDate().getYear();
                int endTheYear = ((LendExpenditure) individualExpenditure).getDeadline().getYear();
                if (year.getValue() >= startTheYear && year.getValue() <= endTheYear) {
                    lentAmount += individualExpenditure.getValue();
                }
            } else if (!individualExpenditure.getPaidIcon().equals("[X]")) {
                if (individualExpenditure.getDate().getYear() == year.getValue()) {
                    updateAmount(individualExpenditure);
                }
            }
        }
    }

    private void checkDay(ExpenditureList expenditures) throws EmptyStringException {
        LocalDate dayVal = LocalDate
                .parse(ParseIndividualValue.parseIndividualValue(userInput, DSLASH, BLANK));
        for (Expenditure individualExpenditure : expenditures.getExpenditures()) {
            if (individualExpenditure instanceof BorrowExpenditure) {
                LocalDate startDate = ((BorrowExpenditure) individualExpenditure).getDate();
                LocalDate endDate = ((BorrowExpenditure) individualExpenditure).getDeadline();
                if (!dayVal.isAfter(endDate) && !dayVal.isBefore(startDate)) {
                    borrowedAmount += individualExpenditure.getValue();
                }
            } else if (individualExpenditure instanceof LendExpenditure) {
                LocalDate startDate = ((LendExpenditure) individualExpenditure).getDate();
                LocalDate endDate = ((LendExpenditure) individualExpenditure).getDeadline();
                if (!dayVal.isAfter(endDate) && !dayVal.isBefore(startDate)) {
                    lentAmount += individualExpenditure.getValue();
                }
            } else if (!individualExpenditure.getPaidIcon().equals("[X]")) {
                if (individualExpenditure.getDate().equals(dayVal)) {
                    totalAmount += individualExpenditure.getValue();
                }
            }
        }
    }

}
