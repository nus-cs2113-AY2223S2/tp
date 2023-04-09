# DinerDirector User Guide

## Table of Contents
- [Introduction](#introduction)
  - [Purpose of this Guide](#purpose-of-this-guide) 
- [Quick Start](#quick-start)
  - [Installing DinerDirector](#installing-dinerdirector)
- [Features](#features)
  - [Help](#help)
  - [Meetings](#meetings)
  - [Deadlines](#deadlines)
  - [Dish](#dish)
  - [Staffs](#staffs)
  - [Exit](#exit)
  - [Storage](#storage)
- [FAQ](#faq)
- [Summary of Commands](#summary-of-commands)
  
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
2. Download the latest version of `DinerDirector` from [here](https://github.com/AY2223S2-CS2113-W15-4/tp/releases). 
3. Copy the file to the folder you want to use as the home folder for your `DinerDirector`. 
4. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar dinerdirector.jar` command to run the application. 
5. Type the command in the command box and press Enter to execute it. e.g. typing `help` and pressing Enter will open the help window. 
6. Refer to the Features below for details of each command.

## Features 

### Help
#### Purpose
Allows the restaurant manager using the application to see the list of commands available to use.  

Format: `help`

### Meetings
#### Purpose
Meeting is a feature for the restaurant manager to manage meetings with 
boss or workers. MeetingManager supports add_meeting, view_meetings,
delete_meeting and find_meeting. 

#### Add Meetings
Add a meeting to the meeting list.  The name of the meeting is written after `n/`, followed by the time of the meeting written after `t/`.  

**Note: `add_meeting` command will not handle extra parameters other than `n/` and `t/`.**  
**Furthermore, please do not contain `t/` inside the meeting name because anything after `t/` will be considered as time.**  

**Note: User is allowed to input the name in any format, and the time parameter in any time format they prefer.**

Format: `add_meeting n/<name> t/<time>`

Example: 
```
add_meeting n/meeting with boss t/Sunday, 3PM
```
Outcome:
```
Got it! You have successfully added a meeting:
meeting with boss at Sunday, 3PM
```

#### View meetings
View the meeting list.

Format: `view_meetings`

Outcome:
```
Meeting list:
meeting with boss at: Sunday, 3PM
```

#### Find meeting
Will return all the meetings if the name of the meeting contains the keyword.

Format: `find_meeting <keyword>`

Example:
```
find_meeting meeting with boss
```
Outcome:
```
Here's the matching meeting list:
1. meeting with boss at Sunday, 3PM
```

#### Delete meeting
Will delete the meeting specified from the list.

Format: `delete_meeting <meeting index>`

Example:
```
delete_meeting 1
```
Outcome:
```
Meeting meeting with boss deleted!
```

### Deadlines
#### Purpose
Deadline objects helps our user, the restaurant manager, to better keep track of and manage the many task that
needs to be completed for the restaurant.

#### Add a deadline to the deadline list
Format: `add_deadline n/<name> t/<time>`

**Note: User is allowed to input the name in any format, and the time parameter in any time format they prefer.**

Example: 
```
add_deadline n/need to buy more potatoes t/9 Apr 2PM
```
Outcome:
```
Got it! This deadline has been successfully added.
need to buy more potatoes by: 9 Apr 2PM
Now you have 1 deadlines in the deadline list.
```

#### View all deadlines

Format: `view_deadlines`

Outcome:
```
Here are the deadlines in your list:
1. need to buy more potatoes by: 9 Apr 2PM
```

#### Delete a deadline
Format: `delete_deadline <deadline index>`

Example:
```
delete_deadline 1
```  

Outcome:
```
Noted. I've removed this deadline:
need to buy more potatoes by: 9 Apr 2PM
Now you have 0 deadlines in the deadline list.
```

#### Find a deadline
Format: `find_deadline <keyword>`

Example: 
```
find_deadline buy
```

Outcome:
```
Here are the matching deadlines in your list:
1. need to buy more potatoes by: 9 Apr 2PM
```

### Dish

#### Purpose

This feature allows the restaurant manager to track the dishes that are sold in the restaurant, using commands that enables the manager to add, view, delete and find dishes.

#### Add a Dish to the list of dishes
Successfully adds Dish only if all the arguments are correct.

Format: `add_dish n/<name of dish> pc/<price of dish in cents> [<ingredient 1>;<ingredient 2>;<ingredient 3> ... etc]`

- Name of dish cannot be blank or start with spaces, it also cannot contain only spaces. It also must be unique.
- Price of dish must be a non-negative integer value and below 2,147,483,647 cents; i.e.: Price cannot be negative, in decimal, etc.
- Ingredient list is encased between two square brackets and separated by a semicolon. The ingredient list can contain any non-negative number of items. 
  - Ingredients are read in as strings separated by ";", thus it can contain leading white spaces and numbers. However, it cannot be an empty string.
  - Ingredients cannot be a number (i.e. "123", "-456", "+7.89", and "0.123"). However, it can be part of a word, like: "3 potatoes".

Example 1: 
```
add_dish n/Chicken Burger pc/1099 [tomatoes;chicken fillet;cheese;bread]
```
Outcome 1: 
```
Added dish: 1. Chicken Burger; $10.99; [tomatoes, chicken fillet, cheese, bread]
```
You can also omit the ingredient list like such:

Example 2:
```
add_dish n/McSpicy Burger pc/1099 []
```
Outcome 2:
```
Added dish: 2. McSpicy Burger; $10.99; []
```

#### View the list of dishes added

It shows the list of dishes currently stored in the program in order.

- The price of dishes are shown in dollars.

Format: `view_dish`

#### Delete a dish from the list of dishes

Deletes the dish in the list based on the index given, if a dish exists at that index.

Format: `delete_dish <dish index>`

Example: 

Supposed that we have the following list of dishes:
```
1. Chicken Burger; $4.99; [tomatoes, chicken fillet, cheese, bread with sesame seeds]
2. McSpicy Burger; $8.99; [tomatoes, chicken fillet, cheese, bread with sesame seeds]
```
When the `delete_dish 1` is entered as a command:

Outcome:
```
1. McSpicy Burger; $8.99; [tomatoes, chicken fillet, cheese, bread with sesame seeds]
```

#### Find dishes containing a keyword from the list of dishes

Finds a list of dishes containing a given keyword, if any exists.

Format: `find_dish <keyword>`

- The keyword cannot contain any spaces. 
- Only 1 keyword can be entered per find_dish command.
- The find dish command returns dishes that has words in its description matching the whole keyword, or has words that contains the keyword as a substring.
- Searching is also case insensitive: i.e.: Apple and apple are treated the same.

Example 1:

Suppose we have the following list of dishes:

```
1. McSpicy Burger; $8.99; [tomatoes, chicken fillet, cheese, bread with sesame seeds]
2. Chicken Burger; $10.99; [tomatoes, chicken fillet, cheese, bread with sesame seeds]
```
When `find_dish Chicken` is entered in as a command:

Outcome 1:

```
2. Chicken Burger; $10.99; [tomatoes, chicken fillet, cheese, bread with sesame seeds]
```

Example 2:

When `find_dish spicy` is entered in as a command:

Outcome 2:

```
1. McSpicy Burger; $8.99; [tomatoes, chicken fillet, cheese, bread with sesame seeds]
```

### Staffs
#### Purpose
This feature allows the restaurant manager to record their staff members personal particulars and also what date they are working on. It allows the restaurant manager to keep track of their staff and what day they are working on.

#### Add a staff
Add a staff to the staffs list.

Format: `add_staff n/<name> w/<working day> d/<date of birth> p/phone`

**Note: The date format should be in YYYY-MM-DD and phone number should only contain number. Date of birth should be before this present time as well.**

Example:
```
add_staff n/John Doe w/Sunday d/2003-01-01 p/82802123
```

Outcome:
```
John Doe, working in Sunday. Date of birth: 2003-01-01, phoneNumber: 82802123 added!
```

#### View a staff
View the staffs list.

Format: `view_staff`

Outcome:
```
1. John Doe, working in Sunday. Date of birth: 2003-01-01, phoneNumber: 82802123
```

#### Delete a staff
Delete a staff from the staffs list.

Format: `delete_staff <staff index>`

Example:
```
delete_staff 1
```

Outcome:
```
1. John Doe, working in Sunday. Date of birth: 2003-01-01, phoneNumber: 82802123 removed
```

#### Find a staff
Find a staff from staffs list

Format: `find_staff <keyword>`

Example:
```
find_staff john
```

Outcome:
```
Here's the matching staff:
John Doe, working in Sunday. Date of birth: 2003-01-01, phoneNumber: 82802123
```
### Exit
Exits the program.

Format: `exit`

Outcome:
```
>exit
Thank you for using DinerDirector!
```

### Storage
The files generated by this application is stored in the `data` folder. The folder is located in the same directory
where you first started the application.  
The data stored in those files are in plaintext format and the user can copy out the data from the plaintext files for backup if needed.  
**Note: It is mandatory to ensure that there is read and write permissions available for the application to successfully store and read what you inputted into the individual list.**

#### Manual editing of the files
The contents in the file are stored in a custom format with `~|~` being the separator for each individual components. Below are examples of the format for each of the respective files.  
**Note: It is mandatory to follow the format stated to ensure that the details will be read correctly by the application.** 

#### Editing meeting_list.txt
Format: `<meeting name>~|~<date>`

Example:
```
Meeting with boss~|~Sunday, 3PM
Meeting with supplier~|~Saturday, 9AM
```

#### Editing deadline_list.txt   
Format: `<deadline name>~|~<date>`

Example:
```
fix water pipes~|~10 Apr
need to buy more potatoes deal~|~9 Apr 2PM
```

#### Editing dish_list.txt
Format: `<dish name>~|~<price>~|~<ingredient1>;<ingredient2>;<ingredient3>`
- You can add more ingredients by separating it with a semicolon.

Example:
```
Chicken Burger~|~1099~|~tomatoes;chicken fillet;cheese;bread with sesame seeds
```

#### Editing staff_list.txt
Format: `<staff name>~|~<working day>~|~<date of birth>~|~<phone>`

Example:
```
John Doe~|~Sunday~|~2001-03-09~|~82802123
```
## FAQ

**Q**: How do I set up the project?

**A**: Clone the project from GitHub and run it through Intellij.

## Summary of Commands

| Action          | Command                                                                                                        |
|-----------------|----------------------------------------------------------------------------------------------------------------|
| help            | `help`                                                                                                         |
| add_meeting     | `add_meeting n/<name> t/<time>`                                                                                |
| view_meetings   | `view_meetings`                                                                                                |
| delete_meeting  | `delete_meeting <meeting index>`                                                                               |
| find_meeting    | `find_meeting <string>`                                                                                        |
| add_dish        | `add_dish n/<name of dish> pc/<price of dish in cents> [<ingredient 1>;<ingredient 2>;<ingredient 3> ... etc]` |
| view_dish       | `view_dish`                                                                                                    |
| delete_dish     | `delete_dish <dish index>`                                                                                     |
| find_dish       | `find_dish <keyword>`                                                                                          |
| add_deadline    | `add_deadline n/<name> t/<time>`                                                                               |
| view_deadlines  | `view_deadlines`                                                                                               |
| delete_deadline | `delete_deadline <deadline index>`                                                                             |
| find_deadline   | `find_deadline <keyword>`                                                                                      |
| add_staff       | `add_staff n/<name> w/<working day> d/<date of birth> p/phone`                                                 |
| view_staff      | `view_staff`                                                                                                   |
| delete_staff    | `delete_staff <staff index>`                                                                                   |
| find_staff      | `find_staff <keyword>`                                                                                         |


