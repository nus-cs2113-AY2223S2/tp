# User Guide

## Table of contents
- [Introduction](#span-stylecolor00a36c-introduction-span)
- [Quick Start](#span-stylecolor00a36c-quick-start-span)
- [Features](#span-stylecolor00a36c-features-span)
  - [Adding a new item](#span-stylecolor6495ed-adding-a-new-item--span-add)
  - [Deleting an item](#span-stylecolor6495ed-deleting-an-item--span-delete)
  - [Packing some items](#span-stylecolor6495ed-packing-some-items--span-pack)
  - [Unpacking some items](#span-stylecolor6495ed-unpacking-some-items--span-unpack)
  - [Listing all items](#span-stylecolor6495ed-listing-all-items--span-list)
  - [Deleting the entire list](#span-stylecolor6495ed-deleting-the-entire-list--span-deletelist)
  - [Help message](#span-stylecolor6495ed-help-message--span-help)
  - [Exiting the application](#span-stylecolor6495ed-exiting-the-application--span-bye)
- [Frequently Asked Questions (FAQ)](#span-stylecolor00a36c-frequently-asked-questions--faq--span)
- [Command Summary](#span-stylecolor00a36c-command-summary-span)

---

## <span style="color:#00A36C">Introduction</span>

BagPacker is an application to help users manage their packing list.

---

## <span style="color:#00A36C">Quick Start</span>

#### For Windows users:
1. Ensure that you have Java 11 installed.
2. Download the latest `jar` file from [here](https://github.com/AY2223S2-CS2113-T14-2/tp/releases)
3. Move this `jar` file into an empty folder on your computer.
4. Open your Terminal, and enter the directory with the jar file. Search for Command Prompt, and launch it.
5. Execute the following command `java -jar Team_Project.jar`

#### For macOS users:
1. Ensure that you have Java 11 installed.
2. Download the latest `jar` file from [here](https://github.com/AY2223S2-CS2113-T14-2/tp/releases)
3. Create a new folder on your computer where you want to run the program. Move this `jar` file into the folder.
4. Open your Terminal and navigate to the directory with the downloaded `jar` file using the `cd` command.
5. Execute the following command `java -jar Team_Project.jar`

You should now be able to use BagPacker on your device.

---

## <span style="color:#00A36C">Features</span>
> **Notes about command format**
> - `n/`, `i/` and `d/` indicate that a user input is expected.
> - The `/` in `/of` is necessary for `of` to be recognised as a keyword.

### <span style="color:#6495ED">Adding a new item:</span> `add`
Adds a new item and its quantity to the packing list.

Format: `add n/QUANTITY /of d/NAME`
* `QUANTITY` is the number of items to be packed, it should not be too large.
* `NAME` is the name of the item to be packed.  

Examples of usage: 
* `add 3 /of jackets`
* `add 2 /of water bottles`

### <span style="color:#6495ED">Deleting an item:</span> `delete`
Deletes an item from the packing list.

Format: `delete i/INDEX`
* `INDEX` is the index of the list item to be deleted.

Examples of usage:
* `delete 2`

### <span style="color:#6495ED">Packing some items:</span> `pack`
Packs some of an item in the packing list.

Format: `pack n/QUANTITY /of i/INDEX`
* `QUANTITY` is the additional number of that item to be packed. It should not be a negative number, and adding it to the current quantity packed should not exceed the total quantity to be packed.
* `INDEX` is the index of the item that is being packed.

Examples of usage:
* `pack 3 /of 1`

### <span style="color:#6495ED">Unpacking some items:</span> `unpack`
Unpacks some of an item in the packing list.

Format: `unpack n/QUANTITY /of i/INDEX`
* `QUANTITY` is the number of that item to be unpacked. It should not be a negative number, and subtracting it from the current quantity packed should not result in a negative number.
* `INDEX` is the index of the item that is being unpacked.

Examples of usage:
* `unpack 2 /of 5`

### <span style="color:#6495ED">Listing all items:</span> `list`
Lists all the items in the packing list, and includes the current packed quantity, and the total quantity to be packed.

Format: `list`

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

Example of usage:
* `deletelist`

### <span style="color:#6495ED">Help message:</span> `help`
Can't get the results you want? Refer to the help message to see which command should be used.

Format: `help`

Examples of usage:
* `help`

### <span style="color:#6495ED">Exiting the application:</span> `bye`
Done packing for today? Exit BagPacker and continue tomorrow!

Format: `bye`

Examples of usage:
* `bye`

---

## <span style="color:#00A36C">Frequently Asked Questions (FAQ)</span>

**Q**: I keep getting a message that says the command is invalid, why? 

**A**: Please check that there are no typos in the command. To check the correct spelling of the commands, please type `help` and hit enter.

---

## <span style="color:#00A36C">Command Summary</span>

| Action description    | Syntax                          |
|:----------------------|:--------------------------------|
| Add an item           | `add n/QUANTITY /of d/NAME`     |
| Delete an item        | `delete i/INDEX`                |
| Pack some of an item  | `pack n/QUANTITY /of i/INDEX`   |
| Unpack some item      | `unpack n/QUANTITY /of i/INDEX` |
| List all items        | `list`                          |
| Delete the whole list | `deletelist`                    |
| Help message          | `help`                          |
| Exit                  | `bye`                           |

---
back to [table of contents](#table-of-contents)