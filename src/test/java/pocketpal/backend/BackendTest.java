package pocketpal.backend;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import pocketpal.communication.Request;
import pocketpal.communication.RequestMethod;
import pocketpal.communication.Response;
import pocketpal.data.EntryTestUtil;
import pocketpal.data.entry.Entry;
import pocketpal.data.entrylog.EntryLog;
import pocketpal.data.parsing.EntryLogParser;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Test Backend")
public class BackendTest extends EntryTestUtil {
    @BeforeEach
    void init() {
        TEST_BACKEND.clearData();
    }

    @Nested
    @DisplayName("Test endpoints")
    class TestEndpoints {
        @Test
        void requestEndpointEntry_responseReturned() {
            assertDoesNotThrow(() -> {
                addEntry(ENTRY_1);
                Request request = new Request(RequestMethod.GET, "1");
                Response response = TEST_BACKEND.requestEndpointEntry(request);
                assertNotEquals(null, response);
            });
        }

        @Test
        void requestEndpointEntries_responseReturned() {
            assertDoesNotThrow(() -> {
                addEntry(ENTRY_1);
                Request request = new Request(RequestMethod.GET);
                Response response = TEST_BACKEND.requestEndpointEntries(request);
                assertNotEquals(null, response);
            });
        }
    }

    @Nested
    @DisplayName("Test data IO")
    class TestDataIO {
        @Test
        void clearData_requestEntryShouldBeNull() {
            addEntry(ENTRY_1);
            TEST_BACKEND.clearData();
            Entry storedEntry = getEntryById(1);
            assertNull(storedEntry);
        }
        @Test
        void automaticSave_shouldLoadOnRestart() {
            addEntry(ENTRY_1); // entry should be automatically saved
            EntryLog expectedEntryLog = new EntryLog();
            expectedEntryLog.addEntry(ENTRY_1);

            Backend restartedBackend = new Backend(true);
            Request request = new Request(RequestMethod.GET);
            Response response = restartedBackend.requestEndpointEntries(request);
            EntryLog returnedEntryLog = EntryLogParser.deserialise(response.getData());

            assertTrue(expectedEntryLog.equals(returnedEntryLog));
        }
    }
}
