# Developer Guide

## Acknowledgements
Credits to [Personbook](https://github.com/nus-cs2113-AY2223S2/personbook)
for some reused skeleton code and inspiration on OOP implementation.

{list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well}

## Setting up

## Design 
### Architecture (Kristian)
### Command Component (Zheng Rong)

##### The command component consists of the following:

![](./uml/CommandPackageDiagram.png)

- 4 subcomponents: Command, HelpCommand, ExitCommand, IncorrectCommand

- 4 sub packages with the following names: deadline, meeting, menu and staff
    - Each package contains the 3 general commands: 
        - AddCommand 
        - DeleteCommand
        - ViewCommand
    - The general commands have specific names depending on the package it is in.

##### Command
It is an abstract class consisting of abstract methods ```execute``` and ```isExit```. These classes are implemented by the 4 sub components as well as the general commands in the 4 sub packages.
The general functions for the methods are as follows:
- execute: To run the functionality specific to the Command class it is implemented in.
- isExit: To return a boolean value that indicates whether the program should exit when the command is called.

##### HelpCommand
HelpCommand inherits from the abstract ```Command``` class.
It has the following functions:
- Prints out the list of commands available for the user to type to the console.
- Returns whether the program should exit when this command is called.

##### ExitCommand
ExitCommand inherits from the abstract ```Command``` class.
It has the following functions:
- Sets the boolean variable that decides whether the program should exit when this command is called to <b>true</b>.
- Returns whether the program should exit when this command is called.

##### IncorrectCommand
IncorrectCommand inherits from the abstract ```Command``` class.
It has the following functions:
- Prints out the error message to the console when an invalid command is keyed in.
- Returns whether the program should exit when this command is called.

##### AddCommand
AddCommand inherits from the abstract ```Command``` class.
AddCommand is the placeholder name for the commands in the sub packages that are of this type. For example, the AddCommand class in the deadline package is ```AddDeadlineCommand```. 
It has the following functions:
- Calls the respective method in the respective manager Class in the manager package. For example: addDishCommand in the DishManager Class is called when AddDishCommand is called.
- Returns whether the program should exit when this command is called.

##### DeleteCommand
DeleteCommand inherits from the abstract ```Command``` class.
DeleteCommand is the placeholder name for the commands in the sub packages that are of this type. For example, the DeleteCommand class in the deadline package is ```DeleteDeadlineCommand```. 
It has the following functions:
- Calls the respective method in the respective manager Class in the manager package. For example: DeleteDishCommand in the DishManager Class is called when DeleteDishCommand is called.
- Returns whether the program should exit when this command is called.

##### ViewCommand
ViewCommand inherits from the abstract ```Command``` class.
ViewCommand is the placeholder name for the commands in the sub packages that are of this type. For example, the ViewCommand class in the deadline package is ```ViewDeadlineCommand```. 
It has the following functions:
- Calls the respective method in the respective manager Class in the manager package. For example: ViewDishCommand in the DishManager Class is called when ViewDishCommand is called.
- Returns whether the program should exit when this command is called.

### Manager Component (PeiHao)
### Ui Component (HuiQi)
### Utils Component (Darren)

(Insert Class Diagram)
The Utils Component consists of the Parser class that will handle the parsing and preparing of commands within the DinerDirector application.
The Parser class performs the following functions:

Parse the command given the user input and extracts out the necessary information related to the command.
Returns the appropriate Command Class based on the parsed input.

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

The Dish feature consists of three functions:

##### Adding dish to list:
- When the ```AddDishCommand()``` constructor is called, it stores the dish name, price and the list of ingredients in an entity called Dish. 
- When the ```execute()``` command in ```AddDishCommand``` is called, it calls the ```addDishCommand()``` in ```DishManager``` class that adds the Dish into an arraylist of Dishes.
- It then prints out a message to the console that informs the user that a dish has been added. 

##### Deleting dish on the list:
- When the ```DeleteDishCommand()``` constructor is called, it stores the index of the Dish to be deleted from the arraylist of Dishes. 
- When the ```execute()``` command in ```DeleteDishCommand``` is called, it calls the ```DeleteDishCommand()``` in ```DishManager``` class that deletes the Dish at the specified index in the arraylist of Dishes
- It then prints out a message to the console that informs the user that a dish has been added.

##### Viewing the list of dishes:
- When the ```execute()``` command in ```ViewDishCommand``` is called, it calls the ```ViewDishCommand()``` in ```DishManager``` class that returns the formatted string of all the dishes in the arraylist. 
- It then prints out the the formatted string to the console.

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
