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
}
