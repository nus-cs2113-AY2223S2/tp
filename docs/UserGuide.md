# Pet Tracker User Guide

## Introduction

Keeping track of multiple pets can be daunting due to information overload. With Pet Tracker, keeping track of our
furry friends will be easier! Pet owners can use this tool to monitor the health of their pet, including tracking their
weight over time. This can help identify any nutritional or health needs.

<br>
The main features of Pet Tracker includes:

* Tracking multiple pets at once
* Viewing the full list of pets


## Quick Start

1. Ensure that you have Java 11 or above installed.
2. Download the latest version of `Pet Tracker` 
from [here](https://github.com/AY2223S2-CS2113-T11-3/tp/releases/download/PetTrackerV1.0/pettracker.jar).
3. Launch a terminal and run the command `java -jar pettracker.jar`

## Features 

Here is a list of features for Pet Tracker

* Tracking multiple pets at once
* Viewing the full list of pets


## FAQ

**Q**: Can I make my pet's data persistent after closing the app? 

**A**: The team is currently planning on implementing that feature soon!

## Command Summary

### Adding a pet: `add-pet`
Adds a pet to the Pet List.

Format: `add-pet NAME`

* The `NAME` must not be empty.

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
Removes a pet to the Pet List.

Format: `remove-pet NAME`

* The `NAME` must not be empty.

Example of usage:

`remove-pet Alice`

Expected Output:

```
Successfully removed pet: Alice
```

### Adding a stat to a pet: `add-stat`
Adds a stat to a pet in the Pet List.

Format: `add-stat NAME STAT VALUE`

* The `NAME` must not be empty.
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

Format: `remove-stat NAME STAT`

* The `NAME` must not be empty.
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

Format: `edit-stat NAME STAT VALUE`

* The `NAME` must not be empty.
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
View the current Pet List and total number of pets.

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

Format: `add-task NAME` or `add-task NAME /by DATE`

* The `NAME` must not be empty.
* The `DATE` must be in the format `yyyy-mm-dd`.

Example of usage:

`add-task Buy food /by 2021-03-01`

Expected Output:

```
Added new task to list
```

### Removing a Task: `remove-task`
Removes a task from the Task List.

Format: `remove-task NUMBER`

* The `NUMBER` must not be empty.

Example of usage:

`remove-task 1`

Expected Output:

```
Successfully removed task 1
```

### Viewing Task List: `list-tasks`
View the current Task List and total number of tasks.

Format: `list-tasks`

Example of usage:

`list-tasks`

Expected Output:

```
Here are your tasks:
1. [ ] Buy food
  (Deadline: 2021-03-01)
2. [ ] Feed the dog
```

### Editing tasks: `edit-task`
View the current Task List and total number of tasks.

Format: `edit-task NUMBER VALUE` or `edit-task NUMBER VALUE /by DATE`
* The `DATE` must be in the format `yyyy-mm-dd`.

Example of usage:

`edit-task 2 feed the cat`

Expected Output:

```
Updated task 2 to feed the cat.
```

### Print Task Schedule: `schedule`
View the current list of tasks with an associated deadline, in order of deadline.

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

* The `NUMBER` must not be empty.

Example of usage:

`mark-task 1`

Expected Output:

```
Task marked as done
```

### Marking a Task as Not Done: `unmark-task`
Marks a task as not done in the Task List.

Format: `unmark-task NUMBER`

* The `NUMBER` must not be empty.

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