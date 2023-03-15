package chching.record;

public class Income extends Record{
    public Income(String description, String date, double value) {
        super(description, date, value);
    }

    @Override
    public String toString() {
        return  "Description - " + getDescription() +
                " | Date - " + getDate() +
                " | Value - " + String.format("%.02f", getValue());
    }
}
