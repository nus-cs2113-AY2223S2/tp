package seedu.brokeMan.entry;

import java.time.LocalDateTime;
import java.time.Month;

public abstract class Entry {
    protected String info;
    protected double amount;
    protected LocalDateTime time;
    protected Category category;

    public Entry(double amount, String info, LocalDateTime time, Category category) {
        this.amount = amount;
        this.info = info;
        this.time = time;
        this.category = category;
    }

    /**
     * Edits amount of the entry
     *
     * @param newAmount New desired amount of the entry
     */
    public void editAmount(double newAmount) {
        this.amount = newAmount;
    }

    /**
     * Edits information entry of the entry
     *
     * @param newInfo New desired information of the entry
     */
    public void editDescription(String newInfo) {
        this.info = newInfo;
    }

    /**
     * Edits time entry of the entry
     *
     * @param newTime New desired time of the entry
     */
    public void editTime(LocalDateTime newTime) {
        this.time = newTime;
    }

    /**
     * Edits category entry of the entry
     * @param newCategory
     */
    public void editCategory(Category newCategory) {this.category = newCategory; }

    /**
     * Returns the amount attribute of the entry
     *
     * @return double that represents the amount of the entry
     */
    public double getAmount() {
        return this.amount;
    }

    /**
     * Returns the time attribute of the entry
     *
     * @return String that represents the time of the entry
     */
    public String getTime() { return this.time.toString(); }

    /**
     * Returns the category attribute of the entry
     *
     * @return Category that represents the category of the entry
     */
    public Category getCategory() { return this.category; }


    public String getInfo() {
        return this.info;
    }

    /**
     * Converts time attribute of the entry into a string that can be shown to the user
     *
     * @return String representation of the time attribute of the entry, shown in form 'date @ time'
     */
    protected String convertTimeToString() {
        String timeAsString = this.time.toString();
        int indexOfT = timeAsString.indexOf('T');
        String dateString = timeAsString.substring(0, indexOfT);
        String timeString = timeAsString.substring(indexOfT + 1);
        return String.format("%s @ %s", dateString, timeString);
    }

    /**
     * Checks if the entry is made at the same month specified by the parameter
     *
     * @param year Year of interest
     * @param month Month of interest
     * @return Boolean value that represents if the entry is made at the same date as specified by the parameter
     */
    protected boolean isSameMonth(int year, Month month) {
        return (this.time.getYear() == year && this.time.getMonth().equals(month));
    }

    public abstract String toString();


}
