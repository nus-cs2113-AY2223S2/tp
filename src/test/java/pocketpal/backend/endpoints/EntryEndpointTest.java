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
import pocketpal.data.entry.Category;
import pocketpal.data.entry.Entry;
import pocketpal.data.parsing.EntryParser;
import pocketpal.frontend.constants.MessageConstants;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Test endpoint: /entry")
public class EntryEndpointTest extends EntryTestUtil {
    @BeforeEach
    void init() {
        TEST_BACKEND.clearData();
    }

    @Nested
    @DisplayName("Test /entry [DELETE]")
    class TestEntryDelete {
        @Test
        void entryEndpointDELETE_validId_returnDeletedEntry() {
            addEntry(ENTRY_1);
            addEntry(ENTRY_2);

            Request request = new Request(RequestMethod.DELETE, "1");
            Response response = TEST_BACKEND.requestEndpointEntry(request);
            Entry deletedEntry = EntryParser.deserialise(response.getData());

            assertEquals(response.getResponseStatus(), ResponseStatus.OK);
            assertTrue(isSameEntry(deletedEntry, ENTRY_1));
        }

        @Test
        void entryEndpointDELETE_invalidId_responseNotFound() {
            addEntry(ENTRY_6);
            addEntry(ENTRY_4); // target entry

            Request requestPositive = new Request(RequestMethod.DELETE, "300");
            Response responsePositive = TEST_BACKEND.requestEndpointEntry(requestPositive);
            Request requestNegative = new Request(RequestMethod.DELETE, "-300");
            Response responseNegative = TEST_BACKEND.requestEndpointEntry(requestNegative);

            assertEquals(responsePositive.getResponseStatus(), ResponseStatus.NOT_FOUND);
            assertEquals(responseNegative.getResponseStatus(), ResponseStatus.NOT_FOUND);
        }

    }

    @Nested
    @DisplayName("Test /entry [GET]")
    class TestEntryGet {
        @Test
        void entryEndpointGET_validId_correctEntry() {
            addEntry(ENTRY_6);
            addEntry(ENTRY_4); // target entry
            addEntry(ENTRY_2);
            addEntry(ENTRY_3);

            Request request = new Request(RequestMethod.GET, "2");
            Response response = TEST_BACKEND.requestEndpointEntry(request);
            Entry storedEntry = EntryParser.deserialise(response.getData());

            assertEquals(response.getResponseStatus(), ResponseStatus.OK);
            assertTrue(isSameEntry(storedEntry, ENTRY_4));
        }

        @Test
        void entryEndpointGET_invalidId_responseNotFound() {
            addEntry(ENTRY_6);
            addEntry(ENTRY_4); // target entry

            Request requestPositive = new Request(RequestMethod.GET, "300");
            Response responsePositive = TEST_BACKEND.requestEndpointEntry(requestPositive);
            Request requestNegative = new Request(RequestMethod.GET, "-300");
            Response responseNegative = TEST_BACKEND.requestEndpointEntry(requestNegative);

            assertEquals(responsePositive.getResponseStatus(), ResponseStatus.NOT_FOUND);
            assertEquals(responseNegative.getResponseStatus(), ResponseStatus.NOT_FOUND);
        }
    }

    @Nested
    @DisplayName("Test /entry [PATCH]")
    class TestEntryPatch {
        @Test
        void entryEndpointPATCH_validId_returnUpdatedEntry() {
            addEntry(ENTRY_3);
            Entry newEntry = new Entry("mango ice cream", 1.50, Category.FOOD);

            Request request = new Request(RequestMethod.PATCH, "1");
            request.addParam(RequestParams.EDIT_AMOUNT, String.valueOf(newEntry.getAmount()));
            request.addParam(RequestParams.EDIT_CATEGORY, newEntry.getCategoryString());
            request.addParam(RequestParams.EDIT_DESCRIPTION, newEntry.getDescription());
            Response response = TEST_BACKEND.requestEndpointEntry(request);
            Entry updatedEntry = EntryParser.deserialise(response.getData());

            assertEquals(response.getResponseStatus(), ResponseStatus.OK);
            assertTrue(isSameEntry(updatedEntry, newEntry));
        }

        @Test
        void entryEndpointPATCH_invalidId_responseNotFound() {
            addEntry(ENTRY_6);

            Request requestPositive = new Request(RequestMethod.PATCH, "300");
            requestPositive.addParam(RequestParams.EDIT_DESCRIPTION, "mango");
            Response responsePositive = TEST_BACKEND.requestEndpointEntry(requestPositive);
            Request requestNegative = new Request(RequestMethod.PATCH, "-300");
            requestNegative.addParam(RequestParams.EDIT_DESCRIPTION, "juice");
            Response responseNegative = TEST_BACKEND.requestEndpointEntry(requestNegative);
            Request requestValidate = new Request(RequestMethod.GET, "1");
            Response responseValidate = TEST_BACKEND.requestEndpointEntry(requestValidate);
            Entry storedEntry = EntryParser.deserialise(responseValidate.getData());

            assertEquals(responsePositive.getResponseStatus(), ResponseStatus.NOT_FOUND);
            assertEquals(responseNegative.getResponseStatus(), ResponseStatus.NOT_FOUND);
            assertTrue(isSameEntry(storedEntry, ENTRY_6)); // ensure entry remains unchanged
        }

        @Test
        void entryEndpointPATCH_invalidCategory_responseUnprocessableContent() {
            addEntry(ENTRY_1);

            Request request = new Request(RequestMethod.PATCH, "1");
            request.addParam(RequestParams.EDIT_CATEGORY, "Fruits");
            Response response = TEST_BACKEND.requestEndpointEntry(request);
            assertEquals(response.getResponseStatus(), ResponseStatus.UNPROCESSABLE_CONTENT);
        }

        @Test
        void entryEndpointPATCH_invalidAmount_patchFailure() {
            addEntry(ENTRY_1);

            Request request = new Request(RequestMethod.PATCH, "1");
            request.addParam(RequestParams.EDIT_AMOUNT, "2147483648");
            Response response = TEST_BACKEND.requestEndpointEntry(request);

            assertEquals(response.getResponseStatus(), ResponseStatus.UNPROCESSABLE_CONTENT);
            assertEquals(response.getData(), MessageConstants.MESSAGE_INVALID_AMOUNT);
        }
    }

    @Nested
    @DisplayName("Test /entry [POST]")
    class TestEntryPost {
        @Test
        void entryEndpointPOST_validEntry_responseCreated() {
            Request request = new Request(RequestMethod.POST, ENTRY_1.serialise());
            Response response = TEST_BACKEND.requestEndpointEntry(request);
            assertEquals(response.getResponseStatus(), ResponseStatus.CREATED);
        }

        // TODO: test for failed POST request
    }
}
