package data;


import org.json.JSONArray;
import org.json.JSONObject;
import org.threeten.extra.Temporals;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.util.HashMap;


public class Currency {

    protected static HashMap<String, String> currencies =  new HashMap<>();
    public Currency() {
        getCurrencyAvailable(currencies);
    }
    public static HashMap<String, String> getCurrencies() {
        return currencies;
    }

    /**
     * Converts the currency to SGD if the input currency is not found in the HashMap. Else it returns the input
     * currency.
     *
     * @param currency the input currency specified by the user.
     * @return returns currency or SGD if currency is not found in exchangeRate
     */
    public static String convertCurrency(String currency) {
        // Default currency is SGD
        currencies = getCurrencies();
        if (currency == null) {
            return "SGD";
        }
        for (String key : currencies.keySet()) {
            if (key.equals(currency.toUpperCase())) {
                return key;
            }
        }
        return "SGD";
    }

    /**
     * Standardize the format of double when we add it to expenseList
     */
    public static BigDecimal roundInput(String expenseAmountInput) {
        BigDecimal roundedExpense = new BigDecimal(expenseAmountInput);
        roundedExpense = roundedExpense.setScale(2, RoundingMode.HALF_UP);
        return roundedExpense;
    }

    /**
     * Gets the types of currency available in the API and stores them in a HashMap
     *
     * @param currencies the HashMap of currencies available stored by ISO4217 and JSON key respectively
     * @throws IOException
     */
    public static void getCurrencyAvailable(HashMap<String, String> currencies) {
        String GET_URL = "https://eservices.mas.gov.sg/api/action/datastore/search.json?resource_id=95932927-c8bc-" +
                "4e7a-b484-68a66a24edfe&filters[end_of_day]=" +
                LocalDate.now().with(Temporals.previousWorkingDay()).toString() + "&limit=1";
        try {
            URL url = new URL(GET_URL);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            int responseCode = httpURLConnection.getResponseCode();
            if (responseCode == httpURLConnection.HTTP_OK) { //successful request
                BufferedReader in = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                JSONObject obj = new JSONObject(response.toString());
                JSONObject result = obj.getJSONObject("result");
                JSONArray records = result.getJSONArray("records");
                JSONObject data = records.getJSONObject(0);
                for (String key : data.keySet()) {
                    if (key.equals("end_of_day") || key.equals("preliminary") || key.equals("timestamp")) {
                        continue;
                    }
                    //stores the ISO4217 and JSON key as a key value pair
                    currencies.put(key.substring(0, 3).toUpperCase(), key);
                }
            }
        } catch (IOException e) {
            System.out.println("get failed");
        }
    }

    /**
     * Gets the exchange rate of the currency relative to SGD from the previous working day from the specified date.
     * Returns an exchange rate of 1 if specified currency cannot be found.
     * @param date the date specified in the user input.
     * @param currency the currency specified in the user input.
     * @return returns the exchange rate of that particular currency for the previous working day from the date.
     */
    public static BigDecimal getExchangeRate(String date, String currency){
        String currencyKey = convertCurrency(currency);
        if(currencyKey.equals("SGD")) {
            return new BigDecimal(1);
        }
        try {
            String GET_URL = "https://eservices.mas.gov.sg/api/action/datastore/search.json?resource_id=95932927-c8b" +
                    "c-4e7a-b484-68a66a24edfe&filters[end_of_day]=" + date + "&limit=1";
            URL url = new URL(GET_URL);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            int responseCode = httpURLConnection.getResponseCode();
            if (responseCode == httpURLConnection.HTTP_OK) { //successful request
                BufferedReader in = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                JSONObject obj = new JSONObject(response.toString());
                JSONObject result = obj.getJSONObject("result");
                JSONArray records = result.getJSONArray("records");
                JSONObject data = records.getJSONObject(0);
                BigDecimal rate = new BigDecimal(data.getDouble(currencies.get(currencyKey)));
                if (currencies.get(currencyKey).contains("100")) {
                    return rate.divide(new BigDecimal(100));
                }
                return rate;
            }
        }catch (IOException e){
            System.out.println("get failed.");
        }
        return new BigDecimal(0);
    }
}
