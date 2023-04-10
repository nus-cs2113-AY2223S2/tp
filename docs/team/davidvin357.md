# Viniarskyi Davyd's Project Portfolio Page
## Project: Food Supply Tracker

### Overview
Food Supply Tracker (FST) is a desktop food list tracking the expiry date of the food in the list.
It is optimized for use via a Command Line Interface (CLI) .
If you can type fast, FST can get your food management tasks done faster than traditional GUI apps.

### Contributions
###### Code Contributed: [Code Dashboard](https://nus-cs2113-ay2223s2.github.io/tp-dashboard/?search=David&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2023-02-17&tabOpen=true&tabType=authorship&tabAuthor=DavidVin357&tabRepo=AY2223S2-CS2113-W13-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

###### Enhancements implemented:
**1. New Feature: Find Command**
* Purpose: the command lets the user to find the products by term and/or custom filters
* Work done:
  * Fully implemented `FindCommand` class 
  * Added JavaDoc and JUnit tests for it

**2. New Feature: Update Command**
* Purpose: the command lets the user to update any attribute of the food item in any order 
  * Work done:
    * Fully implemented `UpdateCommand` class 
    * Added JavaDoc and JUnit tests for it
   
**3. Utils: `DateFormatter` and `Validator` classes**
* Purpose: reduce code redundancy and provide standard interface to oftenly used methods for parsing date and validating food attributes 
* Work done:
    * Created utils package with its `DateFormatter` and `Validator` classes and replaced repeated code throughout codebase with their methods

**4. Miscellaneous**
* Added Food constructor attribute validation and exception handling as a last resort mechanism  for catching errors 
* Implemented Food class `toString` method 
* Created `InvalidFlagException` and `FlagValueExceptionn` used for throwing errors on faulty flags


###### Contributions to the UG
- Find Command description with 2 examples
- Update Command description with 2 examples

###### Contributions to the DG
- Diagrams:
  + FindCommandSequenceDiagram.puml
  + FindCommandClassDiagram.puml
  + UpdateCommandSequenceDiagram.puml
  + UpdateCommandClassDiagram.puml

- Specifications:
  + `FindCommand` implementation description
  + `UpdateCommand` implementation description

###### Contributions to team-based tasks
- Allocated the issues among team members
- Resolved the following issues: [link](https://github.com/AY2223S2-CS2113-W13-3/tp/issues?q=is%3Aissue+assignee%3ADavidVin357+is%3Aclosed)

###### Review/mentoring contributions
- [#69](https://github.com/AY2223S2-CS2113-W13-3/tp/pull/69)
- [#200](https://github.com/AY2223S2-CS2113-W13-3/tp/pull/200)
- [#226](https://github.com/AY2223S2-CS2113-W13-3/tp/pull/226)
- [all reviews](https://github.com/AY2223S2-CS2113-W13-3/tp/pulls?q=is%3Apr+is%3Aclosed+reviewed-by%3A%40me)
