package seedu.pocketpal.backend.endpoints;

import seedu.pocketpal.communication.Request;
import seedu.pocketpal.communication.Requestable;
import seedu.pocketpal.communication.Response;

public abstract class Endpoint implements Requestable {
    private static final String METHOD_NOT_IMPLEMENTED = "Unexpected request. This method not implemented.";

    @Override
    public Response handleRequest(Request request) {
        switch (request.getRequestMethod()) {
        case DELETE:
            return handleDelete(request);
        case GET:
            return handleGet(request);
        case PATCH:
            return handlePatch(request);
        case POST:
            return handlePost(request);
        default:
            throw new AssertionError(METHOD_NOT_IMPLEMENTED);
        }
    }

    public Response handleDelete(Request request) {
        throw new AssertionError(METHOD_NOT_IMPLEMENTED);
    }

    public Response handleGet(Request request) {
        throw new AssertionError(METHOD_NOT_IMPLEMENTED);
    }

    public Response handlePatch(Request request) {
        throw new AssertionError(METHOD_NOT_IMPLEMENTED);
    }

    public Response handlePost(Request request) {
        throw new AssertionError(METHOD_NOT_IMPLEMENTED);
    }
}
