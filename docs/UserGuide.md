# User Guide

## Introduction

{Give a product intro}

## Quick Start

{Give steps to get started quickly}

1. Ensure that you have Java 11 or above installed.
1. Down the latest version of `Duke` from [here](http://link.to/duke).

## Features 

{Give detailed description of each feature}

### Adding a todo: `todo`
Adds a new item to the list of todo items.

Format: `todo n/TODO_NAME d/DEADLINE`

* The `DEADLINE` can be in a natural language format.
* The `TODO_NAME` cannot contain punctuation.  

Example of usage: 

`todo n/Write the rest of the User Guide d/next week`

`todo n/Refactor the User Guide to remove passive voice d/13/04/2020`

### `mark` - Mark a task

Marks a task with the given id by the user as completed. 

Format: `mark <id>`

* The `id` has to be an id of a task that can be found in the ToDo list.

Example of usage:

`mark 1`

Expected outcome:

* Marks the task of id 1 of the ToDo list as done.
    * `COMMAND`: mark
    * `ID`: 1

```
Okay, I have marked this task as complete:
[ID:1]	[X][todo][Due: 23 Sep 3000 23:59]
```

### `unmark` - Unmark a task

Unmark a task with the given id by the user as incomplete. 

Format: `unmark <id>`
* The `id` has to be an id of a task that can be found in the ToDo list.

Example of usage:

`unmark 1`

Expected outcome:

* Unmark the task at id 1 of the ToDo list as incomplete.
    * `COMMAND`: unmark
    * `ID`: 1

```
Okay, I have marked this task as incomplete:
[ID:1]	[ ][todo][Due: 23 Sep 3000 23:59]
```

### `email` - Add/Edit an email to a task

Add or edit the email address of a task with a given id in the ToDo List. 

Format: `email <id> -edit <EMAIL>`
* The `id` has to be an id of a task that can be found in the ToDo list.
* The `EMAIL` has to be of proper email address format: "username@email.com".

Example of usage:

`email 1 -edit rui@gmail.com`

Expected outcome:

* Adds an email address to the task of id 1 from the ToDo list

    * `COMMAND`: email
    * `ID`: 1
    * `FLAG`: -edit
    * `EMAIL`: rui@gmail.com

```
Okay, I have edited the email address of this task to [rui@gmail.com]:
[ID:1]	[ ][todo][Due: 23 Sep 3000 23:59]
```
### `delete` - Delete a task

Removes the task with the given id by the user from the ToDo list. 

Format: `delete <id>`
* The id has to be an id of a task that can be found in the ToDo list.

Example of usage:

`delete 1`

Expected outcome:

* Deletes the task of id 1 from the ToDo list

  * `COMMAND`: delete
  * `ID`: 1

```
Okay, I have removed this task:
[ID:1]	[ ][todo][Due: 23 Sep 3000 23:59]
```

## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: {your answer here}

## Command Summary

{Give a 'cheat sheet' of commands here}

* Add todo `todo n/TODO_NAME d/DEADLINE`
* Mark a task `mark <id>`
* Unmark a task `unmark <id>`
* Delete a task `delete <id>`
* Add/Edit email to a task `email <id> -edit <EMAIL>`
