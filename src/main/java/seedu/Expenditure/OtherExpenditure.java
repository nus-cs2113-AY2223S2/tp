package seedu.Expenditure;

import java.time.LocalDate;

public class OtherExpenditure extends Expenditure {
        public OtherExpenditure(double value, LocalDate date) {
            super(value, date);
        }

        @Override
        public String toString() {
            return getDate() + Double.toString(getValue());
        }
}
