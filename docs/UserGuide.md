# User Guide

## Table of Contents

1. [Introduction](#introduction)
2. [Quick Start](#quick-start)
3. [Features](#features)
   * [Adding an expense](#adding-an-expense-addexpense)
   * [Adding an income](#adding-an-income-addincome)
   * [Listing all expenses](#listing-all-expenses-listexpense)
   * [Listing all income](#listing-all-income-listincome)
   * [Editing an expense](#editing-an-expense-editexpense)
   * [Editing an income](#editing-an-income-editincome)
   * [Sorting an expense by amount](#sorting-an-expense-by-amount-sortexpensebyamount)
   * [Sorting an income by amount](#sorting-an-income-by-amount-sortincomebyamount)
   * [Sorting an expense by date](#sorting-an-expense-by-date-sortexpensebydate)
   * [Sorting an income by date](#sorting-an-income-by-date-sortincomebydate)
   * [Deleting an expense](#deleting-an-expense-deleteexpense)
   * [Deleting an income](#deleting-an-income-deleteincome)
   * [Set budget](#set-budget-setbudget)
   * [View budget](#view-budget-viewbudget)
   * [Help command](#help-command-help)
   * [Exiting the program](#exiting-the-program-exit)
   * [Saving the data](#saving-the-data)
4. [FAQ](#faq)
5. [Command Summary](#command-summary)


## Introduction


BrokeMan is a desktop app for managing expenses and income, optimized for use via a Command Line Interface(CLI).
If you can type fast, BrokeMan can get your expenses and income management tasks done faster than traditional GUI apps.

[back to Contents](#table-of-contents)

---

## Quick Start


1. Ensure that you have Java 11 or above installed.
2. Download the latest version of `BrokeMan` from [here](https://github.com/AY2223S2-CS2113-F13-2/tp/releases/tag/v2.1).
3. Copy the file to the folder you want to use as the home folder for your BrokeMan.
4. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar CS2113-F13-2_BrokeMan.jar` command to run the application.

    A welcome message as shown below should appear.

    ```
    |  -----------------------------------------------------------------------
    |  -----------------------------------------------------------------------
    |
    |  $$$$$___ ______ _______ $$_____ _______ $$___$$_ _______ _______
    |  $$__$$__ ______ _______ $$__$$_ _$$$$__ $$$_$$$_ $$$$$__ _______
    |  $$$$$___ $$_$$_ _$$$$__ $$_$$__ $$__$$_ $$$$$$$_ ____$$_ $$$$$__
    |  $$___$$_ $$$_$_ $$__$$_ $$$$___ $$$$$$_ $$_$_$$_ _$$$$$_ $$__$$_
    |  $$___$$_ $$____ $$__$$_ $$_$$__ $$_____ $$___$$_ $$__$$_ $$__$$_
    |  $$$$$$__ $$____ _$$$$__ $$__$$_ _$$$$$_ $$___$$_ _$$$$$_ $$__$$_
    |  Welcome to BrokeMan!
    |  Your personal budget manager to prevent you to become broke like me...
    |
    |  -----------------------------------------------------------------------
    |  -----------------------------------------------------------------------
    |
    |  Enter command:
    ```

5. Type the command in the command box and press Enter to execute it. Refer to the Features below for details of each command. Type `help` to receive a list of commands that are available. 

[back to Contents](#table-of-contents)

---

## Features

### Adding an expense: `addExpense`
Format: `addExpense a/ <amount> d/ <description> t/ <time> c/ <category>`
Adds a new expense to the list of expenses.

* The `amount` should be a double up to **2 decimal places** (dp). If amount has more than 2dp, it will round off to nearest 2dp.
  * The largest amount that can be entered is `9999999999.99`.
  * The amount entered must be greater `0`.
* The `description` should be a String.
* The `time` should follow `YYYY MM DD HH mm` format.
  * (Note: entering 3 instead of 03 still works for March. 
  Users can remove preceding 0s, where apt, for simplicity sake)
  * The oldest time that can be entered is 2000 01 01 00 00.
  * The furthest time that can be entered is 9999 12 31 23 59.
* The `category` should be one of the categories in the category list.

**Category list**: FOOD, SHOPPING, GROCERIES, TRANSPORTATION, ENTERTAINMENT, TRAVEL, SALARY, INVESTMENT, and OTHERS

Example of usage:
`addExpense a/ 4.5 d/ lunch t/ 2023 03 22 20 12 c/ FOOD`

Example output:

```
|  -----------------------------------------------------------------------
|
|  Enter command: addExpense a/ 4.5 d/ lunch t/ 2023 3 22 20 12 c/ FOOD
|  You have successfully added [$4.50 spent on lunch - 2023-03-22 @ 20:12 [FOOD]]
|
|  -----------------------------------------------------------------------
|
|  Enter command:
```

**Note**: Please do not leave the description of flags empty.

[back to Contents](#table-of-contents)

---

### Adding an income: `addIncome`
Format: `addIncome a/ <amount> d/ <description> t/ <time> c/ <category>`
Adds a new income to the list of incomes.

* The `amount` should be a double up to **2dp**. If amount has more than 2dp, it will round off to nearest 2dp.
    * The largest amount that can be entered is `9999999999.99`.
    * The amount entered must be greater `0`.
* The `description` should be a String.
* The `time` should follow `YYYY MM DD HH mm` format.
  * (Note: entering 3 instead of 03 still works for March.
  Users can remove preceding 0s, where apt, for simplicity sake)
  * The oldest time that can be entered is 2000 01 01 00 00.
  * The furthest time that can be entered is 9999 12 31 23 59.
* The `category` should be one of the categories in the category list.

**Category list**: FOOD, SHOPPING, GROCERIES, TRANSPORTATION, ENTERTAINMENT, TRAVEL, SALARY, INVESTMENT, and OTHERS

Example of usage:
`addIncome a/ 400 d/ salary t/ 2023 03 12 15 01 c/ SALARY`

Example output:

```
|  -----------------------------------------------------------------------
|
|  Enter command: addIncome a/ 400 d/ salary t/ 2023 03 12 15 01 c/ SALARY
|  You have successfully added [$400.00 earned on salary - 2023-03-12 @ 15:01 [SALARY]]
|
|  -----------------------------------------------------------------------
|
|  Enter command:
```

**Note**: Please do not leave the description of flags empty.

[back to Contents](#table-of-contents)

---

### Listing all expenses: `listExpense`
Format: `listExpense [t/ date]`
Shows a list of all expenses in the list of expense.

- The `date` should follow `YYYY/MM` format.
  * The oldest date that can be entered is 2000/01.
  * The furthest date that can be entered is 9999/12.
- The time parameter is **optional**. If you add this optional parameter,
it will show a list of all the expenses incurred in the month specified. If the optional time parameter is not given,
it will show a list of all expenses across the entire time period.

Example of usage: `listExpense t/ 2023/03` Shows a list of all expenses incurred in the month 2023 March

Example output:

```
|  -----------------------------------------------------------------------
|
|  Enter command: listExpense t/ 2023/03
|  Here are the expenses you have made for 2023/MARCH.
|  1. $4.50 spent on lunch - 2023-03-22 @ 20:12 [FOOD]
|  Total expenses: $4.5
|
|  -----------------------------------------------------------------------
|
|  Enter command:
```

[back to Contents](#table-of-contents)

---

### Listing all income: `listIncome`
Format: `listIncome [t/ date]`
Shows a list of all income in the list of income.

- The `date` should follow `YYYY/MM` format.
  * The oldest date that can be entered is 2000/01.
  * The furthest date that can be entered is 9999/12.
- The date parameter is **optional**. If you add this optional parameter,
it will show a list of all income made in the specified month. If the optional date parameter is not given,
it will show a list of all income across the entire time period.

Example of usage: `listIncome` Shows a list of all income made across the entire time period.

Example output:

```
|  -----------------------------------------------------------------------
|
|  Enter command: listIncome
|  Here are the income you have made.
|  1. $3000.00 earned on salary - 2023-03-10 @ 10:10 [SALARY]
|  2. $3000.00 earned on salary - 2023-02-10 @ 10:10 [SALARY]
|  3. $3000.00 earned on salary - 2023-01-10 @ 10:10 [SALARY]
|  Total income: $9000.00
|
|  -----------------------------------------------------------------------
|
|  Enter command:
```

[back to Contents](#table-of-contents)

---

### Editing an expense: `editExpense`
Format: `editExpense i/ <index> t/ <type> n/ <newEntry>`
* Edits the expense at the specified `index`. 
The index refers to the index number shown in the displayed expense list.
The index must be a positive integer 1, 2, 3, …​
* The `type` can be: amount, info, time, or category.
  * amount (of type **double** up to **2dp**. If amount has more than 2dp, it will round off to nearest 2dp.): edits the expense
    * The largest amount that can be entered is `9999999999.99`.
    * The amount entered must be greater `0`.
  * info (of type **String**): edits the description of expense
  * time (in the format **YYYY MM DD HH mm**): edits the time at which user spent that expense.
    * (Note: entering 3 instead of 03 still works for March, Users can remove preceding 0s, where apt, for simplicity sake)
    * The oldest time that can be entered is 2000 01 01 00 00.
    * The furthest time that can be entered is 9999 12 31 23 59.
  * category (should be in the category list mentioned in [`addExpense`](#adding-an-expense-addexpense) feature): edits the category of that expense
* You can only edit one type at a time.
* Existing values will be updated to the input values.

Example of usage:
`editExpense i/ 1 t/ amount n/ 5`

Example output:

```
|  -----------------------------------------------------------------------
|
|  Enter command: editExpense i/ 1 t/ amount n/ 5
|  Successfully edited amount.
|
|  -----------------------------------------------------------------------
|
|  Enter command:
```

**Note**: Please do not leave the description of flags empty.

[back to Contents](#table-of-contents)

---

### Editing an income: `editIncome`
Format: `editIncome i/ <index> t/ <type> n/ <newEntry>`
* Edits the income at the specified `index`.
  The index refers to the index number shown in the displayed income list.
  The index must be a positive integer 1, 2, 3, ...
* The `type` can be: amount, info, time, or category.
  * amount (of type **double** up to **2dp**. If amount has more than 2dp, it will round off to nearest 2dp.): edits the income
    * The largest amount that can be entered is `9999999999.99`.
    * The amount entered must be greater `0`.
  * info (of type **String**): edits the description of income
  * time (in the format **YYYY MM DD HH mm**): edits the time at which income is earned. 
    * (Note: entering 3 instead of 03 still works for March, Users can remove preceding 0s, where apt, for simplicity sake)
    * The oldest time that can be entered is 2000 01 01 00 00.
    * The furthest time that can be entered is 9999 12 31 23 59.
  * category (should be in the category list mentioned in [`addExpense`](#adding-an-expense-addexpense) feature): edits the category of that income
* You can only edit one type at a time.
* Existing values will be updated to the input values.

Example of usage: `editIncome i/ 1 t/ category n/ FOOD`

Example output:

```
|  -----------------------------------------------------------------------
|
|  Enter command: editIncome i/ 1 t/ category n/ FOOD
|  Successfully edited category.
|
|  -----------------------------------------------------------------------
|
|  Enter command:
```

**Note**: Please do not leave the description of flags empty.

[back to Contents](#table-of-contents)

---

### Sorting an expense by amount: `sortExpenseByAmount`
Format: `sortExpenseByAmount`
Shows a list of all expenses in the order of higher amount to lower amount.

Example of usage: `sortExpenseByAmount`

Example output:

```
|
|  Enter command: sortExpenseByAmount
|  1. $50.00 spent on dinner - 2023-03-10 @ 18:18 [FOOD]
|  2. $20.00 spent on brunch - 2023-12-12 @ 12:12 [FOOD]
|  3. $5.00 spent on lunch - 2023-03-22 @ 14:00 [FOOD]
|  Total expenses: $75.0
|
|  -----------------------------------------------------------------------
|
|  Enter command:
```

**Note**: As long as the input `sortExpenseByAmount` is correct,
any trailing characters with a space after it will still call the function.

I.e.,`sortExpenseByAmount random characters` will still work.

[back to Contents](#table-of-contents)

---

### Sorting an income by amount: `sortIncomeByAmount`
Format: `sortIncomeByAmount`
Shows a list of all income in the order of higher amount to lower amount.

Example of usage: `sortIncomeByAmount`

Example output:

```
|  -----------------------------------------------------------------------
|
|  Enter command: sortIncomeByAmount
|  1. $3000.00 earned on stocks - 2023-02-11 @ 01:01 [INVESTMENT]
|  2. $1000.00 earned on grab - 2023-03-15 @ 15:15 [SALARY]
|  3. $400.00 earned on salary - 2023-03-12 @ 15:01 [FOOD]
|  Total income: $4400.00
|
|  -----------------------------------------------------------------------
|
|  Enter command:
```

**Note**: As long as the input `sortIncomeByAmount` is correct,
any trailing characters with a space after it will still call the function.

I.e.,`sortIncomeByAmount random characters` will still work.

[back to Contents](#table-of-contents)

---

### Sorting an expense by date: `sortExpenseByDate`
Format: `sortExpenseByDate`
Shows a list of all expenses in a chronological order.



Example of usage: `sortExpenseByDate`

Example output:

```
|  -----------------------------------------------------------------------
|
|  Enter command: sortExpenseByDate
|  1. $20.00 spent on brunch - 2023-12-12 @ 12:12 [FOOD]
|  2. $5.00 spent on lunch - 2023-03-22 @ 14:00 [FOOD]
|  3. $50.00 spent on dinner - 2023-03-10 @ 18:18 [FOOD]
|  Total expenses: $75.0
|
|  -----------------------------------------------------------------------
|
|  Enter command:
```

**Note**: As long as the input `sortExpenseByDate` is correct,
any trailing characters with a space after it will still call the function.

I.e.,`sortExpenseByDate random characters` will still work.

[back to Contents](#table-of-contents)

---

### Sorting an income by date: `sortIncomeByDate`
Format: `sortIncomeByDate`
Shows a list of all income in a chronological order.

Example of usage: `sortIncomeByDate`

Example output:

```
|  -----------------------------------------------------------------------
|
|  Enter command: sortIncomeByDate
|  1. $1000.00 earned on grab - 2023-03-15 @ 15:15 [SALARY]
|  2. $400.00 earned on salary - 2023-03-12 @ 15:01 [FOOD]
|  3. $3000.00 earned on stocks - 2023-02-11 @ 01:01 [INVESTMENT]
|  Total income: $4400.00
|
|  -----------------------------------------------------------------------
|
|  Enter command:
```

**Note**: As long as the input `sortIncomeByDate` is correct,
any trailing characters with a space after it will still call the function.

I.e.,`sortIncomeByDate random characters` will still work.

[back to Contents](#table-of-contents)

---

### Deleting an expense: `deleteExpense`
Format: `deleteExpense <index>`
* Deletes the expense at the specified `index`.
* The index refers to the index number shown in the displayed expense list.
* The index must be a positive integer 1, 2, 3, ...

Example of usage:
`deleteExpense 2`

Example output:

```
|  -----------------------------------------------------------------------
|
|  Enter command: deleteExpense 2
|  Successfully deleted.
|
|  -----------------------------------------------------------------------
|
|  Enter command:
```

**Note**: Please do not leave the index empty.

[back to Contents](#table-of-contents)

---

### Deleting an income: `deleteIncome`
Format: `deleteIncome <index>`
* Deletes the income at the specified `index`.
* The index refers to the index number shown in the displayed income list.
* The index must be a positive integer 1, 2, 3, ...

Example of usage:
`deleteIncome 2`

Example output:

```
|  -----------------------------------------------------------------------
|
|  Enter command: deleteIncome 2
|  Successfully deleted.
|
|  -----------------------------------------------------------------------
|
|  Enter command:
```

**Note**: Please do not leave the index empty.

[back to Contents](#table-of-contents)

---

### Set budget: `setBudget`
Format:  `setBudget <amount> [t/ date]`
* The `amount` should be double up to **2dp**. If amount has more than 2dp, it will round off to nearest 2dp.
  * The largest amount that can be entered is `9999999999.99`. 
  * The amount entered must be greater `0`.
* The `date` should follow `YYYY/MM` format.
  * The oldest date that can be entered is 2000/01.
  * The furthest date that can be entered is 9999/12.
* The date parameter is **optional**. 
If you add this optional parameter, the budget will only take expenses within the indicated month into consideration.
If the optional date parameter is not given, it will set the budget for the current month.

Example of usage:
`setBudget 2000 t/ 2023/05` sets the budget of 2023/05 at 2000.
`setBudget 2000` sets the budget for the current month.

Example output with optional time flag:

```
|  -----------------------------------------------------------------------
|
|  Enter command: setBudget 2000 t/ 2023/05
|  You have successfully set $2000.00 as your budget for 2023/MAY.
|
|  -----------------------------------------------------------------------
|
|  Enter command:
```

Example output without optional time flag:

```
|  -----------------------------------------------------------------------
|
|  Enter command: setBudget 2000
|  You have successfully set $2000.00 as your budget for 2023/APRIL.
|
|  -----------------------------------------------------------------------
|
|  Enter command:
```

**Note**: Please do not leave the description of amount and / or description of optional time flag empty.

[back to Contents](#table-of-contents)

---

### View budget: `viewBudget`
Format: `viewBudget [t/ date]`
* The `date` should follow `YYYY/MM` format.
  * The oldest date that can be entered is 2000/01.
  * The furthest date that can be entered is 9999/12.
* The date parameter is **optional**.
If you add this optional parameter, it shows the budget of the indicated month.
If the optional date parameter is not given, it will show the budget for the current month.

Example of usage:
`viewBudget t/ 2023/05` shows the budget in 2023/05.
`viewBudget` shows the budget in the current month.

Example output with optional time flag:

```
|  -----------------------------------------------------------------------
|
|  Enter command: viewBudget t/ 2023/05
|  You have set your budget as $2000.00 for 2023/MAY.
|  The amount of budget left is $2000.00
|
|  -----------------------------------------------------------------------
|
|  Enter command:
```

Example output without optional time flag:

```
|  -----------------------------------------------------------------------
|
|  Enter command: viewBudget
|  You have set your budget as $2000.00 for 2023/APRIL.
|  The amount of budget left is $2000.00
|
|  -----------------------------------------------------------------------
|
|  Enter command:
```

**Note**: Please do not leave the description of time flag empty.

[back to Contents](#table-of-contents)

---

### Help command: `help`
Format: `help` Shows all the help messages for all features.

**Help message will appear when invalid command is entered.**

**This is to help users maximize their benefit from the application by utilizing it as designed.**

Example of usage: `help`

Example output:

```
|  -----------------------------------------------------------------------
|
|  Enter command: help
|  addExpense: add expense to the expense list.
|  Parameters: a/ <amount> d/ <description> t/ <time in YYYY MM DD HH mm> c/ <category>
|  Valid categories are: FOOD, SHOPPING, GROCERIES, TRANSPORTATION, ENTERTAINMENT, TRAVEL, SALARY, INVESTMENT, and OTHERS
|  Example: addExpense a/ 4.5 d/ lunch t/ 2023 03 22 20 12 c/ FOOD
|
|  addIncome: add income to the income list.
|  Parameters: a/ <amount> d/ <description> t/ <time in YYYY MM DD HH mm> c/ <category>
|  Valid categories are: FOOD, SHOPPING, GROCERIES, TRANSPORTATION, ENTERTAINMENT, TRAVEL, SALARY, INVESTMENT, and OTHERS
|  Example: addIncome a/ 3000 d/ salary t/ 2023 03 10 10 10 c/ SALARY
|
|  deleteExpense: deletes an expense from the list
|  Parameter: <index>
|  Example: deleteExpense 1
|
|  deleteIncome: deletes an income from the list
|  Parameter: <index>
|  Example: deleteIncome 1
|
|  editExpense: edits the expense from the list.
|  Parameter: i/ <index> t/ <type> n/ <newEntry>
|  There are 4 types that can be changed, amount, info, time, category
|  Example: editExpense i/ 1 t/ amount n/ 5
|
|  editIncome: edits the income from the list.
|  Parameter: i/ <index> t/ <type> n/ <newEntry>
|  There are 4 types that can be changed, amount, info, time, category
|  Example: editIncome i/ 1 t/ info n/ stocks
|
|  listExpense: lists expenses made in the current month.
|  listExpense t/ <date in YYYY/MM>: : lists expenses made in the specified month
|  Optional Parameter: t/ <date in YYYY/MM>
|  Example: listExpense
|  Example: listExpense t/ 2023/03
|
|  listIncome: lists incomes made in the current month.
|  listIncome t/ <date in YYYY/MM>: : lists incomes made in the specified month
|  Optional Parameter: t/ <date in YYYY/MM>
|  Example: listIncome
|  Example: listIncome t/ 2023/03
|
|  setBudget: sets your budget for current month.
|  setBudget <amount> t/ <date in YYYY/MM>: sets your budget for specified month
|  Compulsory Parameter: <amount>
|  Optional Parameter: t/ <date in YYYY/MM>
|  Example: setBudget 500
|  Example: setBudget 500 t/ 2023/03
|
|  viewBudget: view your budget for the current month and how much of it is left remaining.
|  viewBudget t/ <date in YYYY/MM>: view your budget and how much of was left in the specified month
|  Optional Parameter: t/ <date in YYYY/MM>
|  Example: viewBudget
|  Example: viewBudget t/ 2023/03
|
|  sortExpenseByAmount: shows the expenses made, sorted by amount of expense
|  Example: sortExpenseByAmount
|
|  sortIncomeByAmount: shows the incomes made, sorted by amount of income
|  Example: sortIncomeByAmount
|
|  sortExpenseByDate: shows the expenses made, sorted by date of expense
|  Example: sortExpenseByDate
|
|  sortIncomeByDate: shows the incomes made, sorted by date of income
|  Example: sortIncomeByDate
|
|  help: shows all the commands for the program.
|  Example: help
|
|  exit: exits the program
|  Example: exit
|
|  -----------------------------------------------------------------------
|
|  Enter command:
```

[back to Contents](#table-of-contents)

---

### Exiting the program: `exit`
Format: `exit` Exits the program, and save the data.

Example of usage: `exit`

Example output:

```
|  -----------------------------------------------------------------------
|
|  Enter command: exit
|  Exiting program...
|
|  -----------------------------------------------------------------------
|  -----------------------------------------------------------------------
|
|     ___                       _              ___     _  _
|    / __|    ___     ___    __| |     o O O  | _ )   | || |   ___
|   | (_ |   / _ \   / _ \  / _` |    o       | _ \    \_, |  / -_)
|    \___|   \___/   \___/  \__,_|   TS__[O]  |___/   _|__/   \___|
|  _|"""""|_|"""""|_|"""""|_|"""""| {======|_|"""""|_| """"|_|"""""|
|  "`-0-0-'"`-0-0-'"`-0-0-'"`-0-0-'./o--000'"`-0-0-'"`-0-0-'"`-0-0-'
|
|  -----------------------------------------------------------------------
|  -----------------------------------------------------------------------
```

[back to Contents](#table-of-contents)

---

### Saving the data
BrokeMan data are saved in text files ExpenseData, IncomeData and BudgetData upon exiting the program.

**TRY NOT TO MAKE CHANGES TO THE .txt FILE GENERATED BY THE PROGRAM!**

**INCORRECT ROW FORMAT OF .txt FILE WILL BE IGNORED DURING THE POPULATION OF DATA AT THE START OF THE PROGRAM!**

[back to Contents](#table-of-contents)

---

### Get total expenses and income by category `[COMING SOON]`

### Wishlist `[COMING SOON]`

### Spending Advisor `[COMING SOON]`


## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous BrokeMan home folder.

[back to Contents](#table-of-contents)

## Command Summary

| Action          | Format, Examples                                                                                                                                                                                                                                                                |
|-----------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Add**         | `addExpense a/ <amount> d/ <description> t/ <time> c/ <category>` <br> eg., `addExpense a/ 4.5 d/ lunch t/ 2023 03 22 20 12 c/ FOOD` <br> `addIncome a/ <amount> d/ <description> t/ <time> c/ <category>` <br> eg., `addIncome a/ 400 d/ salary t/ 2023 03 12 15 01 c/ SALARY` |
| **List**        | `listExpense [t/ time]` <br> eg., `listIncome t/ 2023/03` <br> `listIncome [t/ time]` <br> eg., `listIncome`                                                                                                                                                                    |
| **Edit**        | `editExpense i/ <index> t/ <type> n/ <newEntry>` <br> eg., `editExpense i/ 1 t/ amount n/ 7` <br> `editIncome i/ <index> t/ <type> n/ <newEntry>` <br> e.g., `editIncome i/ 1 t/ info n/ pay`                                                                                   |
| **Sort**        | `sortExpenseByAmount` <br> `sortExpenseByTime` <br> `sortIncomeByAmount` <br> `sortExpenseByAmount`                                                                                                                                                                             |
| **Delete**      | `deleteExpense <index>` <br> eg., `deleteExpense 2` <br> `deleteIncome <index>` <br> eg., `deleteIncome 2`                                                                                                                                                                      |
| **Set Budget**  | `setBudget <amount> [t/ time]` <br> eg., `setBudget 2000 t/ 2023/04` <br> eg., `setBudget 4000`                                                                                                                                                                                 |
| **View Budget** | `viewBudget [t/ time]` <br> eg., `viewBudget t/ 2023/02`   <br> eg., `viewBudget`                                                                                                                                                                                               |
| **Exit**        | `exit`                                                                                                                                                                                                                                                                          |
| **Help**        | `help`                                                                                                                                                                                                                                                                          |

[back to Contents](#table-of-contents)
