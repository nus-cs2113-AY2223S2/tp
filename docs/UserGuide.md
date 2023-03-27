# User Guide

## Introduction

DinerDirector (DD) is a terminal app that helps restaurant managers to manage their restaurant in a more convenient manner, optimized for use via a Command Line Interface (CLI).


## Quick Start

1. Ensure that you have Java 11 or above installed. 
2. Down the latest version of `DinerDirector` from [here](http://link.to/duke). 
3. Copy the file to the folder you want to use as the home folder for your DinerDirector. 
4. Open a command terminal, cd into the folder you put the jar file in, and use the java -jar dinnerdirector.jar command to run the application. 
5. Type the command in the command box and press Enter to execute it. e.g. typing help and pressing Enter will open the help window. 
6. Refer to the Features below for details of each command. 
7. Command format: <> denotes parameter. [] denotes optional field.

## Features 

### Viewing Help
A simple command to display the help page in the program. 

Format: `help`

### Meetings

#### Add a meeting to the meeting list:

Successfully adds meeting only if the name is unique, otherwise it informs the user to key in a unique meeting name

Format: `add_meeting n/<name> t/<time>`

Example: 
```
add_meeting n/meeting with boss t/Sunday, 3PM
```

#### View meetings:

View the meeting list.

Format: `view_meetings`

#### Delete a meeting:

Format: `delete_meeting n/<meeting>`

Example: 
```
delete_meeting n/meeting with boss
```


### Deadlines

#### Add a deadline to the deadline list:

Successfully adds deadline only if the name is unique, otherwise it informs the user to key in a unique deadline name

Format: `add_deadline n/<name> t/<time>`

Example: 
```
add_deadline n/need to buy more potatoes t/Saturday, 2PM
```

#### View all deadlines:

View the deadline list.

Format: `view_deadline`

#### Delete a deadline:

Format: `delete_deadline n/<name>`


Example: 
```
delete_deadline n/need to buy more potatoes
```

### Menu

#### Add a recipe to the menu:

Successfully adds menu only if the name is unique, otherwise it informs the user to key in a unique menu name

Format: `add_recipe n/<name>`

Example: 
```
add_recipe n/Chicken rice
```

#### View the list of recipes:

Format: `view_recipe`

#### Delete a recipe from the menu:

Format: `delete_recipe n/<name>`

Example: 
```
delete_recipe n/Chicken rice
```

### Workers

#### Add a worker:

Add a worker to the workers list.

Format: `add_worker n/<name> w/<working day> d/<date of birth> p/<phone>`

Example: 
```
add_worker w/Weekdays Morning Shift n/Patrick Parker d/12 Aug 2002 p/9583 4832
```

#### View a worker:

View the workers list.

Format: `view_workers`

#### Delete a worker:

Delete a worker from the workers list.

Format: `delete_worker n/<name>`

Example: 
```
delete_worker n/Patrick Parker
```

## FAQ

**Q**: How do I set up the project?

**A**: Clone the project from GitHub and run it through Intellij.


## Command Summary

| Action          | Command                       |
|-----------------|-------------------------------|
| help            | help                          |
| add_meeting     | add_meeting n/<name> t/<time> |
| view_meetings   | view_meetings                 |
| delete_meeting  | delete_meeting n/<name>       |
| find_meeting    | find_meeting s/<string>       |


