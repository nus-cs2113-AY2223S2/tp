package pocketpal.frontend.parser;

import pocketpal.frontend.commands.AddCommand;
import pocketpal.frontend.commands.Command;
import pocketpal.frontend.constants.MessageConstants;
import pocketpal.frontend.constants.ParserConstants;
import pocketpal.frontend.exceptions.*;
import pocketpal.frontend.util.CategoryUtil;
import pocketpal.frontend.util.StringUtil;

public class ParseAddCommand extends ParseCommand {
    String description;
    String price;
    String category;

    /**
     * Returns an AddCommand object to be executed by the backend. The AddCommand
     * contains the parameters of the expenses to be added to the expense list.
     *
     * @param input User input entered after add command.
     * @return Command AddCommand object to be executed.
     * @throws MissingArgumentsException If required arguments are missing.
     * @throws InvalidArgumentsException If required arguments are in wrong format.
     * @throws InvalidCategoryException  If category entered is invalid.
     * @throws UnknownOptionException    If an unknown option is used.
     */
    @Override
    public Command parseArguments(String input) throws InvalidArgumentsException, InvalidCategoryException,
            MissingArgumentsException, UnknownOptionException, UnknownArgumentException {
        checkUnknownOptionExistence(input.trim(), ParserConstants.ADD_OPTIONS);
        String argumentsBeforeOption = extractArgumentsBeforeOption(input, ParserConstants.ID_PATTERN);
        description = extractDetail(input, ParserConstants.DESCRIPTION_PATTERN);
        price = extractDetail(input, ParserConstants.PRICE_PATTERN);
        category = extractDetail(input, ParserConstants.CATEGORY_PATTERN);
        checkOptionsExistence(description, price, category);
        if (!argumentsBeforeOption.isEmpty()) {
            throw new UnknownArgumentException(MessageConstants.MESSAGE_UNKNOWN_ARGUMENTS + argumentsBeforeOption);
        }
        checkDescriptionValidity(description);
        checkPriceValidity(price);
        category = StringUtil.toTitleCase(category);
        return new AddCommand(description, Double.parseDouble(price), CategoryUtil.convertStringToCategory(category));
    }

    private void checkOptionsExistence(String description, String price, String category)
            throws MissingArgumentsException {
        String errorMessage = MessageConstants.MESSAGE_MISSING_REQUIRED_OPTION;
        if (description == null) {
            errorMessage += System.lineSeparator() + ParserConstants.DESCRIPTION_OPTION;
        }
        if (price == null) {
            errorMessage += System.lineSeparator() + ParserConstants.PRICE_OPTION;
        }
        if (category == null) {
            errorMessage += System.lineSeparator() + ParserConstants.CATEGORY_OPTION;
        }
        if (description == null || price == null || category == null) {
            throw new MissingArgumentsException(errorMessage);
        }
    }


}
