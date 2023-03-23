---
title: API
weight: 10
---

## Endpoints

There are 2 endpoints currently available:

{{< table style="table-hover" >}}
| Endpoint | Method to call |
|------------|-------------------------|
| `/entry`   | `callEntryEndpoint()`   |
| `/entries` | `callEntriesEndpoint()` |
{{< /table >}}

## Creating a request

- To create a request, simply instantiate `seedu.pocketpal.communication.Request`
  with the desired request method.
- If there are any parameters associated with the request, you may add them using `addParam()`

```java
Request req = new Request(RequestMethod.PATCH);
req.addParam(RequestParams.EDIT_DESCRIPTION, "mango juice");
```

## Making a request

- To call each endpoint, pass the `Request` into its corresponding method in the frontend.
- A `Response` will be returned to the frontend.

{{< alert style="warning" >}}
All request body and parameter data should be serialised with `String.valueOf()` if not specified.
{{< /alert >}}

```java
Backend backend = new Backend();
Response res = backend.callEntryEndpoint(req);

if (res.getResponseStatus() != ResponseStatus.OK) {
// handle status        
}

Entry entry = EntryParser.deserialise(res.getData());
// process entry
```
