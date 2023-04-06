package seedu.badmaths.matrix.exception;

import seedu.badmaths.matrix.Tensor2D;

public class ExceptionChecker {
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

    public void checkUnknownOperator(String command) throws UnknownOperatorException {
        boolean isMul = command.contains(".*");
        boolean isDot = command.contains("*");
        boolean isAdd = command.contains("+");
        boolean isSub = command.contains("-");

        if(!isMul && !isDot && !isAdd && !isSub) {
            throw new UnknownOperatorException();
        }
    }
}
