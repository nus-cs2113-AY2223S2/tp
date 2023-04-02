package seedu.duke.medicine;
//@@author tanyizhe
public class Medicine {
    private String name;
    private String dosage;

    //@@author Geeeetyx
    private String medicineDescription;
    //@@author tanyizhe

    public Medicine(String name, String dosage, String medicineDescription) {
        this.name = name;
        this.dosage = dosage;
        this.medicineDescription = medicineDescription;
    }

    /**
     * Accesses dosage of medication
     * @return dosage of medication
     */
    public String getDosage() {
        return dosage;
    }
    @Override
    public String toString() {
        return name;
    }


    //@@author Geeeetyx
    public String getDescription() {
        return medicineDescription;
    }
    //@@author
}
