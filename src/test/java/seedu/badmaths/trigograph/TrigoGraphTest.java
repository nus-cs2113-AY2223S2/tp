package seedu.badmaths.trigograph;

import org.junit.jupiter.api.Test;
import seedu.badmaths.IllegalTodoException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TrigoGraphTest {
    @Test
    void invalidEqnWithMultipleBrackets() {
        String eqn = "2*((cos(2*x+1)))";
        TrigoGraph test = new TrigoGraph(eqn);
        assertThrows(IllegalTodoException.class,()->{
            test.startGraphAnalysis();
        });
    }
    @Test
    void eqnWithTrigoAsSin(){
        String eqn = "2*sin(2*x-1)+1";
        TrigoGraph test = new TrigoGraph(eqn);
        try {
            test.startGraphAnalysis();
            assertEquals(2.0,test.getAmplitude());
            assertEquals(2/(2*Math.PI),test.getFrequency());
            assertEquals(-1.0,test.getPhase());
            assertEquals(1.0,test.getVerticalShift());
            assertEquals("sin",test.getTrig());
        } catch (IllegalTodoException e) {
            System.out.println("JUnit test for integrateTestForTrigoGraph failed.");
        }
    }

    @Test
    void emptyEqnExpectIllegalTodoException(){
        String eqn = "";
        TrigoGraph test = new TrigoGraph(eqn);
        assertThrows(IllegalTodoException.class,()->{
            test.startGraphAnalysis();
        });
    }

    @Test
    void integrateTestForTrigoGraph(){
        String eqn = "1*cos(8*x+8)-1";
        TrigoGraph test = new TrigoGraph(eqn);
        try {
            test.startGraphAnalysis();
            assertEquals(1.0,test.getAmplitude());
            assertEquals(8/(2*Math.PI),test.getFrequency());
            assertEquals(8.0,test.getPhase());
            assertEquals(-1.0,test.getVerticalShift());
            assertEquals("cos",test.getTrig());
        } catch (IllegalTodoException e) {
            System.out.println("JUnit test for integrateTestForTrigoGraph failed.");
        }
    }

    @Test
    void validEqn_withMinusFreq_expectCannotStartAnalyser() {
        String eqn = "cos(-1*x-1)-1";
        TrigoGraphAnalyser test = new TrigoGraphAnalyser(eqn);
        assertEquals(false, test.canStartAnalyser());
    }

    @Test
    void validEqn_returnsCorrectDetails() {
        String eqn = "2*tan(2*pi*x+1)-9";
        TrigoGraph graphTest = new TrigoGraph(eqn);
        try {
            graphTest.startGraphAnalysis();
            assertEquals(2.0, graphTest.getAmplitude());
            assertEquals("tan", graphTest.getTrig());
            assertEquals(1.0, graphTest.getFrequency());
            assertEquals(1.0, graphTest.getPhase());
            assertEquals(-9.0, graphTest.getVerticalShift());
        }catch (IllegalTodoException e){
            System.out.println("JUnit test for validEqn_returnsCorrectDetails failed.");
        }
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
