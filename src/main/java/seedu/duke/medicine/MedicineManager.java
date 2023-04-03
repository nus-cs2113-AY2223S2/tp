package seedu.duke.medicine;

import seedu.duke.diagnosis.Diagnosis;
import seedu.duke.diagnosis.IllnessMatch;
import seedu.duke.diagnosis.symptoms.Symptom;
import seedu.duke.ui.Menu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
//@@author tanyizhe

/**
 * This class manages Medicines that will be prescribed to patients.
 */
public class MedicineManager {
    private static final Hashtable<String, ArrayList<Medicine>> medicationDict = new Hashtable<>();
    private static final Hashtable<String, String> medicineDosages = new Hashtable<>();
    private static final Medicine PARACETAMOL = new Medicine(
            "Paracetamol", "1 or 2 pills up to 3 times a day",
            //@@author Geeeetyx
            "Description: Paracetamol is a commonly used medicine that can help treat pain and reduce a " +
                    "high temperature \n" +
                    "    It's typically used to relieve mild or moderate pain, such as headaches, \n" +
                    "    toothache or sprains, and reduce fevers caused by illnesses such as colds and flu.");
    //@@author tanyizhe

    private static final Medicine LOZENGE = new Medicine(
            "Lozenges", "When you feel pain from sore throat",
            //@@author Geeeetyx
            "Description: Lozenges are used to medicate the mouth and throat for the slow administration " +
                    "in digestion or \n" +
                    "    cough remedies. Lozenges may contain an anesthetic, a demulcent, or an antiseptic.");
    //@@author tanyizhe

    private static final Medicine ROBITUSSIN = new Medicine(
            "Robitussin", "20ml every 12 hours",
            //@@author Geeeetyx
            "Description: Robitussin is an expectorant. It helps loosen congestion in your chest and throat, \n" +
                    "    making it easier to cough out through your mouth. \n" +
                    "    Robitussin is used to reduce chest congestion caused by the common cold, \n" +
                    "    infections, or allergies.");
    //@@author tanyizhe

    private static final Medicine IBUPROFEN = new Medicine(
            "Ibuprofen", "1 or 2 pills every 4 to 6 hours",
            //@@author Geeeetyx
            "Description: Ibuprofen eases mild to moderate pain – such as toothache, migraine and period pain, \n" +
                    "    control a fever (high temperature) – for example, when someone has the flu (influenza), \n" +
                    "    eases pain and inflammation (redness and swelling) caused by conditions that affect the \n" +
                    "    joints, bones and muscles - such as rheumatoid arthritis and osteoarthritis and eases \n" +
                    "    pain and swelling caused by sprains and strains – such as sports injuries");
    //@@author tanyizhe

    private static final Medicine ASPIRIN = new Medicine(
            "Aspirin", "1 or 2 pills every 4 to 6 hours",
            //@@author Geeeetyx
            "Description: A drug that reduces pain, fever, inflammation, and blood clotting.");
    //@@author tanyizhe
    private static final Medicine MAGNESIUM = new Medicine(
            "Magnesium", "100 to 350mg before bed",
            //@@author Geeeetyx
            "Description: Magnesium is a mineral that is important for normal bone structure in the body. \n" +
                    "    People get magnesium from their diet, but sometimes magnesium supplements are needed if \n" +
                    "    magnesium levels are too low. \n" +
                    "    Magnesium is most commonly used for constipation, as an antacid for heartburn, \n" +
                    "    for low magnesium levels, \n" +
                    "    for pregnancy complications called pre-eclampsia and eclampsia, \n" +
                    "    and for a certain type of irregular heartbeat.");
    //@@author tanyizhe

    private static final Medicine EYE_DROPS = new Medicine(
            "Eye Drops", "When your eyes itch",
            //@@author Geeeetyx
            "Description: Eye Drops are a decongestant used to relieve redness in the eyes caused by " +
                    "minor eye irritations \n" +
                    "    (such as smog, swimming, dust, or smoke).");
    //@@author tanyizhe

    private static final Medicine ULTRACARBON = new Medicine(
            "Ultracarbon", "1 250mg Tablet",
            //@@author Geeeetyx
            "Description: Ultracarbon is thought to offer several benefits, including less gas and flatulence, \n" +
                    "    and is thus administered to treat diarrhoea.");
    //@@author tanyizhe

    private static final Medicine DULCOLAX = new Medicine(
            "Dulcolax", "1 tablet every day",
            //@@author Geeeetyx
            "Description: Dulcolax is used to treat constipation. \n" +
                    "Swallow this medication whole. Do not crush, chew, or break the tablet or take it within \n" +
                    "    1 hour of antacids, milk, or milk products. Doing so can destroy the \n" +
                    "    coating on the tablet and may increase the risk of stomach upset and nausea.");
    //@@author tanyizhe
    private static final Medicine GUAIFENESIN = new Medicine(
            "Guaifenesin", "200-400 mg 4 hourly",

            //@@author Geeeetyx
            "Description: Guaifenesin is used to relieve chest congestion. \n" +
                    "    It works by thinning the mucus in the air passages to make it easier to \n" +
                    "    cough up the mucus and clear the airways.");

    private static final Medicine ZOLPIDEM = new Medicine(
            "Zolpidem", "taking it once every 2 or 3 nights",
            "Description: Zolpidem is used for short-term treatment of " +
                    "insomnia (difficulty sleeping). \n" +
                    "    It helps you fall asleep faster and sleep through the night.");

    private static final Medicine DIPHENOXYLATE = new Medicine(
            "Diphenoxylate", "2 tablets 3-4 times daily",
            "Description: Diphenoxylate works by decreasing activity of the bowel, " +
                    "for the treatment of diarrhea.");

    private static final Medicine LACTULOSE = new Medicine(
            "Lactulose", "15ml twice a day",
            "Description: This medication is used to treat constipation. \n" +
                    "    It encourages bowel movement by drawing water into the bowel. \n" +
                    "    This helps to soften the stools and increase bowel movement.");

    private static final Medicine DECONGESTANT_SPRAY = new Medicine(
            "Decongestant", "1 to 4 times a day",
            "Description: Decongestants shrink the blood vessels (or decongest them) in the nose, \n" +
                    "    reducing the swelling and allowing easier breathing. \n" +
                    "    Use the spray for 2 - 3 days and then wait 2 - 3 days before using the spray again.");

    private static final Medicine ZYRTEC_D = new Medicine(
            "Antihistamine", "1 5mg tablet every 12 hours",
            "Description: ZYRTEC-D® contains both an antihistamine and a decongestant for allergies. \n" +
                    "    It clears your blocked nose and relieves other allergy symptoms.");

    private static final Medicine DIFFLAM_THROAT_SPRAY = new Medicine(
            "Difflam Throat Spray", "2 - 4 times every 1.5 - 3 hours until you feel better",
            "Description: This spray offers targeted and rapid symptom relief for " +
                    "hard-to-reach inflamed and \n" +
                    "    painful areas in your mouth and throat. It provides rapid relief from local inflammation \n" +
                    "    and pain from 60 seconds. ");

    private static final Medicine NAPROXEN = new Medicine(
            "Naproxen", "Dissolove 1-2 tablets in water, take for 1-2 days ONLY and only after meals",
            "Description: Naproxen is a non-steroidal anti-inflammatory drug (NSAID). \n" +
                    "    It reduces swelling (inflammation) and pain in joints and muscles, \n" +
                    "    which can help with muscle aches.");


    //@@author tanyizhe
    public MedicineManager() {
        initialiseMedications();
        initialiseMedicineDosages();
    }

    /**
     * This Method initialises the dictionary of Illnesses and Medications. The keys are the names of illnesses
     * and the values are an ArrayList of medications.
     */
    private void initialiseMedications() {
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
        ArrayList<Medicine> insomniaMedications = Stream.of(MAGNESIUM, ZOLPIDEM)
                .collect(Collectors.toCollection(ArrayList::new));
        ArrayList<Medicine> conjunctivitisMedications = Stream.of(EYE_DROPS)
                .collect(Collectors.toCollection(ArrayList::new));
        ArrayList<Medicine> diarrhoeaMedications = Stream.of(ULTRACARBON, DIPHENOXYLATE)
                .collect(Collectors.toCollection(ArrayList::new));
        ArrayList<Medicine> constipationMedications = Stream.of(DULCOLAX, LACTULOSE)
                .collect(Collectors.toCollection(ArrayList::new));
        ArrayList<Medicine> soreThroatMedications = Stream.of(LOZENGE, DIFFLAM_THROAT_SPRAY)
                .collect(Collectors.toCollection(ArrayList::new));
        ArrayList<Medicine> bronchitisMedications = Stream.of(GUAIFENESIN, IBUPROFEN)
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
        medicationDict.put("Sore Throat", soreThroatMedications);
        medicationDict.put("Bronchitis", bronchitisMedications);

        //@@author Geeeetyx
        ArrayList<Medicine> nasalCongestionMedications = Stream.of(DECONGESTANT_SPRAY, ZYRTEC_D)
                .collect(Collectors.toCollection(ArrayList::new));
        ArrayList<Medicine> muscleAcheMedications = Stream.of(NAPROXEN)
                        .collect(Collectors.toCollection(ArrayList::new));
        medicationDict.put("Nasal Congestion", nasalCongestionMedications);
        medicationDict.put("Muscle Ache", muscleAcheMedications);
        //@@author tanyizhe
    }

    /**
     * This Method initialises the dictionary of Medications and their dosages.
     * The keys are the names of Medications and the values is a string that describes dosage.
     */
    public void initialiseMedicineDosages() {
        medicineDosages.put(PARACETAMOL.toString(), PARACETAMOL.getDosage());
        medicineDosages.put(ROBITUSSIN.toString(), ROBITUSSIN.getDosage());
        medicineDosages.put(LOZENGE.toString(), LOZENGE.getDosage());
        medicineDosages.put(ASPIRIN.toString(), ASPIRIN.getDosage());
        medicineDosages.put(IBUPROFEN.toString(), IBUPROFEN.getDosage());
        medicineDosages.put(MAGNESIUM.toString(), MAGNESIUM.getDosage());
        medicineDosages.put(EYE_DROPS.toString(), EYE_DROPS.getDosage());
        medicineDosages.put(ULTRACARBON.toString(), ULTRACARBON.getDosage());
        medicineDosages.put(DULCOLAX.toString(), DULCOLAX.getDosage());
        medicineDosages.put(GUAIFENESIN.toString(), GUAIFENESIN.getDosage());

        //@@author Geeeetyx
        medicineDosages.put(ZOLPIDEM.toString(), ZOLPIDEM.getDosage());
        medicineDosages.put(DIPHENOXYLATE.toString(), DIPHENOXYLATE.getDosage());
        medicineDosages.put(LACTULOSE.toString(), LACTULOSE.getDosage());
        medicineDosages.put(DECONGESTANT_SPRAY.toString(), DECONGESTANT_SPRAY.getDosage());
        medicineDosages.put(ZYRTEC_D.toString(), ZYRTEC_D.getDosage());
        medicineDosages.put(DIFFLAM_THROAT_SPRAY.toString(), DIFFLAM_THROAT_SPRAY.getDosage());
        medicineDosages.put(NAPROXEN.toString(),NAPROXEN.getDosage());
        //@@author tanyizhe

        assert medicineDosages.isEmpty() == false : "Medicine dosage hashtable must not be empty";
    }

    /**
     * Analyses possible illnesses and outputs relevant medication and their dosages.
     */
    public ArrayList<IllnessMatch> analyseIllness(ArrayList<Symptom> symptoms) {
        ArrayList<IllnessMatch> possibleIllnesses = Diagnosis.getPossibleIllnesses(symptoms);
        for (IllnessMatch illnessMatch : possibleIllnesses) {
            System.out.println("Medication for: " + illnessMatch.getIllness().getIllnessName());
            ArrayList<Medicine> relevantMedications = getRelevantMedication(illnessMatch.getIllness().getIllnessName());
            printMedication(relevantMedications);
        }

        Menu.displayEndOfDiagnosisMessage();
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
                //@@author Geeeetyx
                System.out.println("-----------------------------------------------------------");
                System.out.println("    " + medicine.getDescription());
                System.out.println("-----------------------------------------------------------");
                //@@author tanyizhe
            }
        } else {
            System.out.println("    No medication available. Please consult a doctor.");
            System.out.println("-----------------------------------------------------------");
        }
    }
    //@@author tanyizhe

    /**
     * Prescribes appropriate medications to patients.
     * @param illness Name of illness diagnosed by Dr. Duke.
     * @return ArrayList of prescribed medications.
     */
    public ArrayList<Medicine> getRelevantMedication(String illness) {
        return medicationDict.get(illness);
    }

    public ArrayList<String> getRelevantMedicationInString(String illness) {
        ArrayList<String> medicineList = new ArrayList<>();
        if (medicationDict.containsKey(illness)) {
            ArrayList<Medicine> medicineArrayList = medicationDict.get(illness);
            for (Medicine medicine : medicineArrayList) {
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
    public String getMedicineDosages(String name) {
        return medicineDosages.get(name);
    }

    /**
     * List all medicines available.
     */
    public void listMedicines() {
        System.out.println("---------------------------------------------------");
        System.out.println("List of available medications:");
        List<String> medicationKeys = Collections.list(medicineDosages.keys());
        Collections.sort(medicationKeys);
        for (String medicationKey : medicationKeys) {
            System.out.println(medicationKey);
        }
    }
    /**
     * Looks for medicine using a keyword or phrase.
     * @param phrase String user has inputted to find medicine.
     */
    public void findMedicine(String phrase) {
        List<String> medicationKeys = Collections.list(medicineDosages.keys());
        Collections.sort(medicationKeys);
        int count = 0;
        for (String medicationKey : medicationKeys) {
            if (medicationKey.toLowerCase().contains(phrase.toLowerCase())) {
                System.out.println(medicationKey);
                count++;
            }
        }
        if (count == 0) {
            System.out.println("No results found.");
        }
    }

    //@@author Thunderdragon221
    public boolean isValidMedicine(String medicine) {
        return medicineDosages.containsKey(medicine);
    }
}
