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

    /**
     * Abstract the corresponding points according to the user's input based on the scores.json file
     * @param age          User's input of age
     * @param pushupReps   User's input of pushup repetitions in 1 minute
     * @param runTimeInput User's input of runtime for the 2.4km run
     * @param situpReps    User's input of situp repetitions in 1 minute
     * @throws Exception
     **/
    public IPPTCalc (int age, String runTimeInput, int pushupReps, int situpReps) throws Exception {
        loadScoringData();
        setAgeGroup(age);
        parseRunTime(runTimeInput);
        this.pushupReps = pushupReps;
        this.situpReps = situpReps;
    }
    /**
     * Loads the scores.json file
     * @throws DukeError if scores.json file is not able to load
     **/
    public void loadScoringData () throws DukeError {
        try (Reader reader = new InputStreamReader(Objects.requireNonNull(this.getClass()
                                                                              .getResourceAsStream("/scores.json")))) {
            this.scores = new Gson().fromJson(reader, Scores.class);
        } catch (IOException e) {
            throw new DukeError(ErrorMessages.ERROR_LOAD_SCORES_FILE.toString());
        }
    }
    /**
     * Reads the user's age input
     * @throws DukeError if user keys in an invalid age input(under or over the valid age range)
     **/
    public void setAgeGroup (int age) throws DukeError {
        if (age < 22 && age >= 16) {
            this.ageGroup = 1;
        } else if (age > 65 || age < 16) {
            throw new DukeError(ErrorMessages.ERROR_IPPT_INVALID_AGE.toString());
        } else {
            this.ageGroup = ((age - 22) / 3) + 2;
        }
    }
    /**
     * Reads and calculates the run score based on the user's run score input
     **/
    public int calculateRun () {
        int runScore;
        if (runReps < 60 && runReps >= 0) {
            runScore = scores.runScores.get(this.ageGroup - 1).get(this.runReps);
        } else {
            runScore = 0;
        }
        return runScore;
    }
    /**
     * Reads and calculates the pushup score based on the user's pushup score input
     * @throws DukeError if user keys in an invalid pushup input(negative number)
     **/
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
    /**
     * Reads and calculates the situp score based on the user's situp score input
     * @throws DukeError if user keys in an invalid situp input(negative number)
     **/
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
    /**
     * Converts the run score to integer form based on the user's run score input in string form
     * @param userRunTimeInput User's input of the runtime for the 2.4km run exercise
     * @throws Exception       (if user keys in an invalid runtime input)
     **/
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


