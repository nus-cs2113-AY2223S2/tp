# MyLedger - User Guide

<p align="center">
    <img src="team/images/MyLedger.jpeg" width="30%">
</p>

<!-- TOC -->

- [MyLedger - User Guide](#myledger---user-guide)
  - [Introduction](#introduction)
  - [Quick Start](#quick-start)
  - [Managing Transactions](#managing-transactions)
    - [4.1. Adding an expenditure](#41-adding-an-expenditure)
    - [4.2. Adding a lend/borrow record](#42-adding-a-lendborrow-record)
    - [4.3. Editing an Expenditure](#43-editing-an-expenditure)
    - [4.4. Editing a Lend/Borrow record](#44-editing-a-lendborrow-record)
    - [4.5. Deleting an expenditure record](#45-deleting-an-expenditure-record)
    - [4.6. Duplicating an expenditure record](#46-duplicating-an-expenditure-record)
    - [4.7. Marking an accommodation or tuition record](#47-marking-an-accommodation-or-tuition-record)
    - [4.8. Unmarking an accommodation or tuition expenditure record](#48-unmarking-an-accommodation-or-tuition-expenditure-record)
    - [4.9. Setting a budget](#49-setting-a-budget)
    - [4.10. Checking expenditures against the set budget](#410-checking-expenditures-against-the-set-budget)
    - [4.11. List out and display the expenditure list](#411-list-out-and-display-the-expenditure-list)
    - [4.12. Finding expenditure records by keyword](#412-finding-expenditure-records-by-keyword)
    - [4.13. Sorting the expenditure list](#413-sorting-the-expenditure-list)
    - [4.14. View the expenditure list by expenditure category or type](#414-view-the-expenditure-list-by-expenditure-category-or-type)
    - [4.15. View the expenditure list by date](#415-view-the-expenditure-list-by-date)
    - [4.16. Currency rates](#416-currency-rates)
  - [FAQ](#faq)
  - [Command Summary](#command-summary)
  <!-- TOC -->

<div style="page-break-after: always;"></div>

## Introduction

MyLedger is a desktop app for managing finances, designed for university students in the National University of Singapore (NUS), studying locally or on exchange. It is optimized for use via a Command Line Interface (CLI). For students that can type fast, MyLedger can help them record and classify their transactions into categories. Students can expect to get an overview of their transactions at a glance,
which helps them to monitor their budget and expenses more effciently.

All currency amounts used in MyLedger is based of the **Singapore Dollar (SGD)**.

## Quick Start

1. Ensure that you have Java 11 or above installed.
2. Download the latest version of `MyLedger` from [here](https://github.com/AY2223S2-CS2113-T14-3/tp/releases/tag/v2.0).
3. Open the command terminal on your device.
4. Navigate to the folder in command terminal and run the command `java -jar [filename].jar`
5. Alternatively, double click on the JAR file to run the app.

## Managing Transactions

### 4.1. Adding an expenditure

Adds an expenditure to the record

Format: `EXPENDITURE_CATEGORY d/DATE a/AMOUNT p/DESCRIPTION`

| Parameter              | Description                                                                                                                            |
| ---------------------- | -------------------------------------------------------------------------------------------------------------------------------------- |
| `EXPENDITURE_CATEGORY` | The type of transaction. There are 7 types, `Academic`, `Accomodation`, `Entertainment` , `Food` , `Transport` , `Tuition` and `Other` |
| `AMOUNT`               | The amount of the transaction. It is a positive whole number ranging from 1 to 10000000 (Ten Million).                                 |
| `DATE`                 | The date when the transaction took place on. It must be in yyyy-MM-dd format, e.g. 2023-02-02                                          |
| `DESCRIPTION`          | More information regarding the transaction. Special symbols and length are not restricted.                                             |

**Important Information:**

- All parameters listed must be present in this command.
- All parameters must not be empty.
- The input date format must be in yyyy-MM-DD format
- The input year cannot be earlier than 2000, and the maximum year that can be set is 9999.
  **Examples:**

- `academic d/2023-02-02 a/25.10 p/NUS` <br>
- `other d/2000-01-31 a/26 p/Eating lunch`

**Expected Output:**

Adding an Academic Expenditure

Input:

```
academic d/2023-02-02 a/25.10 p/NUS
```

Output:

```
Added academic expenditure: [Academic] || Date: 2 Feb 2023
|| Value: 25.1 || Description: NUS
```

The output should be displayed on a single line. However, output in UG has been displayed across 2 lines to format the output in PDF form.

Adding a Other Expenditure

Input:

```
other d/2000-01-31 a/26 p/Eating lunch
```

Output:

```
Added other expenditure: [Other] || Date: 31 Jan 2000
|| Value: 26.0 || Description: Eating lunch
```

The output should be displayed on a single line. However, output in UG has been displayed across 2 lines to format the output in PDF form.

<div style="page-break-after: always;"></div>
### 4.2. Adding a lend/borrow record

Adds a lending or borrowing transaction to the record

Format: `CATEGORY d/DATE n/NAME a/AMOUNT b/DEADLINE p/DESCRIPTION`

| Parameter     | Description                                                                                             |
| ------------- | ------------------------------------------------------------------------------------------------------- |
| `CATEGORY`    | The category of record of `lend` or `borrow`. It should either be `lend` or `borrow`.                   |
| `DATE`        | The date when the transaction took place on. It must be in yyyy-MM-dd format, e.g. 2023-02-02.          |
| `NAME`        | The name of the other party involved in the transaction. Input name should not have a slash.            |
| `AMOUNT`      | The amount of the transaction. It can a positive whole number ranging from 1 to 10000000 (Ten Million). |
| `DEADLINE`    | The date when the transaction is dued. It must be in yyyy-MM-dd format, e.g. 2023-02-02.                |
| `DESCRIPTION` | More information regarding the transaction. Special symbols and length are not restricted.              |

**Important Information:**

- All parameters must be present in this command.
- All parameters must not be empty.
- The input date format must be in yyyy-MM-DD format.
- The input year cannot be earlier than 2000, and the maximum year that can be set is 9999.
- Our application does not support input names with a slash '/'.
- Return date should be equal to or later than the present date.
- Borrow date must be before the return date.

**Examples:**

- `lend d/2022-02-02 n/Akshay Narayan a/25.10 b/2024-07-14 p/CS2113`

**Expected Output:**

Adding a lend transaction

Input:

```
lend d/2022-02-02 n/Akshay Narayan a/25.10 b/2024-07-14 p/CS2113
```

Output:

```
Added lend expenditure: [Lend] || Lent to: Akshay Narayan || Date: 2 Feb 2022
|| Value: 25.1 || Description: CS2113 || by: 14 Jul 2024
```

The output should be displayed on a single line. However, output in UG has been displayed across 2 lines to format the output in PDF form.

### 4.3. Editing an Expenditure

Edits an existing expenditure transaction in the record. After a successful edit, the updated list is shown.

**Format:** `edit INDEX d/DATE a/AMOUNT p/DESCRIPTION`

| Parameter     | Description                                                                                                                                                                  |
| ------------- | ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| `INDEX`       | A list entry value for the transaction. It is a positive whole number ranging from 1 to 1000000 and must be within the range of the number of items in the expenditure list. |
| `DATE`        | The date when the transaction took place on. It must be in yyyy-MM-dd format, e.g. 2023-02-02                                                                                |
| `AMOUNT`      | The amount of the transaction. It is a positive whole number ranging from 1 to 10000000 (Ten Million).                                                                       |
| `DESCRIPTION` | More information regarding the transaction. Special symbols and length are not restricted.                                                                                   |

**Important Information:**

- The fields provided are the same as adding an expenditure in [4.1](#41-adding-an-expenditure)
- Cannot change an expenditure type, e.g. cannot change an `Academic` expenditure to an `Accomodation` expenditure
- The input year cannot be earlier than 2000, and the maximum year that can be set is 9999.
- All parameters must be present in this command.

**Examples:**

- `edit 2 d/2023-02-15 a/20.00 p/Eat Food`

**Expected Output:**

Editing an expenditure

```
edit 2 d/2023-02-15 a/20.00 p/Eat Food
Edited! Here is the updated list:
The list is printed subsequent to the command but is omitted as
different users will have different expenditures
```

- Take note that the list that is printed subsequent to the command is omitted as different users will have different expenditures and hence different lists.

### 4.4. Editing a Lend/Borrow record

Edits an existing lend or borrow in the record. After a successful edit, the updated list is shown.

**Format:** `edit INDEX d/DATE n/NAME a/AMOUNT b/DEADLINE p/DESCRIPTION`

| Parameter     | Description                                                                                            |
| ------------- | ------------------------------------------------------------------------------------------------------ |
| `INDEX`       | A list entry value for the transaction. It is a positive whole number ranging from 1 to 1000000.       |
| `DATE`        | The date when the transaction took place on. It must be in yyyy-MM-dd format, e.g. 2023-02-02          |
| `NAME`        | The name of the other party involved in the transaction. Input name should not have a slash.           |
| `AMOUNT`      | The amount of the transaction. It is a positive whole number ranging from 1 to 10000000 (Ten Million). |
| `DEADLINE`    | The date when the transaction is dued. It must be in yyyy-MM-dd format, e.g. 2023-02-02.               |
| `DESCRIPTION` | More information regarding the transaction. Special symbols and length are not restricted.             |

**Important Information:**

- The fields provided are the same as adding an expenditure in [4.2](#42-adding-a-lendborrow-record)
- Cannot change a `lend` record to a `borrow` record or vice versa.
- Our application does not support input names with a slash '/'.
- The input year cannot be earlier than 2000, and the maximum year that can be set is 9999.
- Return date should be equal to or later than the present date.
- Borrow date must be before the return date.
- All parameters must be present in this command.

**Examples:**

- `edit 3 d/2022-02-02 n/Akshay Narayan a/25.10 b/2024-07-14 p/CS2113`

**Expected Output:**

Editing an expenditure

```
edit 3 d/2022-02-02 n/Akshay Narayan a/25.10 b/2024-07-14 p/CS2113
Edited! Here is the updated list:
The list is printed subsequent to the command but is omitted as
different users will have different expenditures
```

- Take note that the list that is printed subsequent to the command is omitted as different users will have different expenditures and hence different lists.

### 4.5. Deleting an expenditure record

Deletes an existing expenditure record from the expenditure list. After a successful delete, the updated list is shown.

**Format:** `delete INDEX`

| Parameter | Description                                                                                                                                                                  |
| --------- | ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| `INDEX`   | A list entry value for the transaction. It is a positive whole number ranging from 1 to 1000000 and must be within the range of the number of items in the expenditure list. |

**Important Information:**

- Providing special characters and indices out of the range of the number of expenditures in the expenditure list are invalid.

Input:

```
delete 1
```

Output:

```
Entry has been deleted
Here is your updated list:
```

- Take note that the list that is printed subsequent to the command is omitted as different users will have different expenditures and hence different lists.

### 4.6. Duplicating an expenditure record

Duplicates an existing expenditure record from the expenditure list. After a successful duplicate, it will be appended to the expenditure list.

**Format:** `duplicate INDEX`

| Parameter | Description                                                                                                                                                                  |
| --------- | ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| `INDEX`   | A list entry value for the transaction. It is a positive whole number ranging from 1 to 1000000 and must be within the range of the number of items in the expenditure list. |

**Important Information:**

- Providing special characters and indices out of the range of the number of expenditures in the expenditure list are invalid.

### 4.7. Marking an accommodation or tuition record

Marks an existing accommodation or tuition expenditure in the expenditure list as completed.

**Format:** `mark INDEX`

| Parameter | Description                                                                                                                                                                  |
| --------- | ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| `INDEX`   | A list entry value for the transaction. It is a positive whole number ranging from 1 to 1000000 and must be within the range of the number of items in the expenditure list. |

**Important Information:**

- Marking expenditures that are not accommodation or tuition expenditures are invalid.
- Marking accommodation or tuition expenditures that is already marked is invalid.

### 4.8. Unmarking an accommodation or tuition expenditure record

Unmarks an existing accommodation or tuition expenditure in the expenditure list as incomplete.

**Format:** `unmark INDEX`

| Parameter | Description                                                                                                                                                                  |
| --------- | ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| `INDEX`   | A list entry value for the transaction. It is a positive whole number ranging from 1 to 1000000 and must be within the range of the number of items in the expenditure list. |

**Important Information:**

- Unmarking expenditures that are not accommodation or tuition expenditures are invalid.
- Unmarking accommodation or tuition expenditures that is already marked is invalid.

### 4.9. Setting a budget

Sets a budget amount that one would like to keep within.

**Format:** `set BUDGET`

| Parameter | Description                                                                                      |
| --------- | ------------------------------------------------------------------------------------------------ |
| `INDEX`   | A list entry value for the transaction. It is a positive whole number ranging from 1 to 1000000. |

**Important Information:**

- The set budget can be compared with the total sum of expenditures with the [`check`](#48-checking-expenditures-against-the-set-budget) command.
- The set budget will not be saved after the `exit` command, thus it will be 0 when MyLedger is restarted.

### 4.10. Checking expenditures against the set budget

Compares the set budget via the [`set`](#47-setting-a-budget) command against the total sum of expenditures in the expenditures.

**Format:** `check FILTER`

| Parameter                | Description                                                                             |
| ------------------------ | --------------------------------------------------------------------------------------- |
| `FILTER`<br/> [optional] | A filter that allows the user to compare budget with a certain category or time period. |

| Filter types     | Description                                                                                                                                                         |
| ---------------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| Year             | `y/YEAR` filters the check to only compare budget with expenditures made in that specific year.                                                                     |
| Day              | `d/YEAR-MONTH-DAY` filters the check to only compare budget with expenditures made on that specific day in that specific month and year.                            |
| Expenditure type | `t/EXPENDITURE_TYPE` filters the check to only compare budget with expenditures made under that expenditure type. This does not include categories lent and borrow. |

**Important Information:**

- Checking budget is compared with the latest stored value of the set budget. By default, the budget set is 0.
- If budget set is 0, message will prompt user to `set` a value before calling `check` again. `check` will not work if budget is 0.
- Borrowed expenditure amount owed is separated for a better view of expenditures.
- The input year cannot be earlier than 2000, and the maximum year that can be set is 9999.

### 4.11. List out and display the expenditure list

Displays all expenditures in the expenditure list. Expenditure value shown can be set to desired currency value.

**Format:** `list CURRENCY`

| Parameter  | Description                                                                                   |
| ---------- | --------------------------------------------------------------------------------------------- |
| `CURRENCY` | The desired [currency](#417-currency-rates) for the expenditure value to be in. Default: SGD. |

**Important Information:**

- It reads saved expenditures from a save file upon launch of MyLedger. Else, the expenditure list is empty by default.

### 4.12. Finding expenditure records by keyword

Find expenditures by description

**Format:** `find keyword`

| Parameter | Description                                                                                         |
| --------- | --------------------------------------------------------------------------------------------------- |
| `KEYWORD` | A keyword to be queried for in the descriptions of all expenditure records in the expenditure list. |

**Important Information:**

- Parameter must not be empty.
- Keyword is case-sensitive
- Works like "Ctrl-F", the find command is able to search for all characters matching the keyword in the expenditure descriptions.

<div style="page-break-after: always;"></div>
### 4.13. Sorting the expenditure list

Sorts the expenditure list by ascending or descending amount, or from earliest to latest date added. It will then display the sorted expenditure list.

**Format:** `sort SORT_TYPE`

| Parameter   | Description                                                                                                                                                                                                                           |
| ----------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| `SORT_TYPE` | The sort types are `ascend` to sort the list in ascending amount, `descend` to sort the list in descending amount, `earliest` to sort the list from the earliest date added and `latest` to sort the list from the latest date added. |

**Important Information:**

- The parameter must not be empty
- The use of sort must follow with a valid parameter or the command would not be valid.

### 4.14. View the expenditure list by expenditure category or type

Sorts the expenditure list by ascending or descending amount, or from earliest to latest date added. It will then display the sorted expenditure list.

**Format:** `viewtype CATEGORY CURRENCY`

| Parameter  | Description                                                                                                                                                              |
| ---------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------ |
| `CATEGORY` | The expenditure categories are of the regular [expenditure](#41-adding-an-expenditure) types and of lend and borrow [expenditure](#42-adding-a-lendborrow-record) types. |
| `CURRENCY` | The desired [currency](#417-currency-rates) for the expenditure value to be in. Default: SGD.                                                                            |

**Important Information:**

- The parameter must not be empty
- The use of viewdate must follow with a valid expenditure type or category or the command would not be valid.

### 4.15. View the expenditure list by date

Sorts the expenditure list by ascending or descending amount, or from earliest to latest date added. It will then display the sorted expenditure list.

**Format:** `viewdate DATE CURRENCY`

| Parameter  | Description                                                                                                 |
| ---------- | ----------------------------------------------------------------------------------------------------------- |
| `DATE`     | The date when the transaction took place on to be queried. It must be in YYYY-MM-DD format, e.g. 2023-02-02 |
| `CURRENCY` | The desired [currency](#417-currency-rates) for the expenditure value to be in. Default: SGD.               |

**Important Information:**

- The parameter must not be empty
- The use of viewdate must follow with a valid date or the command would not be valid.
- The date must be input in YYYY-MM-DD format.
- If the date selected by the user falls between the time period of a borrow/lend record, those lend/borrow records will not be included in the amounts that are updated.
- The input year cannot be earlier than 2000, and the maximum year that can be set is 9999.

### 4.16. Currency rates

Displays list of the other currency available in MyLedger and their value against SGD.

**Format:** `showrates`

<div style="page-break-after: always;"></div>

## FAQ

**Q**: How do I transfer my data to another computer?

**A**: MyLedger is able to transfer data using only it's text file. Simply copy the text from myLedger_data and paste it into the other device.

## Command Summary

- Help: `help`

- Add academic expenditure: `academic d/DATE a/AMOUNT p/DESCRIPTION`

- Add accommodation expenditure: `accommodation d/DATE a/AMOUNT p/DESCRIPTION`

- Add borrow expenditure: `borrow d/DATE n/BORROWER_NAME a/AMOUNT b/DEADLINE p/DESCRIPTION`

- Add entertainment expenditure: `entertainment d/DATE a/AMOUNT p/DESCRIPTION`

- Add food expenditure: `food d/DATE a/AMOUNT p/DESCRIPTION`

- Add lend expenditure: `lend d/DATE n/LENT_NAME a/AMOUNT b/DEADLINE p/DESCRIPTION`

- Add other expenditure: `other d/DATE a/AMOUNT p/DESCRIPTION`

- Add transport expenditure: `transport d/DATE a/AMOUNT p/DESCRIPTION`

- Add tuition expenditure: `tuition d/DATE a/AMOUNT p/DESCRIPTION`

- Check all expenditure with budget: `check`

- Check budget with expenditures made in specific year: `check y/YEAR`

- Check budget with expenditures made in specific day: `check d/YEAR-MONTH-DAY`

- Check budget with expenditures made under specific expenditure type: `check t/EXPENDITURE TYPE`

- Delete expenditure: `delete INDEX`

- Duplicate expenditure `duplicate INDEX`

- Edit expenditure: `edit INDEX d/DATE a/AMOUNT p/DESCRIPTION`

- Edit borrow or lend expenditure: `edit INDEX d/DATE n/BORROWER_OR_LENT_NAME a/AMOUNT b/DEADLINE p/DESCRIPTION`

- Find by keyword in expenditure descriptions: `find KEYWORD`

- List all expenditures: `list CURRENCY`

- Mark a specific expenditure to be complete (Tuition or Accommodation): `mark INDEX`

- Unmark a specific expenditure to be complete (Tuition or Accommodation): `unmark INDEX`

- Set temporary expenditure: `set AMOUNT`

- Sort expenditure list by ascending/descending amount: `sort ASCEND/DESCEND`

- Sort expenditure list by latest/earliest date added: `sort LATEST/EARLIEST`

- View expenditure list by date added: `viewdate DATE CURRENCY`

- View expenditure list by type of expenditure: `viewtype CATEGORY CURRENCY`

- Show all currencies: `showrates`
