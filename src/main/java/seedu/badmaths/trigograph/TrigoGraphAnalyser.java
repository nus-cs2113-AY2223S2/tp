package seedu.badmaths.trigograph;


import seedu.badmaths.ui.Ui;

import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class TrigoGraphAnalyser {
    private static final boolean CAN_RUN_ANALYSER = true;
    private static final boolean CANNOT_RUN_ANALYSER = false;
    private static final int PLACEHOLDER_SIZE_WITH_NEG_PHASE = 3;
    private static final int PLACEHOLDER_SIZE_WITH_POS_PHASE = 2;
    private static final int PLACEHOLDER_SIZE_WITH_TRIGO_AND_VERTICAL_SHIFT = 2;
    private static final int PLACEHOLDER_SIZE_WITH_AMPLITUDE_AND_EQN = 2;
    private static final int INDEX_FOR_NEG_PHASE_WITH_NEG_FREQ = 2;
    private static final int INDEX_FOR_NEG_PHASE_WITH_POS_FREQ = 1;
    private static final int INDEX_FOR_FREQ_WITH_POS_PHASE = 0;
    private static final int INDEX_FOR_POS_FREQ = 0;
    private static final int INDEX_FOR_POS_PHASE = 1;
    private static final int INDEX_FOR_NEG_FREQ = 1;
    private static final int START_POS_OF_TRIG = 0;
    private static final int END_POS_OF_TRIG = 3;
    private static final int CORRECT_POS_OF_PHASE = 4;
    private static final double ONE_HERTZ = 1.0;
    private static final double ONE_AMPLITUDE = 1.0;
    private static final double ZERO_PHASE = 0.0;
    private static final double ZERO_VERTICAL_SHIFT = 0.0;
    private static final double NEGATIVE_SIGN = -1.0;
    private static final double ZERO_HERTZ = 0.0;
    private static final int FIRST_INDEX_FOR_FREQ = 0;
    private static final String STRING_NEGATIVE_SIGN = "-";
    private static final String STRING_POSITIVE_SIGN = "+";
    private static Logger logger = Logger.getLogger(TrigoGraphAnalyser.class.getName());
    private String trigoEqn;
    private String trigonometry;
    private double phase;
    private double amplitude;
    private double verticalShift;
    private double freq;



    public TrigoGraphAnalyser(String trigoEqn) {
        this.trigoEqn = trigoEqn;
    }

    public static void setUpLogger() {
        LogManager.getLogManager().reset();
        logger.setLevel(Level.ALL);
        try {
            if (!new File("trigoGraphAnalyser.log").exists()) {
                new File("trigoGraphAnalyser.log").createNewFile();
            }
            FileHandler logFile = new FileHandler("trigoGraphAnalyser.log", true);
            logFile.setLevel(Level.FINE);
            logger.addHandler(logFile);

        } catch (IOException e) {
            logger.log(Level.SEVERE, "File logger not working.", e);
        }
    }

    public String getTrig() {
        return trigonometry;
    }

    /**
     * Main method that calls various methods to split the equation into Amplitude, Frequency, Phase Shift,
     * and Vertical Shift. This method also catches the exceptions thrown and prints out the corresponding message to
     * the user.
     * @return true if equation entered is valid.
     */
    public boolean canStartAnalyser() {
        setUpLogger();
        try {
            String[] amplitudeAndEqn = splitAmplitudeFromTrigoEqn();
            findAmplitude(amplitudeAndEqn);
            String[] trigoAndVerticalShift;
            // To handle the situation if equation starts with tan or 1*tan
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
            logger.log(Level.SEVERE, "NumberFormatException", e);
            Ui.printIncorrectFormatEntered();
            return CANNOT_RUN_ANALYSER;
        } catch (GraphException e) {
            logger.log(Level.SEVERE, "GraphException", e);
            Ui.printNegativeAmplitudeEntered();
            return CANNOT_RUN_ANALYSER;
        } catch (ArrayIndexOutOfBoundsException e) {
            logger.log(Level.SEVERE, "ArrayIndexOutOfBounds", e);
            Ui.printIncorrectFormatEntered();
            return CANNOT_RUN_ANALYSER;
        } catch (IllegalArgumentException e) {
            logger.log(Level.SEVERE, "IllegalArguementException", e);
            Ui.printIncorrectFormatEntered();
            return CANNOT_RUN_ANALYSER;
        } catch (NegativeFrequencyException e) {
            Ui.printNegativeFrequencyEntered();
            return CANNOT_RUN_ANALYSER;
        } catch (ZeroFrequencyException e) {
            Ui.printZeroFrequencyEntered();
            return CANNOT_RUN_ANALYSER;
        }
    }

    /**
     * Assigns an int to this amplitude.
     * @param eqn contains the value of amplitude at index 0 and the remaining equation at index 1.
     * @throws NumberFormatException if Double.parseDouble attempts to parse a non-numerical string such as "abc".
     */
    public void findAmplitude(String[] eqn) throws NumberFormatException {
        String[] trigoAndVerticalShift;

        if (isAmplitudeEqualsToOne(eqn[0])) {
            amplitude = ONE_AMPLITUDE;
        } else {
            amplitude = Double.parseDouble(eqn[0]);
        }
    }

    private void testForSignOfAmplitude() throws GraphException {
        if (trigoEqn.startsWith("-")) {
            throw new GraphException();
        }
    }

    private void testForMultipleAsterisk(String input) throws NumberFormatException {
        if (input.startsWith("*")) {
            throw new NumberFormatException();
        }
    }

    public String[] splitAmplitudeFromTrigoEqn() throws IllegalArgumentException, GraphException {
        testForSignOfAmplitude();
        String[] amplitudeAndEqn = trigoEqn.split("\\*", PLACEHOLDER_SIZE_WITH_AMPLITUDE_AND_EQN);
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

    public String getTrigonometry() {
        return trigonometry;
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

        String[] trigoAndVerticalShift = eqn.split("\\)", PLACEHOLDER_SIZE_WITH_TRIGO_AND_VERTICAL_SHIFT);
        return trigoAndVerticalShift;
    }

    private void findVerticalShift(String[] trigoAndVerticalShift) {
        if (isVerticalShiftZero(trigoAndVerticalShift[1])) {
            verticalShift = ZERO_VERTICAL_SHIFT;
        } else {
            verticalShift = Double.parseDouble(trigoAndVerticalShift[1]);
        }
    }

    /**
     * Remove the closing bracket in {@code trigo} to form a proper trigonometric equation containing frequency and
     * phase.
     * @param trigo is a trigo equation with amplitude and vertical shift removed
     * @throws IllegalArgumentException
     * @throws NegativeFrequencyException
     * @throws ZeroFrequencyException
     */
    public void splitTrigoIntoPhasors(String trigo) throws IllegalArgumentException, NegativeFrequencyException,
            ZeroFrequencyException {
        int startPosOfPhase = trigo.indexOf("(") + 1;
        int endPosOfPhase = trigo.length();
        if (startPosOfPhase == CORRECT_POS_OF_PHASE) {
            trigonometry = trigo.substring(START_POS_OF_TRIG, END_POS_OF_TRIG);
        } else {
            throw new IllegalArgumentException();
        }

        String phase = trigo.substring(startPosOfPhase, endPosOfPhase);
        splitPhasorsIntoFreq(phase);
    }

    public void findFreqForNoPhasors(String phasors) throws NegativeFrequencyException, ZeroFrequencyException {
        phase = ZERO_PHASE;
        boolean isFreqNegative = testForNegativeFreq(phasors);
        findFreq(phasors, isFreqNegative);
    }

    /*
     * In the event that the phase is positive, this method will find the phase and frequency.
     * @param phasors contain frequency and phase
     * @throws NegativeFrequencyException
     * @throws ZeroFrequencyException
     */
    private void findFreqForPlus(String phasors) throws NegativeFrequencyException, ZeroFrequencyException {
        String[] freqAndShift = phasors.split("\\+", PLACEHOLDER_SIZE_WITH_POS_PHASE);
        findPhase(freqAndShift[INDEX_FOR_POS_PHASE], false);
        boolean isFreqNegative = testForNegativeFreq(freqAndShift[INDEX_FOR_FREQ_WITH_POS_PHASE]);
        findFreq(freqAndShift[INDEX_FOR_POS_FREQ], isFreqNegative);
    }

    private void findFreqForMinus(String phasors) throws NegativeFrequencyException, ZeroFrequencyException {
        /*
         * If both phase and frequency are negative, empty string will be in index 0,freq will be in index 1,
         * and phase will be in index 2 of freqAndShift.
         * However, if only phase is negative, freq will be in index 0, phase will be in index 1, and empty string will
         * in index 2.
         */
        String[] freqAndShift = phasors.split(STRING_NEGATIVE_SIGN, PLACEHOLDER_SIZE_WITH_NEG_PHASE);
        boolean isPhaseNegative = true;
        // Find if freq is negative based on its index in freqAndShift as mentioned above
        boolean isFreqNegative = testForNegativeFreq(freqAndShift);
        if (isFreqNegative) {
            findPhase(freqAndShift[INDEX_FOR_NEG_PHASE_WITH_NEG_FREQ], isPhaseNegative);
            findFreq(freqAndShift[INDEX_FOR_NEG_FREQ], isFreqNegative);
        } else {
            findPhase(freqAndShift[INDEX_FOR_NEG_PHASE_WITH_POS_FREQ], isPhaseNegative);
            findFreq(freqAndShift[INDEX_FOR_POS_FREQ], isFreqNegative);
        }
    }

    private void splitPhasorsIntoFreq(String phasors) throws NegativeFrequencyException, ZeroFrequencyException {
        if (phasors.endsWith("x")) {
            findFreqForNoPhasors(phasors);
        } else if (phasors.contains(STRING_POSITIVE_SIGN)) {
            findFreqForPlus(phasors);
        } else {
            findFreqForMinus(phasors);
        }
    }

    private boolean testForNegativeFreq(String[] freqAndShift) {
        if (freqAndShift[INDEX_FOR_POS_FREQ].isEmpty()) {
            return true;
        }
        return false;
    }

    private boolean testForNegativeFreq(String freq) {
        if (freq.startsWith(STRING_NEGATIVE_SIGN)) {
            return true;
        }
        return false;
    }

    public void findFreq(String freqWithX, boolean isFreqNeg) throws NumberFormatException, NegativeFrequencyException,
            ZeroFrequencyException {
        try {
            if (isFreqNeg) {
                throw new NegativeFrequencyException();
            }
            String freqComponents;
            if (freqWithX.equals("x")) {
                freq = ONE_HERTZ;
            } else {
                /*
                 *  freqWithX can have 1 or 2 "*", e.g. 5*pi*x or 5*x, simply split to get "5" will suffice as checks
                 *  below will take "pi" into account, if any.
                 */
                int lastIndexForFreq = freqWithX.indexOf("*");
                freqComponents = freqWithX.substring(FIRST_INDEX_FOR_FREQ, lastIndexForFreq);
                checkForZeroFreq(freqComponents);
                if (trigoEqn.contains("pi")) {
                    freq = Double.parseDouble(freqComponents) / 2;
                } else {
                    freq = Double.parseDouble(freqComponents) / (2 * Math.PI);
                }
            }
            freq = Math.abs(freq);

        } catch (NumberFormatException e) {
            throw new NumberFormatException();
        }
    }

    public void checkForZeroFreq(String freqComponents) throws ZeroFrequencyException {
        if (Double.parseDouble(freqComponents) == ZERO_HERTZ) {
            throw new ZeroFrequencyException();
        }
    }

    private void findPhase(String phasor, boolean isPhaseNegative) throws NumberFormatException {
        try {
            if (phasor.isEmpty()) {
                phase = ZERO_PHASE;
            } else if (isPhaseNegative) {
                phase = Double.parseDouble(phasor) * (NEGATIVE_SIGN);
            } else {
                phase = Double.parseDouble(phasor);
            }
        } catch (NumberFormatException e) {
            throw new NumberFormatException();
        }
    }
}

