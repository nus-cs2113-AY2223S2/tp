# Apollo Developer Guide

## Table of Contents

1. [Acknowledgments](#acknowledgements)
2. [Design](#design)
    + [Architecture](#architecture)
    + [UI Component](#ui-component)
    + [Parser Component](#parser-component)
    + [Command Component](#command-component)
    + [Storage Component](#storage-component)
3. [Implementation](#implementation)
    + [Task Commands](#task-commands)
    + [Module Commands](#module-commands)
    + [Utility Commands](#utility-commands)
    + [Storage](#storage)
    + [Logging](#logging)
4. [Appendix](#appendix)
   + [Appendix A: Product Scope](#appendix-a--product-scope)
   + [Appendix B: User Stories](#appendix-b--user-stories)
   + [Appendix C: Non-Functional Requirements](#appendix-c--non-functional-requirements) 
   + [Appendix D: Glossary](#appendix-d--glossary)
   + [Appendix E: Instructions for manual testing](#appendix-e--instructions-for-manual-testing)


## Acknowledgements

We would like to acknowledge Hong Lin Shang, whose Duke we built upon for our project. We would also like to acknowledge
the NUSMods Team, whose NUSMods API we used to scrape module data from.

## Design

### Architecture
Below is the overall architecture diagram for Apollo.

![](https://github.com/AY2223S2-CS2113-T13-4/tp/blob/master/docs/uml-diagrams/SystemArchitecture.png?raw=true)

Given below is a quick overview of the main components of Apollo and how they interact with one another.

`Apollo` is the main class and it is responsible for:
* At app launch: Initializes the components in the correct sequence, and connects them up with each other.
* Load the plaintext files (save and moduleData) and files in the Resources folder to populate its internal memory of
  ModuleList and TaskList.
* At shut down: Shuts down the components and invokes cleanup methods where necessary. Updates the plaintext files
  (save and moduleData) and files in the Resources folder to reflect the current state of Apollo.

The rest of the App consists of the following components:
* `UI`: The UI of the App.
* `Parser`: Parses the user input.
* `Command`: Executes the command.
* `Data Storage`: Reads data from, and writes data to, the hard disk.
* `Resources`: Contains relevant module data scraped from NUSMods_API, which is a database.

The user's (NUS_Student) interaction with the UI will be parsed into a command which would update the DataStorage 
and eventually update the UI which is displayed back to the user. This would continue until the user exits the program, 
which would result in the latest data stored in DataStorage being saved into the plaintext files.

### UI Component
![](https://github.com/AY2223S2-CS2113-T13-4/tp/blob/master/docs/uml-diagrams/Ui.png?raw=true)

### Parser Component
**API:** `Parser.java`
Here's a class diagram of the `Parser` component
![](https://github.com/AY2223S2-CS2113-T13-4/tp/blob/master/docs/uml-diagrams/Parser.png?raw=true)

### Command Component
**API:** `Command.java`    
Here's two class diagrams of the `Command` component, 
one for Task and Util related Commands, and one for Module related Commands   
![](https://github.com/AY2223S2-CS2113-T13-4/tp/blob/master/docs/uml-diagrams/Command-for_Task__Util_Commands.png.png?raw=true)
> SpecificHelpCommand is a placeholder Class for all command-specific help commands (eg. `help addmod`)    

![](https://github.com/AY2223S2-CS2113-T13-4/tp/blob/master/docs/uml-diagrams/Command2-for_Module_Commands.png?raw=true)

How the `Command` component works:
1. When a command is entered by the user, `Parser` will create the relevant subclass of `Command`. 
A logger is set up during the initialisation of the `Command`, after which it is sent back to `Apollo`. 
2. If the command entered was valid, `Apollo` then executes the `Command`. 
3. `Command` can communicate with `TaskList`, `ModuleList` and `Calendar` when it is executed (eg. to modify Tasks, to 
add Modules).
4. `Command` can also communicate with `Storage` to update the local save files if there are changes.
5. The result of the command execution is sent to `Ui` to be printed out to the user.   

![](https://github.com/AY2223S2-CS2113-T13-4/tp/blob/master/docs/uml-diagrams/Command_Seq-Command.png?raw=true)

Further elaboration on how the individual `Command` subclasses work can be found under [Implementation](#implementation)

### Storage Component
![](https://github.com/AY2223S2-CS2113-T13-4/tp/blob/master/docs/uml-diagrams/Storage.png?raw=true)

## Implementation

## *Task Commands*

<!--@@author yixuann02 -->

### List Task

The ListTask functionality allows users to list the tasks (todo, event and deadline) that are in the TaskList. It is
facilitated by ListCommand class which is an extension of the Command class.

Given below is an example usage scenario of how to list the tasks in the TaskList and how the mechanism behaves
at each step.

Step 1. Define the `setUpLogger()` method: The `setUpLogger()` method sets up the logger for the ModifyCommand
class.
It creates a ConsoleHandler and a FileHandler to handle logging.

Step 2. Override the `execute()` method: The `execute()` method is overridden to execute the list task
functionality. It takes the necessary parameters, including the `Tasklist`, `Ui`, `Storage`, `ModuleList`,
`allModule`, `calendar`.

<!--@@author T-Wan-Lin -->

Step 3. Iterate through the list of tasks and perform sorting: The `execute()` method will iterate through `TaskList`
and first calls the
`sortTaskByDay()` method in the TaskList class that takes in the list of tasks, `TaskList` that the user has updated
as a parameter. During the iteration, this method will first sort the task in the list by type with the method
`clusterByType()`, then by date with `deterministicSortForDeadline()` method for deadline type tasks sublist or
`deterministicSortForEvent()` for event type tasks sublist. The `clusterByType()` and deterministic sort
methods belong to the TaskList class. The `clusterByType()` method takes in the list of tasks, `TaskList` that the
user has updated, as a parameter and returns a list of tasks sorted by type. The `deterministicSortForDeadline()` takes
in two LocalDateTime objects which correspond to the dates of two deadlines being compared, whereas
the `deterministicSortForEvent()`takes in four LocalDateTime objects which correspond to the start and end dates of two
events being compared.

<!--@@author yixuann02 -->

Step 4. Iterate through the list of sorted tasks and print it:The `execute()` method then calls the `printList()` method
in the Ui class that takes in the list of tasks,`TaskList` that the user has updated, as a parameter.
During the iteration, the `printList()` method will check the `taskStatus` of each task and calculate
the total number of unmarked tasks. If the list is empty, a message is printed to the user indicating that there are
no tasks in the list.

Step 4. Print the confirmation message: A confirmation message is printed to the user indicating the list of tasks
in `TaskList` that the user updated and the total number of unmarked tasks. The message includes the task type,
description and date of all tasks if the tasks are either an event or a deadline task.

![](https://github.com/AY2223S2-CS2113-T13-4/tp/blob/master/docs/uml-diagrams/ListCommand-ListCommand.png?raw=true)

[*Return to TOC*](#table-of-contents)

<!--@@author honglinshang -->

### Add Task

The add task mechanism is facilitated by `AddCommand`. It extends `Command` with the ability to add three different
types of `Task`s to the TaskList, namely: `ToDo`, `Deadline`, and `Event`.

Given below is an example usage scenario and how the add task mechanism behaves at each step as the user adds an event.

Step 1. The user enters the command `event concert /from 2023-06-06T20:00 /to 2023-06-06T22:00`.  
This is to add a `Task` with the description "concert" on Jun 6 2023 from 8-10pm to their TaskList.
The String containing the command is parsed in `Parser` and determined to be an `AddCommand`.

Step 2. Within `Parser`, an `AddCommand` is initialised with the String `command` "event".
The remaining params of the command are further parsed into Strings: `desc` "concert" (description), `from`
"2023-06-06T20:00" (start date), and `to` "2023-06-06T22:00" (end date) based on the delimiters "/from" and "/to".
- For `command` "deadline", remaining params are parsed into `desc` and `by` (due date) based on the delimiter "/by".
- For `command` "todo", all remaining params are parsed into `desc`.

Step 3. The initialised `AddCommand` is returned to Apollo.
In the event of the following, an error message is printed and no more steps are executed.
- Delimiters are not entered correctly
- Remaining params of the command are empty (i.e. CLI input of user is "todo"/"deadline"/"event" only)

Step 4. `Command#execute()` is called. This in turn calls `AddCommand#addTask()`.
`addTask()` will try to initialise a new `Event` by parsing the Strings `from` and `to` into LocalDateTimes.
In the event of the following, an error message is printed and no more steps are executed.
- String for date cannot be parsed into LocalDateTime (wrong format of input)
- Task occurs entirely before the current date
- (for `Event`) Start date occurs after end date

Step 5. `addTask()` checks if the initialised `Event` clashes with any existing tasks. If so,
`Ui#printClashingEventMessage()` is called to print a warning message.

Step 6. Similarly, `addTask()` also checks if the initialised `Event` clashes with any existing lessons. If so,
`Ui#printClashingEventModuleMessage()` is called to print a warning message.

Step 7. The initialised `Event` is added to the `TaskList`. Return to `AddCommand#execute`.

Step 8. If the Task has been added successfully, `Ui#printAddMessage()` prints a success message.

Step 9. `Storage#updateTask()` is called to update the local save file to reflect the changes.

![](https://github.com/AY2223S2-CS2113-T13-4/tp/blob/master/docs/uml-diagrams/AddCommand-AddCommand__for_Tasks_.png?raw=true)

[*Return to TOC*](#table-of-contents)

<!--@@author T-Wan-Lin -->

### Modify Task
There are three ways to modify a task: delete, mark and unmark.

#### Delete Task

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

[*Return to TOC*](#table-of-contents)

#### Mark Task As Done

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

[*Return to TOC*](#table-of-contents)

#### Unmark Task

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

![](https://github.com/AY2223S2-CS2113-T13-4/tp/blob/master/docs/uml-diagrams/ModifyCommand-ModifyCommand__Unmark_Tasks_.png?raw=true)
![](https://github.com/AY2223S2-CS2113-T13-4/tp/blob/master/docs/uml-diagrams/UnmarkCommandActivityDiagram.png?raw=true)

[*Return to TOC*](#table-of-contents)

<!--@@author yixuann02 -->

### Find Task

The FindTask functionality allows user to search for a task (todo, event and deadline) from the TaskList using a
specific keyword. The FindTask mechanism is facilitated by FindCommand which extends Command class.

Below is an example usage of how the FindTask command can be used to search for a task in the TaskList using a keyword
and how it behaves at each step.

Step 1. Define the Constructor: When the user executes the command `find read`, the Parser class calls the
`FindCommand()` method of the FindCommand class. The constructor of the FindCommand class takes in the keyword
string `read` as a parameter. This string is used to find tasks in the TaskList that contains this keyword.

Step 2: Define the `setUpLogger()` method: The `setUpLogger()` method sets up the logger for the ModifyCommand
class.
It creates a ConsoleHandler and a FileHandler to handle logging.

Step 3. Override the `execute()` method: The `execute()` method is overridden to execute the find task
functionality. It takes the necessary parameters, including the `Tasklist`, `Ui`, `Storage`, `ModuleList`,
`allModule`, `calendar`.

Step 4. Find the list of tasks containing the `KEYWORD`: Using the parameter string `KEYWORD`, the `execute()` method
will iterate through `TaskList` and call `printFoundList()` method in the Ui class that takes in a list of task,
`ArrayList<Tasks>`, containing the `KEYWORD` as a parameter. If the `KEYWORD` does not exist in any tasks
in `TaskList`, a message is printed to the user indicating that there are no matching tasks that contains the
`KEYWORD`.

Step 5. Print the confirmation message: A confirmation message is printed to the user indicating the list of
tasks in `TaskList` that matches the `KEYWORD` input by the user. The message includes the task type, description and
date of the task containing `KEYWORD` if the matching task is either an event or a deadline task.

<!--@@author PoobalanAatmikaLakshmi -->

![](https://github.com/AY2223S2-CS2113-T13-4/tp/blob/master/docs/uml-diagrams/FindCommand-FindCommand__Find_tasks_.png?raw=true)

[*Return to TOC*](#table-of-contents)

<!--@@author yixuann02 -->

### Find Task on Date

The Find Task on Date functionality allows user to search for a list of tasks (event and deadline) that are happening
or due on a specific `date` in their TaskList. It is facilitated by the DateCommand class which is an extension of
the Command class.

Below is an example usage of how the Find Task on Date command can be used to search for tasks happening or due on
a specific date in the TaskList and how it behaves at each step.

Step 1. Define the Constructor: When the user executes the command `date 2023-03-22`, the Parser class calls the    
`DateCommand()` method of the DateCommand class. The constructor of the DateCommand class takes in the dateString
`2023-03-22`  as a parameter. This date is used to find the corresponding tasks happening or due on this date from
the TaskList.

Step 2. This date is then passed into the `LocalDate`. If the date parsed is in the wrong format (date format is not
`yyyy-MM-dd`), a `DateTimeParseException` is thrown, calling the `printInvalidDate()` method in the Ui class.

Step 3. Define the `setUpLogger()` method: The `setUpLogger()` method sets up the logger for the ModifyCommand
class.
It creates a ConsoleHandler and a FileHandler to handle logging.

Step 4. Override the `execute()` method: The `execute()` method is overridden to execute the find task
functionality. It takes the necessary parameters, including the `Tasklist`, `Ui`, `Storage`, `ModuleList`,
`allModule`, `calendar`.

Step 5. Find the list of tasks happening or due on the `date`: Using the parameter string `date` in the format
`yyyy-MM-dd`, the `execute()` method will iterate through the `TaskList` to look for tasks that occurs on the given
`date`. It will then call `printDateList()` method in the Ui class that takes in the list of tasks happening on the
given `date` and the LocalDate `date`. If there are no tasks on the specific `date`, a message is printed to the user
indicating that there are no tasks on that day.

Step 6. Print the confirmation message: A confirmation message is printed to the user indicating the list of tasks in
`TaskList` that are occurring on the `date` input by the user. The message includes the task type, description, date
and time of the task if the task is either an event or a deadline task.

<!--@@author PoobalanAatmikaLakshmi -->
![](https://github.com/AY2223S2-CS2113-T13-4/tp/blob/master/docs/uml-diagrams/DateCommand-DateCommand__Find_tasks_by_date_.png?raw=true)

[*Return to TOC*](#table-of-contents)

## *Module Commands*

<!--@@author irving11119 -->

### List Modules

The ListModule functionality allows users to list the modules that are in the ModuleList. It is facilitated by
ListModuleCommand class which is an extension of the Command class.

Given below is an example usage scenario of how to list the modules in the ModuleList and how the mechanism behaves
at each step.

Step 1. Define the `setUpLogger()` method: The `setUpLogger()` method sets up the logger for the ModifyCommand
class.
It creates a ConsoleHandler and a FileHandler to handle logging.

Step 2. Override the `execute()` method: The `execute()` method is overridden to execute the list module
functionality. It takes the necessary parameters, including the `Tasklist`, `Ui`, `Storage`, `ModuleList`,
`allModule`, `calendar`.

Step 3. Iterate through the list of modules: The `execute` method will iterate through `ModuleList` and call
`printModuleList()` method in the Ui class that takes in the list of modules, `ModuleList` that the user has updated,
as a parameter. During the iteration, the `printModuleList()` method will get the number of modular credits for each
module the user is taking and calculate the total modular credits in that semester. If the list is empty, a message
is printed to the user indicating that there are no modules in the list.

Step 4. Print the confirmation message: A confirmation message is printed to the user indicating the list of modules
in `ModuleList` that the user updated. The message includes the module code and name, modular credits for each module
and  total modular credits the user is taking this semester.

UML Sequence Diagram for ListModuleCommand

The following sequence diagram shows how the list module command works when the user inputs the command `listmod`:

![](https://github.com/AY2223S2-CS2113-T13-4/tp/blob/master/docs/uml-diagrams/ListModuleWithLessonCommand-ListModuleWithLessonCommand.png?raw=true)

[*Return to TOC*](#table-of-contents)

<!--@@author yixuann02 -->

### List Module With Lesson

The List Module with Lesson functionality allows users to list the lessons of a specific module in their timetable.
The use of flags, for eg. `-lec`, `-st` etc., allows users to view specific LessonType information for the module
that they have added into their timetable.

It is facilitated by ListModuleWithLessonCommand class which is an extension of the Command class.

Given below is an example usage scenario of how to view the information of a specific module in the timetable
using the moduleCode and how the mechanism behaves at each step.

#### For when user request to show the module (e.g. CS2113) in their timetable:

Step 1. Define the Constructor: When user executes the command `listmod cs2113`, the Parser class calls the
`ListModuleWithLessonCommand()` method of the ListModuleWithLessonCommand class. The constructor of the
ListModuleWithLessonCommand class takes in a parameter of type `String`, which will be split into `moduleCode` and
`-FLAG`. In this case, only `cs2113` is parsed in as the parameter. This is then used to find `cs2113` from the
existing timetable. If a moduleCode does not exist in NUS modules, an `InvalidModule()` is thrown.

Step 2. Define the `setUpLogger()` method: The `setUpLogger()` method sets up the logger for the ShowModuleCommand
class. It creates a ConsoleHandler and a FileHandler to handle logging.

Step 3. Override the `execute()` method: The `execute()` method is overridden to execute the List Module with
Lesson functionality. It takes in the necessary parameters, including the ModuleList, Ui, Storage, TaskList and
Calendar.

Step 4. Find the module from the timetable: The first step in the `execute()` method is to find the module from 
timetable using the `Module` class with the module code parameter `cs2113` and using the `getModuleTimetable()` 
function of the `Module` class. If the module `cs2113` has not been added to the timetable, `ModuleNotAddedException` 
will be thrown.

Step 5. Calls the `handleSingleCommand()` method: The `handleSingleCommand()` method is called to handle the command
with only the `moduleCode` as the parameter.

Step 6. Print the confirmation message: A confirmation message is printed to the user indicating the module and lesson 
information that the user has added into their module list. The message will include the `ModuleCode`, `LessonTypes`
of that user has added, `Classnumber` of the `LessonType` that user has added and `Day` and `Time` of the lesson.

#### For when user request to show a specific lesson of the module (e.g. CS2113 -tut) in their timetable:

Step 1. Define the Constructor: When user executes the command `listmod cs2113`, the Parser class calls the
`ListModuleWithLessonCommand()` method of the ListModuleWithLessonCommand class. The constructor of the
ListModuleWithLessonCommand class takes in a parameter of type `String`, which will be split into `moduleCode`, 
`cs2113` and `-FLAG`, `-tut`. In this case, `cs2113 -tut` is parsed in as the parameter. This is stored in the `args` 
array field of the `ListModuleWithLessonCommand` class.

Step 2. Define the `setUpLogger()` method: The `setUpLogger()` method sets up the logger for the ShowModuleCommand
class. It creates a ConsoleHandler and a FileHandler to handle logging.

Step 3. Override the `execute()` method: The `execute()` method is overridden to execute the List Module with
Lesson functionality. It takes in the necessary parameters, including the ModuleList, Ui, Storage, TaskList and
Calendar. The lesson type is determined by calling the `getLessonType()` method of the `lessonType` class and 
parsing in `args[1]` while the moduleCode is set by `args[0]`. If the lessonType is not valid, an 
`InvalidCommandException` is thrown.

Step 4. Find the module from the timetable: The first step in the `execute()` method is to find the module from
timetable using the `Module` class with the module code parameter `cs2113` and using the `getModuleTimetable()`
function of the `Module` class. If the module `cs2113` has not been added to the timetable, `ModuleNotAddedException`
will be thrown.

Step 5. Calls the `handleMultiCommand()` method: The `handleMultiCommand()` method is called to handle the command.

Step 6. Print the confirmation message: A confirmation message is printed to the user indicating the module and lesson
information that the user has added into their module list. The message will include the `ModuleCode`, the 
specific `LessonTypes` of that user has added, `Classnumber` of the `LessonType` that user has added and 
`Day` and `Time` of the lesson.

![](https://github.com/AY2223S2-CS2113-T13-4/tp/blob/master/docs/uml-diagrams/ListModuleWithLessonCommand-ListModuleWithLessonCommand.png?raw=true)

[*Return to TOC*](#table-of-contents)

<!--@@author irving11119 -->

### Add Module

The AddModule functionality allows users to add a module to their Module List. Beyond just adding their modules to the
module list, users are also able to add their specific lessons (e.g Lectures and Tutorials) to their module list.
This is facilitated by the AddMod command which is an extension of the Command class. Below is an example usage of how
the AddMod command can be used to add both modules and their specific lessons.

#### For when a user adds only a module (e.g CS2113) to the module list with no specific lessons:

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


#### For when a user adds a module's specific lesson (e.g CS2113 -lec 1) to the module list:

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

UML Sequence Diagram for AddModCommand Class

The following sequence diagram shows how the AddModCommand class works for both adding modules and adding lessons to 
the module list.:

![](https://github.com/AY2223S2-CS2113-T13-4/tp/blob/master/docs/uml-diagrams/AddModule-AddModuleCommand__Add_Module_.png?raw=true)

[*Return to TOC*](#table-of-contents)

<!--@@author PoobalanAatmikaLakshmi -->

### Delete Module

The DeleteModule functionality allows users to remove either a module from the ModuleList or a lesson associated with 
the module. It is facilitated by DeleteModuleCommand class which is an extension of the Command class.
Given below is an example usage scenario and how to delete mechanism behaves at each step.

#### For when a user deletes only a module (e.g CS2113) from the module list:

Step 1: Define the Constructor :
When user executes the command `delmod cs2113` the Parser class calls the `DeleteModuleCommand()` method of the
DeleteModuleCommand class. The constructor of the DeleteModuleCommand class takes in a moduleCode `cs2113` as a 
parameter. This moduleCode is
used to find `cs2113`  from the ModuleList.

Step 2: Define the `setUpLogger()` method :
The `setUpLogger()` method sets up the logger for the DeleteModuleCommand class. It creates a ConsoleHandler and a
FileHandler to handle logging.

Step 3: Override the `execute()` method :
The `execute()` method is overridden to execute the delete module functionality. It takes in the necessary parameters,
including the ModuleList, Ui, Storage, and TaskList.

Step 4: Find the module to delete :
The first step in the `execute()` method is to find the module using the module code parameter  `cs2113` by calling
the `findModule()` method of the moduleList class.
If the module `cs2113` is not found, a ModuleNotFoundException is thrown.

Step 5: Remove the module from the ModuleList :
If `cs2113` is found, it is removed from the ModuleList by calling `remove()` method of moduleList class.

Step 6: Print the confirmation message :
A confirmation message is printed to the user indicating that the `cs2113` has been successfully deleted.

Step 7: Update the storage :
The storage is updated with the new ModuleList without `cs2113`

#### For when a user deletes a specific lesson (e.g CS2113 -lec 1) from the module list:

Step 1: Define the Constructor :
When user executes the command `delmod cs2113 -lec 1` the Parser class calls the `DeleteModuleCommand()` method of the 
DeleteModuleCommand class. The constructor of the DeleteModuleCommand class takes in the string `cs2113 -lec 1` as a 
parameter. The string is split into a moduleCode `cs2113`, lessonType `-lec` and `1` as class number and stored in the
`args` array field of the `DeleteModule` class. 

Step 2: Define the `setUpLogger()` method :
The setUpLogger() method sets up the logger for the DeleteModuleCommand class. It creates a ConsoleHandler and a 
FileHandler to handle logging.

Step 3: Override `execute()` method :
The `execute()` method is overridden to execute the delete module functionality. It takes in the necessary parameters,
including the ModuleList, Ui, Storage, TaskList, AllModules and Calendar objects. The lesson type is determined by
calling the `getLessonType()` method of the `LessonType` class and parsing in `arg[1]` while the lesson number is 
set by `arg[2]`. If the lesson type is not valid, an`InvalidCommandException` is thrown.

Step 4: Calls the `handleMultiCommand()` method:
The `handleMultiCommand()` method is called to handle the command. It takes in `moduleList`, `lessonType` and `args` as
parameters. It then searches for the module using the `findModule()` method of the `ModuleList` class. If the module
is not found, a `ModuleNotFoundException` is thrown.

Step 5: Remove the module lessons from the ModuleList :
If the module is found, the timetable of the classes are removed from the module by calling the `removeTimetable()` 
method which takes in `searchModule` and `lessonType` as parameters. The `removeTimetable()` method of the `ModuleList`
will create a copy of the module timetable array list and search for the lesson to be removed. If the lesson type and 
class number matches, the lesson is removed from the original timetable array list using the `remove()` method. The
lesson is then marked as found by setting the `isFound` boolean to true. If the lesson is not found, a 
`ClassNotFoundException` is thrown.

Step 6: Print the confirmation message :
A confirmation message is printed to the user indicating that the module lesson has been successfully deleted. It is
is printed by calling the `printModuleLessonDeleteMessage()` method of the `Ui` class. If the module is not found, 
the message is printed by calling the `printModuleNotFoundMessage()` method of the `Ui` class. If the 
argument is invalid, the message is printed by calling the `printInvalidCommand()` method of the `Ui` class.

![](https://github.com/AY2223S2-CS2113-T13-4/tp/blob/master/docs/uml-diagrams/DeleteModuleCommand-DeleteModuleCommand.png?raw=true)

[*Return to TOC*](#table-of-contents)

<!--@@author yixuann02 -->

### Show Module

The ShowModule functionality allows users to see all the information of a specific module. The information includes 
LessonTypes, ClassNumbers and the Day and Time of the lessons, sorted lexicographically. The use of flags, for eg. `-lec`, `-st` etc., allows 
users to view specific LessonType information instead of all the information for the whole module.

ShowModule is facilitated by ShowModuleCommand class which is an extension of the Command class.

Given below is an example usage scenario of how to view the information of a specific module using the moduleCode and 
how the mechanism behaves at each step.

#### For when a user request to show the module (e.g. CS2113):

Step 1. Define the Constructor: When user executes the command `showmod cs2113`, the Parser class calls the
`ShowModuleCommand()` method of the ShowModuleCommand class. The constructor of the ShowModuleCommand class takes
in a parameter of type `String`, which will be split into `moduleCode` and `-FLAG`. In this case, only `cs2113` is 
parsed in as the parameter. This is then used to find `cs2113` from the module information. If the moduleCode, 
`cs2113`, is not found, an `InvalidModule()` is thrown.

Step 2. Define the `setUpLogger()` method: The `setUpLogger()` method sets up the logger for the ShowModuleCommand 
class. It creates a ConsoleHandler and a FileHandler to handle logging.

Step 3. Override the `execute()` method: The `execute()` method is overridden to execute the Show Module functionality.
It takes in the necessary parameters, including the ModuleList, Ui, Storage, TaskList and Calendar.

Step 4. Find the module to display information: The first step in the `execute()` method is to find the module from 
`Module` class using the module code parameter `cs2113` by using the `findModule()` function of the `Module` class.

Step 5. Print the confirmation message: A confirmation message is printed to the user indicating the information
of the module requested by the user. The message includes the `ModuleCode`, `LessonTypes` of the module, `Classnumber` 
of each `lessonTypes` and `Day` and `Time` of the existing `Classnumber`.

#### For when a user request to show a specific lessonType of the module (e.g. CS2113 -tut):

Step 1. Define the Constructor: When user executes the command `showmod cs2113`, the Parser class calls the
`ShowModuleCommand()` method of the ShowModuleCommand class. The constructor of the ShowModuleCommand class takes
in a parameter of type `String`, which will be split into `moduleCode`, `cs2113`, and `-FLAG`, `-tut`. In this case, 
`cs2113 -tut` is parsed in as the parameter. This is stored in the `args` array field of the `ShowModuleCommand` class.

Step 2. Define the `setUpLogger()` method: The `setUpLogger()` method sets up the logger for the ShowModuleCommand
class. It creates a ConsoleHandler and a FileHandler to handle logging.

Step 3. Override the `execute()` method: The `execute()` method is overridden to execute the Show Module functionality.
It takes in the necessary parameters, including the ModuleList, Ui, Storage, TaskList and Calendar objects. The lesson 
type is determined by calling the `getLessonType()` method of the `lessonType` class and parsing in `args[1]` while 
the moduleCode is set by `args[0]`. If the lessonType is not valid, an `InvalidCommandException` is thrown.

Step 4. Calls the `handleMultiCommand()` method: The `handleMultiCommand()` method is called to handle the command. 
It takes in `moduleList`, `lessonType` and `args` as parameters.

Step 5. Find the module to display information: The first step in the `execute()` method is to find the module from
`Module` class using the module code parameter `cs2113` by using the `findModule()` function of the `Module` class.
To find the lessonType of the module, `getLessonType()` is called to return the lessonType for the Show Module
functionality.

Step 6. Print the confirmation message: A confirmation message is printed to the user indicating the information
of the module requested by the user. The message includes the `ModuleCode`, the specific `LessonType` of the module, 
`Classnumber`of requested `lessonTypes` and `Day` and `Time` of the existing `Classnumber`.

![](https://github.com/AY2223S2-CS2113-T13-4/tp/blob/master/docs/uml-diagrams/ShowModuleCommand-ShowModuleCommand.png?raw=true)

<!--@@author -->

[*Return to TOC*](#table-of-contents)

## *Utility Commands*

### Viewing Help
![](https://github.com/AY2223S2-CS2113-T13-4/tp/blob/master/docs/uml-diagrams/HelpCommand-HelpCommand.png?raw=true)

<!--@@author honglinshang -->

[*Return to TOC*](#table-of-contents)

### Viewing Week
The `week` command allows the user to view their weekly schedule at a glance, including lessons, deadlines, and events.
It is facilitated by `WeekCommand` which is an extension of the `Command` class.

Given below is an example usage scenario and how the add task mechanism behaves at each step.

Step 1. The user executes the command `week`. It is parsed by the `Parser` class which then creates a new `WeekCommand`.

Step 2. The `execute()` method of `WeekCommand` is called. 

Step 3. The dates of Monday and Sunday of the current week (`startWeek`, `endWeek`) are determined using `LocalDate`.

Step 4. The parameters `startWeek`, `endWeek`, `taskList` (all tasks), and `calendar` (all lessons) are passed into `Ui`

Step 5. Starting from Monday, the lessons and tasks occurring on each day of the week are printed out. 
- Step 5a. The day of week is printed using the `determineDay()` method in `DayTypeUtil`. 
- Step 5b. All lessons on that day are stored in an `ArrayList<CalendarModule> lessonsOnDay` using `calender.get()`. 
  If no lessons occur on that day, Step 5c is skipped.
- Step 5c. `lessonsOnDay` is passed into the method `printLessonsOnDay()` in `Ui`. 
  The schedule of each lesson is stored in a new `Timetable`, then printed out in the desired format. 
- Step 5d. Similarly, All tasks on that day are stored in an `TaskList tasksOnDay` using `taskList.getTasksOnDay()`.
  If no lessons occur on that day, Step 5e is skipped.
- Step 5e. `tasksOnDay` is passed into the method `printTasksOnDay()` in `Ui`. Each task is printed out.
- Step 5f. The current day is increased to the following day.
- Step 5g. Go back to Step 5a, stop after all lessons and tasks on Sunday have been printed. 

![](https://github.com/AY2223S2-CS2113-T13-4/tp/blob/master/docs/uml-diagrams/Week-WeekCommand.png?raw=true)

[*Return to TOC*](#table-of-contents)

<!--@@author -->

### Exiting the Program

The `bye` command allows the user to exit the program. It is facilitated by `ExitCommand` which is an extension of the `Command` class.
The `ExitCommand` class overrides the `execute()` method from the `Command` class and is only excuted when the user inputs `bye`
with no additional parameters (e.g `bye bye` would not exit the program).

Given below is an example usage scenario and how the add task mechanism behaves at each step.

Step 1. The user executes the command `bye` and handled by `Apollo` class. It is parsed by the `Parser` class which 
then creates a new `ExitCommand`.

Step 2. The `setUpLogger()` method of the `ExitCommand` class is called. It creates a `ConsoleHandler` and a `FileHandler`
to handle logging.

Step 3. The `execute()` method of `ExitCommand` is called from `Apollo` class. It takes in the necessary parameters.

Step 4. Within the `execute()` method, the method `printExitMessage()` is called from `Ui` class to print the exit message.

Step 5. The `setExit()` method of `ExitCommand` class is called to set the `isExit` boolean to true. Subsequently, the
program exceeds the loop in the `run()` method of `Apollo` class and the program terminates.

Below is a sequence diagram of the `bye` command.

![](https://github.com/AY2223S2-CS2113-T13-4/tp/blob/master/docs/uml-diagrams/ExitCommand-ExitCommand.png?raw=true)

[*Return to TOC*](#table-of-contents)

## *Logging*
(TO BE ADDED SOON)

# Appendix

## Appendix A: Product Scope

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

Priority Legend:  
`***` - Highest priority (Must-haves) 
`**` - Medium priority  (Should-haves)
`*` - Lowest priority (Could-haves)

## Appendix B: User Stories

| Priority | Version |           As a/an ...           | I want to ...                                              | So that I can ...                                                             |
|:--------:|:-------:|:-------------------------------:|:-----------------------------------------------------------|-------------------------------------------------------------------------------|
|   ***    |  v1.0   |           expert user           | be able to quickly interface with the program using a CLI  | issue commands to the bot.                                                    |
|   ***    |  v1.0   |            new user             | see usage instructions                                     | refer to them when I forget how to use the application.                       |
|   ***    |  v1.0   |              user               | find a to-do item by name                                  | locate a to-do without having to go through the entire list.                  |
|   ***    |  v1.0   |              user               | delete a to-do item                                        | remove items that I no longer need to keep track of.                          |
|   ***    |  v1.0   |              user               | add a to-do item                                           | keep track of things that I need to do.                                       |
|   ***    |  v1.0   |              user               | add an event item                                          | keep track of events that I need to attend.                                   |
|   ***    |  v1.0   |          busy student           | add a deadline item                                        | keep track of deadlines that I need to meet.                                  |
|   ***    |  v1.0   |              user               | list all items                                             | view all items that I have added.                                             |
|   ***    |  v1.0   |              user               | mark a to-do item as done                                  | keep track of which to-do items I have completed.                             |
|   ***    |  v1.0   |              user               | unmark a to-do item as not done                            | keep track of which to-do items I have not completed yet.                     |
|   ***    |  v1.0   |              user               | view task count                                            | see how much I have to do.                                                    |
|   ***    |  v1.0   |          undergraduate          | add modules                                                | keep track of the modules I am taking for the semester.                       |
|   ***    |  v1.0   |          undergraduate          | list all modules                                           | see all the modules I am taking for the semester.                             |
|    **    |  v1.0   |          undergraduate          | view module count                                          | keep how many modules I have for the semester.                                |
|   ***    |  v1.0   |          undergraduate          | delete modules                                             | remove modules that I am no longer taking.                                    |
|    **    |  v2.0   |          undergraduate          | keep track of the total modular credits                    | plan my academic year.                                                        |
|   ***    |  v2.0   |             student             | add specific lesson types lessons for each module          | go to the correct classes at the right time.                                  |
|   ***    |  v2.0   |             student             | delete specific lesson types for each module               | avoid going to a class I never signed up for.                                 |
|   ***    |  v2.0   |             student             | see my lesson timetable                                    | see the timings of my lessons for the week.                                   |
|    **    |  v2.0   |     over-committed student      | be alerted to clashes between events and module lessons    | rearrange conflicting events.                                                 |
|    **    |  v2.0   |          busy student           | be alerted to clashes between deadlines and events         | avoid missing out on important deadlines.                                     |
|    **    |  v2.0   |          busy student           | be alerted to clashes between deadlines and module lessons | avoid missing out on important deadlines during lessons.                      |
|    **    |  v2.0   |        organised student        | be able to organise my tasklist according to type          | be able to read the tasklist more easily.                                     |
|    **    |  v2.0   |        organised student        | be able to organise my tasklist according to date          | prioritise deadlines and events correctly.                                    |
|    **    |  v2.0   |             student             | see my overall timetable                                   | see all lessons, events and module lessons at the same time.                  |
|    **    |  v2.0   |         curious student         | view module information                                    | see what a module is about without adding it to my module list.               |
|    **    |  v2.0   | prospective student of a module | view module lesson information                             | see which lessons I am able to fit into my timetable.                         |
|    **    |  v2.0   |             student             | list all lessons I have for a module                       | see if I registered for the right lessons.                                    |
|    **    |  v2.1   |        forgetful student        | view which weeks my lessons occur                          | turn up for lesson on the correct weeks.                                      |
|    **    |  v2.1   |         forgetful user          | view help menu for specific commands                       | get formatting options for each command without viewing the entire help menu. |


## Appendix C: Non-Functional Requirements

* Apollo should work on any mainstream OS as long as it has Java 11 or above installed.
* Apollo should be able to work on a typical CLI without any issues. It does not require installation of third-party
  software.
* Apollo should be able to hold up to 1000 tasks without a noticeable sluggishness in performance for typical usage.
* A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be
  able to accomplish most of the tasks faster using commands than using the mouse.
* Apollo is free-to-use. No payment is required to use the application.
* The user interface should be intuitive enough for users who are new to the application.
* Apollo should respond to any user interaction within 3 seconds.
* Module data for Apollo is limited to what is available on NUSMods, i.e. Apollo has module data for all modules offered
  by NUS in AY22/23 Semester 1 and 2.

## Appendix D: Glossary

* *Mainstream OS* - Windows, Linux, Unix, OS-X
* *CLI* - Command Line Interface
* *Modules* - Modules are the courses that students take in NUS. They are identified by a 6-character alphanumeric code.
* *Modular Credits* - Modular credits are the credits that students earn for each module they take. They are used to
  calculate the total number of credits a student has earned for the semester.

## Appendix E: Instructions for manual testing

Given below are instructions to test the app manually.
> Note: These instructions only provide a starting point for testers to work on;
> testers are expected to do more *exploratory* testing.

### Launch
* Download the .jar file from the latest release here and copy it into an empty folder. 
* Open a terminal in the folder and run the command `java -jar apollo.jar`. The CLI should appear in a few seconds.
* Expected: The CLI should appear with a welcome message and a prompt to enter a command. Resize the CLI window size
    for optimal text wrapping.
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
### Sample test cases
#### Invalid Commands
1. Type `hello` and press enter.
2. Any commands Apollo cannot understand will be treated as invalid commands.

Expected: Apollo should respond with an error message for invalid commands.
```
____________________________________________________________
Sorry, but I don't know what that means :(
____________________________________________________________
```
#### Help Command
1. Make sure you are in the main interface.
2. Sub-case 1: Type `help` and press enter.
3. Sub-case 2: You may also type `help [COMMAND]` to get more information about a specific command. Example: `help addmod`

Expected of Sub-case 1: The help menu should appear with the list of all commands available on Apollo with `help`.
```
The help menu gives a summary of all the commands available in Apollo!
Input `help` to see all available commands.Input "help [COMMAND]" for usage help and more information for a specific command.

These are the available Task Commands and their corresponding commands (in brackets):

1. `list` - Track and organises your tasklist!
2. `todo [TASK]` - Adds a ToDo in your tasklist.
3. `deadline [TASK] -[BY]` - Adds a Deadline in your tasklist.
4. `event [TASK] -[FROM] -[TO]` - Adds an Event in your tasklist.
5. `mark [IDX]` - Marks a task in your tasklist as done!
6. `unmark [IDX]` - Unmarks a task in your tasklist as incomplete.
7. `delete [IDX]` - Deletes a task from your list.
8. `find [KEYWORD]` - Shows all tasks that contain a specified keyword.
9. `date [DATE]` - Shows all tasks that occur on the specified date.

____________________________________________________________
These are the available Module Commands and their corresponding commands (in brackets):

1. `listmod` - Track and organise your academic plan for this semester!
2. `listmod [MODULE_CODE]` - See more information about the classes you've added for a module in your list.
3. `listmod [MODULE_CODE] -[FLAG]` - See more information about a specific class type for a module in your list
4. `showmod [MODULE_CODE]` - See more information about the specified module.
5. `showmod [MODULE_CODE] -[FLAG]` - View timing of specific lesson type for a chosen module
6. `addmod [MODULE_CODE]` - Adds a module to your module list.
7. `addmod [MODULE_CODE] -[FLAG] [LESSON NUMBER]` - Adds a chosen lesson of a specified module to your timetable! 
8. `Remove a module (delmod [MODULE_CODE or IDX]` - Removes a Module you previously added by code or index in module list.
9. `delmod [MODULE_CODE] -[FLAG] [LESSON NUMBER]` - Removes a lesson of a specified module from your timetable. 

NOTE: showmod, addmod, delmod, listmod are commands with flags included in them. 
Whatever in [THE SQUARE BRACKETS] are provided by you.For more information on the flags, please input "help [COMMAND]" exclusive of the square brackets. 
For example, if you want to know more about the addmod command and its flags, input "help addmod".

____________________________________________________________
These are the Utility Commands:

1. `week` - Displays your schedule for the week.
2. `bye` - Exit the program
3. `help` - Get a summary of all the commands available on Apollo.
View help for a specific command by inputting help [COMMAND] 
____________________________________________________________
```
Expected of Sub-case 2: The help menu for that command should appear.
```
____________________________________________________________
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
____________________________________________________________
```
#### Adding a ToDo/Event/Deadline
Prerequisite: Make sure you are in the main interface.
#### Invalid Commands
1. Test case for empty task description: ```todo ``` or ```deadline``` or ```event```
Expected: Exception thrown. Error details shown in status message
2. Test case for invalid formats
   - Out of calendar range: ```deadline return book -by 40-11-2023-23:23``` or ```event wedding -from 40-11-2023-22:23 -to 41-11-2023-11:23```
   - Invalid dateTime format ```deadline return book -by 2023-10-11-11:23``` or ```event wedding -from 2023-10-11-11:23 -to 2023-10-12-11:23```
   - Missing parameters ``` deadline return book 15-11-2023-11:23``` or ```event wedding 16-11-2023-11:23 -to 20-11-2023-11:23```
   - Extra parameters ```deadline return book -by 17-11-2023-11:23 blah blah```
   - Occurs before system dateTime `deadline return book 15-01-2023-11:23` or `event wedding 16-01-2023-11:23 -to 20-01-2023-11:23`
   
   For all these cases Expected: Exception thrown. Error details shown in status message. 
   For instance invalid dateTime format prints `Please enter [date]s in the format of dd-MM-yyyy-HH:mm.
   eg. "30-10-2023-23:59" for Oct 30 2023, 11:59PM`
### Adding a ToDo
1. Test case : ```todo Feed the fish```

Expected: ToDo is added into TasksList. Details shown in status message.
2. Test case : ```todo```

Expected: Empty task description exception thrown. Error details shown in status message 
### Adding a Deadline
1. Test case : ```deadline return book -by 17-11-2023-11:23```

Expected: Deadline is added into TasksList. Details shown in status message.
### Adding a Event
1. Test case : ``````event wedding -from 16-11-2023-11:23 -to 20-11-2023-11:23``````

Expected: Event is added into TasksList. Details shown in status message.

### Deleting a Todo/Event/Deadline
Prerequisites: Use command ```list``` to obtains task's index ```[IDX]```
1. Test case : ```del [IDX]```

Expected: Task is deleted from tasksList. Deletion confirmation message is shown. 
2. Test case : ```del [IDX not inside list]```

Expected: Exception thrown. Error details shown in status message

#### Adding a Module
1. Test case : ```addmod cs2113```

Expected: Module added into moduleList.Details shown in status message
2. Test case : ```addmod cs1111```

Expected: Exception thrown as module does not exist currently. Error details shown in status message

3. Test case : ```addmod```

Expected: Exception thrown due to empty description. Error details shown in status message

#### Adding a Lesson
Prerequisites: Obtain lessons timings and numbers using ```showmod CS1010 -st``` which shows all available lessons of type ```st``` of ```CS1010```
or ```showmod CS1010``` which shows all the lesson types available for CS1010 

1.Test case : ```addmod CS1010 -st 1```

Expected: first section teaching lesson of CS1010 to your module list. If this lesson clashes with any of your other lessons a warning message will be displayed,
but you will still be able to add it.

2.Test case ```addmod CS1010 -st 99``` or ```addmod CS1010 -oo 1```

Expected: Exception thrown as due to invalid arguments. Error details shown in status message
#### Deleting a Module
Prerequisites: Use command ```listmod``` to obtains module's index ```[IDX]``` for 1.
1. Test case: ```delmod 1```

Expected: Module under index 1 is deleted from moduleList. Confirmation message is shown
2. Test case: ```delmod cs1010``` assuming cs1010 is inside moduleList

Expected: CS1010 is removed from moduleList. Confirmation message is printed 
3. Test case: ```delmod cs2040c``` assuming cs2040c is not in your moduleList 

Expected: Exception thrown, `Sorry, the module cs2040c does not exist in your Module list!
Total modular credits you have in this semester:`[Number of MCs in your moduleList]

#### Deleting a Lesson
1. Test case: `delmod CS1010 -st 1` assuming cs1010 -st 1 is inside moduleList

Expected: Deletes SECTIONAL TEACHING - 1 of CS1010.
2.Test case: `delmod CS1010 -st 5` assuming cs1010 -st 5 not inside moduleList

Expected: Exception thrown, error message printed 
### Saving Data

1. Dealing with save files with erroneous data.

The save file for tasks is located at save.txt within the *home folder* for Apollo. 
The save file for modules is located at moduleData.txt within the *home folder* for Apollo.
To simulate erroneous save files, edit either files in the data folder to contain erroneous data.
You can input random characters, or delete some lines to simulate a corrupted save file. Apollo should be able to
detect the errors and inform the user of the errors, skipping the erroneous data entirely.

Example message when save.txt has errors when the first line has been mutated so that the data is no longer 
in the correct format:
```
Module Data loaded
____________________________________________________________
There is an error in save.txt at line 1
Task 1 has been excluded. You can edit the save file at:
save.txt
____________________________________________________________
```
Note: The above message will be displayed before the welcome message.

[*Return to TOC*](#table-of-contents)
