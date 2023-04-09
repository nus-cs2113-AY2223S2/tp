package seedu.duke.diagnosis;
//@@author Brennanzuz
import seedu.duke.diagnosis.illnesses.Illness;
import seedu.duke.diagnosis.illnesses.allergic.Asthma;
import seedu.duke.diagnosis.illnesses.allergic.GeneralAllergicReaction;
import seedu.duke.diagnosis.illnesses.bacterial.Bronchitis;
import seedu.duke.diagnosis.illnesses.bacterial.GeneralBacterialInfection;
import seedu.duke.diagnosis.illnesses.bacterial.Pneumonia;
import seedu.duke.diagnosis.illnesses.bacterial.SoreThroat;
import seedu.duke.diagnosis.illnesses.bacterial.Tuberculosis;
import seedu.duke.diagnosis.illnesses.cardiovascular.CoronaryHeartDisease;
import seedu.duke.diagnosis.illnesses.cardiovascular.Hypertension;
import seedu.duke.diagnosis.illnesses.dental.GeneralToothAche;
import seedu.duke.diagnosis.illnesses.dental.ToothDecay;
import seedu.duke.diagnosis.illnesses.dermatological.GeneralRash;
import seedu.duke.diagnosis.illnesses.dermatological.Hives;
import seedu.duke.diagnosis.illnesses.dermatological.Psoriasis;
import seedu.duke.diagnosis.illnesses.digestive.Constipation;
import seedu.duke.diagnosis.illnesses.digestive.Diarrhoea;
import seedu.duke.diagnosis.illnesses.digestive.FoodPoisoning;
import seedu.duke.diagnosis.illnesses.digestive.GeneralStomachAche;
import seedu.duke.diagnosis.illnesses.digestive.Hemorrhoids;
import seedu.duke.diagnosis.illnesses.immunological.Diabetes;
import seedu.duke.diagnosis.illnesses.immunological.GeneralFever;
import seedu.duke.diagnosis.illnesses.immunological.HumanImmunodeficiencyVirus;
import seedu.duke.diagnosis.illnesses.musculoskeletal.Arthritis;
import seedu.duke.diagnosis.illnesses.musculoskeletal.GeneralBackAche;
import seedu.duke.diagnosis.illnesses.musculoskeletal.GeneralMuscleAche;
import seedu.duke.diagnosis.illnesses.musculoskeletal.Osteoporosis;
import seedu.duke.diagnosis.illnesses.neurological.GeneralHeadAche;
import seedu.duke.diagnosis.illnesses.neurological.Migraine;
import seedu.duke.diagnosis.illnesses.physical.Fracture;
import seedu.duke.diagnosis.illnesses.physical.InternalBleeding;
import seedu.duke.diagnosis.illnesses.physical.SuperficialWound;
import seedu.duke.diagnosis.illnesses.psychological.AttentionDeficitHyperactivityDisorder;
import seedu.duke.diagnosis.illnesses.psychological.Depression;
import seedu.duke.diagnosis.illnesses.psychological.Insomnia;
import seedu.duke.diagnosis.illnesses.viral.Conjunctivitis;
import seedu.duke.diagnosis.illnesses.viral.Coronavirus;
import seedu.duke.diagnosis.illnesses.viral.Dengue;
import seedu.duke.diagnosis.illnesses.viral.GeneralCold;
import seedu.duke.diagnosis.illnesses.viral.GeneralFlu;
import seedu.duke.diagnosis.symptoms.Symptom;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.util.stream.Collectors.toCollection;
/**
 * This class conducts the diagnosis by taking in the patient's symptoms and returning the most probable illnesses based
 * on the percentage of overlapping symptoms the patient has with each suspected illness, and based on a predefined
 * threshold.
 *
 * @author Brennanzuz
 */
public class Diagnosis {
    private static final double POSSIBILITY_THRESHOLD = 0.5;
    private static final ArrayList<Illness> ALL_ILLNESSES = new ArrayList<>(
            List.of(
                    new Asthma(),
                    new GeneralAllergicReaction(),
                    new Bronchitis(),
                    new GeneralBacterialInfection(),
                    new Pneumonia(),
                    new SoreThroat(),
                    new Tuberculosis(),
                    new CoronaryHeartDisease(),
                    new Hypertension(),
                    new GeneralToothAche(),
                    new ToothDecay(),
                    new GeneralRash(),
                    new Hives(),
                    new Psoriasis(),
                    new Constipation(),
                    new Diarrhoea(),
                    new FoodPoisoning(),
                    new GeneralStomachAche(),
                    new Hemorrhoids(),
                    new Diabetes(),
                    new GeneralFever(),
                    new HumanImmunodeficiencyVirus(),
                    new Arthritis(),
                    new GeneralBackAche(),
                    new GeneralMuscleAche(),
                    new Osteoporosis(),
                    new GeneralHeadAche(),
                    new Osteoporosis(),
                    new Migraine(),
                    new Fracture(),
                    new InternalBleeding(),
                    new SuperficialWound(),
                    new AttentionDeficitHyperactivityDisorder(),
                    new Depression(),
                    new Insomnia(),
                    new Conjunctivitis(),
                    new Coronavirus(),
                    new Dengue(),
                    new GeneralCold(),
                    new GeneralFlu()
            )
    );
    private static Logger diagnosisLogger = Logger.getLogger("diagnosisLogger");

    /**
     * @author Brennanzuz
     * @param patientSymptoms The ArrayList of Symptoms the patient has indicated to have.
     *                        Passed from UI.
     * @param illnessSymptoms The ArrayList of Symptoms the suspected Illness has.
     *                        To be matched with that of the patient
     * @return An ArrayList of Symptoms that is the intersection between the arguments,
     *         or simply put, the symptoms both the patient and the suspected Illness at hand has.
     */
    private static ArrayList<Symptom> getMatchingSymptoms(ArrayList<Symptom> patientSymptoms,
                                                          ArrayList<Symptom> illnessSymptoms) {
        return illnessSymptoms.stream()
                .filter(patientSymptoms::contains)
                .collect(toCollection(ArrayList::new));
    }

    /**
     * @author Brennanzuz
     * @param patientSymptoms The ArrayList of Symptoms the patient has indicated to have. Passed from UI.
     * @return An ArrayList of IllnessMatch which indicates the most probable Illness and its similarity percentage.
     */
    public static ArrayList<IllnessMatch> getPossibleIllnesses(ArrayList<Symptom> patientSymptoms) {
        diagnosisLogger.setLevel(Level.SEVERE);
        diagnosisLogger.log(Level.INFO, "Receiving patient symptoms for diagnosis.");
        ArrayList<IllnessMatch> possibleIllnesses = new ArrayList<>();
        IllnessMatch illnessMatch;
        for (Illness illness : ALL_ILLNESSES) {
            assert illness.getSymptoms().size() > 0 : illness.getIllnessName() + " should have symptoms listed";
            illnessMatch = new IllnessMatch(illness, (double) getMatchingSymptoms(patientSymptoms,
                    illness.getSymptoms()).size() / illness.getSymptoms().size());
            diagnosisLogger.log(Level.INFO, "Compared patient's symptoms to those of " +
                    illnessMatch.getIllness().getIllnessName() +
                    " and found a " + illnessMatch.getSimilarityPercentage() * 100 +
                    "% match.");
            possibleIllnesses.add(illnessMatch);
        }
        return possibleIllnesses.stream()
                .filter(possibleIllness -> possibleIllness.getSimilarityPercentage() > POSSIBILITY_THRESHOLD)
                .collect(toCollection(ArrayList::new));
    }

    //@@author Thunderdragon221
    public static boolean isValidDiagnosis(String diagnosis) {
        boolean isValid = false;

        for (Illness illness : ALL_ILLNESSES) {
            if (illness.getIllnessName().equals(diagnosis)) {
                isValid = true;
            }
        }

        return isValid;
    }
}
