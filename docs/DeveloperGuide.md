[DeveloperGuide.md](DeveloperGuide.md)
# Developer Guide

## Acknowledgements

This project is based on the AddressBook-Level3 project created by the SE-EDU initiative.

## Design & implementation
#### BagPacker Class is the main entry point for the BagPacker Program, below are the Packages and every class contained within each Package
1. commands 
   - Command
   - AddCommand
   - ByeCommand
   - DeleteCommand
   - DeleteListCommand
   - EditQuantityCommand
   - FindCommand
   - HelpCommand
   - IncorrectCommand
   - ListCommand
   - ListUnpackedCommand
   - PackAllCommand
   - PackCommand
   - UnpackAllCommand
   - UnpackCommand
2. exception
   - EmptyInputException
   - InvalidIndexException
   - InvalidVariablesException
3. iohandler
   - Parser
   - Storage
   - Ui
4. packingfunc
   - Parser
   - Storage
   - Ui

The class diagram below shows the overall structure of BagPacker application, many methods, variables and associations are omitted for improved clarity

![BagPackerClassDiagram.png](umlDiagrams%2FBagPackerClassDiagram.png)


### Command Mechanisms:
For all valid commands, the mechanism of implementation are as follows:
1. Read input - ```runBagPacker()``` method in ```BagPacker``` calls the ```Parser``` class to read user input command
2. Create command object - The ```Parser``` class creates a corresponding command object of the relevant command
3. Execute command object - ```runBagPacker()``` method executes the ```.execute()``` method (overridden by child classes) of the command object 
   which runs the actual command function

---

#### Add Command

Add command is used to add a quantity of item(s) to the packing list.

Mechanism: ```AddCommand.execute()``` calls the ```PackingList.addItem()``` method from the ```PackingList``` class which executes the ```ArrayList.add()``` method to add the item to the ```PackingList``` ArrayList. 
It then updates the ```quantity``` variable according to the quantity inputted by the user.


##### Preventing duplicate items

When using the `add` function, we have decided to implement a method to check whether the item with the same name already exists in the packingList.

This is done through the `itemFinder()` method in class `PackingList()`, which is called during `AddCommand.execute()`.

Below is the UML diagram showing what occurs during `add` function when trying to add a duplicate item.

![AddExistingItemDiagram.png](umlDiagrams%2FAddExistingItemDiagram.png)

When `AddCommand.execute()` is called in `BagPacker`, the `PackingList.itemFinder()` method is called.

The `PackingList.itemFinder()` method loops through all items in the packingList, and returns `true` if any of the existing items have the same name as the item to be added, else `false`.

If `PackingList.itemFinder()` returns `true`, method `AddToItemQty(itemName, addQty)` will be called to update the existing item in the packing list by adding the addQty to the current total quantity of that item. 

If `PackingList.itemFinder()` returns false, the item will be added onto `packingList` using `PackingList.AddItem(Item)`.

In both scenarios, the relevant `ui.printToUser` messages (omitted in the sequence diagram for easier reading) will be called to print a message to the user.


---

#### Delete Command

Delete command is used to delete an item from the packing list.

Mechanism: ```DeleteCommand.execute()``` calls the ```PackingList.deleteItem()``` method from the ```PackingList``` class which executes the ```ArrayList.remove()``` method to remove the item from the ```PackingList``` ArrayList.

---

#### Help Command
Help command is used to exit the BagPacker application.

Execute: ```HelpCommand.execute()``` prints the following help message.

```
All Commands:
1. add: Adds quantity and name of item to the packing list.
	Example: add 3 /of toothbrush
	Meaning: Add quantity of 3 toothbrushes to the packing list
2. delete: Deletes an item from the packing list.
	Example: delete 1
	Meaning: Removes the first item in the packing list
3. list: List all items and their total quantities packed in packing list.
	Example: list
4. pack: Adds to the current quantity of items packed in the packing list.
	Example: pack 2 /of 3
	Meaning: Packs 2 more quantities of the third item in the packing list
5. unpack: Deducts from the current quantity of items packed in the packing list.
	Example: unpack 1 /of 2
	Meaning: Unpacks 1 quantity of the second item in the packing list
6. deletelist: Deletes all items in the packing list.
	Example: deletelist
7. listunpacked: List all items and their total quantities if they are not fully packed yet.
	Example: listunpacked
8. editquantity: Edit the total quantity of an item to be packed.
	Example: editquantity 3 /of 2
	Meaning: Change the total quantity of the second item to 3
9. packall: Fully pack an item of choice from the packing list.
	Example: packall /of 3
	Meaning: Set the third item in the packing list as fully packed
10. unpackall: Fully unpacks an item of choice from the packing list.
	Example: unpackall /of 3
	Meaning: Set the third item in the packing list as totally not packed yet
11. find: Displays all items in the packingList as a list that contains the keyword used in the command.
	Example: find jacket
12. bye: Stops the BagPacker Application
	Example: bye
____________________________________________________________


```

---

#### List Command

List command is used to list out all items in the packing list.

Mechanism: ```ListCommand.execute()``` prints the full list of items in `PackingList` to the CLI.

List format: `ITEM_INDEX. [CURRENTLY_PACKED_QUANTITY/TOTAL_QUANTITY] ITEM_NAME`

Example:
```
____________________________________________________________
list
____________________________________________________________
Here are the items in your list
1. [0/1] passport
2. [2/3] shirts
3. [0/2] phones
4. [3/3] pairs of socks
____________________________________________________________
```

---

#### List Unpacked Command

`listunpacked` command is used to list out all items in the packing list that are not fully packed.

Mechanism: ```ListUnpackedCommand.execute()``` calls `getUnpackedList()`, which iterates through every item in `PackingList` to check if it is fully packed.
In each iteration, `Item.checkFullyPacked()` is called which checks `packedQuantity == totalQuantity` for a particular item and returns its packed status (true if fully packed, else false).
Each item that is not fully packed is added to an ArrayList of unpacked items.
Finally, `ListUnpackedCommand.execute()` prints the full list of unpacked items in `unpackedlist` to the CLI.

Example:
```
____________________________________________________________
listunpacked
____________________________________________________________
Here are the unpacked items in your list
1. [0/1] passport
2. [2/3] shirts
3. [0/2] phones
____________________________________________________________
```

---


#### DeleteList Command
```DeleteListCommand``` is used to delete a whole packing list in the BagPacker application.

Mechanism: ```DeleteListCommand.execute()``` reassigns the existing ```packingList``` to a new empty ArrayList of Items, thus deleting the ```packingList```.


---

#### Bye Command
```ByeCommand``` is used to exit the BagPacker application.

Mechanism: ```ByeCommand.execute()``` updates the static boolean ```isBagPackerRunning``` to be false. 
The ```runBagPacker()``` method will continually parse and execute relevant commands (refer to Command Mechanisms in DG) until
```isBagPackerRunning == false``` which occurs upon the execution of the ```byeCommand```.

## Product scope

### Target user profile

BagPacker is for NUS students, in particular, exchange students who travel a lot and want a simple CLI to keep track of their packing.

### Value proposition

BagPacker aims to help busy students simplify their packing process by allowing easy adding of items to pack and record of the items they have already packed so that they can be organised and aboard their travels with ease.


## User Stories

| Version | As a ... | I want to ...                                  | So that I can ...                                                            |
|---------|----------|------------------------------------------------|------------------------------------------------------------------------------|
| v1      | user     | add an item to my packing list                 | update my packing list                                                       |
| v1      | user     | remove an item from my packing list            | update my packing list                                                       |
| v1      | user     | view a list of my packed and unpacked items    | keep track of my packing list                                                |
| v1      | user     | mark an item as packed                         | keep track of what is packed                                                 |
| v1      | user     | mark an item as unpacked                       | keep track of what is unpacked                                               |
| v1      | new user | see usage instructions                         | refer to them when I forget how to use the application                       |
| v2.0    | user     | remove my packing list                         | clear my list once I am done packing                                         |
| v2.0    | user     | specify the quantity of an item I need to pack | keep track of individual item quantities being packed                        |
| v2.1    | user     | find an item by name                           | find the pack status of an item without having to go through the entire list |
| v2.1    | user     | save my packing list                           | keep track of my packing list even after leaving the app                     |
| v2.1    | user     | see the list of items I have yet to pack       | easily track what I am missing                                               |


## Non-Functional Requirements

- be able to retrieve the user's packing list quickly and accurately
- the quantity of each item to be packed should not be unreasonably large
- the total number of items to be packed should not be unreasonably large

## Glossary

* *glossary item* - Definition

## Instructions for manual testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}
