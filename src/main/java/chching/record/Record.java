package chching.record;

public abstract class Record {
    protected String category;
    protected String description;
    protected String date;
    protected float value;

    /**
     * Generic constructor for Record class.
     * @param category string of record category, accepts "income" or "expense"
     * @param description string of record description
     * @param date string of record date
     * @param value float of record amount
     */
    public Record(){

    }
    public Record(String category, String description, String date, float value) {
        this.category = category;
        this.description = description;
        this.date = date;
        this.value = value;

    }

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public String getDate() {
        return date;
    }

    public float getValue() {
        return value;
    }

    public String toString() {
        return "Category - " + getCategory() +
                " | Description - " + getDescription() +
                " | Date - " + getDate() +
                " | Value - " + getValue();
    }
}
