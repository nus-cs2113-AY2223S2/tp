# Developer Guide

## Acknowledgements

{list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well}

## Design & implementation

This section describes how Duck is implemented and how its commands are executed.

### SchoolClass Feature

**Implementation**

The ```SchoolClass``` Class is implemented to facilitate the adding of students' classes to the task list. It extends the ```Task``` Class with additional String attributes to store the class name, start date/time, and end date/time. It also overrides the ```toString()``` method to have its own specialised output when being printed, as well as overriding the ```toSaveString()``` method to have correctly save its details to the save file. The ```SchoolClass``` Class will also facilitate the implementation of automatically recurring classes, which is a planned feature for milestone v2.0. ```SchoolClass``` Tasks will be added by default as recurring tasks to the task list, and will be automatically added back at their same set timing each week.

The following are the new operations implemented.

-   ```TaskList#addSchoolClass()``` - Adds a SchoolClass Task to the task list.

-   ```Ui#addedTaskMessage()``` - Outputs a message to show that the SchoolClass Task was successfully added to the task list

-   ```Storage#loadSchoolClass()``` - Adds a SchoolClass to the task list without generating a successfully added message, to be used when loading from the save data.

<br />

**Given below is an example usage scenario for  TaskList#addSchoolClass().**

Step 1. The user inputs a command following the proper formatting for adding a ```SchoolClass```. The Duck class will call ```Parser#processCommand()```, instantiating a ```Parser``` class, which will then call ```TaskList#addTask()```, instantiating a ```TaskList``` Class. From there, ```TaskList#addSchoolClass()``` is called and a new ```SchoolClass``` Task is instantiated, which calls ```Ui#addedTaskMessage()``` and instantiating a ```Ui``` Class. This ```SchoolClass``` Task will thus be added to the ArrayList<Task> tasks that was instantiated in the ```Duck``` class.

![](https://lh4.googleusercontent.com/u4zVr8TYxFw3rMvnqdwCYlJmq0JxUgEtC_cFmOY7rPqCM9nvzcQL1t-GcTmgbedeVEHi2L6MG6xG3QaJ7XaOPs8nYvHz1Uf4wGK9bMsHDHwxZVNdS2zR79TtHL_Ub2Za0_jm6bUsnY_RQWX6QmFqCl4)

![](https://lh3.googleusercontent.com/owAwOcAeTYadanERD7zj2eVD_SsbxhXUvohhaV962-DfYkgh-fV4wWVv8LnLjPpt9jl3yEkBrVUuiPg7jor-uWSpIhwdze4C3yKMWdRQEYrcR7I6tW4RMIGeyazNhEYUZzTd2BTBqUNpKL-6O2KRXug)
  
<br />

**Given below is an example usage scenario for  Storage#loadSchoolClass.**

Step 1. The user launches the application. The task list will be initialised with the data from a given pre-existing datafile if it exists, and the ```SchoolClass``` items will be inserted into the task list if they exist using ```Storage#loadSchoolClass()```.

<br />

## Product scope
### Target user profile

{Describe the target user profile}

### Value proposition

{Describe the value proposition: what problem does it solve?}

## User Stories

|Version| As a ... | I want to ... | So that I can ...|
|--------|----------|---------------|------------------|
|v1.0|new user|see usage instructions|refer to them when I forget how to use the application|
|v2.0|user|find a to-do item by name|locate a to-do without having to go through the entire list|

## Non-Functional Requirements

{Give non-functional requirements}

## Glossary

* *glossary item* - Definition

## Instructions for manual testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}
