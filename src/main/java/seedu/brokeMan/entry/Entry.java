package seedu.brokeMan.entry;

import java.time.LocalDateTime;

public abstract class Entry {
    protected String info;
    protected double amount;
    protected LocalDateTime time;

    public Entry(double amount, String info, String time) {
        this.amount = amount;
        this.info = info;
        this.time = StringToTime.convertStringToTime(time);
    }

    /**
     * Edits amount of the expense/income
     *
     * @param newAmount New desired amount of the expense/income
     */
    public void editAmount(double newAmount) {
        this.amount = newAmount;
    }

    /**
     * Edits information entry of the expense/income
     *
     * @param newInfo New desired information of the expense/income
     */
    public void editInfo(String newInfo) {
        this.info = newInfo;
    }

    /**
     * Edits time entry of the expense/income
     *
     * @param newTime New desired time of the expense/income
     */
    public void editTime(LocalDateTime newTime) {
        this.time = newTime;
    }

    public double getAmount() {
        return this.amount;
    }

    public String getTime() { return this.time.toString(); }

    public abstract String toString();
}
