# Apollo Developer Guide

## Table of Contents

1. [Acknowledgments](#acknowledgements)
2. [Design](#design)
    + [Architecture](#architecture)
    + [UI Component](#ui-component)
    + [Logic Component](#logic-component)
    + [Model Component](#model-component)
    + [Storage Component](#storage-component)
    + [Common Classes](#common-classes)
3. [Implementation](#implementation)
    + [Add Module](#add-module)
    + [Delete Module](#delete-module)
    + [List Modules](#list-modules)
    + [Add Task](#add-task)
    + [Delete Task](#delete-task)
    + [Mark Task As Done](#mark-task-as-done)
    + [Unmark Task](#unmark-task)
    + [Find Task](#find-task)
    + [List Task](#list-task)
    + [Find Task on Date](#find-task-on-date)
    + [Storage](#storage)
    + [Logging](#logging)
4. [Documentation, logging, testing, configuration, dev-ops](#documentations-logging-testing-configuration-dev-ops)
    + [Documentation](#documentation)
    + [Logging](#logging)
    + [Testing](#testing)
    + [Configuration](#configuration)
    + [Dev-ops](#dev-ops)
5. [Appendix A: Requirements](#appendix-a-requirements)
    + [Product Scope](#product-scope)
        + [Target User Profile](#target-user-profile)
        + [Value Proposition](#value-proposition)
    + [User Stories](#user-stories)
    + [Use Cases](#use-cases)
    + [Non-Functional Requirements](#non-functional-requirements)
    + [Glossary](#glossary)
6. [Appendix B: Instructions for manual testing](#appendix-b-instructions-for-manual-testing)
    + [Launch and Shutdown](#launch-and-shutdown)

## Acknowledgements

We would like to acknowledge:

## Design & implementation

### Architecture

### UI Component

### Logic Component

### Model Component

### Storage Component

### Common Classes

## Implementation

### Add Module

The AddModule functionality allows users to add a module to their Module List. Beyond just adding their modules to the
module list, users are also able to add their specific lessons (e.g Lectures and Tutorials) to their module list.
This is facilitated by the AddMod command which is an extension of the Command class. Below is an example usage of how
the AddMod command can be used to add both modules and their specific lessons.

For when a user adds only a module (e.g CS2113) to the module list with no specific lessons, the following command can
be used:

Step 1: Define the Constructor :
When user executes the command `addmod cs2113` the Parser class calls the `AddModCommand()` method of the AddModCommand.
The constructor of the AddModCommand class takes in a moduleCode `cs2113` as a parameter and `allModules`. This
moduleCode is used to find `cs2113` from the ModuleData list.

Step 2: Define the `setUpLogger()` method :
The `setUpLogger()` method sets up the logger for the DeleteModuleCommand class. It creates a ConsoleHandler and a
FileHandler to handle logging.

Step 3: Override the `execute()` method :
The `execute()` method is overridden to execute the delete module functionality. It takes in the necessary parameters,
including the `ModuleList`, `Ui`, `Storage`, `TaskList` and `allModules`.

Step 4: Check if the module exists in the list :
The first step in the `execute()` method is to split the parameters into the module code and the lesson type. It then
calls the `isAdded()` method of the `ModuleList` class to check if the module already exists in the list. If the module
exists, a `DuplicateModuleException` is thrown.

Step 5: Add the module to the ModuleList :
If the module does not exist, it is added to the `ModuleList` by calling the `add()` method of the `ModuleList` class.
The module is sorted alphabetically by the `sort()` method of the `ModuleList` class.

Step 6: Print the confirmation message :
A confirmation message is printed to the user indicating that the module has been successfully added. The message
includes the module code and title of the module added as well as the available lesson types for the module.


For when a user adds a module (e.g CS2113) to the module list with specific lessons (e.g Lectures and Tutorials), the
following command can be used:

Step 1: Define the Constructor :

When user executes the command `addmod cs2113 -lec 1` the Parser class calls the `AddModCommand()` method of the 
AddModCommand class. The constructor of the AddModCommand class takes in the string `cs2113 -lec 1` as a parameter and
`allModules`. The string is split into a moduleCode `cs2113`, lessonType `-lec` and `1` as class number.

Step 2: Define the `setUpLogger()` method :

The setUpLogger() method sets up the logger for the DeleteModuleCommand class. It creates a ConsoleHandler and a
FileHandler to handle logging.

Step 3: Override `execute()` method :

The `execute()` method is overridden to execute the delete module functionality. It takes in the necessary parameters,
including the ModuleList, Ui, Storage, and TaskList.

Step 4: Calls the `handleMultiCommand()` method:

The `handleMultiCommand()` method is called to handle the command. It takes in `moduleList`, `lessonType` and `args` as
parameters. It then checks if the module already exists in the `ModuleList` by calling the `isAdded()` method.

Step 5: Add the module lessons to the ModuleList :

If the module already exists, the timetable of the classes are added to the module by calling the `addTimetable()` 
method which takes in `searchModule` and `lessonType` as parameters. If the module does not exist, it is added to the
Module list and the lessons are added to the module using the `addTimetable()` method of the `ModuleList` class.

Step 6: 

Print the confirmation message :
A confirmation message is printed to the user indicating that the module lesson has been successfully added.

### Delete Module

The DeleteModule functionality allows users to remove a module from the ModuleList.
It is facilitated by DeleteModuleCommand class which is an extension of the Command class.
Given below is an example usage scenario and how the delete mechanism behaves at each step.

Step 1: Define the Constructor :
When user executes the command `delmod cs2113` the Parser class calls the `DeleteModuleCommand()` method of the
DeleteModuleCommand class.
The constructor of the DeleteModuleCommand class takes in a
moduleCode `cs2113` as a parameter. This moduleCode is
used to find `cs2113`  from the ModuleList.

Step 2: Define the setUpLogger() method :
The setUpLogger() method sets up the logger for the DeleteModuleCommand class. It creates a ConsoleHandler and a
FileHandler to handle logging.

Step 3: Override the execute() method :
The execute() method is overridden to execute the delete module functionality. It takes in the necessary parameters,
including the ModuleList, Ui, Storage, and TaskList.

Step 4: Find the module to delete :
The first step in the execute() method is to find the module using the module code parameter  `cs2113` by calling
the `findModule()` method of the moduleList class.
If the module `cs2113` is not found, a ModuleNotFoundException is thrown.

Step 5: Remove the module from the ModuleList :
If `cs2113` is found, it is removed from the ModuleList by calling `remove()` method of moduleList class.

Step 6: Print the confirmation message :
A confirmation message is printed to the user indicating that the `cs2113` has been successfully deleted.

Step 7: Update the storage :
The storage is updated with the new ModuleList without `cs2113`

### List Modules
(TO BE ADDED SOON)

### Add Task
The add task mechanism is facilitated by `AddCommand`. It extends `Command` with the ability to add three different 
types of `Task`s to the TaskList, namely: `ToDo`, `Deadline`, and `Event`. 

Given below is an example usage scenario and how the add task mechanism behaves at each step.

Step 1. The user launches the application for the first time. `run()` method in Apollo is called and the program waits 
for a command.

Step 2. The user executes `event concert /from 2023-06-06T20:00 /to 2023-06-06T22:00` command.  
This is to add a `Task` with the description "concert" on Jun 6 2023 from 8-10pm to their TaskList. 
The String containing the command is parsed in `Parser` and determined to be an Add Task command since it starts
with "event". 

Step 3. Within `Parser`, an `AddCommand` is initialised with the String `command` "event". 
The remaining params of the command are further parsed into Strings: `desc` "concert" (description), `from` 
"2023-06-06T20:00" (start date), and `to` "2023-06-06T22:00" (end date) based on the delimiters "/from" and "/to". 
- For `command` "deadline", remaining params are parsed into `desc` and `by` (due date) based on the delimiter "/by". 
- For `command` "todo", all remaining params are parsed into `desc`.

Step 4. The initialised `AddCommand` is returned to the `run()` method in Apollo. 
In the event of the following, an error message is printed and steps 5-6 are skipped.
- Delimiters are not entered correctly
- Remaining params of the command are empty (ie. CLI input of user is "todo"/"deadline"/"event" only)

Step 5. `Command#execute()` is called. This in turn calls `AddCommand#addTask()`. As `command` has been initialised to
"event", `addTask()` will try to initialise a new `Event` by parsing the Strings `from` and `to` into LocalDateTimes.
In the event of the following, an error message is printed and step 6 is skipped.
- String for date cannot be parsed into LocalDateTime (wrong format of input)
- Task occurs entirely before the current date
- (for `Event`) Start date occurs after end date

Step 6. The initialised `Event` is added to the TaskList. A success message is printed and the hard disk storage is 
updated to reflect these changes. 

Step 7. Apollo waits for the next CLI command to be input by the user.

### Delete Task

The DeleteTask functionality allows users to remove a task (todo, event and deadline) from the TaskList.
It is facilitated by the ModifyCommand class which is an extension of the Command class.
Below is an example usage of how the DeleteTask command can be used to delete a task and how it behaves
at each step.

Step 1: Define the Constructor: When the user executes the command `delete 1`, the Parser class calls the
`ModifyCommand()` method of the ModifyCommand. The constructor of the ModifyCommand class takes in the taskIndex `1`
as a parameter. This index is used to find the task to be deleted from the TaskList.

Step 2: Define the `setUpLogger()` method: The `setUpLogger()` method sets up the logger for the ModifyCommand
class.
It creates a ConsoleHandler and a FileHandler to handle logging.

Step 3: Override the `execute()` method: The `execute()` method is overridden to execute the delete task functionality.
It takes in the necessary parameters, including the `TaskList`, `Ui`, `Storage`, `ModuleList`, `allModules`.

Step 4: Find out how to modify the TaskList: The first step in the `execute()` method is to find out how to modify the
TaskList. In this case, the task at a user-provided index is to be deleted.

Step 5: Find the task to delete: Using the parameter `taskIndex`, the `execute()` method will iterate to that
index in the `TaskList` and call the `remove()` method of the `TaskList` class. If the index is outside the bounds of
the size of the `TaskList`, a `NumberFormatException` is thrown, calling the `printErrorForIdx()` method in the Ui class
that takes in the size of the `TaskList` as a parameter.

Step 6: Print the confirmation message: A confirmation message is printed to the user indicating what task has been
successfully removed from the user-provided index of the `TaskList`. The message includes the task type, description
(and date of the task deleted if the task is either an event or a deadline).
It also includes the updated size of the `TaskList`, obtained with the `size()` method of the `TaskList` class.

Step 7: Update the storage: The storage is updated with the new TaskList without the deleted task.

### Mark Task As Done

The MarkTask functionality allows users to mark a task (todo, event and deadline) as done in their TaskList.
It is facilitated by the ModifyCommand class which is an extension of the Command class.
Below is an example usage of how the MarkTask command can be used to mark a task as done.

Step 1: Define the Constructor: When the user executes the command `mark 1`, the Parser class calls the
`ModifyCommand()` method of the ModifyCommand. The constructor of the ModifyCommand class takes in the taskIndex `1`
as a parameter. This index is used to find the task to be marked as done from the TaskList.

Step 2: Define the `setUpLogger()` method: The `setUpLogger()` method sets up the logger for the ModifyCommand
class.
It creates a ConsoleHandler and a FileHandler to handle logging.

Step 3: Override the `execute()` method: The `execute()` method is overridden to execute the mark task functionality.
It takes in the necessary parameters, including the `TaskList`, `Ui`, `Storage`, `ModuleList`, `allModules`.

Step 4: Find out how to modify the TaskList: The first step in the `execute()` method is to find out how to modify the
TaskList. In this case, the task at a user-provided index is to be marked as done.

Step 5: Find the task to mark as done: Using the parameter `taskIndex`, the `execute()` method will iterate to that
index in the `TaskList` and call the `setAsDone()` method of the `Task` class, setting the boolean `isDone` to `true`.
If the index is outside the bounds of the size of the `TaskList`, a `NumberFormatException` is thrown,
calling the `printErrorForIdx()` method in the Ui class that takes in the size of the `TaskList` as a parameter.

Step 6: Print the confirmation message: A confirmation message is printed to the user indicating what task has been
successfully marked as done from the user-provided index of the `TaskList`. The message includes the task type,
description (and date of the task deleted if the task is either an event or a deadline).

Step 7: Update the storage: The storage is updated with the new TaskList with the task marked with a cross next to it.

### Unmark Task 

The UnmarkTask functionality allows users to toggle a task (todo, event and deadline) to *not* done in their TaskList.
It is facilitated by the ModifyCommand class which is an extension of the Command class.
Below is an example usage of how the UnmarkTask command can be used to unmark a task as not done.

Step 1: Define the Constructor: When the user executes the command `unmark 1`, the Parser class calls the
`ModifyCommand()` method of the ModifyCommand. The constructor of the ModifyCommand class takes in the taskIndex `1`
as a parameter. This index is used to find the task to be unmarked from the TaskList.

Step 2: Define the `setUpLogger()` method: The `setUpLogger()` method sets up the logger for the ModifyCommand
class.
It creates a ConsoleHandler and a FileHandler to handle logging.

Step 3: Override the `execute()` method: The `execute()` method is overridden to execute the mark task functionality.
It takes in the necessary parameters, including the `TaskList`, `Ui`, `Storage`, `ModuleList`, `allModules`.

Step 4: Find out how to modify the TaskList: The first step in the `execute()` method is to find out how to modify the
TaskList. In this case, the task at a user-provided index is to be unmarked.

Step 5: Find the task to unmark as not done: Using the parameter `taskIndex`, the `execute()` method will iterate to
that index in the `TaskList` and call the `setAsDone()` method of the `Task` class, setting the boolean `isDone`
to `false`.
If the index is outside the bounds of the size of the `TaskList`, a `NumberFormatException` is thrown,
calling the `printErrorForIdx()` method in the Ui class that takes in the size of the `TaskList` as a parameter.

Step 6: Print the confirmation message: A confirmation message is printed to the user indicating what task has been
successfully unmarked from the user-provided index of the `TaskList`. The message includes the task type,
description (and date of the task deleted if the task is either an event or a deadline).

Step 7: Update the storage: The storage is updated with the new TaskList with the task marked without a cross next to
it.

### Find Task

The FindTask functionality allows user to search for a task (todo, event and deadline) from the TaskList using a 
specific keyword. The FindTask mechanism is facilitated by FindCommand which extends Command class. 

Below is an example usage of how the FindTask command can be used to search for a task in the TaskList using a keyword 
and how it behaves at each step.

Step 1. Define the Constructor: When the user executes the command `find read`, the Parser class calls the 
`FindCommand()` method of the FindCommand class. The constructor of the FindCommand class takes in the keyword 
string `read` as a parameter. This string is used to find tasks in the TaskList that contains this keyword.

Step 2. Override the `execute()` method: The `execute()` method is overridden to execute the find task 
functionality. It takes the necessary parameters, including the `Tasklist`, `Ui`, `Storage`, `ModuleList`, 
`allModule`, `calendar`.

Step 3. Find the list of tasks containing the `KEYWORD`: Using the parameter string `KEYWORD`, the `execute()` method 
will iterate through `TaskList` and call `printFoundList()` method in the Ui class that takes in a list of task,
`ArrayList<Tasks>`, containing the `KEYWORD` as a parameter. If the `KEYWORD` does not exist in any tasks 
in `TaskList`, a message is printed to the user indicating that there are no matching tasks that contains the 
`KEYWORD`.

Step 4. Print the confirmation message: A confirmation message is printed to the user indicating the list of 
tasks in `TaskList` that matches the `KEYWORD` input by the user. The message includes the task type, description (and
date of the task containing `KEYWORD` if the matching task is either an event or a deadline task).

### List Task
(TO BE ADDED SOON)

### Find Task on Date
(TO BE ADDED SOON)

### Storage
(TO BE ADDED SOON)

### Logging
(TO BE ADDED SOON)
## Documentation, logging, testing, configuration, dev-ops

### Documentation

### Logging

### Testing

### Configuration

### Dev-ops

## Appendix A: Requirements

### Product scope

#### Target user profile

The target user profile is an average NUS student who:

* has a need to manage a significant number of tasks (todo, event, deadline) and modules,
* prefer desktop applications over other types of applications,
* can type fast,
* prefers typing to mouse interactions,
* is reasonably comfortable using CLI apps.

#### Value proposition

Existing schedulers do not have access to NUS’s database, making it so that a student here would have to manually
input all their lessons. We can expedite this process by creating a scheduler that sets itself up via module codes.
It can also alert the user to possible event clashes.

### User Stories
Priority Legend:  
`***` - Highest priority (Must-haves) 
`**` - Medium priority  (Should-haves)
`*` - Lowest priority (Could-haves)

| Priority | Version |  As a/an ...  | I want to ...                                             | So that I can ...                                           |
|:--------:|:-------:|:-------------:|:----------------------------------------------------------|-------------------------------------------------------------|
|   ***    |  v1.0   |  expert user  | be able to quickly interface with the program using a CLI | issue commands to the bot                                   |
|   ***    |  v1.0   |   new user    | see usage instructions                                    | refer to them when I forget how to use the application      |
|   ***    |  v1.0   |     user      | find a to-do item by name                                 | locate a to-do without having to go through the entire list |
|   ***    |  v1.0   |     user      | delete a to-do item                                       | remove items that I no longer need to keep track of         |
|   ***    |  v1.0   |     user      | add a to-do item                                          | keep track of things that I need to do                      |
|   ***    |  v1.0   |     user      | add an event item                                         | keep track of events that I need to attend                  |
|   ***    |  v1.0   | busy student  | add a deadline item                                       | keep track of deadlines that I need to meet                 |
|   ***    |  v1.0   |     user      | list all items                                            | view all items that I have added                            |
|   ***    |  v1.0   |     user      | mark a to-do item as done                                 | keep track of which to-do items I have completed            |
|   ***    |  v1.0   |     user      | unmark a to-do item as not done                           | keep track of which to-do items I have not completed yet    |
|   ***    |  v1.0   |     user      | view task count                                           | see how much I have to do                                   |
|   ***    |  v1.0   | undergraduate | add modules                                               | keep track of the modules I am taking for the semester      |
|   ***    |  v1.0   | undergraduate | list all modules                                          | see all the modules I am taking for the semester            |
|    **    |  v1.0   | undergraduate | view module count                                         | keep how many modules I have for the semester               |
|   ***    |  v1.0   | undergraduate | delete modules                                            | remove modules that I am no longer taking                   |
|    **    |  v2.0   | undergraduate | keep track of the total modular credits                   | plan my academic year                                       |
|   ***    |  v2.0   |    student    | add lessons for each module                               | go to the correct classes                                   |

(More coming soon)
## Use Cases

## Non-Functional Requirements

* Apollo should work on any mainstream OS as long as it has Java 11 or above installed.
* Apollo should be able to hold up to 1000 tasks without a noticeable sluggishness in performance for typical usage.
* A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be
  able to accomplish most of the tasks faster using commands than using the mouse.

## Glossary

* *Mainstream OS* - Windows, Linux, Unix, OS-X
* *CLI* - Command Line Interface
*

## Appendix B: Instructions for manual testing

Given below are instructions to test the app manually.
> Note: These instructions only provide a starting point for testers to work on;
> testers are expected to do more *exploratory* testing. 
