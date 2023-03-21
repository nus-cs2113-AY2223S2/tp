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


## Product scope
### Target user profile

{Describe the target user profile}

### Value proposition

{Describe the value proposition: what problem does it solve?}

## User Stories

|Version| As a ... | I want to ... | So that I can ...|
|--------|----------|---------------|------------------|
|v1.0|new user|see usage instructions|refer to them when I forget how to use the application|
|v2.0|user|find a to-do item by name|locate a to-do without having to go through the entire list|

## Non-Functional Requirements

{Give non-functional requirements}

## Glossary

* *glossary item* - Definition

## Instructions for manual testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}
