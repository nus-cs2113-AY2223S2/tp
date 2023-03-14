---
title: Features
weight: 2
---

{Give detailed description of each feature}

## Deleting an expense: `/delete`
Removes the entry of the specified entry ID (1-based) from the entry log.

**_Format:_** `/delete ID`

* The `ID` must be in numeric format.

**_Example of usage:_**

`/delete  5`


**_Exceptions:_**

The following message will be displayed if the user does not input a valid entry ID.
```
________________________________________________
Please enter a valid numerical index!
________________________________________________
```
