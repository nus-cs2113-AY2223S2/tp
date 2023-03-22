# User Guide

## Introduction

ChChing is a desktop app for tracking spending, and it uses a Command Line Interface (CLI) for managing finances. If you are someone who needs a simple interface to get a better hold of your finances, this app is for you!

## Quick Start

1. Ensure that you have Java 11 or above installed.
2. Down the latest version of `ChChing` from [here](https://github.com/AY2223S2-CS2113-T12-1/tp/releases).
3. Copy downloaded `ChChing.jar` file to the desired home folder of choice.
4. Open a command terminal and `cd` into the folder mentioned in step 3.
5. Type in the following to run the application:
```
java -jar ChChing.jar
```
6. If successful, the following greeting should appear:
```
    ____________________________________________________________
    Hello! I'm ChChing.
    What can I do for you?
    ____________________________________________________________
```
7. Type in desired command to start using the program! List of commands are listed below

## Features

### Adding an entry: `add`
Creates a new entry for expenses or income, with respective input formats.

Format:
<br>`add expense /c CATEGORY /de DESCRIPTION /da DATE /v VALUE`
<br>`add income /de DESCRIPTION /da DATE /v VALUE`

Use case:
* Re-prompts user if any field is not provided properly.
* VALUE will be stored as a positive float.

Example of usage:
<br>`add expense /c meal /de breakfast @Technoedge /da 2023-03-22 /v 3.50`
<br>`add income /c salary /de salary /da 2023-03-22 /v 500`

### Listing all expenses and incomes: `list`
Shows a full list of both the expenses and incomes created by the user. Includes the index number, category, description, and value.
<br> Can view list of expenses/incomes separately as well.

Format:
<br> `list`
<br> `list expense`
<br> `list income`

### Deleting an entry: `delete`
Remove an entry from the expense/income list.

Format:
<br> `delete expense /in INDEX`
<br> `delete income /in INDEX`

Use case:
* INDEX is a positive integer.
* Prompts user if index is out of bounds.

Example of usage:
<br>`delete expense /in 1`
<br>`delete income /in 2`

### Finding balance of finances: `balance`
Shows users the balance after calculating the difference between income and expenses

Format:
<br> `balance`

### View help: `help`
Shows basic commands executable by the program and the use format for the commands

Format:
<br> `help`

### Saving and loading of data
Record entries are saved in `data/chching.txt` file. This file is updated upon exit. 
When reading from the file, entries for income and expenses are stored in their respective arrayList.

## Command Summary

{Give a 'cheat sheet' of commands here}

* Add Expense: `add expense /c CATEGORY /de DESCRIPTION /da DATE /v VALUE`
* Add Income: `add income /de DESCRIPTION /da DATE /v VALUE`
* List Records: `list`
* List Expenses: `list expense`
* List Incomes: `list income`
* Delete Expense: `delete expense /in INDEX`
* Delete Income: `delete income /in INDEX`
* Show Balance: `balance`
* Show help: `help`
