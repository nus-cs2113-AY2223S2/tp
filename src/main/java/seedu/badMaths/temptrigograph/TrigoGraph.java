package seedu.badMaths.temptrigograph;
import seedu.badMaths.tempui.Ui;

public class TrigoGraph {
    private String trigoEqn;

    public TrigoGraph(String trigoEqn) {
        this.trigoEqn = trigoEqn;
    }

    public void startGraphAnalysis() {
        TrigoGraphAnalyser analyser = new TrigoGraphAnalyser(trigoEqn);
        if(analyser.canStartAnalyser()){
            assert analyser.canStartAnalyser() == true;
            Ui.printAmplitude(analyser.getAmplitude());
            Ui.printFrequency(analyser.getFreq());
            Ui.printPhase(analyser.getPhase());
            Ui.printVerticalShift(analyser.getVerticalShift());
        }
        assert analyser.canStartAnalyser() == false;

    }
}


