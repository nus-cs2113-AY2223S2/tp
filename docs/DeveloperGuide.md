# Developer Guide
![img_12.png](img_12.png)
## Contents
- [Acknowledgements](#acknowledgements)
- [Design](#_1-design)
  - [Architecture Design Diagram](#_11-architecture-design-diagram)
  - [UML Sequence Diagram](#_12-uml-sequence-diagram)
- [Implementation](#_2-implementation)
  - [Command Component](#_22-command-component)
  - [Parser Component](#_21-parser-component)
  - [List](#_23-list)
  - [Add](#_24-add)
  - [Edit](#_25-edit)
  - [Restock](#_26-restock)
  - [Sell](#_27-sell)
  - [Remove](#_28-remove)
  - [Search](#_29-search)
  - [Filter](#_210-filter)
  - [History](#_211-history)
  - [Alert](#_212-alert)
  - [Category](#_213-category)
- [Product Scope](#product-scope)
  - [Target User Profile](#target-user-profile)
  - [Value Proposition](#value-proposition)
- [User Stories](#user-stories)
- [Non-Functional Requirements](#non-functional-requirements)
- [Glossary](#glossary)
- [Instructions for Manual Testing](#instructions-for-manual-testing)

## Acknowledgements

The documentation and organisation of our project follows the recommended format as shown in [SE-Education](http://se-education.org/addressbook-level3/DeveloperGuide.html)

<div style="height: 420px;"></div>

## 1. Design

### 1.1. Architecture Design Diagram
![Architecture Diagram](ArchitectureDiagram.png)

Breakdown of each component and its role in the application:

`MagusStock`: This is the entry point for the application, and it's responsible for starting the application and coordinating the interactions between the other components.

`ParserHandler`: This component is responsible for handling user input and determining which parser to execute based on the input. It uses a Parser to parse the input and generate a corresponding Command object.

`Parser`: This component is responsible for parsing the user input and generating a Command object. The ParserHandler uses the Parser to parse the input and determine which Command object to create.

`SessionManager`: This component is responsible for managing the inventory data persistence. It's connected to the Storage component, which reads and writes inventory data to and from a CSV file.

`Storage`: This component is responsible for reading and writing inventory data to a CSV file. It's connected to the SessionManager, which uses it to manage the inventory data persistence.

`Ui`: This component is responsible for displaying information to the user. It receives messages and data from the other components and displays them to the user.

Overall, the architecture diagram shows how the different components of the MagusStock application work together to provide the user with a command-line interface for managing an inventory of items. The components are designed to be modular and loosely coupled, allowing for easy modification and extension of the application.

<div style="height: 190px;"></div>

### 1.2. UML Sequence Diagram

![Sequence Diagram](SequenceDiagram.png)

The sequence diagram above illustrates the overall flow of Magus Stock. When the application is initialised by the User,
it will invoke `run()` of the MagusStock class to start the application. During the startup phase, a welcome message
will be printed on the terminal by invoking `greetUser()` of the `Ui` class. Following that, previous session inventory
and alert data of the application will be retrieved by invoking `getSession()` and `getSessionAlerts()` of the 
`SessionManager` class.

After the startup phase, Magustock will instantiate a `ParserHandler` object which will be responsible for handling user
input and determining which parser to execute based on the input. This is followed by a self-invocation of the `run()` 
method which will execute a loop that will continuously prompt the user for input and execute the corresponding command
until the user exits the application with `bye` or `exit` command.

## 2. Implementation

To better understand the implementation of the application, we will be looking at the relationship between the 
`ParserHandler`, `Parser` and `Command` classes.

![Command_ParserFlowClassDiagram.png](Command_ParserFlowClassDiagram.png)

1. On startup, the `MagusStock` class will create a new `ParserHandler` object on `run()` method.
2. The `ParserHandler` object will take in user input from the terminal and delegate the parsing of the input to the 
   corresponding `Parser` object.
3. The respective `Parser` object will parse the input and create the corresponding `Command` object that was abstracted 
from the `Command` class.

!> In the example above, the user is executing the `add` command. The `ParserHandler` will create a new `AddParser` 
object which will parse the user input and create a new `AddCommand` object which is responsible for
the execution of the `add` command by performing the necessary operations

<div style="height: 150px;"></div>

### 2.1. Parser Component
The class diagram below illustrates the abstract relationship between the different parser for each command. 
The `ParserHandler` class is responsible for handling the user input and determining which parser to execute 
based on the user input. Each unique parser is abstracted from the `Parser` class as shown in the diagram below.

!> Attributes are omitted intentionally for clarity due to large number of it in the actual code.

![ParserClassDiagram.png](ParserClassDiagram.png)

### 2.2. Command Component

The class diagram below illustrates the abstract relationship between the different command classes and `Command`.
The corresponding parser from 2.1 is responsible for the creation and execution of the the respective command object
that is abstracted from the `Command` class as shown in the diagram below.

!> Attributes are omitted intentionally for clarity due to large number of it in the actual code.

![CommandClassDiagram.png](CommandClassDiagram.png)

---

<div style="height: 130px;"></div>

### 2.3. List
The list command is mainly handled by the `ListCommand` class, which extends the `Command` class.

![ListCommand.png](UML%2FList%2FListCommand.png)


**Step 1**. When the user executes the command `list`, the `ParserHandler` will create a new `ListParser` object and pass to it the `Inventory` where the items to be listed are stored.

![ListStep1.png](UML%2FList%2FListStep1.png)

**Step 2**. The `run` method in `ListParser` overrides the `run` method in `Parser` to create a new `ListCommand` object, passing to it the relevant `Inventory`.

![ListStep2.png](UML%2FList%2FListStep2.png)

**Step 3**. The `run` method in `ListCommand` overrides the `run` method in `Command` and calls the `listItems` method. The `listItems` method checks if the inventory is empty. If the inventory is empty, the method prints a message to inform the user that there are no items in the inventory. Otherwise, the `printTable` method from the `Ui` object is called.

![ListStep3.png](UML%2FList%2FListStep3.png)

**Step 4.**. If the `printTable` method is called, it takes in an `ArrayList<Item> items` as a parameter and prints out a table showing the name, UPC, quantity and price of all items in the inventory.

<div style="height: 300px;"></div>


### 2.4. Add
The add feature is mainly handled by `AddParser` and `AddCommand`. 
The `AddParser` class extends the `Parser` abstract class and the `AddCommand` class extends the `Command` abstract 
class.

#### 2.4.1. AddParser Class
![AddParser.png](UML%2FAdd%2FAddParser.png)

**Step 1.** User executes the `add` command in the following format: `add n/[Item_name] upc/[UPC] qty/[Quantity] p/[Price] 
c/[Category]`

**Step 2.** The input is handled by the `ParserHandler` class which creates a new `AddParser` and invokes its `run()` method.

**Step 3.** If the rawInput is null, it throws a `MissingParametersException`. Otherwise, it uses a regular expression matcher to 
match the rawInput to a specific pattern.

**Step 4.** If the input doesn't match the pattern, it prints an error message using `Ui` and returns.

**Step 5.** If the input matches the pattern, it creates a new `Item` object using the parsed parameters and creates a new
`AddCommand` object using the `Inventory` object and `Item` object.

**Step 6.** It then calls the `run()` method of the `AddCommand` object to execute the add command to 
perform the necessary operations to add the item to the inventory.

**Step 7.** Once the `AddCommand` object has finished executing, `Command` and `AddCommand` is destroyed
and the `AddParser` object is returned to the `ParserHandler` object with itself being destroyed as well.

<div style="height: 380px;"></div>

#### 2.4.2. AddCommand Class

![AddCommand.png](UML%2FAdd%2FAddCommand.png)

The `AddCommand` class represents the command to add an item to the inventory. It takes in an `Inventory` object and 
an `Item` object as parameters. The constructor sets the `Inventory` object to be the inventory of the `Command` class, 
and the `Item` object to be the item to be added to the inventory. 

The class also contains a private method called `addItem()` that adds the `Item` object to 
the inventory. The `addItem()` method checks if the item already exists in the inventory using its unique UPC 
(Universal Product Code) code. If the item already exists, it prints a message stating that the item is a duplicate. 
Otherwise, it adds the item to the inventory, updates the item name hash, and adds the item to the UPC code history. 
If the `SessionManager`'s `autoSave` flag is enabled, it writes the current inventory to a file using the 
`SessionManager`.

The `run()` method of the `AddCommand` class calls the `addItem()` method to execute the add command.

### 2.5. Edit
The "edit" command is mainly handled by the `EditCommand` class, which extends the `Command` class. It is parsed
by the `EditParser` class, which extends the `Parser` class.


**Step 1**. When the user executes the command `edit upc/[UPC Code] n/[Name] qty/[Quantity] p/[Price] c/[Category]`,
the `ParserHandler` will create a new `EditParser` object and pass the appropriate `input` and `Inventory` in which
the items are stored.

**Step 2**. The `EditParser` object checks for the validity of the `input`. If it is determined that no `upc/` is given
or only the `UPC` is given without additional parameters, an exception will be thrown for error handling. If none of
these conditions are met, an `EditCommand` object will be created to further process the user input.

**Step 3**. In the `EditCommand` object, the method `setEditInfo()` will be called first. This method will be used
to not only call other methods for user input processing, but will also handle the exceptions thrown by the other
methods it has called. It will begin by retrieving the item information from the inventory using
`retrieveItemFromHashmap()` and storing the item attribute information in three `Item` objects, namely `updatedItem`,
`oldItem` and `oldItemForCat`. `oldItem` will be used to keep track of the old attribute information, while 
`updatedItem` will be used to overwrite the old attributes, should the user inputs be valid. `oldItemForCat` will
exclusively be used for updating of category information.

**Step 4**. Still within the `setEditInfo()` method, `updatedItem` and `oldItem` will be pass into another method
`updateItemInfo()`. This method calls `handleUserEditCommand()` for further user input processing and also handles
a series of different exceptions thrown. Within `handleUserEditCommand()`, further verification of user input by
`validateUserEdtiCommand()` will take place, before allowing `makeEdits()` to be executed, which will change the
information in the `updatedItem` object and hence the attribute information of the item. Should exceptions be thrown 
by `validateUserEditCommands()`, attribute information stored by `oldItem` will be used by `revertChanges()` to 
update the item attributes instead.

**Step 5**. After item attributes have been updated, we go back to the `setEditInfo()` method, which will then update
the data structures responsible for tracking of the item  and its attributes using the `handleTrie()`, `remove()` and
`put()` methods.

Included below are UML Sequence Diagrams for `EditParser` and `EditCommand`. The second diagram shows a 
more detailed reference frame for `Process User Edit Input` found in the first diagram.

![EditParser.png](UML/Edit/EditParser.png)

![EditCommand.png](UML/Edit/EditCommand.png)

### 2.6. Restock
The `restock` command is mainly handled by the `RestockCommand` class, which extends the `Command` class. It is parsed 
by the `RestockParser` class, which extends the `Parser` class. Included below is a sequence diagram for the `restock`
command:

**Step 1**. When the user executes the command `restock upc/[UPC Code] qty/[Quantity]`, the
`ParserHandler` will create a new `RestockParser` object and pass the appropriate `input` and `Inventory` in which
the items are stored.

**Step 2**. The `RestockParser` object checks for the validity of the user inputs. If user inputs were determined to
be invalid due to lack of a UPC call `upc/` or wrong command length of `1` or below, an exception will be thrown for 
error handling. If both conditions as mentioned earlier are satisfied, a `RestockCommand` object will be created to 
handle the next step of user input processing.

**Step 3**. In the `RestockCommand` object, method `restockItem()` is called to begin item quantity addition process.
Two `Item` objects, `updatedItem` and `oldItem`, will be created for ease of passing information on the quantity of
the item before and after the update to other classes. Should `updatedItem` fail to retrieve item information from the
hashmap, possibly due to a non-existent UPC code, `Exception` will be thrown for error handling.

**Step 4**. The method `restockItem` calls the methods `checkRestockCommandLength()` and `updateItemQuantity()` for 
quantity addition. `checkRestockCommandLength()` checks whether the there are two string inputs and throws an 
`Exception` if the input is not exactly 2 strings long, before allowing `updateItemQuantity()` to perform the actual
addition. However, even within `updateItemQuantity()`, checks are also performed to detect for negative values, or
strings in place of actual integers. These checks will also throw an `Exception` for error handling.

**Step 5**. Hashmap and Tree data structures containing information about the Item will be updated, by taking
reference from the `oldItem` and `updatedItem` objects. The `UI` class will be called to handle output of strings
to inform the user on the status of the program, based on whether quantity addition has been done successfully or
if an `Exception` has been thrown.

Included below are UML Sequence Diagrams for the `RestockParser` and `RestockCommand`. The second diagram shows a more
detailed version of what happens in the reference frame of `Increase Quantity of the Item`.

![RestockParser.png](UML/Restock/RestockParser.png)

![RestockCommand.png](UML/Restock/RestockCommand.png)

<div style="height: 250px;"></div>

### 2.7. Sell
The "sell" command is mainly handled by the `SellCommand` class, which extends the `Command` class. It is parsed
by the `SellParser` class, which extends the `Parser` class. 

**Step 1**. When the user executes the command `sell upc/[UPC Code] qty/[Quantity]`, the
`ParserHandler` will create a new `SellParser` object and pass to it the appropriate `input` and the appropriate
`Inventory` in which the items are stored.

**Step 2**. The `SellParser` object checks for the validity of the user inputs. If user inputs were determined to
be invalid due to lack of a UPC code or wrong command length of `1` or below, an exception will be thrown for error 
handling. If both conditions as mentioned earlier are satisfied, a `SellCommand` object will be created to handle
the next step of user input processing.

**Step 3**. In the `SellCommand` object, method `sellItem()` is called to begin item quantity deduction process.
Two `Item` objects, `updatedItem` and `oldItem`, will be created for ease of passing information on the quantity of
the item before and after the update to other classes. Should `updatedItem` fail to retrieve item information from the
hashmap, `Exception` will be thrown for error handling.

**Step 4**. The method `sellItem` calls the methods `checkSellCommandLength()` and `updateItemQuantity()` for quantity
deduction. `checkSellCommandLength()` checks whether the there are two string inputs and throws an `Exception` if the
input is not exactly 2 strings long, before allowing `updateItemQuantity()` to perform the actual deduction. However,
even within `updateItemQuantity()`, checks are also performed to detect for negative values, strings as quantities, or
values exceeding the total available quantity. These checks will also throw an `Exception` for error handling.

**Step 5**. Hashmap and Tree data structures containing information about the Item will be updated, by taking
reference from the `oldItem` and `updatedItem` objects. The `UI` class will be called to handle output of strings
to inform the user on the status of the program, based on whether quantity deduction has been done successfully or
if an `Exception` has been thrown.

Included below are UML Sequence Diagrams for `SellParser` and `SellCommand`. The second diagram gives a detailed
version of what happens in the reference frame 'Deduct Quantity of the Item'.

![SellParser.png](UML/Sell/SellParser.png)

![SellCommand.png](UML/Sell/SellCommand.png)


### 2.8. Remove

The remove command is mainly handled by the `RemoveCommand` class, which extends the `Command` class. It is parsed by the 
`RemoveParser` class, which extends the `Parser` class.

![RemoveParser.png](UML/Remove/RemoveParser.png)

![RemoveCommand.png](UML/Remove/RemoveCommand.png)

**Step 1**. When the user executes the command `remove f/index [Index]` or `remove f/item upc/[UPC]`, the `ParserHandler` will
create a new `RemoveParser` object and pass to it the appropriate `input`, and `inventory` in which the items are stored.

**Step 2**. The `run` method in the `RemoveParser` is called which overrides the `run` method in `Parser`. This leads the
`RemoveParser` to call either `parseRemoveByIndex` or `parseRemoveByUpc` method, depending on whether the `f/` is `f/index`
or `f/item` respectively. 

**Step 3**. The methods `parseRemoveByIndex` or `parseRemoveByUpc` will check the validity of the input. If user input is invalid,
an error message will be printed out and method execution will halt. 

![RemoveStep3.png](UML/Remove/RemoveStep3.png)

**Step 4**. The `run` method in `RemoveCommand` is called which overrides the `run` method in the `Command` object.
This calls either `removeByUpcCode` or `removeByIndex` method depending on the type identified earlier. Now, both functions
will check if the UPC or index input by user is valid/exists in the list and execute removal. If not, an error message will be printed and method
will halt.


### 2.9. Search
The search command is mainly handled by the `SearchCommand` class, which extends the `Command` class. It is parsed by 
the `SearchParser` class, which extends the `Parser` class.

![SearchSequence.png](UML%2FSearch%2FSearchSequence.png)

![SearchCommand.png](UML%2FSearch%2FSearchCommand.png)

**Step 1**. When the user executes the command `search [keyword]` or `searchupc [keyword]`, the `ParserHandler` will create a 
new `SearchParser` object and pass to it the appropriate `input`, the `SearchType`, and the appropriate `Inventory` in 
which the items are stored.

![SearchStep1.png](UML%2FSearch%2FSearchStep1.png)

**Step 2**. The `run` method in `SearchParser` is called which overrides the `run` method in `Parser`. This leads the 
`SearchParser` to call either the `parseSearch` or `parseSearchUPC` method, depending on whether the `SearchType` is
`Types.SearchType.KEYWORD` or `Types.SearchType.UPC` respectively.

**Step 3**. The methods `parseSearch` or `parseSearchUPC` will check the validity of the input, and if the input
is valid, both will create a new `SearchCommand` object, passing to it the relevant `Inventory`, `SearchType`, and 
the `input`. If the input is not valid, an error message will be printed out and method execution will halt.

![SearchStep3.png](UML%2FSearch%2FSearchStep3.png)

**Step 4**. The `run` method in the `SearchCommand` object is called which overrides the `run` method in the
`Command` object. This calls either the `searchKeyword` method which returns `ArrayList<Item>`, or the `searchUPC`
method which returns an `Item` if there are found item(s), depending on whether the `SearchType` is 
`Types.SearchType.KEYWORD` or `Types.SearchType.UPC` respectively. Else, the methods will return `null`.

**Step 5**. The object(s) are returned to the `run` method. If the returned object is `null`, then the method
will inform the user that no search results were found. Otherwise, the `printSearchItems` or the 
`printSearchUPCItems` from the `Ui` object is called, depending on whether the `SearchType` is
`Types.SearchType.KEYWORD` or `Types.SearchType.UPC` respectively.

![SearchStep5.png](UML%2FSearch%2FSearchStep5.png)

![SearchStep5UPC.png](UML%2FSearch%2FSearchStep5UPC.png)

**Step 6**. If the `printSearchItems` method is called, it takes in an `ArrayList<Item> items` as a parameter and
prints out a table showing the name, UPC, quantity and price of all search results. Otherwise, the `printSearchUPCItems`
method takes in an `Item item` and prints it out in a table showing the name, UPC, quantity and price of the item.

### 2.10. Filter
The filter command is mainly handled by the `FilterCommand` class, which extends the `Command` class. It is parsed by
the `FilterParser` class, which extends the `Parser` class.

![FilterSequence.png](UML%2FFilter%2FFilterSequence.png)

![FilterPrice.png](UML%2FFilter%2FFilterPrice.png)

![FilterTagCategory.png](UML%2FFilter%2FFilterTagCategory.png)


<div class="page-break"></div>

**Step 1**. When the user executes the command `filter f/[filtertype] p/[price type] [category/price/tag]`, the 
`ParserHandler` will create a new `FilterParser` object and pass to it the appropriate `input` and the appropriate
`Inventory` in which the items are stored.

![FilterStep1.png](UML%2FFilter%2FFilterStep1.png)

**Step 2**. The `run` method in `FilterParser` is called which overrides the `run` method in `Parser`. This leads the
`FilterParser` to call either the `parseFilterCategory` or `parseFilterPrice` method, depending on whether the 
`f` flag is `category` or `tag` or `price` respectively. If the `f` flag is invalid, an error message is printed 
instead.

**Step 3**. The method `parseFilterCategory` will take the keyword from the user input, create a new `FilterCommand`
object and pass to it the relevant `Inventory`, `value` and `filterMode`. The `filterMode` is the `f` flag. The method 
`parseFilterPrice` will check if the `p` flag is set correctly. If it is not set correctly, an error message will be 
printed out and execution of the method will halt. Otherwise, a new `FilterCommand` object is created and passed the 
`Inventory`, `Price`, and `FilterPriceMode`.

![FilterStep3.png](UML%2FFilter%2FFilterStep3.png)

![FilterStep3Tag.png](UML%2FFilter%2FFilterStep3Tag.png)

**Step 4**. The `run` method in the `FilterCommand` object is called which overrides the `run` method in the
`Command` object. This calls either the `filterCategory` method or `filterPrice` which returns 
`ArrayList<Item>`, depending on the `filterMode`, which is set to either `filterMode` or `FilterPriceMode`. If there are no
filtered items, the methods will return `null`.

**Step 5**. The objects are returned to the `run` method. If the returned object is `null`, then the method
will inform the user that no filtered results were found. Otherwise, the `printSearchItems` from the `Ui` object is 
called.

![FilterStep5.png](UML%2FFilter%2FFilterStep5.png)

**Step 6**. If the `printSearchItems` method is called, it takes in an `ArrayList<Item> items` as a parameter and
prints out a table showing the name, UPC, quantity and price of all search results. 

### 2.11. History
The history command is mainly handled by the `HistoryCommand` class, which extends the `Command` class. It is parsed by
the `HistoryParser` class, which extends the `Parser` class.

![HistoryParser.png](UML%2FHistory%2FHistoryParser.png)

**Step 1**. When the user executes the command `history [upc]`, the
`ParserHandler` will create a new `HistoryParser` object and pass to it the appropriate `input` and the appropriate
`Inventory` in which the items are stored.

![HistoryStep1.png](UML%2FHistory%2FHistoryStep1.png)

**Step 2**. The `run` method in `HistoryParser` is called which overrides the `run` method in `Parser`. The 
`HistoryParser` will check if the `input` is a word. If not, an error is shown and the method will halt execution. 
Otherwise, the `HistoryParser` will create a new `HistoryCommand` object and pass it the relevant inventory and 
user input.

![HistoryStep2.png](UML%2FHistory%2FHistoryStep2.png)

**Step 3**. The `run` method in the `HistoryCommand` object is called which overrides the `run` method in the
`Command` object. The `HistoryCommand` object will call the `getHistoryResults` function which will return `null`
if the input specified by the user does not fit any UPC code of any item. Else, the `getHistoryResults` will return
a sorted `ArrayList<Item>` which represents the item's history.

**Step 4**. The objects are returned to the `run` method. If the returned object is `null`, then the method
will inform the user that no filtered results were found. Otherwise, the `printHistory` function from the `Ui` object is
called.

![HistoryStep3.png](UML%2FHistory%2FHistoryStep3.png)

**Step 5**. The `printHistory` function will first state the time at which the first instance of the item was added,
followed by printing the details of this first instance. It will then go through the following items in the list and 
print the differences, if any. If there is more than 1 item in the list provided to the function, it will then print
the details of the last and most current instant of the item.


### 2.12 Alert
The alert command is mainly handled by the `AddAlertCommand` class and `RemoveAlertCommand` class, both of which extend the `Command` class. It is parsed by the `AlertParser` class, which extends the `Parser` class.

![AlertParser.png](UML%2FAlert%2FAlertParser.png)

![AddAlertCommand.png](UML%2FAlert%2FAddAlertCommand.png)

![AddAlertCommand.png](UML%2FAlert%2FRemoveAlertCommand.png)

**Step 1**. When the user executes a command that begins with the word `alert`, the ParserHandler will create a new `AlertParser` object and pass in the appropriate `input`, as well as the corresponding `inventory` where the list of alerts for inventory items are stored.

![AlertStep1.png](UML%2FAlert%2FAlertStep1.png)


**Step 2**. The `run` method in `AlertParser` is called, which overrides the `run` method in `Parser`. The `AlertParser`checks if the `rawInput`
begins with the word `add` or `remove`.
This leads the `AlertParser` to call either the `parseAddAlert` if `rawInput` begins with the word `add`,
or `parseRemoveAlert` if `rawInput` begins with the word `remove`.
If the `rawInput` does not begin with either `add` or `remove`, an error is shown and the method will halt execution.

**Step 3**. 
If the input begins with `add`, the `AlertParser` creates a new `AddAlertCommand` object 
and passes in the relevant inventory, as well as a new `Alert` object.
If the `input` begins with `remove`, the `AlertParser` creates a new `RemoveAlertCommand` object and passes in the relevant inventory, as well as a new `Alert` object.
The `Alert` object is constructed using the `input` string. 
Note that both the `AddAlertCommand` and `RemoveAlertCommand` classes have an `AlertList` as part of their constructors, and that this `AlertList` is obtained from the inventory.

![AlertStep3Add.png](UML%2FAlert%2FAlertStep3Add.png)

![AlertStep3Remove.png](UML%2FAlert%2FAlertStep3Remove.png)


**Step 4**. The `run` method in `AddAlertCommand` overrides the `run` method in `Command`. This calls the `checkAddAlertUpc` method, which checks if the UPC of the alert is one that exists in the inventory.
If the UPC does not exist in the inventory, an error message is shown. Otherwise, the `addAlert` method is called.
The `addAlert` method checks if the alert is a minimum or maximum alert, and then adds the alert to the AlertList by calling either the `addMinAlert()` or `addMaxAlert()` method.
If the `SessionManager`'s `autosave` flag is enabled, it writes the current alert list to a file using the `SessionManager`.

The `run` method in `RemoveAlertCommand` overrides the `run` method in `Command`. This calls the `checkRemoveAlertUpc` method, which checks if the UPC of the alert is one that exists in the inventory.
If the UPC does not exist in the inventory, an error message is shown. Otherwise, the `removeAlert` method is called.
The `removeAlert` method checks if the alert is a minimum or maximum alert, and then removes the alert.
If the `SessionManager`'s `autosave` flag is enabled, it writes the current alert list to a file using the `SessionManager`.



### 2.13 Category
The category command is mainly handled by the `CategoryCommand` class, which extends the `Command` class. It is parsed by
the `CategoryParser` class, which extends the `Parser` class.

![CategoryParser.png](UML/Category/CategoryParser.png)

![CategoryCommand.png](UML/Category/CategoryCommand.png)

**Step 1** When the user executes the command `cat list` or `cat table`, the `ParserHandler` will create a new 
`CategoryParser` object and pass to it the appropriate `input` and the appropriate `Inventory` in which the items are
stored.

**Step 2**. The `run` method in `CategoryParser` is called which overrides the `run` method in `Parser`. 
`CategoryParser` checks if the `input` is not empty. If not, an error is shown and the method halts execution. 
Otherwise, the `CategoryParser` will create a new `CategoryCommand` object and pass it the relevant inventory and 
user input.

**Step 3**. The `run` method in the `CategoryParser` object is called which overrides the `run` method in the `Command` 
object. The `CategoryCommand` object will call either `listAllCategories` or `listCategoriesAndItems` 
method, depending on the user input (`list`, `table` respectively).

**Step 4**. `listAllCategories` and `listCategoriesAndItems` will call the `printCategoryList` or `printCategory` functions respectively from the `Ui` object
if the category hashmap is not empty. Otherwise, the methods will inform the user that the inventory list is empty and there
is no category hashmap available. 


## Product scope
### Target user profile
* Has a need to manage a wide variety of items, and track various information related to the item.
* Is able to type fast which leads to usage of CLI applications being a more efficient method of managing inventories
as compared to tradition inventory management systems.
* Prefers a desktop application for inventory management and tracking rather than using traditional pen and paper, or
smartphones.
* Requires a simplistic yet effective solution to the problem of inventory management, rather than a complex but 
costly one.

### Value proposition
* For users who can type fast, usage of MagusStock over conventional GUI applications for inventory management will be
significantly faster.
* MagusStock offers a wide variety of features that are critical in enhancing the user's experience in inventory
management.
* A low-cost solution for small companies and individuals, who likely do not require a costly and complex inventory 
management system for tracking of their stocks.
* Simple command formats that are easy to learn and get used to, without complex functions and terms that may be 
unsuitable for less seasoned users of the application.

## User Stories

| Version | As a ...                 | I want to ...                                                   | So that I can ...                                             |
|---------|--------------------------|-----------------------------------------------------------------|---------------------------------------------------------------|
| v1.0    | small business owner     | add new items to the inventory                                  | account for the stocks I have                                 |
| v1.0    | small business owner     | keep track of all the items we have in stock                    | have a record readily available                               |
| v1.0    | small business owner     | delete items from the inventory if they are no longer needed    | keep the inventory clean and up-to-date                       |
| v1.0    | IT team member           | update the quantity of items in the inventory                   | ensure the inventory always reflects the current stock levels |
| v1.0    | IT team member           | search for items in the inventory by name SKU or category       | quickly find what I need                                      |


| Version | As a ...                | I want to ...                                                           | So that I can ...                                                                                            |
|---------|-------------------------|-------------------------------------------------------------------------|--------------------------------------------------------------------------------------------------------------|
| v2.0    | small business owner    | analyze past sales from the historical records of the stocks            | make better decisions                                                                                        |
| v2.0    | small business owner    | see a report of items that are due to be reordered                      | make sure we have enough stock to meet customer demand                                                       |
| v2.0    | small business owner    | set different prices for items in the inventory                         | charge different prices for different items                                                                  |
| v2.0    | small business owner    | categorize items in the inventory                                       | quickly find items that belong to a specific category                                                        |
| v2.0    | small business owner    | directly top up and deduct the quantity of an item in the inventory list | keep track of the item quantities with ease without the inconvenience of editing the item quantity manually  |
| v2.0    | small business owner    | see the list of commands that can be executed                           | quickly identify the function I can use and their command formats.                                           |
| v2.0    | small business owner    | set minimum and maximum stock levels for items in the inventory         | be alerted if our stock levels fall outside of these limits                                                  |
| v2.0    | IT team member          | assign unique identifying numbers to items in the inventory             | easily track and manage the items                                                                            |
| v2.0    | convenience store owner | store information on the large variety of products                      | find them easily                                                                                             |


## Non-Functional Requirements

1. MagusStock should be able to hold up to 10,000 unique items, without a noticeable drop in its speed or performance.
2. MagusStock should be able to work on both Windows and macOS with Java `11` installed.
3. No prior setup of the application or downloading of external software is required, users are able to launch and 
use the application once it is downloaded and transferred to an empty folder.

## Glossary

* **MagusStock**: The name of the Inventory Management Program.
* **UPC**:Universal Product Code, used to track, edit, or find a unique item found in MagusStock's inventory database.
* **CLI**:Command Line Interface, a text-based user interface (UI) used to run programs, manage computer files and 
interact with the computer.

## Instructions for manual testing
### **Startup and Termination of Program**
  * #### Starting up MagusStock
  1. Ensure that you have Java `11` or above installed.
  2. Download the latest version of `MagusStock`from [here](https://github.com/AY2223S2-CS2113-W12-3/tp/releases).
  3. Move the .jar file to an **empty** folder.
  4. After changing directory to your folder in the command terminal, run the file with the
     command `java -jar magusstock.jar`.
  5. Upon successful **First** launch of the program, the screen should look similar to what is shown below. A new CSV 
     file will automatically be created by the program for storage purposes.
  6. Output should look as followed:   

````
java -jar magusstock.jar
__________________________________________________________________________

███╗░░░███╗░█████╗░░██████╗░██╗░░░██╗░██████╗░██████╗████████╗░█████╗░░█████╗░██╗░░██╗
████╗░████║██╔══██╗██╔════╝░██║░░░██║██╔════╝██╔════╝╚══██╔══╝██╔══██╗██╔══██╗██║░██╔╝
██╔████╔██║███████║██║░░██╗░██║░░░██║╚█████╗░╚█████╗░░░░██║░░░██║░░██║██║░░╚═╝█████═╝░
██║╚██╔╝██║██╔══██║██║░░╚██╗██║░░░██║░╚═══██╗░╚═══██╗░░░██║░░░██║░░██║██║░░██╗██╔═██╗░
██║░╚═╝░██║██║░░██║╚██████╔╝╚██████╔╝██████╔╝██████╔╝░░░██║░░░╚█████╔╝╚█████╔╝██║░╚██╗
╚═╝░░░░░╚═╝╚═╝░░╚═╝░╚═════╝░░╚═════╝░╚═════╝░╚═════╝░░░░╚═╝░░░░╚════╝░░╚════╝░╚═╝░░╚═╝
Welcome to MagusStock. How may I assist you today?
__________________________________________________________________________
INFO: Empty/No Session Inventory file found.
__________________________________________________________________________
INFO: Empty/No Session Alerts file found.
__________________________________________________________________________
````

  * #### Terminating MagusStock
  1. To terminate the program, simply type `bye` or `exit` in the command line.
  2. The expected result of the program is as followed:

````
exit
__________________________________________________________________________
Hope you had an enjoyable experience. See you next time!
__________________________________________________________________________
````

### Testing of Commands
The following shows a series of positive and negative test cases that can be used to test each feature of the program.
A description of the expected output as well as an example will also be provided.
#### List Testing
  * **Positive** Test Case Input: `list`
    * Expected Output: Print string stating that there are no items in the inventory if no items are added. Else,
      print a table showing all items found in the database.
    * Output Example:

````
__________________________________________________________________________
There are no items in your inventory.
__________________________________________________________________________
````
  * **Negative** Test Case Input: `list all` (Too many parameters for `list`)
    * Expected Output: An error message which shows the correct format for usage of the `list` command will be printed.
    * Output Example:

````
__________________________________________________________________________
Wrong/Incomplete Format! Please list items in the following format :
Sample Format: "list"
__________________________________________________________________________
````
    

#### Add Testing
  * **Positive** Test Case Input: `add n/Orange upc/123 qty/5 p/2.00 c/fruit`
    * Expected Output: A string informing the user that item has been added to the list will be printed.
    * Output Example:

````
__________________________________________________________________________
Successfully added the item(s) into the system!
__________________________________________________________________________
````
  * **Negative** Test Case Input: `add n/Orange upc/123` (Incomplete `Add` format)
    * Expected Output: Due to wrong format, a string informing the user of wrong `add` command format will be printed.
    * Output Example:

````
__________________________________________________________________________
Wrong/Incomplete Entry For Add! Please refer to UG for more information
Sample Format: "add n/[name] upc/[UPC] qty/[quantity] p/[price] c/[category]"
__________________________________________________________________________
````

#### Edit Testing
!> Note: Ensure that the **Positive** test case input for `Add` testing has been successfully executed before 
attempting to test this feature.
  * **Positive** Test Case Input: `edit upc/123 qty/10 p/3.00`
    * Expected Output: A series of strings which shows the attributes of the item before and after the `edit` will be
      printed.
    * Output Example:

````
__________________________________________________________________________
Successfully edited the following item:

Before Update:
Name: Orange
UPC: 123
Price: 2.0
Quantity: 5
Category: fruit

After Update:
Name: Orange
UPC: 123
Price: 3.0
Quantity: 10
Category: fruit
__________________________________________________________________________
````

  * **Negative** Test Case Input: `edit upc/123 qty/999999999` (Quantity out of range)
    * Expected Output: Due to an invalid quantity of `999999999`, an error message informing the user of an invalid 
    quantity will be printed.
    * Output Example:

````
__________________________________________________________________________
The number you have entered should be between 0 and 99,999,999.
__________________________________________________________________________
````

#### Restock Testing
!> Note: Ensure that the **Positive** test case input for `Add` testing has been successfully executed before
attempting to test this feature.
  * **Positive** Test Case Input: `restock upc/123 qty/100`
    * Expected Output: A series of strings showing both the old and new quantity of the item after executing the
      `restock` command will be printed.
    * Output Example:

````
__________________________________________________________________________
Successfully restocked the following item:

Before Restocking:
Item Name: Orange
UPC Code: 123
Quantity Available: 5

After Restocking:
Item Name: Orange
UPC Code: 123
Quantity Available: 105
__________________________________________________________________________
````

  * **Negative** Test Case Input: `restock upc/123 qty/0.1` (Decimal inputs for quantity are not allowed)
    * Expected Output: An error message informing user of the conditions of using the `restock` command will be 
      printed.
    * Output Example:

````
__________________________________________________________________________
Unable to restock item. REASON: Quantity inputs SHOULD NOT contain NEGATIVE integers, ZERO(0), or STRING inputs!
Also ensure that the desired quantity to be added does not cause current stock levels to exceed MAX
quantity limit of 99,999,999.
__________________________________________________________________________
````

#### Sell Testing
!> Note: Ensure that the **Positive** test case input for `Add` testing has been successfully executed before
attempting to test this feature.
  * **Positive** Test Case Input: `sell upc/123 qty/2`
    * Expected Output: A series of strings showing both the old and new quantity of the item after executing the
      `sell` command will be printed. The amount sold and the price it's sold at will also be printed.
    * Output Example:

````
__________________________________________________________________________
Successfully sold the following item:

Before Selling:
Item Name: Orange
UPC Code: 123
Quantity Available: 105

After Selling:
Item Name: Orange
UPC Code: 123
Quantity Available: 103

Sold 2 Orange at a price of $2.0.
__________________________________________________________________________
````

  * **Negative** Test Case Input: `sell upc/123 qty/two` (String input for quantity is not allowed)
    * Expected Output: An error message informing the user of the conditions of using the `sell` command will 
      be printed.
    * Output Example:

````
__________________________________________________________________________
Unable to sell item. REASON: Quantity inputs SHOULD NOT contain NEGATIVE integers, DECIMALS, ZERO(0), or STRING inputs!
Also ensure that the desired quantity to be deducted is LESS THAN current stock levels.
__________________________________________________________________________
````

#### Remove Testing
!> Note: Ensure that the **Positive** test case input for `Add` testing has been successfully executed before
attempting to test this feature.
  * **Positive** Test Case Input: `remove f/index 0` (Assuming index 0 contains an item)
    * Expected Output: A series of strings showing the item removed from the inventory will be printed.
    * Output Example:

````
__________________________________________________________________________
Successfully removed the following item:
Name: Orange
UPC: 123
Price: 2.0
Quantity: 103
Category: fruit
__________________________________________________________________________
````

  * **Negative** Test Case Input: `remove f/index 9999999` (Index does not exist)
    * Expected Output: Prints an error message to inform that the index is invalid. If inventory is empty, print a
      string to inform the user about an empty inventory list instead.
    * Output Example (If inventory is not empty):

````
__________________________________________________________________________
This index is invalid.
Please enter number 0 to remove item successfully.
__________________________________________________________________________
````

#### Search Testing
!> Note: Ensure that the **Positive** test case input for `Add` testing has been successfully executed before
attempting to test this feature.
  * **Positive** Test Case Input: `search or`
    * Expected Output: Return a table containing a list of items whose name starts with `or`. If it does not exist,
      print a string that says item does not exist.
    * Output Example (Found three items that starts with `or`):

````
__________________________________________________________________________
+-------+-----------------+--------------+----------+----------+-----------------+
| Index | Name            | UPC          | Quantity | Price    | Category        |
+-------+-----------------+--------------+----------+----------+-----------------+
| 0     | Orcas           | 2            | 1        | $1000.00 | Animal          |
+-------+-----------------+--------------+----------+----------+-----------------+
| 1     | Oreo            | 1234         | 5        | $2.00    | Snacks          |
+-------+-----------------+--------------+----------+----------+-----------------+
| 2     | orange          | 1            | 1        | $1.00    | Uncategorized   |
+-------+-----------------+--------------+----------+----------+-----------------+

__________________________________________________________________________
````

  * **Negative** Test Case Input: `search` (Incomplete command format)
    * Expected Output: A message informing the user of incorrect usage of the search command will be printed.
    * Output Example:

````
Wrong/Incomplete Entry For Search by Keywords! Please refer to UG for more information
Sample Format: "search [KEYWORDS]"
__________________________________________________________________________
````

#### Filter Testing
!> Note: Ensure that the **Positive** test case input for `Add` testing has been successfully executed before
attempting to test this feature.
  * **Positive** Test Case Input: `filter f/price p/lt 999`
    * Expected Output: Generates a table containing a list of items that are less than the price of `999`. If there is
      no such item, print a string to inform the user that no results can be found.
    * Output Example (Assuming there are items with prices below $999):

````
__________________________________________________________________________
+-------+-----------------+--------------+----------+----------+-----------------+
| Index | Name            | UPC          | Quantity | Price    | Category        |
+-------+-----------------+--------------+----------+----------+-----------------+
| 0     | orange          | 1            | 1        | $1.00    | Uncategorized   |
+-------+-----------------+--------------+----------+----------+-----------------+
| 1     | Oreo            | 1234         | 5        | $2.00    | Snacks          |
+-------+-----------------+--------------+----------+----------+-----------------+

__________________________________________________________________________
````

* **Positive** Test Case Input: `filter f/category fruit`
    * Expected Output: Generates a table containing a list of items that are in the specified category. If there is
      no such item, print a string to inform the user that no results can be found.
    * Output Example (Assuming there are items in this category):

````
__________________________________________________________________________
+-------+-----------------+--------------+----------+----------+-----------------+
| Index | Name            | UPC          | Quantity | Price    | Category        |
+-------+-----------------+--------------+----------+----------+-----------------+
| 0     | apples          | 12345678910  | 1        | $1.00    | fruit           |
+-------+-----------------+--------------+----------+----------+-----------------+
| 1     | watermelon      | 103437378374 | 10       | $40.00   | fruit           |
|       |                 | 2            |          |          |                 |
+-------+-----------------+--------------+----------+----------+-----------------+

__________________________________________________________________________
````

* **Negative** Test Case Input: `filter f/price p/lt one` (String as a price input)
    * Expected Output: An error message informing the user to change the price input to a numerical input will be
      printed.
    * Output Example:

````
Please enter a number for the price!
````

* **Negative** Test Case Input: `filter f/category ` (with no keywords)
    * Expected Output: An error message informing the user of wrong or incorrect input will be
      printed.
    * Output Example:

````
Wrong/Incomplete Entry For Filter! Please refer to UG for more information
Sample Format:
 For price filter: "filter f/price p/{gt/get/lt/let} [Price]"
 For category filter: "filter f/category [Keywords]"
````


#### History Testing
!> Note: Ensure that the **Positive** test case input for `Add` testing has been successfully executed before
attempting to test this feature.
  * **Positive** Test Case Input: `history 1234`
    * Expected Output: Prints a message showing the history of the item with UPC code `1234`, which shows its initial
      attributes when it was first added, the changes made to it over its lifetime, and its current attributes.
    * Output Example (Assuming item with UPC `1234` exists in the inventory):

````
__________________________________________________________________________
Item added at: 4:48 AM, MONDAY, APRIL 10, 2023
Name: Oreo
UPC: 1234
Price: 2.0
Quantity: 5
Category: Snacks
__________________________________________________________________________
__________________________________________________________________________
At: 12:30 PM, MONDAY, APRIL 10, 2023
Sold 1 items
__________________________________________________________________________
__________________________________________________________________________
Name: Oreo
UPC: 1234
Price: 2.0
Quantity: 4
Category: Snacks
__________________________________________________________________________
````

  * **Negative** Test Case Input: `history -1` (Item with such UPC does not exist)
    * Expected Output: Returns an error message informing the user that the item does not exist.
    * Output Example (Assuming item with UPC `1234` exists in the inventory):

````
__________________________________________________________________________
Command failed! Reason: Item not found in database. Please add item first!
__________________________________________________________________________
````

  * **Negative** Test Case Input: `history`
    * Expected Output: An error message informing the user of wrong or incorrect input printed.
    * Output Example:

````
Wrong/Incomplete Entry For History! Please refer to UG for more information
Sample Format: "history [UPC]"
````


#### Alert Testing
!> Note: Ensure that the **Positive** test case input for `Add` testing has been successfully executed before
attempting to test this feature.

  * **Positive** Test Case Input: `alert add upc/1234 min/3`
    * Expected Output: Prints a message informing the user that an alert has been successfully added.
    * Output Example:

````
__________________________________________________________________________
Successfully added a new alert.
__________________________________________________________________________
````

  * **Positive** Test Case Input: `alert add upc/12345678910 min/20`
    * Expected Output: Prints message on successful addition of new alert, as well as notify user if current quantity is 
      above or below threshold (for max and min alerts respectively). If item UPC is not found in inventory, prints message
      to inform user to add item into inventory first.
    * Output Example: (Assuming item UPC is in inventory and current quantity is below minimum set)

````
__________________________________________________________________________
Successfully added a new alert.
__________________________________________________________________________
ALERT: The quantity of apples is below the minimum level of 20.
__________________________________________________________________________
````
  * **Negative** Test Case Input: `alert` (Incomplete format)
    * Expected Output: Prints an error message that tells the user to refer to the User Guide for the appropriate
      format for the `alert` command.
    * Output Example:

````
____________________________________________________________
Wrong/Incomplete Entry For Alert! Please refer to UG for more information
____________________________________________________________
````

* **Negative** Test Case Input: `alert add upc/`
    * Expected Output: Prints error message for wrong or incomplete entry, and sample format
    * Output Example:

````
__________________________________________________________________________
Wrong/Incomplete Entry For Add Alert! Please refer to UG for more information 
Sample Format:
"alert add upc/[UPC] min/[integer]" OR
"alert add upc/[UPC] max/[integer]"
__________________________________________________________________________
````

#### Category Testing
!> Note: Ensure that the **Positive** test case input for `Add` testing has been successfully executed before
attempting to test this feature.
  * **Positive** Test Case Input: `cat list`
    * Expected Output: Shows all categories that are currently in use to classify the items added.
    * Output Example:

````
__________________________________________________________________________
Here is the list of categories you have:
Fruit
Uncategorized
__________________________________________________________________________
````

  * **Negative** Test Case Input: `cat` (Invalid command format)
    * Expected Output: A message to inform the user of an incorrect `category` command format will be printed.
    * Output Example: 

````
Wrong/Incomplete Entry For Category! Please refer to UG for more information
Sample Format:
List all categories: "cat list"
List all items and all categories: "cat table"
__________________________________________________________________________
````