package seedu.badmaths.matrix;


import seedu.badmaths.matrix.exception.ExceptionChecker;
import seedu.badmaths.matrix.exception.ExceptionPrinter;
import seedu.badmaths.matrix.exception.UnknownOperatorException;
import seedu.badmaths.matrix.exception.MatrixShapeException;
import seedu.badmaths.matrix.exception.MatrixFormatException;

/**
 * This class parses the given expression with extracting operands and operator.
 * It also calls the corresponding function from Execute class.
 */
public class Parser {
    static ExceptionChecker check = new ExceptionChecker();
    static ExceptionPrinter ep = new ExceptionPrinter();

    /**
     * Parse the given command and execute it with returning the output.
     *
     * @param command given matrix expression.
     */
    public Tensor2D parse(String command) {
        CalType type;
        Tensor2D result;
        Execute ex = new Execute();

        command = command.replace(" ", "");

        try {
            check.checkUnknownOperator(command);

            type = parseOp(command);
            result = ex.execute(type, command);

            return result;
        } catch (UnknownOperatorException e) {
            ep.printUnknownOperatorExceptionLog();
            return null;
        }
    }

    /**
     * Parse the given command with discovering the type of operator.
     * Return the corresponding CalType.
     *
     * @param command given matrix expression.
     */
    protected CalType parseOp(String command){
        if(command.contains("]+[")) {
            return CalType.ADDITION;
        } else if(command.contains("]-[")) {
            return CalType.SUBTRACTION;
        } else if(command.contains("].*[")) {
            return CalType.MULTIPLICATION;
        } else if(command.contains("]*[")) {
            return CalType.ELEMENT_WISE_DOT_PRODUCT;
        } else {
            return CalType.UNKNOWN;
        }
    }

    /**
     * Parse the matrix to Tensor2D class.
     *
     * @param command given matrix expression.
     */
    public static Tensor2D parseMatrix(String command) {
        int[][] tensor;
        int rowNum;
        int colNum;

        try {
            check.checkMatrixFormat(command);

            command = command.replace("[", "");
            command = command.replace("]", "");

            String[] rows;
            String[] column;

            rows = command.split(";");
            rowNum = rows.length;
            colNum = rows[0].split(",").length;

            assert rowNum == 1 || colNum == rows[1].split(",").length;

            tensor = new int[rowNum][colNum];
            for (int i = 0; i < rowNum; i++) {
                column = rows[i].split(",");
                for (int j = 0; j < colNum; j++) {
                    try {
                        tensor[i][j] = Integer.parseInt(column[j]);
                    } catch (NumberFormatException e) {
                        ep.printMatrixNumericExceptionLog();
                        return null;
                    }
                }
            }
        } catch (MatrixFormatException fe) {
            ep.printMatrixFormatExceptionLog();
            return null;
        } catch (MatrixShapeException se) {
            ep.printMatrixShapeExceptionLog();
            return null;
        }

        return new Tensor2D(tensor);
    }

    enum CalType {
        ADDITION, SUBTRACTION, MULTIPLICATION, ELEMENT_WISE_DOT_PRODUCT, UNKNOWN
    }
}
