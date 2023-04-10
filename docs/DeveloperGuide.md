# Developer Guide

## Introduction

Welcome to rainyDay's developer guide! rainyDay is a desktop application for managing your finances that runs on a
Command Line Interface (CLI). rainyDay provides a simple solution to track your finances and achieve your financial
goals.

<!-- TOC -->

* [Developer Guide](#developer-guide)
    * [Introduction](#introduction)
        * [Acknowledgements](#acknowledgements)
        * [Purpose and target reader](#purpose-and-target-reader)
        * [How to use this guide](#how-to-use-this-guide)
        * [Main functions](#main-functions)
    * [Design](#design)
        * [Architecture](#architecture)
        * [Modules package](#modules-package)
        * [Parser package](#parser-package)
        * [Data package](#data-package)
        * [Command package](#command-package)
        * [Exceptions package](#exceptions-package)
    * [Implementation](#implementation)
        * [Maintaining of Financial Report](#maintaining-of-financial-report)
        * [Adding an entry `add`](#adding-an-entry-add)
        * [Deleting an entry `delete`](#deleting-an-entry-delete)
        * [Implementation of regex and parser](#implementation-of-regex-and-parser)
        * [Viewing your data `view`](#viewing-your-data-view)
        * [Editing an entry `edit`](#editing-an-entry-edit)
        * [Filtering your data `filter`](#filtering-your-data-filter)
        * [Setting your monthly Budget Goal `setbudget`](#setting-your-monthly-budget-goal-setbudget)
        * [Adding a shortcut `shortcut`](#adding-a-shortcut-shortcut)
        * [Using a shortcut](#using-a-shortcut)
        * [Viewing shortcuts `shortcut_view`](#viewing-shortcuts-shortcut_view)
        * [Deleting a shortcut `shortcut_delete`](#deleting-a-shortcut-shortcut_delete)
        * [Saving Data](#saving-data)
        * [Loading Data](#loading-data)
        * [Exporting to .csv](#exporting-to-csv)
    * [Product scope](#product-scope)
        * [Target user profile](#target-user-profile)
        * [Value proposition](#value-proposition)
    * [User Stories](#user-stories)
    * [Non-Functional Requirements](#non-functional-requirements)
    * [Glossary](#glossary)
    * [Instructions for manual testing](#instructions-for-manual-testing)
<!-- TOC -->

### Acknowledgements

- [gson](https://github.com/google/gson) for saving and loading of data from a .json file
- [Junit](https://junit.org/junit5/) for unit testing
- [OpenCSV](https://opencsv.sourceforge.net/) for exporting of data into a .csv file
- rainyDay's Developer Guide and User Guide references [AB3](https://github.com/se-edu/addressbook-level3)'s Developer
  Guide and User Guide

### Purpose and target reader

This guide is designed for potential engineers and professors who may want to understand how rainyDay is implemented.
We assume that you already have a certain level of technical knowledge as we will be going into detail of
the technical aspects of rainyDay. <br> If you are a user looking for instructions on how to use rainyDay, please refer
to our [UserGuide](https://ay2223s2-cs2113t-t09-1.github.io/tp/UserGuide.html).

### How to use this guide

* `inline code` format are related to commands, classes and methods in rainyDay
* "double quotes" format are related to names
* Items surrounded by [square brackets] are mandatory fields, while the items in {curly brackets} are optional e.g.
  [DESCRIPTION] {TIMESPAN}
* 💡 indicates helpful tips
* ℹ️ indicates information to take note of

### Main functions

* Adding, deleting and editing transaction entries
* Viewing your transactions and filtering by certain fields
* Saving your data into a .csv file
* Setting budgeting goals

## Design

> 💡 The .puml files used to create diagrams in this document can be
> found [here](https://github.com/AY2223S2-CS2113T-T09-1/tp/tree/master/docs/diagrams).

> ℹ️ The lifeline for all sequence diagrams should end at the destroy marker (X) but due to a limitation of PlantUML,
> the lifeline reaches the end of the diagram.

### Architecture

#### Components of the architecture

The following are the components of the architecture that are required for rainyDay to function.

- `RainyDay`: initializes the components in the correct sequence, and connects them up with each other
- `Parser`: breaks down inputs passed from user
- `Command`: executes the appropriate commands
- `UserData`: the data of the user
- `Ui`: reads user input and output texts to user
- `Storage`: reads data from and writes data to the hard disk file

The diagram given below explains the high-level design of rainyDay, and how the components are related to one another.

![architecture.png](images\DeveloperGuide\architecture.png)

The sequence diagram below shows the timeline of how the different components will interact with one another, as
illustrated with a 'delete' command, e.g. `delete 1`. Note that the sequence diagram is only meant to show the main idea
of interaction between the components, hence some details are omitted.

![ArchitectureSequence.png](images\DeveloperGuide\ArchitectureSequence.png)

### Modules package

The modules package consists of classes `Storage`, and `Ui`.

#### Storage

`Storage` reads data from and writes data to the hard disk file.

![StorageClassDiagram.png](images%2FDeveloperGuide%2FStorageClassDiagram.png)

The methods in `Storage` perform the [save](#saving-data), [load](#loading-data),
and [export to .csv](#exporting-to-csv) operations.

- The data of the user is saved in a .json file
- Loading is done from a .json file. `Storage` will also perform the necessary checks to ensure that the .json file
  is not corrupted and contains valid data

#### UI

The UI class is used mainly for user-related functions, including the reading of user input.

It is used to print to the user welcome and goodbye messages. On first startup, it is also used to get the name of
the user.

The UI class is also used when printing various outputs to the user after a command is executed

### Parser package

The parser package consists of different classes to parse the different inputs from users. The following shows a partial
class diagram for the Parser package, irrelevant details are omitted.

![ParserClassDiagram.png](images%2FDeveloperGuide%2FParserClassDiagram.png)

There will be a main parser class that will identify the appropriate parser class to call based on the first word of the
user's input. All the parser classes will inherit from the main parser class. The corresponding parser class will then
execute the appropriate commands.

### Data package

The data package consists of
classes `FinancialReport`, `FinancialStatement`, `FlowDirection`, `MonthlyExpenditures`, `SavedData` and `UserData`.

The following shows a partial class diagram for the Data package with irrelevant details omitted.

![DataClassDiagram.png](images\DeveloperGuide\DataClassDiagram.png)

#### FinancialReport

Consists of attributes:

- "reportOwner" of object String
- "financialReport" of ArrayList<FinancialStatement>

Represents a list of `FinancialStatement`

#### FinancialStatement

Consists of attributes:

- "description" of object String
- "value" of type double
- "category" of object String
- "date" of object LocalDate
- "isIgnored" of type boolean

Represents the details of a real world transaction

#### FlowDirection

An enum class consisting of:

- "INFLOW", representing a transaction direction towards the user, such as a deposit
- "OUTFLOW", representing a transaction direction away from the user, such as a purchase

Represents the flow direction of a transaction

#### MonthlyExpenditures

Consists of the attribute:

- "monthlyExpenditures" of object HashMap<integer, double>

Maps the year and month to the expenditures

#### SavedData

Consists of the attributes:

- "shorcutCommands" of object HashMap<String, String>
- "budgetGoals" of type double
- "financialReport" of object FinancialReport

Represents the data saved to the computer by rainyDay

#### UserData

Consists of the attributes:

- "savedData" of object SavedData
- "monthlyExpenditures" of object HashMap<integer, double>

Represents the data required during runtime of rainyDay

#### Design considerations

`SavedData` is a subset of `UserData` and not all user data will be saved.

- The hash map containing the monthly expenditure of the user will not be saved.
    - Advanced users may choose to edit the .json file to change the value of each transaction. Hence, to ensure that
      the monthly expenditure remains accurate, we must recompute the monthly expenditure upon startup.
    - Therefore, saving the monthly expenditure hashmap will be redundant as it has to be recomputed on startup to
      ensure that it matches the user's actual expenditure.

To prevent slow processing of the program when there are many entries, a hash map is used to keep track of the
expenditures for the month.

- We believe that the "Set Budget" feature will be commonly used as it is one of the key aspects of a Financial Tracker.
  Hence, it would be feasible to implement an additional storage such that common addition operations and
  retrieval of information can be done in Amortised O(1)
- The Key used is the number of months from the Year 0000, or more precisely calculated by (Year * 12 + Month).
  This is to allow for fast processing and collision-free information collection

### Command package

The following shows a partial class diagram for the Command package, with irrelevant details omitted.

![CommandClassDiagram.png](images\DeveloperGuide\CommandClassDiagram.png)

1. When a command is parsed, a command object specific to the command given will be created, with the necessary
   parameters stored as attributes in the command specific class
2. rainyDay will then call the `Command` specific `execute` method to return a `CommandResult` object, which contains
   the output to be shown to user

- The various command classes inherits from an abstract `Command` class which contains the abstract `execute` method
  that each specific command class will implement
- The shortcut commands further inherit from the abstract `ShortcutCommand` class which contains the hashmap that maps
  all configured shortcuts to its actual command.

### Exceptions package

The exceptions component consists of classes `RainyDayException` and `ErrorMessage`.

#### RainyDayException

- Extends the built-in Exception class and takes a string parameter "errorMessage".

#### ErrorMessages

- An enum class consisting of all the error messages thrown by `RainyDayException`

## Implementation

### Maintaining of Financial Report

`financialReport` is achieved with the use of an ArrayList collection of the object `FinancialStatement` to model a
financial report containing a list of financial statements.

- A `FinancialStatement` object contains the following attributes:
    - "description": represents the description of a transaction, stored as a string
    - "flowDirection": represents the direction of a transaction, stored as an enum of `FlowDirection`: "INFLOW" or
      "OUTFLOW"
    - "value": represents the amount tagged with the transaction, stored as a double
    - "category": represents the type of transaction, stored as a string
    - "date": represents the date to be tagged of transaction, stored as `LocalDate` object

### Adding an entry `add`

- When a command is given to add a statement, the command is first parsed to check whether it follows the format of an
  add command: `add [-DIRECTION] [DESCRIPTION] [$AMOUNT] {-c CATEGORY} {-date DAY/MONTH/YEAR}` with the use of regex
  pattern
    - Details on implementation for parsing and command fields are documented below
- Commands in the correct format will then be parsed to extract the relevant information, and an `AddCommand` object
  will be created with the relevant attributes
- `RainyDay` will then call the `Command` specific `execute` method, where the transaction will be added into the
  financial report

The sequence diagram for the implementation of add is as shown below.

![AddCommandSequenceDiagram.png](images\DeveloperGuide\AddCommandSequenceDiagram.png)

#### Design considerations `add`

**Format of add command**

- Alternative 1 (current choice): usage of flags
    - Pros: able to identify the arguments easily, as the combination of the characters are highly unlikely to be used
      as a description or category name
    - Cons: not that intuitive from the user's point of view
- Alternative 2: usage of keywords
    - Pros: easier to use from the user's point of view
    - Cons: unable to correctly identify the keyword as the sequence of characters may be a description or category that
      the user wants to use

### Deleting an entry `delete`

- When a command is given to delete a statement, the command is first parsed to check whether it follows the format of a
  delete command: `delete [INDEX]` with the use of regex pattern
    - `INDEX`: the index number of the statement in the financial report, stored as an int
- Commands in the correct format will then be parsed to extract index, and a `DeleteCommand` object will
  be created with the relevant attribute information
- `RainyDay` will then call the `Command` specific `execute` method, where the indicated transaction will be deleted
  from the financial report

The sequence diagram for the implementation of delete is as shown below.

![DeleteCommandSequenceDiagram.png](images\DeveloperGuide\DeleteCommandSequenceDiagram.png)

#### Design considerations `delete`

**Format of delete command**

- Alternative 1 (current choice): usage of index to identify the statement to be deleted
    - Pros: able to identify the correct statement to be deleted following the index assigned to the statement
    - Cons: may not be intuitive for the user, as they have to look up the index of the statement before deletion
- Alternative 2: usage of full statement information to identify the statement to be deleted
    - Pros: more intuitive for the user
    - Cons: user needs to know the full details of the statement and needs to type the full statement information for
      identifying the statement to be deleted

### Implementation of regex and parser

The parser class is used to extract the necessary information from the user's input. Our aim is to make the parser as
user-friendly and flexible as possible, and at the same time also reducing the margin for error and bugs. Thus, we
decided to implement using regex and use the features provided by the libraries `java.util.regex.Matcher` and
`java.util.regex.Pattern`.

The first word of the user's input is checked and will be matched to one of the functions in the following list:

- `addStatement(String userInput)`: Parses an 'add' instruction from a user
- `parseDeleteStatement(String userInput)`: Parses a 'delete' instruction from a user

#### addStatement(String userInput)

This makes up the bulk of the class, as there are many fields for the add instruction, thus resulting in many
variations of inputs. The following shows the format of input we expect from a user:

Adds a new transaction to the financial report.

Format: `add [-DIRECTION] [DESCRIPTION] [$AMOUNT] {-c CATEGORY} {-date DAY/MONTH/YEAR}`

* The `DIRECTION` to be `in` signifying an inflow type of transaction, or `out` signifying an outflow type of
  transaction. This is a required field
* `DESCRIPTION` is a required field
* The `$AMOUNT` takes in a number, is also a required field.
* `-c CATEGORY` is an optional field that takes in a user-defined category of the product
* `-date DAY/MONTH/YEAR` is an optional field that takes in the date of a transaction

As this is quite a long command to parse, we will use regular expressions in the following steps to match and break
down the instructions.

1. Function `addStatement` will call function `returnRemainingInformation` which will check whether the input contains the
   mandatory fields are present. This is done through the following:

    - `-(in|out)\\s+(.+)\\s+\$([\d.]+)` checks for the corresponding structure: `(-IN/OUT) <whitespace>
      (DESCRIPTION) <whitespace> ($AMOUNT) `. This will match when the optional flags are not included. An empty string
      will then be returned
    - `-(in|out)\\s+(.+)\\s+\$([\d.]+)\\s+(.*)` checks for the corresponding structure `(-IN/OUT) <whitespace>
      (DESCRIPTION) <whitespace> ($AMOUNT) <whitespace> (remaining input)`. This will match when at least one of the
      optional flags are included, and a string corresponding to `(remaining input)` will be returned<br><br>

2. If an empty string is returned, an `addCommand` object will be returned from the method `addStatement`. Otherwise,
   a variable `String remainingInformation` will correspond to `(remaining input)` above. `remainingInformation` will
   then be checked for if it contains `-c` or `-date` flags<br><br>

3. If `remainingInformation` contains a `-c` flag, it means that the user has provided a category for the item.
   Thus, it will be passed into the method `setCategory`. `setCategory` will then use the following regex to match
   `remainingInformation`:
    - `-c\\s+(.+)` checks for the corresponding structure `-c <whitespace> (CATEGORY)`
    - `-c\\s+(.+)\\s+-date\\s+(.*)` checks for the same thing except whether it contains
      the `-date <whitespace> (any input)`<br><br>

4. The last field to check is the `-date` field. If present, the `setDate` method will be called
   on `remainingInformation`. The `setDate` method will then use the following regex to match
    - `-date\\s+(.*)` matches the corresponding structure`-date <whitespace> (any input)`
    - If the string matches the regex, the `(any input)` will be passed into the method `checkValidDateAndSet` to check
      whether the date provided is in the correct format. If the date is valid, the `date` attribute in `parserAdd`
      will be updated

These steps will ultimately parse the user's input and extract the necessary information. If any pattern does not match,
our parser will throw a `RainyDayException` indicating the wrong input format.

#### Design considerations

- We have tried using the `.split()` method of strings to extract information. However, as we introduced more features
  and
  the commands grew more complex, the `.split()` method became very messy and inconvenient. Using this will also result
  in
  us needing to do more exceptions and error handling, which just made the entire process very complicated. Thus, we
  opted
  to use regular expressions, which is a more tidy and logical way to parse the inputs.

### Viewing your data `view`

- When a command is given to view all statements, the command is first parsed to check if it follows the format as shown
  below. A `ViewCommand` object will be created at the end.
    - The format for the command is `view {TIMESPAN} {-sort}`
- The relevant details will then be extracted out and passed as a `ViewCommand` object. Details include the start date,
  the end date, if sorting is required, and if `-all` is passed in the `TIMESPAN` field
    - If `-all` is passed, a boolean value will be set to true to indicate this. The start date and end date will be
      set to the earliest and latest possible date of Java's LocalDate
    - The boolean value is only used during formatting of the summary table

![ViewCommandSequenceDiagram.png](images\DeveloperGuide\ViewCommand.png)

#### Design considerations

- Information is presented in a table format to help improve clarity for users
    - The table includes information in the summary such as whether it is sorted and the timespan of transactions shown
    - Sorting features also show the latest information at the bottom, as compared to a normal GUI-based application.
      This is because in a CLI, users will always be redirected to the bottom after the output. Hence, by placing
      critical information at the bottom, it will make it easier for users to spot in case of a large table.
- The limit for the timespans are deliberately set to cover commonly used timespans
    - One can view up to 31 days / 4 weeks, as they each make up a month
    - Similarly, one can view up to 12 months, as they make up a year
    - The limit for 10 years was set as a soft limit as the target user is not expected to need to view more than
      10 years of history at any time.
- The setting of hiding the value of transactions that are ignored is deliberate, as it is the most prominent and
  direct way for users to see this.
    - Users can view the value via the export command, as elaborated below

### Editing an entry `edit`

- When a command is given to edit a statement, the command is first parsed by the parser
- The parser checks whether the command follows the format of an edit command: `edit [INDEX] [FLAG] {NEWFIELD}`
  with the use of regex. The regex pattern also checks whether the flags are in the correct order
- Commands in the correct format will then be used to create an `EditCommand` object
- `RainyDay` will then call the `Command` specific `execute` method, where the fields in the indicated transaction will
  be edited

The sequence diagram for the implementation of edit is as shown below with the details of looping through
`editFlagAndField` have been omitted from the diagram:

![EditCommand.png](images\DeveloperGuide\EditCommand.png)

Those details are shown in a separate sequence diagram given below

![EditCommandRefFrame.png](images\DeveloperGuide\EditCommandRefFrame.png)

#### Design considerations

- The flags accepted are currently fixed by this order `-in/-out` -> `-d DESCRIPTION` -> `-v $VALUE` -> `-c CATEGORY` ->
  `-date DATE`. While accepting any order of flags would increase convenience for the user, since add command currently
  also fixes the flag orders, it would be easier and more intuitive for users to remember and use the same flag order
  for edit

### Filtering your data `filter`

- When a command is given to filter the financial report by certain conditions, the command is first parsed to check
  whether it follows the format of a filter command with the use of regex. The regex pattern also checks whether the
  flags are in the correct order
- Commands in the correct format will then be parsed to create a `FilterCommand` object
- `RainyDay` will then call the `Command` specific `execute` method, where every transaction in the financial report
  with the matching conditions will be printed
- Information will be presented in a table format to help improve clarity for users

The sequence diagram for the implementation of filter is as shown below:

![FilterCommand.png](images\DeveloperGuide\FilterCommand.png)

The diagram above shows the sequence diagram for a `-c` flag, the details for the other flags are omitted to simplify
the diagram.

#### Design considerations

[Same design considerations as edit.](#editing-an-entry-edit)

### Setting your monthly Budget Goal `setbudget`

- The command `setbudget` is used to set the user's monthly budget goal
    - Format: `setbudget [VALUE]`
    - Users can also turn off the feature at any time by setting `VALUE` to 0
- Once a goal is present, user's will be reminded of how close they are to sticking to their budget, or how
  much they have exceeded it by
- This can be seen at start-up and when the user makes any changes to their expenses for the month

![SetBudgetCommandSequenceDiagram.png](images\DeveloperGuide\SetBudgetCommand.png)

### Adding a shortcut `shortcut`

- When a command is given to add a shortcut, the command is first parsed to check if it follows the format of an add
  shortcut command: `shortcut [SHORTCUTNAME] -maps [ACTUALCOMMAND]`
    - Furthermore, the `[SHORTCUTNAME]` should be given without spaces
- Commands in the correct format will then be parsed to create a `ShortcutAddCommand` object with a constructor
    - The given `[SHORTCUTNAME]` and `[ACTUALCOMMAND]` will be the key value pair of the hashmap.
- `RainyDay` will then call the `Command` specific `execute` method
    - A call will be made to the `savedData` object which returns a reference to the "shortcutCommands" hashmap
    - A self-call will be made to the `checkShortcutValidity` method to ensure that the shortcut to be added is valid
    - If the shortcut is valid, the new shortcut mapping will be added into the hashmap

The sequence diagram for the implementation of adding a shortcut is as shown below.

![ShortcutAddCommand.png](images/DeveloperGuide/ShortcutAddCommand.png)

### Using a shortcut

- When an input is given by the user, it will first be processed by `Parser` to check if it is a valid actual command.
- If the input is not a valid command, `Parser` will check if the input is a shortcut present in the "shortcutCommands"
  hashmap.
- If the shortcut is present, the input will be replaced with the command the shortcut maps to. The same `Parser`
  check will then be repeated with the replacement command.

### Viewing shortcuts `shortcut_view`

- The command `shortcut_view` is used to view all currently configured shortcuts
- The command will create a `ShortcutViewCommand` object with a constructor
- `RainyDay` will then call the `Command` specific `execute` method
    - A call will be made to the "savedData" object which returns a reference to the "shortcutCommands" hashmap
    - For each shortcut in the hashmap, the corresponding actual command will be obtained from the
      "shortcutCommands" hashmap with the `get` method
    - The mapping between the shortcut and actual command will then be printed to the user by calling
      the `printShortcutMapping` method
- Information will be presented in a table format to help improve clarity for users

The sequence diagram for the implementation of viewing a shortcut is as shown below:

![ShortcutViewCommand.png](images%2FDeveloperGuide%2FShortcutViewCommand.png)

### Deleting a shortcut `shortcut_delete`

- When a command is given to delete a shortcut, the command is first parsed to check if it follows the format of a
  delete shortcut command: `shortcut_delete [SHORTCUTNAME]`
- Commands in the correct format will then be parsed to create a `ShortcutDeleteCommand` object with a constructor
    - The given `[SHORTCUTNAME]` will be the key of the hashmap
- `RainyDay` will then call the `Command` specific `execute` method
    - A call will be made to the "savedData" object which returns a reference to the "shortcutCommands" hashmap
    - If the shortcut key exists in the "shortcutCommands" hashmap, it will be deleted from the hashmap

The sequence diagram for the implementation of deleting a shortcut is as shown below:

![ShortcutDeleteCommand.png](images%2FDeveloperGuide%2FShortcutDeleteCommand.png)

#### Design considerations

- A hashmap is used to store the mapping between the shortcuts and the command that the shortcut maps to
- A hashmap is an appropriate data structure as it provides a one to one mapping and allows shortcut access in O(1)

### Saving Data

- The "savedData" object will contain all the data of the user, such as the `FinancialReport`, the
  configured "shortcutCommands" and "budgetGoal"
- Whenever a change is made in the "savedData" the updated "savedData" will automatically be saved to
  reflect the changes
- Saving is done by serializing the "savedData" into json format and writing it into a file
- The saved file will be named "rainyDay.json" and will be located in the "data" folder

#### Design considerations

##### Implementation of saving

- Alternative 1 (current choice): Save the "savedData" automatically whenever there is a change to its
  data
    - Pros:
        - User will never forget to save data
        - Process is done automatically and invisible to the user
        - Save should already be performed even when application crashes or exits abnormally. At worst, only the latest
          entry will be lost
    - Cons:
        - May have performance issue in terms of speed. Since a save is done with every change rather than after all
          changes are already done
        - Less flexibility if a user wants to perform changes without saving
- Alternative 2: Perform save automatically on normal exit, such as after `bye` command
    - Pros:
        - Save will only be performed once user is done with all changes and ready to exit. Better performance than
          alternative 1
        - Process is done automatically and invisible to the user
    - Cons:
        - "savedData" may not be saved if application crashes or abnormal exit is performed
- Alternative 3: Perform save only when user states explicitly, such as with a command to save
    - Pros:
        - More flexibility to the user to decide when to save or to discard changes
        - Save only performed when explicitly needed. Performance benefit
    - Cons:
        - User may forget to save
        - "savedData" may not be saved if application crashes or abnormal exit is performed

##### Type of file to save data into

- Alternative 1 (current choice): Make use of serialization in the gson library to serialize "savedData" object before
  writing to file
    - Pros:
        - Easier to implement. Minimal changes to the code required as new attributes are added to "savedData" as
          we develop the app incrementally
        - Less prone to bugs as data does not need to be manually parsed to save/load
        - More flexibility for advanced users who may choose to directly edit the json file
    - Cons:
        - Difficult to prevent users from incorrectly manipulating the json file. May cause errors if corrupted.
            - Must implement proper error handling such that any improper format or invalid data in the data file can be
              detected
- Alternative 2: Make use of plaintext to save the relevant data in the "savedData" object.
    - Pros:
        - Data will be more readable and user can get clearer information about the "savedData" by viewing the text file
    - Cons:
        - Difficult to implement and parse, changes in implementation will be necessary when new attributes are added
          to "savedData" as we develop the app incrementally
        - Prone to bugs as data must be parsed manually to save/load

### Loading Data

- Json serialized data will be read from file automatically upon startup of the application
- Data from file will be deserialized and a "savedData" object will be created based on the save file
    - If the Json file is corrupted or contain invalid values such as a negative budget, the application will be started
      without loading the previous save. The invalid save file will also be overwritten

#### Design Considerations

**Type of file to load data into**

[Same design considerations as saving.](#type-of-file-to-save-data-into)

### Exporting to .csv

- When the `export` command is given, the `FinancialStatement` data will be written to a .csv file
- Relevant fields in the `FinancialStatement` will be saved, including the "ID", "description", "amount", "category"
  and "date" of each transaction

#### Design considerations

**Implementation of export to .csv**

- Alternative 1 (current choice): Perform export only when user states explicitly, with `export` command.
    - Pros:
        - More flexibility to the user to decide when to export to .csv
        - Avoids unnecessarily creating .csv file. Performance benefit
    - Cons:
        - User might find it inconvenient to export .csv file manually if it's a commonly used feature
- Alternative 2: Perform export to .csv file upon exit
    - Pros:
        - Automatically generates .csv file for users without requiring an explicit command
    - Cons:
        - Exporting to .csv might be costly operation when dealing with a large financial statement. An unnecessary
          operation may be performed if user is uninterested in the export to .csv feature

## Product scope

### Target user profile

- Tech-savvy
- Working adults who are inexperienced in managing their finances
- Prefers typing
- Can type fast
- Prefers typing to mouse interactions
- Is reasonably comfortable using command line interface applications
- Prefers desktop applications over other types

### Value proposition

Help people who are just starting out working and troubled by financial issues such as managing of budget

## User Stories

| Version | As a ...                                    | I want to ...                                           | So that I can ...                                      |
|---------|---------------------------------------------|---------------------------------------------------------|--------------------------------------------------------|
| v1.0    | working adult                               | track my outflows by adding new entries                 | where my money is going                                |
| v1.0    | working adult                               | track my inflows by adding new entries                  | know how much I am earning                             |
| v1.0    | diligent user                               | view my inflows and outflows                            | know where my money is going                           |
| v1.0    | careless user                               | delete my entries                                       | recover after making wrong entries                     |
| v1.0    | returning user                              | continue from my previous data                          | avoid re-entering all the entries                      |
| v2.0    | new user                                    | see usage instructions                                  | refer to them when I forget how to use the application |
| v2.0    | user who uses multiple devices              | access the data I saved in RainyDay in my other devices | avoid having to re-enter the same data                 |
| v2.0    | busy user                                   | create and use shortcuts                                | avoid having to repeatedly type in lengthy commands    |
| v2.0    | diligent user                               | filter my data                                          | view my past expenses with ease                        |
| v2.0    | careless user                               | edit my entries                                         | amend my wrong entries                                 |
| v2.0    | careful user                                | ignore certain entries                                  | I can have a more accurate track of my finances        |
| v2.0    | user who is serious in managing my finances | set up a budget                                         | know whether my outflows have exceeded my budget goal  |
| v2.0    | advanced user                               | export my data                                          | view my expenses in charts and diagrams in excel       |

## Non-Functional Requirements

1. rainyDay works on common operating systems (Windows, Mac OS, Linux, etc.), with Java 11 installed.
2. The target users should be able to execute all commands with reasonable descriptions in under 10 seconds.
3. Data of rainyDay can be moved and read in any other computers with rainyDay with no implications
4. rainyDay should be responsive, with no noticeable delay for report sizes under 10,000.
5. Commands should be straightforward, such that the name makes its function obvious to the user.

## Glossary

| Term                   | Explanation                                                                                                                                                      |
|------------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Cmd                    | Acronym for Command, which is the command-line interpreter of Windows Operating Systems                                                                          |
| Command Line Interface | An interface that uses text as the mode of interaction between the user and the program                                                                          |
| Corrupted saved data   | When data saved is not understandable by rainyDay. May occur due to editing of saved file to a condition that violates the saving format                         |
| CSV                    | Stands for Comma Separated Value, a type of file format that can be imported to other statistical software such as Microsoft Excel, R Commander or Google Sheets |
| Filter                 | A function to narrow down the range of items to be shown                                                                                                         |
| Financial Statement    | Represents a transaction                                                                                                                                         |
| Financial Report       | Represents a compilation of financial statements                                                                                                                 |
| Flags                  | Has a "-" appended to the front of a symbol, example: "-d", "-date", "-c", etc                                                                                   |
| Inflow                 | Signify an increment of money on your side, such as deposits into your wallet                                                                                    |
| Json                   | Stands for JavaScript Object Notation. It is a file format that uses human-readable text to store data objects                                                   |
| Outflow                | Signify a decrement of money on your side, such as payments from your wallet                                                                                     |
| Transaction            | An activity relating to transferring of money                                                                                                                    |

## Instructions for manual testing

### Launch and shutdown

1. Initial launch
    1. Download the jar file and copy it into an empty folder

    2. Open the terminal and `cd` into the file directory where you placed "rainyDay.jar"

    3. Type the command `java -jar rainyDay.jar` and press Enter <br>Expected: rainyDay startup appears, prompting for
       your name

2. Shutdown
    1. Type the command `bye` and press Enter.<br>Expected: rainyDay should output a goodbye message and shutdown
       accordingly.

### Viewing rainyDay's inbuilt help

Prerequisites: None

1. Test case: `help`<br>Expected: A table showing all features and their commands will be shown, together with
   details, and a simplified guide.

2. Test case: `help add`<br>Expected: A table showing all the additional fields for the add command will be shown,
   together with its requirement and description. Example commands will also be shown, together with a description
   of what the example commands are trying to achieve.

3. Test case: `help shortcut`<br>Expected: As shortcut is a feature with 3 commands related to it, a table with the
   additional fields for the 3 commands will be shown, along with more information for them and examples.
   However, note that since shortcut_view does not have any additional fields needed, it is omitted from the first
   section of the table. An example for it is still given.

4. Test case: `help invalidcommand`<br>Expected: The same help table as if only `help` is inputted will be shown.

### Adding a transaction

Prerequisites: None

1. Test case: `add -in income $4000 -c full time pay -date 5/4/2023`<br>Expected: An inflow transaction with amount of
   4000, description of "income", category of "full time pay" and date of 05/04/2023 will be added into rainyDay.
   An output reflecting the action done will be shown.

2. Test case: `add -out beef-noodles $12`<br>Expected: No new transaction added. Error message for "unsupported
   description name" will be shown.

3. Test case: `add -in jackpot $21,474,837`<br>Expected: No new transaction added. Error message for "invalid value"
   will be shown.

### Deleting a transaction

Prerequisites: There are multiple transactions in rainyDay

1. Test case: `delete 1`<br>Expected: Transaction with index 1 shown in the `view -all` list before the delete command
   will be deleted. An output with the description of the transaction deleted will be shown.

2. Test case: `delete x`, where x is any character or a number < 1 or > the number of transactions inside rainyDay<br>
   Expected: No transaction deleted. Error message for "wrong delete index" will be shown.

### Viewing transactions

Prerequisites: There are no transactions in rainyDay that fit the provided timespan

1. Test case: `view`<br>Expected: A message indicating that there are no transactions in rainyDay in the time span will
   be shown.

2. Test case: `view -all`<br>Expected: A message indicating that rainyDay is completely empty will be shown.<br>

Prerequisites: There are multiple transactions with different amount and dates in rainyDay

1. Test case: `view`<br>Expected: Transactions that falls within the current month will be shown, sorted by the order
   of ascending date.

2. Test case: `view 3m -sort`<br>Expected: Transactions that falls between the current day and the last 3 months will
   be shown, sorted by the order of ascending date and increasing amount of inflow type of transaction, followed by
   decreasing amount of outflow type of transaction.

3. Test case: `view -all`<br>Expected: All transactions stored in rainyDay will be shown to user, ordered in ascending
   date.

4. Test case: `view 32d`<br>Expected: No transaction will be shown. Error message for "wrong view format" will be
   shown.

### Editing a transaction

Prerequisites: There must be at least one entry to edit

1. Test case: `edit 1 -d Chicken rice` <br> Expected: The description of transaction with index 1 shown in `view -all`
   list will be changed to "Chicken Rice".

2. Test case: `edit 1 -v $5 -c Food and Drinks -date 4/8/2023` <br> Expected: The value, category and date of
   transaction with index 1 will be changed to "$5", "Food and Drinks" and "04/08/2023" respectively.

3. Test case: `edit 1 -date 4/8/23 -d Noodles` <br> Expected: No transaction edited. Error message for "wrong edit
   format" will be shown as flag is in incorrect order.

### Filtering transactions

Prerequisites: There are multiple transactions in rainyDay.

1. Test case: `filter -d rice` <br> Expected: Transactions that contain "rice" in the description field will be listed
   out.

2. Test case: `filter -c Shopping -date 2/4/2023` <br> Expected: Transactions that contain "Shopping" in the category
   field and dated "02/04/2023" will be listed out.

3. Test case: `filter -c Shopping -date 2/4/2023 8/4/2023` <br> Expected: Transactions that contain "Shopping" in the
   category field and dated between "02/04/2023" and "08/04/2023" will be listed out.

4. Test case: `filter -c Food -d rice` <br> Expected: No transaction edited. Error message for "wrong filter
   format" will be shown as flag is in incorrect order.

### Ignoring/Un-ignoring Transactions

Prerequisites: There is at least 1 transaction in rainyDay. Assume test cases are done in order, and the transaction
begins un-ignored

1. Test case: `ignore 1`<br>Expected: A success message should be displayed, indicating that the 1st transaction is now
   ignored from all calculations.

2. Test case: `ignore 1`<br>Expected: An error message should be displayed, indicating that the 1st transaction was
   already ignored from all calculations.

3. Test case: `unignore 1`<br>Expected: A success message should be displayed, indicating that the 1st transaction is
   now included in all calculations.

4. Test case: `unignore 1`<br>Expected: An error message should be displayed, indicating that the 1st transaction was
   already included in all calculations.

5. Test case: `ignore 2`<br>Expected: An error message should be displayed, indicating the range of values allowed for
   the `INDEX` field.

### Set monthly budget goal

Prerequisites: None

1. Test case: `setbudget 1000` <br>Expected: A success message should be displayed, indicating that the user's monthly
   budget goal has been set to $1000 successfully.

2. Test case: `setbudget 0`<br>Expected: A success message should be displayed, indicating that the user's monthly
   budget goal has been removed successfully.

3. Test case: `setbudget -70`<br>Expected: An error message should be displayed, indicating that the command is
   input incorrectly.

### Adding a shortcut

Prerequisites: None

1. Test case: `shortcut a -maps add -out noodles $5`<br>Expected: A success message should be displayed
   indicating that the shortcut has been successfully added.

2. Test case: `shortcut b -maps b`<br>Expected: An error message should be displayed
   indicating that a shortcut cannot map to itself.

### Using a shortcut

Prerequisites: You have added a shortcut which you will be using. For this section, we will require you to configure
the first shortcut in the [adding a shortcut](#adding-a-shortcut) section

1. Test case: `a` <br>Expected: A message should indicate that a shortcut is being used. The `add -out noodles $5`
   command should also be successfully performed.

### Viewing shortcuts

Prerequisites: None

1. Test case: `shortcut_view` <br>Expected: A table should be printed out which displays all the configured shortcut
   commands. If no shortcuts have been configured, then a message should indicate that no shortcuts have been
   configured.

### Deleting a shortcut

Prerequisites: You have added a shortcut which you will be deleting. For this section, we will require you to
configure only the first shortcut in the [adding a shortcut](#adding-a-shortcut) section

1. Test case: `shortcut_delete a` <br>Expected: A success message should be displayed indicating that the shortcut has
   been successfully deleted.

2. Test case: `shortcut_delete doesnotexist`<br>Expected: An error message should be displayed indicating that the
   shortcut does not exist.

### Saving data

Prerequisites: Ensure that a change has been made to the `savedData`

1. Test case: `add -out beef noodles $12` <br>Expected: The .json file found in the data folder should reflect the new
   transaction.

2. Test case: `shortcut a -maps add -out noodles $5`<br>Expected: The .json file found in the data folder should
   reflect the new shortcut.

### Loading data

Prerequisite: None

1. Test case: Start rainyDay while the .json file is not present in the data folder.<br>Expected: No valid saved file
   will be detected and rainyDay will start up without loading any saved data.

2. Test case: Ensure that a valid .json file containing valid data named `rainyDay.json` is present in the data folder
   and start rainyDay.  <br>Expected: rainyDay will welcome you based on your configured name and all previously saved
   data will be loaded.

3. Test case: Start rainyDay with a corrupted `rainyDay.json`file, containing invalid fields like a negative transaction
   amount or an invalid .json format.
   <br>Expected: No valid saved file will be detected and rainyDay will start up without loading any saved data.

### Export to .csv

Prerequisites: There is at least one transaction in the financial report of rainyDay

1. Test case: `export` <br>Expected: A success message should be displayed indicating that the data has been
   successfully saved in a .csv file.

