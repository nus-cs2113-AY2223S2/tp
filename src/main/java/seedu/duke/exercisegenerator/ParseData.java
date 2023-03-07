package seedu.duke.exercisegenerator;


import com.google.gson.Gson;
import seedu.duke.exersisedata.ExerciseData;
import seedu.duke.exersisedata.ExerciseDataList;


import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;

public class ParseData{

    public ArrayList<ExerciseData> getExercises(){
        try (Reader reader = new InputStreamReader(this.getClass()
                .getResourceAsStream("/data.json"))) {
            ExerciseDataList result = new Gson().fromJson(reader, ExerciseDataList.class);
            ArrayList<ExerciseData> exerciseData = result.exercises;
            return exerciseData;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
