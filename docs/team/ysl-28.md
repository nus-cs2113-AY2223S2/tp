# Yong Shan Ling's Project Portfolio Page

## Overview
MagusStock is a Java command-line interface (CLI) application designed for inventory management.
With MagusStock, the user can perform CRUD operations on inventory items, generate history reports, 
and read/write inventory data in CSV format.

## Summary of Contributions
Code contributed: [RepoSense link](https://nus-cs2113-ay2223s2.github.io/tp-dashboard/?search=w12-3&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2023-02-17&tabOpen=true&tabType=authorship&tabAuthor=ysl-28&tabRepo=AY2223S2-CS2113-W12-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

Enhancements implemented:
* Added `list` feature
  * Allows the user to view all items in the inventory in table form
  * Enhanced `list` feature by adding text wrapping for content in the table
  * Created `ListCommand` and `ListParser` classes
* Added `alert` feature
  * Allows the user to set alerts to be printed when the quantity of an item falls below a minimum or exceeds a maximum
  * Allows the user to remove previously-added alerts and view existing alerts in table form
  * Created `Alert` and `AlertList` 
  * Created `AddAlertCommand`, `RemoveAlertCommand` and `AlertParser` classes
* Javadoc comments for classes and methods
* JUnit tests for `ListCommand`, `AddAlertCommand`, `RemoveAlertCommand` and `AlertParser`


Contributions to the UG:
* Added documentation for the `list` and `alert` commands
* Added command summary 
* Updated sample output of all UG commands to match updated output of `list` command
* Fixed typos and formatting errors in the UG


Contributions to the DG:
* Added implementation details for the `list` and `alert` commands
* Added sequence diagrams and UML diagrams for the `list` and `alert` commands


Contributions to team-based tasks:
* Maintaining of issue tracker
* Added Quick Start guide to UG


Review/mentoring contributions:
* [PRs reviewed](https://github.com/AY2223S2-CS2113-W12-3/tp/pulls?q=reviewed-by%3Aysl-28)

