package data;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

    protected static HashMap<String, String> currencies = new HashMap<>();
    protected static HashMap<String, BigDecimal> offlineExchangeRate = new HashMap<>();

    public Currency() {
        generateOfflineRates();
        getCurrencyAvailable(currencies);
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
     * Standardize the format of double when we add it to account
     */
    public static BigDecimal roundInput(String expenseAmountInput) {
        BigDecimal roundedExpense = new BigDecimal(expenseAmountInput);
        roundedExpense = roundedExpense.setScale(2, RoundingMode.HALF_UP);
        return roundedExpense;
    }

    /**
     * Instantiates the key and API key pairs of the types of currency available and stores them in a HashMap
     *
     * @param currencies the HashMap of currencies available stored by ISO4217 and JSON key respectively
     */
    public static void getCurrencyAvailable(HashMap<String, String> currencies) {
        currencies.put("EUR", "eur_sgd");
        currencies.put("GBP", "gbp_sgd");
        currencies.put("USD", "usd_sgd");
        currencies.put("AUD", "aud_sgd");
        currencies.put("CAD", "cad_sgd");
        currencies.put("CNY", "cny_sgd_100");
        currencies.put("HKD", "hkd_sgd_100");
        currencies.put("INR", "inr_sgd_100");
        currencies.put("IDR", "idr_sgd_100");
        currencies.put("JPY", "jpy_sgd_100");
        currencies.put("KRW", "krw_sgd_100");
        currencies.put("MYR", "myr_sgd_100");
        currencies.put("TWD", "twd_sgd_100");
        currencies.put("NZD", "nzd_sgd");
        currencies.put("PHP", "php_sgd_100");
        currencies.put("QAR", "qar_sgd_100");
        currencies.put("SAR", "sar_sgd_100");
        currencies.put("CHF", "chf_sgd");
        currencies.put("THB", "thb_sgd_100");
        currencies.put("AED", "aed_sgd_100");
        currencies.put("VND", "vnd_sgd_100");
    }

    /**
     * Gets the exchange rate of the currency relative to SGD from the previous working day from the specified date.
     * Returns an exchange rate of 1 if specified currency cannot be found.
     *
     * @param date     the date specified in the user input.
     * @param currency the currency specified in the user input.
     * @return returns the exchange rate of that particular currency for the previous working day from the date.
     */
    public static BigDecimal getExchangeRate(LocalDate date, String currency) {
        String currencyKey = convertCurrency(currency);
        if (currencyKey.equals("SGD")) {
            return new BigDecimal(1);
        }
        try {
            String GET_URL = "https://eservices.mas.gov.sg/api/action/datastore/search.json?resource_id=95932927-c8b" +
                    "c-4e7a-b484-68a66a24edfe&filters[end_of_day]=" + date.toString() + "&limit=1";
            URL url = new URL(GET_URL); //converts the string into a URL class
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET"); //open a HTTP connection and set a 'get' request
            int responseCode = httpURLConnection.getResponseCode();
            if (responseCode == httpURLConnection.HTTP_OK) { //successful request
                BufferedReader in = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                BigDecimal rate;
                JSONObject obj = new JSONObject(response.toString()); //Parses the data into a JSON object
                JSONObject result = obj.getJSONObject("result");
                JSONArray records = result.getJSONArray("records");
                if (records.isEmpty()) {
                    //recursively searches for previous day data if current day data is unavailable
                    return getExchangeRate(date.minusDays(1), currency);
                } else {
                    JSONObject data = records.getJSONObject(0);
                    rate = new BigDecimal(data.getDouble(currencies.get(currencyKey)));
                    if (currencies.get(currencyKey).contains("100")) {
                        return rate.divide(new BigDecimal(100));
                    }
                    return rate;
                }
            }
        } catch (IOException e) {
            //returns a preset offline rate if no internet connection is available
            System.out.println("get failed.");
            return getOfflineRate(currencyKey);
        } catch (JSONException e) {
            //catches JSON exception when API is down
            return getOfflineRate(currencyKey);
        }
        assert false;
        return null;
    }

    /**
     * Creates an array list which stores a preset exchange rate for the respective API currency keys.
     */
    public static void generateOfflineRates() {
        offlineExchangeRate.put("eur_sgd", new BigDecimal(1.5395));
        offlineExchangeRate.put("gbp_sgd", new BigDecimal(1.8278));
        offlineExchangeRate.put("usd_sgd", new BigDecimal(1.3241));
        offlineExchangeRate.put("aud_sgd", new BigDecimal(0.9596));
        offlineExchangeRate.put("cad_sgd", new BigDecimal(1.0601));
        offlineExchangeRate.put("cny_sgd_100", new BigDecimal(0.2111));
        offlineExchangeRate.put("hkd_sgd_100", new BigDecimal(0.1687));
        offlineExchangeRate.put("inr_sgd_100", new BigDecimal(0.017075));
        offlineExchangeRate.put("idr_sgd_100", new BigDecimal(0.00009342));
        offlineExchangeRate.put("jpy_sgd_100", new BigDecimal(0.011688));
        offlineExchangeRate.put("krw_sgd_100", new BigDecimal(0.001119));
        offlineExchangeRate.put("myr_sgd_100", new BigDecimal(0.3214));
        offlineExchangeRate.put("twd_sgd_100", new BigDecimal(0.048318));
        offlineExchangeRate.put("nzd_sgd", new BigDecimal(0.8968));
        offlineExchangeRate.put("php_sgd_100", new BigDecimal(0.026322));
        offlineExchangeRate.put("qar_sgd_100", new BigDecimal(0.3689));
        offlineExchangeRate.put("sar_sgd_100", new BigDecimal(0.3580));
        offlineExchangeRate.put("chf_sgd", new BigDecimal(1.4600));
        offlineExchangeRate.put("thb_sgd_100", new BigDecimal(0.040657));
        offlineExchangeRate.put("aed_sgd_100", new BigDecimal(0.3657));
        offlineExchangeRate.put("vnd_sgd_100", new BigDecimal(0.00005930));
    }

    public static BigDecimal getOfflineRate(String key) {
        return offlineExchangeRate.get(currencies.get(key));
    }
}
