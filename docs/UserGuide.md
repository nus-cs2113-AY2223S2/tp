# MyLedger - User Guide

<p align="center">
    <img src="team/images/MyLedger.jpeg" width="30%">
</p>

<!-- TOC -->
* [MyLedger - User Guide](#myledger---user-guide)
  * [Introduction](#introduction)
  * [Quick Start](#quick-start)
  * [Managing Transactions](#managing-transactions)
    * [4.1. Adding an expenditure](#41-adding-an-expenditure)
    * [4.2. Adding a lend/borrow record](#42-adding-a-lendborrow-record)
    * [4.3. Editing an Expenditure](#43-editing-an-expenditure)
    * [4.4. Editing a Lend/Borrow record](#44-editing-a-lendborrow-record)
    * [4.5. Deleting an expenditure record](#45-deleting-an-expenditure-record)
    * [4.6. Duplicating an expenditure record](#46-duplicating-an-expenditure-record)
    * [4.7. Marking a lend or borrow expenditure record](#47-marking-a-lend-or-borrow-expenditure-record)
    * [4.8. Unmarking a lend or borrow expenditure record](#48-unmarking-a-lend-or-borrow-expenditure-record)
    * [4.9. Setting a budget](#49-setting-a-budget)
    * [4.10. Checking expenditures against the set budget](#410-checking-expenditures-against-the-set-budget)
    * [4.11. Marking a lend or borrow expenditure record](#411-marking-a-lend-or-borrow-expenditure-record)
    * [4.12. List out and display the expenditure list](#412-list-out-and-display-the-expenditure-list)
    * [4.13. Finding expenditure records by keyword](#413-finding-expenditure-records-by-keyword)
    * [4.14. Sorting the expenditure list](#414-sorting-the-expenditure-list)
    * [4.15. View the expenditure list by expenditure category or type](#415-view-the-expenditure-list-by-expenditure-category-or-type)
    * [4.16. View the expenditure list by date](#416-view-the-expenditure-list-by-date)
  * [FAQ](#faq)
  * [Command Summary](#command-summary)
<!-- TOC -->

## Introduction

MyLedger is a desktop app for managing finances, designed for university students studying locally or on exchange. It is optimized for use via a Command Line Interface (CLI). For students that can type fast, MyLedger can help them record and monitor their budget and expenses, managing their transactions more effciently. 

## Quick Start

1. Ensure that you have Java 11 or above installed.
2. Download the latest version of `MyLedger` from [here](https://github.com/AY2223S2-CS2113-T14-3/tp/releases/tag/v2.0).
3. Open the command terminal on your device.
4. Navigate to the folder in command terminal and run the command `java -jar [filename].jar`
5. Alternatively, double click on the JAR file to run the app.
## Managing Transactions

### 4.1. Adding an expenditure

Adds an expenditure to the record

Format: `EXPENDITURE_CATEGORY d/DATE a/AMOUNT s/DESCRIPTION`

| Parameter     | Description                                                                                            |
|---------------|--------------------------------------------------------------------------------------------------------|
| `EXPENDITURE_CATEGORY`        | The type of transaction. There are 7 types, `Academic`, `Accomodation`, `Entertainment` , `Food` , `Transport` , `Tuition` and `Other`                                 |        |
| `AMOUNT`      | The amount of the transaction. It is a positive whole number ranging from 1 to 10000000 (Ten Million). | 
| `DATE`        | The date when the transaction took place on. It must be in yyyy-MM-dd format, e.g. 2023-02-02             |                                                                                               
| `DESCRIPTION` | More information regarding the transaction.                  | 

**Important Information:**

- All parameters listed must be present in this command.
- All parameters must not be empty.
- The input date format must be in yyyy-MM-DD format

**Examples:**

- `academic d/2023-02-02 a/25.10 s/NUS` <br> 
- `other d/2000-01-31 a/26 s/Eating lunch`

**Expected Output:**

Adding an Academic Expenditure

Input:

```
academic d/2023-02-02 a/25.10 s/NUS
```

Output:

```
Added academic expenditure: [Academic] || Date: 2 Feb 2023 || Value: 25.1 || Description: NUS
```

Adding a Other Expenditure

Input:

```
other d/2000-01-31 a/26 s/Eating lunch
```

Output:

```
Added other expenditure: [Other] || Date: 31 Jan 2000 || Value: 26.0 || Description: Eating lunch
```

### 4.2. Adding a lend/borrow record

Adds a lending or borrowing transaction to the record

Format: `TYPE d/DATE n/NAME a/AMOUNT b/DEADLINE s/DESCRIPTION`

| Parameter     | Description                                                                                            |
|---------------|--------------------------------------------------------------------------------------------------------|
| `CATEGORY`        | The category of record of `lend` or `borrow`. It should either be `lend` or `borrow`.                                 |        |
| `DATE`      | The date when the transaction took place on. It must be in yyyy-MM-dd format, e.g. 2023-02-02. | 
| `NAME`       | The name of the other party involved in the transaction       |
| `AMOUNT`        | The amount of the transaction. It can a positive whole number ranging from 1 to 10000000 (Ten Million).           |                                                                                               
| `DEADLINE`      | The date when the transaction is dued. It must be in yyyy-MM-dd format, e.g. 2023-02-02.| 
| `DESCRIPTION` | More information regarding the transaction.                  | 


**Important Information:**

- All parameters must be present in this command.
- All parameters must not be empty.
- The input date format must be in yyyy-MM-DD format

**Examples:**

- `lend d/2023-02-02 n/Akshay Narayan a/25.10 b/2023-04-02 s/CS2113`

**Expected Output:**

Adding a lend transaction

Input:

```
lend d/2023-02-02 n/Akshay Narayan a/25.10 b/2023-04-02 s/CS2113 
```
Output:

```
Added lend expenditure: [Lend] || Lent to: Akshay Narayan || Date: 2 Feb 2023 || Value: 25.1 || Description: CS2113 || by: 2 Apr 2023
```

### 4.3. Editing an Expenditure

Edits an existing expenditure transaction in the record. After a successful edit, the updated list is shown. 

**Format:** `edit INDEX d/DATE a/AMOUNT s/DESCRIPTION`

| Parameter     | Description                                                                                            |
|---------------|--------------------------------------------------------------------------------------------------------|
| `INDEX`       | A list entry value for the transaction. It is a positive whole number ranging from 1 to 1000000 and must be within the range of the number of items in the expenditure list.       |
| `DATE`        | The date when the transaction took place on. It must be in yyyy-MM-dd format, e.g. 2023-02-02                                   |
| `AMOUNT`    | The amount of the transaction. It is a positive whole number ranging from 1 to 10000000 (Ten Million).                 |
| `DESCRIPTION`      | More information regarding the transaction. | 


**Important Information:**

- The fields provided are the same as adding an expenditure in [4.1](#41-adding-an-expenditure)
- Cannot change an expenditure type, e.g. cannot change an `Academic` expenditure to an `Accomodation` expenditure

**Examples:**

- `edit 2 d/2023-02-15 a/20.00 s/Eat Food`

**Expected Output:**

Editing an expenditure

```
edit 2 d/2023-02-15 a/20.00 s/Eat Food

Edited! Here is the updated list:

```
### 4.4. Editing a Lend/Borrow record

Edits an existing lend or borrow in the record. After a successful edit, the updated list is shown. 

**Format:** `edit INDEX d/DATE n/NAME a/AMOUNT b/DEADLINE s/DESCRIPTION`

| Parameter     | Description                                                                                            |
|---------------|--------------------------------------------------------------------------------------------------------|
| `INDEX`       | A list entry value for the transaction. It is a positive whole number ranging from 1 to 1000000.       |
| `DATE`        | The date when the transaction took place on. It must be in yyyy-MM-dd format, e.g. 2023-02-02                                   |
| `NAME`       | The name of the other party involved in the transaction       |
| `AMOUNT`    | The amount of the transaction. It is a positive whole number ranging from 1 to 10000000 (Ten Million).                 |
| `DEADLINE`      | The date when the transaction is dued. It must be in yyyy-MM-dd format, e.g. 2023-02-02.| 
| `DESCRIPTION` | More information regarding the transaction.                | 


**Important Information:**

- The fields provided are the same as adding an expenditure in [4.2](#42-adding-a-lendborrow-record)
- Cannot change a `lend` record to a `borrow` record or vice versa.

**Examples:**

- `edit 17 d/2023-02-02 n/Akshay Narayan a/25.10 b/2023-04-02 s/CS2040`

**Expected Output:**

Editing an expenditure

```
edit 17 d/2023-02-02 n/Akshay Narayan a/25.10 b/2023-04-02 s/CS2040

Edited! Here is the updated list:

```

### 4.5. Deleting an expenditure record

Deletes an existing expenditure record from the expenditure list. After a successful delete, the updated list is shown.

**Format:** `delete INDEX`

| Parameter     | Description                                                                                            |
|---------------|--------------------------------------------------------------------------------------------------------|
| `INDEX`       | A list entry value for the transaction. It is a positive whole number ranging from 1 to 1000000 and must be within the range of the number of items in the expenditure list.       |

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

### 4.6. Duplicating an expenditure record

Duplicates an existing expenditure record from the expenditure list. After a successful duplicate, it will be appended to the expenditure list.

**Format:** `duplicate INDEX`

| Parameter     | Description                                                                                            |
|---------------|--------------------------------------------------------------------------------------------------------|
| `INDEX`       | A list entry value for the transaction. It is a positive whole number ranging from 1 to 1000000 and must be within the range of the number of items in the expenditure list.       |

**Important Information:**

- Providing special characters and indices out of the range of the number of expenditures in the expenditure list are invalid.

### 4.7. Marking a lend or borrow expenditure record

Marks an existing lend or borrow expenditure in the expenditure list as completed.

**Format:** `mark INDEX`

| Parameter     | Description                                                                                            |
|---------------|--------------------------------------------------------------------------------------------------------|
| `INDEX`       | A list entry value for the transaction. It is a positive whole number ranging from 1 to 1000000 and must be within the range of the number of items in the expenditure list.       |

**Important Information:**

- Marking expenditures that are not lend or borrow expenditures are invalid.
- Marking lend or borrow expenditures that is already marked is invalid.

### 4.8. Unmarking a lend or borrow expenditure record

Unmarks an existing lend or borrow expenditure in the expenditure list as incomplete.

**Format:** `unmark INDEX`

| Parameter     | Description                                                                                            |
|---------------|--------------------------------------------------------------------------------------------------------|
| `INDEX`       | A list entry value for the transaction. It is a positive whole number ranging from 1 to 1000000 and must be within the range of the number of items in the expenditure list.       |

**Important Information:**

- Unmarking expenditures that are not lend or borrow expenditures are invalid.
- Unmarking lend or borrow expenditures that is already marked is invalid.

### 4.9. Setting a budget

Sets a budget amount that one would like to keep within.

**Format:** `set BUDGET`

| Parameter     | Description                                                                                            |
|---------------|--------------------------------------------------------------------------------------------------------|
| `INDEX`       | A list entry value for the transaction. It is a positive whole number ranging from 1 to 1000000.       |

**Important Information:**

- The set budget can be compared with the total sum of expenditures with the [`check`](#48-checking-expenditures-against-the-set-budget) command.
- The set budget will not be saved after the `exit` command, thus it will be 0 when MyLedger is restarted.
### 4.10. Checking expenditures against the set budget

Compares the set budget via the [`set`](#47-setting-a-budget) command against the total sum of expenditures in the expenditures.

**Format:** `check`

**Important Information:**

- Checking budget is compared with the latest stored value of the set budget. By default, the budget set is 0.
- If budget set is 0, message will prompt user to `set` a value before calling `check` again. `check` will not work if budget is 0.
- Borrowed expenditure amount owed is separated for a better view of expenditures.

### 4.11. Marking a lend or borrow expenditure record

Marks an existing lend or borrow expenditure in the expenditure list as completed.

**Format:** `mark INDEX`

| Parameter     | Description                                                                                            |
|---------------|--------------------------------------------------------------------------------------------------------|
| `INDEX`       | A list entry value for the transaction. It is a positive whole number ranging from 1 to 1000000 and must be within the range of the number of items in the expenditure list.       |

**Important Information:**

- Marking expenditures that are not lend or borrow expenditures are invalid.
- Marking lend or borrow expenditures that is already marked is invalid.

### 4.12. List out and display the expenditure list

Displays all expenditures in the expenditure list.

**Format:** `list`     |

**Important Information:**

- It reads saved expenditures from a save file upon launch of MyLedger. Else, the expenditure list is empty by default.

### 4.13. Finding expenditure records by keyword

Find expenditures by description 

**Format:** `find keyword`

| Parameter     | Description                                                                                            |
|---------------|--------------------------------------------------------------------------------------------------------|
| `KEYWORD`       | A keyword to be queried for in the descriptions of all expenditure records in the expenditure list.       |

**Important Information:**

- Parameter must not be empty.
- Keyword is case-sensitive
- Works like "Ctrl-F", the find command is able to search for all characters matching the keyword in the expenditure descriptions.

### 4.14. Sorting the expenditure list

Sorts the expenditure list by ascending or descending amount, or from earliest to latest date added. It will then display the sorted expenditure list.

**Format:** `sort SORT_TYPE`

| Parameter     | Description                                                                                            |
|---------------|--------------------------------------------------------------------------------------------------------|
| `SORT_TYPE`       | The sort types are `ascend` to sort the list in ascending amount, `descend` to sort the list in descending amount, `earliest` to sort the list from the earliest date added and `latest` to sort the list from the latest date added.       |

**Important Information:**

- The parameter must not be empty
- The use of sort must follow with a valid parameter or the command would not be valid.

### 4.15. View the expenditure list by expenditure category or type

Sorts the expenditure list by ascending or descending amount, or from earliest to latest date added. It will then display the sorted expenditure list.

**Format:** `viewtype CATEGORY`

| Parameter     | Description                                                                                            |
|---------------|--------------------------------------------------------------------------------------------------------|
| `CATEGORY`       | The expenditure categories are of the regular [expenditure](#41-adding-an-expenditure) types and of lend and borrow [expenditure](#42-adding-a-lendborrow-record) types.      |

**Important Information:**

- The parameter must not be empty
- The use of viewdate must follow with a valid expenditure type or category or the command would not be valid.

### 4.16. View the expenditure list by date

Sorts the expenditure list by ascending or descending amount, or from earliest to latest date added. It will then display the sorted expenditure list.

**Format:** `viewdate DATE`

| Parameter     | Description                                                                                            |
|---------------|--------------------------------------------------------------------------------------------------------|
| `DATE`       | The date when the transaction took place on to be queried. It must be in yyyy-MM-dd format, e.g. 2023-02-02      |

**Important Information:**

- The parameter must not be empty
- The use of viewdate must follow with a valid date or the command would not be valid.
- The date must be input in yyyy-MM-dd format.

## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: {your answer here}

## Command Summary

{Give a 'cheat sheet' of commands here}

* Help: `help`

* Add academic expenditure: `academic d/DATE a/AMOUNT s/DESCRIPTION`

* Add accommodation expenditure: `accommodation d/DATE a/AMOUNT s/DESCRIPTION`

* Add borrow expenditure: `borrow d/DATE n/BORROWER_NAME a/AMOUNT b/DEADLINE s/DESCRIPTION`

* Add entertainment expenditure: `entertainment d/DATE a/AMOUNT s/DESCRIPTION`

* Add food expenditure: `food d/DATE a/AMOUNT s/DESCRIPTION`

* Add lend expenditure: `academic d/DATE n/LENT_NAME a/AMOUNT b/DEADLINE s/DESCRIPTION`

* Add other expenditure: `other d/DATE a/AMOUNT s/DESCRIPTION`

* Add transport expenditure: `transport d/DATE a/AMOUNT s/DESCRIPTION`

* Add tuition expenditure: `tuition d/DATE a/AMOUNT s/DESCRIPTION`

* Check expenditure: `check`

* Delete expenditure: `delete INDEX`

* Duplicate expenditure `duplicate INDEX`

* Edit expenditure: `edit d/DATE a/AMOUNT s/DESCRIPTION`

* Edit borrow or lend expenditure: `edit d/DATE n/BORROWER_OR_LENT_NAME a/AMOUNT b/DEADLINE s/DESCRIPTION`

* Find by keyword in expenditure descriptions: `find KEYWORD`

* List all expenditures: `list`

* Mark a specific expenditure to be complete (Tuition or Accommodation): `mark INDEX`

* Unmark a specific expenditure to be complete (Tuition or Accommodation): `unmark INDEX`

* Set temporary expenditure: `set AMOUNT`

* Sort expenditure list by ascending/descending amount: `sort ASCEND/DESCEND`

* Sort expenditure list by latest/earliest date added: `sort LATEST/EARLIEST`

* View expenditure list by date added: `viewdate DATE`

* View expenditure list by type of expenditure: `viewtype CATEGORY`
