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

    public String getPetName() {
        return petName;
    }

    public String getPetType() {
        return petType;
    }

    public void setPetType(String petType) {
        this.petType = petType;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return getPetName() + "\nType: " +  getPetType() + "\nAge: " + getAge() + "\nWeight: " + getWeight();
    }
}
