package command;

import data.Expense;
import data.ExpenseList;

import java.util.ArrayList;

import static common.MessageList.MESSAGE_DIVIDER_LIST;

public class CommandList extends Command {
    public static final String COMMAND_NAME = "list";
    public static final String SYNTAX = "Here's your task in the list: ";
    protected ArrayList<Expense> expenseList;
    public CommandList() {
        super(COMMAND_NAME);
    }

    @Override
    public CommandRes execute() {
        return new CommandRes(MESSAGE_DIVIDER_LIST, ExpenseList.expenseList, ExpenseList.getAllMessage());
    }
}
