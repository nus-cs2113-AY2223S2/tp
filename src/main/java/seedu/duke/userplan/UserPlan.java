package seedu.duke.userplan;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static seedu.duke.exersisedata.ExerciseFilter.isAValidFilter;
import static seedu.duke.userplan.Day.dayToInt;
import static seedu.duke.userplan.Day.intToDay;

//@@author Khulon
public class UserPlan {
    private static final Integer DAYSINAWEEK = 7;
    private static ArrayList<Plan>[] plan;

    public UserPlan () {
        this.plan = new ArrayList[DAYSINAWEEK]; //not safe (UNCHECKED ASSIGNMENT)
        for (int i = 0; i < DAYSINAWEEK; i++) {
            plan[i] = new ArrayList<>();
        }
    }

    public static ArrayList<Plan>[] getPlan () {
        return plan;
    }

    public static ArrayList<String> getExercisePlan (String planName) {
        for (int day = 0; day < DAYSINAWEEK; day++) {
            for (int exercisePlan = 0; exercisePlan < plan[day].size(); exercisePlan++) {
                if (plan[day].get(exercisePlan).getPlanName().equals(planName)) {
                    return plan[day].get(exercisePlan).getExercisePlans();
                }
            }
        }
        return null;
    }

    public static void addPlan (String[] userCommands) {
        ArrayList<String> exerciseFilters = new ArrayList<>();
        if (userCommands.length < 4) {
            System.out.println("invalid add command");
            //throw new InvalidAddPlanCommand();
            return;
        }
        int intDay = dayToInt(userCommands[1]);
        if (intDay == -1) {
            System.out.println("invalid date");
            //throw new InvalidDate();
            return;
        }
        String name = userCommands[2];
        for (int i = 3; i < userCommands.length; i++) {
            if (!isAValidFilter(userCommands[i])) {
                System.out.println("invalid filter");
                //throw new InvalidFilter();
                return;
            }
            exerciseFilters.add(userCommands[i]);
        }
        Plan newPlan = new Plan(exerciseFilters, name);
        plan[intDay].add(newPlan);
        System.out.println("[" + name + "] added to " + intToDay(intDay));
    }

    public static void deletePlan (String[] userCommands) {
        if (userCommands.length != 3) {
            System.out.println("invalid delete command");
            //throw new InvalidDeleteCommand();
            return;
        }
        int intDay = dayToInt(userCommands[1]);
        if (intDay == -1) {
            System.out.println("invalid date");
            //throw new InvalidDate();
            return;
        }
        String name = userCommands[2];
        for (int i = 0; i < plan[intDay].size(); i++) {
            if (plan[intDay].get(i).getPlanName().equals(name)) {
                plan[intDay].remove(i);
                System.out.println("[" + name + "] deleted from " + intToDay(intDay));
                return;
            }
        }
        System.out.println("no such plan found");
        //throw new InvalidPlan
    }

    /**
     * Adds a created plan by day.
     * Warning: Used Unchecked assignment: 'java.util.ArrayList[]' to adapt with the rest of code.
     *
     * @param dayPlan Plan class for the day.
     * @param day Day of the week.
     */
    public void addDayPlan (Plan dayPlan, int day) {
        plan[day].add(dayPlan);
    }

    /**
     * Gets the plans for the specified day.
     *
     * @param day Day of the week.
     * @return the arraylist of plans for the day.
     */
    public ArrayList<Plan> getDayPlans (int day) {
        return plan[day];
    }

}

