import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ReadCSVFile {
    // Split but ignore those in strings e.g. "Genre, Genre, Genre".
    // Follow CSV convention.
    // https://stackoverflow.com/questions/1757065/java-splitting-a-comma-separated-string-but-ignoring-commas-in-quotes
    private static final String CSV_DELIM = ",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)";
    // We only want this many movies.
    private static final int MAX_LINES_READ = 10000;

    public static String[] find(String filePath, String movieName) {
        String csvFile = filePath;
        String line = "";


        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            Integer c = 0;
            while (c < MAX_LINES_READ && (line = br.readLine()) != null) {
                if (line.contains(movieName)) {
                    String[] row = line.split(CSV_DELIM, -1);
                    return row;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ArrayList<String[]> readEntireCSV(String filePath) {
        String line = "";

        ArrayList<String[]> output = new ArrayList<String[]>();
        // Open file to read
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {

            Integer numLinesRead = 0;
            while (numLinesRead < MAX_LINES_READ && (line = br.readLine()) != null) {
                String[] row = line.split(CSV_DELIM, -1);
                output.add(row);
            }

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
        return output;
    }
}

