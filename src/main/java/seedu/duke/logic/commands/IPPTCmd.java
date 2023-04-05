package seedu.duke.logic.commands;


import seedu.duke.data.exercisegenerator.GenerateExercise;
import seedu.duke.data.exercisegenerator.exersisedata.ExerciseData;
import seedu.duke.data.ipptcalculator.IPPTCalc;
import seedu.duke.data.ipptcalculator.UserScore;
import seedu.duke.data.userdata.IPPTSession;
import seedu.duke.data.userdata.UserCareerData;
import seedu.duke.storage.Storage;

import java.util.ArrayList;


public class IPPTCmd{
    private IPPTCalc ipptCalculator;
    // pass in input : IPPT [age] [runtime] [pushup score] [situp score]
    public IPPTCmd(String[] userCommands){
        int ageGroup = Integer.parseInt(userCommands[0]);
        String userRunTimeInput = userCommands[1];
        int pushupReps = Integer.parseInt(userCommands[2]);
        int situpReps = Integer.parseInt(userCommands[3]);
        try {
            ipptCalculator = new IPPTCalc(ageGroup, userRunTimeInput, pushupReps, situpReps);
        } catch (Exception e){
            System.out.println("Failed to load ippt scores from database");
        }
    }

    public void addIPPTSession(GenerateExercise exerciseGenerator, UserCareerData userCareerData,
                               Storage storage){
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
        } catch (Exception e){
            System.out.println("Oops something went wrong adding your IPPT data");
        }
    }
}

