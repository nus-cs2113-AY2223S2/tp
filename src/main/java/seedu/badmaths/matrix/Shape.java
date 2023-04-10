package seedu.badmaths.matrix;

/**
 * Representation of the shape of the matrix.
 */
public class Shape {
    protected int row;
    protected int column;

    /**
     * Create new shape class with the specified configuration.
     *
     * @param row the number of the row of 2D matrix.
     * @param column the number of the column of 2D matrix.
     */
    public Shape(int row, int column) {
        this.row = row;
        this.column = column;
    }

    /**
     * Return the row.
     */
    public int getRow(){
        return row;
    }

    /**
     * Return the column.
     */
    public int getColumn(){
        return column;
    }

    /**
     * Print the configuration of the Shape class.
     */
    @Override
    public String toString() {
        return row + " x " + column;
    }
}
