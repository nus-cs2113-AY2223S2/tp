package seedu.badmaths.Quadratic;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class QuadraticTest {

    @Test
    void quadraticFormulaShouldReturnArrayOfX() {
        String toDo = "-2x^2 + 5x + 3";
        Quadratic quadraticTest = new Quadratic(toDo);
        ArrayList<Double> xStoreTest = new ArrayList<>();
        xStoreTest = quadraticTest.quadraticFormula(-2, 5, 3);
        assertEquals(-0.5,xStoreTest.get(0));
        assertEquals(3.0,xStoreTest.get(1));
    }

    @Test
    void minMaxPointFinderShouldReturnVertexCoordinate() {
        String toDo = "-x^2 + x + 1";
        double A = -1;
        double B = 1;
        double C = 1;
        Quadratic quadraticTest = new Quadratic(toDo);
        String coordinate = quadraticTest.minMaxPointFinder(A, B, C);
        assertEquals("(0.5, 1.25)", coordinate);
    }

}
