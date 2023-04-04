package pocketpal.frontend.parser;

import pocketpal.frontend.commands.Command;
import pocketpal.frontend.commands.ViewCommand;
import pocketpal.frontend.exceptions.InvalidArgumentsException;
import pocketpal.frontend.exceptions.InvalidCategoryException;
import pocketpal.frontend.exceptions.MissingArgumentsException;

public class ParseViewCommand extends ParseCommand {
    String viewCount;
    String category;
    String startDate;
    String endDate;
    String startPrice;
    String endPrice;
    @Override
    public Command parseArguments(String input) throws MissingArgumentsException, InvalidArgumentsException, InvalidCategoryException {
        viewCount = extractDetail(input, ParserConstants.ID_PATTERN);
        category = extractDetail(input, ParserConstants.CATEGORY_PATTERN);
        startDate = extractDetail(input, ParserConstants.START_DATE_PATTERN);
        endDate = extractDetail(input, ParserConstants.END_DATE_PATTERN);
        startPrice = extractDetail(input, ParserConstants.START_PRICE_PATTERN);
        endPrice = extractDetail(input, ParserConstants.END_PRICE_PATTERN);
        checkIdValidity(viewCount);
        checkCategoryValidity(category);
        checkDateValidity(startDate);
        checkDateValidity(endDate);
        checkPriceValidity(startPrice);
        checkPriceValidity(endPrice);
        if (startPrice == null) {
            startPrice = ;
        }
        return new ViewCommand(viewCount, category, startPrice, endPrice, startDate, endDate);
    }
}
