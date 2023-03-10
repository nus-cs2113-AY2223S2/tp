package seedu.duke.diagnosis;

import seedu.duke.diagnosis.illnesses.Illness;

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
