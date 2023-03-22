<div align="center">
<h2> MoneyMind Developer Guide</h2>
<h3> "Mind your Money" </h3>
</div>

<div align="left">

## Acknowledgements

{list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well}

## Design & implementation

{Describe the design and implementation of the product. Use UML diagrams and short code snippets where applicable.}

:smiley: You can use PlantUML to create UML diagrams like what we did in this documentation. See [Using PlantUML](https://se-education.org/guides/tutorials/plantUml.html) for more information.

### Architecture

<img src="images\ArchitectureDiagram.png" width="300" />

The ***Architecture Diagram*** given above explains the high-level design of the App. 

Given below is a quick overview of how each component interact with each other.

The main components in the architecture are:

* `MoneyMind`: The main program of the application, 
it initializes the other components in the correct sequence and is responsible for shut down the application.
* `UI`: The user interface of the application.
* `Storage`: The storage of data of the application.
* `Data`: The data classes used in the application, including Event, Category and CategoryList.
* `Command`: The command of the application, including AddCommand, DeleteCommand, ListCommand, etc.

The following sections will explain the architecture in more detail.

## Storage component

**API**: `Storage.java`

{UML class diagram}

The `Storage` component,
* can save category and event data in txt format, and read it back. (txt format is chosen because it is human readable)
* depends on `CategoryList.java` and `CategoryCommand.java` to save and load data from ArrayList and HashMap.


## Product scope
### Target user profile

{Describe the target user profile}

### Value proposition

{Describe the value proposition: what problem does it solve?}

## User Stories

| Version | As a ... | I want to ...                                                                                          | So that I can ...                                          |
|-----|------|--------------------------------------------------------------------------------------------------------|------------------------------------------------------------|
| v1.0 | user | **add** an one time expense                                                                            | keep track of how much I spend                             |
|     | user | **delete** a one time expense                                                                          | ammend the record in case I add the wrong expense          |
|     | user | **categorize** my expenses into different categories such as food, transportation, entertainment, etc. | better understand where my money is going                  |
| v2.0 | user | **edit** one time expenses                                                                             | change when I type wrongly |
|     | user | **search** for specific expenses by keyword or date range                                              | easily find and review my past spending         |

## Non-Functional Requirements

{Give non-functional requirements}

## Glossary

* *glossary item* - Definition

## Instructions for manual testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}

</div>
