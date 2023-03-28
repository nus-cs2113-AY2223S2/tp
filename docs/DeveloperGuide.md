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
  - [History](#history)
  - [Category](#category)
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
The list feature is facilitated by the `ListCommand` class, which extends the `Command` class.

**Step 1**. When the user executes the command `list`, the `Parser` class calls the method `parseList`. This creates a new `ListCommand` object.
The constructor of the `ListCommand` class takes in an Inventory object as a parameter.

**Step 2**. The `run` method in `ListCommand` is called which overrides the `run` method in `Command`. The `listItem` method is called.

**Step 3**. The `listItems` method checks if the inventory is empty. If the inventory is empty, an error message is printed. 
Otherwise, a success message is printed and the `printTable` method from the `Ui` class is called.

**Step 4**. The `printTable` method takes in the ArrayList `itemInventory` as a parameter and prints out a table showing the name, UPC, quantity and price of all items in `itemInventory`.

### Add
The add command is mainly handled by the `AddCommand` class, which extends the `Command` class.

### Edit
The "edit" command is mainly handled by the `EditCommand` class, which extends the `Command` class.

### Restock
The "restock" command is mainly handled by the `RestockCommand` class, which extends the `Command` class.

### Sell
The "sell" command is mainly handled by the `SellCommand` class, which extends the `Command` class.

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

### Category
The category command is mainly handled by the `CategoryCommand` class, which extends the `Command` class. It is parsed by
the `CategoryParser` class, which extends the `Parser` class.

![CategoryParser.png](UML/Category/CategoryParser.png)

**Step 1** When the user executes the command `cat {list/function/[Category]}`, the `ParserHandler` will create a new 
`CategoryParser` object and pass to it the appropriate `input` and the appropriate `Inventory` in which the items are
stored.

**Step 2**. The `run` method in `CategoryParser` is called which overrides the `run` method in `Parser`. 
`CategoryParser` checks if the `input` is not empty. If not, an error is shown and the method halts execution. 
Otherwise, the `CategoryParser` will create a new `CategoryCommand` object and pass it the relevant inventory and 
user input.

**Step 3**. The `run` method in the `CategoryParser` object is called which overrides the `run` method in the `Command` 
object. The `CategoryCommand` object will call either `listAllCategories` or `listCategoriesAndItems` or `findCategory` 
method, depending on the user input (`list`, `table`, `[Category]` respectively).

**Step 4**. `listAllCategories` and `listCategoriesAndItems` will call the `printCategory` function from the `Ui` object
if the category hashmap is not empty. Otherwise, the methods will inform the user that the inventory list is empty and there
is no category hashmap available. Whereas `findCategory` will call the `printCategory` function from the `Ui` object if 
the category that user input is found. Otherwise, the method will inform user that the category cannot be found.

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
