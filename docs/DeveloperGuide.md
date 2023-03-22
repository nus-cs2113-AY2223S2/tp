# Developer Guide

## Acknowledgements

{list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well}

## Design & implementation

{Describe the design and implementation of the product. Use UML diagrams and short code snippets where applicable.}

## Implementation

### Record and RecordList  

The main class in our program is the ```Record``` and ```RecordList``` abstract classes, in which ```Income```, ```Expense``` will inherit from ```Record``` and ```IncomeList``` and ```ExpenseList``` will inherit from ```RecordList```. Most commands will act on instances of the ```Income```, ```Expense```,```IncomeList``` and ```ExpenseList``` classes.  

![Record Class](/images/Record_RecordList_UML_class.png)

### DeleteIncomeCommand 

The proposed DeleteIncomeCommand mechanism is facilitated by ```System```, ```Incomes```, ```UI``` and ```ChChingException```.  
The command receives the instruction from ```UI``` and will call the ```execute``` method.  
If ```index <= 0```, the command will throw a new ChChingException and print ```"Zero/Negative index"```.  
If ```index > incomes.size()```, the command will also throw a new ChChingException and print ```"The number is too big"```  
Entering any of these optional lines will result in early termination of the command.  
Or else, the command will continue to delete the entry at the particular index.  
Afterwards, the ```execute()``` method will print ```"Income deleted, here is the updated list:"``` and prints the entries in the income list.

![Record Class](/images/Sequence_diagram.png)

### ListExpenseCommand
The listExpenseCommand is facilitated by ```System```, ```Parser``` and ```ExpenseList```.

1. The user inputs the command top list expense. This input is handled by```Parser``` which returns the
```listExpenseCommand``` if successful. 
2. ```ListExpenseCommand``` will then call its ```execute``` method
which makes use of ```System``` to print a new line ```"Expenses:"```. 
3. The ```expenseList``` method ```printExpenseList```
is called, which iterates through the expenseList, ```expenses``` and prints the index as well as a completed string of 
expenses in ```expenses```.

## Product scope
### Target user profile

Target users are people who are keen on improving their financial accountability

### Value proposition

The value proposition of ChChing is its ability to track income and expenses on a daily basis.

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
