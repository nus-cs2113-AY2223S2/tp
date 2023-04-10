package seedu.workout;

import seedu.exceptions.InvalidArgumentException;
import seedu.parser.DateFormatter;
import seedu.storage.Storage;
import seedu.ui.Ui;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

//@@author calebcjl
public class WorkoutList {
    public static final String EMPTY_WORKOUT = "There are no workouts reported during this week !";
    public static final String INFORMATION = "Information of exercises for the week of ";
    public static final int NO_CURRENT_WORKOUT = -1;
    private final ArrayList<Workout> workouts;
    private int currentWorkoutIndex;

    public WorkoutList() {
        workouts = new ArrayList<>();
        currentWorkoutIndex = NO_CURRENT_WORKOUT;
    }

    public WorkoutList(Storage storage) {
        workouts = storage.readWorkoutListFile();
        currentWorkoutIndex = NO_CURRENT_WORKOUT;
    }


    //@@author ZIZI-czh
    //this method is used for JunitTest
    public WorkoutList(ArrayList<Workout> workouts) {
        this.workouts = workouts;
        currentWorkoutIndex = NO_CURRENT_WORKOUT;
    }


    public int getCurrentWorkoutIndex() {
        return currentWorkoutIndex;
    }

    public void setCurrentWorkoutIndex(int currentWorkoutIndex) {
        this.currentWorkoutIndex = currentWorkoutIndex;
    }

    public ArrayList<Workout> getWorkouts() {
        return workouts;
    }

    //@@author guillaume-grn
    /**
     * Counts the number of sets and reps for each exercise in a specific week.
     *
     * @param dateInSpecificWeek Date in the specific week.
     * @return String representation of the number of sets and reps for each exercise in the specific week.
     */
    public String countSetsReps(Date dateInSpecificWeek) {
        ArrayList<Workout> workoutsInSpecificWeek = getWorkoutsInSpecificWeek(dateInSpecificWeek);
        HashMap<String, ArrayList<Integer>> distinctExercises = new HashMap<>();
        for (Workout workout : workoutsInSpecificWeek) {
            for (Exercise exercise : workout.getExercises()) {
                ArrayList<Integer> setsAndReps = new ArrayList<>();
                int sets = exercise.getSetsCount();
                int reps = exercise.getRepsCount();
                if (distinctExercises.containsKey(exercise.getName())) {
                    sets += distinctExercises.get(exercise.getName()).get(0);
                    reps += distinctExercises.get(exercise.getName()).get(1);
                }
                setsAndReps.add(sets);
                setsAndReps.add(reps);
                distinctExercises.put(exercise.getName(), setsAndReps);
            }
        }
        return displayCountSetsReps(distinctExercises, dateInSpecificWeek);
    }

    /**
     * Displays the count of sets and reps for each distinct exercise performed in a specific week.
     *
     * @param distinctExercises A HashMap containing distinct exercises names as keys and an ArrayList
     *               of sets and reps as values.
     * @param dateInSpecificWeek Date representing a specific week to display count of sets and reps.
     * @return A String representation of the count of sets and reps for each distinct exercise performed
     *               in the specific week.
     */
    //@@ author ZIZI-czh
    public static String displayCountSetsReps(HashMap<String, ArrayList<Integer>> distinctExercises,
                                              Date dateInSpecificWeek) {
        if (distinctExercises.isEmpty()) {
            return EMPTY_WORKOUT;
        }

        StringBuilder output = new StringBuilder();
        output.append(INFORMATION)
                .append(DateFormatter.dateToString(dateInSpecificWeek))
                .append(System.lineSeparator());
        for (String exerciseName : distinctExercises.keySet()) {
            output.append("Name: ")
                    .append(exerciseName)
                    .append(", sets: ")
                    .append(distinctExercises.get(exerciseName).get(0))
                    .append(", rps: ")
                    .append(distinctExercises.get(exerciseName).get(1))
                    .append(System.lineSeparator());
        }
        return output + Ui.line();
    }

    //@@author guillaume-grn
    /**
     * Returns a list of workouts that occurred within a specific week.
     *
     * @param dayInSpecificWeekDate Date representing a specific day in a week to get workouts from.
     * @return ArrayList of Workout objects that occurred within the specified week.
     */
    public ArrayList<Workout> getWorkoutsInSpecificWeek(Date dayInSpecificWeekDate) {
        ArrayList<Workout> workoutsInSpecificWeek = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dayInSpecificWeekDate);
        calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek());
        Date startOfWeekDate = calendar.getTime(); //Monday of the specific week
        calendar.add(Calendar.DAY_OF_WEEK, 6);
        Date endOfWeekDate = calendar.getTime(); //Sunday of the specified week
        for (Workout workout : workouts) {
            if (workout.getDate().compareTo(startOfWeekDate) >= 0 && workout.getDate().compareTo(endOfWeekDate) <= 0 ) {
                workoutsInSpecificWeek.add(workout);
            }
        }
        return workoutsInSpecificWeek;
    }

    //@@author calebcjl
    /**
     * Start a new workout and adds it to the workout list.
     *
     * @param date Date of workout.
     * @param workoutName Name of workout.
     * @throws InvalidArgumentException If there is a workout with the same date and name already in the list.
     */
    public void startWorkout(Date date, String workoutName) throws InvalidArgumentException {
        Workout workout = new Workout(date, workoutName);
        if (isInlist(workout)) {
            throw new InvalidArgumentException("Workout already exist!");
        }
        workouts.add(workout);
        currentWorkoutIndex = workouts.size() - 1;
    }

    //@@author calebcjl
    /**
     * Checks if a workout is in the workout list.
     *
     * @param toCheck Workout to check.
     * @return True if workout found in list. Returns false otherwise.
     */
    private boolean isInlist(Workout toCheck) {
        for (Workout workout: workouts) {
            if (workout.getDate().equals(toCheck.getDate())
                    && workout.getWorkoutName().equals(toCheck.getWorkoutName())) {
                return true;
            }
        }
        return false;
    }

    //@@author calebcjl
    /**
     * Checks if workout list is empty.
     *
     * @return True if empty. Returns false otherwise.
     */
    public boolean isEmptyList() {
        return workouts.isEmpty();
    }

    //@@author calebcjl
    /**
     * Returns workout at a specified index of the workout list.
     *
     * @param index Index of Workout.
     * @return Returns Workout at workoutIndex.
     */
    public Workout getWorkout(int index) {
        return workouts.get(index);
    }

    //@@author calebcjl
    /**
     * Removes workout at a specified index of the workout list.
     *
     * @param index Index of workout.
     */
    public void deleteWorkout(int index) {
        workouts.remove(index);
    }

    //@@author calebcjl
    /**
     * Returns current workout.
     * @return Workout at current workout index.
     */
    public Workout getCurrentWorkout() {
        return workouts.get(currentWorkoutIndex);
    }
}

