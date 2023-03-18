package seedu.pocketpal.communication;

public class Response {
    private final ResponseStatus responseStatus;
    private final String data;

    public Response (ResponseStatus responseStatus, String data) {
        this.responseStatus = responseStatus;
        this.data = data;
    }

    public ResponseStatus getResponseStatus() {
        return responseStatus;
    }

    public String getData() {
        return data;
    }
}
