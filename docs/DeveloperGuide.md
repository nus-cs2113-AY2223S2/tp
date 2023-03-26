<div align="center">
<h2> MoneyMind Developer Guide</h2>
<h3> "Mind your Money" </h3>
</div>

<div align="left">

## Acknowledgements

{list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well}

## Design

{Describe the design and implementation of the product. Use UML diagrams and short code snippets where applicable.}

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

<img src="images\ArchitectureSequence.png" width="500" />

The *sequence diagram* above shows how the components interact with each other for the scenario where the user issues the command `event buy lunch e/10`.

The following sections will explain the architecture in more detail.

### Storage component

**API**: `Storage.java`

{UML class diagram}

The `Storage` component,
* can save category and event data in txt format, and read it back. (txt format is chosen because it is human readable)
* depends on `CategoryList.java` and `CategoryCommand.java` to save and load data from ArrayList and HashMap.

### Commands component

**API**: `Command.java`

{UML class diagram}

The `Command` component,
* can execute different commands, such as adding category, adding event, deleting category, deleting event, viewing category, viewing event
* depends on `CategoryList.java`, `CategoryCommand.java`, `Event.java`, `Parser.java` to execute the commands.

### Parser component

**API**: `Parser.java`

{UML class diagram}

The `Parser` component,
* can parse the user input and process it to the correct format
* depends on `CategoryList.java`, `CategoryCommand.java`, `Event.java`, `Command.java` to execute the commands.

## Implementation
This section describes some noteworthy details on how certain features are implemented.
  
## Appendix A: Product Scope
### Target user profile

-------------------------
<div align="center">
<img src="images\NUS_Students.png" width="300" />
</div>

* **Name**: NUS Student
* **Age**: 18-25 years old
* **Occupation**: Student
* **Education**: Currently enrolled in NUS
* **Income**: Limited income sources (part-time jobs, allowances from parents, etc.)
* **Technology usage**: Tech-savvy and comfortable using a CLI-based application
* **Interests**: Campus life, budgeting, saving, optimizing spending
* **Financial goals**: Prioritize spending, cut down on unnecessary expenses, improve financial health
* **Challenges**: Limited income, lack of financial knowledge, lack of financial discipline
* **Needs**: A simple, easy-to-use application to help manage expenses and track spending habits

Overall, NUS Student is a financially-conscious individual who wants to prioritize spending and save for the future while 
enjoying a good campus life. They are comfortable with technology and prefer an efficient and easy-to-use tool like MoneyMind 
to manage their finances. They are interested in tracking their expenses by category and exploring new ways to 
optimize their spending to achieve their financial goals.

### Value proposition

{Describe the value proposition: what problem does it solve?}

## Appendix B: User Stories

| Version | As a ... | I want to ...                                                                                          | So that I can ...                                          |
|-----|------|--------------------------------------------------------------------------------------------------------|------------------------------------------------------------|
| v1.0 | user | **add** an one time expense                                                                            | keep track of how much I spend                             |
|     | user | **delete** a one time expense                                                                          | ammend the record in case I add the wrong expense          |
|     | user | **categorize** my expenses into different categories such as food, transportation, entertainment, etc. | better understand where my money is going                  |
| v2.0 | user | **edit** one time expenses                                                                             | change when I type wrongly |
|     | user | **search** for specific expenses by keyword or date range                                              | easily find and review my past spending         |

## Appendix C: Non Functional Requirements

1. MoneyMind should work on any mainstream OS as long as it has Java 11 or above installed.
2. The user is expected to be a fast-typer. MoneyMind is not optimised for users that are not 
used to CLI applications.
3. The user is expected to have a good habit of constantly and systematically recording their
expenses. MoneyMind is not optimised for users that only record their expense on ad-hoc basis.
4. Other than the above, please enjoy using MoneyMind!

## Appendix D: Glossary

* *budget* - A budget is a financial plan that outlines an individual's or 
organization's expected income and expenses over a specific period of time.
In context given, the budget here is scaled down to NUS students' expenses over 
different categories.
Budgets can typically include categories for different types of expenses, 
such as housing, transportation, food, entertainment, and savings.
* *expense* - An expense is the cost incurred by an organization or individual.
In context given, the expense here is scaled down to NUS students' spending over
different events that they are engaged in.
*  *event* - An event is a specific occurrence of expenses that is planned or occurs.
It can be a one time expense like buying a pair of sneakers or recurring expenses like
electricity bills.
*  *category* - A category is a group of events that are related to each other. For example,
food, transportation, entertainment, etc.
*  *one time expense* - An expense that occurs only once.
*  *recurring expense* - An expense that occurs repeatedly, in the context of MoneyMind, the
frequency is set to monthly.

## Appendix E: Instructions for Manual Testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}

</div>
