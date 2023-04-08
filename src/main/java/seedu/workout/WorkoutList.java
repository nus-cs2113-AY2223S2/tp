package seedu.workout;


import seedu.parser.DateFormatter;

import seedu.ui.Ui;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;


//@@ author ZIZI-czh
public class WorkoutList {

    public static final String EMPTY_WORKOUT = "There are no workouts reported during this week !";

    public static final String INFORMATION = "Information of exercises for the week of ";
    public HashMap<String, Workout> workout;
    private HashMap<Date, Day> workouts;
    private Day day = new Day();
    private Workout dailyWorkout = new Workout();
    private Date date;


    //@@ author ZIZI-czh
    public WorkoutList() {

        workout = new HashMap<>();
        workouts = new HashMap<>();
    }

    //@@ author ZIZI-czh
    public HashMap<String, Workout> getWorkout() {
        return workout;
    }

    //@@ author ZIZI-czh
    public Day getSingleWorkout() {
        return day;
    }
    //@@ author ZIZI-czh
    public void setDay(Day day) {
        this.day = day;
    }
    //@@ author ZIZI-czh
    public HashMap<Date, Day> getWorkouts() {
        return workouts;
    }


    //@@ author ZIZI-czh
    public void addWorkoutToList(Date date, Day day) {
        workouts.put(date, day);
        setDay(day);
        setDate(date);
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    //@@ author ZIZI-czh
    public Day getDay() {
        return day;
    }

    //@@ author ZIZI-czh
    public void setWorkouts(HashMap<Date, Day> workouts) {
        this.workouts = workouts;
    }


    //@@ author guillaume-grn
    //@@ author ZIZI-czh
    public ArrayList<Exercise> countSetsRepsPreparation(Date dayInSpecificWeekDate) {

        HashMap<Date, Day> workoutsInSpecificWeek = getWorkoutsInSpecificWeek(dayInSpecificWeekDate);
        Day workoutForDay = workoutsInSpecificWeek.get(dayInSpecificWeekDate);
        ArrayList<Exercise> distinctExercisesList = new ArrayList<>();
        HashMap<String, Workout> workoutsForSpecificDay = workoutForDay.getWorkoutsByDate();

        for (Workout workout1 : workoutsForSpecificDay.values()) {
            for (Exercise exercise : workout1.getExercises()) {
                boolean isExistingExercise = false;

                for (Exercise distinctExercise : distinctExercisesList) {
                    if (exercise.getName().equals(distinctExercise.getName())) {
                        distinctExercise.setRepsPerSet(exercise.getRepsPerSet());
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
    //@@ author ZIZI-czh
    public String countSetsReps(Date dayInSpecificWeekDate) {
        ArrayList<Exercise> distinctExercisesList = countSetsRepsPreparation(dayInSpecificWeekDate);
        return displayCountSetsReps(distinctExercisesList, dayInSpecificWeekDate);
    }

    //@@ author ZIZI-czh
    public static String displayCountSetsReps(ArrayList<Exercise> distinctExercisesList, Date dayInSpecificWeekDate) {
        StringBuilder output = new StringBuilder();
        if(distinctExercisesList == null){
            return null;
        }
        if (distinctExercisesList.isEmpty()) {
            output.append(EMPTY_WORKOUT);
            return output.toString();
        }

        output.append(INFORMATION)
                .append(DateFormatter.dateToString(dayInSpecificWeekDate))
                .append(System.lineSeparator());
        //Ui.showSeparator();
        for (Exercise exercise : distinctExercisesList) {
            output.append("Name: ")
                    .append(exercise.getName())
                    .append(", sets: ")
                    .append(exercise.getSetsCount())
                    .append(", rps:")
                    .append(exercise.getRepsCount())
                    .append(System.lineSeparator());
        }
        return output + Ui.showSeparator();
    }

    //@@ author guillaume-grn
    //@@ author ZIZI-czh
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

