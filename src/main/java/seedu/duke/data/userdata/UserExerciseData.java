package seedu.duke.data.userdata;

import java.util.HashMap;

public class UserExerciseData {

    private static final String OPEN_BRACE = "[";
    private static final String CLOSE_BRACE = "]";
    private static int overallCount;

    /**
     * This class takes in data from all workout sessions finished by
     * the user and stores these exercises as the key and their
     * frequencies as the value in a HashMap. It also calculates the
     * total number of exercises which the user has completed.
     *
     * @param userCareerData Contains data on all the user sessions completed by the user.
     * @return HashMap containing unique exercises and their frequencies of completion.
     */

    public static HashMap<String, Integer> addUserExerciseHistory (UserCareerData userCareerData) {
        assert userCareerData != null : "User career data should not be null!";
        HashMap<String, Integer> userExerciseDataMap = new HashMap<>();
        //Get the names of all exercises from each of the sessions, then
        // get their individual frequencies
        int totalSessionsArraySize = userCareerData.getTotalUserCareerSessions().size();
        for (int i = 0; i < totalSessionsArraySize; i++) {
            int indivSessionsArraySize = userCareerData.getTotalUserCareerSessions().
                                                       get(i).getSessionExercises().size();

            for (int j = 0; j < indivSessionsArraySize; j++) {
                String exerciseName = userCareerData.getTotalUserCareerSessions().
                                                    get(i).getSessionExercises().get(j).getName();
                String exerciseInstructions = userCareerData.getTotalUserCareerSessions().
                        get(i).getSessionExercises().get(j).getInstructions().toString();
                String exerciseDifficulty = userCareerData.getTotalUserCareerSessions().
                        get(i).getSessionExercises().get(j).getLevel();
                String exerciseWorkoutType = userCareerData.getTotalUserCareerSessions().
                        get(i).getSessionExercises().get(j).getWorkoutType().toString();
                int startInstructions = exerciseInstructions.indexOf(OPEN_BRACE);
                int endInstructions = exerciseInstructions.indexOf(CLOSE_BRACE);
                int startWorkoutType = exerciseWorkoutType.indexOf(OPEN_BRACE);
                int endWorkoutType = exerciseWorkoutType.indexOf(CLOSE_BRACE);
                exerciseInstructions = exerciseInstructions.substring(startInstructions+1,endInstructions);
                exerciseWorkoutType = exerciseWorkoutType.substring(startWorkoutType+1,endWorkoutType);

                String exerciseDescription = "Exercise Name: " + exerciseName +
                        System.lineSeparator() + "Difficulty Level: " + exerciseDifficulty +
                        System.lineSeparator() + "Workout type: " + exerciseWorkoutType +
                        System.lineSeparator() + exerciseInstructions;

                if (userExerciseDataMap.containsKey(exerciseDescription)) {
                    int count = userExerciseDataMap.get(exerciseDescription);
                    userExerciseDataMap.put(exerciseDescription, count + 1);
                    overallCount++;
                } else {
                    userExerciseDataMap.put(exerciseDescription, 1);
                    overallCount++;
                }
            }
        }
        return userExerciseDataMap;
    }


}
