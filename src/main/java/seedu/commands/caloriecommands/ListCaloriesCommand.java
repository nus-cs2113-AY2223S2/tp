package seedu.commands.caloriecommands;

import seedu.calorietracker.Calories;
import seedu.commands.Command;
import seedu.parser.DateFormatter;
import seedu.ui.Ui;

import java.util.Date;
import java.util.HashMap;

public class ListCaloriesCommand extends Command {
    private static final String EMPTY_DAY_LIST_MESSAGE = "No days have been found in the list";
    private static final String CALORIES_LIST_HEADER =
            "Here is the list of dates of your calorie intake:";
    @Override
    public String execute() {
        HashMap<Date, Calories> calorieMap1 = caloriesRecorder.getCalorieMap();
        if (calorieMap1 != null && !calorieMap1.isEmpty()) {
            StringBuilder string = new StringBuilder();
            string.append(CALORIES_LIST_HEADER).append(System.lineSeparator());
            for (Date date : calorieMap1.keySet()) {
                String formattedDate = DateFormatter.dateToString(date);
                string.append(formattedDate)
                        .append("Total Calories = ")
                        .append(caloriesRecorder.getCalories())
                        .append(System.lineSeparator());

            }
            return string + Ui.showSeparator();
        } else {
            return EMPTY_DAY_LIST_MESSAGE;
        }
    }
}
