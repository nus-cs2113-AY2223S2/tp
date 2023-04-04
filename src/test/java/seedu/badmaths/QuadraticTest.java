package seedu.badmaths;

import org.junit.jupiter.api.Test;
import seedu.badmaths.Quadratic.Quadratic;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class QuadraticTest {

    @Test
    void findAShouldReturnA() {
        String toDo = "-2.5x^2 + 5x + 3";
        Quadratic quadraticTest = new Quadratic(toDo);
        double A = quadraticTest.findA();
        assertEquals(-2.5, A);
    }

    @Test
    void findAIfOnlySignShouldReturnA() {
        String toDo = "x^2 + 5.9x + 3";
        Quadratic quadraticTest = new Quadratic(toDo);
        double A = quadraticTest.findA();
        assertEquals(1, A);
    }
    @Test
    void findBShouldReturnB() {
        String toDo = "-2x^2 - 5.9x + 3";
        Quadratic quadraticTest = new Quadratic(toDo);
        double B = quadraticTest.findB();
        assertEquals(-5.9, B);
    }

    @Test
    void findBIfOnlySignShouldReturnB() {
        String toDo = "-2x^2 - x + 3";
        Quadratic quadraticTest = new Quadratic(toDo);
        double B = quadraticTest.findB();
        assertEquals(-1, B);
    }

    @Test
    void findCShouldReturnC() {
        String toDo = "-2x^2 + 5x + 3.0888";
        Quadratic quadraticTest = new Quadratic(toDo);
        double C = quadraticTest.findC();
        assertEquals(3.0888, C);
    }
    @Test
    void quadraticFormulaShouldReturnArrayOfX() {
        String toDo = "-2x^2 + 5x + 3";
        Quadratic quadraticTest = new Quadratic(toDo);
        ArrayList<Double> xStoreTest = new ArrayList<>();
        xStoreTest = quadraticTest.quadraticFormula(-2, 5, 3);
        assertEquals(-0.5,xStoreTest.get(0));
        assertEquals(3.0,xStoreTest.get(1));
    }

}
