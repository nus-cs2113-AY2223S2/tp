package seedu.pocketpal.communication;

import java.util.EnumMap;

public class Request {
    private final RequestMethod requestMethod;
    private final String data;
    private final EnumMap<RequestParams, String> params = new EnumMap<>(RequestParams.class);

    public Request(RequestMethod requestMethod) {
        this(requestMethod, null);
    }

    public Request(RequestMethod requestMethod, String data) {
        this.requestMethod = requestMethod;
        this.data = data;
    }

    public void addParam(RequestParams paramKey, String paramValue) {
        params.put(paramKey, paramValue);
    }

    public String getData() {
        return data;
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
