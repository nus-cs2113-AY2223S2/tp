---
layout: page
title: Developer Guide
---

<div align="center">
<h2> MoneyMind Developer Guide</h2>
<h3> "Mind your Money" </h3>
</div>

Moneymind is a Command Line Interface (CLI) application that helps you manage your personal finances. With Moneymind, you can keep track of your budgets, expenses, and categorize them for better organization.

* Table of Contents
{:toc}

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

The *sequence diagram* above shows how the components interact with each other for the scenario 
where the user issues the command `event buy lunch e/10`.

The following sections will explain the architecture in more detail.

### Storage component

**API**: `Storage.java`

<img src="images\Storage.png" width="1000" height = "600">

The `Storage` component,
* can save category and event data in txt format, and read it back. (txt format is chosen because it is human readable)
* depended on by `CategoryList.java` and `CategoryCommand.java` to load data to ArrayList and HashMap, and takes in an ArrayList of Category objects as parameter to save data.

When the user first starts the application:<br>
<img src="images\StorageSequenceDiagram.png" width="500"><p>
When the program is running:<br>
<img src="images\StorageRunSequenceDiagram.png" width="700"><p>

### Data component

**API**: `Category.java`, `CategoryList.java`, `Event.java`

The data structure of MoneyMind follows a very simple design.

`CategoryList`: It uses an ArrayList to store all the categories. 
Each category is an object of `Category` class.

`Category`: It uses an ArrayList to store all the events.
Each event is an object of `Event` class.

`Event`: It stores the information of each event.

The details of the definition of `Category` and `Event` can be found
in the [Glossary](#appendix-d--glossary) section.

### Commands component
  
<img src="images\CommandParser.png" height = "550" width= "1400">

**API**: `Command.java`

The `Command` interface and classes,
* every command type is represented by a class that implements the Command interface
* can perform actions by executing different commands, such as adding category, adding event, deleting category, deleting event, viewing category, viewing event
* also depends on `CategoryList.java`, `CategoryCommand.java`, `Event.java`, to execute the commands.

**API**: `Parser.java`

The `Parser` class,
* responsible for parsing user input and creating the appropriate command classes
* also depends on `CategoryList.java`, `CategoryCommand.java`, `Event.java`, to execute the commands.

## Implementation

### Parser component

The Parser class contains several private methods, each of which is responsible for creating a specific type of Command
object based on the user input. Each method takes in an array of strings as an argument, which contains 
the user input split into keyword and the content. The method then checks the first word in the array to determine which
type of Command object to create. If the first word matches a specific keyword (e.g. "bye"), the corresponding Command
object is created and returned. If the keyword is not recognized, an InvalidCommandException is thrown. Some methods 
also perform additional checks on the input string to ensure that it is formatted correctly. If the input is in correct
format, the corresponding Command object is created and returned. Otherwise, an InvalidCommandException is thrown.

### Commands component

The Command class is an interface that is implemented by all the different types of Command objects. It contains methods
that are common to all Command objects, such as execute() and isExit(). The execute() method contains the logic to
perform certain tasks and is called when the Command object is to be executed. The isExit() method is called to 
check if the Command object is an ExitCommand object, which signals the end of the program.

### Exceptions component

The Exceptions class contains several types of custom Exception objects that are thrown when the user input is not
in the correct format. 

### Category component

The components use arraylist to store events for each category and 
store all categories in a category list.

### Event component

The components implement event class to store the information of each event.

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

If you are a NUS Student who wants to prioritize spending and save for the future while enjoying a good campus life,
MoneyMind is the perfect application for you. It is a simple, easy-to-use application that helps you manage your expenses
and track your spending habits. You can easily categorize your expenses and view your spending history by category.

As the application is CLI-based, you can easily navigate through the application and perform the necessary tasks if
you are a fast-typer. You can also use the application on any mainstream OS as long as you have Java 11 or above installed.


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
3. This application is not built for mobile devices. It is recommended to use a laptop.
4. The user is expected to have a good habit of constantly and systematically recording their
expenses. MoneyMind is not optimised for users that only record their expense on ad-hoc basis.
5. Other than the above, please enjoy using MoneyMind!

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

### Launch

1. Download the latest MoneyMind.jar and save it to an empty folder.
2. Open a command prompt in the folder and run the command java -jar MoneyMind.jar. The output should be similar to the below.

```
Loading file...
Welcome to Moneymind
  __  __                        __  __ _           _ 
 |  \/  |                      |  \/  (_)         | |
 | \  / | ___  _ __   ___ _   _| \  / |_ _ __   __| |
 | |\/| |/ _ \| '_ \ / _ \ | | | |\/| | | '_ \ / _` |
 | |  | | (_) | | | |  __/ |_| | |  | | | | | | (_| |
 |_|  |_|\___/|_| |_|\___|\__, |_|  |_|_|_| |_|\__,_|
                           __/ |                     
                          |___/                      
How may I help you?
Type 'summary' to see the summary of all the commands you can use.
Type 'help' to see the details of all the commands.
```

### View summary of commands

Type summary and press enter. The output should be similar to the below.

```
Here are the commands you can use:
1. help
2. summary
3. category
4. event
5. view
6. edit
7. delete
8. search
9. bye
```

### View details of commands

Type help and press enter. The output should be similar to the below.

```
Here are the commands you can use:
1. help - show detailed instructions on how to use the app
Format: help
Example: help

2. summary - show a summary of the commands that you can use
Format: summary
Example: summary

3. category - add a category to your list
Format: category <name> [(optional) b/<budget number>]
Example: category food b/2000

4. event - add an event to a category
Format: event <name> e/<expense number> [(optional) t/<time>]
Example: event lunch e/10 t/01/01/2020 12:00
(time is optional and the format is dd/mm/yyyy hh:mm)

5. view - view all the events in a category or all the categories
You can view all the events in a category by specifying the category name
Format: view <category name>
Example: view food
(category name is optional and if you do not enter a category name, all the categories will be shown)

6. edit - edit the expense for an event or budget for a category
Format: edit c/<category name> [(optional) e/<event index>]
Example: edit c/food e/1

7. delete - delete an event or a category
Format: delete c/<category name> [(optional) e/<event index>]
Example: delete c/food e/1
Example: delete c/food

8. search - search for matching events and categories
Format: search <keyword>
Example: search bill

9. bye - exit the app
Format: bye
Example: bye
```

The use of "/" is not allowed anywhere in the user input except for the time parameter in the event command or 
when using in specifier in the command.
### Add a category

| Cases                                | Example                       | Expected                                            |
|--------------------------------------|-------------------------------|-----------------------------------------------------|
| correct format without budget        | category food                 | category food with budget 0 is added                |
| correct format with budget           | category food b/1000          | category food with budget 1000 is added             |
| incorrect format or empty parameters | category food / b/            | show correct format message                         |
| empty description                    | category                      | show empty description message                      |
| not a natural integer for budget     | category food b/-1            | remind user to type non-negative integer for budget |
| big number for budget                | category food b/9999999999999 | remind user to type value under limit               |
| existed category                     | category food                 | remind user that the category already existed       |


### Add an event

| Cases                                | Example                             | Expected                                             |
|--------------------------------------|-------------------------------------|------------------------------------------------------|
| correct format without time          | event sugar e/12                    | one time expense sugar is added                      |
| correct format with time             | event sugar e/12 t/10/03/2020 12:00 | monthly recurring expense sugar is added             |
| incorrect format or empty parameters | event e/12 t/10/03/2020 12:00       | show correct format message                          |
| empty description                    | event                               | show empty description message                       |
| not a natural integer for expense    | event sugar e/as                    | remind user to type non-negative integer for expense |
| big number for expense               | event sugar e/9999999999999         | remind user to type value under limit                |
| incorrect format for time            | event sugar e/12 t/August           | remind user to use correct format for time           |

Upon successfully pass the command input, the user will be prompted to select a category to add the event to. 

| Cases                 | Example | Expected                                                                                              |
|-----------------------|---------|-------------------------------------------------------------------------------------------------------|
| existing category     | food    | one time expense sugar is added to category food                                                      |
| non-existing category | book    | notify the user the category does not exist and the user can type back to go back to the main program |

### View all the events in a category or all the categories

| Cases                                 | Example   | Expected                                   |
|---------------------------------------|-----------|--------------------------------------------|
| the user only type view               | view      | every category and event are shown to user |
| the user type view with category name | view book | show every events in category book to user | 
| non-existing category                 | view car  | notify user the category does not exist    | 

### Edit the expense for an event or budget for a category

| Cases                                  | Example                     | Expected                                                 |
|----------------------------------------|-----------------------------|----------------------------------------------------------|
| correct format without event index     | edit c/food                 | prepare to edit budget of food                           |
| correct format with event index        | edit c/food e/1             | prepare to edit expense for first event in category food |
| incorrect format or empty parameters   | edit e/1 c/food             | show correct format message                              |
| empty description                      | edit                        | show empty description message                           |
| not a positive integer for event index | edit c/food e/0             | remind user to type positive integer for event index     |
| big number for event index             | edit c/food e/9999999999999 | remind user to type value under limit                    |
| non-existing category                  | edit c/car                  | notify the user the category does not exist              |
| non-existing event index               | edit c/food e/12            | notify the user the event does not exist                 |

Upon successfully pass the command input, the user will be prompted to enter the new expense or budget.

| Cases                      | Example      | Expected                                                                                                |
|----------------------------|--------------|---------------------------------------------------------------------------------------------------------|
| non-negative integer       | 12           | the value is changed to 12                                                                              |
| not a non-negative integer | as           | remind the user to enter a non-negative value and the user can type back to go back to the main program |
| big positive integer       | 999999999999 | remind the user about the limit and the user can type back to go back to the main program               |

### Delete an event or a category

| Cases                                  | Example                       | Expected                                             |
|----------------------------------------|-------------------------------|------------------------------------------------------|
| correct format without event index     | delete c/food                 | delete food category                                 |
| correct format with event index        | delete c/food e/1             | delete first event in food category                  |
| incorrect format or empty parameters   | delete e/1 c/food             | show correct format message                          |
| empty description                      | delete                        | show empty description message                       |
| not a positive integer for event index | delete c/food e/0             | remind user to type positive integer for event index |
| big number for event index             | delete c/food e/9999999999999 | remind user to type value under limit                |
| non-existing category                  | delete c/car                  | notify the user the category does not exist          |
| non-existing event index               | delete c/food e/12            | notify the user the event does not exist             |


### Search for matching events and categories

| Cases                                | Example    | Expected                                                                                                                     |
|--------------------------------------|------------|------------------------------------------------------------------------------------------------------------------------------|
| empty description                    | search     | remind user to include keyword after search                                                                                  |
| with keyword                         | search foo | show all matching categories and events which contain the keyword and show top 3 most similar matching categories and events |

### Exit the app

Type bye and press enter to exit the app. The output should be similar to the following:

```
Bye. Hope to see you again soon!
```

