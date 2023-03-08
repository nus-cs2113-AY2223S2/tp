package seedu.badMaths;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TrigoGraphTest {

    @Test
    void splitAmplitude_oneSplit_expectTwoParts() {
        String equation = "2*sin(2*pi*x+1)+3";
        String separator = "\\*";
        String [] amplitudeAndEqn = equation.split("\\*",2);
        assertEquals(2,amplitudeAndEqn.length);
    }
    @Test
    void splitAmplitude_NoAmplitude_expectException() {
        String equation = "sin(2*pi*x+1)+3";
        String separator = "\\*";
        String[] amplitudeAndEqn = equation.split("\\*", 2);
        assertThrows(NumberFormatException.class,()->Double.parseDouble(amplitudeAndEqn[0]));

    }

    @Test
    void splitTrigoAndVerticalShift_doubleDigitsVShift_expectSameOutput() {
        String equation_Pos = "sin(2*pi*x+1)+36";
        double answer = 0;
        String[] TrigoAndVerticalShift = equation_Pos.split("\\)", 2);
        if (TrigoAndVerticalShift[1].trim().contains("+")) {
            answer = Double.parseDouble(TrigoAndVerticalShift[1].substring(1).trim());
        }
        assertEquals(36,answer);
    }
}