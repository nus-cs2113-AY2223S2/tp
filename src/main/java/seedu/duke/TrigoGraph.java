package seedu.duke;

public class TrigoGraph {
    private String trigoEqn;

    public TrigoGraph(String trigoEqn) {
        this.trigoEqn = trigoEqn;
    }

    public void splitAmplitude(){
        Double amplitude;
        String [] amplitudeAndEqn = trigoEqn.split("\\*",2);
        amplitude = Double.parseDouble(amplitudeAndEqn[0]);
        System.out.println("This is the amplitude: "+amplitude);
        splitTrigoAndVerticalShift(amplitudeAndEqn[1]);
    }

    public void splitTrigoAndVerticalShift(String trigoAndVerticalShift){
        String [] TrigoAndVerticalShift = trigoAndVerticalShift.split("\\)",2);
        if(TrigoAndVerticalShift[1].trim().contains("+")){
            String positiveVShift = TrigoAndVerticalShift[1].substring(2); //+ 5
            System.out.println("This is the vertical shift: "+positiveVShift);
        }else{
            System.out.println("This is the vertical shift: "+TrigoAndVerticalShift[1]);

        }
        splitTrigoIntoPhasors(TrigoAndVerticalShift[0]);
    }

    public void splitTrigoIntoPhasors(String trigo){
        int startPosOfPhase = trigo.indexOf("(")+1;
        int endPosOfPhase = trigo.length();
        String phase = trigo.substring(startPosOfPhase,endPosOfPhase);
        splitPhasorsIntoFreq(phase);
    }
    public void splitPhasorsIntoFreq(String phasors){
        String [] freqAndShift = phasors.split("\\+",2);
        String freqWithX = freqAndShift[0];
        String [] freqComponents = freqWithX.split("\\*",3);
        Double freq;
        if(trigoEqn.contains("pi")){
            freq = Double.parseDouble(freqComponents[0])/2;
        }else{
            freq = Double.parseDouble(freqComponents[0])/(2*Math.PI);
        }
        System.out.println("The Freq (HZ) is: "+freq);
        System.out.println("The phase shift is: "+freqAndShift[1]);
    }

}
