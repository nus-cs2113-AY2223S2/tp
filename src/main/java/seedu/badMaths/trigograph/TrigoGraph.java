package seedu.badMaths.trigograph;
import seedu.badMaths.ui.Ui;

public class TrigoGraph {
    private String trigoEqn;

    public TrigoGraph(String trigoEqn) {
        this.trigoEqn = trigoEqn;
    }

    public void startGraphAnalysis() {
        TrigoGraphAnalyser analyser = new TrigoGraphAnalyser(trigoEqn);
        if(analyser.canStartAnalyser()){
            assert analyser.canStartAnalyser() == true;
            double amplitude = analyser.getAmplitude();
            double frequency = analyser.getFreq();
            double phase = analyser.getPhase();
            double verticalShift = analyser.getVerticalShift();
            String trig = analyser.getTrigonometry();
            Ui.printAmplitude(amplitude);
            Ui.printFrequency(frequency);
            Ui.printPhase(phase);
            Ui.printVerticalShift(verticalShift);
            System.out.println("Trigo is: " + trig);

            TrigoGraphVisualiser visualiser = new TrigoGraphVisualiser(amplitude,phase,frequency,verticalShift,trig);
            visualiser.startVisualiser();
        }
        assert analyser.canStartAnalyser() == false;

    }
}


