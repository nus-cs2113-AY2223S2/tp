# <span style="color:#00A36C">BagPacker User Guide</span>

BagPacker is a desktop application to help travellers manage their packing list, via a Command Line Interface (CLI).
Users can add items of varying quantities to their packing list, delete items, view their packing list and much more.
With BagPacker, you never need to worry about packing wrongly again. Simply use BagPacker to remind you of what
is unpacked and travel with an ease of mind.

---

## <span style="color:#00A36C">Table of contents</span>

* [Quick Start](#quick-start)
* [Features](#features)
  * [Help message](#help-message-help)
  * [Adding a new item](#adding-a-new-item-add)
  * [Deleting an item](#deleting-an-item-delete)
  * [Packing an item](#packing-an-item-pack)
  * [Packing all of an item](#packing-all-quantities-of-an-item-packall)
  * [Unpacking an item](#unpacking-an-item-unpack)
  * [Unpacking all of an item](#unpacking-all-quantities-of-an-item-unpackall)
  * [Editing item quantity](#editing-item-quantity-editquantity)
  * [Listing all items](#listing-all-items-list)
  * [Listing all unpacked items](#listing-all-unpacked-items-listunpacked)
  * [Deleting the entire list](#deleting-the-entire-list-deletelist)
  * [Finding an item](#finding-an-item-find)
  * [Exiting the application](#exiting-the-application-bye)
* [Frequently Asked Questions (FAQ)](#frequently-asked-questions-faq)
* [Command Summary](#command-summary)

<div style="page-break-after: always;"></div>

## <span style="color:#00A36C">Quick Start</span>

1. Ensure that you have Java `11` installed in your Computer.
   * If not, please follow the instructions here for [Windows](https://docs.oracle.com/en/java/javase/11/install/installation-jdk-microsoft-windows-platforms.html#GUID-A7E27B90-A28D-4237-9383-A58B416071CA) and [macOS](https://docs.oracle.com/en/java/javase/11/install/installation-jdk-macos.html#GUID-2FE451B0-9572-4E38-A1A5-568B77B146DE).
2. Download the `BagPacker.jar` file from the **latest release** [here](https://github.com/AY2223S2-CS2113-T14-2/tp/releases).
3. Copy the `BagPacker.jar` file to the folder you want to use as the _home folder_ for BagPacker.
   * We recommend using an empty folder.
4. Open a command terminal, and navigate to the folder with the `jar` file using the `cd` command.
   * For Windows users: search for Command Prompt, and launch it.
   * For macOS users: search for Terminal, and launch it.
5. Execute the following command `java -jar BagPacker.jar` to run BagPacker. BagPacker should start up, and the following message will be displayed.
    
   ```
    Hi this is,
     ____              _____           _
    |  _ \            |  __ \         | |
    | |_) | __ _  __ _| |__) |_ _  ___| | _____ _ __
    |  _ < / _` |/ _` |  ___/ _` |/ __| |/ / _ \ '__|
    | |_) | (_| | (_| | |  | (_| | (__|   <  __/ |
    |____/ \__,_|\__, |_|   \__,_|\___|_|\_\___|_|
                  __/ |
                 |___/
    
    Enter "help" to find out how to use BagPacker
    _________________________________________________________________________________
    _________________________________________________________________________________
    Welcome back, User!
    ```
   
6. Type a command, and press Enter to execute it. 
To start off, typing `help` and pressing Enter will display a help message.

7. Refer to the [Features section](#features) below for details of each command.

<div style="page-break-after: always;"></div>

## <span style="color:#00A36C">Features</span>
> **Notes about command format**
> * Words in `UPPER_CASE` (such as `QUANTITY`) indicate that an input is expected from the user.
>   * E.g. in `add QUANTITY /of NAME`, `QUANTITY` and `NAME` are parameters which can be used as `add 2 /of shoes`.
> * The `/` in `/of` is necessary for `of` to be recognised as a keyword.
> * `NAME` is a case-sensitive input.
> * The order of parameters has to be strictly followed. 
>   * Not following the given order would result in error messages or incorrect behaviour.
> * Leading and trailing spaces for each input will be ignored.
> * Extraneous parameters will be ignored for commands that do not take in parameters.
>   * For example, `deletelist abc123` will be interpreted as `deletelist`.
> * For more information on integers, please read this [Wikipedia page](https://en.wikipedia.org/wiki/Integer).

### <span style="color:#6495ED">Help message:</span> `help`
Displays a help message containing the command summary and examples of how to use each command.

Format: `help`
* This command is not expecting any parameters. Any additional parameters will be ignored.

Example:

`help`
* Expected outcome:

```
All Commands:
1. add: Adds quantity and name of item to the packing list.
  Example: add 3 /of toothbrush
  Meaning: Add quantity of 3 toothbrushes to the packing list
2. delete: Deletes an item from the packing list.
  Example: delete 1
  Meaning: Removes the first item in the packing list
3. pack: Adds to the current quantity of items packed in the packing list.
  Example: pack 2 /of 3
  Meaning: Packs 2 more quantities of the third item in the packing list
4. packall: Fully pack an item of choice from the packing list.
  Example: packall /of 3
  Meaning: Set the third item in the packing list as fully packed
5. unpack: Deducts from the current quantity of items packed in the packing list.
  Example: unpack 1 /of 2
  Meaning: Unpacks 1 quantity of the second item in the packing list
6. unpackall: Fully unpacks an item of choice from the packing list.
  Example: unpackall /of 3
  Meaning: Set the third item in the packing list as totally not packed yet
7. editquantity: Edit the total quantity of an item to be packed.
  Example: editquantity 3 /of 2
  Meaning: Change the total quantity of the second item to 3
8. list: List all items and their total quantities packed in packing list.
  Example: list
9. listunpacked: List all items and their total quantities if they are not fully packed yet.
  Example: listunpacked
10. deletelist: Deletes all items in the packing list.
  Example: deletelist
11. find: Displays all items in the packingList as a list that contains the keyword used in the command.
  Example: find jacket
12. bye: Stops the BagPacker Application
  Example: bye
 ```


### <span style="color:#6495ED">Adding a new item:</span> `add`
Adds a new item and its quantity to the packing list.

Format: `add QUANTITY /of NAME`
* `QUANTITY` is the number of items to be packed.
  * It must be a positive integer.
  * It must be more than 0, but not more than 1 million (1,000,000).
  * An invalid `QUANTITY` will result in an error message.
* `NAME` is the name of the item to be packed.  
  * `NAME` is case-sensitive.
  * `NAME` accepts alphanumeric and special characters.
  * If there is already an item with the same `NAME`, `QUANTITY` will be added to the total quantity of that item.
 
Examples:

`add 3 /of jackets`
* Adds an item of name `jackets` with a total quantity of `3`.
* Expected outcome: 
  
  ```
  New item added: [0/3] jackets
  ```

`add 2 /of water bottles` and `add 3 /of water bottles`
* Adds an item of name `water botles` with a total quantity of `2`.
* Adds `3` to the total quantity of the index of the item `water bottles`.
* Expected outcome:

  ```
  New item added: [0/2] water bottles
  ```
  and
  ```
  Add to quantity of existing item: [0/5] water bottles
  ```

### <span style="color:#6495ED">Deleting an item:</span> `delete`
Deletes an item from the packing list.

Format: `delete INDEX`
* `INDEX` is the index of the item to be deleted. 
  * It must be a positive integer.
  * It must be more than 0, but no more than the number of items in the list.
  * An invalid index will result in an error message.

Examples:

`delete 2`
* Deletes the item at index `2` from the packing list.
* Expected outcome: 
  
  ```
  [0/2] water bottles removed from the list
  ```

### <span style="color:#6495ED">Packing an item:</span> `pack`
Packs the given amount of that item in the packing list.

Format: `pack QUANTITY /of INDEX`
* `QUANTITY` is the additional number of that item to be packed. 
  * It must be a positive integer that is more than 0.
  * Adding `QUANTITY` to the current quantity packed must not exceed the total quantity to be packed.
  * An invalid `QUANTITY` will result in an error message.
* `INDEX` is the index of the item that is being packed.
  * It must be a positive integer that is more than 0, but no more than the number of items in the list.
  * An invalid `INDEX` will result in an error message.

Example:

`pack 2 /of 1`
* Adds `3` to the packed quantity of the item at index `1` in the packing list.
* Expected outcome:
  
  ```
  Item packed: [2/3] jackets
  ```

### <span style="color:#6495ED">Packing all quantities of an item:</span> `packall`
Packs the total quantity of that item in the packing list.

Format: `packall /of INDEX`

* `INDEX` is the index of the item that is being packed.
  * It must be a positive integer that is more than 0, but no more than the number of items in the list.
  * An invalid `INDEX` will result in an error message.

Example:

`packall /of 3`
* The packed quantity of the item at index `3` becomes its total quantity.
* Expected outcome:
  
  ```
  Item packed: [20/20] socks
  ```

### <span style="color:#6495ED">Unpacking an item:</span> `unpack`
Unpacks the given amount of that item in the packing list.

Format: `unpack QUANTITY /of INDEX`
* `QUANTITY` is the number of that item to be unpacked. 
  * It must be a positive integer that is more than 0.
  * Subtracting `QUANTITY` from the current quantity packed must not result in a negative integer.
  * An invalid `QUANTITY` will result in an error message.
* `INDEX` is the index of the item that is being unpacked.
  * It must be a positive integer that is more than 0, but no more than the number of items in the list.
  * An invalid `INDEX` will result in an error message.

Example:

`unpack 2 /of 5`
* Subtracts `2` from the packed quantity of the item at index `5` in the packing list.
* Expected outcome:
  
  ```
  Item unpacked: [2/10] shirts
  ```

### <span style="color:#6495ED">Unpacking all quantities of an item:</span> `unpackall`
Unpacks the total quantity of that item in the packing list.

Format: `unpackall /of INDEX`

* `INDEX` is the index of the item that is being packed.
  * It must be a positive integer that is more than 0, but no more than the number of items in the list.
  * An invalid `INDEX` will result in an error message.

Example:

`unpackall /of 5`
* The packed quantity of the item at index 5 becomes 0.
* Expected outcome:
  
  ```
  Item unpacked: [0/10] shirts
  ```

### <span style="color:#6495ED">Editing item quantity:</span> `editquantity`
Edits the total quantity of an item to be packed.

Format: `editquantity QUANTITY /of INDEX`
* `QUANTITY` is the new total quantity of that item to be packed.
  * It must be a positive integer.
  * It must be more than 0, but not more than 1 million (1,000,000).
  * It must be at least the value of the current quantity packed.
  * An invalid `QUANTITY` will result in an error message.
* `INDEX` is the index of the item that the total quantity will be changed.
  * It must be a positive integer that is more than 0, but no more than the number of items in the list.
  * An invalid `INDEX` will result in an error message.

Example:

`editquantity 200 /of 1`
* Replaces the total quantity of the item at index `1` with `200`.
* Expected outcome:

  ```
  Item total quantity edited: [0/200] jackets 
  ```


### <span style="color:#6495ED">Listing all items:</span> `list`
Lists all the items in the packing list.
* Includes the current packed quantity, the total quantity to be packed, and the name of the item.

Format: `list`
* This command is not expecting any parameters. Any additional parameters will be ignored.

Example:

`list` and `list abc1123*#+`
* Both will give the same output - return a list of all the items in the packing list.
* Format explanation: `ITEM_INDEX. [CURRENTLY_PACKED_QUANTITY/TOTAL_QUANTITY] ITEM_NAME`.
* Expected outcome:

  ``` 
  Here are the items in your list
  1. [1000000/1000000] jackets
  2. [0/5] water bottles
  ```

### <span style="color:#6495ED">Listing all unpacked items:</span> `listunpacked`
Lists all the items in the packing list that are not fully packed yet.
* Fully packed meaning an item's current quantity packed is the same as the total quantity to be packed.
* Includes the current packed quantity, the total quantity to be packed, and the name of the item.

Format: `listunpacked`
* This command is not expecting any parameters. Any additional parameters will be ignored.

Example:

`listunpacked` and `listunpacked abc123*#+`
* Both will give the same output - return a list of unpacked items.
* Format explanation: `INDEX. [CURRENTLY_PACKED_QUANTITY/TOTAL_QUANTITY] ITEM_NAME`
  * ❗ The `INDEX` here is not the `ITEM_INDEX` in the `list` command. `INDEX` is the number of the item in this list of unpacked items.
* Expected outcome:

  ```
  Here are the unpacked items in your list
  1. [10/200] jackets
  2. [0/5] water bottles
  ```

### <span style="color:#6495ED">Deleting the entire list:</span> `deletelist`
Deletes the whole packing list.

>❗ This action is irreversible.

Format: `deletelist`
* This command is not expecting any parameters. Any additional parameters will be ignored.

Example:

`deletelist` and `deletelist abc123*#+`
* Both will give the same output - delete the entire packing list of items.
* Expected outcome:

  ```
  Packing list deleted
  ```

### <span style="color:#6495ED">Finding an item:</span> `find`
Searches for item names that contain any part of the input keywords.

Format: `find KEYWORDS`
* `KEYWORDS` should contain a phrase that can be found in the names of at least one list item.
  * A `KEYWORD` that cannot be found in the names of at least one list item will result in an error message.
* `KEYWORDS` is case-sensitive.
  * E.g. `ipad` will not return `iPad`.
* The order of the words in `KEYWORDS` matters.
  * E.g. `winter jacket` will not return `jacket winter`.
* `find` will only search the item names.
* `KEYWORDS` need not be complete words.
  * E.g. `sh` will return `shirts`.
* Only item names that completely contain the input phrase will be returned.
  * E.g. `ter sh` will return `winter shawl` and `winter shirt`.

Example:

`find shirt`
* The names of all the items in the packing list will be searched.
* Expected outcome:

  ```
  There are 2 items(s) in your list containing shirt: 
  1. [0/3] inner shirts
  2. [0/4] outer shirts
  ```

### <span style="color:#6495ED">Exiting the application:</span> `bye`
Exits BagPacker and saves the packing list.

Format: `bye`
* This command is not expecting any parameters. Any additional parameters will be ignored.

Example:
`bye`
* Exits BagPacker
* Expected outcome:

  ```
  Bye thanks for using,
   ____              _____           _
  |  _ \            |  __ \         | |
  | |_) | __ _  __ _| |__) |_ _  ___| | _____ _ __
  |  _ < / _` |/ _` |  ___/ _` |/ __| |/ / _ \ '__|
  | |_) | (_| | (_| | |  | (_| | (__|   <  __/ |
  |____/ \__,_|\__, |_|   \__,_|\___|_|\_\___|_|
                __/ |
               |___/
  ```

[⏫ Go to Table of Contents](#table-of-contents) | [⏫ Go to Features](#features)

<div style="page-break-after: always;"></div>

## <span style="color:#00A36C">Frequently Asked Questions (FAQ)</span>

**Q**: I keep getting a message that says the command is invalid, why? 

**A**: Please check that there are no typos in the command. To check the correct spelling of the commands, please type `help` and hit enter.


**Q**: I am following the suggested input, why is it still giving me an error?

**A**: For command inputs that have item quantity and item index, the / character is necessary before the of (e.g. `pack 1 /of 1` is valid but `pack 1 of 1` is not)


**Q**: Why are some commands affected by extra random inputs before or after the full command while others aren't?

**A**: The commands that are not affected by extra inputs are those that have no variables (e.g. `list`, `listunpacked`, `bye`, and `help`)


**Q**: Why does `editquantity` command give me an error for a positive integer quantity?

**A**: Your new input quantity may be considered invalid as it is smaller than the current packed amount of the item. (You cannot set the total quantity to be less than what is currently packed)


**Q**: Why does my list have duplicate items?

**A**: Item names are caps sensitive, you could have accidentally input two items with the same name but differing in capitalisation. 


**Q**: Why is the list indexes for `find` command not sequential?

**A**: The index printed for `find` comes from the full packing list which can be retrieved from `list` command.  


[⏫ Go to Table of Contents](#table-of-contents) |

<div style="page-break-after: always;"></div>

## <span style="color:#00A36C">Command Summary</span>

| Action description                  | Syntax                            | Remarks                                                                                                              |
|:------------------------------------|:----------------------------------|:---------------------------------------------------------------------------------------------------------------------|
| Help                                | `help`                            | Additional parameters will be ignored                                                                                |
| Adding a new item                   | `add QUANTITY /of NAME`           | `QUANTITY` must be between 0 and 1,000,000, not inclusive of 0 and 1,000,000                                         |
| Deleting an item                    | `delete INDEX`                    | `INDEX` must be a valid list index                                                                                   |
| Packing an item                     | `pack QUANTITY /of INDEX`         | Adding `QUANTITY` should not cause the quantity to exceed the total quantity<br/> `INDEX` must be a valid list index |
| Packing all quantities of an item   | `packall /of INDEX`               | `INDEX` must be a valid list index                                                                                   |
| Unpacking an item                   | `unpack QUANTITY /of INDEX`       | Subtracting `QUANTITY` should not cause the quantity to be less than 0<br/> `INDEX` must be a valid list index       |
| Unpacking all quantities of an item | `unpackall /of INDEX`             | `INDEX` must be a valid list index                                                                                   |
| Editing item quantity               | `editquantity QUANTITY /of INDEX` | `QUANTITY` must be between 0 and 1,000,000, not inclusive of 0 and 1,000,000<br/> `INDEX` must be a valid list index | 
| Listing all items                   | `list`                            | Additional parameters will be ignored                                                                                |
| Listing all unpacked items          | `listunpacked`                    | Additional parameters will be ignored                                                                                |
| Deleting the entire list            | `deletelist`                      | Additional parameters will be ignored                                                                                |
| Finding an item                     | `help`                            | Additional parameters will be ignored                                                                                |
| Exiting the application             | `bye`                             | Additional parameters will be ignored                                                                                |

[⏫ Go to Table of Contents](#table-of-contents) | [⏫ Go to Features](#features)