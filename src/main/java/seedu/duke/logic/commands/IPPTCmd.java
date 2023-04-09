package seedu.duke.logic.commands;

import seedu.duke.ui.ErrorMessages;
import seedu.duke.commons.exceptions.DukeError;
import seedu.duke.data.exercisegenerator.GenerateExercise;
import seedu.duke.data.exercisegenerator.exersisedata.ExerciseData;
import seedu.duke.data.ipptcalculator.IPPTCalc;
import seedu.duke.data.ipptcalculator.UserScore;
import seedu.duke.data.userdata.IPPTSession;
import seedu.duke.data.userdata.UserCareerData;
import seedu.duke.storage.Storage;

import java.util.ArrayList;

//@@author ghzr0
public class IPPTCmd {
    private IPPTCalc ipptCalculator;
    /**
     * Reads in the user's input of the 3 exercises
     * @param userCommands User's inputs for the arguments in the sequence:
     *                     age , runtime score, pushup score and situp score
     * @throws DukeError   If user keys in an invalid argument or invalid input format
     **/
    // pass in input : IPPT [age] [runtime] [pushup score] [situp score]
    public IPPTCmd (String[] userCommands) throws DukeError {
        String userInputAge = userCommands[0];
        String userInputRunTime = userCommands[1];
        String userInputPushUps = userCommands[2];
        String userInputSitups = userCommands[3];
        if (userInputRunTime.length() > 5) {
            throw new DukeError(ErrorMessages.ERROR_IPPT_INVALID_TIMING.toString());
        }
        try {
            int ageGroup = Integer.parseInt(userInputAge);
            int pushupReps = Integer.parseInt(userInputPushUps);
            int situpReps = Integer.parseInt(userInputSitups);
            ipptCalculator = new IPPTCalc(ageGroup, userInputRunTime, pushupReps, situpReps);
        } catch (NullPointerException | NumberFormatException numberFormatError) {
            throw new DukeError(ErrorMessages.ERROR_IPPT_INVALID_FORMAT.toString());
        } catch (Exception e) {
            throw new DukeError(e.getMessage());
        }
    }
    /**
     * Adds a valid IPPT exercise session to the user's list of completed exercise sessions
     * @param storage           This handles the storage of data
     * @param exerciseGenerator This takes in filter parameters and outputs a
     *      *                   curated exercise list
     * @param userCareerData    This keeps track and allows logging of all user
     *      *                   data
     * @throws DukeError
     **/
    public void addIPPTSession (GenerateExercise exerciseGenerator, UserCareerData userCareerData,
                                Storage storage) throws DukeError {
        UserScore userScore = new UserScore();
        ArrayList<ExerciseData> exercises = exerciseGenerator.generateIPPTExercises(exerciseGenerator.generateSetAll());
        try {
            int runScore = ipptCalculator.calculateRun();
            int pushupScore = ipptCalculator.calculatePushup();
            int situpScore = ipptCalculator.calculateSitup();
            userScore.setRunScore(runScore);
            userScore.setPushupScore(pushupScore);
            userScore.setSitupScore(situpScore);
            IPPTSession ipptsession = new IPPTSession(exercises, userScore);
            userCareerData.addWorkoutSession(ipptsession);
            storage.writeToJson(userCareerData);
            System.out.println("We added your IPPT as a session");
            System.out.println("Your total score is " + userScore.getTotalScore());
        } catch (Exception e) {
            throw new DukeError(e.getMessage());
        }
    }
}

