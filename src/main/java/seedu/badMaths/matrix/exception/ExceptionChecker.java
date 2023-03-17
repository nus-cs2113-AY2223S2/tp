package seedu.badMaths.matrix.exception;

import seedu.badMaths.matrix.Tensor2D;

public class ExceptionChecker {
    public void checkShapeMismatch(Tensor2D t1, Tensor2D t2) throws ShapeMismatchException{
        if(t1.column() != t2.row()){
            throw new ShapeMismatchException();
        }
    }
}
