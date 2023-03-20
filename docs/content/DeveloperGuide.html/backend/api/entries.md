---
title: Access all entries available
weight: 2
---

## Add an entry

`GET /entries`

__Body__

Number of recent entries to view if present, otherwise all entries will be returned.

__Parameters__

__`GET_SIZE`__ boolean

- If present, response will contain the number of entries.
- Otherwise, the following filters will be applied to the entries:

__`FILTER_BY_AMOUNT_START`__ double

- If this is the only parameter, it will be used as a minimum amount filter.
- If both filter amount parameters are present, it will be used as a amount range filter.

__`FILTER_BY_AMOUNT_END`__ double

- If this is the only parameter, it will be used as a maximum amount filter.
- If both filter amount parameters are present, it will be used as a amount range filter.

__`FILTER_BY_CATEGORY`__ Category

- Filter entries by category

__`FILTER_BY_QUERY`__ String

- Filter entries by user query

__Responses__

{{< button style="success" >}} 200: Ok {{< /button >}}

Gson-serialised `List<Entry>`, deserialise with `EntryLogParser::deserialise`

{{< button style="danger" >}} 422: Unprocessable Content {{< /button >}}
