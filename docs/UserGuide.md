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

- Re-prompts user if any field is not provided properly.
- VALUE will be stored as a positive float.
- Format for DATE will be dd-MM-yyyy.

Example of usage:
<br>`add expense /c meal /de breakfast @Technoedge /da 22-03-2023 /v 3.50`
<br>`add income /c salary /de salary /da 22-03-2023 /v 500`

### Listing all expenses and incomes: `list`

Shows a full list of both the expenses and incomes created by the user. Includes the index number, category, description, and value.
<br> Can view list of expenses/incomes separately as well.

Format:
<br> `list`
<br> `list expense`
<br> `list income`

### Updating an entry: `edit`

Edit an existing expense/income that is currently in the list.

Format:
<br> `edit expense /in INDEX [/c CATEGORY] [/de DESCRIPTION] [/da DATE] [/v VALUE]`
<br> `edit income /in INDEX [de/DESCRIPTION] [da/DATE] [v/VALUE]`
<br> where [] indicates optional fields.

Use case:

- Edits the item at the specified INDEX, where INDEX is a positive integer.
- At least one of the fields must be provided.
- User inputs desired fields to be edited.
- Existing values will be updated to the input values.

Example of usage:
`edit expense /in 1 /de Lunch @Technoedge /v 5.20`
`edit income /in 2 /da 12-12-2022 /v 100`

### Setting currency to be converted: `set currency`

Setting a currency that the user wants to convert to. So the converted amount will show in the list of expenses and incomes.

Format:
<br> `set currency /cr CURRENCY`

Use case:

- show the currency that users want to convert to.

Example of usage:
<br> `set currency /cr HKD`

### Unset currency to be converted: `unset currency`

Unset the currency that the user does not want to convert to.

Format:
<br> `unset currency /cr CURRENCY`

Use case:

- stop showing the currency that users do not want to convert to.

Example of usage:
<br> `unset currency /cr HKD`

### Finding an entry: `find`

Find an existing expense/income that is currently in the list.

- CATEGORY is specified as either `income` or `expense`

Format:
<br> `find /c CATEGORY /k DESCRIPTION`

Use case:

- Shows user the entry that they are looking for.

Example of usage:
<br> `find /c expense /k beef`
<br> `find /c income /k salary`

### Showing the balance: `balance`

Shows users the balance he has after calculating the difference of income and expense.

Format:
<br> `balance`

### Deleting an entry: `delete`

Remove an entry from the expense/income list.

Format:
<br> `delete expense /in INDEX`
<br> `delete income /in INDEX`

Use case:

- INDEX is a positive integer.
- Prompts user if index is out of bounds.

Example of usage:
<br>`delete expense /in 1`
<br>`delete income /in 2`

### Clear entire income list: `clear expense`

Clear the entire list of incomes.

Format:
<br> `clear income`

### Clear entire expense list: `clear income`

Clear the entire list of expenses.

Format:
<br> `clear expense`

### Clear entire both income and expense lists: `clear all`

Clear the entire list of incomes and expenses.

Format:
<br> `clear all`

### Set a target for balance of finances: `add target`

Allows users to set a target for their balance.

Format:
<br> `add target /v VALUE`

Use case:

- VALUE is more than or equal to zero

Example of usage:
<br> `add target 350`

### Finding balance of finances: `balance`

Shows users the balance after calculating the difference between income and expenses and informs users on whether they have reached their target.

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

- Add Expense: `add expense /c CATEGORY /de DESCRIPTION /da DATE /v VALUE`
- Add Income: `add income /de DESCRIPTION /da DATE /v VALUE`
- List Records: `list`
- List Expenses: `list expense`
- List Incomes: `list income`
- Edit income list: `edit income /i INDEX [/de DESCRIPTION] [/da DATE] [/v VALUE]`
- Edit expense list: `edit expense /i INDEX [/c CATEGORY] [/de DESCRIPTION] [/da DATE] [/v VALUE]`
- Find entry: `find income /c CATEGORY /k DESCRIPTION`
- Delete Expense: `delete expense /in INDEX`
- Delete Income: `delete income /in INDEX`
- Clear income list: `clear income`
- Clear expense list: `clear expense`
- Clear both income and expense lists: `clear all`
- Set Target: `add target /v Value`
- Show Target: `show target`
- Show Balance: `balance`
- Show help: `help`
