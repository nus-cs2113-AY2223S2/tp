package seedu.commands.caloriecommands;


import seedu.calorietracker.Calories;
import seedu.commands.Command;
import seedu.parser.DateFormatter;
import seedu.ui.Ui;

import java.util.Date;
import java.util.HashMap;

public class ViewCaloriesCommand extends Command {
    private static final String FAIL_TO_FIND_DATE = " does not exit in the list";
    private Date caloriesToViewDate;

    public ViewCaloriesCommand(Date caloriesToViewDate) {
        //super();
        this.caloriesToViewDate = caloriesToViewDate;
    }


    @Override
    public String execute() {
        // convert the date to string for display purposes
        String formattedDate = DateFormatter.dateToString(caloriesToViewDate);
        // get the Day object associated with the given date
        Calories caloriesOnDate = caloriesRecorder.getCalorieMap().get(caloriesToViewDate);
        // if the Day object exists, retrieve the workouts and print them
        if (caloriesOnDate != null) {
            HashMap<String, Integer> singleCalorie;
            singleCalorie = caloriesOnDate.getSingleFoodCalories();
            System.out.println(caloriesOnDate.getCalories() + caloriesOnDate.getName());
            StringBuilder string = new StringBuilder();
            if (singleCalorie.isEmpty()) {
                return "No calories found on " + formattedDate + System.lineSeparator() + Ui.showSeparator();
            }
            string.append("Calories on ").append(formattedDate).append(":").append(System.lineSeparator());
            int index = 1;
            for (String foodName : singleCalorie.keySet()) {
                string.append(index).append(". ").append("Food: ")
                        .append(foodName)
                        .append(",  Calories: ")
                        .append(singleCalorie.get(foodName))
                        .append(" kcal")
                        .append(System.lineSeparator());
                index++;
            }
            string.append(Ui.showSeparator()).append(System.lineSeparator());
            return string.toString();
        }
        // if the Day object doesn't exist, return an error message
        return formattedDate + FAIL_TO_FIND_DATE;
    }
}
