package seedu.storage;


import seedu.commands.Command;
import seedu.parser.DateFormatter;
import seedu.workout.Exercise;
import seedu.workout.Workout;
import seedu.workout.WorkoutList;

import java.io.File;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileReader;


import java.text.ParseException;
import java.util.Date;


public class ReadFile extends Storage{

    private static Workout toStart;
    private static Date date;
    private static final String SPACE = " ";

    private static final int EXERCISE_NAME_INDEX = 0;
    private static final int WEIGHT_INDEX = 1;
    private static final int REPS_PER_SET_INDEX_1 = 2;
    private static final int REPS_PER_SET_INDEX_2 = 3;
    private static final int REPS_PER_SET_INDEX_3 = 4;
    private static final int REPS_PER_SET_INDEX_4 = 5;

    public static void readFile(String filePath) {
        //WorkoutList workoutList = Command.getWorkoutList();

        File savedFile = new File(filePath);
        if (!savedFile.getParentFile().exists()) {
            savedFile.getParentFile().mkdirs();

        }
        try {
            if (!savedFile.exists()) {
                savedFile.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("Failed to create a new file!!!");
        }

        try (BufferedReader bufferRead = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = bufferRead.readLine()) != null) {
                if (line.startsWith("Date:")) {
                    String[] storeDateList = line.split(":");
                    String storeDate = storeDateList[1].trim();
                    date = DateFormatter.stringToDate(storeDate);
                    toStart = new Workout(date);
                    workoutList.addStorageWorkout(toStart);
                    workoutArrayList = workoutList.getWorkoutArrayList();
                }
                if (line.contains(".")) {
                    String exerciseEntry = line.substring(line.indexOf(".") + 1).trim();
                    String[] exerciseParts = exerciseEntry.split(" ");
                    String exerciseName = exerciseParts[EXERCISE_NAME_INDEX];
                    String weight = exerciseParts[WEIGHT_INDEX];
                    String repsPerSet = exerciseParts[REPS_PER_SET_INDEX_1]
                            + SPACE + exerciseParts[REPS_PER_SET_INDEX_2]
                            + SPACE + exerciseParts[REPS_PER_SET_INDEX_3]
                            + SPACE + exerciseParts[REPS_PER_SET_INDEX_4];
                    Exercise toAdd = new Exercise(exerciseName, weight, repsPerSet);
                    //workoutExercises = Workout.getExercises();
                    toStart.addExercise(toAdd);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}

