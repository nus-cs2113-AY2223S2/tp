package chching.record;

// Abstract class that will not be initialized as an object
public abstract class Record {
    protected String description;
    protected String date;
    protected double value;

    public Record(){
    }

    public Record(String description, String date, double value) {
        this.description = description;
        this.date = date;
        this.value = value;

    }

    public String getCategory() {
        return "";
    }

    public String getDescription() {
        return description;
    }

    public String getDate() {
        return date;
    }

    public double getValue() {
        return value;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setValue(double value) {
        this.value = value;
    }
    public String toString() {
        return "";
    }
}
