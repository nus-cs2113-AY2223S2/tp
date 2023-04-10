# Developer Guide


SEP Helper is a desktop application for Mechanical Engineering students, studying at the
National University of Singapore (NUS), intending to go to Korea for a Student Exchange Programme (SEP).
---

## Table of Contents

1. [Acknowledgements](#acknowledgements)
2. [Design & Implementation](#design--implementation)
    1. [Architecture](#architecture)
    2. [ModuleStorage](#modulestorage)
    3. [Parser](#parser)
    4. [UserInterface](#userinterface-ui) 
    5. [Help Command](#help-command)
    6. [Module Commands](#module-commands)
       - [List Current Command](#list-current-command)
       - [List Current PU Command](#list-current-pu-command)
       - [List PU Command](#list-pu-command)
       - [List PU Modules Command](#list-pu-modules-command)
       - [List Found NUS Modules Command](#list-found-nus-mods-command)
       - [List Mappable NUS Modules Command](#list-mappable-nus-mods-command)
       - [Add Module Command](#add-module-command)
       - [Delete Module Command](#delete-module-command)
    7. [Deadline Commands](#deadline-commands)
       - [List Deadline Command](#list-deadline-command)
       - [Add Deadline Command](#add-deadline-command)
       - [Delete Deadline Command](#delete-deadline-command)
    8. [Reference Block for Sorting Modules](#reference-block-for-sortmodulesaccording-to-printing-length-function)
    9. [Budget Commands](#budget-commands) 
       - [View Budget Command](#view-budget-command-view)
       - [Edit Budget Command](#edit-budget-command-budget)
       - [Edit Accommodation Command](#edit-accommodation-command-cost)
       - [Edit Airplane Ticket Command](#edit-airplane-ticket-command-cost)
       - [Edit Food Command](#edit-food-command-cost)
       - [Edit Entertainment Command](#edit-entertainment-command-cost)
3. [Product Scope](#product-scope)
    1. [Target User Profile](#target-user-profile)
    2. [Value Proposition](#value-proposition)
4. [User Stories](#user-stories)
5. [Non-Functional Requirements](#non-functional-requirements)
6. [Glossary](#glossary)
7. [Instructions for Manual Testing](#instructions-for-manual-testing)

---

## Acknowledgements

1. Using ByteArrayOutputStream to test System.out.print functions
   Author: dfa. Link: https://stackoverflow.com/questions/1119385/junit-test-for-system-out-println

   
# Design & implementation

## Architecture

![Architecture Diagram.png](diagrams%2FMiscellaneous%2FArchitecture%20Diagram.png)

The above _**Architecture**_ diagram gives a high-level overall of the program.

**Main Components of system**

1. SEPHelper : Taking user inputs
2. Parser : Processes user inputs and creates Command Class objects accordingly
3. Command : Executes functionalities  
4. UI : Prints out messages to user
5. ModuleStorage : Processes and stores module mappings
6. DataReader : Reads and provides module mapping information and partner universities information
7. Budget Storage : Processes and stores budget information
8. Deadline Storage: Processes and stores deadline information

**1. SEPHelper**

SEPHelper is the starting point of our program and takes in user inputs.
SEPHelper executes commands based on an object of class "Command" passed to it after Parser processes user inputs and
returns a Command object back to SEPHelper.

Sequence Diagram for SEPHelper:

![DukeHighLevel.png](diagrams%2FDukeHighLevel.png)

Reference Diagram for SEPHelper initialisation:

![DukeInitialisation.png](diagrams%2FDukeInitialisation.png)


**2. Parser**

Parser class serves to process raw user input and map it to one of the various commands.
Parser class will return an object of class "Command", which will be used by SEPHelper to execute the user's commands.

**3. Command**

Command class serves to execute the functionalities of the program.
It is an abstract class inherited by other various commands, where each of those commands have their
own functionality to execute.

**4. UI** 

UI class is in charge of the majority of the print functions present in the program.
It's print functions are utilized by many other components in order to print outputs to the user.

**5. Module Storage**

ModuleStorage class holds an ArrayList of modules that the user has selected.
It saves the user's modules to an external .txt file every time the module list is altered,
and reads from the same .txt file when the program is first booted up.

**6. DataReader**

DataReader class reads two external .txt files to acquire the list of Partner Universities and list
of Modules available in the PUs, and provides this information to other components.

**7. Budget Storage**

BudgetStorage class reads from the budget.txt file to acquire the stored budget details from the user when
saving their budget. This data is then passed to the BudgetPlanner to control.

**8. Deadline Storage**

DeadlineStorage class reads from deadlines.txt to acquire the stored deadline details from the user when
saving new deadlines to be added. It stores both the deadline and the description of the task.

### ModuleStorage

The ModuleStorage class handles the loading and storing of information into a single text file in the User's computer.
During the initialisation of the ModuleStorage class, which is at the start of the program, loading of data occurs.
From here on, querying of the ModuleStorage class is allowed, where modules saved by the user can be accessed through
the ListCurrentCommand. There are only two commands that will cause changes to the text file. They are the AddCommand
and DeleteCommand. This involves adding of new modules and deleting of old modules to the ModuleStorage. The text file 
will be continuously updated every time an Add or Delete command is called.

Class Diagram of ModuleStorage:

![StorageClassDiagram.png](diagrams%2FStorage%2FStorageClassDiagram.png)

The ModuleStorage class follows the Singleton pattern, where there is only one instance of ModuleStorage class. During the
first initialisation of the ModuleStorage class, ModuleStorage also tries to handle the case where the txt have been tampered.
How the ModuleStorage handles this is through the checkDatabaseCorrupted function which the ModuleStorage class implements from
the Database Interface. 

**Checking for tampering:**

- The ModuleStorage class would save module mappings in a string format that contains all the information. 
- Checking for duplication would then occur.
- To double-check that such module mappings are not corrupted, it is cross-referenced with the main Module database from the
DataReader class.
- Tampered data will be removed or the module database would reset if
too many modules are affected.

Sequence Diagram of ModuleStorage initialisation:

![Storage.png](diagrams%2FStorage%2FStorage.png)

How module data is stored in text file:

Module information is stored in one single line separated by commas
`univID`,`moduleCode`,`moduleName`,`moduleMCs`,`nusModuleCode`,`nusModuleName`,`nusModuleMcs`

Reference readModData Diagram:

![readModData.png](diagrams%2FStorage%2FreadModData.png)

The ModuleStorage class also handles the adding of new modules and the deleting of past modules. When any of this occurs, the
txt file will be updated immediately after the successful adding/deletion of saved modules in the ModuleStorage.

- For adding of newly saved modules, they are added through appending to the saved_modules.txt file
- For deleting of past saved modules, they are deleted, and the txt file is updated by rewriting every module from the
  ArrayList of saved modules

### Parser

The Parser class is responsible for parsing the user's input commands and returning the appropriate command object.
The `parseUserCommand()` is the main function of the Parser class where it first tokenizes the user input string and 
extracts the first two keywords of the input. The first keyword is used to determine the type of command that the user
is executing, while the second keyword (if it exists) is used as an argument for the command. Afterwards, it uses a
switch-case statement to execute the command corresponding to the first keyword in the user's input. if the user's input command does not
match the specified command format, it will throw `InvalidCommandException` and `ExceptionHandleCommand` is returned
instead.

The following class diagrams illustrates the relationship between the Parser class and the Command classes.

![ParserSequenceDiagram.png](diagrams%2FParserSequenceDiagram.png)

Reference prepareListCommands Sequence Diagram:

![prepareListCommand.png](diagrams%2Fremove%2FprepareListCommand.png)

Reference prepareSearchByNusModCode Sequence Diagram:

![prepareSearchByNusModCode.png](diagrams%2Fremove%2FprepareSearchByNusModCode.png)

Reference ExitCommand Sequence Diagram:

![ExitCommand.png](diagrams%2Fremove%2FExitCommand.png)

Reference prepareAddModuleCommand Sequence Diagram:

![prepareAddModuleCommand.png](diagrams%2Fremove%2FprepareAddModuleCommand.png)

Reference prepareRemoveModuleCommand Sequence Diagram:

![removeParser.png](diagrams%2Fremove%2FremoveParser.png)

Reference prepareRemoveModuleCommand Sequence Diagram:

![removeParser.png](diagrams%2Fremove%2FremoveParser.png)

Reference HelpCommand Sequence Diagram:

![helpParser.png](diagrams%2Fremove%2FhelpParser.png)

Reference prepareBudgetCommand Sequence Diagram:

![budgetParser.png](diagrams%2Fremove%2FbudgetParser.png)

Reference BudgetCommandType Sequence Diagram:

![BudgetCommandType.png](diagrams%2Fremove%2FBudgetCommandType.png)

Reference listDeadlineCommand Sequence Diagram:

![deadlinelistParser.png](diagrams%2Fremove%2FdeadlinelistParser.png)

Reference prepareAddDeadlineCommand Sequence Diagram:

![deadlineaddParser.png](diagrams%2Fremove%2FdeadlineaddParser.png)

Reference deleteDeadlineCommand Sequence Diagram:

![deadlinedeleteParser.png](diagrams%2Fremove%2FdeadlinedeleteParser.png)

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
   to UserConsole.


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

### List Found Nus Mods Command

Allows the user to search for mappable PU's modules from the specific NUS module code inputted.
> Syntax: /search [Specific Nus Module Code]

Sequence Diagram of List Found Nus Mods Command.
![ListFoundNusModsCommand.png](diagrams%2FCommands%2FListFoundNusModsCommand.png)

**Explanation**
1. ListFoundNusModsCommand object is initialized with ArrayList<Modules> foundNusModList containing all
   mappable PU's modules, nusModCode containing the specific NUS module code inputted and the Arrayist universities.
2. ListFoundNusModsCommand calls printFoundNusModules() of UI Class passing these three objects as arguments.
3. printFoundNusModules() first filters out modules of the specific Partner University using uniID from the
   ArrayList<Module> modules.
4. printFoundNusModules() loops through the ArrayList<Modules> foundNusModList to get the corresponding PU's moduleCode,
   moduleName, moduleMCs and currPuAbbr.
5. In the loop, it will print out to the User Control the list of mappable PU's module according to the user's specific
   Nus module code and the lists will be shown accordingly to their PU university.

### List Mappable Nus Mods Command

Shows the user the list of available Nus Module Code that they can use to search for mappable PU's module
> Syntax: /search /mods

Sequence Diagram of List Mappable Nus Mods Command.
![ListMappableNusModsCommand.png](diagrams%2FCommands%2FListMappableNusModsCommand.png)

**Explanation**
1. ListMappableNusModsCommand object calls printNusMods() from the UI class
2. To remove the duplicated Nus Module Codes in the ArrayList<Module> allModule, removeDupeNusMods() was called and
   return the HashSet<String> nusModuleCodeList.
3. printNusMods() will then loop through for each String nusModCode in the nusModuleCodeList.
4. At each nusModCode string, it will loop through all the PU modules in the allModules ArrayList<Module> to retrieve
   Nus module details such as nusModuleCode string, nusModuleName string and int nusModuleMc.
5. Once a match is found for the nusModuleCode and nusModCode, it will print out the available Nus module details and
   break from the loop to print the next Nus module details.

### Add Module Command

Adds the Module the user has wants to save to the saved module's database.

> Syntax: /add [_uniAbbreviation/index_]

Sequence Diagram of Add Module Command.

![StorageAddModule.png](diagrams%2FStorage%2FStorageAddModule.png)

**Explanation**

1. AddModuleCommand calls addModuleToModuleList(moduleToAdd) of ModuleStorage class. moduleToAdd refers to the Module that
   the user has picked to add.
2. In the circumstance that the moduleToAdd is null, ModuleStorage would call the printAddModuleFailureMessage to tell the
   user that module adding has failed, and stop the operation of AddModuleCommand.
3. ModuleStorage class would then add the module to its ArrayList of saved modules.
4. ModuleStorage class would then call sortModulesAccordingToPrintingLength(modules), to ensure modules are sorted 
according to the correct printing length. _Refer to Reference Block for SortModulesAccording for more info._
[SortModulesAccordingToPrintingLength](#reference-block-for-sortmodulesaccording-to-printing-length-function)
5. ModuleStorage class would then initialise an instance of FileWriter to append the newly added module to the txt file.
6. After saving successfully, AddModuleCommand would call UI to print an AddModMessage and returns to SEPHelper
   

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

1. DeleteModuleCommand calls deleteModule(indexToRemove, modules, moduleStorage, uniID) of ModuleStorage class.
2. indexToDelete and counterUpToIndexToDelete variables of type Int are initialised.
3. In the circumstance where indexToRemove is -1, deleteModule function will return false.
4. It will then loop through the list of modules and counterUpToIndexToDelete will keep incrementing until it is equal
to the indexToDeletePuSpecificListToZeroBased.
5. indexToDelete is then set to the current Module iteration index, and it will remove the module from the list of 
modules.
6. The list is then sorted according to the string length of each module in the list of modules to be printed out in a
readable format.
7. The list is saved in the saved_modules.txt file by overwriting it via writeModListToFile(module) function.
8. deleteModule function then returns true and DeleteModuleCommand calls UI class to print the successful deletion of
module message in the User Console.

---

#### Reference Block for SortModulesAccording to Printing Length Function

![SortModulesAccordingToPrintingLengthFunction.png](diagrams%2FMiscellaneous%2FSortModulesAccordingToPrintingLengthFunction.png)

**Explanation** 
1. ModuleStorage calls Module's getPrintingLength() function.
2. Module retrieves its own module code, name and MCs and calculate their total length known as "printing length"
and returns it to ModuleStorage
3. ModuleStorage calls ArrayList modules sort function passing the "printing length" into a comparator. 
4. ArrayList Modules sorts the modules according to this "printing length"

**Goal of this sorting function**

The main objective of this sorting function is to increase readability of print functions for users.

Previously, when printing out multiple lines, the lines that are printed out are messy and hard to read.

![ExampleOfPrintFunctionsBeingHardToReadOneLine.png](diagrams%2FMiscellaneous%2FExampleOfPrintFunctionsBeingHardToReadOneLine.png)

The sorting functions aids in helping it more readable, when the list of mappings are made to be in one line and sorted
according to the length of string before the "maps to ---->" key words.

![ExampleOfPrintFunctionsMoreReadable.jpg](diagrams%2FMiscellaneous%2FExampleOfPrintFunctionsMoreReadable.jpg)

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
5. After saving successfully, AddDeadlineCommand will call UI to print an AddDeadlineMessage and returns to SEPHelper.

### Delete Deadline Command
The delete deadline command removes a deadline from the user's list of deadlines that is specified by the user.
> Syntax: /deadline/remove [_index_]

The following sequence diagram shows the relationship between the classes involved when the delete deadline command is
called.

![DeleteDeadlineCommandSequenceDiagram.png](diagrams%2FDeadline%2FDeleteDeadlineCommandSequenceDiagram.png)

**Explanation**
1. DeleteDeadlineCommand calls deleteDeadline(indexToRemove, deadline, deadlineStorage) of DeadlineStorage class.
2. It then removes the deadline respective to the index via .remove(index) function.
3. The list is saved in the deadlines txt file by overwriting it via writeDeadlinesToFile(deadline) function.
4. deleteDeadline function then returns true and DeleteDeadlineCommand calls UI class to print the successful deletion
   of deadline message in the User Console.

---

### Budget Commands

Class Diagram of Budget Commands

![budgetClassDiagram.png](diagrams%2Fbudget%2FbudgetClassDiagram.png)

The BudgetPlanner class is the main control centre for all budget commands. It helps facilitate commands being called
to edit Budget data of the BudgetStorage. It handles exception handling for invalid budget and cost values. It also
handles the main mathematical logic to make sense of the BudgetStorage data.

BudgetStorage saves data in this format of the txt file.

`amount` - Budget

`amount` - Accommodation cost

`amount` - Airplane Ticket cost

`amount` - Food cost

`amount` - Entertainment cost

Preventive measures would be done to check if the amount entered is valid or that is some tampering of the data out of
the usual format through the checkDatabaseCorrupted() function.

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

Mechanical Engineering students of National University of Singapore looking to go to Korea for the 
Student Exchange Programme. This product is for those students who prefer CLI (Command-Line Interface) 
over surfing the web for information on the student exchange program.


### Value proposition


The Student Exchange Programme can be overwhelming due to the number of Partner Universities available, 
each offering different module combinations, 
as well as the need to provide various administrative documents and adhere to deadlines.

This is made worse as the 
student exchange programme is often the first time students of National University of Singapore (NUS) leave 
Singapore for an extended period of time independently.


This product aims to better prepare NUS Mechanical Engineering students to embark on the Student Exchange Programme to 
Korea in the following areas.
1. Module Mapping of NUS Modules to Korean Partner University's Modules. 
2. Budgeting of funds.
3. Reminders of key deadlines related to the Student Exchange Programme's process.



## User Stories

| Version | As a ...                                               | I want to ...                                                    | So that I can ...                                                 |
|---------|--------------------------------------------------------|------------------------------------------------------------------|-------------------------------------------------------------------|
| v1.0    | Mechanical Engineering student who is going for SEP    | access the list of pre-mapped modules                            | make better choices on which modules to map                       |
| v1.0    | Mechanical Engineering student who is going for SEP    | add the modules that I want to map into a list                   | keep track of my choices/options                                  |
| v1.0    | Mechanical Engineering student who is going for SEP    | look up for the PU’s information                                 | better planning of SEP                                            |
| v1.0    | Mechanical Engineering student who is going for SEP    | delete module mapping plans to change my schedule                | as I receive feedbacks from my academic advisor along the way     |
| v2.0    | Mechanical Engineering student who is going for SEP    | keep a separate list of  modules I am interested in for each PUs | compare modules between different PUs                             |
| v2.0    | Mechanical Engineering student who is going for SEP    | search mappable PU modules by the NUS module code                | see the mappable PU modules according to the specific NUS modules |
| v2.0    | forgetful Mechanical Engineering student going for SEP | set and view important deadlines                                 | not miss anything that would impact my SEP process                |
| v2.0    | forgetful Mechanical Engineering student going for SEP | be able to get notifications for urgent submissions              | not miss any important submissions for SEP                        |
| v2.0    | SEP student                                            | plan what areas I will be spending on in the SEP trip            | understand how much money I have for leisure                      |
| v2.0    | SEP student                                            | see the total cost I would be spending on the SEP trip           | save enough money to go on the trip                               |
| v2.1    | SEP student                                            | see all the available NUS modules that can be mapped to PU modules | see which NUS modules I can map                                   |


## Non-Functional Requirements

1. SEP Helper should work on the Windows, Linux, and OS-X platforms with only Java 11 installed.
2. SEP Helper should work better for users with a faster typing speed.
3. SEP Helper should work without requiring an installer.
4. SEP Helper should be able to process mappings within a reasonable speed of 3 seconds.

## Glossary

* *SEP Helper* - The name of this software programme.
* *CLI* - Command-Line interface is a text-based user interface (UI) used to run programs, manage computer files and interact with the computer.
* *NUS* - National University of Singapore
* *PU* - Partner University is the university that NUS is working with for Student Exchange Programmes.
* *SEP* - Student Exchange Programme

## Instructions for manual testing

**Setting up SEP Helper**

Follow Quick Start Instructions in User Guide. 

1. Ensure that you have Java 11 or above installed in your computer, if not proceed to download from the link:
   https://www.oracle.com/sg/java/technologies/javase/jdk11-archive-downloads.html
2. Download the latest version of `SEPHelper.jar` from [here](https://github.com/AY2223S2-CS2113-T12-3/tp/releases/tag/v2.1).
3. Create a new empty folder, and place SEPHelper.jar file into that folder.
4. Using command prompt, change directory to that folder and type `"java -jar [FILE PATH OF JAR]"` to start app.
5. Enlarge to fullscreen mode.

**Overview of Manual Testing**

There are 3 main features to be tested in "SEP Helper" programme, which are
1. Module Mapping features
2. Budget features
3. Deadline features

Manual Testing instructions are split into these 3 different sections.


**1. Testing of Module Mapping Features**

Start off with the command `/list pu`. 
This function lists out the Partner Universities and their name abbreviations which will be utilized in a lot of 
other commands.

There are 3 essential commands to our SEP Helper Programme which are

Essential Commands: 
1. /LIST [PU ABBRV]
2. /LIST CURRENT [PU ABBRV]
3. /ADD [PU ABBRV]/[INDEX]  
4. /REMOVE [PU ABBRV]/[INDEX] 

Example of Test case:

`/list ku` -> `/add ku/1` -> `/add ku/2` -> `/list current ku` -> `/remove ku/1` -> `/list current ku`

Other features and aid in module mapping are

1. /LIST [PU ABBREVIATION/PU INDEX] /FILTER /MC [MC]
2. /LIST [PU ABBREVIATION/PU INDEX] /FILTER /NAME [NAME]
3. /SEARCH /MODS  
4. /SEARCH [NUS MOD CODE]

Example of Test cases:

1. `/list POSTECH` -> `/list POSTECH /filter /mc 0`
2. `/list SNU` -> `/list SNU /filter /name Intro`
3. `/search /mods`
4. `/search ME3122`


**2. Testing of Budget Features**

Essential Commands:

1. /budget /budget [AMOUNT]
2. /budget /accommodation [AMOUNT]
3. /budget /airplane [AMOUNT]
4. /budget /food [AMOUNT]
5. /budget /entertainment [AMOUNT]
6. /budget /view

Example of Test case:

`/budget /budget 3000` -> `/budget /accommodation 600` -> `/budget /food 400` -> `/budget /airplane 600`
-> `/budget /entertainment 300` -> `/budget /view`

**3. Testing of Deadline Features**

Essential Commands:

1. /deadline/list
2. /deadline/add [DEADLINE DESCRIPTION] /by [DD-MM-YYYY]
3. /deadline/remove [DEADLINE INDEX]

Example of Test case:

Step 1: `/deadline/list`

Step 2: `/deadline/add task1 /by 01-01-2023`

Step 3: `/deadline/add task2 /by 02-01-2023`

Step 4: `/deadline/list`

Step 5: `/deadline/remove 1`

Step 6: `/deadline/list`

Example : Todays date is 10-04-2023, you will receive a notification if the deadline is between
10-04-2023 to 17-04-2023

Step 7: `/deadline/add task3 /by 15-04-2023`

Step 8: reboot the application to see notification


