package seedu.commands.caloriecommands;



import seedu.calorietracker.Calories;
import seedu.calorietracker.FoodList;
import seedu.commands.Command;
import seedu.parser.DateFormatter;
import seedu.ui.Ui;

import java.util.Date;
import java.util.HashMap;

public class ViewCaloriesCommand extends Command {
    private static final String FAIL_TO_FIND_DATE = " does not exit in the list";
    private  Date caloriesToViewDate;

    public ViewCaloriesCommand(Date caloriesToViewDate) {
        //super();
        this.caloriesToViewDate = caloriesToViewDate;
    }

    public ViewCaloriesCommand(Date caloriesToViewDate, FoodList foodList) {
        this.caloriesToViewDate = caloriesToViewDate;
        this.foodList = foodList;
    }

    @Override
    public String execute() {
        // convert the date to string for display purposes
        String formattedDate = DateFormatter.dateToString(caloriesToViewDate);
       // Calories caloriesOnDate = caloriesRecorder.getCalories();
        //HashMap<String, Integer> singleCalorie = caloriesOnDate.getSingleFoodCalories();
        // get the Day object associated with the given date
        Calories caloriesOnDate = caloriesRecorder.getCalorieMap().get(caloriesToViewDate);
       // HashMap<String, Integer> singleCalorie = caloriesOnDate.getSingleFoodCalories();
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
//            for (String DietName : singleCalorie.keySet()) {
//                string.append("Diet Name: ").append(DietName).append(System.lineSeparator());
////                string.append("Exercise Info: ").append(System.lineSeparator());
            int index = 1;
            for (String foodName : singleCalorie.keySet()) {
                string.append(index).append(". ").append("Food: ")
                        .append(foodName)
                        .append(", calories: ")
                        .append(singleCalorie.get(foodName));
                index++;
            }
            string.append(Ui.showSeparator()).append(System.lineSeparator());
//            }
            return string.toString();
        }
        // if the Day object doesn't exist, return an error message
        return formattedDate + FAIL_TO_FIND_DATE;
    }
}
