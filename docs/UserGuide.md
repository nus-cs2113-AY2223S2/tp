# User Guide

* Table of Contents
{:toc}




## Introduction

ChChing is a Command Line Interface (CLI) desktop app for tracking spending and managing finances, with currency conversion capabilities. If you are someone who needs a simple interface to get a better hold of your finances, this app is for you!

## Quick Start

1. Ensure that you have Java 11 or above installed.
2. Download the latest version of `ChChing` from [here](https://github.com/AY2223S2-CS2113-T12-1/tp/releases).
3. Copy downloaded `tp.jar` file to the desired home folder of choice.
4. Open a command terminal and `cd` into the folder mentioned in step 3.
5. Type in the following to run the application:

```
java -jar tp.jar
```

6. If successful, the following greetings should appear:
<br> For first time users:
```
Unfortunately, income list file can't be found. I'll make a new one!
Unfortunately, expense list can't be found. I'll make a new one!
    ____________________________________________________________
    Hello! I'm ChChing.
    What can I do for you?
    ____________________________________________________________
```
<br> For current users:

```
    ____________________________________________________________
    Hello! I'm ChChing.
    What can I do for you?
    ____________________________________________________________
```

7. Type in desired command to start using the program! List of commands are listed below.
8. Do not edit any program-created saved files (.txt files, etc.), else the program may crash.

## Features

### Adding an entry: `add`

Creates a new entry for expenses or income, with respective input formats.
<br> Income and Expense values must be less than 1000000.
<br> Income and Expense values must be up to 2 decimal places or less.

Format:
<br>`add expense /c CATEGORY /de DESCRIPTION /da DATE /v VALUE`
<br>`add income /de DESCRIPTION /da DATE /v VALUE`

Use case:

- Re-prompts user if any field is not provided properly.
- CATEGORY has a maximum char limit of 30.
- DESCRIPTION has a maximum char limit of 99.
- VALUE will be stored as a positive double up to 2 decimal places.
- VALUE will not accept doubles with more than 2 decimal places.
- VALUE will be recorded as SGD, regardless of the currency set.
- Format for DATE will be DD-MM-YYYY.
- DATE needs to be a valid date, and it cannot be a date in the future.

Example of usage:
<br>`add expense /c meal /de breakfast @Technoedge /da 22-03-2023 /v 3.50`
<br>`add income /de salary /da 22-03-2023 /v 500`

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
<br> `edit expense /in INDEX [c/ CATEGORY] [de/ DESCRIPTION] [da/ DATE] [v/ VALUE]`
<br> `edit income /in INDEX [de/DESCRIPTION] [da/DATE] [v/VALUE]`
<br> where [] indicates optional fields.

Use case:

- Edits the item at the specified INDEX, where INDEX is a positive integer.
- At least one of the optional fields must be provided.
- User inputs desired fields to be edited.
- Existing values will be updated to the input values.

Example of usage:
`edit expense /in 1 /de Lunch @Technoedge /v 5.20`
<br> `edit income /in 2 /da 12-12-2022 /v 100`

### Setting currency to be converted: `set currency`

Setting a currency that the user wants to convert to. So the converted amount will show in the list of expenses and incomes.

Format:
<br> `set currency /cr CURRENCY`

Use case:
- show the currency that users want to convert to.
- List of supported currencies:
  * HKD - Hong Kong Dollar
  * PHP - Philippine Peso
  * IDR - Indonesian Rupiah
  * MYR - Malaysian Ringgit
  * VND - Vietnamese Dong

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
<br> The program will search for the keywords in your entries for category and description
<br> The program will search for exact date in entries for date field
<br> Vague keywords will render less specific results.

- TYPE is specified as either `income` or `expense`

Format:
<br> `find /t TYPE [/c CATEGORY] [/de DESCRIPTION] [/da DATE]`
<br> fields with [] are optional fields, but at least 1 must be not empty for search to work

Use case:

- Shows user the entry that they are looking for.

Example of usage:
<br> `find /t expense /c food /de beef `
<br> `find /t income /de salary /da 03-03-2023 `

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

### Clear entire income list: `clear income`

Clear the entire list of incomes.

Format:
<br> `clear income`

### Clear entire expense list: `clear expense`

Clear the entire list of expenses.

Format:
<br> `clear expense`

### Clear entire both income and expense lists: `clear all`

Clear the entire list of incomes and expenses.

Format:
<br> `clear all`

### Set a target for balance of finances: `set target`

Allows users to set a target for their balance.

Format:
<br> `set target /v VALUE`

Use case:
- VALUE will be recorded as SGD, regardless of the currency set.
- VALUE ranges from negative to positive.
- Target must be within the range of -9999999.99 to 9999999.99.
- Target will be set to 2 decimal places.
- Default target is 0.00

Example of usage:
<br> `set target /v 350`

### See target set: `show target`

Allows users to see the target they have set.

Format:
<br> `show target`

### Clear Target Set: `clear target`

Allow users to clear the target they have set.

Format:
<br> `clear target`

Use case:
- A target must have been set in the first place.

### Finding balance of finances: `balance`

Shows users the balance after calculating the difference between income and expenses and informs users on whether they have reached their target.

Format:
<br> `balance`

### View help: `help`

Shows basic commands executable by the program and the use format for the commands

Format:
<br> `help`

### Exit: `exit`

Exit the program

Format:
<br> `exit`

### Saving and loading of data

Record entries are saved in `data/chching.json` file. This file is updated upon exit.
When reading from the file, entries for income and expenses are stored in their respective arrayList.
The data file is not to be edited manually.

### Other Notes
* Should additional fields that is not required be added to the input, the program will ignore it.
<br> e.g. field "a" with value "1" will be ignored for `add expense /c meal /de breakfast @Technoedge /da 22-03-2023 /v 3.50 /a 1`, and `exit /de 1` will still successfully exit the program.
* ChChing will not allow duplicate fields within the same input.
<br> e.g. `add expense /c meal /c meal /de breakfast @Technoedge /da 22-03-2023 /v 3.50` will not be allowed since it has duplicate category fields.
* ChChing allows for the fields' order to be changed.
<br> e.g. `add expense /de breakfast @Technoedge /c meal /da 22-03-2023 /v 3.50` will add the expense successfully.
* ChChing allows command and argument fields to be case-insensitive. However, the values of the fields would be stored according to the case of the input.
<br> e.g. `EDIT INCOME /IN 1 /DE DINNER` would successfully edit the description of the first income recorded to `DINNER`.
* ChChing will allow for duplicate entries to be added. No warning would be raised.
* ChChing will not allow for the user to use '/', unless it is used to specify the field.
<br> e.g. `add expense /c meal /de breakfast / lunch /da 22-03-2023 /v 3.50` will be allowed.
* For values that require double/float, ChChing will only allow for 2 decimal places. Furthermore, ChChing will not accept values that does not have a number before the decimal point.
<br> e.g. `.01` and `0.001` is not taken as a valid value, but `0.01` is.

## Command Summary (Alphabetical Order)

| **Command**        | **Format & Examples**                                                                                                                      |
|--------------------|--------------------------------------------------------------------------------------------------------------------------------------------|
| Add Expense        | `add expense /c CATEGORY /de DESCRIPTION /da DATE /v VALUE`<br>e.g. `add expense /c meal /de breakfast @Technoedge /da 22-03-2023 /v 3.50` |
| Add Income         | `add income /de DESCRIPTION /da DATE /v VALUE`<br>e.g. `add income /de salary /da 22-03-2023 /v 500`                                       |
| Clear All Lists    | `clear all`                                                                                                                                |
| Clear Expense List | `clear expense`                                                                                                                            |
| Clear Income List  | `clear income`                                                                                                                             |
| Clear Target       | `clear target`                                                                                                                             |
| Delete Expense     | `delete expense /in INDEX`<br>e.g. `delete expense /in 1`                                                                                  |
| Delete Income      | `delete income /in INDEX`<br>e.g. `delete income /in 2`                                                                                    |
| Edit Expense       | `edit expense /in INDEX [/c CATEGORY] [/de DESCRIPTION] [/da DATE] [/v VALUE]`<br>e.g. `edit expense /in 1 /de Lunch @Technoedge /v 5.20`  |
| Edit Income        | `edit income /in INDEX [/de DESCRIPTION] [/da DATE] [/v VALUE]`<br>e.g. `edit income /in 2 /da 12-12-2022 /v 100`                          |
| Exit               | `exit`                                                                                                                                     |
| Find               | `find /c CATEGORY /k KEYWORD`<br>e.g. `find /c expense /k beef`<br>e.g. `find /c income /k salary`                                         |
| Help               | `help`                                                                                                                                     |
| List All Lists     | `list`                                                                                                                                     |
| List Expense List  | `list expense`                                                                                                                             |
| List Income List   | `list income`                                                                                                                              |
| Set Currency       | `set currency /cr CURRENCY`<br>e.g. `set currency /cr HKD`                                                                                 |
| Set Target         | `set target /v VALUE`<br>e.g. `set target /v 350`                                                                                          |
| Show Target        | `show target`                                                                                                                              |
| Show Balance       | `balance`                                                                                                                                  |
| Unset Currency     | `unset currency /cr CURRENCY`<br>e.g. `unset currency /cr HKD`                                                                             |
