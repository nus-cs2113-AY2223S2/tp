package seedu.commands.caloriecommands;

import seedu.calorietracker.Calories;
import seedu.commands.Command;
import seedu.parser.DateFormatter;

import java.util.Date;
import java.util.HashMap;

public class StartDayCaloriesCommand extends Command {

    private static final String STARTED_DAY_FIRST = "You had started calories record on this day before.";
    private static final String STARTED_DAY_SECOND = "Please use '/cstart' to add a food calories consumption!";
    private static final String STARTED_CALORIES = "Great! You start a new food calories record for ";
    private Date date;

    public StartDayCaloriesCommand(Date date) {
        super();
        this.date = date;
    }

    //@@ author ZIZI-czh
    public Date getDate() {
        return date;
    }

    //@@ author ZIZI-czh
    @Override
    public String execute() {

        //get the calories
        HashMap<Date, Calories> dailyCalories = caloriesRecorder.getCalorieMap();
        StringBuilder stringBuilder = new StringBuilder();

        //Check id the date is exit in the HashMap
        if (!dailyCalories.containsKey(date)) {
            //get the calories for entered day
            Calories calories = dailyCalories.get(date);
            // Check if there are food calories record on that day
            if (calories == null) {

                caloriesRecorder.addFoodCalories(date, new Calories(date));
                String formattedDate = DateFormatter.dateToString(date);
                stringBuilder.append(STARTED_CALORIES)
                        .append(formattedDate);
            } else {
                stringBuilder.append(STARTED_DAY_FIRST)
                        .append(System.lineSeparator())
                        .append(STARTED_DAY_SECOND);
            }
        } else {
            stringBuilder.append(STARTED_DAY_FIRST)
                    .append(System.lineSeparator())
                    .append(STARTED_DAY_SECOND);
        }
        return stringBuilder.toString();
    }

}
