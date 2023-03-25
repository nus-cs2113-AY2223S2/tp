package seedu.duke.storage;

import seedu.duke.venue.Venue;

import java.io.FileNotFoundException;

import java.io.IOException;

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
