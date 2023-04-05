package seedu.duke.data.ipptcalculator;

import com.google.gson.Gson;
import seedu.duke.commons.exceptions.DukeError;
import seedu.duke.data.exercisegenerator.exersisedata.ExerciseDataList;
import seedu.duke.ui.ErrorMessages;

import java.io.*;
import java.util.Objects;

public class IPPTCalc {
    private int ageGroup;
    private int runReps;
    private int pushupReps;
    private int situpReps;
    private Scores scores;

    public IPPTCalc(int age, String runTimeInput, int pushupReps,int situpReps) throws Exception {
        loadScoringData();
        setAgeGroup(age);
        parseRunTime(runTimeInput);
        this.pushupReps = pushupReps;
        this.situpReps = situpReps;
    }

    public void loadScoringData() throws FileNotFoundException {
        try (Reader reader = new InputStreamReader(Objects.requireNonNull(this.getClass()
                .getResourceAsStream("/scores.json")))) {
            this.scores = new Gson().fromJson(reader, Scores.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setAgeGroup(int age){
        if(age < 22){
            this.ageGroup = 1;
        }else{
            this.ageGroup = ((age - 22) / 3 ) + 2;
        }
    }

    public int calculateRun(){
        int runScore;
        if(runReps < 60 && runReps >= 0){
            runScore = scores.runScores.get(this.ageGroup-1).get(this.runReps);
        }else {
            runScore = 0;
        }
        return runScore;
    }

    public int calculatePushup(){
        int pushScore;
        if(pushupReps <= 60 && pushupReps > 0 ){
            pushScore = scores.pushupScores.get(ageGroup-1).get(pushupReps-1);
        }else {
            pushScore = 0;
        }
        return pushScore;
    }

    public int calculateSitup(){
        int sitScore;
        if(situpReps <= 60 && situpReps > 0){
            sitScore = scores.situpScores.get(ageGroup-1).get(situpReps-1);
        }else {
            sitScore = 0;
        }
        return sitScore;
    }

    public void parseRunTime(String userRunTimeInput) throws Exception {
        try {
            String[] runTime = userRunTimeInput.split(":");
            int mins = Integer.parseInt(runTime[0]);
            int secs = Integer.parseInt(runTime[1]);
            int rounded_secs = (int) ((Math.round(secs / 10.0) * 10) + mins * 60);
            this.runReps = scores.runTimeInSecs.indexOf(rounded_secs);
        } catch (Exception e){
            throw new DukeError(ErrorMessages.ERROR_IPPT_INVALID_TIMING.toString());
        }
    }
}


