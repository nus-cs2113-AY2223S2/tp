# Developer Guide

SEP Helper is a desktop application for Mechanical Engineering students, studying at the 
National University of Singapore (NUS), intending to go to Korea for a Student Exchange Programme (SEP).
---
## Table of Contents
1. [Acknowledgements](#acknowledgements)
2. [Design & Implementation](#design--implementation)
   1. [Architecture](#architecture)
   2. [Help Command](#help-command)
   3. [Delete Command](#delete-command)
3. [Product Scope](#product-scope)
   1. [Target User Profile](#target-user-profile)
   2. [Value Proposition](#value-proposition)
4. [User Stories](#user-stories)
5. [Non-Functional Requirements](#non-functional-requirements)
6. [Glossary](#glossary)
7. [Instructions for Manual Testing](#instructions-for-manual-testing)
---
## Acknowledgements

{list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well}

## Design & implementation

# Architecture
![Architecture.png](diagrams%2FArchitecture.png)

The above _**Architecture**_ diagram gives a high-level overall of the program. 

**Main Components of system**
1. Duke : Taking user inputs
2. Parser : Processes and Executes User Commands
3. UI : Prints out messages to user
4. Storage : Processes and stores 
5. DataReader

**1. Duke**

Duke holds an instance of each of the component mentioned above.
It is the starting point of our program and takes in user inputs.
Duke executes commands based on an object of class "Command" after Parser processes user inputs and 
returns a Command object back to Duke.

**2. Parser**

Parser class serves to process raw user input and map it to one of the various commands.
Parser class will return an object of class "Command", which will be used by Duke to execute the user's commands.

**3. UI**

UI class is in charge of the majority of the print functions present in the program.
It is instantiated once in both Parser and Duke classes, where it's print functions are utilized 
to print outputs to the User. 

Future Improvements: UI to handle ALL of the printing functions present in the program.

**4. Storage** 

Storage class holds an ArrayList of modules that the user has selected. 
It saves the user's modules to an external .txt file every time the module list is altered,
and reads from the same .txt file when the program is first booted up.

**5. DataReader**

DataReader class reads two external .txt files to acquire the list of Partner Universities and list
of Modules available in the PUs, and provides this information to other components.


{Describe the design and implementation of the product. Use UML diagrams and short code snippets where applicable.}

### Storage

The Storage class handles the loading and storing of information into a single text file in the User's computer.
During the initialisation of the Storage class, which is at the start of the program, loading of data occurs.
From here on, querying of the Storage class is allowed, where modules saved by the user can be accessed through
the ListCurrentCommand. There are only two commands that  will cause changes to the text file. They are the AddCommand 
and DeleteCommand. This involves adding of new modules and deleting of old modules to the Storage. The text file will 
be continuously updated every time an Add or Delete command is called.

How module data is stored in text file:

Module information is stored in one single line separated by commas
`univID`,`moduleCode`,`moduleName`,`moduleMCs`,`nusModuleCode`,`nusModuleName`,`nusModuleMcs`


### Help Command
The help command provides a list of commands and the commands' respective input format for the user.  

The following sequence diagram shows the relationship between the classes involved when the delete command is called.

### Delete Command
The delete command removes a module from the user's saved list of modules that is specified by the user.  

The following sequence diagram shows the relationship between the classes involved when the delete command is called.


### List Current Command 
Lists out all of current modules that user has added. 

> Syntax: list current

Sequence Diagram of List Current Command.

![ListCurrentCommandSequenceDiagram.png](diagrams%2FListCurrentCommandSequenceDiagram.png)

**Explanation**

1. ListCurrentCommand calls printCurrentModList() of UI class, passing an ArrayList<Module> modules.
2. UI accesses Module object in the ArrayList<Module> to receive information using getter functions.
3. UI prints out to userConsole the various information.

### List Pu Command 
Lists out all of Partner Universities.

> Syntax: list pu 

Sequence Diagram of List Pu Command.

![ListPuCommandSequenceDiagram.png](diagrams%2FListPuCommandSequenceDiagram.png)

**Explanation**

1. ListPuCommand calls printPUListMessage() of UI class, which simply prints out a string of message to user
2. ListPuCommand calls printPUList() of UI class.
3. UI class holds an instance of all the possible PUs in an Arraylist<University> object.
4. UI class loops through ArrayList<University> to receive the various university information and print out 
to UserConsole

### List Pu Modules Command

Prints out list of modules available at a given Partner University

> Syntax: list [_uniAbbreviation_]

Partner Universities Abbreviations can be found using List Pu command

Sequence Diagram of List Pu Modules Command.
![ListPuModulesCommandSequenceDiagram.png](diagrams%2FListPuModulesCommandSequenceDiagram.png)

**Explanation**
1. ListPuModulesCommand calls printPUModListMessage() of UI class, which prints out PU Name in a message.
2. ListPuModulesCommand calls printPUModules of UI class, passing the UI class an integer called univID which 
UI class uses to identify which Partner University to process.
3. UI class use uniID to loop through the list of modules available, and adds modules of the selected university 
to an ArrayList<Module> puModulesToPrint.
4. UI class loops through the modules in puModulesToPrint ArrayList<Module>, retrieving module information and 
printing it out to userConsole

**Future Development**

The UI class currently holds an instance all available PUs and their modules.
This can and will be further refactored into other class to adhere to Single Responsbility Principle.
Possible Solution: Store the PUs and modules in DataReader and use a getter function when needed.

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
