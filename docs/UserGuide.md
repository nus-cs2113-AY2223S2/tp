# Apollo User Guide (as of V2.0)

## Introduction and Aim

Welcome to Apollo, a timetable organiser made for the average NUS students by NUS students!
Apollo is a desktop app for managing your tasks, events, deadlines and modules optimized for use via a Command Line
Interface (CLI) .
If you can type fast, Apollo can get your timetable management done faster than traditional GUI apps.

## Table of Contents

1. [Quick Start](#quick-start)
2. [Features](#features)
    + [Task Commands](#task-commands)
    + [Module Commands](#module-commands)
    + [Common Commands](#common-commands)
    + [Loading and saving of data](#loading-and-saving-of-data)

3. [Command summary](#command-summary)
4. [FAQ](#faq)

## Quick Start

1. Ensure that you have Java `11` or above installed on your Personal Computer.
2. Download the latest `tp.jar` from [here]().
3. Copy the file to the folder you want to use as the home folder for your Apollo. This folder must be *empty*.
4. Open a command terminal, cd into the directory you put the jar file in and run the command `java -jar tp.jar`.
5. On opening, Apollo should greet you with this:

```
____________________________________________________________
Hello from
 ____    ____    _____  __      __       _____
|  _  | |  _ \  | ___ | | |     | |     | ___ |
| |_| | | |_| | | | | | | |     | |     | | | |
| | | | |  __/  | |_| | | |___  | |___  | |_| |
|_| |_| |_|     \_____/ |_____| |_____| \_____/
Enter "help" to see a list of commands.
____________________________________________________________

```

## Command Summary

|         Action          |              Format              |
|:-----------------------:|:--------------------------------:|
|       List Tasks        |              `list`              |
|          Todo           |           `todo TASK`            |
|        Deadline         |     `deadline TASK /by DATE`     |
|          Event          | `event TASK /from DATE /to DATE` |
|          Mark           |            `mark IDX`            |
|         Unmark          |           `unmark IDX`           |
|       Delete Task       |           `delete IDX`           |
| Find Tasks with Keyword |          `find KEYWORD`          |
|   Find Tasks on Date    |           `date DATE`            |
|      List Modules       |            `listmod`             |
|       Add Module        |       `addmod MODULE_CODE`       |
|      Delete Module      |           `delmod IDX`           |
| Show Module Information |            `showmod`             |
|          Help           |              `help`              |
 |     Weekly Schedule     |              `week`              |
|           Bye           |              `bye`               |

> Notes about the command format:
> + Words in `UPPER_CASE` are the parameters to be supplied by the user.
> > e.g. in 'todo TASK', `TASK` is a parameter that can be used as `todo read book`.
> + `DATE`s should be input in the format `yyyy-MM-ddThh:mm`.
> > e.g. `deadline read book /by 2023-10-30T23:59` sets a deadline for Oct 20 2023, 11:59PM
> + Tasks that have occurred prior to the current date cannot be added. 
> + `IDX` can be obtained by using `list` for tasks or `listmod` for modules.  
> + By default, all newly added tasks are not completed.

## Features

## *Task Commands*

### `list` - Listing all saved tasks

Shows a numbered list of all tasks (Todos, Events, Deadlines) in Apollo.

Format: `list`

```
>> list
You have a total of 4 tasks in your tasklist.
Here are the tasks in your list:
1.[T][ ] Eat Lunch
2.[E][ ] holiday (from: Mar 25 2023, 12:00AM to: Mar 30 2023, 11:59PM)
3.[D][ ] submit tutorial (by: Mar 30 2023, 11:59PM)
4.[T][ ] Feed the fish
There are 4 unmarked tasks in your tasklist.
```

### `todo` - Adding a ToDo

Adds a normal task to Apollo.

Format: `todo TASK`

```
>> todo Feed the fish
Got it. I've added this todo:
   [T][ ] Feed the fish
```

### `deadline` - Adding a Deadline

Adds a task with a due date to Apollo.

Format: `deadline TASK /by DATE`

> Note: `DATE` must be entered in the format `yyyy-MM-ddThh:mm`.

```
>> deadline submit tutorial /by 2023-03-30T23:59
Got it. I've added this deadline:
  [D][ ] submit tutorial (by: Mar 30 2023, 11:59PM)
```

### `event` - Adding an Event

Adds a task with a start and end date to Apollo.

Format: `event TASK /from DATE /to DATE`

> Note: `DATE` must be entered in the format `yyyy-MM-ddThh:mm`.

```
>> event holiday /from 2023-03-25T00:00 /to 2023-03-30T23:59
Got it. I've added this event:
  [E][ ] holiday (from: Mar 25 2023, 12:00AM to: Mar 30 2023, 11:59PM)
```

### `mark` - Marking done

Marks the specified task as completed.

Format: `mark IDX`

> Note: `IDX` can be obtained by using `list` to find the task's index.

```
>> mark 4
Nice!, I've marked this task as done:
[T][X] Feed the fish
```

### `unmark` - Marking not done

Marks the specified task as not completed.  

Format: `unmark IDX`

> Note: `IDX` can be obtained by using `list` to find the task's index.

```
>> unmark 4
OK, I've marked this task as not done yet:
  [T][ ] Feed the fish
```

### `delete` - Deleting a task

Deletes the specified task from Apollo.

Format: `delete IDX`

> Note: `IDX` can be obtained by using `list` to find the task's index.

```
>> delete 4
Noted, I've removed this task:
  [T][ ] Feed the fish
Now you have 3 tasks in the list
```

### `find` - Finding a task

Shows all tasks in Apollo that contain the specified keyword.

Format: `find KEYWORD`

```
>> find tutorial
Here are the matching tasks in your list:
1.[D][ ] submit tutorial (by: Mar 30 2023, 11:59PM)
```

### `date` - Find tasks on date

Shows all tasks in Apollo that occur on the specified date.

Format: `date DATE`

> Note: `DATE` should be entered in the format `yyyy-MM-dd`.

```
>> date 2023-03-30
Here are the tasks happening on Mar 30 2023:
1.[E][ ] holiday (from: Mar 25 2023, 12:00AM to: Mar 30 2023, 11:59PM)
2.[D][ ] submit tutorial (by: Mar 30 2023, 11:59PM)
```

## *Module Commands*

### `listmod` - Listing all modules

Shows a list of all modules in Apollo.
Format: `listmod`

```
>> listmod
You are taking 3 module(s) this semester:
1.CDE2000: Creating Narratives
2.CG2023: Signals and Systems
3.CS2040C: Data Structures and Algorithms
```

### `addmod` - Adding a module

Adds a module to Apollo.

Format: `addmod MODULE_CODE`
> Note: `MODULE_CODE` can be either uppercase or lowercase. 
```
>> addmod cs2113
Got it. I've added this module:
  CS2113: Software Engineering & Object-Oriented Programming
Total modular credits you have in this semester: 36
Here are the lesson types for this module:
LECTURE
TUTORIAL
To see how to add lessons, enter 'help'.
```
#### `addmod` flags
There are many lesson options and types, the below is a list of all the flags and their respective lesson types.
```
-lec        LECTURE
-plec       PACKAGED LECTURE
-st         SECTIONAL TEACHING
-dlec       DESIGN LECTURE
-tut        TUTORIAL
-ptut       PACKAGED TUTORIAL
-rcit       RECITATION
-lab        LABORATORY
-ws         WORKSHOP
-smc        SEMINAR STYLE MODULE CLASS
-mp         MINI PROJECT
-tt2        TUTORIAL TYPE 2
```

To add a lesson, use the following format:
`addmod MODULE_CODE -FLAG LESSON_NUMBER`

Example:
```
addmod CS1010 -st 1
```
This will add the first section teaching lesson of CS1010 to your module list.

### `delmod` - Deleting a module

Removes a module from Apollo. Can be done using either IDX or MODULE_CODE. 

#### `delmod` IDX
> Note: `IDX` can be obtained by using `listmod` to find the module's index.

Format: `delmod IDX`

```
>> delmod 1
Got it, removed CS2113 from your Module list.
```

#### `delmod` MODULE_CODE
Format: `delmod MODULE_CODE`

```
>> delmod CS1010
Got it, removed CS1010 from your Module list.
```

#### `delmod` flags
The lesson types and their corresponding guide are the same as `addmod` flags.

To delete a lesson, use the following format:
`delmod MODULE_CODE -FLAG LESSON NUMBER`

```
>> delmod CS1010 -st 1
Deleting lessons for module: CS1010
Lessons deleted: SECTIONAL TEACHING - 1
```

### `showmod` - Show information of a module

Shows the information of a module.
Format: `showmod`

```
>> showmod cs1231
Here are the lesson types for this module:
Sectional Teaching (-st)
Tutorial (-tut)
Number of MC: 4
```

## *Common Commands*

### `help` - Viewing help

Shows a menu of commands available in Apollo and their usage, as well as their required format/parameters.

Format: `help`

### `week` - Viewing weekly schedule

Shows a list of all lessons and tasks occurring during the current week (Mon to Sun).   
Format: `week`

### `bye` - Exiting the program

Format: `exit`

```
>> bye 
Bye. Hope to see you again soon!
```


## *Loading and saving of data*

Apollo automatically loads up your todo and module lists on start-up.

After any command that changes the data, Apollo will save the changes into your hard disk automatically.
No need to save manually!

The save file for your tasks is located at save.txt within the *home folder* for Apollo.
The save file for your modules is located at moduleData.txt within the *home folder* for Apollo.
If either file is corrupted, Apollo will show you a warning before the welcome message.





