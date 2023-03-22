# Developer Guide

## Acknowledgements

{list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the
original source as well}

- [AB3](https://github.com/se-edu/addressbook-level3)
- [OpenCSV](https://opencsv.sourceforge.net/)

## Design

{Describe the design and implementation of the product. Use UML diagrams and short code snippets where applicable.}

### Architecture

{to insert image?}
{insert description of image?}
Components:

- `RainyDay`: the main component to initialize the components and shutting down
- `Modules`: parts of the product
- `Data`: holds the data of the app
- `Command`: carries out the respective commands
- `Exceptions`: application specific error related information

**How the architecture components interact with each other**
{insert diagram and explain}

### Modules component

### Data component

{insert diagrams}

- stores the financial report data, such as financial statement objects

### Command component

{insert diagrams}

1. When a command is parsed, a command object specific to the command given will be created, with the necessary
   parameters stored as attributes in the command specific class
2. RainyDay will then call method execute() in the command class to return a CommandResult object, which contains
   the output to be shown to user

### Exceptions component

## Implementation

### Maintaining of Financial Report

- The modelling of a financial report is achieved with the use of an ArrayList collection of the object
  FinancialStatement.
- FinancialStatement contains the following attributes:
    - description: a string representing the description of a transaction
    - flowDirection: an enum of FlowDirection, either INFLOW or OUTFLOW
    - value: a double representing the amount tagged with the transaction
    - category: a string representing the type of transaction
    - date: the date to be tagged with the type of transaction

### Adding an entry

- When a command is given to add a statement, the command is first parsed to check whether it follows the format of an
  add command: `add -DIRECTION DESCRIPTION $VALUE -c CATEGORY -d DESCRIPTION` with the use of regex pattern
    - {details of how the command format should be?}
- Commands in the correct format will then be parsed to extract the relevant information, and an AddCommand object will
  be created with the relevant information passed to it
- rainyDay will then call Command.execute(), where the transaction will be added into the financial report

### Deleting an entry

- When a command is given to delete a statement, the command is first parsed to check whether it follows the format of a
  delete command: `delete INDEX` with the use of regex pattern
    - {details of how the command format should be?}
- Commands in the correct format will then be parsed to extract index, and a DeleteCommand object will
  be created with the relevant information passed to it
- rainyDay will then call Command.execute(), where the indicated transaction will be deleted from the financial report

### Editing an entry
- When a command is given to edit a statement, the command is first parsed to check whether it follows the format of an
  edit command: `edit INDEX ADDCOMMAND` or `edit INDEX FLAG NEWFIELD` or `edit INDEX FLAG` with the use of regex.
- Commands in the correct format will then be used to create a FilterCommand object.
- rainyDay will then call Command.execute(), where the transaction's specific field be edited or deleted then added 
  into the financial report.

### Filtering an entry

## Product scope

### Target user profile

- Tech-savy
- Working adults who are inexperienced in managing their finances
- Prefers typing
- Can type fast
- Prefers typing to mouse interactions
- Is reasonably comfortable using command line interface applications
- Prefers desktop applications over other types

### Value proposition

Help people who are just starting out working and troubled by financial issues such as managing of budget

## User Stories

| Version | As a ...                            | I want to ...                                                | So that I can ...                                      |
|---------|-------------------------------------|--------------------------------------------------------------|--------------------------------------------------------|
| v1.0    | working adult                       | track my outflows by adding new entries                      | where my money is going                                |
| v1.0    | working adult                       | track my inflows by adding new entries                       | know how much I am earning                             |
| v1.0    | diligent user                       | view my inflow and outflow                                   | know where my money is going                           |
| v1.0    | careless user                       | delete my entries                                            | recover after making wrong entries                     |
| v1.0    | returning user                      | continue from my previous data                               | re-enter all the entries                               |
| v2.0    | new user                            | see usage instructions                                       | refer to them when I forget how to use the application |
| v2.0    | user who uses multiple applications | access the data I saved in RainyDay in my other applications | avoid having to re-enter the same data                 |
| v2.0    | lazy user                           | use shortcuts                                                | avoid having to type in lengthy commands               |
| v2.0    | diligent user                       | filter my data                                               | view my past expense with ease                         |
| v2.0    | lazy user                           | edit my entries                                              | amend my wrong entries                                 |

## Non-Functional Requirements

{Give non-functional requirements}

## Glossary

* *glossary item* - Definition

## Instructions for manual testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}
