package chching.command;

import chching.ChChingException;
import chching.Storage;
import chching.Ui;
import chching.currency.Converter;
import chching.currency.Selector;
import chching.record.ExpenseList;
import chching.record.IncomeList;
import chching.record.Target;
import chching.record.TargetStorage;

/**
 * Models a class that sets target. Inherited from Command class.
 */
public class AddTargetCommand extends Command{

    private final Target target;

    public AddTargetCommand(Target target) throws ChChingException {
        if(target == null) {
            throw new ChChingException("No fields found");
        } else if (target.getValue() == null) {
            throw new ChChingException("Missing target value");
        }
        this.target = target;
    }


    /**
     * Executes setting of target
     *
     * @param incomes       ArrayList of income.
     * @param expenses      ArrayList of income.
     * @param ui        User interface
     * @param storage       Storage of data
     * @param converter     Convert value
     * @param targetStorage store target
     */
    @Override
    public void execute(IncomeList incomes, ExpenseList expenses, Ui ui, Storage storage, Selector selector,
                        Converter converter, TargetStorage targetStorage) throws ChChingException {
        targetStorage.addTarget(target);
    }
}
