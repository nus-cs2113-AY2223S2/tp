# User Guide

## Introduction

rainyDay is a desktop application for managing your finances. With a command line interface, you can keep track of your
financial inflows and outflows and view them with a single command.

## Quick Start

{Give steps to get started quickly}

1. Ensure that you have Java 11 or above installed.
1. Download the latest version of `rainyDay`
   from [here](https://github.com/AY2223S2-CS2113T-T09-1/tp/releases/download/v1.0/rainyDay.jar).

## Features

### Adding a transaction: `add`

Adds a new transaction to the financial report.

Format: `add -DIRECTION TRANSACTION_NAME $AMOUNT`

* The `DIRECTION` to be `in` signifying an inflow type of transaction, or `out` signifying an outflow type of
  transaction
* The `TRANSACTION_NAME` can be any keyboard input.
* The `AMOUNT` needs to be a number.

Example of usage:

`add -in angpao $500`

`add -out school fees $1000`

### Deleting a transaction: `delete`

Deletes a transaction in the financial report.

Format: `delete INDEX`

* The `INDEX` needs to be a number

Example of usage:

`delete 1`

`delete 2`

### Viewing the financial report: `view`

Lists all the transaction added, the total resultant amount after inflow - outflow, total inflow amount, and total
outflow amount

Format: `view`

## FAQ

**Q**: How do I transfer my data to another computer?

**A**: Copy the txt file named "rainyDay" that is in the same directory as rainyDay.jar to the new device and location
that
rainyDay.jar is going to be stored in

## Command Summary

| Action | Format                              |
|--------|-------------------------------------|
| Add    | add -(in/out) (description) (value) |
| Delete | delete (index)                      |
| View   | view                                |
| Help   | help                                |
| Exit   | bye                                 |

* Add todo `todo n/TODO_NAME d/DEADLINE`
