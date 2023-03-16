package seedu.pettracker.data;

public class Pet {
    private String petName;
    private String petType;
    private String age;
    private String weight;

    public Pet(String petName) {
        this.petName = petName;
        petType = "";
        assert petType.equals("") : "petType should be an empty string when a pet is added";
        age = "";
        assert age.equals("") : "age should be an empty string when a pet is added";
        weight = "";
        assert weight.equals("") : "weight should be an empty string when a pet is added";
    }

    public void addStat(String statName, String statValue) {
        assert statName != null && statValue != null : "statName/statValue is null";
        switch (statName.toLowerCase()) {
        case "type":
            setPetType(statValue);
            break;
        case "age":
            setAge(statValue);
            break;
        case "weight":
            setWeight(statValue);
            break;
        default:
            System.out.println("ERROR: The only valid stats are type, age, or weight.");
        }

    }

    public void removeStat(String statName) {
        assert statName != null : "statName is null";
        switch (statName.toLowerCase()) {
        case "type":
            setPetType("");
            break;
        case "age":
            setAge("");
            break;
        case "weight":
            setWeight("");
            break;
        default:
            System.out.println("ERROR: The only valid stats are type, age, or weight.");
        }
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

    /**
     * List out a custom formatted string of Pet containing the pet name and details
     * when using the list pet command.
     *
     * @return Pet name and details in a custom formatted string.
     */
    @Override
    public String toString() {
        return getPetName() + "\nType: " + getPetType() + "\nAge: " + getAge() + "\nWeight: " + getWeight();
    }
}
