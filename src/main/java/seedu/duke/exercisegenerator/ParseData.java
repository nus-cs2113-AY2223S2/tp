package seedu.duke.exercisegenerator;

import com.google.gson.Gson;
import seedu.duke.exersisedata.ExerciseData;
import seedu.duke.exersisedata.ExerciseDataList;


import java.util.ArrayList;
import java.io.FileReader;
import java.io.Reader;

public class ParseData{
    private static final String FILE_PATH = "ExerciseDataset.json";
    public ArrayList<ExerciseData> getExercises(){
        Gson gson = new Gson();
        try {
            Reader reader =new FileReader(FILE_PATH); //reads the database
            ExerciseDataList exerciseDataList = gson.fromJson(reader, ExerciseDataList.class);
            ArrayList<ExerciseData> exerciseData = exerciseDataList.exercises; //gets array of exercises
            return exerciseData;

        }catch (Exception e){
            System.out.println(e);
        }
        return null;
    }
}
