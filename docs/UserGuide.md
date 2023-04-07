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

"/" is a special character that is used to start command parameters. Other uses of "/" are not allowed except for t/ in event command

### Add an Event: `event`
Add an event which is to be recorded by Moneymind. Event must be placed in a category.

Format: `event NAME e/<expense number> [(optional) t/<time>]`

* `NAME` is the name of the event.
* `<expense number>` is the expense number of the event.
* `<time>` is the date of the monthly recurring event, in the pattern of "dd/MM/yyyy hh:mm". 
Invalid patterns are replaced with the current System date.
* The `t/` parameters is optional.
* This gives you the flexibility to add a one-time expense or monthly recurring expenses.
* Monthly recurring expenses will be require the user to update the expense number every month.
* In the context of this application, the 'MM', 'yyyy', 'hh' and 'mm' in the data format of `<time>` is less important
as the frequency is set as *monthly*. Hence, whatever valid month input that the user types
in, the event will be always recorded as a monthly recurring event based on its date.
To avoid confusion, the user is advised to type in the month and year of the first occurrence of the event
which they want to record down.

Example of usage:

`event bus travel fee e/2 t/01/01/2020 12:00`

`event bubble tea e/6`

### Add a Category: `category`

Add a category of event  to be recorded on Moneymind.

Format: `category NAME [(optional) b/<budget number>]`

* `NAME` is the name of the category.
* `<budget number>` is the total amount of budget 
set to the category of events.
* The `b/` parameter is optional.

Example of usage:

`category electricity bill`

`category travel to Thailand b/2000`

### Delete an Event or Category: `delete`

Delete an event or category from Moneymind.

Format: `delete c/<category name> [(optional) e/<event index>]`

* `<category name>` is the name of the category.
* `<event index>` is the position of the event (1 based).
* The `e/` parameter is optional so that the user can choose between deleting a single event or the whole category.

Example of usage:

`delete c/electricity bill`

`delete c/food e/1`

### Edit an Event: `edit`

Edit the details of an event on Moneymind.

Format: `edit c/<category name> [(optional) e/<event index>] `

* `<category name>` is the name of the category the user want to edit.
* `<event index>` is the position of the event that the user want to edit (1 based).
* The user will be prompted to enter the new details of the event or category

Example of usage:

`edit c/overseas travel e/1`

Ok, the current expense for book hotel is: 200;

Your new expense would be:

`100`

Ok, the new expense is now changed to: 100 sgd

### View Events in Categories: `view`

This commands allows the user to view all events in one category or view all categories in moneymind.

Format: `view [(optional) <category name>]`

* `<category name>` is the name of the category that the user wants to view.
* The user can view all the categories by entering `view` without any category name

Example of usage:

`view food` : view all events in the food category

`view` : view all categories in moneymind

### Search for a Category or Event: `search`

Finds and displays all categories and events that contain a specified keyword.

Format: `search KEYWORD`

* Categories and events that contains `KEYWORD` will be displayed.

Example of usage:

`search bill`

### Reminders

When starting up the program, it will remind the user of the 
upcoming events in the next 5 days.

`Appoaching expenses:`<br>
`Category_Name has an event: Event_Name in 2 days`<br>
`Category_Name_2 has an event: Event_Name_2 in 3 days`<br>

### Check the Instruction Menu: `help`

This command allows the user to view the instruction menu.

Format: `help`

Example of usage:

`help`

### Exit the Program: `bye`

This command allows the user to exit the program.

Format: `bye`

Example of usage:

`bye`

## FAQ

**Q**: Am I able to keep my data after I exit the program?

**A**: Yes. Your data will be saved automatically after you exit the program, and loaded the next time you use the program.

## Command Summary

* `event` - Add an event to Moneymind
* `category` - Add a category to Moneymind
* `delete` - Delete an event or category from Moneymind
* `edit` - Edit an event in Moneymind
* `view` - View all events in a category or view all categories in Moneymind
* `help` - Show the help message
* `search` - Search for a Category or Event
* `bye` - Exit the program
