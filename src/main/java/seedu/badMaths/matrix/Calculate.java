package seedu.badMaths.matrix;

import seedu.badMaths.matrix.exception.ExceptionChecker;
import seedu.badMaths.matrix.exception.ShapeMismatchException;

import java.util.logging.Logger;

public class Calculate {
    ExceptionChecker check = new ExceptionChecker();
    Logger logger = Logger.getLogger("Exception");
    Ui ui = new Ui();

    // TODO : Implement exception related to tensor's shape mismatch
    public Tensor2D mul(Tensor2D t1, Tensor2D t2){
        Shape t1Shape = t1.shape();
        Shape t2Shape = t2.shape();

        try{
            check.checkShapeMismatch(t1, t2);
        }catch (ShapeMismatchException e){
            ui.printShapeMismatchExceptionLog();
            return null;
        }

        Tensor2D t2T = t2.t();
        int[][] output = new int[t1Shape.row][t2Shape.column];

        for(int i=0; i<t1Shape.row; i++){
            for(int j=0; j<t2Shape.column; j++){
                output[i][j] = 0;

                for(int k=0; k<t1Shape.column; k++){
                    output[i][j] += t1.get(i, k) * t2T.get(j, k);
                }
            }
        }

        return new Tensor2D(output);
    }

    // TODO : Implement exception related to tensor's shape mismatch
    public Tensor2D dot(Tensor2D t1, Tensor2D t2){
        Shape t1Shape = t1.shape();
        Shape t2Shape = t2.shape();

        int[][] output = new int[t1Shape.row][t1Shape.column];

        for(int i=0; i<t1Shape.row; i++){
            for(int j=0; j<t1Shape.column; j++){
                output[i][j] = t1.get(i, j) * t2.get(i, j);
            }
        }

        return new Tensor2D(output);
    }

    // TODO : Implement exception related to tensor's shape mismatch
    public Tensor2D add(Tensor2D t1, Tensor2D t2){
        Shape t1Shape = t1.shape();
        Shape t2Shape = t2.shape();

        int[][] output = new int[t1Shape.row][t1Shape.column];

        for(int i=0; i<t1Shape.row; i++){
            for(int j=0; j<t1Shape.column; j++){
                output[i][j] = t1.get(i, j) + t2.get(i, j);
            }
        }

        return new Tensor2D(output);
    }
}
