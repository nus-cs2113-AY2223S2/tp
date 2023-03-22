package seedu.duke.medicine;
//@@author tanyizhe
public class Medicine {
    private String name;
    private String dosage;
    public Medicine(String name, String dosage) {
        this.name = name;
        this.dosage = dosage;
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

}
