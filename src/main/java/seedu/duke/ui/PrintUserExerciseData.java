package seedu.duke.ui;

import java.util.HashMap;
import java.util.Map;

//@@author L-K-Chng
public class PrintUserExerciseData {

    /**
     * Prints the contents of the HashMap containing unique exercises
     * completed by user as key and frequencies of those exercises as value.
     * Also prints the total number of non-unique and unique exercises
     * completed by the user.
     *
     * @param userExerciseHistory HashMap with exercise name as key and frequency of exercise as value.
     * @param overallCount The total number of non-unique exercises completed.
     * @param uniqueCount The total number of unique exercises completed.
     */

    public static void printUserExerciseHistory(HashMap<String, Integer> userExerciseHistory,
                                                int overallCount, int uniqueCount) {
        assert userExerciseHistory != null : "user exercise history should not be null!";
        if (userExerciseHistory.isEmpty()) {
            System.out.println("You have not completed any exercises ☹");
            System.out.println("Add on to this list by completing a workout session!");
        } else {
            String message = "Here is a list of all the exercises you have completed:" +
                    System.lineSeparator();
            System.out.println(message);
            for (Map.Entry<String,Integer> entry: userExerciseHistory.entrySet()) {
                String key = entry.getKey();
                Integer value = entry.getValue();
                System.out.println(key + System.lineSeparator() +
                        "Times Completed: " + value + System.lineSeparator());
            }
            System.out.println("You have completed a total of " + overallCount + " non-unique exercise(s), " +
                    "of which " + uniqueCount + " of them are unique! Keep it up!:)");
        }
    }
}
