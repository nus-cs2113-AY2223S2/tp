package seedu.badmaths.trigograph;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class TrigoGraphAnalyserTest {
    @Test
    void noAsteriskThrowsArrayIndexOutOfBoundsException() {
        String eqn = "sin(x)";
        TrigoGraphAnalyser analyser = new TrigoGraphAnalyser(eqn);
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            analyser.splitAmplitudeFromTrigoEqn();
        });
        assertEquals(false, analyser.canStartAnalyser());
    }

    @Test
    void multipleAsteriskThrowsNumberFormatException() {
        String eqn = "2**sin(4*x+1)-6";
        TrigoGraphAnalyser analyser = new TrigoGraphAnalyser(eqn);
        assertThrows(NumberFormatException.class, () -> {
            analyser.testForMultipleAsterisk("*sin(4*x+1)-6");
        });
    }

    @Test
    void zeroVerticalShiftAndOneRadians() {
        String eqn = "2*sin(1*x-1)";
        TrigoGraphAnalyser analyser = new TrigoGraphAnalyser(eqn);
        analyser.canStartAnalyser();
        assertEquals(0.0, analyser.getVerticalShift());
        assertEquals(1.0 / (Math.PI * 2), analyser.getFreq());
    }

    @Test
    void negativeAmplitudeShouldReturnIllegalArgumentException() {
        String equation = "-2*sin(2*pi*x+1)+3";
        TrigoGraphAnalyser analyser = new TrigoGraphAnalyser(equation);
        assertEquals(false, analyser.canStartAnalyser());
        assertThrows(GraphException.class, () -> {
            analyser.splitAmplitudeFromTrigoEqn();
        });
    }

    @Test
    void amplitudeOfOneShouldReturnOne() throws GraphException {
        String equation = "sin(2*pi*x+1)+3";
        TrigoGraphAnalyser analyser = new TrigoGraphAnalyser(equation);
        String[] input = analyser.splitAmplitudeFromTrigoEqn();
        analyser.findAmplitude(input);
        assertEquals(1.0, analyser.getAmplitude());
    }

    @Test
    void wrongFreqFormat_withMinus_expectsNegativeFrequencyException() {
        String eqn = "2*cos(-*x+5)-2";
        TrigoGraphAnalyser test = new TrigoGraphAnalyser(eqn);
        assertThrows(NegativeFrequencyException.class, () -> {
            test.findFreq("-*x", test.testForNegativeFreq("-*x"));
        });
    }

    @Test
    void freqWithNoPhasorsExpectNoException() {
        String freq = "5*x";
        TrigoGraphAnalyser analyser = new TrigoGraphAnalyser(freq);
        assertDoesNotThrow(() -> {
            analyser.findFreqForNoPhasors(freq);
        });
    }

    @Test
    void tanHasNoAmplitude() {
        TrigoGraphAnalyser test = new TrigoGraphAnalyser("2*tan(2*x+5)-2");
        test.canStartAnalyser();
        assertEquals("tan", test.getTrig());
    }

    @Test
    void zeroFrequencyExpectZeroFrequencyException() {
        TrigoGraphAnalyser test = new TrigoGraphAnalyser("2*tan(0*x-1)+2");
        assertEquals(false, test.canStartAnalyser());
        assertThrows(ZeroFrequencyException.class, () -> {
            test.findFreq("0*x", false);
        });
    }

    @Test
    void negativeFrequencyReturnsTrue() {
        TrigoGraphAnalyser test = new TrigoGraphAnalyser("2*tan(0*x-1)+2");

    }
}
