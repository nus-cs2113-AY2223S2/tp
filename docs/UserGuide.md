# User Guide

## Introduction

{Give a product intro}

## Quick Start

1. Ensure that you have Java 11 or above installed.
2. Down the latest version of `Duke` from [here](https://github.com/AY2223S2-CS2113-T13-2/tp/releases).
3. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar Duke.jar` command to run the application.
4. Enter your name when prompted in the command box and press enter.
5. Log in to your account by typing `login` if you have already got one, or else type `signup` to create your account,
and then press Enter to proceed. After sign up successfully, you should log in to experience the app. More instructions 
about account and password are stated in the "Account Instructions" part.
6. Once you have logged in successfully, you will see a message `Login successful`, and you are ready to experience our
application! You will see a command box for command instruction.
7. Type the command in the command box and press Enter to execute it.
   Some example commands you can try:
   * `add amt/100 t/15-03-2023`: Add an expense entry with a value of SGD100.0 and a date of 15th March 2023 to the expense list.
   * `list`: List all expenses in the expense list.
   * `delete 1`: Delete the first entry in the expense list.
   * `sort D`: Sort the expenses by date.
   * `category food`: Find all expenses with food category.
   * `find 2.5`: Find all expenses with information of 2.5.
8. When you want to log out and save your expense list, type `logout`. 

## Account instructions
   ### Create Account: `signup`
   * When you sign up, you should type your username, press Enter, then type your password, and press Enter. 
   * If your username is taken already, you should sign up again. Else, you could proceed to `login`.
   ### Note: Username and password rules:
   * Username must just include alphabet characters (both uppercase and lowercase) or number.
   * Password must have at least 8 characters.
   ### Log In To Your Account: `login`
   * In order to log in to your account, you do similar steps as `signup`. 
   * If you type the password wrongly, you will see a message `Invalid username or password.`and you have to log in again.
   ### Sign out and save your expense: `logout`
   * When you log out, all of your expenses in the expense list is saved, and the data is just restricted to your account.
   * Once you log in again, the expense list could be loaded from your previous session. You can view the list through 
   command `list`.

## Features

### Adding an expense: `add`
Adds a new item to the list of todo items.

Format: `add amt/EXPENSE_AMOUNT t/TIME [cat/EXPENSE_DESCRIPTION] [cur/EXPENSE_CURRENCY]`

* The `TIME` must be in the DD-MM-YYYY format.
* The `EXPENSE_AMOUNT` must be an integer or decimal number.  
* The `EXPENSE_DESCRIPTION` can be any word or phrase that does not contain the backdash symbol`/` or whitespace symbol.
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
Soring all current expenses in the expense list based on sortBy criteria.

Format: `delete SORTBY`

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
* The method also will tell user how many categories they have, and also what are these categories,
* so as to better help them with futuer obtaining purpose

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
