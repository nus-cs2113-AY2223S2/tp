package seedu.duke.ui;

import java.util.HashMap;
import java.util.Map;

public class PrintUserExerciseData {

    public static void printUserExerciseHistory(HashMap<String, Integer> userExerciseHistory) {
        String message = "Here is a list of all the exercises you have completed:" +
                System.lineSeparator();
        System.out.println(message);
        for (Map.Entry<String,Integer> entry: userExerciseHistory.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            System.out.println("Exercise: " + key + "\tFrequency of Completion: " + value);
        }
    }
}
