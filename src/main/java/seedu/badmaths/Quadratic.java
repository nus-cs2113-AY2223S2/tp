package seedu.badmaths;
import java.util.ArrayList;
import seedu.badmaths.ui.Ui;

public class Quadratic {

    protected String toDo;
    public Quadratic (String toDo) {
        this.toDo = toDo;
    }

    public double findA() {
        String stringA = toDo.substring(0, toDo.indexOf("x"));
        if (stringA.equals("-")) {
            return -1;
        } else {
            if (stringA.isEmpty()) {
                return 1;
            } else {
                return Double.parseDouble(stringA);
            }
        }
    }

    public double findB() {
        String sign = toDo.substring(toDo.indexOf("^2") + 3, toDo.indexOf("^2") + 4);
        String stringB = toDo.substring(toDo.indexOf("^2") + 5, toDo.indexOf("x "));
        if (stringB.isEmpty()) {
            if (sign.equals("+")) {
                return 1;
            } else {
                return -1;
            }
        } else {
            if (sign.equals("+")) {
                return Double.parseDouble(stringB);
            } else {
                return Double.parseDouble(stringB) * (-1);
            }
        }
    }

    public double findC() {
        String sign = toDo.substring(toDo.indexOf("x ") + 2, toDo.indexOf("x ") + 3);
        String stringC = toDo.substring(toDo.indexOf("x ") + 4);
        double C = Double.parseDouble(stringC);
        if (sign.equals("+")) {
            return C;
        } else {
            return C*(-1);
        }
    }

    public ArrayList<Double> quadraticFormula(double A, double B, double C) {
        double x1 = (-B + Math.sqrt(B*B - 4*A*C))/(2*A);
        double x2 = (-B - Math.sqrt(B*B - 4*A*C))/(2*A);
        ArrayList<Double> xStore = new ArrayList<>();
        xStore.add(x1);
        xStore.add(x2);
        return xStore;
    }

    public void printAnswer(ArrayList<Double> xStore) {
        Ui.printQuadraticAnswer(xStore);
    }

    public void solveQuadratic() {
        try {
            double A = findA();
            double B = findB();
            double C = findC();
            ArrayList<Double> xStore = new ArrayList<>();
            xStore = quadraticFormula(A, B, C);
            printAnswer(xStore);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            Ui.printQuadraticFormatError();
        }
    }
}
