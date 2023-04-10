# User Guide

<!-- TOC -->
* [User Guide](#user-guide)
  * [Introduction](#introduction)
  * [Quick Start](#quick-start)
  * [Account Instructions](#account-instructions)
    * [Create Account: `signup`](#create-account-signup)
    * [Log In To Your Account: `login`](#log-in-to-your-account-login)
    * [Sign out and save your expense: `logout`](#sign-out-and-save-your-expense-logout)
    * [Exiting the program (outside the account): `exit`](#exiting-the-program-outside-the-account-exit)
  * [Features](#features)
    * [Get helpful instruction: `help`](#get-helpful-instruction-help)
    * [Adding an expense: `add`](#adding-an-expense-add)
    * [Listing all expenses: `list`](#listing-all-expenses-list)
    * [Deleting an expense entry: `delete`](#deleting-an-expense-entry-delete)
    * [Calculating total expenses: `total`](#calculating-total-expenses-total)
    * [Sorting all current expenses: `sort`](#sorting-all-current-expenses-sort)
    * [Obtaining expenses: `category`](#obtaining-expenses-category)
    * [Finding expenses: `find`](#finding-expenses-find)
    * [Generating overview: `overview`](#generating-overview-overview)
    * [Exiting the program (inside the account): `exit`](#exiting-the-program-inside-the-account-exit)
  * [FAQ](#faq)
    * [Do I require an internet connection to use ET?](#do-i-require-an-internet-connection-to-use-et)
    * [Why is my primary currency not available for use?](#why-is-my-primary-currency-not-available-for-use)
    * [Am I allowed to add my own currency and exchange rate to ET?](#am-i-allowed-to-add-my-own-currency-and-exchange-rate-to-et)
    * [I want to add a date beyond 1981, how do I do that?](#i-want-to-add-a-date-beyond-1981-how-do-i-do-that)
    * [What will happen when other keys are added or when non-compulsory keys are removed from the JSON data file?](#what-will-happen-when-other-keys-are-added-or-when-non-compulsory-keys-are-removed-from-the-json-data-file)
  * [Command Summary](#command-summary)
    * [Account-related command](#account-related-command)
    * [Expense management command](#expense-management-command)
<!-- TOC -->

<div style="page-break-after: always;"></div>

## Introduction

Our Expense Tracker CLI software ("ET" for short) allows users to create their own bookmakers and helps
them keep track of their expense based on category, date, amount, and currency.

## Quick Start

1. Ensure that you have Java 11 or above installed.
2. Download the latest version of `ET` from [here](https://github.com/AY2223S2-CS2113-T13-2/tp/releases).
3. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar tp.jar` command to run
   the application.
4. Signup for an account using the `signup` option and then log in to proceed.
5. Once logged in, use the `help` command to see the list of available commands.
6. Once you are done, `exit` to quit the program or `logout` to switch accounts.

<div style="page-break-after: always;"></div>

## Account instructions
### Create Account: `signup`

* When you sign up, you should type your username, press Enter, then type your password, and press Enter.
* If your username is taken already, you should sign up again. Else, you could proceed to `login`.

Format:
```
signup
username
your_username     //Type your username after seeing `username`, then press 'Enter'
password
your_password   //Type your password after seeing `Password`, then press 'Enter'
```

Expected Output:

This will be displayed when your username contains a special characters (ie `_` in `your_username`):
```
Username must contain only letters and numbers.
_____________________________________________________________________________________
> Type "login" to start manage your expense. 
> Haven't got your account yet? Type "signup" to create your account. 
> Type "exit" to instantly terminate the program.
_____________________________________________________________________________________
```

This will be displayed when your password is less than 8 characters:

```
Password must be at least 8 characters long.
_____________________________________________________________________________________
> Type "login" to start manage your expense. 
> Haven't got your account yet? Type "signup" to create your account. 
> Type "exit" to instantly terminate the program.
_____________________________________________________________________________________

```

This will be displayed if your username is taken already (not case-sensitive):

```
The username is taken, please use another username.
_____________________________________________________________________________________
> Type "login" to start manage your expense. 
> Haven't got your account yet? Type "signup" to create your account. 
> Type "exit" to instantly terminate the program.
_____________________________________________________________________________________
```

For all those cases, the user will need to change to `login` or `exit`, or continue to signup until they could create 
a satisfied account. When the username is satisfied, this will be displayed:

```
User <your_username> has been created
Signup successfully
_____________________________________________________________________________________
> Type "login" to start manage your expense. 
> Haven't got your account yet? Type "signup" to create your account. 
> Type "exit" to instantly terminate the program.
_____________________________________________________________________________________
```
Then, you can log in to your new account.

 **Note: Username and password rules:**

* Username must just include alphabet characters (both uppercase and lowercase) or numbers.
* Password must have at least 8 characters.
* Username is not case-sensitive, (ie ABC and abc are considered 1 user), but password is case-sensitive.

### Log In To Your Account: `login`

* In order to log in to your account, you do similar steps as `signup`.
* If you type the password wrongly, you will see the message `Invalid username or password.` and you have to log in again.

Format
```
login
username
your_username     //Type your username after seeing `username`, then press 'Enter'
password
your_password   //Type your password after seeing `Password`, then press 'Enter'
```

Expected Output:

If the password and username are matched, this will be displayed, follow by a command list box for expense tracking.
```
Login successfully
```
After that, you can start managed your expense in your account

Else, this will be displayed, meaning that you will have to type the password again:
```
Invalid username or password.
_____________________________________________________________________________________
> Type "login" to start manage your expense. 
> Haven't got your account yet? Type "signup" to create your account. 
> Type "exit" to instantly terminate the program.
_____________________________________________________________________________________
```

### Sign out and save your expense: `logout`

* When you log out, all of your expenses in the expense list are saved, and the data is just restricted to your account.
* Once you log in again, the expense list could be loaded from your previous session. You can view the list through
  command `list`.

Format: `logout`

Expected Output:
```
Saved successfully.
You are logged out successfully!
_____________________________________________________________________________________
> Type "login" to start manage your expense. 
> Haven't got your account yet? Type "signup" to create your account. 
> Type "exit" to instantly terminate the program.
_____________________________________________________________________________________
```

### Exiting the program (outside the account): `exit`

Exits the program immediately.

Format: `exit`

Example of usage:

`exit`

<div style="page-break-after: always;"></div>

## Features

### Get helpful instruction: `help`

List all available commands user can type in the software, and give instructions and examples to help user get
familiar with our software

Format: `help`

### Adding an expense: `add`

Adds a new item to the list of expenses.

Format: `add amt/EXPENSE_AMOUNT t/TIME [cat/EXPENSE_CATEGORY] [cur/EXPENSE_CURRENCY]`

* The `EXPENSE_AMOUNT` must be an integer or decimal number without comma.
* The `TIME` must be in the DD-MM-YYYY format.
    * Future dates beyond today's date and Past dates before 1981 are invalid.
    * A warning will be shown if the input date has been adjusted as such a date may not exist for that particular
      month or year.
* The `EXPENSE_CATERGORY` can be any string that does not contain a whitespace symbol.
* The `EXPENSE_CURRENCY` has 22 currencies to choose from.
    * The list of available currencies are
      `SGD`, `EUR`, `GBP`, `USD`, `AUD`, `CAD`, `CNY`, `HKD`, `INR`, `IDR`, `JPY`, `KRW`, `MYR`, `TWD`, `NZD`, `PHP`,
      `QAR`, `SAR`, `CHF`, `THB`, `AED`, `VND`.
    * If the input currency is not found in the list of currencies
      available, the currency will default to SGD and a warning will be shown.
* The command parameters may be entered in any order. If multiple of the same parameter type is inputted, such as in
  `add amt/10 amt/100 t/11-11-2023` only the first parameter `amt/10` of its kind is used.
    * A warning will be shown if an invalid input type is specified.
* After the expense is added, it is automatically saved to the user's expense list. If the program is accidentally shut 
down, all the data in the account is still saved.

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

Expected output:

```
____________________________LIST____________________________
1.SGD10.00 cat:uncategorized date:11/11/2022
2.SGD24.00 cat:uncategorized date:02/02/2022
Now you have 2 expenses in the list.
____________________________________________________________
```

### Deleting an expense entry: `delete`

Delete expense entry with index X in the expense list.

Format: `delete INDEX`

* INDEX is entry number INDEX in the expense list displayed with `list` command, following base-1 indexing.
* Will display `Invalid expense index. Please try again.` if INDEX is greater than number of items in the list.
* After the expense is deleted, the change is automatically saved to the user's expense list. If the program is accidentally shut
  down, all the data in the account is still saved.

Example of usage:

`delete 1`

Expected output:

```
____________________________________________________________
The following expense is successfully deleted:
    SGD10.00 cat:uncategorized date:11/11/2022
Now you have 1 expense in the list.
____________________________________________________________
```

### Calculating total expenses: `total`

Calculates the total expenses in the expense list in `SGD`.

Format `total`

* Whenever an entry is added into the expense list, the exchange rate for the date before the input date is recorded.
  As exchange rate data is unavailable on weekends and public holidays, the exchange rate of the previous working day
  is taken instead.
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

Example of output (sort D):

```
____________________________________________________________
Date: 02/02/2012
1.SGD2.50 cat:food
2.USD2.50 cat:food
Date: 02/02/2013
1.USD2.50 cat:eat
____________________________________________________________
```

### Obtaining expenses: `category`

Obtain all the expenses with specified category, also obtain the category set to tell user what categories exist now.

Format: `category CATEGORY`

* CATEGORY represents the category that user want.
* If the category doesn't belong to the categories that user has entered before, it will tell the user to switch to
  another one.
* The method also will tell user how many categories they have, and also what are these categories, to better help
  them with future obtaining purpose
* Case sensitivity doesn't matter!

Example of usage:

`category food`

`category clothes`

### Finding expenses: `find`

Find all expenses with the specified information, regardless whether this information can form a meaningful word or
match the expense attribute.

Format: `find INFO`

* INFO represents the information that the user want to find.
* If none of the expenses have such information, it will tell the user this case.
* Even if the information is not a word, let's say "f", the method still will find expenses that any of their attributes
  contain the character f.
* Case sensitivity doesn't matter!

Example of usage:

`find 2.5`

`find f`

`find SGD`

### Generating overview: `overview`

**Monthly overview**

Generate a monthly overview consisting of total expenses of the month and a breakdown of expenses based on category, 
from the most spent to the least. The currency for display is standardised to SGD.

Format: `overview MONTH YEAR`

- `MONTH` must be the standard English Month Name
- `YEAR` must be between 1981 and 2023 (inclusive), in integer format

Example of usage:
`overview June 2021`

Expected output:

```
-------------Monthly Overview for JUNE 2021-------------

    Total expenses: 80.55 SGD

    Breakdown of expenses by category in descending order 
    by category sum:
    ----------------------------
     uncategorized 44.00 SGD
    ----------------------------
     food 26.55 SGD
    ----------------------------
     travel 10.00 SGD
    ----------------------------
----------------------------END----------------------------
```

**Yearly overview**

Generate a yearly overview consisting of total expenses of the year and a breakdown of monthly expenses, following
the order of natural month from January to December.
The currency for display is standardised to SGD.

Format: `overview YEAR`
- `YEAR` must be between 1981 and 2023 (inclusive), in integer format

Example of usage:
`overview 2022`

Expected output:
```
-------Yearly Overview for 2022-------

    Total expenses: 35.66 SGD

    Breakdown of expenses by month:
    ----------------------------
     JANUARY 0.00 SGD
    ----------------------------
     FEBRUARY 0.00 SGD
    ----------------------------
     MARCH 0.00 SGD
    ----------------------------
     APRIL 0.00 SGD
    ----------------------------
     MAY 10.00 SGD
    ----------------------------
     JUNE 0.00 SGD
    ----------------------------
     JULY 4.15 SGD
    ----------------------------
     AUGUST 0.00 SGD
    ----------------------------
     SEPTEMBER 0.00 SGD
    ----------------------------
     OCTOBER 21.51 SGD
    ----------------------------
     NOVEMBER 0.00 SGD
    ----------------------------
     DECEMBER 0.00 SGD
    ----------------------------
-----------------END-----------------
```

### Exiting the program (inside the account): `exit`
* When you are inside the account (ie: after logging in and before logging out of your account), typing `exit` will
  automatically save your account expense list, and then terminate the application.

Format `exit`

Expected Output:
```
Saved successfully.
Goodbye! Hope to see you again soon!
```

<div style="page-break-after: always;"></div>

## FAQ

### Do I require an internet connection to use ET?

- No an internet connection is not required. It is preferred as we use an exchange rate API to obtain our currency
exchange rates. However, a lack of internet connection will not hinder the use of our application.

### Why is my primary currency not available for use?

- Our selection of currencies is based on the currencies available in our exchange rate API, we apologise if
your currency is not available for use.

### Am I allowed to add my own currency and exchange rate to ET?

- The current version of ET does not support adding of arbitrary currency. We apologise if this caused any 
  inconvenience.

### I want to add a date beyond 1981, how do I do that?

- Our API only contains exchange rate information starting from 1981. For ET to effectively track expenses in different
currencies, we require a limit on how far back the date can be set.

### What will happen when other keys are added or when non-compulsory keys are removed from the JSON data file?
- Newly added keys by the user will be ignored upon initial loading of the expense list to prevent any potential
  conflict with the other features. The new keys will be deleted subsequently when the JSON file is overwritten
  with new changes.
- Non-compulsory keys (rate, currencyType and description) removed from the JSON file will be restored using the 
  default value.

<div style="page-break-after: always;"></div>
  
## Command Summary

### Account-related command

|             Action             |  Command   |
|:------------------------------:|:----------:|
|      Log in your account       |  `login`   |
|      Create a new account      |  `signup`  |
|  Exit ET (inside the account)  |   `exit`   |
|     Log out of the account     |  `logout`  |


### Expense management command

|             Action              |                                     Command                                     |
|:-------------------------------:|:-------------------------------------------------------------------------------:|
|         Add an expense          |  `add amt/EXPENSE_AMOUNT t/TIME [cat/EXPENSE_CATEGORY] [cur/EXPENSE_CURRENCY]`  |
|        Delete an expense        |                                 `delete INDEX`                                  |
|        List all expenses        |                                     `list`                                      |
|   Calculate total expenditure   |                                     `total`                                     |
|              Sort               |              sort by category: `sort C`<br/>sort by date: `sort D`              |
|    View expenses by category    |                               `category CATEGORY`                               |
|            Overview             |  monthly overview: `overview MONTH YEAR`<br/>yearly overview: `overview YEAR`   |
|          Find expenses          |                                 `find KEYWORD`                                  |
|  Exit ET (outside the account)  |                                     `exit`                                      |

