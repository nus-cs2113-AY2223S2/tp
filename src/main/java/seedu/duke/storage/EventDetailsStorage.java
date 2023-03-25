package seedu.duke.storage;

import seedu.duke.data.VenueListData;
import seedu.duke.venue.Venue;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class EventDetailsStorage extends Storage{

    private static final String filePath = "data/eventDetails.txt";

    public static void eventDetailsInit() {
        try {
            checkFileAccess(filePath);
        } catch (FileNotFoundException err) {
            System.out.println("File not Found");
        } catch (IOException err) {
            System.out.println("Something went wrong");
        }
    }
    public static void updateFile(String eventName, Venue venue) {
        try {
            writeToFile("", filePath);
            appendToFile(eventName, filePath);
            appendToFile(venue.getVenueName(), filePath);
        } catch (IOException err) {
            System.out.println("Something went wrong: " + err.getMessage());
        }
    }
}
