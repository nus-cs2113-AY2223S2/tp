# User Guide

## Table of Contents

1. [Introduction](#introduction)
2. [Quick Start](#quick-start)
3. [Features](#features)
   * [Adding an expense](#adding-an-expense--addexpense)
   * [Adding an income](#adding-an-income--addincome)
   * [Listing all expenses](#listing-all-expenses--listexpense)
   * [Listing all income](#listing-all-income--listincome)
   * [Editing an expense](#editing-an-expense--editexpense)
   * [Editing an income](#editing-an-income--editincome)
   * [Sorting an expense by amount](#sorting-an-expense-by-amount--sortexpensebyamount)
   * [Sorting an income by amount](#sorting-an-income-by-amount--sortincomebyamount)
   * [Sorting an expense by date](#sorting-an-expense-by-date--sortexpensebydate)
   * [Sorting an income by date](#sorting-an-income-by-date--sortincomebydate)
   * [Deleting an expense](#deleting-an-expense--deleteexpense)
   * [Deleting an income](#deleting-an-income--deleteincome)
   * [Set budget](#set-budget--setbudget)
   * [View budget](#view-budget--viewbudget)
   * [Help command](#help-command--help)
   * [Exiting the program](#exiting-the-program--exit)
   * [Saving the data](#saving-the-data)
4. [FAQ](#faq)
5. [Command Summary](#command-summary)


## Introduction


BrokeMan is a desktop app for managing expenses and income, optimized for use via a Command Line Interface(CLI).
If you can type fast, BrokeMan can get your expenses and income management tasks done faster than traditional GUI apps.

## Quick Start


1. Ensure that you have Java 11 or above installed.
2. Download the latest version of `BrokeMan` from [here](http://link.to/duke).
3. Copy the file to the folder you want to use as the home folder for your BrokeMan.
4. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar brokeman.jar` command to run the application.

    A welcoming message should appear.
5. Type the command in the command box and press Enter to execute it.
   Some example commands you can try:
6. Refer to the Features below for details of each command.

---

## Features

### Adding an expense: `addExpense`
Format: `addExpense a/ <amount> d/ <description> t/ <time> c/ <category>`
Adds a new expense to the list of expenses.

* The `amount` should be double.
* The `description` can be in a natural language format.
* The `time` should follow `YYYY MM HH mm` format.  
* The `category` should be one of the tags in the category list.

**Category list**: FOOD, SHOPPING, GROCERIES, TRANSPORTATION, ENTERTAINMENT, TRAVEL, SALARY, INVESTMENT, and OTHERS

Example of usage:
`addExpense a/ 4.5 d/ lunch t/ 2023 3 22 20 12 c/ FOOD`

**Note**: Please do not leave the description of tags empty.

---

### Adding an income: `addIncome`
Format: `addIncome a/ <amount> d/ <description> t/ <time> c/ <category>`
Adds a new expense to the list of expenses.

* The `amount` should be double.
* The `description` can be in a natural language format.
* The `time` should follow `YYYY MM HH mm` format.
* The `category` should be one of the categories in the category list mentioned in `addExpense` feature.

Example of usage:
`addIncome a/ 400 d/ salary t/ 2023 03 12 15 01 c/ SALARY`

**Note**: Please do not leave the description of tags empty.

---

### Listing all expenses: `listExpense`
Format: `listExpense [t/ time]`
Shows a list of all expenses in the list of expense.

- The `time` should follow `YYYY/MM` format
- The time parameter is optional. If you add this optional parameter,
it will show a list of all the expenses incurred in the month specified.

Example of usage: `listExpense t/ 2023/03` Shows a list of all expenses incurred in the month 2023 March

---

### Listing all income: `listIncome`
Format: `listIncome [t/ time]`
Shows a list of all income in the list of income.

- The `time` should follow `YYYY/MM` format
- The time parameter is optional. If you add this optional parameter,
it will show a list of all income made in the specified month.

Example of usage: `listIncome` Shows a list of all income made across the entire time period.

---

### Editing an expense: `editExpense`
Format: `editExpense i/ <index> t/ <type> n/ <newEntry>`
* Edits the expense at the specified `index`. 
The index refers to the index number shown in the displayed expense list.
The index must be a positive integer 1, 2, 3, …​
* The `type` can be: amount, description, time, or category.
  * amount (of type **double**): edits the expense
  * info (of type **String**): edits the description of expense
  * time (in the format **YYYY MM DD HH mm**): edits the time at which user spent that expense
  * category (should be in the category list mentioned in `addExpense` feature): edits the category of that expense
* You can only edit one type at a time.
* Existing values will be updated to the input values.

Example of usage:
`editExpense i/ 1 t/ amount n/ 5`

**Note**: Please do not leave the description of tags empty.

---

### Editing an income: `editIncome`
Format: `editIncome i/ <index> t/ <type> n/ <newEntry>`
* Edits the expense at the specified `index`.
  The index refers to the index number shown in the displayed expense list.
  The index must be a positive integer 1, 2, 3, …​
* The `type` can be: amount, info, time, or category.
  * amount (of type **double**): edits the income
  * info (of type **String**): edits the description of income
  * time (in the format **YYYY MM DD HH mm**): edits the time at which income is earned
  * category (should be in the category list mentioned in `addExpense` feature): edits the category of that income
* You can only edit one type at a time.
* Existing values will be updated to the input values.

Example of usage: `editIncome i/ 2 t/ category n/ FOOD`

**Note**: Please do not leave the description of tags empty.

---

### Sorting an expense by amount: `sortExpenseByAmount`
Format: `sortExpenseByAmount`
Shows a list of all expenses in the order of higher amount to lower amount.

Example of usage: `sortExpenseByAmount`

---

### Sorting an income by amount: `sortIncomeByAmount`
Format: `sortIncomeByAmount`
Shows a list of all income in the order of higher amount to lower amount.

Example of usage: `sortIncomeByAmount`

---

### Sorting an expense by date: `sortExpenseByDate`
Format: `sortExpenseByDate`
Shows a list of all expenses in a chronological order.

Example of usage: `sortExpenseByDate`

---

### Sorting an income by date: `sortIncomeByDate`
Format: `sortIncomeByDate`
Shows a list of all income in a chronological order.

Example of usage: `sortIncomeByDate`

---

### Deleting an expense: `deleteExpense`
Format: `deleteExpense <index>`
* Deletes the expense at the specified `index`.
* The index refers to the index number shown in the displayed expense list.
* The index must be a positive integer 1, 2, 3, ...

Example of usage:
`deleteExpense 2`

**Note**: Please do not leave the index empty.

---

### Deleting an income: `deleteIncome`
Format: `deleteIncome <index>`
* Deletes the expense at the specified `index`.
* The index refers to the index number shown in the displayed income list.
* The index must be a positive integer 1, 2, 3, ...

Example of usage:
`deleteIncome 2`

**Note**: Please do not leave the index empty.

---

### Set budget: `setBudget`
Format:  `setBudget <amount> [t/ time]`
* The `amount` should be double.
* The `time` should follow `YYYY/MM` format.
* The time parameter is optional. 
If you add this optional parameter, the budget will only take expenses within the indicated month into consideration.

Example of usage:
`setBudget 2000 t/ 2023/04` sets the budget of 2023/04 at 2000.

**Note**: Please do not leave the description of amount and / or description of optional time tag empty.

---

### View budget: `viewBudget`
Format: `viewBudget [t/ time]`
* The `time` should follow `YYYY/MM` format.
* The time parameter is optional.
If you add this optional parameter, it shows the budget of the indicated month.

Example of usage:
`viewBudget t/ 2023/02` shows the budget in 2023/02.

**Note**: Please do not leave the description of time tag empty.

---

### Help command: `help`
Format: `help` Shows all the help messages for all features.

Example of usage: `help`

---

### Exiting the program: `exit`
Format: `exit` Exits the program.

Example of usage: `exit`

---

### Saving the data
BrokeMan data are saved in text files ExpenseData and IncomeData automatically after any command that changes the data.
There is no need to save manually.

---

### Get total expenses and income by category `[COMING SOON]`

### Wishlist `[COMING SOON]`




## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous BrokeMan home folder.

## Command Summary

| Action          | Format, Examples                                                                                                                                                                                                                                                                |
|-----------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Add**         | `addExpense a/ <amount> d/ <description> t/ <time> c/ <category>` <br> e.g., `addExpense a/ 4.5 d/ lunch t/ 2023 3 22 20 12 c/ FOOD` <br> `addIncome a/ <amount> d/ <description> t/ <time> c/ <category>` <br> eg., `addIncome a/ 400 d/ salary t/ 2023 03 12 15 01 c/ SALARY` |
| **List**        | `listExpense [t/ time]` <br> eg. `listIncome t/ 2023/03` <br> `listIncome [t/ time]` <br> eg `listIncome`                                                                                                                                                                       |
| **Edit**        | `editExpense i/ <index> t/ <type> n/ <newEntry>` <br> e.g., `editExpense i/ 1 t/ amount n/ 7` <br> `editIncome i/ <index> t/ <type> n/ <newEntry>` <br> e.g., `editIncome i/ 1 t/ info n/ pay`                                                                                  |
| **Sort**        | `sortExpenseByAmount` <br> `sortExpenseByTime` <br> `sortIncomeByAmount` <br> `sortExpenseByAmount`                                                                                                                                                                             |
| **Delete**      | `deleteExpense <index>` <br> e.g., `deleteExpense 2` <br> `deleteIncome <index>` <br> e.g., `deleteIncome 2`                                                                                                                                                                    |
| **Set Budget**  | `setBudget <amount> [t/ time]` <br> e.g., `setBudget 2000 t/ 2023/04`                                                                                                                                                                                                           |
| **View Budget** | `viewBudget [t/ time]` <br> e.g., `viewBudget t/ 2023/02`                                                                                                                                                                                                                       |
|  **Exit**       | `exit`                                                                                                                                                                                                                                                                          |

