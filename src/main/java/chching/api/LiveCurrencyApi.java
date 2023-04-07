package chching.api;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import chching.ChChing;
import chching.currency.Converter;
import chching.currency.Selector;
import chching.ChChingException;

public class LiveCurrencyApi {
    /**
     * Program Logging
     */
    private static final Logger logger = Logger.getLogger(ChChing.class.getName());

    static {
        LogManager.getLogManager().reset();
        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setLevel(Level.SEVERE);
        logger.addHandler(consoleHandler);
        logger.setLevel(Level.ALL);
        try {
            new File("data/LiveCurrencyAPILog.log").createNewFile();
            FileHandler fileHandler = new FileHandler("data/LogFiles/LiveCurrencyAPILog.log");
            fileHandler.setLevel(Level.FINE);
            logger.addHandler(fileHandler);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "File logger not working.", e);
        }
    }

    public LiveCurrencyApi(Selector selector, Converter converter) throws Exception {
        try {

            StringBuilder result = new StringBuilder();
            URL url = new URL("https://v6.exchangerate-api.com/v6/ab8bf379e8c93df65c1d7417/latest/SGD");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()))) {
                for (String line; (line = reader.readLine()) != null; ) {
                    result.append(line);
                }
            }
            String temp = result.toString();
            temp = temp.substring(363, temp.length() - 2);
            String[] arr = temp.split(",");
            for (String s : arr) {
                String[] tempArr = s.split(":");
                String currencyCode = tempArr[0].substring(3, tempArr[0].length() - 1);
                if (selector.containsCurrency(currencyCode)) {
                    converter.setConversionRate(currencyCode, Double.parseDouble(tempArr[1].trim()));
                }
            }
        } catch (Exception e) {
            throw new ChChingException("Error in retrieving live currency rates");
        }
    }
}
