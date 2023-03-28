# MyLedger - User Guide

<p align="center">
    <img src="team/images/MyLedger.jpeg" width="30%">
</p>

## Introduction

MyLedger is a desktop app for managing finances, designed for university students studying locally or on exchange. It is optimized for use via a Command Line Interface (CLI). For students that can type fast, MyLedger can help them record and monitor their budget and expenses, managing their transactions more effciently. 

## Quick Start

{Give steps to get started quickly}

1. Ensure that you have Java 11 or above installed.
1. Down the latest version of `Duke` from [here](http://link.to/duke).

## Managing Transactions

### 4.1 Adding an expenditure

Adds an expenditure to the record

Format: `EXPENDITURE_TYPE d/DATE a/AMOUNT s/DESCRIPTION`

| Parameter     | Description                                                                                            |
|---------------|--------------------------------------------------------------------------------------------------------|
| `EXPENDITURE_TYPE`        | The type of transaction. There are 7 types, `Academic`, `Accomodation`, `Entertainment` , `Food` , `Transport` , `Tuition` and `Other`                                 |        |
| `AMOUNT`      | The amount of the transaction. It is a positive whole number ranging from 1 to 10000000 (Ten Million). | 
| `DATE`        | The date when the transaction took place on. It must be in yyyy-MM-dd format, e.g. 2023-02-02             |                                                                                               
| `DESCRIPTION` | More information regarding the transaction.                  | 

**Important Information:**

- All tags must be present in this command.
- All parameters must not be empty.

**Examples:**

- `academic d/2023-02-02 a/25.10 s/NUS` <br> 
- `other d/2000-01-31 a/26 s/Eating lunch`

**Expected Output:**

Adding an Academic Expenditure

```
academic d/2023-02-02 a/25.10 s/NUS

Added academic expenditure: [Academic] || Date: 2 Feb 2023 || Value: 25.1 || Description: NUS

```

Adding a Other Expenditure

```
other d/2000-01-31 a/26 s/Eating lunch

Added other expenditure: [Other] || Date: 31 Jan 2000 || Value: 26.0 || Description: Eating lunch
```
### 4.2 Adding a lend/borrow record

Adds a lending or borrowing transaction to the record

Format: `TYPE d/DATE n/NAME a/AMOUNT b/DEADLINE s/DESCRIPTION`

| Parameter     | Description                                                                                            |
|---------------|--------------------------------------------------------------------------------------------------------|
| `TYPE`        | The type of record. It should either be `lend` or `borrow`.                                 |        |
| `DATE`      | The date when the transaction took place on. It must be in yyyy-MM-dd format, e.g. 2023-02-02. | 
| `NAME`       | The name of the other party involved in the transaction       |
| `AMOUNT`        | The amount of the transaction. It is a positive whole number ranging from 1 to 10000000 (Ten Million).           |                                                                                               
| `DEADLINE`      | The date when the transaction is dued. It must be in yyyy-MM-dd format, e.g. 2023-02-02.| 
| `DESCRIPTION` | More information regarding the transaction.                  | 


**Important Information:**

- All tags must be present in this command.
- All parameters must not be empty.

**Examples:**

- `lend d/2023-02-02 n/Akshay Narayan a/25.10 b/2023-04-02 s/CS2113`

**Expected Output:**

Adding a lend transaction

```
lend d/2023-02-02 n/Akshay Narayan a/25.10 b/2023-04-02 s/CS2113 

Added lend expenditure: [Lend] || Lent to: Akshay Narayan || Date: 2 Feb 2023 || Value: 25.1 || Description: CS2113 || by: 2 Apr 2023

```

### 4.3. Editing an Expenditure

Edits an existing expenditure transaction in the record. After a successful edit, the updated list is shown. 

**Format:** `edit INDEX d/DATE a/AMOUNT s/DESCRIPTION`

| Parameter     | Description                                                                                            |
|---------------|--------------------------------------------------------------------------------------------------------|
| `INDEX`       | A list entry value for the transaction. It is a positive whole number ranging from 1 to 1000000.       |
| `DATE`        | The date when the transaction took place on. It must be in yyyy-MM-dd format, e.g. 2023-02-02                                   |
| `AMOUNT`    | The amount of the transaction. It is a positive whole number ranging from 1 to 10000000 (Ten Million).                 |
| `DESCRIPTION`      | More information regarding the transaction. | 


**Important Information:**

- All tags must be present in this command.
- All parameters must not be empty.
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

- All tags must be present in this command.
- All parameters must not be empty.
- Cannot change a `lend` record to a `borrow` record

**Examples:**

- `edit 17 d/2023-02-02 n/Akshay Narayan a/25.10 b/2023-04-02 s/CS2040`

**Expected Output:**

Editing an expenditure

```
edit 17 d/2023-02-02 n/Akshay Narayan a/25.10 b/2023-04-02 s/CS2040

Edited! Here is the updated list:

```

## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: {your answer here}

## Command Summary

{Give a 'cheat sheet' of commands here}

* Add todo `todo n/TODO_NAME d/DEADLINE`
