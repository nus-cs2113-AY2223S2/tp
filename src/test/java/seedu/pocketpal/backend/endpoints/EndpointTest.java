package seedu.pocketpal.backend.endpoints;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class EndpointTest {
    private static final EndpointImpl endpoint = new EndpointImpl();

    @Test
    void callMethodHandlers_exceptionThrown() {
        assertThrows(AssertionError.class, () -> endpoint.handleDelete(null));
        assertThrows(AssertionError.class, () -> endpoint.handleGet(null));
        assertThrows(AssertionError.class, () -> endpoint.handlePatch(null));
        assertThrows(AssertionError.class, () -> endpoint.handlePost(null));
    }
}
