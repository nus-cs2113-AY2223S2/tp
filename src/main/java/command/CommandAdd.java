package command;

import data.Currency;
import data.Expense;
import data.ExpenseList;
import data.Time;
import parser.ParserAdd;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.math.BigDecimal;
import java.math.RoundingMode;

import static common.MessageList.SUCCESSFUL_ADD;

public class CommandAdd extends Command {
    public static final String COMMAND_NAME = "add";
    protected ArrayList<Expense> expenseList;
    protected String[] parsedInput;
    protected DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    /**
     * Instantiates and references the expense list for the entry to be added to as well as the parsed input from the
     * parser.
     *
     * @param expenseList The expense list to add the entry to.
     * @param parsedInput The parsed input from the parser.
     */
    public CommandAdd(ArrayList<Expense> expenseList, String[] parsedInput) {
        super(COMMAND_NAME);
        this.expenseList = expenseList;
        this.parsedInput = parsedInput;
    }

    /**
     * Adds an entry into the ArrayList based on the parsed input provided.
     */
    @Override
    public CommandRes execute() {
        try {
            Time date = new Time(LocalDate.parse(parsedInput[ParserAdd.TIME_INDEX], formatter));
            Expense addedExpense = new Expense(roundInput((parsedInput[ParserAdd.AMOUNT_INDEX])),
                    date, parsedInput[ParserAdd.CATEGORY_INDEX],
                    Currency.checkCurrency(parsedInput[ParserAdd.CURRENCY_INDEX]));
            expenseList.add(addedExpense);
            return new CommandRes(SUCCESSFUL_ADD, addedExpense,
                    ExpenseList.getAllMessage(expenseList));
        } catch (NumberFormatException e) {
            System.out.println("Please input a valid amount.");
        } catch (NullPointerException e) {
            System.out.println("Please input both the amount and date with amt/ and t/ respectively.");
        } catch (DateTimeException e) {
            System.out.println("Invalid date. Please input the date in dd-MM-yyyy format.");
        }
        assert false;
        return null;
    }

    /**
     * Standardize the format of double when we add it to expenseList
     */
    public BigDecimal roundInput(String expenseAmountInput) {
        BigDecimal roundedExpense = new BigDecimal(expenseAmountInput);
        roundedExpense = roundedExpense.setScale(2, RoundingMode.HALF_UP);
        return roundedExpense;
    }

}
