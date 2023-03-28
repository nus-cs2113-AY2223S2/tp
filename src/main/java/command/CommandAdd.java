package command;

import data.Currency;
import data.Expense;
import data.ExpenseList;
import data.Time;
import org.threeten.extra.Temporals;
import parser.ParserAdd;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


import static common.MessageList.SUCCESSFUL_ADD;

public class CommandAdd extends Command {
    public static final String COMMAND_NAME = "add";
    protected ArrayList<Expense> expenseList;
    protected Currency currency;
    protected String[] parsedInput;
    protected DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    /**
     * Instantiates and references the expense list for the entry to be added to as well as the parsed input from the
     * parser.
     *
     * @param expenseList The expense list to add the entry to.
     * @param parsedInput The parsed input from the parser.
     */
    public CommandAdd(ArrayList<Expense> expenseList, String[] parsedInput, Currency currency) {
        super(COMMAND_NAME);
        this.currency = currency;
        this.expenseList = expenseList;
        this.parsedInput = parsedInput;
    }
    /**
     * Adds an entry into the ArrayList based on the parsed input provided. Currently, if the currency specified does
     * not exist, it is defaulted to SGD.
     */
    @Override
    public CommandRes execute() {
        try {
            if(LocalDate.parse(parsedInput[ParserAdd.TIME_INDEX], formatter).isAfter(LocalDate.now())) {
                throw new Exception();
            }else {
                Time date = new Time(LocalDate.parse(parsedInput[ParserAdd.TIME_INDEX], formatter));
                String exchangeRateDate = LocalDate.parse(parsedInput[ParserAdd.TIME_INDEX], formatter)
                        .with(Temporals.previousWorkingDay()).toString();
                Expense addedExpense = new Expense(currency.roundInput((parsedInput[ParserAdd.AMOUNT_INDEX])),
                        date, parsedInput[ParserAdd.CATEGORY_INDEX],
                        Currency.convertCurrency(parsedInput[ParserAdd.CURRENCY_INDEX]),
                        Currency.getExchangeRate(LocalDate.parse(exchangeRateDate),
                                currency.convertCurrency(parsedInput[ParserAdd.CURRENCY_INDEX])));
                expenseList.add(addedExpense);
                return new CommandRes(SUCCESSFUL_ADD, addedExpense,
                        ExpenseList.getAllMessage(expenseList));
            }
        } catch (NumberFormatException e) {
            System.out.println("Please input a valid amount.");
        } catch (NullPointerException e) {
            System.out.println("Please input both the amount and date with amt/ and t/ respectively.");
        } catch (DateTimeException e) {
            System.out.println("Invalid date. Please input the date in dd-MM-yyyy format.");
        } catch (Exception e) {
            System.out.println("Invalid date. Please input a date before today's date.\nToday's date is: " +
                    LocalDate.now());
        }
        return null;
    }



}
