package seedu.duke.command;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import seedu.duke.exception.BBException;
import seedu.duke.exception.CommandActionInvalidException;
import seedu.duke.exception.CommandInvalidException;
import seedu.duke.exception.CommandParamInvalidException;
import seedu.duke.exception.CommandParamSlashException;
import seedu.duke.exception.CommandParamTypeInvalidException;
import seedu.duke.util.Constants;
import seedu.duke.util.Pair;

//@@author pinyoko573
/**
 * All in one command parser that will create
 * the command class based on the command, action,
 * required and optional parameters from the user's input.
 * This command will then be used to execute in Main().
 */
public class CommandParser {

    /**
     * Parses the command based on the user's input.
     * Input must have the command name, action name,
     * required and optional parameters (if any). List of
     * action names and their required and optional parameters
     * are specified in the sub-class of Command.
     * 
     * @param input String input by the user
     * @return Command that will be used for execution in main
     * @throws BBException for unknown and invalid parsing errors
     */
    public static Command parse(String input) throws BBException {
        CommandEnum commandName = getCommandName(input);
        assert commandName != null : "CommandName must be from the enum";

        // Assign the command class based on the command input by the user.
        Command command = getCommand(commandName);

        // For commands that do not have any action
        if (command.getActions().length == 0) {
            return command;
        }

        // Check if action (given in input) exists and set the action in command for execution
        String action = getAction(command, input);
        command.setAction(action);

        // Check if all parameters are valid based on action and set the parameters in command for execution
        String[] requiredParams = getRequiredParams(command, action, input);
        String[] optionalParams = getOptionalParams(command, action, input);
        command.setParams(requiredParams, optionalParams);
        
        return command;
    }

    /**
     * Gets the command name from the user's input and
     * returns if command is valid.
     * 
     * @param input String input by the user
     * @return command that is chosen by the user
     * @throws CommandInvalidException if the command given is not valid
     */
    private static CommandEnum getCommandName(String input) throws CommandInvalidException {
        try {
            String[] splitInput = input.split(" ", 2);
            CommandEnum commandName = CommandEnum.valueOf(splitInput[0].toUpperCase());
            return commandName;
        } catch (IllegalArgumentException err) {
            throw new CommandInvalidException();
        }
    }

    /**
     * Gets the command object from CommandEnum.
     * 
     * @param commandName CommandEnum from getCommandName
     * @return command object from the valid CommandEnum given
     * @throws CommandInvalidException if the CommandEnum given is not valid
     */
    private static Command getCommand(CommandEnum commandName) throws CommandInvalidException  {
        Command command;
        switch (commandName) {
        case BUDGET:
            command = new BudgetCommand();
            break;
        case DEPOSIT:
            command = new DepositCommand();
            break;
        case EXPENSE:
            command = new ExpenseCommand();
            break;
        case STATS:
            command = new StatsCommand();
            break;
        case EXIT:
            command = new ExitCommand();
            break;
        case HELP:
            command = new HelpCommand();
            break;
        default:
            throw new CommandInvalidException();
        }

        return command;
    }

    /**
     * Gets the action name from the user's input and
     * returns if the action exists in the command.
     * 
     * @param command command that is chosen by the user
     * @param input String input by the user
     * @return action name that exists in the command
     * @throws CommandActionInvalidException if the action given is not valid
     */
    private static String getAction(Command command, String input) throws CommandActionInvalidException {
        String[] splitInput = input.split(" ", 3);
        try {
            String actionName = splitInput[1].toLowerCase();
            int actionNo = command.getActionNo(actionName);

            if (actionNo != -1) {
                return actionName;
            }

            throw new CommandActionInvalidException(command);
        } catch (ArrayIndexOutOfBoundsException err) {
            throw new CommandActionInvalidException(command);
        }
    }

    /**
     * Check that all the required parameters are provided
     * by the user's input. These parameters are based on
     * the action of the command.
     * 
     * @param command command that is chosen by the user
     * @param actionName name of the action chosen by the user
     * @param input String input by the user
     * @return array of parameters that can run the action
     * @throws BBException if there are missing parameters, or invalid parameter type
     */
    private static String[] getRequiredParams(Command command, String actionName, String input) throws BBException {
        int actionNo = command.getActionNo(actionName);
        Pair[] requiredParams =  command.requiredParamsList[actionNo];
        assert requiredParams != null : "List of required params retrieved must not be null (can be 0 elements)";

        String[] params = new String[requiredParams.length];

        try {
            int paramCount = 0;
            for (Pair param : requiredParams) {
                String paramName = param.getKey();
                Class<?> paramType = param.getValue();

                // Gets parameter value from the parameter syntax, throw if value contains /
                String paramValue = input.split(" " + paramName + " ")[1].split(" /")[0].strip();
                if (paramValue.contains("/")) {
                    throw new CommandParamSlashException();
                }

                // Check if the parameter value suits for the class type (e.g. int, string)
                validateParamType(paramName, paramValue, paramType);

                assert !paramValue.isEmpty() : "Value of a parameter must contain something";

                params[paramCount] = paramValue;
                paramCount++;
            }
        } catch (ArrayIndexOutOfBoundsException err) {
            throw new CommandParamInvalidException(command);
        } catch (CommandParamTypeInvalidException err) {
            // Initalize action message to get invalid action message and throw
            CommandParamInvalidException exc = new CommandParamInvalidException(command);
            err.setActionMessage(exc.getMessage());
            throw err;
        }

        return params;
    }

    /**
     * Check if any the optional parameters are provided
     * by the user's input. These parameters are based on
     * the action of the command.
     * 
     * @param command command that is chosen by the user
     * @param actionName name of the action chosen by the user
     * @param input String input by the user
     * @return array of parameters that can run the action
     * @throws BBException if there are missing parameters, or invalid parameter type
     */
    private static String[] getOptionalParams(Command command, String actionName, String input) throws BBException {
        int actionNo = command.getActionNo(actionName);
        Pair[] optionalParams =  command.optionalParamsList[actionNo];
        assert optionalParams != null : "List of optional params retrieved must not be null (can be 0 elements)";
        String[] params = new String[optionalParams.length];

        try {
            int paramCount = 0;
            for (Pair param : optionalParams) {
                String paramName = param.getKey();
                Class<?> paramType = param.getValue();

                // Gets parameter value from the parameter syntax
                // If the parameter slash is not found, go to next loop
                String[] splitParam = input.split(" " + paramName + " ");
                if (splitParam.length == 1) {
                    paramCount++;
                    continue;
                }

                // Throw if param value contains /
                String paramValue = splitParam[1].split(" /")[0].strip();
                if (paramValue.contains("/")) {
                    throw new CommandParamSlashException();
                }

                // Check if the parameter value suits for the class type (e.g. int, string)
                validateParamType(paramName, paramValue, paramType);

                assert !paramValue.isEmpty() : "Value of a parameter must contain something";

                params[paramCount] = paramValue;
                paramCount++;
            }
        } catch (ArrayIndexOutOfBoundsException err) {
            throw new CommandParamInvalidException(command);
        } catch (CommandParamTypeInvalidException err) {
            // Initalize action message to get invalid action message and throw
            CommandParamInvalidException exc = new CommandParamInvalidException(command);
            err.setActionMessage(exc.getMessage());
            throw err;
        }

        return params;
    }

    /**
     * Check if the parameter input given by user is valid
     * based on the data type of parameter.
     * 
     * @param paramValue value of the parameter provided by user
     * @param paramType Data type of the parameter
     * @throws BBException if invalid parameter type
     */
    private static void validateParamType(String paramName, String paramValue, Class<?> paramType) throws BBException {
        try {
            if (paramType.isAssignableFrom(int.class)) {
                Integer.parseInt(paramValue);
            } else if (paramType.isAssignableFrom(double.class)) {
                validateParamDouble(paramValue);
            } else if (paramType.isAssignableFrom(LocalDate.class)) {
                validateParamDate(paramValue);
            } else if (paramType.isAssignableFrom(String.class)) {
                validateParamString(paramValue);
            }
        } catch (NumberFormatException | DateTimeParseException | CommandParamTypeInvalidException err) {
            throw new CommandParamTypeInvalidException(paramName, paramType);
        }
    }

    /**
     * Check if the input value given can be parsed
     * to double.
     * 
     * @param paramValue value of the parameter provided by user
     * @throws NumberFormatException when double is not positive/contain alphabet/above 3dp
     */
    private static void validateParamDouble(String paramValue) throws NumberFormatException {
        if (paramValue.matches("[a-zA-Z]")) {
            throw new NumberFormatException();
        }

        double value = Double.parseDouble(paramValue);

        // Solution below adapted from https://stackoverflow.com/questions/32531910/
        // Check if input only contains at most 2 dec points and is positive
        if (BigDecimal.valueOf(value).scale() > 2 || value <= 0) {
            throw new NumberFormatException();
        }
    }

    /**
     * Check if the input value given can be parsed
     * to date.
     * 
     * @param paramValue value of the parameter provided by user
     * @throws CommandParamTypeInvalidException when date is not in 4 digits
     * @throws DateTimeParseException when date format is invalid
     */
    private static void validateParamDate(String paramValue) throws CommandParamTypeInvalidException,
            DateTimeParseException {

        LocalDate date = LocalDate.parse(paramValue, Constants.ACCEPTABLE_DATE_FORMAT);

        // Throw if year is not in 4 digits
        int yearDigits = String.valueOf(date.getYear()).length();
        if (yearDigits != 4) {
            throw new CommandParamTypeInvalidException();
        }
    }

    /**
     * Check if the input value given can be parsed
     * to string.
     * 
     * @param paramValue value of the parameter provided by user
     * @throws CommandParamTypeInvalidException when string is empty/above 31 chars
     */
    private static void validateParamString(String paramValue) throws CommandParamTypeInvalidException {
        // Check if input contains less than 30 characters
        if (paramValue.length() > Constants.STRING_MAX_LENGTH || paramValue.isEmpty()) {
            throw new CommandParamTypeInvalidException();
        }
    }
}
