package seedu.badMaths.Trigo;
import seedu.badMaths.UI.Ui;

public class TrigoGraph {
    private String trigoEqn;

    public TrigoGraph(String trigoEqn) {
        this.trigoEqn = trigoEqn;
    }

    public void startGraphAnalysis() {
        TrigoGraphAnalyser analyser = new TrigoGraphAnalyser(trigoEqn);
        if(analyser.canStartAnalyser()){
            Ui.printAmplitude(analyser.getAmplitude());
            Ui.printFrequency(analyser.getFreq());
            Ui.printPhase(analyser.getPhase());
            Ui.printVerticalShift(analyser.getVerticalShift());
        }
    }
}


