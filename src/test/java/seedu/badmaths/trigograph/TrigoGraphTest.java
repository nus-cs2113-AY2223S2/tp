package seedu.badmaths.trigograph;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TrigoGraphTest {

    @Test
    void validEqn_withMinusFreq_expectCannotStartAnalyser() {
        String eqn = "cos(-1*x-1)-1";
        TrigoGraphAnalyser test = new TrigoGraphAnalyser(eqn);
        assertEquals(false, test.canStartAnalyser());
    }

    @Test
    void validEqn_returnsCorrectDetails() {
        String eqn = "2*tan(2*pi*x+1)-9";
        TrigoGraphAnalyser graphTest = new TrigoGraphAnalyser(eqn);
        graphTest.canStartAnalyser();
        assertEquals(2.0, graphTest.getAmplitude());
        assertEquals("tan", graphTest.getTrigonometry());
        assertEquals(1.0, graphTest.getFreq());
        assertEquals(1.0, graphTest.getPhase());
        assertEquals(-9.0, graphTest.getVerticalShift());
    }

    @Test
    void invalidEqn_withMultipleTrigo_expectCanStartAnalyserAsFalse() {
        String eqn1 = "2*tan*tan(2*pi*x+1)-9";
        TrigoGraphAnalyser test = new TrigoGraphAnalyser(eqn1);
        assertEquals(false, test.canStartAnalyser());

    }

    @Test
    void invalidEqn2_withMultipleTrigo_expectCanStartAnalyserAsFalse() {
        String eqn2 = "2*tantan(2*pi*x+1)-9";
        TrigoGraphAnalyser test = new TrigoGraphAnalyser(eqn2);
        assertEquals(false, test.canStartAnalyser());
    }

    @Test
    void correctStatementsWithValidEqn() {
        String eqn = "2*cos(2*pi*x+5)+1";
        TrigoGraph test = new TrigoGraph(eqn);
        TrigoGraphAnalyser analyser = new TrigoGraphAnalyser(eqn);
        assertEquals(true, analyser.canStartAnalyser());
    }
}
