package seedu.badMaths.Trigo;

import seedu.badMaths.UI.Ui;

public class TrigoGraphAnalyser {
    private static final boolean CAN_RUN_ANALYSER = true;
    private static final boolean CANNOT_RUN_ANALYSER = false;
    private static final int PLACEHOLDER_SIZE_WITH_NEG_PHASE = 3;
    private static final int PLACEHOLDER_SIZE_WITH_POS_PHASE = 2;
    private static final int SIZE_OF_FREQ_AND_PHASE = 3;
    private static final int INDEX_FOR_NEG_PHASE_WITH_NEG_FREQ = 2;
    private static final int INDEX_FOR_NEG_PHASE_WITH_POS_FREQ = 1;
    private static final int INDEX_FOR_POS_FREQ = 0;
    private static final int INDEX_FOR_POS_PHASE = 1;
    private static final int INDEX_FOR_NEG_FREQ = 1;
    private String trigoEqn;
    private double phase;
    private double amplitude;
    private double verticalShift;
    private double freq;


    public TrigoGraphAnalyser(String trigoEqn) {
        this.trigoEqn = trigoEqn;
    }

    public boolean canStartAnalyser() {
        try {
            String[] amplitudeAndEqn = splitAmplitudeFromTrigoEqn();
            findAmplitude(amplitudeAndEqn);
            String[] trigoAndVerticalShift;
            if (isAmplitudeEqualsToOne(amplitudeAndEqn[0])) {
                trigoAndVerticalShift = splitTrigoAndVerticalShift(trigoEqn);
            } else {
                trigoAndVerticalShift = splitTrigoAndVerticalShift(amplitudeAndEqn[1]);
            }
            findVerticalShift(trigoAndVerticalShift);
            String trigo = trigoAndVerticalShift[0];
            splitTrigoIntoPhasors(trigo);
            return CAN_RUN_ANALYSER;
        } catch (NumberFormatException e) {
            Ui.printIncorrectFormatEntered();
            return CANNOT_RUN_ANALYSER;
        } catch (IllegalArgumentException e) {
            Ui.printNegativeAmplitudeEntered();
            return CANNOT_RUN_ANALYSER;
        }
    }

    private void findAmplitude(String[] eqn) throws NumberFormatException {
        String[] trigoAndVerticalShift;

        if (isAmplitudeEqualsToOne(eqn[0])) {
            amplitude = 1.0;
        } else {
            amplitude = Double.parseDouble(eqn[0]);
        }
    }

    private void testForSignOfAmplitude() throws IllegalArgumentException {
        if (trigoEqn.startsWith("-")) {
            throw new IllegalArgumentException();
        }
    }

    private void testForMultipleAsterisk(String input) throws NumberFormatException {
        if (input.startsWith("*")) {
            throw new NumberFormatException();
        }
    }

    private String[] splitAmplitudeFromTrigoEqn() throws NumberFormatException, IllegalArgumentException {
        testForSignOfAmplitude();
        String[] amplitudeAndEqn = trigoEqn.split("\\*", 2);
        testForMultipleAsterisk(amplitudeAndEqn[1]);
        return amplitudeAndEqn;
    }

    public Double getAmplitude() {
        return amplitude;
    }

    public double getPhase() {
        return phase;
    }

    public double getVerticalShift() {
        return verticalShift;
    }

    public double getFreq() {
        return freq;
    }

    private boolean isAmplitudeEqualsToOne(String input) {
        if (input.startsWith("cos") || input.startsWith("sin") || input.startsWith("tan")) {
            return true;
        }
        return false;
    }


    private boolean isVerticalShiftZero(String verticalShift) {
        return verticalShift.isEmpty();
    }


    private String[] splitTrigoAndVerticalShift(String eqn) {

        String[] trigoAndVerticalShift = eqn.split("\\)", 2);
        return trigoAndVerticalShift;
    }

    private void findVerticalShift(String[] trigoAndVerticalShift) {
        if (isVerticalShiftZero(trigoAndVerticalShift[1])) {
            verticalShift = 0.0;
        } else {
            verticalShift = Double.parseDouble(trigoAndVerticalShift[1]);
        }
    }

    public void splitTrigoIntoPhasors(String trigo) {
        int startPosOfPhase = trigo.indexOf("(") + 1;
        int endPosOfPhase = trigo.length();
        String phase = trigo.substring(startPosOfPhase, endPosOfPhase);
        splitPhasorsIntoFreq(phase);
    }


    private void splitPhasorsIntoFreq(String phasors) {
        String[] freqAndShift = new String[SIZE_OF_FREQ_AND_PHASE];
        boolean isPhaseNegative = false;
        boolean isFreqNegative = false;
        if (phasors.contains("+")) {
            freqAndShift = phasors.split("\\+", PLACEHOLDER_SIZE_WITH_POS_PHASE);
            findPhase(freqAndShift[INDEX_FOR_POS_PHASE], isPhaseNegative);
            isFreqNegative = testForNegativeFreq(freqAndShift);
            findFreq(freqAndShift[INDEX_FOR_POS_FREQ], isFreqNegative);
        } else if (phasors.contains("-")) {
            freqAndShift = phasors.split("-", PLACEHOLDER_SIZE_WITH_NEG_PHASE);
            isPhaseNegative = true;
            isFreqNegative = testForNegativeFreq(freqAndShift);
            if (isFreqNegative) {
                findPhase(freqAndShift[INDEX_FOR_NEG_PHASE_WITH_NEG_FREQ], isPhaseNegative);
                findFreq(freqAndShift[INDEX_FOR_NEG_FREQ], isFreqNegative);
            } else {
                findPhase(freqAndShift[INDEX_FOR_NEG_PHASE_WITH_POS_FREQ], isPhaseNegative);
                findFreq(freqAndShift[INDEX_FOR_POS_FREQ], isFreqNegative);
            }
        }
    }

    private boolean testForNegativeFreq(String[] freqAndShift) {
        if (freqAndShift[INDEX_FOR_POS_FREQ].isEmpty()) {
            return true;
        }
        return false;
    }

    private void findFreq(String freqWithX, boolean isFreqNeg) throws NumberFormatException {
        try {
            String freqComponents;
            if (freqWithX.equals("x")) {
                if (isFreqNeg) {
                    freqComponents = "-1.0";
                } else {
                    freqComponents = "1.0";
                }
            } else {
                int lastIndexForFreq = freqWithX.indexOf("*");
                freqComponents = freqWithX.substring(0, lastIndexForFreq);
                if (trigoEqn.contains("pi")) {
                    freq = Double.parseDouble(freqComponents) / 2;
                } else {
                    freq = Double.parseDouble(freqComponents) / (2 * Math.PI);
                }
            }
            if (isFreqNeg) {
                freq = Math.abs(freq) * -1;
            } else {
                freq = Math.abs(freq);
            }
        } catch (NumberFormatException e) {
            throw new NumberFormatException();
        }
    }

    private void findPhase(String phasor, boolean isPhaseNegative) throws NumberFormatException {
        try {
            if (phasor.isEmpty()) {
                phase = 0.0;
            } else if (isPhaseNegative) {
                phase = Double.parseDouble(phasor) * (-1.0);
            } else {
                phase = Double.parseDouble(phasor);
            }
        } catch (NumberFormatException e) {
            throw new NumberFormatException();
        }
    }
}

