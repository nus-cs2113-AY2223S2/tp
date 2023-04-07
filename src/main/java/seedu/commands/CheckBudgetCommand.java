package seedu.commands;

import java.time.LocalDate;
import java.time.Year;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import seedu.exceptions.EmptyStringException;
import seedu.expenditure.Expenditure;
import seedu.expenditure.ExpenditureList;
import seedu.parser.ParseIndividualValue;

public class CheckBudgetCommand extends Command {
    public static final String COMMAND_WORD = "check";
    public static final String BLANK = "";
    public static final String DSLASH = "d/";
    public static final String MSLASH = "m/";
    public static final String YSLASH = "y/";
    public static final String SLASH = "/";
    private final String userInput;

    public CheckBudgetCommand(String userInput) {
        this.userInput = userInput;
    }

    @Override
    public CommandResult execute(ExpenditureList expenditures) {
        try {
            String timePeriod = ParseIndividualValue.parseIndividualValue(userInput, BLANK, SLASH);
            double budget = expenditures.getBudgetSet();
            double totalAmount = 0;
            double borrowedAmount = 0;
            double lentAmount = 0;
            switch (timePeriod) {
            case "d":
                LocalDate dayVal = LocalDate
                        .parse(ParseIndividualValue.parseIndividualValue(userInput, DSLASH, BLANK));
                for (Expenditure individualExpenditure : expenditures.getExpenditures()) {
                    if (individualExpenditure.getDate().equals(dayVal)) {
                        if (individualExpenditure.getExpenditureType().equals("B")) {
                            borrowedAmount += individualExpenditure.getValue();
                        } else if (individualExpenditure.getExpenditureType().equals("L")) {
                            lentAmount += individualExpenditure.getValue();
                        } else if (!individualExpenditure.getPaidIcon().equals("[X]")) {
                            totalAmount += individualExpenditure.getValue();
                        }
                    }
                }
                return getCheckCommandResult(budget, totalAmount, borrowedAmount, lentAmount);
            case "m":
                DateTimeFormatter formatMonth = DateTimeFormatter.ofPattern("uuuu-MM");
                String monthVal = ParseIndividualValue.parseIndividualValue(userInput,
                        MSLASH, BLANK);
                YearMonth yearMonth = YearMonth.parse(monthVal, formatMonth);
                for (Expenditure individualExpenditure : expenditures.getExpenditures()) {
                    if (individualExpenditure.getDate().getYear() == yearMonth.getYear()
                            && individualExpenditure.getDate().getMonth() == yearMonth.getMonth()) {
                        if (individualExpenditure.getExpenditureType().equals("B")) {
                            borrowedAmount += individualExpenditure.getValue();
                        } else if (individualExpenditure.getExpenditureType().equals("L")) {
                            lentAmount += individualExpenditure.getValue();
                        } else if (!individualExpenditure.getPaidIcon().equals("[X]")) {
                            totalAmount += individualExpenditure.getValue();
                        }
                    }
                }
                return getCheckCommandResult(budget, totalAmount, borrowedAmount, lentAmount);
            case "y":
                DateTimeFormatter formatYear = DateTimeFormatter.ofPattern("uuuu");
                String yearVal = ParseIndividualValue.parseIndividualValue(userInput, YSLASH,
                        BLANK);
                Year year = Year.parse(yearVal, formatYear);
                for (Expenditure individualExpenditure : expenditures.getExpenditures()) {
                    if (individualExpenditure.getDate().getYear() == year.getValue()) {
                        if (individualExpenditure.getExpenditureType().equals("B")) {
                            borrowedAmount += individualExpenditure.getValue();
                        } else if (individualExpenditure.getExpenditureType().equals("L")) {
                            lentAmount += individualExpenditure.getValue();
                        } else if (!individualExpenditure.getPaidIcon().equals("[X]")) {
                            totalAmount += individualExpenditure.getValue();
                        }
                    }
                }
                return getCheckCommandResult(budget, totalAmount, borrowedAmount, lentAmount);
            case "c":
                for (Expenditure individualExpenditure : expenditures.getExpenditures()) {
                    if (individualExpenditure.getExpenditureType().equals("B")) {
                        borrowedAmount += individualExpenditure.getValue();
                    } else if (individualExpenditure.getExpenditureType().equals("L")) {
                        lentAmount += individualExpenditure.getValue();
                    } else if (!individualExpenditure.getPaidIcon().equals("[X]")) {
                        totalAmount += individualExpenditure.getValue();
                    }
                }
                return getCheckCommandResult(budget, totalAmount, borrowedAmount, lentAmount);
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
}
