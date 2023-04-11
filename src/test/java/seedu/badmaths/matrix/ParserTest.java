
package seedu.badmaths.matrix;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    String matrix = "[1,2;3,4]";
    String add = "[1,2;3,4] + [1,2;3,4]";
    String sub = "[1,2;3,4] - [1,2;3,4]";
    String mul = "[1,2;3,4] .* [1,2;3,4]";
    String dot = "[1,2;3,4] * [1,2;3,4]";
    String transpose = "[1,2;3,4] .* [1,2;3,4].T";

    int[][] value = new int[][]{
            {1, 2},
            {3, 4}
    };
    Tensor2D tensor = new Tensor2D(value);

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
            {5, 11},
            {11, 25}
    };
    Tensor2D transposeAnswerTensor = new Tensor2D(transposeAnswer);

    @Test
    public void testParse(){
        Parser p = new Parser();

        Tensor2D addResult = p.parse(add);
        for(int i=0; i<2; i++){
            for(int j=0; j<2; j++){
                assertEquals(addResult.get(i, j), addAnswerTensor.get(i, j));
            }
        }

        Tensor2D subResult = p.parse(sub);
        for(int i=0; i<2; i++){
            for(int j=0; j<2; j++){
                assertEquals(subResult.get(i, j), subAnswerTensor.get(i, j));
            }
        }

        Tensor2D mulResult = p.parse(mul);
        for(int i=0; i<2; i++){
            for(int j=0; j<2; j++){
                assertEquals(mulResult.get(i, j), mulAnswerTensor.get(i, j));
            }
        }

        Tensor2D dotResult = p.parse(dot);
        for(int i=0; i<2; i++){
            for(int j=0; j<2; j++){
                assertEquals(dotResult.get(i, j), dotAnswerTensor.get(i, j));
            }
        }

        Tensor2D transposeResult = p.parse(transpose);
        for(int i=0; i<2; i++){
            for(int j=0; j<2; j++){
                assertEquals(transposeResult.get(i, j), transposeAnswerTensor.get(i, j));
            }
        }
    }

    @Test
    public void testParseMatrix(){
        for(int i=0; i<2; i++){
            for(int j=0; j<2; j++){
                assertEquals(tensor.get(i, j), Parser.parseMatrix(matrix).get(i, j));
            }
        }
    }
}
