package pocketpal.backend.endpoints;

import pocketpal.frontend.constants.MessageConstants;
import pocketpal.frontend.exceptions.InvalidArgumentsException;
import pocketpal.frontend.util.StringUtil;

import java.util.regex.Matcher;

import static pocketpal.backend.constants.MiscellaneousConstants.AMOUNT_MIN_DOUBLE;
import static pocketpal.backend.constants.MiscellaneousConstants.PATTERN_AMOUNT_2DP;

public class EndpointUtil {
    /**
     * Parses the input string and returns the corresponding positive integer.
     *
     * @param number The string to be parsed
     * @return Positive integer
     * @throws NumberFormatException If string is not an integer, or is non-positive
     */
    public static int getPositiveIntegerFromString(String number) throws NumberFormatException {
        assert number != null : "Unexpected null string";
        int result = Integer.parseInt(number);
        if (result <= 0) {
            throw new NumberFormatException();
        }
        return result;
    }

    /**
     * Parses the input string and returns the corresponding amount.
     * Checks if the string is positive, has at most 9 whole digits, and has up to 2 decimal points.
     *
     * @param amount The string to be parsed
     * @return Positive Double with at most 2 decimal points
     */
    public static double getAmountFromString(String amount) throws InvalidArgumentsException {
        assert amount != null : "Unexpected null string";
        try {
            double result = Double.parseDouble(amount);
            Matcher amountMatcher = PATTERN_AMOUNT_2DP.matcher(StringUtil.doubleToString(result));
            boolean isValidAmount = amountMatcher.find() && result >= AMOUNT_MIN_DOUBLE;
            if (!isValidAmount) {
                throw new NumberFormatException();
            }
            return result;
        } catch (NumberFormatException e) {
            throw new InvalidArgumentsException(MessageConstants.MESSAGE_INVALID_PRICE);
        }
    }
}
