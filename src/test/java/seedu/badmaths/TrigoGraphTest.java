package seedu.badmaths;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TrigoGraphTest {

    @Test
    void splitAmplitude_oneSplit_expectTwoParts() {
        String equation = "2*sin(2*pi*x+1)+3";
        String separator = "\\*";
        String [] amplitudeAndEqn = equation.split("\\*",2);
        assertEquals(2,amplitudeAndEqn.length);
    }
    @Test
    void splitAmplitude_noAmplitude_expectException() {
        String equation = "sin(2*pi*x+1)+3";
        String separator = "\\*";
        String[] amplitudeAndEqn = equation.split("\\*", 2);
        assertThrows(NumberFormatException.class,()->Double.parseDouble(amplitudeAndEqn[0]));

    }
    @Test
    void splitTrigoAndVerticalShift_doubleDigitsVShift_expectSameOutput() {
        String equation_Pos = "sin(2*pi*x+1)+36";
        double answer = 0;
        String[] trigoAndVerticalShift = equation_Pos.split("\\)", 2);
        if (trigoAndVerticalShift[1].trim().contains("+")) {
            answer = Double.parseDouble(trigoAndVerticalShift[1].substring(1).trim());
        }
        assertEquals(36,answer);
    }
    @Test
    void splitTrigoIntoPhasors_startEndPosPhase_expectPhase() {
        String trigo = "sin(2*pi*x+1";
        int startPosOfPhase = trigo.indexOf("(") + 1;
        int endPosOfPhase = trigo.length();
        String phase = trigo.substring(startPosOfPhase,endPosOfPhase);
        assertEquals("2*pi*x+1", phase);
    }
    @Test
    void splitPhasorsIntoFreq_phasors_expectFreqAndShift() {
        String phasors = "2*pi*x+1";
        String [] freqAndShift = phasors.split("\\+",2);
        String freqWithX = freqAndShift[0];
        String [] freqComponents = freqWithX.split("\\*",3);
        Double freq;
        if(phasors.contains("pi")){
            freq = Double.parseDouble(freqComponents[0])/2;
        }else{
            freq = Double.parseDouble(freqComponents[0])/(2*Math.PI);
        }
        assertEquals(1.0, freq);
        assertEquals("1", freqAndShift[1]);
    }
}
