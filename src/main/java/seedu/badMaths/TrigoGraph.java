package seedu.badMaths;

public class TrigoGraph {
    private String trigoEqn;

    public TrigoGraph(String trigoEqn) {
        this.trigoEqn = trigoEqn;
    }

    public void startGraphAnalysis() {
        splitAmplitude();
    }

    public void splitAmplitude() {
        try {
            Double amplitude;
            String[] amplitudeAndEqn = trigoEqn.split("\\*", 2);
            amplitude = Double.parseDouble(amplitudeAndEqn[0]);
            System.out.println("This is the amplitude: " + amplitude);
            splitTrigoAndVerticalShift(amplitudeAndEqn[1]);
        } catch (NumberFormatException e) {
            System.out.println("Please enter the format as required: ");
        }
    }

    public void splitTrigoAndVerticalShift(String trigoAndVerticalShift) {
        try {
            String[] TrigoAndVerticalShift = trigoAndVerticalShift.split("\\)", 2);
            if (TrigoAndVerticalShift[1].trim().contains("+")) {
                double positiveVShift = Double.parseDouble(TrigoAndVerticalShift[1].substring(1)); //+5
                System.out.println("This is the vertical shift: " + positiveVShift);
            } else {
                double negativeShift = Double.parseDouble(TrigoAndVerticalShift[1]);
                System.out.println("This is the vertical shift: " + negativeShift);

            }
            splitTrigoIntoPhasors(TrigoAndVerticalShift[0]);
        } catch (NumberFormatException e) {
            System.out.println("Please enter the format as required: ");
        }
    }

    public void splitTrigoIntoPhasors(String trigo) {
        int startPosOfPhase = trigo.indexOf("(") + 1;
        int endPosOfPhase = trigo.length();
        String phase = trigo.substring(startPosOfPhase, endPosOfPhase);
        splitPhasorsIntoFreq(phase);
    }

    public void splitPhasorsIntoFreq(String phasors) {
        String[] freqAndShift = phasors.split("\\+", 2);
        String freqWithX = freqAndShift[0];
        String[] freqComponents = freqWithX.split("\\*", 3);
        Double freq;
        if (trigoEqn.contains("pi")) {
            freq = Double.parseDouble(freqComponents[0]) / 2;
        } else {
            freq = Double.parseDouble(freqComponents[0]) / (2 * Math.PI);
        }
        System.out.println("The Freq (HZ) is: " + freq);
        System.out.println("The phase shift is: " + freqAndShift[1]);
    }

}
