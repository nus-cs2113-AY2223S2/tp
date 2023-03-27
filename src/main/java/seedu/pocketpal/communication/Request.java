package seedu.pocketpal.communication;

import java.util.EnumMap;

public class Request {
    private final RequestMethod requestMethod;
    private final String body;
    private final EnumMap<RequestParams, String> params = new EnumMap<>(RequestParams.class);

    public Request(RequestMethod requestMethod) {
        this(requestMethod, null);
    }

    public Request(RequestMethod requestMethod, String body) {
        this.requestMethod = requestMethod;
        this.body = body;
    }

    public void addParam(RequestParams paramKey, String paramValue) {
        params.put(paramKey, paramValue);
    }

    public String getBody() {
        return body;
    }

    public RequestMethod getRequestMethod() {
        return requestMethod;
    }

    public boolean hasParam(RequestParams paramKey) {
        return params.containsKey(paramKey);
    }

    public String getParam(RequestParams paramKey) {
        return params.get(paramKey);
    }
}
