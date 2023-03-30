package seedu.badMaths.matrix;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExecuteTest {
    String add = "[1,2;3,4]+[1,2;3,4]";
    String sub = "[1,2;3,4]-[1,2;3,4]";
    String mul = "[1,2;3,4].*[1,2;3,4]";
    String dot = "[1,2;3,4]*[1,2;3,4]";
    String transpose = "[1,2;3,4].T";

    int[][] addAnswer = new int[][]{
            {2, 4},
            {6, 8}
    };
    Tensor2D addAnswerTensor = new Tensor2D(addAnswer);

    int[][] subAnswer = new int[][]{
            {0, 0},
            {0, 0}
    };
    Tensor2D subAnswerTensor = new Tensor2D(subAnswer);

    int[][] mulAnswer = new int[][]{
            {7, 10},
            {15, 22}
    };
    Tensor2D mulAnswerTensor = new Tensor2D(mulAnswer);

    int[][] dotAnswer = new int[][]{
            {1, 4},
            {9, 16}
    };
    Tensor2D dotAnswerTensor = new Tensor2D(dotAnswer);

    int[][] transposeAnswer = new int[][]{
            {1, 3},
            {2, 4}
    };
    Tensor2D transposeAnswerTensor = new Tensor2D(transposeAnswer);

    @Test
    public void testMatrixAddition(){
        Execute e = new Execute();

        Tensor2D addResult = e.executeAdd(add);
        for(int i=0; i<2; i++){
            for(int j=0; j<2; j++){
                assertEquals(addResult.get(i, j), addAnswerTensor.get(i, j));
            }
        }
    }

    @Test
    public void testMatrixSubtraction(){
        Execute e = new Execute();

        Tensor2D subResult = e.executeSub(sub);
        for(int i=0; i<2; i++){
            for(int j=0; j<2; j++){
                assertEquals(subResult.get(i, j), subAnswerTensor.get(i, j));
            }
        }
    }


    @Test
    public void testMatrixMul(){
        Execute e = new Execute();

        Tensor2D mulResult = e.executeMul(mul);
        for(int i=0; i<2; i++){
            for(int j=0; j<2; j++){
                assertEquals(mulResult.get(i, j), mulAnswerTensor.get(i, j));
            }
        }
    }

    @Test
    public void testMatrixDot(){
        Execute e = new Execute();

        Tensor2D dotResult = e.executeDot(dot);
        for(int i=0; i<2; i++){
            for(int j=0; j<2; j++){
                assertEquals(dotResult.get(i, j), dotAnswerTensor.get(i, j));
            }
        }
    }

    @Test
    public void testMatrixTranspose(){
        Execute e = new Execute();

        Tensor2D transposeResult = e.executeTranspose(transpose);
        for(int i=0; i<2; i++){
            for(int j=0; j<2; j++){
                assertEquals(transposeResult.get(i, j), transposeAnswerTensor.get(i, j));
            }
        }
    }
}
