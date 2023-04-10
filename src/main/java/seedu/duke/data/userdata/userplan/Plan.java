package seedu.duke.data.userdata.userplan;

import java.lang.reflect.Field;
import java.util.ArrayList;
import seedu.duke.commons.exceptions.DukeError;

//@@author Khulon
public class Plan {
    private ArrayList<String> exercisePlans;
    private String planName;
    private int noOfExercise;

    /**
     * initialize new plan
     *
     * @param exercisePlan array list of filters for the exercise plan
     * @param planName name of the plan
     */
    public Plan (ArrayList<String> exercisePlan, String planName) {
        this.exercisePlans = exercisePlan;
        this.planName = planName;
        this.noOfExercise = 0;
    }

    /**
     * initialize new plan
     *
     * @return planName name of the plan
     */
    public String getPlanName () {
        return planName;
    }

    /**
     * get the exercise plans of the plan
     *
     * @return the exercise plan
     */
    public ArrayList<String> getExercisePlans () {
        return this.exercisePlans;
    }

    public void checkDayPlanNullity () throws DukeError {
        for (Field f : getClass().getDeclaredFields()) {
            try {
                if (f.get(this) == null) {
                    throw new DukeError("Null element in day plan");
                }
            } catch (Exception e) {
                throw new DukeError("Illegal access");
            }
        }
    }

}
