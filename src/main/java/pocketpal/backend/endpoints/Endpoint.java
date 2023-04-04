package pocketpal.backend.endpoints;

import pocketpal.backend.constants.MiscellaneousConstants;
import pocketpal.communication.Request;
import pocketpal.communication.Requestable;
import pocketpal.communication.Response;

import java.util.logging.Logger;

public abstract class Endpoint extends EndpointUtil implements Requestable {
    private static final Logger logger = Logger.getLogger(Endpoint.class.getName());

    /**
     * Routes the incoming request to the appropriate handler, if valid.
     * Otherwise, an AssertionError will be thrown.
     *
     * @param request Request to be handled
     * @return Response containing requested information
     */
    @Override
    public Response handleRequest(Request request) {
        switch (request.getRequestMethod()) {
        case DELETE:
            logger.info("Routing DELETE request");
            return handleDelete(request);
        case GET:
            logger.info("Routing GET request");
            return handleGet(request);
        case PATCH:
            logger.info("Routing PATCH request");
            return handlePatch(request);
        case POST:
            logger.info("Routing POST request");
            return handlePost(request);
        default:
            // should not be executed, unless new request methods are implemented
            logger.severe("Unable to route request: " + request.getRequestMethod());
            throw new AssertionError(MiscellaneousConstants.METHOD_NOT_IMPLEMENTED);
        }
    }

    /**
     * Send a DELETE request to the endpoint
     *
     * @param request Request to be handled
     * @return Response containing requested information
     */
    public Response handleDelete(Request request) {
        throw new AssertionError(MiscellaneousConstants.METHOD_NOT_IMPLEMENTED);
    }

    /**
     * Send a GET request to the endpoint
     *
     * @param request Request to be handled
     * @return Response containing requested information
     */
    public Response handleGet(Request request) {
        throw new AssertionError(MiscellaneousConstants.METHOD_NOT_IMPLEMENTED);
    }

    /**
     * Send a PATCH request to the endpoint
     *
     * @param request Request to be handled
     * @return Response containing requested information
     */
    public Response handlePatch(Request request) {
        throw new AssertionError(MiscellaneousConstants.METHOD_NOT_IMPLEMENTED);
    }

    /**
     * Send a POST request to the endpoint
     *
     * @param request Request to be handled
     * @return Response containing requested information
     */
    public Response handlePost(Request request) {
        throw new AssertionError(MiscellaneousConstants.METHOD_NOT_IMPLEMENTED);
    }
}
