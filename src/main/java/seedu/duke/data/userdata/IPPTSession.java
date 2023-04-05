package seedu.duke.data.userdata;

import seedu.duke.data.exercisegenerator.exersisedata.ExerciseData;
import seedu.duke.data.ipptcalculator.UserScore;

import java.util.ArrayList;

public class IPPTSession extends Session{
    private UserScore userScore;
    public IPPTSession (ArrayList<ExerciseData>exerciseData,UserScore userScore){
        super(exerciseData);
        this.userScore = userScore;
    }

    public UserScore getUserScore(){
        return this.userScore;
    }
}
