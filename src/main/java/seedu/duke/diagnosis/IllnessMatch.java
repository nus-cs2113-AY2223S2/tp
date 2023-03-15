package seedu.duke.diagnosis;

import seedu.duke.diagnosis.illnesses.Illness;

/**
 * A class to indicate a match found between the suspected illness and the actual illness by stating the percentage
 * similarity it has to the actual symptoms.
 * @author Brennanzuz
 */
public class IllnessMatch {
    private final Illness illness;
    private final double similarityPercentage;

    public IllnessMatch(Illness illness, double similarityPercentage) {
        this.illness = illness;
        this.similarityPercentage = similarityPercentage;
    }
    public Illness getIllness() {
        return illness;
    }
    public double getSimilarityPercentage() {
        return similarityPercentage;
    }
}
