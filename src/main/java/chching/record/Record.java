package chching.record;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import chching.ChChingException;

/**
 * Models an abstract class that act as a template for Expense and Income
 */
public abstract class Record {
    protected String description;
    protected LocalDate date;
    protected double value;

    /**
     * Constructor template for Expense and Income classes
     *
     * @param description String description
     * @param date        String of the date
     * @param value       value of the income/expense
     */
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

    public void setDescription(String description) throws ChChingException {
        if (description.trim().equals("")) {
            throw new ChChingException("Description cannot be empty");
        }
        this.description = description;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String toString() {
        return "";
    }
}
