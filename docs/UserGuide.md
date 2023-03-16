# User Guide

## Introduction

rainyDay is a desktop application for managing your finances. With a command line interface, you can keep track of your
financial inflows and outflows and view them with a single command.

<!-- TOC -->

* [User Guide](#user-guide)
    * [Introduction](#introduction)
    * [Quick Start](#quick-start)
    * [Features](#features)
        * [Adding a transaction](#adding-a-transaction)
        * [Deleting a transaction](#deleting-a-transaction)
        * [Viewing the financial report](#viewing-the-financial-report)
        * [Viewing help](#viewing-help)
        * [Saving the data](#saving-the-data)
        * [Loading saved data](#loading-saved-data)
        * [Exiting the application](#exiting-the-application)
    * [FAQ](#faq)
    * [Command Summary](#command-summary)
    * [Glossary](#glossary)

<!-- TOC -->

## Quick Start

1. Ensure that you have Java 11 or above installed your computer.
    1. Click [here](https://www.java.com/en/download/help/version_manual.html) for steps on how to check your Java
       version.
    2.
   Click [here](https://docs.oracle.com/en/java/javase/11/install/overview-jdk-installation.html#GUID-8677A77F-231A-40F7-98B9-1FD0B48C346A)
   for the guide on installing Java 11
2. Download the latest version of "rainyDay"
   from [here](https://github.com/AY2223S2-CS2113T-T09-1/tp/releases/download/v1.0/rainyDay.jar).
3. Move the "rainyDay.jar" file to the file directory of your choice
    1. You can move the jar file by right-clicking on Duke.jar
    2. Select cut
    3. Go to the directory of choice
    4. Right-click in the file window
    5. Select paste
4. Right-click on the directory with "rainyDay.jar" and select "open in terminal"
   ![](\images\right-click-to-open-terminal.png)
    - Screenshot of file directory containing rainyDay.jar, after performing a right click on an empty space in the file
      directory
5. Type `java -jar rainyDay.jar` and press Enter on your keyboard to start the application

## Features

### Adding a transaction

Adds a new transaction to the financial report.

Format: `add -DIRECTION TRANSACTION_NAME $AMOUNT`

* The `DIRECTION` to be `in` signifying an inflow type of transaction, or `out` signifying an outflow type of
  transaction
* The `TRANSACTION_NAME` can be any keyboard input.
* The `AMOUNT` needs to be a number.

Example of usage:

`add -in angpao $500`

`add -out school fees $1000`

### Deleting a transaction

Deletes a transaction in the financial report.

Format: `delete INDEX`

* The `INDEX` needs to be a number

Example of usage:

`delete 1`

`delete 2`

### Viewing the financial report

Lists all the transaction added, the total resultant amount after inflow - outflow, total inflow amount, and total
outflow amount

Format: `view`

### Viewing help

{todo}

### Saving the data

{todo}

### Loading saved data

{todo}

### Exiting the application

{todo}

## FAQ

**Q**: How do I transfer my data to another computer?

**A**: Copy the txt file named "rainyDay" that is in the same directory as rainyDay.jar to the new device and location
that
rainyDay.jar is going to be stored in

## Command Summary

| Action | Format <br> Example input                                                                                            |
|--------|----------------------------------------------------------------------------------------------------------------------|
| Add    | `add -`(in/out) (description) `$`(value) <br><br> **Example:** <br> `add -out Ipad $120` <br> `add -in angpao $3000` |
| Delete | `delete` (index) <br><br> **Example:** <br> `delete 1` <br> `delete 2`                                               |
| View   | `view`                                                                                                               |
| Help   | `help`                                                                                                               |
| Exit   | `bye`                                                                                                                |

## Glossary

Financial Report

- {to add definition of financial report in our scope of application}

Inflow

- {to add definition of financial report in our scope of application}

Outflow

- {to add definition of financial report in our scope of application}