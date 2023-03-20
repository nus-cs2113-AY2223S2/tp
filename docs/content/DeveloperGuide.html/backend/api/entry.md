---
title: Add, modify, view or delete an entry
weight: 1
---
## Add an entry

`POST /entry`

__Body__

- Gson-serialised `Entry`, obtained using `Entry::serialise`

__Parameters__

N/A

__Responses__

{{< button style="success" >}} 201: Created {{< /button >}}


## Delete an entry

`DELETE /entry`

__Body__

{{< lead >}} Required {{< /lead >}}

- 1-based index of entry to be deleted 

__Parameters__

N/A

__Responses__

{{< button style="success" >}} 200: Ok {{< /button >}}

- Gson-serialised `Entry`, deserialise with `EntryParser::deserialise`

{{< button style="danger" >}} 404: Not Found {{< /button >}}

## Modify an entry

`PATCH /entry`

__Body__


{{< lead >}} Required {{< /lead >}}

- 1-based index of entry to be deleted

__Parameters__

__`EDIT_AMOUNT`__ int

- The updated entry amount

__`EDIT_CATEGORY`__ Category

- The updated entry category

__`EDIT_DESCRIPTION`__ string

- The updated entry description

__Responses__

{{< button style="success" >}} 200: Ok {{< /button >}}

- Gson-serialised `Entry`, deserialise with `EntryParser::deserialise`

{{< button style="danger" >}} 404: Not Found {{< /button >}}

{{< button style="danger" >}} 422: Unprocessable Content {{< /button >}}

## View a specific entry

`GET /entry`

__Body__

{{< lead >}} Required {{< /lead >}}

- 1-based index of entry to view

__Parameters__

N/A

__Responses__

{{< button style="success" >}} 200: Ok {{< /button >}}

- Gson-serialised `Entry`, deserialise with `EntryParser::deserialise`

{{< button style="danger" >}} 404: Not Found {{< /button >}}
