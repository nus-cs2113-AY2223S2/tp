package seedu.duke.data.exercisegenerator.exersisedata;

import java.lang.reflect.Field;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import seedu.duke.commons.exceptions.DukeError;

public class ExerciseData {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("force")
    @Expose
    private String force;
    @SerializedName("level")
    @Expose
    private String level;
    @SerializedName("mechanic")
    @Expose
    private String mechanic;
    @SerializedName("equipment")
    @Expose
    private String equipment;
    @SerializedName("workoutType")
    @Expose
    private List<String> workoutType;
    @SerializedName("secondaryMuscles")
    @Expose
    private List<Object> secondaryMuscles;
    @SerializedName("instructions")
    @Expose
    private List<String> instructions;
    @SerializedName("category")
    @Expose
    private String category;

    public String getId () {
        return id;
    }

    //added setId for junit test.
    public void setId (String id) {
        this.id = id;
    }

    public String getName () {
        return name;
    }

    public void setName (String name) {
        this.name = name;
    }

    public String getForce () {
        return force;
    }

    public void setForce (String force) {
        this.force = force;
    }

    public String getLevel () {
        return level;
    }

    public void setLevel (String level) {
        this.level = level;
    }

    public String getMechanic () {
        return mechanic;
    }

    public void setMechanic (String mechanic) {
        this.mechanic = mechanic;
    }

    public String getEquipment () {
        return equipment;
    }

    public void setEquipment (String equipment) {
        this.equipment = equipment;
    }

    public List<String> getWorkoutType () {
        return workoutType;
    }

    public void setWorkoutType (List<String> workoutType) {
        this.workoutType = workoutType;
    }

    public List<Object> getSecondaryMuscles () {
        return secondaryMuscles;
    }

    public void setSecondaryMuscles (List<Object> secondaryMuscles) {
        this.secondaryMuscles = secondaryMuscles;
    }

    public List<String> getInstructions () {
        return instructions;
    }

    public void setInstructions (List<String> instructions) {
        this.instructions = instructions;
    }

    public String getCategory () {
        return category;
    }

    public void setCategory (String category) {
        this.category = category;
    }

    public boolean checkExerciseNullity () throws DukeError {
        for (Field f : getClass().getDeclaredFields()) {
            try {
                if (f.get(this) == null) {
                    return false;
                }
            } catch (Exception e) {
                throw new DukeError("Illegal access");
            }
        }
        return true;
    }

}

