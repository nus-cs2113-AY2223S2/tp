package seedu.workout;


import seedu.parser.DateFormatter;
import seedu.ui.Ui;

import java.util.*;

public class WorkoutList {
    public static final int NO_CURRENT_WORKOUT = -1;
    public static final int EMPTY = 0;
    private static final String EMPTY_WORKOUT_LIST_MESSAGE = "No workout recorded.";
    private static final String EMPTY_DAY_LIST_MESSAGE = "No days have been found in the list";
    private static final String WORKOUT_LIST_HEADER =
            "Here are the list of dates of your workouts:" + System.lineSeparator();
    private int currentStorageWorkoutIndex;

    private boolean hasWorkout = false;
    //private  HashMap<String, HashMap<String, Workout>> workouts;
    private HashMap<String, Workout> workoutsByDate;

    public HashMap<String, Workout> workout;
    private HashMap<Date, Day> workouts;
    private Day day = new Day();

    public WorkoutList() {
        // workouts = new HashMap<>();
        workout = new HashMap<>();
        workouts = new HashMap<>();
        //day = new Day();
    }

    public HashMap<String, Workout> getWorkout() {
        return workout;
    }

    public Day getSingleWorkout() {
        return day;
    }
    
    public HashMap<Date, Day> getWorkouts() {
        return workouts;
    }

    public void setDay(Day day) {
        this.day = day;
    }

    public void addWorkoutToList(Date date, Day day) {
        workouts.put(date, day);
        setDay(day);
    }

    public Day getDay() {
        return day;
    }

    public void setWorkouts(HashMap<Date, Day> workouts) {
        this.workouts = workouts;
    }


    //@@ author guillaume-grn
    public ArrayList<Exercise> countSetsRepsPreparation(Date dayInSpecificWeekDate) {
        HashMap<Date, Day> workoutsInSpecificWeek = getWorkoutsInSpecificWeek(dayInSpecificWeekDate);
        Day workoutForDay = workoutsInSpecificWeek.get(dayInSpecificWeekDate);
        ArrayList<Exercise> distinctExercisesList = new ArrayList<>();
        if (workoutForDay == null) {
            System.out.println("There is no workout in this week");
            return distinctExercisesList;
        }
        // System.out.println("size:" + workoutsInSpecificWeek.getDay().getWorkout().getExercises());
        HashMap<String, Workout> workoutsForSpecificDay = workoutForDay.getWorkoutsByDate();

        for (Workout workout1 : workoutsForSpecificDay.values()) {
            for (Exercise exercise : workout1.getExercises()) {
                boolean isExistingExercise = false;

                for (Exercise distinctExercise : distinctExercisesList) {
                    if (exercise.getName().equals(distinctExercise.getName())) {
                        distinctExercise.setRepsPerSet(distinctExercise.getRepsPerSet() + " "
                                + exercise.getRepsPerSet());
                        isExistingExercise = true;
                        break;
                    }
                }
                if (!isExistingExercise) {
                    Exercise distinctExercise = new Exercise(exercise.getName(), exercise.getWeight(),
                            exercise.getRepsPerSet());
                    distinctExercisesList.add(distinctExercise);
                }
            }
        }

        return distinctExercisesList;
    }

    //@@ author guillaume-grn
    public void countSetsReps(Date dayInSpecificWeekDate) {
        ArrayList<Exercise> distinctExercisesList = countSetsRepsPreparation(dayInSpecificWeekDate);
        displayCountSetsReps(distinctExercisesList, dayInSpecificWeekDate);
    }

    public static void displayCountSetsReps(ArrayList<Exercise> distinctExercisesList, Date dayInSpecificWeekDate) {
        if (distinctExercisesList.isEmpty()) {
            System.out.println("There are no workouts reported during this week !");
            return;
        }
        System.out.println("Exercises and number of sets and reps for the week of " + DateFormatter.dateToString(dayInSpecificWeekDate));
        //Ui.showSeparator();
        for (Exercise exercise : distinctExercisesList) {
            System.out.println(exercise.getName() + " - " + exercise.getSetsCount() + " sets" + " - "
                    + exercise.getRepsCount() + " reps");
        }
        Ui.showSeparator();
    }

    //@@ author guillaume-grn
    public HashMap<Date, Day> getWorkoutsInSpecificWeek(Date dayInSpecificWeekDate) {
        HashMap<Date, Day> workoutsInSpecificWeek = new HashMap<>();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dayInSpecificWeekDate);
        calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek());
        Date startOfWeekDate = calendar.getTime();
        calendar.add(Calendar.DAY_OF_WEEK, 6);
        Date endOfWeekDate = calendar.getTime();
        for (Date workoutDate : workouts.keySet()) {
            if (workoutDate.compareTo(startOfWeekDate) >= 0 && workoutDate.compareTo(endOfWeekDate) <= 0) {
                workoutsInSpecificWeek.put(workoutDate, workouts.get(workoutDate));
            }
        }
        return workoutsInSpecificWeek;
    }
}

