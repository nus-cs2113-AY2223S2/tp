package pocketpal.backend;

import pocketpal.communication.Request;
import pocketpal.communication.RequestMethod;
import pocketpal.communication.RequestParams;
import pocketpal.communication.Response;
import pocketpal.communication.ResponseStatus;
import pocketpal.data.entry.Entry;
import pocketpal.data.entrylog.EntryLog;
import pocketpal.data.parsing.EntryParser;
import pocketpal.frontend.ui.UI;

public abstract class BackendTestUtil {
    protected static final Backend TEST_BACKEND = new Backend(true);
    protected static final UI TEST_UI = new UI();

    /**
     * Retrieve the entry stored in the database.
     *
     * @param entryId 1-based index of the entry to retrieve
     * @return Entry corresponding to the id if it exists
     */
    public Entry getEntryById(int entryId) {
        Request request = new Request(RequestMethod.GET, String.valueOf(entryId));
        Response response = TEST_BACKEND.requestEndpointEntry(request);
        return response.getResponseStatus() == ResponseStatus.OK
                ? EntryParser.deserialise(response.getData())
                : null;
    }

    /**
     * Retrieve the number of entries in the database.
     *
     * @return The number of entries
     */
    public int getNumEntries() {
        Request request = new Request(RequestMethod.GET);
        request.addParam(RequestParams.GET_SIZE, String.valueOf(true));
        Response response = TEST_BACKEND.requestEndpointEntries(request);
        return Integer.parseInt(response.getData());
    }

    public void addEntry(Entry entry) {
        Request request = new Request(RequestMethod.POST, entry.serialise());
        Response response = TEST_BACKEND.requestEndpointEntry(request);
        assert response.getResponseStatus() == ResponseStatus.CREATED;
    }

    public static boolean isSameEntry(Entry entry1, Entry entry2) {
        return entry1.equals(entry2);
    }

    public static boolean isSameEntryLog(EntryLog entryLog1, EntryLog entryLog2) {
        return entryLog1.equals(entryLog2);
    }
}
