package chching.record;

import java.time.LocalDate;
import java.util.ArrayList;

import chching.ChChingException;
import chching.currency.Converter;
import chching.currency.Selector;
import chching.parser.DecimalsChecker;

import static chching.parser.Incomes.parseDate;

/**
 * Models a class that act as list of incomes. Inherited from RecordList Class
 */
public class IncomeList extends RecordList {
    protected ArrayList<Income> incomeList;

    /**
     * Constructor to instantiate IncomeList objects
     *
     * @param incomeList ArrayList of incomes
     */
    public IncomeList(ArrayList<Income> incomeList) {
        this.incomeList = incomeList;
    }

    /**
     * Default constructor to instantiate IncomeList objects
     */
    public IncomeList() {
        incomeList = new ArrayList<>();

    }

    public int size() {
        return incomeList.size();
    }

    public void addIncome(Income income) {
        incomeList.add(income);

    }

    /**
     * Method to edit an income in the income list.
     * Edits a specific field of an income based on the parameters field to value.
     *
     * @param index Index of income to be edited.
     * @param field Field to be edited.
     * @param value Updated value desired.
     * @throws ChChingException If value value is not a non-numeric input.
     */
    public void editIncome(int index, String field, String value) throws ChChingException {

        // change from 1-based indexing to 0-based indexing
        int indexZeroBased = index - 1;
        Income income = incomeList.get(indexZeroBased);

        // edit the according field
        switch (field) {
        case "de":
            income.setDescription(value);
            break;
        case "da":
            LocalDate date = parseDate(value);
            income.setDate(date);
            break;
        case "v":
            boolean isTwoDecimalsOrLess = DecimalsChecker.isTwoDecimals(value);
            if(!isTwoDecimalsOrLess) {
                throw new ChChingException("Income value must be a valid positive double that is 2 d.p. or less");
            }
            try {
                double amount = Double.parseDouble(value);
                if (amount < 0.01) {
                    throw new ChChingException("Income must be greater than or equals 0.01");
                } else if (amount > 999999.99) {
                    throw new ChChingException("Income value must be less than 1000000");
                }
                assert amount > 0.01 : "Income cannot be negative";
                income.setValue(amount);
            } catch (Exception e) {
                if (e instanceof NumberFormatException) {
                    throw new ChChingException("Income value must be a number");
                }
                throw new ChChingException(e.getMessage());
            }
            break;
        default:
            assert false : "No such field to enter here";
            throw new ChChingException("No such field in income");
        }
    }

    /**
     * Deletes income from an IncomeList
     *
     * @param i index of the income entry
     */
    public void deleteIncome(int i) throws IndexOutOfBoundsException {
        try {
            incomeList.remove(i - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException("There is no income with this index");
        }
    }

    public void printIncomeList(Selector selector, Converter converter) {
        for (int i = 1; i <= incomeList.size(); i++) {
            Record record = incomeList.get(i - 1);
            String convertedCurrencies = converter.printConverter(record.value, selector);
            System.out.println("    " + i + ". " + record.toString() + convertedCurrencies);
        }
    }

    public void clearIncomeList() {
        incomeList.clear();
    }

    @Override
    public Income get(int i) {
        return incomeList.get(i);
    }
}
