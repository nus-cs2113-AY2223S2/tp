package pocketpal.frontend.parser;

import pocketpal.frontend.commands.Command;
import pocketpal.frontend.commands.DeleteCommand;
import pocketpal.frontend.constants.MessageConstants;
import pocketpal.frontend.exceptions.InvalidArgumentsException;
import pocketpal.frontend.exceptions.MissingArgumentsException;
import pocketpal.frontend.exceptions.UnknownOptionException;

import java.util.logging.Logger;


public class ParseDeleteCommand extends ParseCommand {
    Integer[] expenseIds;
    private Logger logger = Logger.getLogger(ParseDeleteCommand.class.getName());

    /**
     * Returns an DeleteCommand object to be executed by the backend. The
     * DeleteCommand takes in an integer index of the expense to be deleted.
     *
     * @param input User input after the delete command.
     * @return Command DeleteCommand object to be executed.
     * @throws InvalidArgumentsException If entered expense ID does not exist.
     * @throws MissingArgumentsException If required expense ID is not entered.
     * @throws UnknownOptionException    If an unknown option is used.
     */
    @Override
    public Command parseArguments(String input) throws InvalidArgumentsException, MissingArgumentsException,
            UnknownOptionException {
        logger.entering(ParseDeleteCommand.class.getName(), "parseArguments()");
        checkUnknownOptionExistence(input.trim(), "");
        if (input.isEmpty()) {
            throw new MissingArgumentsException(MessageConstants.MESSAGE_MISSING_ID_DELETE);
        }
        String[] argumentsArray = input.trim().split(" ");
        expenseIds = new Integer[argumentsArray.length];
        for (int i = 0; i < argumentsArray.length; i++) {
            String expenseId = argumentsArray[i];
            checkIdValidity(expenseId);
            expenseIds[i] = Integer.parseInt(expenseId);
        }
        logger.exiting(ParseDeleteCommand.class.getName(), "parseArguments()");
        return new DeleteCommand(expenseIds);
    }
}
