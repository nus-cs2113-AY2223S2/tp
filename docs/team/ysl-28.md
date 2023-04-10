# Yong Shan Ling's Project Portfolio Page

## Overview
MagusStock is a Java command-line interface (CLI) designed for inventory management. 
This application that I have contributed to developing with my team aims to help store operators, 
IT administrators and logistics managers to manage their inventory more efficiently and effectively. 
While CLI applications are not as user-friendly as GUI applications, they are much faster to use and 
more importantly, portable and can be used on any platform that supports Java.

## Summary of Contributions
Code contributed: [RepoSense link](https://nus-cs2113-ay2223s2.github.io/tp-dashboard/?search=w12-3&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2023-02-17&tabOpen=true&tabType=authorship&tabAuthor=ysl-28&tabRepo=AY2223S2-CS2113-W12-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

**Enhancements implemented**:
* Added `list` feature
  * Allows the user to view all items in the inventory in table form
  * Enhanced `list` feature by adding text wrapping for content in the table to allow long and multi-word item names to be viewed conveniently
  * Implemented `ListCommand` and `ListParser` classes
* Added `alert` feature
  * Allows the user to set alerts to be printed when the quantity of an item is below a minimum or exceeds a maximum
  * Allows the user to remove previously-added alerts
  * Allows the user to view existing alerts in table form
  * Implemented `Alert` and `AlertList` 
  * Implemented `AddAlertCommand`, `RemoveAlertCommand` and `AlertParser` classes
* Javadoc comments for classes and methods
* JUnit tests for `ListCommand`, `AddAlertCommand`, `RemoveAlertCommand` and `AlertParser`


**Contributions to the UG**:
* Added documentation for the `list` and `alert` commands
* Added command summary 
* Updated sample output of all UG commands to match updated output of `list` command
* Fixed typos and formatting errors in the UG


**Contributions to the DG**:
* Added implementation details for the `list` and `alert` commands
* Added sequence diagrams for `ListCommand`, `AlertParser`, `AddAlertCommand` and `RemoveAlertCommand`
* Added UML diagrams for `list` and `alert` commands


**Contributions to team-based tasks**:
* Maintaining of issue tracker
* General code style checks
* Added Quick Start guide to UG
* Managed release for v2.1 


**Review/mentoring contributions**:
* [PRs reviewed](https://github.com/AY2223S2-CS2113-W12-3/tp/pulls?q=reviewed-by%3Aysl-28)

**Contributions beyond the project team**:
* Found 12 bugs during PE-D (above-average)


**DG Diagrams**  


![AlertParser.png](..%2FUML%2FAlert%2FAlertParser.png)


![AlertStep1.png](..%2FUML%2FAlert%2FAlertStep1.png)
![AlertStep3Add.png](..%2FUML%2FAlert%2FAlertStep3Add.png)
![AlertStep3Remove.png](..%2FUML%2FAlert%2FAlertStep3Remove.png)

![AddAlertCommand.png](..%2FUML%2FAlert%2FAddAlertCommand.png)  

![RemoveAlertCommand.png](..%2FUML%2FAlert%2FRemoveAlertCommand.png)

![ListCommand.png](..%2FUML%2FList%2FListCommand.png)


![ListStep1.png](..%2FUML%2FList%2FListStep1.png)
![ListStep2.png](..%2FUML%2FList%2FListStep2.png)
![ListStep3.png](..%2FUML%2FList%2FListStep3.png)