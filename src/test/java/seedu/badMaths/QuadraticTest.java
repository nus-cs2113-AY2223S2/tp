package seedu.badMaths;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class QuadraticTest {

    @Test
    void findAShouldReturnA() {
        String toDo = "-2x^2 + 5x + 3";
        Quadratic quadraticTest = new Quadratic(toDo);
        int A = quadraticTest.findA();
        assertEquals(-2, A);
    }
    @Test
    void findBShouldReturnB() {
        String toDo = "-2x^2 + 5x + 3";
        Quadratic quadraticTest = new Quadratic(toDo);
        int B = quadraticTest.findB();
        assertEquals(5, B);
    }
    @Test
    void findCShouldReturnC() {
        String toDo = "-2x^2 + 5x + 3";
        Quadratic quadraticTest = new Quadratic(toDo);
        int C = quadraticTest.findC();
        assertEquals(3, C);
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
