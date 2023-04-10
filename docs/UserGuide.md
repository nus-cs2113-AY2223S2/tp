# Pet Tracker User Guide
<!-- TOC -->
* [Pet Tracker User Guide](#pet-tracker-user-guide)
  * [Introduction](#introduction)
  * [Quick Start](#quick-start)
  * [Features](#features)
    * [Tracking multiple pets at once](#tracking-multiple-pets-at-once)
    * [Add Tasks with deadlines](#add-tasks-with-deadlines)
    * [Reminder feature for tasks due today](#reminder-feature-for-tasks-due-today)
    * [Viewing the full list of pets and tasks](#viewing-the-full-list-of-pets-and-tasks)
  * [Command Summary](#command-summary)
    * [Adding a pet: `add-pet`](#adding-a-pet-add-pet)
    * [Removing a pet: `remove-pet`](#removing-a-pet-remove-pet)
    * [Adding a stat to a pet: `add-stat`](#adding-a-stat-to-a-pet-add-stat)
    * [Removing a stat from a pet: `remove-stat`](#removing-a-stat-from-a-pet-remove-stat)
    * [Editing a stat of a pet: `edit-stat`](#editing-a-stat-of-a-pet-edit-stat)
    * [Viewing Pet List: `list`](#viewing-pet-list-list)
    * [Adding a Task: `add-task`](#adding-a-task-add-task)
    * [Removing a Task: `remove-task`](#removing-a-task-remove-task)
    * [Viewing Task List: `list-tasks`](#viewing-task-list-list-tasks)
    * [Editing tasks: `edit-task`](#editing-tasks-edit-task)
    * [Print Task Schedule: `schedule`](#print-task-schedule-schedule)
    * [Marking a Task as Done: `mark-task`](#marking-a-task-as-done-mark-task)
    * [Marking a Task as Not Done: `unmark-task`](#marking-a-task-as-not-done-unmark-task)
    * [Exiting the program: `exit`](#exiting-the-program-exit)
  * [FAQ](#faq)
<!-- TOC -->
## Introduction

Keeping track of multiple pets can be daunting due to information overload. With Pet Tracker, keeping track of our
furry friends will be easier! Pet owners can use this tool to monitor the health of their pet, including tracking their
current weight. This can help identify any nutritional or health needs.

<br>
The main features of Pet Tracker includes:

* Tracking multiple pets at once
* Add Tasks with deadlines
* Viewing the full list of pets and tasks


## Quick Start

1. Ensure that you have Java 11 or above installed.
2. Download the latest version of `Pet Tracker` 
from [here](https://github.com/AY2223S2-CS2113-T11-3/tp/releases/download/PetTrackerV2.1/pettracker.jar).
3. Launch a terminal in the folder that the jar file is located in
4. Run the command `java -jar pettracker.jar`

## Features 

Here is a list of features for Pet Tracker

### Tracking multiple pets at once
Pet Tracker allows you to track multiple pets, saving important statistics like their Type, Age and Weight.

Related Commands: `add-pet` and `add-stat`

### Add Tasks with deadlines
Apart from pets, tasks can also be added to track things to do such as feeding your pet, setting a deadline for when
the task should be completed.

Related Commands: `add-task`

### Reminder feature for tasks due today
When a task is due today, Pet Tracker will remind you about this task upon starting the program.

### Viewing the full list of pets and tasks
Conveniently view the full list of your pets and tasks

Related Commands: `list` and `list-tasks`

## Command Summary

### Adding a pet: `add-pet`
Adds a pet to the Pet List. 

Format: `add-pet PETNAME`

* The `PETNAME` must not be empty.

Example of usage:

```
add-pet Alice
add-pet Bob
```

Expected Output:

```
Successfully added new pet: Alice
Successfully added new pet: Bob
```

### Removing a pet: `remove-pet`
Removes a pet from the Pet List.

Format: `remove-pet PETNAME`

* The `PETNAME` must not be empty.

Example of usage:

`remove-pet Alice`

Expected Output:

```
Successfully removed pet: Alice
```

### Adding a stat to a pet: `add-stat`
Adds a stat to a pet in the Pet List.
Currently, the only stats supported are Type, Age and Weight. The unit of the Weight is in Kg.

Format: `add-stat PETNAME STAT VALUE`

* The `PETNAME` must not be empty.
* The `STAT` must not be empty.
* The `VALUE` must not be empty.


Example of usage:

```
add-stat Bob Weight 50
add-stat Bob Age 10
```

Expected Output:

```
Updated Weight to 50 for Bob
Updated Age to 10 for Bob
```

### Removing a stat from a pet: `remove-stat`
Removes a stat from a pet in the Pet List.

Format: `remove-stat PETNAME STAT`

* The `PETNAME` must not be empty.
* The `STAT` must not be empty.

Example of usage:

`
remove-stat Bob Age
`

Expected Output:

```
Successfully removed Age from Bob
```

### Editing a stat of a pet: `edit-stat`
Edit a stat of a pet in the Pet List.

Format: `edit-stat PETNAME STAT VALUE`

* The `PETNAME` must not be empty.
* The `STAT` must not be empty.
* The `VALUE` must not be empty.


Example of usage:

```
edit-stat Bob Type Cat
edit-stat Bob Weight 12
```

Expected Output:

```
Successfully updated Bob's Type to Cat
Successfully updated Bob's Weight to 12
```


### Viewing Pet List: `list`
Views the current Pet List and total number of pets.

Format: `list`

Example of usage:

`list`

Expected Output:

```
Name: Bob
Type:
Age:
Weight: 50
____________________
Number of pets: 1
```
### Adding a Task: `add-task`
Adds a task to the Task List.

Format: `add-task TASKNAME` or `add-task TASKNAME /by DATE`

* The `TASKNAME` must not be empty.
* The `DATE` must be in the format `yyyy-mm-dd`.
* The `DATE` can be a date that is already over, in the event that the user is adding an overdue task.

Example of usage:

`add-task Buy food /by 2021-03-01`

Expected Output:

```
Added new task to list
```

### Removing a Task: `remove-task`
Removes a task from the Task List.

Format: `remove-task NUMBER`

* The `NUMBER` must not be empty. The NUMBER here refers to index in the list

Example of usage:

`remove-task 1`

Expected Output:

```
Successfully removed task 1
```

### Viewing Task List: `list-tasks`
Views the current Task List and total number of tasks.

Format: `list-tasks`

Example of usage:

`list-tasks`

Expected Output:

```
Here are your tasks:
1. [ ] Buy food (Deadline: 2021-03-01)
2. [ ] Feed the dog
```

### Editing tasks: `edit-task`
Views the current Task List and total number of tasks.

Format: `edit-task NUMBER VALUE` or `edit-task NUMBER VALUE /by DATE`
* The `NUMBER` must not be empty. The NUMBER here refers to index in the list
* The `DATE` must be in the format `yyyy-mm-dd`.

Example of usage:

`edit-task 2 feed the cat`

Expected Output:

```
Updated task 2 to feed the cat.
```

### Print Task Schedule: `schedule`
Views the current list of tasks with an associated deadline, in order of deadline.

Format: `schedule`

Example of usage:

`schedule`

Expected Output:

```
Here is your schedule:
1. [ ] Buy food (Deadline: 2021-03-01)
2. [ ] Feed the dog (Deadline: 2021-04-02)
```

### Marking a Task as Done: `mark-task`
Marks a task as done in the Task List.

Format: `mark-task NUMBER`

* The `NUMBER` must not be empty. The NUMBER here refers to index in the list

Example of usage:

`mark-task 1`

Expected Output:

```
Task marked as done
```

### Marking a Task as Not Done: `unmark-task`
Marks a task as not done in the Task List.

Format: `unmark-task NUMBER`

* The `NUMBER` must not be empty. The NUMBER here refers to index in the list

Example of usage:

`unmark-task 1`

Expected Output:

```
Task marked as not done
```

### Exiting the program: `exit`
Exits the program.

Format: `exit`

Example of usage:

`exit`

Expected Output:

```
Goodbye! See you soon.
```

## FAQ

**Q**: Can I edit the saved files directly?

**A**: It is strongly advised not to do so to prevent data corruption which will disrupt
the retrieval of previous data

**Q**: Can I get help with the commands while the program is running?

**A**: Yes, you can run `help` to view all commands and its usage.