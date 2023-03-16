# User Guide

## Introduction

Welcome to Apollo, a timetable organiser made for the average NUS students by NUS students!
Apollo is a desktop app for managing your tasks, events, deadlines and modules optimized for use via a Command Line Interface (CLI) . 
If you can type fast, Apollo can get your timetable management done faster than traditional GUI apps.

## Table of Contents

1. [Quick Start](#quick-start)
2. [Features](#features)
   + [`help` - Viewing help](#help---viewing-help)
   + [`list` - Listing all saved tasks](#list---listing-all-saved-tasks)
   + [`todo` - Adding a ToDo](#todo---adding-a-todo)
   + [`deadline` - Adding a Deadline](#deadline---adding-a-deadline)
   + [`event` - Adding an Event](#event---adding-an-event)
   + [`mark` - Marking done](#mark---marking-done)
   + [`unmark` - Marking not done](#unmark---marking-not-done)
   + [`delete` - Deleting a task](#delete---deleting-a-task)
   + [`find` - Finding a task](#find---finding-a-task)
   + [`date` - Find tasks on date](#date---find-tasks-on-date)
   + [`bye` - Exiting the program](#bye---exiting-the-program)
   + [`addmod` - Adding a module](#addmod---adding-a-module)
   + [`delmod` - Deleting a module](#delmod---deleting-a-module)
   + [`listmod` - Listing all modules](#listmod---listing-all-modules)
   + [Loading and saving of data](#loading-and-saving-of-data)
3. [Command summary](#command-summary)
4. [FAQ](#faq)


## Quick Start
1. Ensure that you have Java `11` or above installed on your Personal Computer.

{Give detailed description of each feature}

### Adding a todo: `todo`
Adds a new item to the list of todo items.

Format: `todo n/TODO_NAME d/DEADLINE`

* The `DEADLINE` can be in a natural language format.
* The `TODO_NAME` cannot contain punctuation.  

Example of usage: 

`todo n/Write the rest of the User Guide d/next week`

`todo n/Refactor the User Guide to remove passive voice d/13/04/2020`

## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: {your answer here}

## Command Summary

|    Action     | Format                           |
|:-------------:|----------------------------------|
|     Help      | `help`                           |
|  List Tasks   | `list`                           |
|     Todo      | `todo TASK`                      |
|   Deadline    | `deadline TASK /by DATE`         |
|     Event     | `event TASK /from DATE /to DATE` |
|     Mark      | `mark IDX`                       |
|    Unmark     | `unmark IDX`                     |
|  Delete Task  | `delete IDX`                     |
|     Find      | `find KEYWORD`                   |
|     Date      | `date DATE`                      |
|      Bye      | `bye`                            |
|  Add Module   | `addmod MODULE_CODE`             |
| Delete Module | `delmod IDX`                     |
| List Modules  | `listmod`                        |
