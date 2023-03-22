package chching.record;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

// Abstract class that will not be initialized as an object
public abstract class Record {
    protected String description;
    protected LocalDate date;
    protected double value;

    public Record(String description, LocalDate date, double value) {
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

    public LocalDate getDate() {
        return date;
    }

    public String getDateString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-uuuu");
        return date.format(formatter);
    }

    public double getValue() {
        return value;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setValue(float value) {
        this.value = value;
    }
    
    public String toString() {
        return "";
    }
}
