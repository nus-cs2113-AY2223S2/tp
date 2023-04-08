package seedu.storage;

import seedu.parser.DateFormatter;
import seedu.ui.Ui;
import seedu.workout.Exercise;
import seedu.workout.Workout;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

//@@author calebcjl
/**
 * Represents a storage for WorkoutList.
 */
public class WorkoutListStorage {
    private static final File defaultWorkoutListFile = new File("data/workoutlist.txt");
    private final File workoutListFile;

    public WorkoutListStorage() {
        workoutListFile = defaultWorkoutListFile;
    }

    public ArrayList<Workout> getUserData() {
        ArrayList<Workout> savedWorkoutList = new ArrayList<>();
        if (workoutListFile.exists()) {
            try {
                Scanner scanner = new Scanner(workoutListFile);
                while (scanner.hasNext()) {
                    readWorkoutListFileLine(scanner.nextLine(), savedWorkoutList);
                }
            } catch (FileNotFoundException | ParseException e) {
                Ui.showReadFileErrorMessage("workout list file");
            }
        } else {
            Ui.showNoSavedDataMessage("workout list");
            createWorkoutListFile();
        }
        Ui.showSuccessfulLoadDataMessage("workout list");
        Ui.showOneLine();
        return savedWorkoutList;
    }

    private void readWorkoutListFileLine(String line, ArrayList<Workout> savedWorkoutList) throws ParseException {
        String[] data = line.split(",");
        Workout workout = new Workout(DateFormatter.stringToDate(data[0]), data[1]);
        for (int i = 2; i < data.length - 2; i += 3) {
            Exercise exercise = new Exercise(data[i], data[i + 1], data[i + 2]);
            workout.addExercise(exercise);
        }
        savedWorkoutList.add(workout);
    }

    private void createWorkoutListFile() {
        try {
            if (workoutListFile.getParentFile().mkdirs()) {
                Ui.showCreateDirectoryMessage();
            } else {
                Ui.showDirectoryNotCreatedMessage();
            }
            if (workoutListFile.createNewFile()) {
                Ui.showCreatedNewFileMessage("workout list");
            }
        } catch (IOException e) {
            Ui.showNewFileNotCreatedMessage("workout list");
        }
    }

    void saveUserData(ArrayList<Workout> workouts) throws IOException {
        FileWriter fileWriter = new FileWriter(workoutListFile);
        for (Workout workout : workouts) {
            fileWriter.write(DateFormatter.dateToString(workout.getDate()) + ','
                    + workout.getWorkoutName() + ',');
            for (Exercise exercise : workout.getExercises()) {
                fileWriter.write(exercise.getName() + ',' + exercise.getWeight() + ','
                        + exercise.getRepsPerSet() + ',');
            }
            fileWriter.write(System.lineSeparator());
        }
        fileWriter.close();
    }
}
