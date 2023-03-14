package seedu.badMaths.Trigo;

public class TrigoGraph {
    private final String trigoEqn;

    public TrigoGraph(String trigoEqn) {
        this.trigoEqn = trigoEqn;
    }

    public void startGraphAnalysis() {
        splitAmplitude();
    }

    public void splitAmplitude() {
        try {
            if(trigoEqn.startsWith("-")){
                throw new IllegalArgumentException();
            }
            Double amplitude;
            String[] amplitudeAndEqn = trigoEqn.split("\\*", 2);
            if (amplitudeAndEqn[1].startsWith("*")){
                throw new NumberFormatException();
            }
            if (amplitudeAndEqn[0].startsWith("cos") || amplitudeAndEqn[0].startsWith("sin")
                    || amplitudeAndEqn[0].startsWith("tan") ){
                amplitude = 1.0;
                System.out.println("This is the amplitude: " + amplitude);
                splitTrigoAndVerticalShift(trigoEqn);
            }else{
                amplitude = Double.parseDouble(amplitudeAndEqn[0]);
                System.out.println("This is the amplitude: " + amplitude);
                splitTrigoAndVerticalShift(amplitudeAndEqn[1]);
            }
        } catch (NumberFormatException e) {
            System.out.println("Please enter the format as required: ");
        } catch (IllegalArgumentException e){
            System.out.println("Amplitude cannot be negative!");
        }
    }

    public void splitTrigoAndVerticalShift(String trigoAndVerticalShift) {
        try {
            String[] TrigoAndVerticalShift = trigoAndVerticalShift.split("\\)", 2);
            if(TrigoAndVerticalShift.length == 1){
                throw new StringIndexOutOfBoundsException();
            }
            if( !TrigoAndVerticalShift[1].isEmpty()) {
                if (TrigoAndVerticalShift[1].trim().contains("+")) {
                    double positiveVShift = Double.parseDouble(TrigoAndVerticalShift[1].substring(1)); //+5
                    System.out.println("This is the vertical shift: " + positiveVShift);
                } else {
                    double negativeShift = Double.parseDouble(TrigoAndVerticalShift[1]);
                    System.out.println("This is the vertical shift: " + negativeShift);

                }
            }else{
                System.out.println("This is the vertical shift: 0.0");
            }
            splitTrigoIntoPhasors(TrigoAndVerticalShift[0]);
        } catch (NumberFormatException e) {
            System.out.println("Please enter the format as required: ");
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("Too little arguments. Please enter the format as required: ");
        }
    }

    public void splitTrigoIntoPhasors(String trigo) {
        int startPosOfPhase = trigo.indexOf("(") + 1;
        int endPosOfPhase = trigo.length();
        String phase = trigo.substring(startPosOfPhase, endPosOfPhase);
        splitPhasorsIntoFreq(phase);
    }

    public void splitPhasorsIntoFreq(String phasors) {
        String[] freqAndShift = new String[2];
        boolean is_negative = false;
        if (phasors.contains("+")) {
            freqAndShift = phasors.split("\\+", 2);
        } else {
            freqAndShift = phasors.split("-", 2);
            is_negative = true;
        }

        String freqWithX = freqAndShift[0];
        String[] freqComponents = new String[1];
        if (freqWithX.equals("x")) {
            freqComponents[0] = "1.0";
        } else {
            freqComponents = freqWithX.split("\\*", 2);
        }
        Double freq;
        try {
            if(freqAndShift[1].contains("+")){
                throw new NumberFormatException();
            }
            if(freqComponents[1].equals("pi*x") || freqComponents[1].equals("x")) {
                if (trigoEqn.contains("pi")) {
                    freq = Double.parseDouble(freqComponents[0]) / 2;
                } else {
                    freq = Double.parseDouble(freqComponents[0]) / (2 * Math.PI);
                }
                System.out.println("The Freq (HZ) is: " + freq);
                if (freqAndShift.length == 2) {
                    if (is_negative) {
                        System.out.println("The phase shift is: -" + Double.valueOf(freqAndShift[1]));
                    } else {
                        System.out.println("The phase shift is: " + Double.valueOf(freqAndShift[1]));
                    }
                } else {
                    System.out.println("The phase shift is: 0.0");
                }
            } else {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            System.out.println("Please enter the format as required: ");
        }
    }
}
