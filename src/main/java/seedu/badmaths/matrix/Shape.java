package seedu.badmaths.matrix;

public class Shape {
    protected int row;
    protected int column;

    public Shape(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getRow(){
        return row;
    }

    public int getColumn(){
        return column;
    }

    @Override
    public String toString() {
        return row + " x " + column;
    }
}
