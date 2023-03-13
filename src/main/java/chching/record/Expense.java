package chching.record;

public class Expense extends Record {

    public Expense(String category, String description, String date, float value) {
        this.category = category;
        this.description = description;
        this.date = date;
        this.value = value;
    }

}
