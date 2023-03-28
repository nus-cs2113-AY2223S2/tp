# Developer Guide
## Contents
- [Acknowledgements](#acknowledgements)
- [Design & Implementation](#design--implementation)
- [Implementation](#implementation)
  - [List](#list)
  - [Add](#add)
  - [Edit](#edit)
  - [Remove](#remove)
  - [Search](#search)
  - [Filter](#filter)
  - [Alert](#alert)
- [Product Scope](#product-scope)
  - [Target User Profile](#target-user-profile)
  - [Value Proposition](#value-proposition)
- [User Stories](#user-stories)
- [Non-Functional Requirements](#non-functional-requirements)
- [Glossary](#glossary)
- [Instructions for Manual Testing](#instructions-for-manual-testing)

## Acknowledgements

The documentation and organisation of our project follows the recommended format as shown in [SE-Education](http://se-education.org/addressbook-level3/DeveloperGuide.html)

## Design
### Architecture Design Diagram
![Architecture Diagram](ArchitectureDiagram.png)

Breakdown of each component and its role in the application:

`MagusStock`: This is the entry point for the application, and it's responsible for starting the application and coordinating the interactions between the other components.

`ParserHandler`: This component is responsible for handling user input and determining which parser to execute based on the input. It uses a Parser to parse the input and generate a corresponding Command object.

`Parser`: This component is responsible for parsing the user input and generating a Command object. The ParserHandler uses the Parser to parse the input and determine which Command object to create.

`SessionManager`: This component is responsible for managing the inventory data persistence. It's connected to the Storage component, which reads and writes inventory data to and from a CSV file.

`Storage`: This component is responsible for reading and writing inventory data to a CSV file. It's connected to the SessionManager, which uses it to manage the inventory data persistence.

`Ui`: This component is responsible for displaying information to the user. It receives messages and data from the other components and displays them to the user.

Overall, the architecture diagram shows how the different components of the MagusStock application work together to provide the user with a command-line interface for managing an inventory of items. The components are designed to be modular and loosely coupled, allowing for easy modification and extension of the application.

### UML Design Diagram

## Implementation
### List
The list command is mainly handled by the `ListCommand` class, which extends the `Command` class.

![ListCommand.png](UML%2FList%2FListCommand.png)


**Step 1**. When the user executes the command `list`, the `ParserHandler` will create a new `ListParser` object and pass to it the `Inventory` where the items to be listed are stored.

![ListStep1.png](UML%2FList%2FListStep1.png)

**Step 2**. The `run` method in `ListParser` overrides the `run` method in `Parser` to create a new `ListCommand` object, passing to it the relevant `Inventory`.

![ListStep2.png](UML%2FList%2FListStep2.png)

**Step 3**. The `run` method in `ListCommand` overrides the `run` method in `Command` and calls the `listItems` method. The `listItems` method checks if the inventory is empty. If the inventory is empty, the method prints a message to inform the user that there are no items in the inventory. Otherwise, the `printTable` method from the `Ui` object is called.
![ListStep3.png](UML%2FList%2FListStep3.png)
**Step 4.**. If the `printTable` method is called, it takes in an `ArrayList<Item> items` aas a parameter and prints out a table showing the name, UPC, quantity and price of all items in the inventory.







### Add
The add command is mainly handled by the `AddCommand` class, which extends the `Command` class.

### Edit
The "edit" command is mainly handled by the `EditCommand` class, which extends the `Command` class. It is parsed
by the `EditParser` class, which extends the `Parser` class.

### Restock
The `restock` command is mainly handled by the `RestockCommand` class, which extends the `Command` class. It is parsed 
by the `RestockParser` class, which extends the `Parser` class. Included below is a sequence diagram for the `restock`
command:

![RestockParser.png](UML/Restock/RestockParser.png)
![RestockCommand.png](UML/Restock/RestockCommand.png)

**Step 1**. When the user executes the command `restock upc/[UPC Code] qty/[Quantity]`, the
`ParserHandler` will create a new `RestockParser` object and pass the appropriate `input` and `Inventory` in which
the items are stored.

**Step 2**. 

### Sell
The "sell" command is mainly handled by the `SellCommand` class, which extends the `Command` class. It is parsed
by the `SellParser` class, which extends the `Parser` class. Included below is a sequence diagram for the `sell`
command:

![SellParser.png](UML/Sell/SellParser.png)

**Step 1**. When the user executes the command `sell upc/[UPC Code] qty/[Quantity]`, the
`ParserHandler` will create a new `SellParser` object and pass to it the appropriate `input` and the appropriate
`Inventory` in which the items are stored.

**Step 2**. 


### Remove
The remove command is mainly handled by the `RemoveCommand` class, which extends the `Command` class.

### Search
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

![SearchStep5UPC.png](UML%2FSearch%2FSearchStep5UPC.png)
![SearchStep5.png](UML%2FSearch%2FSearchStep5.png)

**Step 6**. If the `printSearchItems` method is called, it takes in an `ArrayList<Item> items` as a parameter and
prints out a table showing the name, UPC, quantity and price of all search results. Otherwise, the `printSearchUPCItems`
method takes in an `Item item` and prints it out in a table showing the name, UPC, quantity and price of the item.

### Filter
The filter command is mainly handled by the `FilterCommand` class, which extends the `Command` class. It is parsed by
the `FilterParser` class, which extends the `Parser` class.

![FilterSequence.png](UML%2FFilter%2FFilterSequence.png)
![FilterPrice.png](UML%2FFilter%2FFilterPrice.png)
![FilterTagCategory.png](UML%2FFilter%2FFilterTagCategory.png)

**Step 1**. When the user executes the command `filter f/[filtertype] p/[price type] [category/price/tag]`, the 
`ParserHandler` will create a new `FilterParser` object and pass to it the appropriate `input` and the appropriate
`Inventory` in which the items are stored.

![FilterStep1.png](UML%2FFilter%2FFilterStep1.png)

**Step 2**. The `run` method in `FilterParser` is called which overrides the `run` method in `Parser`. This leads the
`FilterParser` to call either the `parseFilterCategoryOrTag` or `parseFilterPrice` method, depending on whether the 
`f` flag is `category` or `tag` or `price` respectively. If the `f` flag is invalid, an error message is printed 
instead.

**Step 3**. The method `parseFilterCategoryOrTag` will take the keyword from the user input, create a new `FilterCommand`
object and pass to it the relevant `Inventory`, `value` and `filterMode`. The `filterMode` is the `f` flag. The method 
`parseFilterPrice` will check if the `p` flag is set correctly. If it is not set correctly, an error message will be 
printed out and execution of the method will halt. Otherwise, a new `FilterCommand` object is created and passed the 
`Inventory`, `Price`, and `FilterPriceMode`.

![FilterStep3Tag.png](UML%2FFilter%2FFilterStep3Tag.png)
![FilterStep3.png](UML%2FFilter%2FFilterStep3.png)

**Step 4**. The `run` method in the `FilterCommand` object is called which overrides the `run` method in the
`Command` object. This calls either the `filterCategory` method, `filterTags` method, or `filterPrice` which returns 
`ArrayList<Item>`, depending on the `filterMode`, which is set to either `filterMode` or `FilterPriceMode`. If there are no
filtered items, the methods will return `null`.

**Step 5**. The objects are returned to the `run` method. If the returned object is `null`, then the method
will inform the user that no filtered results were found. Otherwise, the `printSearchItems` from the `Ui` object is 
called.

![FilterStep5.png](UML%2FFilter%2FFilterStep5.png)

**Step 6**. If the `printSearchItems` method is called, it takes in an `ArrayList<Item> items` as a parameter and
prints out a table showing the name, UPC, quantity and price of all search results. 

### History
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


### Alert
The alert command is mainly handled by the `AddAlertCommand` class and `RemoveAlertCommand` class, both of which extend the `Command` class. It is parsed by the `AlertParser` class, which extends the `Parser` class.

![AlertParser.png](UML%2FAlert%2FAlertParser.png)



**Step 1**. When the user executes a command that begins with the word `alert`, the ParserHandler will create a new `AlertParser` object and pass in the appropriate `input`, as well as the corresponding `inventory` where the list of alerts for inventory items are stored.

![AlertStep1.png](UML%2FAlert%2FAlertStep1.png)


**Step 2**. The `run` method in `AlertParser` is called, which overrides the `run` method in `Parser`. The `AlertParser`checks if 
This leads the `AlertParser` to call either the `parseAddAlert` or `parseRemoveAlert`, depending on whether the `input` begins with the word `add` or `remove`.
If the `input` does not begin with either `add` or `remove`, an error is shown and the method will halt execution.

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













## Product scope
### Target user profile

{Describe the target user profile}

### Value proposition

{Describe the value proposition: what problem does it solve?}

## User Stories

| Version | As a ...                 | I want to ...                                                   | So that I can ...                                             |
|---------|--------------------------|-----------------------------------------------------------------|---------------------------------------------------------------|
| v1.0    | small business owner     | add new items to the inventory                                  | account for the stocks I have                                 |
| v1.0    | small business owner     | keep track of all the items we have in stock                    | have a record readily available                               |
| v1.0    | small business owner     | delete items from the inventory if they are no longer needed    | keep the inventory clean and up-to-date                       |
| v1.0    | IT team member           | update the quantity of items in the inventory                   | ensure the inventory always reflects the current stock levels |
| v1.0    | IT team member           | search for items in the inventory by name SKU or category       | quickly find what I need                                      |


| Version | As a ...                | I want to ...                                                            | So that I can ...                                                                                           |
|---------|-------------------------|--------------------------------------------------------------------------|-------------------------------------------------------------------------------------------------------------|
| v2.0    | small business owner    | analyze past sales from the historical records of the stocks             | make better decisions                                                                                       |
| v2.0    | small business owner    | see a report of items that are due to be reordered                       | make sure we have enough stock to meet customer demand                                                      |
| v2.0    | small business owner    | set different prices for items in the inventory                          | charge different prices for different items                                                                 |
| v2.0    | small business owner    | categorize items in the inventory                                        | quickly find items that belong to a specific category                                                       |
| v2.0    | small business owner    | directly top up and deduct the quantity of an item in the inventory list | keep track of the item quantities with ease without the inconvenience of editing the item quantity manually |
| v2.0    | small business owner    | see the list of commands that can be executed                            | quickly identify the function I can use and their command formats.                                          |
| v2.0    | small business owner    | set minimum and maximum stock levels for items in the inventory          | be alerted if our stock levels fall outside of these limits                                                 |
| v2.0    | IT team member          | assign unique SKUs to items in the inventory                             | easily track and manage the items                                                                           |
| v2.0    | convenience store owner | store information on the large variety of products                       | find them easily                                                                                            |


## Non-Functional Requirements

{Give non-functional requirements}

## Glossary

* *glossary item* - Definition

## Instructions for manual testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}
