# User Guide
![img_11.png](img_11.png)
## Contents
- [Introduction](#introduction)
- [Quick Start](#quick-start)
- [Features](#features)
- [Command Summary](#command-summary)

## Introduction

MagusStock is a Java command-line interface (CLI) application designed for inventory management.

## Quick Start

1. Ensure that you have Java 11 or above installed. 
2. Download the latest version of MagusStock from [here](https://github.com/AY2223S2-CS2113-W12-3/tp/releases).
3. Move the `.jar` file to an empty folder.
4. Run the file with the command `java -jar magusstock.jar`.

## Features
- [Adding an item: `add`](#adding-an-item-add-)
- [Editing an item: `edit`](#editing-an-item-edit-)
- [Removing an item: `remove`](#removing-an-item-remove-)
- [Listing all items: `list`](#list-all-items-list-)
- [Searching for item(s): `search`](#search-for-an-item-search-searchupc-)
- [Filtering item(s) by type: `filter`](#filtering-items-filter-)
- [Listing all commands: `help`](#list-all-available-commands-help-)
- [History of item: `history`](#historical-records-of-item-history-)
- [Selling an item: `sell`](#sell-quantity-of-item-sell-)
- [Restocking an item: `restock`](#restock-an-item-restock-)
- [Dashboard: `db`](#dashboard-db-)
- [Category: `cat`](#category-cat-)
- [Alert for item(s): `alert`](#alert-for-an-item-alert-)
- [Autosave of Inventory: `autosave`](#change-autosave-mode-autosave-)
- [Exiting the program: `exit`](#exiting-the-program-exit-)

---
### Adding an item: `add` <a name = "add"></a>
Adds a new item to the inventory list.

Format: `add n/[item_name] upc/[UPC] qty/[quantity] p/[price] c/[category]`

**REQUIRED** parameters:
* The `n/` parameter where `[item_name]` field must be alphanumeric. 
* The `upc/` parameter where `[UPC]` field must be a **non-negative numerical** value.
* The `qty/` parameter where `[quantity]` field must be a **non-negative numerical** value.
* The `p/` parameter where `[price]` field must be a **non-negative numerical** value _(decimals accepted)_.

**OPTIONAL** parameters:
* The `c/` parameter for `[category]` must be alphanumeric. (Defaults to: `uncategorized` if not specified.)

!> **Enforced** valid range for numerical parameters is **0** to **99,999,999**.

#### Example of usage

`add n/HP Laptop upc/2142535453 qty/10 p/1299.99`

`add n/iPhone 11 Pro Max Max upc/2987654323 qty/11 p/1099.99 c/electronics`

#### Sample output
```
add n/iPhone 11 Pro Max Max upc/2987654323 qty/11 p/1099.99 c/electronics
____________________________________________________________
Successfully added the item(s) into the system!
____________________________________________________________
```
---
### Editing an item: `edit` <a name = "edit"></a>
Edit an item's details in the inventory.


Format: `edit upc/[UPC] n/[item_name] qty/[quantity] p/[price] c/[category]`


**REQUIRED** parameters:
* The `upc/` parameter where `[UPC]` must be a **non-negative numerical value** and exists in the inventory.

**OPTIONAL** parameters:
* The `n/` parameter where `[item_name]` field must be alphanumeric.
* The `qty/` parameter where `[quantity]` field must be a **non-negative numerical** value.
* The `p/` parameter where `[price]` field must be a **non-negative numerical value** _(decimals accepted)_.
* The `c/` parameter where `[category]` field must be alphanumeric.

!> **Enforced** valid range for numerical parameters is **0** to **99,999,999**.

#### Example of usage

`edit upc/2142535453 c/laptop`: Searches for the item in the inventory with a `UPC` code of `2142535453`, and change
its `Category` type to `laptop`. <br />

`edit upc/123 n/Orange qty/5 p/2.00`: Searches for the item in the inventory with a `UPC` code of `123`, and change
its `Name` to `Orange`, `quantity` to be set to `5` and `price` will be set to `2.00`.

#### Sample output
```
edit upc/2142535453 c/laptop
____________________________________________________________
Successfully edited the following item:

Before Update: 
Name: HP Laptop Pro
UPC: 2142535453
Price: 1299.99
Quantity: 10
Category: uncategorised

After Update: 
Name: HP Laptop Pro
UPC: 2142535453
Price: 1299.99
Quantity: 10
Category: laptop
____________________________________________________________

```
---
### Removing an item: `remove` <a name = "remove"></a>
Removes an item from the inventory list using either its UPC or index in list.

Format: `remove f/upc [UPC]` or `remove f/index [Index]`

**REQUIRED** parameters:

* The `[UPC]` must be a **non-negative numerical value**
* The `[index]` can only be a **non-negative whole number**.

!> **index** follows 0-indexing. (i.e. The first item in the list is at index 0.)

#### Example of usage

`remove f/upc 123456789`: Removes the item with `UPC` code of `123456789`.

`remove f/index 0`: Removes the item with the `index` of `0` inside the inventory list.

#### Sample output
**Case I:** Remove by UPC
```
remove f/upc 123
---------------------------------------------------------------------------
Successfully removed the following item: 
Name: orange
UPC: 123
Price: 5.0
Quantity: 5
Category: uncategorized
---------------------------------------------------------------------------
```

**Case II:** Remove By Index
```
remove f/index 0
---------------------------------------------------------------------------
Successfully removed the following item: 
Name: apple
UPC: 456
Price: 5.0
Quantity: 5
Category: uncategorized
---------------------------------------------------------------------------
```

---
### List all items: `list` <a name = "list"></a>
Lists all items in the inventory list.

Format: `list`

#### Example of usage

`list`: Lists all the items found in the inventory.

#### Sample output 
**Case I:** When there are items in the inventory

```
list
__________________________________________________________________________

██╗███╗░░██╗██╗░░░██╗███████╗███╗░░██╗████████╗░█████╗░██████╗░██╗░░░██╗
██║████╗░██║██║░░░██║██╔════╝████╗░██║╚══██╔══╝██╔══██╗██╔══██╗╚██╗░██╔╝
██║██╔██╗██║╚██╗░██╔╝█████╗░░██╔██╗██║░░░██║░░░██║░░██║██████╔╝░╚████╔╝░
██║██║╚████║░╚████╔╝░██╔══╝░░██║╚████║░░░██║░░░██║░░██║██╔══██╗░░╚██╔╝░░
██║██║░╚███║░░╚██╔╝░░███████╗██║░╚███║░░░██║░░░╚█████╔╝██║░░██║░░░██║░░░
╚═╝╚═╝░░╚══╝░░░╚═╝░░░╚══════╝╚═╝░░╚══╝░░░╚═╝░░░░╚════╝░╚═╝░░╚═╝░░░╚═╝░░░
Here are the items in your inventory:
+-------+-----------------+--------------+----------+----------+-----------------+
| Index | Name            | UPC          | Quantity | Price    | Category        |
+-------+-----------------+--------------+----------+----------+-----------------+
| 0     | apples          | 1235678910   | 10       | $10.00   | fruit           |
+-------+-----------------+--------------+----------+----------+-----------------+
| 1     | laptop          | 01987654321  | 33       | $2.30    | electronics     |
+-------+-----------------+--------------+----------+----------+-----------------+
| 2     | pencil          | 5678901234   | 15       | $0.10    | uncategorized   |
+-------+-----------------+--------------+----------+----------+-----------------+

__________________________________________________________________________

```

**Case II:** When there is no items in the inventory
```
list
__________________________________________________________________________
There are no items in your inventory.
__________________________________________________________________________
```
---
### Search for an item: `search` / `searchupc` <a name = "search"></a>
Search for item(s) in the inventory list by keywords or UPC.

| Format | Required parameter |
| --- | --- |
| `search [Keywords]` | `[Keywords]` can be an alphanumerical value. |
| `searchupc [UPC]` | `[UPC]` can only be a **non-negative numerical** value. |


!> Note: `search` will find items with all keywords. Hence, the search term `sleeves` will find both `Laptop Sleeves`
and `Clothes Sleeves`, but the search term `laptop slee` will only return the item `Laptop Sleeves`

#### Example of usage:

`search laptop slee` or `searchupc 0123241`

#### Sample output


**Case I:** Search by keywords
```
search laptop slee
__________________________________________________________________________
+-------+-----------------+--------------+----------+----------+-----------------+
| Index | Name            | UPC          | Quantity | Price    | Category        |
+-------+-----------------+--------------+----------+----------+-----------------+
| 0     | laptop sleeves  | 123          | 10       | $40.00   | uncategorized   |
+-------+-----------------+--------------+----------+----------+-----------------+

__________________________________________________________________________
```
**Case II:** Search by UPC

```
searchupc 0123241
__________________________________________________________________________
Here is your item: 
+-------+-----------------+--------------+----------+----------+-----------------+
| Index | Name            | UPC          | Quantity | Price    | Category        |
+-------+-----------------+--------------+----------+----------+-----------------+
| 0     | apples          | 0123241      | 10       | $10.00   | fruit           |
+-------+-----------------+--------------+----------+----------+-----------------+

__________________________________________________________________________
```
---
### Filtering items: `filter` <a name = "filter"></a>


Filters items from the inventory list by price OR category.


| Filter By | Format              | Required parameter                                  |
|-----------|---------------------|-----------------------------------------------------|
| Price     | `filter f/price`    | `p/gt`/`p/get`/`p/lt`/`p/let` followed by `[price]` |
| Category  | `filter f/category` | `[Category keywords]`                               |


**REQUIRED** parameters:
* For `filter f/price`, the `[price]` parameter must be a **non-negative numerical value** within a valid range
* For `filter f/category`, the `[Category keywords]` parameter must be an **alphanumerical value**.


!> **Enforced** valid range for numerical parameters is **0** to **999999999**.


| Price Comparator | Required parameter                     |
|------------------|----------------------------------------|
| `p/gt`           | Items price greater than `[price]`     |
| `p/get`          | Items price greater/equals to`[price]` |
| `p/lt`           | Items price less than `[price]`        |
| `p/let`          | Items price lesser/equals to `[price]` |



#### Example of usage

`filter f/category fruits` or `filter f/price p/gt 10.2`

#### Sample output

**Case I:** Filter by category

```
filter f/category fruit
__________________________________________________________________________
+-------+-----------------+--------------+----------+----------+-----------------+
| Index | Name            | UPC          | Quantity | Price    | Category        |
+-------+-----------------+--------------+----------+----------+-----------------+
| 0     | apples          | 12345        | 10       | $15.00   | fruit           |
+-------+-----------------+--------------+----------+----------+-----------------+
| 1     | watermelons     | 54321        | 5        | $10.00   | fruit           |
+-------+-----------------+--------------+----------+----------+-----------------+
| 2     | oranges         | 6789         | 20       | $15.00   | fruit           |
+-------+-----------------+--------------+----------+----------+-----------------+

__________________________________________________________________________


```

**Case II:** Filter by price

```
filter f/price p/gt 10.2
__________________________________________________________________________
+-------+-----------------+--------------+----------+----------+-----------------+
| Index | Name            | UPC          | Quantity | Price    | Category        |
+-------+-----------------+--------------+----------+----------+-----------------+
| 0     | apples          | 0123241      | 10       | $15.00   | fruit           |
+-------+-----------------+--------------+----------+----------+-----------------+
| 1     | TV              | 987654       | 1        | $1099.00 | uncategorized   |
+-------+-----------------+--------------+----------+----------+-----------------+

__________________________________________________________________________
```
---
### List all available commands: `help` <a name = "help"></a>
Lists all commands available and the command formats.

Format: `help`

#### Example of usage

`help`

#### Sample output
```
help
__________________________________________________________________________
+---------------------------+---------------------------+
| Command                   | Command Format            |
+---------------------------+---------------------------+
| history: shows the        | history UPC               |
| historical commands       |                           |
| executed for an item      |                           |
+---------------------------+---------------------------+
.........INTENTIONALLY TRUNCATED FOR DEMONSTRATION........
+---------------------------+---------------------------+
| db: Displays the          | db                        |
| dashboard of MagusStock   |                           |
+---------------------------+---------------------------+

__________________________________________________________________________
```


---
### Historical records of item: `history` <a name = "history"></a>
Lists historical changes to an item in the inventory list.

Format: `history [UPC]`

#### Example of usage

``history 0123241``

#### Sample output


```
__________________________________________________________________________
Item added at: 12:00 AM, WEDNESDAY, MARCH 29, 2023
Name: Apples
UPC: 0123241
Price: 15.0
Quantity: 10
Category: uncategorized
__________________________________________________________________________
__________________________________________________________________________
At: 12:04 AM, WEDNESDAY, MARCH 29, 2023
Category changed to: fruits
__________________________________________________________________________
__________________________________________________________________________
At: 12:06 AM, WEDNESDAY, MARCH 29, 2023
Sold 5 items
__________________________________________________________________________
__________________________________________________________________________
At: 12:06 AM, WEDNESDAY, MARCH 29, 2023
Bought 50 items
__________________________________________________________________________
__________________________________________________________________________
At: 12:06 AM, WEDNESDAY, MARCH 29, 2023
Price decreased from $15.0 to $5.0
__________________________________________________________________________
__________________________________________________________________________
Name: Apples
UPC: 0123241
Price: 5.0
Quantity: 55
Category: fruits
__________________________________________________________________________
```

---
### Sell quantity of item: `sell` <a name = "sell"></a>
Reduces the quantity of an item in the inventory list.

Format: `sell upc/[UPC] qty/[Quantity]`

**REQUIRED** Parameters:

* The `upc/` parameter, whereby `[UPC]` refers to the identification number assigned to the item at the point 
of **initial addition** of the item.
* The `qty/` parameter, whereby `[Quantity]` refers to the amount of stock to be **DEDUCTED** from the current 
stock levels recorded.

!> `[UPC]` has to be **VALID**, that is, it EXISTS in the database, and has to be a POSITIVE NUMBER and NOT EMPTY.

!> **Enforced** valid `[Quantity]` input range to be from **1** up to the **Current Quantity Level** of the item,
provided that the **Current Quantity Level** is **NOT ZERO**. Strings, Zero(0), Negative Integers and Empty inputs for
`[Quantity]` are not allowed.

!> For both `[UPC]` and `[Quantity]`, the `[` and `]` symbols are **NOT NEEDED** for the input. Refer to the
examples below for reference.

#### Example of Usage
`sell upc/123 qty/5`: Searches for the item of `UPC` code `123`, and if it exists, **DEDUCT** a `quantity` of `5`
items from its current stock levels, provided that the total quantity after selling does not go below 0.

`sell upc/987612345 qty/10`: Searches for the item of `UPC` code `987612345`, and if it exists, **DEDUCT** a 
`quantity` of `10` items from its current stock levels. provided that the total quantity after selling does not go
below 0.

#### Sample output
````
__________________________________________________________________________
sell upc/123 qty/5
__________________________________________________________________________
Successfully sold the following item:

Before Selling: 
Item Name: orange and apples
UPC Code: 123
Quantity Available: 10

After Selling: 
Item Name: orange and apples
UPC Code: 123
Quantity Available: 5

Sold 5 orange and apples at a price of $6.0.
__________________________________________________________________________
````

---
### Restock an item: `restock` <a name = "restock"></a>
Restock quantities of an item in the inventory list.

Format: `restock upc/[UPC] qty/[Quantity]`

**REQUIRED** Parameters:

* The `upc/` parameter  whereby `[UPC]` refers to the identification number assigned to the item at the point 
of **initial addition** of the item.
* The `qty/` parameter whereby `[Quantity]` refers to the amount of stock to be **ADDED** from the current stock 
levels recorded.

!> `[UPC]` has to be **VALID**, that is, it EXISTS in the database, and has to be a POSITIVE NUMBER and NOT EMPTY.

!> **Enforced** valid `[Quantity]` input range to be from **1** to **99,999,999**. Zero(0), negative integers, string
and empty inputs are **NOT** allowed. Ensure that the post-restock quantity does not add up to above 99,999,999. 

!> For both `[UPC]` and `[Quantity]`, the `[` and `]` symbols are **NOT NEEDED** for the input. Refer to the
examples below for reference.

#### Example of Usage
`restock upc/12345 qty/5`: Searches for the item of `UPC` code `12345`, and if it exists, **ADD** a `quantity` of `5`
items to its current stock levels, provided that the total quantity after restocking does **not** exceed 99,999,999.

`restock upc/999 qty/10`: Searches for the item of `UPC` code `999`, and if it exists, **ADD** a
`quantity` of `10` items to its current stock levels, provided that the total quantity after restocking does **not**
exceed 99,999,999.

#### Sample output
```
__________________________________________________________________________
restock upc/12345 qty/5
__________________________________________________________________________
Successfully restocked the following item:

Before Restocking:
Item Name: Computer
UPC Code: 12345
Quantity Available: 100

After Restocking:
Item Name: Computer
UPC Code: 12345
Quantity Available: 105
__________________________________________________________________________
```

---
### Dashboard: `db` <a name = "db"></a>
Shows a dashboard of information related to the system's inventory, user insights and 
session configurations.

Format: `db`

**REQUIRED** Parameters:
* Only the `db` command keyword is needed. 

!> Note: There should **NOT** be any further user inputs after typing `db`.

#### Example of usage
`db`: Opens the dashboard.

#### Sample output
```
db
__________________________________________________________________________

██████╗░░█████╗░░██████╗██╗░░██╗██████╗░░█████╗░░█████╗░██████╗░██████╗░
██╔══██╗██╔══██╗██╔════╝██║░░██║██╔══██╗██╔══██╗██╔══██╗██╔══██╗██╔══██╗
██║░░██║███████║╚█████╗░███████║██████╦╝██║░░██║███████║██████╔╝██║░░██║
██║░░██║██╔══██║░╚═══██╗██╔══██║██╔══██╗██║░░██║██╔══██║██╔══██╗██║░░██║
██████╔╝██║░░██║██████╔╝██║░░██║██████╦╝╚█████╔╝██║░░██║██║░░██║██████╔╝
╚═════╝░╚═╝░░╚═╝╚═════╝░╚═╝░░╚═╝╚═════╝░░╚════╝░╚═╝░░╚═╝╚═╝░░╚═╝╚═════╝░
Overview:
__________________________________________________________________________
Total number of items: 2
Total number of active alerts: 0
Total value of inventory: $155.1
Item with most quantity: laptop (33)
Item with least quantity: laptop (33)
__________________________________________________________________________
Current Session Configurations:
__________________________________________________________________________
AutoSave Mode: TRUE
Inventory Data File Status: CORRUPTED
Alerts Data File Status: MISSING (Will be created if AutoSave is TRUE)
__________________________________________________________________________
List of active alerts:
No alerts to print.
__________________________________________________________________________
```

---
### Category: `cat` <a name = "cat"></a>
Shows list of categories, or a summary table of all categories and their items.

Format: <br /> 
`cat list`: Shows list of all categories in the inventory. <br />
`cat table`: Shows table of all categories and all items in each category.

**REQUIRED** Parameters:
* The `list` which tells the program to show a list of categories, **OR**
* The `table` keyword, which tells the program to show a table of all categories and items in each category.

!> Note: There should **NOT** be any additional user inputs after typing `cat list` or `cat table`. `list` and `table`
should **NOT** be used concurrently.

#### Example of Usage 

`cat list`

`cat table`

#### Sample output
**CASE I:** Show the list of all categories in the inventory.
```
cat list
__________________________________________________________________________
Here is the list of categories you have: 
uncategorized
fruits
__________________________________________________________________________
```
**CASE II:** Show all the categories in the inventory as well as their respective items
```
cat table
__________________________________________________________________________
+-----------------+--------------------------------+
| Category        | Name: UPC                      |
+-----------------+--------------------------------+
| fruit           | apples:1235678910,             |
|                 | watermelon:1034373783742       |
+-----------------+--------------------------------+
| uncategorized   | oranges:1029348576             |
+-----------------+--------------------------------+
__________________________________________________________________________
```

### Alert for an item: `alert` <a name = "alert"></a>
Add alerts that will display messages when the quantity of an item falls below a set minimum or exceeds a maximum level.

Example of Alert Message:
```
__________________________________________________________________________
ALERT: The quantity of apples is below the minimum level of 2.
__________________________________________________________________________
```


Add Alert Format:  
`alert add upc/[UPC] min/[Quantity] ` to set an alert when quantity falls below a minimum  
`alert add upc/[UPC] max/[Quantity]` to set an alert when quantity exceeds a maximum  

**REQUIRED** parameters:

* The `[UPC]` must be a **non-negative numerical value**
* For **MIN alert**: The `[Quantity]` must be a **non-negative whole number** and is **less than** current item's 
quantity
* For **MAX alert**: The `[Quantity]` must be a **non-negative whole number** and is **greater than** current item's
quantity

Remove Alert Format:

`alert remove upc/[UPC] level/min` to remove an alert for the minimum quantity of an item  
`alert remove upc/[UPC] level/max` to remove an alert for the maximum quantity of an item  


**REQUIRED** parameters:

* The `[UPC]` must be a **non-negative numerical value**

#### Examples of usage
`alert add upc/1234 min/55`  
`alert add upc/1234 max/100`

`alert remove upc/1234 level/min`  
`alert remove upc/1234 level/max`

#### Sample output
**Sample I:** Addition of a minimum quantity alert to an item
```
alert add upc/1234 min/55
__________________________________________________________________________
Successfully added a new alert.
__________________________________________________________________________
```

**Sample II:** Removal of a minimum quantity alert for an item
```
alert remove upc/1234 level/min
__________________________________________________________________________
Successfully removed the alert.
__________________________________________________________________________
``` 



Example of
---
### Change Auto save Mode: `autosave` <a name = "autosave"></a>

Set whether the program should automatically save the updated inventory to the inventory data file after every 
successful write command issued.

Format: `autosave [on/off]`

**REQUIRED** Parameters:
* The `on` **OR** `off` parameter, whereby it toggles the auto save function **ON** and **OFF** respectively.

!> Note: if auto save is disabled, the program will **NOT** save on exit. This is because auto save `off` functions
similarly to incognito mode on a browser.

#### Example of usage

`autosave on`: Turns the `autosave` function `on`.

`autosave off`: Turns the `autosave` function `off`.

#### Sample output

```
autosave on
__________________________________________________________________________
Auto-save has been enabled!
__________________________________________________________________________
```

```
autosave off
__________________________________________________________________________
Auto-save has been disabled!
__________________________________________________________________________
```

---
### Exiting the program: `exit` <a name = "exit"></a>
Exits the MagusStock program.

Format: `exit` or `bye`

!> Note: Do **NOT** type additional parameters after typing `exit` or `bye`. 

#### Example of usage

`bye`: Terminates the program.

`exit`: Terminates the program.

#### Sample output:

```
bye
__________________________________________________________________________
Hope you had an enjoyable experience. See you next time!
__________________________________________________________________________
```

---

<a name = "faq"></a>
<a name = "command_summary"></a>
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


