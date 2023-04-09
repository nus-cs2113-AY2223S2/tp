# Apollo User Guide (as of V2.1)

## Introduction and Aim

Welcome to Apollo, a timetable organiser made for the average NUS students by NUS students!
Apollo is a desktop app for managing your tasks, events, deadlines and modules optimized for use via a Command Line
Interface (CLI) .
If you can type fast, Apollo can get your timetable management done faster than traditional GUI apps.

## Table of Contents

1. [Quick Start](#quick-start)
2. [Command summary](#command-summary)
3. [Features](#features)
    + [Task Commands](#task-commands)
      + [`list` - Listing all saved tasks](#list---listing-all-saved-tasks)
      + [`todo` - Adding a ToDo](#todo---adding-a-todo)
      + [`todo` - Adding a ToDo that sounds like a Deadline](#todo---adding-a-todo-that-sounds-like-a-deadline)
      + [`deadline` - Adding a Deadline](#deadline---adding-a-deadline)
      + [`event` - Adding an Event](#event---adding-an-event)
      + [`mark` - Marking done](#mark---marking-done)
      + [`unmark` - Marking undone](#unmark---marking-not-done)
      + [`delete` - Deleting a task](#delete---deleting-a-task)
      + [`find` - Finding a task](#find---finding-a-task)
      + [`date` - Listing tasks on a specific date](#date---find-tasks-on-date)
      
    + [Module Commands](#module-commands)
      + [`listmod` - Listing all modules](#listmod---listing-all-modules)
      + [`listmod` - Listing all added lessons for a Module](#listmod-with-lessons---lists-all-the-lessons-user-is-taking-in-that-module)
      + [`addmod` - Adding a module](#addmod---adding-a-module)
      + [`addmod lessons` - Adding a lesson to a module](#addmod-lessons---adding-lessons-to-a-module) 
      + [`delmod` - Deleting a module](#delmod---deleting-a-module)
      + [`delmod lessons` - Deleting a lesson from a module](#delmod-lessons---deleting-a-lesson-from-a-module)
      + [`show mod` - Showing a module](#showmod---show-information-of-a-module)
      + [`show mod lessons` - Showing a lesson](#showmod-lessons---show-information-of-a-lesson-from-a-module)

    + [Utility Commands](#utility-commands)
      + [`help` - Viewing help](#help---viewing-help)
      + [`help` - For help with a specific command](#help---for-help-with-specific-commands)
      + [`week` -Viewing weekly schedule](#week---viewing-weekly-schedule)
      + [`bye` - Exiting the program](#bye---exiting-the-program)
    + [Loading and saving of data](#loading-and-saving-of-data)

4. [FAQ](#faq)

## Quick Start

1. Ensure that you have Java `11` or above installed on your Personal Computer.
2. Download the latest `Apollo.jar` from [here](https://github.com/AY2223S2-CS2113-T13-4/tp/releases).
3. Copy the file to the folder you want to use as the home folder for your Apollo. This folder must be *empty*.
4. Open a command terminal, cd into the directory you put the jar file in and run the command `java -jar Apollo.jar`.
5. On opening, Apollo should greet you with this:

```
____________________________________________________________
Hello from
 ____    ____    _____  __      __       _____
|  _  | |  _ \  | ___ | | |     | |     | ___ |
| |_| | | |_| | | | | | | |     | |     | | | |
| | | | |  __/  | |_| | | |___  | |___  | |_| |
|_| |_| |_|     \_____/ |_____| |_____| \_____/

Your personal task and timetable manager!
Enter "help" to see a list of commands.
____________________________________________________________

```

**IMPORTANT:** 
- It is recommended that users **not** mutate the logger files. If the logger file is corrupted, severe errors will be logged
on the console by design. However, the application will still run normally and error messages thrown by the logger is not considered a bug.
- Users may edit the save.txt and the moduleData.txt files directly on a plaintext file editor, but it is recommended 
  that users edit it with the application. This is to ensure that save data in the correct format so that the data can be
  correctly loaded upon relaunch. (TLDR: Edit them at your own risk.)
- Currently, Apollo is only optimised to store data for NUS Students reading modules in the Academic Year 22/23 Semester 2. 
Due to a lack of a DBMS and data size constraints for the project, we are unable to store data for more than one semester.
Hence, users utilising Apollo should input their modules for the current semester only, which as of 06/04/23 is AY22/23 S2.



## Command Summary

|             Action             |                       Format                       |
|:------------------------------:|:--------------------------------------------------:|
|           List Tasks           |                       `list`                       |
|              Todo              |                   `todo <TASK>`                    |
|            Deadline            |            `deadline <TASK> -by <DATE>`            |
|             Event              |       `event <TASK> -from <DATE> -to <DATE>`       |
|              Mark              |                    `mark <IDX>`                    |
|             Unmark             |                   `unmark <IDX>`                   |
|          Delete Task           |                   `delete <IDX>`                   |
|    Find Tasks with Keyword     |                  `find <KEYWORD>`                  |
|       Find Tasks on Date       |                   `date <DATE>`                    |
|          List Modules          |                     `listmod`                      |
|   List Modules with lessons    |              `listmod <MODULE CODE>`               |
|  List Module with lesson type  |       `listmod <MODULE CODE> -<LESSON_TYPE>`       |
|           Add Module           |               `addmod <MODULE_CODE>`               |
|       Add Module Lessons       | `addmod MODULE_CODE -<LESSON_TYPE> <CLASS_NUMBER>` |
|     Delete Module by Index     |                   `delmod <IDX>`                   |
|     Delete Module by Code      |               `delmod <MODULE_CODE>`               |
|       Delete Module Data       | `delmod MODULE_CODE -<LESSON_TYPE> <CLASS_NUMBER>` |
|    Show Module Information     |              `showmod <MODULE_CODE>`               |
| Show Module Lesson Information |       `showmod <MODULE_CODE> -<LESSON_TYPE>`       |
|              Help              |                       `help`                       |
|        Help for Command        |                  `help <COMMAND>`                  |
|        Weekly Schedule         |                       `week`                       |
|              Bye               |                       `bye`                        |
    
> Notes about the command format:
> + Words in `<UPPER_CASE>` are the parameters to be supplied by the user.
> > e.g. in 'todo <TASK>', `<TASK>` is a parameter that can be used as `todo read book`.
> + `<DATE>`s should be input in the format `dd-MM-yyyy-HH:mm` where HH is 24-hour format
> > e.g. `deadline read book -by 30-10-2023-23:59` sets a deadline for Oct 20 2023, 11:59PM
> + Words preceded by a `-` are command flags.
> > e.g. in `addmod MODULE_CODE -<LESSON_TYPE> <CLASS_NUMBER>`, `-<LESSON_TYPE>` is a command flag that can be used as `addmod CS2113 -LEC 1`.
> + Tasks that have occurred prior to the current date cannot be added. 
> + `IDX` can be obtained by using `list` for tasks or `listmod` for modules.  
> + By default, all newly added tasks are not completed.

## Features

## *Task Commands*

### `list` - Listing all saved tasks

Shows a numbered list of all tasks (Todos, Events, Deadlines) in Apollo. `list` automatically sorts the tasks by type, 
then date within each task subtype.

Format: `list`

```
>> list
You have a total of 7 tasks in your tasklist.
Here are the tasks in your list:
1.[D][ ] submit tutorial (by: Apr 01 2023, 11:59PM)
2.[D][ ] submit tutorial (by: May 01 2023, 11:59PM)
3.[D][ ] submit tutorial (by: May 03 2023, 11:59PM)
4.[E][ ] lecture (from: Apr 03 2023, 09:00AM to: May 03 2023, 11:00AM)
5.[E][ ] lecture (from: Apr 04 2023, 09:00AM to: May 03 2023, 11:00AM)
6.[E][ ] lecture (from: May 03 2023, 09:00AM to: May 03 2023, 11:00AM)
7.[T][ ] eat lunch
There are 7 unmarked tasks in your tasklist.
```

### `todo` - Adding a ToDo

Adds a normal task to Apollo.

Format: `todo TASK`

```
>> todo Feed the fish
Got it. I've added this todo:
   [T][ ] Feed the fish
```

#### `todo` - Adding a ToDo that sounds like a Deadline

In the event that a user inputs a todo that sounds like a deadline, 
Apollo will prompt the user to consider using the deadline command instead. Users will still be able to add it into the 
Task List for greater user flexibility.
```
>> todo submit TP by 3rd May
This todo seems to suggest that this is a deadline type task.
You could consider using the deadline command instead.

Got it. I've added this todo:
[T][ ] submit TP by 3rd May
```

### `deadline` - Adding a Deadline

Adds a task with a due date to Apollo. 
If deadline clashes with any event or lesson type you will be alerted through a warning message. 
However, users will still be able to add it into the Task List. 

Format: `deadline TASK -by DATE`

> Note: `DATE` must be entered in the format `dd-MM-yyyy-HH:mm`.

```
>> deadline submit tutorial -by 30-03-2023-23:59
Got it. I've added this deadline:
  [D][ ] submit tutorial (by: Mar 30 2023, 11:59PM)
```

### `event` - Adding an Event

Adds a task with a start and end date to Apollo.
If there is an event in the Task List that is clashing with any event added previously a warning message will be printed.
Similarly, if there is a lesson in the Timetable that is clashing with any event added previously a warning message will be printed.
However, event will still be added. 

Format: `event TASK -from DATE -to DATE`

> Note: `DATE` must be entered in the format `dd-MM-yyyy-HH:mm`.

```
>> event holiday -from 25-03-2023-00:00 -to 30-03-2023-23:59
Got it. I've added this event:
  [E][ ] holiday (from: Mar 25 2023, 12:00AM to: Mar 30 2023, 11:59PM)
```

### `mark` - Marking done

Marks the specified task as completed. Note that if a task was already marked as completed previously and users attempt 
to mark the same task as done again, Apollo will alert users to this.

Format: `mark IDX`

> Note: `IDX` can be obtained by using `list` to find the task's index. You can only mark the same task as done once.

```
>> mark 4
Nice!, I've marked this task as done:
[T][X] Feed the fish
```
> Case when user attempts to mark a task that was already marked as done in the first place.
```
>> mark 4
You have already marked this task as done previously.
```

### `unmark` - Marking not done

Marks the specified task as not completed. Note that if a task was never marked as completed in the first place and users
attempt to mark the same task as not done again, Apollo will alert users to this.

Format: `unmark IDX`

> Note: `IDX` can be obtained by using `list` to find the task's index. You can only unmark the same task as incomplete once.

```
>> unmark 4
OK, I've marked this task as not done yet:
  [T][ ] Feed the fish
```
> Case when user attempts to unmark a task that was never marked as done in the first place.
```
>> unmark 4
This task was never marked as done!
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

> Note: `DATE` should be entered in the format `dd-MM-yyyy.

```
>> date 30-03-2023
Here are the tasks happening on Mar 30 2023:
1.[E][ ] holiday (from: Mar 25 2023, 12:00AM to: Mar 30 2023, 11:59PM)
2.[D][ ] submit tutorial (by: Mar 30 2023, 11:59PM)
```

## *Module Commands*

### `listmod` - Listing all modules

Shows a list of all modules in Apollo. It will also show the total number of modular credits you have in this semester.
The list will be automatically sorted in alphabetical order according to EduRec standards.
Format: `listmod`

```
>> listmod
You are taking 3 module(s) this semester:
1.CG1111A: Engineering Principles and Practice I (4 MCs)
2.CS2113: Software Engineering & Object-Oriented Programming (4 MCs)
3.DTK1234: Design Thinking (4 MCs)
Total modular credits you have in this semester: 12
```
### `listmod with lessons` - Lists all the lessons user is taking in that module
Shows the list of classes user is taking for that module and their lesson type, day, timing and frequency.
```
>> listmod cs2113
These are your classes for Module CS2113: 

Lecture 1
   Friday 1600 - 1800 [Weekly]
Tutorial 13
   Friday 1300 - 1400 [Weeks: 3-13]

```

### `addmod` - Adding a module

Adds a module to Apollo.

Format: `addmod MODULE_CODE`
> Note: `MODULE_CODE` can be either uppercase or lowercase. 
```
>> addmod cs2113
Got it. I've added this module:
  CS2113: Software Engineering & Object-Oriented Programming
Total modular credits you have in this semester: 12
Enter "addmod CS2113 -[FLAG] [LESSON NUMBER]" to add lessons for this module.
Here are the lesson types for this module:
Lecture (-lec)
Tutorial (-tut)
```
### `addmod lessons` - Adding lessons to a module

To add a lesson of a particular module, use the following format:
`addmod <MODULE_CODE> -FLAG LESSON_NUMBER`

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

Example:
```
addmod CS1010 -st 1
```
This will add the first section teaching lesson of CS1010 to your module list.
If this lesson clashes with any of your other lessons a warning message will be displayed. 
However, the lesson will still be added to your timetable, similar to NUSMods.

**NOTE: LESSON_NUMBER must strictly follow that of NUSMods. If it is Lecture 01 and Lecture 1,
the corresponding flag inputs are -lec 01 and -lec 1 respectively.**


#### Behaviour of Command:

Should the user not have the Module in their list, Apollo will add the module to the list and add the lesson to the module.
If the module is already in the list, Apollo will only add the lesson to the module.


#### Invalid Modules:

If the module code is invalid or the module is not offered in the current semester, Apollo will display an error message.


e.g. 

```
>> addmod CG2028
This module does not exist, or is not available this semester!
Please refer to official NUS module list for more information.
```

### `delmod` - Deleting a module

Removes a module from Apollo. Can be done using either IDX or MODULE_CODE. 

#### `delmod` IDX
> Note: `IDX` can be obtained by using `listmod` to find the module's index.   
> We assume that each user will take no more than the number of mods available in the current semester. 

Format: `delmod IDX`

```
>> delmod 1
Got it, removed CS2113 from your Module list.
```

#### `delmod` MODULE_CODE
Format: `delmod MODULE_CODE`

```
>> delmod dtk1234
Got it, removed DTK1234 from your Module list.
Total modular credits you have in this semester: 8
```

### `delmod lessons` - Deleting a lesson from a module
The lesson types and their corresponding guide are the same as `addmod` flags.

To delete a lesson, use the following format:
`delmod <MODULE_CODE> -<FLAG> <LESSON NUMBER>`

```
>> delmod CS1010 -st 1
Deleting lessons for module: CS1010
Lessons deleted: SECTIONAL TEACHING - 1
```

**NOTE: LESSON_NUMBER must strictly follow that of NUSMods. If it is Lecture 01 and Lecture 1,
the corresponding flag inputs are -lec 01 and -lec 1 respectively.**

### `showmod` - Show information of a module

Shows the information of a module. 
Format: `showmod MODULE_CODE`

```
>> showmod cs1231
Here are the lesson types for this module:
Sectional Teaching (-st)
Tutorial (-tut)
Number of MC: 4
```

#### Invalid Module Code

If the module code is invalid or the module is not offered in the current semester, Apollo will display an error message:

```
>>> showmod CG2028
This module does not exist, or is not available this semester!
Please refer to official NUS module list for more information.
```

### `showmod lessons` - Show information of a lesson from a module 
The lesson types and their corresponding guide are the same as `addmod` flags.
Shows the list of classes a module has and their lesson types, day, timing and frequency.
To show the information on a lesson, use the following format:
`showmod <MODULE_CODE> -<FLAG>`

```
>> showmod CS1010 -st
Here are all available lessons of type: SECTIONAL_TEACHING for CS1010:
Class Number: 1
   Tuesday 1000 - 1200 [Weekly]
```

The ordering of lessons in the list are sorted as follows:

1. Lesson Type
2. Lesson Number (lexicographically)
3. Lesson Day and time

If module does not have specified lesson type, Apollo will display an error message:

```
>> showmod CS1010 -lec
This module does not have this lesson type
```

## *Utility Commands*

### `help` - Viewing help

Shows a menu of commands available in Apollo and their usage, as well as their required format/parameters.

Format: `help`

### `help` - For help with specific commands

To see a shorter help menu for a specific command instead of the longer help command or to find out more about a command, 
you can do so by typing `help COMMAND`.
The below is a list of commands that you can use with `help`.

Format: `help COMMAND`


|  Command   |                          Help/Information Message contains...                           |
|:----------:|:---------------------------------------------------------------------------------------:|
|   `list`   |                      information and format for list tasks command                      |
|   `todo`   |                                 format for todo command                                 |
| `deadline` |                               format for deadline command                               |
|  `event`   |                                format for event command                                 |
|   `mark`   |                                 format for mark command                                 |
|  `unmark`  |                                format for unmark command                                |
|  `delete`  |                             format for delete task command                              |
|   `find`   |                     format for finding matching tasks with keyword                      |
|   `date`   |                           format for finding tasks with date                            |
| `listmod`  |                     information and format for list module command                      |
|  `addmod`  | information and format of adding modules and module lessons commands, with flag options |
|  `delmod`  |       information and format of delete module command options, with flag options        |
| `showmod`  |                  information and format of show module command options                  |
|   `help`   |           information for help commands and how to seek specific command help           |
|   `bye`    |                         information and format for bye command                          |


Format: `help COMMAND`

```
>> help showmod

Shows the information of a module, including Modular Credits, lesson types, lesson numbers and times.
Format: showmod MODULE_CODE

If you would like to view timing information on a specific lesson type of a module, you can use flags.
Format: showmod MODULE_CODE -FLAG
Example: showmod CS1010 -st
NOTE: Different modules have different lesson types.
It is recomended to run `showmod MODULE_CODE` to see the lesson types available for that module.

There are -FLAGS for the various lessons options per module:
-lec			LECTURE
-plec			PACKAGED LECTURE
-st 			SECTIONAL TEACHING
-dlec			DESIGN LECTURE
-tut			TUTORIAL
-ptut			PACKAGED TUTORIAL
-rcit			RECITATION
-lab			LABORATORY
-ws 			WORKSHOP
-smc			SEMINAR STYLE MODULE CLASS
-mp 			MINI PROJECT
-tt2			TUTORIAL TYPE 2
```

#### Invalid Help Command Message:

If the command is invalid, Apollo will display an error message.

```
>> help me
Sorry, but I don't know what that means :(
```

### `week` - Viewing weekly schedule

Shows a list of all lessons and tasks occurring during the current week (Mon to Sun) as well as the current week of the
semester you are on.   
Format: `week`

Example: For a simulated timetable in week 12 of semester 2. 
```
>> week
____________________________________________________________
Here's your week from 2023-04-03 to 2023-04-09:
Week 12
_____________________________
MONDAY

There are no lessons on this day.

There are no tasks on this day.
_____________________________
TUESDAY

Lessons:
0900-1200: CG2023 Laboratory (04)
1000-1200: CS1010 Sectional Teaching (1)

There are no tasks on this day.
_____________________________
WEDNESDAY

Lessons:
1200-1400: DTK1234 Tutorial (E37)
1200-1500: GEA1000 Tutorial (E37)

There are no tasks on this day.
_____________________________
THURSDAY

Lessons:
1000-1200: CG2271 Laboratory (01)

There are no tasks on this day.
_____________________________
FRIDAY

There are no lessons on this day.

There are no tasks on this day.
_____________________________
SATURDAY

There are no lessons on this day.

There are no tasks on this day.
_____________________________
SUNDAY

There are no lessons on this day.

There are no tasks on this day.
____________________________________________________________

```

If the date is currently outside the semester, Apollo will display the following message as the week field:
```
It is currently not AY22/23 Semester 2
```

If the date is during recess week, Apollo will display the following message as the week field:
```
Recess Week
```

If the date is during reading week, Apollo will display the following message as the week field:
```
Reading Week
```

If the date is during exam week, Apollo will display the following message as the week field:
```
Examination week
```

### `bye` - Exiting the program

Format: `bye`

```
>> bye 
Bye. Hope to see you again soon!
```

#### Invalid Bye Command:
An invalid bye command (e.g `bye` followed by a word) will result in the following message:

```
>> bye bye
Sorry, but I don't know what that means :(
```
## *Loading and saving of data*

- Apollo automatically loads up your todo and module lists on start-up.

- After any command that changes the data, Apollo will save the changes into your hard disk automatically.
  No need to save manually!

- The save file for your tasks is located at save.txt within the *home folder* for Apollo.
- The save file for your modules is located at moduleData.txt within the *home folder* for Apollo.
- If either file is corrupted or has errors, Apollo will show you a warning before the welcome message.

Example message when save.txt has errors:
```
Module Data loaded
____________________________________________________________
There is an error in save.txt at line 1
Task 1 has been excluded. You can edit the save file at:
save.txt
____________________________________________________________
____________________________________________________________
Hello from
 ____    ____    _____  __      __       _____
|  _  | |  _ \  | ___ | | |     | |     | ___ |
| |_| | | |_| | | | | | | |     | |     | | | |
| | | | |  __/  | |_| | | |___  | |___  | |_| |
|_| |_| |_|     \_____/ |_____| |_____| \_____/

Your personal task and timetable manager!
Enter "help" to see a list of commands.
____________________________________________________________
```

## FAQ

**Q**: How do I transfer my data to another Computer?

**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains 
the data of your previous Apollo home folder. 

**Q**: Why does Apollo only have data for NUS Modules in Academic Year 2022/2023 Semester 2?

**A**: Due to requirements constraints of Apollo, we are not able to store multiple years of data due to data size limits.
Additionally, we are not able to store data in a database due to the lack of a database server. As a result, data 
for Apollo will be needed to be updated manually by the developers for each semester.

**Q**: Does Apollo have data for all modules available in NUS?

**A**: No. Apollo only has data for modules that are available in the current semester. Additionally, modules which have 
timetables which fall outside the official NUS Semester 2 timetable will not be available in Apollo. These include
certain modules such as Special Term Modules and iBloc Modules (CS1010x).

**Q**: Should I edit the save file manually?

**A**: No. Apollo is not designed to be edited manually. If you edit the save file manually, you may corrupt the data 
and cause unpredictable behaviours in Apollo. If you want to edit the save file, you can do so at your own risk. Editing the file
while Apollo is running will not affect the data in Apollo.







