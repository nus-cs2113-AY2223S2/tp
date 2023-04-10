package seedu.moviemate.storage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Class that contains only static methods to parse CSV.
 * To be used by Storage through delegation.
 */
public final class ReadCSVFile {
    // Split but ignore those in strings e.g. "Genre, Genre, Genre".
    // Follow CSV convention.
    // https://stackoverflow.com/questions/1757065/java-splitting-a-comma-separated-string-but-ignoring-commas-in-quotes
    private static final String CSV_DELIM = ",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)";
    // For above, to target (and remove) the opening and closing quotes.
    private static final String REGEX_REMOVE_QUOTES = "^\\\"+|\\\"+$";

    // We only want this many movies (Note the file itself has about 130,000).
    private static final int MAX_LINES_READ = 150000;

    public static final ArrayList<String[]> readEntireCSV(InputStream is) {
        String line = "";

        ArrayList<String[]> output = new ArrayList<String[]>();
        // Open file to read
        try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {

            int numLinesRead = 0;
            while (numLinesRead < MAX_LINES_READ && (line = br.readLine()) != null) {
                String[] row = line.split(CSV_DELIM, -1);

                for (int i = 0; i < row.length; i++) {
                    row[i] = trimQuotes(row[i]);
                }

                output.add(row);
            }

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
        return output;
    }

    /**
     * Remove start and end quotes.
     */
    private static String trimQuotes(String input) {
        if (input.contains("\"")) {
            String[] split = input.split(REGEX_REMOVE_QUOTES);
            for (String s : split) {
                if (!s.isEmpty()) {
                    return s;
                }
            }
        }
        return input;
    }
}

