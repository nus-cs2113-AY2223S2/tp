<!-- omit in toc -->

# User Guide

<!-- omit in toc -->

## Table of Contents

- [Getting Started](#getting-started)
- [Features](#features)
    - [Adding an expense: `/add` {#add}](#adding-an-expense-add-add)
    - [Deleting an expense: `/delete` {#delete}](#deleting-an-expense-delete-delete)
    - [Edit an expense: `/edit` {#edit}](#edit-an-expense-edit-edit)
    - [View an expense: `/view` {#view}](#view-an-expense-view)
    - [Show help menu: `/help` {#help}](#show-help-menu-help)
    - [Exit Program: `/bye` {#bye}](#exit-program-bye)
    - [Supported Categories {#categories}](#supported-categories-categories)
- [Command Summary {#summary}](#command-summary)

## Getting Started

- Ensure that you have Java `11` and above
  installed [(Installation Guide)](https://docs.oracle.com/en/java/javase/11/install/overview-jdk-installation.html#GUID-8677A77F-231A-40F7-98B9-1FD0B48C346A)
- Download our latest release of `PocketPal` [here](https://github.com/AY2223S2-CS2113-W15-2/tp/releases)
- Run the application
  with `java -jar PocketPal.jar` [(Running JAR Guide)](https://se-education.org/guides/tutorials/jar.html#running-jar-files)
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
|--------------------|:---------------------------------------------------:|
| [/add](#add)       |                   Adds an expense                   |
| [/delete](#delete) |                 Deletes an expense                  |
| [/edit](#edit)     |                  Edits an expense                   |
| [/view](#view)     | Displays details of an expense e.g. Price, Category |
| [/help](#help)     |               Displays the help menu                |
| [/bye](#bye)       |               Terminates the program                |

### Adding an expense: `/add` {#add}

Adds an expense to your current expenditure.

Format: `/add <-d | -description DESCRIPTION> [EXTRA_DESCRIPTION...] <-c | -category CATEGORY> <-p | -price PRICE>`

- `DESCRIPTION`, `EXTRA_DESCRIPTION` can only contain alphabets, digits or spaces.
- `CATEGORY` must be currently support in PocketPal.
- `PRICE` can be in numeric or decimal format.
- Flags can be used in any order, but they are all **required**.

Here is a [list](#categories) of categories currently supported in PocketPal.

Example of usage:

`/add -d Lunch at McDonalds -category Food -price 19.9`

`/add -d Apple Macbook Air -p 1300 -c Personal`

`/add -p 1300 -c Personal -d Apple Macbook Air`

### Deleting an expense: `/delete` {#delete}

Deletes a specified expense from your current expenditure.

The expense ID can be obtained from the [`/view`](#view) command.

Format: `/delete <EXPENSE_ID>`

- The `EXPENSE_ID` must be a whole number.

Example of usage:

`/delete 5`

### Edit an expense: `/edit` {#edit}

Edits a specified expense in your current expenditure with the given flag(s).

Format: `/edit <EXPENSE_ID> [-c | -category NEW_CATEGORY] [-p | -price NEW_PRICE] [-d | -description NEW_DESC]`

- `EXPENSE_ID` must be a whole number.
- Flags can be specified in any order. If none are specified, the expense remains unmodified.
- `NEW_CATEGORY`, `NEW_PRICE`, `NEW_DESC` must follow the required format as per the corresponding flag.

__Available flags__

- `-d | -description DESCRIPTION`
    - Replaces the current description of the expense with `DESCRIPTION`

- `-c | -category CATEGORY`
    - Replaces the current category of the expense with `CATEGORY`

- `-p | -price PRICE`
    - Replaces the current price of the expense with `PRICE`

Example of usage:

`/edit 5 -p 10.50`

`/edit 5 -description Grab to school -c Transportation`

### View an expense: `/view` {#view}

Displays a list of your current expenditure.

Format: `/view [COUNT] [-c | -category CATEGORY] [-p | -price PRICE_MIN] [-p | -price PRICE_MAX]
[<-sd | -startdate START_DATE -ed | -enddate END_DATE>]`

- `COUNT` must be a whole number. If not specified, all the expenditures will be listed.
- `CATEGORY` must be a supported category. If not specified, expenditures from all categories will be listed.
- `PRICE_MIN`, `PRICE_MAX` must be more than 0. If `PRICE_MAX` is not specified, all expenses with a higher price than
  `PRICE_MIN` price will be displayed. The first price entered must be less than that on the
  right. i.e. **MIN_PRICE should be entered before MAX_PRICE.**
- `START_DATE`, `END_DATE` must be in `dd/MM/yy` format. **Both flags are required if user wishes to use this
  filter feature.**

Example of usage:

`/view 10`

`/view -c food`

`/view -c food -sd 12/03/23 -ed 11/04/23`

`/view -c food -p 2 -sd 12/03/23 -ed 11/04/23`

### Show help menu: `/help` {#help}

Displays the help menu.

Format: `/help`

### Exit Program: `/bye` {#summary}

Terminates PocketPal.

Format: `/bye`

### Supported Categories {#categories}

These are the categories currently supported by PocketPal:

`Clothing, Entertainment, Food, Medical, Personal, Transportation, Utilities, Income, Others`

## Command Summary {#summary}

| Command | Format                                                                                                                                                                     |
|--------:|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
|    /add | `/add <-d &#124; -description DESCRIPTION> [EXTRA_DESCRIPTION...] <-c &#124; -category CATEGORY> <-p &#124; -price PRICE>`                                                 |
|   /view | `/view [COUNT] [-c &#124; -category CATEGORY] [-p &#124; -price PRICE_MIN] [-p &#124; -price PRICE_MAX] [<-sd &#124; -startdate START_DATE -ed &#124; -enddate END_DATE>]` |
|   /edit | `/edit <EXPENSE_ID> [-c &#124; -category NEW_CATEGORY] [-p &#124; -price NEW_PRICE] [-d &#124; -description NEW_DESC]`                                                     |
| /delete | `/delete <EXPENSE_ID>`                                                                                                                                                     |
|   /help | `/help`                                                                                                                                                                    |

<!-- @@author -->
