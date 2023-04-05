package seedu.duke.data.userdata;

import seedu.duke.commons.exceptions.DukeError;
import seedu.duke.data.exercisegenerator.exersisedata.ExerciseData;
import seedu.duke.data.ipptcalculator.UserScore;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class IPPTSession extends Session{
    private UserScore userScore;
    public IPPTSession (ArrayList<ExerciseData>exerciseData,UserScore userScore){
        super(exerciseData);
        this.userScore = userScore;
    }
    public boolean checkIPPTSessionNullity () throws DukeError {
        for (Field f : getClass().getDeclaredFields()) {
            try {
                super.checkExerciseDataNullity();
                if (f.get(this) == null) {
                    return false;
                }
            } catch (Exception e) {
                throw new DukeError("Null element in Session");
            }
        }
        return true;
    }

    public UserScore getUserScore(){
        return this.userScore;
    }
}
