package seedu.badMaths;
import java.util.ArrayList;

public class Quadratic {

    protected String toDo;
    public Quadratic (String toDo) {
        this.toDo = toDo;
    }

    public int findA() {
        String stringA = toDo.substring(0, toDo.indexOf("x"));
        return Integer.parseInt(stringA);
    }

    public int findB() {
        String sign = toDo.substring(toDo.indexOf("^2") + 3, toDo.indexOf("^2") + 4);
        String stringB = toDo.substring(toDo.indexOf("^2") + 5, toDo.indexOf("x "));
        int B = Integer.parseInt(stringB);
        if (sign.equals("+")) {
            return B;
        } else {
            return B*(-1);
        }
    }

    public int findC() {
        String sign = toDo.substring(toDo.indexOf("x ") + 2, toDo.indexOf("x ") + 3);
        String stringC = toDo.substring(toDo.indexOf("x ") + 4);
        int C = Integer.parseInt(stringC);
        if (sign.equals("+")) {
            return C;
        } else {
            return C*(-1);
        }
    }

    public ArrayList<Double> quadraticFormula(int A, int B, int C) {
        double x1 = (-B + Math.sqrt(B*B - 4*A*C))/(2*A);
        double x2 = (-B - Math.sqrt(B*B - 4*A*C))/(2*A);
        ArrayList<Double> xStore = new ArrayList<>();
        xStore.add(x1);
        xStore.add(x2);
        return xStore;
    }

    public void printAnswer(ArrayList<Double> xStore) {
        if (xStore.get(0).isNaN() || xStore.get(1).isNaN()) {
            System.out.println("x is imaginary.");
        } else {
            System.out.println("x1 = " + xStore.get(0) + " , x2 = " + xStore.get(1));
        }
    }

    public void solveQuadratic() {
        try {
            int A = findA();
            int B = findB();
            int C = findC();
            ArrayList<Double> xStore = new ArrayList<>();
            xStore = quadraticFormula(A, B, C);
            printAnswer(xStore);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            System.out.println("Please use the format as shown below:");
            System.out.println("ax^2 + bx + c");;
        }
    }
}