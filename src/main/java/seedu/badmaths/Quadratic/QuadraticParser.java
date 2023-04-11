package seedu.badmaths.Quadratic;

public class QuadraticParser extends Quadratic {

    private static final int POSITIVE = 1;
    private static final int NEGATIVE = -1;
    private static final String ADDITION = "+";
    private static final String SUBTRACTION = "-";
    private static final int INDEX_MODIFIER_2 = 2;
    private static final int INDEX_MODIFIER_3 = 3;
    private static final int INDEX_MODIFIER_4 = 4;
    private static final int INDEX_MODIFIER_5 = 5;

    public QuadraticParser(String toDo) {
        super(toDo);
    }

    /**
     * Identifies A from Ax^2 + Bx + C
     * @return A as a double data type
     */
    public static double findA() {
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
    public static String findSignOfB() {
        int startIndexOfSign = toDo.indexOf("^2") + INDEX_MODIFIER_3;
        int endIndexOfSign = toDo.indexOf("^2") + INDEX_MODIFIER_4;
        return toDo.substring(startIndexOfSign, endIndexOfSign);
    }

    public static String findStringOfB() {
        int startIndexOfB = toDo.indexOf("^2") + INDEX_MODIFIER_5;
        int endIndexOfB = toDo.indexOf("x ");
        return toDo.substring(startIndexOfB, endIndexOfB);
    }

    /**
     * Identifies B from Ax^2 + Bx + C
     * @return A as a double data type
     */
    public static double findB() {
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
    public static String findSignOfC() {
        int startIndexOfSign = toDo.indexOf("x ") + INDEX_MODIFIER_2;
        int endIndexOfSign = toDo.indexOf("x ") + INDEX_MODIFIER_3;
        return toDo.substring(startIndexOfSign, endIndexOfSign);
    }

    public static String findStringOfC() {
        int indexOfC = toDo.indexOf("x ") + INDEX_MODIFIER_4;
        return toDo.substring(indexOfC);
    }

    /**
     * Identifies C from Ax^2 + Bx + C
     * @return C as a double data type
     */
    public static double findC() {
        String sign = findSignOfC();
        String stringC = findStringOfC();
        double C = Double.parseDouble(stringC);
        if (sign.equals(ADDITION)) {
            return C;
        } else {
            return C * NEGATIVE;
        }
    }
}
