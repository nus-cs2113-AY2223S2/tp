package seedu.duke.data.ipptcalculator;

import com.google.gson.Gson;
import seedu.duke.commons.exceptions.DukeError;
import seedu.duke.ui.ErrorMessages;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Objects;

//@@author ghzr0
public class IPPTCalc {
    private int ageGroup;
    private int runReps;
    private int pushupReps;
    private int situpReps;
    private Scores scores;

    public IPPTCalc (int age, String runTimeInput, int pushupReps, int situpReps) throws Exception {
        loadScoringData();
        setAgeGroup(age);
        parseRunTime(runTimeInput);
        this.pushupReps = pushupReps;
        this.situpReps = situpReps;
    }

    public void loadScoringData () throws DukeError {
        try (Reader reader = new InputStreamReader(Objects.requireNonNull(this.getClass()
                                                                              .getResourceAsStream("/scores.json")))) {
            this.scores = new Gson().fromJson(reader, Scores.class);
        } catch (IOException e) {
            throw new DukeError(ErrorMessages.ERROR_LOAD_SCORES_FILE.toString());
        }
    }

    public void setAgeGroup (int age) throws DukeError {
        if (age < 22 && age >= 16) {
            this.ageGroup = 1;
        } else if (age > 65 || age < 16) {
            throw new DukeError(ErrorMessages.ERROR_IPPT_INVALID_AGE.toString());
        } else {
            this.ageGroup = ((age - 22) / 3) + 2;
        }
    }

    public int calculateRun () {
        int runScore;
        if (runReps < 60 && runReps >= 0) {
            runScore = scores.runScores.get(this.ageGroup - 1).get(this.runReps);
        } else {
            runScore = 0;
        }
        return runScore;
    }

    public int calculatePushup () throws DukeError {
        int pushScore;
        if (pushupReps < 0) {
            throw new DukeError(ErrorMessages.ERROR_NEGATIVE_PUSHUP_NUMBER.toString());
        }
        if (pushupReps <= 60 && pushupReps > 0) {
            pushScore = scores.pushupScores.get(ageGroup - 1).get(pushupReps - 1);
        } else if (pushupReps > 60) {
            pushScore = 25;
        } else {
            pushScore = 0;
        }
        return pushScore;
    }

    public int calculateSitup () throws DukeError {
        int sitScore;
        if (situpReps < 0) {
            throw new DukeError(ErrorMessages.ERROR_NEGATIVE_SITUP_NUMBER.toString());
        }
        if (situpReps <= 60 && situpReps > 0) {
            sitScore = scores.situpScores.get(ageGroup - 1).get(situpReps - 1);
        } else if (situpReps > 60) {
            sitScore = 25;
        } else {
            sitScore = 0;
        }
        return sitScore;
    }

    public void parseRunTime (String userRunTimeInput) throws Exception {
        try {
            String[] runTime = userRunTimeInput.split(":");
            int secs = Integer.parseInt(runTime[1]);
            int mins = Integer.parseInt(runTime[0]);
            if (mins < 0 || secs < 0) {
                throw new DukeError(ErrorMessages.ERROR_IPPT_INVALID_TIMING.toString());
            }
            if (secs > 60) {
                secs = 59;
            }
            int secondsRounded = (int) ((Math.round(secs / 10.0) * 10) + mins * 60);
            if (secondsRounded < 510) {
                secondsRounded = 510;
            } else if (secondsRounded > 1100) {
                secondsRounded = 1100;
            }
            this.runReps = scores.runTimeInSecs.indexOf(secondsRounded);
        } catch (Exception e) {
            throw new DukeError(ErrorMessages.ERROR_IPPT_INVALID_TIMING.toString());
        }
    }

}


