package seedu.badmaths.trigograph;
import seedu.badmaths.IllegalTodoException;
import seedu.badmaths.ui.Ui;

import java.awt.*;


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

    public void startGraphAnalysis() throws IllegalTodoException {
        TrigoGraphAnalyser analyser = new TrigoGraphAnalyser(trigoEqn);
        if(trigoEqn.isEmpty()){
            throw new IllegalTodoException();
        }
        try{
        if(analyser.canStartAnalyser()){
            getGraphDetails(analyser);
            printGraphDetails();
            TrigoGraphVisualiser visualiser = new TrigoGraphVisualiser(amplitude,phase,frequency,verticalShift,trig);
            visualiser.startVisualiser();
        } else{
            throw new IllegalTodoException();
        }
        } catch (IllegalTodoException e){
            throw new IllegalTodoException();
        } catch (HeadlessException e){
            assert true;
        }

    }
    public void getGraphDetails(TrigoGraphAnalyser analyser){
        amplitude = analyser.getAmplitude();
        frequency = analyser.getFreq();
        phase = analyser.getPhase();
        verticalShift = analyser.getVerticalShift();
        trig = analyser.getTrigonometry();
        assert true: "Information retrieved.";
    }


    public void printGraphDetails() {
        if(trig.equals("tan")){
            Ui.printTanHasNoAmplitude();
        }else{
            Ui.printAmplitude(amplitude);
        }
        Ui.printFrequency(frequency);
        Ui.printPhase(phase);
        Ui.printVerticalShift(verticalShift);
        assert true: "Information printed.";
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
