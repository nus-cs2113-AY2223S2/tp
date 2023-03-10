package seedu.duke.diagnosis;

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
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toCollection;

//@@author Brennanzuz
public class Diagnosis {
    private final static double POSSIBILITY_THRESHOLD = 0.5;
    private final static ArrayList<Illness> allIllnesses = new ArrayList<>(
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
                    new GeneralMuscleAche(),
                    new Osteoporosis(),
                    new GeneralHeadAche(),
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

    private static ArrayList<Symptom> getMatchingSymptoms(ArrayList<Symptom> patientSymptoms, ArrayList<Symptom> illnessSymptoms) {
        return illnessSymptoms.stream()
                .filter(patientSymptoms::contains)
                .collect(toCollection(ArrayList::new));
    }

    public static ArrayList<IllnessMatch> getPossibleIllnesses(ArrayList<Symptom> patientSymptoms) {
        ArrayList<IllnessMatch> possibleIllnesses = new ArrayList<>();
        for (Illness illness : allIllnesses) {
            possibleIllnesses.add(new IllnessMatch(illness, (double)getMatchingSymptoms(patientSymptoms,
                    illness.getSymptoms()).size()/illness.getSymptoms().size()));
        }
        return possibleIllnesses.stream()
                .filter(possibleIllness -> possibleIllness.getSimilarityPercentage() > POSSIBILITY_THRESHOLD)
                .collect(toCollection(ArrayList::new));
    }
}
