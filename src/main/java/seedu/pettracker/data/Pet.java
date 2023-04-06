package seedu.pettracker.data;

import seedu.pettracker.exceptions.EmptyStatException;
import seedu.pettracker.exceptions.InvalidStatException;
import seedu.pettracker.exceptions.NonPositiveIntegerException;

public class Pet {
    private String petName;
    private String petType;
    private String age;
    private String weight;

    public Pet(String petName) {
        this.petName = petName;
        petType = "";
        age = "";
        weight = "";
        assert petType.equals("") && age.equals("") && weight.equals("") :
                "petType/age/weight should be an empty string when a pet is added";
    }

    /**
     * Add Stat to a pet
     *
     * @param statName  Stat Name to add
     * @param statValue Stat Value to add
     * @throws NonPositiveIntegerException When stat is Age/Weight and is non-positive
     * @throws NumberFormatException       When stat is Age/Weight and is not a number
     * @throws InvalidStatException        When stat is not Type/Age/Weight
     */
    public void addStat(String statName, String statValue) throws NonPositiveIntegerException,
            NumberFormatException, InvalidStatException {
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
            throw new InvalidStatException();
        }

    }

    /**
     * Removes a stat from a pet
     *
     * @param statName Stat Name to add
     * @throws InvalidStatException When stat is not Type/Age/Weight
     */
    public void removeStat(String statName) throws InvalidStatException {
        // assert statName != null : "statName is null";
        if (statName == null) {
            throw new InvalidStatException();
        } else {
            switch (statName.toLowerCase()) {
            case "type":
                setPetType("");
                break;
            case "age":
                removeAge();
                break;
            case "weight":
                removeWeight();
                break;
            default:
                throw new InvalidStatException();
            }
        }
    }

    private void removeAge() {
        this.age = "";
    }

    private void removeWeight() {
        this.weight = "";
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
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

    /**
     * Set the pet Age
     *
     * @param age String of age to set to
     * @throws NonPositiveIntegerException When stat is non-positive
     * @throws NumberFormatException       When stat is not a number
     */
    public void setAge(String age) throws NonPositiveIntegerException, NumberFormatException {
        int ageInt = Integer.parseInt(age);
        if (ageInt <= 0) {
            throw new NonPositiveIntegerException();
        }
        this.age = age;
    }

    public String getWeight() {
        return weight;
    }

    /**
     * Set the pet Weight
     *
     * @param weight String of weight to set to
     * @throws NonPositiveIntegerException When stat is non-positive
     * @throws NumberFormatException       When stat is not a number
     */
    public void setWeight(String weight) throws NonPositiveIntegerException, NumberFormatException {
        int weightInt = Integer.parseInt(weight);
        if (weightInt <= 0) {
            throw new NonPositiveIntegerException();
        }
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

    /**
     * Converts a pet to a formatted string to save into the output file
     *
     * @return String that contains pet name, type, age and weight seperated by pipes
     */
    public String saveFormat() {
        return getPetName() + "|" + getPetType() + "|" + getAge() + "|" + getWeight();
    }
}
