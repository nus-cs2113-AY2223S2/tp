# Developer Guide

## Table of Contents

1. [Acknowledgements](#acknowledgements)
2. [Design](#design)
   - [Architecture](#architecture)
   - [UI component](#ui-component)
   - [Parser component](#parser-component)
   - [Storage component](#storage-component)
   - [Common class](#common-class)
3. [Implementation](#implementation)
4. [Appendix: Requirements](#appendix--requirements)
5. [Appendix: Instructions for manual testing](#appendix--instructions-for-manual-testing)

---

## Acknowledgements

{list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the
original source as well}

- [addressbook-level2](https://github.com/se-edu/addressbook-level2)
- [addressbook-level3](https://github.com/se-edu/addressbook-level3)

---

## Design

### Architecture

![Architecture Diagram](images/ArchitectureDiagram.png)

The ***architecture diagram*** given above explains the high level design of the program.

Given below is a quick overview of the main components and how they interact with each other.

**Main components of architecture**

`BrokeMan` has one class [`Main`](https://github.com/AY2223S2-CS2113-F13-2/tp/blob/master/src/main/java/seedu/brokeMan/BrokeMan.java), which is responsible for:
- At program launch: Initialises the components in the correct sequence, and connect them up with each other
- At program termination: Shuts down the components and invokes cleanup methods where necessary.

[`Common`](#common-class) represents a collection of messages used by multiple other components.

The rest of the program consists of mainly 5 main components.
- [`Ui`](#ui-component): The Ui of the program.
- [`Parser`](#parser-component): The user input parser.
- [`Command`](#command-component): The command executor.
- [`Storage`](#storage-component): Reads data from, and writes data to hard disk.
- [`EntryList`](#entrylist-component): Stores the list of entries when program is running.

**How the architecture components interact with each other**

The sequence diagram shows how the components interact with each other for the scenarios where the user issues
the commands `deleteExpense 1` and `exit`.

![ArchitectureSequenceDiagram](images/ArchitectureSequenceDiagram.png)

---

### Ui component

The **API** of this component is specified in [`Ui.java`](https://github.com/AY2223S2-CS2113-F13-2/tp/blob/master/src/main/java/seedu/brokeMan/ui/Ui.java)

The Ui consists of methods to format the output to be displayed to the user.

Here is the UML diagram of Ui class:
{to be added}

### Parser component

The **API** of this component is specified in [`Ui.java`](https://github.com/AY2223S2-CS2113-F13-2/tp/blob/master/src/main/java/seedu/brokeMan/parser/Parser.java)

How the `Parser` component works:
1. When `Parser` is called to execute a command, it uses the `Parser` class to parse the user command. 
2. The `Parser` class uses `UserInput` class to split the user input.
2. This results in a `Command` object(more precisely, an object of one of its subclasses eg., `AddExpenseCommand`) which is executed by the `runCommandUntilExitCommand` method in BrokeMan class.
3. The command can communicate with the `EntryList` component when it is executed(eg. to add an expense in the expense list)
4. The result of the command execution is returned back from `Parser`.

How the parsing works:

- When called upon to parse a user command, the `Parser` class creates an `prepareXYZCommand` (XYZ is a placeholder for the specific command name e.g., `prepareViewBudgetCommand`) which uses the other classes shown above to parse the user command and create a XYZCommand object (e.g., `ViewBudgetCommand`) which the `Parser` returns back as a `Command` object.
- All `prepareXYZCommand` methods (e.g., `prepareViewBudgetCommand`, `prepareSetBudgetCommand`, …) can be treated similarly where possible e.g, during testing.

### Save component

The **API** of this component is specified in [`SaveBudget.java`](https://github.com/AY2223S2-CS2113-F13-2/tp/blob/master/src/main/java/seedu/brokeMan/save/SaveBudget.java), [`SaveExpense.java`](https://github.com/AY2223S2-CS2113-F13-2/tp/blob/master/src/main/java/seedu/brokeMan/save/SaveExpense.java), [`SaveIncome.java`](https://github.com/AY2223S2-CS2113-F13-2/tp/blob/master/src/main/java/seedu/brokeMan/save/SaveIncome.java)

The `Save` component,

- can save entrylist data and budget data in the hard disk as txt files, and read them back into corresponding objects.
- depends on some classes in the EntryList component and Budget component(because the Save component’s job is to save/retrieve objects that belong to the EntryList and Budget)

### EntryList component

The **API** of this component is specified in [`EntryList.java`](https://github.com/AY2223S2-CS2113-F13-2/tp/blob/master/src/main/java/seedu/brokeMan/entry/EntryList.java)

The `EntryList` component,
- stores the entry list data i.e., all `Entry` objects (which can inherit the behavior of `Expense` class or `Income` class)
- stores the `EntryAmountComparator` and `EntryDateComparator` to filter the list. The UI can be bound to this list so that the UI automatically updates when the data in the list change.
- does not depend on any of the other three components (as the `EntryList` represents data entities of the domain, they should make sense on their own without depending on other components).

Here is the (partial) UML diagram of the `EntryList` component:

![EntryListClassDiagram](images/EntryListClassDiagram.png)

### Budget component

The **API** of this component is specified in [`Budget.java`](https://github.com/AY2223S2-CS2113-F13-2/tp/blob/master/src/main/java/seedu/brokeMan/budget/Budget.java)

The `Budget` component,
- stores the monthly budget data in a hashmap
- does not depend on any of the other three components (as the `Budget` represents data entities of the domain, they should make sense on their own without depending on other components).

### Common class

Messages used by multiple components are in the `seedu.brokeMan.commmon` package.

### Command classes

The execution behaviors of possible commands are in the `seedu.brokeMan.command` package.

### Exception classes

Possible exceptions in multiple components are defined in the `seedu.brokeMan.exception` package.

---

## Implementation

### Entry

Entry class is the underlying superclass for Expense and Income classes. It establishes the common attributes and
methods that are necessary to represent Expenses and Incomes. Abstract class is used to represent their common features
to maximize code reusability and increase maintainability.

Private attributes

- Info: String that stores the description of the entry

- Amount: Double that stores the monetary value of entry 

- Time: LocalDateTime that stores the date and time of entry

- Category: Category that stores the type tag of entry


**Methods**

Getters can be used to provide the private attributes to other classes

editDescription(), editAmount(), editTime(), editCategory()

* Takes in corresponding parameters to edit the private attributes.
* Used by EntryList to make edits

isSameMonth()

* Takes in Integer year and Month and returns if the entry is made in the date specified by parameters.

### EntryList

The EntryList class represents a collection of Entry instances. It is an abstract class that serves as a superclass for
ExpenseList and IncomeList classes, providing common functionalities to minimize repetitive code and easing code
maintenance. It provides underlying methods for adding, removing, editing, and listing entries from the list, which can
be expenses or incomes depending on the subclass.

On top of the methods that provide basic functionality of expense/income lists, the EntryList class provides additional
methods such as summing all entries or filtering the entries according to LocalDate provided to the method. Most methods
of EntryList take in a List<Entry>, which is because it has to operate on a list of expenses or incomes passed by the
IncomeList or ExpenseList subclasses.

**Methods**

addEntry(), listEntry(), deleteEntry(), editEntry()

* Underlying methods of the add, edit, view, and delete features of Expense and Income class. 
* Edit entry has different methods for each data stored in an entry. (E.g. amount, time...etc).

   
getTotalAmount(), sortEntriesByAmount(), sortEntriesByDate(), findEntriesByCategory(), selectEntryForDate(), getEntryListSum()

* Underlying methods of subclasses of EntryList, which are used to implement features that extends beyond the CRUD features.
* They all take in a list of entries, which are IncomeLists or ExpenseLists, then returns appropriate data back to the subclass.

   
### ExpenseList, IncomeList

Classes ExpenseList and IncomeList are responsible for keeping track of the corresponding entry instances added to the program by the user. 
At a class level, it keeps a **LinkedList** of corresponding entries. 
They both extend EntryList, the abstract class that represents a collection of Entry instances. 
It provides static functionalities of managing and viewing entry instances at a class level. 
Instances of ExpenseList and IncomeList are not created as all functionalities can be provided at the class level. 
  
**Methods**

listExpense() / listIncome()

* Overloaded method, may take it no parameter or LocalDate parameter
* If it has no passed parameters, it returns all entries in the list
* If a LocalDate is passed, it returns all entries made in the month specified by LocalDate instance.

### Budget

The Budget class represents the user’s monthly budget. The class utilize class-level hashmaps to represent the monthly
budget, using outer key year and inner key month. It provides a method to set and view budget for different months.

It makes use of a static HashMap<Integer, HashMap<Month, Double>> to keep track of monthly budget. If the user tries to
access budget using keys that are not entered in the HashMap, it will return a warning mentioning that the inquired
budget has not been set yet.

### SaveExpense, SaveIncome, Save Budget

The SaveExpense, SaveIncome, Save Budget class deal with saving in the user inputted data locally.
So that it can all be later accessed.
They all save once the exit command is set.
So assuming there are no bugs it should save.

**Methods**

writeFile(LinkedList<Entry> expenses/incomes)
* writes to the file in a similar format that is entered in to make a new Expense object within the constructor.

readExpenseFile()

* This method reads in from the saved file either ExpenseData.txt or IncomeData.txt. 
* Reads in and initialized each line as either expense or income. 
* Then adds to the respective list.


writeFile(HashMap<Integer, HashMap<Month, Double>> budget)
* This method writes to the file under ./data/BudgetData.txt file.
* Will write upon exit.
* organizes it based on how it the information is constructed for easy reading.
* Iterates through the outer HashMap then inner.

public static HashMap<Integer, HashMap<Month, Double>> readFile()
* On start up reads through the file ./data/BudgetData.txt.
* Reads in and the year then as a key then the monthly declared budget of the given months.
* Returns this as the initialized budget on start.


### Wishlist (To be implemented)
   
The Wishlist class represents a good or a product the user wants to purchase in the future that are expensive enough, prompting the user to save up. Users can funnel their income entry to a specific wishlisted-product. Users can view how much percentage of the good's price they have saved up, which can give them further motivation to cut their spendings. Users will be able to add a list of wishlisted products. 
   
They can be implemented through a structure that is similar to Entry and EntryList. Each wishlisted product will be a separate class that stores the information of the good, such as name, price, and the date of wishlist created. 
   
The wishlist entry can then be arranged to a list, where users can easily navigate and compare between different wishlisted products. They can also give priority value to each wishlisted product, which can be used to sort them.
   

### Spending Advisor (To be implemented)
   
The spending advisor will be integrated to the product by assisting users to make best consumption choices. The advisor will help users compare prices from different food outlets and shops. As the target user for this program is students, the program will first implement food stalls and shops in NUS, which is where the developers for this program are enrolled. In subsequent iterations of the program, the advisor will expand into other regions and recommend users of the best-value purchases. 
   
---


## Appendix: Requirements

### Product scope

**Target user profile**:

- BrokeMan is suitable for students who have to work with tight budgets
- students who want to use their money efficiently
- students who want to minimize their spending.
- students who can type fast
- prefer typing to mouse interactions
- is reasonably comfortable using CLI apps

**Value proposition**:

- Manage income and expenses faster than a typical mouse/GUI driven app
- The program will offer visualization of the user's incomes, expenses and budget, allowing them to recap and be mindful
  about their financial status. The project will allow division of budget into multiple subcategories of expenses. In
  essence, the program sets students up for a better financial future.

### User Stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | Version | As a ... | I want to ...                             | So that I can ...                                |
|----------|---------|----------|-------------------------------------------|--------------------------------------------------|
| `* * *`  | v1.0    | user     | add, delete, edit, and list my income     | manage my income                                 |
| `* * *`  | v1.0    | user     | add, delete, edit, and list my expenses   | manage my expenses                               |
| `* * *`  | v1.0    | user     | set and view my budget                    | set expectation of how much money I should use   |
| `* * *`  | v1.0    | user     | view how much of the budget I spend       | manage and change my spending habit as necessary |
| `* *`    | v1.0    | user     | view all command that I can enter         | get help on the features if necessary            |
| `* *`    | v2.0    | user     | list monthly expenses, income, and budget | refer to financial status in previous months     |
| `* *`    | v2.0    | user     | save all my income and expenses entered   | so that I can refer to it next time I return     |
| `* *`    | v3.0    | user     | add goods on wishlist                     | so that I can save my incomes to purchase them   |
| `* `     | v3.0    | user     | compare different spending options in the area | so that I can make the best-value purchases |
   
### Use cases

(For all use cases below, the System is the `BrokeMan` and the Actor is the `user`, 
unless specified otherwise)

#### Use case: Delete an expense

#### MSS

1. User requests to list expenses.
2. BrokeMan shows a list of expenses.
3. User requests to delete a specific person in the list.
4. BrokeMan deletes the person.

    Use case ends.

#### Extensions
* 2a. The list is empty.
    
    Use case ends.
* 3a. The given index is invalid.
  * 3a1. BrokeMan shows an error message.

    Use case resumes at step 2.

{More to be added}

### Non-Functional Requirements

1. Should work on any mainstream OS as long as it has Java 11 or above installed.
2. Should be able to hold up to 1000 entries without a noticeable sluggishness in performance for typical usage.
3. A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.
{More to be added}

## Glossary

- **Mainstream OS**: Windows, Linux, Unix, OS-X
- Command Line Interface (CLI)

---

## Appendix: Instructions for manual testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}
