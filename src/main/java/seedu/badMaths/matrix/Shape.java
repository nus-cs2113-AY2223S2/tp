package seedu.badMaths.matrix;

public class Shape {
    protected int row;
    protected int column;

    public Shape(int row, int column){
        this.row = row;
        this.column = column;
    }

    @Override
    public String toString() {
        return row + " x " + column;
    }
}
