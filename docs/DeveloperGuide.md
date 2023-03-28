# MyLedger - Developer Guide

<p align="center">
    <img src="team/images/MyLedger.jpeg" width="30%">
</p>

- [1. Preface](#1-preface)
- [2. Acknowledgements](#2-acknowledgements)
- [3. Design & implementation](#3-design--implementation)
  * [3.1. Architecture](#31-architecture)
  * [3.2. Parser Component](#32-parser)
  * [3.3. Expenditures Component](#33-expenditure-categories)
  * [3.4 Command Component](#34-command-component)
  * [3.5. Storage Component](#35-storage)
  * [3.6. UI Component](#36-ui)
- [4. Command List](#4-command-list)
  * [4.1. Add a transaction](#adding-a-todo-todo)
  * [4.2. Edit a transaction](#adding-a-todo-todo)
  * [4.3. Delete a transaction](#adding-a-todo-todo)
  * [4.4. Find transactions](#adding-a-todo-todo)
  * [4.5. Duplicate a transaction](#adding-a-todo-todo)
  * [4.6. Sort transactions](#adding-a-todo-todo)
  * [4.7. View transactions](#adding-a-todo-todo)
  * [4.8. Set a budget](#adding-a-todo-todo)


## 1. Preface

MyLedger is a desktop app for managing finances, designed for university students studying locally or on exchange. It is optimized for use via a Command Line Interface (CLI). For students that can type fast, MyLedger can help them record and classify their transactions into categories. Students can expect to get an overview of their transactions at a glance,
which helps them to monitor their budget and expenses more effciently.

This developer guide provides a detailed view of the overall structure of MyLedger V2.0 and explains how its components and functions are implemented. Additionally, it outlines the specific parameters that were established before feature development began. The aim is to help developers gain a comprehensive understanding of the application's operation and how to maintain it without difficulty.

## 2. Acknowledgements

The format of this developer guide was adapted from SE-EDU AddressBook Level 3 Developer Guide. The class and sequence diagrams are styled using draw.io

## 3. Design & implementation

### 3.1. Architecture

<p align="center">
    <img src="team/images/Architecture.png">
    <i>Figure 1: Architecture Diagram for MyLedger</i>
</p>

The Architecture Diagram shown above is a high-level components within MyLedger. The ```MyLedger``` class contains the main method which
is responsible for:

1. When MyLedger is launched, it will initialize the ```Storage``` to load the saved expenditures from the textfile and  ```Ui``` to print
   the welcome message.
2. When MyLedger is executing, it receives input for the user and sends it to ```Storage``` then ```Command``` which carries out the various
   commands. 
3. After the command has been carried out ```Command``` sends the result back to ```MyLedger.main()``` which would print the message back to the user.

The other components of MyLedger include:

* ```Ui```: The user interface that prints the welcome and help message.
* ```Parser```: Parser which process the user's command and calls the specific command.
* ```Command```: Executes the command given.
* ```Expenditure```: Constructs an expenditure and is added to ```ExpenditureList```.
* ```ExpenditureList```: An ArrayList of the current expenditures.
* ```Storage```: Uses ```MyLedger_inputs.txt``` to initialize ```ExpenditureList```, updates ```MyLedger_inputs.txt``` whenever ```ExpenditureList```
  changed.

### Main Components of MyLedger
`Parser:` Processes the inputs made by the user and converts into a sensible form for further processing.

`Commands:` Matches the input command with the respective commands created and executes the command result.

`Expenditure Type:` Expenditure information that allows the users to access their input data from the respective 
command classes.

`Storage:` Stores, reads and updates the user input into their hard disk.

The following section describes the implementation of certain features.

### 3.2. Parser
#### Processing an input
The main parser component `MainInputParser` is called whenever the user inputs a command line that requires action from 
the application. The command word will be read and further processed into further components depending on the type of 
command. 

The following shows the UML diagram used for the parser component implemented in MyLedger.

<p align="center">
    <img src="team/images/parserOverview.png">
    <br/>
    <i>Figure 2: UML diagram for the parser component</i>
</p>

It must be noted that not all the existing parser commands are included in this sequence diagram for parsing, namely
the mark, unmark and edit commands. This is because they have a similar sequence diagram as the functions parseAdd and 
parseLendBorrow. The only difference is the condition, with the loop happening one, one and four time(s) respectively. 


### 3.3. Expenditure Categories
The **[API](https://github.com/AY2223S2-CS2113-T14-3/tp/blob/master/src/main/java/seedu/expenditure/Expenditure.java)** of this component is specified in the super abstract class `Expenditure.java` and its sub-classes. Its sub-classes represent the different expenditure categories. When users create a new expenditure record, one of these different expenditure categories are instantiated. After which, the expenditure is added to the expenditure list.

The **[API](https://github.com/AY2223S2-CS2113-T14-3/tp/blob/master/src/main/java/seedu/expenditure/ExpenditureList.java)** of the expenditure list is specified in the `ExpenditureList.java` class.

The `ExpenditureList` class description is as follows:
- A representation of a list of expenditures. It is an `ArrayList<Expenditure>` container
- The list is instantiated at the start of the program and stores expenditures of type `Expenditure`.

As for the expenditure types, their fields are as shown below.

`Expenditure`:
- Fields: `date`, `amount`, `description`

`AcademicExpenditure`**`: 
- Fields: `date`, `amount`, `description`

`AccommodationExpenditure`:
- Fields: `date`, `amount`, `description`, `isPaid`

`BorrowExpenditure`:
- Fields: `date`, `borrowerName`, `amount`, `deadline`, `description`

`EntertainmentExpenditure`:
- Fields: `date`, `amount`, `description`

`FoodExpenditure`: 
- Fields: `date`, `amount`, `description`

`LendExpenditure`:
- Fields: `date`, `borrowerName`, `amount`, `deadline`, `description`

`OtherExpenditure`:
- Fields: `date`, `amount`, `description`

`TransportExpenditure`:
- Fields: `date`, `amount`, `description`

`TuitionExpenditure`:
- Fields: `date`, `amount`, `description`, `isPaid`

The following shows the UML diagram used for the Expenditure Categories component implemented in MyLedger.

<p align="center">
    <img src="team/images/UMLClassDiagramExpenditure.png" width="80%">
    <br/>
    <i>Figure 3: UML diagram for the Expenditure Categories component</i>
</p>

In the diagram, the aforementioned expenditure categories inherit from the `Expenditure` class. The `ExpenditureList` class is a composition of expenditures of `Expenditure` type. 

`Expenditure` has a multiplicity of `*` to `ExpenditureList` as an empty expenditure list is instantiated at the beginning of the program, and any number of expenditures can be added to the expenditure list. Thus, it is also observed that the `ExpenditureList` class is an *composition* of `Expenditure`.

### 3.4. Command Component

The `Command` component is represented by the `command` package. The `command` package contains all the available user commands supported by the application. These commands are utilised by the user to interact with the expenditure types and the expenditure list. 

The `AcademicExpenditureCommand`, `AccommodationExpenditureCommand`, `BorrowExpenditureCommand`, `EntertainmentExpenditureCommand`, `FoodExpenditureCommand`, `LendExpenditureCommand`, `OtherExpenditureCommand`, `TransportExpenditureCommand`, `TuitionExpenditureCommand` commands contain the operations pertaining to adding a new expenditure into the list of expenditures.

The `CheckBudgetCommand` class class contains the operations pertaining to comparing the total expenditure amount with a budget set by the user. The budget is set with the `SetBudgetCommand`.

The `DeleteCommand` class contains the operations pertaining to deleting a specific expenditure from the list of expenditures.

The `DuplicateCommand` class contains the operations pertaining to duplicating a specific expenditure from the list of expenditures.

The `EditCommand` class contains the operations pertaining to editing a expenditure from the list of expenditures.

The `ExitCommand` class contains the operation that safely closes the application.

The `FindCommand`class contains the operations pertaining to searching the list of expenditures for expenditures that match the keyword entered by the user.

The `HelpCommand` class contains the operation pertaining to providing the user a user interface to the instructions on the use of the application.

The `InvalidCommand` class is instantiated when an unrecognised command is entered by the user.

The `SetBudgetCommand` class contains the operations in setting an amount of money users would like to budget.

The `SortCommand` class contains the operations pertaining to sorting the list of expenditures by amount or by date.

The `ViewDateExpenditureCommand` and `ViewTypeExpenditureCommand` classes contain the operations pertaining to displaying a filtered expenditure list by the expenditure date and type respectively.

Below shows the UML diagram representing the `command` package.
<p align="center">
    <img src="team/images/umlCommandClassDiagram.png" width="100%">
    <br/>
    <i>Figure 4: UML diagram for the command package</i>
</p>

A more detailed coverage is explored in [Command List](#4-command-list).

### 3.5. Storage
The class `TxtFileStatus` and `ExpenditureList` are involved in storing the expenditure list.
After every user input is completed, saveExpenditureList is called, and the text file will be
updated with all the current expenditures in the expenditure array list.

<p align="center">
    <img src="team/images/saveList.png">
    <br/>
    <i>Figure 5: Sequence diagram for TxtFileStatus</i>
</p>

The following sequence diagram shows the details of the process for saveExpenditureList.

Likewise when MyLedger first runs, it instantiates ExpenditureList and stores a reference to it.
MyLedger then checks if the text file exists, else it gets created. Recorded expenditure stored 
as a string in the text file is then added to the array list in ExpenditureList by iterating through
the strings in the text file, instantiating an expenditure using the string received, and adding
the expenditure into the array list.

<p align="center">
    <img src="team/images/initializeList.png">
    <br/>
    <i>Figure 6: Sequence diagram for the process of saveExpenditureList</i>
</p>

### 3.6. UI 

## 4. Command List

### 4.1. Add Expenditure Command 

The `AcademicExpenditureCommand`, `AccommodationExpenditureCommand`, `EntertainmentExpenditureCommand`, `FoodExpenditureCommand`, `OtherExpenditureCommand`, `TransportExpenditureCommand`, `TuitionExpenditureCommand` commands contain the operations to add an expenditure of a fixed category into the list of expenditures. As these expenditure types take in the same fields, the `BorrowExpenditureCommand` and `LendExpenditureCommand` have been isolated from these commands. 

This is due to the fact that the 7 formerly stated commands all take in the same fields, and hence can be parsed in a similar fashion to instantiate the **Expenditure Command**, and later the **Expenditure** itself. In other words, the 7 stated commands are instantiated in the same way and will be explained altogether in this section.

To instantiate the commands, the full commands are the following: 
`AcademicExpenditureCommand`: `academic d/<date> a/<amount> s/<description>`
- To create an academic expenditure.

`AccommodationExpenditureCommand`: `accommodation d/<date> a/<amount> s/<description>`
- To create an accommodation expenditure.

`EntertainmentExpenditureCommand`: `entertainment d/<date> a/<amount> s/<description>`
- To create an entertainment expenditure.

`FoodExpenditureCommand`: `food d/<date> a/<amount> s/<description>`
- To create a food expenditure.

`OtherExpenditureCommand`: `other d/<date> a/<amount> s/<description>`
- To create an expenditure with a category of "other".

`TransportExpenditureCommand`: `transport d/<date> a/<amount> s/<description>`
- To create a transport expenditure.

`TuitionExpenditureCommand`: `tuition d/<date> a/<amount> s/<description>`
- To create a tuition expenditure.

When the user inputs one of the 7 expenditure commands into the application, the `MainInputParser.java` takes in the input and determines the command's operations via switch statements. Next, the `ParseIndividualValue.java` class contains the operation to split the valid input given by the user. This splits the inputs into fields to instantiate the **Expenditure Commands**. In this instance, the 7 stated commands will be referred to `ExpenditureCommand`. After splitting, `MainInputParser.java` calls operations from `ParseAdd.java`. `ParseAdd.java` prepares the split inputs for the `ExpenditureCommand` as fields, and instantiates one of its seven commands based on the user's specified expenditure category. 

Below shows the sequence diagram for the aforementioned logic:

<p align="center">
    <img src="team/images/parseAddSequenceDiagram.png">
    <br />
    <i>Figure 7: Sequence Diagram for edit Command</i>
</p>


### 4.2. Edit Command

The ```EditCommand``` edits an existing expenditure in the record.

It cannot change the expenditure type of a record, only its fields

For editing an expenditure, the full command is  ```edit INDEX d/DATE a/AMOUNT s/DESCRIPTION```

For editing a borrow/lend record, the full command is  ```edit INDEX d/DATE n/(LEND/BORROW)_NAME a/AMOUNT b/DEADLINE s/DESCRIPTION```
       
The sequence diagram below shows the interactions of a successful execution of the EditCommand

<p align="center">
    <img src="team/images/parserEdit.png">
    <br />
    <i>Figure 7: Sequence Diagram for edit Command</i>
</p>

### 4.3. Delete Command

### 4.4. Find Command

### 4.5. Duplicate Command

### 4.6. Sort Command

### 4.7. View Command

The view command filters and lists the expenditures of a specified date or type.
At the end of the list, the total amount of the filtered expenditures are tabulated.
For viewing expenditures of specific date, the command is ```viewdate DATE```.
For viewing expenditures of specific type, the command is ```viewtype EXPENDITURE_TYPE```.

The sequence diagram below shows the details of the process for viewdate.

<p align="center">
    <img src="team/images/viewDate.png">
    <br />
    <i>Figure 8: Sequence Diagram of the process for viewdate</i>
</p>

The process for viewtype is similar as viewdate with an additional step within ViewTypeExpenditureCommand
that converts the input string into a string recognisable for comparison in the opt block. 

### 4.8. Set Budget Command

## Product scope
### Target user profile

- University students studying locally or on exchange 
- has a need to monitor their budget and expenses
- prefer desktop CLI over other available types of expense tracking applications 
- prefers typing to mouse interactions

### Value proposition

Manage finances more efficiently than a typical mouse/GUI driven app

## User Stories

| Version | As a ...        | I want to ...                        | So that I can ...                                    |
|---------|-----------------|--------------------------------------|------------------------------------------------------|
| v1.0    | first time user | have access to a help page           | be familarized with the features available           |
| v1.0    | user            | add a expenditure recorded in a day  |                                                      |
| v1.0    | user            | delete an expenditure record         | get rid of expenditure that I no longer plan to use  |
| v1.0    | user            | edit an expenditure record           | correct previous expenditure records                 |
| v1.0    | user            | view all current expenses            | have a good overview of my spending to date          |
| v1.0    | user            | add a record for borrowing money     | keep track of how much money I borrowed to someone   |
| v1.0    | user            | add a record for lending money       | keep track of how much money I lent to someone       |
| v2.0    | user            | sort expenditures based on date      | better manage my expenditures                        |
| v2.0    | user            | sort expenditures based on amount    | better manage my expenditures                        |
| v2.0    | user            | add income earned                    | keep track of my current budget                      |
| v2.0    | user            | find expenditures using description  | better manage my spending                            |
| v2.0    | user            | duplicate a current expenditure      | update repeated purchases easily                     |
| v2.0    | user            | indicate a specific budget to follow | track my spending and make sure I stay within budget |
| v2.0    | user            | be able to view expenses by day      | see which day and why I am overspending              |
| v2.0    | user            | view my total expenses by categories | see which categories I am overspending on            |

## Non-Functional Requirements

{Give non-functional requirements}

## Glossary

* *glossary item* - Definition

## Instructions for manual testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}
