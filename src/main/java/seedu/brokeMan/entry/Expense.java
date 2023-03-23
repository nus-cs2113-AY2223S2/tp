package seedu.brokeMan.entry;

import java.time.LocalDateTime;

public class Expense extends Entry {
    public Expense(double amount, String info, LocalDateTime time, Category category) {
        super(amount, info, time, category);
    }

    @Override
    public String toString() {
        return String.format("$%.2f spent on %s - %s [%s]", amount, info, super.convertTimeToString(), category.name());
    }
}
