package pocketpal.frontend.parser;

import pocketpal.frontend.commands.Command;
import pocketpal.frontend.commands.EditCommand;
import pocketpal.frontend.constants.MessageConstants;
import pocketpal.frontend.constants.ParserConstants;
import pocketpal.frontend.exceptions.InvalidArgumentsException;
import pocketpal.frontend.exceptions.InvalidCategoryException;
import pocketpal.frontend.exceptions.MissingArgumentsException;
import pocketpal.frontend.exceptions.UnknownOptionException;

import java.util.logging.Logger;


public class ParseEditCommand extends ParseCommand {
    String entryId;
    String description;
    String price;
    String category;
    private Logger logger = Logger.getLogger(ParseEditCommand.class.getName());


    /**
     * Returns an EditCommand object to be executed by the backend. The object
     * contains the new data to be updated for the specified entry. If the new
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
        logger.entering(ParseEditCommand.class.getName(), "parseArguments()");
        checkUnknownOptionExistence(input.trim(), ParserConstants.EDIT_OPTIONS);
        entryId = extractArgumentsBeforeOption(input, ParserConstants.ID_PATTERN);
        description = extractDetail(input, ParserConstants.DESCRIPTION_PATTERN);
        price = extractDetail(input, ParserConstants.PRICE_PATTERN);
        category = extractDetail(input, ParserConstants.CATEGORY_PATTERN);
        checkIdValidity(entryId);
        checkDescriptionValidity(description);
        checkPriceValidity(price);
        checkCategoryValidity(category);
        if (entryId == null || entryId.isEmpty()) {
            throw new MissingArgumentsException(MessageConstants.MESSAGE_MISSING_ID_EDIT);
        }
        if (description == null && price == null && category == null) {
            throw new MissingArgumentsException(MessageConstants.MESSAGE_MISSING_OPTION_EDIT);
        }
        logger.exiting(ParseEditCommand.class.getName(), "parseArguments()");
        return new EditCommand(entryId, description, category, price);
    }

}
