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
    + [Logging](#logging)
4. [Appendix](#appendix)
   + [Appendix A: Product Scope](#appendix-a-product-scope)
   + [Appendix B: User Stories](#appendix-b-user-stories)
   + [Appendix C: Non-Functional Requirements](#appendix-c-non-functional-requirements) 
   + [Appendix D: Glossary](#appendix-d-glossary)
   + [Appendix E: Instructions for manual testing](#appendix-e-instructions-for-manual-testing)


## Acknowledgements

We would like to acknowledge Hong Lin Shang, whose Duke we built upon for our project. We would also like to acknowledge
the NUSMods Team, whose NUSMods API we used to scrape module data from. Additionally, the usage of GSON API was used to
read JSON data for our project.

### Duke:
* [Duke Project IP](https://github.com/honglinshang/ip)
* Author: Hong Lin Shang
* Based on: [Duke Project](https://github.com/nus-cs2113-AY2223S2/ip)

### NUSMods:
* [NUSMods API](https://api.nusmods.com/v2/)
* [NUSMods Repository](https://github.com/nusmodifications/nusmods)
* License: [MIT License](https://github.com/nusmodifications/nusmods/blob/master/LICENSE)
* Author: NUSMods Team

### GSON:
* [GSON API](https://github.com/google/gson)
* License: [Apache License 2.0](https://github.com/google/gson/blob/master/LICENSE)
* Author: Google

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
**API** `Ui.java`
Here's a class diagram of the `Ui` component
![](https://github.com/AY2223S2-CS2113-T13-4/tp/blob/master/docs/uml-diagrams/Ui.png?raw=true)

How the `Ui` component works:
1. When a command is entered by the user, `Parser` will create the relevant subclass of `Command`
2. If the command is invalid, `Ui` will be called to print an error message.
3. On the other hand, `Ui` will print a confirmation/success message after the execution of a task.

### Parser Component
**API:** `Parser.java`
Here's a class diagram of the `Parser` component
![](https://github.com/AY2223S2-CS2113-T13-4/tp/blob/master/docs/uml-diagrams/Parser.png?raw=true)

How the `Parser` component works:
1. When a command is entered by the user, `Parser` split the inputs from user accordingly and create the relevant
subclass of `Command`.
2. `Parser` will check for the number of arguments after splitting the inputs from user. 
3. If the parameter is empty, `Parser` will throw `Exceptions` according to the input commands. 
4. Otherwise, `Parser` will then create the subclass of `Command` according to the user input.

![](https://github.com/AY2223S2-CS2113-T13-4/tp/blob/master/docs/uml-diagrams/Parser_Seq-Parser.png?raw=true)

### Command Component
**API:** `Command.java`    
Here's two class diagrams of the `Command` component, 
one for Task and Util related Commands, and one for Module related Commands   
![](https://github.com/AY2223S2-CS2113-T13-4/tp/blob/master/docs/uml-diagrams/Command-for_Task__Util_Commands.png?raw=true)
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
**API:** `Storage.java`  
Here is a class diagram of the 'Storage component'
![](https://github.com/AY2223S2-CS2113-T13-4/tp/blob/master/docs/uml-diagrams/Storage.png?raw=true)

How the `Storage` component works:
1. When the user first launches `Apollo`, the `Storage` will look for the storage text files (`save.txt` and
`moduleData.txt`). 
2. If these text files do not exist, `Storage` will create new text files.
3. If these text files exist, `Storage` will read from the text files and write into `Apollo` for the user to use.
4. When a command is entered by the user, the `Command` class will communicate with `Storage` to update the local save
files if there are changes.

[*Return to TOC*](#table-of-contents)

## Implementation

## *Task Commands*

<!--@@author yixuann02 -->

### List Task

The ListTask functionality allows users to list the tasks (todo, event and deadline) that are in the TaskList. It is
facilitated by ListCommand class which is an extension of the Command class.

Given below is an example usage scenario of how to list the tasks in the TaskList and how the mechanism behaves
at each step.

**Step 1. Define the `setUpLogger()` method:**
The `setUpLogger()` method sets up the logger for the ModifyCommand
class.
It creates a ConsoleHandler and a FileHandler to handle logging.

**Step 2. Override the `execute()` method:**
The `execute()` method is overridden to execute the list task
functionality. It takes the necessary parameters, including the `TaskList`, `Ui`, `Storage`, `ModuleList`,
`allModule`, `calendar`.

<!--@@author T-Wan-Lin -->

**Step 3. Iterate through the list of tasks and perform sorting:**

The `execute()` method will iterate through `TaskList`
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

**Step 4. Iterate through the list of sorted tasks and print it:**

The `execute()` method then calls the `printList()` method
in the Ui class that takes in the list of tasks,`TaskList` that the user has updated, as a parameter.
During the iteration, the `printList()` method will check the `taskStatus` of each task and calculate
the total number of unmarked tasks. If the list is empty, a message is printed to the user indicating that there are
no tasks in the list.

**Step 4. Print the confirmation message:**

A confirmation message is printed to the user indicating the list of tasks
in `TaskList` that the user updated and the total number of unmarked tasks. The message includes the task type,
description and date of all tasks if the tasks are either an event or a deadline task.

![](https://github.com/AY2223S2-CS2113-T13-4/tp/blob/master/docs/uml-diagrams/ListCommand-ListCommand.png?raw=true)

[*Return to TOC*](#table-of-contents)

<!--@@author honglinshang -->

### Add Task

The add task mechanism is facilitated by `AddCommand`. It extends `Command` with the ability to add three different
types of `Task`s to the TaskList, namely: `ToDo`, `Deadline`, and `Event`.

Given below is an example usage scenario and how the add task mechanism behaves at each step as the user adds an event.

**Step 1.**

The user enters the command "`event concert -from 05-06-2023-20:00 -to 05-06-2023-22:00`".  
This is to add a `Task` with the description "concert" on Jun 5 2023 from 8-10pm to their TaskList.
`Apollo` calls `Parser#getCommand()`, where the user command String is parsed and determined to be an `AddCommand`.

**Step 2.**

Within `Parser`, an `AddCommand` is initialised and a logger for the command is set up. 
Variables within the `AddCommand` are then assigned. The String `command` is set to "event".
The rest of the user's command are further parsed into Strings: `desc` "concert" (description), `from`
"05-06-2023-20:00" (start date), and `to` "05-06-2023-22:00" (end date) based on the delimiters "`-from`" and "`-to`".
- For `command` "deadline", remaining params are parsed into `desc` and `by` (due date) based on the delimiter "`-by`".
- For `command` "todo", all remaining params are parsed into `desc`.

**Step 3.**

The initialised `AddCommand` is returned to Apollo.
In the event of the following, an error message is printed and no more steps are executed.
- Delimiters are not entered correctly
- Remaining params of the command are empty (i.e. CLI input of user is "todo"/"deadline"/"event" only)

**Step 4.**

`Apollo` calls `Command#execute()`, which is overwritten by `AddCommand#execute()`. 
This in turn calls `AddCommand#addTask()`.

**Step 5.**
`addTask()` will try to initialise a new `Event` by parsing the Strings `from` and `to` into `LocalDateTime`s.
- For `Deadline`, the String `by` will be parsed into a `LocalDateTime`.
- For `Todo`, no dates need to be parsed.   

In the event of the following, an error message is printed and no more steps are executed.   
- String for date cannot be parsed into LocalDateTime (wrong format of input)
- Task occurs entirely before the current date
- (for `Event`) Start date occurs after end date

**Step 6.**

`addTask()` checks if the initialised `Task` clashes with any existing tasks. If so,
`Ui#printClashingEventMessage()` is called to print a warning message.

**Step 7.**

Similarly, `addTask()` also checks if the initialised `Task` clashes with any existing lessons. If so, 
`Ui#printClashingEventModuleMessage()` is called to print a warning message.

**Step 8.**

The initialised `Task` is added to the `TaskList`. Return to `execute()`.

**Step 9.**

`execute()` ensures that the `Task` has been added successfully, 
then calls `Ui#printAddMessage()` which prints a success message.

**Step 10.**

`execute()` calls `Storage#updateTask()` to update the local save file `save.txt` to reflect the changes.

Here is a Sequence Diagram illustrating the adding of an `Event`: 
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

**Step 1: Define the Constructor:**

When the user executes the command `delete 1`, the Parser class calls the
`ModifyCommand()` method of the ModifyCommand. The constructor of the ModifyCommand class takes in the taskIndex `1`
as a parameter. This index is used to find the task to be deleted from the TaskList.

**Step 2: Define the `setUpLogger()` method:**

The `setUpLogger()` method sets up the logger for the ModifyCommand
class.
It creates a ConsoleHandler and a FileHandler to handle logging.

**Step 3: Override the `execute()` method:**

The `execute()` method is overridden to execute the delete task functionality.
It takes in the necessary parameters, including the `TaskList`, `Ui`, `Storage`, `ModuleList`, `allModules`.

**Step 4: Find out how to modify the TaskList:**

The first step in the `execute()` method is to find out how to modify the
TaskList. In this case, the task at a user-provided index is to be deleted.

**Step 5: Find the task to delete:**

Using the parameter `taskIndex`, the `execute()` method will iterate to that
index in the `TaskList` and call the `remove()` method of the `TaskList` class. If the index is outside the bounds of
the size of the `TaskList`, a `NumberFormatException` is thrown, calling the `printErrorForIdx()` method in the Ui class
that takes in the size of the `TaskList` as a parameter.

**Step 6: Print the confirmation message:**

A confirmation message is printed to the user indicating what task has been
successfully removed from the user-provided index of the `TaskList`. The message includes the task type, description
(and date of the task deleted if the task is either an event or a deadline).
It also includes the updated size of the `TaskList`, obtained with the `size()` method of the `TaskList` class.

**Step 7: Update the storage:**

The storage is updated with the new TaskList without the deleted task.

The below diagram shows the sequence diagram for the DeleteTask functionality.
![](https://github.com/AY2223S2-CS2113-T13-4/tp/blob/master/docs/uml-diagrams/DeleteCommand-ModifyCommand__Delete_Tasks_.png)

The below diagram shows the activity diagram for DeleteCommand for tasks 
![](https://github.com/AY2223S2-CS2113-T13-4/tp/blob/master/docs/uml-diagrams/DeleteCommandActivityDiagram.png)

[*Return to TOC*](#table-of-contents)

#### Mark Task As Done

The MarkTask functionality allows users to mark a task (todo, event and deadline) as done in their TaskList.
It is facilitated by the ModifyCommand class which is an extension of the Command class.
Below is an example usage of how the MarkTask command can be used to mark a task as done.

**Step 1: Define the Constructor:**

When the user executes the command `mark 1`, the Parser class calls the
`ModifyCommand()` method of the ModifyCommand. The constructor of the ModifyCommand class takes in the taskIndex `1`
as a parameter. This index is used to find the task to be marked as done from the TaskList.

**Step 2: Define the `setUpLogger()` method:**

The `setUpLogger()` method sets up the logger for the ModifyCommand
class.
It creates a ConsoleHandler and a FileHandler to handle logging.

**Step 3: Override the `execute()` method:**

The `execute()` method is overridden to execute the mark task functionality.
It takes in the necessary parameters, including the `TaskList`, `Ui`, `Storage`, `ModuleList`, `allModules`.

**Step 4: Find out how to modify the TaskList:**

The first step in the `execute()` method is to find out how to modify the
TaskList. In this case, the task at a user-provided index is to be marked as done.

**Step 5: Find the task to mark as done:**

The `execute()` method will perform a call to the method `markTask()`
that takes in the `TaskList` and `Ui` as parameters. It will iterate to the user-given index, and it will first check
the task completeness status. If it is not, call the `setAsDone()` method of the `Task` class, setting the boolean `isDone`
to `true`, proceeding on to step 6a. If the task the user is trying to mark is done from previous iterations
, it will proceed to step 6b.
If the index is outside the bounds of the size of the `TaskList`, a `NumberFormatException` is thrown,
calling the `printErrorForIdx()` method in the Ui class that takes in the size of the `TaskList` as a parameter.

**Step 6: Print the confirmation message:**

Step 6a: A confirmation message is printed to the user indicating what task has been
successfully marked as done from the user-provided index of the `TaskList`. The message includes the task type,
description (and date of the task deleted if the task is either an event or a deadline).The execution will proceed to step 7.

Step 6b: Apollo will print a message to the user to state that the task was marked as done previously. The execution of the mark
command will stop here.

**Step 7: Update the storage:**

The storage is updated with the new TaskList with the task marked with a cross next to it.

The sequence diagram for marking of tasks is similar to that of the unmark task command. The only difference is that the
Ui message formats. The activity diagram for marking of tasks is shown below.


[*Return to TOC*](#table-of-contents)

#### Unmark Task

The UnmarkTask functionality allows users to toggle a task (todo, event and deadline) to *not* done in their TaskList.
It is facilitated by the ModifyCommand class which is an extension of the Command class.
Below is an example usage of how the UnmarkTask command can be used to unmark a task as not done.

**Step 1: Define the Constructor:**

When the user executes the command `unmark 1`, the Parser class calls the
`ModifyCommand()` method of the ModifyCommand. The constructor of the ModifyCommand class takes in the taskIndex `1`
as a parameter. This index is used to find the task to be unmarked from the TaskList.

**Step 2: Define the `setUpLogger()` method:**

The `setUpLogger()` method sets up the logger for the ModifyCommand
class.
It creates a ConsoleHandler and a FileHandler to handle logging.

**Step 3: Override the `execute()` method:**

The `execute()` method is overridden to execute the mark task functionality.
It takes in the necessary parameters, including the `TaskList`, `Ui`, `Storage`, `ModuleList`, `allModules`.

**Step 4: Find out how to modify the TaskList:**

The first step in the `execute()` method is to find out how to modify the
TaskList. In this case, the task at a user-provided index is to be unmarked.


**Step 5: Find the task to unmark as not done:**

The `execute()` method will perform a call to the method `unmarkTask()`
that takes in the `TaskList` and `Ui` as parameters. It will iterate to the user-given index, and it will first check
the task completeness status. If it is done, call the `setAsDone()` method of the `Task` class, setting the boolean `isDone`
to `false`, proceeding on to step 6a. If the task the user is trying to unmark is not done from previous iterations
, it will proceed to step 6b.
If the index is outside the bounds of the size of the `TaskList`, a `NumberFormatException` is thrown,
calling the `printErrorForIdx()` method in the Ui class that takes in the size of the `TaskList` as a parameter.


**Step 6a: Print the confirmation message:**

A confirmation message is printed to the user indicating what task has been
successfully unmarked from the user-provided index of the `TaskList`. The message includes the task type,
description (and date of the task deleted if the task is either an event or a deadline). The execution will move on to step 7.

**Step 6b:**

Apollo will print a message to the user to state that the task was never marked as done. The execution of the unmark
command will stop here.

**Step 7: Update the storage:**

The storage is updated with the new TaskList with the task marked without a cross next to
it.

The below sequence and activity diagram summarises the above steps for the unmark task command.
![](https://github.com/AY2223S2-CS2113-T13-4/tp/blob/master/docs/uml-diagrams/ModifyCommand-ModifyCommand__Unmark_Tasks_.png?raw=true)
![](https://github.com/AY2223S2-CS2113-T13-4/tp/blob/master/docs/uml-diagrams/UnmarkCommandActivityDiagram.png?raw=true)

[*Return to TOC*](#table-of-contents)

<!--@@author yixuann02 -->

### Find Task

The FindTask functionality allows user to search for a task (todo, event and deadline) from the TaskList using a
specific keyword. The FindTask mechanism is facilitated by FindCommand which extends Command class.

Below is an example usage of how the FindTask command can be used to search for a task in the TaskList using a keyword
and how it behaves at each step.

**Step 1. Define the Constructor:**

When the user executes the command `find read`, the Parser class calls the
`FindCommand()` method of the FindCommand class. The constructor of the FindCommand class takes in the keyword
string `read` as a parameter. This string is used to find tasks in the TaskList that contains this keyword.

**Step 2: Define the `setUpLogger()` method:**

The `setUpLogger()` method sets up the logger for the ModifyCommand
class.
It creates a ConsoleHandler and a FileHandler to handle logging.

**Step 3. Override the `execute()` method:**

The `execute()` method is overridden to execute the find task
functionality. It takes the necessary parameters, including the `TaskList`, `Ui`, `Storage`, `ModuleList`,
`allModule`, `calendar`.

**Step 4. Find the list of tasks containing the `KEYWORD`:**

Using the parameter string `KEYWORD`, the `execute()` method
will iterate through `TaskList` and call `printFoundList()` method in the Ui class that takes in a list of task,
`ArrayList<Tasks>`, containing the `KEYWORD` as a parameter. If the `KEYWORD` does not exist in any tasks
in `TaskList`, a message is printed to the user indicating that there are no matching tasks that contains the
`KEYWORD`.

**Step 5. Print the confirmation message:**

A confirmation message is printed to the user indicating the list of
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

**Step 1. Define the Constructor:**

When the user executes the command `date 2023-03-22`, the Parser class calls the    
`DateCommand()` method of the DateCommand class. The constructor of the DateCommand class takes in the dateString
`2023-03-22`  as a parameter. This date is used to find the corresponding tasks happening or due on this date from
the TaskList.

**Step 2.**

This date is then passed into the `LocalDate`. If the date parsed is in the wrong format (date format is not
`yyyy-MM-dd`), a `DateTimeParseException` is thrown, calling the `printInvalidDate()` method in the Ui class.

**Step 3. Define the `setUpLogger()` method:**

The `setUpLogger()` method sets up the logger for the ModifyCommand
class.
It creates a ConsoleHandler and a FileHandler to handle logging.

**Step 4. Override the `execute()` method:**

The `execute()` method is overridden to execute the find task
functionality. It takes the necessary parameters, including the `TaskList`, `Ui`, `Storage`, `ModuleList`,
`allModule`, `calendar`.

**Step 5.**

Find the list of tasks happening or due on the `date`: Using the parameter string `date` in the format
`yyyy-MM-dd`, the `execute()` method will iterate through the `TaskList` to look for tasks that occurs on the given
`date`. It will then call `printDateList()` method in the Ui class that takes in the list of tasks happening on the
given `date` and the LocalDate `date`. If there are no tasks on the specific `date`, a message is printed to the user
indicating that there are no tasks on that day.

**Step 6.**

Print the confirmation message: A confirmation message is printed to the user indicating the list of tasks in
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

**Step 1. Define the `setUpLogger()` method:**

The `setUpLogger()` method sets up the logger for the ModifyCommand
class.
It creates a ConsoleHandler and a FileHandler to handle logging.

**Step 2. Override the `execute()` method:**

The `execute()` method is overridden to execute the list module
functionality. It takes the necessary parameters, including the `TaskList`, `Ui`, `Storage`, `ModuleList`,
`allModule`, `calendar`.

**Step 3. Iterate through the list of modules:**

The `execute` method will iterate through `ModuleList` and call
`printModuleList()` method in the Ui class that takes in the list of modules, `ModuleList` that the user has updated,
as a parameter. During the iteration, the `printModuleList()` method will get the number of modular credits for each
module the user is taking and calculate the total modular credits in that semester. If the list is empty, a message
is printed to the user indicating that there are no modules in the list.

**Step 4. Print the confirmation message:**

A confirmation message is printed to the user indicating the list of modules
in `ModuleList` that the user updated. The message includes the module code and name, modular credits for each module
and  total modular credits the user is taking this semester.

UML Sequence Diagram for ListModuleCommand

The following sequence diagram shows how the list module command works when the user inputs the command `listmod`:

![](https://github.com/AY2223S2-CS2113-T13-4/tp/blob/master/docs/uml-diagrams/ListModuleWithLessonCommand-ListModuleWithLessonCommand.png?raw=true)

The following activity diagram shows how the list module command works when the user inputs the command `listmod`:

![](https://github.com/AY2223S2-CS2113-T13-4/tp/blob/master/docs/uml-diagrams/ListModuleCommandActivityDiagram.png?raw=true)

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

**Step 1. Define the Constructor:**

When user executes the command `listmod cs2113`, the Parser class calls the
`ListModuleWithLessonCommand()` method of the ListModuleWithLessonCommand class. The constructor of the
ListModuleWithLessonCommand class takes in a parameter of type `String`, which will be split into `moduleCode` and
`-FLAG`. In this case, only `cs2113` is parsed in as the parameter. This is then used to find `cs2113` from the
existing timetable. If a moduleCode does not exist in NUS modules, an `InvalidModule()` is thrown.

**Step 2. Define the `setUpLogger()` method:**
The `setUpLogger()` method sets up the logger for the ShowModuleCommand
class. It creates a ConsoleHandler and a FileHandler to handle logging.

**Step 3. Override the `execute()` method:**

The `execute()` method is overridden to execute the List Module with
Lesson functionality. It takes in the necessary parameters, including the ModuleList, Ui, Storage, TaskList and
Calendar.

**Step 4. Find the module from the timetable:**

The first step in the `execute()` method is to find the module from 
timetable using the `Module` class with the module code parameter `cs2113` and using the `getModuleTimetable()` 
function of the `Module` class. If the module `cs2113` has not been added to the timetable, `ModuleNotAddedException` 
will be thrown.

**Step 5. Calls the `handleSingleCommand()` method:**

The `handleSingleCommand()` method is called to handle the command
with only the `moduleCode` as the parameter.

**Step 6. Print the confirmation message:**

A confirmation message is printed to the user indicating the module and lesson 
information that the user has added into their module list. The message will include the `ModuleCode`, `LessonTypes`
of that user has added, `ClassNumber` of the `LessonType` that user has added and `Day` and `Time` of the lesson.

#### For when user request to show a specific lesson of the module (e.g. CS2113 -tut) in their timetable:

**Step 1. Define the Constructor:**

When user executes the command `listmod cs2113`, the Parser class calls the
`ListModuleWithLessonCommand()` method of the ListModuleWithLessonCommand class. The constructor of the
ListModuleWithLessonCommand class takes in a parameter of type `String`, which will be split into `moduleCode`, 
`cs2113` and `-FLAG`, `-tut`. In this case, `cs2113 -tut` is parsed in as the parameter. This is stored in the `args` 
array field of the `ListModuleWithLessonCommand` class.

**Step 2. Define the `setUpLogger()` method:**

The `setUpLogger()` method sets up the logger for the ShowModuleCommand
class. It creates a ConsoleHandler and a FileHandler to handle logging.

**Step 3. Override the `execute()` method:**

The `execute()` method is overridden to execute the List Module with
Lesson functionality. It takes in the necessary parameters, including the ModuleList, Ui, Storage, TaskList and
Calendar. The lesson type is determined by calling the `getLessonType()` method of the `lessonType` class and 
parsing in `args[1]` while the moduleCode is set by `args[0]`. If the lessonType is not valid, an 
`InvalidCommandException` is thrown.

**Step 4. Find the module from the timetable:**

The first step in the `execute()` method is to find the module from
timetable using the `Module` class with the module code parameter `cs2113` and using the `getModuleTimetable()`
function of the `Module` class. If the module `cs2113` has not been added to the timetable, `ModuleNotAddedException`
will be thrown.

**Step 5. Calls the `handleMultiCommand()` method:**

The `handleMultiCommand()` method is called to handle the command.

**Step 6. Print the confirmation message:**

A confirmation message is printed to the user indicating the module and lesson
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

**Step 1: Define the Constructor :**

When user executes the command `addmod cs2113` the Parser class calls the `AddModCommand()` method of the AddModCommand.
The constructor of the AddModCommand class takes in a moduleCode `cs2113` as a parameter and `allModules`. This
moduleCode is used to find `cs2113` from the ModuleData list.

**Step 2: Define the `setUpLogger()` method :**

The `setUpLogger()` method sets up the logger for the DeleteModuleCommand class. It creates a ConsoleHandler and a
FileHandler to handle logging.

**Step 3: Override the `execute()` method :**

The `execute()` method is overridden to execute the delete module functionality. It takes in the necessary parameters,
including the `ModuleList`, `Ui`, `Storage`, `TaskList` and `allModules`.

**Step 4: Check if the module exists in the list :**

The first step in the `execute()` method is to split the parameters into the module code and the lesson type. It then
calls the `isAdded()` method of the `ModuleList` class to check if the module already exists in the list. If the module
exists, a `DuplicateModuleException` is thrown.

**Step 5: Add the module to the ModuleList :**

If the module does not exist, it is added to the `ModuleList` by calling the `add()` method of the `ModuleList` class.
The module is sorted alphabetically by the `sort()` method of the `ModuleList` class.

**Step 6: Print the confirmation message :**

A confirmation message is printed to the user indicating that the module has been successfully added. The message
includes the module code and title of the module added as well as the available lesson types for the module.


#### For when a user adds a module's specific lesson (e.g CS2113 -lec 1) to the module list:

**Step 1: Define the Constructor :**

When user executes the command `addmod cs2113 -lec 1` the Parser class calls the `AddModCommand()` method of the 
AddModCommand class. The constructor of the AddModCommand class takes in the string `cs2113 -lec 1` as a parameter and
`allModules`. The string is split into a moduleCode `cs2113`, lessonType `-lec` and `1` as class number.

**Step 2: Define the `setUpLogger()` method :**

The setUpLogger() method sets up the logger for the DeleteModuleCommand class. It creates a ConsoleHandler and a
FileHandler to handle logging.

**Step 3: Override `execute()` method :**

The `execute()` method is overridden to execute the delete module functionality. It takes in the necessary parameters,
including the ModuleList, Ui, Storage, and TaskList.

**Step 4: Calls the `handleMultiCommand()` method:**

The `handleMultiCommand()` method is called to handle the command. It takes in `moduleList`, `lessonType` and `args` as
parameters. It then checks if the module already exists in the `ModuleList` by calling the `isAdded()` method.

**Step 5: Add the module lessons to the ModuleList :**

If the module already exists, the timetable of the classes are added to the module by calling the `addTimetable()` 
method which takes in `searchModule` and `lessonType` as parameters. If the module does not exist, it is added to the
Module list and the lessons are added to the module using the `addTimetable()` method of the `ModuleList` class.

**Step 6:** 

Print the confirmation message :
A confirmation message is printed to the user indicating that the module lesson has been successfully added.

UML Sequence Diagram for AddModCommand Class

The following sequence diagram shows how the AddModCommand class works for both adding modules and adding lessons to 
the module list.:

![](https://github.com/AY2223S2-CS2113-T13-4/tp/blob/master/docs/uml-diagrams/AddModule-AddModuleCommand__Add_Module_.png?raw=true)

The following activity diagram shows how the AddModCommand class works for both adding modules and adding lessons to
the module list:

![](https://github.com/AY2223S2-CS2113-T13-4/tp/blob/master/docs/uml-diagrams/AddModuleActivityDiagram.png?raw=true)

[*Return to TOC*](#table-of-contents)

<!--@@author PoobalanAatmikaLakshmi -->

### Delete Module

The DeleteModule functionality allows users to remove either a module from the ModuleList or a lesson associated with 
the module. It is facilitated by DeleteModuleCommand class which is an extension of the Command class.
Given below is an example usage scenario and how to delete mechanism behaves at each step.

#### For when a user deletes only a module (e.g CS2113) from the module list:

**Step 1: Define the Constructor :**

When user executes the command `delmod cs2113` the Parser class calls the `DeleteModuleCommand()` method of the
DeleteModuleCommand class. The constructor of the DeleteModuleCommand class takes in a moduleCode `cs2113` as a 
parameter. This moduleCode is
used to find `cs2113`  from the ModuleList.

**Step 2: Define the `setUpLogger()` method :**

The `setUpLogger()` method sets up the logger for the DeleteModuleCommand class. It creates a ConsoleHandler and a
FileHandler to handle logging.

**Step 3: Override the `execute()` method :**

The `execute()` method is overridden to execute the delete module functionality. It takes in the necessary parameters,
including the ModuleList, Ui, Storage, and TaskList.

**Step 4: Find the module to delete :**

The first step in the `execute()` method is to find the module using the module code parameter  `cs2113` by calling
the `findModule()` method of the moduleList class.
If the module `cs2113` is not found, a ModuleNotFoundException is thrown.

**Step 5: Remove the module from the ModuleList :**

If `cs2113` is found, it is removed from the ModuleList by calling `remove()` method of moduleList class.

**Step 6: Print the confirmation message :**

A confirmation message is printed to the user indicating that the `cs2113` has been successfully deleted.

**Step 7: Update the storage :**

The storage is updated with the new ModuleList without `cs2113`

#### For when a user deletes a specific lesson (e.g CS2113 -lec 1) from the module list:

**Step 1: Define the Constructor :**

When user executes the command `delmod cs2113 -lec 1` the Parser class calls the `DeleteModuleCommand()` method of the 
DeleteModuleCommand class. The constructor of the DeleteModuleCommand class takes in the string `cs2113 -lec 1` as a 
parameter. The string is split into a moduleCode `cs2113`, lessonType `-lec` and `1` as class number and stored in the
`args` array field of the `DeleteModule` class. 

**Step 2: Define the `setUpLogger()` method :**
The setUpLogger() method sets up the logger for the DeleteModuleCommand class. It creates a ConsoleHandler and a 
FileHandler to handle logging.

**Step 3: Override `execute()` method :**

The `execute()` method is overridden to execute the delete module functionality. It takes in the necessary parameters,
including the ModuleList, Ui, Storage, TaskList, AllModules and Calendar objects. The lesson type is determined by
calling the `getLessonType()` method of the `LessonType` class and parsing in `arg[1]` while the lesson number is 
set by `arg[2]`. If the lesson type is not valid, an`InvalidCommandException` is thrown.

**Step 4: Calls the `handleMultiCommand()` method:**

The `handleMultiCommand()` method is called to handle the command. It takes in `moduleList`, `lessonType` and `args` as
parameters. It then searches for the module using the `findModule()` method of the `ModuleList` class. If the module
is not found, a `ModuleNotFoundException` is thrown.

**Step 5: Remove the module lessons from the ModuleList :**

If the module is found, the timetable of the classes are removed from the module by calling the `removeTimetable()` 
method which takes in `searchModule` and `lessonType` as parameters. The `removeTimetable()` method of the `ModuleList`
will create a copy of the module timetable array list and search for the lesson to be removed. If the lesson type and 
class number matches, the lesson is removed from the original timetable array list using the `remove()` method. The
lesson is then marked as found by setting the `isFound` boolean to true. If the lesson is not found, a 
`ClassNotFoundException` is thrown.

**Step 6: Print the confirmation message :**

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

**Step 1. Define the Constructor:**

When user executes the command `showmod cs2113`, the Parser class calls the
`ShowModuleCommand()` method of the ShowModuleCommand class. The constructor of the ShowModuleCommand class takes
in a parameter of type `String`, which will be split into `moduleCode` and `-FLAG`. In this case, only `cs2113` is 
parsed in as the parameter. This is then used to find `cs2113` from the module information. If the moduleCode, 
`cs2113`, is not found, an `InvalidModule()` is thrown.

**Step 2. Define the `setUpLogger()` method:**

The `setUpLogger()` method sets up the logger for the ShowModuleCommand 
class. It creates a ConsoleHandler and a FileHandler to handle logging.

**Step 3. Override the `execute()` method:**

The `execute()` method is overridden to execute the Show Module functionality.
It takes in the necessary parameters, including the ModuleList, Ui, Storage, TaskList and Calendar.

**Step 4. Find the module to display information:**

The first step in the `execute()` method is to find the module from 
`Module` class using the module code parameter `cs2113` by using the `findModule()` function of the `Module` class.

**Step 5. Print the confirmation message:**

A confirmation message is printed to the user indicating the information
of the module requested by the user. The message includes the `ModuleCode`, `LessonTypes` of the module, `ClassNumber` 
of each `lessonTypes` and `Day` and `Time` of the existing `ClassNumber`.

#### For when a user request to show a specific lessonType of the module (e.g. CS2113 -tut):

**Step 1. Define the Constructor:**

When user executes the command `showmod cs2113`, the Parser class calls the
`ShowModuleCommand()` method of the ShowModuleCommand class. The constructor of the ShowModuleCommand class takes
in a parameter of type `String`, which will be split into `moduleCode`, `cs2113`, and `-FLAG`, `-tut`. In this case, 
`cs2113 -tut` is parsed in as the parameter. This is stored in the `args` array field of the `ShowModuleCommand` class.

**Step 2. Define the `setUpLogger()` method:**

The `setUpLogger()` method sets up the logger for the ShowModuleCommand
class. It creates a ConsoleHandler and a FileHandler to handle logging.

**Step 3. Override the `execute()` method:**

The `execute()` method is overridden to execute the Show Module functionality.
It takes in the necessary parameters, including the ModuleList, Ui, Storage, TaskList and Calendar objects. The lesson 
type is determined by calling the `getLessonType()` method of the `lessonType` class and parsing in `args[1]` while 
the moduleCode is set by `args[0]`. If the lessonType is not valid, an `InvalidCommandException` is thrown.

**Step 4. Calls the `handleMultiCommand()` method:**

The `handleMultiCommand()` method is called to handle the command. 
It takes in `moduleList`, `lessonType` and `args` as parameters.

**Step 5. Find the module to display information:**

The first step in the `execute()` method is to find the module from
`Module` class using the module code parameter `cs2113` by using the `findModule()` function of the `Module` class.
To find the lessonType of the module, `getLessonType()` is called to return the lessonType for the Show Module
functionality.

**Step 6. Print the confirmation message:**

A confirmation message is printed to the user indicating the information
of the module requested by the user. The message includes the `ModuleCode`, the specific `LessonType` of the module, 
`Classnumber`of requested `lessonTypes` and `Day` and `Time` of the existing `Classnumber`.

![](https://github.com/AY2223S2-CS2113-T13-4/tp/blob/master/docs/uml-diagrams/ShowModuleCommand-ShowModuleCommand.png?raw=true)

<!--@@author -->

[*Return to TOC*](#table-of-contents)

## *Utility Commands*
<!--@@author PoobalanAatmikaLakshmi -->
### Viewing Help
This is a sequence diagram of a specific help command: deleteHelpCommand 
This command allows the user to see how to use the `delete` command 
![](https://github.com/AY2223S2-CS2113-T13-4/tp/blob/master/docs/uml-diagrams/DeleteHelpCommand-DeleteHelpCommand.png?raw=true)
Given below is an example usage scenario and how the specific help mechanism behaves at each step.

**Step 1.**

The user executes the command `help delete`. It is parsed by Parser class which then creates a new `DeleteHelpCommand`.

**Step 2.**

The `execute()` method of `DeleteHelpCommand` is called.

**Step 3.**

The `printDeleteHelpMessage()` method of `Ui` class is called. 
Instructions on how to use the `delete` command are printed out.
<!--@@author honglinshang -->

[*Return to TOC*](#table-of-contents)

### Viewing Week
The `week` command allows the user to view their weekly schedule at a glance, including lessons, deadlines, and events.
It is facilitated by `WeekCommand` which is an extension of the `Command` class.

Given below is an example usage scenario and how the add task mechanism behaves at each step.

**Step 1.**
The user executes the command "`week`".
`Apollo` calls `Parser#getCommand()`, where the user command String is parsed and determined to be a `WeekCommand`.
A new `WeekCommand` is created and sent back to `Apollo`.

**Step 2.**
`Apollo` calls `Command#execute()`, which is overwritten by `WeekCommand#execute()`. 

**Step 3.** 
`execute()` determines the dates of Monday and Sunday of the current week (`startWeek`, `endWeek`) using `LocalDate`.

**Step 4.** 
`execute()` calls `Ui#printWeek()`, 
which passes the parameters `startWeek`, `endWeek`, `taskList` (all tasks), and `calendar` (all lessons) into `Ui`

**Step 5.** 
`printWeek()` calls `SemesterUtils#getWeekNumber()` and prints out a message indicating the current week. 

**Step 6.**
`printWeek()` calls `Ui#printEachDayInWeek()`, 
which prints out the lessons and tasks occurring on each day of the current week, starting from Monday, as follows: 
1. The day of week is printed using the `DayTypeUtil#determineDay()`. 
2. All lessons on the day are stored in an `ArrayList<CalendarModule> lessonsOnDay` using `calender.getModulesForDay()`. 
   If no lessons occur on that day, the next step is skipped. 
3. `modulesOnDay` is passed into the method `Ui#printLessonsOnDay()`. 
   The schedule of each lesson is stored in a new `Timetable`, then printed out in the desired format. 
4. Similarly, All tasks on the day are stored in an `TaskList tasksOnDay` using `taskList.getTasksOnDate()`.
   If no tasks occur on that day, the next step is skipped.
5. `tasksOnDay` is passed into the method `Ui#printTasksOnDay()`. Each task is printed out. 
6. The current day is increased to the following day. 
7. Go back to `1.`, stop after all lessons and tasks on Sunday have been printed. 

![](https://github.com/AY2223S2-CS2113-T13-4/tp/blob/master/docs/uml-diagrams/Week-WeekCommand.png?raw=true)

[*Return to TOC*](#table-of-contents)

<!--@@author -->

### Exiting the Program

The `bye` command allows the user to exit the program. It is facilitated by `ExitCommand` which is an extension of the `Command` class.
The `ExitCommand` class overrides the `execute()` method from the `Command` class and is only executed when the user inputs `bye`
with no additional parameters (e.g `bye bye` would not exit the program).

Given below is an example usage scenario and how the add task mechanism behaves at each step.

**Step 1.**

The user executes the command `bye` and handled by `Apollo` class. It is parsed by the `Parser` class which 
then creates a new `ExitCommand`.

**Step 2.**

The `setUpLogger()` method of the `ExitCommand` class is called. It creates a `ConsoleHandler` and a `FileHandler`
to handle logging.

**Step 3.**

The `execute()` method of `ExitCommand` is called from `Apollo` class. It takes in the necessary parameters.

**Step 4.** 

Within the `execute()` method, the method `printExitMessage()` is called from `Ui` class to print the exit message.

**Step 5.**

The `setExit()` method of `ExitCommand` class is called to set the `isExit` boolean to true. Subsequently, the
program exceeds the loop in the `run()` method of `Apollo` class and the program terminates.

Below is a sequence diagram of the `bye` command.

![](https://github.com/AY2223S2-CS2113-T13-4/tp/blob/master/docs/uml-diagrams/ExitCommand-ExitCommand.png?raw=true)

[*Return to TOC*](#table-of-contents)

## *Logging*
- We are using `java.util.logging` package for logging. The `Logger` class is used to log messages for the different
  error levels

- Each command class has a private static final field called `logger` for logging and a `setUpLogger()` method to set
  up the logging properties such as the logging level and the format of logging messages.

- The `setUpLogger()` method is called in the constructor of the command class. It is implemented from the `LoggerInterface`
  interface. Details of the implementation can be found further down in the subsection.

- Log messages are output to a file called `Apollo.log` in the `data` folder. All error messages of logging level
  `FINE` and above are logged to the file. Additionally, all log messages of logging level `SEVERE` are logged 
  to the console.


### *The `LoggerInterface` Interface*

API: `LoggerInterface.java`


The `LoggerInterface` interface is used to implement the `setUpLogger()` method in the command classes. This design choice
was made to reduce code duplication and to improve the maintainability of the code. It also allows for multi-level inheritance
amongst different command classes. The implementation of the `setUpLogger()` method is shown below.

**Step 1**:

The `setUpLogger()` method calls `getLogManager().reset()` of the `LogManager` class to reset the logging properties
to the default values.

**Step 2**:

The logger is set to log messages of logging level `WARNING` and above. A new `ConsoleHandler` is created to handle logging
to the console. The `ConsoleHandler` is set to log messages of logging level `SEVERE` and above. The `ConsoleHandler` is
then added to the logger using the `addHandler()` method.

**Step 3**:

First, apollo.log is checked to see if it exists, if it does not exist, a new file is created. A new `FileHandler` is created
to handle logging to the file. The `FileHandler` is set to log messages of logging level `FINE` and above. The `FileHandler`
is then added to the logger using the `addHandler()` method. 

**Step 4**:

If the file cannot be created, a `SecurityException` is thrown. The `IOException` is caught and logged using the ConsoleHandler.
Subsequently, the only logging will be through the ConsoleHandler.

[*Return to TOC*](#table-of-contents)

# Appendix
<!--@@T-Wan-Lin -->
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

| Priority | Version |           As a/an ...            | I want to ...                                                | So that I can ...                                                             |
|:--------:|:-------:|:--------------------------------:|:-------------------------------------------------------------|-------------------------------------------------------------------------------|
|   ***    |  v1.0   |           expert user            | be able to quickly interface with the program using a CLI    | issue commands to the bot.                                                    |
|   ***    |  v1.0   |             new user             | see usage instructions                                       | refer to them when I forget how to use the application.                       |
|   ***    |  v1.0   |               user               | find a to-do item by name                                    | locate a to-do without having to go through the entire list.                  |
|   ***    |  v1.0   |               user               | delete a to-do item                                          | remove items that I no longer need to keep track of.                          |
|   ***    |  v1.0   |               user               | add a to-do item                                             | keep track of things that I need to do.                                       |
|   ***    |  v1.0   |               user               | add an event item                                            | keep track of events that I need to attend.                                   |
|   ***    |  v1.0   |           busy student           | add a deadline item                                          | keep track of deadlines that I need to meet.                                  |
|   ***    |  v1.0   |               user               | list all items                                               | view all items that I have added.                                             |
|   ***    |  v1.0   |               user               | mark a to-do item as done                                    | keep track of which to-do items I have completed.                             |
|   ***    |  v1.0   |               user               | unmark a to-do item as not done                              | keep track of which to-do items I have not completed yet.                     |
|   ***    |  v1.0   |               user               | view task count                                              | see how much I have to do.                                                    |
|   ***    |  v1.0   |          undergraduate           | add modules                                                  | keep track of the modules I am taking for the semester.                       |
|   ***    |  v1.0   |          undergraduate           | list all modules                                             | see all the modules I am taking for the semester.                             |
|    **    |  v1.0   |          undergraduate           | view module count                                            | keep how many modules I have for the semester.                                |
|   ***    |  v1.0   |          undergraduate           | delete modules                                               | remove modules that I am no longer taking.                                    |
|    **    |  v2.0   |          undergraduate           | keep track of the total modular credits                      | plan my academic year.                                                        |
|   ***    |  v2.0   |             student              | add specific lesson types lessons for each module            | go to the correct classes at the right time.                                  |
|   ***    |  v2.0   |             student              | delete specific lesson types for each module                 | avoid going to a class I never signed up for.                                 |
|   ***    |  v2.0   |             student              | see my lesson timetable                                      | see the timings of my lessons for the week.                                   |
|    **    |  v2.0   |      over-committed student      | be alerted to clashes between events and module lessons      | rearrange conflicting events.                                                 |
|    **    |  v2.0   |           busy student           | be alerted to clashes between deadlines and events           | avoid missing out on important deadlines.                                     |
|    **    |  v2.0   |           busy student           | be alerted to clashes between deadlines and module lessons   | avoid missing out on important deadlines during lessons.                      |
|    **    |  v2.0   | student planning their timetable | be alerted to clashes between module lessons                 | avoid registering for the wrong lessons.                                      |
|    **    |  v2.0   |        organised student         | be able to organise my tasklist according to type            | be able to read the tasklist more easily.                                     |
|    **    |  v2.0   |        organised student         | be able to organise my tasklist according to date            | prioritise deadlines and events correctly.                                    |
|    **    |  v2.0   |             student              | see my overall timetable                                     | see all lessons, events and module lessons at the same time.                  |
|    **    |  v2.0   |         curious student          | view module information                                      | see what a module is about without adding it to my module list.               |
|    **    |  v2.0   | prospective student of a module  | view module lesson information                               | see which lessons I am able to fit into my timetable.                         |
|    **    |  v2.0   |             student              | list all lessons I have for a module                         | see if I registered for the right lessons.                                    |
|    **    |  v2.1   |        forgetful student         | view which weeks my lessons occur                            | turn up for lesson on the correct weeks.                                      |
|    **    |  v2.1   |          forgetful user          | view help menu for specific commands                         | get formatting options for each command without viewing the entire help menu. |
|    *     |  v2.1   |             new user             | be guided to the more appropriate way of recording deadlines | better organise my deadlines.                                                 |


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
  by NUS in AY22/23 Semester 2.


## Appendix D: Glossary

* *Mainstream OS* - Windows, Linux, Unix, OS-X
* *CLI* - Command Line Interface
* *Modules* - Modules are the courses that students take in NUS. They are identified by a 6-character alphanumeric code.
* *Modular Credits* - Modular credits are the credits that students earn for each module they take. They are used to
  calculate the total number of credits a student has earned for the semester.

<!--@@T-Wan-Lin, PoobalanAatmikaLakshmi -->
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
For command testing, you have to be in Apollo's main CLI interface. 
Do remember to hit `Enter` after typing the command so that Apollo registers it.

In this section, there are various test cases for the many commands Apollo has. For negative testing,
invalid commands are tested, where Apollo can handle invalid commands gracefully without crashing.
For positive testing, valid commands are tested.
#### Invalid Commands at any point of execution

Any commands Apollo cannot understand will be treated as invalid commands. Apollo will respond with an error message.
Note: Some commands have command-specific error messages. These are not covered in this sub-section for brevity.

|  No.  |  Type of Testing  | Test Command | Expected Output                       |
|:-----:|:-----------------:|:-------------|:--------------------------------------|
|   1   |     Negative      | `hello`      | Exception thrown for invalid command. |

The exception thrown in for this sub-section is: 
```
Sorry, but I don't know what that means :(
```
#### Help Command

| No. | Type of Testing | Test Command                | Expected Output                                                                              |
|:---:|:---------------:|:----------------------------|:---------------------------------------------------------------------------------------------|
|  1  |    Positive     | `help`                      | The help menu should appear with the list of *all* commands available on Apollo with `help`. |
|  2  |    Positive     | `help showmod`              | The *shorter* help menu for *only* the showmod command should appear.                        |
|  3  |    Negative     | `help how to get a partner` | Exception thrown for non-existent command.                                                   |


### Adding a ToDo/Event/Deadline
 The below table is a summary of the test cases.

| No. | Type of Testing | Test Command                                                | Expected Output                                                       |
|:---:|:---------------:|:------------------------------------------------------------|:----------------------------------------------------------------------|
|  1  |    Positive     | `todo Feed the fish`                                        | ToDo is added into TaskList. Details of ToDo shown in status message. |
|  2  |    Positive     | `deadline return book -by 17-11-2023-11:23`                 | Deadline is added into the TaskList.                                  |
|  3  |    Positive     | `event wedding -from 16-11-2023-11:23 -to 20-11-2023-11:23` | Event is added into the TaskList.                                     |
|  4  |    Negative     | `event wedding -from 40-11-2023-22:23 -to 41-11-2023-11:23` | Exception thrown for dates outside calendar range.                    |
|  5  |    Negative     | `deadline return book -by 40-11-2023-23:23`                 | Exception thrown for dates outside calendar range.                    |
|  6  |    Negative     | `deadline return book -by 2023-10-11-11:23`                 | Exception thrown for invalid DateTime format.                         |
|  7  |    Negative     | `event wedding -from 2023-10-11-11:23 -to 2023-10-12-11:23` | Exception thrown for invalid DateTime format.                         |
|  8  |    Negative     | `deadline return book 15-11-2023-11:23`                     | Exception thrown for missing parameters.                              |
|  9  |    Negative     | `event wedding 16-11-2023-11:23 -to 20-11-2023-11:23`       | Exception thrown for missing parameters.                              |
| 10  |    Negative     | `deadline return book -by 17-11-2023-11:23 blah blah`       | Exception thrown for extra parameters.                                |
| 11  |    Negative     | `deadline return book 15-01-2022-11:23`                     | Exception thrown for date occurring before system DateTime.           |
| 12  |    Negative     | `event wedding 16-01-2022-11:23 -to 20-01-2022-11:23`       | Exception thrown for date occuring before system DateTime.            |
| 13  |    Negative     | `todo`                                                      | Exception thrown for missing parameters.                              |
| 14  |    Negative     | `event`                                                     | Exception thrown for missing parameters.                              |
| 15  |    Negative     | `deadline`                                                  | Exception thrown for missing parameters.                              |

Note: 
* For brevity, the error messages for all forms of invalid inputs are not shown in the table. Each exception will have their own error message, detailing the errors occurred. 
For instance invalid dateTime format prints `Please enter [date]s in the format of dd-MM-yyyy-HH:mm.
eg. "30-10-2023-23:59" for Oct 30 2023, 11:59PM`
* System DateTime refers to the date and time on the computer Apollo is installed and run on.

### Deleting a Todo/Event/Deadline

Prerequisites: 

* Use command `list` to obtains task's index `[IDX]`.
* List of tasks is not empty (at least one event/todo/deadline).

| No. | Type of Testing | Test Command | Expected Outcome                                                        |
|:---:|:---------------:|--------------|:------------------------------------------------------------------------|
|  1  |    Positive     | `delete 1`   | Task is deleted from tasksList. Deletion confirmation message is shown. |
|  2  |    Negative     | `delete 0`   | Exception thrown for index out of bounds.                               |

The index out of bounds exception is structured as follows:

```
Please enter [idx] in the form of an integer from <IDX_LOWERBOUND> to <IDX_UPPERBOUND>
```
### Unmarking/Marking a Todo/Event/Deadline
Prerequisites:

* Use command `list` to obtains task's index `[IDX]`.
* List of tasks is not empty (at least one event/todo/deadline).

The tests are similar to that of the `delete` command. The only difference is replacing `delete` with `mark` or `unmark`.
Instead of confirming that the task is deleted, Apollo would confirm that a task is marked or unmarked respectively.

### Adding a Module

| No. | Type of Testing | Test Command    | Expected Outcome                                              |
|:---:|:---------------:|:----------------|:--------------------------------------------------------------|
|  1  |    Positive     | `addmod cs2113` | Module added into moduleList. Details shown in status message |
|  2  |    Negative     | `addmod cs9999` | Exception thrown as module does not exist currently.          |
|  3  |    Negative     | `addmod`        | Exception thrown due to empty description.                    |

### Adding a Module Lesson
Prerequisites: 
* Obtain lessons timings and numbers using `showmod CS1010 -st` which shows all available lessons of type `st` of `CS1010`
or `showmod CS1010` which shows all the lesson types available for CS1010

| No. | Type of Testing | Test Command           | Expected Outcome                                                                                                                                                                             |
|:---:|:---------------:|:-----------------------|:---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
|  1  |    Positive     | `addmod CS1010 -st 1`  | First section teaching lesson of CS1010 to your module list. If this lesson clashes with any of your other lessons a warning message will be displayed,but you will still be able to add it. |
|  2  |    Negative     | `addmod CS1010 -st 99` | Exception thrown as due to invalid arguments.                                                                                                                                                |
|  3  |    Negative     | `addmod CS1010 -oo 1`  | Exception thrown as due to invalid arguments.                                                                                                                                                |


### Deleting a Module
Prerequisites:

Perform the following steps before running the test cases:
* List should be populated as follows:
  * Use `addmod ac5004` command to add AC5004 to your moduleList.
  * Use `addmod cs1010` command to add CS1010 to your moduleList.
  * Use command `listmod` to obtains module's index `[IDX]`.
  
Note: moduleList *should not* contain CS2040C, i.e. `addmod cs2040c` should not be run before this test.


| No. | Type of Testing | Test Command     | Expected Outcome                                                                |
|:---:|:---------------:|:-----------------|:--------------------------------------------------------------------------------|
|  1  |    Positive     | `delmod 1`       | Module under index 1 is deleted from moduleList. Confirmation message is shown. |
|  2  |    Positive     | `delmod cs1010`  | CS1010 is removed from moduleList. Confirmation message is printed              |
|  3  |    Negative     | `delmod cs2040c` | Exception that module is not in moduleList thrown.                              |


### Deleting a Module Lesson
Prerequisites:

Perform the following steps before running the test cases:
* List should be populated as follows:
   * Use `addmod cs1010 -st 1` command to add CS1010 to your moduleList.

Note: moduleList *should not* contain CS1010 -st 5, i.e. `addmod cs1010 -st 5` should not be run before this test.

| No. | Type of Testing | Test Command          | Expected Outcome                                                         |
|:---:|:---------------:|:----------------------|:-------------------------------------------------------------------------|
|  1  |    Positive     | `delmod cs1010 -st 1` | Deletes SECTIONAL TEACHING - 1 of CS1010. Confirmation message is shown. |
|  2  |    Negative     | `delmod cs1010 -st 5` | Exception thrown for lesson not in moduleList.                           |


<!--@@T-Wan-Lin -->
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
