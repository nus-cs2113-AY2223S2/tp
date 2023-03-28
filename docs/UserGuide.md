# DinerDirector User Guide

## Table of Contents
- [Introduction](#Introduction)
  - [Purpose of this Guide](#purpose-of-this-guide) 
- [Quick Start](#quick-start)
  - [Installing DinerDirector](#installing-dinerdirector)
- [Features](#features)
  - [Viewing Help](#viewing-help)
  - [Meetings](#meetings)
  - [Deadlines](#deadlines)
  - [Dish](#dish)
  - [Staffs](#staffs)
- [FAQ](#faq)
  
## Introduction

DinerDirector (DD) is a terminal app that helps restaurant managers to manage their restaurant in a more convenient manner, optimized for use via a Command Line Interface (CLI).

### Purpose of this Guide
This document aims to let you know how to use the application. 
This is a guide useful for both beginners or advance users who want a quick reference on how to run each command. 
How to install and run the application is also part of this guide.

## Quick Start
This section will guide you through the installation process  
### Installing DinerDirector

1. Ensure that you have Java 11 or above installed. 
2. Down the latest version of `DinerDirector` from [here](https://github.com/AY2223S2-CS2113-W15-4/tp/releases). 
3. Copy the file to the folder you want to use as the home folder for your `DinerDirector`. 
4. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar dinerdirector.jar` command to run the application. 
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

Note: The price of dishes will be shown in dollars.

#### Delete a deadline:

Format: `delete_deadline n/<name>`


Example: 
```
delete_deadline n/need to buy more potatoes
```

### Dish

#### Add a Dish to the list of dishes:

Successfully adds Dish only if all the arguments are correct.

Format: `add_dish n/<name of dish> pc/<price of dish in cents> [<ingredient 1>;<ingredient 2>;<ingredient 3> ... etc]`

- Name of dish cannot be blank or start with spaces, it also cannot contain only spaces.
- Price of dish must be an non negative integer value; i.e.: Price cannot be negative, in decimal, etc.
- Ingredient list is encased betwen two square brackets and separated by a semi-colon. The ingredient list can contain any non negative number of items.

Example 1: 
```
add_dish n/Chicken Burger pc/1099 [tomatoes;chicken fillet;cheese;bread with sesame seeds]
```
Outcome 1: 
```
<index_number>. Chicken Burger; $10.99; [tomatoes, chicken fillet, cheese, bread with sesame seeds]
```
You can also omit the ingredient list like such:

Example 2:
```
add_dish n/Chicken Burger pc/1099 []
```
Outcome 2:
```
<index_number>. Chicken Burger; $10.99; []
```

#### View the list of dishes added:

It shows the list of dishes currently stored in the program in order.

- The price of dishes are shown in dollars.

Format: `view_dish`

#### Delete a dish from the list of dishes:

Deletes the dish in the list based on the index given, if a dish exists at that index.

Format: `delete_dish <index_number>`

Example: 

Supposed that we have the following list of dishes:
```
1. Chicken Burger; $4.99; [tomatoes, chicken fillet, cheese, bread with sesame seeds]
2. McSpicy Burger; $8.99; [tomatoes, chicken fillet, cheese, bread with sesame seeds]
```
When the `delete_recipe 1` is entered as a command:

Outcome:
```
1. McSpicy Burger; $8.99; [tomatoes, chicken fillet, cheese, bread with sesame seeds]
```
#### Find dishes containing a keyword from the list of dishes

Finds a list of dishes containing a given keyword, if any at all.

Format: `find_dish <keyword>`

- The keyword cannot contain any spaces. 
- Only 1 keyword can be entered per find_dish command.

Example:

Supposed we have the following list of dishes:

```
1. McSpicy Burger; $8.99; [tomatoes, chicken fillet, cheese, bread with sesame seeds]
2. Chicken Burger; $10.99; [tomatoes, chicken fillet, cheese, bread with sesame seeds]
```
When `find_dish Chicken` is entered in as a command:

Outcome:

```
2. Chicken Burger; $10.99; [tomatoes, chicken fillet, cheese, bread with sesame seeds]
```

### Staffs

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

### Storage
The files generated by this application is stored in the `data` folder. The folder is located in the same directory
where you first started the application.  
The data stored in those files are in plaintext format and the user can copy out the data from the plaintext files for backup if needed.
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
| add_dish        | `add_dish n/<name of dish> pc/<price of dish in cents> [<ingredient 1>;<ingredient 2>;<ingredient 3> ... etc]` |
| view_dish       | `view_dish`                    |
| delete_dish     | `delete_dish <index_number>`    |
| find_dish       | `find_dish <keyword>`           |



