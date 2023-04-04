package pocketpal.frontend.parser;

import pocketpal.data.entry.Category;
import pocketpal.frontend.commands.AddCommand;
import pocketpal.frontend.commands.Command;
import pocketpal.frontend.exceptions.InvalidArgumentsException;
import pocketpal.frontend.exceptions.InvalidCategoryException;
import pocketpal.frontend.exceptions.MissingArgumentsException;
import pocketpal.frontend.util.CategoryUtil;
import pocketpal.frontend.util.StringUtil;
import pocketpal.frontend.util.UIUtil;

public class ParseAddCommand extends ParseCommand {
    String description;
    String price;
    String category;

    @Override
    public Command parseArguments(String input) throws InvalidArgumentsException, InvalidCategoryException, MissingArgumentsException {
        description = extractDetail(input, ParserConstants.DESCRIPTION_PATTERN);
        price = extractDetail(input, ParserConstants.PRICE_PATTERN);
        category = extractDetail(input, ParserConstants.CATEGORY_PATTERN);
        System.out.println("Description: " + description);
        System.out.println("Price: " + price);
        System.out.println("Category: " + category);
        checkOptionsExistence(description, price, category); //check if required options are specified
        checkDescriptionValidity(description);
        checkPriceValidity(price);
        category = StringUtil.toTitleCase(category);
        return new AddCommand(description, Double.parseDouble(price), CategoryUtil.convertStringToCategory(category));
    }

    private void checkOptionsExistence(String description, String price, String category) throws MissingArgumentsException {
        String errorMessage = "Missing required options:";
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
