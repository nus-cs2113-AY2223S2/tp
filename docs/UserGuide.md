# User Guide

# PocketPal

PocketPal is an expense tracking tool that allows you to record your financial transactions using the command-line interface. It offers several useful features, including automatic saving and loading of data to ensure data persistence. However, it's worth noting that PocketPal is intended for single-user usage only, as it doesn't have any online database functionality.

## Quick start

1. Ensure that you have Java 11 or above installed. [(Installation Guide)](https://docs.oracle.com/en/java/javase/11/install/overview-jdk-installation.html#GUID-8677A77F-231A-40F7-98B9-1FD0B48C346A)
2. Down the latest version of `PocketPal` from [here]().
3. Copy the file to the folder you want to use as the home folder for your PocketPal.
4. Launch `PocketPal` using he `java -jar command`. [(Running JAR Guide)](https://se-education.org/guides/tutorials/jar.html#running-jar-files)


## Features 

{Give detailed description of each feature}

### Adding a todo: `todo`
Adds a new item to the list of todo items.

Format: `todo n/TODO_NAME d/DEADLINE`

* The `DEADLINE` can be in a natural language format.
* The `TODO_NAME` cannot contain punctuation.  

Example of usage: 

`todo n/Write the rest of the User Guide d/next week`

`todo n/Refactor the User Guide to remove passive voice d/13/04/2020`

## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: {your answer here}


## Command Summary

| Command | Format                                                                                       |
|--------:|----------------------------------------------------------------------------------------------|
|    /add | `/add <DESCRIPTION> <-c,-category CATEGORY> <-p,-price PRICE>`                               |
|   /view | `/view [COUNT] [-c,-category CATEGORY]`                                                      | -categroCATEGORY]`                        |
|   /edit | `/edit <EXPENSE_ID> [-d,-description DESCRIPTION] [-c,-category CATEGORY] [-p,-price PRICE]` |
| /delete | `/delete <EXPENSE_ID>`                                                                       |
|   /help | `/help`                                                                                      |

