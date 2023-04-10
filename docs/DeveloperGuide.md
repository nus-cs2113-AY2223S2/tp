[DeveloperGuide.md](DeveloperGuide.md)
# Developer Guide
## Table of Contents
* **Acknowledgements**
* **Setting up, getting started**
* **Design**
    * [Architecture](#architecture)
    * [BagPacker Mechanism](#bagpacker-mechanism)
    * [commands Package](#commands-package) 
        - <em>[Command](#command)
        - [AddCommand](#add-command)
        - [ByeCommand](#bye-command)
        - [DeleteCommand](#delete-command)
        - [DeleteListCommand](#deletelist-command)
        - EditQuantityCommand
        - FindCommand
        - [HelpCommand](#help-command)
        - IncorrectCommand
        - [ListCommand](#list-command)
        - [ListUnpackedCommand](#list-unpacked-command)
        - PackAllCommand
        - [PackCommand](#pack-command)
        - UnpackAllCommand
        - [UnpackCommand](#unpack-command)</em>
    * **[exception Package](#exceptions)**
        - <em>[EmptyInputException](#emptyinputexception)
        - [InvalidIndexException](#invalidindexexception)
        - [InvalidQuantityException](#invalidquantityexception)
        - [InvalidVariablesException](#invalidvariablesexception)</em>
    * **[iohandler Pakage](#iohandler-package)**
        - <em>[Parser](#parser-class)
        - Storage
        - Ui</em>
    * **[packingfunc Package](#packingfunc-package)**
        - <em>Item
        - PackingList</em>
* **[Documentation, logging, testing, configuration, dev-ops](#documentation-logging-testing-configuration-dev-ops)**
* **[Appendix: Requirements](#appendix--requirements)**
    * [Product scope](#product-scope)
    * [User stories](#user-stories)
    * [Non-Functional Requirements](#non-functional-requirements)
    * [Glossary](#glossary)



## Acknowledgements

This project is based on the AddressBook-Level3 project created by the SE-EDU initiative.
* The design and structure of our User Guide and Developer Guide is referenced from the AddressBook-Level3 (AB3) User Guide and Developer Guide.
  * [AB3 User Guide](https://se-education.org/addressbook-level3/UserGuide.html)
  * [AB3 Developer Guide](https://se-education.org/addressbook-level3/DeveloperGuide.html)


---
### Setting up, getting started
Refer to the [UserGuide.md](UserGuide.md) for more details


##### Architecture
![BagPackerClassDiagram.png](diagrams%2FBagPackerClassDiagram.png)
![ExceptionClassDiagram.png](diagrams%2FExceptionClassDiagram.png)

The Architecture of BagPacker Application can be seen  from the diagram above

BagPacker() role is similar to a how a standard main() class acts in Java programs

`BagPacker` is responsible for, 

- At app launch: Initializes the components in the correct sequence. Retrieves saved packing list if any.
- Running the main app: Calls 'BagPacker.runBagPacker()' which runs the main program for BagPacker application. (including execution of commands)
- At shut down: Shuts down the components and invokes cleanup methods where necessary. Saves the current packing list.

##### runBagPacker() Mechanism

Run Condition: ByeCommand.isBagPackerRunning

While Loop (if Run Condition is true):
1. `Parser.parse()` the user input which involves reading, getting command variables, and creating of relevant command object. (more info in [Parser](#parser-class))
2. execute the relevant command returned from `Parser.parse()`

This can be seen from the interactions between `:BagPacker` and `:Parser` in the diagram below.

![BagPackerSequenceDiagram.png](diagrams%2FBagPackerSequenceDiagram.png)

### Commands Package

For all valid commands, the mechanism of implementation are as follows:
1. If `ByeCommand.isBagPackerRunning` is true, keep looping through steps 2-4
2. Read input - `runBagPacker()` method in `BagPacker` calls the `Parser` class to read user input command using `Parser.parse()`
3. Create command object - The ```Parser``` class creates a corresponding command object of the relevant command (child class of the Command Class)
4. Execute command object - ```runBagPacker()``` method executes the ```.execute()``` method (overridden by child classes) of the command object 
   which runs the actual command function

Refer to the sequence diagram in [runBagPacker() Mechanism](#runbagpacker---mechanism) for a visual aid of the above explanation.

---

#### Command

The `Command` abstract class is used to create subclasses of commands for BagPacker. The constructor `Command()` takes in an integer of `targetIndex` which sets the internal `targetIndex` value. 
`targetIndex` is used for certain commands such as delete, pack, and edit, where the `index` of a certain `item` in the `packingList` is important in the command. An `item` of that index will be extracted out using `getTargetItem()`.



#### Add Command

Add command is used to add a quantity of item(s) to the packing list.

Mechanism: ```AddCommand.execute()``` calls the ```PackingList.addItem()``` method from the ```PackingList``` class which executes the ```ArrayList.add()``` method to add the item to the ```PackingList``` ArrayList. 
It then updates the ```quantity``` variable according to the quantity inputted by the user.



##### Preventing duplicate items

When using the `add` function, we have decided to implement a method to check whether an item with the same name already exists in the packingList.

This is done through the `itemFinder()` method in class `PackingList()`, which is called during `AddCommand.execute()`.

Below is the UML diagram showing what occurs during `add` function when trying to add a duplicate item.

![AddExistingItemDiagram.png](diagrams%2FAddExistingItemDiagram.png)

When `AddCommand.execute()` is called in `BagPacker`, the `PackingList.itemFinder()` method is called.

The `PackingList.itemFinder()` method loops through all items in the packingList, and returns `true` if any of the existing items have the same name as the item to be added, else `false`.

If `PackingList.itemFinder()` returns `true`, method `AddToItemQty(itemName, addQty)` will be called to update the existing item in the packing list by adding the addQty to the current total quantity of that item. 

If `PackingList.itemFinder()` returns false, the item will be added onto `packingList` using `PackingList.AddItem(Item)`.

In both scenarios, the relevant `ui.printToUser` messages (omitted in the sequence diagram for easier reading) will be called to print a message to the user.


#### Delete Command

Delete command is used to delete an item from the packing list.

Mechanism: ```DeleteCommand.execute()``` calls the ```PackingList.deleteItem()``` method from the ```PackingList``` class which executes the ```ArrayList.remove()``` method to remove the item from the ```PackingList``` ArrayList.


#### Pack Command

Pack command is used to pack a quantity of an item in the packing list.

Create Mechanism: Creating of `PackCommand` object is done in `Parser.CreatePackObj()`, which should return a new `PackCommand` object if there are no exceptions caught.
The following conditions will cause an `IncorrectCommand` to be returned instead of a `PackCommand`, signalling an error has occurred. 
1. Empty PackingList
2. `QuantityPacked` less than 1 OR greater than 1,000,000 OR greater than the item's unpacked quantity 
3. Item index not a positive integer at most the size of the Packing List


This new command created (either `PackCommand` OR `IncorrectCommand`) will then be executed by `BagPacker()`.

Execute Mechanism: ```PackCommand.execute()``` calls the ```PackCommand.getTargetItem()``` method to retrieve the target item to pack. 
After which the ```PackingList.PackItem(item, packQuantity)``` method is called in ```PackingList``` which calls ```Item.setPacked(packQuantity)```in `Item` class. `Item.setPacked()` will add the `packedQuantity` to the current pack quantity of the item `toPack`.
Lastly `Ui.printToUser(MSG_SUCCESS_PACK, item)` from `Ui` class is called to print a message to the user signifying that the pack command has been executed successfully.

![ExecutePackCommandSequenceDiagram.png](diagrams%2FExecutePackCommandSequenceDiagram.png)

Sequence Diagram of Successful `PackCommand.execute()`


#### Unpack Command

Unpack command is used to unpack a quantity of an item in the packing list.

Create Mechanism: Creating of `UnpackCommand` object is done in `Parser.CreateUnpackObj()`, which should return a new `UnpackCommand` object if there are no exceptions caught.
The following conditions will cause an `IncorrectCommand` to be returned instead of a `PackCommand`, signalling an error has occurred.
1. Empty PackingList
2. `QuantityUnpacked` less than 1 OR greater than 1,000,000 OR greater than the item's packed quantity
3. Item index not a positive integer at most the size of the Packing List

This new command created (either `UnpackCommand` OR `IncorrectCommand`) will then be executed by `BagPacker()`.

Execute Mechanism: ```UnpackCommand.execute()``` calls the ```UnpackCommand.getTargetItem()``` method to retrieve the target item to pack.
After which the ```PackingList.UnpackItem(item, quantity)``` method is called in ```PackingList``` which calls ```Item.setUnpacked(quantity)```in `Item` class. `Item.setUnpacked()` will remove the `quantity` from the current pack quantity of the item.
Lastly `Ui.printToUser(MSG_SUCCESS_UNPACK, item)` from `Ui` class is called to print a message to the user signifying that the unpack command has been executed successfully.

![ExecuteUnpackCommandSequenceDiagram.png](diagrams%2FExecuteUnpackCommandSequenceDiagram.png)

Sequence Diagram of Successful `UnpackCommand.execute()`

---

#### Help Command
Help command is used to display all the possible commands in the BagPacker application for the user.

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

Mechanism: `ListCommand.execute()` prints the full list of items in `packingList` to the CLI.

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

Mechanism: `ListUnpackedCommand.execute()` calls `getUnpackedList()`, which iterates through every item in `packingList` to check if it is fully packed. 'Fully packed' here refers to an item that has its current `packedQuantity` to be less than its `totalQuantity`.
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

In the case where there are no items in the list, or all items are fully packed, the respective messages are shown on the CLI.

_**No items:**_
```
________________________________________________________________________________________________________________________
listunpacked
________________________________________________________________________________________________________________________
There are no items in your list. What would you like to add?
________________________________________________________________________________________________________________________
```

**_All items fully packed:_**
```
________________________________________________________________________________________________________________________
listunpacked
________________________________________________________________________________________________________________________
All items in your list are fully packed!
________________________________________________________________________________________________________________________

```

---

#### Find Command
`FindCommand` is used to find all items containing the keyword(s) provided.

Mechanism: `FindCommand.execute()` calls `PackingList.keywordFinder()` with the given `keyword`. This method loops through every `item` in `packingList` to see if the `itemName` for each `item` contains the keyword(s) given. 
The `item`(s) that contain the keyword are placed into an ArrayList with their `itemIndex` then used in `printToUser`.

---

#### DeleteList Command
`DeleteListCommand` is used to delete all items inside `packingList`.

Mechanism: `DeleteListCommand.execute()` reassigns the existing `packingList` to a new empty ArrayList of Items, thus deleting any items in `packingList`.

---

#### Bye Command
`ByeCommand` is used to exit the BagPacker application.

Mechanism: `ByeCommand.execute()` updates the static boolean `isBagPackerRunning` to be false. 
The `runBagPacker()` method will continually parse and execute relevant commands (refer to Command Mechanisms in DG) until 
`isBagPackerRunning == false` which occurs upon the execution of the `byeCommand`.

---

#### Incorrect Command

`IncorrectCommand` is a special type command that is returned by `Parser.parse()` when an exception is thrown by one of the methods in `Parse`. 
These exceptions are thrown when an error is detected in the user's input, consist of many types, such as a blank input, incorrect command format, or a missing parameter. See [Exceptions](#exceptions).
Depending on the error, `IncorrectCommand` will be constructed with an `errorType` and `helpMessage`.

Mechanism: `IncorrectCommand.execute()` will print the relevant error message to the user by calling `Ui.errorMessage`.


---
### Exceptions
The `exceptions` package contains all exceptions within BagPacker that are thrown when an error is detected. This is done to allow the program
to continue running and to elegantly handle any potential app stopping errors.


#### EmptyInputException

The `EmptyInputException` is thrown when the user input is empty or is composed of whitespace characters only.

Thrown by:
1. `Parser.readLine()`

Caught in:
1. `Parser.parse()`


#### InvalidIndexException
The `InvalidIndexException` is thrown when the user inputs an item index that is out of bounds of the `PackingList` or greater than 1,000,000

Thrown by:
1. `Parser.getItemIndex()`
2. `Parser.getPackVariables()`
3. `Parser.getPackAllIndex()`

Thrown and caught in:
1. `Parser.createDeleteObj()`
2. `Parser.createPackObj()`
3. `Parser.createEditQuantityObj()`
4. `Parser.createPackAllObj()`
5. `Parser.createUnpackObj()`


#### InvalidQuantityException
The `InvalidQuantityException` is thrown when the user inputs a quantity of an item that is invalid. This differs depending on the respective Command. 

Invalid Quantity definition:
1. For all cases, when input quantity is less than 1 or more than 1,000,000
2. For `add` command, when new total quantity of item will exceed 1,000,000
3. For `pack` command, when input pack quantity is greater than the item's unpacked quantity
4. For `unpack` command, when input unpack quantity is greater than the item's packed quantity
5. For `editquantity` command, when new total quantity (input) is less than the item's packed quantity

Thrown and caught in:
1. `Parser.createAddObj()`
2. `Parser.createPackObj()`
3. `Parser.createEditQuantityObj()`
4. `Parser.createUnpackObj()`


#### InvalidVariablesException
The `InvalidVariablesException` is thrown when the user inputs the wrong number of variables for commands with input variables (i.e. excluding `help`, `list`, `bye`, `deletelist` and `listunpacked` commands)

Thrown by:
1. `Parser.getKeyword()`
2. `Parser.getAddVariables`
3. `Parser.getItemIndex()`
4. `Parser.getPackVariables()`
5. `Parser.getPackAllIndex()`
6. `Parser.getEditQuantityVariables()`

Caught by: 
all createCommandObj methods except for commands without input variables (i.e. excluding `help`, `list`, `bye`, `deletelist` and `listunpacked` commands)

---


### IOHandler
The `IOHandler` package contains three main classes, which are [Parser](#parser), [Storage](#storage) and [Ui](#ui). These classes are used to handle input from and output to the user through the CLI, 
while managing the storage and retrieval of the associated `item`s in the user's `packingList`.

### parser class
The Parser class has 2 main functions:
1. Reading and retrieving the relevant command, and command variables from the users input (get*CommandVariable()*)
2. Creating a command object based on the retrieved command and command variables (create*Command*Obj())

##### get*CommandVariable*() methods:
`getCommand()` - returns the command from user input by finding the first word and changing it to lower case.

`getItemIndex()` - returns a string which represents the index of the item from the user input. Used by `createDeleteObj()`.

`getKeyword()` - return the keyword Used by `createFindObj()`.

`getAddVariables()` - returns the relevant components of an add command from the user input. Used by `createAddObj()`.

`getEditQuantityVariables()` - returns the target item's quantity and item index. Used by `createEditQuantityObj()`.

`getPackVariables()` - returns the target item's quantity to be packed and item index. Used by `createPackObj()` and `createUnpackObj`.

`getPackAllIndex()` - returns the item Index of the item to set as fully packed. Used by `createPackAllObj()` and `createUnpackAllObj`. .

##### create*Command*Obj() methods:
All erroneous inputs will instead return `IncorrectCommand()` with respective error messages. Go to [Exceptions](#exceptions) to see more details 

The following show the respective create methods for each command. The `command` object they return will be executed in `BagPacker()`

`createAddObj()` - returns new `AddCommand(item)`

`createDeleteObj()` - returns new `DeleteCommand(itemIndex)`

`createPackObj()` - returns new `PackCommand(itemQuantity, itemIndex)`

`createUnpackObj()` - returns new `UnpackCommand(itemQuantity, itemIndex)`

`createListObj()` - returns new `ListCommand()`

`createListUnpackedObj()` - returns new `ListUnpackedCommand()`

`createHelpObj()` - returns new `HelpCommand()`

`createDeleteListObj()` - returns new `DeleteListCommand()`

`createPackAllObj()` - returns new `PackAllCommand(itemIndex)`

`createUnpackAllObj()` - returns new `UnpackAllCommand(itemIndex)`

`createEditQuantityObj()` - returns new `EditQuantityCommand(newTotalQuantity, itemIndex)`

`createFindObj()` - returns new `FindCommand(keyword)`

`createByeObj()` - returns new `ByeCommand()`

`IncorrectCommand()` - of format `IncorrectCommand(errorType, helpMessage)` is returned for any [Exceptions](#exceptions) caught. 


---
#### Parser


---
#### Storage
The `Storage` class consists of two main methods: `save()` and `load()`.
The constructor of this class, which is called in `BagPacker`, will set the `file_path` of storage, which is default at "packingList.txt".

`save()` calls the method `writeToFile`, which loops through the `packingList` to write every `item` to a file in the `file_path` using a `FileWriter`.

Each `item` is written on a newline with a format using `.toString()`, which is `[PACKED_QUANTITY/TOTAL_QUANTITY] ITEM_NAME`. 
Example:
```text
[0/4] jackets
[2/4] cats
[0/3] toothbrush
```

`load()` is called at the start of `main()` in `BagPacker`.
This method reads in the file in `file_path` and translates each line to construct an `item`. 

This is done by the method `readItem()`, which marks out the relevant variables for `packedQuantity`, `totalQuantity` and `itemName` in a line, then uses the overloaded constructor method in `Item` class to form an item. 

Each `item` is returned to `load()` and added to the packingList.

---
#### Ui



---

## Documentation, logging, testing, configuration, dev-ops


### Appendix: Requirements
#### Product scope

**Target user profile**
* Has a need to pack items for travel purposes
* Prefers desktop apps over other types of apps (such as mobile apps)
* Prefers typing to mouse interactions
* Is reasonably comfortable using CLI apps



**Value proposition**

BagPacker aims to help busy students simplify their packing process by allowing easy adding of items to pack and record of the items they have already packed so that they can be organised and aboard their travels with ease.



##### User Stories

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
| v2.1    | user     | edit the number of items i need to pack        | change my mind whenever I want                                               |
| v2.1    | user     | fully pack or unpack an item                   | don't have to refer to how many of that item I need                          |

---

#### Non-Functional Requirements

- be able to retrieve the user's packing list quickly and accurately
- the quantity of each item to be packed should not be unreasonably large


#### Glossary

* *CLI* - Command Line Interface

---

### Instructions for manual testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}
