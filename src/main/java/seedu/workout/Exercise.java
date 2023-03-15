package seedu.workout;

public class Exercise {

    private String name;
    private String weight;
    private String repsPerSet;

    public Exercise(String name, String weight, String repsPerSet){
        this.name = name;
        this.weight = weight;
        this.repsPerSet = repsPerSet;
    }

    @Override
    public String toString() {
        return name + ' ' + weight + ' ' + repsPerSet;
    }
}
