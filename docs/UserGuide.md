# User Guide

## Introduction

Our Expense Tracker CLI software ("ET" for short) allows users to create their own bookmakers and helps 
them keep track of their expense based on category, time, amount, and currency.

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
   * `sort D`: Sort the expenses by date.
   * `category food`: Find all expenses with food category.
   * `find 2.5`: Find all expenses with information of 2.5.

## Features

### Get helpful instruction: `help`
List all available commands user can type in the software, and give instructions 
and examples to help user get familiar with our software 

Format: `help`


### Adding an expense: `add`
Adds a new item to the list of expenses.

Format: `add amt/EXPENSE_AMOUNT t/TIME [cat/EXPENSE_CATEGORY] [cur/EXPENSE_CURRENCY]`

* The `TIME` must be in the DD-MM-YYYY format. Future dates beyond today's date and Past dates before 1981 are invalid.
* The `EXPENSE_AMOUNT` must be an integer or decimal number.  
* The `EXPENSE_CATERGORY` can be any string that does not contain a whitespace symbol.
* The `EXPENSE_CURRENCY` has 22 currencies to choose from. If the input currency is not found in the list of currencies
available, the currency will default to SGD.
* The command parameters may be entered in any order. If multiple of the same parameter type is inputted, such as in 
`add amt/10 amt/100 t/11-11-2023` only the first parameter `amt/10` of its kind is used.

Example of usage:

`add amt/10 t/11-11-2022`

`add amt/9.5 t/01-11-2022 cat/food cur/USD`

Expected output:

```
____________________________________________________________
The following expense is successfully added:
SGD10.00 cat:uncategorized date:11/11/2022
Now you have 1 expense in the list.
____________________________________________________________
```
```
____________________________________________________________
The following expense is successfully added:
    USD9.50 cat:food date:01/11/2022
Now you have 2 expenses in the list.
____________________________________________________________
```


### Listing all expenses: `list`
List all tracked expenses in the expense list in the order of input (First input first print).

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

### Calculating total expenses: `total`
Calculates the total expenses in the expense list in `SGD`.

Format `total`

* Whenever an entry is added into the expense list, the exchange rate for the date before the input date is recorded. As
exchange rate data is unavailable on weekends and public holidays, the exchange rate of the previous working day is
taken instead.
* If no internet access is available, a preset exchange rate is taken instead.

Example of usage:

`total`

Expected output:
```
____________________________________________________________
Your total expenses add up to:
    SGD23.41
____________________________________________________________
```

### Sorting all current expenses: `sort`
Sorting all current expenses in the expense list based on sortBy criteria.

Format: `sort SORTBY`

* SORTBY represents the criteria the user want to sort their expenses list and display by.
* If user wants to sort the expenses list by Date, he/she should enter "D".
* If user wants to sort the expenses list by Category through alphabet order, he/she should enter "C".

Example of usage:

`sort D`

`sort C`

### Obtaining expenses: `category`
Obtain all the expenses with specified category, also obtain the category set to tell user what categories exist now.

Format: `category CATEGORY`

* CATEGORY represents the category that user want.
* If the category doesn't belong to the categories that user have entered before, it will tell the user to switch to another one.
* The method also will tell user how many categories they have, and also what are these categories, so as to better help them with future obtaining purpose

Example of usage:

`category food`

`category clothes`

### Find expenses: `find`
Find all expenses with the specified information, regardless whether this information can form a meaningful word or match the expense attribute.

Format: `find INFO`

* INFO represents the information that the user want to find.
* If none of the expenses have such information, it will tell the user this case.
* Even if the information is not a word, let's say "f", the method still will find expenses that any of their attributes contain the character f.

Example of usage:

`find 2.5`

`find f`

`find SGD`



### Generating a monthly overview: `overview`
Generate a monthly overview consisting of total expenses of the month and a 
breakdown of expenses based on category, from the most spent to the least. The currency 
for display is standardised as SGD.

Format: `overview MONTH YEAR`
- `MONTH` must be the standard English Month Name

Example of usage:
`overview June 2021`

Expected output:
```
Overview for JUNE 2021

    Total expenses: 80.55 SGD

    Breakdown of expenses by category in descending order by category sum:
    ----------------------------
     uncategorized 44.00 SGD
    ----------------------------
     food 26.55 SGD
    ----------------------------
     travel 10.00 SGD
    ----------------------------
```



### Exiting the program: `exit`
Exits the program without saving expense list.

Format: `exit`

Example of usage:

`exit`


## FAQ


## Command Summary


* Add expense `amt/EXPENSE_AMOUNT t/TIME cat/EXPENSE_DESCRIPTION`
* List all expenses `list`
* Delete entry X in list `delete X`
* List total `total`
