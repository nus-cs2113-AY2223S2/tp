package pocketpal.backend.endpoints;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import pocketpal.communication.Request;
import pocketpal.communication.RequestMethod;
import pocketpal.communication.RequestParams;
import pocketpal.communication.Response;
import pocketpal.communication.ResponseStatus;
import pocketpal.data.EntryTestUtil;
import pocketpal.data.entrylog.EntryLog;
import pocketpal.data.parsing.EntryLogParser;
import pocketpal.frontend.constants.MessageConstants;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EntriesEndpointTest extends EntryTestUtil {
    private static final EntryLog expectedEntryLog = new EntryLog();

    @BeforeEach
    void init() {
        TEST_BACKEND.clearData();
        expectedEntryLog.clearAllEntries();
    }

    @Nested
    @DisplayName("Test /entries [GET]")
    class TestEntriesGet {
        @Test
        void entriesEndpointGET_numEntries_correctNumber() {
            addEntry(ENTRY_1);
            addEntry(ENTRY_1);
            addEntry(ENTRY_1);
            addEntry(ENTRY_1);
            addEntry(ENTRY_1);

            Request request = new Request(RequestMethod.GET, "");
            request.addParam(RequestParams.GET_SIZE, String.valueOf(true));
            Response response = TEST_BACKEND.requestEndpointEntries(request);
            assertEquals(response.getResponseStatus(), ResponseStatus.OK);
            assertEquals(Integer.parseInt(response.getData()), 5);
        }

        @Test
        void entriesEndpointGET_allEntries_correctEntries() {
            addEntry(ENTRY_1);
            addEntry(ENTRY_2);
            addEntry(ENTRY_3);
            expectedEntryLog.addEntry(ENTRY_1);
            expectedEntryLog.addEntry(ENTRY_2);
            expectedEntryLog.addEntry(ENTRY_3);

            Request request = new Request(RequestMethod.GET);
            Response response = TEST_BACKEND.requestEndpointEntries(request);
            EntryLog returnedEntryLog = EntryLogParser.deserialise(response.getData());

            assertEquals(response.getResponseStatus(), ResponseStatus.OK);
            assertTrue(isSameEntryLog(expectedEntryLog, returnedEntryLog));
        }

        @Test
        void entriesEndpointGET_recentEntries_correctEntries() {
            addEntry(ENTRY_1);
            addEntry(ENTRY_2);
            addEntry(ENTRY_3);
            addEntry(ENTRY_4);
            expectedEntryLog.addEntry(ENTRY_3);
            expectedEntryLog.addEntry(ENTRY_4);

            Request request = new Request(RequestMethod.GET); // recent 2 entries
            request.addParam(RequestParams.NUM_ENTRIES, "2");
            Response response = TEST_BACKEND.requestEndpointEntries(request);
            EntryLog returnedEntryLog = EntryLogParser.deserialise(response.getData());

            assertEquals(response.getResponseStatus(), ResponseStatus.OK);
            assertTrue(isSameEntryLog(expectedEntryLog, returnedEntryLog));
        }

        @Test
        void entriesEndpointGET_filterCategory_correctEntries() {
            addEntry(ENTRY_1); // Food
            addEntry(ENTRY_2); // Transportation
            addEntry(ENTRY_3); // Medical
            expectedEntryLog.addEntry(ENTRY_1);

            Request request = new Request(RequestMethod.GET);
            request.addParam(RequestParams.FILTER_BY_CATEGORY, "Food");
            Response response = TEST_BACKEND.requestEndpointEntries(request);
            EntryLog returnedEntryLog = EntryLogParser.deserialise(response.getData());

            assertEquals(response.getResponseStatus(), ResponseStatus.OK);
            assertTrue(isSameEntryLog(expectedEntryLog, returnedEntryLog));
        }

        @Test
        void entriesEndpointGET_invalidCategory_statusUnprocessableContent() {
            Request request = new Request(RequestMethod.GET);
            request.addParam(RequestParams.FILTER_BY_CATEGORY, "Fruits");
            Response response = TEST_BACKEND.requestEndpointEntries(request);

            assertEquals(response.getResponseStatus(), ResponseStatus.UNPROCESSABLE_CONTENT);
        }

        @Test
        void entriesEndpointGET_filterMinAmount_correctEntries() {
            addEntry(ENTRY_3); // 99.99
            addEntry(ENTRY_4); // 3.50
            addEntry(ENTRY_5); // 1.45
            addEntry(ENTRY_6); // 399
            expectedEntryLog.addEntry(ENTRY_3);
            expectedEntryLog.addEntry(ENTRY_6);

            Request request = new Request(RequestMethod.GET);
            request.addParam(RequestParams.FILTER_BY_AMOUNT_START, "50");
            Response response = TEST_BACKEND.requestEndpointEntries(request);
            EntryLog returnedEntryLog = EntryLogParser.deserialise(response.getData());

            assertEquals(response.getResponseStatus(), ResponseStatus.OK);
            assertTrue(isSameEntryLog(expectedEntryLog, returnedEntryLog));
        }

        @Test
        void entriesEndpointGET_filterMaxAmount_correctEntries() {
            addEntry(ENTRY_3); // 99.99
            addEntry(ENTRY_4); // 3.50
            addEntry(ENTRY_5); // 1.45
            addEntry(ENTRY_6); // 399
            expectedEntryLog.addEntry(ENTRY_5);

            Request request = new Request(RequestMethod.GET);
            request.addParam(RequestParams.FILTER_BY_AMOUNT_END, "2");
            Response response = TEST_BACKEND.requestEndpointEntries(request);
            EntryLog returnedEntryLog = EntryLogParser.deserialise(response.getData());

            assertEquals(response.getResponseStatus(), ResponseStatus.OK);
            assertTrue(isSameEntryLog(expectedEntryLog, returnedEntryLog));
        }

        @Test
        void entriesEndpointGET_filterRangeAmount_correctEntries() {
            addEntry(ENTRY_3); // 99.99
            addEntry(ENTRY_4); // 3.50
            addEntry(ENTRY_5); // 1.45
            addEntry(ENTRY_6); // 399
            expectedEntryLog.addEntry(ENTRY_3);
            expectedEntryLog.addEntry(ENTRY_4);

            Request request = new Request(RequestMethod.GET);
            request.addParam(RequestParams.FILTER_BY_AMOUNT_START, "2");
            request.addParam(RequestParams.FILTER_BY_AMOUNT_END, "100");
            Response response = TEST_BACKEND.requestEndpointEntries(request);
            EntryLog returnedEntryLog = EntryLogParser.deserialise(response.getData());

            assertEquals(response.getResponseStatus(), ResponseStatus.OK);
            assertTrue(isSameEntryLog(expectedEntryLog, returnedEntryLog));
        }

        @Test
        void entriesEndpointGET_filterInvalidAmount_failure() {
            Request request = new Request(RequestMethod.GET);
            request.addParam(RequestParams.FILTER_BY_AMOUNT_END, "1000000000");
            Response response = TEST_BACKEND.requestEndpointEntries(request);

            assertEquals(response.getResponseStatus(), ResponseStatus.UNPROCESSABLE_CONTENT);
            assertEquals(response.getData(), MessageConstants.MESSAGE_INVALID_AMOUNT);
        }

        @Test
        void entriesEndpointGET_filterQueryCaseInsensitive_correctEntries() {
            addEntry(ENTRY_1); // 5 packets of dried mango
            addEntry(ENTRY_2); // Grab ride to mango farm at 2am
            addEntry(ENTRY_3); // Food poisoning
            expectedEntryLog.addEntry(ENTRY_1);
            expectedEntryLog.addEntry(ENTRY_2);

            Request request = new Request(RequestMethod.GET);
            request.addParam(RequestParams.FILTER_BY_QUERY, "maNgO");
            Response response = TEST_BACKEND.requestEndpointEntries(request);
            EntryLog returnedEntryLog = EntryLogParser.deserialise(response.getData());

            assertEquals(response.getResponseStatus(), ResponseStatus.OK);
            assertTrue(isSameEntryLog(expectedEntryLog, returnedEntryLog));
        }


        @Test
        void entriesEndpointGET_filterRecentEntries_correctEntries() {
            addEntry(ENTRY_2); // Grab ride to mango farm at 2am
            addEntry(ENTRY_3); // Food poisoning
            addEntry(ENTRY_4); // Mango juice
            addEntry(ENTRY_5); // Bus ride home
            expectedEntryLog.addEntry(ENTRY_2);
            expectedEntryLog.addEntry(ENTRY_4);

            Request request = new Request(RequestMethod.GET); // recent 3 entries
            request.addParam(RequestParams.NUM_ENTRIES, "3");
            request.addParam(RequestParams.FILTER_BY_QUERY, "mango");
            Response response = TEST_BACKEND.requestEndpointEntries(request);
            EntryLog returnedEntryLog = EntryLogParser.deserialise(response.getData());

            assertEquals(response.getResponseStatus(), ResponseStatus.OK);
            assertTrue(isSameEntryLog(expectedEntryLog, returnedEntryLog));
        }
        @Test
        void entriesEndpointGET_numberGreaterThanInteger_exceptionThrown() {
            Request request = new Request(RequestMethod.GET);
            request.addParam(RequestParams.NUM_ENTRIES, "2147483648");
            Response response = TEST_BACKEND.requestEndpointEntries(request);

            assertEquals(response.getResponseStatus(), ResponseStatus.UNPROCESSABLE_CONTENT);
            assertEquals(response.getData(), MessageConstants.MESSAGE_INVALID_NUMBER_OF_ENTRIES);
        }

        @Test
        void entriesEndpointGET_zeroEntries_getFailure() {
            Request request = new Request(RequestMethod.GET);
            request.addParam(RequestParams.NUM_ENTRIES, "0");
            Response response = TEST_BACKEND.requestEndpointEntries(request);

            assertEquals(response.getResponseStatus(), ResponseStatus.UNPROCESSABLE_CONTENT);
            assertEquals(response.getData(), MessageConstants.MESSAGE_INVALID_NUMBER_OF_ENTRIES);
        }
    }
}
