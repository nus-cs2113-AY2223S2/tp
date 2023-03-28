package seedu.workouttracker;

import seedu.parser.DateFormatter;

import java.util.ArrayList;
import java.util.Date;

public class WorkoutList {
    public static final int NO_CURRENT_WORKOUT = -1;
    public static final String WORKOUT_LIST_HEADER =
            "Here are the list of dates of your workouts:" + System.lineSeparator();
    public static final int EMPTY = 0;
    public static final String EMPTY_WORKOUT_LIST_MESSAGE = "No workouts recorded.";
    private final ArrayList<Workout> workoutArrayList;
    private int currentWorkoutIndex;

    public WorkoutList() {
        workoutArrayList = new ArrayList<>();
        currentWorkoutIndex = NO_CURRENT_WORKOUT;
    }

    public void addWorkout(Workout workout) {
        workoutArrayList.add(workout);
        currentWorkoutIndex = getLastIndex();
    }

    public void setCurrentWorkoutIndex(int currentWorkoutIndex) {
        this.currentWorkoutIndex = currentWorkoutIndex;
    }

    public int getCurrentWorkoutIndex() {
        return currentWorkoutIndex;
    }

    public int getLastIndex() {
        return workoutArrayList.size() - 1;
    }

    public Workout getCurrentWorkout() {
        return workoutArrayList.get(currentWorkoutIndex);
    }

    public ArrayList<Workout> getWorkoutArrayList() {
        return workoutArrayList;
    }

    public String deleteWorkout(Date date) {
        for (Workout workout : workoutArrayList) {
            if (workout.getDate() == date) {
                workoutArrayList.remove(workout);
                return "Workout deleted";
            }
        }

        return "Workout not in list";
    }

    @Override
    public String toString() {
        if (workoutArrayList.size() == EMPTY) {
            return EMPTY_WORKOUT_LIST_MESSAGE;
        }

        StringBuilder workoutListString = new StringBuilder();
        workoutListString.append(WORKOUT_LIST_HEADER);
        for (int i = 0; i < workoutArrayList.size(); i += 1) {
            workoutListString.append(i + 1).append(". "
                    + DateFormatter.dateToString(workoutArrayList.get(i).getDate()) + System.lineSeparator());
        }

        return workoutListString.toString();
    }
}
