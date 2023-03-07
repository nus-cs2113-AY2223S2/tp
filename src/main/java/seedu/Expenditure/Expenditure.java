package seedu.Expenditure;

import java.time.LocalDate;

public abstract class Expenditure {
    private double value;
    private LocalDate date;

    public Expenditure(double value, LocalDate date) {
        setValue(value);
        setDate(date);
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

    public abstract String toString();

}
