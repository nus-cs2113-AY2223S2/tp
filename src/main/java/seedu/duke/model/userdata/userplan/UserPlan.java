package seedu.duke.userplan;

import seedu.duke.commons.exceptions.DukeError;
import seedu.duke.commons.exceptions.InvalidAddPlanError;
import seedu.duke.commons.exceptions.InvalidDateInputError;
import seedu.duke.commons.exceptions.InvalidFilterInputError;
import seedu.duke.commons.exceptions.InvalidDeletePlanError;
import seedu.duke.commons.exceptions.InvalidPlanError;
import java.util.ArrayList;

import static seedu.duke.exersisedata.ExerciseFilter.isAValidFilter;
import static seedu.duke.userplan.Day.dayToInt;
import static seedu.duke.userplan.Day.intToDay;

//@@author Khulon
public class UserPlan {
    private static final Integer DAYSINAWEEK = 7;
    private static ArrayList<Plan>[] plan;

    /**
     * initialize new UserPlan
     */
    public UserPlan () {
        this.plan = new ArrayList[DAYSINAWEEK];
        for (int i = 0; i < DAYSINAWEEK; i++) {
            plan[i] = new ArrayList<>();
        }
    }

    /**
     * get the Plan
     *
     * @return the plan
     */
    public static ArrayList<Plan>[] getPlan () {
        return plan;
    }

    /**
     * get the exercise plan that of the plan that matches name input by user
     *
     * @param planName the name of the plan
     * @return the exercise plan or null if not found
     */
    public static ArrayList<String> getExercisePlan (String planName) {
        System.out.println("1:" + planName);
        for (int day = 0; day < DAYSINAWEEK; day++) {
            for (int exercisePlan = 0; exercisePlan < plan[day].size(); exercisePlan++) {
                System.out.println("2:" + plan[day].get(exercisePlan).getPlanName());
                if (plan[day].get(exercisePlan).getPlanName().equals(planName)) {
                    return plan[day].get(exercisePlan).getExercisePlans();
                }
            }
        }
        return null;
    }

    /**
     * add a plan
     *
     * @param userCommands the commands input by user
     */
    public static void addPlan (String[] userCommands) throws DukeError {
        ArrayList<String> exerciseFilters = new ArrayList<>();
        if (userCommands.length < 4) {
            throw new InvalidAddPlanError();
        }
        int intDay = dayToInt(userCommands[1]);

        if (intDay == -1) {
            throw new InvalidDateInputError();
        }
        String name = userCommands[2];
        for (int i = 3; i < userCommands.length; i++) {
            if (!isAValidFilter(userCommands[i])) {
                throw new InvalidFilterInputError();
            }
            exerciseFilters.add(userCommands[i]);
        }
        Plan newPlan = new Plan(exerciseFilters, name);
        plan[intDay].add(newPlan);
        System.out.println("[" + name + "] added to " + intToDay(intDay));
    }

    /**
     * delete a plan
     *
     * @param userCommands the commands input by user
     */
    public static void deletePlan (String[] userCommands) throws DukeError {
        if (userCommands.length != 3) {
            throw new InvalidDeletePlanError();
        }
        int intDay = dayToInt(userCommands[1]);
        if (intDay == -1) {
            throw new InvalidDateInputError();
        }
        String name = userCommands[2];
        for (int i = 0; i < plan[intDay].size(); i++) {
            if (plan[intDay].get(i).getPlanName().equals(name)) {
                plan[intDay].remove(i);
                System.out.println("[" + name + "] deleted from " + intToDay(intDay));
                return;
            }
        }
        throw new InvalidPlanError();
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

