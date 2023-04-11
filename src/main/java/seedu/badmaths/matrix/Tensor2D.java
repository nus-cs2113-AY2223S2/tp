package seedu.badmaths.matrix;

/**
 * Representation of the 2D matrix class.
 */
public class Tensor2D extends Tensor {
    protected int[][] tensor;
    protected Shape shape;

    /**
     * Create new Tensor2D class with the specified configuration.
     *
     * @param tensor 2D array of integer which is the value of the matrix.
     */
    public Tensor2D(int[][] tensor){
        this.tensor = tensor;
        shape = new Shape(tensor.length, tensor[0].length);
    }

    /**
     * Return the shape of the matrix.
     */
    public Shape shape(){
        return shape;
    }

    /**
     * Return the number of row of matrix.
     */
    public int row(){
        return shape.getRow();
    }

    /**
     * Return the number of column of matrix.
     */
    public int column(){
        return shape.getColumn();
    }

    /**
     * Return the value of the matrix.
     */
    public int[][] tensor(){
        return this.tensor;
    }

    /**
     * Return the value of the given index within the matrix.
     */
    public int get(int row, int column){
        return tensor[row][column];
    }

    /**
     * Return the transposed matrix.
     */
    public Tensor2D t(){
        int row = shape.row;
        int column = shape.column;

        int[][] transposeTensor = new int[column][row];

        for(int i = 0; i < column; i++) {
            for(int j = 0; j < row; j++) {
                transposeTensor[i][j] = tensor[j][i];
            }
        }

        return new Tensor2D(transposeTensor);
    }

    /**
     * Print the configuration of the Tensor2D.
     */
    @Override
    public String toString() {
        int row = shape.row;
        int column = shape.column;

        StringBuilder str = new StringBuilder("");
        str.append("    1. shape : ").append(row).append(" x ").append(column).append("\n");
        str.append("    2. value : \n");

        for(int i = 0; i < row; i++) {
            str.append("        ").append(i).append(") ");
            for(int j = 0; j < column; j++){
                str.append(tensor[i][j]).append(" ");
            }
            str.append("\n");
        }

        return str.toString();
    }
}
