# User Guide

## Introduction

Food Supply Tracker (FST) is a desktop app for managing food supplies, optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, FST can get your food management tasks done faster than traditional GUI apps.

---
+ [Quick Start](#quick-start)
+ [Features](#features)
  + [Viewing help: `help`](#viewing-help)
  + [Adding a food product: `add`](#adding-a-food-product)
  + [Listing all food products: `list`](#listing-all-food-products)
  + [Removing a food product: `remove`](#removing-a-food-product) 
  + [Finding food products by name: `find`](#finding-food-products-by-name-and-attributes)
  + [Updating food products by index: `update`](#updating-food-products-by-index)
  + [Clears the food list: `clear`](#clearing-the-food-list)
  + [Exit FST Program: `exit`](#exiting-fst)
  + [Editing the foodTrackerList text file](#editing-foodtrackerlist-text-file)
+ [FAQ](#faq)
+ [Command Summary](#command-summary)

---

## Quick Start

1. Ensure you have Java 11 installed in your Computer.
2. Download the latest Food Supply Tracker [here](https://github.com/AY2223S2-CS2113-W13-3/tp/releases).
3. Copy the file to the folder you want your FoodSupplyTracker to be in. 
4. Open a command terminal, navigate into the folder with your jar file using cd and run the application with java -jar tp.jar
5. Type the command in the command box and press Enter to execute it. E.g. typing help and pressing Enter will open the help window.
6. Go to [Command Summary](#command-summary) for a quick start on command format.

---

## Features

##### Quick notes about the command format:
1. Words in UPPER_CASE are the parameters to be supplied by the user.
2. Parameters in the `{ }` are optionals, and `{ }` the bracket characters cannot be included in the command line.
3. Optional flags are put in curly braces.
4. Parameter is expected only once in the command. If you specify it multiple times, it will be deemed as invalid command.

### Viewing help

`help`- Show a message explaining how to access the help page and the command specified.
* Any extraneous parameters input after the command `help` will be ignored.

  (e.g. `help 123 abcde` will still work as `help`)

- When a filter (prefix <code>--</code>) is applied, the help message will display the helper for the specified command.
- By default, all available commands and the link to the user guide would be displayed.

Format: `help {--COMMAND_WORD}`

Example of usage:

Input:

<code>help --remove --find</code>

Output:
```
______________________________
Command 'remove': This command removes the food product from the food supply tracker based on its index.
Format: remove INDEX


Command 'find': This command filters the list according to the flags applied.
Format: find {PRODUCT_NAME} {--fresh} {--expired} {--flag ATTRIBUTE_NAME}
List of valid flags as follows:
   --fresh: list non-expired items
   --expired: list expired items
   --u: filter by unit
   --q: filter by quantity
   --c: filter by category
It is possible to have multiple flags (all flags will be accounted for. i.e. with more flags, the list can only get smaller or remain the same).


List of commands: 'add', 'list', 'remove', 'find', 'update', 'clear', 'exit'.
For more detailed information on usage of specific command, type: help --COMMAND
Refer to our user guide for more in-depth details on how to use Food Supply Tracker:
https://ay2223s2-cs2113-w13-3.github.io/tp/UserGuide.html
______________________________
```

### Adding a food product 

`add` - Add a food product to the list of food items.

Format: `add --n FOOD_NAME --e DD/MM/YYYY {--c CAT --q QUANTITY --u UNITS}`

* The parameter cannot contain any punctuations (except `QUANTITY` can contain `.` decimal point), or else it will return as incorrect command.
* The order of format should be strictly followed.
* `--n FOOD_NAME` and `--e DD/MM/YYYY` are compulsory.
* `--c CAT`and`--q QUANTITY -u UNITS` are optional.
  * `--u UNIT` must be added together with `--q QUANTITY`.
  * `--q QUANTITY` can be added without `--u UNIT`
    * For example, a proper command can be `add --n milk --e 21/03/2025 --q 10.0`. 
However, it cannot be `add --n milk --e 21/03/2025 --u packets`
* For `CATEGORY`, we have `FRUIT, VEGETABLE, MEAT, DAIRY, GRAIN, SEAFOOD, BEVERAGE, OTHERS`
any other category or no category added will be deemed as `OTHERS`.
* For `UNIT`, we have `mg`, `g`, `kg`, `ml`, `l`, `unit`, `units`, `serving`, `servings`, `packet`, `packets`, 
`box` and `boxes`. 
  * Default `UNIT` will be deemed as `unit` or `units` according to the value of `QUANTITY`.
  * If `QUANTITY` added is more than 1, then the `UNIT` will automatically change to plural form such as `units`, 
  `servings`, `packets` and `boxes` if applicable.
  * If `QUANTITY` added is 1, then the `UNIT` will automatically change to singular form such as `unit`, `serving`,
  `packet` and `box` if applicable.

Examples of usage:

Input 1: `add --n milk --e 21/03/2025 --c dairy --q 10 --u packets`

Output 1: 

(The product is added on 06/04/2023, thus it shows 715 days left. The display of remaining freshness date will vary,
based on your current date)
```
______________________________
milk (fresh) 
       Expiry date: 21/03/2025 (715 days left)
       Category: dairy
       Remaining quantity: 10.0 packets

I have added this product! :)
______________________________
```

Input 2: `add --n mike's milk --e 21/03/2025 --c da,iry --q 10 -u pac^kets`
* Parameter cannot contain any symbol or punctuation such as the `'` in `mike's milk` and `, `in `da,iry` and `^` in `pac^kets`.

Output 2:

```
______________________________
Oops! Incorrect command format. Type 'help' to see more!!
______________________________

```

Input 3: `add --n bread --e 11/11/2023 --q 2 --u box`

Output 3:

* `QUANTITY` of `bread` is more than 1
* Unit of `bread` is `boxes` instead of `box` which is the value of `UNIT`.

```
______________________________
bread (fresh) 
       Expiry date: 11/11/2023 (218 days left)
       Category: others
       Remaining quantity: 2.0 boxes

I have added this product! :)
______________________________
```

### Listing all food products

`list` - List all food products available in the tracker.
* Maximum number of food products in the list is 9999.
* Any extraneous parameters input after the command `list` will be ignored.

  (e.g. `list 123 abcde` will still work as `list`)

- The list is sorted in order of the expiry dates, regardless of the order of food that is added.
- All food products will be listed with its index, name, freshness, expiry date, number of days left / expired,
category and quantity together with its units.
- Food products without any quantity added or with its quantity as `0.0` will be listed with its index, name, freshness, 
expiry date, number of days left / expired and category.

  - Number of days left will decrease as the day passed by.
  - Number of days expired will increase as the day passed by.
  - Freshness of food will be `(fresh)` if its expiry date is after today (number of days left > 0).
  - Freshness of food will be `(expired)` if its expiry date is today and before today (number of days left <= 0).

Format: `list`

Example of usage:

(Number of days depends on today's date: 07/04/2023)

Input: `list`

Output:
```
______________________________
Below are the food list: 
1. Blueberry (expired) 
       Expiry date: 03/04/2023 (expired 4 days)
       Category: fruit
       Remaining quantity: 2.0 boxes
2. strawberry (fresh) 
       Expiry date: 08/04/2023 (1 days left)
       Category: others
3. bread (fresh) 
       Expiry date: 14/04/2023 (7 days left)
       Category: others
4. Blueberry Yogurt (fresh) 
       Expiry date: 23/04/2023 (16 days left)
       Category: dairy
       Remaining quantity: 1.0 packet

You now have 4 food products in your lists.
______________________________
```

### Removing a food product

`remove` - Remove a food product from the list based on its index.

Format: `remove INDEX`

* Deletes the food according to its `INDEX` in the list.
* `INDEX` must be represented by an integer `i.e. 1, 2, 3`
  * `INDEX` will result in an error message if it is input as a `String` or `Double`.
* Out-of-bounds `INDEX` will result in an error message.

Example of usage:

Input: <code>remove 1</code>

Output:
```
______________________________
Removed 'milk' from the food supply list.
There is/are now 1 item(s) in the list.
______________________________
```

### Finding food products by name and attributes
`find` - List all food products with matching name and filters

**Format:** `find {TERM} {--fresh} {--expired} {--flag ATTRIBUTE_NAME}`

Allowed flags to find the food item:
* fresh items: `--fresh`
* expired items: `--expired`
* unit: `--u {UNIT}`
* quantity: `--q {number}`
* category: `--c {CATEGORY}`

**Notes:**
- The term is optional, and it's possible to only use filters for finding products
  - E.g., `find --fresh` will give all the fresh items
- The search is case-insensitive.
  - E.g. "eggs" will match "Eggs"
- Parts of words will be matched.
  - E.g. "egg" will match "eggs"
- Using empty query will output user error.
  - E.g. typing `find` will result in "No term or flag provided"
- Using both `--fresh` and `--expired` will give no results.
  - E.g. typing `find --fresh --expired` will result in "No food found for such query"

**Example of Usage:**

(Number of days depends on today's date)

**Input 1:** `find blueberry`

**Output 1:**
```
______________________________
1. Blueberry (expired) 
       Expiry date: 03/04/2023 (expired 4 days)
       Category: fruit
       Remaining quantity: 2.0 boxes
2. Blueberry Yogurt (fresh) 
       Expiry date: 23/04/2023 (16 days left)
       Category: dairy
       Remaining quantity: 1.0 packet

Found 2 of food items
______________________________
```

**Input 2:** `find --expired`

**Output 2:**
```
______________________________
1. Blueberry (expired) 
       Expiry date: 03/04/2023 (expired 4 days)
       Category: fruit
       Remaining quantity: 2.0 boxes

Found 1 of food items
______________________________
```

**Input 3:** `find blueberry --fresh`

**Output 3:**
```
______________________________
1. Blueberry Yogurt (fresh) 
       Expiry date: 23/04/2023 (16 days left)
       Category: dairy
       Remaining quantity: 1.0 packet

Found 1 of food items
______________________________
```
**Input 4:** `find blueberry --c dairy`

**Output 4:**
```
______________________________
1. Blueberry Yogurt (fresh) 
       Expiry date: 23/04/2023 (16 days left)
       Category: dairy
       Remaining quantity: 1.0 packet

Found 1 of food items
______________________________
```

**Input 5:** `find --u ml`

**Output 5:**
```
______________________________
No food found for such query
______________________________
```

### Updating food products by index
`update` - Change any attribute based on the index `i` in the list and
values of flags provided

Format: `update i {flags}`

Allowed flags to update the food item attributes:
* name: `--n {string}`
* expiry date: `--e {dd/mm/yyyy}`
* unit: `--u {UNIT}`
* quantity: `--q {number}` 
* category: `--c {CATEGORY}`

**Notes:**
* Multiple attributes can be changed at once by appending different flags.
* `update --u` can be applied individually only if its quantity is more than 0.0

**Example of Usage**

**Input 1:** <code>update 2 --q 10</code>

**Output 1:**

* Before `update` command:
```
______________________________
2. strawberry (fresh) 
       Expiry date: 08/04/2023 (1 days left)
       Category: others
       Remaining quantity: 20.0 boxes
______________________________
```

* After `update` command:
```
______________________________
Updated food item successfully! 
strawberry (fresh) 
       Expiry date: 08/04/2023 (1 days left)
       Category: others
       Remaining quantity: 10.0 boxes
______________________________
```

**Input 2:** `update 2 --u packets`

**Output 2:**

* Before `update` command:
```
______________________________
2. strawberry (fresh) 
       Expiry date: 08/04/2023 (1 days left)
       Category: others
       Remaining quantity: 10.0 boxes
______________________________
```
* After `update` command:
```
______________________________
Updated food item successfully! 
strawberry (fresh) 
       Expiry date: 08/04/2023 (1 days left)
       Category: others
       Remaining quantity: 10.0 packets
______________________________
```

**Input 3:** `update 2 --q 1 --u boxes`

**Output 3:**

* Before `update` command:
```
______________________________
2. strawberry (fresh) 
       Expiry date: 08/04/2023 (1 days left)
       Category: others
       Remaining quantity: 10.0 packets
______________________________
```

* After `update` command:
  * Quantity of `strawberry` is 1. 
  * Unit of `strawberry` is `box` instead of `boxes` (user command). 
```
______________________________
Updated food item successfully! 
strawberry (fresh) 
       Expiry date: 08/04/2023 (1 days left)
       Category: others
       Remaining quantity: 1.0 box
______________________________
```

**Input 4:** `update 1 --u kg`

**Output 4:**

* Before `update` command (`quantity = 0`):
```
______________________________
2. strawberry (fresh) 
       Expiry date: 08/04/2023 (1 days left)
       Category: others
______________________________
```

* After `update` command:
  * The program notifies of inability to change unit when quantity is zero

```
______________________________
Please set the quantity to change a unit
______________________________
```

**Input 5:** `update 1 --u kg --q 5.3`

**Output 5:**

* Before `update` command (`quantity = 0`):
```
______________________________
2. strawberry (fresh) 
       Expiry date: 08/04/2023 (1 days left)
       Category: others
______________________________
```

* After `update` command:
  * The program successfully updates quantity and unit together

```
______________________________
Updated food item successfully! 
strawberry (fresh)
       Expiry date: 08/05/2023 (30 days left)
       Category: others
       Remaining quantity: 5.3 kg
______________________________
```

### Clearing the food list
`clear` - This command will clear the entire food list.
* Any extraneous parameters input after the command `clear` will be ignored.
  
  (e.g. `clear 123 abcde` will still work as `clear`)

Output:

```
______________________________
Clearing the food list as requested...
______________________________
```

### Exiting FST
`exit` - This command will save the food list in an external file before closing the program.
* Any extraneous parameters input after the command `exit` will be ignored.

  (e.g. `exit 123 abcde` will still work as `exit`)

### Editing foodTrackerList text file
It is possible for users to edit their food list without using the program. However, the editing should take when the
program is not running as the edits will not be saved otherwise (the program has higher priority).

#### The syntax and logic of the file is as follows
* Syntax:
  * `|n` indicates the name of the food 
  * `|e` indicates the expiry date
  * `|q` indicates the quantity 
  * `|u` indicates the unit
  * `|c` indicates the category
* When more than one of the same identifier is used, the value associated with the last identifier will be used.
* It is possible for optional attributes which includes `quantity`, `unit` and `category` to be not included.
* Compulsory attributes such as `name` and `expiry date` have to be included or else the line of data will be ignored.

## FAQ

**Q1**: Do I need other software to support FST?

**A**: No, you do not need any other software to support FST.

**Q2**: I am unable to add the food product./I cannot list the food items./ I cannot remove the items.

**A**: Please take note of the command line format shown in the features section. Type ‘help’ to show the command line and please follow the command line exactly. Moreover, please check that the parameters are also in the correct format to avoid meaningless addition of food product.

**Q3**: Is searching case-sensitive?

**A**: No, and items matching at least one keyword will be returned.

**Q4**: Can I write creative names for my food items?

**A**: Yes, you may do that to increase the amusement when listing the food items. However, it might be difficult for you to search for that specific item.

**Q5**: What if I add the item's name incorrectly?

**A**: Currently, we have not implemented the edit features. Please remove the item that you created wrongly and add a new food product.

**Q6**: Can I export my food list into CSV files?

**A**: Sorry, currently FST does not have such features, but we will work on it in the future.

**Q7**:How do I transfer my data to another Computer?

**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous FST home folder.

---

## Command Summary

* Help - `help {--COMMAND_WORD}`
  * e.g. <code>help --update --add</code> 
* Add - `add --n FOOD_NAME --e DD/MM/YYYY {--c CAT --q QUANTITY --u UNITS}`
  * All possible add command format:
    * e.g. `add --n Red Mill Granola --e 20/05/2025`
    * e.g. `add --n Red Mill Granola --e 20/05/2025 --c others`
    * e.g. `add --n Red Mill Granola --e 20/05/2025 --q 10`
    * e.g. `add --n Red Mill Granola --e 20/05/2025 --c others --q 10`
    * e.g. `add --n Red Mill Granola --e 20/05/2025 --q 10 --u packets`
    * e.g. `add --n Red Mill Granola --e 20/05/2025 --c others --q 10 --u packets`
* List - `list`
* Remove - `remove INDEX_NUMBER`
  * e.g. `remove 1`
* Find - `find KEYWORD {--fresh} {--expired} {--ATTRIBUTE_FLAG}`
  * e.g. `find egg`, `find egg --fresh`, `find --expired`, `find --c`, `find --u` 
* Update - `update INDEX --ATTRIBUTE_FLAG UPDATED_VALUE`
  * e.g. `update 2 --q 1`, `update 2 --q 1 --u packet`, `update 2 --q 1 --u packet --c fruit --n potato --e 05/05/2024`
* Clear - `clear`
* exit - `exit`