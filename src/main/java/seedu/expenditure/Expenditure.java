package seedu.expenditure;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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

    public String getFullDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM yyyy");
        return getDate().format(formatter);
    }

    public String toString() {
        return String.format("Date: %s || Value: %s || Description: %s", getFullDate(), getValue(), getDescription());
    }
    /**
     * @return String representing the type of expenditure
     */
    public abstract String getExpenditureType();

    /**
     * Outputs a formatted String containing information of the task saved in a text file.
     * Overridden for expenditure types with extra descriptions
     * @return String containing information of the expenditure.
     */
    //override not done for special expenditures
    public abstract String saveInfo();
}
