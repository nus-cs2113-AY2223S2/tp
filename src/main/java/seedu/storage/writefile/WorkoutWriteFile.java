package seedu.storage.writefile;


import seedu.parser.DateFormatter;
import seedu.workout.Day;
import seedu.workout.Exercise;
import seedu.workout.Workout;
import seedu.workout.WorkoutList;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;

public class WorkoutWriteFile {
    private static final String SPACE = "          ";


    private static HashMap<Date, Day> workouts;

    private static HashMap<String, Workout> dailyWorkout;


    public static void writeWorkoutToFile(String filePath, WorkoutList workoutList) {

        try {
            File savedFile = new File(filePath);
            FileWriter writeFile = new FileWriter(savedFile);
            workouts = workoutList.getWorkouts();
            for (Date date : workouts.keySet()) {
                writeFile.write("Date    : ");
                String formattedDate = DateFormatter.dateToString(date);
                writeFile.write(formattedDate + System.lineSeparator());
                Day workoutsOnOneDay = workouts.get(date);
                dailyWorkout = workoutsOnOneDay.getWorkoutsByDate();
                for (String workoutName : dailyWorkout.keySet()) {
                    writeFile.write("Workout : " + workoutName + System.lineSeparator());
                    int index = 1;
                    writeFile.write("Exercise: " + System.lineSeparator());
                    //  if(dailyWorkout.get(workoutName)) {
                    for (Exercise exercises : dailyWorkout.get(workoutName).getExercises()) {
                        String exerciseStr = String.format("%s. Name: %s, Weight: %s, Reps: %s", index,
                                exercises.getName(), exercises.getWeight(), exercises.getRepsPerSet());
                        writeFile.write(SPACE + exerciseStr + System.lineSeparator());
                        index++;
                        //  }
                    }
                }
            }
            writeFile.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
