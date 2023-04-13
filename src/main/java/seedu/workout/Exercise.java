package seedu.workout;

//@@author calebcjl
/**
 * Represents an exercise. It stores the name, weight used and the number of sets and reps.
 */
public class Exercise {
    private final String name;
    private final String weight;
    private final String repsPerSet;

    public Exercise(String name, String weight, String repsPerSet) {
        this.name = name;
        this.weight = weight;
        this.repsPerSet = repsPerSet;
    }

    public String getName() {
        return name;
    }

    public String getWeight() {
        return weight;
    }

    public String getRepsPerSet() {
        return repsPerSet;
    }

    @Override
    public String toString() {
        return name + ' ' + weight + ' ' + repsPerSet;
    }

    //@@author guillaume-grn
    /**
     * Returns the number of sets for the exercise.
     *
     * @return the number of sets for the exercise
     */
    public int getSetsCount() {
        String repsPerSet = this.repsPerSet;
        String[] sets = repsPerSet.split(" ");
        return sets.length;
    }

    //@@author guillaume-grn
    /**
     * Returns the total number of reps for the exercise.
     *
     * @return the total number of reps for the exercise
     */
    public int getRepsCount() {
        String repsPerSet = this.repsPerSet;
        String[] sets = repsPerSet.split(" ");
        int totalReps = 0;
        for (String set : sets) {
            try {
                totalReps += Integer.parseInt(set);
            } catch (NumberFormatException e) {
                System.out.println("Invalid reps format:" + repsPerSet);
                return 0;
            }
        }
        return totalReps;
    }

}
