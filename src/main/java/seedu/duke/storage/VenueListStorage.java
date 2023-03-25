package seedu.duke.storage;

import seedu.duke.data.VenueListData;
import seedu.duke.venue.Venue;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class VenueListStorage extends Storage{

    private static ArrayList<Venue> venueList = new ArrayList<>();

    private static final String filePath = "data/venueList.txt";
    public static ArrayList<Venue> venueListInit() {
        try {
            checkFileAccess(filePath);
            updateFile();
//            load();
        } catch (FileNotFoundException err) {
            System.out.println("File not Found");
        } catch (IOException err) {
            System.out.println("Something went wrong");
        }
        return venueList;
    }

    public static void load() throws FileNotFoundException {
        File file = new File(filePath);
        Scanner s = new Scanner(file);
        while (s.hasNext()) {
            String textLine = s.nextLine();
            String[] parsedTextLine = textLine.split("\\|");
            String venueName = parsedTextLine[0].substring(0,parsedTextLine[0].length() - 1);
            String venueLocation = parsedTextLine[1].substring(1,parsedTextLine[1].length());
            int venueCapacity = Integer.parseInt(parsedTextLine[2].substring(1,parsedTextLine[2].length()));
            Venue v = new Venue(venueName, venueLocation, venueCapacity);
            venueList.add(v);
        }
    }

    public static void writeToFile(String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    public static void appendToFile(String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(textToAppend);
        fw.close();
    }
    public static void updateFile() {
        venueList = VenueListData.returnVenueList();
        try {
            writeToFile("");
            for (Venue venue : venueList) {
                appendToFile(venue + System.lineSeparator());
            }
        } catch (IOException err) {
            System.out.println("Something went wrong: " + err.getMessage());
        }
    }
}
