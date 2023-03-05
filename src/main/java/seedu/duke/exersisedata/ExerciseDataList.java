package seedu.duke.exersisedata;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
public class ExerciseDataList {
    //Data retrieved from: https://github.com/wrkout/exercises.json (open source MIT license)
    @SerializedName("exercises")
    @Expose
    public ArrayList<ExerciseData> exercises;
}
