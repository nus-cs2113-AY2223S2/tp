package seedu.badmaths.trigograph;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class TrigoGraphAnalyserTest {
    @Test
    void negativeAmplitudeShouldReturnIllegalArgumentException() {
        String equation = "-2*sin(2*pi*x+1)+3";
        TrigoGraphAnalyser analyser = new TrigoGraphAnalyser(equation);
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
            test.findFreq("-*x", true);
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
    void zeroFrequencyExpectZeroFrequencyException(){
        TrigoGraphAnalyser test = new TrigoGraphAnalyser("2*tan(0*x-1)+2");
        assertThrows(ZeroFrequencyException.class,()->{
            test.findFreq("0*x",false);
        });
    }
}
