package data;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

import org.threeten.extra.Temporals;


class CurrencyTest {
    public String inputDate = "06-02-2023";
    public HashMap<String, BigDecimal> exchangeRate = new HashMap<>();
    public DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");


    public LocalDate localDate = LocalDate.parse(inputDate, formatter).plusDays(1)
            .with(Temporals.previousWorkingDay());
    //plus 1 day as previousWorkingDay method not inclusive of the day itself

    @Test
    public void sendHTTPRequestTest() {
        try {
            Currency.sendHTTPGetRequest(localDate.toString(), exchangeRate);
        } catch (IOException e) {
            assert false;
        }

    }
}
