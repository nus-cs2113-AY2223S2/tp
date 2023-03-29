package pocketpal.frontend.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import pocketpal.frontend.exceptions.InvalidDateException;
import pocketpal.frontend.constants.MessageConstants;
import pocketpal.backend.constants.MiscellaneousConstants;

public class DateTimeUtil {
    public static LocalDateTime convertStringToLocalDateTime(String dateTimeString) throws InvalidDateException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(
                MiscellaneousConstants.DATETIME_PATTERN
            );
            LocalDateTime dateTime = LocalDateTime.parse(dateTimeString, formatter);
            return dateTime;
        } catch (Exception e) {
            throw new InvalidDateException(MessageConstants.MESSAGE_INVALID_DATE_READ);
        }
    }
}
