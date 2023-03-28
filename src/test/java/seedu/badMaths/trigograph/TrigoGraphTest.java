package seedu.badMaths.trigograph;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TrigoGraphTest {

    @Test
    void validEqn_withMinus_expectCanStartAnalyser(){
        String eqn = "cos(-1*x-1)-1";
        TrigoGraphAnalyser test = new TrigoGraphAnalyser(eqn);
        assertEquals(true,test.canStartAnalyser());
    }

    @Test
    void validEqn_returnsCorrectDetails() {
        String eqn = "2*tan(2*pi*x+1)-9";
        TrigoGraph graphTest = new TrigoGraph(eqn);
        graphTest.startGraphAnalysis();
        assertEquals(2.0,graphTest.getAmplitude());
        assertEquals("tan",graphTest.getTrig());
        assertEquals(1.0,graphTest.getFrequency());
        assertEquals(1.0,graphTest.getPhase());
        assertEquals(-9.0,graphTest.getVerticalShift());
    }

    @Test
    void invalidEqn_withMultipleTrigo_expectCanStartAnalyserAsFalse() {
        String eqn1 = "2*tan*tan(2*pi*x+1)-9";
        TrigoGraphAnalyser test = new TrigoGraphAnalyser(eqn1);
        assertEquals(false,test.canStartAnalyser());

    }

    @Test
    void invalidEqn2_withMultipleTrigo_expectCanStartAnalyserAsFalse() {
        String eqn2 = "2*tantan(2*pi*x+1)-9";
        TrigoGraphAnalyser test = new TrigoGraphAnalyser(eqn2);
        assertEquals(false,test.canStartAnalyser());
    }
}