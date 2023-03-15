package chching.record;

public class Expense extends Record {

    public Expense(String category, String description, String date, double value) {
        super(category, description, date, value);
    }
    
    @Override
    public String toString() {
        return " Category - " + getCategory() +
                " | Description - " + getDescription() +
                " | Date - " + getDate() +
                " | Value - " + String.format("%.02f", getValue());
    }

}
