package seedu.workout;


public class Exercise {
    private String name;
    private String weight;
    private String repsPerSet;

    //@@ author ZIZI-czh
    public Exercise(String name, String weight, String repsPerSet){
        this.name = name;
        this.weight = weight;
        this.repsPerSet = repsPerSet;
    }

    //@@ author guillaume-grn
    public int getSetsCount() {
        String repsPerSet = this.repsPerSet;
        String[] sets = repsPerSet.split(" ");
        return sets.length;
    }

    //@@ author guillaume-grn
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

    public String getName() {
        return name;
    }

    public String getWeight() {
        return weight;
    }

    public String getRepsPerSet() {
        return repsPerSet;
    }

    public void setRepsPerSet(String repsPerSet) {
        this.repsPerSet = repsPerSet;
    }

    @Override
    public String toString() {
        return name + ' ' + weight + ' ' + repsPerSet;
    }
}
