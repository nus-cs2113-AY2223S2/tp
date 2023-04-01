package pocketpal.backend;

import pocketpal.backend.constants.Config;
import pocketpal.backend.storage.Storage;
import pocketpal.backend.endpoints.EntriesEndpoint;
import pocketpal.backend.endpoints.EntryEndpoint;
import pocketpal.communication.Request;
import pocketpal.communication.Response;
import pocketpal.backend.exceptions.InvalidReadFileException;
import pocketpal.data.entry.Entry;
import pocketpal.data.entrylog.EntryLog;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Backend {
    private static final Logger logger = Logger.getLogger(Backend.class.getName());
    private static final Storage storage = new Storage();
    private final EntryLog entries;
    private final EntryEndpoint entryEndpoint;
    private final EntriesEndpoint entriesEndpoint;

    public Backend() {
        this(false);
    }

    public Backend(boolean isTest) {
        Storage storage = isTest
                ? new Storage(Config.TEST_PATH_STRING)
                : new Storage();

        List<Entry> savedEntries;

        try {
            savedEntries = storage.readFromDatabase();
        } catch (InvalidReadFileException e) {
            logger.log(Level.INFO, "Save data is invalid.", e);
            savedEntries = new ArrayList<Entry>();
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Unable to perform IO operation.", e);
            throw new RuntimeException(e);
        }

        entries = new EntryLog(savedEntries);
        entryEndpoint = new EntryEndpoint(entries);
        entriesEndpoint = new EntriesEndpoint(entries);
    }

    private void save() {
        try {
            storage.writeToDatabase(entries.getEntriesList());
            logger.info("Successfully performed save operation.");
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Unable to perform IO operation.", e);
            throw new RuntimeException(e);
        }
    }

    public void clearData() {
        try {
            storage.reset();
            entries.clearAllEntries();
        } catch (IOException e) {
            logger.severe("backend: encountered IOException on data clear.");
            throw new RuntimeException(e);
        }
    }

    // endpoints
    public Response requestEndpointEntry(Request request) {
        logger.info("backend: routing request to /entry");
        Response response = entryEndpoint.handleRequest(request);
        save();
        return response;
    }

    public Response requestEndpointEntries(Request request) {
        logger.info("backend: routing request to /entries");
        Response response = entriesEndpoint.handleRequest(request);
        save();
        return response;
    }
}
