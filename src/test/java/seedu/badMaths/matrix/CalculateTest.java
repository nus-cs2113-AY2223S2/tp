package seedu.badMaths.matrix;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculateTest {
    int[][] arr = new int[][]{
        {0, 1, 2},
        {3, 4, 5},
        {6, 7, 8},
        {9, 10, 11}
    };
    Tensor2D tensor = new Tensor2D(arr);

    int[][] arrT = new int[][]{
            {0, 3, 6, 9},
            {1, 4, 7, 10},
            {2, 5, 8, 11}
    };
    Tensor2D tensorT = new Tensor2D(arrT);

    int[][] dotResult = new int[][]{
            {0, 1, 4},
            {9, 16, 25},
            {36, 49, 64},
            {81, 100, 121}
    };
    Tensor2D dotResultTensor = new Tensor2D(dotResult);

    int[][] mulResult = new int[][]{
            {5, 14, 23, 32},
            {14, 50, 86, 122},
            {23, 86, 149, 212},
            {32, 122, 212, 302}
    };
    Tensor2D mulResultTensor = new Tensor2D(mulResult);

    int[][] addResult = new int[][]{
            {0, 2, 4},
            {6, 8, 10},
            {12, 14, 16},
            {18, 20, 22}
    };
    Tensor2D addResultTensor = new Tensor2D(addResult);

    @Test
    public void testShapeStringConversion() {
        assertEquals("4 x 3", new Tensor2D(arr).shape().toString());
    }

    @Test
    public void testMatrixTranspose(){
        for(int i=0; i<tensorT.row(); i++){
            for(int j=0; j<tensorT.column(); j++){
                assertEquals(tensorT.get(i, j), tensor.t().get(i, j));
            }
        }
    }

    @Test
    public void testMatrixDot(){
        Calculate c = new Calculate();

        for(int i=0; i<tensor.row(); i++){
            for(int j=0; j<tensor.column(); j++){
                assertEquals(dotResultTensor.get(i, j), c.dot(tensor, tensor).get(i, j));
            }
        }
    }

    @Test
    public void testMatrixMul(){
        Calculate c = new Calculate();

        for(int i=0; i<tensor.row(); i++){
            for(int j=0; j<tensor.column(); j++){
                assertEquals(mulResultTensor.get(i, j), c.mul(tensor, tensor.t()).get(i, j));
            }
        }
    }

    @Test
    public void testMatrixAdd(){
        Calculate c = new Calculate();

        for(int i=0; i<tensor.row(); i++){
            for(int j=0; j<tensor.column(); j++){
                assertEquals(addResultTensor.get(i, j), c.add(tensor, tensor).get(i, j));
            }
        }
    }
}
