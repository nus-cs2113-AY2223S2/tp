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

    protected static String toDo;

    public Quadratic (String toDo) {
        Quadratic.toDo = toDo;
    }

    /**
     * Solves the quadratic equation, Ax^2 + Bx + C, to get 2 values of x
     * @param A which is the A value
     * @param B which is the B value
     * @param C which is the C value
     * @return xStore which is an ArrayList of Doubles containing 2 elements, the two values of x
     */
    public ArrayList<Double> quadraticFormula(double A, double B, double C) {
        double x1 = (-B + Math.sqrt(B * B - 4 * A * C)) / (2 * A);
        double x2 = (-B - Math.sqrt(B * B - 4 * A * C)) / (2 * A);
        ArrayList<Double> xStore = new ArrayList<>();
        xStore.add(x1);
        xStore.add(x2);
        return xStore;
    }

    /**
     * Finds the coordinates of the minimum or maximum point of the quadratic equation, Ax^2 + Bx + C
     * @param A which is the A value
     * @param B which is the B value
     * @param C which is the C value
     * @return the coordinates in the form of a string: (x,y)
     */
    public String minMaxPointFinder(double A, double B, double C) {
        double xCoordinate = (-B) / (2 * A);
        double yCoordinate = (A * (xCoordinate * xCoordinate)) + (B * xCoordinate) + C;
        String stringofXCoor = Double.toString(xCoordinate);
        String stringOfYCoor = Double.toString(yCoordinate);
        return "(" + stringofXCoor + (", ") + stringOfYCoor + ")";
    }

    public void printAnswer(ArrayList<Double> xStore, String vertextCoordinate, boolean isMinimum) {
        Ui.printQuadraticAnswer(xStore, vertextCoordinate, isMinimum);
    }

    /**
     * Calls the above methods to find and print the answer to the quadratic equation
     * Catches possible exceptions and prints an error message
     */
    public void solveQuadratic() {
        try {
            double A = QuadraticParser.findA();
            double B = QuadraticParser.findB();
            double C = QuadraticParser.findC();
            boolean isMinimum;
            if (A > 0) {
                isMinimum = true;
            } else {
                isMinimum = false;
            }
            ArrayList<Double> xStore;
            xStore = quadraticFormula(A, B, C);
            String vertexCoordinate = minMaxPointFinder(A, B, C);
            printAnswer(xStore, vertexCoordinate, isMinimum);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            Ui.printQuadraticFormatError();
        }
    }
}
