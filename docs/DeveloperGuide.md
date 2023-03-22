# Developer Guide

## Acknowledgements

{list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well}

## Design

{Describe the design of the product. Use UML diagrams and short code snippets where applicable.}

## Implementation

### Delete Task feature
The DeleteTaskCommand extends NUS To-do List with a delete feature for the removal of tasks from the task list.  is facilitated by ToDoListManager, Parser, exception, TaskList and Storage classes. It implements the following operations:

TaskList#deleteTask()

Given below is an example usage scenario and how the DeleteTaskCommand mechanism behaves at each step.

Step 1: The user launches the program for the first time. The ToDoListManager will be initialised. This in turn will then initialise the Parser, TaskList and Storage. Take it as there are no existing tasks read/stored by the program.

Step 2: The user executes add survey -d 20/03/2023 23:59 command to add a task for the To-do List. The add command calls TaskList#addTask(), which causes a new Task to be added to the existing TaskList.

Step 3: The user now then decides that adding this task was a mistake, and decides to remove the task from the To-do List. The user does this by inputting the command “delete 1” into the terminal to delete a task in the task list. The command will then call the TaskList#deleteTask(), which removes the task at index 1 of the TaskList.

The following sequence diagram shows how the delete task operation works:
![DeleteTaskCommandSequence](images/DeleteTaskCommandSequence.png)

Step 4: The user then decides to execute the command list. This command does not modify the TaskList. Thus, the TaskList will return to its initial state where there are no tasks stored in the TaskList.


## Product scope
### Target user profile

Forgetful NUS students who used to rely on LumiNUS’s deadline reminders.

### Value proposition

With the transition to Canvas, the most important feature of LumiNUS’s deadline reminders is gone! Our project aims to 
bring an application to keep you aware of your deadlines and not miss them.


## User Stories

|Version| As a ... | I want to ... | So that I can ...|
|--------|----------|---------------|------------------|
|v1.0|user|add tasks to my to-do list|
|v1.0|user|add a time/deadline to a task|record when a task needs to be done|
|v1.0|user|list all tasks by ascending date|view tasks that have an earlier deadline|
|v1.0|user|remove tasks|remove finished/wrong tasks|
|v1.0|user|mark/unmark tasks|check off unfinished tasks|
|v1.0|user|edit the time/deadline of existing tasks|update tasks with changed deadlines (postponed/brought forward)|
|v2.0|student|add tags/module codes to each task|group related tasks together|
|v2.0|student|set reminders at the start of the day|do not forget what I have to achieve by the end of the day/week|
|v2.0|user|list all the tasks in chronological order/grouped by module code/grouped by type of work|(individual/group work)|have a “birds’ eye view” of all my tasks. (i.e different sorting methods)|
|v2.0|user|filter the tasks by their properties (description, deadline, tags, …)|find them easily|
|v2.0|user|view the tasks in a calendar view|view them in a summarised layout|
|v2.0|user|add the email of the professor/TA in charge of the task|can email them to clarify if needed|
|v2.0|user|attach a list of files/links to refer to|know where the materials I can refer to are|
|v2.0|user|set a task to repeat|create 1 task to represent repeating tasks every week|
|v2.0|user|set priority level and can sort the tasks based on the priority level|can identify high priority tasks|
|v2.0|user|see a progress bar|able to track my progress|
|v2.0|user|view up to 10 previously completed tasks tied with the completion date and time|


## Non-Functional Requirements

1. Should work on any mainstream OS assuming it has Java 11 or above installed.
2. A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be 
able to accomplish most of the tasks faster using commands than using the mouse.

## Glossary

* *glossary item* - Definition

## Instructions for manual testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}
