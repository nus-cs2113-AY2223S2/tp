[DeveloperGuide.md](DeveloperGuide.md)
# Developer Guide

## Acknowledgements

{list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well}

## Design & implementation

### Preventing duplicate items

When using the `add` function, we have decided to implement a passive function that checks whether the item with the same name already exists in the packingList.

This is done through the `contains()` method in class `PackingList()`, which is called during `execute` in an `AddCommand` object.

Below is the UML diagram showing what occurs during `add` function.
![img_1.png](img_1.png)
When `execute()` is called in `a`(object of class `AddCommand`), the `addItem(item)` method is called in the object `packingList`. This method will see if method `contains()` will return `true`. 

The `contains()` method, which is shown as a reference on the right of the UML diagram, is a boolean method that loops through all items in the packingList, and returns `true` if any of the existing items have the same description as the item to be added in `toAdd`, and `false` otherwise.

When `contains()` returns `true`, method `addItem(item)` will be interrupted and a `DuplicateItemException` will be thrown from `packingList`, which will be caught by `a`. If `contains()` returns false, the item will be added onto `packingList`. 

In both scenarios, `ui.printToUser` will be called to print a message to the user. In the former case, `ExistItemError` will be printed, while `AddSuccess` will be shown if the item was added with no issues. 


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
