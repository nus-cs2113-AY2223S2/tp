package pocketpal.frontend.parser;

import pocketpal.frontend.commands.Command;
import pocketpal.frontend.commands.EditCommand;
import pocketpal.frontend.constants.MessageConstants;
import pocketpal.frontend.exceptions.InvalidArgumentsException;
import pocketpal.frontend.exceptions.InvalidCategoryException;
import pocketpal.frontend.exceptions.MissingArgumentsException;
import pocketpal.frontend.util.StringUtil;

public class ParseEditCommand extends ParseCommand {
    String expenseId;
    String description;
    String price;
    String category;

    @Override
    public Command parseArguments(String input) throws InvalidArgumentsException, InvalidCategoryException, MissingArgumentsException {
        expenseId = extractId(input, ParserConstants.ID_PATTERN);
        description = extractDetail(input, ParserConstants.DESCRIPTION_PATTERN);
        price = extractDetail(input, ParserConstants.PRICE_PATTERN);
        category = extractDetail(input, ParserConstants.CATEGORY_PATTERN);
        System.out.println("Expense ID: " + expenseId);
        checkIdValidity(expenseId);
        checkDescriptionValidity(description);
        checkPriceValidity(price);
        checkCategoryValidity(category);
        if (expenseId == null) {
            throw new MissingArgumentsException(MessageConstants.MESSAGE_INVALID_ID);
        }
        if (description == null && price == null && category == null) {
            throw new MissingArgumentsException(MessageConstants.MESSAGE_MISSING_OPTION);
        }
        return new EditCommand(expenseId, description, category, price);
    }

}
