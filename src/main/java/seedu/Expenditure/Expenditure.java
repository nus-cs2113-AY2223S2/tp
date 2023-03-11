package seedu.Expenditure;

import java.time.LocalDate;

public abstract class Expenditure {
    private String description;
    private double value;
    private LocalDate date;

    public Expenditure(String description, double value, LocalDate date) {
        setDescription(description);
        setValue(value);
        setDate(date);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public void setValue(double value) {
        this.value = value;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getValue() {
        return value;
    }

    public LocalDate getDate() {
        return date;
    }

    public String toString() {
        return String.format("Date: %s || Value: %s || Description: %s", getDate(), getValue(), getDescription());
    }

}
