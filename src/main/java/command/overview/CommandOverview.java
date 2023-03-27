package command.overview;

import command.Command;
import command.CommandRes;
import data.Expense;
import exception.FutureDateException;
import utils.MonthFilter;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Locale;

public class CommandOverview extends Command {

    private String month;
    private String year;
    private ArrayList<Expense> expenses;

    private static final String COMMAND_NAME = "overview";
    private static final String MONTH_NAME_ERROR = "Incorrect month name. Please key in the full english month name.";
    private static final String FUTURE_MONTH_ERROR = "Invalid month. Please input a month before ";
    private static final String NO_SPECIFIC_MONTH_ERROR =
            "Please specify the month and year of the overview you intend to view.";
    private static final String WHITE_SPACE = " ";




    public CommandOverview(ArrayList<Expense> expenses, String month, String year) throws ArrayIndexOutOfBoundsException{
        super(COMMAND_NAME);
        this.expenses = expenses;
        this.month = month;
        this.year = year;
    }

    private boolean isMonthlyOverview() {
        return month != null;
    }

    // TODO: move over to Time
    private Integer getCurrentMonth() {
        return LocalDate.now().getMonthValue();
    }

    private Integer getCurrentYear() {
        return LocalDate.now().getYear();
    }

    private boolean isFutureYear() {
        return Integer.parseInt(year) > getCurrentYear();
    }

    private boolean isFutureMonth() {
        return Integer.parseInt(year) == getCurrentYear()
                && Month.valueOf(month.toUpperCase()).getValue() > getCurrentMonth();
    }

    @Override
    public CommandRes execute() {
        try {
            if (isFutureYear() | isFutureMonth()) {
                throw new FutureDateException();
            }
            if (isMonthlyOverview()) {
                new MonthlyOverview(expenses, month, year).printOverview();
            } else { // yearly overview
                new YearlyOverview();
            }
        } catch (IllegalArgumentException | NullPointerException e) {
            System.out.println(MONTH_NAME_ERROR);
        } catch (FutureDateException e) {
            System.out.println(FUTURE_MONTH_ERROR + Month.of(getCurrentMonth() + 1) +
                    WHITE_SPACE + getCurrentYear());
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(NO_SPECIFIC_MONTH_ERROR);
        }
        return null;
    }

}
