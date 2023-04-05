package seedu.badmaths.Quadratic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class QuadraticParserTest {
    @Test
    void findSignOfBShouldReturnSign() {
        String toDo = "-2.5x^2 + 5x + 3";
        QuadraticParser quadraticTest = new QuadraticParser(toDo);
        String sign = QuadraticParser.findSignOfB();
        assertEquals("+",sign);
    }

    @Test
    void findStringOfBShouldReturnB() {
        String toDo = "-2.5x^2 + 5x + 3";
        QuadraticParser quadraticTest = new QuadraticParser(toDo);
        String B = QuadraticParser.findStringOfB();
        assertEquals("5", B);
    }

    @Test
    void findSignOfCShouldReturnC() {
        String toDo = "-2.5x^2 + 5x - 3";
        QuadraticParser quadraticTest = new QuadraticParser(toDo);
        String sign = QuadraticParser.findSignOfC();
        assertEquals("-", sign);
    }

    @Test
    void findAShouldReturnA() {
        String toDo = "-2.5x^2 + 5x + 3";
        QuadraticParser quadraticTest = new QuadraticParser(toDo);
        double A = QuadraticParser.findA();
        assertEquals(-2.5, A);
    }
    @Test
    void findStringOfCShouldReturnC() {
        String toDo = "-2.5x^2 + 5x + 3";
        QuadraticParser quadraticTest = new QuadraticParser(toDo);
        String C = QuadraticParser.findStringOfC();
        assertEquals("3", C);
    }

    @Test
    void findAIfOnlySignShouldReturnA() {
        String toDo = "x^2 + 5.9x + 3";
        QuadraticParser quadraticTest = new QuadraticParser(toDo);
        double A = QuadraticParser.findA();
        assertEquals(1, A);
    }
    @Test
    void findBShouldReturnB() {
        String toDo = "-2x^2 - 5.9x + 3";
        QuadraticParser quadraticTest = new QuadraticParser(toDo);
        double B = QuadraticParser.findB();
        assertEquals(-5.9, B);
    }

    @Test
    void findBIfOnlySignShouldReturnB() {
        String toDo = "-2x^2 - x + 3";
        QuadraticParser quadraticTest = new QuadraticParser(toDo);
        double B = QuadraticParser.findB();
        assertEquals(-1, B);
    }

    @Test
    void findCShouldReturnC() {
        String toDo = "-2x^2 + 5x + 3.0888";
        QuadraticParser quadraticTest = new QuadraticParser(toDo);
        double C = QuadraticParser.findC();
        assertEquals(3.0888, C);
    }
}
