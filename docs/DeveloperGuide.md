# Developer Guide

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

### Edit
The "edit" command is mainly handled by the `EditCommand` class, which extends the `Command` class.




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
