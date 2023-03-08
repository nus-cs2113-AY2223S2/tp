package Command;

import MajorClasses.ExpenseList;

import static data.MessageList.MESSAGE_DIVIDER_LIST;

public class CommandList extends Command {
    public CommandList() {
        super(COMMAND_NAME);
    }

    public static final String COMMAND_NAME = "list";
    public static final String SYNTAX = "Here's your task in the list: ";

    @Override
    public CommandRes execute() {
        return new CommandRes(MESSAGE_DIVIDER_LIST, ExpenseList.expenseList, ExpenseList.getAllMessage());
    }
}
