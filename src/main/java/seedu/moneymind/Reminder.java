package seedu.moneymind;

import java.util.ArrayList;

import seedu.moneymind.category.Category;
import seedu.moneymind.event.Event;

import static seedu.moneymind.string.Strings.NEW_LINE;
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
        String reminder = "Approaching expenses:" + NEW_LINE;
        for (Category category : categories) {
            reminder += checkEventReminder(category);
        }
        return reminder; 
        // TODO: implement in startup sequence
        // TODO: update outdated time to next month
    }

    private static String checkEventReminder(Category category) {
        String reminder = "";
        for (Event event : category.getEvents()) {
            if (isApproaching(event.getTime())) {
                reminder += category.getName() + "has an event: " + event.getDescription() 
                        + " in " + numberDaysAway(event.getTime()) + " days" + NEW_LINE;
            }
        }
        return reminder;
    }
    
}
