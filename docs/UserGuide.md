# User Guide

## Introduction

Moneymind is a Command Line Interface (CLI) application that 
helps you manage your personal finances. 
With Moneymind, you can keep track of your budgets, 
expenses, and categorize them for better organization.

## Quick Start

1. Download and install Moneymind on your computer and ensure that you have Java 11 or above installed.

2. Ensure you have Java11 or above installed on your computer.

3. Open the terminal and navigate to the directory where Moneymind is installed.

4. Run the following command to start the Moneymind app.

5. Type the command in the command box and press Enter to execute.

## Features 

{Give detailed description of each feature}

### Add an Event: `event`
Add an event which is to be recorded by Moneymind.

Format: `event NAME e/<expense number> [(optional) t/<time>]`

* `NAME` is the name of the event.
* `<expense number>` is the expense number of the event.
* `<time>` is the date of the monthly recurring event, in the pattern of "dd/MM/yyyy". Invalid patterns are replaced with the current System date. 
* The `t/` parameters is optional.
* This gives you the flexibility to add a one-time expense or monthly recurring expenses.
* Monthly recurring expenses will be require the user to update the expense number every month.

Example of usage:

`event bus travel fee e/1.50`

`event bubble tea e/6.50`

### Add a category: `category`

Add a category of event  to be recorded on Moneymind

Format: `category NAME [(optional) b/<budget number>]`

* `NAME` is the name of the category.
* `<budget number>` is the total amount of budget 
set to the category of events.
* The `b/` parameter is optional.

Example of usage:

`category electricity bill`

`category travel to Thailand b/2000`

### Delete an event or category: `delete`

Delete an event or category from Moneymind.

Format: `delete c/<category name> [(optional) e/<event index>]`

* `<category name>` is the name of the category.
* `<event index>` is the position of the event (1 based).
* The `e/` parameter is optional so that the user can choose between deleting a single event or the whole category.

Example of usage:

`delete c/electricity bill`

`delete c/food e/1`

### Edit an event: `edit`

Edit the details of an event on Moneymind.

Format: `edit c/<category name> e/<event index> `

* `<category name>` is the name of the category.
* `<event index>` is the position of the event that the user want to edit (1 based).
* The user will be prompted to enter the new details of the event.

Example of usage:

`edit c/overseas travel e/1`

Ok, the current expense for book hotel is: 200;

Your new expense would be:

`100`

Ok, the new expense is now changed to: 100 sgd

### View events in categories: `view`

This commands allows the user to view all events in one category or view all categories in moneymind.

Format: `view [(optional) c/<category name>]`

* `<category name>` is the name of the category that the user wants to view.
* The user can view all the categories by entering `view` without any category name

Example of usage:

`view c/food` : view all events in the food category

`view` : view all categories in moneymind

### Check the Instruction Menu: `help`

This command allows the user to view the instruction menu.

Format: `help`

Example of usage:

`help`

### Exit the program: `bye`

This command allows the user to exit the program.

Format: `bye`

Example of usage:

`bye`

## FAQ

**Q**: How do I get good in cs2113? 

**A**: spend more time on this module

## Command Summary

* `event` - Add an event to Moneymind
* `category` - Add a category to Moneymind
* `delete` - Delete an event or category from Moneymind
* `edit` - Edit an event in Moneymind
* `view` - View all events in a category or view all categories in Moneymind
* `help` - Show the help message
* `bye` - Exit the program
