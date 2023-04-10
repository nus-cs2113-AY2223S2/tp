package seedu.expenditure;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import static seedu.expenditure.CurrencyValue.sgdConversion;

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

    public void setDescriptionValueDate(String description, double value, LocalDate date) {
        this.description = description;
        this.value = value;
        this.date = date;
    }

    public double getValue() {
        return value;
    }

    public double getConvertedValue(String currency) {
        return sgdConversion(value, currency);
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
     * Returns string in the same format as toString, but accounts for currency
     * conversion.
     * If currency is irrelevent input field should be "SGD".
     * 
     * @param currency
     * @return
     */
    public String expenditureString(String currency) {
        return String.format("Date: %s || Value: %.2f || Description: %s",
                getFullDate(), getConvertedValue(currency), getDescription());
    }

    public abstract String getPaidIcon();

    /**
     * @return String representing the type of expenditure
     */
    public abstract String getExpenditureType();

    /**
     * Outputs a formatted String containing information of the task saved in a text
     * file.
     * 
     * @return String containing information of the expenditure.
     */
    public abstract String saveInfo();
}
