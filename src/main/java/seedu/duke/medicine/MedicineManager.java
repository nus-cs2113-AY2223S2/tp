package seedu.duke.medicine;

import seedu.duke.diagnosis.Diagnosis;
import seedu.duke.diagnosis.IllnessMatch;
import seedu.duke.diagnosis.symptoms.Symptom;

import java.util.Hashtable;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;
//@@author tanyizhe
/**
 * This class manages Medicines that will be prescribed to patients.
 */
public class MedicineManager {
    private static final Hashtable<String, ArrayList<Medicine>> medicationDict = new Hashtable<>();
    private static final Hashtable<String, String> medicineDosages = new Hashtable<>();
    private static final Medicine PARACETAMOL = new Medicine("Paracetamol", "1 or 2 pills up to 3 times a day");
    private static final Medicine LOZENGE = new Medicine("Lozenges","When you feel pain from sore throat");
    private static final Medicine ROBITUSSIN = new Medicine("Robitussin", "20ml every 12 hours");
    private static final Medicine IBUPROFEN = new Medicine("Ibuprofen",  "1 or 2 pills every 4 to 6 hours");
    private static final Medicine ASPIRIN = new Medicine("Aspirin", "1 or 2 pills every 4 to 6 hours");
    private static final Medicine MAGNESIUM = new Medicine("Magnesium", "100 to 350mg before bed");
    private static final Medicine EYE_DROPS = new Medicine("Eye Drops", "When your eyes itch");
    private static final Medicine ULTRACARBON = new Medicine("Ultracarbon", "1 250mg Tablet");
    private static final Medicine DULCOLAX = new Medicine("Dulcolax", "1 tablet every day");
    public MedicineManager() {
        initialiseMedications();
        initialiseMedicineDosages();
    }

    /**
     * This Method initialises the dictionary of Illnesses and Medications. The keys are the names of illnesses
     * and the values are an ArrayList of medications.
     */
    private void initialiseMedications () {
        ArrayList<Medicine> covidMedications = Stream.of(PARACETAMOL, LOZENGE, ROBITUSSIN)
                .collect(Collectors.toCollection(ArrayList::new));
        ArrayList<Medicine> commonFluMedications = Stream.of(IBUPROFEN, ASPIRIN, ROBITUSSIN)
                .collect(Collectors.toCollection(ArrayList::new));
        ArrayList<Medicine> migraineMedications = Stream.of(IBUPROFEN, ASPIRIN)
                .collect(Collectors.toCollection(ArrayList::new));
        ArrayList<Medicine> feverMedications = Stream.of(PARACETAMOL)
                .collect(Collectors.toCollection(ArrayList::new));
        ArrayList<Medicine> headacheMedications = Stream.of(PARACETAMOL)
                .collect(Collectors.toCollection(ArrayList::new));
        ArrayList<Medicine> insomniaMedications = Stream.of(MAGNESIUM)
                .collect(Collectors.toCollection(ArrayList::new));
        ArrayList<Medicine> conjunctivitisMedications = Stream.of(EYE_DROPS)
                .collect(Collectors.toCollection(ArrayList::new));
        ArrayList<Medicine> diarrhoeaMedications = Stream.of(ULTRACARBON)
                .collect(Collectors.toCollection(ArrayList::new));
        ArrayList<Medicine> constipationMedications = Stream.of(DULCOLAX)
                .collect(Collectors.toCollection(ArrayList::new));


        medicationDict.put("Covid-19", covidMedications);
        medicationDict.put("General Flu", commonFluMedications);
        medicationDict.put("Migraine", migraineMedications);
        medicationDict.put("Fever", feverMedications);
        medicationDict.put("Headache", headacheMedications);
        medicationDict.put("Insomnia", insomniaMedications);
        medicationDict.put("Conjunctivitis", conjunctivitisMedications);
        medicationDict.put("Diarrhoea", diarrhoeaMedications);
        medicationDict.put("Constipation", constipationMedications);
    }
    /**
     * This Method initialises the dictionary of Medications and their dosages.
     * The keys are the names of Medications and the values is a string that describes dosage.
     */
    public void initialiseMedicineDosages () {
        medicineDosages.put(PARACETAMOL.toString(), PARACETAMOL.getDosage());
        medicineDosages.put(ROBITUSSIN.toString(), ROBITUSSIN.getDosage());
        medicineDosages.put(LOZENGE.toString(), LOZENGE.getDosage());
        medicineDosages.put(ASPIRIN.toString(), ASPIRIN.getDosage());
        medicineDosages.put(IBUPROFEN.toString(), IBUPROFEN.getDosage());
        medicineDosages.put(MAGNESIUM.toString(), MAGNESIUM.getDosage());
        medicineDosages.put(EYE_DROPS.toString(), EYE_DROPS.getDosage());
        medicineDosages.put(ULTRACARBON.toString(), ULTRACARBON.getDosage());
        medicineDosages.put(DULCOLAX.toString(), DULCOLAX.getDosage());
        assert medicineDosages.isEmpty() == false : "Medicine dosage hashtable must not be empty";
    }

    /**
     * Analyses possible illnesses and outputs relevant medication and their dosages.
     */
    public ArrayList<IllnessMatch> analyseIllness (ArrayList<Symptom> symptoms) {
        ArrayList<IllnessMatch> possibleIllnesses = Diagnosis.getPossibleIllnesses(symptoms);
        for (IllnessMatch illnessMatch : possibleIllnesses) {
            System.out.println("Medication for: " + illnessMatch.getIllness().getIllnessName());
            ArrayList<Medicine> relevantMedications = getRelevantMedication(illnessMatch.getIllness().getIllnessName());
            printMedication(relevantMedications);
        }
        return possibleIllnesses;
    }

    /**
     * Prints medications if they are available over the counter. Otherwise, recommends patient to consult doctor.
     * @param relevantMedications ArrayList of medications suggested.
     */
    private static void printMedication(ArrayList<Medicine> relevantMedications) {
        if (relevantMedications != null) {
            for (Medicine medicine : relevantMedications) {
                System.out.println("    " + medicine.toString() + " - Dosage: " + medicine.getDosage());
            }
        } else {
            System.out.println("    No medication available. Please consult a doctor.");
        }
    }
    //@@author tanyizhe
    /**
     * Prescribes appropriate medications to patients.
     * @param illness Name of illness diagnosed by Dr. Duke.
     * @return ArrayList of prescribed medications.
     */
    public ArrayList<Medicine> getRelevantMedication (String illness) {
        return medicationDict.get(illness);
    }
    public ArrayList<String> getRelevantMedicationInString (String illness) {
        ArrayList<String> medicineList = new ArrayList<>();
        if (medicationDict.containsKey(illness)) {
            ArrayList<Medicine> medicineArrayList = medicationDict.get(illness);
            for(Medicine medicine : medicineArrayList) {
                medicineList.add(medicine.toString());
            }
            return medicineList;
        } else {
            assert medicineList.size() == 0 : "No medicines available";
            return null;
        }

    }
    /**
     * Gets dosage for medicine.
     * @param name name of medication
     * @return String specifying dosage of specified medication
     */
    public String getMedicineDosages (String name) {
        return medicineDosages.get(name);
    }
}
