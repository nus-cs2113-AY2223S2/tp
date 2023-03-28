package seedu.workout;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import seedu.ui.Ui;

public class WorkoutList {
    public static final int NO_CURRENT_WORKOUT = -1;
    public ArrayList<Workout> workoutArrayList;
    public int currentWorkoutIndex;

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

    public int getLastIndex() {
        return workoutArrayList.size() - 1;
    }

    public Workout getCurrentWorkout() {
        return workoutArrayList.get(currentWorkoutIndex);
    }

    //@@ author guillaume-grn
    public ArrayList<Exercise> countSetsRepsPreparation(Date dayInSpecificWeekDate) {
        WorkoutList workoutsInSpecificWeek = getWorkoutsInSpecificWeek(dayInSpecificWeekDate);
        ArrayList<Exercise> distinctExercisesList = new ArrayList<>();
        for (Workout workout : workoutsInSpecificWeek.workoutArrayList) {
            for (Exercise exercise : workout.getExercises()) {
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
        Ui.displayCountSetsReps(distinctExercisesList,dayInSpecificWeekDate);
    }


    //@@ author guillaume-grn
    public WorkoutList getWorkoutsInSpecificWeek(Date dayInSpecificWeekDate) {
        WorkoutList workoutsInSpecificWeek = new WorkoutList();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dayInSpecificWeekDate);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        Date startOfWeekDate = calendar.getTime();
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        Date endOfWeekDate = calendar.getTime();
        for (Workout workout : workoutArrayList) {
            Date workoutDate = workout.getDate();
            if (workoutDate.compareTo(startOfWeekDate) >= 0 && workoutDate.compareTo(endOfWeekDate) <= 0) {
                workoutsInSpecificWeek.addWorkout(workout);
            }
        }
        return workoutsInSpecificWeek;
    }
}
