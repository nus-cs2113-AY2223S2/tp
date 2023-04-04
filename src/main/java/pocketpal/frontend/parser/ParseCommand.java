package pocketpal.frontend.parser;

import pocketpal.frontend.commands.Command;
import pocketpal.frontend.constants.MessageConstants;
import pocketpal.frontend.exceptions.InvalidArgumentsException;
import pocketpal.frontend.exceptions.InvalidCategoryException;
import pocketpal.frontend.exceptions.MissingArgumentsException;
import pocketpal.frontend.util.CategoryUtil;
import pocketpal.frontend.util.StringUtil;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class ParseCommand {
    public abstract Command parseArguments(String input) throws InvalidArgumentsException, InvalidCategoryException, MissingArgumentsException;

    public String extractDetail(String input, Pattern pattern) throws MissingArgumentsException {
        String detail = null;
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {
            System.out.println("grp 1: " + matcher.group(1));
            System.out.println("grp 2: " + matcher.group(2));
            System.out.println("grp 3: " + matcher.group(3));
            String option = matcher.group(ParserConstants.OPTION_GROUP);
            detail = matcher.group(ParserConstants.DETAIL_GROUP);
            if ((detail == null || detail.isEmpty()) && option != null) { //option specified with 0 arguments
                throw new MissingArgumentsException(MessageConstants.MESSAGE_MISSING_OPTION_ARG + option);
            }
        }
        if (detail != null) {
            detail = detail.trim();
        }
        return detail;
    }

    public void checkIdValidity(String id) throws InvalidArgumentsException {
        try {
            Integer.parseInt(id);
        } catch (NumberFormatException e) {
            throw new InvalidArgumentsException(MessageConstants.MESSAGE_INVALID_ID);
        }
    }

    public void checkDescriptionValidity(String description) throws InvalidArgumentsException {
        if (description == null) { //description not declared
            return;
        }
        if (description.contains(",")) {
            throw new InvalidArgumentsException(MessageConstants.MESSAGE_INVALID_DESCRIPTION);
        }
    }

    public void checkPriceValidity(String price) throws InvalidArgumentsException {
        if (price == null) { //price not declared
            return;
        }
        try {
            Double.parseDouble(price);
        } catch (NumberFormatException e) {
            throw new InvalidArgumentsException(MessageConstants.MESSAGE_INVALID_PRICE);
        }
    }

    public void checkDateValidity(String date) throws InvalidArgumentsException {

    }

    public void checkCategoryValidity(String category) throws InvalidCategoryException {
        if (category == null) { //option not declared
            return;
        }
        category = StringUtil.toTitleCase(category);
        CategoryUtil.convertStringToCategory(category);
    }

}
