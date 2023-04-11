package seedu.badmaths.matrix.exception;

import seedu.badmaths.matrix.Tensor2D;

/**
 * This class checks whether exception would be occurred.
 * This class handles four exceptions below:
 * 1. MatrixFormatException
 * 2. MatrixShapeException
 * 3. ShapeMismatchException
 * 4. UnknownOperatorException
 */
public class ExceptionChecker {

    /**
     * Check whether shapes of the two operands match each other for the matrix calculation.
     *
     * @param t1 operand1
     * @param t2 operand2
     * @param type type of the operator
     */
    public void checkShapeMismatch(Tensor2D t1, Tensor2D t2, String type) throws ShapeMismatchException {
        switch(type) {
        case "MUL":
            if(t1.column() != t2.row()) {
                throw new ShapeMismatchException();
            }
            break;
        case "DOT":
        case "ADD":
        case "SUB":
            boolean isColumnMatch = t1.column() != t2.column();
            boolean isRowMatch = t1.row() != t2.row();

            if(isColumnMatch || isRowMatch) {
                throw new ShapeMismatchException();
            }
            break;
        default:
            break;
        }
    }

    /**
     * Check whether given operator is appropriate.
     *
     * @param command given matrix expression.
     */
    public void checkUnknownOperator(String command) throws UnknownOperatorException {
        boolean isMul = command.contains("].*[");
        boolean isDot = command.contains("]*[");
        boolean isAdd = command.contains("]+[");
        boolean isSub = command.contains("]-[");

        if (!isMul && !isDot && !isAdd && !isSub) {
            throw new UnknownOperatorException();
        }
    }

    /**
     * Check whether matrix format of the given expression is appropriate.
     *
     * @param matrix given matrix expression.
     */
    public static void checkMatrixFormat(String matrix) throws MatrixShapeException, MatrixFormatException {
        boolean isRightWrapper = matrix.contains("[") && matrix.contains("]");

        if(!isRightWrapper) {
            throw new MatrixFormatException();
        }

        matrix = matrix.replace("[", "");
        matrix = matrix.replace("]", "");

        String[] rows;
        String[] column;
        int rowNum;
        int colNum;

        rows = matrix.split(";");
        rowNum = rows.length;
        colNum = rows[0].split(",").length;

        for(int i = 0; i < rowNum; i++) {
            column = rows[i].split(",");
            for(int j = 0; j < colNum; j++) {
                if(colNum != column.length) {
                    throw new MatrixShapeException();
                }
            }
        }
    }
}
