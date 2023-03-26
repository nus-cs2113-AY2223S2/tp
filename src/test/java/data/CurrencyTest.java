package data;

import org.junit.jupiter.api.Test;
import org.threeten.extra.Temporals;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;


class CurrencyTest {
    public String inputDate = "07-02-2023";
    public HashMap<String, String> currencies = new HashMap<>();
    public DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    public Currency currency = new Currency();


    @Test
    public void getCurrencyAvailable_test() {
        Currency.getCurrencyAvailable(currencies);
    }

    @Test
    public void getExchangeRate_success() {
        BigDecimal rate = currency.getExchangeRate(LocalDate.parse(inputDate, formatter)
                .with(Temporals.previousWorkingDay()), "USD");
        assertEquals(new BigDecimal(1.3241)

                .setScale(5, RoundingMode.HALF_UP), rate.setScale(5, RoundingMode.HALF_UP));
        BigDecimal newrate1 = currency.getExchangeRate(LocalDate.parse(inputDate, formatter)
                .with(Temporals.previousWorkingDay()), "HKD");
        assertNotEquals(rate.setScale(5, RoundingMode.HALF_UP),
                newrate1.setScale(5, RoundingMode.HALF_UP));
        assertEquals(newrate1.setScale(5, RoundingMode.HALF_UP),
                new BigDecimal(0.16870).
                        setScale(5, RoundingMode.HALF_UP));
        BigDecimal newrate2 = currency.getExchangeRate(LocalDate.parse("02-02-2012", formatter)
                        .with(Temporals.previousWorkingDay()),
                "hkd");
        assertEquals(newrate2.setScale(5, RoundingMode.HALF_UP),
                new BigDecimal(0.16199999999999999289457264239899814128875732421875
        ).setScale(5, RoundingMode.HALF_UP));
    }

    @Test
    public void convertCurrency_success() {
        String currency = "SGD";
        String convertedCurrency = Currency.convertCurrency(currency);
        assertEquals(currency, convertedCurrency);
    }
}
