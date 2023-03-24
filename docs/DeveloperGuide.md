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
- [Product Scope](#product-scope)
  - [Target User Profile](#target-user-profile)
  - [Value Proposition](#value-proposition)
- [User Stories](#user-stories)
- [Non-Functional Requirements](#non-functional-requirements)
- [Glossary](#glossary)
- [Instructions for Manual Testing](#instructions-for-manual-testing)

## Acknowledgements

{list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well}

## Design & implementation

{Describe the design and implementation of the product. Use UML diagrams and short code snippets where applicable.}


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

### Remove
The remove command is mainly handled by the `RemoveCommand` class, which extends the `Command` class.

### Search
The search command is mainly handled by the `SearchCommand` class, which extends the `Command` class. It is parsed by 
the `SearchParser` class, which extends the `Parser` class.

**Step 1**. When the user executes the command `search [keyword]` or `searchupc [keyword]`, the `ParserHandler` will create a 
new `SearchParser` object and pass to it the appropriate `input`, the `SearchType`, and the appropriate `Inventory` in 
which the items are stored.

**Step 2**. The `run` method in `SearchParser` is called which overrides the `run` method in `Parser`. This leads the 
`SearchParser` to call either the `parseSearch` or `parseSearchUPC` method, depending on whether the `SearchType` is
`Types.SearchType.KEYWORD` or `Types.SearchType.UPC` respectively.

**Step 3**. The methods `parseSearch` or `parseSearchUPC` will check the validity of the input, and if the input
is valid, both will create a new `SearchCommand` object, passing to it the relevant `Inventory`, `SearchType`, and 
the `input`. If the input is not valid, an error message will be printed out and method execution will halt.

**Step 4**. The `run` method in the `SearchCommand` class is called which overrides the `run` method in the
`Command` class. This calls either the `searchKeyword` method which returns `ArrayList<Item>`, or the `searchUPC`
method which returns an `Item` if there are found item(s), depending on whether the `SearchType` is 
`Types.SearchType.KEYWORD` or `Types.SearchType.UPC` respectively. Else, the methods will return `null`.

**Step 5**. The object(s) are returned to the `run` method. If the returned object is `null`, then the method
will inform the user that no search results were found. Otherwise, the `printSearchItems` or the 
`printSearchUPCItems` from the `Ui` class is called, depending on whether the `SearchType` is
`Types.SearchType.KEYWORD` or `Types.SearchType.UPC` respectively.

**Step 6**. If the `printSearchItems` method is called, it takes in an `ArrayList<Item> items` as a parameter and
prints out a table showing the name, UPC, quantity and price of all search results. Otherwise, the `printSearchUPCItems`
method takes in an `Item item` and prints it out in a table showing the name, UPC, quantity and price of the item.

### Filter
The filter command is mainly handled by the `FilterCommand` class, which extends the `Command` class. It is parsed by
the `FilterParser` class, which extends the `Parser` class.

**Step 1**. When the user executes the command `filter f/[filtertype] p/[price type] [category/price/tag]`, the 
`ParserHandler` will create a  new `FilterParser` object and pass to it the appropriate `input` and the appropriate
`Inventory` in which the items are stored.

**Step 2**. The `run` method in `FilterParser` is called which overrides the `run` method in `Parser`. This leads the
`FilterParser` to call either the `parseFilterCategoryOrTag` or `parseFilterPrice` method, depending on whether the 
`f` flag is `category` or `tag` or `price` respectively. If the `f` flag is invalid, an error message is printed 
instead.

**Step 3**. The method `parseFilterCategoryOrTag` will take the keyword from the user input, create a new `FilterCommand`
class and pass to it the relevant `Inventory`, `keyword` and `mode`. The `mode` is the `f` flag. The method 
`parseFilterPrice` will check if the `p` flag is set correctly. If it is not set correctly, an error message will be 
printed out and execution of the method will halt. Otherwise, a new `FilterCommand` class is created and passed the 
`Inventory`, `Price`, and `FilterPriceMode`.

**Step 4**. The `run` method in the `FilterCommand` class is called which overrides the `run` method in the
`Command` class. This calls either the `filterCategory` method, `filterTags` method, or `filterPrice` which returns 
`ArrayList<Item>`, depending on the `filterMode`, which is set to either `mode` or `FilterPriceMode`. If there are no
filtered items, the methods will return `null`.

**Step 5**. The objects are returned to the `run` method. If the returned object is `null`, then the method
will inform the user that no filtered results were found. Otherwise, the `printSearchItems` from the `Ui` class is 
called.

**Step 6**. If the `printSearchItems` method is called, it takes in an `ArrayList<Item> items` as a parameter and
prints out a table showing the name, UPC, quantity and price of all search results. Otherwise, the `printSearchUPCItems`
method takes in an `Item item` and prints it out in a table showing the name, UPC, quantity and price of the item.

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
| v1.0    | small business owner     | set minimum and maximum stock levels for items in the inventory | be alerted if our stock levels fall outside of these limits   |
| v1.0    | IT team member           | update the quantity of items in the inventory                   | ensure the inventory always reflects the current stock levels |
| v1.0    | IT team member           | search for items in the inventory by name SKU or category       | quickly find what I need                                      |


| Version | As a ...                 | I want to ...                                                   | So that I can ...                                             |
|---------|--------------------------|-----------------------------------------------------------------|---------------------------------------------------------------|
| v2.0    | small business owner     | analyze past sales from the historical records of the stocks    | make better decisions                                         |
| v2.0    | small business owner     | see a report of items that are due to be reordered              | make sure we have enough stock to meet customer demand        |
| v2.0    | small business owner     | set different prices for items in the inventory                 | charge different prices for different items                   |
| v2.0    | small business owner     | categorize items in the inventory                               | quickly find items that belong to a specific category         |
| v2.0    | IT team member           | assign unique SKUs to items in the inventory                    | easily track and manage the items                             |
| v2.0    | convenience store owner  | store information on the large variety of products              | find them easily                                              |


## Non-Functional Requirements

{Give non-functional requirements}

## Glossary

* *glossary item* - Definition

## Instructions for manual testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}
