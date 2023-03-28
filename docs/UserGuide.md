# User Guide
## Contents
- [Introduction](#introduction)
- [Quick Start](#Quick-start)
- [Features](#features)
- [FAQ](#faq)
- [Command Summary](#command-summary)

## Introduction

MagusStock is a Java command-line interface (CLI) application designed for inventory management.

## Quick Start

1. Ensure that you have Java 11 or above installed. 
2. Download the latest version of MagusStock from [here](https://github.com/AY2223S2-CS2113-W12-3/tp/releases).
3. Move the `.jar` file to an empty folder.
4. Run the file with the command `java -jar magusstock.jar`.

## Features 

{Give detailed description of each feature}

### Adding an item: `add`
Adds a new item to the inventory list.

Format: `todo n/TODO_NAME d/DEADLINE`

* The `DEADLINE` can be in a natural language format.
* The `TODO_NAME` cannot contain punctuation.  

Example of usage: 

`todo n/Write the rest of the User Guide d/next week`

`todo n/Refactor the User Guide to remove passive voice d/13/04/2020`

Sample output:

### Editting an item: `edit`
Edit an item's details in the inventory list.

Format: `todo n/TODO_NAME d/DEADLINE`

* The `DEADLINE` can be in a natural language format.
* The `TODO_NAME` cannot contain punctuation.

Example of usage:

``

``

Sample output:

### Removing an item: `remove`
Removes an item from the inventory list using either its UPC or index in list.

Format: `remove f/item upc/[UPC]` or `remove f/index [Index]`

* The `UPC` can be only be a numerical value.
* The `index` can only be a number.

Example of usage:

`remove f/item upc/123456789`

`remove f/index 0`

Sample output:

### List all items: `list`
Lists all items in the inventory list.

Format: `list`

Example of usage:

`list`

Sample output:

### Searching an item: `search`
Search for item(s) in the inventory list using keywords or UPC.

Format: `search [Keywords]` or `searchupc [UPC]`

Example of usage:

``

``

Sample output:

### Filtering by type: `filter`
Filters items from the inventory list by price or category.

Format: `filter f/{price/category} {p/[type] [Price] or [Category keywords]`

Example of usage:

``

``

Sample output:

### List all commands: `help`
Lists all commands available and the command formats.

Format: `help`

Example of usage:

`help`

Sample output:

### Historical records of item: `history`
Lists historical changes to an item in the inventory list.

Format: `history [UPC]`

Example of usage:

``

Sample output:

### Sell quantity of item: `sell`
Reduces the quantity of an item in the inventory list.

Format: ``

Example of usage:

``

``

Sample output:

### Restock an item: `restock`
Restock quantities of an item in the inventory list.

Format: ``

Example of usage:

``

``

Sample output:

### Dashboard: `db`
Shows a dashboard of information related to the inventory.

Format: `db`

Example of usage:

`db`

Sample output:

### Category: `cat`
Shows list of categories, and/or its items, or a specified category of items.

Format: 
`cat list`: shows list of all categories in the inventory.
`cat table`: shows table of all categories and all items in each category.
`cat [Category]`: shows list of all items in a specified category.

Example of usage:

`cat list`

`cat table`
`cat fruit`

Sample output:

### Alert for an item: `alert`
* insert description here

Format: 
`alert ...`: ...
``: ...

Example of usage:

``

``

Sample output:



## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: {your answer here}

## Command Summary
| Action  | Command |
|---------|-------|
| Add an item to the inventory |       |
| Remove an item from the inventory | |




{Give a 'cheat sheet' of commands here}

* Add todo `todo n/TODO_NAME d/DEADLINE`
