package seedu.duke.data.exercisegenerator;

import com.google.gson.Gson;

import seedu.duke.data.exercisegenerator.exersisedata.ExerciseData;
import seedu.duke.data.exercisegenerator.exersisedata.ExerciseDataList;

import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Objects;

//@@author EangJS
public class LoadExerciseJson {

    public ArrayList<ExerciseData> getExercises () {
        ArrayList<ExerciseData> exerciseData = new ArrayList<>();
        try (Reader reader = new InputStreamReader(Objects.requireNonNull(this.getClass()
                                                                              .getResourceAsStream("/data.json")))) {
            ExerciseDataList result = new Gson().fromJson(reader, ExerciseDataList.class);
            exerciseData = result.exercises;
            assert exerciseData != null : "exercise data must contain data if resource file is present";
            return exerciseData;

        } catch (Exception e) {
            System.out.println("Unable to read exercise data from resources file!");
        }
        return exerciseData;
    }

}
