package data;

import java.time.LocalDate;

public class Transform {
    // This class is used to transform the user input to a format that the computer can understand
    public static final String SLASH = "/";
    private LocalDate time;
    private double amount;

    public LocalDate getTime() {
        return this.time;
    }

    public double getAmount() {
        return this.amount;
    }

    // This method will accept the String entries and transform them back to the required type

    public <T> T transformString(String input) {
        // Obtain the information of the input command
        // Currently just thought for case add
        int slashPosition = input.indexOf(SLASH);
        String indication = input.substring(0, slashPosition);
        String description = input.substring(slashPosition);

        switch (indication) {
        case "t":
            int year = Integer.parseInt(description.substring(4));
            int month = Integer.parseInt(description.substring(2, 4));
            int day = Integer.parseInt(description.substring(0, 2));
            return (T) LocalDate.of(year, month, day);

        case "amt":
            Double amountValue = Double.parseDouble(description);
            return (T) amountValue;

        case "cur":
            //return (T) Currency.checkCurrency(description);

        default:
            return (T) description;
        }
    }

}
