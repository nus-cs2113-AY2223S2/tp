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

### For BEST experience please run the java file in Terminal App (Not CMD)
Reason: The UI Color is designed to be used in Terminal App as CMD does not support ANSI Escape Code.

![img_3.png](img_3.png)

Where to get it if you don't have one? (Go to Windows Store)
![img_4.png](img_4.png)
## Features 
- [Adding an item: `add`](#adding-an-item--add)
- [Editing an item: `edit`](#editting-an-item--edit)
- [Removing an item: `remove`](#removing-an-item--remove)
- [Listing all items: `list`](#list-all-items--list)
- [Searching for item(s): `search`](#searching-an-item--search)
- [Filtering item(s) by type: `filter`](#filtering-by-type--filter)
- [Listing all commands: `help`](#list-all-commands--help)
- [History of item: `history`](#historical-records-of-item--history)
- [Selling an item: `sell`](#sell-quantity-of-item--sell)
- [Restocking an item: `restock`](#restock-an-item--restock)
- [Dashboard: `db`](#dashboard--db)
- [Category: `cat`](#category--cat)
- [Alert for item(s): `alert`](#alert-for-an-item--alert)
- [Autosave of Inventory: `autosave`](#change-autosave-mode--autosave)
- [Exiting the program: `exit`](#exiting-the-program--exit)

{Give detailed description of each feature}

### Adding an item: `add`
Adds a new item to the inventory list.

Format: `add n/[item_name] upc/[UPC] qty/[quantity] p/[price]`

Required parameters:
* The `n/` parameter where `[item_name]`  must be alphanumeric. 
* The `upc/` parameter for `[UPC]` must be a numerical value.
* The `qty/` parameter for `[quantity]` must be a numerical value.
* The `p/` parameter for `[price]` must be a numerical value (decimals accepted).

Example of usage: 

`add n/HP Laptop upc/2142535453 qty/10 p/1299.99`

`add n/iPhone 11 Pro Max Max upc/2987654323 qty/11 p/1099.99`

Sample output:

![img.png](img.png)
### Editing an item: `edit`
Edit an item's details in the inventory.

Format: `edit upc/[UPC] {n/[item_name] qty/[quantity] p/[price]}`

Required parameters:
* The `upc/` parameter where `[UPC]`  must be a numerical value and exists in the inventory.

Optional parameters:
* The `n/` parameter where `[item_name]` must be alphanumeric.
* The `qty/` parameter for `[quantity]` must be a numerical value.
* The `p/` parameter for `[price]` must be a numerical value (decimals accepted).

Example of usage:

``
edit upc/2142535453 n/HP Laptop Pro qty/10 p/1299.99
``

Sample output:
```
Successfully edited the following item:

Before Update:
Name: HP Laptop
UPC: 2142535453
Price: 1299.99
Quantity: 10
Category: uncategorized

After Update:
Name: HP Laptop Pro
UPC: 2142535453
Price: 1299.99
Quantity: 10
Category: uncategorized

```

### Removing an item from the inventory: `remove`
Removes an item from the inventory list using either its UPC or index in list.

Format: `remove f/item upc/[UPC]` or `remove f/index [Index]`

* The `UPC` can be only be a numerical value.
* The `index` can only be a number.

Example of usage:

`remove f/item upc/123456789`

`remove f/index 0`

Sample output:

### List all items in the inventory: `list`
Lists all items in the inventory list.

Format: `list`

Example of usage:

`list`

Sample output:  
![img_2.png](img_2.png)

### Search for an item in the inventory: `search`
Search for item(s) in the inventory list using keywords or UPC.

Format: `search [Keywords]` or `searchupc [UPC]`

Example of usage:

``search apples``

Sample output:

```
____________________________________________________________
+-----------------+--------------+----------+----------+
| Name            | UPC          | Quantity | Price    |
+-----------------+--------------+----------+----------+
| Apples          | 0123241      | 10       | $15.0    |
+-----------------+--------------+----------+----------+
| Large Apples    | 012321       | 10       | $15.0    |
+-----------------+--------------+----------+----------+

____________________________________________________________
```
Example of usage:

``searchupc 0123241``

Sample output:
```
____________________________________________________________
Here is your item: 
+-----------------+--------------+----------+----------+
| Name            | UPC          | Quantity | Price    |
+-----------------+--------------+----------+----------+
| Apples          | 0123241      | 10       | $15.0    |
+-----------------+--------------+----------+----------+

____________________________________________________________
```

### Filtering by type: `filter`

### Filtering inventory list by type: `filter`

Filters items from the inventory list by price or category.

Format: `filter f/{price/category} {p/[gt/get/lt/let] [Price] or [Category keywords]`

Example of usage:

``
filter f/category fruits
``

Sample output:
```
+-----------------+--------------+----------+----------+
| Name            | UPC          | Quantity | Price    |
+-----------------+--------------+----------+----------+
| Apples          | 0123241      | 10       | $15.0    |
+-----------------+--------------+----------+----------+
| Large Apples    | 012321       | 10       | $15.0    |
+-----------------+--------------+----------+----------+

____________________________________________________________
```

Example of usage:

``filter f/price p/gt 10.2``

Sample output:

```
____________________________________________________________
+-----------------+--------------+----------+----------+
| Name            | UPC          | Quantity | Price    |
+-----------------+--------------+----------+----------+
| TV              | 1            | 1        | $10999.0 |
+-----------------+--------------+----------+----------+
| Apples          | 0123241      | 10       | $15.0    |
+-----------------+--------------+----------+----------+
| Large Apples    | 012321       | 10       | $15.0    |
+-----------------+--------------+----------+----------+

____________________________________________________________

```

### List all available commands: `help`
Lists all commands available and the command formats.

Format: `help`

Example of usage:

`help`

Sample output:

### Historical records of item: `history`
Lists historical changes to an item in the inventory list.

Format: `history [UPC]`

Example of usage:

``history 0123241``

Sample output:

```
____________________________________________________________
Item added at: 12:00 AM, WEDNESDAY, MARCH 29, 2023
Name: Apples
UPC: 0123241
Price: 15.0
Quantity: 10
Category: uncategorized
____________________________________________________________
____________________________________________________________
At: 12:04 AM, WEDNESDAY, MARCH 29, 2023
Category changed to: fruits
____________________________________________________________
____________________________________________________________
At: 12:06 AM, WEDNESDAY, MARCH 29, 2023
Sold 5 items
____________________________________________________________
____________________________________________________________
At: 12:06 AM, WEDNESDAY, MARCH 29, 2023
Bought 50 items
____________________________________________________________
____________________________________________________________
At: 12:06 AM, WEDNESDAY, MARCH 29, 2023
Price decreased from $15.0 to $5.0
____________________________________________________________
____________________________________________________________
Name: Apples
UPC: 0123241
Price: 5.0
Quantity: 55
Category: fruits
____________________________________________________________
```

### Sell quantity of item: `sell`
Reduces the quantity of an item in the inventory list.

Format: `sell upc/[UPC] qty/[Quantity]`

Required Parameters: 
* The `upc/` parameter where `[UPC]` can be only be a numerical value and must exists in the inventory. 
* The `qty/` parameter where `[Quantity]` can only be a numerical value.

Example of usage:

```
sell upc/1231 qty/3
```

Sample output:
```
Successfully sold the following item:

Before Selling: 
Item Name: Item A
UPC Code: 1231
Quantity Available: 3

After Selling: 
Item Name: Item A
UPC Code: 1231
Quantity Available: 0

Sold 3 asdsadsa at a price of $123.0.
```

### Restock an item: `restock`
Restock quantities of an item in the inventory list.

Format: `restock upc/[UPC] qty/[Quantity]`

Required Parameters:
* The `upc/` parameter where `[UPC]` can be only be a numerical value and must exists in the inventory.
* The `qty/` parameter where `[Quantity]` can only be a numerical value.

Example of usage:

```
restock upc/1231 qty/20
```

Sample output:
```
Successfully restocked the following item:

Before Restocking: 
Item Name: asdsadsa
UPC Code: 1231
Quantity Available: 0

After Restocking: 
Item Name: asdsadsa
UPC Code: 1231
Quantity Available: 20
```


### Dashboard: `db`
Shows a dashboard of information related to the system's inventory, user insights and 
session configurations.

Format: `db`

Example of usage:

`db` - It's that simple

Sample output:  
![img_1.png](img_1.png)
### Category: `cat`
Shows list of categories, and/or its items, or a specified category of items.

Format: 
* `cat list`: shows list of all categories in the inventory.
* `cat table`: shows table of all categories and all items in each category.
* `cat [Category]`: shows list of all items in a specified category.

Example of Usage & Expected Output:
```
cat list
____________________________________________________________
Here is the list of categories you have: 
uncategorized
fruits
____________________________________________________________
```
```
cat table
+-----------------+--------------------------------+
| Category        | Name: UPC                      |
+-----------------+--------------------------------+
| uncategorized   | testItem:123456789012,         |
|                 | testItem2:123456789013,        |
|                 | asdsadsa:1231                  |
+-----------------+--------------------------------+
```

```
cat fruit
+-----------------+--------------------------------+
| Category        | Name: UPC                      |
+-----------------+--------------------------------+
| fruit           | apple:123456789012,            |
|                 | pear:123456789013,             |
|                 | oranges:123456789555           |
+-----------------+--------------------------------+
```
### Alert for an item: `alert`
Add alerts that will display when the quantity of an item falls below a set minimum or exceeds a maximum level.

Format:  
`alert add upc/{upc} min/{quantity} ` to set an alert when quantity falls below a minimum  
`alert add upc/{upc} max/{quantity}` to set an alert when quantity exceeds a maximum  
`alert remove upc/{upc} level/min` to remove an alert for the minimum quantity of an item  
`alert remove upc/{upc} level/max` to remove an alert for the maximum quantity of an item  

Examples of usage:  
`alert add upc/1234 min/55`  
`alert add upc/1234 max/100`

Sample output:
```
____________________________________________________________
Successfully added a new alert.
____________________________________________________________
```

Examples of usage:  
`alert remove upc/1234 level/min`  
`alert remove upc/1234 level/max`


Sample output:
```
____________________________________________________________
Successfully removed the alert.
____________________________________________________________
``` 
### Change Autosave Mode: `autosave`

Set whether the program should automatically save the udpated inventory to the inventory data file after every successful
write command issued.

Format: `autosave [on/off]`

Sample input and expected output:
```
autosave on
____________________________________________________________
Auto-save has been enabled!
____________________________________________________________
```

```
autosave off
____________________________________________________________
Auto-save has been disabled!
____________________________________________________________
```


### Exiting the program: `exit`
Exits the MagusStock program.

Format: `exit` or `bye`

Sample output:
```
____________________________________________________________
Hope you had an enjoyable experience. See you next time!
____________________________________________________________
```



## Command Summary
| Action                          |
|---------------------------------|
| Add item (`add`)                |
| Remove item (`remove`)          |
| Edit item (`edit`)              |
| List all items (`list`)         |
| Search items (`search`)         |
| Filter items (`filter`)         |
| Sell items (`sell`)             |
| Restock items (`restock`)       |
| Add and remove alerts (`alert`) |
| View item categories (`cat`)    |
| View history (`history`)        |
| View dashboard (`db`) |
| View all commands (`help`) |
| Set autosave mode (`autosave`) |
| Exit the program (`exit`) |


