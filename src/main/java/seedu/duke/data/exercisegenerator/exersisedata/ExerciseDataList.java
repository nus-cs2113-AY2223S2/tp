package seedu.duke.data.exercisegenerator.exersisedata;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

//@@author EangJS
public class ExerciseDataList {
    @SerializedName("exercises")
    @Expose
    public ArrayList<ExerciseData> exercises;

}
