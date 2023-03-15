# User Guide

## Introduction

{Give a product intro}

## Quick Start

1. Ensure that you have Java 11 or above installed.
1. Down the latest version of `Duke` from [here](https://github.com/AY2223S2-CS2113-T13-2/tp/releases).
1. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar Duke.jar` command to run the application.
1. Enter your name when prompted in the command box and press enter.
1. Type the command in the command box and press Enter to execute it.
Some example commands you can try:

    * `add amt/100 t/15-03-2023`: Add an expense entry with a value of SGD100.0 and a date of 15th March 2023 to the expense list.
    * `list`: List all expenses in the expense list.
    * `delete 1`: Delete the first entry in the expense list.

## Features 

### Adding an expense: `add`
Adds a new item to the list of todo items.

Format: `add amt/EXPENSE_AMOUNT t/TIME cat/EXPENSE_DESCRIPTION`

* The `TIME` must be in the DD-MM-YYYY format.
* The `EXPENSE_AMOUNT` must be an integer or decimal number.  
* The `EXPENSE_DESCRIPTION' can be any word or phrase that does not contain the backdash symbol`/` or whitespace symbol.
* The `EXPENSE_DESCRIPTION` may be ommited.
* The command parameters may be entered in any order. If multiple of the same parameter type is inputted, such as in `add amt/10 amt/100 t/11-11-2023` only the first parameter `amt/10` of its kind is used.

Example of usage: 

`add amt/10 t/11-11-2023`

`add amt/9.5 t/01-11-2023 cat/food`

### Listing all expenses: `list`
List all tracked expenses in the expense list.

Format: `list`

* Will display `Sorry, there are no expenses tracked currently.` if expense list is empty.

Example of usage:

`list`

### Deleting an expense entry: `delete`
Delete expense entry with index X in the expense list.

Format: `delete INDEX`

* INDEX is entry number INDEX in the expense list displayed with `list' command, following base-1 indexing.
* Will display `Invalid expense index. Please try again.` if INDEX is greater than number of items in the list.

Example of usage:

`delete 1`

`delete 7`

### Exiting the program: `exit`
Exits the programn without saving expense list.

Format: `exit`

Example of usage:

`exit`


## FAQ


## Command Summary


* Add expense `amt/EXPENSE_AMOUNT t/TIME cat/EXPENSE_DESCRIPTION`
* List all expenses `list`
* Delete entry X in list `delete X`
