package pocketpal.frontend.util;

import pocketpal.frontend.constants.MessageConstants;
import pocketpal.frontend.constants.UIConstants;

import java.text.DecimalFormat;

public class UIUtil {
    /**
     * Converts double into string, adds decimal places if required.
     *
     * @param priceDouble price
     * @return String of price with two decimal places
     */
    public static String formatPrice(double priceDouble) {
        return new DecimalFormat(UIConstants.FORMAT_2DP)
                .format(priceDouble);
    }

    /**
     * Converts entry into readable format.
     *
     * @param description Description of entry
     * @param price       Price of entry
     * @param category    Category of entry
     * @return Formatted entry string
     */
    public static String formatEntry(String description, double price, String category, String dateTime) {
        return MessageConstants.DESCRIPTION +
                description +
                MessageConstants.NEWLINE +
                MessageConstants.PRICE +
                formatPrice(price) +
                MessageConstants.NEWLINE +
                MessageConstants.CATEGORY +
                category +
                MessageConstants.NEWLINE +
                dateTime +
                MessageConstants.NEWLINE ;
    }
}
