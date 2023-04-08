# <span style="color:#00A36C">BagPacker User Guide</span>

---

## <span style="color:#00A36C">Table of contents</span>
* [Introduction](#span-stylecolor00a36c-introduction-span)
* [Quick Start](#span-stylecolor00a36c-quick-start-span)
* [Features](#span-stylecolor00a36c-features-span)
  * [Adding a new item](#span-stylecolor6495ed-adding-a-new-item--span-add)
  * [Deleting an item](#span-stylecolor6495ed-deleting-an-item--span-delete)
  * [Packing an items](#span-stylecolor6495ed-packing-some-items--span-pack)
  * [Fully pack an item](#span-stylecolor6495ed-marking-total-quantity-of-item-as-packed--span-packall)
  * [Unpacking an item](#span-stylecolor6495ed-unpacking-some-items--span-unpack)
  * [Editing item quantity](#span-stylecolor6495ed-editing-item-quantity--span-editquantity)
  * [Listing all items](#span-stylecolor6495ed-listing-all-items--span-list)
  * [Listing all unpacked items](#span-stylecolor6495ed-listing-all-unpacked-items--span-listunpacked)
  * [Deleting the entire list](#span-stylecolor6495ed-deleting-the-entire-list--span-deletelist)
  * [Finding an item](#span-stylecolor6495ed-finding-an-item--span-find)
  * [Help message](#span-stylecolor6495ed-help-message--span-help)
  * [Exiting the application](#span-stylecolor6495ed-exiting-the-application--span-bye)
* [Frequently Asked Questions (FAQ)](#span-stylecolor00a36c-frequently-asked-questions--faq--span)
* [Command Summary](#span-stylecolor00a36c-command-summary-span)

---

## <span style="color:#00A36C">Introduction</span>

BagPacker is an application to help travellers manage their packing list. 
Users can add items of varying quantities to their packing list, delete items, view their packing list and much more.
With BagPacker, you never need to worry about miss-packing again simply use BagPacker to remind you of what
is unpacked and travel with ease of mind.

---

## <span style="color:#00A36C">Quick Start</span>

1. Ensure that you have Java 11 installed.
   * If not, please follow the instructions here for [Windows](https://docs.oracle.com/en/java/javase/11/install/installation-jdk-microsoft-windows-platforms.html#GUID-A7E27B90-A28D-4237-9383-A58B416071CA) and [macOS](https://docs.oracle.com/en/java/javase/11/install/installation-jdk-macos.html#GUID-2FE451B0-9572-4E38-A1A5-568B77B146DE)
2. Download the `jar` file from the **latest release** [here](https://github.com/AY2223S2-CS2113-T14-2/tp/releases)
3. Move this `jar` file into an empty folder on your computer.
4. Open a command line interface, and navigate to the directory with the `jar` file using the `cd` command.
   * For Windows users: search for Command Prompt, and launch it.
   * For macOS users: search for Terminal, and launch it.
5. Execute the following command `java -jar Team_Project.jar`

---

## <span style="color:#00A36C">Features</span>
> **Notes about command format**
> * Words that are fully capitalised (such as `QUANTITY`) indicate that an input is expected from the user.
> * The `/` in `/of` is necessary for `of` to be recognised as a keyword.
> * `NAME` is a case-sensitive input.
> * Additional parameters will be ignored for commands that do not need them.
>   * For example, `deletelist abc123` will be interpreted as `deletelist`.

### <span style="color:#6495ED">Adding a new item:</span> `add`
Adds a new item and its quantity to the packing list.

Format: `add QUANTITY /of NAME`
* `QUANTITY` is the number of items to be packed.
  * It must be a positive integer.
  * It must be more than 0, but not more than 1 million (1,000,000).
  * An invalid `QUANTITY` will result in an error message.
* `NAME` is the name of the item to be packed.  
  * `NAME` is case-sensitive.

<h5> Examples of usage: </h5>

* `add 3 /of jackets`

* `add 2 /of water bottles`

<h5> Expected outcomes: </h5>

BagPacker will add a quantity of 3 jackets to the packing list

```
New item added: [0/3] jackets
```
BagPacker will add a quantity of 2 water bottles to the packing list
```
New item added: [0/2] water bottles
```

### <span style="color:#6495ED">Deleting an item:</span> `delete`
Deletes an item from the packing list.

Format: `delete INDEX`
* `INDEX` is the index of the item to be deleted. 
  * It must be a positive integer.
  * It must be more than 0, but no more than the number of items in the list.
  * An invalid index will result in an error message.

<h5> Example of usage: </h5>

* `delete 2`

<h5> Expected outcome: </h5>

BagPacker will remove the second item from the packing list

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

Examples of usage:
* `pack 3 /of 1`

### <span style="color:#6495ED">Fully pack an item:</span> `packall`
Packs the total quantity of that item in the packing list.

Format: `packall /of INDEX`

* `INDEX` is the index of the item that is being packed.
  * It must be a positive integer that is more than 0, but no more than the number of items in the list.
  * An invalid `INDEX` will result in an error message.

<h5> Example of usage: </h5>

`packall /of 3`

<h5> Expected outcome: </h5>

BagPacker will mark all of the quantities of the third item in the packing list as 'packed'

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

Example of usage:
* `unpack 2 /of 5`

### <span style="color:#6495ED">Editing item quantity:</span> `editquantity`
Edits the total quantity of an item to be packed.

Format: `editquantity QUANTITY /of INDEX`
* `QUANTITY` is the new total quantity of that item to be packed.
  * It must be a positive integer
  * It must be more than 0, but not more than 1 million (1,000,000).
  * It must be at least the value of the current quantity packed.
  * An invalid `QUANTITY` will result in an error message.
* `INDEX` is the index of the item that the total quantity will be changed.
  * It must be a positive integer that is more than 0, but no more than the number of items in the list.
  * An invalid `INDEX` will result in an error message.

Examples of usage:
* `editquantity 200 /of 1`


### <span style="color:#6495ED">Listing all items:</span> `list`
Lists all the items in the packing list.
* Includes the current packed quantity, the total quantity to be packed, and the name of the item.

Format: `list`
* This command is not expecting any parameters. Any additional parameters will be ignored.

Examples of usage:
* `list`

Example output:
``` 
Here are the items in your list
1. [1000000/1000000] jackets
2. [0/5] water bottles
```
List Format explanation:

`ITEM_INDEX. [CURRENTLY_PACKED_QUANTITY/TOTAL_QUANTITY] ITEM_NAME`

### <span style="color:#6495ED">Listing all unpacked items:</span> `listunpacked`
Lists all the items in the packing list that are not fully packed yet.
* Fully packed meaning an item's current quantity packed is the same as the total quantity to be packed.
* Includes the current packed quantity, the total quantity to be packed, and the name of the item.

Format: `listunpacked`
* This command is not expecting any parameters. Any additional parameters will be ignored.

Examples of usage:
* `listunpacked`
* `listunpacked 123`

Example output:
``` 
Here are the unpacked items in your list
1. [19317/1000000] jackets
2. [0/5] water bottles
```
List Format explanation:

`ITEM_INDEX. [CURRENTLY_PACKED_QUANTITY/TOTAL_QUANTITY] ITEM_NAME`


### <span style="color:#6495ED">Deleting the entire list:</span> `deletelist`
Deletes the whole packing list.

>❗ This action is irreversible.

Format: `deletelist`
* This command is not expecting any parameters. Any additional parameters will be ignored.

Example of usage:
* `deletelist`

### <span style="color:#6495ED">Finding an item:</span> `find`
Searches for item names that contain any part of the input keywords.

Format: `find KEYWORDS`
* `KEYWORDS` should contain a phrase that can be found in the names of at least one list item.
  * A `KEYWORD` that cannot be found in the names of at least one list item will result in an error message.

Example of usage:
* `find shirt`

### <span style="color:#6495ED">Help message:</span> `help`
Displays a help message containing the command summary.

Format: `help`
* This command is not expecting any parameters. Any additional parameters will be ignored.

Examples of usage:
* `help`

### <span style="color:#6495ED">Exiting the application:</span> `bye`
Exits BagPacker and saves the packing list.

Format: `bye`
* This command is not expecting any parameters. Any additional parameters will be ignored.

Examples of usage:
* `bye`

[⏫ Go to Table of Contents](#span-stylecolor00a36c-table-of-contents-span) | [⏫ Go to Features](#span-stylecolor00a36c-table-of-contents-span)

---

## <span style="color:#00A36C">Frequently Asked Questions (FAQ)</span>

**Q**: I keep getting a message that says the command is invalid, why? 

**A**: Please check that there are no typos in the command. To check the correct spelling of the commands, please type `help` and hit enter.

[⏫ Go to Table of Contents](#span-stylecolor00a36c-table-of-contents-span) |

---

## <span style="color:#00A36C">Command Summary</span>

| Action description                       | Syntax                      | Remarks                                                                                                              |
|:-----------------------------------------|:----------------------------|:---------------------------------------------------------------------------------------------------------------------|
| Add an item                              | `add QUANTITY /of NAME`     | `QUANTITY` must be between 0 and 1,000,000, not inclusive of 0 and 1,000,000                                         |
| Delete an item                           | `delete INDEX`              | `INDEX` must be a valid list index                                                                                   |
| Pack some of an item                     | `pack QUANTITY /of INDEX`   | Adding `QUANTITY` should not cause the quantity to exceed the total quantity<br/> `INDEX` must be a valid list index |
| Marking total quantity of item as packed | `packall /of INDEX`         | `INDEX` must be a valid list index                                                                                   |
| Unpack some item                         | `unpack QUANTITY /of INDEX` | Subtracting `QUANTITY` should not cause the quantity to be less than 0<br/> `INDEX` must be a valid list index       |      
| List all items                           | `list`                      | Additional parameters will be ignored                                                                                |
| Delete the whole list                    | `deletelist`                | Additional parameters will be ignored                                                                                |
| Help message                             | `help`                      | Additional parameters will be ignored                                                                                |
| Exit                                     | `bye`                       | Additional parameters will be ignored                                                                                |

[⏫ Go to Table of Contents](#span-stylecolor00a36c-table-of-contents-span) | [⏫ Go to Features](#span-stylecolor00a36c-table-of-contents-span)