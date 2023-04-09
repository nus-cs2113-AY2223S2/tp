# Developer Guide

## Acknowledgements

Reused iP to serve as base code from [jeraldgau/ip](https://github.com/jeraldgau/ip)

## Architecture

![](https://lh3.googleusercontent.com/FwYOJJpyhNrHNAUjWo1grnsWCDHdKMosDWaJaI_3kqtVbfD8108yk0MeJaybK3ac0MOAL3EVKYYCUjSiAbBrwAT8wRBkPTj2UDfw3AdhPT0fB8twOxfRDfh3BGAawOUOUlt-rxSACvVOhvqRnFamqWM)

The **Architecture Diagram** given above explains the high-level design of Duck.

Given below is a quick overview of main components and how they interact with each other.

<br />

**Main components of the architecture**

```Duck``` is responsible for:

**At app launch:** 

-   Executes the ```Ui#greetingMessage()``` operation.

-   Initializes a new ArrayList tasks 

-   Populates the ArrayList by executing the ```Storage#tryLoad()``` operation.

-   Begins scanning for user inputs, and passing these inputs to ```Parser``` class.

-   At shut down: Shuts down the components and invokes the ```Ui#exitMessage()``` operation.

```Commons``` represents a collection of classes used by multiple other components, such as ```java.time.LocalDateTime```

The rest of the App consists of four components.

-   ```UI```: The UI of the App.

-   ```Parser```: The command executor.

-   ```Storage```: Reads data from, and writes data to, the hard disk.

-   ```TaskList```: Holds the data of Duck in memory.

```TaskList``` stores items that belong to 4 subclasses that draw upon a shared superclass.

```Task```: The skeleton superclass of all Tasks. 

-   ```ToDo```: Basic form of ```Task```

-   ```Deadline```: Expands upon ```Task``` by storing a ```by``` variable

-   ```Event```: Expands upon ```Task``` by storing ```start``` and ```end``` variable

-   ```SchoolClass```: Expands upon ```Task``` by storing ```start``` and ```end``` variable, along with a ```className``` variable

## Design & implementation

This section describes how Duck is implemented and how its commands are executed.

### Purge feature

**Sequence Diagram**

![image](https://user-images.githubusercontent.com/1620654/227128864-cfc9cac7-bc68-4962-ac45-77e1ae067db0.png)

The ```TaskList#purge()``` command will be implemented following the sequence diagram as shown above.


**Implementation**

The ```TaskList#purge()``` command will be implemented to facilitate the removal of expired ```Task``` items from the ```TaskList```. This will be executed once upon Duck's startup, and can be further executed by inputting the term ```purge``` into the CLI when Duck is running. The following is the new operation to be implemented.

-   ```TaskList#purge()``` - Navigates through the ```TaskList``` array, and checks the date/time of each Task item within. If the date/time of a given ```Task``` has passed, it will be removed from the ```TaskList``` array. The user will be then able to view a total of how many ```Tasks``` were purged, along with what ```Task``` they were.

<br />

**Given below is an example usage scenario for ```purge()```**

**Step 1.** The user launches the application for the first time. The ```TaskList``` will be initialized with the data from a given pre-existing datafile if it exists, and the ```Task``` items will be inserted into the ```TaskList```.![](https://lh5.googleusercontent.com/XcnArNZc5ZqHhxXZ1iRuq0aRxNKkcD_snVgDv-dlpMVYFQFQjyGXdtHWvFGzG-k2UW5SXkjQbthIRtkrol_SYsNgmsmKw1kKEkME0vSQ0tfRfUIWj3jZTTVrerHbESuA-AlTpPEkj8JrKl-mMlu-3yM)

**Step 2.** The ```TaskList#purge()``` command will be executed. The process is illustrated through two steps:

**Identifying all expired ```Task``` items in ```TaskList```.**

![](https://lh3.googleusercontent.com/Kbz7j3EmMGJGrIX1aQ8DCkHG8Y3gaIx3Ohf-zmYvr-S-LrxRqt8P1wlTMWUnlnna2tnR0i72yUJWB72Z2mWwGuwXiD_RnlIpYEk-MY6k9zMaLqTk_VkyHaRwIuzxi1trXHX1ySdZWBqZFDaCZ6K-QrY)

**Removing all expired ```Task``` items in ```TaskList```.**
![](https://lh4.googleusercontent.com/kVOcvIKAF1nlLSHXQsRPDuG6RwiyaqRXf9CYTLrv2WUezegCNx05GIC1KPYME6Eojid1hRbqUIzHUjKWRpYjUG7zCYE8586kLzbIrVdjZVnC5j_1ke1WBdFZSHoar_MuXKBA9eMARxCOgcja5qjP2_A)

The user will see this on his terminal after the ```purge``` command has executed.

```
----------------------------------------------------------------------
Quack! <expiredCount> Tasks have expired! Purge proceeding...
2. [X if <isDone>, " " if !<isDone>] <Deadline_1>
4. [X if <isDone>, " " if !<isDone>] <Event_1>

Purge Completed! Now we are one quack closer to finishing all tasks!
----------------------------------------------------------------------
```


**Step 3.** The ```Duck``` will continue to run as per usual. The user can choose to manually input the keyword purge into the CLI to repeat Step 2 above at any given time.

<br />

### Clear feature

**Implementation**

The ```Storage#clear()``` command was implemented to facilitate the removal of all ```Task``` items from the ```TaskList```. Upon the entry of the keyword ```clear```, the new operation ```Ui#doubleCheck``` will prompt the user for an input of ```Y/N```.

-   If a ```Y``` is inputted, the operation ```Storage#clear()``` executes and removes all ```Task``` items from the ```TaskList```. Additionally, the ```savedata``` file located in ```\data\savedata.txt``` will also be deleted and recreated as a blank slate.

-   If a ```N``` is inputted, the operation ```Storage#clear()``` executes and removes all ```Task``` items from the ```TaskList```. Additionally, the ```savedata``` file located in ```\data\savedata.txt``` will also be deleted and recreated as a blank slate.

<br />

 The following are the new operations implemented.

-   ```Storage#clear()``` - Removes all ```Task``` items from the ```TaskList```. Additionally, the ```savedata``` file located in ```\data\savedata.txt``` will also be deleted and recreated as a blank slate.

-   ```Ui#doubleCheck``` - Prompts the user for further input. If a ```Y``` is inputted, the ```Storage#clear()``` operation is executed. Else if a ```N``` is inputted, the operation is cancelled.

<br />

Given below is an example usage scenario for ```Storage#clear()```

**Step 1.** The user launches the application for the first time. The ```TaskList``` will be initialised with the data from a given pre-existing datafile if it exists, and the ```Task``` items will be inserted into the ```TaskList```.![](https://lh5.googleusercontent.com/XcnArNZc5ZqHhxXZ1iRuq0aRxNKkcD_snVgDv-dlpMVYFQFQjyGXdtHWvFGzG-k2UW5SXkjQbthIRtkrol_SYsNgmsmKw1kKEkME0vSQ0tfRfUIWj3jZTTVrerHbESuA-AlTpPEkj8JrKl-mMlu-3yM)

**Step 2.** The user wants to start afresh. The ```Storage#clear()```  command is then executed. The user will see an output in the following format:

```
THIS IS AN IRREVERSIBLE PROCESS. ARE YOU SURE? Y/N
```

**Step 3.1.** The user decides against clearing the ```TaskList```, and inputs a ```N```. The ```TaskList``` has no changes. ```Duck``` resumes after the output in the following format has been displayed:

```

Process cancelled.

```

**Step 3.2.** The user decides upon clearing the ```TaskList```, and inputs a ```Y```. The ```TaskList``` has been cleared.

![](https://lh4.googleusercontent.com/G4PalSoaVQxExSGKCDefJuO4TlyCJwrCfZDT4BsTrrSHMJ8aQSPsaxGoFJYb9YIO1yo3_3nV0Jar3Haqfac90v7G_yfZdx8-OwxSKEART5zLqMZ73k3YW0ssPbIE3kOhkbeusR3jVMdD6kTSkG7rK4s)

```Duck``` resumes after the output in the following format has been displayed:

```

Got it, all tasks have been cleared.

 ```

### SchoolClass `list_classes` feature

**Implementation**

The ```SchoolClass``` Class is implemented to facilitate the adding of students' classes to a separate schedule, which is a priority queue. It extends the ```Task``` Class with additional String attributes to store the class name, day of week, start time, and end time. It also overrides the ```toString()``` method to have its own specialised output when being printed, as well as overriding the ```toSaveString()``` method to correctly save its details to the save file. The ```SchoolClass``` Class will also facilitate the implementation of automatic time tracking of whether the class is over for the week, as well as sorting the SchoolClasses based on their day of week and start/end time. These two features are part of the ```list_classes``` feature, which lists out all the SchoolClasses stored in Duck.

The following are the new operations implemented.

-   ```TaskList#addSchoolClass()``` - Adds a SchoolClass to the schedule.

-   ```TaskList#deleteClass()``` - Removes a SchoolClass from the schedule.

-   ```Ui#listClasses()``` - Lists out all currently stored SchoolClasses according to day of week, start and end time in ascending order.

-   ```Storage#loadSchoolClass()``` - Adds a SchoolClass to the task list without generating a successfully added message, to be used when loading from the save data.

<br />

**Given below is an example usage scenario for  TaskList#addSchoolClass().**

The user inputs a command following the proper formatting for adding a ```SchoolClass```. The sequence diagram for adding a SchoolClass is shown below

![image](https://user-images.githubusercontent.com/88079008/230120692-d4771d07-b228-400e-9968-aed978a323e6.png)
  
<br />

**Given below is an example usage scenario for Ui#listClasses.**

The user inputs the command for ```list_classes```. The program then lists out all the SchoolClasses stored in Duck according to chronological order. The sequence diagram for Ui#listClasses is shown below.
 
![image](https://user-images.githubusercontent.com/88079008/230112915-fd04ca83-4d30-47c0-a3a4-c7a3c82e0b17.png)

<br />

### Recurring deadlines and events feature

**Implementation**

The `RecurringDeadline` and `RecurringEvent` classes are implemented to facilitate the adding of deadlines and events that happen every week to the `TaskList`. They extend `Deadline` and `Event` respectively with an additional `DayOfWeek` attribute `day` to indicate which day of the week the task recurs on. They both override the `toString()` and `toSaveString()` methods to return the correct `String` representation for output to the user and storage. `RecurringDeadline` and `RecurringEvent` also have a public method `getDay()` to return the private attribute `day`.

**The following new operations are implemented. **

-   `TaskList#addRecurrDeadline()` -- adds a `RecurringDeadline` object to the task list

-   `Storage#loadRecurrDeadline()` -- loads a `RecurringDeadline` from the saved data file to the task list

-   `TaskList#addRecurrEvent()` -- adds a `RecurringEvent` object to the task list

-   `Storage#loadRecurrEvent()` -- loads a `RecurringEvent` from the saved data file to the task list

Below is a class diagram for the implementation
 
 <img width="859" alt="image" src="https://user-images.githubusercontent.com/88618401/228712251-dc8a6eb0-7483-4d01-b2f0-99f9c105aa9a.png">

 
 <br />
 
## Product scope
### Target user profile

Duck is a program developed for students with multiple tasks such as deadlines and events, as well as many different classes who prefer CLI applications.

### Value proposition

Duck will be able to allow students who can type fast to quickly record down the tasks that they need to do, as well as helping them keep track of their different classes in an organised manner. By inputting short commands, students will be able to retrieve information on their upcoming tasks and classes and any related notes easily.

## User Stories

|Version| As a ... | I want to ... | So that I can ...|
|--------|----------|---------------|------------------|
|v1.0|user|have a help command that will display all possible commands and their appropriate input format|understand how to use Duck easily and conveniently|
|v1.0|student|be able to add tasks and events|remind myself constantly|
|v1.0|user|have a command to close the program|exit the program when I want to|
|v1.0|user|have a find filter|find the tasks in the list that contain a specified input keyword|
|v1.0|user|add a priority tag to each task such that they will be easily identified|be aware of urgent tasks to completed|
|v1.0|user|clear function with double check confirmation|delete all tasks from the list, with a confirmation message before clearing|
|v1.0|user|have priority variables for tasks|add priority levels to individual tasks|
|v1.0|student|be able to add school classes|keep track of my classes|
|v2.0|student|receive notifications about deadlines|not miss out on any tasks|
|v2.0|student with different classes|view my next class with its specified time||
|v2.0|student with multiple classes|record recurring classes at fixed times throughout the week|view my weekly class schedule without needing to record them down again each week|
|v2.0|student|create recurring tasks that will reset their 'Done' status when appropriate|not have to manually unmark these tasks|
|v2.0|student|easily access my schedule|plan my daily life|
|v2.0|user|automatically remove my expired tasks|not deal with the hassle of having to manually remove expired tasks|
|v2.0|user|add notes to my tasks|record down additional details|
|v2.0|user|edit my tasks|change previously recorded information|
|v2.0|student|delete my classes|remove classes that are no longer necessary|

## Non-Functional Requirements

1. Duck should be able to run on Macos, Windows and Linux with at least Java 11 installed.
2. Recorded tasks and classes should not be lost when the program or the system is shut down.
3. Users who are proficient in typing should be able to accomplish the different tasks faster than a typical user trying to accomplish the same tasks with a GUI instead.

## Glossary

* *Duck* - The name of the program
* *Task* - A task that can be recorded in Duck (Todo, Deadline, Event)
* *Command* - Lines of input that when typed into the terminal, will execute appropriate functions as indicated in the User Guide

## Instructions for manual testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}

### Launching the program
1. Ensure that Java 11 or above is installed
2. Download the latest version of Duck
3. Launch the terminal and navigate to the folder where Duck is located in
4. Type in the command ```java -jar Duck.jar``` to lauch Duck
    - Expected Outcome: Duck launches in the terminal with its start message displayed.

### Closing the program
1. Type ```bye``` in the terminal to exit the program
    - Expected Outcome: The exit message is displayed in the terminal, and the program closes.

### Storage and Save Data
- If Duck is run for the first time, the save file will be generated automatically along with the ```data``` folder
    - Expected Outcome: The ```data``` folder will be created, and the ```savedata.txt``` file will be created inside this folder

- After any changes are made to the list of tasks or schedule, the changes will be stored in to the save file located in ```data/savedata.txt```
    - Expected Outcome: Any newly added tasks or school classes or other changes made will be reflected in the ```savedata.txt``` file

- If any edits or changes are made directly to the ```savedata.txt``` file (which users are strongly recommended not to do so) which leads to the save file being invalid, the ```savedata.txt``` file will have all its data wiped upon starting up Duck.
    - Expected Outcome: An error message will be displayed in the terminal when Duck is lauched, with none of the previously saved tasks or classes being loaded. The ```savedata.txt``` file will have all of its contents erased.
