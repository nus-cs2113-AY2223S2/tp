package pocketpal.frontend.parser;

import pocketpal.frontend.commands.Command;
import pocketpal.frontend.commands.DeleteCommand;
import pocketpal.frontend.exceptions.InvalidArgumentsException;


public class ParseDeleteCommand extends ParseCommand {
    Integer[] expenseIds;

    @Override
    public Command parseArguments(String input) throws InvalidArgumentsException {
        String[] argumentsArray = input.split(" ");
        for (int i = 0; i < argumentsArray.length; i++) {
            String expenseId = argumentsArray[i];
            checkIdValidity(expenseId);
            expenseIds[i] = Integer.parseInt(expenseId);
        }
        expenseIds = new Integer[argumentsArray.length];
        return new DeleteCommand(expenseIds);
    }
}
