package seedu.badmaths.matrix;

import seedu.badmaths.matrix.exception.ExceptionChecker;
import seedu.badmaths.matrix.exception.ExceptionPrinter;
import seedu.badmaths.matrix.exception.ShapeMismatchException;

import java.util.logging.Logger;

/**
 * This class calculates the various matrix calculation below:
 * 1. Matrix multiplication
 * 2. Matrix element wise product
 * 3. Matrix subtraction
 * 4. Matrix addition
 */
public class Calculate {
    ExceptionChecker check = new ExceptionChecker();
    Logger logger = Logger.getLogger("Exception");
    ExceptionPrinter ep = new ExceptionPrinter();

    /**
     * Calculate the matrix multiplication.
     *
     * @param t1 operand1
     * @param t2 operand2
     */
    public Tensor2D mul(Tensor2D t1, Tensor2D t2) {
        Shape t1Shape = t1.shape();
        Shape t2Shape = t2.shape();

        try{
            check.checkShapeMismatch(t1, t2, "MUL");

            Tensor2D t2T = t2.t();
            int[][] output = new int[t1Shape.row][t2Shape.column];

            for(int i = 0; i < t1Shape.row; i++) {
                for(int j = 0; j < t2Shape.column; j++) {
                    output[i][j] = 0;

                    for(int k = 0; k < t1Shape.column; k++) {
                        output[i][j] += t1.get(i, k) * t2T.get(j, k);
                    }
                }
            }

            return new Tensor2D(output);
        } catch (ShapeMismatchException e) {
            ep.printShapeMismatchExceptionLog();
            return null;
        }
    }

    /**
     * Calculate the matrix element wise product.
     *
     * @param t1 operand1
     * @param t2 operand2
     */
    public Tensor2D dot(Tensor2D t1, Tensor2D t2) {
        Shape t1Shape = t1.shape();
        Shape t2Shape = t2.shape();

        try {
            check.checkShapeMismatch(t1, t2, "DOT");

            int[][] output = new int[t1Shape.row][t1Shape.column];

            for(int i = 0; i < t1Shape.row; i++) {
                for(int j = 0; j < t1Shape.column; j++) {
                    output[i][j] = t1.get(i, j) * t2.get(i, j);
                }
            }

            return new Tensor2D(output);
        } catch (ShapeMismatchException e) {
            ep.printShapeMismatchExceptionLog();
            return null;
        }
    }

    /**
     * Calculate the matrix addition.
     *
     * @param t1 operand1
     * @param t2 operand2
     */
    public Tensor2D add(Tensor2D t1, Tensor2D t2) {
        Shape t1Shape = t1.shape();
        Shape t2Shape = t2.shape();

        try {
            check.checkShapeMismatch(t1, t2, "ADD");

            int[][] output = new int[t1Shape.row][t1Shape.column];

            for(int i = 0; i < t1Shape.row; i++) {
                for(int j = 0; j < t1Shape.column; j++) {
                    output[i][j] = t1.get(i, j) + t2.get(i, j);
                }
            }

            return new Tensor2D(output);
        } catch (ShapeMismatchException e) {
            ep.printShapeMismatchExceptionLog();
            return null;
        }
    }

    /**
     * Calculate the matrix subtraction.
     *
     * @param t1 operand1
     * @param t2 operand2
     */
    public Tensor2D sub(Tensor2D t1, Tensor2D t2) {
        Shape t1Shape = t1.shape();
        Shape t2Shape = t2.shape();

        try {
            check.checkShapeMismatch(t1, t2, "SUB");

            int[][] output = new int[t1Shape.row][t1Shape.column];

            for(int i = 0; i < t1Shape.row; i++) {
                for(int j = 0; j < t1Shape.column; j++) {
                    output[i][j] = t1.get(i, j) - t2.get(i, j);
                }
            }

            return new Tensor2D(output);
        } catch (ShapeMismatchException e) {
            ep.printShapeMismatchExceptionLog();
            return null;
        }
    }
}
