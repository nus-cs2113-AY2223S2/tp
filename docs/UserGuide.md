# <span style="color:#00A36C">BagPacker User Guide</span>

---

## <span style="color:#00A36C">Table of contents</span>
* [Introduction](#span-stylecolor00a36c-introduction-span)
* [Quick Start](#span-stylecolor00a36c-quick-start-span)
* [Features](#span-stylecolor00a36c-features-span)
  * [Adding a new item](#span-stylecolor6495ed-adding-a-new-item--span-add)
  * [Deleting an item](#span-stylecolor6495ed-deleting-an-item--span-delete)
  * [Packing some items](#span-stylecolor6495ed-packing-some-items--span-pack)
  * [Packing all quantity of items](#span-stylecolor6495ed-marking-total-quantity-of-item-as-packed--span-packall)
  * [Unpacking some items](#span-stylecolor6495ed-unpacking-some-items--span-unpack)
  * [Listing all items](#span-stylecolor6495ed-listing-all-items--span-list)
  * [Deleting the entire list](#span-stylecolor6495ed-deleting-the-entire-list--span-deletelist)
  * [Help message](#span-stylecolor6495ed-help-message--span-help)
  * [Exiting the application](#span-stylecolor6495ed-exiting-the-application--span-bye)
* [Frequently Asked Questions (FAQ)](#span-stylecolor00a36c-frequently-asked-questions--faq--span)
* [Command Summary](#span-stylecolor00a36c-command-summary-span)

---

## <span style="color:#00A36C">Introduction</span>

Meant for travellers, BagPacker is an application to help travellers manage their packing list. 
Users can add items of multiple quantities to their packing list, delete items, and view their packing lists.

---

## <span style="color:#00A36C">Quick Start</span>

1. Ensure that you have Java 11 installed.
   * If not, please follow the instructions here for [Windows](https://docs.oracle.com/en/java/javase/11/install/installation-jdk-microsoft-windows-platforms.html#GUID-A7E27B90-A28D-4237-9383-A58B416071CA) and [macOS](https://docs.oracle.com/en/java/javase/11/install/installation-jdk-macos.html#GUID-2FE451B0-9572-4E38-A1A5-568B77B146DE)
2. Download the `jar` file from the **latest release** [here](https://github.com/AY2223S2-CS2113-T14-2/tp/releases)
3. Move this `jar` file into an empty folder on your computer.
4. Open a command line interface, and navigate to the directory with the `jar` file using the `cd` command.
   * For Windows users: search for Command Prompt, and launch it.
   * For macOS users: search for Terminal, and launch it.
6. Execute the following command `java -jar Team_Project.jar`

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

### <span style="color:#6495ED">Packing some items:</span> `pack`
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

### <span style="color:#6495ED">Marking total quantity of item as packed:</span> `packall`
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

### <span style="color:#6495ED">Unpacking some items:</span> `unpack`
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
1. [1000000/1000000] tooth
2. [0/1] toothpaste
```
Format explanation:

`ITEM_INDEX. [CURRENTLY_PACKED_QUANTITY/TOTAL_QUANTITY] ITEM_NAME`

### <span style="color:#6495ED">Deleting the entire list:</span> `deletelist`
Want to start on a new packing list? This command will clear the whole packing list. 
Be careful, this action is irreversible.

Format: `deletelist`
* This command is not expecting any parameters. Any additional parameters will be ignored.

Example of usage:
* `deletelist`

### <span style="color:#6495ED">Help message:</span> `help`
Can't get the results you want? Refer to the help message to see which command should be used.

Format: `help`
* This command is not expecting any parameters. Any additional parameters will be ignored.

Examples of usage:
* `help`

### <span style="color:#6495ED">Exiting the application:</span> `bye`
Done packing for today? Exit BagPacker and continue tomorrow!

Format: `bye`
* This command is not expecting any parameters. Any additional parameters will be ignored.

Examples of usage:
* `bye`

[Go to Table of Contents](#span-stylecolor00a36c-table-of-contents-span) | [Go to Features](#span-stylecolor00a36c-table-of-contents-span)

---

## <span style="color:#00A36C">Frequently Asked Questions (FAQ)</span>

**Q**: I keep getting a message that says the command is invalid, why? 

**A**: Please check that there are no typos in the command. To check the correct spelling of the commands, please type `help` and hit enter.

[Go to Table of Contents](#span-stylecolor00a36c-table-of-contents-span)

---

## <span style="color:#00A36C">Command Summary</span>

| Action description                       | Syntax                      | Remarks                                                                                                                          |
|:-----------------------------------------|:----------------------------|:---------------------------------------------------------------------------------------------------------------------------------|
| Add an item                              | `add QUANTITY /of NAME`     | `QUANTITY` must be between 0 and 1,000,000, not inclusive of 0 and 1,000,000                                                     |
| Delete an item                           | `delete INDEX`              | `INDEX` must be a valid list index                                                                                               |
| Pack some of an item                     | `pack QUANTITY /of INDEX`   | <li>Adding `QUANTITY` should not cause the quantity to exceed the total quantity</li><li>`INDEX` must be a valid list index</li> |
| Marking total quantity of item as packed | `packall /of INDEX`         | `INDEX` must be a valid list index                                                                                               |
| Unpack some item                         | `unpack QUANTITY /of INDEX` | <li>Subtracting `QUANTITY` should not cause the quantity to be less than 0</li><li>`INDEX` must be a valid list index</li>       |      
| List all items                           | `list`                      | Additional parameters will be ignored                                                                                            |
| Delete the whole list                    | `deletelist`                | Additional parameters will be ignored                                                                                            |
| Help message                             | `help`                      | Additional parameters will be ignored                                                                                            |
| Exit                                     | `bye`                       | Additional parameters will be ignored                                                                                            |

[Go to Table of Contents](#span-stylecolor00a36c-table-of-contents-span) | [Go to Features](#span-stylecolor00a36c-table-of-contents-span)