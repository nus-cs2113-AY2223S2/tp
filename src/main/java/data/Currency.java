package data;


import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

public class Currency {

    protected HashMap<String, BigDecimal> exchangeRate = null;

    /**
     * Gets the HashMap of exchange rates. Instantiates the HashMap if it has not been instantiated.
     * @return returns the HashMap exchangeRate with the input exchange rates. (Singleton Pattern)
     */

    public HashMap<String, BigDecimal> getExchangeRate() {
        if(exchangeRate == null) {
            exchangeRate = new HashMap<>();
//            exchangeRate.put("SGD", new BigDecimal(1.0));
//            exchangeRate.put("USD", new BigDecimal(0.75));
        }
        return exchangeRate;
    }

    /**
     * Converts the currency to SGD if the input currency is not found in the HashMap. Else it returns the input
     * currency.
     * @param currency the input currency specified by the user.
     * @return returns SGD as the default currency if input currency is missing or not found in HashMap, else return
     * the input currency.
     */
    public String convertCurrency(String currency) {
        // Default currency is SGD
        exchangeRate = getExchangeRate();
        if (currency == null || !exchangeRate.containsKey(currency)) {
            return "SGD";
        }
        return currency;
    }

    /**
     * Checks if the input currency is found in the HashMap of exchange rates.
     * @param currency the input currency specified by the user.
     * @return returns true if the input currency is found in the HashMap, false otherwise.
     */
    public boolean checkCurrency(String currency) {
        if(exchangeRate.containsKey(currency)) {
            return true;
        }
        return false;
    }

    /**
     * Updates the input currency with a new exchange rate or adds it into the HashMap if it is not found
     * @param currency the input currency specified by the user.
     * @param rate the new exchange rate of the currency with respect to SGD.
     */
    public void updateCurrency(String currency, BigDecimal rate) {
        exchangeRate.put(currency, rate);
    }

    /**
     * Standardize the format of double when we add it to expenseList
     */
    public BigDecimal roundInput(String expenseAmountInput) {
        BigDecimal roundedExpense = new BigDecimal(expenseAmountInput);
        roundedExpense = roundedExpense.setScale(2, RoundingMode.HALF_UP);
        return roundedExpense;
    }

    /**
     * Gets the exchange rate of the currency specified from the API
     * @param date the closest previous working day of the input date.
     * @param currency the currency to be converted to.
     * @throws IOException
     */
    public static void sendHTTPGetRequest(String date, HashMap<String, BigDecimal> exchangeRate)
            throws IOException {
        String GET_URL = "https://eservices.mas.gov.sg/api/action/datastore/search.json?resource_id=95932927-c8bc-" +
                "4e7a-b484-68a66a24edfe&filters[end_of_day]=" + date + "&limit=1";
        URL url = new URL(GET_URL);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod("GET");
        int responseCode = httpURLConnection.getResponseCode();
        if(responseCode == httpURLConnection.HTTP_OK) { //successful request
            BufferedReader in = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            JSONObject obj = new JSONObject(response.toString());
            JSONObject result = obj.getJSONObject("result");
            JSONArray records = result.getJSONArray("records");
            JSONObject data = records.getJSONObject(0);
            System.out.println(date);
            for(String key: data.keySet()) {
                if(key.equals("end_of_day") || key.equals("preliminary") || key.equals("timestamp")){
                    continue;
                }
                BigDecimal rate = new BigDecimal(data.getDouble(key)).setScale(5, RoundingMode.HALF_UP);;
                exchangeRate.put(key, rate);
                System.out.println(key + " " + rate);
            }
        }else{
            System.out.println("get failed.");
        }


    }
}
