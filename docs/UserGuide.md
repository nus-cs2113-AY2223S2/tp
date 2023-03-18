# ***Life Tracker user guide***

## Introduction

**_Life Tracker_** is an application that allows users who are health conscious automate the tracking of their calories and keep a record of 
their calorie intake history.

The app also allows users to keep track of their daily exercises to compute their calorie loss.


## Quick Start

{Give steps to get started quickly}

1. Ensure that you have Java 11 or above installed.
1. Down the latest version of `Duke` from [here](http://link.to/duke).

## Features v1.0

{Give detailed description of each feature}
* Add meal
* View today's meal
* Delete meal
* Calculate caloric needs
* Calculate amount of calories left in the day

### Adding a meal: `add`
Adds a new item to the list of todo items.

Format: `todo n/TODO_NAME d/DEADLINE`

* The `DEADLINE` can be in a natural language format.
* The `TODO_NAME` cannot contain punctuation.  

Example of usage: 

`todo n/Write the rest of the User Guide d/next week`

`todo n/Refactor the User Guide to remove passive voice d/13/04/2020`

### Updating user's information: `update`
Allows the user to update any of their information.
User will come across a menu that shows them what information they can choose to update, they will need
to input the number in order to update the specific information they want to change.

Format: `update`

* The `update` must be in lower case and cannot contain punctuations.

Example of usage:

`todo n/Write the rest of the User Guide d/next week`

`todo n/Refactor the User Guide to remove passive voice d/13/04/2020`

### Viewing user's information: `view`
Allows the user to view any of their information as well as their calories.
User will come across a menu that shows them what information they can choose to view, they will need
to input the number in order to view the specific information they want to see.

Format: `view`
* The `view` must be in lower case and cannot contain punctuations.

Example of menu:

    ------------------------------------------------------------
    View user settings
    1. View Name
    2. View Weight
    3. View Height
    4. View Age      
    5. View Gender
    6. View Daily Caloric limit
    7. View Calories left today



### Exiting the program: `bye`
Allows user to exit program.

## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: {your answer here}

## Command Summary

{Give a 'cheat sheet' of commands here}

* Add todo `todo n/TODO_NAME d/DEADLINE`
