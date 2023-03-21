# Developer Guide

## Acknowledgements
Credits to [Personbook](https://github.com/nus-cs2113-AY2223S2/personbook)
for some reused skeleton code and inspiration on OOP implementation.

{list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well}

## Setting up

## Design 
### Architecture (Kristian)
### Command Component (Zheng Rong)
### Manager Component (PeiHao)
### Ui Component (HuiQi)
### Utils Component (Darren)
(Insert UML Diagram)  
The Utils Component consists of the Parser class that will handle the parsing and preparing of commands within the DinerDirector application.  
The `Parser` class performs the following functions: 
* Parse the command given the user input and extracts out the necessary information related to the command.
* Returns the appropriate Command Class based on the parsed input.

## Implementation
### Parsing Feature (Darren)
(Insert Sequence Diagram)  
How the parsing works:
1. When the `parseCommand()` method is called from `DinerDirector` class, the `parseCommand()` will split the given userInput first.
2. With the `userInputSplit[]`, the `0` index will be extracted out. That will be used as identification for the command the user typed in.
3. The `commandWord` will be used in the switch statement to select the appropriate command. returning `IncorrectCommand` class is the default behavior.
4. If the `commandWord` is valid, it will run the appropriate `prepareXYZCommand()`.
5. Each of the individual `prepareXYZCommand()` will take in the userInput without the command portion. The variable is named `userInputNoCommand`. The `prepareXYZCommand()` will check the userInput to see if all the appropriate values are added, and return `XYZCommand` class if the values are correct. `prepareXXXCommand()` will return `IncorrectCommand` class if there are some missing values or inappropriate values.
### Meeting Feature (PeiHao)
### Deadline Feature (HuiQi)
### Staff Feature (Kristian)
### Dish Feature (Zheng Rong)
{Describe the design and implementation of the product. Use UML diagrams and short code snippets where applicable.}


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
