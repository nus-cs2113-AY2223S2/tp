package chching.record;

public class Income extends Record{
    public Income(String description, String date, double value) {
        this.description = description;
        this.date = date;
        this.value = value;
    }
    @Override
    public String toString() {
        return  " Description - " + getDescription() +
                " | Date - " + getDate() +
                " | Value - " + getValue();
    }
}
