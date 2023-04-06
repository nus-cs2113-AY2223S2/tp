package pocketpal.frontend.parser;

import pocketpal.frontend.commands.Command;
import pocketpal.frontend.commands.EditCommand;
import pocketpal.frontend.constants.MessageConstants;
import pocketpal.frontend.constants.ParserConstants;
import pocketpal.frontend.exceptions.InvalidArgumentsException;
import pocketpal.frontend.exceptions.InvalidCategoryException;
import pocketpal.frontend.exceptions.MissingArgumentsException;
import pocketpal.frontend.exceptions.UnknownOptionException;


public class ParseEditCommand extends ParseCommand {
    String expenseId;
    String description;
    String price;
    String category;

    /**
     * Returns an EditCommand object to be executed by the backend. The object
     * contains the new data to be updated for the specified expense. If the new
     * data is in incorrect format, error is raised to the user.
     *
     * @param input User input after edit command.
     * @return Command EditCommand object containing the new parameters to be updated.
     * @throws MissingArgumentsException If required arguments are missing.
     * @throws InvalidArgumentsException If entered arguments are in incorrect format.
     * @throws UnknownOptionException    If an unknown option is used.
     * @throws InvalidCategoryException  If an unsupported category is used.
     */
    @Override
    public Command parseArguments(String input) throws InvalidArgumentsException, InvalidCategoryException,
            MissingArgumentsException, UnknownOptionException {
        checkUnknownOptionExistence(input.trim(), ParserConstants.EDIT_OPTIONS);
        expenseId = extractArgumentsBeforeOption(input, ParserConstants.ID_PATTERN);
        description = extractDetail(input, ParserConstants.DESCRIPTION_PATTERN);
        price = extractDetail(input, ParserConstants.PRICE_PATTERN);
        category = extractDetail(input, ParserConstants.CATEGORY_PATTERN);
        checkIdValidity(expenseId);
        checkDescriptionValidity(description);
        checkPriceValidity(price);
        checkCategoryValidity(category);
        if (expenseId == null || expenseId.isEmpty()) {
            throw new MissingArgumentsException(MessageConstants.MESSAGE_MISSING_ID_EDIT);
        }
        if (description == null && price == null && category == null) {
            throw new MissingArgumentsException(MessageConstants.MESSAGE_MISSING_OPTION_EDIT);
        }
        return new EditCommand(expenseId, description, category, price);
    }

}
