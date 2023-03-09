package seedu.pettracker.data;

public class Pet {
    protected String petName;
    protected String petType;
    protected String age;
    protected String weight;

    public Pet(String petName) {
        this.petName = petName;
        petType = "";
        age = "";
        weight = "";
    }
}
