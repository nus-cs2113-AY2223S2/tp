# User Guide

## Introduction

BagPacker is an application to help users manage their packing list.

## Quick Start

1. Ensure that you have Java 11 above installed.
2. Download the jar file, and move it into an empty folder.
3. Open a command line interface, and enter the directory with the jar file.
4. Execute the following command `java -jar Team_Project.jar`

## Features

### Add a new item: add
Adds a new item and its quantity to the packing list.

Format: `add n/QUANTITY /of d/NAME`
* `QUANTITY` is the number of items to be packed, it should not be too large.
* `NAME` is the name of the item to be packed.  

Examples of usage: 
* `add 3 /of jackets`
* `add 2 /of water bottles`

### Delete an item: delete
Deletes an item from the packing list.

Format: `delete i/INDEX`
* `INDEX` is the index of the list item to be deleted.

Examples of usage:
* `delete 2`

### Pack some items: pack
Packs some of an item in the packing list.

Format: `pack n/QUANTITY /of i/INDEX`
* `QUANTITY` is the additional number of that item to be packed. It should not be a negative number, and adding it to the current quantity packed should not exceed the total quantity to be packed.
* `INDEX` is the index of the item that is being packed.

Examples of usage:
* `pack 3 /of 1`

### Unpacks some items: unpack
Unpacks some of an item in the packing list.

Format: `unpack n/QUANTITY /of i/INDEX`
* `QUANTITY` is the number of that item to be unpacked. It should not be a negative number, and subtracting it from the current quantity packed should not result in a negative number.
* `INDEX` is the index of the item that is being unpacked.

Examples of usage:
* `unpack 2 /of 5`

### List all items: list
Lists all the items in the packing list, and includes the current packed quantity, and the total quantity to be packed.

Format: `list`

Examples of usage:
* `list`

### Delete the entire list: deletelist
Want to start on a new packing list? This command will clear the whole packing list.

Format: `deletelist`

Example of usage:
* `deletelist`

### Help message: help
Some command doesn't seem to be giving you the results you want?  Refer to the help message to see which command should be used.

Format: `help`

Examples of usage:
* `help`

### Exiting the application: bye
Done packing for today? Exit BagPacker and continue tomorrow!

Format: `bye`

Examples of usage:
* `bye`

## FAQ

**Q**: I keep getting a message that says the command is invalid, why? 

**A**: Please check that there are no typos in the command. To check the correct spelling of the commands, please type `help` and hit enter.

## Command Summary

* Add an item | `add n/QUANTITY /of d/NAME`
* Delete an item | `delete i/INDEX`
* Pack some item | `pack n/QUANTITY /of i/INDEX`
* Unpack some item | `unpack n/QUANTITY /of i/INDEX`
* List all items | `list`
* Delete the whole list | `deletelist`
* Help message | `help`
* Exit | `bye`