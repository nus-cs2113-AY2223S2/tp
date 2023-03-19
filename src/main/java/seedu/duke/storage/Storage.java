package seedu.duke.storage;

import seedu.duke.venue.Venue;

import java.io.IOException;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Storage {
    private static final String dirPath = "data";
    private static final String filePath = "data/venueList.txt";
    private static ArrayList<Venue> venueList = new ArrayList<>();
    public static ArrayList<Venue> venueListInit() {
        try {
            checkFileAccess();
            load();
        } catch (FileNotFoundException err) {
            System.out.println("File not Found");
        } catch (IOException err) {
            System.out.println("Something went wrong");
        }
        return venueList;
    }

    public static void load() throws FileNotFoundException {
        File file = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(file); // create a Scanner using the File as the source
        while (s.hasNext()) {
            String textLine = s.nextLine();
            String[] parsedTextLine = textLine.split("\\|");
            String venueName = parsedTextLine[0].substring(0,parsedTextLine[0].length());
            String venueLocation = parsedTextLine[1].substring(1,parsedTextLine[1].length());
            int venueCapacity = Integer.parseInt(parsedTextLine[2].substring(1,parsedTextLine[2].length()));
            Venue v = new Venue(venueName, venueLocation, venueCapacity);
            venueList.add(v);
        }
    }

    public static void checkFileAccess() throws IOException {
        File dir = new File(dirPath);
        if (!dir.exists()) {
            dir.mkdir();
        }
        File f = new File(filePath);
        if (!f.exists()) {
            f.createNewFile();
        }
    }
}
