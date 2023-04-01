package seedu.duke.ui;

import java.util.HashMap;
import java.util.Map;

public class PrintUserExerciseData {

    /**
     * Prints the contents of the HashMap containing unique exercises
     * completed by user as key and frequencies of those exercises as value.
     * @param userExerciseHistory HashMap with exercise name as key and frequency of exercise as value.
     */
    public static void printUserExerciseHistory(HashMap<String, Integer> userExerciseHistory) {
        assert userExerciseHistory != null : "user exercise history should not be null!";
        if (userExerciseHistory.size() == 0) {
            System.out.println("You have not completed any exercises ☹");
            System.out.println("Add on to this list by completing a workout session!");
        } else {
            String message = "Here is a list of all the exercises you have completed:" +
                    System.lineSeparator();
            System.out.println(message);
            for (Map.Entry<String,Integer> entry: userExerciseHistory.entrySet()) {
                String key = entry.getKey();
                Integer value = entry.getValue();
                String val = value.toString();
                System.out.println(String.format("%-10s %-60s %-20s", "Exercise: ", key, "Times Completed: " + val));
            }
        }
    }
}
