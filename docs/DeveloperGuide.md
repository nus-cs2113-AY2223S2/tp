# Developer Guide

## Acknowledgements

{list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well}

## Design & implementation
{Describe the design and implementation of the product. Use UML diagrams and short code snippets where applicable.}
## Design
{insert high level diagram of overall project structure here}

Our main `Duke` class is responsible for the instantiation and launch of our application.  
Our overall project design is split into 4 components, `command`, `common`,`data` and `parser`.
 - `command`: The command executor.
 - `data`: Holds data and data structures of our application.
 - `parser`: Reads data from the user input and parses it into an 'executable' command.
 - `common`: Holds mainly static data that is used by multiple components.

## Implementation
### 'Add' feature
This mechanism is facilitated by `CommandAdd`, which extends `Command`, as well as `ParserAdd`. `ParserAdd` parses the 
user input into a string of words `parsedInput[]` which `CommandAdd` then stores internally. `CommandAdd` also stores an 
internal reference to the list of expenses `expenseList` to be added to.

`CommandAdd` implements the following operations:
 - `CommandAdd#execute()` -- Instantiates the `Expense` object with the parsed inputs and adds it to `expenseList`
 - `CommandAdd#roundInput(amount)` -- Rounds the expense amount to 2 decimal places. 

`ParserAdd` implements the following operations:
 - `ParserAdd#parseInput(userInput)` -- Parses the user input into an array of strings for `CommandAdd` to read.
 - `ParserAdd#checkType(input)` -- Checks the type of the input corresponding to its index.
 - `ParserAdd#substringIndex(type)` -- Returns the substring index of the start of the user input.
 - 
The `CommandAdd#execute()` operation is exposed in the main `Duke` class, while the operations in `ParserAdd` are
exposed in the `Parser` class.

![](/docs/diagrams/AddFeature.puml)

Given below is an example usage of the feature.

Step 1. The user executes `add amt/24 t/02-02-2012` as the `userInput` to add a new `Expense` into the the list of 
expenses. `Duke` calls `Parser#extractCommandKeyword(userInput)` to parse the input and determines that the `add` command is called.

Step 2. `Duke` instantiates a new `CommandAdd` and calls `CommandAdd#execute()`, which in turn calls 
`Parser#extractAddParameters(userInput)` and `ExpenseList#getExpenseList()`.

Step 3. `Parser#extractAddParameters(userInput)` then calls `ParserAdd#parseInput(userInput)` and returns the parsed 
input as a string of words `parsedInput[]`.

Step 4. `CommandAdd#execute` instantiates a new `Expense` object with the returned `parsedInput[]` and adds it to
`expenseList`.

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
