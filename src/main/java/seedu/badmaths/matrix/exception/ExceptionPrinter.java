package seedu.badmaths.matrix.exception;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class prints the corresponding exception message.
 */
public class ExceptionPrinter {
    Logger logger = Logger.getLogger("exception");

    /**
     * Print the exception message of ShapeMismatchException.
     */
    public void printShapeMismatchExceptionLog() {
        String ExceptionMessage = "";
        ExceptionMessage += "<Exception occurs>\n";
        ExceptionMessage += "There is shape mismatch between t1 and t2 : cannot execute matrix calculation.";
        System.out.println(ExceptionMessage);

        logger.log(Level.WARNING, "shape mismatch occur.");
    }

    /**
     * Print the exception message of UnknownOperatorException.
     */
    public void printUnknownOperatorExceptionLog() {
        String ExceptionMessage = "";
        ExceptionMessage += "<Exception occurs>\n";
        ExceptionMessage += "Operator in expression is unknown.";
        System.out.println(ExceptionMessage);

        logger.log(Level.WARNING, "Unknown operator.");
    }

    /**
     * Print the exception message of MatrixShapeException.
     */
    public void printMatrixShapeExceptionLog(){
        String ExceptionMessage = "";
        ExceptionMessage += "<Exception occurs>\n";
        ExceptionMessage += "Length of every rows should be the same with each other.";
        System.out.println(ExceptionMessage);

        logger.log(Level.WARNING, "shape of matrix is not correct.");
    }

    /**
     * Print the exception message of MatrixFormatException.
     */
    public void printMatrixFormatExceptionLog(){
        String ExceptionMessage = "";
        ExceptionMessage += "<Exception occurs>\n";
        ExceptionMessage += "Format of matrix is not correct.";
        System.out.println(ExceptionMessage);

        logger.log(Level.WARNING, "Format of matrix is not correct.");
    }

    /**
     * Print the exception message of NumericFormatException.
     */
    public void printMatrixNumericExceptionLog(){
        String ExceptionMessage = "";
        ExceptionMessage += "<Exception occurs>\n";
        ExceptionMessage += "Every entities of matrix should be integer.";
        System.out.println(ExceptionMessage);

        logger.log(Level.WARNING, "Format of matrix is not correct.");
    }
}
