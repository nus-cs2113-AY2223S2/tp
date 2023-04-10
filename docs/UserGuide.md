<div id="top"></div>

# User Guide

## Acknowledgements

- Reference to [AB-3 User Guide](https://se-education.org/addressbook-level3/UserGuide.html)

## Introduction

CLIAlgo is a desktop application for **managing your CS2040C notes and codes**. It is optimized to be used via a Command 
Line Interface (CLI). If you can type fast, you can access and sort your notes faster than ever before. `CLIAlgo` is a 
**note management** application and not a note-taking application. Therefore, users are expected to already have the 
required `.txt` or `.cpp` files **in the same folder** as the `.jar` file before starting the application. Features like
note creation and editing are beyond the scope of this application.

## Table of Contents

- [Quick Start](#quick-start)
- [Features](#features)
    - [`help` - Viewing help](#help)
    - [`add` - Adding a CS2040CFile](#add)
    - [`remove` - Removing a CS2040CFile](#remove)
    - [`list` - Listing CS2040CFiles](#list)
    - [`filter` - Filtering CS2040CFiles](#filter)
    - [`topo` - Topologically Sort CS2040CFiles](#topo)
    - [`export` - Exporting files](#export)
    - [`exit` - Exiting the program](#exit)
- [FAQ](#faq)
- [Command Summary](#command-summary)

<div id="quick-start"></div>

## Quick Start

1. Ensure that you have Java <code>11</code> or above installed.
2. Download the latest version of `clialgo.jar` from [here](https://github.com/AY2223S2-CS2113-T15-1/tp/releases).
3. Copy the file to the folder you want to use as the home folder for your CS2040C notes.
4. Open a command terminal, ‘cd’ into the folder that you put the `.jar` file in, and use the `java -jar clialgo-v2.1.jar` 
command to run the application.
5. Ensure that all your `CS2040CFile`s with either `.cpp` or `.txt` as their extension are present in the same folder 
as the `.jar` file.
6. Type the command in the command box and press Enter to execute it. e.g. typing `help` and pressing Enter will open 
the help window. Some example commands you can try are:
   - `add n/bubble sort t/SORTING`: add a 'bubble sort.txt' file as notes to the topic 'SORTING'
   - `list`: list all existing notes
   - `remove n/bubble sort`: remove the 'bubble sort.txt' note from the list

> **WARNING**
> If any of the files stored in the data files are corrupted, i.e. any of the fields within the data files are invalid 
> the application discards that file stored within the data file.

<p align="right">(<a href="#top">back to top</a>)</p>

<div style="page-break-after: always;"></div>

<div id="features"></div>

## Features

> **Notes about the command format:**
> - Words in `UPPER_CASE` are parameters to be supplied by the user. 
> E.g. `remove n/NAME` where `NAME` is the parameter to be used as `remove n/bubble sort`.
> - Items in square brackets are optional.
> E.g. `help [c/COMMAND_TYPE]` can be used as `help` or `help c/add`.

<div id="help"></div>

### Viewing help: `help`
Shows a message explaining the format of supported commands in the application and their functions. If a valid command is entered after ‘help’ using the `c/` delimiter, it shows the format and function of that specific command instead.

#### Format:
```
help [c/COMMAND_TYPE]
```
- `COMMAND_TYPE` is **case-sensitive** and is an optional input

#### Example of usage:
Input:
```
help
```

<div style="page-break-after: always;"></div>

Output:
```
======================================================
The available COMMAND_TYPE(s) are:

[add]: add CS2040CFile
[remove]: remove CS2040CFile
[list]: displays all CS2040CFiles
[filter]: filters CS2040CFiles by topic
[topo]: displays all CS2040CFiles before the selected topic
[export]: places CS2040CFiles sorted by filter/topo in a file
[exit]: close the application

For more help on a specific command, type `help c/COMMAND_TYPE`.
======================================================
```
Input:
```
help c/add
```
Output:
```
======================================================
Add a CS2040CFile to a topic using:

    `add n/NAME t/TOPIC [i/IMPORTANCE]`

NAME: String name of the CS2040CFile file.
TOPIC: String topic that NAME will be tagged to.
IMPORTANCE: int level of importance on a scale of 1-10 (optional field).

Valid TOPIC's are 'SORTING', 'LINKED_LIST', 'GRAPH_STRUCTURES',
'BINARY_HEAP', 'HASH_TABLE', 'GRAPH_TRAVERSAL', 'BINARY_SEARCH_TREE',
'SS_SHORTEST_PATH', 'UNION_FIND_DS' and 'MINIMUM_SPANNING_TREE'.
======================================================
```

<p align="right">(<a href="#top">back to top</a>)</p>
<div id="add"></div>

<div style="page-break-after: always;"></div>

### Adding a CS2040CFile: `add`
Adds a `CS2040CFile` that currently already exists, into our file manager. It must **strictly** be either a `.txt` note 
file or a `.cpp` C++ code file. Requires a topic tagged to it, and an optional importance level, a number from 1 to 10.

#### Format:
```
add n/NAME t/TOPIC [i/IMPORTANCE_LEVEL]
```
- **The corresponding file must already be present in the same folder as the `.jar` in order for the `CS2040CFile` to be
added successfully.**
  - For example, if you want to add `queue.txt` into `CLIAlgo`, `queue.txt` must be present in the same folder as the
`.jar` file.
- Not following the syntax strictly would result in an invalid command message.
- `NAME` represents the name of the `CS2040CFile` to be added **without the file extension**.
- `TOPIC` represents the topic which will be tagged to the `CS2040CFile` added.
  - Only full words will be matched. 
  - E.g. `LINKED_LIST` will not match `Link List`.
  - Topic field is **case-sensitive**. Enter `help c/add` to view list of valid topics and their case-sensitivities.
  - For example, keying in `add n/queue t/sorting` is **not** valid. It should be `add n/queue t/SORTING` instead.
- `IMPORTANCE_LEVEL` represents the importance level you want to assign to the `CS2040CFile` added.
  - This field is optional. **If this field is left empty, the note or code files' importance is set to 5 by default**.

#### Example of usage:
Input:
```
add n/queue t/LINKED_LIST
```
or
```
add n/queue t/LINKED_LIST i/5
```

<div style="page-break-after: always;"></div>

Output:
```
======================================================
Successfully added queue into LINKED_LIST.
======================================================
```

<p align="right">(<a href="#top">back to top</a>)</p>
<div id="remove"></div>

### Removing a CS2040CFile: `remove`
Removes a `CS2040CFile` that exists from our file manager.

#### Format:
```
remove n/NAME
```
- `NAME` represents the name of the `CS2040CFile` to be removed **without the file extension**.
- Only `CS2040CFile`s that have been added can be removed.
- Not following the syntax strictly would result in an invalid command message.

#### Example of usage:
Input:
```
remove n/queue
```

Output:
```
======================================================
Successfully removed queue.
======================================================
```

<p align="right">(<a href="#top">back to top</a>)</p>
<div id="list"></div>

<div style="page-break-after: always;"></div>

### Listing CS2040CFiles: `list`
List all `CS2040CFile`s (in any order) present in `CLIAlgo`.

#### Format:
```
list
```

- Command is **case-sensitive**.
- Command should only contain one word.
- Not following the syntax strictly would result in an invalid command message.

#### Example of usage:
Input:
```
list
```
Output:
```
======================================================
Here are all your CS2040CFiles:
======================================================
1. [CODE] bubble sort
2. [CODE] queue
3. [NOTE] linked list note
======================================================
```

<p align="right">(<a href="#top">back to top</a>)</p>
<div id="filter"></div>

<div style="page-break-after: always;"></div>

### Filtering CS2040CFiles: `filter`
Filters the `CS2040CFile`s by a user-specified `KEYWORD` and prints a list of `CS2040CFile`s filtered based
on the `KEYWORD`.

#### Format:
```
filter k/KEYWORD [t/TOPIC_NAME]
```
- `KEYWORD` represents the criteria to filter the `CS2040CFile`s.
    - Valid `KEYWORD` includes: `topic`, `importance`.
- Filtering by `importance` will result in a filtered list of `CS2040CFile`s in **non-increasing order**.
- `TOPIC_NAME` represents the scope where the filter operation will be applied.
  -   It is an optional input and leaving it blank would result in setting the scope to include all `CS2040CFile`
  in `CLIAlgo`.
- `KEYWORD` and `TOPIC_NAME` are **case-sensitive**.
- Not following the syntax strictly would result in an invalid command message.

#### Example of usage:

Filtering by topic without providing `TOPIC_NAME`.

Input:
```
filter k/topic
```
Output:
```
======================================================
Here are the filtered CS2040CFiles:
======================================================
[SORTING]
1. bubble sort
[LINKED_LIST]
1. linked list code
2. linked list note
======================================================
```

<div style="page-break-after: always;"></div>

Filtering by topic according to `TOPIC_NAME`.

Input:
```
filter k/topic t/LINKED_LIST
```
Output:
```
======================================================
Here are the filtered CS2040CFiles:
======================================================
[LINKED_LIST]
1. linked list code
2. linked list note
======================================================
```

Filtering by importance without providing `TOPIC_NAME`.

Input:
```
filter k/importance
```
Output:
```
======================================================
Here are the filtered CS2040CFiles:
======================================================
1. [NOTE] bubble sort [6]
2. [CODE] linked list code [5]
3. [NOTE] linked list note [3]
======================================================
```

<div style="page-break-after: always;"></div>

Filtering by importance according to `TOPIC_NAME`.

Input:
```
filter k/importance t/SORTING
```
Output:
```
Here are the filtered CS2040CFiles:
======================================================
[SORTING]
1. [NOTE] bubble sort [6]
======================================================
```

<p align="right">(<a href="#top">back to top</a>)</p>
<div id="topo"></div>

### Topologically Sort CS2040CFiles: `topo`
Prints a topologically sorted list of `CS2040CFile`s that comes before and within the topic of a user-specified note.
This will allow the user to be able to revise all pre-requisite topics before revising the topic of the specified note.

#### Format:
```
topo n/NAME
```

- The topological sort follows the following order (latest to earliest): "MINIMUM_SPANNING_TREE",
"SS_SHORTEST_PATH", "GRAPH_TRAVERSAL", "GRAPH_STRUCTURES", "BINARY_SEARCH_TREE", "UNION_FIND_DS",
"HASH_TABLE", "BINARY_HEAP", "LINKED_LIST", "SORTING".
- `NAME` represents the name of the `CS2040CFile` where the user wants the topological sort to start from.
  - Only notes that are **saved locally and added to CLIAlgo** can be used.
  - If no notes are saved locally and added to CLIAlgo, a feedback message will be printed instead.
- Command and `NAME` are **case-sensitive**.
- Not following the syntax strictly would result in an invalid command message.

> **Note:** Among all the notes added to CLIAlgo, **only** notes that come before and within the topic of the specified note will
be printed.

> **Example:**
> Notes of the following names, `bst`, `queue` and `sorting` of topics `BINARY_SEARCH_TREE`,
> `LINKED_LIST` and `SORTING` respectively are added to CLIAlgo.
> The `topo n/queue` input will only print `queue` and `sorting` since `bst` is of the topic
> `BINARY_SEARCH_TREE` that comes after the topic of `queue` which is `LINKED_LIST`.

#### Example of usage:
Input:
```
topo n/bst
```

Output:
```
======================================================
Here are the topologically sorted CS2040CFiles:
======================================================
[BINARY_SEARCH_TREE]
1. [NOTE] bst
[LINKED_LIST]
2. [NOTE] queue
[SORTING]
3. [NOTE] sorting
======================================================
```

<p align="right">(<a href="#top">back to top</a>)</p>
<div id="export"></div>

### Exporting `Files`
Whenever a `filter` or `topo` command is input by the user, the
`CS2040CFile` listed by the above commands would be stored in a 
`Buffer`. These stored `CS2040CFile`s can then be copied into 
`./export`.

> *The `export` command is to further expand the functionality of commands such as `filter`
> and `topo` such that all the files are placed into a single folder. This means that if the user
> wanted to only access files that are of a certain category, they are able to. For users that have hundreds
> of files, it improves their productivity!*
> 
> *For instance, the user has dozens of files of similar names such
> as a.txt, aa.txt, aaaaa.cpp. The user just has to categorize the 
> files within the application once and with filter, all the relevant
> files would be retrieved and placed together.*

<div style="page-break-after: always;"></div>

#### Format:
```
export
```

- Note that `export` **_ONLY_** works with `filter` and `topo` and **_not_** other functions such as `list`.
- If the Operating System has a valid file explorer, `./export` 
would be opened automatically.
- If the buffer is empty when `export` is called, an error message
would be printed.
- Every time `export` is called, the `CS2040CFile`s within `./export`
are deleted before the `CS2040CFile`s are copied into `./export`
- If a `CS2040CFile` stored in the `Buffer` does not exist for some reason 
(i.e. if the user deleted the `CS2040CFile`) an error message would be
printed for each `CS2040CFile` not found.
- **Removing a file using the remove command empties the buffer!**

#### Example of usage:
Input
```
filter k/topic
======================================================
Here are the filtered CS2040CFiles:
======================================================
[SORTING]
1. TEST
[LINKED_LIST]
1. Test1
======================================================
export
```

Output
```
======================================================
Successfully exported file(s).
======================================================
```

<div style="page-break-after: always;"></div>

Here is `./export` opened after `export` is input.

![](images/export1.png)

<p align="right">(<a href="#top">back to top</a>)</p>
<div id="exit"></div>

### Exiting the program: `exit`
Exits `CLIAlgo`.

#### Format:
```
exit
```
- Command is **case-sensitive**.
- Command should only contain one word.
- Not following the syntax strictly would result in an invalid command message.

#### Example of usage:
Input:
```
exit
```

<div style="page-break-after: always;"></div>

Output:
```
======================================================
Thank you for using CLIAlgo! Study hard!
======================================================
```

<p align="right">(<a href="#top">back to top</a>)</p>
<div id="FAQ"></div>

## FAQ

---
**Q**: How do I transfer my data to another computer? 

**A**: Copy all the `CS2040CFile`s as well as the `data` folder into the new Computer.

---

**Q**: How do I gain easy access to all my filtered or topologically sorted `CS2040CFile`s?

**A**: Enter the `export` command after filtering or topologically sorting your `CS2040CFile`s.

---

**Q**: What if I am unable to add because the program tells me that my `CS2040CFile` does not exist?

**A**: Ensure that all your `CS2040CFile`s are in the same directory as the `.jar` file.

---
<p align="right">(<a href="#top">back to top</a>)</p>
<div id="command-summary"></div>

<div style="page-break-after: always;"></div>

## Command Summary

| Action | Format                              | Examples                                               |
|--------|-------------------------------------|--------------------------------------------------------|
| help   | `help [c/COMMAND_TYPE]`             | `help`, `help c/add`                                   | 
| add    | `add n/NAME t/TOPIC [i/IMPORTANCE]` | `add n/bst t/BST i/6`                                  | 
| remove | `remove n/NAME`                     | `remove n/bst`                                         | 
| list   | `list`                              | `list`                                                 |
| filter | `filter k/KEYWORD [t/TOPIC_NAME]`   | `filter k/topic`, <br/> `filter k/topic t/LINKED_LIST` |
| topo   | `topo n/NAME`                       | `topo n/queue`                                         |
| export | `export`                            | `export`                                               | 
| exit   | `exit`                              | `exit`                                                 |

<p align="right">(<a href="#top">back to top</a>)</p>
