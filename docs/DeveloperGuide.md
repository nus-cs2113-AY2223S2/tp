# Developer Guide

## Acknowledgements

{list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the
original source as well}

- [AB3](https://github.com/se-edu/addressbook-level3)
- [OpenCSV](https://opencsv.sourceforge.net/)

## Design & implementation

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
