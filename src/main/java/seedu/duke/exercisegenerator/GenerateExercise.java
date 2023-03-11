package seedu.duke.exercisegenerator;

import seedu.duke.exersisedata.ExerciseData;

import java.util.ArrayList;
import java.util.Random;

public class GenerateExercise {

    private ArrayList<ExerciseData> exerciseData = new ArrayList<>();

    public GenerateExercise() {
        ParseData parseData = new ParseData();
        exerciseData = parseData.getExercises();
    }

    public ArrayList<ExerciseData> generateRandomSet(int count){
        Random random = new Random();
        ArrayList<ExerciseData>randomExerciseList = new ArrayList<>();
        for(int i=0;i<count;i++) {
            ExerciseData randomExercise = exerciseData.get(random.nextInt(exerciseData.size()));
            randomExerciseList.add(randomExercise);
        }
        return randomExerciseList;
    }

    public ArrayList<ExerciseData> generateRandomSetFrom(ArrayList<ExerciseData>exerciseList, int count){
        Random random = new Random();
        ArrayList<ExerciseData>randomExerciseList = new ArrayList<>();
        for(int i=0;i<count;i++) {
            ExerciseData randomExercise = exerciseList.get(random.nextInt(exerciseList.size()));
            randomExerciseList.add(randomExercise);
        }
        return randomExerciseList;
    }

    public ArrayList<ExerciseData> generateFilteredGymSetFrom(ArrayList<ExerciseData>exerciseList){
        ArrayList<ExerciseData>randomExerciseList = new ArrayList<>();
        for(int i=0;i<exerciseList.size();i++) {
            ExerciseData Exercise = exerciseList.get(i);
            if (Exercise.getEquipment() != null && !Exercise.getEquipment().equals("body only")) {
                randomExerciseList.add(Exercise);
            }
        }
        return randomExerciseList;
    }

    public ArrayList<ExerciseData> generateFilteredBodySetFrom(ArrayList<ExerciseData>exerciseList){
        ArrayList<ExerciseData>randomExerciseList = new ArrayList<>();
        for(int i=0;i<exerciseList.size();i++) {
            ExerciseData Exercise = exerciseList.get(i);
            if (Exercise.getEquipment() != null && Exercise.getEquipment().equals("body only")) {
                randomExerciseList.add(Exercise);
            }
        }
        return randomExerciseList;
    }


    public ArrayList<ExerciseData> generateSetAll(){
        ArrayList<ExerciseData>allExerciseList = exerciseData;
        return allExerciseList;
    }


}
