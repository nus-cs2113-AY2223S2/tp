# User Guide

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


## Features

### Adding an expense: `addExpense`
Format: `addExpense a/ <amount> d/ <description> t/ <time> c/ <category>`
Adds a new expense to the list of expenses.

* The `amount` should be double.
* The `description` can be in a natural language format.
* The `time` should follow `YYYY MM HH mm` format.  
* The `category` should be one of the tags in the category list.

Example of usage: 

`addExpense a/ 4.5 d/ lunch t/ 2023 3 22 20 12 c/ FOOD`

### Adding an income: `addIncome`
Format: `addIncome a/ <amount> d/ <description> t/ <time> c/ <category>`
Adds a new expense to the list of expenses.

* The `amount` should be double.
* The `description` can be in a natural language format.
* The `time` should follow `YYYY MM HH mm` format.
* The `category` should be one of the categories in the category list.

Example of usage:

`addIncome a/ 400 d/ salary t/ 2023 03 12 15 01 c/ SALARY`

### Listing all expenses: `listExpense'
Format: `listExpense`
Shows a list of all expenses in the list of expense.

### Listing all income: `listIncome'
Format: `listIncome`
Shows a list of all income in the list of income.

### Editing an expense: `editExpense'
Format: `editExpense i/ <index> t/ <type> n/ <newEntry>`
* Edits the expense at the specified `index`. 
The index refers to the index number shown in the displayed expense list.
The index must be a positive integer 1, 2, 3, …​
* The `type` can be amount, description, time, or category. 
* You can only edit one type at a time.
* Existing values will be updated to the input values.

### Editing an income: `editIncome'
Format: `editIncome i/ <index> t/ <type> n/ <newEntry>`
* Edits the expense at the specified `index`.
  The index refers to the index number shown in the displayed expense list.
  The index must be a positive integer 1, 2, 3, …​
* The `type` can be amount, description, time, or category.
* You can only edit one type at a time.
* Existing values will be updated to the input values.

### Sorting an income by amount: `sortExpenseByAmount`
Format: `sortExpenseByAmount`
Shows a list of all expenses in the order of higher amount to lower amount.

### Sorting an income by amount: `sortIncomeByAmount`
Format: `sortIncomeByAmount`
Shows a list of all income in the order of higher amount to lower amount.

### Sorting an income by amount: `sortExpenseByDate`
Format: `sortExpenseByDate`
Shows a list of all expenses in a chronological order.

### Sorting an income by amount: `sortIncomeByCommand`
Format: `sortIncomeByCommand`
Shows a list of all income in a chronological order.

### Deleting an expense: `deleteExpense'
Format: `deleteExpense <index>`
* Deletes the expense at the specified `index`.
* The index refers to the index number shown in the displayed expense list.
* The index must be a positive integer 1, 2, 3, ...

Example of usage:

`deleteExpense 2` deletes the 2nd expense in the list of expense.

### Deleting an income: `deleteIncome'
Format: `deleteIncome <index>`
* Deletes the expense at the specified `index`.
* The index refers to the index number shown in the displayed income list.
* The index must be a positive integer 1, 2, 3, ...

Example of usage:

`deleteIncome 2` deletes the 2nd income in the list of income.

### Set budget: `setBudget`
Format:  `setBudget <amount> [t/ time]`
* The `amount` should be double.
* The `time` should follow `YYYY/MM` format.
* The time parameter is optional. 
If you add this optional parameter, the budget will only take expenses within the indicated month into consideration.

Example of usage:

`setBudget 2000 t/ 2023/04` sets the budget of 2023/04 at 2000.

### View budget: 'viewBudget'
Format: `viewBudget [t/ time]`
* The `time` should follow `YYYY/MM` format.
* The time parameter is optional.
If you add this optional parameter, it shows the budget of the indicated month.

Example of usage:

`viewBudget t/ 2023/02` shows the budget in 2023/02.

### Exiting the program: `exit`
Exits the program.
Format: `exit`

### Saving the data
BrokeMan data are saved in text files ExpenseData and IncomeData automatically after any command that changes the data.
There is no need to save manually.

### Get total expenses and income by category `[COMING SOON]`

### Wishlist `[COMING SOON]`




## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous BrokeMan home folder.

## Command Summary

Action | Format, Examples
--------|------------------
**Add** | `addExpense a/ <amount> d/ <description> t/ <time> c/ <category>` <br> e.g., `addExpense a/ 4.5 d/ lunch t/ 2023 3 22 20 12 c/ FOOD` <br> `addIncome a/ <amount> d/ <description> t/ <time> c/ <category>` <br> eg., `addIncome a/ 400 d/ salary t/ 2023 03 12 15 01 c/ SALARY`
**List** | `listExpense` <br> `listIncome`
**Edit** | `editExpense i/ <index> t/ <type> n/ <newEntry>` <br> e.g., `editExpense i/ 1 t/ amount n/ 7` <br> `editIncome i/ <index> t/ <type> n/ <newEntry>` <br> e.g., `editIncome i/ 1 t/ info n/ pay`
**Sort** | `sortExpenseByAmount` <br> `sortExpenseByTime` <br> `sortIncomeByAmount` <br> `sortExpenseByAmount`
**Delete** | `deleteExpense <index>` <br> e.g., `deleteExpense 2` <br> `deleteIncome <index>` <br> e.g., `deleteIncome 2`
**Set Budget** | `setBudget <amount> [t/ time]` <br> e.g., `setBudget 2000 t/ 2023/04` 
**View Budget** | `viewBudget [t/ time]` <br> e.g., `viewBudget t/ 2023/02`
**Exit** | `exit`

