/**
 * Takes in a quadratic equation and calculates the values of x
 * Prints out the x values if real, and notifies user if its imaginary
 *
 * Contains a String toDo which is the quadratic equation
 */

package seedu.badmaths.Quadratic;
import java.util.ArrayList;
import seedu.badmaths.ui.Ui;

public class Quadratic {

    private static final int POSITIVE = 1;
    private static final int NEGATIVE = -1;
    private static final String ADDITION = "+";
    private static final String SUBTRACTION = "-";
    private static final int INDEX_MODIFIER_2 = 2;
    private static final int INDEX_MODIFIER_3 = 3;
    private static final int INDEX_MODIFIER_4 = 4;
    private static final int INDEX_MODIFIER_5 = 5;
    protected String toDo;

    public Quadratic (String toDo) {
        this.toDo = toDo;
    }

    /**
     * Identifies A from Ax^2 + Bx + C
     * @return A as a double data type
     */
    public double findA() {
        String stringA = toDo.substring(0, toDo.indexOf("x"));
        if (stringA.equals(SUBTRACTION)) {
            return NEGATIVE;
        } else {
            if (stringA.isEmpty()) {
                return POSITIVE;
            } else {
                return Double.parseDouble(stringA);
            }
        }
    }

    /**
     * Identifies the sign of B (+ or -)
     * @return the sign as a String
     */
    public String findSignOfB() {
        int startIndexOfSign = toDo.indexOf("^2") + INDEX_MODIFIER_3;
        int endIndexOfSign = toDo.indexOf("^2") + INDEX_MODIFIER_4;
        return toDo.substring(startIndexOfSign, endIndexOfSign);
    }

    public String findStringOfB() {
        int startIndexOfB = toDo.indexOf("^2") + INDEX_MODIFIER_5;
        int endIndexOfB = toDo.indexOf("x ");
        return toDo.substring(startIndexOfB, endIndexOfB);
    }

    /**
     * Identifies B from Ax^2 + Bx + C
     * @return A as a double data type
     */
    public double findB() {
        String sign = findSignOfB();
        String stringB = findStringOfB();
        if (stringB.isEmpty()) {
            if (sign.equals(ADDITION)) {
                return POSITIVE;
            } else {
                return NEGATIVE;
            }
        } else {
            if (sign.equals(ADDITION)) {
                return Double.parseDouble(stringB);
            } else {
                return Double.parseDouble(stringB) * NEGATIVE;
            }
        }
    }

    /**
     * Identifies the sign of C (+ or -)
     * @return the sign as a String
     */
    public String findSignOfC() {
        int startIndexOfSign = toDo.indexOf("x ") + INDEX_MODIFIER_2;
        int endIndexOfSign = toDo.indexOf("x ") + INDEX_MODIFIER_3;
        return toDo.substring(startIndexOfSign, endIndexOfSign);
    }

    public String findStringC() {
        int indexOfC = toDo.indexOf("x ") + INDEX_MODIFIER_4;
        return toDo.substring(indexOfC);
    }

    /**
     * Identifies C from Ax^2 + Bx + C
     * @return C as a double data type
     */
    public double findC() {
        String sign = findSignOfC();
        String stringC = findStringC();
        double C = Double.parseDouble(stringC);
        if (sign.equals(ADDITION)) {
            return C;
        } else {
            return C * NEGATIVE;
        }
    }

    /**
     * Solves the quadratic equation, Ax^2 + Bx + C, to get 2 values of x
     * @param A which is the A value
     * @param B which is the B value
     * @param C which is the C value
     * @return xStore which is an ArrayList of Doubles containing 2 elements, the two values of x
     */
    public ArrayList<Double> quadraticFormula(double A, double B, double C) {
        double x1 = (-B + Math.sqrt(B * B - 4 * A * C))/(2 * A);
        double x2 = (-B - Math.sqrt(B * B - 4 * A * C))/(2 * A);
        ArrayList<Double> xStore = new ArrayList<>();
        xStore.add(x1);
        xStore.add(x2);
        return xStore;
    }

    public void printAnswer(ArrayList<Double> xStore) {
        Ui.printQuadraticAnswer(xStore);
    }

    /**
     * Calls the above methods to find and print the answer to the quadratic equation
     * Catches possible exceptions and prints an error message
     */
    public void solveQuadratic() {
        try {
            double A = findA();
            double B = findB();
            double C = findC();
            ArrayList<Double> xStore;
            xStore = quadraticFormula(A, B, C);
            printAnswer(xStore);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            Ui.printQuadraticFormatError();
        }
    }
}
