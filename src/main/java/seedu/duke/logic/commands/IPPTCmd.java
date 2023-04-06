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

//@author ghzr0
public class IPPTCmd{
    private IPPTCalc ipptCalculator;
    // pass in input : IPPT [age] [runtime] [pushup score] [situp score]
    public IPPTCmd(String[] userCommands) throws DukeError{
        String userInputAge = userCommands[0];
        String userInputRunTime = userCommands[1];
        String userInputPushUps = userCommands[2];
        String userInputSitups = userCommands[3];
        if (userInputRunTime.length() > 5){
            throw new DukeError(ErrorMessages.ERROR_IPPT_INVALID_TIMING.toString());
        }
        try {
            int ageGroup = Integer.parseInt(userInputAge);
            int pushupReps = Integer.parseInt(userInputPushUps);
            int situpReps = Integer.parseInt(userInputSitups);
            ipptCalculator = new IPPTCalc(ageGroup, userInputRunTime, pushupReps, situpReps);
        } catch (Exception e){
            throw new DukeError(e.getMessage());
        }
    }

    public void addIPPTSession(GenerateExercise exerciseGenerator, UserCareerData userCareerData,
                               Storage storage) throws DukeError{
        UserScore userScore = new UserScore();
        ArrayList<ExerciseData> exercises = exerciseGenerator.generateIPPTExercises(exerciseGenerator.generateSetAll());
        try {
            int runScore = ipptCalculator.calculateRun();
            int pushupScore = ipptCalculator.calculatePushup();
            int situpScore = ipptCalculator.calculateSitup();
            userScore.setRunScore(runScore);
            userScore.setPushupScore(pushupScore);
            userScore.setSitupScore(situpScore);
            IPPTSession ipptsession = new IPPTSession(exercises,userScore);
            userCareerData.addWorkoutSession(ipptsession);
            storage.writeToJson(userCareerData);
            System.out.println("We added your IPPT as a session");
            System.out.println("Your total score is " + userScore.getTotalScore());
        } catch (Exception e){
            throw new DukeError(e.getMessage());
        }
    }
}

