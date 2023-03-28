package seedu.badMaths.trigograph;
import seedu.badMaths.ui.Ui;

public class TrigoGraph {
    private String trigoEqn;
    private double amplitude;
    private double frequency;
    private double phase;
    private double verticalShift;
    private String trig;

    public TrigoGraph(String trigoEqn) {
        this.trigoEqn = trigoEqn;
    }

    public void startGraphAnalysis() {
        TrigoGraphAnalyser analyser = new TrigoGraphAnalyser(trigoEqn);
        if(analyser.canStartAnalyser()){
            assert analyser.canStartAnalyser() == true;
            getGraphDetails(analyser);
            printGraphDetails();
            TrigoGraphVisualiser visualiser = new TrigoGraphVisualiser(amplitude,phase,frequency,verticalShift,trig);
            visualiser.startVisualiser();
        } else{
            assert analyser.canStartAnalyser() == false;
        }

    }
    public void getGraphDetails(TrigoGraphAnalyser analyser){
        amplitude = analyser.getAmplitude();
        frequency = analyser.getFreq();
        phase = analyser.getPhase();
        verticalShift = analyser.getVerticalShift();
        trig = analyser.getTrigonometry();
    }


    public void printGraphDetails(){
        Ui.printAmplitude(amplitude);
        Ui.printFrequency(frequency);
        Ui.printPhase(phase);
        Ui.printVerticalShift(verticalShift);
    }
    public double getAmplitude() {
        return amplitude;
    }

    public double getFrequency() {
        return frequency;
    }

    public double getPhase() {
        return phase;
    }

    public double getVerticalShift() {
        return verticalShift;
    }

    public String getTrig() {
        return trig;
    }
}


