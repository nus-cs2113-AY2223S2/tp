package seedu.duke.data.ipptcalculator;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

public class Scores {
    @SerializedName("runScores")
    @Expose
    public ArrayList<ArrayList<Integer>> runScores;
    @SerializedName("runTimeInSecs")
    @Expose
    public ArrayList<Integer> runTimeInSecs;
    @SerializedName("pushupScores")
    @Expose
    public ArrayList<ArrayList<Integer>> pushupScores;
    @SerializedName("situpScores")
    @Expose
    public ArrayList<ArrayList<Integer>> situpScores;

    //public ArrayList<Scores> scores;
}
