package seedu.badmaths.matrix;

public class Tensor2D extends Tensor {
    protected int[][] tensor;
    protected Shape shape;

    public Tensor2D(int[][] tensor){
        this.tensor = tensor;
        shape = new Shape(tensor.length, tensor[0].length);
    }

    public Shape shape(){
        return shape;
    }

    public int row(){
        return shape.getRow();
    }

    public int column(){
        return shape.getColumn();
    }

    public int[][] tensor(){
        return this.tensor;
    }

    public int get(int row, int column){
        return tensor[row][column];
    }

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
