package seedu.storage.readfile;


import seedu.parser.DateFormatter;
import seedu.workout.Day;
import seedu.workout.Exercise;
import seedu.workout.Workout;
import seedu.workout.WorkoutList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.Scanner;


public class WorkoutReadFile {
    private static final String SPACE = " ";


    public static WorkoutList readWorkoutFromFile(String filePath) {
        WorkoutList workoutList = new WorkoutList();
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

        try {
            File file = new File(filePath);
            Scanner scanner = new Scanner(file);
            Date currentDate = null;
            String currentWorkoutName = null;

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.startsWith("Date")) {
                    //currentDate = DateFormatter.stringToDate(line.substring(6).trim());
                    String[] currentDateSplit = line.split(":");
                    currentDate = DateFormatter.stringToDate(currentDateSplit[1].trim());
                    workoutList.addWorkoutToList(currentDate, new Day(currentDate));
                } else if (line.startsWith("Workout :")) {
                    String[] currentDWorkoutNameSplit = line.split(":");
                    currentWorkoutName = currentDWorkoutNameSplit[1].trim();
                    Day singleWorkout = workoutList.getSingleWorkout();
                    Workout workout = singleWorkout.getWorkout();
                    workout = new Workout(currentWorkoutName);
                    singleWorkout.addWorkout(currentWorkoutName, workout);
                } else if (line.startsWith(SPACE)) {
                    String[] exerciseDetails = line.substring(4).split(",");
                    String exerciseName = exerciseDetails[0].trim().substring(8).trim();
                    String weight = exerciseDetails[1].replace("Weight:", "").trim();
                    String repsString = exerciseDetails[2].replace("Reps:", "").trim();
                    Exercise exercise = new Exercise(exerciseName, weight, repsString);
                    /* System.out.println("name: " + exerciseName + " weight: "  + weight + "reps: " + repsString);*/
                    // workoutList.addExerciseToWorkout(currentDate, currentWorkoutName, exercise);
                    Day day = workoutList.getSingleWorkout();
                    Workout workoutForOneDay = day.getWorkout();
                    workoutForOneDay.addExercise(exercise);
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        } catch (ParseException e) {
            System.out.println("Error parsing date.");
        }

        return workoutList;
    }
}

