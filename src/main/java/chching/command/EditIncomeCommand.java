package chching.command;

import chching.ChChingException;
import chching.Storage;
import chching.Ui;
import chching.currency.Converter;
import chching.currency.Selector;
import chching.parser.Incomes;
import chching.record.ExpenseList;
import chching.record.Income;
import chching.record.IncomeList;
import chching.record.TargetStorage;

import java.util.HashMap;

/**
 * Models a class to handle the EditIncome command. Inherited from Command class.
 */
public class EditIncomeCommand extends Command {
    public static final String DESCRIPTION_FIELD = "de";
    public static final String DATE_FIELD = "da";
    public static final String VALUE_FIELD = "v";
    private int index;
    private HashMap<String, String> argumentsByField;
    private boolean hasDescription;
    private boolean hasDate;
    private boolean hasValue;

    public EditIncomeCommand(HashMap<String, String> argumentsByField) throws ChChingException {
        this.argumentsByField = argumentsByField;

        index = Incomes.getIndex(argumentsByField);
        hasDescription = argumentsByField.containsKey(DESCRIPTION_FIELD);
        hasDate = argumentsByField.containsKey(DATE_FIELD);
        hasValue = argumentsByField.containsKey(VALUE_FIELD);
    }

    /**
     * Executes edit of incomes.
     * Based on the fields the user wants to edit, the corresponding fields will be edited.
     *
     * @param incomes       ArrayList of income.
     * @param expenses      ArrayList of income.
     * @param ui            User interface.
     * @param storage       Storage of data.
     * @param converter     Convert value.
     * @param targetStorage store target.
     * @throws ChChingException if the index is invalid or if there is no field to edit.
     */
    @Override
    public void execute(IncomeList incomes, ExpenseList expenses, Ui ui, Storage storage, Selector selector,
                        Converter converter, TargetStorage targetStorage) throws ChChingException {

        if (index <= 0) {
            throw new ChChingException("Negative/Zero index");
        } else if (index > incomes.size()) {
            throw new ChChingException("The index is too big");
        }
        assert index > 0 : "Index must be a positive integer";

        if (!hasDescription && !hasDate && !hasValue) {
            throw new ChChingException("No fields to edit");
        }

        int indexZeroBased = index - 1;
        Income income = incomes.get(indexZeroBased);


        if (hasDescription) {
            String value = argumentsByField.get(DESCRIPTION_FIELD);
            incomes.editIncome(index, DESCRIPTION_FIELD, value);
        }
        if (hasDate) {
            String value = argumentsByField.get(DATE_FIELD);
            incomes.editIncome(index, DATE_FIELD, value);
        }
        if (hasValue) {
            String value = argumentsByField.get(VALUE_FIELD);
            incomes.editIncome(index, VALUE_FIELD, value);
        }

        boolean isExpense = false;
        ui.showEdited(index, income, isExpense);
    }
}
