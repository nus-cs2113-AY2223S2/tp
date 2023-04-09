package seedu.badmaths.matrix.exception;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ExceptionPrinter {
    Logger logger = Logger.getLogger("exception");

    public void printShapeMismatchExceptionLog() {
        String ExceptionMessage = "";
        ExceptionMessage += "<Exception occurs>\n";
        ExceptionMessage += "There is shape mismatch between t1 and t2 : cannot execute matrix calculation.";
        System.out.println(ExceptionMessage);

        logger.log(Level.WARNING, "shape mismatch occur.");
    }

    public void printUnknownOperatorExceptionLog() {
        String ExceptionMessage = "";
        ExceptionMessage += "<Exception occurs>\n";
        ExceptionMessage += "Operator in expression is unknown.";
        System.out.println(ExceptionMessage);

        logger.log(Level.WARNING, "Unknown operator.");
    }

    public void printMatrixShapeExceptionLog(){
        String ExceptionMessage = "";
        ExceptionMessage += "<Exception occurs>\n";
        ExceptionMessage += "Length of every rows should be the same with each other.";
        System.out.println(ExceptionMessage);

        logger.log(Level.WARNING, "shape of matrix is not correct.");
    }

    public void printMatrixFormatExceptionLog(){
        String ExceptionMessage = "";
        ExceptionMessage += "<Exception occurs>\n";
        ExceptionMessage += "Format of matrix is not correct.";
        System.out.println(ExceptionMessage);

        logger.log(Level.WARNING, "Format of matrix is not correct.");
    }

    public void printMatrixNumericExceptionLog(){
        String ExceptionMessage = "";
        ExceptionMessage += "<Exception occurs>\n";
        ExceptionMessage += "Every entities of matrix should be integer.";
        System.out.println(ExceptionMessage);

        logger.log(Level.WARNING, "Format of matrix is not correct.");
    }
}
