package seedu.moneymind;

import java.util.ArrayList;

import seedu.moneymind.category.Category;
import seedu.moneymind.event.Event;

import static seedu.moneymind.string.Strings.NEW_LINE;
import static seedu.moneymind.string.Strings.HORIZONTAL_LINE;
import static seedu.moneymind.string.Strings.EMPTY_STRING;
import static seedu.moneymind.UserDate.isApproaching;
import static seedu.moneymind.UserDate.numberDaysAway;

/**
 * A class to remind the user of nearing expenses.
 */
public class Reminder {

    /**
     * Checks if there are any nearing expenses.
     *
     * @param categories The list of categories.
     * @return A string containing the nearing expenses.
     */
    public static String checkCategoryReminder(ArrayList<Category> categories) {
        String reminder = EMPTY_STRING;
        for (Category category : categories) {
            reminder += checkEventReminder(category);
        }
        if (reminder.isBlank()) {
            return EMPTY_STRING;
        } else {
            return HORIZONTAL_LINE + NEW_LINE + "Approaching expenses:" + NEW_LINE + reminder;
        }
    }

    private static String checkEventReminder(Category category) {
        String reminder = EMPTY_STRING;
        for (Event event : category.getEvents()) {
            if (event.isOneTimeExpense() == false && isApproaching(event.getTime())) {
                reminder += category.getName() + " has an event: " + event.getDescription() 
                        + stringOfDaysAway(event);
            }
        }
        return reminder;
    }

    private static String stringOfDaysAway(Event event) {
        if (numberDaysAway(event.getTime()) > 1) {
            return " in " + numberDaysAway(event.getTime()) + " days" + NEW_LINE;
        } else if (numberDaysAway(event.getTime()) == 1) {
            return " in 1 day" + NEW_LINE;
        } else {
            return "today" + NEW_LINE;
        }
    }
    
}
