package seedu.duke.diagnosis.symptoms;

import java.util.ArrayList;

/**
 * A tool to format symptoms.
 *
 * @author Brennanzuz
 */
public class SymptomHandler {

    /**
     * @author Brennanzuz
     * @param symptom ArrayList of symptoms
     * @return the name of the symptom in a readable format without capitalisation and underlines.
     */
    public static String toString(Symptom symptom) {
        switch (symptom) {
        case DIFFICULTY_BREATHING:
            return "difficulty breathing";
        case HISTORY_OF_ASTHMA:
            return "history of asthma";
        case HYPERVENTILATION:
            return "hyperventilation";
        case HISTORY_OF_ALLERGIES:
            return "history of allergies";
        case RUNNY_NOSE:
            return "runny nose";
        case FATIGUE:
            return "fatigue";
        case SNIFFING:
            return "sniffing";
        case THROAT_IRRITATION:
            return "throat irritation";
        case DRY_COUGH:
            return "dry cough";
        case COUGH_WITH_PHLEGM:
            return "cough with phlegm";
        case BLEEDING_WHEN_COUGHING:
            return "coughing blood";
        case CHEST_PAIN:
            return "chest pain";
        case HEAD_ACHE:
            return "headache";
        case HIGH_HEART_RATE:
            return "high heart rate";
        case TOOTH_ACHE:
            return "toothache";
        case BLACK_TEETH:
            return "blackened teeth";
        case RED_SKIN:
            return "reddened skin";
        case ITCHY_SKIN:
            return "itchy skin";
        case SWELLING_SKIN:
            return "swelling skin";
        case DRY_SCALING_SKIN:
            return "dry and scaling skin";
        case HARD_LUMPY_STOOL:
            return "hard and lumpy stools";
        case WET_STOOL:
            return "wet stools";
        case DIARRHOEA:
            return "diarrhoea";
        case STOMACH_ACHE:
            return "stomachache";
        case NAUSEA:
            return "nausea";
        case VOMITING:
            return "vomiting";
        case BLOODIED_STOOLS:
            return "bloodied stools";
        case WEIGHT_LOSS:
            return "weight loss";
        case FREQUENT_URINATION:
            return "frequent urination";
        case SLOW_REGENERATION:
            return "slow healing wounds";
        case FEVER:
            return "fever";
        case SUSCEPTIBILITY_TO_ILLNESS:
            return "susceptibility to other illnesses";
        case CHILLS:
            return "chills";
        case BLOCKED_NOSE:
            return "blocked nose";
        case SWOLLEN_LYMPH_NODES:
            return "swollen lymph nodes";
        case SWOLLEN_JOINTS:
            return "swollen joints";
        case MUSCLE_ACHE:
            return "muscle ache";
        case BACK_ACHE:
            return "backache";
        case JOINT_PAIN:
            return "joint pain";
        case HISTORY_OF_OSTEOPOROSIS:
            return "history of osteoporosis";
        case BLURRED_VISION:
            return "blurred vision";
        case SENSITIVITY_TO_LIGHT_AND_SOUND:
            return "sensitivity to light and sound";
        case SPRAIN:
            return "sprain";
        case SLEEPLESSNESS:
            return "lack of sleep";
        case FRACTURE:
            return "fracture";
        case BREATHLESSNESS:
            return "short of breath";
        case GENERAL_SWELLING:
            return "unspecified swelling";
        case GENERAL_PAIN:
            return "unspecified pain";
        case PALENESS_OF_SKIN:
            return "pale skin";
        case THIRST:
            return "persistent thirst";
        case WOUND_OR_CUT:
            return "external wound or cut";
        case BLEEDING:
            return "unspecified bleeding";
        case PUSS:
            return "secretion of puss";
        case HISTORY_OF_ADHD:
            return "history of ADHD";
        case HISTORY_OF_DEPRESSION:
            return "history of depression";
        case LOSS_OF_TASTE_OR_SMELL:
            return "loss of taste or smell";
        case ITCHY_EYE:
            return "itchy eyes";
        case RED_EYES:
            return "reddened eyes";
        case SNEEZING:
            return "persistent sneezing";
        default:
            return String.valueOf(symptom).toLowerCase().replace('_', ' ');
        }
    }

    /**
     * Prints all symptoms in an array in a readable format.
     *
     * @author Brennanzuz
     * @param symptoms ArrayList of symptoms.
     */
    public static void printSymptoms(ArrayList<Symptom> symptoms) {
        for (Symptom symptom : symptoms) {
            System.out.println(toString(symptom));
        }
    }
}
