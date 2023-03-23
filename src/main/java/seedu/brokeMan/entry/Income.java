package seedu.brokeMan.entry;

import java.time.LocalDateTime;

public class Income extends Entry{
    public Income(double amount, String info, LocalDateTime time, Category category) {
        super(amount, info, time, category);
    }

    public String toString() {
        return String.format("$%.2f earned on %s - %s [%s]", amount, info, super.convertTimeToString(), category.name());
    }
}
