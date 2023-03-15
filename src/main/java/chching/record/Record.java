package chching.record;

// Abstract class that will not be initialized as an object
public abstract class Record {
    protected String category;
    protected String description;
    protected String date;
    protected double value;

    public Record(){

    }
    public Record(String category, String description, String date, double value) {
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

    public double getValue() {
        return value;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setValue(float value) {
        this.value = value;
    }
    
    public String toString() {
        return "";
    }
}
