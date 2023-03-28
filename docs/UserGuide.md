<!-- omit in toc -->
# User Guide

<!-- omit in toc -->
## Table of Contents
- [Getting Started](#getting-started)
- [Features](#features)
  - [Adding an expense: `/add` {#add}](#adding-an-expense-add-add)
  - [Deleting an expense: `/delete` {#delete}](#deleting-an-expense-delete-delete)
  - [Edit an expense: `/edit` {#edit}](#edit-an-expense-edit-edit)
  - [View an expense: `/view`](#view-an-expense-view)
  - [Show help menu: `/help`](#show-help-menu-help)
  - [Exit Program: `/bye`](#exit-program-bye)
  - [Supported Categories {#categories}](#supported-categories-categories)
- [Command Summary](#command-summary)

## Getting Started

- Ensure that you have Java `11` and above installed [(Installation Guide)](https://docs.oracle.com/en/java/javase/11/install/overview-jdk-installation.html#GUID-8677A77F-231A-40F7-98B9-1FD0B48C346A)
- Download our latest release of `PocketPal` [here](https://github.com/AY2223S2-CS2113-W15-2/tp/releases)
- Run the application with `java -jar PocketPal.jar` [(Running JAR Guide)](https://se-education.org/guides/tutorials/jar.html#running-jar-files)
- You should see the following welcome screen
   ```
   Welcome to
   _____           _        _   _____      _
   |  __ \         | |      | | |  __ \    | |
   | |__) |__   ___| | _____| |_| |__) |_ _| |
   |  ___/ _ \ / __| |/ / _ \ __|  ___/ _` | |
   | |  | (_) | (__|   <  __/ |_| |  | (_| | |
   |_|   \___/ \___|_|\_\___|\__|_|   \__,_|_|

   How may I help you?
   ________________________________________________
   Enter a command or /help to see the list of commands available.
   > 
   ```

<!-- @@author adenteo -->
The table below provides a summary of all the currently supported features in PocketPal.
More detailed explanations on the usage of the commands are provided as well.

## Features 

If you face any problems, do visit the [FAQ](../faq) segment!

| Command            |                      Function                       |
| ------------------ | :-------------------------------------------------: |
| [/add](#add)       |                   Adds an expense                   |
| [/delete](#delete) |                 Deletes an expense                  |
| [/edit](#edit)     |                  Edits an expense                   |
| [/view](#view)     | Displays details of an expense e.g. Price, Category |
| [/help](#help)     |               Displays the help menu                |
| [/bye](#bye)       |               Terminates the program                |

### Adding an expense: `/add` {#add}

Adds an expense to your current expenditure.

Format: `/add <DESCRIPTION> <-c | -category CATEGORY> <-p | -price PRICE>`

- The `DESCRIPTION` and `CATEGORY` can be in a natural language format.
- The `PRICE` can be in numeric or decimal format.
- The flags can be used in any order, but they are both **required**.

Here is a [list](#categories) of categories currently supported in PocketPal.

Example of usage:

`/add Lunch at McDonalds -category Food -price 19.9`

`/add Apple Macbook Air -p 1300 -c Personal`

### Deleting an expense: `/delete` {#delete}

Deletes a specified expense from your current expenditure.

The expense ID can be obtained from the [`/view`](#view) command.

Format: `/delete <EXPENSE_ID>`

- The `EXPENSE_ID` must be a whole number.

Example of usage:

`/delete 5`

### Edit an expense: `/edit` {#edit}

Edits a specified expense in your current expenditure.

Format: `/edit <EXPENSE_ID> [FLAG...]`

- `EXPENSE_ID` must be a whole number.
- Flags can be specified in any order. If none are specified, the expense remains unmodified.

__FLAGS__

- `-d | -description DESCRIPTION`
    - Replaces the current description of the expense with `DESCRIPTION`

- `-c | -category CATEGORY`
    - Replaces the current category of the expense with `CATEGORY`

- `-p | -price PRICE`
    - Replaces the current price of the expense with `PRICE`

Example of usage:

`/edit 5 -p 10.50`

`/edit 5 -description Grab to school -c Transportation`

### View an expense: `/view`

Displays a list of your current expenditure.

Format: `/view [COUNT] [-c | -category CATEGORY]`

- `COUNT` must be a whole number. If not specified, all the expenditures will be listed.
- `CATEGORY` must be a supported category. If not specified, expenditures from all categories will be listed.

Example of usage:

`/view 10`

`/view -c food`

### Show help menu: `/help`

Displays the help menu.

Format: `/help`

### Exit Program: `/bye`

Terminates PocketPal.

Format: `/bye`

### Supported Categories {#categories}

These are the categories currently supported by PocketPal:

`Clothing, Entertainment, Food, Medical, Personal, Transportation, Utilities, Income, Others`

## Command Summary

| Command | Format                                                                                       |
| ------: | -------------------------------------------------------------------------------------------- |
|    /add | `/add <DESCRIPTION> <-c,-category CATEGORY> <-p,-price PRICE>`                               |
|   /view | `/view <COUNT> <-c,-category CATEGORY> <-p, -price PRICE> <-p, -price PRICE>`                |
|   /edit | `/edit <EXPENSE_ID> <-d,-description DESCRIPTION> <-c,-category CATEGORY> <-p,-price PRICE>` |
| /delete | `/delete <EXPENSE_ID>`                                                                       |
|   /help | `/help`                                                                                      |

<!-- @@author -->
