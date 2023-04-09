# Developer Guide

SEP Helper is a desktop application for Mechanical Engineering students, st
udying at the
National University of Singapore (NUS), intending to go to Korea for a Student Exchange Programme (SEP).
---

## Table of Contents

1. [Acknowledgements](#acknowledgements)
2. [Design & Implementation](#design--implementation)
    1. [Architecture](#architecture)
    2. [Storage](#storage)
    3. [Parser](#parser)
    4. [UserInterface](#userinterface--ui-) 
    5. [Help Command](#help-command)
    6. [Module Commands](#module-commands)
       - [List Current Command](#list-current-command)
       - [List Current PU Command](#list-current-pu-command)
       - [List PU Command](#list-pu-command)
       - [List PU Modules Command](#list-pu-modules-command)
       - [Add Module Command](#add-module-command)
       - [Delete Module Command](#delete-module-command)
    7. [Deadline Commands](#deadline-commands)
       - [List Deadline Command](#list-deadline-command)
       - [Add Deadline Command](#add-deadline-command)
       - [Delete Deadline Command](#delete-deadline-command)
    8. [Reference Block for Sorting Modules](#reference-block-for-sortmodulesaccording-to-printing-length-function)
    9. [Budget Commands](#budget-commands) 
3. [Product Scope](#product-scope)
    1. [Target User Profile](#target-user-profile)
    2. [Value Proposition](#value-proposition)
4. [User Stories](#user-stories)
5. [Non-Functional Requirements](#non-functional-requirements)
6. [Glossary](#glossary)
7. [Instructions for Manual Testing](#instructions-for-manual-testing)

---

## Acknowledgements

{list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the
original source as well}

1. Using ByteArrayOutputStream to test System.out.print functions
   Author: dfa. Link: https://stackoverflow.com/questions/1119385/junit-test-for-system-out-println



# Design & implementation

## Architecture

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

**3. UI** (**outdated**)

UI class is in charge of the majority of the print functions present in the program.
It is instantiated once in both Parser and Duke classes, where it's print functions are utilized
to print outputs to the User.


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
the ListCurrentCommand. There are only two commands that will cause changes to the text file. They are the AddCommand
and DeleteCommand. This involves adding of new modules and deleting of old modules to the Storage. The text file will
be continuously updated every time an Add or Delete command is called.

Class Diagram of Storage:

![StorageClassDiagram.png](diagrams%2FStorage%2FStorageClassDiagram.png)

The Storage class follows the Singleton pattern, where there is only one instance of Storage class. During the
first initialisation of the Storage class, Storage also tries to handle the case where the txt have been tampered.
How the Storage handles this is through the checkDatabaseCorrupted function which the Storage class implements from
the Database Interface. 

**Checking for tampering:**

- The Storage class would save module mappings in a string format that contains all the information. 
- Checking for duplication would then occur.
- To double-check that such module mappings are not corrupted, it is cross-referenced with the main Module database from the
DataReader class.
- Tampered data will be removed or the module database would reset if
too many modules are affected.

Sequence Diagram of Storage initialisation:

![Storage.png](diagrams%2FStorage%2FStorage.png)

How module data is stored in text file:

Module information is stored in one single line separated by commas
`univID`,`moduleCode`,`moduleName`,`moduleMCs`,`nusModuleCode`,`nusModuleName`,`nusModuleMcs`

Reference readModData Diagram:

![readModData.png](diagrams%2FStorage%2FreadModData.png)

The Storage class also handles the adding of new modules and the deleting of past modules. When any of this occurs, the
txt file will be updated immediately after the successful adding/deletion of saved modules in the Storage.

- For adding of newly saved modules, they are added through appending to the saved_modules.txt file
- For deleting of past saved modules, they are deleted, and the txt file is updated by rewriting every module from the
  ArrayList of saved modules


### Parser

The parser class is responsible for parsing the user's input commands and returning the appropriate command object.
The commands that the parser class will initialise are ListPuCommand(), ListCurrentCommand(modules),
prepareListPuModulesCommand(userCommandSecondKeyword, universities), ExitCommand(),
prepareAddModuleCommand(storage, userCommandSecondKeyword, puModules, universities),
DeleteModuleCommand(storage, indexToRemove, modules), and HelpCommand(). The parser class will handle error checking by
throwing InvalidCommandException if the user's input command does not match the specified format.

The following class diagrams illustrates the relationship between the Parser class and the Command classes.
- (TODO: finish up the rest of the command cases)
![ParserSequenceDiagram.png](diagrams%2FParserSequenceDiagram.png)

### UserInterface (UI)

UI class main purpose is to be in charge of the majority of the print functions present in the program.
It has the singleton design pattern so that only one instance of UI is instantiated.
For some functions, the UI class filters out modules to print accordingly, therefore it does not exist 
solely for the purpose of holding print functions, but has some logical components as well.

**Class Diagram of UI Class**

![UIClassDiagram.png](diagrams%2FUIClassDiagram.png)


Future Improvements: UI to handle ALL of the printing functions present in the program.

---

## Module Commands
### Help Command

The help command provides a list of commands and the commands' respective input format for the user.
> Syntax: /help

The following sequence diagram shows the relationship between the classes involved when the delete command is called.

![HelpCommandSequenceDiagram.png](diagrams%2FCommands%2FHelpCommandSequenceDiagram.png)


### List Current Command

Lists out all of current modules that user has added.


> Syntax: /list current

Sequence Diagram of List Current Command.

![ListCurrentCommandSequenceDiagram.png](diagrams%2FCommands%2FListCurrentCommandSequenceDiagram.png)


**Explanation**

1. ListCurrentCommand calls printCurrentPuModList() of UI class, passing an ArrayList<Module> modules.
2. UI object calls ListCurrentPu Command for all universities stored in the class-level object _universities_.
3. UI prints out to userConsole the modules user has added to his list in order of the Partner Universities.

See ListCurrentPuCommand for further explanation: 
[ListCurrentPuCommand](#list-current-pu-command) 


### List Current Pu Command

Prints out modules selected by user specific to a Partner University.

> Syntax: /list current [_uniAbbreviation_]

**Sequence Diagram of List Current Pu Command**

![ListCurrentPuCommandSequenceDiagram.png](diagrams%2FCommands%2FListCurrentPuCommandSequenceDiagram.png)

**Explanation**

1. ListCurrentPuCommand object is initialized with ArrayList<Modules> modules containing all user selected modules
   and Integer univId which is the Partner University unique ID that user has inputted.
2. ListCurrentPuCommand calls PrintCurrentPuModList() of UI Class passing these two objects as arguments.
3. PrintCurrentPuModList first filters out modules of the specific Partner University using uniID from the
   ArrayList<Module> modules.
4. PrintCurrentPuModList loops through the class level object universities to retrieve the Partner university name
   corresponding to the uniID.
5. Loops through filtered modules retrieving module information and printing it out to User Console.
   _See reference block below._

Reference Diagram for Print Module Details:

![ListCurrentPuCommandPrintModuleDetailsSequenceDiagram.png](diagrams%2FCommands%2FListCurrentPuCommandPrintModuleDetailsSequenceDiagram.png)


### List Pu Command

Lists out all of Partner Universities.

> Syntax: /list pu

Sequence Diagram of List Pu Command.


![ListPuCommandSequenceDiagram.png](diagrams%2FCommands%2FListPuCommandSequenceDiagram.png)

**Explanation**

1. ListPuCommand calls printPUListMessage() of UI class, which simply prints out a string of message to user
2. ListPuCommand calls printPUList() of UI class.
3. UI class holds an instance of all the possible PUs in an Arraylist<University> object.
4. UI class loops through ArrayList<University> to receive the various university information and print out
   to UserConsole


### List Pu Modules Command

Prints out list of modules available at a given Partner University

> Syntax: /list [_uniAbbreviation_]

**Note: Partner Universities Abbreviations can be found using List Pu command**

Sequence Diagram of List Pu Modules Command.

![ListPuModulesCommandSequenceDiagram.png](diagrams%2FCommands%2FListPuModulesCommandSequenceDiagram.png)

**Explanation**

1. ListPuModulesCommand calls printPUModListMessage() of UI class, which prints out PU Name in a message.
2. ListPuModulesCommand calls printPUModules of UI class, passing the UI class an integer called univID which
   UI class uses to identify which Partner University to process.
3. UI class use uniID to loop through the list of modules available, and adds modules of the selected university
   to an ArrayList<Module> puModulesToPrint.
4. UI class loops through the modules in puModulesToPrint ArrayList<Module>, retrieving module information and
   printing it out to userConsole

### Add Module Command

Adds the Module the user has wants to save to the saved module's database.

> Syntax: /add [_uniAbbreviation/index_]

Sequence Diagram of Add Module Command.

![StorageAddModule.png](diagrams%2FStorage%2FStorageAddModule.png)

**Explanation**

1. AddModuleCommand calls addModuleToModuleList(moduleToAdd) of Storage class. moduleToAdd refers to the Module that
   the user has picked to add.
2. In the circumstance that the moduleToAdd is null, Storage would call the printAddModuleFailureMessage to tell the
   user that module adding has failed, and stop the operation of AddModuleCommand.
3. Storage class would then add the module to its ArrayList of saved modules.
4. Storage class would then call sortModulesAccordingToPrintingLength(modules), to ensure modules are sorted 
according to the correct printing length. _Refer to Reference Block for SortModulesAccording for more info._
[SortModulesAccordingToPrintingLength](#reference-block-for-sortmodulesaccording-to-printing-length-function)
5. Storage class would then initialise an instance of FileWriter to append the newly added module to the txt file.
6. After saving successfully, AddModuleCommand would call UI to print an AddModMessage and returns to Duke
   

**Future Development**

The UI class currently holds an instance of all available PUs and their modules.
This can and will be further refactored into other class to adhere to Single Responsibility Principle.
Possible Solution: Store the PUs and modules in DataReader and use a getter function when needed.

### Delete Module Command

The delete command removes a module from the user's saved list of modules that is specified by the user.
> Syntax: /remove [_uniAbbreviation_]/[_index_]

The following sequence diagram shows the relationship between the classes involved when the delete command is called.

![DeleteModuleCommandSequenceDiagram.png](diagrams%2FCommands%2FDeleteModuleCommandSequenceDiagram.png)

**Explanation**

1. DeleteModuleCommand calls deleteModule(indexToRemove, modules, storage, uniID) of Storage class.
2. indexToDelete and counterUpToIndexToDelete variables of type Int are initialised.
3. In the circumstance where indexToRemove is -1, deleteModule function will return false.
4. It will then loop through the list of modules and counterUpToIndexToDelete will keep incrementing until it is equal
to the indexToDeletePuSpecificListToZeroBased.
5. indexToDelete is then set to the current Module iteration index, and it will remove the module from the list of 
modules.
6. The list is the sorted according to the string length of each module in the list of modules to be printed out in a
readable format.
7. The list is saved in the modules txt file by overwriting it via writeModListToFile(module) function.
8. deleteModule function then returns true and DeleteModuleCommand calls UI class to print the successful deletion of
module message in the User Console.

---

## Deadline Commands
Class Diagram of Deadline Commands


![DeadlineClassDiagram.png](diagrams%2FDeadline%2FDeadlineClassDiagram.png)


### List Deadline Command
The list deadline command provides the list of deadlines that was added by the user.
> Syntax: /deadline/list

The following sequence diagram shows the relationship between the classes involved when the list deadline command is
called.

![ListDeadlineCommandSequenceDiagram.png](diagrams%2FDeadline%2FListDeadlineCommandSequenceDiagram.png)

**Explanation**
1. ListDeadlineCommands calls the UI class to print all the deadlines.
2. One by one, UI class calls Deadline class to get the deadline's task and its due date.
3. The UI class will then print to the user's console the deadline task and due date in readable and in a list format.

### Add Deadline Command
The add deadline command allows the user to add a deadline into the user's list of deadlines.
> Syntax: /deadline/add [_task_] /by [_due date_]
> 
> Take note that [_due date_] is in the format of [_dd-mm-yyyy_]

The following sequence diagram shows the relationship between the classes involved when the add deadline command is 
called.

![AddDeadlineCommandSequenceDiagram.png](diagrams%2FDeadline%2FAddDeadlineCommandSequenceDiagram.png)

**Explanation**
1. AddDeadlineModule command calls addDeadlineToDeadlines(deadlineToAdd) of DeadlineStorage class.
2. In the circumstance that the deadlineToAdd is null, DeadlineStorage will call printAddDeadlineFailureMessage to tell
the user that the deadline adding has failed, and stop the operation of AddDeadlineCommand.
3. DeadlineStorage class will then add the deadline to its ArrayList of saved deadlines.
4. DeadlineStorage class will then initialise an instance of FileWriter to append the newly added deadline to the txt
file.
5. After saving successfully, AddDeadlineCommand will call UI to print an AddDeadlineMessage and returns to Duke.

### Delete Deadline Command
The delete deadline command removes a deadline from the user's list of deadlines that is specified by the user.
> Syntax: /deadline/remove [_index_]

The following sequence diagram shows the relationship between the classes involved when the delete deadline command is 
called.

![DeleteDeadlineCommandSequenceDiagram.png](diagrams%2FDeadline%2FDeleteDeadlineCommandSequenceDiagram.png)

**Explanation**
1. DeleteDeadlineCommand calls deleteDeadline(indexToRemove, deadline, deadlineStorage) of Storage class.
2. It then removes the deadline respective to the index via .remove(index) function.
3. The list is saved in the deadlines txt file by overwriting it via writeDeadlinesToFile(deadline) function.
4. deleteDeadline function then returns true and DeleteDeadlineCommand calls UI class to print the successful deletion 
of deadline message in the User Console.


#### Reference Block for SortModulesAccording to Printing Length Function

![SortModulesAccordingToPrintingLengthFunction.png](diagrams%2FMiscellaneous%2FSortModulesAccordingToPrintingLengthFunction.png)

**Explanation** 
1. Storage calls Module's getPrintingLength() function.
2. Module retrieves its own module code, name and MCs and calculate their total length known as "printing length"
and returns it to Storage
3. Storage calls ArrayList modules sort function passing the "printing length" into a comparator. 
4. ArrayList Modules sorts the modules according to this "printing length"

**Goal of this sorting function**

The main objective of this sorting function is to increase readability of print functions for users.

Previously, when printing out multiple lines, the lines that are printed out are messy and hard to read.

![ExampleOfPrintFunctionsBeingHardToReadOneLine.png](diagrams%2FMiscellaneous%2FExampleOfPrintFunctionsBeingHardToReadOneLine.png)

The sorting functions aids in helping it more readable, when the list of mappings are made to be in one line and sorted
according to the length of string before the "maps to ---->" key words.

![ExampleOfPrintFunctionsMoreReadable.jpg](diagrams%2FMiscellaneous%2FExampleOfPrintFunctionsMoreReadable.jpg)

---

### Budget Commands
Class Diagram of Budget Commands
![budgetClassDiagram.png](diagrams%2Fbudget%2FbudgetClassDiagram.png)

#### View Budget Command (View)

> Syntax: /budget /view

Sequence Diagram of View Budget Command

![ViewBudgetCommand.png](diagrams%2Fbudget%2FViewBudgetCommand.png)

**Explanation**
1. ViewBudgetCommand calls the UI class to print all the budget details
2. One by one, UI class would call ViewBudgetCommand to get the budget, accommodation cost, airplane cost, food cost, 
entertainment cost, and lastly the surplus. After calling each of those functions, it will print out the output
accordingly.

#### Edit Budget Command (Budget)

> Syntax: /budget /budget [_amount_]

Edits the total budget the user plans to use for his/her SEP trip.

Sequence Diagram of Edit Budget Command

![EditBudgetCommand.png](diagrams%2Fbudget%2FEditBudgetCommand.png)

**Explanation**

1. EditBudgetCommand calls BudgetPlanner get command to get the initial budget that the user had.
2. EditBudgetCommand then calls BudgetPlanner's set command to change the budget to the latest amount the user inputted.
3. EditBudgetCommand calls BudgetPlanner get command again to check if the current budget is the same as initial budget.
4. If budget value has not changed, EditBudgetCommand would call UI class to print a BudgetNoChangeMessage and return.
5. If budget has changed, EditBudgetCommand would call UI class to print a EditBudgetMessage and return.

#### Edit Accommodation Command (Cost)

> Syntax: /budget /accommodation [_amount_]

Edits the total accommodation cost the user plans to spend for his/her SEP trip.

Sequence Diagram of Edit Accommodation Command

![EditAccommodationCommand.png](diagrams%2Fbudget%2FEditAccommodationCommand.png)

Note: All Cost Command Sequence Diagrams are similar to the EditAccommodationCommand, except change in variables

**Explanation**

1. EditAccommodationCommand calls BudgetPlanner get command to get the initial accommodation cost that the user had.
2. EditAccommodationCommand then calls BudgetPlanner's set command to change the accommodation cost to the latest 
amount the user inputted.
3. EditAccommodationCommand calls BudgetPlanner get command again to check if the current accommodation cost is the
same as initial budget.
4. If accommodation cost value has not changed, EditAccommodationCommand would call UI class to print a 
CostNoChangeMessage and return.
5. If accommodation cost has changed, EditAccommodationCommand would call UI class to print a 
EditCostMessage and return.

#### Edit Airplane Ticket Command (Cost)

> Syntax: /budget /airplane [_amount_]

Edits the total airplane ticket cost the user plans to spend for his/her SEP trip.

Sequence Diagram of Edit Airplane Ticket Command

![EditAirplaneTicketCommand.png](diagrams%2Fbudget%2FEditAirplaneTicketCommand.png)

Note: All Cost Command Sequence Diagrams are similar to the EditAccommodationCommand, except change in variables

**Explanation**

1. EditAirplaneTicketCommand calls BudgetPlanner get command to get the initial airplane ticket cost that the user had.
2. EditAirplaneTicketCommand then calls BudgetPlanner's set command to change the airplane ticket cost to the latest
   amount the user inputted.
3. EditAirplaneTicketCommand calls BudgetPlanner get command again to check if the current airplane ticket cost is the
   same as initial budget.
4. If airplane ticket cost value has not changed, EditAirplaneTicketCommand would call UI class to print a
   CostNoChangeMessage and return.
5. If airplane ticket cost has changed, EditAirplaneTicketCommand would call UI class to print a
   EditCostMessage and return.

#### Edit Food Command (Cost)

> Syntax: /budget /food [_amount_]

Edits the total food cost the user plans to spend for his/her SEP trip.

Sequence Diagram of Edit Budget Command

![EditFoodCommand.png](diagrams%2Fbudget%2FEditFoodCommand.png)

Note: All Cost Command Sequence Diagrams are similar to the EditAccommodationCommand, except change in variables

**Explanation**

1. EditFoodCommand calls BudgetPlanner get command to get the initial food cost that the user had.
2. EditFoodCommand then calls BudgetPlanner's set command to change the food cost to the latest
   amount the user inputted.
3. EditFoodCommand calls BudgetPlanner get command again to check if the current food cost is the
   same as initial budget.
4. If food cost value has not changed, EditFoodCommand would call UI class to print a
   CostNoChangeMessage and return.
5. If food cost has changed, EditFoodCommand would call UI class to print a
   EditCostMessage and return.

#### Edit Entertainment Command (Cost)

> Syntax: /budget /entertainment [_amount_]

Edits the total entertainment cost the user plans to spend for his/her SEP trip.

Sequence Diagram of Edit Entertainment Command

![EditEntertainmentCommand.png](diagrams%2Fbudget%2FEditEntertainmentCommand.png)

Note: All Cost Command Sequence Diagrams are similar to the EditAccommodationCommand, except change in variables

**Explanation**

1. EditEntertainmentCommand calls BudgetPlanner get command to get the initial entertainment cost that the user had.
2. EditEntertainmentCommand then calls BudgetPlanner's set command to change the entertainment cost to the latest
   amount the user inputted.
3. EditEntertainmentCommand calls BudgetPlanner get command again to check if the current entertainment cost is the
   same as initial budget.
4. If entertainment cost value has not changed, EditEntertainmentCommand would call UI class to print a
   CostNoChangeMessage and return.
5. If entertainment cost has changed, EditEntertainmentCommand would call UI class to print a
   EditCostMessage and return.


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
