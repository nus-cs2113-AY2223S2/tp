package pocketpal.communication;

public interface Requestable {
    Response handleRequest(Request request);
}
