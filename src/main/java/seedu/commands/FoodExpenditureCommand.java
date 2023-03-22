package seedu.commands;

import seedu.expenditure.ExpenditureList;
import seedu.expenditure.FoodExpenditure;

import java.time.LocalDate;

public class FoodExpenditureCommand extends Command {
    public static final String COMMAND_WORD = "food";
    private final String foodExpenditureDescription;
    private final double foodExpenditureValue;
    private final LocalDate foodExpenditureDate;

    public FoodExpenditureCommand(String description, double value, LocalDate date) {
        this.foodExpenditureDescription = description;
        this.foodExpenditureValue = value;
        this.foodExpenditureDate = date;
    }

    public CommandResult execute(ExpenditureList expenditures) {
        FoodExpenditure foodExpenditure = new FoodExpenditure(
                foodExpenditureDescription,
                foodExpenditureValue,
                foodExpenditureDate);
        expenditures.addExpenditure(foodExpenditure);
        return new CommandResult(String.format("Added %s expenditure: %s",
                COMMAND_WORD, foodExpenditure.toString()));
    }
}
